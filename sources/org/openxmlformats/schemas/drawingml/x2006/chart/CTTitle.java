package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public interface CTTitle extends XmlObject {
    public static final DocumentFactory<CTTitle> Factory;
    public static final SchemaType type;

    CTExtensionList addNewExtLst();

    CTLayout addNewLayout();

    CTBoolean addNewOverlay();

    CTShapeProperties addNewSpPr();

    CTTx addNewTx();

    CTTextBody addNewTxPr();

    CTExtensionList getExtLst();

    CTLayout getLayout();

    CTBoolean getOverlay();

    CTShapeProperties getSpPr();

    CTTx getTx();

    CTTextBody getTxPr();

    boolean isSetExtLst();

    boolean isSetLayout();

    boolean isSetOverlay();

    boolean isSetSpPr();

    boolean isSetTx();

    boolean isSetTxPr();

    void setExtLst(CTExtensionList cTExtensionList);

    void setLayout(CTLayout cTLayout);

    void setOverlay(CTBoolean cTBoolean);

    void setSpPr(CTShapeProperties cTShapeProperties);

    void setTx(CTTx cTTx);

    void setTxPr(CTTextBody cTTextBody);

    void unsetExtLst();

    void unsetLayout();

    void unsetOverlay();

    void unsetSpPr();

    void unsetTx();

    void unsetTxPr();

    static {
        DocumentFactory<CTTitle> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttitleb54etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
