package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public interface CTLegendEntry extends XmlObject {
    public static final DocumentFactory<CTLegendEntry> Factory;
    public static final SchemaType type;

    CTBoolean addNewDelete();

    CTExtensionList addNewExtLst();

    CTUnsignedInt addNewIdx();

    CTTextBody addNewTxPr();

    CTBoolean getDelete();

    CTExtensionList getExtLst();

    CTUnsignedInt getIdx();

    CTTextBody getTxPr();

    boolean isSetDelete();

    boolean isSetExtLst();

    boolean isSetTxPr();

    void setDelete(CTBoolean cTBoolean);

    void setExtLst(CTExtensionList cTExtensionList);

    void setIdx(CTUnsignedInt cTUnsignedInt);

    void setTxPr(CTTextBody cTTextBody);

    void unsetDelete();

    void unsetExtLst();

    void unsetTxPr();

    static {
        DocumentFactory<CTLegendEntry> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlegendentrya8e1type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
