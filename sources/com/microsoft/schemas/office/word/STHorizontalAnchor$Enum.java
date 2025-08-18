package com.microsoft.schemas.office.word;

import org.apache.xmlbeans.StringEnumAbstractBase;

public final class STHorizontalAnchor$Enum extends StringEnumAbstractBase {
    static final int INT_CHAR = 4;
    static final int INT_MARGIN = 1;
    static final int INT_PAGE = 2;
    static final int INT_TEXT = 3;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STHorizontalAnchor$Enum[]{new STHorizontalAnchor$Enum("margin", 1), new STHorizontalAnchor$Enum("page", 2), new STHorizontalAnchor$Enum("text", 3), new STHorizontalAnchor$Enum("char", 4)});

    public static STHorizontalAnchor$Enum forString(String str) {
        return (STHorizontalAnchor$Enum) table.forString(str);
    }

    public static STHorizontalAnchor$Enum forInt(int i) {
        return (STHorizontalAnchor$Enum) table.forInt(i);
    }

    private STHorizontalAnchor$Enum(String str, int i) {
        super(str, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
