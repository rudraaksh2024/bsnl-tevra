package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.util.TempFile;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.SystemProperties;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.repackage.Repackager;
import org.apache.xmlbeans.impl.util.FilerImpl;

public class SchemaCodeGenerator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static Set deleteFileQueue = new HashSet();
    private static int triesRemaining = 0;

    public static void saveTypeSystem(SchemaTypeSystem schemaTypeSystem, File file, File file2, Repackager repackager, XmlOptions xmlOptions) throws IOException {
        schemaTypeSystem.save(new FilerImpl(file, (File) null, repackager, false, false));
    }

    static void deleteObsoleteFiles(File file, File file2, Set set) {
        if (!file.isDirectory() || !file2.isDirectory()) {
            throw new IllegalArgumentException();
        }
        String absolutePath = file2.getAbsolutePath();
        if (absolutePath.length() > 5) {
            if (!absolutePath.startsWith("/home/") || (absolutePath.indexOf(PackagingURIHelper.FORWARD_SLASH_STRING, 6) < absolutePath.length() - 1 && absolutePath.indexOf(PackagingURIHelper.FORWARD_SLASH_STRING, 6) >= 0)) {
                File[] listFiles = file2.listFiles();
                for (int i = 0; i < listFiles.length; i++) {
                    if (listFiles[i].isDirectory()) {
                        deleteObsoleteFiles(file, listFiles[i], set);
                    } else if (!set.contains(listFiles[i])) {
                        deleteXmlBeansFile(listFiles[i]);
                        deleteDirRecursively(file, listFiles[i].getParentFile());
                    }
                }
            }
        }
    }

    private static void deleteXmlBeansFile(File file) {
        if (file.getName().endsWith(".java")) {
            file.delete();
        }
    }

    private static void deleteDirRecursively(File file, File file2) {
        String[] list = file2.list();
        while (list != null && list.length == 0 && !file2.equals(file)) {
            file2.delete();
            file2 = file2.getParentFile();
            list = file2.list();
        }
    }

    protected static File createTempDir() throws IOException {
        String str;
        try {
            new File(SystemProperties.getProperty(TempFile.JAVA_IO_TMPDIR)).mkdirs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = null;
        File createTempFile = File.createTempFile("xbean", (String) null);
        String absolutePath = createTempFile.getAbsolutePath();
        if (absolutePath.endsWith(".tmp")) {
            int i = 0;
            String substring = absolutePath.substring(0, absolutePath.length() - 4);
            while (true) {
                if (i >= 100) {
                    break;
                }
                StringBuilder append = new StringBuilder().append(substring).append(".d");
                if (i == 0) {
                    str = "";
                } else {
                    String num = Integer.toString(i);
                    i++;
                    str = num;
                }
                File file2 = new File(append.append(str).toString());
                if (!file2.exists()) {
                    file2.mkdirs();
                    file = file2;
                    break;
                }
                i++;
                file = file2;
            }
            createTempFile.deleteOnExit();
            return file;
        }
        throw new IOException("Error: createTempFile did not create a file ending with .tmp");
    }

    protected static void tryHardToDelete(File file) {
        tryToDelete(file);
        if (file.exists()) {
            tryToDeleteLater(file);
        }
    }

    private static void tryToDelete(File file) {
        String[] list;
        if (file.exists()) {
            if (file.isDirectory() && (list = file.list()) != null) {
                for (String file2 : list) {
                    tryToDelete(new File(file, file2));
                }
            }
            file.delete();
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0056  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean tryNowThatItsLater() {
        /*
            java.util.Set r0 = deleteFileQueue
            monitor-enter(r0)
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x005c }
            java.util.Set r2 = deleteFileQueue     // Catch:{ all -> 0x005c }
            r1.<init>(r2)     // Catch:{ all -> 0x005c }
            java.util.Set r2 = deleteFileQueue     // Catch:{ all -> 0x005c }
            r2.clear()     // Catch:{ all -> 0x005c }
            monitor-exit(r0)     // Catch:{ all -> 0x005c }
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r1 = r1.iterator()
        L_0x0019:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0032
            java.lang.Object r2 = r1.next()
            java.io.File r2 = (java.io.File) r2
            tryToDelete(r2)
            boolean r3 = r2.exists()
            if (r3 == 0) goto L_0x0019
            r0.add(r2)
            goto L_0x0019
        L_0x0032:
            java.util.Set r1 = deleteFileQueue
            monitor-enter(r1)
            int r2 = triesRemaining     // Catch:{ all -> 0x0059 }
            r3 = 1
            if (r2 <= 0) goto L_0x003d
            int r2 = r2 - r3
            triesRemaining = r2     // Catch:{ all -> 0x0059 }
        L_0x003d:
            int r2 = triesRemaining     // Catch:{ all -> 0x0059 }
            r4 = 0
            if (r2 <= 0) goto L_0x004f
            int r2 = r0.size()     // Catch:{ all -> 0x0059 }
            if (r2 != 0) goto L_0x0049
            goto L_0x004f
        L_0x0049:
            java.util.Set r2 = deleteFileQueue     // Catch:{ all -> 0x0059 }
            r2.addAll(r0)     // Catch:{ all -> 0x0059 }
            goto L_0x0051
        L_0x004f:
            triesRemaining = r4     // Catch:{ all -> 0x0059 }
        L_0x0051:
            int r0 = triesRemaining     // Catch:{ all -> 0x0059 }
            if (r0 > 0) goto L_0x0056
            goto L_0x0057
        L_0x0056:
            r3 = r4
        L_0x0057:
            monitor-exit(r1)     // Catch:{ all -> 0x0059 }
            return r3
        L_0x0059:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0059 }
            throw r0
        L_0x005c:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x005c }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.tool.SchemaCodeGenerator.tryNowThatItsLater():boolean");
    }

    /* access modifiers changed from: private */
    public static void giveUp() {
        synchronized (deleteFileQueue) {
            deleteFileQueue.clear();
            triesRemaining = 0;
        }
    }

    private static void tryToDeleteLater(File file) {
        synchronized (deleteFileQueue) {
            deleteFileQueue.add(file);
            if (triesRemaining == 0) {
                new Thread() {
                    public void run() {
                        while (!SchemaCodeGenerator.tryNowThatItsLater()) {
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException unused) {
                                SchemaCodeGenerator.giveUp();
                                return;
                            }
                        }
                    }
                };
            }
            if (triesRemaining < 10) {
                triesRemaining = 10;
            }
        }
    }
}
