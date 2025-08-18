package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STBlipCompression extends XmlToken {
    public static final Enum EMAIL = Enum.forString("email");
    public static final SimpleTypeFactory<STBlipCompression> Factory;
    public static final Enum HQPRINT = Enum.forString("hqprint");
    public static final int INT_EMAIL = 1;
    public static final int INT_HQPRINT = 4;
    public static final int INT_NONE = 5;
    public static final int INT_PRINT = 3;
    public static final int INT_SCREEN = 2;
    public static final Enum NONE = Enum.forString("none");
    public static final Enum PRINT = Enum.forString("print");
    public static final Enum SCREEN = Enum.forString("screen");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STBlipCompression> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stblipcompressionb216type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_EMAIL = 1;
        static final int INT_HQPRINT = 4;
        static final int INT_NONE = 5;
        static final int INT_PRINT = 3;
        static final int INT_SCREEN = 2;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("email", 1), new Enum("screen", 2), new Enum("print", 3), new Enum("hqprint", 4), new Enum("none", 5)});

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
