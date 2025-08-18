package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTCustomWorkbookViews extends XmlObject {
    public static final DocumentFactory<CTCustomWorkbookViews> Factory;
    public static final SchemaType type;

    CTCustomWorkbookView addNewCustomWorkbookView();

    CTCustomWorkbookView getCustomWorkbookViewArray(int i);

    CTCustomWorkbookView[] getCustomWorkbookViewArray();

    List<CTCustomWorkbookView> getCustomWorkbookViewList();

    CTCustomWorkbookView insertNewCustomWorkbookView(int i);

    void removeCustomWorkbookView(int i);

    void setCustomWorkbookViewArray(int i, CTCustomWorkbookView cTCustomWorkbookView);

    void setCustomWorkbookViewArray(CTCustomWorkbookView[] cTCustomWorkbookViewArr);

    int sizeOfCustomWorkbookViewArray();

    static {
        DocumentFactory<CTCustomWorkbookViews> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcustomworkbookviewse942type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
