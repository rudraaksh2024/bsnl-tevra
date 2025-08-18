package com.microsoft.schemas.vml;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STFillType extends XmlString {
    public static final Enum FRAME = Enum.forString(TypedValues.AttributesType.S_FRAME);
    public static final SimpleTypeFactory<STFillType> Factory;
    public static final Enum GRADIENT = Enum.forString("gradient");
    public static final Enum GRADIENT_RADIAL = Enum.forString("gradientRadial");
    public static final int INT_FRAME = 6;
    public static final int INT_GRADIENT = 2;
    public static final int INT_GRADIENT_RADIAL = 3;
    public static final int INT_PATTERN = 5;
    public static final int INT_SOLID = 1;
    public static final int INT_TILE = 4;
    public static final Enum PATTERN = Enum.forString("pattern");
    public static final Enum SOLID = Enum.forString("solid");
    public static final Enum TILE = Enum.forString("tile");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STFillType> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stfilltype382btype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_FRAME = 6;
        static final int INT_GRADIENT = 2;
        static final int INT_GRADIENT_RADIAL = 3;
        static final int INT_PATTERN = 5;
        static final int INT_SOLID = 1;
        static final int INT_TILE = 4;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("solid", 1), new Enum("gradient", 2), new Enum("gradientRadial", 3), new Enum("tile", 4), new Enum("pattern", 5), new Enum(TypedValues.AttributesType.S_FRAME, 6)});

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
