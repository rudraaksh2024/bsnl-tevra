package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface DocumentationDocument extends XmlObject {
    public static final DocumentFactory<DocumentationDocument> Factory;
    public static final SchemaType type;

    Documentation addNewDocumentation();

    Documentation getDocumentation();

    void setDocumentation(Documentation documentation);

    static {
        DocumentFactory<DocumentationDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "documentation6cdbdoctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface Documentation extends XmlObject {
        public static final ElementFactory<Documentation> Factory;
        public static final SchemaType type;

        String getLang();

        String getSource();

        boolean isSetLang();

        boolean isSetSource();

        void setLang(String str);

        void setSource(String str);

        void unsetLang();

        void unsetSource();

        LangAttribute.Lang xgetLang();

        XmlAnyURI xgetSource();

        void xsetLang(LangAttribute.Lang lang);

        void xsetSource(XmlAnyURI xmlAnyURI);

        static {
            ElementFactory<Documentation> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "documentationa475elemtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }
    }
}
