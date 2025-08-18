package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface ImportDocument extends XmlObject {
    public static final DocumentFactory<ImportDocument> Factory;
    public static final SchemaType type;

    Import addNewImport();

    Import getImport();

    void setImport(Import importR);

    static {
        DocumentFactory<ImportDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "import99fedoctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface Import extends Annotated {
        public static final ElementFactory<Import> Factory;
        public static final SchemaType type;

        String getNamespace();

        String getSchemaLocation();

        boolean isSetNamespace();

        boolean isSetSchemaLocation();

        void setNamespace(String str);

        void setSchemaLocation(String str);

        void unsetNamespace();

        void unsetSchemaLocation();

        XmlAnyURI xgetNamespace();

        XmlAnyURI xgetSchemaLocation();

        void xsetNamespace(XmlAnyURI xmlAnyURI);

        void xsetSchemaLocation(XmlAnyURI xmlAnyURI);

        static {
            ElementFactory<Import> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "importe2ffelemtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }
    }
}
