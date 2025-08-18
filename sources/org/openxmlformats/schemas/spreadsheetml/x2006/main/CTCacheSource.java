package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSourceType;

public interface CTCacheSource extends XmlObject {
    public static final DocumentFactory<CTCacheSource> Factory;
    public static final SchemaType type;

    CTConsolidation addNewConsolidation();

    CTExtensionList addNewExtLst();

    CTWorksheetSource addNewWorksheetSource();

    long getConnectionId();

    CTConsolidation getConsolidation();

    CTExtensionList getExtLst();

    STSourceType.Enum getType();

    CTWorksheetSource getWorksheetSource();

    boolean isSetConnectionId();

    boolean isSetConsolidation();

    boolean isSetExtLst();

    boolean isSetWorksheetSource();

    void setConnectionId(long j);

    void setConsolidation(CTConsolidation cTConsolidation);

    void setExtLst(CTExtensionList cTExtensionList);

    void setType(STSourceType.Enum enumR);

    void setWorksheetSource(CTWorksheetSource cTWorksheetSource);

    void unsetConnectionId();

    void unsetConsolidation();

    void unsetExtLst();

    void unsetWorksheetSource();

    XmlUnsignedInt xgetConnectionId();

    STSourceType xgetType();

    void xsetConnectionId(XmlUnsignedInt xmlUnsignedInt);

    void xsetType(STSourceType sTSourceType);

    static {
        DocumentFactory<CTCacheSource> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcachesource00dctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
