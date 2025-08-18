package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public interface CTShape extends XmlObject {
    public static final DocumentFactory<CTShape> Factory;
    public static final SchemaType type;

    CTExtensionListModify addNewExtLst();

    CTShapeNonVisual addNewNvSpPr();

    CTShapeProperties addNewSpPr();

    CTShapeStyle addNewStyle();

    CTTextBody addNewTxBody();

    CTExtensionListModify getExtLst();

    CTShapeNonVisual getNvSpPr();

    CTShapeProperties getSpPr();

    CTShapeStyle getStyle();

    CTTextBody getTxBody();

    boolean getUseBgFill();

    boolean isSetExtLst();

    boolean isSetStyle();

    boolean isSetTxBody();

    boolean isSetUseBgFill();

    void setExtLst(CTExtensionListModify cTExtensionListModify);

    void setNvSpPr(CTShapeNonVisual cTShapeNonVisual);

    void setSpPr(CTShapeProperties cTShapeProperties);

    void setStyle(CTShapeStyle cTShapeStyle);

    void setTxBody(CTTextBody cTTextBody);

    void setUseBgFill(boolean z);

    void unsetExtLst();

    void unsetStyle();

    void unsetTxBody();

    void unsetUseBgFill();

    XmlBoolean xgetUseBgFill();

    void xsetUseBgFill(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTShape> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctshapecfcetype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
