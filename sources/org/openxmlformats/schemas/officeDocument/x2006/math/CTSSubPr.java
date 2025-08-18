package org.openxmlformats.schemas.officeDocument.x2006.math;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTSSubPr extends XmlObject {
    public static final DocumentFactory<CTSSubPr> Factory;
    public static final SchemaType type;

    CTCtrlPr addNewCtrlPr();

    CTCtrlPr getCtrlPr();

    boolean isSetCtrlPr();

    void setCtrlPr(CTCtrlPr cTCtrlPr);

    void unsetCtrlPr();

    static {
        DocumentFactory<CTSSubPr> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctssubpr1ae3type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
