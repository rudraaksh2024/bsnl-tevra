package org.apache.poi.ddf;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndian;

public abstract class AbstractEscherOptRecord extends EscherRecord {
    private final List<EscherProperty> properties;

    protected AbstractEscherOptRecord() {
        this.properties = new ArrayList();
    }

    protected AbstractEscherOptRecord(AbstractEscherOptRecord abstractEscherOptRecord) {
        super(abstractEscherOptRecord);
        ArrayList arrayList = new ArrayList();
        this.properties = arrayList;
        arrayList.addAll(abstractEscherOptRecord.properties);
    }

    public void addEscherProperty(EscherProperty escherProperty) {
        this.properties.add(escherProperty);
    }

    public int fillFields(byte[] bArr, int i, EscherRecordFactory escherRecordFactory) {
        int readHeader = readHeader(bArr, i);
        if (readHeader >= 0) {
            short readInstance = readInstance(bArr, i);
            EscherPropertyFactory escherPropertyFactory = new EscherPropertyFactory();
            this.properties.clear();
            this.properties.addAll(escherPropertyFactory.createProperties(bArr, i + 8, readInstance));
            return readHeader + 8;
        }
        throw new IllegalStateException("Invalid value for bytesRemaining: " + readHeader);
    }

    public List<EscherProperty> getEscherProperties() {
        return this.properties;
    }

    public EscherProperty getEscherProperty(int i) {
        return this.properties.get(i);
    }

    private int getPropertiesSize() {
        int i = 0;
        for (EscherProperty propertySize : this.properties) {
            i += propertySize.getPropertySize();
        }
        return i;
    }

    public int getRecordSize() {
        return getPropertiesSize() + 8;
    }

    public <T extends EscherProperty> T lookup(EscherPropertyTypes escherPropertyTypes) {
        return lookup((int) escherPropertyTypes.propNumber);
    }

    static /* synthetic */ boolean lambda$lookup$0(int i, EscherProperty escherProperty) {
        return escherProperty.getPropertyNumber() == i;
    }

    public <T extends EscherProperty> T lookup(int i) {
        return (EscherProperty) this.properties.stream().filter(new AbstractEscherOptRecord$$ExternalSyntheticLambda6(i)).findFirst().orElse((Object) null);
    }

    public int serialize(int i, byte[] bArr, EscherSerializationListener escherSerializationListener) {
        escherSerializationListener.beforeRecordSerialize(i, getRecordId(), this);
        LittleEndian.putShort(bArr, i, getOptions());
        LittleEndian.putShort(bArr, i + 2, getRecordId());
        LittleEndian.putInt(bArr, i + 4, getPropertiesSize());
        int i2 = i + 8;
        for (EscherProperty serializeSimplePart : this.properties) {
            i2 += serializeSimplePart.serializeSimplePart(bArr, i2);
        }
        for (EscherProperty serializeComplexPart : this.properties) {
            i2 += serializeComplexPart.serializeComplexPart(bArr, i2);
        }
        int i3 = i2 - i;
        escherSerializationListener.afterRecordSerialize(i2, getRecordId(), i3, this);
        return i3;
    }

    public void sortProperties() {
        this.properties.sort(Comparator.comparingInt(new AbstractEscherOptRecord$$ExternalSyntheticLambda0()));
    }

    static /* synthetic */ boolean lambda$setEscherProperty$1(EscherProperty escherProperty, EscherProperty escherProperty2) {
        return escherProperty2.getId() == escherProperty.getId();
    }

    public void setEscherProperty(EscherProperty escherProperty) {
        this.properties.removeIf(new AbstractEscherOptRecord$$ExternalSyntheticLambda1(escherProperty));
        this.properties.add(escherProperty);
        sortProperties();
    }

    static /* synthetic */ boolean lambda$removeEscherProperty$2(EscherPropertyTypes escherPropertyTypes, EscherProperty escherProperty) {
        return escherProperty.getPropertyNumber() == escherPropertyTypes.propNumber;
    }

    public void removeEscherProperty(EscherPropertyTypes escherPropertyTypes) {
        this.properties.removeIf(new AbstractEscherOptRecord$$ExternalSyntheticLambda5(escherPropertyTypes));
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new AbstractEscherOptRecord$$ExternalSyntheticLambda2(this), "isContainer", new AbstractEscherOptRecord$$ExternalSyntheticLambda3(this), "properties", new AbstractEscherOptRecord$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-ddf-AbstractEscherOptRecord  reason: not valid java name */
    public /* synthetic */ Object m1948lambda$getGenericProperties$3$orgapachepoiddfAbstractEscherOptRecord() {
        return super.getGenericProperties();
    }
}
