package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.xmlbeans.impl.common.IOUtil;

public class SchemaResourceManager extends BaseSchemaResourceManager {
    private File _directory;

    public static void printUsage() {
        System.out.println("Maintains \"xsdownload.xml\", an index of locally downloaded .xsd files");
        System.out.println("usage: sdownload [-dir directory] [-refresh] [-recurse] [-sync] [url/file...]");
        System.out.println("");
        System.out.println("URLs that are specified are downloaded if they aren't already cached.");
        System.out.println("In addition:");
        System.out.println("  -dir specifies the directory for the xsdownload.xml file (default .).");
        System.out.println("  -sync synchronizes the index to any local .xsd files in the tree.");
        System.out.println("  -recurse recursively downloads imported and included .xsd files.");
        System.out.println("  -refresh redownloads all indexed .xsd files.");
        System.out.println("If no files or URLs are specified, all indexed files are relevant.");
    }

    public static void main(String[] strArr) throws IOException {
        String[] strArr2 = strArr;
        if (strArr2.length == 0) {
            printUsage();
            System.exit(0);
            return;
        }
        HashSet hashSet = new HashSet();
        hashSet.add("h");
        hashSet.add("help");
        hashSet.add("usage");
        hashSet.add("license");
        hashSet.add("version");
        hashSet.add("sync");
        hashSet.add("refresh");
        hashSet.add("recurse");
        HashSet hashSet2 = new HashSet();
        hashSet2.add("dir");
        CommandLine commandLine = new CommandLine(strArr2, hashSet, hashSet2);
        if (commandLine.getOpt("h") == null && commandLine.getOpt("help") == null && commandLine.getOpt("usage") == null) {
            String[] badOpts = commandLine.getBadOpts();
            if (badOpts.length > 0) {
                for (int i = 0; i < badOpts.length; i++) {
                    System.out.println("Unrecognized option: " + badOpts[i]);
                }
                printUsage();
                System.exit(0);
            } else if (commandLine.getOpt("license") != null) {
                CommandLine.printLicense();
                System.exit(0);
            } else if (commandLine.getOpt("version") != null) {
                CommandLine.printVersion();
                System.exit(0);
            } else {
                String[] args = commandLine.args();
                boolean z = commandLine.getOpt("sync") != null;
                boolean z2 = commandLine.getOpt("refresh") != null;
                boolean z3 = commandLine.getOpt("recurse") != null;
                String opt = commandLine.getOpt("dir");
                if (opt == null) {
                    opt = ".";
                }
                File file = new File(opt);
                try {
                    SchemaResourceManager schemaResourceManager = new SchemaResourceManager(file);
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < args.length; i2++) {
                        if (looksLikeURL(args[i2])) {
                            arrayList.add(args[i2]);
                        } else {
                            arrayList2.add(new File(file, args[i2]));
                        }
                    }
                    Iterator it = arrayList2.iterator();
                    while (it.hasNext()) {
                        File file2 = (File) it.next();
                        if (!isInDirectory(file2, file)) {
                            System.err.println("File not within directory: " + file2);
                            it.remove();
                        }
                    }
                    List collectXSDFiles = collectXSDFiles((File[]) arrayList2.toArray(new File[0]));
                    String[] strArr3 = (String[]) arrayList.toArray(new String[0]);
                    String[] relativeFilenames = relativeFilenames((File[]) collectXSDFiles.toArray(new File[0]), file);
                    if (strArr3.length + relativeFilenames.length > 0) {
                        schemaResourceManager.process(strArr3, relativeFilenames, z, z2, z3);
                    } else {
                        schemaResourceManager.processAll(z, z2, z3);
                    }
                    schemaResourceManager.writeCache();
                    System.exit(0);
                } catch (IllegalStateException e) {
                    if (e.getMessage() != null) {
                        System.out.println(e.getMessage());
                    } else {
                        e.printStackTrace();
                    }
                    System.exit(1);
                }
            }
        } else {
            printUsage();
            System.exit(0);
        }
    }

    private static boolean looksLikeURL(String str) {
        return str.startsWith("http:") || str.startsWith("https:") || str.startsWith("ftp:") || str.startsWith("file:");
    }

    private static String relativeFilename(File file, File file2) {
        return (file == null || file.equals(file2)) ? "." : relativeFilename(file.getParentFile(), file2) + PackagingURIHelper.FORWARD_SLASH_STRING + file.getName();
    }

    private static String[] relativeFilenames(File[] fileArr, File file) {
        String[] strArr = new String[fileArr.length];
        for (int i = 0; i < fileArr.length; i++) {
            strArr[i] = relativeFilename(fileArr[i], file);
        }
        return strArr;
    }

    private static boolean isInDirectory(File file, File file2) {
        if (file == null) {
            return false;
        }
        if (file.equals(file2)) {
            return true;
        }
        return isInDirectory(file.getParentFile(), file2);
    }

    public SchemaResourceManager(File file) {
        this._directory = file;
        init();
    }

    /* access modifiers changed from: protected */
    public void warning(String str) {
        System.out.println(str);
    }

    /* access modifiers changed from: protected */
    public boolean fileExists(String str) {
        return new File(this._directory, str).exists();
    }

    /* access modifiers changed from: protected */
    public InputStream inputStreamForFile(String str) throws IOException {
        return new FileInputStream(new File(this._directory, str));
    }

    /* access modifiers changed from: protected */
    public void writeInputStreamToFile(InputStream inputStream, String str) throws IOException {
        File file = new File(this._directory, str);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        IOUtil.copyCompletely(inputStream, (OutputStream) new FileOutputStream(file));
    }

    /* access modifiers changed from: protected */
    public void deleteFile(String str) {
        new File(this._directory, str).delete();
    }

    /* access modifiers changed from: protected */
    public String[] getAllXSDFilenames() {
        return relativeFilenames((File[]) collectXSDFiles(new File[]{this._directory}).toArray(new File[0]), this._directory);
    }

    private static List collectXSDFiles(File[] fileArr) {
        ArrayList arrayList = new ArrayList();
        for (File file : fileArr) {
            if (!file.isDirectory()) {
                arrayList.add(file);
            } else {
                arrayList.addAll(collectXSDFiles(file.listFiles(new FileFilter() {
                    public boolean accept(File file) {
                        return file.isDirectory() || (file.isFile() && file.getName().endsWith(".xsd"));
                    }
                })));
            }
        }
        return arrayList;
    }
}
