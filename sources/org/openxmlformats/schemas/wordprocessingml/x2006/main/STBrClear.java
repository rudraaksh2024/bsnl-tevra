package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STBrClear extends XmlString {
    public static final Enum ALL = Enum.forString("all");
    public static final SimpleTypeFactory<STBrClear> Factory;
    public static final int INT_ALL = 4;
    public static final int INT_LEFT = 2;
    public static final int INT_NONE = 1;
    public static final int INT_RIGHT = 3;
    public static final Enum LEFT = Enum.forString("left");
    public static final Enum NONE = Enum.forString("none");
    public static final Enum RIGHT = Enum.forString("right");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STBrClear> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stbrclearb1e5type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_ALL = 4;
        static final int INT_LEFT = 2;
        static final int INT_NONE = 1;
        static final int INT_RIGHT = 3;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("none", 1), new Enum("left", 2), new Enum("right", 3), new Enum("all", 4)});

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
