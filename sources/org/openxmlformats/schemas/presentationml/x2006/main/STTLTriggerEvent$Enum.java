package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.xmlbeans.StringEnumAbstractBase;

public final class STTLTriggerEvent$Enum extends StringEnumAbstractBase {
    static final int INT_BEGIN = 3;
    static final int INT_END = 4;
    static final int INT_ON_BEGIN = 1;
    static final int INT_ON_CLICK = 5;
    static final int INT_ON_DBL_CLICK = 6;
    static final int INT_ON_END = 2;
    static final int INT_ON_MOUSE_OUT = 8;
    static final int INT_ON_MOUSE_OVER = 7;
    static final int INT_ON_NEXT = 9;
    static final int INT_ON_PREV = 10;
    static final int INT_ON_STOP_AUDIO = 11;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STTLTriggerEvent$Enum[]{new STTLTriggerEvent$Enum("onBegin", 1), new STTLTriggerEvent$Enum("onEnd", 2), new STTLTriggerEvent$Enum("begin", 3), new STTLTriggerEvent$Enum("end", 4), new STTLTriggerEvent$Enum("onClick", 5), new STTLTriggerEvent$Enum("onDblClick", 6), new STTLTriggerEvent$Enum("onMouseOver", 7), new STTLTriggerEvent$Enum("onMouseOut", 8), new STTLTriggerEvent$Enum("onNext", 9), new STTLTriggerEvent$Enum("onPrev", 10), new STTLTriggerEvent$Enum("onStopAudio", 11)});

    public static STTLTriggerEvent$Enum forString(String str) {
        return (STTLTriggerEvent$Enum) table.forString(str);
    }

    public static STTLTriggerEvent$Enum forInt(int i) {
        return (STTLTriggerEvent$Enum) table.forInt(i);
    }

    private STTLTriggerEvent$Enum(String str, int i) {
        super(str, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
