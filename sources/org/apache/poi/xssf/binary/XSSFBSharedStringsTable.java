package org.apache.poi.xssf.binary;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

@Internal
public class XSSFBSharedStringsTable implements SharedStrings {
    /* access modifiers changed from: private */
    public int count;
    /* access modifiers changed from: private */
    public List<String> strings = new ArrayList();
    /* access modifiers changed from: private */
    public int uniqueCount;

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0030, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0031, code lost:
        if (r2 != null) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0037, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0038, code lost:
        r1.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003b, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public XSSFBSharedStringsTable(org.apache.poi.openxml4j.opc.OPCPackage r2) throws java.io.IOException, org.xml.sax.SAXException {
        /*
            r1 = this;
            r1.<init>()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1.strings = r0
            org.apache.poi.xssf.binary.XSSFBRelation r0 = org.apache.poi.xssf.binary.XSSFBRelation.SHARED_STRINGS_BINARY
            java.lang.String r0 = r0.getContentType()
            java.util.ArrayList r2 = r2.getPartsByContentType(r0)
            boolean r0 = r2.isEmpty()
            if (r0 != 0) goto L_0x003c
            r0 = 0
            java.lang.Object r2 = r2.get(r0)
            org.apache.poi.openxml4j.opc.PackagePart r2 = (org.apache.poi.openxml4j.opc.PackagePart) r2
            java.io.InputStream r2 = r2.getInputStream()
            r1.readFrom(r2)     // Catch:{ all -> 0x002e }
            if (r2 == 0) goto L_0x003c
            r2.close()
            goto L_0x003c
        L_0x002e:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0030 }
        L_0x0030:
            r0 = move-exception
            if (r2 == 0) goto L_0x003b
            r2.close()     // Catch:{ all -> 0x0037 }
            goto L_0x003b
        L_0x0037:
            r2 = move-exception
            r1.addSuppressed(r2)
        L_0x003b:
            throw r0
        L_0x003c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.binary.XSSFBSharedStringsTable.<init>(org.apache.poi.openxml4j.opc.OPCPackage):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
        r1.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0024, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0019, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001a, code lost:
        if (r2 != null) goto L_0x001c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    XSSFBSharedStringsTable(org.apache.poi.openxml4j.opc.PackagePart r2) throws java.io.IOException, org.xml.sax.SAXException {
        /*
            r1 = this;
            r1.<init>()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1.strings = r0
            java.io.InputStream r2 = r2.getInputStream()
            r1.readFrom(r2)     // Catch:{ all -> 0x0017 }
            if (r2 == 0) goto L_0x0016
            r2.close()
        L_0x0016:
            return
        L_0x0017:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0019 }
        L_0x0019:
            r0 = move-exception
            if (r2 == 0) goto L_0x0024
            r2.close()     // Catch:{ all -> 0x0020 }
            goto L_0x0024
        L_0x0020:
            r2 = move-exception
            r1.addSuppressed(r2)
        L_0x0024:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.binary.XSSFBSharedStringsTable.<init>(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    private void readFrom(InputStream inputStream) throws IOException {
        new SSTBinaryReader(inputStream).parse();
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

    private class SSTBinaryReader extends XSSFBParser {
        SSTBinaryReader(InputStream inputStream) {
            super(inputStream);
        }

        public void handleRecord(int i, byte[] bArr) throws XSSFBParseException {
            int i2 = AnonymousClass1.$SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType[XSSFBRecordType.lookup(i).ordinal()];
            if (i2 == 1) {
                XSSFBSharedStringsTable.this.strings.add(XSSFBRichStr.build(bArr, 0).getString());
            } else if (i2 == 2) {
                int unused = XSSFBSharedStringsTable.this.count = XSSFBUtils.castToInt(LittleEndian.getUInt(bArr, 0));
                int unused2 = XSSFBSharedStringsTable.this.uniqueCount = XSSFBUtils.castToInt(LittleEndian.getUInt(bArr, 4));
            }
        }
    }

    /* renamed from: org.apache.poi.xssf.binary.XSSFBSharedStringsTable$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                org.apache.poi.xssf.binary.XSSFBRecordType[] r0 = org.apache.poi.xssf.binary.XSSFBRecordType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType = r0
                org.apache.poi.xssf.binary.XSSFBRecordType r1 = org.apache.poi.xssf.binary.XSSFBRecordType.BrtSstItem     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.xssf.binary.XSSFBRecordType r1 = org.apache.poi.xssf.binary.XSSFBRecordType.BrtBeginSst     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.binary.XSSFBSharedStringsTable.AnonymousClass1.<clinit>():void");
        }
    }
}
