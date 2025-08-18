package org.apache.poi.hssf.record.chart;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public class ChartTitleFormatRecord extends StandardRecord {
    public static final short sid = 4176;
    private final CTFormat[] _formats;

    public short getSid() {
        return sid;
    }

    private static final class CTFormat implements GenericRecord {
        public static final int ENCODED_SIZE = 4;
        private int _fontIndex;
        private int _offset;

        public CTFormat(CTFormat cTFormat) {
            this._offset = cTFormat._offset;
            this._fontIndex = cTFormat._fontIndex;
        }

        public CTFormat(RecordInputStream recordInputStream) {
            this._offset = recordInputStream.readShort();
            this._fontIndex = recordInputStream.readShort();
        }

        public int getOffset() {
            return this._offset;
        }

        public void setOffset(int i) {
            this._offset = i;
        }

        public int getFontIndex() {
            return this._fontIndex;
        }

        public void serialize(LittleEndianOutput littleEndianOutput) {
            littleEndianOutput.writeShort(this._offset);
            littleEndianOutput.writeShort(this._fontIndex);
        }

        public Map<String, Supplier<?>> getGenericProperties() {
            return GenericRecordUtil.getGenericProperties(TypedValues.CycleType.S_WAVE_OFFSET, new ChartTitleFormatRecord$CTFormat$$ExternalSyntheticLambda0(this), "fontIndex", new ChartTitleFormatRecord$CTFormat$$ExternalSyntheticLambda1(this));
        }
    }

    public ChartTitleFormatRecord(ChartTitleFormatRecord chartTitleFormatRecord) {
        super(chartTitleFormatRecord);
        this._formats = (CTFormat[]) Stream.of(chartTitleFormatRecord._formats).map(new ChartTitleFormatRecord$$ExternalSyntheticLambda1()).toArray(new ChartTitleFormatRecord$$ExternalSyntheticLambda2());
    }

    static /* synthetic */ CTFormat[] lambda$new$0(int i) {
        return new CTFormat[i];
    }

    public ChartTitleFormatRecord(RecordInputStream recordInputStream) {
        int readUShort = recordInputStream.readUShort();
        this._formats = new CTFormat[readUShort];
        for (int i = 0; i < readUShort; i++) {
            this._formats[i] = new CTFormat(recordInputStream);
        }
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this._formats.length);
        for (CTFormat serialize : this._formats) {
            serialize.serialize(littleEndianOutput);
        }
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return (this._formats.length * 4) + 2;
    }

    public int getFormatCount() {
        return this._formats.length;
    }

    public void modifyFormatRun(short s, short s2) {
        int i = 0;
        int i2 = 0;
        while (true) {
            CTFormat[] cTFormatArr = this._formats;
            if (i < cTFormatArr.length) {
                CTFormat cTFormat = cTFormatArr[i];
                if (i2 != 0) {
                    cTFormat.setOffset(cTFormat.getOffset() + i2);
                } else if (s == cTFormat.getOffset()) {
                    CTFormat[] cTFormatArr2 = this._formats;
                    if (i < cTFormatArr2.length - 1) {
                        i2 = s2 - (cTFormatArr2[i + 1].getOffset() - cTFormat.getOffset());
                    }
                }
                i++;
            } else {
                return;
            }
        }
    }

    public ChartTitleFormatRecord copy() {
        return new ChartTitleFormatRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CHART_TITLE_FORMAT;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("formats", new ChartTitleFormatRecord$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-chart-ChartTitleFormatRecord  reason: not valid java name */
    public /* synthetic */ Object m2155lambda$getGenericProperties$1$orgapachepoihssfrecordchartChartTitleFormatRecord() {
        return this._formats;
    }
}
