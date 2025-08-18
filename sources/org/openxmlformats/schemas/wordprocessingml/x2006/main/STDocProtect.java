package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STDocProtect extends XmlString {
    public static final Enum COMMENTS = Enum.forString("comments");
    public static final Enum FORMS = Enum.forString("forms");
    public static final SimpleTypeFactory<STDocProtect> Factory;
    public static final int INT_COMMENTS = 3;
    public static final int INT_FORMS = 5;
    public static final int INT_NONE = 1;
    public static final int INT_READ_ONLY = 2;
    public static final int INT_TRACKED_CHANGES = 4;
    public static final Enum NONE = Enum.forString("none");
    public static final Enum READ_ONLY = Enum.forString("readOnly");
    public static final Enum TRACKED_CHANGES = Enum.forString("trackedChanges");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STDocProtect> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stdocprotect5801type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_COMMENTS = 3;
        static final int INT_FORMS = 5;
        static final int INT_NONE = 1;
        static final int INT_READ_ONLY = 2;
        static final int INT_TRACKED_CHANGES = 4;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("none", 1), new Enum("readOnly", 2), new Enum("comments", 3), new Enum("trackedChanges", 4), new Enum("forms", 5)});

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
