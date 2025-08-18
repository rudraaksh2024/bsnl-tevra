package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STTextRotation extends XmlAnySimpleType {
    public static final SimpleTypeFactory<STTextRotation> Factory;
    public static final SchemaType type;

    Object getObjectValue();

    SchemaType instanceType();

    void setObjectValue(Object obj);

    static {
        SimpleTypeFactory<STTextRotation> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sttextrotationec64type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public interface Member extends XmlNonNegativeInteger {
        public static final ElementFactory<Member> Factory;
        public static final SchemaType type;

        int getIntValue();

        void setIntValue(int i);

        static {
            ElementFactory<Member> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "anon9d89type");
            Factory = elementFactory;
            type = elementFactory.getType();
        }
    }

    public interface Member2 extends XmlNonNegativeInteger {
        public static final ElementFactory<Member2> Factory;
        public static final SchemaType type;

        static {
            ElementFactory<Member2> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "anon1c0atype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }
    }
}
