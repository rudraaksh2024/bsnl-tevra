package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlide;
import org.openxmlformats.schemas.presentationml.x2006.main.SldDocument;

public class SldDocumentImpl extends XmlComplexContentImpl implements SldDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "sld")};
    private static final long serialVersionUID = 1;

    public SldDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTSlide getSld() {
        CTSlide cTSlide;
        synchronized (monitor()) {
            check_orphaned();
            cTSlide = (CTSlide) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTSlide == null) {
                cTSlide = null;
            }
        }
        return cTSlide;
    }

    public void setSld(CTSlide cTSlide) {
        generatedSetterHelperImpl(cTSlide, PROPERTY_QNAME[0], 0, 1);
    }

    public CTSlide addNewSld() {
        CTSlide cTSlide;
        synchronized (monitor()) {
            check_orphaned();
            cTSlide = (CTSlide) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTSlide;
    }
}
