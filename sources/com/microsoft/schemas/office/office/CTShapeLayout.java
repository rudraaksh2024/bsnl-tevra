package com.microsoft.schemas.office.office;

import com.microsoft.schemas.vml.STExt;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTShapeLayout extends XmlObject {
    public static final DocumentFactory<CTShapeLayout> Factory;
    public static final SchemaType type;

    CTIdMap addNewIdmap();

    CTRegroupTable addNewRegrouptable();

    CTRules addNewRules();

    STExt.Enum getExt();

    CTIdMap getIdmap();

    CTRegroupTable getRegrouptable();

    CTRules getRules();

    boolean isSetExt();

    boolean isSetIdmap();

    boolean isSetRegrouptable();

    boolean isSetRules();

    void setExt(STExt.Enum enumR);

    void setIdmap(CTIdMap cTIdMap);

    void setRegrouptable(CTRegroupTable cTRegroupTable);

    void setRules(CTRules cTRules);

    void unsetExt();

    void unsetIdmap();

    void unsetRegrouptable();

    void unsetRules();

    STExt xgetExt();

    void xsetExt(STExt sTExt);

    static {
        DocumentFactory<CTShapeLayout> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctshapelayoutbda4type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
