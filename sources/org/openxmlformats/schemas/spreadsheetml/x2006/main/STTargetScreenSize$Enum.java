package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.xmlbeans.StringEnumAbstractBase;

public final class STTargetScreenSize$Enum extends StringEnumAbstractBase {
    static final int INT_X_1024_X_768 = 5;
    static final int INT_X_1152_X_882 = 6;
    static final int INT_X_1152_X_900 = 7;
    static final int INT_X_1280_X_1024 = 8;
    static final int INT_X_1600_X_1200 = 9;
    static final int INT_X_1800_X_1440 = 10;
    static final int INT_X_1920_X_1200 = 11;
    static final int INT_X_544_X_376 = 1;
    static final int INT_X_640_X_480 = 2;
    static final int INT_X_720_X_512 = 3;
    static final int INT_X_800_X_600 = 4;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STTargetScreenSize$Enum[]{new STTargetScreenSize$Enum("544x376", 1), new STTargetScreenSize$Enum("640x480", 2), new STTargetScreenSize$Enum("720x512", 3), new STTargetScreenSize$Enum("800x600", 4), new STTargetScreenSize$Enum("1024x768", 5), new STTargetScreenSize$Enum("1152x882", 6), new STTargetScreenSize$Enum("1152x900", 7), new STTargetScreenSize$Enum("1280x1024", 8), new STTargetScreenSize$Enum("1600x1200", 9), new STTargetScreenSize$Enum("1800x1440", 10), new STTargetScreenSize$Enum("1920x1200", 11)});

    public static STTargetScreenSize$Enum forString(String str) {
        return (STTargetScreenSize$Enum) table.forString(str);
    }

    public static STTargetScreenSize$Enum forInt(int i) {
        return (STTargetScreenSize$Enum) table.forInt(i);
    }

    private STTargetScreenSize$Enum(String str, int i) {
        super(str, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
