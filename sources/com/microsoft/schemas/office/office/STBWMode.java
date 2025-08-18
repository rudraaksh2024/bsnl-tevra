package com.microsoft.schemas.office.office;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlinx.coroutines.DebugKt;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STBWMode extends XmlString {
    public static final Enum AUTO = Enum.forString(DebugKt.DEBUG_PROPERTY_VALUE_AUTO);
    public static final Enum BLACK = Enum.forString("black");
    public static final Enum BLACK_TEXT_AND_LINES = Enum.forString("blackTextAndLines");
    public static final Enum COLOR = Enum.forString(TypedValues.Custom.S_COLOR);
    public static final SimpleTypeFactory<STBWMode> Factory;
    public static final Enum GRAY_OUTLINE = Enum.forString("grayOutline");
    public static final Enum GRAY_SCALE = Enum.forString("grayScale");
    public static final Enum HIDE = Enum.forString("hide");
    public static final Enum HIGH_CONTRAST = Enum.forString("highContrast");
    public static final int INT_AUTO = 2;
    public static final int INT_BLACK = 8;
    public static final int INT_BLACK_TEXT_AND_LINES = 12;
    public static final int INT_COLOR = 1;
    public static final int INT_GRAY_OUTLINE = 6;
    public static final int INT_GRAY_SCALE = 3;
    public static final int INT_HIDE = 10;
    public static final int INT_HIGH_CONTRAST = 7;
    public static final int INT_INVERSE_GRAY = 5;
    public static final int INT_LIGHT_GRAYSCALE = 4;
    public static final int INT_UNDRAWN = 11;
    public static final int INT_WHITE = 9;
    public static final Enum INVERSE_GRAY = Enum.forString("inverseGray");
    public static final Enum LIGHT_GRAYSCALE = Enum.forString("lightGrayscale");
    public static final Enum UNDRAWN = Enum.forString("undrawn");
    public static final Enum WHITE = Enum.forString("white");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STBWMode> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stbwmode77abtype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_AUTO = 2;
        static final int INT_BLACK = 8;
        static final int INT_BLACK_TEXT_AND_LINES = 12;
        static final int INT_COLOR = 1;
        static final int INT_GRAY_OUTLINE = 6;
        static final int INT_GRAY_SCALE = 3;
        static final int INT_HIDE = 10;
        static final int INT_HIGH_CONTRAST = 7;
        static final int INT_INVERSE_GRAY = 5;
        static final int INT_LIGHT_GRAYSCALE = 4;
        static final int INT_UNDRAWN = 11;
        static final int INT_WHITE = 9;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum(TypedValues.Custom.S_COLOR, 1), new Enum(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, 2), new Enum("grayScale", 3), new Enum("lightGrayscale", 4), new Enum("inverseGray", 5), new Enum("grayOutline", 6), new Enum("highContrast", 7), new Enum("black", 8), new Enum("white", 9), new Enum("hide", 10), new Enum("undrawn", 11), new Enum("blackTextAndLines", 12)});

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
