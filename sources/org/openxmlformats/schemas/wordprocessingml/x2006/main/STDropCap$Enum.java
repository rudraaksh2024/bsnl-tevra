package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.xmlbeans.StringEnumAbstractBase;

public final class STDropCap$Enum extends StringEnumAbstractBase {
    static final int INT_DROP = 2;
    static final int INT_MARGIN = 3;
    static final int INT_NONE = 1;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STDropCap$Enum[]{new STDropCap$Enum("none", 1), new STDropCap$Enum("drop", 2), new STDropCap$Enum("margin", 3)});

    public static STDropCap$Enum forString(String str) {
        return (STDropCap$Enum) table.forString(str);
    }

    public static STDropCap$Enum forInt(int i) {
        return (STDropCap$Enum) table.forInt(i);
    }

    private STDropCap$Enum(String str, int i) {
        super(str, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
