package org.apache.poi.xssf.binary;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;

@Internal
public class XSSFBCommentsTable extends XSSFBParser {
    private StringBuilder authorBuffer = new StringBuilder();
    private int authorId = -1;
    private List<String> authors = new ArrayList();
    private CellAddress cellAddress;
    private XSSFBCellRange cellRange;
    private String comment;
    private Queue<CellAddress> commentAddresses = new LinkedList();
    private Map<CellAddress, XSSFBComment> comments = new TreeMap();

    public XSSFBCommentsTable(InputStream inputStream) throws IOException {
        super(inputStream);
        parse();
        this.commentAddresses.addAll(this.comments.keySet());
    }

    /* renamed from: org.apache.poi.xssf.binary.XSSFBCommentsTable$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                org.apache.poi.xssf.binary.XSSFBRecordType[] r0 = org.apache.poi.xssf.binary.XSSFBRecordType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType = r0
                org.apache.poi.xssf.binary.XSSFBRecordType r1 = org.apache.poi.xssf.binary.XSSFBRecordType.BrtBeginComment     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.xssf.binary.XSSFBRecordType r1 = org.apache.poi.xssf.binary.XSSFBRecordType.BrtCommentText     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.xssf.binary.XSSFBRecordType r1 = org.apache.poi.xssf.binary.XSSFBRecordType.BrtEndComment     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.xssf.binary.XSSFBRecordType r1 = org.apache.poi.xssf.binary.XSSFBRecordType.BrtCommentAuthor     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.binary.XSSFBCommentsTable.AnonymousClass1.<clinit>():void");
        }
    }

    public void handleRecord(int i, byte[] bArr) throws XSSFBParseException {
        int i2 = AnonymousClass1.$SwitchMap$org$apache$poi$xssf$binary$XSSFBRecordType[XSSFBRecordType.lookup(i).ordinal()];
        if (i2 == 1) {
            this.authorId = XSSFBUtils.castToInt(LittleEndian.getUInt(bArr));
            this.cellRange = XSSFBCellRange.parse(bArr, 4, this.cellRange);
            this.cellAddress = new CellAddress(this.cellRange.firstRow, this.cellRange.firstCol);
        } else if (i2 == 2) {
            this.comment = XSSFBRichStr.build(bArr, 0).getString();
        } else if (i2 == 3) {
            this.comments.put(this.cellAddress, new XSSFBComment(this.cellAddress, this.authors.get(this.authorId), this.comment));
            this.authorId = -1;
            this.cellAddress = null;
        } else if (i2 == 4) {
            this.authorBuffer.setLength(0);
            XSSFBUtils.readXLWideString(bArr, 0, this.authorBuffer);
            this.authors.add(this.authorBuffer.toString());
        }
    }

    public Queue<CellAddress> getAddresses() {
        return this.commentAddresses;
    }

    public XSSFBComment get(CellAddress cellAddress2) {
        if (cellAddress2 == null) {
            return null;
        }
        return this.comments.get(cellAddress2);
    }
}
