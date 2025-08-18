package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.presentationml.x2006.main.STSlideSizeType;

public interface CTSlideSize extends XmlObject {
    public static final DocumentFactory<CTSlideSize> Factory;
    public static final SchemaType type;

    int getCx();

    int getCy();

    STSlideSizeType.Enum getType();

    boolean isSetType();

    void setCx(int i);

    void setCy(int i);

    void setType(STSlideSizeType.Enum enumR);

    void unsetType();

    STSlideSizeCoordinate xgetCx();

    STSlideSizeCoordinate xgetCy();

    STSlideSizeType xgetType();

    void xsetCx(STSlideSizeCoordinate sTSlideSizeCoordinate);

    void xsetCy(STSlideSizeCoordinate sTSlideSizeCoordinate);

    void xsetType(STSlideSizeType sTSlideSizeType);

    static {
        DocumentFactory<CTSlideSize> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctslidesizeb0fdtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
