package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster;
import org.openxmlformats.schemas.presentationml.x2006.main.SldMasterDocument;

public class SldMasterDocumentImpl extends XmlComplexContentImpl implements SldMasterDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "sldMaster")};
    private static final long serialVersionUID = 1;

    public SldMasterDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTSlideMaster getSldMaster() {
        CTSlideMaster cTSlideMaster;
        synchronized (monitor()) {
            check_orphaned();
            cTSlideMaster = (CTSlideMaster) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTSlideMaster == null) {
                cTSlideMaster = null;
            }
        }
        return cTSlideMaster;
    }

    public void setSldMaster(CTSlideMaster cTSlideMaster) {
        generatedSetterHelperImpl(cTSlideMaster, PROPERTY_QNAME[0], 0, 1);
    }

    public CTSlideMaster addNewSldMaster() {
        CTSlideMaster cTSlideMaster;
        synchronized (monitor()) {
            check_orphaned();
            cTSlideMaster = (CTSlideMaster) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTSlideMaster;
    }
}
