package org.apache.xmlbeans.impl.xb.xmlconfig;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLCONFIG.TypeSystemHolder;

public interface Usertypeconfig extends XmlObject {
    public static final DocumentFactory<Usertypeconfig> Factory;
    public static final SchemaType type;

    String getJavaname();

    QName getName();

    String getStaticHandler();

    boolean isSetJavaname();

    boolean isSetName();

    void setJavaname(String str);

    void setName(QName qName);

    void setStaticHandler(String str);

    void unsetJavaname();

    void unsetName();

    XmlString xgetJavaname();

    XmlQName xgetName();

    XmlString xgetStaticHandler();

    void xsetJavaname(XmlString xmlString);

    void xsetName(XmlQName xmlQName);

    void xsetStaticHandler(XmlString xmlString);

    static {
        DocumentFactory<Usertypeconfig> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "usertypeconfig7bbatype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
