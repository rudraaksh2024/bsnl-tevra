package org.apache.poi.hssf.record.common;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

@Internal
public class FormatRun implements Comparable<FormatRun>, GenericRecord {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    final short _character;
    short _fontIndex;

    public int hashCode() {
        return 42;
    }

    public FormatRun(short s, short s2) {
        this._character = s;
        this._fontIndex = s2;
    }

    public FormatRun(FormatRun formatRun) {
        this._character = formatRun._character;
        this._fontIndex = formatRun._fontIndex;
    }

    public FormatRun(LittleEndianInput littleEndianInput) {
        this(littleEndianInput.readShort(), littleEndianInput.readShort());
    }

    public short getCharacterPos() {
        return this._character;
    }

    public short getFontIndex() {
        return this._fontIndex;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FormatRun)) {
            return false;
        }
        FormatRun formatRun = (FormatRun) obj;
        if (this._character == formatRun._character && this._fontIndex == formatRun._fontIndex) {
            return true;
        }
        return false;
    }

    public int compareTo(FormatRun formatRun) {
        short s = this._character;
        short s2 = formatRun._character;
        if (s == s2 && this._fontIndex == formatRun._fontIndex) {
            return 0;
        }
        return s == s2 ? this._fontIndex - formatRun._fontIndex : s - s2;
    }

    public String toString() {
        return "character=" + this._character + ",fontIndex=" + this._fontIndex;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this._character);
        littleEndianOutput.writeShort(this._fontIndex);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("characterPos", new FormatRun$$ExternalSyntheticLambda0(this), "fontIndex", new FormatRun$$ExternalSyntheticLambda1(this));
    }
}
