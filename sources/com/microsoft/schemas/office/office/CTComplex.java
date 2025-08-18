package com.microsoft.schemas.office.office;

import com.microsoft.schemas.vml.STExt;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTComplex extends XmlObject {
    public static final DocumentFactory<CTComplex> Factory;
    public static final SchemaType type;

    STExt.Enum getExt();

    boolean isSetExt();

    void setExt(STExt.Enum enumR);

    void unsetExt();

    STExt xgetExt();

    void xsetExt(STExt sTExt);

    static {
        DocumentFactory<CTComplex> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcomplexd4a9type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
