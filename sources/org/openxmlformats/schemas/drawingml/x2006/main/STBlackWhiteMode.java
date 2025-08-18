package org.openxmlformats.schemas.drawingml.x2006.main;

import kotlinx.coroutines.DebugKt;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.poi.ss.util.CellUtil;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STBlackWhiteMode extends XmlToken {
    public static final Enum AUTO = Enum.forString(DebugKt.DEBUG_PROPERTY_VALUE_AUTO);
    public static final Enum BLACK = Enum.forString("black");
    public static final Enum BLACK_GRAY = Enum.forString("blackGray");
    public static final Enum BLACK_WHITE = Enum.forString("blackWhite");
    public static final Enum CLR = Enum.forString("clr");
    public static final SimpleTypeFactory<STBlackWhiteMode> Factory;
    public static final Enum GRAY = Enum.forString("gray");
    public static final Enum GRAY_WHITE = Enum.forString("grayWhite");
    public static final Enum HIDDEN = Enum.forString(CellUtil.HIDDEN);
    public static final int INT_AUTO = 2;
    public static final int INT_BLACK = 9;
    public static final int INT_BLACK_GRAY = 7;
    public static final int INT_BLACK_WHITE = 8;
    public static final int INT_CLR = 1;
    public static final int INT_GRAY = 3;
    public static final int INT_GRAY_WHITE = 6;
    public static final int INT_HIDDEN = 11;
    public static final int INT_INV_GRAY = 5;
    public static final int INT_LT_GRAY = 4;
    public static final int INT_WHITE = 10;
    public static final Enum INV_GRAY = Enum.forString("invGray");
    public static final Enum LT_GRAY = Enum.forString("ltGray");
    public static final Enum WHITE = Enum.forString("white");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STBlackWhiteMode> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stblackwhitemode0558type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_AUTO = 2;
        static final int INT_BLACK = 9;
        static final int INT_BLACK_GRAY = 7;
        static final int INT_BLACK_WHITE = 8;
        static final int INT_CLR = 1;
        static final int INT_GRAY = 3;
        static final int INT_GRAY_WHITE = 6;
        static final int INT_HIDDEN = 11;
        static final int INT_INV_GRAY = 5;
        static final int INT_LT_GRAY = 4;
        static final int INT_WHITE = 10;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("clr", 1), new Enum(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, 2), new Enum("gray", 3), new Enum("ltGray", 4), new Enum("invGray", 5), new Enum("grayWhite", 6), new Enum("blackGray", 7), new Enum("blackWhite", 8), new Enum("black", 9), new Enum("white", 10), new Enum(CellUtil.HIDDEN, 11)});

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
