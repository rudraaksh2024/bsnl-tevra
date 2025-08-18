package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.xmlbeans.StringEnumAbstractBase;

public final class STTLTimeNodePresetClassType$Enum extends StringEnumAbstractBase {
    static final int INT_EMPH = 3;
    static final int INT_ENTR = 1;
    static final int INT_EXIT = 2;
    static final int INT_MEDIACALL = 6;
    static final int INT_PATH = 4;
    static final int INT_VERB = 5;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STTLTimeNodePresetClassType$Enum[]{new STTLTimeNodePresetClassType$Enum("entr", 1), new STTLTimeNodePresetClassType$Enum("exit", 2), new STTLTimeNodePresetClassType$Enum("emph", 3), new STTLTimeNodePresetClassType$Enum("path", 4), new STTLTimeNodePresetClassType$Enum("verb", 5), new STTLTimeNodePresetClassType$Enum("mediacall", 6)});

    public static STTLTimeNodePresetClassType$Enum forString(String str) {
        return (STTLTimeNodePresetClassType$Enum) table.forString(str);
    }

    public static STTLTimeNodePresetClassType$Enum forInt(int i) {
        return (STTLTimeNodePresetClassType$Enum) table.forInt(i);
    }

    private STTLTimeNodePresetClassType$Enum(String str, int i) {
        super(str, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
