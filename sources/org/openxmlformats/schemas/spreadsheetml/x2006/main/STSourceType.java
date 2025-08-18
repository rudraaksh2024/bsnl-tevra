package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STSourceType extends XmlString {
    public static final Enum CONSOLIDATION = Enum.forString("consolidation");
    public static final Enum EXTERNAL = Enum.forString("external");
    public static final SimpleTypeFactory<STSourceType> Factory;
    public static final int INT_CONSOLIDATION = 3;
    public static final int INT_EXTERNAL = 2;
    public static final int INT_SCENARIO = 4;
    public static final int INT_WORKSHEET = 1;
    public static final Enum SCENARIO = Enum.forString("scenario");
    public static final Enum WORKSHEET = Enum.forString("worksheet");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STSourceType> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stsourcetype074etype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_CONSOLIDATION = 3;
        static final int INT_EXTERNAL = 2;
        static final int INT_SCENARIO = 4;
        static final int INT_WORKSHEET = 1;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("worksheet", 1), new Enum("external", 2), new Enum("consolidation", 3), new Enum("scenario", 4)});

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
