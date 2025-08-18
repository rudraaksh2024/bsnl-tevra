package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface MapInfoDocument extends XmlObject {
    public static final DocumentFactory<MapInfoDocument> Factory;
    public static final SchemaType type;

    CTMapInfo addNewMapInfo();

    CTMapInfo getMapInfo();

    void setMapInfo(CTMapInfo cTMapInfo);

    static {
        DocumentFactory<MapInfoDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "mapinfo5715doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
