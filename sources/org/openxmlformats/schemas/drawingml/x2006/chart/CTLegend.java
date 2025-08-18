package org.openxmlformats.schemas.drawingml.x2006.chart;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public interface CTLegend extends XmlObject {
    public static final DocumentFactory<CTLegend> Factory;
    public static final SchemaType type;

    CTExtensionList addNewExtLst();

    CTLayout addNewLayout();

    CTLegendEntry addNewLegendEntry();

    CTLegendPos addNewLegendPos();

    CTBoolean addNewOverlay();

    CTShapeProperties addNewSpPr();

    CTTextBody addNewTxPr();

    CTExtensionList getExtLst();

    CTLayout getLayout();

    CTLegendEntry getLegendEntryArray(int i);

    CTLegendEntry[] getLegendEntryArray();

    List<CTLegendEntry> getLegendEntryList();

    CTLegendPos getLegendPos();

    CTBoolean getOverlay();

    CTShapeProperties getSpPr();

    CTTextBody getTxPr();

    CTLegendEntry insertNewLegendEntry(int i);

    boolean isSetExtLst();

    boolean isSetLayout();

    boolean isSetLegendPos();

    boolean isSetOverlay();

    boolean isSetSpPr();

    boolean isSetTxPr();

    void removeLegendEntry(int i);

    void setExtLst(CTExtensionList cTExtensionList);

    void setLayout(CTLayout cTLayout);

    void setLegendEntryArray(int i, CTLegendEntry cTLegendEntry);

    void setLegendEntryArray(CTLegendEntry[] cTLegendEntryArr);

    void setLegendPos(CTLegendPos cTLegendPos);

    void setOverlay(CTBoolean cTBoolean);

    void setSpPr(CTShapeProperties cTShapeProperties);

    void setTxPr(CTTextBody cTTextBody);

    int sizeOfLegendEntryArray();

    void unsetExtLst();

    void unsetLayout();

    void unsetLegendPos();

    void unsetOverlay();

    void unsetSpPr();

    void unsetTxPr();

    static {
        DocumentFactory<CTLegend> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlegenda54ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
