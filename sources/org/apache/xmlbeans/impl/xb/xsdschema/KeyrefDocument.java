package org.apache.xmlbeans.impl.xb.xsdschema;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface KeyrefDocument extends XmlObject {
    public static final DocumentFactory<KeyrefDocument> Factory;
    public static final SchemaType type;

    Keyref addNewKeyref();

    Keyref getKeyref();

    void setKeyref(Keyref keyref);

    static {
        DocumentFactory<KeyrefDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "keyref45afdoctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface Keyref extends Keybase {
        public static final ElementFactory<Keyref> Factory;
        public static final SchemaType type;

        QName getRefer();

        void setRefer(QName qName);

        XmlQName xgetRefer();

        void xsetRefer(XmlQName xmlQName);

        static {
            ElementFactory<Keyref> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "keyref7a1felemtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }
    }
}
