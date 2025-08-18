package org.apache.poi.xssf.binary;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import kotlin.UByte;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.util.Internal;

@Internal
public class XSSFBStylesTable extends XSSFBParser {
    private boolean inCellXFS;
    private boolean inFmts;
    private final SortedMap<Short, String> numberFormats = new TreeMap();
    private final List<Short> styleIds = new ArrayList();

    public XSSFBStylesTable(InputStream inputStream) throws IOException {
        super(inputStream);
        parse();
    }

    /* access modifiers changed from: package-private */
    public String getNumberFormatString(int i) {
        short numberFormatIndex = getNumberFormatIndex(i);
        if (this.numberFormats.containsKey(Short.valueOf(numberFormatIndex))) {
            return (String) this.numberFormats.get(Short.valueOf(numberFormatIndex));
        }
        return BuiltinFormats.getBuiltinFormat((int) numberFormatIndex);
    }

    /* access modifiers changed from: package-private */
    public short getNumberFormatIndex(int i) {
        return this.styleIds.get(i).shortValue();
    }

    /* renamed from: org.apache.poi.xssf.binary.XSSFBStylesTable$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.xssf.binary.XSSFBRecordType[] r0 = org.apache.poi.xssf.binary.XSSFBRecordType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType = r0
                org.apache.poi.xssf.binary.XSSFBRecordType r1 = org.apache.poi.xssf.binary.XSSFBRecordType.BrtBeginCellXFs     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.xssf.binary.XSSFBRecordType r1 = org.apache.poi.xssf.binary.XSSFBRecordType.BrtEndCellXFs     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.xssf.binary.XSSFBRecordType r1 = org.apache.poi.xssf.binary.XSSFBRecordType.BrtXf     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.xssf.binary.XSSFBRecordType r1 = org.apache.poi.xssf.binary.XSSFBRecordType.BrtBeginFmts     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.xssf.binary.XSSFBRecordType r1 = org.apache.poi.xssf.binary.XSSFBRecordType.BrtEndFmts     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.xssf.binary.XSSFBRecordType r1 = org.apache.poi.xssf.binary.XSSFBRecordType.BrtFmt     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.binary.XSSFBStylesTable.AnonymousClass1.<clinit>():void");
        }
    }

    public void handleRecord(int i, byte[] bArr) throws XSSFBParseException {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType[XSSFBRecordType.lookup(i).ordinal()]) {
            case 1:
                this.inCellXFS = true;
                return;
            case 2:
                this.inCellXFS = false;
                return;
            case 3:
                if (this.inCellXFS) {
                    handleBrtXFInCellXF(bArr);
                    return;
                }
                return;
            case 4:
                this.inFmts = true;
                return;
            case 5:
                this.inFmts = false;
                return;
            case 6:
                if (this.inFmts) {
                    handleFormat(bArr);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void handleFormat(byte[] bArr) {
        byte b = bArr[0] & UByte.MAX_VALUE;
        if (b <= 32767) {
            StringBuilder sb = new StringBuilder();
            XSSFBUtils.readXLWideString(bArr, 2, sb);
            this.numberFormats.put(Short.valueOf((short) b), sb.toString());
            return;
        }
        throw new POIXMLException("Format id must be a short");
    }

    private void handleBrtXFInCellXF(byte[] bArr) {
        this.styleIds.add(Short.valueOf((short) (bArr[2] & UByte.MAX_VALUE)));
    }
}
