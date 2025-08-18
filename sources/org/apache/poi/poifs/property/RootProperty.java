package org.apache.poi.poifs.property;

public final class RootProperty extends DirectoryProperty {
    private static final String NAME = "Root Entry";

    public String getName() {
        return NAME;
    }

    RootProperty() {
        super(NAME);
        setNodeColor((byte) 1);
        setPropertyType((byte) 5);
        setStartBlock(-2);
    }

    RootProperty(int i, byte[] bArr, int i2) {
        super(i, bArr, i2);
    }

    public void setSize(int i) {
        super.setSize(Math.multiplyExact(i, 64));
    }
}
