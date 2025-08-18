package org.apache.poi.ddf;

import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndian;

public class EscherSimpleProperty extends EscherProperty {
    private final int propertyValue;

    public int serializeComplexPart(byte[] bArr, int i) {
        return 0;
    }

    public EscherSimpleProperty(short s, int i) {
        super(s);
        this.propertyValue = i;
    }

    public EscherSimpleProperty(EscherPropertyTypes escherPropertyTypes, int i) {
        this(escherPropertyTypes, false, false, i);
    }

    public EscherSimpleProperty(short s, boolean z, boolean z2, int i) {
        super(s, z, z2);
        this.propertyValue = i;
    }

    public EscherSimpleProperty(EscherPropertyTypes escherPropertyTypes, boolean z, boolean z2, int i) {
        super(escherPropertyTypes, z, z2);
        this.propertyValue = i;
    }

    public int serializeSimplePart(byte[] bArr, int i) {
        LittleEndian.putShort(bArr, i, getId());
        LittleEndian.putInt(bArr, i + 2, this.propertyValue);
        return 6;
    }

    public int getPropertyValue() {
        return this.propertyValue;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EscherSimpleProperty)) {
            return false;
        }
        EscherSimpleProperty escherSimpleProperty = (EscherSimpleProperty) obj;
        return this.propertyValue == escherSimpleProperty.propertyValue && getId() == escherSimpleProperty.getId();
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.propertyValue), Short.valueOf(getId())});
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new EscherSimpleProperty$$ExternalSyntheticLambda0(this), "value", new EscherSimpleProperty$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ddf-EscherSimpleProperty  reason: not valid java name */
    public /* synthetic */ Object m1960lambda$getGenericProperties$0$orgapachepoiddfEscherSimpleProperty() {
        return super.getGenericProperties();
    }
}
