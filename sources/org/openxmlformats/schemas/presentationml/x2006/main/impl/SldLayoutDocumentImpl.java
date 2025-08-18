package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout;
import org.openxmlformats.schemas.presentationml.x2006.main.SldLayoutDocument;

public class SldLayoutDocumentImpl extends XmlComplexContentImpl implements SldLayoutDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "sldLayout")};
    private static final long serialVersionUID = 1;

    public SldLayoutDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTSlideLayout getSldLayout() {
        CTSlideLayout cTSlideLayout;
        synchronized (monitor()) {
            check_orphaned();
            cTSlideLayout = (CTSlideLayout) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTSlideLayout == null) {
                cTSlideLayout = null;
            }
        }
        return cTSlideLayout;
    }

    public void setSldLayout(CTSlideLayout cTSlideLayout) {
        generatedSetterHelperImpl(cTSlideLayout, PROPERTY_QNAME[0], 0, 1);
    }

    public CTSlideLayout addNewSldLayout() {
        CTSlideLayout cTSlideLayout;
        synchronized (monitor()) {
            check_orphaned();
            cTSlideLayout = (CTSlideLayout) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTSlideLayout;
    }
}
