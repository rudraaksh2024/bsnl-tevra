package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.HashSet;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

public class PrettyPrinter {
    private static final int DEFAULT_INDENT = 2;

    public static void printUsage() {
        System.out.println("Pretty prints XML files.");
        System.out.println("Usage: xpretty [switches] file.xml");
        System.out.println("Switches:");
        System.out.println("    -indent #   use the given indent");
        System.out.println("    -license prints license information");
    }

    public static void main(String[] strArr) {
        int i;
        HashSet hashSet = new HashSet();
        hashSet.add("h");
        hashSet.add("help");
        hashSet.add("usage");
        hashSet.add("license");
        hashSet.add("version");
        CommandLine commandLine = new CommandLine(strArr, hashSet, Collections.singleton("indent"));
        if (commandLine.getOpt("h") == null && commandLine.getOpt("help") == null && commandLine.getOpt("usage") == null) {
            String[] badOpts = commandLine.getBadOpts();
            if (badOpts.length > 0) {
                for (int i2 = 0; i2 < badOpts.length; i2++) {
                    System.out.println("Unrecognized option: " + badOpts[i2]);
                }
                printUsage();
                System.exit(0);
            } else if (commandLine.getOpt("license") != null) {
                CommandLine.printLicense();
                System.exit(0);
            } else if (commandLine.getOpt("version") != null) {
                CommandLine.printVersion();
                System.exit(0);
            } else if (commandLine.args().length == 0) {
                printUsage();
            } else {
                String opt = commandLine.getOpt("indent");
                if (opt == null) {
                    i = 2;
                } else {
                    i = Integer.parseInt(opt);
                }
                File[] files = commandLine.getFiles();
                for (int i3 = 0; i3 < files.length; i3++) {
                    try {
                        try {
                            XmlObject.Factory.parse(files[i3], new XmlOptions().setLoadLineNumbers()).save((OutputStream) System.out, new XmlOptions().setSavePrettyPrint().setSavePrettyPrintIndent(i));
                        } catch (IOException e) {
                            System.err.println("Unable to pretty print " + files[i3] + ": " + e.getMessage());
                        }
                    } catch (Exception e2) {
                        System.err.println(files[i3] + " not loadable: " + e2.getMessage());
                    }
                }
            }
        } else {
            printUsage();
            System.exit(0);
        }
    }

    public static String indent(String str) throws IOException, XmlException {
        StringWriter stringWriter = new StringWriter();
        XmlObject.Factory.parse(str, new XmlOptions().setLoadLineNumbers()).save((Writer) stringWriter, new XmlOptions().setSavePrettyPrint().setSavePrettyPrintIndent(2));
        stringWriter.close();
        return stringWriter.getBuffer().toString();
    }
}
