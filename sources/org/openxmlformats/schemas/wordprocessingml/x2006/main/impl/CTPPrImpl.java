package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

public class CTPPrImpl extends CTPPrBaseImpl implements CTPPr {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rPr"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "sectPr"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pPrChange")};
    private static final long serialVersionUID = 1;

    public CTPPrImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTParaRPr getRPr() {
        CTParaRPr cTParaRPr;
        synchronized (monitor()) {
            check_orphaned();
            cTParaRPr = (CTParaRPr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTParaRPr == null) {
                cTParaRPr = null;
            }
        }
        return cTParaRPr;
    }

    public boolean isSetRPr() {
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

    public void setRPr(CTParaRPr cTParaRPr) {
        generatedSetterHelperImpl(cTParaRPr, PROPERTY_QNAME[0], 0, 1);
    }

    public CTParaRPr addNewRPr() {
        CTParaRPr cTParaRPr;
        synchronized (monitor()) {
            check_orphaned();
            cTParaRPr = (CTParaRPr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTParaRPr;
    }

    public void unsetRPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTSectPr getSectPr() {
        CTSectPr cTSectPr;
        synchronized (monitor()) {
            check_orphaned();
            cTSectPr = (CTSectPr) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTSectPr == null) {
                cTSectPr = null;
            }
        }
        return cTSectPr;
    }

    public boolean isSetSectPr() {
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

    public void setSectPr(CTSectPr cTSectPr) {
        generatedSetterHelperImpl(cTSectPr, PROPERTY_QNAME[1], 0, 1);
    }

    public CTSectPr addNewSectPr() {
        CTSectPr cTSectPr;
        synchronized (monitor()) {
            check_orphaned();
            cTSectPr = (CTSectPr) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTSectPr;
    }

    public void unsetSectPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTPPrChange getPPrChange() {
        CTPPrChange find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetPPrChange() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setPPrChange(CTPPrChange cTPPrChange) {
        generatedSetterHelperImpl(cTPPrChange, PROPERTY_QNAME[2], 0, 1);
    }

    public CTPPrChange addNewPPrChange() {
        CTPPrChange add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return add_element_user;
    }

    public void unsetPPrChange() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }
}
