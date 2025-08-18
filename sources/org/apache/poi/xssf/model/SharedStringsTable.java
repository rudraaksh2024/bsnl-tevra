package org.apache.poi.xssf.model;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSst;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.SstDocument;

public class SharedStringsTable extends POIXMLDocumentPart implements SharedStrings, Closeable {
    private static final XmlOptions options;
    private SstDocument _sstDoc;
    protected int count;
    private final Map<String, Integer> stmap = new HashMap();
    private final List<CTRst> strings = new ArrayList();
    protected int uniqueCount;

    public void close() throws IOException {
    }

    static {
        XmlOptions xmlOptions = new XmlOptions();
        options = xmlOptions;
        xmlOptions.setSaveInner();
        xmlOptions.setSaveAggressiveNamespaces();
        xmlOptions.setUseDefaultNamespace(true);
        xmlOptions.setSaveImplicitNamespaces(Collections.singletonMap("", XSSFRelation.NS_SPREADSHEETML));
    }

    public SharedStringsTable() {
        SstDocument newInstance = SstDocument.Factory.newInstance();
        this._sstDoc = newInstance;
        newInstance.addNewSst();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0027, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
        r1.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002b, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0020, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0021, code lost:
        if (r2 != null) goto L_0x0023;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SharedStringsTable(org.apache.poi.openxml4j.opc.PackagePart r2) throws java.io.IOException {
        /*
            r1 = this;
            r1.<init>((org.apache.poi.openxml4j.opc.PackagePart) r2)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1.strings = r0
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r1.stmap = r0
            java.io.InputStream r2 = r2.getInputStream()
            r1.readFrom(r2)     // Catch:{ all -> 0x001e }
            if (r2 == 0) goto L_0x001d
            r2.close()
        L_0x001d:
            return
        L_0x001e:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0020 }
        L_0x0020:
            r0 = move-exception
            if (r2 == 0) goto L_0x002b
            r2.close()     // Catch:{ all -> 0x0027 }
            goto L_0x002b
        L_0x0027:
            r2 = move-exception
            r1.addSuppressed(r2)
        L_0x002b:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.model.SharedStringsTable.<init>(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    public void readFrom(InputStream inputStream) throws IOException {
        try {
            SstDocument parse = SstDocument.Factory.parse(inputStream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            this._sstDoc = parse;
            CTSst sst = parse.getSst();
            this.count = (int) sst.getCount();
            this.uniqueCount = (int) sst.getUniqueCount();
            int i = 0;
            for (CTRst cTRst : sst.getSiArray()) {
                this.stmap.put(xmlText(cTRst), Integer.valueOf(i));
                this.strings.add(cTRst);
                i++;
            }
        } catch (XmlException e) {
            throw new IOException("unable to parse shared strings table", e);
        }
    }

    /* access modifiers changed from: protected */
    public String xmlText(CTRst cTRst) {
        return cTRst.xmlText(options);
    }

    public RichTextString getItemAt(int i) {
        return new XSSFRichTextString(this.strings.get(i));
    }

    public int getCount() {
        return this.count;
    }

    public int getUniqueCount() {
        return this.uniqueCount;
    }

    /* access modifiers changed from: package-private */
    @Internal
    public int addEntry(CTRst cTRst) {
        String xmlText = xmlText(cTRst);
        this.count++;
        if (this.stmap.containsKey(xmlText)) {
            return this.stmap.get(xmlText).intValue();
        }
        this.uniqueCount++;
        CTRst addNewSi = this._sstDoc.getSst().addNewSi();
        addNewSi.set(cTRst);
        int size = this.strings.size();
        this.stmap.put(xmlText, Integer.valueOf(size));
        this.strings.add(addNewSi);
        return size;
    }

    public int addSharedStringItem(RichTextString richTextString) {
        if (richTextString instanceof XSSFRichTextString) {
            return addEntry(((XSSFRichTextString) richTextString).getCTRst());
        }
        throw new IllegalArgumentException("Only XSSFRichTextString argument is supported");
    }

    public List<RichTextString> getSharedStringItems() {
        ArrayList arrayList = new ArrayList();
        for (CTRst xSSFRichTextString : this.strings) {
            arrayList.add(new XSSFRichTextString(xSSFRichTextString));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveCDataLengthThreshold(1000000);
        xmlOptions.setSaveCDataEntityCountThreshold(-1);
        CTSst sst = this._sstDoc.getSst();
        sst.setCount((long) this.count);
        sst.setUniqueCount((long) this.uniqueCount);
        this._sstDoc.save(outputStream, xmlOptions);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001e, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0014, code lost:
        if (r0 != null) goto L_0x0016;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void commit() throws java.io.IOException {
        /*
            r2 = this;
            org.apache.poi.openxml4j.opc.PackagePart r0 = r2.getPackagePart()
            java.io.OutputStream r0 = r0.getOutputStream()
            r2.writeTo(r0)     // Catch:{ all -> 0x0011 }
            if (r0 == 0) goto L_0x0010
            r0.close()
        L_0x0010:
            return
        L_0x0011:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0013 }
        L_0x0013:
            r1 = move-exception
            if (r0 == 0) goto L_0x001e
            r0.close()     // Catch:{ all -> 0x001a }
            goto L_0x001e
        L_0x001a:
            r0 = move-exception
            r2.addSuppressed(r0)
        L_0x001e:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.model.SharedStringsTable.commit():void");
    }
}
