package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STErrBarType extends XmlString {
    public static final Enum BOTH = Enum.forString("both");
    public static final SimpleTypeFactory<STErrBarType> Factory;
    public static final int INT_BOTH = 1;
    public static final int INT_MINUS = 2;
    public static final int INT_PLUS = 3;
    public static final Enum MINUS = Enum.forString("minus");
    public static final Enum PLUS = Enum.forString("plus");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STErrBarType> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sterrbartypea2a4type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_BOTH = 1;
        static final int INT_MINUS = 2;
        static final int INT_PLUS = 3;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("both", 1), new Enum("minus", 2), new Enum("plus", 3)});

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
