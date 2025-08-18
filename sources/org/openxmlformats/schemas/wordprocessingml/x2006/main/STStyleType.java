package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STStyleType extends XmlString {
    public static final Enum CHARACTER = Enum.forString("character");
    public static final SimpleTypeFactory<STStyleType> Factory;
    public static final int INT_CHARACTER = 2;
    public static final int INT_NUMBERING = 4;
    public static final int INT_PARAGRAPH = 1;
    public static final int INT_TABLE = 3;
    public static final Enum NUMBERING = Enum.forString("numbering");
    public static final Enum PARAGRAPH = Enum.forString("paragraph");
    public static final Enum TABLE = Enum.forString("table");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STStyleType> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "ststyletypec2b7type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_CHARACTER = 2;
        static final int INT_NUMBERING = 4;
        static final int INT_PARAGRAPH = 1;
        static final int INT_TABLE = 3;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("paragraph", 1), new Enum("character", 2), new Enum("table", 3), new Enum("numbering", 4)});

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
