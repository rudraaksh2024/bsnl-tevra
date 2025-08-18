package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STRubyAlign extends XmlString {
    public static final Enum CENTER = Enum.forString("center");
    public static final Enum DISTRIBUTE_LETTER = Enum.forString("distributeLetter");
    public static final Enum DISTRIBUTE_SPACE = Enum.forString("distributeSpace");
    public static final SimpleTypeFactory<STRubyAlign> Factory;
    public static final int INT_CENTER = 1;
    public static final int INT_DISTRIBUTE_LETTER = 2;
    public static final int INT_DISTRIBUTE_SPACE = 3;
    public static final int INT_LEFT = 4;
    public static final int INT_RIGHT = 5;
    public static final int INT_RIGHT_VERTICAL = 6;
    public static final Enum LEFT = Enum.forString("left");
    public static final Enum RIGHT = Enum.forString("right");
    public static final Enum RIGHT_VERTICAL = Enum.forString("rightVertical");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STRubyAlign> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "strubyalignb1f7type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_CENTER = 1;
        static final int INT_DISTRIBUTE_LETTER = 2;
        static final int INT_DISTRIBUTE_SPACE = 3;
        static final int INT_LEFT = 4;
        static final int INT_RIGHT = 5;
        static final int INT_RIGHT_VERTICAL = 6;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("center", 1), new Enum("distributeLetter", 2), new Enum("distributeSpace", 3), new Enum("left", 4), new Enum("right", 5), new Enum("rightVertical", 6)});

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
