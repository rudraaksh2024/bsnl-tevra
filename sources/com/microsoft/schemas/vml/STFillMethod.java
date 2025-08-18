package com.microsoft.schemas.vml;

import org.apache.commons.codec.language.bm.Languages;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STFillMethod extends XmlString {
    public static final Enum ANY = Enum.forString(Languages.ANY);
    public static final SimpleTypeFactory<STFillMethod> Factory;
    public static final int INT_ANY = 4;
    public static final int INT_LINEAR = 2;
    public static final int INT_LINEAR_SIGMA = 5;
    public static final int INT_NONE = 1;
    public static final int INT_SIGMA = 3;
    public static final Enum LINEAR = Enum.forString("linear");
    public static final Enum LINEAR_SIGMA = Enum.forString("linear sigma");
    public static final Enum NONE = Enum.forString("none");
    public static final Enum SIGMA = Enum.forString("sigma");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STFillMethod> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stfillmethoda592type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_ANY = 4;
        static final int INT_LINEAR = 2;
        static final int INT_LINEAR_SIGMA = 5;
        static final int INT_NONE = 1;
        static final int INT_SIGMA = 3;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("none", 1), new Enum("linear", 2), new Enum("sigma", 3), new Enum(Languages.ANY, 4), new Enum("linear sigma", 5)});

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
