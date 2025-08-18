package org.apache.xmlbeans.impl.xb.xsdschema;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface ListDocument extends XmlObject {
    public static final DocumentFactory<ListDocument> Factory;
    public static final SchemaType type;

    List addNewList();

    List getList();

    void setList(List list);

    static {
        DocumentFactory<ListDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "listcde5doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface List extends Annotated {
        public static final ElementFactory<List> Factory;
        public static final SchemaType type;

        LocalSimpleType addNewSimpleType();

        QName getItemType();

        LocalSimpleType getSimpleType();

        boolean isSetItemType();

        boolean isSetSimpleType();

        void setItemType(QName qName);

        void setSimpleType(LocalSimpleType localSimpleType);

        void unsetItemType();

        void unsetSimpleType();

        XmlQName xgetItemType();

        void xsetItemType(XmlQName xmlQName);

        static {
            ElementFactory<List> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "list391felemtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }
    }
}
