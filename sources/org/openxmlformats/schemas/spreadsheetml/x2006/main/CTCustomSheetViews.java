package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTCustomSheetViews extends XmlObject {
    public static final DocumentFactory<CTCustomSheetViews> Factory;
    public static final SchemaType type;

    CTCustomSheetView addNewCustomSheetView();

    CTCustomSheetView getCustomSheetViewArray(int i);

    CTCustomSheetView[] getCustomSheetViewArray();

    List<CTCustomSheetView> getCustomSheetViewList();

    CTCustomSheetView insertNewCustomSheetView(int i);

    void removeCustomSheetView(int i);

    void setCustomSheetViewArray(int i, CTCustomSheetView cTCustomSheetView);

    void setCustomSheetViewArray(CTCustomSheetView[] cTCustomSheetViewArr);

    int sizeOfCustomSheetViewArray();

    static {
        DocumentFactory<CTCustomSheetViews> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcustomsheetviewsc069type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
