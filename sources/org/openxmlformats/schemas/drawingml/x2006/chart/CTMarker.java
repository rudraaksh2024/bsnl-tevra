package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

public interface CTMarker extends XmlObject {
    public static final DocumentFactory<CTMarker> Factory;
    public static final SchemaType type;

    CTExtensionList addNewExtLst();

    CTMarkerSize addNewSize();

    CTShapeProperties addNewSpPr();

    CTMarkerStyle addNewSymbol();

    CTExtensionList getExtLst();

    CTMarkerSize getSize();

    CTShapeProperties getSpPr();

    CTMarkerStyle getSymbol();

    boolean isSetExtLst();

    boolean isSetSize();

    boolean isSetSpPr();

    boolean isSetSymbol();

    void setExtLst(CTExtensionList cTExtensionList);

    void setSize(CTMarkerSize cTMarkerSize);

    void setSpPr(CTShapeProperties cTShapeProperties);

    void setSymbol(CTMarkerStyle cTMarkerStyle);

    void unsetExtLst();

    void unsetSize();

    void unsetSpPr();

    void unsetSymbol();

    static {
        DocumentFactory<CTMarker> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctmarkera682type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
