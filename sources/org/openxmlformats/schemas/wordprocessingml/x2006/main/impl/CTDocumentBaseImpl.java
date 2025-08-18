package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocumentBase;

public class CTDocumentBaseImpl extends XmlComplexContentImpl implements CTDocumentBase {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "background")};
    private static final long serialVersionUID = 1;

    public CTDocumentBaseImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTBackground getBackground() {
        CTBackground cTBackground;
        synchronized (monitor()) {
            check_orphaned();
            cTBackground = (CTBackground) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTBackground == null) {
                cTBackground = null;
            }
        }
        return cTBackground;
    }

    public boolean isSetBackground() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = false;
            if (get_store().count_elements(PROPERTY_QNAME[0]) != 0) {
                z = true;
            }
        }
        return z;
    }

    public void setBackground(CTBackground cTBackground) {
        generatedSetterHelperImpl(cTBackground, PROPERTY_QNAME[0], 0, 1);
    }

    public CTBackground addNewBackground() {
        CTBackground cTBackground;
        synchronized (monitor()) {
            check_orphaned();
            cTBackground = (CTBackground) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTBackground;
    }

    public void unsetBackground() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }
}
