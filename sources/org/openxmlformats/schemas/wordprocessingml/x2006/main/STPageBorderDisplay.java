package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STPageBorderDisplay extends XmlString {
    public static final Enum ALL_PAGES = Enum.forString("allPages");
    public static final Enum FIRST_PAGE = Enum.forString("firstPage");
    public static final SimpleTypeFactory<STPageBorderDisplay> Factory;
    public static final int INT_ALL_PAGES = 1;
    public static final int INT_FIRST_PAGE = 2;
    public static final int INT_NOT_FIRST_PAGE = 3;
    public static final Enum NOT_FIRST_PAGE = Enum.forString("notFirstPage");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STPageBorderDisplay> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stpageborderdisplay731btype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_ALL_PAGES = 1;
        static final int INT_FIRST_PAGE = 2;
        static final int INT_NOT_FIRST_PAGE = 3;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("allPages", 1), new Enum("firstPage", 2), new Enum("notFirstPage", 3)});

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
