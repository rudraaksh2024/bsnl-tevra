package org.apache.xmlbeans.impl.xb.ltgfmt;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLTOOLS.TypeSystemHolder;

public interface Code extends XmlObject {
    public static final DocumentFactory<Code> Factory;
    public static final SchemaType type;

    String getID();

    boolean isSetID();

    void setID(String str);

    void unsetID();

    XmlToken xgetID();

    void xsetID(XmlToken xmlToken);

    static {
        DocumentFactory<Code> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "codef72ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
