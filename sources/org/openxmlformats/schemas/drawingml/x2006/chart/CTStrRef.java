package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTStrRef extends XmlObject {
    public static final DocumentFactory<CTStrRef> Factory;
    public static final SchemaType type;

    CTExtensionList addNewExtLst();

    CTStrData addNewStrCache();

    CTExtensionList getExtLst();

    String getF();

    CTStrData getStrCache();

    boolean isSetExtLst();

    boolean isSetStrCache();

    void setExtLst(CTExtensionList cTExtensionList);

    void setF(String str);

    void setStrCache(CTStrData cTStrData);

    void unsetExtLst();

    void unsetStrCache();

    XmlString xgetF();

    void xsetF(XmlString xmlString);

    static {
        DocumentFactory<CTStrRef> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctstrref5d1atype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
