package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STPlaceholderSize extends XmlToken {
    public static final Enum FULL = Enum.forString("full");
    public static final SimpleTypeFactory<STPlaceholderSize> Factory;
    public static final Enum HALF = Enum.forString("half");
    public static final int INT_FULL = 1;
    public static final int INT_HALF = 2;
    public static final int INT_QUARTER = 3;
    public static final Enum QUARTER = Enum.forString("quarter");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STPlaceholderSize> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stplaceholdersize914btype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_FULL = 1;
        static final int INT_HALF = 2;
        static final int INT_QUARTER = 3;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("full", 1), new Enum("half", 2), new Enum("quarter", 3)});

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
