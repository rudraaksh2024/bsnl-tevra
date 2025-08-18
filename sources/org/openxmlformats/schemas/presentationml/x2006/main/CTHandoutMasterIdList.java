package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTHandoutMasterIdList extends XmlObject {
    public static final DocumentFactory<CTHandoutMasterIdList> Factory;
    public static final SchemaType type;

    CTHandoutMasterIdListEntry addNewHandoutMasterId();

    CTHandoutMasterIdListEntry getHandoutMasterId();

    boolean isSetHandoutMasterId();

    void setHandoutMasterId(CTHandoutMasterIdListEntry cTHandoutMasterIdListEntry);

    void unsetHandoutMasterId();

    static {
        DocumentFactory<CTHandoutMasterIdList> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cthandoutmasteridlist5b95type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
