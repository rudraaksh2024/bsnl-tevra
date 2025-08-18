package org.apache.poi.hssf.record;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class PaletteRecord extends StandardRecord {
    private static final int[] DEFAULT_COLORS = {0, ViewCompat.MEASURED_SIZE_MASK, 16711680, MotionEventCompat.ACTION_POINTER_INDEX_MASK, 255, 16776960, 16711935, 65535, 8388608, 32768, 128, 8421376, 8388736, 32896, 12632256, 8421504, 10066431, 10040166, 16777164, 13434879, 6684774, 16744576, 26316, 13421823, 128, 16711935, 16776960, 65535, 8388736, 8388608, 32896, 255, 52479, 13434879, 13434828, 16777113, 10079487, 16751052, 13408767, 16764057, 3368703, 3394764, 10079232, 16763904, 16750848, 16737792, 6710937, 9868950, 13158, 3381606, 13056, 3355392, 10040064, 10040166, 3355545, 3355443};
    public static final short FIRST_COLOR_INDEX = 8;
    public static final byte STANDARD_PALETTE_SIZE = 56;
    public static final short sid = 146;
    private final ArrayList<PColor> _colors;

    public short getSid() {
        return 146;
    }

    public PaletteRecord() {
        ArrayList<PColor> arrayList = new ArrayList<>(100);
        this._colors = arrayList;
        Arrays.stream(DEFAULT_COLORS).mapToObj(new PaletteRecord$$ExternalSyntheticLambda0()).forEach(new PaletteRecord$$ExternalSyntheticLambda1(arrayList));
    }

    public PaletteRecord(PaletteRecord paletteRecord) {
        super(paletteRecord);
        ArrayList<PColor> arrayList = new ArrayList<>(100);
        this._colors = arrayList;
        arrayList.ensureCapacity(paletteRecord._colors.size());
        paletteRecord._colors.stream().map(new PaletteRecord$$ExternalSyntheticLambda2()).forEach(new PaletteRecord$$ExternalSyntheticLambda1(arrayList));
    }

    public PaletteRecord(RecordInputStream recordInputStream) {
        ArrayList<PColor> arrayList = new ArrayList<>(100);
        this._colors = arrayList;
        short readShort = recordInputStream.readShort();
        arrayList.ensureCapacity(readShort);
        for (int i = 0; i < readShort; i++) {
            this._colors.add(new PColor(recordInputStream));
        }
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this._colors.size());
        Iterator<PColor> it = this._colors.iterator();
        while (it.hasNext()) {
            it.next().serialize(littleEndianOutput);
        }
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return (this._colors.size() * 4) + 2;
    }

    public byte[] getColor(int i) {
        int i2 = i - 8;
        if (i2 < 0 || i2 >= this._colors.size()) {
            return null;
        }
        return this._colors.get(i2).getTriplet();
    }

    public void setColor(short s, byte b, byte b2, byte b3) {
        int i = s - 8;
        if (i >= 0 && i < 56) {
            while (this._colors.size() <= i) {
                this._colors.add(new PColor(0, 0, 0));
            }
            this._colors.set(i, new PColor(b, b2, b3));
        }
    }

    public PaletteRecord copy() {
        return new PaletteRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.PALETTE;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("colors", new PaletteRecord$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-PaletteRecord  reason: not valid java name */
    public /* synthetic */ Object m2073lambda$getGenericProperties$0$orgapachepoihssfrecordPaletteRecord() {
        return this._colors;
    }

    private static final class PColor implements GenericRecord {
        public static final short ENCODED_SIZE = 4;
        private final int _blue;
        private final int _green;
        private final int _red;

        PColor(int i) {
            this._red = (i >>> 16) & 255;
            this._green = (i >>> 8) & 255;
            this._blue = i & 255;
        }

        PColor(int i, int i2, int i3) {
            this._red = i;
            this._green = i2;
            this._blue = i3;
        }

        PColor(PColor pColor) {
            this._red = pColor._red;
            this._green = pColor._green;
            this._blue = pColor._blue;
        }

        PColor(RecordInputStream recordInputStream) {
            this._red = recordInputStream.readByte();
            this._green = recordInputStream.readByte();
            this._blue = recordInputStream.readByte();
            recordInputStream.readByte();
        }

        /* access modifiers changed from: package-private */
        public byte[] getTriplet() {
            return new byte[]{(byte) this._red, (byte) this._green, (byte) this._blue};
        }

        /* access modifiers changed from: package-private */
        public void serialize(LittleEndianOutput littleEndianOutput) {
            littleEndianOutput.writeByte(this._red);
            littleEndianOutput.writeByte(this._green);
            littleEndianOutput.writeByte(this._blue);
            littleEndianOutput.writeByte(0);
        }

        public Map<String, Supplier<?>> getGenericProperties() {
            return GenericRecordUtil.getGenericProperties("red", new PaletteRecord$PColor$$ExternalSyntheticLambda0(this), "green", new PaletteRecord$PColor$$ExternalSyntheticLambda1(this), "blue", new PaletteRecord$PColor$$ExternalSyntheticLambda2(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-PaletteRecord$PColor  reason: not valid java name */
        public /* synthetic */ Object m2074lambda$getGenericProperties$0$orgapachepoihssfrecordPaletteRecord$PColor() {
            return Integer.valueOf(this._red & 255);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-PaletteRecord$PColor  reason: not valid java name */
        public /* synthetic */ Object m2075lambda$getGenericProperties$1$orgapachepoihssfrecordPaletteRecord$PColor() {
            return Integer.valueOf(this._green & 255);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-PaletteRecord$PColor  reason: not valid java name */
        public /* synthetic */ Object m2076lambda$getGenericProperties$2$orgapachepoihssfrecordPaletteRecord$PColor() {
            return Integer.valueOf(this._blue & 255);
        }
    }
}
