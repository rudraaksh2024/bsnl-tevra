package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

public class StreamInstanceValidator {
    public static void printUsage() {
        System.out.println("Validates the specified instance against the specified schema.");
        System.out.println("A streaming validation useful for validating very large instance ");
        System.out.println("documents with less memory. Contrast with the validate tool.");
        System.out.println("Usage: svalidate [-dl] [-nopvr] [-noupa] [-license] schema.xsd instance.xml");
        System.out.println("Options:");
        System.out.println("    -dl - permit network downloads for imports and includes (default is off)");
        System.out.println("    -noupa - do not enforce the unique particle attribution rule");
        System.out.println("    -nopvr - do not enforce the particle valid (restriction) rule");
        System.out.println("    -license - prints license information");
    }

    public static void main(String[] strArr) {
        HashSet hashSet = new HashSet();
        hashSet.add("h");
        hashSet.add("help");
        hashSet.add("usage");
        hashSet.add("license");
        hashSet.add("version");
        hashSet.add("dl");
        hashSet.add("noupr");
        hashSet.add("noupa");
        CommandLine commandLine = new CommandLine(strArr, hashSet, Collections.EMPTY_SET);
        if (commandLine.getOpt("h") == null && commandLine.getOpt("help") == null && commandLine.getOpt("usage") == null) {
            boolean z = true;
            if (strArr.length >= 1) {
                String[] badOpts = commandLine.getBadOpts();
                if (badOpts.length > 0) {
                    for (int i = 0; i < badOpts.length; i++) {
                        System.out.println("Unrecognized option: " + badOpts[i]);
                    }
                    printUsage();
                    System.exit(0);
                    return;
                } else if (commandLine.getOpt("license") != null) {
                    CommandLine.printLicense();
                    System.exit(0);
                    return;
                } else if (commandLine.getOpt("version") != null) {
                    CommandLine.printVersion();
                    System.exit(0);
                    return;
                } else if (commandLine.args().length == 0) {
                    printUsage();
                    return;
                } else {
                    boolean z2 = commandLine.getOpt("dl") != null;
                    boolean z3 = commandLine.getOpt("nopvr") != null;
                    if (commandLine.getOpt("noupa") == null) {
                        z = false;
                    }
                    File[] filesEndingWith = commandLine.filesEndingWith(".xsd");
                    File[] filesEndingWith2 = commandLine.filesEndingWith(".xml");
                    File[] filesEndingWith3 = commandLine.filesEndingWith(".jar");
                    ArrayList arrayList = new ArrayList();
                    XmlOptions loadLineNumbers = new XmlOptions().setLoadLineNumbers();
                    for (int i2 = 0; i2 < filesEndingWith.length; i2++) {
                        try {
                            arrayList.add(XmlObject.Factory.parse(filesEndingWith[i2], loadLineNumbers.setLoadMessageDigest()));
                        } catch (Exception e) {
                            System.err.println(filesEndingWith[i2] + " not loadable: " + e);
                        }
                    }
                    XmlObject[] xmlObjectArr = (XmlObject[]) arrayList.toArray(new XmlObject[0]);
                    ArrayList<Object> arrayList2 = new ArrayList<>();
                    XmlOptions xmlOptions = new XmlOptions();
                    xmlOptions.setErrorListener(arrayList2);
                    if (z2) {
                        xmlOptions.setCompileDownloadUrls();
                    }
                    if (z3) {
                        xmlOptions.setCompileNoPvrRule();
                    }
                    if (z) {
                        xmlOptions.setCompileNoUpaRule();
                    }
                    SchemaTypeLoader typeLoaderForResource = (filesEndingWith3 == null || filesEndingWith3.length <= 0) ? null : XmlBeans.typeLoaderForResource(XmlBeans.resourceLoaderForPath(filesEndingWith3));
                    if (xmlObjectArr != null) {
                        try {
                            if (xmlObjectArr.length > 0) {
                                typeLoaderForResource = XmlBeans.compileXsd(xmlObjectArr, typeLoaderForResource, xmlOptions);
                            }
                        } catch (Exception e2) {
                            if (arrayList2.isEmpty() || !(e2 instanceof XmlException)) {
                                e2.printStackTrace(System.err);
                            }
                            System.out.println("Schema invalid");
                            for (Object println : arrayList2) {
                                System.out.println(println);
                            }
                            return;
                        }
                    }
                    validateFiles(filesEndingWith2, typeLoaderForResource, loadLineNumbers);
                    return;
                }
            }
        }
        printUsage();
        System.exit(0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0065, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0067, code lost:
        r0 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0065 A[ExcHandler: Exception (e java.lang.Exception), Splitter:B:7:0x0020] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00d8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void validateFiles(java.io.File[] r18, org.apache.xmlbeans.SchemaTypeLoader r19, org.apache.xmlbeans.XmlOptions r20) {
        /*
            r1 = r18
            org.apache.xmlbeans.impl.validator.ValidatingXMLStreamReader r9 = new org.apache.xmlbeans.impl.validator.ValidatingXMLStreamReader
            r9.<init>()
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            r0 = 0
            r11 = r0
        L_0x000e:
            int r0 = r1.length
            if (r11 >= r0) goto L_0x0118
            r12 = r1[r11]
            java.lang.String r13 = r12.getPath()
            r10.clear()
            r2 = 0
            org.apache.xmlbeans.XmlOptions r0 = new org.apache.xmlbeans.XmlOptions     // Catch:{ XMLStreamException -> 0x0091, Exception -> 0x0069 }
            r14 = r20
            r0.<init>(r14)     // Catch:{ XMLStreamException -> 0x0067, Exception -> 0x0065 }
            javax.xml.stream.XMLInputFactory r0 = org.apache.xmlbeans.impl.common.StaxHelper.newXMLInputFactory(r0)     // Catch:{ XMLStreamException -> 0x0067, Exception -> 0x0065 }
            java.io.FileInputStream r15 = new java.io.FileInputStream     // Catch:{ XMLStreamException -> 0x0067, Exception -> 0x0065 }
            r15.<init>(r12)     // Catch:{ XMLStreamException -> 0x0067, Exception -> 0x0065 }
            javax.xml.stream.XMLStreamReader r0 = r0.createXMLStreamReader(r13, r15)     // Catch:{ XMLStreamException -> 0x0067, Exception -> 0x0065 }
        L_0x0030:
            boolean r4 = r0.isStartElement()     // Catch:{ XMLStreamException -> 0x0067, Exception -> 0x0065 }
            if (r4 != 0) goto L_0x003a
            r0.next()     // Catch:{ XMLStreamException -> 0x0067, Exception -> 0x0065 }
            goto L_0x0030
        L_0x003a:
            long r16 = java.lang.System.currentTimeMillis()     // Catch:{ XMLStreamException -> 0x0067, Exception -> 0x0065 }
            r4 = 1
            r5 = 0
            r2 = r9
            r3 = r0
            r6 = r19
            r7 = r20
            r8 = r10
            r2.init(r3, r4, r5, r6, r7, r8)     // Catch:{ XMLStreamException -> 0x0061, Exception -> 0x0065 }
        L_0x004a:
            boolean r0 = r9.hasNext()     // Catch:{ XMLStreamException -> 0x0061, Exception -> 0x0065 }
            if (r0 == 0) goto L_0x0054
            r9.next()     // Catch:{ XMLStreamException -> 0x0061, Exception -> 0x0065 }
            goto L_0x004a
        L_0x0054:
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ XMLStreamException -> 0x0061, Exception -> 0x0065 }
            long r2 = r2 - r16
            r9.close()     // Catch:{ XMLStreamException -> 0x0067, Exception -> 0x0065 }
            r15.close()     // Catch:{ XMLStreamException -> 0x0067, Exception -> 0x0065 }
            goto L_0x00af
        L_0x0061:
            r0 = move-exception
            r2 = r16
            goto L_0x0094
        L_0x0065:
            r0 = move-exception
            goto L_0x006c
        L_0x0067:
            r0 = move-exception
            goto L_0x0094
        L_0x0069:
            r0 = move-exception
            r14 = r20
        L_0x006c:
            java.io.PrintStream r2 = java.lang.System.err
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "error for file: "
            r3.<init>(r4)
            java.lang.StringBuilder r3 = r3.append(r12)
            java.lang.String r4 = ": "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r0)
            java.lang.String r3 = r3.toString()
            r2.println(r3)
            java.io.PrintStream r2 = java.lang.System.err
            r0.printStackTrace(r2)
            goto L_0x0114
        L_0x0091:
            r0 = move-exception
            r14 = r20
        L_0x0094:
            javax.xml.stream.Location r4 = r0.getLocation()
            java.lang.String r0 = r0.getMessage()
            int r5 = r4.getLineNumber()
            int r6 = r4.getColumnNumber()
            int r4 = r4.getCharacterOffset()
            org.apache.xmlbeans.XmlError r0 = org.apache.xmlbeans.XmlError.forLocation(r0, r13, r5, r6, r4)
            r10.add(r0)
        L_0x00af:
            boolean r0 = r10.isEmpty()
            if (r0 == 0) goto L_0x00d8
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.StringBuilder r4 = r4.append(r12)
            java.lang.String r5 = " valid. ("
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r2 = r4.append(r2)
            java.lang.String r3 = " ms)"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.println(r2)
            goto L_0x0114
        L_0x00d8:
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.StringBuilder r4 = r4.append(r12)
            java.lang.String r5 = " NOT valid ("
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r2 = r4.append(r2)
            java.lang.String r3 = " ms):"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.println(r2)
            java.util.Iterator r0 = r10.iterator()
        L_0x00fe:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0114
            java.lang.Object r2 = r0.next()
            org.apache.xmlbeans.XmlError r2 = (org.apache.xmlbeans.XmlError) r2
            java.io.PrintStream r3 = java.lang.System.out
            java.lang.String r2 = stringFromError(r2, r13)
            r3.println(r2)
            goto L_0x00fe
        L_0x0114:
            int r11 = r11 + 1
            goto L_0x000e
        L_0x0118:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.tool.StreamInstanceValidator.validateFiles(java.io.File[], org.apache.xmlbeans.SchemaTypeLoader, org.apache.xmlbeans.XmlOptions):void");
    }

    private static String stringFromError(XmlError xmlError, String str) {
        return XmlError.severityAsString(xmlError.getSeverity()) + ": " + str + ParameterizedMessage.ERROR_MSG_SEPARATOR + xmlError.getLine() + ParameterizedMessage.ERROR_MSG_SEPARATOR + xmlError.getColumn() + " " + xmlError.getMessage() + " ";
    }
}
