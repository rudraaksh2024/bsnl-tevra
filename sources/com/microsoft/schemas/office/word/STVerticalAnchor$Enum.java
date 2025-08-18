package com.microsoft.schemas.office.word;

import org.apache.xmlbeans.StringEnumAbstractBase;

public final class STVerticalAnchor$Enum extends StringEnumAbstractBase {
    static final int INT_LINE = 4;
    static final int INT_MARGIN = 1;
    static final int INT_PAGE = 2;
    static final int INT_TEXT = 3;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STVerticalAnchor$Enum[]{new STVerticalAnchor$Enum("margin", 1), new STVerticalAnchor$Enum("page", 2), new STVerticalAnchor$Enum("text", 3), new STVerticalAnchor$Enum("line", 4)});

    public static STVerticalAnchor$Enum forString(String str) {
        return (STVerticalAnchor$Enum) table.forString(str);
    }

    public static STVerticalAnchor$Enum forInt(int i) {
        return (STVerticalAnchor$Enum) table.forInt(i);
    }

    private STVerticalAnchor$Enum(String str, int i) {
        super(str, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
