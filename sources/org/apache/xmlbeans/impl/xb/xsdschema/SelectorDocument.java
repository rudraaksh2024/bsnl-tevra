package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface SelectorDocument extends XmlObject {
    public static final DocumentFactory<SelectorDocument> Factory;
    public static final SchemaType type;

    Selector addNewSelector();

    Selector getSelector();

    void setSelector(Selector selector);

    static {
        DocumentFactory<SelectorDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "selectorcb44doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface Selector extends Annotated {
        public static final ElementFactory<Selector> Factory;
        public static final SchemaType type;

        String getXpath();

        void setXpath(String str);

        Xpath xgetXpath();

        void xsetXpath(Xpath xpath);

        static {
            ElementFactory<Selector> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "selector233felemtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }

        public interface Xpath extends XmlToken {
            public static final ElementFactory<Xpath> Factory;
            public static final SchemaType type;

            static {
                ElementFactory<Xpath> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "xpath6f9aattrtype");
                Factory = elementFactory;
                type = elementFactory.getType();
            }
        }
    }
}
