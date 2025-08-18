package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface IncludeDocument extends XmlObject {
    public static final DocumentFactory<IncludeDocument> Factory;
    public static final SchemaType type;

    Include addNewInclude();

    Include getInclude();

    void setInclude(Include include);

    static {
        DocumentFactory<IncludeDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "includeaf6ddoctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface Include extends Annotated {
        public static final ElementFactory<Include> Factory;
        public static final SchemaType type;

        String getSchemaLocation();

        void setSchemaLocation(String str);

        XmlAnyURI xgetSchemaLocation();

        void xsetSchemaLocation(XmlAnyURI xmlAnyURI);

        static {
            ElementFactory<Include> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "include59d9elemtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }
    }
}
