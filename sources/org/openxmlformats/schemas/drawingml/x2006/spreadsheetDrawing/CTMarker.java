package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate;

public interface CTMarker extends XmlObject {
    public static final DocumentFactory<CTMarker> Factory;
    public static final SchemaType type;

    int getCol();

    Object getColOff();

    int getRow();

    Object getRowOff();

    void setCol(int i);

    void setColOff(Object obj);

    void setRow(int i);

    void setRowOff(Object obj);

    STColID xgetCol();

    STCoordinate xgetColOff();

    STRowID xgetRow();

    STCoordinate xgetRowOff();

    void xsetCol(STColID sTColID);

    void xsetColOff(STCoordinate sTCoordinate);

    void xsetRow(STRowID sTRowID);

    void xsetRowOff(STCoordinate sTCoordinate);

    static {
        DocumentFactory<CTMarker> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctmarkeree8etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
