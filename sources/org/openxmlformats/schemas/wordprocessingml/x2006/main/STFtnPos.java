package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STFtnPos extends XmlString {
    public static final Enum BENEATH_TEXT = Enum.forString("beneathText");
    public static final Enum DOC_END = Enum.forString("docEnd");
    public static final SimpleTypeFactory<STFtnPos> Factory;
    public static final int INT_BENEATH_TEXT = 2;
    public static final int INT_DOC_END = 4;
    public static final int INT_PAGE_BOTTOM = 1;
    public static final int INT_SECT_END = 3;
    public static final Enum PAGE_BOTTOM = Enum.forString("pageBottom");
    public static final Enum SECT_END = Enum.forString("sectEnd");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STFtnPos> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stftnposdc44type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_BENEATH_TEXT = 2;
        static final int INT_DOC_END = 4;
        static final int INT_PAGE_BOTTOM = 1;
        static final int INT_SECT_END = 3;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("pageBottom", 1), new Enum("beneathText", 2), new Enum("sectEnd", 3), new Enum("docEnd", 4)});

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
