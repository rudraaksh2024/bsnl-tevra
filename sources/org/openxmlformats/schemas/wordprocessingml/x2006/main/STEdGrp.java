package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STEdGrp extends XmlString {
    public static final Enum ADMINISTRATORS = Enum.forString("administrators");
    public static final Enum CONTRIBUTORS = Enum.forString("contributors");
    public static final Enum CURRENT = Enum.forString("current");
    public static final Enum EDITORS = Enum.forString("editors");
    public static final Enum EVERYONE = Enum.forString("everyone");
    public static final SimpleTypeFactory<STEdGrp> Factory;
    public static final int INT_ADMINISTRATORS = 3;
    public static final int INT_CONTRIBUTORS = 4;
    public static final int INT_CURRENT = 7;
    public static final int INT_EDITORS = 5;
    public static final int INT_EVERYONE = 2;
    public static final int INT_NONE = 1;
    public static final int INT_OWNERS = 6;
    public static final Enum NONE = Enum.forString("none");
    public static final Enum OWNERS = Enum.forString("owners");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STEdGrp> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stedgrp6bdctype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_ADMINISTRATORS = 3;
        static final int INT_CONTRIBUTORS = 4;
        static final int INT_CURRENT = 7;
        static final int INT_EDITORS = 5;
        static final int INT_EVERYONE = 2;
        static final int INT_NONE = 1;
        static final int INT_OWNERS = 6;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("none", 1), new Enum("everyone", 2), new Enum("administrators", 3), new Enum("contributors", 4), new Enum("editors", 5), new Enum("owners", 6), new Enum("current", 7)});

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
