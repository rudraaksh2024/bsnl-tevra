package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STTabJc extends XmlString {
    public static final Enum BAR = Enum.forString("bar");
    public static final Enum CENTER = Enum.forString("center");
    public static final Enum CLEAR = Enum.forString("clear");
    public static final Enum DECIMAL = Enum.forString(XmlErrorCodes.DECIMAL);
    public static final Enum END = Enum.forString("end");
    public static final SimpleTypeFactory<STTabJc> Factory;
    public static final int INT_BAR = 6;
    public static final int INT_CENTER = 3;
    public static final int INT_CLEAR = 1;
    public static final int INT_DECIMAL = 5;
    public static final int INT_END = 4;
    public static final int INT_LEFT = 8;
    public static final int INT_NUM = 7;
    public static final int INT_RIGHT = 9;
    public static final int INT_START = 2;
    public static final Enum LEFT = Enum.forString("left");
    public static final Enum NUM = Enum.forString("num");
    public static final Enum RIGHT = Enum.forString("right");
    public static final Enum START = Enum.forString("start");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STTabJc> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sttabjc10f4type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_BAR = 6;
        static final int INT_CENTER = 3;
        static final int INT_CLEAR = 1;
        static final int INT_DECIMAL = 5;
        static final int INT_END = 4;
        static final int INT_LEFT = 8;
        static final int INT_NUM = 7;
        static final int INT_RIGHT = 9;
        static final int INT_START = 2;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("clear", 1), new Enum("start", 2), new Enum("center", 3), new Enum("end", 4), new Enum(XmlErrorCodes.DECIMAL, 5), new Enum("bar", 6), new Enum("num", 7), new Enum("left", 8), new Enum("right", 9)});

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
