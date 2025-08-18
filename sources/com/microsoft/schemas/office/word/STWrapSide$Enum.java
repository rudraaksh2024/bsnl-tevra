package com.microsoft.schemas.office.word;

import org.apache.xmlbeans.StringEnumAbstractBase;

public final class STWrapSide$Enum extends StringEnumAbstractBase {
    static final int INT_BOTH = 1;
    static final int INT_LARGEST = 4;
    static final int INT_LEFT = 2;
    static final int INT_RIGHT = 3;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STWrapSide$Enum[]{new STWrapSide$Enum("both", 1), new STWrapSide$Enum("left", 2), new STWrapSide$Enum("right", 3), new STWrapSide$Enum("largest", 4)});

    public static STWrapSide$Enum forString(String str) {
        return (STWrapSide$Enum) table.forString(str);
    }

    public static STWrapSide$Enum forInt(int i) {
        return (STWrapSide$Enum) table.forInt(i);
    }

    private STWrapSide$Enum(String str, int i) {
        super(str, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
