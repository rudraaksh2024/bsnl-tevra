package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STCellFormulaType extends XmlString {
    public static final Enum ARRAY = Enum.forString("array");
    public static final Enum DATA_TABLE = Enum.forString("dataTable");
    public static final SimpleTypeFactory<STCellFormulaType> Factory;
    public static final int INT_ARRAY = 2;
    public static final int INT_DATA_TABLE = 3;
    public static final int INT_NORMAL = 1;
    public static final int INT_SHARED = 4;
    public static final Enum NORMAL = Enum.forString("normal");
    public static final Enum SHARED = Enum.forString("shared");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STCellFormulaType> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stcellformulatypee2cdtype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_ARRAY = 2;
        static final int INT_DATA_TABLE = 3;
        static final int INT_NORMAL = 1;
        static final int INT_SHARED = 4;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("normal", 1), new Enum("array", 2), new Enum("dataTable", 3), new Enum("shared", 4)});

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
