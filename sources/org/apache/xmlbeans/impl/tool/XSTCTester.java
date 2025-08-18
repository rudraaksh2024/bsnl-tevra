package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Pattern;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.xmlbeans.SystemProperties;
import org.apache.xmlbeans.XmlCalendar;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc;
import org.apache.xmlbeans.impl.xb.ltgfmt.TestsDocument;

public class XSTCTester {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Pattern leadingSpace = Pattern.compile("^\\s+", 8);

    public interface Harness {
        void runTestCase(TestCaseResult testCaseResult);
    }

    public static void printUsage() {
        System.out.println("Usage: xstc [-showpass] [-errcode] foo_LTGfmt.xml ...");
    }

    public static void main(String[] strArr) throws IOException {
        Throwable th;
        Iterator it;
        HashSet hashSet = new HashSet();
        hashSet.add("h");
        hashSet.add("help");
        hashSet.add("usage");
        hashSet.add("version");
        hashSet.add("showpass");
        hashSet.add("errcode");
        long currentTimeMillis = System.currentTimeMillis();
        CommandLine commandLine = new CommandLine(strArr, hashSet, Collections.EMPTY_SET);
        if (commandLine.getOpt("h") != null || commandLine.getOpt("help") != null || commandLine.getOpt("usage") != null) {
            printUsage();
            System.exit(0);
        } else if (commandLine.getOpt("version") != null) {
            CommandLine.printVersion();
            System.exit(0);
        } else {
            String[] badOpts = commandLine.getBadOpts();
            if (badOpts.length > 0) {
                int length = badOpts.length;
                for (int i = 0; i < length; i++) {
                    System.out.println("Unrecognized option: " + badOpts[i]);
                }
                printUsage();
                System.exit(0);
            } else if (commandLine.args().length == 0) {
                printUsage();
            } else {
                boolean z = true;
                boolean z2 = commandLine.getOpt("showpass") != null;
                if (commandLine.getOpt("errcode") == null) {
                    z = false;
                }
                File[] files = commandLine.getFiles();
                ArrayList arrayList = new ArrayList();
                XMLBeanXSTCHarness xMLBeanXSTCHarness = new XMLBeanXSTCHarness();
                for (File file : files) {
                    if (file.getName().contains("LTG")) {
                        arrayList.add(file);
                    }
                }
                File file2 = new File("out.html");
                PrintWriter printWriter = new PrintWriter(Files.newBufferedWriter(file2.toPath(), StandardCharsets.ISO_8859_1, new OpenOption[0]));
                try {
                    printWriter.println("<html>");
                    printWriter.println("<style>td {border-bottom: 1px solid black} xmp {white-space: normal; word-wrap: break-word; word-break: break-all} </style>");
                    printWriter.println("<body>");
                    printWriter.println("<script language='JavaScript' type='text/javascript'>");
                    printWriter.println("var w;");
                    printWriter.println("function openWindow(schema, instance) {");
                    printWriter.println("  if (w == null) {");
                    printWriter.println("    w = window.open('about:blank', 'xstc');");
                    printWriter.println("  }");
                    printWriter.println("  if (w.closed) {");
                    printWriter.println("    w = window.open('about:blank', 'xstc');");
                    printWriter.println("  }");
                    printWriter.println("  w.document.open();");
                    printWriter.println("  w.document.write(\"<frameset rows=*,*><frame src='\" + schema + \"'><frame src='\" + instance + \"'></frameset>\");");
                    printWriter.println("  w.document.close();");
                    printWriter.println("  w.focus();");
                    printWriter.println(VectorFormat.DEFAULT_SUFFIX);
                    printWriter.println("</script>");
                    printWriter.println("<h1>XML Schema Test Collection Results</h1>");
                    printWriter.println("<p>Run on " + new XmlCalendar(new Date()) + "</p>");
                    printWriter.println("<p>Values in schema or instance valid columns are results from compiling or validating respectively.");
                    printWriter.println("Red or orange background mean the test failed.</p>");
                    printWriter.println("<table style='border: 1px solid black' cellpadding=0 cellspacing=0>");
                    printWriter.println("<tr><td witdh=10%>id</td><td width=70%>Description</td><td width=10%>sch v</td><td width=10%>ins v</td></tr>");
                    Iterator it2 = arrayList.iterator();
                    int i2 = 0;
                    int i3 = 0;
                    while (it2.hasNext()) {
                        File file3 = (File) it2.next();
                        System.out.println("Processing test cases in " + file3);
                        ArrayList<String> arrayList2 = new ArrayList<>();
                        TestCase[] parseLTGFile = parseLTGFile(file3, arrayList2);
                        ArrayList<TestCaseResult> arrayList3 = new ArrayList<>();
                        if (parseLTGFile != null) {
                            int length2 = parseLTGFile.length;
                            it = it2;
                            int i4 = 0;
                            while (i4 < length2) {
                                int i5 = length2;
                                TestCase testCase = parseLTGFile[i4];
                                TestCase[] testCaseArr = parseLTGFile;
                                TestCaseResult testCaseResult = new TestCaseResult();
                                TestCase unused = testCaseResult.testCase = testCase;
                                xMLBeanXSTCHarness.runTestCase(testCaseResult);
                                i3++;
                                if (!testCaseResult.succeeded(z)) {
                                    i2++;
                                } else if (!z2) {
                                    i4++;
                                    length2 = i5;
                                    parseLTGFile = testCaseArr;
                                }
                                arrayList3.add(testCaseResult);
                                i4++;
                                length2 = i5;
                                parseLTGFile = testCaseArr;
                            }
                        } else {
                            it = it2;
                        }
                        printWriter.println("<tr><td colspan=4 bgcolor=skyblue>" + file3 + "</td></tr>");
                        if (!arrayList2.isEmpty()) {
                            printWriter.println("<tr><td>Errors within the LTG file:");
                            printWriter.println("<xmp>");
                            for (String println : arrayList2) {
                                printWriter.println(println);
                            }
                            printWriter.println("</xmp>");
                            printWriter.println("</td></tr>");
                        } else if (arrayList3.size() == 0) {
                            printWriter.println("<tr><td colspan=4 bgcolor=green>Nothing to report</td></tr>");
                        }
                        for (TestCaseResult summarizeResultAsHTMLTableRows : arrayList3) {
                            summarizeResultAsHTMLTableRows(summarizeResultAsHTMLTableRows, printWriter, z);
                        }
                        it2 = it;
                    }
                    printWriter.println("<tr><td colspan=4>Summary: " + i2 + " failures out of " + i3 + " cases run.</td></tr>");
                    printWriter.println("</table>");
                    printWriter.close();
                    System.out.println("Time run tests: " + (((double) (System.currentTimeMillis() - currentTimeMillis)) / 1000.0d) + " seconds");
                    System.out.println("Results output to " + file2);
                    if (SystemProperties.getProperty("os.name").toLowerCase(Locale.ROOT).contains("windows")) {
                        Runtime.getRuntime().exec("cmd /c start iexplore \"" + file2.getAbsolutePath() + "\"");
                    } else {
                        Runtime.getRuntime().exec("mozilla file://" + file2.getAbsolutePath());
                    }
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    try {
                        printWriter.close();
                    } catch (Throwable th4) {
                        th.addSuppressed(th4);
                    }
                    throw th3;
                }
            }
        }
    }

    public static class TestCase {
        /* access modifiers changed from: private */
        public String description;
        /* access modifiers changed from: private */
        public String errorCode;
        /* access modifiers changed from: private */
        public String id;
        /* access modifiers changed from: private */
        public File instanceFile;
        /* access modifiers changed from: private */
        public boolean ivExpected;
        /* access modifiers changed from: private */
        public File ltgFile;
        /* access modifiers changed from: private */
        public String origin;
        /* access modifiers changed from: private */
        public File resourceFile;
        /* access modifiers changed from: private */
        public boolean rvExpected;
        /* access modifiers changed from: private */
        public File schemaFile;
        /* access modifiers changed from: private */
        public boolean svExpected;

        public File getLtgFile() {
            return this.ltgFile;
        }

        public String getId() {
            return this.id;
        }

        public String getOrigin() {
            return this.origin;
        }

        public String getDescription() {
            return this.description;
        }

        public File getSchemaFile() {
            return this.schemaFile;
        }

        public File getInstanceFile() {
            return this.instanceFile;
        }

        public File getResourceFile() {
            return this.resourceFile;
        }

        public boolean isSvExpected() {
            return this.svExpected;
        }

        public boolean isIvExpected() {
            return this.ivExpected;
        }

        public boolean isRvExpected() {
            return this.rvExpected;
        }

        public String getErrorCode() {
            return this.errorCode;
        }
    }

    public static class TestCaseResult {
        private boolean crash;
        private boolean ivActual;
        /* access modifiers changed from: private */
        public final Collection<XmlError> ivMessages = new ArrayList();
        private boolean svActual;
        /* access modifiers changed from: private */
        public final Collection<XmlError> svMessages = new ArrayList();
        /* access modifiers changed from: private */
        public TestCase testCase;

        public TestCase getTestCase() {
            return this.testCase;
        }

        public boolean isSvActual() {
            return this.svActual;
        }

        public void setSvActual(boolean z) {
            this.svActual = z;
        }

        public boolean isIvActual() {
            return this.ivActual;
        }

        public void setIvActual(boolean z) {
            this.ivActual = z;
        }

        public Collection<XmlError> getSvMessages() {
            return Collections.unmodifiableCollection(this.svMessages);
        }

        public void addSvMessages(Collection<XmlError> collection) {
            this.svMessages.addAll(collection);
        }

        public Collection<XmlError> getIvMessages() {
            return Collections.unmodifiableCollection(this.ivMessages);
        }

        public void addIvMessages(Collection<XmlError> collection) {
            this.ivMessages.addAll(collection);
        }

        public void setCrash(boolean z) {
            this.crash = z;
        }

        public boolean isCrash() {
            return this.crash;
        }

        public boolean succeeded(boolean z) {
            boolean z2 = true;
            boolean z3 = !this.crash && isIvActual() == this.testCase.isIvExpected() && isSvActual() == this.testCase.isSvExpected();
            if (!z || this.testCase.getErrorCode() == null) {
                return z3;
            }
            if (!XSTCTester.errorReported(this.testCase.getErrorCode(), this.svMessages) && !XSTCTester.errorReported(this.testCase.getErrorCode(), this.ivMessages)) {
                z2 = false;
            }
            return z3 & z2;
        }
    }

    public static String makeHTMLLink(File file, boolean z) {
        if (file == null) {
            return "&nbsp;";
        }
        return "<a href=\"" + file.getAbsoluteFile().toURI() + "\" target=_blank>" + z + "</a>";
    }

    public static String makeHTMLDescription(TestCase testCase) {
        StringBuilder sb = new StringBuilder("<a class=noline href='javascript:openWindow(\"");
        if (testCase.getSchemaFile() == null) {
            sb.append("about:No schema");
        } else {
            sb.append(testCase.getSchemaFile().getAbsolutePath().replaceAll("\\\\", "\\\\\\\\"));
        }
        sb.append("\", \"");
        if (testCase.getInstanceFile() == null) {
            sb.append("about:No instance");
        } else {
            sb.append(testCase.getInstanceFile().getAbsolutePath().replaceAll("\\\\", "\\\\\\\\"));
        }
        sb.append("\")'><xmp>");
        sb.append(leadingSpace.matcher(testCase.getDescription()).replaceAll(""));
        sb.append("</xmp></a>");
        return sb.toString();
    }

    public static void summarizeResultAsHTMLTableRows(TestCaseResult testCaseResult, PrintWriter printWriter, boolean z) {
        String str;
        TestCase testCase = testCaseResult.getTestCase();
        boolean z2 = false;
        boolean z3 = true;
        int i = (!z || testCase.getErrorCode() == null) ? 0 : 1;
        int i2 = (!testCaseResult.getIvMessages().isEmpty() || !testCaseResult.getSvMessages().isEmpty()) ? 1 : 0;
        boolean z4 = testCase.getSchemaFile() == null || testCase.isSvExpected() == testCaseResult.isSvActual();
        boolean z5 = testCase.getInstanceFile() == null || testCase.isIvExpected() == testCaseResult.isIvActual();
        if (i != 0) {
            if (errorReported(testCase.getErrorCode(), testCaseResult.svMessages) || errorReported(testCase.getErrorCode(), testCaseResult.ivMessages)) {
                z2 = true;
            }
            z3 = z2;
        }
        printWriter.println(testCaseResult.isCrash() ? "<tr bgcolor=black color=white>" : "<tr>");
        printWriter.println("<td rowspan=" + (i + 1 + i2) + " valign=top>" + testCase.getId() + "</td>");
        String str2 = "<td valign=top>";
        printWriter.println(str2 + makeHTMLDescription(testCase) + "</td>");
        if (testCase.getResourceFile() == null) {
            str = makeHTMLLink(testCase.getSchemaFile(), testCaseResult.isSvActual());
        } else {
            str = makeHTMLLink(testCase.getSchemaFile(), testCaseResult.isSvActual()) + "<br>" + makeHTMLLink(testCase.getResourceFile(), testCaseResult.isSvActual());
        }
        printWriter.println((z4 ? str2 : testCaseResult.isSvActual() ? "<td bgcolor=orange valign=top>" : "<td bgcolor=red valign=top>") + str + "</td>");
        StringBuilder sb = new StringBuilder();
        if (!z5) {
            str2 = testCaseResult.isIvActual() ? "<td bgcolor=orange valign=top>" : "<td bgcolor=red valign=top>";
        }
        printWriter.println(sb.append(str2).append(makeHTMLLink(testCase.getInstanceFile(), testCaseResult.isIvActual())).append("</td>").toString());
        printWriter.println("</tr>");
        if (i != 0) {
            printWriter.println("<tr>");
            printWriter.println((z3 ? "<td colspan=4 valid=top>" : "<td colspan=4 bgcolor=orange valign=top>") + "expected error: " + testCase.getErrorCode() + "</td>");
            printWriter.println("</tr>");
        }
        if (i2 != 0) {
            if (!testCaseResult.succeeded(z)) {
                printWriter.println("<tr><td colspan=4 bgcolor=yellow><xmp>");
            } else {
                printWriter.println("<tr><td colspan=4><xmp>");
            }
            for (XmlError println : testCaseResult.getSvMessages()) {
                printWriter.println(println);
            }
            for (XmlError println2 : testCaseResult.getIvMessages()) {
                printWriter.println(println2);
            }
            printWriter.println("</xmp></tr></td>");
        }
    }

    public static TestCase[] parseLTGFile(File file, Collection<String> collection) {
        File file2 = file;
        Collection<String> collection2 = collection;
        ArrayList<XmlError> arrayList = new ArrayList<>();
        try {
            XmlOptions xmlOptions = new XmlOptions();
            xmlOptions.setLoadSubstituteNamespaces(Collections.singletonMap("", "http://www.bea.com/2003/05/xmlbean/ltgfmt"));
            xmlOptions.setErrorListener(arrayList);
            xmlOptions.setLoadLineNumbers();
            TestsDocument parse = TestsDocument.Factory.parse(file2, xmlOptions);
            if (parse.validate(xmlOptions)) {
                org.apache.xmlbeans.impl.xb.ltgfmt.TestCase[] testArray = parse.getTests().getTestArray();
                ArrayList arrayList2 = new ArrayList();
                int length = testArray.length;
                int i = 0;
                while (i < length) {
                    org.apache.xmlbeans.impl.xb.ltgfmt.TestCase testCase = testArray[i];
                    TestCase testCase2 = new TestCase();
                    File unused = testCase2.ltgFile = file2;
                    String unused2 = testCase2.id = testCase.getId();
                    String unused3 = testCase2.origin = testCase.getOrigin();
                    String unused4 = testCase2.description = testCase.getDescription();
                    FileDesc[] fileArray = testCase.getFiles().getFileArray();
                    testCase.getOrigin();
                    int length2 = fileArray.length;
                    int i2 = 0;
                    while (i2 < length2) {
                        FileDesc fileDesc = fileArray[i2];
                        String folder = fileDesc.getFolder();
                        org.apache.xmlbeans.impl.xb.ltgfmt.TestCase[] testCaseArr = testArray;
                        File file3 = new File(file.getParentFile(), folder + PackagingURIHelper.FORWARD_SLASH_STRING + fileDesc.getFileName());
                        if (file3.exists() && file3.isFile()) {
                            if (file3.canRead()) {
                                int intValue = fileDesc.getRole().intValue();
                                if (intValue == 1) {
                                    if (testCase2.schemaFile != null) {
                                        collection2.add(XmlError.forObject("More than one schema file speicifed - ignoring all but last", fileDesc).toString());
                                    }
                                    File unused5 = testCase2.schemaFile = file3;
                                    boolean unused6 = testCase2.svExpected = fileDesc.getValidity();
                                } else if (intValue == 2) {
                                    if (testCase2.instanceFile != null) {
                                        collection2.add(XmlError.forObject("More than one instance file speicifed - ignoring all but last", fileDesc).toString());
                                    }
                                    File unused7 = testCase2.instanceFile = file3;
                                    boolean unused8 = testCase2.ivExpected = fileDesc.getValidity();
                                } else if (intValue == 3) {
                                    if (testCase2.resourceFile != null) {
                                        collection2.add(XmlError.forObject("More than one resource file speicifed - ignoring all but last", fileDesc).toString());
                                    }
                                    File unused9 = testCase2.resourceFile = file3;
                                    boolean unused10 = testCase2.rvExpected = fileDesc.getValidity();
                                } else {
                                    throw new XmlException(XmlError.forObject("Unexpected file role", fileDesc));
                                }
                                if (fileDesc.getCode() != null) {
                                    String unused11 = testCase2.errorCode = fileDesc.getCode().getID();
                                }
                                i2++;
                                testArray = testCaseArr;
                            }
                        }
                        collection2.add(XmlError.forObject("Can't read file " + file3, fileDesc).toString());
                        i2++;
                        testArray = testCaseArr;
                    }
                    arrayList2.add(testCase2);
                    i++;
                    testArray = testArray;
                }
                return (TestCase[]) arrayList2.toArray(new TestCase[0]);
            }
            throw new Exception("Document " + file2 + " not valid.");
        } catch (Exception e) {
            if (arrayList.isEmpty()) {
                collection2.add(e.getMessage());
                return null;
            }
            for (XmlError xmlError : arrayList) {
                collection2.add(xmlError.toString());
            }
            return null;
        }
    }

    public static boolean errorReported(String str, Collection<XmlError> collection) {
        if (!(str == null || collection == null || collection.size() == 0)) {
            for (XmlError errorCode : collection) {
                if (str.equals(errorCode.getErrorCode())) {
                    return true;
                }
            }
        }
        return false;
    }
}
