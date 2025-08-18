package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface SimpleContentDocument extends XmlObject {
    public static final DocumentFactory<SimpleContentDocument> Factory;
    public static final SchemaType type;

    SimpleContent addNewSimpleContent();

    SimpleContent getSimpleContent();

    void setSimpleContent(SimpleContent simpleContent);

    static {
        DocumentFactory<SimpleContentDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "simplecontent8acedoctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface SimpleContent extends Annotated {
        public static final ElementFactory<SimpleContent> Factory;
        public static final SchemaType type;

        SimpleExtensionType addNewExtension();

        SimpleRestrictionType addNewRestriction();

        SimpleExtensionType getExtension();

        SimpleRestrictionType getRestriction();

        boolean isSetExtension();

        boolean isSetRestriction();

        void setExtension(SimpleExtensionType simpleExtensionType);

        void setRestriction(SimpleRestrictionType simpleRestrictionType);

        void unsetExtension();

        void unsetRestriction();

        static {
            ElementFactory<SimpleContent> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "simplecontent9a5belemtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }
    }
}
