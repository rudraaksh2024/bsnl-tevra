package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import kotlinx.coroutines.DebugKt;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STCalcMode extends XmlString {
    public static final Enum AUTO = Enum.forString(DebugKt.DEBUG_PROPERTY_VALUE_AUTO);
    public static final Enum AUTO_NO_TABLE = Enum.forString("autoNoTable");
    public static final SimpleTypeFactory<STCalcMode> Factory;
    public static final int INT_AUTO = 2;
    public static final int INT_AUTO_NO_TABLE = 3;
    public static final int INT_MANUAL = 1;
    public static final Enum MANUAL = Enum.forString("manual");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STCalcMode> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stcalcmode5e71type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_AUTO = 2;
        static final int INT_AUTO_NO_TABLE = 3;
        static final int INT_MANUAL = 1;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("manual", 1), new Enum(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, 2), new Enum("autoNoTable", 3)});

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
