package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTPivotTableStyle extends XmlObject {
    public static final DocumentFactory<CTPivotTableStyle> Factory;
    public static final SchemaType type;

    String getName();

    boolean getShowColHeaders();

    boolean getShowColStripes();

    boolean getShowLastColumn();

    boolean getShowRowHeaders();

    boolean getShowRowStripes();

    boolean isSetName();

    boolean isSetShowColHeaders();

    boolean isSetShowColStripes();

    boolean isSetShowLastColumn();

    boolean isSetShowRowHeaders();

    boolean isSetShowRowStripes();

    void setName(String str);

    void setShowColHeaders(boolean z);

    void setShowColStripes(boolean z);

    void setShowLastColumn(boolean z);

    void setShowRowHeaders(boolean z);

    void setShowRowStripes(boolean z);

    void unsetName();

    void unsetShowColHeaders();

    void unsetShowColStripes();

    void unsetShowLastColumn();

    void unsetShowRowHeaders();

    void unsetShowRowStripes();

    XmlString xgetName();

    XmlBoolean xgetShowColHeaders();

    XmlBoolean xgetShowColStripes();

    XmlBoolean xgetShowLastColumn();

    XmlBoolean xgetShowRowHeaders();

    XmlBoolean xgetShowRowStripes();

    void xsetName(XmlString xmlString);

    void xsetShowColHeaders(XmlBoolean xmlBoolean);

    void xsetShowColStripes(XmlBoolean xmlBoolean);

    void xsetShowLastColumn(XmlBoolean xmlBoolean);

    void xsetShowRowHeaders(XmlBoolean xmlBoolean);

    void xsetShowRowStripes(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTPivotTableStyle> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpivottablestyle0f84type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
