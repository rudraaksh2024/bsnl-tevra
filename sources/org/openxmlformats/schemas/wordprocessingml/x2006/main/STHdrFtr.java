package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STHdrFtr extends XmlString {
    public static final Enum DEFAULT = Enum.forString("default");
    public static final Enum EVEN = Enum.forString("even");
    public static final Enum FIRST = Enum.forString("first");
    public static final SimpleTypeFactory<STHdrFtr> Factory;
    public static final int INT_DEFAULT = 2;
    public static final int INT_EVEN = 1;
    public static final int INT_FIRST = 3;
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STHdrFtr> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sthdrftr30catype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_DEFAULT = 2;
        static final int INT_EVEN = 1;
        static final int INT_FIRST = 3;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("even", 1), new Enum("default", 2), new Enum("first", 3)});

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
