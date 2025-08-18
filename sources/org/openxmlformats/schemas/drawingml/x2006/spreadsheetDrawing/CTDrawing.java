package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTDrawing extends XmlObject {
    public static final DocumentFactory<CTDrawing> Factory;
    public static final SchemaType type;

    CTAbsoluteAnchor addNewAbsoluteAnchor();

    CTOneCellAnchor addNewOneCellAnchor();

    CTTwoCellAnchor addNewTwoCellAnchor();

    CTAbsoluteAnchor getAbsoluteAnchorArray(int i);

    CTAbsoluteAnchor[] getAbsoluteAnchorArray();

    List<CTAbsoluteAnchor> getAbsoluteAnchorList();

    CTOneCellAnchor getOneCellAnchorArray(int i);

    CTOneCellAnchor[] getOneCellAnchorArray();

    List<CTOneCellAnchor> getOneCellAnchorList();

    CTTwoCellAnchor getTwoCellAnchorArray(int i);

    CTTwoCellAnchor[] getTwoCellAnchorArray();

    List<CTTwoCellAnchor> getTwoCellAnchorList();

    CTAbsoluteAnchor insertNewAbsoluteAnchor(int i);

    CTOneCellAnchor insertNewOneCellAnchor(int i);

    CTTwoCellAnchor insertNewTwoCellAnchor(int i);

    void removeAbsoluteAnchor(int i);

    void removeOneCellAnchor(int i);

    void removeTwoCellAnchor(int i);

    void setAbsoluteAnchorArray(int i, CTAbsoluteAnchor cTAbsoluteAnchor);

    void setAbsoluteAnchorArray(CTAbsoluteAnchor[] cTAbsoluteAnchorArr);

    void setOneCellAnchorArray(int i, CTOneCellAnchor cTOneCellAnchor);

    void setOneCellAnchorArray(CTOneCellAnchor[] cTOneCellAnchorArr);

    void setTwoCellAnchorArray(int i, CTTwoCellAnchor cTTwoCellAnchor);

    void setTwoCellAnchorArray(CTTwoCellAnchor[] cTTwoCellAnchorArr);

    int sizeOfAbsoluteAnchorArray();

    int sizeOfOneCellAnchorArray();

    int sizeOfTwoCellAnchorArray();

    static {
        DocumentFactory<CTDrawing> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdrawing2748type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
