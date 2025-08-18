package com.microsoft.schemas.office.office;

import org.apache.xmlbeans.StringEnumAbstractBase;

public final class STOLEUpdateMode$Enum extends StringEnumAbstractBase {
    static final int INT_ALWAYS = 1;
    static final int INT_ON_CALL = 2;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STOLEUpdateMode$Enum[]{new STOLEUpdateMode$Enum("Always", 1), new STOLEUpdateMode$Enum("OnCall", 2)});

    public static STOLEUpdateMode$Enum forString(String str) {
        return (STOLEUpdateMode$Enum) table.forString(str);
    }

    public static STOLEUpdateMode$Enum forInt(int i) {
        return (STOLEUpdateMode$Enum) table.forInt(i);
    }

    private STOLEUpdateMode$Enum(String str, int i) {
        super(str, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
