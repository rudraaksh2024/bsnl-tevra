package org.apache.poi.hssf.record;

import com.google.firebase.messaging.Constants;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.hpsf.ClassID;
import org.apache.poi.hpsf.ClassIDPredefined;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.HexRead;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.util.StringUtil;

public final class HyperlinkRecord extends StandardRecord {
    private static final byte[] FILE_TAIL;
    static final int HLINK_ABS = 2;
    static final int HLINK_LABEL = 20;
    static final int HLINK_PLACE = 8;
    private static final int HLINK_TARGET_FRAME = 128;
    private static final int HLINK_UNC_PATH = 256;
    static final int HLINK_URL = 1;
    private static final Logger LOG = LogManager.getLogger((Class<?>) HyperlinkRecord.class);
    private static final int TAIL_SIZE;
    private static final byte[] URL_TAIL = HexRead.readFromString("79 58 81 F4  3B 1D 7F 48   AF 2C 82 5D  C4 85 27 63   00 00 00 00  A5 AB 00 00");
    public static final short sid = 440;
    private String _address;
    private int _fileOpts;
    private ClassID _guid;
    private String _label;
    private int _linkOpts;
    private ClassID _moniker;
    private CellRangeAddress _range;
    private String _shortFilename;
    private String _targetFrame;
    private String _textMark;
    private byte[] _uninterpretedTail;

    public int getLabelOptions() {
        return 2;
    }

    public short getSid() {
        return sid;
    }

    static {
        byte[] readFromString = HexRead.readFromString("FF FF AD DE  00 00 00 00   00 00 00 00  00 00 00 00   00 00 00 00  00 00 00 00");
        FILE_TAIL = readFromString;
        TAIL_SIZE = readFromString.length;
    }

    public HyperlinkRecord() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: byte[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public HyperlinkRecord(org.apache.poi.hssf.record.HyperlinkRecord r3) {
        /*
            r2 = this;
            r2.<init>(r3)
            org.apache.poi.ss.util.CellRangeAddress r0 = r3._range
            r1 = 0
            if (r0 != 0) goto L_0x000a
            r0 = r1
            goto L_0x000e
        L_0x000a:
            org.apache.poi.ss.util.CellRangeAddress r0 = r0.copy()
        L_0x000e:
            r2._range = r0
            org.apache.poi.hpsf.ClassID r0 = r3._guid
            if (r0 != 0) goto L_0x0016
            r0 = r1
            goto L_0x001a
        L_0x0016:
            org.apache.poi.hpsf.ClassID r0 = r0.copy()
        L_0x001a:
            r2._guid = r0
            int r0 = r3._fileOpts
            r2._fileOpts = r0
            int r0 = r3._linkOpts
            r2._linkOpts = r0
            java.lang.String r0 = r3._label
            r2._label = r0
            java.lang.String r0 = r3._targetFrame
            r2._targetFrame = r0
            org.apache.poi.hpsf.ClassID r0 = r3._moniker
            if (r0 != 0) goto L_0x0032
            r0 = r1
            goto L_0x0036
        L_0x0032:
            org.apache.poi.hpsf.ClassID r0 = r0.copy()
        L_0x0036:
            r2._moniker = r0
            java.lang.String r0 = r3._shortFilename
            r2._shortFilename = r0
            java.lang.String r0 = r3._address
            r2._address = r0
            java.lang.String r0 = r3._textMark
            r2._textMark = r0
            byte[] r3 = r3._uninterpretedTail
            if (r3 != 0) goto L_0x0049
            goto L_0x0050
        L_0x0049:
            java.lang.Object r3 = r3.clone()
            r1 = r3
            byte[] r1 = (byte[]) r1
        L_0x0050:
            r2._uninterpretedTail = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.record.HyperlinkRecord.<init>(org.apache.poi.hssf.record.HyperlinkRecord):void");
    }

    public int getFirstColumn() {
        return this._range.getFirstColumn();
    }

    public void setFirstColumn(int i) {
        this._range.setFirstColumn(i);
    }

    public int getLastColumn() {
        return this._range.getLastColumn();
    }

    public void setLastColumn(int i) {
        this._range.setLastColumn(i);
    }

    public int getFirstRow() {
        return this._range.getFirstRow();
    }

    public void setFirstRow(int i) {
        this._range.setFirstRow(i);
    }

    public int getLastRow() {
        return this._range.getLastRow();
    }

    public void setLastRow(int i) {
        this._range.setLastRow(i);
    }

    /* access modifiers changed from: package-private */
    public ClassID getGuid() {
        return this._guid;
    }

    /* access modifiers changed from: package-private */
    public ClassID getMoniker() {
        return this._moniker;
    }

    private static String cleanString(String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(0);
        if (indexOf < 0) {
            return str;
        }
        return str.substring(0, indexOf);
    }

    private static String appendNullTerm(String str) {
        if (str == null) {
            return null;
        }
        return str + 0;
    }

    public String getLabel() {
        return cleanString(this._label);
    }

    public void setLabel(String str) {
        this._label = appendNullTerm(str);
    }

    public String getTargetFrame() {
        return cleanString(this._targetFrame);
    }

    public String getAddress() {
        if ((this._linkOpts & 1) != 0 && ClassIDPredefined.FILE_MONIKER.equals(this._moniker)) {
            String str = this._address;
            if (str == null) {
                str = this._shortFilename;
            }
            return cleanString(str);
        } else if ((this._linkOpts & 8) != 0) {
            return cleanString(this._textMark);
        } else {
            return cleanString(this._address);
        }
    }

    public void setAddress(String str) {
        if ((this._linkOpts & 1) != 0 && ClassIDPredefined.FILE_MONIKER.equals(this._moniker)) {
            this._shortFilename = appendNullTerm(str);
        } else if ((this._linkOpts & 8) != 0) {
            this._textMark = appendNullTerm(str);
        } else {
            this._address = appendNullTerm(str);
        }
    }

    public String getShortFilename() {
        return cleanString(this._shortFilename);
    }

    public void setShortFilename(String str) {
        this._shortFilename = appendNullTerm(str);
    }

    public String getTextMark() {
        return cleanString(this._textMark);
    }

    public void setTextMark(String str) {
        this._textMark = appendNullTerm(str);
    }

    /* access modifiers changed from: package-private */
    public int getLinkOptions() {
        return this._linkOpts;
    }

    public int getFileOptions() {
        return this._fileOpts;
    }

    public HyperlinkRecord(RecordInputStream recordInputStream) {
        this._range = new CellRangeAddress(recordInputStream);
        this._guid = new ClassID((LittleEndianInput) recordInputStream);
        int readInt = recordInputStream.readInt();
        if (readInt == 2) {
            int readInt2 = recordInputStream.readInt();
            this._linkOpts = readInt2;
            if ((readInt2 & 20) != 0) {
                this._label = recordInputStream.readUnicodeLEString(recordInputStream.readInt());
            }
            if ((this._linkOpts & 128) != 0) {
                this._targetFrame = recordInputStream.readUnicodeLEString(recordInputStream.readInt());
            }
            int i = this._linkOpts;
            if (!((i & 1) == 0 || (i & 256) == 0)) {
                this._moniker = null;
                this._address = recordInputStream.readUnicodeLEString(recordInputStream.readInt());
            }
            int i2 = this._linkOpts;
            if ((i2 & 1) != 0 && (i2 & 256) == 0) {
                this._moniker = new ClassID((LittleEndianInput) recordInputStream);
                if (ClassIDPredefined.URL_MONIKER.equals(this._moniker)) {
                    int readInt3 = recordInputStream.readInt();
                    if (readInt3 == recordInputStream.remaining()) {
                        this._address = recordInputStream.readUnicodeLEString(readInt3 / 2);
                    } else {
                        this._address = recordInputStream.readUnicodeLEString((readInt3 - TAIL_SIZE) / 2);
                        this._uninterpretedTail = readTail(URL_TAIL, recordInputStream);
                    }
                } else if (ClassIDPredefined.FILE_MONIKER.equals(this._moniker)) {
                    this._fileOpts = recordInputStream.readShort();
                    this._shortFilename = StringUtil.readCompressedUnicode(recordInputStream, recordInputStream.readInt());
                    this._uninterpretedTail = readTail(FILE_TAIL, recordInputStream);
                    if (recordInputStream.readInt() > 0) {
                        int readInt4 = recordInputStream.readInt();
                        recordInputStream.readUShort();
                        this._address = StringUtil.readUnicodeLE(recordInputStream, readInt4 / 2);
                    } else {
                        this._address = null;
                    }
                } else if (ClassIDPredefined.STD_MONIKER.equals(this._moniker)) {
                    this._fileOpts = recordInputStream.readShort();
                    byte[] safelyAllocate = IOUtils.safelyAllocate((long) recordInputStream.readInt(), HSSFWorkbook.getMaxRecordLength());
                    recordInputStream.readFully(safelyAllocate);
                    this._address = new String(safelyAllocate, StringUtil.UTF8);
                }
            }
            if ((this._linkOpts & 8) != 0) {
                this._textMark = recordInputStream.readUnicodeLEString(recordInputStream.readInt());
            }
            if (recordInputStream.remaining() > 0) {
                LOG.atWarn().log("Hyperlink data remains: {} : {}", Unbox.box(recordInputStream.remaining()), HexDump.toHex(recordInputStream.readRemainder()));
                return;
            }
            return;
        }
        throw new RecordFormatException("Stream Version must be 0x2 but found " + readInt);
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        this._range.serialize(littleEndianOutput);
        this._guid.write(littleEndianOutput);
        littleEndianOutput.writeInt(2);
        littleEndianOutput.writeInt(this._linkOpts);
        if ((this._linkOpts & 20) != 0) {
            littleEndianOutput.writeInt(this._label.length());
            StringUtil.putUnicodeLE(this._label, littleEndianOutput);
        }
        if ((this._linkOpts & 128) != 0) {
            littleEndianOutput.writeInt(this._targetFrame.length());
            StringUtil.putUnicodeLE(this._targetFrame, littleEndianOutput);
        }
        int i = this._linkOpts;
        if (!((i & 1) == 0 || (i & 256) == 0)) {
            littleEndianOutput.writeInt(this._address.length());
            StringUtil.putUnicodeLE(this._address, littleEndianOutput);
        }
        int i2 = this._linkOpts;
        if ((i2 & 1) != 0 && (i2 & 256) == 0) {
            this._moniker.write(littleEndianOutput);
            if (ClassIDPredefined.URL_MONIKER.equals(this._moniker)) {
                if (this._uninterpretedTail == null) {
                    littleEndianOutput.writeInt(this._address.length() * 2);
                    StringUtil.putUnicodeLE(this._address, littleEndianOutput);
                } else {
                    littleEndianOutput.writeInt((this._address.length() * 2) + TAIL_SIZE);
                    StringUtil.putUnicodeLE(this._address, littleEndianOutput);
                    writeTail(this._uninterpretedTail, littleEndianOutput);
                }
            } else if (ClassIDPredefined.FILE_MONIKER.equals(this._moniker)) {
                littleEndianOutput.writeShort(this._fileOpts);
                littleEndianOutput.writeInt(this._shortFilename.length());
                StringUtil.putCompressedUnicode(this._shortFilename, littleEndianOutput);
                writeTail(this._uninterpretedTail, littleEndianOutput);
                String str = this._address;
                if (str == null) {
                    littleEndianOutput.writeInt(0);
                } else {
                    int length = str.length() * 2;
                    littleEndianOutput.writeInt(length + 6);
                    littleEndianOutput.writeInt(length);
                    littleEndianOutput.writeShort(3);
                    StringUtil.putUnicodeLE(this._address, littleEndianOutput);
                }
            }
        }
        if ((this._linkOpts & 8) != 0) {
            littleEndianOutput.writeInt(this._textMark.length());
            StringUtil.putUnicodeLE(this._textMark, littleEndianOutput);
        }
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        int length;
        int length2 = (this._linkOpts & 20) != 0 ? 36 + (this._label.length() * 2) : 32;
        if ((this._linkOpts & 128) != 0) {
            length2 = length2 + 4 + (this._targetFrame.length() * 2);
        }
        int i = this._linkOpts;
        if (!((i & 1) == 0 || (i & 256) == 0)) {
            length2 = length2 + 4 + (this._address.length() * 2);
        }
        int i2 = this._linkOpts;
        if ((i2 & 1) != 0 && (i2 & 256) == 0) {
            length2 += 16;
            if (ClassIDPredefined.URL_MONIKER.equals(this._moniker)) {
                length2 = length2 + 4 + (this._address.length() * 2);
                if (this._uninterpretedTail != null) {
                    length = TAIL_SIZE;
                }
            } else if (ClassIDPredefined.FILE_MONIKER.equals(this._moniker)) {
                length2 = length2 + 2 + 4 + this._shortFilename.length() + TAIL_SIZE + 4;
                String str = this._address;
                if (str != null) {
                    length2 += 6;
                    length = str.length() * 2;
                }
            }
            length2 += length;
        }
        return (this._linkOpts & 8) != 0 ? length2 + 4 + (this._textMark.length() * 2) : length2;
    }

    private static byte[] readTail(byte[] bArr, LittleEndianInput littleEndianInput) {
        byte[] bArr2 = new byte[TAIL_SIZE];
        littleEndianInput.readFully(bArr2);
        return bArr2;
    }

    private static void writeTail(byte[] bArr, LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.write(bArr);
    }

    public boolean isUrlLink() {
        int i = this._linkOpts;
        return (i & 1) > 0 && (i & 2) > 0;
    }

    public boolean isFileLink() {
        int i = this._linkOpts;
        return (i & 1) > 0 && (i & 2) == 0;
    }

    public boolean isDocumentLink() {
        return (this._linkOpts & 8) > 0;
    }

    public void newUrlLink() {
        this._range = new CellRangeAddress(0, 0, 0, 0);
        this._guid = ClassIDPredefined.STD_MONIKER.getClassID();
        this._linkOpts = 23;
        setLabel("");
        this._moniker = ClassIDPredefined.URL_MONIKER.getClassID();
        setAddress("");
        this._uninterpretedTail = URL_TAIL;
    }

    public void newFileLink() {
        this._range = new CellRangeAddress(0, 0, 0, 0);
        this._guid = ClassIDPredefined.STD_MONIKER.getClassID();
        this._linkOpts = 21;
        this._fileOpts = 0;
        setLabel("");
        this._moniker = ClassIDPredefined.FILE_MONIKER.getClassID();
        setAddress((String) null);
        setShortFilename("");
        this._uninterpretedTail = FILE_TAIL;
    }

    public void newDocumentLink() {
        this._range = new CellRangeAddress(0, 0, 0, 0);
        this._guid = ClassIDPredefined.STD_MONIKER.getClassID();
        this._linkOpts = 28;
        setLabel("");
        this._moniker = ClassIDPredefined.FILE_MONIKER.getClassID();
        setAddress("");
        setTextMark("");
    }

    public HyperlinkRecord copy() {
        return new HyperlinkRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.HYPERLINK;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("range", new HyperlinkRecord$$ExternalSyntheticLambda0(this), "guid", new HyperlinkRecord$$ExternalSyntheticLambda1(this), "linkOpts", new HyperlinkRecord$$ExternalSyntheticLambda2(this), Constants.ScionAnalytics.PARAM_LABEL, new HyperlinkRecord$$ExternalSyntheticLambda3(this), "targetFrame", new HyperlinkRecord$$ExternalSyntheticLambda4(this), "moniker", new HyperlinkRecord$$ExternalSyntheticLambda5(this), "textMark", new HyperlinkRecord$$ExternalSyntheticLambda6(this), "address", new HyperlinkRecord$$ExternalSyntheticLambda7(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-HyperlinkRecord  reason: not valid java name */
    public /* synthetic */ Object m2031lambda$getGenericProperties$0$orgapachepoihssfrecordHyperlinkRecord() {
        return this._range;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-HyperlinkRecord  reason: not valid java name */
    public /* synthetic */ Object m2032lambda$getGenericProperties$1$orgapachepoihssfrecordHyperlinkRecord() {
        return GenericRecordUtil.getBitsAsString((Supplier<Number>) new HyperlinkRecord$$ExternalSyntheticLambda8(this), new int[]{1, 2, 8, 20, 128, 256}, new String[]{"URL", "ABS", "PLACE", "LABEL", "TARGET_FRAME", "UNC_PATH"});
    }
}
