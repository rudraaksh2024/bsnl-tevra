package com.microsoft.schemas.office.office;

import org.apache.xmlbeans.StringEnumAbstractBase;

public final class STScreenSize$Enum extends StringEnumAbstractBase {
    static final int INT_X_1024_768 = 5;
    static final int INT_X_1152_862 = 6;
    static final int INT_X_544_376 = 1;
    static final int INT_X_640_480 = 2;
    static final int INT_X_720_512 = 3;
    static final int INT_X_800_600 = 4;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STScreenSize$Enum[]{new STScreenSize$Enum("544,376", 1), new STScreenSize$Enum("640,480", 2), new STScreenSize$Enum("720,512", 3), new STScreenSize$Enum("800,600", 4), new STScreenSize$Enum("1024,768", 5), new STScreenSize$Enum("1152,862", 6)});

    public static STScreenSize$Enum forString(String str) {
        return (STScreenSize$Enum) table.forString(str);
    }

    public static STScreenSize$Enum forInt(int i) {
        return (STScreenSize$Enum) table.forInt(i);
    }

    private STScreenSize$Enum(String str, int i) {
        super(str, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
