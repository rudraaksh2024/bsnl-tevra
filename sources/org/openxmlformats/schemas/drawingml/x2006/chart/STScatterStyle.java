package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STScatterStyle extends XmlString {
    public static final SimpleTypeFactory<STScatterStyle> Factory;
    public static final int INT_LINE = 2;
    public static final int INT_LINE_MARKER = 3;
    public static final int INT_MARKER = 4;
    public static final int INT_NONE = 1;
    public static final int INT_SMOOTH = 5;
    public static final int INT_SMOOTH_MARKER = 6;
    public static final Enum LINE = Enum.forString("line");
    public static final Enum LINE_MARKER = Enum.forString("lineMarker");
    public static final Enum MARKER = Enum.forString("marker");
    public static final Enum NONE = Enum.forString("none");
    public static final Enum SMOOTH = Enum.forString("smooth");
    public static final Enum SMOOTH_MARKER = Enum.forString("smoothMarker");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STScatterStyle> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stscatterstyle9eb9type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_LINE = 2;
        static final int INT_LINE_MARKER = 3;
        static final int INT_MARKER = 4;
        static final int INT_NONE = 1;
        static final int INT_SMOOTH = 5;
        static final int INT_SMOOTH_MARKER = 6;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("none", 1), new Enum("line", 2), new Enum("lineMarker", 3), new Enum("marker", 4), new Enum("smooth", 5), new Enum("smoothMarker", 6)});

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
