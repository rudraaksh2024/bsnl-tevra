package org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STWrapText extends XmlToken {
    public static final Enum BOTH_SIDES = Enum.forString("bothSides");
    public static final SimpleTypeFactory<STWrapText> Factory;
    public static final int INT_BOTH_SIDES = 1;
    public static final int INT_LARGEST = 4;
    public static final int INT_LEFT = 2;
    public static final int INT_RIGHT = 3;
    public static final Enum LARGEST = Enum.forString("largest");
    public static final Enum LEFT = Enum.forString("left");
    public static final Enum RIGHT = Enum.forString("right");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STWrapText> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stwraptext4a98type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_BOTH_SIDES = 1;
        static final int INT_LARGEST = 4;
        static final int INT_LEFT = 2;
        static final int INT_RIGHT = 3;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("bothSides", 1), new Enum("left", 2), new Enum("right", 3), new Enum("largest", 4)});

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
