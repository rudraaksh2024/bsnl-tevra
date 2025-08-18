package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTCustomXmlPr extends XmlObject {
    public static final DocumentFactory<CTCustomXmlPr> Factory;
    public static final SchemaType type;

    CTAttr addNewAttr();

    CTString addNewPlaceholder();

    CTAttr getAttrArray(int i);

    CTAttr[] getAttrArray();

    List<CTAttr> getAttrList();

    CTString getPlaceholder();

    CTAttr insertNewAttr(int i);

    boolean isSetPlaceholder();

    void removeAttr(int i);

    void setAttrArray(int i, CTAttr cTAttr);

    void setAttrArray(CTAttr[] cTAttrArr);

    void setPlaceholder(CTString cTString);

    int sizeOfAttrArray();

    void unsetPlaceholder();

    static {
        DocumentFactory<CTCustomXmlPr> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcustomxmlpr4b8atype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
