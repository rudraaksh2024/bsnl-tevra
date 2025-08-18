package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface SldMasterDocument extends XmlObject {
    public static final DocumentFactory<SldMasterDocument> Factory;
    public static final SchemaType type;

    CTSlideMaster addNewSldMaster();

    CTSlideMaster getSldMaster();

    void setSldMaster(CTSlideMaster cTSlideMaster);

    static {
        DocumentFactory<SldMasterDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "sldmaster5156doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
