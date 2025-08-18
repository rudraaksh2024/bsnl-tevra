package org.apache.poi.ddf;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import kotlin.jvm.internal.ShortCompanionObject;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.GenericRecordXmlWriter;

public abstract class EscherProperty implements GenericRecord {
    private static final int[] FLAG_MASK = {16384, 32768};
    private static final String[] FLAG_NAMES = {"IS_BLIP", "IS_COMPLEX"};
    static final int IS_BLIP = 16384;
    static final int IS_COMPLEX = 32768;
    private final short id;

    public List<? extends GenericRecord> getGenericChildren() {
        return null;
    }

    public int getPropertySize() {
        return 6;
    }

    public abstract int serializeComplexPart(byte[] bArr, int i);

    public abstract int serializeSimplePart(byte[] bArr, int i);

    protected EscherProperty(short s) {
        this.id = s;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected EscherProperty(short s, boolean z, boolean z2) {
        this((short) (s | (z ? ShortCompanionObject.MIN_VALUE : 0) | (z2 ? (short) 16384 : 0)));
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected EscherProperty(EscherPropertyTypes escherPropertyTypes, boolean z, boolean z2) {
        this((short) (escherPropertyTypes.propNumber | (z ? ShortCompanionObject.MIN_VALUE : 0) | (z2 ? (short) 16384 : 0)));
    }

    public short getId() {
        return this.id;
    }

    public short getPropertyNumber() {
        return (short) (this.id & 16383);
    }

    public boolean isComplex() {
        return (this.id & ShortCompanionObject.MIN_VALUE) != 0;
    }

    public boolean isBlipId() {
        return (this.id & 16384) != 0;
    }

    public String getName() {
        return EscherPropertyTypes.forPropertyID(getPropertyNumber()).propName;
    }

    public final String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    public final String toXml(String str) {
        return GenericRecordXmlWriter.marshal(this);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("id", new EscherProperty$$ExternalSyntheticLambda0(this), "name", new EscherProperty$$ExternalSyntheticLambda1(this), "propertyNumber", new EscherProperty$$ExternalSyntheticLambda2(this), "propertySize", new EscherProperty$$ExternalSyntheticLambda3(this), "flags", GenericRecordUtil.getBitsAsString((Supplier<Number>) new EscherProperty$$ExternalSyntheticLambda0(this), FLAG_MASK, FLAG_NAMES));
    }

    public EscherPropertyTypes getGenericRecordType() {
        return EscherPropertyTypes.forPropertyID(this.id);
    }
}
