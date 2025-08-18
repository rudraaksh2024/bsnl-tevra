package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.xmlbeans.StringEnumAbstractBase;

public final class STTLTimeNodeMasterRelation$Enum extends StringEnumAbstractBase {
    static final int INT_LAST_CLICK = 2;
    static final int INT_NEXT_CLICK = 3;
    static final int INT_SAME_CLICK = 1;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STTLTimeNodeMasterRelation$Enum[]{new STTLTimeNodeMasterRelation$Enum("sameClick", 1), new STTLTimeNodeMasterRelation$Enum("lastClick", 2), new STTLTimeNodeMasterRelation$Enum("nextClick", 3)});

    public static STTLTimeNodeMasterRelation$Enum forString(String str) {
        return (STTLTimeNodeMasterRelation$Enum) table.forString(str);
    }

    public static STTLTimeNodeMasterRelation$Enum forInt(int i) {
        return (STTLTimeNodeMasterRelation$Enum) table.forInt(i);
    }

    private STTLTimeNodeMasterRelation$Enum(String str, int i) {
        super(str, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
