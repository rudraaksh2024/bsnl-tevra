package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.poi.ss.util.CellUtil;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STSheetState extends XmlString {
    public static final SimpleTypeFactory<STSheetState> Factory;
    public static final Enum HIDDEN = Enum.forString(CellUtil.HIDDEN);
    public static final int INT_HIDDEN = 2;
    public static final int INT_VERY_HIDDEN = 3;
    public static final int INT_VISIBLE = 1;
    public static final Enum VERY_HIDDEN = Enum.forString("veryHidden");
    public static final Enum VISIBLE = Enum.forString("visible");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STSheetState> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stsheetstate158btype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_HIDDEN = 2;
        static final int INT_VERY_HIDDEN = 3;
        static final int INT_VISIBLE = 1;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("visible", 1), new Enum(CellUtil.HIDDEN, 2), new Enum("veryHidden", 3)});

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
