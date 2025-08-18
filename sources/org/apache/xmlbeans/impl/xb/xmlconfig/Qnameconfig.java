package org.apache.xmlbeans.impl.xb.xmlconfig;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLCONFIG.TypeSystemHolder;

public interface Qnameconfig extends XmlObject {
    public static final DocumentFactory<Qnameconfig> Factory;
    public static final SchemaType type;

    String getJavaname();

    QName getName();

    List getTarget();

    boolean isSetJavaname();

    boolean isSetName();

    boolean isSetTarget();

    void setJavaname(String str);

    void setName(QName qName);

    void setTarget(List list);

    void unsetJavaname();

    void unsetName();

    void unsetTarget();

    XmlString xgetJavaname();

    XmlQName xgetName();

    Qnametargetlist xgetTarget();

    void xsetJavaname(XmlString xmlString);

    void xsetName(XmlQName xmlQName);

    void xsetTarget(Qnametargetlist qnametargetlist);

    static {
        DocumentFactory<Qnameconfig> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "qnameconfig463ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
