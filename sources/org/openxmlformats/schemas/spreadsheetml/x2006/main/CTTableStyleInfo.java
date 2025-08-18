package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;

public interface CTTableStyleInfo extends XmlObject {
    public static final DocumentFactory<CTTableStyleInfo> Factory;
    public static final SchemaType type;

    String getName();

    boolean getShowColumnStripes();

    boolean getShowFirstColumn();

    boolean getShowLastColumn();

    boolean getShowRowStripes();

    boolean isSetName();

    boolean isSetShowColumnStripes();

    boolean isSetShowFirstColumn();

    boolean isSetShowLastColumn();

    boolean isSetShowRowStripes();

    void setName(String str);

    void setShowColumnStripes(boolean z);

    void setShowFirstColumn(boolean z);

    void setShowLastColumn(boolean z);

    void setShowRowStripes(boolean z);

    void unsetName();

    void unsetShowColumnStripes();

    void unsetShowFirstColumn();

    void unsetShowLastColumn();

    void unsetShowRowStripes();

    STXstring xgetName();

    XmlBoolean xgetShowColumnStripes();

    XmlBoolean xgetShowFirstColumn();

    XmlBoolean xgetShowLastColumn();

    XmlBoolean xgetShowRowStripes();

    void xsetName(STXstring sTXstring);

    void xsetShowColumnStripes(XmlBoolean xmlBoolean);

    void xsetShowFirstColumn(XmlBoolean xmlBoolean);

    void xsetShowLastColumn(XmlBoolean xmlBoolean);

    void xsetShowRowStripes(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTTableStyleInfo> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttablestyleinfo499atype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
