package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTSheetCalcPr extends XmlObject {
    public static final DocumentFactory<CTSheetCalcPr> Factory;
    public static final SchemaType type;

    boolean getFullCalcOnLoad();

    boolean isSetFullCalcOnLoad();

    void setFullCalcOnLoad(boolean z);

    void unsetFullCalcOnLoad();

    XmlBoolean xgetFullCalcOnLoad();

    void xsetFullCalcOnLoad(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTSheetCalcPr> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsheetcalcprc6d5type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
