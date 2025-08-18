package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTPageSetUpPr extends XmlObject {
    public static final DocumentFactory<CTPageSetUpPr> Factory;
    public static final SchemaType type;

    boolean getAutoPageBreaks();

    boolean getFitToPage();

    boolean isSetAutoPageBreaks();

    boolean isSetFitToPage();

    void setAutoPageBreaks(boolean z);

    void setFitToPage(boolean z);

    void unsetAutoPageBreaks();

    void unsetFitToPage();

    XmlBoolean xgetAutoPageBreaks();

    XmlBoolean xgetFitToPage();

    void xsetAutoPageBreaks(XmlBoolean xmlBoolean);

    void xsetFitToPage(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTPageSetUpPr> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpagesetuppr24cftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
