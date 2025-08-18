package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTPictureLocking extends XmlObject {
    public static final DocumentFactory<CTPictureLocking> Factory;
    public static final SchemaType type;

    CTOfficeArtExtensionList addNewExtLst();

    CTOfficeArtExtensionList getExtLst();

    boolean getNoAdjustHandles();

    boolean getNoChangeArrowheads();

    boolean getNoChangeAspect();

    boolean getNoChangeShapeType();

    boolean getNoCrop();

    boolean getNoEditPoints();

    boolean getNoGrp();

    boolean getNoMove();

    boolean getNoResize();

    boolean getNoRot();

    boolean getNoSelect();

    boolean isSetExtLst();

    boolean isSetNoAdjustHandles();

    boolean isSetNoChangeArrowheads();

    boolean isSetNoChangeAspect();

    boolean isSetNoChangeShapeType();

    boolean isSetNoCrop();

    boolean isSetNoEditPoints();

    boolean isSetNoGrp();

    boolean isSetNoMove();

    boolean isSetNoResize();

    boolean isSetNoRot();

    boolean isSetNoSelect();

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setNoAdjustHandles(boolean z);

    void setNoChangeArrowheads(boolean z);

    void setNoChangeAspect(boolean z);

    void setNoChangeShapeType(boolean z);

    void setNoCrop(boolean z);

    void setNoEditPoints(boolean z);

    void setNoGrp(boolean z);

    void setNoMove(boolean z);

    void setNoResize(boolean z);

    void setNoRot(boolean z);

    void setNoSelect(boolean z);

    void unsetExtLst();

    void unsetNoAdjustHandles();

    void unsetNoChangeArrowheads();

    void unsetNoChangeAspect();

    void unsetNoChangeShapeType();

    void unsetNoCrop();

    void unsetNoEditPoints();

    void unsetNoGrp();

    void unsetNoMove();

    void unsetNoResize();

    void unsetNoRot();

    void unsetNoSelect();

    XmlBoolean xgetNoAdjustHandles();

    XmlBoolean xgetNoChangeArrowheads();

    XmlBoolean xgetNoChangeAspect();

    XmlBoolean xgetNoChangeShapeType();

    XmlBoolean xgetNoCrop();

    XmlBoolean xgetNoEditPoints();

    XmlBoolean xgetNoGrp();

    XmlBoolean xgetNoMove();

    XmlBoolean xgetNoResize();

    XmlBoolean xgetNoRot();

    XmlBoolean xgetNoSelect();

    void xsetNoAdjustHandles(XmlBoolean xmlBoolean);

    void xsetNoChangeArrowheads(XmlBoolean xmlBoolean);

    void xsetNoChangeAspect(XmlBoolean xmlBoolean);

    void xsetNoChangeShapeType(XmlBoolean xmlBoolean);

    void xsetNoCrop(XmlBoolean xmlBoolean);

    void xsetNoEditPoints(XmlBoolean xmlBoolean);

    void xsetNoGrp(XmlBoolean xmlBoolean);

    void xsetNoMove(XmlBoolean xmlBoolean);

    void xsetNoResize(XmlBoolean xmlBoolean);

    void xsetNoRot(XmlBoolean xmlBoolean);

    void xsetNoSelect(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTPictureLocking> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpicturelockinga414type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
