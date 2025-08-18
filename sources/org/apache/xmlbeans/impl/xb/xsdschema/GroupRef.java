package org.apache.xmlbeans.impl.xb.xsdschema;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface GroupRef extends RealGroup {
    public static final DocumentFactory<GroupRef> Factory;
    public static final SchemaType type;

    QName getRef();

    boolean isSetRef();

    void setRef(QName qName);

    void unsetRef();

    XmlQName xgetRef();

    void xsetRef(XmlQName xmlQName);

    static {
        DocumentFactory<GroupRef> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "groupref303ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
