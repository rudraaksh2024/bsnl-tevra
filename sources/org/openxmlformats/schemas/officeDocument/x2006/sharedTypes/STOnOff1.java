package org.openxmlformats.schemas.officeDocument.x2006.sharedTypes;

import kotlinx.coroutines.DebugKt;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STOnOff1 extends XmlString {
    public static final SimpleTypeFactory<STOnOff1> Factory;
    public static final int INT_OFF = 2;
    public static final int INT_ON = 1;
    public static final Enum OFF = Enum.forString(DebugKt.DEBUG_PROPERTY_VALUE_OFF);
    public static final Enum ON = Enum.forString(DebugKt.DEBUG_PROPERTY_VALUE_ON);
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STOnOff1> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stonoff18ddbtype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_OFF = 2;
        static final int INT_ON = 1;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum(DebugKt.DEBUG_PROPERTY_VALUE_ON, 1), new Enum(DebugKt.DEBUG_PROPERTY_VALUE_OFF, 2)});

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
