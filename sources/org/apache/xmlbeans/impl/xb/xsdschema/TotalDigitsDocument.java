package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface TotalDigitsDocument extends XmlObject {
    public static final DocumentFactory<TotalDigitsDocument> Factory;
    public static final SchemaType type;

    TotalDigits addNewTotalDigits();

    TotalDigits getTotalDigits();

    void setTotalDigits(TotalDigits totalDigits);

    static {
        DocumentFactory<TotalDigitsDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "totaldigits4a8bdoctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface TotalDigits extends NumFacet {
        public static final ElementFactory<TotalDigits> Factory;
        public static final SchemaType type;

        static {
            ElementFactory<TotalDigits> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "totaldigits2615elemtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }
    }
}
