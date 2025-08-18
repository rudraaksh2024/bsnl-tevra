package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STOrientation extends XmlString {
    public static final Enum DEFAULT = Enum.forString("default");
    public static final SimpleTypeFactory<STOrientation> Factory;
    public static final int INT_DEFAULT = 1;
    public static final int INT_LANDSCAPE = 3;
    public static final int INT_PORTRAIT = 2;
    public static final Enum LANDSCAPE = Enum.forString("landscape");
    public static final Enum PORTRAIT = Enum.forString("portrait");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STOrientation> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "storientation3c9ftype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_DEFAULT = 1;
        static final int INT_LANDSCAPE = 3;
        static final int INT_PORTRAIT = 2;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("default", 1), new Enum("portrait", 2), new Enum("landscape", 3)});

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
