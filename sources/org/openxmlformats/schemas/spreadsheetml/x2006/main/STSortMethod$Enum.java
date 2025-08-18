package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.xmlbeans.StringEnumAbstractBase;

public final class STSortMethod$Enum extends StringEnumAbstractBase {
    static final int INT_NONE = 3;
    static final int INT_PIN_YIN = 2;
    static final int INT_STROKE = 1;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STSortMethod$Enum[]{new STSortMethod$Enum("stroke", 1), new STSortMethod$Enum("pinYin", 2), new STSortMethod$Enum("none", 3)});

    public static STSortMethod$Enum forString(String str) {
        return (STSortMethod$Enum) table.forString(str);
    }

    public static STSortMethod$Enum forInt(int i) {
        return (STSortMethod$Enum) table.forInt(i);
    }

    private STSortMethod$Enum(String str, int i) {
        super(str, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
