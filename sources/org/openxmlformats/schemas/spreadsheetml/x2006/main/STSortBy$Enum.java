package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.xmlbeans.StringEnumAbstractBase;

public final class STSortBy$Enum extends StringEnumAbstractBase {
    static final int INT_CELL_COLOR = 2;
    static final int INT_FONT_COLOR = 3;
    static final int INT_ICON = 4;
    static final int INT_VALUE = 1;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STSortBy$Enum[]{new STSortBy$Enum("value", 1), new STSortBy$Enum("cellColor", 2), new STSortBy$Enum("fontColor", 3), new STSortBy$Enum("icon", 4)});

    public static STSortBy$Enum forString(String str) {
        return (STSortBy$Enum) table.forString(str);
    }

    public static STSortBy$Enum forInt(int i) {
        return (STSortBy$Enum) table.forInt(i);
    }

    private STSortBy$Enum(String str, int i) {
        super(str, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
