package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STTLTimeNodeFillType extends XmlToken {
    public static final Enum FREEZE = Enum.forString("freeze");
    public static final SimpleTypeFactory<STTLTimeNodeFillType> Factory;
    public static final Enum HOLD = Enum.forString("hold");
    public static final int INT_FREEZE = 2;
    public static final int INT_HOLD = 3;
    public static final int INT_REMOVE = 1;
    public static final int INT_TRANSITION = 4;
    public static final Enum REMOVE = Enum.forString("remove");
    public static final Enum TRANSITION = Enum.forString("transition");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STTLTimeNodeFillType> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sttltimenodefilltypeb7f1type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_FREEZE = 2;
        static final int INT_HOLD = 3;
        static final int INT_REMOVE = 1;
        static final int INT_TRANSITION = 4;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("remove", 1), new Enum("freeze", 2), new Enum("hold", 3), new Enum("transition", 4)});

        public static Enum forString(String str) {
            return (Enum) table.forString(str);
        }

        public static Enum forInt(int i) {
            return (Enum) table.forInt(i);
        }

        private Enum(String str, int i) {
            super(str, i);
        }

        private Object readResolve() {
            return forInt(intValue());
        }
    }
}
