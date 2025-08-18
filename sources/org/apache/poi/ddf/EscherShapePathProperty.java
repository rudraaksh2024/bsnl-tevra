package org.apache.poi.ddf;

public class EscherShapePathProperty extends EscherSimpleProperty {
    public static final int CLOSED_CURVES = 3;
    public static final int CLOSED_POLYGON = 1;
    public static final int COMPLEX = 4;
    public static final int CURVES = 2;
    public static final int LINE_OF_STRAIGHT_SEGMENTS = 0;

    public EscherShapePathProperty(short s, int i) {
        super(s, false, false, i);
    }

    public EscherShapePathProperty(EscherPropertyTypes escherPropertyTypes, int i) {
        super(escherPropertyTypes, false, false, i);
    }
}
