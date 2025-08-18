package org.apache.xmlbeans.impl.xb.xmlschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLLANG.TypeSystemHolder;

public interface BaseAttribute extends XmlObject {
    public static final DocumentFactory<BaseAttribute> Factory;
    public static final SchemaType type;

    String getBase();

    boolean isSetBase();

    void setBase(String str);

    void unsetBase();

    XmlAnyURI xgetBase();

    void xsetBase(XmlAnyURI xmlAnyURI);

    static {
        DocumentFactory<BaseAttribute> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "basece23attrtypetype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
