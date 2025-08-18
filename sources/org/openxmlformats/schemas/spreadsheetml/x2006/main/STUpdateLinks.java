package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STUpdateLinks extends XmlString {
    public static final Enum ALWAYS = Enum.forString("always");
    public static final SimpleTypeFactory<STUpdateLinks> Factory;
    public static final int INT_ALWAYS = 3;
    public static final int INT_NEVER = 2;
    public static final int INT_USER_SET = 1;
    public static final Enum NEVER = Enum.forString("never");
    public static final Enum USER_SET = Enum.forString("userSet");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STUpdateLinks> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stupdatelinksfb3ftype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_ALWAYS = 3;
        static final int INT_NEVER = 2;
        static final int INT_USER_SET = 1;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("userSet", 1), new Enum("never", 2), new Enum("always", 3)});

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
