package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCellMergeTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrInner;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChange;

public class CTTcPrInnerImpl extends CTTcPrBaseImpl implements CTTcPrInner {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cellIns"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cellDel"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cellMerge")};
    private static final long serialVersionUID = 1;

    public CTTcPrInnerImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTTrackChange getCellIns() {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTTrackChange == null) {
                cTTrackChange = null;
            }
        }
        return cTTrackChange;
    }

    public boolean isSetCellIns() {
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

    public void setCellIns(CTTrackChange cTTrackChange) {
        generatedSetterHelperImpl(cTTrackChange, PROPERTY_QNAME[0], 0, 1);
    }

    public CTTrackChange addNewCellIns() {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTTrackChange;
    }

    public void unsetCellIns() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTTrackChange getCellDel() {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTTrackChange == null) {
                cTTrackChange = null;
            }
        }
        return cTTrackChange;
    }

    public boolean isSetCellDel() {
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

    public void setCellDel(CTTrackChange cTTrackChange) {
        generatedSetterHelperImpl(cTTrackChange, PROPERTY_QNAME[1], 0, 1);
    }

    public CTTrackChange addNewCellDel() {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTTrackChange;
    }

    public void unsetCellDel() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTCellMergeTrackChange getCellMerge() {
        CTCellMergeTrackChange find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetCellMerge() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setCellMerge(CTCellMergeTrackChange cTCellMergeTrackChange) {
        generatedSetterHelperImpl(cTCellMergeTrackChange, PROPERTY_QNAME[2], 0, 1);
    }

    public CTCellMergeTrackChange addNewCellMerge() {
        CTCellMergeTrackChange add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return add_element_user;
    }

    public void unsetCellMerge() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }
}
