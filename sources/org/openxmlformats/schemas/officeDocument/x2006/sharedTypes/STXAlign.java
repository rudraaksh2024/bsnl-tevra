package org.openxmlformats.schemas.officeDocument.x2006.sharedTypes;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STXAlign extends XmlString {
    public static final Enum CENTER = Enum.forString("center");
    public static final SimpleTypeFactory<STXAlign> Factory;
    public static final Enum INSIDE = Enum.forString("inside");
    public static final int INT_CENTER = 2;
    public static final int INT_INSIDE = 4;
    public static final int INT_LEFT = 1;
    public static final int INT_OUTSIDE = 5;
    public static final int INT_RIGHT = 3;
    public static final Enum LEFT = Enum.forString("left");
    public static final Enum OUTSIDE = Enum.forString("outside");
    public static final Enum RIGHT = Enum.forString("right");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STXAlign> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stxalign8127type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_CENTER = 2;
        static final int INT_INSIDE = 4;
        static final int INT_LEFT = 1;
        static final int INT_OUTSIDE = 5;
        static final int INT_RIGHT = 3;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("left", 1), new Enum("center", 2), new Enum("right", 3), new Enum("inside", 4), new Enum("outside", 5)});

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
