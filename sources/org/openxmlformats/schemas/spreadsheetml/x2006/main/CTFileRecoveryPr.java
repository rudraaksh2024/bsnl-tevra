package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTFileRecoveryPr extends XmlObject {
    public static final DocumentFactory<CTFileRecoveryPr> Factory;
    public static final SchemaType type;

    boolean getAutoRecover();

    boolean getCrashSave();

    boolean getDataExtractLoad();

    boolean getRepairLoad();

    boolean isSetAutoRecover();

    boolean isSetCrashSave();

    boolean isSetDataExtractLoad();

    boolean isSetRepairLoad();

    void setAutoRecover(boolean z);

    void setCrashSave(boolean z);

    void setDataExtractLoad(boolean z);

    void setRepairLoad(boolean z);

    void unsetAutoRecover();

    void unsetCrashSave();

    void unsetDataExtractLoad();

    void unsetRepairLoad();

    XmlBoolean xgetAutoRecover();

    XmlBoolean xgetCrashSave();

    XmlBoolean xgetDataExtractLoad();

    XmlBoolean xgetRepairLoad();

    void xsetAutoRecover(XmlBoolean xmlBoolean);

    void xsetCrashSave(XmlBoolean xmlBoolean);

    void xsetDataExtractLoad(XmlBoolean xmlBoolean);

    void xsetRepairLoad(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTFileRecoveryPr> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfilerecoveryprf05ctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
