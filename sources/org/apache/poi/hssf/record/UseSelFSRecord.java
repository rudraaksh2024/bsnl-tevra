package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class UseSelFSRecord extends StandardRecord {
    public static final short sid = 352;
    private static final BitField useNaturalLanguageFormulasFlag = BitFieldFactory.getInstance(1);
    private int _options;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return sid;
    }

    private UseSelFSRecord(UseSelFSRecord useSelFSRecord) {
        super(useSelFSRecord);
        this._options = useSelFSRecord._options;
    }

    private UseSelFSRecord(int i) {
        this._options = i;
    }

    public UseSelFSRecord(RecordInputStream recordInputStream) {
        this(recordInputStream.readUShort());
    }

    public UseSelFSRecord(boolean z) {
        this(0);
        this._options = useNaturalLanguageFormulasFlag.setBoolean(this._options, z);
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this._options);
    }

    public UseSelFSRecord copy() {
        return new UseSelFSRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.USE_SEL_FS;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("options", GenericRecordUtil.getBitsAsString((Supplier<Number>) new UseSelFSRecord$$ExternalSyntheticLambda0(this), new BitField[]{useNaturalLanguageFormulasFlag}, new String[]{"USE_NATURAL_LANGUAGE_FORMULAS_FLAG"}));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-UseSelFSRecord  reason: not valid java name */
    public /* synthetic */ Number m2112lambda$getGenericProperties$0$orgapachepoihssfrecordUseSelFSRecord() {
        return Integer.valueOf(this._options);
    }
}
