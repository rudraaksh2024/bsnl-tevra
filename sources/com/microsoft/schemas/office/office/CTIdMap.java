package com.microsoft.schemas.office.office;

import com.microsoft.schemas.vml.STExt;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTIdMap extends XmlObject {
    public static final DocumentFactory<CTIdMap> Factory;
    public static final SchemaType type;

    String getData();

    STExt.Enum getExt();

    boolean isSetData();

    boolean isSetExt();

    void setData(String str);

    void setExt(STExt.Enum enumR);

    void unsetData();

    void unsetExt();

    XmlString xgetData();

    STExt xgetExt();

    void xsetData(XmlString xmlString);

    void xsetExt(STExt sTExt);

    static {
        DocumentFactory<CTIdMap> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctidmap63fatype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
