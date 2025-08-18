package com.microsoft.schemas.office.word;

import com.microsoft.schemas.office.word.STWrapType;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTWrap extends XmlObject {
    public static final DocumentFactory<CTWrap> Factory;
    public static final SchemaType type;

    STHorizontalAnchor$Enum getAnchorx();

    STVerticalAnchor$Enum getAnchory();

    STWrapSide$Enum getSide();

    STWrapType.Enum getType();

    boolean isSetAnchorx();

    boolean isSetAnchory();

    boolean isSetSide();

    boolean isSetType();

    void setAnchorx(STHorizontalAnchor$Enum sTHorizontalAnchor$Enum);

    void setAnchory(STVerticalAnchor$Enum sTVerticalAnchor$Enum);

    void setSide(STWrapSide$Enum sTWrapSide$Enum);

    void setType(STWrapType.Enum enumR);

    void unsetAnchorx();

    void unsetAnchory();

    void unsetSide();

    void unsetType();

    STHorizontalAnchor xgetAnchorx();

    STVerticalAnchor xgetAnchory();

    STWrapSide xgetSide();

    STWrapType xgetType();

    void xsetAnchorx(STHorizontalAnchor sTHorizontalAnchor);

    void xsetAnchory(STVerticalAnchor sTVerticalAnchor);

    void xsetSide(STWrapSide sTWrapSide);

    void xsetType(STWrapType sTWrapType);

    static {
        DocumentFactory<CTWrap> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctwrapbc3btype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
