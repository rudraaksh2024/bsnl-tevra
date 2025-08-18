package org.apache.xmlbeans.impl.inst2xsd;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.inst2xsd.util.TypeSystemHolder;
import org.apache.xmlbeans.impl.tool.CommandLine;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;

public class Inst2Xsd {
    public static void main(String[] strArr) {
        String[] strArr2 = strArr;
        if (strArr2 == null || strArr2.length == 0) {
            printHelp();
            System.exit(0);
            return;
        }
        HashSet hashSet = new HashSet();
        hashSet.add("h");
        hashSet.add("help");
        hashSet.add("usage");
        hashSet.add("license");
        hashSet.add("version");
        hashSet.add("verbose");
        hashSet.add("validate");
        HashSet hashSet2 = new HashSet();
        hashSet2.add("design");
        hashSet2.add("simple-content-types");
        hashSet2.add("enumerations");
        hashSet2.add("outDir");
        hashSet2.add("outPrefix");
        String str = "'. ";
        CommandLine commandLine = new CommandLine(strArr2, hashSet, hashSet2);
        Inst2XsdOptions inst2XsdOptions = new Inst2XsdOptions();
        if (commandLine.getOpt("license") != null) {
            CommandLine.printLicense();
            System.exit(0);
        } else if (commandLine.getOpt("version") != null) {
            CommandLine.printVersion();
            System.exit(0);
        } else if (commandLine.getOpt("h") == null && commandLine.getOpt("help") == null && commandLine.getOpt("usage") == null) {
            String[] badOpts = commandLine.getBadOpts();
            if (badOpts.length > 0) {
                for (int i = 0; i < badOpts.length; i++) {
                    System.out.println("Unrecognized option: " + badOpts[i]);
                }
                printHelp();
                System.exit(0);
                return;
            }
            String opt = commandLine.getOpt("design");
            boolean z = true;
            if (opt != null) {
                if (opt.equals("vb")) {
                    inst2XsdOptions.setDesign(3);
                } else if (opt.equals("rd")) {
                    inst2XsdOptions.setDesign(1);
                } else if (opt.equals("ss")) {
                    inst2XsdOptions.setDesign(2);
                } else {
                    printHelp();
                    System.exit(0);
                    return;
                }
            }
            String opt2 = commandLine.getOpt("simple-content-types");
            if (opt2 != null) {
                if (opt2.equals("smart")) {
                    inst2XsdOptions.setSimpleContentTypes(1);
                } else if (opt2.equals(TypedValues.Custom.S_STRING)) {
                    inst2XsdOptions.setSimpleContentTypes(2);
                } else {
                    printHelp();
                    System.exit(0);
                    return;
                }
            }
            String opt3 = commandLine.getOpt("enumerations");
            if (opt3 != null) {
                if (opt3.equals("never")) {
                    inst2XsdOptions.setUseEnumerations(1);
                } else {
                    try {
                        inst2XsdOptions.setUseEnumerations(Integer.parseInt(opt3));
                    } catch (NumberFormatException unused) {
                        printHelp();
                        System.exit(0);
                        return;
                    }
                }
            }
            File file = new File(commandLine.getOpt("outDir") == null ? "." : commandLine.getOpt("outDir"));
            String opt4 = commandLine.getOpt("outPrefix");
            if (opt4 == null) {
                opt4 = "schema";
            }
            inst2XsdOptions.setVerbose(commandLine.getOpt("verbose") != null);
            if (commandLine.getOpt("validate") == null) {
                z = false;
            }
            File[] filesEndingWith = commandLine.filesEndingWith(".xml");
            int length = filesEndingWith.length;
            XmlObject[] xmlObjectArr = new XmlObject[length];
            if (length == 0) {
                printHelp();
                System.exit(0);
                return;
            }
            int i2 = 0;
            while (i2 < filesEndingWith.length) {
                try {
                    xmlObjectArr[i2] = XmlObject.Factory.parse(filesEndingWith[i2]);
                    i2++;
                } catch (XmlException e) {
                    System.err.println("Invalid xml file: '" + filesEndingWith[i2].getName() + str + e.getMessage());
                    return;
                } catch (IOException e2) {
                    System.err.println("Could not read file: '" + filesEndingWith[i2].getName() + str + e2.getMessage());
                    return;
                }
            }
            SchemaDocument[] inst2xsd = inst2xsd(xmlObjectArr, inst2XsdOptions);
            int i3 = 0;
            while (i3 < inst2xsd.length) {
                try {
                    SchemaDocument schemaDocument = inst2xsd[i3];
                    if (inst2XsdOptions.isVerbose()) {
                        System.out.println("----------------------\n\n" + schemaDocument);
                    }
                    schemaDocument.save(new File(file, opt4 + i3 + ".xsd"), new XmlOptions().setSavePrettyPrint());
                    i3++;
                } catch (IOException e3) {
                    System.err.println("Could not write file: '" + file + File.pathSeparator + opt4 + i3 + ".xsd'. " + e3.getMessage());
                    return;
                }
            }
            if (z) {
                validateInstances(inst2xsd, xmlObjectArr);
            }
        } else {
            printHelp();
            System.exit(0);
        }
    }

    private static void printHelp() {
        System.out.println("Generates XMLSchema from instance xml documents.");
        System.out.println("Usage: inst2xsd [opts] [instance.xml]*");
        System.out.println("Options include:");
        System.out.println("    -design [rd|ss|vb] - XMLSchema design type");
        System.out.println("             rd  - Russian Doll Design - local elements and local types");
        System.out.println("             ss  - Salami Slice Design - global elements and local types");
        System.out.println("             vb  - Venetian Blind Design (default) - local elements and global complex types");
        System.out.println("    -simple-content-types [smart|string] - Simple content types detection (leaf text). Smart is the default");
        System.out.println("    -enumerations [never|NUMBER] - Use enumerations. Default value is 10.");
        System.out.println("    -outDir [dir] - Directory for output files. Default is '.'");
        System.out.println("    -outPrefix [file_name_prefix] - Prefix for output file names. Default is 'schema'");
        System.out.println("    -validate - Validates input instances agaist generated schemas.");
        System.out.println("    -verbose - print more informational messages");
        System.out.println("    -license - print license information");
        System.out.println("    -help - help imformation");
    }

    private Inst2Xsd() {
    }

    public static SchemaDocument[] inst2xsd(Reader[] readerArr, Inst2XsdOptions inst2XsdOptions) throws IOException, XmlException {
        XmlObject[] xmlObjectArr = new XmlObject[readerArr.length];
        for (int i = 0; i < readerArr.length; i++) {
            xmlObjectArr[i] = XmlObject.Factory.parse(readerArr[i]);
        }
        return inst2xsd(xmlObjectArr, inst2XsdOptions);
    }

    public static SchemaDocument[] inst2xsd(XmlObject[] xmlObjectArr, Inst2XsdOptions inst2XsdOptions) {
        XsdGenStrategy xsdGenStrategy;
        if (inst2XsdOptions == null) {
            inst2XsdOptions = new Inst2XsdOptions();
        }
        TypeSystemHolder typeSystemHolder = new TypeSystemHolder();
        int design = inst2XsdOptions.getDesign();
        if (design == 1) {
            xsdGenStrategy = new RussianDollStrategy();
        } else if (design == 2) {
            xsdGenStrategy = new SalamiSliceStrategy();
        } else if (design == 3) {
            xsdGenStrategy = new VenetianBlindStrategy();
        } else {
            throw new IllegalArgumentException("Unknown design.");
        }
        xsdGenStrategy.processDoc(xmlObjectArr, inst2XsdOptions, typeSystemHolder);
        if (inst2XsdOptions.isVerbose()) {
            System.out.println("typeSystemHolder.toString(): " + typeSystemHolder);
        }
        return typeSystemHolder.getSchemaDocuments();
    }

    private static boolean validateInstances(SchemaDocument[] schemaDocumentArr, XmlObject[] xmlObjectArr) {
        ArrayList<XmlError> arrayList = new ArrayList<>();
        XmlOptions xmlOptions = new XmlOptions();
        xmlOptions.setErrorListener(arrayList);
        try {
            SchemaTypeLoader loadXsd = XmlBeans.loadXsd(schemaDocumentArr, xmlOptions);
            System.out.println("\n-------------------");
            boolean z = true;
            for (int i = 0; i < xmlObjectArr.length; i++) {
                try {
                    XmlObject parse = loadXsd.parse(xmlObjectArr[i].newXMLStreamReader(), (SchemaType) null, new XmlOptions().setLoadLineNumbers());
                    ArrayList<XmlError> arrayList2 = new ArrayList<>();
                    if (parse.schemaType() == XmlObject.type) {
                        System.out.println(xmlObjectArr[i].documentProperties().getSourceName() + " NOT valid.  ");
                        System.out.println("  Document type not found.");
                    } else if (parse.validate(new XmlOptions().setErrorListener(arrayList2))) {
                        System.out.println("Instance[" + i + "] valid - " + xmlObjectArr[i].documentProperties().getSourceName());
                    } else {
                        System.out.println("Instance[" + i + "] NOT valid - " + xmlObjectArr[i].documentProperties().getSourceName());
                        for (XmlError xmlError : arrayList2) {
                            System.out.println(xmlError.getLine() + ParameterizedMessage.ERROR_MSG_SEPARATOR + xmlError.getColumn() + " " + xmlError.getMessage());
                        }
                    }
                } catch (XmlException e) {
                    System.out.println("Error:\n" + xmlObjectArr[i].documentProperties().getSourceName() + " not loadable: " + e);
                    e.printStackTrace(System.out);
                }
                z = false;
            }
            return z;
        } catch (Exception e2) {
            if (arrayList.isEmpty() || !(e2 instanceof XmlException)) {
                e2.printStackTrace(System.out);
            }
            System.out.println("\n-------------------\n\nInvalid schemas.");
            for (XmlError xmlError2 : arrayList) {
                System.out.println(xmlError2.getLine() + ParameterizedMessage.ERROR_MSG_SEPARATOR + xmlError2.getColumn() + " " + xmlError2.getMessage());
            }
            return false;
        }
    }
}
