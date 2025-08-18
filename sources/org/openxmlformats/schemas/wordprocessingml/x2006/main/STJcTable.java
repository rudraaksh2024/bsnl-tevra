package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STJcTable extends XmlString {
    public static final Enum CENTER = Enum.forString("center");
    public static final Enum END = Enum.forString("end");
    public static final SimpleTypeFactory<STJcTable> Factory;
    public static final int INT_CENTER = 1;
    public static final int INT_END = 2;
    public static final int INT_LEFT = 3;
    public static final int INT_RIGHT = 4;
    public static final int INT_START = 5;
    public static final Enum LEFT = Enum.forString("left");
    public static final Enum RIGHT = Enum.forString("right");
    public static final Enum START = Enum.forString("start");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STJcTable> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stjctable2eadtype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_CENTER = 1;
        static final int INT_END = 2;
        static final int INT_LEFT = 3;
        static final int INT_RIGHT = 4;
        static final int INT_START = 5;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("center", 1), new Enum("end", 2), new Enum("left", 3), new Enum("right", 4), new Enum("start", 5)});

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
