package org.apache.poi.ddf;

public class EscherBoolProperty extends EscherSimpleProperty {
    public EscherBoolProperty(short s, int i) {
        super(s, i);
    }

    public EscherBoolProperty(EscherPropertyTypes escherPropertyTypes, int i) {
        super(escherPropertyTypes.propNumber, i);
    }

    public boolean isTrue() {
        return getPropertyValue() != 0;
    }
}
