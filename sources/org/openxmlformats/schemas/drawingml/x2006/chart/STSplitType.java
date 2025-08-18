package org.openxmlformats.schemas.drawingml.x2006.chart;

import kotlinx.coroutines.DebugKt;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STSplitType extends XmlString {
    public static final Enum AUTO = Enum.forString(DebugKt.DEBUG_PROPERTY_VALUE_AUTO);
    public static final Enum CUST = Enum.forString("cust");
    public static final SimpleTypeFactory<STSplitType> Factory;
    public static final int INT_AUTO = 1;
    public static final int INT_CUST = 2;
    public static final int INT_PERCENT = 3;
    public static final int INT_POS = 4;
    public static final int INT_VAL = 5;
    public static final Enum PERCENT = Enum.forString("percent");
    public static final Enum POS = Enum.forString("pos");
    public static final Enum VAL = Enum.forString("val");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STSplitType> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stsplittype6842type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_AUTO = 1;
        static final int INT_CUST = 2;
        static final int INT_PERCENT = 3;
        static final int INT_POS = 4;
        static final int INT_VAL = 5;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, 1), new Enum("cust", 2), new Enum("percent", 3), new Enum("pos", 4), new Enum("val", 5)});

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
