package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTNonVisualGraphicFrameProperties extends XmlObject {
    public static final DocumentFactory<CTNonVisualGraphicFrameProperties> Factory;
    public static final SchemaType type;

    CTOfficeArtExtensionList addNewExtLst();

    CTGraphicalObjectFrameLocking addNewGraphicFrameLocks();

    CTOfficeArtExtensionList getExtLst();

    CTGraphicalObjectFrameLocking getGraphicFrameLocks();

    boolean isSetExtLst();

    boolean isSetGraphicFrameLocks();

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setGraphicFrameLocks(CTGraphicalObjectFrameLocking cTGraphicalObjectFrameLocking);

    void unsetExtLst();

    void unsetGraphicFrameLocks();

    static {
        DocumentFactory<CTNonVisualGraphicFrameProperties> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctnonvisualgraphicframeproperties43b6type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
