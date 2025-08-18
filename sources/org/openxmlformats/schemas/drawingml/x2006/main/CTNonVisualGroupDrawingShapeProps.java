package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTNonVisualGroupDrawingShapeProps extends XmlObject {
    public static final DocumentFactory<CTNonVisualGroupDrawingShapeProps> Factory;
    public static final SchemaType type;

    CTOfficeArtExtensionList addNewExtLst();

    CTGroupLocking addNewGrpSpLocks();

    CTOfficeArtExtensionList getExtLst();

    CTGroupLocking getGrpSpLocks();

    boolean isSetExtLst();

    boolean isSetGrpSpLocks();

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setGrpSpLocks(CTGroupLocking cTGroupLocking);

    void unsetExtLst();

    void unsetGrpSpLocks();

    static {
        DocumentFactory<CTNonVisualGroupDrawingShapeProps> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctnonvisualgroupdrawingshapeprops610ctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
