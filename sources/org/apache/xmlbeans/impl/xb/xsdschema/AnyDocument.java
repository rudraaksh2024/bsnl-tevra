package org.apache.xmlbeans.impl.xb.xsdschema;

import java.math.BigInteger;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface AnyDocument extends XmlObject {
    public static final DocumentFactory<AnyDocument> Factory;
    public static final SchemaType type;

    Any addNewAny();

    Any getAny();

    void setAny(Any any);

    static {
        DocumentFactory<AnyDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "anye729doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface Any extends Wildcard {
        public static final ElementFactory<Any> Factory;
        public static final SchemaType type;

        Object getMaxOccurs();

        BigInteger getMinOccurs();

        boolean isSetMaxOccurs();

        boolean isSetMinOccurs();

        void setMaxOccurs(Object obj);

        void setMinOccurs(BigInteger bigInteger);

        void unsetMaxOccurs();

        void unsetMinOccurs();

        AllNNI xgetMaxOccurs();

        XmlNonNegativeInteger xgetMinOccurs();

        void xsetMaxOccurs(AllNNI allNNI);

        void xsetMinOccurs(XmlNonNegativeInteger xmlNonNegativeInteger);

        static {
            ElementFactory<Any> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "anye9d1elemtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }
    }
}
