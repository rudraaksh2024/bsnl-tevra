package org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STRelFromV extends XmlToken {
    public static final Enum BOTTOM_MARGIN = Enum.forString("bottomMargin");
    public static final SimpleTypeFactory<STRelFromV> Factory;
    public static final Enum INSIDE_MARGIN = Enum.forString("insideMargin");
    public static final int INT_BOTTOM_MARGIN = 6;
    public static final int INT_INSIDE_MARGIN = 7;
    public static final int INT_LINE = 4;
    public static final int INT_MARGIN = 1;
    public static final int INT_OUTSIDE_MARGIN = 8;
    public static final int INT_PAGE = 2;
    public static final int INT_PARAGRAPH = 3;
    public static final int INT_TOP_MARGIN = 5;
    public static final Enum LINE = Enum.forString("line");
    public static final Enum MARGIN = Enum.forString("margin");
    public static final Enum OUTSIDE_MARGIN = Enum.forString("outsideMargin");
    public static final Enum PAGE = Enum.forString("page");
    public static final Enum PARAGRAPH = Enum.forString("paragraph");
    public static final Enum TOP_MARGIN = Enum.forString("topMargin");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STRelFromV> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "strelfromv56dctype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_BOTTOM_MARGIN = 6;
        static final int INT_INSIDE_MARGIN = 7;
        static final int INT_LINE = 4;
        static final int INT_MARGIN = 1;
        static final int INT_OUTSIDE_MARGIN = 8;
        static final int INT_PAGE = 2;
        static final int INT_PARAGRAPH = 3;
        static final int INT_TOP_MARGIN = 5;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("margin", 1), new Enum("page", 2), new Enum("paragraph", 3), new Enum("line", 4), new Enum("topMargin", 5), new Enum("bottomMargin", 6), new Enum("insideMargin", 7), new Enum("outsideMargin", 8)});

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
