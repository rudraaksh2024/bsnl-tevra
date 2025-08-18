package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

public class InstanceValidator {
    public static void printUsage() {
        System.out.println("Validates the specified instance against the specified schema.");
        System.out.println("Contrast with the svalidate tool, which validates using a stream.");
        System.out.println("Usage: validate [-dl] [-nopvr] [-noupa] [-license] schema.xsd instance.xml");
        System.out.println("Options:");
        System.out.println("    -dl - permit network downloads for imports and includes (default is off)");
        System.out.println("    -noupa - do not enforce the unique particle attribution rule");
        System.out.println("    -nopvr - do not enforce the particle valid (restriction) rule");
        System.out.println("    -strict - performs strict(er) validation");
        System.out.println("    -partial - allow partial schema type system");
        System.out.println("    -license - prints license information");
    }

    public static void main(String[] strArr) {
        System.exit(extraMain(strArr));
    }

    public static int extraMain(String[] strArr) {
        int i;
        XmlOptions xmlOptions;
        String[] strArr2 = strArr;
        HashSet hashSet = new HashSet();
        hashSet.add("h");
        hashSet.add("help");
        hashSet.add("usage");
        hashSet.add("license");
        hashSet.add("version");
        hashSet.add("dl");
        hashSet.add("noupa");
        hashSet.add("nopvr");
        hashSet.add("strict");
        hashSet.add("partial");
        CommandLine commandLine = new CommandLine(strArr2, hashSet, Collections.EMPTY_SET);
        int i2 = 0;
        if (commandLine.getOpt("h") == null && commandLine.getOpt("help") == null && commandLine.getOpt("usage") == null && strArr2.length >= 1) {
            String[] badOpts = commandLine.getBadOpts();
            if (badOpts.length > 0) {
                for (int i3 = 0; i3 < badOpts.length; i3++) {
                    System.out.println("Unrecognized option: " + badOpts[i3]);
                }
                printUsage();
                return 0;
            } else if (commandLine.getOpt("license") != null) {
                CommandLine.printLicense();
                return 0;
            } else if (commandLine.getOpt("version") != null) {
                CommandLine.printVersion();
                return 0;
            } else if (commandLine.args().length == 0) {
                return 0;
            } else {
                boolean z = commandLine.getOpt("dl") != null;
                boolean z2 = commandLine.getOpt("nopvr") != null;
                boolean z3 = commandLine.getOpt("noupa") != null;
                boolean z4 = commandLine.getOpt("strict") != null;
                boolean z5 = commandLine.getOpt("partial") != null;
                File[] filesEndingWith = commandLine.filesEndingWith(".xsd");
                File[] filesEndingWith2 = commandLine.filesEndingWith(".xml");
                File[] filesEndingWith3 = commandLine.filesEndingWith(".jar");
                ArrayList arrayList = new ArrayList();
                int length = filesEndingWith.length;
                int i4 = 0;
                while (i4 < length) {
                    File file = filesEndingWith[i4];
                    try {
                        arrayList.add(XmlObject.Factory.parse(file, new XmlOptions().setLoadLineNumbers().setLoadMessageDigest()));
                    } catch (Exception e) {
                        System.err.println(file + " not loadable: " + e);
                    }
                    i4++;
                    i2 = 0;
                }
                XmlObject[] xmlObjectArr = (XmlObject[]) arrayList.toArray(new XmlObject[i2]);
                ArrayList<XmlError> arrayList2 = new ArrayList<>();
                XmlOptions xmlOptions2 = new XmlOptions();
                xmlOptions2.setErrorListener(arrayList2);
                if (z) {
                    xmlOptions2.setCompileDownloadUrls();
                }
                if (z2) {
                    xmlOptions2.setCompileNoPvrRule();
                }
                if (z3) {
                    xmlOptions2.setCompileNoUpaRule();
                }
                if (z5) {
                    xmlOptions2.setCompilePartialTypesystem();
                }
                SchemaTypeLoader typeLoaderForResource = (filesEndingWith3 == null || filesEndingWith3.length <= 0) ? null : XmlBeans.typeLoaderForResource(XmlBeans.resourceLoaderForPath(filesEndingWith3));
                if (xmlObjectArr != null) {
                    try {
                        if (xmlObjectArr.length > 0) {
                            typeLoaderForResource = XmlBeans.compileXsd(xmlObjectArr, typeLoaderForResource, xmlOptions2);
                        }
                    } catch (Exception e2) {
                        if (arrayList2.isEmpty() || !(e2 instanceof XmlException)) {
                            e2.printStackTrace(System.err);
                        }
                        System.out.println("Schema invalid:".concat(z5 ? " couldn't recover from errors" : ""));
                        for (XmlError println : arrayList2) {
                            System.out.println(println);
                        }
                        return 10;
                    }
                }
                if (!z5 || arrayList2.isEmpty()) {
                    i = 0;
                } else {
                    System.out.println("Schema invalid: partial schema type system recovered");
                    for (XmlError println2 : arrayList2) {
                        System.out.println(println2);
                    }
                    i = 11;
                }
                if (typeLoaderForResource == null) {
                    typeLoaderForResource = XmlBeans.getContextTypeLoader();
                }
                for (File file2 : filesEndingWith2) {
                    try {
                        XmlOptions xmlOptions3 = new XmlOptions();
                        xmlOptions3.setLoadLineNumbersEndElement();
                        XmlObject parse = typeLoaderForResource.parse(file2, (SchemaType) null, xmlOptions3);
                        ArrayList<XmlError> arrayList3 = new ArrayList<>();
                        if (parse.schemaType() == XmlObject.type) {
                            System.out.println(file2 + " NOT valid.  ");
                            System.out.println("  Document type not found.");
                        } else {
                            if (z4) {
                                xmlOptions = new XmlOptions().setErrorListener(arrayList3).setValidateStrict();
                            } else {
                                xmlOptions = new XmlOptions().setErrorListener(arrayList3);
                            }
                            if (parse.validate(xmlOptions)) {
                                System.out.println(file2 + " valid.");
                            } else {
                                System.out.println(file2 + " NOT valid.");
                                for (XmlError println3 : arrayList3) {
                                    System.out.println(println3);
                                }
                                i = 1;
                            }
                        }
                    } catch (Exception e3) {
                        System.err.println(file2 + " not loadable: " + e3);
                        e3.printStackTrace(System.err);
                    }
                }
                return i;
            }
        } else {
            printUsage();
            return 0;
        }
    }
}
