package org.apache.xmlbeans.impl.xb.xmlconfig;

import java.util.List;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLCONFIG.TypeSystemHolder;

public interface Nsconfig extends XmlObject {
    public static final DocumentFactory<Nsconfig> Factory;
    public static final SchemaType type;

    String getPackage();

    String getPrefix();

    String getSuffix();

    Object getUri();

    List getUriprefix();

    boolean isSetPackage();

    boolean isSetPrefix();

    boolean isSetSuffix();

    boolean isSetUri();

    boolean isSetUriprefix();

    void setPackage(String str);

    void setPrefix(String str);

    void setSuffix(String str);

    void setUri(Object obj);

    void setUriprefix(List list);

    void unsetPackage();

    void unsetPrefix();

    void unsetSuffix();

    void unsetUri();

    void unsetUriprefix();

    XmlString xgetPackage();

    XmlString xgetPrefix();

    XmlString xgetSuffix();

    NamespaceList xgetUri();

    NamespacePrefixList xgetUriprefix();

    void xsetPackage(XmlString xmlString);

    void xsetPrefix(XmlString xmlString);

    void xsetSuffix(XmlString xmlString);

    void xsetUri(NamespaceList namespaceList);

    void xsetUriprefix(NamespacePrefixList namespacePrefixList);

    static {
        DocumentFactory<Nsconfig> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "nsconfigaebatype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
