package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.xmlbeans.StringEnumAbstractBase;

public final class STComments$Enum extends StringEnumAbstractBase {
    static final int INT_COMM_INDICATOR = 2;
    static final int INT_COMM_IND_AND_COMMENT = 3;
    static final int INT_COMM_NONE = 1;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STComments$Enum[]{new STComments$Enum("commNone", 1), new STComments$Enum("commIndicator", 2), new STComments$Enum("commIndAndComment", 3)});

    public static STComments$Enum forString(String str) {
        return (STComments$Enum) table.forString(str);
    }

    public static STComments$Enum forInt(int i) {
        return (STComments$Enum) table.forInt(i);
    }

    private STComments$Enum(String str, int i) {
        super(str, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
