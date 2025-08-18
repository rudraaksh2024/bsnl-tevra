package org.apache.poi.hssf.record;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.util.StringUtil;

public class LbsDataSubRecord extends SubRecord {
    public static final int sid = 19;
    private boolean[] _bsels;
    private int _cLines;
    private int _cbFContinued;
    private LbsDropData _dropData;
    private int _flags;
    private int _iSel;
    private int _idEdit;
    private Ptg _linkPtg;
    private String[] _rgLines;
    private Byte _unknownPostFormulaByte;
    private int _unknownPreFormulaInt;

    public boolean isTerminating() {
        return true;
    }

    LbsDataSubRecord() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: boolean[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LbsDataSubRecord(org.apache.poi.hssf.record.LbsDataSubRecord r3) {
        /*
            r2 = this;
            r2.<init>(r3)
            int r0 = r3._cbFContinued
            r2._cbFContinued = r0
            int r0 = r3._unknownPreFormulaInt
            r2._unknownPreFormulaInt = r0
            org.apache.poi.ss.formula.ptg.Ptg r0 = r3._linkPtg
            r1 = 0
            if (r0 != 0) goto L_0x0012
            r0 = r1
            goto L_0x0016
        L_0x0012:
            org.apache.poi.ss.formula.ptg.Ptg r0 = r0.copy()
        L_0x0016:
            r2._linkPtg = r0
            java.lang.Byte r0 = r3._unknownPostFormulaByte
            r2._unknownPostFormulaByte = r0
            int r0 = r3._cLines
            r2._cLines = r0
            int r0 = r3._iSel
            r2._iSel = r0
            int r0 = r3._flags
            r2._flags = r0
            int r0 = r3._idEdit
            r2._idEdit = r0
            org.apache.poi.hssf.record.LbsDataSubRecord$LbsDropData r0 = r3._dropData
            if (r0 != 0) goto L_0x0032
            r0 = r1
            goto L_0x0036
        L_0x0032:
            org.apache.poi.hssf.record.LbsDataSubRecord$LbsDropData r0 = r0.copy()
        L_0x0036:
            r2._dropData = r0
            java.lang.String[] r0 = r3._rgLines
            if (r0 != 0) goto L_0x003e
            r0 = r1
            goto L_0x0044
        L_0x003e:
            java.lang.Object r0 = r0.clone()
            java.lang.String[] r0 = (java.lang.String[]) r0
        L_0x0044:
            r2._rgLines = r0
            boolean[] r3 = r3._bsels
            if (r3 != 0) goto L_0x004b
            goto L_0x0052
        L_0x004b:
            java.lang.Object r3 = r3.clone()
            r1 = r3
            boolean[] r1 = (boolean[]) r1
        L_0x0052:
            r2._bsels = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.record.LbsDataSubRecord.<init>(org.apache.poi.hssf.record.LbsDataSubRecord):void");
    }

    public LbsDataSubRecord(LittleEndianInput littleEndianInput, int i, int i2) {
        this._cbFContinued = i;
        int readUShort = littleEndianInput.readUShort();
        if (readUShort > 0) {
            int readUShort2 = littleEndianInput.readUShort();
            this._unknownPreFormulaInt = littleEndianInput.readInt();
            Ptg[] readTokens = Ptg.readTokens(readUShort2, littleEndianInput);
            if (readTokens.length == 1) {
                this._linkPtg = readTokens[0];
                int i3 = (readUShort - readUShort2) - 6;
                if (i3 == 0) {
                    this._unknownPostFormulaByte = null;
                } else if (i3 == 1) {
                    this._unknownPostFormulaByte = Byte.valueOf(littleEndianInput.readByte());
                } else {
                    throw new RecordFormatException("Unexpected leftover bytes");
                }
            } else {
                throw new RecordFormatException("Read " + readTokens.length + " tokens but expected exactly 1");
            }
        }
        this._cLines = littleEndianInput.readUShort();
        this._iSel = littleEndianInput.readUShort();
        this._flags = littleEndianInput.readUShort();
        this._idEdit = littleEndianInput.readUShort();
        if (i2 == 20) {
            this._dropData = new LbsDropData(littleEndianInput);
        }
        if ((this._flags & 2) != 0) {
            this._rgLines = new String[this._cLines];
            for (int i4 = 0; i4 < this._cLines; i4++) {
                this._rgLines[i4] = StringUtil.readUnicodeString(littleEndianInput);
            }
        }
        int i5 = this._flags;
        if (((i5 >> 4) & 1) + ((i5 >> 5) & 1) != 0) {
            this._bsels = new boolean[this._cLines];
            for (int i6 = 0; i6 < this._cLines; i6++) {
                this._bsels[i6] = littleEndianInput.readByte() == 1;
            }
        }
    }

    public static LbsDataSubRecord newAutoFilterInstance() {
        LbsDataSubRecord lbsDataSubRecord = new LbsDataSubRecord();
        lbsDataSubRecord._cbFContinued = 8174;
        lbsDataSubRecord._iSel = 0;
        lbsDataSubRecord._flags = 769;
        LbsDropData lbsDropData = new LbsDropData();
        lbsDataSubRecord._dropData = lbsDropData;
        int unused = lbsDropData._wStyle = 2;
        int unused2 = lbsDataSubRecord._dropData._cLine = 8;
        return lbsDataSubRecord;
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        int i;
        Ptg ptg = this._linkPtg;
        if (ptg != null) {
            i = ptg.getSize() + 8;
            if (this._unknownPostFormulaByte != null) {
                i++;
            }
        } else {
            i = 2;
        }
        int i2 = i + 8;
        LbsDropData lbsDropData = this._dropData;
        if (lbsDropData != null) {
            i2 += lbsDropData.getDataSize();
        }
        String[] strArr = this._rgLines;
        if (strArr != null) {
            for (String encodedSize : strArr) {
                i2 += StringUtil.getEncodedSize(encodedSize);
            }
        }
        boolean[] zArr = this._bsels;
        return zArr != null ? i2 + zArr.length : i2;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(19);
        littleEndianOutput.writeShort(this._cbFContinued);
        Ptg ptg = this._linkPtg;
        if (ptg == null) {
            littleEndianOutput.writeShort(0);
        } else {
            int size = ptg.getSize();
            int i = size + 6;
            if (this._unknownPostFormulaByte != null) {
                i++;
            }
            littleEndianOutput.writeShort(i);
            littleEndianOutput.writeShort(size);
            littleEndianOutput.writeInt(this._unknownPreFormulaInt);
            this._linkPtg.write(littleEndianOutput);
            Byte b = this._unknownPostFormulaByte;
            if (b != null) {
                littleEndianOutput.writeByte(b.intValue());
            }
        }
        littleEndianOutput.writeShort(this._cLines);
        littleEndianOutput.writeShort(this._iSel);
        littleEndianOutput.writeShort(this._flags);
        littleEndianOutput.writeShort(this._idEdit);
        LbsDropData lbsDropData = this._dropData;
        if (lbsDropData != null) {
            lbsDropData.serialize(littleEndianOutput);
        }
        String[] strArr = this._rgLines;
        if (strArr != null) {
            for (String writeUnicodeString : strArr) {
                StringUtil.writeUnicodeString(littleEndianOutput, writeUnicodeString);
            }
        }
        boolean[] zArr = this._bsels;
        if (zArr != null) {
            for (boolean z : zArr) {
                littleEndianOutput.writeByte(z ? 1 : 0);
            }
        }
    }

    public LbsDataSubRecord copy() {
        return new LbsDataSubRecord(this);
    }

    public Ptg getFormula() {
        return this._linkPtg;
    }

    public int getNumberOfItems() {
        return this._cLines;
    }

    public SubRecord.SubRecordTypes getGenericRecordType() {
        return SubRecord.SubRecordTypes.LBS_DATA;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("unknownShort1", new LbsDataSubRecord$$ExternalSyntheticLambda0(this));
        linkedHashMap.put("unknownPreFormulaInt", new LbsDataSubRecord$$ExternalSyntheticLambda3(this));
        linkedHashMap.put("formula", new LbsDataSubRecord$$ExternalSyntheticLambda4(this));
        linkedHashMap.put("unknownPostFormulaByte", new LbsDataSubRecord$$ExternalSyntheticLambda5(this));
        linkedHashMap.put("numberOfItems", new LbsDataSubRecord$$ExternalSyntheticLambda6(this));
        linkedHashMap.put("selEntryIx", new LbsDataSubRecord$$ExternalSyntheticLambda7(this));
        linkedHashMap.put("style", new LbsDataSubRecord$$ExternalSyntheticLambda8(this));
        linkedHashMap.put("unknownShort10", new LbsDataSubRecord$$ExternalSyntheticLambda9(this));
        linkedHashMap.put("dropData", new LbsDataSubRecord$$ExternalSyntheticLambda10(this));
        linkedHashMap.put("rgLines", new LbsDataSubRecord$$ExternalSyntheticLambda1(this));
        linkedHashMap.put("bsels", new LbsDataSubRecord$$ExternalSyntheticLambda2(this));
        return Collections.unmodifiableMap(linkedHashMap);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-LbsDataSubRecord  reason: not valid java name */
    public /* synthetic */ Object m2036lambda$getGenericProperties$0$orgapachepoihssfrecordLbsDataSubRecord() {
        return Integer.valueOf(this._cbFContinued);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-LbsDataSubRecord  reason: not valid java name */
    public /* synthetic */ Object m2037lambda$getGenericProperties$1$orgapachepoihssfrecordLbsDataSubRecord() {
        return Integer.valueOf(this._unknownPreFormulaInt);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-LbsDataSubRecord  reason: not valid java name */
    public /* synthetic */ Object m2038lambda$getGenericProperties$2$orgapachepoihssfrecordLbsDataSubRecord() {
        return this._unknownPostFormulaByte;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-LbsDataSubRecord  reason: not valid java name */
    public /* synthetic */ Object m2039lambda$getGenericProperties$3$orgapachepoihssfrecordLbsDataSubRecord() {
        return Integer.valueOf(this._iSel);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-LbsDataSubRecord  reason: not valid java name */
    public /* synthetic */ Object m2040lambda$getGenericProperties$4$orgapachepoihssfrecordLbsDataSubRecord() {
        return Integer.valueOf(this._flags);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$5$org-apache-poi-hssf-record-LbsDataSubRecord  reason: not valid java name */
    public /* synthetic */ Object m2041lambda$getGenericProperties$5$orgapachepoihssfrecordLbsDataSubRecord() {
        return Integer.valueOf(this._idEdit);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$6$org-apache-poi-hssf-record-LbsDataSubRecord  reason: not valid java name */
    public /* synthetic */ Object m2042lambda$getGenericProperties$6$orgapachepoihssfrecordLbsDataSubRecord() {
        return this._dropData;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$7$org-apache-poi-hssf-record-LbsDataSubRecord  reason: not valid java name */
    public /* synthetic */ Object m2043lambda$getGenericProperties$7$orgapachepoihssfrecordLbsDataSubRecord() {
        return this._rgLines;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$8$org-apache-poi-hssf-record-LbsDataSubRecord  reason: not valid java name */
    public /* synthetic */ Object m2044lambda$getGenericProperties$8$orgapachepoihssfrecordLbsDataSubRecord() {
        return this._bsels;
    }

    public static class LbsDropData implements Duplicatable, GenericRecord {
        public static final int STYLE_COMBO_DROPDOWN = 0;
        public static final int STYLE_COMBO_EDIT_DROPDOWN = 1;
        public static final int STYLE_COMBO_SIMPLE_DROPDOWN = 2;
        /* access modifiers changed from: private */
        public int _cLine;
        private int _dxMin;
        private final String _str;
        private Byte _unused;
        /* access modifiers changed from: private */
        public int _wStyle;

        public LbsDropData() {
            this._str = "";
            this._unused = (byte) 0;
        }

        public LbsDropData(LbsDropData lbsDropData) {
            this._wStyle = lbsDropData._wStyle;
            this._cLine = lbsDropData._cLine;
            this._dxMin = lbsDropData._dxMin;
            this._str = lbsDropData._str;
            this._unused = lbsDropData._unused;
        }

        public LbsDropData(LittleEndianInput littleEndianInput) {
            this._wStyle = littleEndianInput.readUShort();
            this._cLine = littleEndianInput.readUShort();
            this._dxMin = littleEndianInput.readUShort();
            String readUnicodeString = StringUtil.readUnicodeString(littleEndianInput);
            this._str = readUnicodeString;
            if (StringUtil.getEncodedSize(readUnicodeString) % 2 != 0) {
                this._unused = Byte.valueOf(littleEndianInput.readByte());
            }
        }

        public void setStyle(int i) {
            this._wStyle = i;
        }

        public void setNumLines(int i) {
            this._cLine = i;
        }

        public void serialize(LittleEndianOutput littleEndianOutput) {
            littleEndianOutput.writeShort(this._wStyle);
            littleEndianOutput.writeShort(this._cLine);
            littleEndianOutput.writeShort(this._dxMin);
            StringUtil.writeUnicodeString(littleEndianOutput, this._str);
            Byte b = this._unused;
            if (b != null) {
                littleEndianOutput.writeByte(b.byteValue());
            }
        }

        public int getDataSize() {
            int encodedSize = StringUtil.getEncodedSize(this._str) + 6;
            return this._unused != null ? encodedSize + 1 : encodedSize;
        }

        public String toString() {
            return GenericRecordJsonWriter.marshal(this);
        }

        public LbsDropData copy() {
            return new LbsDropData(this);
        }

        public Map<String, Supplier<?>> getGenericProperties() {
            return GenericRecordUtil.getGenericProperties("wStyle", new LbsDataSubRecord$LbsDropData$$ExternalSyntheticLambda0(this), "cLine", new LbsDataSubRecord$LbsDropData$$ExternalSyntheticLambda1(this), "dxMin", new LbsDataSubRecord$LbsDropData$$ExternalSyntheticLambda2(this), "str", new LbsDataSubRecord$LbsDropData$$ExternalSyntheticLambda3(this), "unused", new LbsDataSubRecord$LbsDropData$$ExternalSyntheticLambda4(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-LbsDataSubRecord$LbsDropData  reason: not valid java name */
        public /* synthetic */ Object m2045lambda$getGenericProperties$0$orgapachepoihssfrecordLbsDataSubRecord$LbsDropData() {
            return Integer.valueOf(this._wStyle);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-LbsDataSubRecord$LbsDropData  reason: not valid java name */
        public /* synthetic */ Object m2046lambda$getGenericProperties$1$orgapachepoihssfrecordLbsDataSubRecord$LbsDropData() {
            return Integer.valueOf(this._cLine);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-LbsDataSubRecord$LbsDropData  reason: not valid java name */
        public /* synthetic */ Object m2047lambda$getGenericProperties$2$orgapachepoihssfrecordLbsDataSubRecord$LbsDropData() {
            return Integer.valueOf(this._dxMin);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-LbsDataSubRecord$LbsDropData  reason: not valid java name */
        public /* synthetic */ Object m2048lambda$getGenericProperties$3$orgapachepoihssfrecordLbsDataSubRecord$LbsDropData() {
            return this._str;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-LbsDataSubRecord$LbsDropData  reason: not valid java name */
        public /* synthetic */ Object m2049lambda$getGenericProperties$4$orgapachepoihssfrecordLbsDataSubRecord$LbsDropData() {
            return this._unused;
        }
    }
}
