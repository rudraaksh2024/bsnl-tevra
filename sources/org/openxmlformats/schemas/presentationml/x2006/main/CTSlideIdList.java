package org.openxmlformats.schemas.presentationml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTSlideIdList extends XmlObject {
    public static final DocumentFactory<CTSlideIdList> Factory;
    public static final SchemaType type;

    CTSlideIdListEntry addNewSldId();

    CTSlideIdListEntry getSldIdArray(int i);

    CTSlideIdListEntry[] getSldIdArray();

    List<CTSlideIdListEntry> getSldIdList();

    CTSlideIdListEntry insertNewSldId(int i);

    void removeSldId(int i);

    void setSldIdArray(int i, CTSlideIdListEntry cTSlideIdListEntry);

    void setSldIdArray(CTSlideIdListEntry[] cTSlideIdListEntryArr);

    int sizeOfSldIdArray();

    static {
        DocumentFactory<CTSlideIdList> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctslideidlist70a5type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
