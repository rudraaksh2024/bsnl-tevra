package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;
import javax.xml.namespace.QName;

public class FactorImports {
    public static void printUsage() {
        System.out.println("Refactors a directory of XSD files to remove name conflicts.");
        System.out.println("Usage: sfactor [-import common.xsd] [-out outputdir] inputdir");
        System.out.println("    -import common.xsd - The XSD file to contain redundant ");
        System.out.println("                         definitions for importing.");
        System.out.println("    -out outputdir - The directory into which to place XSD ");
        System.out.println("                     files resulting from refactoring, ");
        System.out.println("                     plus a commonly imported common.xsd.");
        System.out.println("    inputdir - The directory containing the XSD files with");
        System.out.println("               redundant definitions.");
        System.out.println("    -license - Print license information.");
        System.out.println();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0216, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x021e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0220, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0221, code lost:
        r25 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0224, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0225, code lost:
        r25 = r1;
        r24 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x022a, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x022b, code lost:
        r25 = r1;
        r24 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0230, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0231, code lost:
        java.lang.System.err.println("Unable to load " + r5 + " - " + r0.getMessage());
        java.lang.System.exit(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0257, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0230 A[ExcHandler: IOException (r0v47 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:38:0x0113] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void main(java.lang.String[] r28) throws java.lang.Exception {
        /*
            r0 = r28
            java.lang.String r1 = "warning: "
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            java.lang.String r3 = "h"
            r2.add(r3)
            java.lang.String r4 = "help"
            r2.add(r4)
            java.lang.String r5 = "usage"
            r2.add(r5)
            java.lang.String r6 = "license"
            r2.add(r6)
            java.lang.String r7 = "version"
            r2.add(r7)
            org.apache.xmlbeans.impl.tool.CommandLine r8 = new org.apache.xmlbeans.impl.tool.CommandLine
            java.lang.String r9 = "import"
            java.lang.String r10 = "out"
            java.lang.String[] r11 = new java.lang.String[]{r9, r10}
            java.util.List r11 = java.util.Arrays.asList(r11)
            r8.<init>(r0, r2, r11)
            java.lang.String r2 = r8.getOpt(r3)
            r3 = 0
            if (r2 != 0) goto L_0x05eb
            java.lang.String r2 = r8.getOpt(r4)
            if (r2 != 0) goto L_0x05eb
            java.lang.String r2 = r8.getOpt(r5)
            if (r2 != 0) goto L_0x05eb
            int r0 = r0.length
            r2 = 1
            if (r0 >= r2) goto L_0x004c
            goto L_0x05eb
        L_0x004c:
            java.lang.String[] r0 = r8.getBadOpts()
            int r4 = r0.length
            if (r4 <= 0) goto L_0x0077
            int r1 = r0.length
            r2 = r3
        L_0x0055:
            if (r2 >= r1) goto L_0x0070
            r4 = r0[r2]
            java.io.PrintStream r5 = java.lang.System.out
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "Unrecognized option: "
            r6.<init>(r7)
            java.lang.StringBuilder r4 = r6.append(r4)
            java.lang.String r4 = r4.toString()
            r5.println(r4)
            int r2 = r2 + 1
            goto L_0x0055
        L_0x0070:
            printUsage()
            java.lang.System.exit(r3)
            return
        L_0x0077:
            java.lang.String r0 = r8.getOpt(r6)
            if (r0 == 0) goto L_0x0084
            org.apache.xmlbeans.impl.tool.CommandLine.printLicense()
            java.lang.System.exit(r3)
            return
        L_0x0084:
            java.lang.String r0 = r8.getOpt(r7)
            if (r0 == 0) goto L_0x0091
            org.apache.xmlbeans.impl.tool.CommandLine.printVersion()
            java.lang.System.exit(r3)
            return
        L_0x0091:
            java.lang.String[] r0 = r8.args()
            int r4 = r0.length
            if (r4 == r2) goto L_0x009c
            java.lang.System.exit(r3)
            return
        L_0x009c:
            java.lang.String r4 = r8.getOpt(r9)
            if (r4 != 0) goto L_0x00a4
            java.lang.String r4 = "common.xsd"
        L_0x00a4:
            java.lang.String r5 = r8.getOpt(r10)
            if (r5 != 0) goto L_0x00b2
            java.io.PrintStream r5 = java.lang.System.out
            java.lang.String r6 = "Using output directory 'out'"
            r5.println(r6)
            goto L_0x00b3
        L_0x00b2:
            r10 = r5
        L_0x00b3:
            java.io.File r5 = new java.io.File
            r5.<init>(r10)
            java.io.File r6 = new java.io.File
            r0 = r0[r3]
            r6.<init>(r0)
            java.io.File[] r7 = r8.getFiles()
            java.util.HashMap r8 = new java.util.HashMap
            r8.<init>()
            java.util.HashSet r9 = new java.util.HashSet
            r9.<init>()
            java.util.HashSet r10 = new java.util.HashSet
            r10.<init>()
            java.util.HashSet r11 = new java.util.HashSet
            r11.<init>()
            java.util.HashSet r12 = new java.util.HashSet
            r12.<init>()
            java.util.HashSet r13 = new java.util.HashSet
            r13.<init>()
            java.util.HashSet r14 = new java.util.HashSet
            r14.<init>()
            java.util.HashSet r15 = new java.util.HashSet
            r15.<init>()
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            r16 = r6
            java.util.HashSet r6 = new java.util.HashSet
            r6.<init>()
            r17 = r4
            java.util.HashSet r4 = new java.util.HashSet
            r4.<init>()
            r18 = r5
            int r5 = r7.length
            r19 = r6
            r6 = 0
        L_0x0109:
            java.lang.String r0 = ""
            if (r6 >= r5) goto L_0x0296
            r20 = r5
            r5 = r7[r6]
            r21 = r0
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument> r0 = org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Factory     // Catch:{ XmlException -> 0x0258, IOException -> 0x0230 }
            java.lang.Object r0 = r0.parse((java.io.File) r5)     // Catch:{ XmlException -> 0x0258, IOException -> 0x0230 }
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument r0 = (org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument) r0     // Catch:{ XmlException -> 0x0258, IOException -> 0x0230 }
            r8.put(r0, r5)     // Catch:{ XmlException -> 0x0258, IOException -> 0x0230 }
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r22 = r0.getSchema()     // Catch:{ XmlException -> 0x0258, IOException -> 0x0230 }
            int r22 = r22.sizeOfImportArray()     // Catch:{ XmlException -> 0x0258, IOException -> 0x0230 }
            if (r22 > 0) goto L_0x013a
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r22 = r0.getSchema()     // Catch:{ XmlException -> 0x0258, IOException -> 0x0230 }
            int r22 = r22.sizeOfIncludeArray()     // Catch:{ XmlException -> 0x0258, IOException -> 0x0230 }
            if (r22 <= 0) goto L_0x0133
            goto L_0x013a
        L_0x0133:
            r24 = r6
            r22 = r7
            r23 = r8
            goto L_0x015c
        L_0x013a:
            r22 = r7
            java.io.PrintStream r7 = java.lang.System.out     // Catch:{ XmlException -> 0x022a, IOException -> 0x0230 }
            r23 = r8
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ XmlException -> 0x0224, IOException -> 0x0230 }
            r8.<init>()     // Catch:{ XmlException -> 0x0224, IOException -> 0x0230 }
            java.lang.StringBuilder r8 = r8.append(r1)     // Catch:{ XmlException -> 0x0224, IOException -> 0x0230 }
            java.lang.StringBuilder r8 = r8.append(r5)     // Catch:{ XmlException -> 0x0224, IOException -> 0x0230 }
            r24 = r6
            java.lang.String r6 = " contains imports or includes that are being ignored."
            java.lang.StringBuilder r6 = r8.append(r6)     // Catch:{ XmlException -> 0x0220, IOException -> 0x0230 }
            java.lang.String r6 = r6.toString()     // Catch:{ XmlException -> 0x0220, IOException -> 0x0230 }
            r7.println(r6)     // Catch:{ XmlException -> 0x0220, IOException -> 0x0230 }
        L_0x015c:
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r6 = r0.getSchema()     // Catch:{ XmlException -> 0x0220, IOException -> 0x0230 }
            java.lang.String r6 = r6.getTargetNamespace()     // Catch:{ XmlException -> 0x0220, IOException -> 0x0230 }
            if (r6 != 0) goto L_0x0168
            r6 = r21
        L_0x0168:
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r7 = r0.getSchema()     // Catch:{ XmlException -> 0x0220, IOException -> 0x0230 }
            org.apache.xmlbeans.impl.xb.xsdschema.TopLevelComplexType[] r7 = r7.getComplexTypeArray()     // Catch:{ XmlException -> 0x0220, IOException -> 0x0230 }
            int r8 = r7.length     // Catch:{ XmlException -> 0x0220, IOException -> 0x0230 }
            r25 = r1
            r1 = 0
        L_0x0174:
            if (r1 >= r8) goto L_0x0186
            r21 = r7[r1]     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            r26 = r7
            java.lang.String r7 = r21.getName()     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            noteName(r7, r6, r11, r3, r4)     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            int r1 = r1 + 1
            r7 = r26
            goto L_0x0174
        L_0x0186:
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r1 = r0.getSchema()     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            org.apache.xmlbeans.impl.xb.xsdschema.TopLevelSimpleType[] r1 = r1.getSimpleTypeArray()     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            int r7 = r1.length     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            r8 = 0
        L_0x0190:
            if (r8 >= r7) goto L_0x01a2
            r21 = r1[r8]     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            r26 = r1
            java.lang.String r1 = r21.getName()     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            noteName(r1, r6, r11, r3, r4)     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            int r8 = r8 + 1
            r1 = r26
            goto L_0x0190
        L_0x01a2:
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r1 = r0.getSchema()     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            org.apache.xmlbeans.impl.xb.xsdschema.TopLevelElement[] r1 = r1.getElementArray()     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            int r7 = r1.length     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            r8 = 0
        L_0x01ac:
            if (r8 >= r7) goto L_0x01be
            r21 = r1[r8]     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            r26 = r1
            java.lang.String r1 = r21.getName()     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            noteName(r1, r6, r9, r14, r4)     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            int r8 = r8 + 1
            r1 = r26
            goto L_0x01ac
        L_0x01be:
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r1 = r0.getSchema()     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            org.apache.xmlbeans.impl.xb.xsdschema.TopLevelAttribute[] r1 = r1.getAttributeArray()     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            int r7 = r1.length     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            r8 = 0
        L_0x01c8:
            if (r8 >= r7) goto L_0x01da
            r21 = r1[r8]     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            r26 = r1
            java.lang.String r1 = r21.getName()     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            noteName(r1, r6, r10, r15, r4)     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            int r8 = r8 + 1
            r1 = r26
            goto L_0x01c8
        L_0x01da:
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r1 = r0.getSchema()     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            org.apache.xmlbeans.impl.xb.xsdschema.NamedGroup[] r1 = r1.getGroupArray()     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            int r7 = r1.length     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            r8 = 0
        L_0x01e4:
            if (r8 >= r7) goto L_0x01f6
            r21 = r1[r8]     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            r26 = r1
            java.lang.String r1 = r21.getName()     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            noteName(r1, r6, r12, r2, r4)     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            int r8 = r8 + 1
            r1 = r26
            goto L_0x01e4
        L_0x01f6:
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r0 = r0.getSchema()     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            org.apache.xmlbeans.impl.xb.xsdschema.NamedAttributeGroup[] r0 = r0.getAttributeGroupArray()     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            int r1 = r0.length     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            r7 = 0
        L_0x0200:
            if (r7 >= r1) goto L_0x0218
            r8 = r0[r7]     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            java.lang.String r8 = r8.getName()     // Catch:{ XmlException -> 0x021e, IOException -> 0x0230 }
            r21 = r1
            r1 = r19
            noteName(r8, r6, r13, r1, r4)     // Catch:{ XmlException -> 0x0216, IOException -> 0x0230 }
            int r7 = r7 + 1
            r19 = r1
            r1 = r21
            goto L_0x0200
        L_0x0216:
            r0 = move-exception
            goto L_0x0263
        L_0x0218:
            r1 = r19
            r8 = r25
            goto L_0x0289
        L_0x021e:
            r0 = move-exception
            goto L_0x0261
        L_0x0220:
            r0 = move-exception
            r25 = r1
            goto L_0x0261
        L_0x0224:
            r0 = move-exception
            r25 = r1
            r24 = r6
            goto L_0x0261
        L_0x022a:
            r0 = move-exception
            r25 = r1
            r24 = r6
            goto L_0x025f
        L_0x0230:
            r0 = move-exception
            java.io.PrintStream r1 = java.lang.System.err
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Unable to load "
            r2.<init>(r3)
            java.lang.StringBuilder r2 = r2.append(r5)
            java.lang.String r3 = " - "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r0 = r0.getMessage()
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            r1.println(r0)
            r1 = 1
            java.lang.System.exit(r1)
            return
        L_0x0258:
            r0 = move-exception
            r25 = r1
            r24 = r6
            r22 = r7
        L_0x025f:
            r23 = r8
        L_0x0261:
            r1 = r19
        L_0x0263:
            java.io.PrintStream r6 = java.lang.System.out
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r8 = r25
            r7.<init>(r8)
            java.lang.StringBuilder r5 = r7.append(r5)
            java.lang.String r7 = " is not a schema file - "
            java.lang.StringBuilder r5 = r5.append(r7)
            org.apache.xmlbeans.XmlError r0 = r0.getError()
            java.lang.String r0 = r0.toString()
            java.lang.StringBuilder r0 = r5.append(r0)
            java.lang.String r0 = r0.toString()
            r6.println(r0)
        L_0x0289:
            int r6 = r24 + 1
            r19 = r1
            r1 = r8
            r5 = r20
            r7 = r22
            r8 = r23
            goto L_0x0109
        L_0x0296:
            r21 = r0
            r23 = r8
            r1 = r19
            int r0 = r23.size()
            if (r0 != 0) goto L_0x02ae
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.String r1 = "No schema files found."
            r0.println(r1)
            r1 = 0
            java.lang.System.exit(r1)
            return
        L_0x02ae:
            int r0 = r3.size()
            int r5 = r14.size()
            int r0 = r0 + r5
            int r5 = r15.size()
            int r0 = r0 + r5
            int r5 = r2.size()
            int r0 = r0 + r5
            int r5 = r1.size()
            int r0 = r0 + r5
            if (r0 != 0) goto L_0x02d4
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.String r1 = "No duplicate names found."
            r0.println(r1)
            r1 = 0
            java.lang.System.exit(r1)
            return
        L_0x02d4:
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.util.HashMap r5 = new java.util.HashMap
            r5.<init>()
            int r6 = r4.size()
            r7 = 1
            if (r6 != r7) goto L_0x02e7
            r6 = 0
            goto L_0x02e8
        L_0x02e7:
            r6 = 1
        L_0x02e8:
            java.util.Iterator r4 = r4.iterator()
        L_0x02ec:
            boolean r7 = r4.hasNext()
            if (r7 == 0) goto L_0x0339
            java.lang.Object r7 = r4.next()
            java.lang.String r7 = (java.lang.String) r7
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument> r8 = org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Factory
            r19 = r4
            java.lang.String r4 = "<xs:schema xmlns:xs='http://www.w3.org/2001/XMLSchema'/>"
            java.lang.Object r4 = r8.parse((java.lang.String) r4)
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument r4 = (org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument) r4
            int r8 = r7.length()
            if (r8 <= 0) goto L_0x0311
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r8 = r4.getSchema()
            r8.setTargetNamespace(r7)
        L_0x0311:
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r8 = r4.getSchema()
            r20 = r13
            org.apache.xmlbeans.impl.xb.xsdschema.FormChoice$Enum r13 = org.apache.xmlbeans.impl.xb.xsdschema.FormChoice.QUALIFIED
            r8.setElementFormDefault(r13)
            r0.put(r7, r4)
            int r8 = r6 + 1
            r13 = r18
            r27 = r17
            r17 = r8
            r8 = r27
            java.io.File r6 = commonFileFor(r8, r7, r6, r13)
            r5.put(r4, r6)
            r6 = r17
            r4 = r19
            r13 = r20
            r17 = r8
            goto L_0x02ec
        L_0x0339:
            r20 = r13
            r13 = r18
            java.util.Set r4 = r23.keySet()
            java.util.Iterator r4 = r4.iterator()
        L_0x0345:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x054e
            java.lang.Object r6 = r4.next()
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument r6 = (org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument) r6
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r7 = r6.getSchema()
            java.lang.String r7 = r7.getTargetNamespace()
            if (r7 != 0) goto L_0x035d
            r7 = r21
        L_0x035d:
            java.lang.Object r8 = r0.get(r7)
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument r8 = (org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument) r8
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r17 = r6.getSchema()
            r18 = r0
            org.apache.xmlbeans.impl.xb.xsdschema.TopLevelComplexType[] r0 = r17.getComplexTypeArray()
            r17 = r4
            int r4 = r0.length
            r19 = 1
            int r4 = r4 + -1
            r19 = 0
        L_0x0376:
            if (r4 < 0) goto L_0x03b7
            r22 = r0[r4]
            r24 = r5
            java.lang.String r5 = r22.getName()
            boolean r5 = isDuplicate(r5, r7, r3)
            if (r5 != 0) goto L_0x0389
            r22 = r13
            goto L_0x03b0
        L_0x0389:
            r5 = r0[r4]
            java.lang.String r5 = r5.getName()
            boolean r5 = isFirstDuplicate(r5, r7, r11, r3)
            if (r5 == 0) goto L_0x03a5
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r5 = r8.getSchema()
            org.apache.xmlbeans.impl.xb.xsdschema.TopLevelComplexType r5 = r5.addNewComplexType()
            r22 = r13
            r13 = r0[r4]
            r5.set(r13)
            goto L_0x03a7
        L_0x03a5:
            r22 = r13
        L_0x03a7:
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r5 = r6.getSchema()
            r5.removeComplexType(r4)
            r19 = 1
        L_0x03b0:
            int r4 = r4 + -1
            r13 = r22
            r5 = r24
            goto L_0x0376
        L_0x03b7:
            r24 = r5
            r22 = r13
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r0 = r6.getSchema()
            org.apache.xmlbeans.impl.xb.xsdschema.TopLevelSimpleType[] r0 = r0.getSimpleTypeArray()
            r4 = 0
        L_0x03c4:
            int r5 = r0.length
            if (r4 >= r5) goto L_0x03f9
            r5 = r0[r4]
            java.lang.String r5 = r5.getName()
            boolean r5 = isDuplicate(r5, r7, r3)
            if (r5 != 0) goto L_0x03d4
            goto L_0x03f6
        L_0x03d4:
            r5 = r0[r4]
            java.lang.String r5 = r5.getName()
            boolean r5 = isFirstDuplicate(r5, r7, r11, r3)
            if (r5 == 0) goto L_0x03ed
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r5 = r8.getSchema()
            org.apache.xmlbeans.impl.xb.xsdschema.TopLevelSimpleType r5 = r5.addNewSimpleType()
            r13 = r0[r4]
            r5.set(r13)
        L_0x03ed:
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r5 = r6.getSchema()
            r5.removeSimpleType(r4)
            r19 = 1
        L_0x03f6:
            int r4 = r4 + 1
            goto L_0x03c4
        L_0x03f9:
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r0 = r6.getSchema()
            org.apache.xmlbeans.impl.xb.xsdschema.TopLevelElement[] r0 = r0.getElementArray()
            r4 = 0
        L_0x0402:
            int r5 = r0.length
            if (r4 >= r5) goto L_0x0437
            r5 = r0[r4]
            java.lang.String r5 = r5.getName()
            boolean r5 = isDuplicate(r5, r7, r14)
            if (r5 != 0) goto L_0x0412
            goto L_0x0434
        L_0x0412:
            r5 = r0[r4]
            java.lang.String r5 = r5.getName()
            boolean r5 = isFirstDuplicate(r5, r7, r9, r14)
            if (r5 == 0) goto L_0x042b
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r5 = r8.getSchema()
            org.apache.xmlbeans.impl.xb.xsdschema.TopLevelElement r5 = r5.addNewElement()
            r13 = r0[r4]
            r5.set(r13)
        L_0x042b:
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r5 = r6.getSchema()
            r5.removeElement(r4)
            r19 = 1
        L_0x0434:
            int r4 = r4 + 1
            goto L_0x0402
        L_0x0437:
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r0 = r6.getSchema()
            org.apache.xmlbeans.impl.xb.xsdschema.TopLevelAttribute[] r0 = r0.getAttributeArray()
            r4 = 0
        L_0x0440:
            int r5 = r0.length
            if (r4 >= r5) goto L_0x0475
            r5 = r0[r4]
            java.lang.String r5 = r5.getName()
            boolean r5 = isDuplicate(r5, r7, r15)
            if (r5 != 0) goto L_0x0450
            goto L_0x0472
        L_0x0450:
            r5 = r0[r4]
            java.lang.String r5 = r5.getName()
            boolean r5 = isFirstDuplicate(r5, r7, r10, r15)
            if (r5 == 0) goto L_0x0469
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r5 = r8.getSchema()
            org.apache.xmlbeans.impl.xb.xsdschema.TopLevelElement r5 = r5.addNewElement()
            r13 = r0[r4]
            r5.set(r13)
        L_0x0469:
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r5 = r6.getSchema()
            r5.removeElement(r4)
            r19 = 1
        L_0x0472:
            int r4 = r4 + 1
            goto L_0x0440
        L_0x0475:
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r0 = r6.getSchema()
            org.apache.xmlbeans.impl.xb.xsdschema.NamedGroup[] r0 = r0.getGroupArray()
            r4 = 0
        L_0x047e:
            int r5 = r0.length
            if (r4 >= r5) goto L_0x04b3
            r5 = r0[r4]
            java.lang.String r5 = r5.getName()
            boolean r5 = isDuplicate(r5, r7, r2)
            if (r5 != 0) goto L_0x048e
            goto L_0x04b0
        L_0x048e:
            r5 = r0[r4]
            java.lang.String r5 = r5.getName()
            boolean r5 = isFirstDuplicate(r5, r7, r12, r2)
            if (r5 == 0) goto L_0x04a7
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r5 = r8.getSchema()
            org.apache.xmlbeans.impl.xb.xsdschema.TopLevelElement r5 = r5.addNewElement()
            r13 = r0[r4]
            r5.set(r13)
        L_0x04a7:
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r5 = r6.getSchema()
            r5.removeElement(r4)
            r19 = 1
        L_0x04b0:
            int r4 = r4 + 1
            goto L_0x047e
        L_0x04b3:
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r0 = r6.getSchema()
            org.apache.xmlbeans.impl.xb.xsdschema.NamedAttributeGroup[] r0 = r0.getAttributeGroupArray()
            r4 = 0
        L_0x04bc:
            int r5 = r0.length
            if (r4 >= r5) goto L_0x0500
            r5 = r0[r4]
            java.lang.String r5 = r5.getName()
            boolean r5 = isDuplicate(r5, r7, r1)
            if (r5 != 0) goto L_0x04d0
            r13 = r20
            r20 = r1
            goto L_0x04f9
        L_0x04d0:
            r5 = r0[r4]
            java.lang.String r5 = r5.getName()
            r13 = r20
            boolean r5 = isFirstDuplicate(r5, r7, r13, r1)
            if (r5 == 0) goto L_0x04ee
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r5 = r8.getSchema()
            org.apache.xmlbeans.impl.xb.xsdschema.TopLevelElement r5 = r5.addNewElement()
            r20 = r1
            r1 = r0[r4]
            r5.set(r1)
            goto L_0x04f0
        L_0x04ee:
            r20 = r1
        L_0x04f0:
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r1 = r6.getSchema()
            r1.removeElement(r4)
            r19 = 1
        L_0x04f9:
            int r4 = r4 + 1
            r1 = r20
            r20 = r13
            goto L_0x04bc
        L_0x0500:
            r13 = r20
            r20 = r1
            if (r19 == 0) goto L_0x0532
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r0 = r6.getSchema()
            org.apache.xmlbeans.impl.xb.xsdschema.IncludeDocument$Include r0 = r0.addNewInclude()
            r1 = r23
            java.lang.Object r4 = r1.get(r6)
            java.io.File r4 = (java.io.File) r4
            r6 = r16
            r5 = r22
            java.io.File r4 = outputFileFor(r4, r6, r5)
            r16 = r2
            r2 = r24
            java.lang.Object r8 = r2.get(r8)
            java.io.File r8 = (java.io.File) r8
            if (r7 == 0) goto L_0x053c
            java.lang.String r4 = relativeURIFor(r4, r8)
            r0.setSchemaLocation(r4)
            goto L_0x053c
        L_0x0532:
            r6 = r16
            r5 = r22
            r1 = r23
            r16 = r2
            r2 = r24
        L_0x053c:
            r23 = r1
            r4 = r17
            r0 = r18
            r1 = r20
            r20 = r13
            r13 = r5
            r5 = r2
            r2 = r16
            r16 = r6
            goto L_0x0345
        L_0x054e:
            r2 = r5
            r5 = r13
            r6 = r16
            r1 = r23
            boolean r0 = r5.isDirectory()
            if (r0 != 0) goto L_0x0579
            boolean r0 = r5.mkdirs()
            if (r0 != 0) goto L_0x0579
            java.io.PrintStream r0 = java.lang.System.err
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unable to makedir "
            r1.<init>(r2)
            java.lang.StringBuilder r1 = r1.append(r5)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
            r1 = 1
            java.lang.System.exit(r1)
            return
        L_0x0579:
            java.util.Set r0 = r1.keySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x0581:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x05bf
            java.lang.Object r3 = r0.next()
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument r3 = (org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument) r3
            java.lang.Object r4 = r1.get(r3)
            java.io.File r4 = (java.io.File) r4
            java.io.File r7 = outputFileFor(r4, r6, r5)
            if (r7 != 0) goto L_0x05ae
            java.io.PrintStream r3 = java.lang.System.out
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "Cannot copy "
            r7.<init>(r8)
            java.lang.StringBuilder r4 = r7.append(r4)
            java.lang.String r4 = r4.toString()
            r3.println(r4)
            goto L_0x0581
        L_0x05ae:
            org.apache.xmlbeans.XmlOptions r4 = new org.apache.xmlbeans.XmlOptions
            r4.<init>()
            org.apache.xmlbeans.XmlOptions r4 = r4.setSavePrettyPrint()
            org.apache.xmlbeans.XmlOptions r4 = r4.setSaveAggressiveNamespaces()
            r3.save((java.io.File) r7, (org.apache.xmlbeans.XmlOptions) r4)
            goto L_0x0581
        L_0x05bf:
            java.util.Set r0 = r2.keySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x05c7:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x05ea
            java.lang.Object r1 = r0.next()
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument r1 = (org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument) r1
            java.lang.Object r3 = r2.get(r1)
            java.io.File r3 = (java.io.File) r3
            org.apache.xmlbeans.XmlOptions r4 = new org.apache.xmlbeans.XmlOptions
            r4.<init>()
            org.apache.xmlbeans.XmlOptions r4 = r4.setSavePrettyPrint()
            org.apache.xmlbeans.XmlOptions r4 = r4.setSaveAggressiveNamespaces()
            r1.save((java.io.File) r3, (org.apache.xmlbeans.XmlOptions) r4)
            goto L_0x05c7
        L_0x05ea:
            return
        L_0x05eb:
            printUsage()
            r1 = 0
            java.lang.System.exit(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.tool.FactorImports.main(java.lang.String[]):void");
    }

    private static File outputFileFor(File file, File file2, File file3) {
        URI relativize = file2.getAbsoluteFile().toURI().relativize(file.getAbsoluteFile().toURI());
        if (!relativize.isAbsolute()) {
            return new File(CodeGenUtil.resolve(file3.toURI(), relativize));
        }
        System.out.println("Cannot relativize " + file);
        return null;
    }

    private static URI commonAncestor(URI uri, URI uri2) {
        String uri3 = uri.toString();
        String uri4 = uri2.toString();
        int length = uri3.length();
        if (uri4.length() < length) {
            length = uri4.length();
        }
        int i = 0;
        while (i < length && uri3.charAt(i) == uri4.charAt(i)) {
            i++;
        }
        int i2 = i - 1;
        if (i2 >= 0) {
            i2 = uri3.lastIndexOf(47, i2);
        }
        if (i2 < 0) {
            return null;
        }
        try {
            return new URI(uri3.substring(0, i2));
        } catch (URISyntaxException unused) {
            return null;
        }
    }

    private static String relativeURIFor(File file, File file2) {
        int indexOf;
        URI uri = file.getAbsoluteFile().toURI();
        URI uri2 = file2.getAbsoluteFile().toURI();
        URI commonAncestor = commonAncestor(uri, uri2);
        if (commonAncestor == null) {
            return uri2.toString();
        }
        URI relativize = commonAncestor.relativize(uri);
        URI relativize2 = commonAncestor.relativize(uri2);
        if (relativize.isAbsolute() || relativize2.isAbsolute()) {
            return uri2.toString();
        }
        String uri3 = relativize.toString();
        String str = "";
        int i = 0;
        while (i < uri3.length() && (indexOf = uri3.indexOf(47, i)) >= 0) {
            str = str + "../";
            i = indexOf + 1;
        }
        return str + relativize2.toString();
    }

    private static File commonFileFor(String str, String str2, int i, File file) {
        if (i > 0) {
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf < 0) {
                lastIndexOf = str.length();
            }
            str = str.substring(0, lastIndexOf) + i + str.substring(lastIndexOf);
        }
        return new File(file, str);
    }

    private static void noteName(String str, String str2, Set<QName> set, Set<QName> set2, Set<String> set3) {
        if (str != null) {
            QName qName = new QName(str2, str);
            if (set.contains(qName)) {
                set2.add(qName);
                set3.add(str2);
                return;
            }
            set.add(qName);
        }
    }

    private static boolean isFirstDuplicate(String str, String str2, Set<QName> set, Set<QName> set2) {
        if (str == null) {
            return false;
        }
        QName qName = new QName(str2, str);
        if (!set2.contains(qName) || !set.contains(qName)) {
            return false;
        }
        set.remove(qName);
        return true;
    }

    private static boolean isDuplicate(String str, String str2, Set<QName> set) {
        if (str == null) {
            return false;
        }
        return set.contains(new QName(str2, str));
    }
}
