package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STBarGrouping extends XmlString {
    public static final Enum CLUSTERED = Enum.forString("clustered");
    public static final SimpleTypeFactory<STBarGrouping> Factory;
    public static final int INT_CLUSTERED = 2;
    public static final int INT_PERCENT_STACKED = 1;
    public static final int INT_STACKED = 4;
    public static final int INT_STANDARD = 3;
    public static final Enum PERCENT_STACKED = Enum.forString("percentStacked");
    public static final Enum STACKED = Enum.forString("stacked");
    public static final Enum STANDARD = Enum.forString("standard");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STBarGrouping> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stbargrouping8400type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_CLUSTERED = 2;
        static final int INT_PERCENT_STACKED = 1;
        static final int INT_STACKED = 4;
        static final int INT_STANDARD = 3;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("percentStacked", 1), new Enum("clustered", 2), new Enum("standard", 3), new Enum("stacked", 4)});

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
