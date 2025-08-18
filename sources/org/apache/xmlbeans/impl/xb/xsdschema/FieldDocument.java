package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface FieldDocument extends XmlObject {
    public static final DocumentFactory<FieldDocument> Factory;
    public static final SchemaType type;

    Field addNewField();

    Field getField();

    void setField(Field field);

    static {
        DocumentFactory<FieldDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "field3f9bdoctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface Field extends Annotated {
        public static final ElementFactory<Field> Factory;
        public static final SchemaType type;

        String getXpath();

        void setXpath(String str);

        Xpath xgetXpath();

        void xsetXpath(Xpath xpath);

        static {
            ElementFactory<Field> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "field12f5elemtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }

        public interface Xpath extends XmlToken {
            public static final ElementFactory<Xpath> Factory;
            public static final SchemaType type;

            static {
                ElementFactory<Xpath> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "xpath7f90attrtype");
                Factory = elementFactory;
                type = elementFactory.getType();
            }
        }
    }
}
