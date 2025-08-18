package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STBarDir extends XmlString {
    public static final Enum BAR = Enum.forString("bar");
    public static final Enum COL = Enum.forString("col");
    public static final SimpleTypeFactory<STBarDir> Factory;
    public static final int INT_BAR = 1;
    public static final int INT_COL = 2;
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STBarDir> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stbardir9d32type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_BAR = 1;
        static final int INT_COL = 2;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("bar", 1), new Enum("col", 2)});

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
