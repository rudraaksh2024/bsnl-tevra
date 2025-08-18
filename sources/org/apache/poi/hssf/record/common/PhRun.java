package org.apache.poi.hssf.record.common;

import java.util.Objects;
import org.apache.poi.hssf.record.cont.ContinuableRecordOutput;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianInput;

@Internal
public class PhRun {
    final int phoneticTextFirstCharacterOffset;
    final int realTextFirstCharacterOffset;
    final int realTextLength;

    public PhRun(PhRun phRun) {
        this.phoneticTextFirstCharacterOffset = phRun.phoneticTextFirstCharacterOffset;
        this.realTextFirstCharacterOffset = phRun.realTextFirstCharacterOffset;
        this.realTextLength = phRun.realTextLength;
    }

    public PhRun(int i, int i2, int i3) {
        this.phoneticTextFirstCharacterOffset = i;
        this.realTextFirstCharacterOffset = i2;
        this.realTextLength = i3;
    }

    PhRun(LittleEndianInput littleEndianInput) {
        this.phoneticTextFirstCharacterOffset = littleEndianInput.readUShort();
        this.realTextFirstCharacterOffset = littleEndianInput.readUShort();
        this.realTextLength = littleEndianInput.readUShort();
    }

    /* access modifiers changed from: package-private */
    public void serialize(ContinuableRecordOutput continuableRecordOutput) {
        continuableRecordOutput.writeContinueIfRequired(6);
        continuableRecordOutput.writeShort(this.phoneticTextFirstCharacterOffset);
        continuableRecordOutput.writeShort(this.realTextFirstCharacterOffset);
        continuableRecordOutput.writeShort(this.realTextLength);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.phoneticTextFirstCharacterOffset), Integer.valueOf(this.realTextFirstCharacterOffset), Integer.valueOf(this.realTextLength)});
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PhRun phRun = (PhRun) obj;
        if (this.phoneticTextFirstCharacterOffset == phRun.phoneticTextFirstCharacterOffset && this.realTextFirstCharacterOffset == phRun.realTextFirstCharacterOffset && this.realTextLength == phRun.realTextLength) {
            return true;
        }
        return false;
    }
}
