package org.w3.x2000.x09.xmldsig;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface TransformType extends XmlObject {
    public static final DocumentFactory<TransformType> Factory;
    public static final SchemaType type;

    XmlString addNewXPath();

    void addXPath(String str);

    String getAlgorithm();

    String getXPathArray(int i);

    String[] getXPathArray();

    List<String> getXPathList();

    XmlString insertNewXPath(int i);

    void insertXPath(int i, String str);

    void removeXPath(int i);

    void setAlgorithm(String str);

    void setXPathArray(int i, String str);

    void setXPathArray(String[] strArr);

    int sizeOfXPathArray();

    XmlAnyURI xgetAlgorithm();

    XmlString xgetXPathArray(int i);

    XmlString[] xgetXPathArray();

    List<XmlString> xgetXPathList();

    void xsetAlgorithm(XmlAnyURI xmlAnyURI);

    void xsetXPathArray(int i, XmlString xmlString);

    void xsetXPathArray(XmlString[] xmlStringArr);

    static {
        DocumentFactory<TransformType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "transformtype550btype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
