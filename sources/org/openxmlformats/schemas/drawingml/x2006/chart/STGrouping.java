package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STGrouping extends XmlString {
    public static final SimpleTypeFactory<STGrouping> Factory;
    public static final int INT_PERCENT_STACKED = 1;
    public static final int INT_STACKED = 3;
    public static final int INT_STANDARD = 2;
    public static final Enum PERCENT_STACKED = Enum.forString("percentStacked");
    public static final Enum STACKED = Enum.forString("stacked");
    public static final Enum STANDARD = Enum.forString("standard");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STGrouping> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stgrouping5ec9type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_PERCENT_STACKED = 1;
        static final int INT_STACKED = 3;
        static final int INT_STANDARD = 2;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("percentStacked", 1), new Enum("standard", 2), new Enum("stacked", 3)});

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
