package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STDataValidationType extends XmlString {
    public static final Enum CUSTOM = Enum.forString("custom");
    public static final Enum DATE = Enum.forString(XmlErrorCodes.DATE);
    public static final Enum DECIMAL = Enum.forString(XmlErrorCodes.DECIMAL);
    public static final SimpleTypeFactory<STDataValidationType> Factory;
    public static final int INT_CUSTOM = 8;
    public static final int INT_DATE = 5;
    public static final int INT_DECIMAL = 3;
    public static final int INT_LIST = 4;
    public static final int INT_NONE = 1;
    public static final int INT_TEXT_LENGTH = 7;
    public static final int INT_TIME = 6;
    public static final int INT_WHOLE = 2;
    public static final Enum LIST = Enum.forString(XmlErrorCodes.LIST);
    public static final Enum NONE = Enum.forString("none");
    public static final Enum TEXT_LENGTH = Enum.forString("textLength");
    public static final Enum TIME = Enum.forString("time");
    public static final Enum WHOLE = Enum.forString("whole");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STDataValidationType> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stdatavalidationtypeabf6type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_CUSTOM = 8;
        static final int INT_DATE = 5;
        static final int INT_DECIMAL = 3;
        static final int INT_LIST = 4;
        static final int INT_NONE = 1;
        static final int INT_TEXT_LENGTH = 7;
        static final int INT_TIME = 6;
        static final int INT_WHOLE = 2;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("none", 1), new Enum("whole", 2), new Enum(XmlErrorCodes.DECIMAL, 3), new Enum(XmlErrorCodes.LIST, 4), new Enum(XmlErrorCodes.DATE, 5), new Enum("time", 6), new Enum("textLength", 7), new Enum("custom", 8)});

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
