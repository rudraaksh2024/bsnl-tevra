package org.openxmlformats.schemas.presentationml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTSlideMasterIdList extends XmlObject {
    public static final DocumentFactory<CTSlideMasterIdList> Factory;
    public static final SchemaType type;

    CTSlideMasterIdListEntry addNewSldMasterId();

    CTSlideMasterIdListEntry getSldMasterIdArray(int i);

    CTSlideMasterIdListEntry[] getSldMasterIdArray();

    List<CTSlideMasterIdListEntry> getSldMasterIdList();

    CTSlideMasterIdListEntry insertNewSldMasterId(int i);

    void removeSldMasterId(int i);

    void setSldMasterIdArray(int i, CTSlideMasterIdListEntry cTSlideMasterIdListEntry);

    void setSldMasterIdArray(CTSlideMasterIdListEntry[] cTSlideMasterIdListEntryArr);

    int sizeOfSldMasterIdArray();

    static {
        DocumentFactory<CTSlideMasterIdList> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctslidemasteridlist0b63type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
