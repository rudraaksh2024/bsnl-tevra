package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTGraphicalObject extends XmlObject {
    public static final DocumentFactory<CTGraphicalObject> Factory;
    public static final SchemaType type;

    CTGraphicalObjectData addNewGraphicData();

    CTGraphicalObjectData getGraphicData();

    void setGraphicData(CTGraphicalObjectData cTGraphicalObjectData);

    static {
        DocumentFactory<CTGraphicalObject> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctgraphicalobject1ce3type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
