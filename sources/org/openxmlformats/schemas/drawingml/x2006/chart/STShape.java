package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STShape extends XmlString {
    public static final Enum BOX = Enum.forString("box");
    public static final Enum CONE = Enum.forString("cone");
    public static final Enum CONE_TO_MAX = Enum.forString("coneToMax");
    public static final Enum CYLINDER = Enum.forString("cylinder");
    public static final SimpleTypeFactory<STShape> Factory;
    public static final int INT_BOX = 3;
    public static final int INT_CONE = 1;
    public static final int INT_CONE_TO_MAX = 2;
    public static final int INT_CYLINDER = 4;
    public static final int INT_PYRAMID = 5;
    public static final int INT_PYRAMID_TO_MAX = 6;
    public static final Enum PYRAMID = Enum.forString("pyramid");
    public static final Enum PYRAMID_TO_MAX = Enum.forString("pyramidToMax");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STShape> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stshapecdf5type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_BOX = 3;
        static final int INT_CONE = 1;
        static final int INT_CONE_TO_MAX = 2;
        static final int INT_CYLINDER = 4;
        static final int INT_PYRAMID = 5;
        static final int INT_PYRAMID_TO_MAX = 6;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("cone", 1), new Enum("coneToMax", 2), new Enum("box", 3), new Enum("cylinder", 4), new Enum("pyramid", 5), new Enum("pyramidToMax", 6)});

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
