package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public interface CTShape extends XmlObject {
    public static final DocumentFactory<CTShape> Factory;
    public static final SchemaType type;

    CTShapeNonVisual addNewNvSpPr();

    CTShapeProperties addNewSpPr();

    CTShapeStyle addNewStyle();

    CTTextBody addNewTxBody();

    boolean getFLocksText();

    boolean getFPublished();

    String getMacro();

    CTShapeNonVisual getNvSpPr();

    CTShapeProperties getSpPr();

    CTShapeStyle getStyle();

    String getTextlink();

    CTTextBody getTxBody();

    boolean isSetFLocksText();

    boolean isSetFPublished();

    boolean isSetMacro();

    boolean isSetStyle();

    boolean isSetTextlink();

    boolean isSetTxBody();

    void setFLocksText(boolean z);

    void setFPublished(boolean z);

    void setMacro(String str);

    void setNvSpPr(CTShapeNonVisual cTShapeNonVisual);

    void setSpPr(CTShapeProperties cTShapeProperties);

    void setStyle(CTShapeStyle cTShapeStyle);

    void setTextlink(String str);

    void setTxBody(CTTextBody cTTextBody);

    void unsetFLocksText();

    void unsetFPublished();

    void unsetMacro();

    void unsetStyle();

    void unsetTextlink();

    void unsetTxBody();

    XmlBoolean xgetFLocksText();

    XmlBoolean xgetFPublished();

    XmlString xgetMacro();

    XmlString xgetTextlink();

    void xsetFLocksText(XmlBoolean xmlBoolean);

    void xsetFPublished(XmlBoolean xmlBoolean);

    void xsetMacro(XmlString xmlString);

    void xsetTextlink(XmlString xmlString);

    static {
        DocumentFactory<CTShape> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctshapee40btype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
