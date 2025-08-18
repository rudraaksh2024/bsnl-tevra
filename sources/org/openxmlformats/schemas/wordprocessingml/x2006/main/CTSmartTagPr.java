package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTSmartTagPr extends XmlObject {
    public static final DocumentFactory<CTSmartTagPr> Factory;
    public static final SchemaType type;

    CTAttr addNewAttr();

    CTAttr getAttrArray(int i);

    CTAttr[] getAttrArray();

    List<CTAttr> getAttrList();

    CTAttr insertNewAttr(int i);

    void removeAttr(int i);

    void setAttrArray(int i, CTAttr cTAttr);

    void setAttrArray(CTAttr[] cTAttrArr);

    int sizeOfAttrArray();

    static {
        DocumentFactory<CTSmartTagPr> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsmarttagprf715type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
