package org.apache.poi.hssf.record.cf;

import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.ss.usermodel.IconMultiStateFormatting;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class IconMultiStateFormatting implements Duplicatable, GenericRecord {
    private static BitField ICON_ONLY = BitFieldFactory.getInstance(1);
    private static final Logger LOG = LogManager.getLogger((Class<?>) IconMultiStateFormatting.class);
    private static BitField REVERSED = BitFieldFactory.getInstance(4);
    private IconMultiStateFormatting.IconSet iconSet;
    private byte options;
    private Threshold[] thresholds;

    public IconMultiStateFormatting() {
        IconMultiStateFormatting.IconSet iconSet2 = IconMultiStateFormatting.IconSet.GYR_3_TRAFFIC_LIGHTS;
        this.iconSet = iconSet2;
        this.options = 0;
        this.thresholds = new Threshold[iconSet2.num];
    }

    public IconMultiStateFormatting(IconMultiStateFormatting iconMultiStateFormatting) {
        this.iconSet = iconMultiStateFormatting.iconSet;
        this.options = iconMultiStateFormatting.options;
        Threshold[] thresholdArr = iconMultiStateFormatting.thresholds;
        if (thresholdArr != null) {
            this.thresholds = (Threshold[]) Stream.of(thresholdArr).map(new IconMultiStateFormatting$$ExternalSyntheticLambda4()).toArray(new IconMultiStateFormatting$$ExternalSyntheticLambda5());
        }
    }

    static /* synthetic */ Threshold[] lambda$new$0(int i) {
        return new Threshold[i];
    }

    public IconMultiStateFormatting(LittleEndianInput littleEndianInput) {
        littleEndianInput.readShort();
        littleEndianInput.readByte();
        byte readByte = littleEndianInput.readByte();
        IconMultiStateFormatting.IconSet byId = IconMultiStateFormatting.IconSet.byId(littleEndianInput.readByte());
        this.iconSet = byId;
        if (byId.num != readByte) {
            LOG.atWarn().log("Inconsistent Icon Set definition, found {} but defined as {} entries", this.iconSet, Unbox.box((int) readByte));
        }
        this.options = littleEndianInput.readByte();
        this.thresholds = new Threshold[this.iconSet.num];
        int i = 0;
        while (true) {
            Threshold[] thresholdArr = this.thresholds;
            if (i < thresholdArr.length) {
                thresholdArr[i] = new IconMultiStateThreshold(littleEndianInput);
                i++;
            } else {
                return;
            }
        }
    }

    public IconMultiStateFormatting.IconSet getIconSet() {
        return this.iconSet;
    }

    public void setIconSet(IconMultiStateFormatting.IconSet iconSet2) {
        this.iconSet = iconSet2;
    }

    public Threshold[] getThresholds() {
        return this.thresholds;
    }

    public void setThresholds(Threshold[] thresholdArr) {
        this.thresholds = thresholdArr == null ? null : (Threshold[]) thresholdArr.clone();
    }

    public boolean isIconOnly() {
        return ICON_ONLY.isSet(this.options);
    }

    public void setIconOnly(boolean z) {
        this.options = ICON_ONLY.setByteBoolean(this.options, z);
    }

    public boolean isReversed() {
        return REVERSED.isSet(this.options);
    }

    public void setReversed(boolean z) {
        this.options = REVERSED.setByteBoolean(this.options, z);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("iconSet", new IconMultiStateFormatting$$ExternalSyntheticLambda0(this), "iconOnly", new IconMultiStateFormatting$$ExternalSyntheticLambda1(this), "reversed", new IconMultiStateFormatting$$ExternalSyntheticLambda2(this), "thresholds", new IconMultiStateFormatting$$ExternalSyntheticLambda3(this));
    }

    public String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    public IconMultiStateFormatting copy() {
        return new IconMultiStateFormatting(this);
    }

    public int getDataLength() {
        int i = 6;
        for (Threshold dataLength : this.thresholds) {
            i += dataLength.getDataLength();
        }
        return i;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(0);
        littleEndianOutput.writeByte(0);
        littleEndianOutput.writeByte(this.iconSet.num);
        littleEndianOutput.writeByte(this.iconSet.id);
        littleEndianOutput.writeByte(this.options);
        for (Threshold serialize : this.thresholds) {
            serialize.serialize(littleEndianOutput);
        }
    }
}
