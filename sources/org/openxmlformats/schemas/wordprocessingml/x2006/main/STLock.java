package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STLock extends XmlString {
    public static final Enum CONTENT_LOCKED = Enum.forString("contentLocked");
    public static final SimpleTypeFactory<STLock> Factory;
    public static final int INT_CONTENT_LOCKED = 2;
    public static final int INT_SDT_CONTENT_LOCKED = 4;
    public static final int INT_SDT_LOCKED = 1;
    public static final int INT_UNLOCKED = 3;
    public static final Enum SDT_CONTENT_LOCKED = Enum.forString("sdtContentLocked");
    public static final Enum SDT_LOCKED = Enum.forString("sdtLocked");
    public static final Enum UNLOCKED = Enum.forString("unlocked");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STLock> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stlocke60dtype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_CONTENT_LOCKED = 2;
        static final int INT_SDT_CONTENT_LOCKED = 4;
        static final int INT_SDT_LOCKED = 1;
        static final int INT_UNLOCKED = 3;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("sdtLocked", 1), new Enum("contentLocked", 2), new Enum("unlocked", 3), new Enum("sdtContentLocked", 4)});

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
