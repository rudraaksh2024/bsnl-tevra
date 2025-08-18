package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEdnPos;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEdnProps;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumFmt;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumRestart;

public class CTEdnPropsImpl extends XmlComplexContentImpl implements CTEdnProps {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pos"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "numFmt"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "numStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "numRestart")};
    private static final long serialVersionUID = 1;

    public CTEdnPropsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTEdnPos getPos() {
        CTEdnPos find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetPos() {
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

    public void setPos(CTEdnPos cTEdnPos) {
        generatedSetterHelperImpl(cTEdnPos, PROPERTY_QNAME[0], 0, 1);
    }

    public CTEdnPos addNewPos() {
        CTEdnPos add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return add_element_user;
    }

    public void unsetPos() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTNumFmt getNumFmt() {
        CTNumFmt cTNumFmt;
        synchronized (monitor()) {
            check_orphaned();
            cTNumFmt = (CTNumFmt) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTNumFmt == null) {
                cTNumFmt = null;
            }
        }
        return cTNumFmt;
    }

    public boolean isSetNumFmt() {
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

    public void setNumFmt(CTNumFmt cTNumFmt) {
        generatedSetterHelperImpl(cTNumFmt, PROPERTY_QNAME[1], 0, 1);
    }

    public CTNumFmt addNewNumFmt() {
        CTNumFmt cTNumFmt;
        synchronized (monitor()) {
            check_orphaned();
            cTNumFmt = (CTNumFmt) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTNumFmt;
    }

    public void unsetNumFmt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTDecimalNumber getNumStart() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTDecimalNumber == null) {
                cTDecimalNumber = null;
            }
        }
        return cTDecimalNumber;
    }

    public boolean isSetNumStart() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setNumStart(CTDecimalNumber cTDecimalNumber) {
        generatedSetterHelperImpl(cTDecimalNumber, PROPERTY_QNAME[2], 0, 1);
    }

    public CTDecimalNumber addNewNumStart() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTDecimalNumber;
    }

    public void unsetNumStart() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTNumRestart getNumRestart() {
        CTNumRestart cTNumRestart;
        synchronized (monitor()) {
            check_orphaned();
            cTNumRestart = (CTNumRestart) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTNumRestart == null) {
                cTNumRestart = null;
            }
        }
        return cTNumRestart;
    }

    public boolean isSetNumRestart() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setNumRestart(CTNumRestart cTNumRestart) {
        generatedSetterHelperImpl(cTNumRestart, PROPERTY_QNAME[3], 0, 1);
    }

    public CTNumRestart addNewNumRestart() {
        CTNumRestart cTNumRestart;
        synchronized (monitor()) {
            check_orphaned();
            cTNumRestart = (CTNumRestart) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTNumRestart;
    }

    public void unsetNumRestart() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
