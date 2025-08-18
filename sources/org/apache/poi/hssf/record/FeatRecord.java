package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.hssf.record.common.FeatFormulaErr2;
import org.apache.poi.hssf.record.common.FeatProtection;
import org.apache.poi.hssf.record.common.FeatSmartTag;
import org.apache.poi.hssf.record.common.FtrHeader;
import org.apache.poi.hssf.record.common.SharedFeature;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class FeatRecord extends StandardRecord {
    private static final Logger LOG = LogManager.getLogger((Class<?>) FeatRecord.class);
    public static final short sid = 2152;
    public static final short v11_sid = 2162;
    public static final short v12_sid = 2168;
    private long cbFeatData;
    private CellRangeAddress[] cellRefs;
    private final FtrHeader futureHeader;
    private int isf_sharedFeatureType;
    private byte reserved1;
    private long reserved2;
    private int reserved3;
    private SharedFeature sharedFeature;

    public short getSid() {
        return sid;
    }

    public FeatRecord() {
        FtrHeader ftrHeader = new FtrHeader();
        this.futureHeader = ftrHeader;
        ftrHeader.setRecordType(sid);
    }

    public FeatRecord(FeatRecord featRecord) {
        super(featRecord);
        CellRangeAddress[] cellRangeAddressArr;
        this.futureHeader = featRecord.futureHeader.copy();
        this.isf_sharedFeatureType = featRecord.isf_sharedFeatureType;
        this.reserved1 = featRecord.reserved1;
        this.reserved2 = featRecord.reserved2;
        this.cbFeatData = featRecord.cbFeatData;
        this.reserved3 = featRecord.reserved3;
        CellRangeAddress[] cellRangeAddressArr2 = featRecord.cellRefs;
        SharedFeature sharedFeature2 = null;
        if (cellRangeAddressArr2 == null) {
            cellRangeAddressArr = null;
        } else {
            cellRangeAddressArr = (CellRangeAddress[]) Stream.of(cellRangeAddressArr2).map(new FeatRecord$$ExternalSyntheticLambda0()).toArray(new FeatRecord$$ExternalSyntheticLambda1());
        }
        this.cellRefs = cellRangeAddressArr;
        SharedFeature sharedFeature3 = featRecord.sharedFeature;
        this.sharedFeature = sharedFeature3 != null ? sharedFeature3.copy() : sharedFeature2;
    }

    static /* synthetic */ CellRangeAddress[] lambda$new$0(int i) {
        return new CellRangeAddress[i];
    }

    public FeatRecord(RecordInputStream recordInputStream) {
        this.futureHeader = new FtrHeader(recordInputStream);
        this.isf_sharedFeatureType = recordInputStream.readShort();
        this.reserved1 = recordInputStream.readByte();
        this.reserved2 = (long) recordInputStream.readInt();
        int readUShort = recordInputStream.readUShort();
        this.cbFeatData = (long) recordInputStream.readInt();
        this.reserved3 = recordInputStream.readShort();
        this.cellRefs = new CellRangeAddress[readUShort];
        int i = 0;
        while (true) {
            CellRangeAddress[] cellRangeAddressArr = this.cellRefs;
            if (i >= cellRangeAddressArr.length) {
                break;
            }
            cellRangeAddressArr[i] = new CellRangeAddress(recordInputStream);
            i++;
        }
        int i2 = this.isf_sharedFeatureType;
        if (i2 == 2) {
            this.sharedFeature = new FeatProtection(recordInputStream);
        } else if (i2 == 3) {
            this.sharedFeature = new FeatFormulaErr2(recordInputStream);
        } else if (i2 != 4) {
            LOG.atError().log("Unknown Shared Feature {} found!", (Object) Unbox.box(this.isf_sharedFeatureType));
        } else {
            this.sharedFeature = new FeatSmartTag(recordInputStream);
        }
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        this.futureHeader.serialize(littleEndianOutput);
        littleEndianOutput.writeShort(this.isf_sharedFeatureType);
        littleEndianOutput.writeByte(this.reserved1);
        littleEndianOutput.writeInt((int) this.reserved2);
        littleEndianOutput.writeShort(this.cellRefs.length);
        littleEndianOutput.writeInt((int) this.cbFeatData);
        littleEndianOutput.writeShort(this.reserved3);
        for (CellRangeAddress serialize : this.cellRefs) {
            serialize.serialize(littleEndianOutput);
        }
        this.sharedFeature.serialize(littleEndianOutput);
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return (this.cellRefs.length * 8) + 27 + this.sharedFeature.getDataSize();
    }

    public int getIsf_sharedFeatureType() {
        return this.isf_sharedFeatureType;
    }

    public long getCbFeatData() {
        return this.cbFeatData;
    }

    public void setCbFeatData(long j) {
        this.cbFeatData = j;
    }

    public CellRangeAddress[] getCellRefs() {
        return this.cellRefs;
    }

    public void setCellRefs(CellRangeAddress[] cellRangeAddressArr) {
        this.cellRefs = cellRangeAddressArr;
    }

    public SharedFeature getSharedFeature() {
        return this.sharedFeature;
    }

    public void setSharedFeature(SharedFeature sharedFeature2) {
        this.sharedFeature = sharedFeature2;
        if (sharedFeature2 instanceof FeatProtection) {
            this.isf_sharedFeatureType = 2;
        }
        if (sharedFeature2 instanceof FeatFormulaErr2) {
            this.isf_sharedFeatureType = 3;
        }
        if (sharedFeature2 instanceof FeatSmartTag) {
            this.isf_sharedFeatureType = 4;
        }
        if (this.isf_sharedFeatureType == 3) {
            this.cbFeatData = (long) sharedFeature2.getDataSize();
        } else {
            this.cbFeatData = 0;
        }
    }

    public FeatRecord copy() {
        return new FeatRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.FEAT;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("futureHeader", new FeatRecord$$ExternalSyntheticLambda2(this), "isf_sharedFeatureType", new FeatRecord$$ExternalSyntheticLambda3(this), "reserved1", new FeatRecord$$ExternalSyntheticLambda4(this), "reserved2", new FeatRecord$$ExternalSyntheticLambda5(this), "cbFeatData", new FeatRecord$$ExternalSyntheticLambda6(this), "reserved3", new FeatRecord$$ExternalSyntheticLambda7(this), "cellRefs", new FeatRecord$$ExternalSyntheticLambda8(this), "sharedFeature", new FeatRecord$$ExternalSyntheticLambda9(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-FeatRecord  reason: not valid java name */
    public /* synthetic */ Object m2019lambda$getGenericProperties$1$orgapachepoihssfrecordFeatRecord() {
        return this.futureHeader;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-FeatRecord  reason: not valid java name */
    public /* synthetic */ Object m2020lambda$getGenericProperties$2$orgapachepoihssfrecordFeatRecord() {
        return Byte.valueOf(this.reserved1);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-FeatRecord  reason: not valid java name */
    public /* synthetic */ Object m2021lambda$getGenericProperties$3$orgapachepoihssfrecordFeatRecord() {
        return Long.valueOf(this.reserved2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-FeatRecord  reason: not valid java name */
    public /* synthetic */ Object m2022lambda$getGenericProperties$4$orgapachepoihssfrecordFeatRecord() {
        return Integer.valueOf(this.reserved3);
    }
}
