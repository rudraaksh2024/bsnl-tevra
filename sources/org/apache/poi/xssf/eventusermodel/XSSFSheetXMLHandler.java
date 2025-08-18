package org.apache.poi.xssf.eventusermodel;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.model.Comments;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.model.Styles;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XSSFSheetXMLHandler extends DefaultHandler {
    private static final Logger LOG = LogManager.getLogger((Class<?>) XSSFSheetXMLHandler.class);
    private String cellRef;
    private Queue<CellAddress> commentCellRefs;
    private final Comments comments;
    private boolean fIsOpen;
    private short formatIndex;
    private String formatString;
    private final DataFormatter formatter;
    private final StringBuilder formula;
    private final boolean formulasNotResults;
    private final StringBuilder headerFooter;
    private boolean hfIsOpen;
    private boolean isIsOpen;
    private xssfDataType nextDataType;
    private int nextRowNum;
    private final SheetContentsHandler output;
    private int rowNum;
    private final SharedStrings sharedStringsTable;
    private final Styles stylesTable;
    private boolean vIsOpen;
    private final StringBuilder value;

    private enum EmptyCellCommentsCheckType {
        CELL,
        END_OF_ROW,
        END_OF_SHEET_DATA
    }

    public interface SheetContentsHandler {
        void cell(String str, String str2, XSSFComment xSSFComment);

        void endRow(int i);

        void endSheet() {
        }

        void headerFooter(String str, boolean z, String str2) {
        }

        void startRow(int i);
    }

    enum xssfDataType {
        BOOLEAN,
        ERROR,
        FORMULA,
        INLINE_STRING,
        SST_STRING,
        NUMBER
    }

    public XSSFSheetXMLHandler(Styles styles, Comments comments2, SharedStrings sharedStrings, SheetContentsHandler sheetContentsHandler, DataFormatter dataFormatter, boolean z) {
        this.value = new StringBuilder(64);
        this.formula = new StringBuilder(64);
        this.headerFooter = new StringBuilder(64);
        this.stylesTable = styles;
        this.comments = comments2;
        this.sharedStringsTable = sharedStrings;
        this.output = sheetContentsHandler;
        this.formulasNotResults = z;
        this.nextDataType = xssfDataType.NUMBER;
        this.formatter = dataFormatter;
        init(comments2);
    }

    public XSSFSheetXMLHandler(Styles styles, SharedStrings sharedStrings, SheetContentsHandler sheetContentsHandler, DataFormatter dataFormatter, boolean z) {
        this(styles, (Comments) null, sharedStrings, sheetContentsHandler, dataFormatter, z);
    }

    public XSSFSheetXMLHandler(Styles styles, SharedStrings sharedStrings, SheetContentsHandler sheetContentsHandler, boolean z) {
        this(styles, sharedStrings, sheetContentsHandler, new DataFormatter(), z);
    }

    private void init(Comments comments2) {
        if (comments2 != null) {
            this.commentCellRefs = new LinkedList();
            Iterator<CellAddress> cellAddresses = comments2.getCellAddresses();
            while (cellAddresses.hasNext()) {
                this.commentCellRefs.add(cellAddresses.next());
            }
        }
    }

    private boolean isTextTag(String str) {
        if ("v".equals(str) || "inlineStr".equals(str)) {
            return true;
        }
        if (!"t".equals(str) || !this.isIsOpen) {
            return false;
        }
        return true;
    }

    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        if (str != null && !str.equals(XSSFRelation.NS_SPREADSHEETML)) {
            return;
        }
        if (isTextTag(str2)) {
            this.vIsOpen = true;
            if (!this.isIsOpen) {
                this.value.setLength(0);
            }
        } else if ("is".equals(str2)) {
            this.isIsOpen = true;
        } else if ("f".equals(str2)) {
            this.formula.setLength(0);
            if (this.nextDataType == xssfDataType.NUMBER) {
                this.nextDataType = xssfDataType.FORMULA;
            }
            String value2 = attributes.getValue("t");
            if (value2 == null || !value2.equals("shared")) {
                this.fIsOpen = true;
                return;
            }
            String value3 = attributes.getValue("ref");
            attributes.getValue("si");
            if (value3 != null) {
                this.fIsOpen = true;
            } else if (this.formulasNotResults) {
                LOG.atWarn().log("shared formulas not yet supported!");
            }
        } else if ("oddHeader".equals(str2) || "evenHeader".equals(str2) || "firstHeader".equals(str2) || "firstFooter".equals(str2) || "oddFooter".equals(str2) || "evenFooter".equals(str2)) {
            this.hfIsOpen = true;
            this.headerFooter.setLength(0);
        } else if ("row".equals(str2)) {
            String value4 = attributes.getValue("r");
            if (value4 != null) {
                this.rowNum = Integer.parseInt(value4) - 1;
            } else {
                this.rowNum = this.nextRowNum;
            }
            this.output.startRow(this.rowNum);
        } else if ("c".equals(str2)) {
            this.nextDataType = xssfDataType.NUMBER;
            this.formatIndex = -1;
            XSSFCellStyle xSSFCellStyle = null;
            this.formatString = null;
            this.cellRef = attributes.getValue("r");
            String value5 = attributes.getValue("t");
            String value6 = attributes.getValue("s");
            if ("b".equals(value5)) {
                this.nextDataType = xssfDataType.BOOLEAN;
            } else if ("e".equals(value5)) {
                this.nextDataType = xssfDataType.ERROR;
            } else if ("inlineStr".equals(value5)) {
                this.nextDataType = xssfDataType.INLINE_STRING;
            } else if ("s".equals(value5)) {
                this.nextDataType = xssfDataType.SST_STRING;
            } else if ("str".equals(value5)) {
                this.nextDataType = xssfDataType.FORMULA;
            } else {
                Styles styles = this.stylesTable;
                if (styles != null) {
                    if (value6 != null) {
                        xSSFCellStyle = this.stylesTable.getStyleAt(Integer.parseInt(value6));
                    } else if (styles.getNumCellStyles() > 0) {
                        xSSFCellStyle = this.stylesTable.getStyleAt(0);
                    }
                }
                if (xSSFCellStyle != null) {
                    this.formatIndex = xSSFCellStyle.getDataFormat();
                    String dataFormatString = xSSFCellStyle.getDataFormatString();
                    this.formatString = dataFormatString;
                    if (dataFormatString == null) {
                        this.formatString = BuiltinFormats.getBuiltinFormat((int) this.formatIndex);
                    }
                }
            }
        }
    }

    public void endElement(String str, String str2, String str3) throws SAXException {
        if (str != null && !str.equals(XSSFRelation.NS_SPREADSHEETML)) {
            return;
        }
        if (isTextTag(str2)) {
            this.vIsOpen = false;
            if (!this.isIsOpen) {
                outputCell();
                this.value.setLength(0);
            }
        } else if ("f".equals(str2)) {
            this.fIsOpen = false;
        } else if ("is".equals(str2)) {
            this.isIsOpen = false;
            outputCell();
            this.value.setLength(0);
        } else if ("row".equals(str2)) {
            checkForEmptyCellComments(EmptyCellCommentsCheckType.END_OF_ROW);
            this.output.endRow(this.rowNum);
            this.nextRowNum = this.rowNum + 1;
        } else if ("sheetData".equals(str2)) {
            checkForEmptyCellComments(EmptyCellCommentsCheckType.END_OF_SHEET_DATA);
            this.output.endSheet();
        } else if ("oddHeader".equals(str2) || "evenHeader".equals(str2) || "firstHeader".equals(str2)) {
            this.hfIsOpen = false;
            this.output.headerFooter(this.headerFooter.toString(), true, str2);
        } else if ("oddFooter".equals(str2) || "evenFooter".equals(str2) || "firstFooter".equals(str2)) {
            this.hfIsOpen = false;
            this.output.headerFooter(this.headerFooter.toString(), false, str2);
        }
    }

    public void characters(char[] cArr, int i, int i2) throws SAXException {
        if (this.vIsOpen) {
            this.value.append(cArr, i, i2);
        }
        if (this.fIsOpen) {
            this.formula.append(cArr, i, i2);
        }
        if (this.hfIsOpen) {
            this.headerFooter.append(cArr, i, i2);
        }
    }

    /* renamed from: org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$xssf$eventusermodel$XSSFSheetXMLHandler$xssfDataType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler$xssfDataType[] r0 = org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.xssfDataType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$xssf$eventusermodel$XSSFSheetXMLHandler$xssfDataType = r0
                org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler$xssfDataType r1 = org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.xssfDataType.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$eventusermodel$XSSFSheetXMLHandler$xssfDataType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler$xssfDataType r1 = org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.xssfDataType.ERROR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$eventusermodel$XSSFSheetXMLHandler$xssfDataType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler$xssfDataType r1 = org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.xssfDataType.FORMULA     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$eventusermodel$XSSFSheetXMLHandler$xssfDataType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler$xssfDataType r1 = org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.xssfDataType.INLINE_STRING     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$eventusermodel$XSSFSheetXMLHandler$xssfDataType     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler$xssfDataType r1 = org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.xssfDataType.SST_STRING     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$eventusermodel$XSSFSheetXMLHandler$xssfDataType     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler$xssfDataType r1 = org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.xssfDataType.NUMBER     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.AnonymousClass1.<clinit>():void");
        }
    }

    private void outputCell() {
        String str;
        XSSFComment xSSFComment = null;
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$xssf$eventusermodel$XSSFSheetXMLHandler$xssfDataType[this.nextDataType.ordinal()]) {
            case 1:
                if (this.value.charAt(0) != '0') {
                    str = "TRUE";
                    break;
                } else {
                    str = "FALSE";
                    break;
                }
            case 2:
                str = "ERROR:" + this.value;
                break;
            case 3:
                if (!this.formulasNotResults) {
                    str = this.value.toString();
                    if (this.formatString != null) {
                        try {
                            str = this.formatter.formatRawCellContents(Double.parseDouble(str), this.formatIndex, this.formatString);
                            break;
                        } catch (NumberFormatException unused) {
                            break;
                        }
                    }
                } else {
                    str = this.formula.toString();
                    break;
                }
                break;
            case 4:
                str = new XSSFRichTextString(this.value.toString()).toString();
                break;
            case 5:
                String sb = this.value.toString();
                if (sb.length() > 0) {
                    try {
                        str = this.sharedStringsTable.getItemAt(Integer.parseInt(sb)).toString();
                        break;
                    } catch (NumberFormatException e) {
                        LOG.atError().withThrowable(e).log("Failed to parse SST index '{}'", (Object) sb);
                    }
                }
                str = null;
                break;
            case 6:
                str = this.value.toString();
                if (this.formatString != null && str.length() > 0) {
                    str = this.formatter.formatRawCellContents(Double.parseDouble(str), this.formatIndex, this.formatString);
                    break;
                }
            default:
                str = "(TODO: Unexpected type: " + this.nextDataType + ")";
                break;
        }
        checkForEmptyCellComments(EmptyCellCommentsCheckType.CELL);
        Comments comments2 = this.comments;
        if (comments2 != null) {
            xSSFComment = comments2.findCellComment(new CellAddress(this.cellRef));
        }
        this.output.cell(this.cellRef, str, xSSFComment);
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0080 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void checkForEmptyCellComments(org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.EmptyCellCommentsCheckType r5) {
        /*
            r4 = this;
            java.util.Queue<org.apache.poi.ss.util.CellAddress> r0 = r4.commentCellRefs
            if (r0 == 0) goto L_0x00c9
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x00c9
            org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler$EmptyCellCommentsCheckType r0 = org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.EmptyCellCommentsCheckType.END_OF_SHEET_DATA
            if (r5 != r0) goto L_0x0023
        L_0x000e:
            java.util.Queue<org.apache.poi.ss.util.CellAddress> r5 = r4.commentCellRefs
            boolean r5 = r5.isEmpty()
            if (r5 != 0) goto L_0x0022
            java.util.Queue<org.apache.poi.ss.util.CellAddress> r5 = r4.commentCellRefs
            java.lang.Object r5 = r5.remove()
            org.apache.poi.ss.util.CellAddress r5 = (org.apache.poi.ss.util.CellAddress) r5
            r4.outputEmptyCellComment(r5)
            goto L_0x000e
        L_0x0022:
            return
        L_0x0023:
            java.lang.String r0 = r4.cellRef
            if (r0 != 0) goto L_0x0067
            org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler$EmptyCellCommentsCheckType r0 = org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.EmptyCellCommentsCheckType.END_OF_ROW
            if (r5 != r0) goto L_0x0050
        L_0x002b:
            java.util.Queue<org.apache.poi.ss.util.CellAddress> r5 = r4.commentCellRefs
            boolean r5 = r5.isEmpty()
            if (r5 != 0) goto L_0x004f
            java.util.Queue<org.apache.poi.ss.util.CellAddress> r5 = r4.commentCellRefs
            java.lang.Object r5 = r5.peek()
            org.apache.poi.ss.util.CellAddress r5 = (org.apache.poi.ss.util.CellAddress) r5
            int r5 = r5.getRow()
            int r0 = r4.rowNum
            if (r5 != r0) goto L_0x004f
            java.util.Queue<org.apache.poi.ss.util.CellAddress> r5 = r4.commentCellRefs
            java.lang.Object r5 = r5.remove()
            org.apache.poi.ss.util.CellAddress r5 = (org.apache.poi.ss.util.CellAddress) r5
            r4.outputEmptyCellComment(r5)
            goto L_0x002b
        L_0x004f:
            return
        L_0x0050:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Cell ref should be null only if there are only empty cells in the row; rowNum: "
            r0.<init>(r1)
            int r4 = r4.rowNum
            java.lang.StringBuilder r4 = r0.append(r4)
            java.lang.String r4 = r4.toString()
            r5.<init>(r4)
            throw r5
        L_0x0067:
            org.apache.poi.ss.util.CellAddress r0 = new org.apache.poi.ss.util.CellAddress
            java.lang.String r1 = r4.cellRef
            r0.<init>((java.lang.String) r1)
            java.util.Queue<org.apache.poi.ss.util.CellAddress> r1 = r4.commentCellRefs
            java.lang.Object r1 = r1.peek()
            org.apache.poi.ss.util.CellAddress r1 = (org.apache.poi.ss.util.CellAddress) r1
            org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler$EmptyCellCommentsCheckType r2 = org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.EmptyCellCommentsCheckType.CELL
            if (r5 != r2) goto L_0x0086
            boolean r2 = r0.equals(r1)
            if (r2 == 0) goto L_0x0086
            java.util.Queue<org.apache.poi.ss.util.CellAddress> r4 = r4.commentCellRefs
            r4.remove()
            return
        L_0x0086:
            int r0 = r1.compareTo((org.apache.poi.ss.util.CellAddress) r0)
            if (r0 <= 0) goto L_0x00a4
            org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler$EmptyCellCommentsCheckType r2 = org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.EmptyCellCommentsCheckType.END_OF_ROW
            if (r5 != r2) goto L_0x00a4
            int r2 = r1.getRow()
            int r3 = r4.rowNum
            if (r2 > r3) goto L_0x00a4
            java.util.Queue<org.apache.poi.ss.util.CellAddress> r0 = r4.commentCellRefs
            java.lang.Object r0 = r0.remove()
            org.apache.poi.ss.util.CellAddress r0 = (org.apache.poi.ss.util.CellAddress) r0
            r4.outputEmptyCellComment(r0)
            goto L_0x00bf
        L_0x00a4:
            if (r0 >= 0) goto L_0x00be
            org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler$EmptyCellCommentsCheckType r0 = org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.EmptyCellCommentsCheckType.CELL
            if (r5 != r0) goto L_0x00be
            int r0 = r1.getRow()
            int r1 = r4.rowNum
            if (r0 > r1) goto L_0x00be
            java.util.Queue<org.apache.poi.ss.util.CellAddress> r0 = r4.commentCellRefs
            java.lang.Object r0 = r0.remove()
            org.apache.poi.ss.util.CellAddress r0 = (org.apache.poi.ss.util.CellAddress) r0
            r4.outputEmptyCellComment(r0)
            goto L_0x00bf
        L_0x00be:
            r0 = 0
        L_0x00bf:
            if (r0 == 0) goto L_0x00c9
            java.util.Queue<org.apache.poi.ss.util.CellAddress> r0 = r4.commentCellRefs
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0067
        L_0x00c9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.checkForEmptyCellComments(org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler$EmptyCellCommentsCheckType):void");
    }

    private void outputEmptyCellComment(CellAddress cellAddress) {
        this.output.cell(cellAddress.formatAsString(), (String) null, this.comments.findCellComment(cellAddress));
    }
}
