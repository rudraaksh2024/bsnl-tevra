package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTSheetViews extends XmlObject {
    public static final DocumentFactory<CTSheetViews> Factory;
    public static final SchemaType type;

    CTExtensionList addNewExtLst();

    CTSheetView addNewSheetView();

    CTExtensionList getExtLst();

    CTSheetView getSheetViewArray(int i);

    CTSheetView[] getSheetViewArray();

    List<CTSheetView> getSheetViewList();

    CTSheetView insertNewSheetView(int i);

    boolean isSetExtLst();

    void removeSheetView(int i);

    void setExtLst(CTExtensionList cTExtensionList);

    void setSheetViewArray(int i, CTSheetView cTSheetView);

    void setSheetViewArray(CTSheetView[] cTSheetViewArr);

    int sizeOfSheetViewArray();

    void unsetExtLst();

    static {
        DocumentFactory<CTSheetViews> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsheetviewsb918type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
