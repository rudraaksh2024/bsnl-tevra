package org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STRelFromH extends XmlToken {
    public static final Enum CHARACTER = Enum.forString("character");
    public static final Enum COLUMN = Enum.forString("column");
    public static final SimpleTypeFactory<STRelFromH> Factory;
    public static final Enum INSIDE_MARGIN = Enum.forString("insideMargin");
    public static final int INT_CHARACTER = 4;
    public static final int INT_COLUMN = 3;
    public static final int INT_INSIDE_MARGIN = 7;
    public static final int INT_LEFT_MARGIN = 5;
    public static final int INT_MARGIN = 1;
    public static final int INT_OUTSIDE_MARGIN = 8;
    public static final int INT_PAGE = 2;
    public static final int INT_RIGHT_MARGIN = 6;
    public static final Enum LEFT_MARGIN = Enum.forString("leftMargin");
    public static final Enum MARGIN = Enum.forString("margin");
    public static final Enum OUTSIDE_MARGIN = Enum.forString("outsideMargin");
    public static final Enum PAGE = Enum.forString("page");
    public static final Enum RIGHT_MARGIN = Enum.forString("rightMargin");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STRelFromH> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "strelfromh72aatype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_CHARACTER = 4;
        static final int INT_COLUMN = 3;
        static final int INT_INSIDE_MARGIN = 7;
        static final int INT_LEFT_MARGIN = 5;
        static final int INT_MARGIN = 1;
        static final int INT_OUTSIDE_MARGIN = 8;
        static final int INT_PAGE = 2;
        static final int INT_RIGHT_MARGIN = 6;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("margin", 1), new Enum("page", 2), new Enum("column", 3), new Enum("character", 4), new Enum("leftMargin", 5), new Enum("rightMargin", 6), new Enum("insideMargin", 7), new Enum("outsideMargin", 8)});

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
