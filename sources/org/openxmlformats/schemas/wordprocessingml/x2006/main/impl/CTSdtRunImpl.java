package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtContentRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtEndPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtRun;

public class CTSdtRunImpl extends XmlComplexContentImpl implements CTSdtRun {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "sdtPr"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "sdtEndPr"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "sdtContent")};
    private static final long serialVersionUID = 1;

    public CTSdtRunImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTSdtPr getSdtPr() {
        CTSdtPr cTSdtPr;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtPr = (CTSdtPr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTSdtPr == null) {
                cTSdtPr = null;
            }
        }
        return cTSdtPr;
    }

    public boolean isSetSdtPr() {
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

    public void setSdtPr(CTSdtPr cTSdtPr) {
        generatedSetterHelperImpl(cTSdtPr, PROPERTY_QNAME[0], 0, 1);
    }

    public CTSdtPr addNewSdtPr() {
        CTSdtPr cTSdtPr;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtPr = (CTSdtPr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTSdtPr;
    }

    public void unsetSdtPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTSdtEndPr getSdtEndPr() {
        CTSdtEndPr cTSdtEndPr;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtEndPr = (CTSdtEndPr) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTSdtEndPr == null) {
                cTSdtEndPr = null;
            }
        }
        return cTSdtEndPr;
    }

    public boolean isSetSdtEndPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    public void setSdtEndPr(CTSdtEndPr cTSdtEndPr) {
        generatedSetterHelperImpl(cTSdtEndPr, PROPERTY_QNAME[1], 0, 1);
    }

    public CTSdtEndPr addNewSdtEndPr() {
        CTSdtEndPr cTSdtEndPr;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtEndPr = (CTSdtEndPr) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTSdtEndPr;
    }

    public void unsetSdtEndPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTSdtContentRun getSdtContent() {
        CTSdtContentRun cTSdtContentRun;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtContentRun = (CTSdtContentRun) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTSdtContentRun == null) {
                cTSdtContentRun = null;
            }
        }
        return cTSdtContentRun;
    }

    public boolean isSetSdtContent() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setSdtContent(CTSdtContentRun cTSdtContentRun) {
        generatedSetterHelperImpl(cTSdtContentRun, PROPERTY_QNAME[2], 0, 1);
    }

    public CTSdtContentRun addNewSdtContent() {
        CTSdtContentRun cTSdtContentRun;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtContentRun = (CTSdtContentRun) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTSdtContentRun;
    }

    public void unsetSdtContent() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }
}
