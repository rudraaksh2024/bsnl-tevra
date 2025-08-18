package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTStretchInfoProperties extends XmlObject {
    public static final DocumentFactory<CTStretchInfoProperties> Factory;
    public static final SchemaType type;

    CTRelativeRect addNewFillRect();

    CTRelativeRect getFillRect();

    boolean isSetFillRect();

    void setFillRect(CTRelativeRect cTRelativeRect);

    void unsetFillRect();

    static {
        DocumentFactory<CTStretchInfoProperties> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctstretchinfopropertiesde57type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
