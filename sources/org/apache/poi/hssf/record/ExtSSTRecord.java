package org.apache.poi.hssf.record;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.record.cont.ContinuableRecord;
import org.apache.poi.hssf.record.cont.ContinuableRecordOutput;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class ExtSSTRecord extends ContinuableRecord {
    public static final int DEFAULT_BUCKET_SIZE = 8;
    public static final int MAX_BUCKETS = 128;
    public static final short sid = 255;
    private InfoSubRecord[] _sstInfos;
    private short _stringsPerBucket;

    public short getSid() {
        return 255;
    }

    public static final class InfoSubRecord implements GenericRecord {
        public static final int ENCODED_SIZE = 8;
        private int field_1_stream_pos;
        private int field_2_bucket_sst_offset;
        private short field_3_zero;

        public InfoSubRecord(int i, int i2) {
            this.field_1_stream_pos = i;
            this.field_2_bucket_sst_offset = i2;
        }

        public InfoSubRecord(InfoSubRecord infoSubRecord) {
            this.field_1_stream_pos = infoSubRecord.field_1_stream_pos;
            this.field_2_bucket_sst_offset = infoSubRecord.field_2_bucket_sst_offset;
            this.field_3_zero = infoSubRecord.field_3_zero;
        }

        public InfoSubRecord(RecordInputStream recordInputStream) {
            this.field_1_stream_pos = recordInputStream.readInt();
            this.field_2_bucket_sst_offset = recordInputStream.readShort();
            this.field_3_zero = recordInputStream.readShort();
        }

        public int getStreamPos() {
            return this.field_1_stream_pos;
        }

        public int getBucketSSTOffset() {
            return this.field_2_bucket_sst_offset;
        }

        public void serialize(LittleEndianOutput littleEndianOutput) {
            littleEndianOutput.writeInt(this.field_1_stream_pos);
            littleEndianOutput.writeShort(this.field_2_bucket_sst_offset);
            littleEndianOutput.writeShort(this.field_3_zero);
        }

        public Map<String, Supplier<?>> getGenericProperties() {
            return GenericRecordUtil.getGenericProperties("streamPos", new ExtSSTRecord$InfoSubRecord$$ExternalSyntheticLambda0(this), "bucketSSTOffset", new ExtSSTRecord$InfoSubRecord$$ExternalSyntheticLambda1(this));
        }
    }

    public ExtSSTRecord() {
        this._stringsPerBucket = 8;
        this._sstInfos = new InfoSubRecord[0];
    }

    public ExtSSTRecord(ExtSSTRecord extSSTRecord) {
        InfoSubRecord[] infoSubRecordArr;
        this._stringsPerBucket = extSSTRecord._stringsPerBucket;
        InfoSubRecord[] infoSubRecordArr2 = extSSTRecord._sstInfos;
        if (infoSubRecordArr2 == null) {
            infoSubRecordArr = null;
        } else {
            infoSubRecordArr = (InfoSubRecord[]) Stream.of(infoSubRecordArr2).map(new ExtSSTRecord$$ExternalSyntheticLambda0()).toArray(new ExtSSTRecord$$ExternalSyntheticLambda1());
        }
        this._sstInfos = infoSubRecordArr;
    }

    static /* synthetic */ InfoSubRecord[] lambda$new$0(int i) {
        return new InfoSubRecord[i];
    }

    public ExtSSTRecord(RecordInputStream recordInputStream) {
        this._stringsPerBucket = recordInputStream.readShort();
        ArrayList arrayList = new ArrayList(recordInputStream.remaining() / 8);
        while (recordInputStream.available() > 0) {
            arrayList.add(new InfoSubRecord(recordInputStream));
            if (recordInputStream.available() == 0 && recordInputStream.hasNextRecord() && recordInputStream.getNextSid() == 60) {
                recordInputStream.nextRecord();
            }
        }
        this._sstInfos = (InfoSubRecord[]) arrayList.toArray(new InfoSubRecord[0]);
    }

    public void setNumStringsPerBucket(short s) {
        this._stringsPerBucket = s;
    }

    public void serialize(ContinuableRecordOutput continuableRecordOutput) {
        continuableRecordOutput.writeShort(this._stringsPerBucket);
        for (InfoSubRecord serialize : this._sstInfos) {
            serialize.serialize(continuableRecordOutput);
        }
    }

    /* access modifiers changed from: package-private */
    public int getDataSize() {
        return (this._sstInfos.length * 8) + 2;
    }

    /* access modifiers changed from: package-private */
    public InfoSubRecord[] getInfoSubRecords() {
        return this._sstInfos;
    }

    public static int getNumberOfInfoRecsForStrings(int i) {
        int i2 = i / 8;
        if (i % 8 != 0) {
            i2++;
        }
        if (i2 > 128) {
            return 128;
        }
        return i2;
    }

    public static int getRecordSizeForStrings(int i) {
        return (getNumberOfInfoRecsForStrings(i) * 8) + 6;
    }

    public void setBucketOffsets(int[] iArr, int[] iArr2) {
        this._sstInfos = new InfoSubRecord[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            this._sstInfos[i] = new InfoSubRecord(iArr[i], iArr2[i]);
        }
    }

    public ExtSSTRecord copy() {
        return new ExtSSTRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.EXT_SST;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("dataSize", new ExtSSTRecord$$ExternalSyntheticLambda2(this), "infoSubRecords", new ExtSSTRecord$$ExternalSyntheticLambda3(this));
    }
}
