package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface TblStyleLstDocument extends XmlObject {
    public static final DocumentFactory<TblStyleLstDocument> Factory;
    public static final SchemaType type;

    CTTableStyleList addNewTblStyleLst();

    CTTableStyleList getTblStyleLst();

    void setTblStyleLst(CTTableStyleList cTTableStyleList);

    static {
        DocumentFactory<TblStyleLstDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "tblstylelst4997doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
