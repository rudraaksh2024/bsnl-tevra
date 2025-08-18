package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTOutlinePr extends XmlObject {
    public static final DocumentFactory<CTOutlinePr> Factory;
    public static final SchemaType type;

    boolean getApplyStyles();

    boolean getShowOutlineSymbols();

    boolean getSummaryBelow();

    boolean getSummaryRight();

    boolean isSetApplyStyles();

    boolean isSetShowOutlineSymbols();

    boolean isSetSummaryBelow();

    boolean isSetSummaryRight();

    void setApplyStyles(boolean z);

    void setShowOutlineSymbols(boolean z);

    void setSummaryBelow(boolean z);

    void setSummaryRight(boolean z);

    void unsetApplyStyles();

    void unsetShowOutlineSymbols();

    void unsetSummaryBelow();

    void unsetSummaryRight();

    XmlBoolean xgetApplyStyles();

    XmlBoolean xgetShowOutlineSymbols();

    XmlBoolean xgetSummaryBelow();

    XmlBoolean xgetSummaryRight();

    void xsetApplyStyles(XmlBoolean xmlBoolean);

    void xsetShowOutlineSymbols(XmlBoolean xmlBoolean);

    void xsetSummaryBelow(XmlBoolean xmlBoolean);

    void xsetSummaryRight(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTOutlinePr> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctoutlineprc483type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
