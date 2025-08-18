package org.apache.poi.xssf.eventusermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class ReadOnlySharedStringsTable extends DefaultHandler implements SharedStrings {
    private StringBuilder characters;
    protected int count;
    private boolean inRPh;
    protected final boolean includePhoneticRuns;
    private List<String> strings;
    private boolean tIsOpen;
    protected int uniqueCount;

    public ReadOnlySharedStringsTable(OPCPackage oPCPackage) throws IOException, SAXException {
        this(oPCPackage, true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002b, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002c, code lost:
        if (r1 != null) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0032, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0033, code lost:
        r0.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0036, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ReadOnlySharedStringsTable(org.apache.poi.openxml4j.opc.OPCPackage r1, boolean r2) throws java.io.IOException, org.xml.sax.SAXException {
        /*
            r0 = this;
            r0.<init>()
            r0.includePhoneticRuns = r2
            org.apache.poi.xssf.usermodel.XSSFRelation r2 = org.apache.poi.xssf.usermodel.XSSFRelation.SHARED_STRINGS
            java.lang.String r2 = r2.getContentType()
            java.util.ArrayList r1 = r1.getPartsByContentType(r2)
            boolean r2 = r1.isEmpty()
            if (r2 != 0) goto L_0x0037
            r2 = 0
            java.lang.Object r1 = r1.get(r2)
            org.apache.poi.openxml4j.opc.PackagePart r1 = (org.apache.poi.openxml4j.opc.PackagePart) r1
            java.io.InputStream r1 = r1.getInputStream()
            r0.readFrom(r1)     // Catch:{ all -> 0x0029 }
            if (r1 == 0) goto L_0x0037
            r1.close()
            goto L_0x0037
        L_0x0029:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x002b }
        L_0x002b:
            r2 = move-exception
            if (r1 == 0) goto L_0x0036
            r1.close()     // Catch:{ all -> 0x0032 }
            goto L_0x0036
        L_0x0032:
            r1 = move-exception
            r0.addSuppressed(r1)
        L_0x0036:
            throw r2
        L_0x0037:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable.<init>(org.apache.poi.openxml4j.opc.OPCPackage, boolean):void");
    }

    public ReadOnlySharedStringsTable(PackagePart packagePart) throws IOException, SAXException {
        this(packagePart, true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
        r0.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001f, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0014, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        if (r1 != null) goto L_0x0017;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ReadOnlySharedStringsTable(org.apache.poi.openxml4j.opc.PackagePart r1, boolean r2) throws java.io.IOException, org.xml.sax.SAXException {
        /*
            r0 = this;
            r0.<init>()
            r0.includePhoneticRuns = r2
            java.io.InputStream r1 = r1.getInputStream()
            r0.readFrom(r1)     // Catch:{ all -> 0x0012 }
            if (r1 == 0) goto L_0x0011
            r1.close()
        L_0x0011:
            return
        L_0x0012:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0014 }
        L_0x0014:
            r2 = move-exception
            if (r1 == 0) goto L_0x001f
            r1.close()     // Catch:{ all -> 0x001b }
            goto L_0x001f
        L_0x001b:
            r1 = move-exception
            r0.addSuppressed(r1)
        L_0x001f:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable.<init>(org.apache.poi.openxml4j.opc.PackagePart, boolean):void");
    }

    public ReadOnlySharedStringsTable(InputStream inputStream) throws IOException, SAXException {
        this(inputStream, true);
    }

    public ReadOnlySharedStringsTable(InputStream inputStream, boolean z) throws IOException, SAXException {
        this.includePhoneticRuns = z;
        readFrom(inputStream);
    }

    public void readFrom(InputStream inputStream) throws IOException, SAXException {
        PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream, 1);
        int read = pushbackInputStream.read();
        if (read > -1) {
            pushbackInputStream.unread(read);
            InputSource inputSource = new InputSource(pushbackInputStream);
            try {
                XMLReader newXMLReader = XMLHelper.newXMLReader();
                newXMLReader.setContentHandler(this);
                newXMLReader.parse(inputSource);
            } catch (ParserConfigurationException e) {
                throw new SAXException("SAX parser appears to be broken - " + e.getMessage());
            }
        }
    }

    public int getCount() {
        return this.count;
    }

    public int getUniqueCount() {
        return this.uniqueCount;
    }

    public RichTextString getItemAt(int i) {
        return new XSSFRichTextString(this.strings.get(i));
    }

    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        if (str != null && !str.equals(XSSFRelation.NS_SPREADSHEETML)) {
            return;
        }
        if ("sst".equals(str2)) {
            String value = attributes.getValue("count");
            if (value != null) {
                this.count = Integer.parseInt(value);
            }
            String value2 = attributes.getValue("uniqueCount");
            if (value2 != null) {
                this.uniqueCount = Integer.parseInt(value2);
            }
            this.strings = new ArrayList(this.uniqueCount);
            this.characters = new StringBuilder(64);
        } else if ("si".equals(str2)) {
            this.characters.setLength(0);
        } else if ("t".equals(str2)) {
            this.tIsOpen = true;
        } else if ("rPh".equals(str2)) {
            this.inRPh = true;
            if (this.includePhoneticRuns && this.characters.length() > 0) {
                this.characters.append(" ");
            }
        }
    }

    public void endElement(String str, String str2, String str3) throws SAXException {
        if (str != null && !str.equals(XSSFRelation.NS_SPREADSHEETML)) {
            return;
        }
        if ("si".equals(str2)) {
            this.strings.add(this.characters.toString());
        } else if ("t".equals(str2)) {
            this.tIsOpen = false;
        } else if ("rPh".equals(str2)) {
            this.inRPh = false;
        }
    }

    public void characters(char[] cArr, int i, int i2) throws SAXException {
        if (this.tIsOpen) {
            boolean z = this.inRPh;
            if (z && this.includePhoneticRuns) {
                this.characters.append(cArr, i, i2);
            } else if (!z) {
                this.characters.append(cArr, i, i2);
            }
        }
    }
}
