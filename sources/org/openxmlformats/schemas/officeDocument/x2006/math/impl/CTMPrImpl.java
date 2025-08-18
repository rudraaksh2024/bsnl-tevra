package org.openxmlformats.schemas.officeDocument.x2006.math.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTCtrlPr;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTMCS;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTSpacingRule;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTUnSignedInteger;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTYAlign;

public class CTMPrImpl extends XmlComplexContentImpl implements CTMPr {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "baseJc"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "plcHide"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "rSpRule"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "cGpRule"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "rSp"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "cSp"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "cGp"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "mcs"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "ctrlPr")};
    private static final long serialVersionUID = 1;

    public CTMPrImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTYAlign getBaseJc() {
        CTYAlign find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetBaseJc() {
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

    public void setBaseJc(CTYAlign cTYAlign) {
        generatedSetterHelperImpl(cTYAlign, PROPERTY_QNAME[0], 0, 1);
    }

    public CTYAlign addNewBaseJc() {
        CTYAlign add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return add_element_user;
    }

    public void unsetBaseJc() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTOnOff getPlcHide() {
        CTOnOff find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetPlcHide() {
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

    public void setPlcHide(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[1], 0, 1);
    }

    public CTOnOff addNewPlcHide() {
        CTOnOff add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return add_element_user;
    }

    public void unsetPlcHide() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTSpacingRule getRSpRule() {
        CTSpacingRule find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetRSpRule() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setRSpRule(CTSpacingRule cTSpacingRule) {
        generatedSetterHelperImpl(cTSpacingRule, PROPERTY_QNAME[2], 0, 1);
    }

    public CTSpacingRule addNewRSpRule() {
        CTSpacingRule add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return add_element_user;
    }

    public void unsetRSpRule() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTSpacingRule getCGpRule() {
        CTSpacingRule find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetCGpRule() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setCGpRule(CTSpacingRule cTSpacingRule) {
        generatedSetterHelperImpl(cTSpacingRule, PROPERTY_QNAME[3], 0, 1);
    }

    public CTSpacingRule addNewCGpRule() {
        CTSpacingRule add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return add_element_user;
    }

    public void unsetCGpRule() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTUnSignedInteger getRSp() {
        CTUnSignedInteger find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetRSp() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setRSp(CTUnSignedInteger cTUnSignedInteger) {
        generatedSetterHelperImpl(cTUnSignedInteger, PROPERTY_QNAME[4], 0, 1);
    }

    public CTUnSignedInteger addNewRSp() {
        CTUnSignedInteger add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return add_element_user;
    }

    public void unsetRSp() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTUnSignedInteger getCSp() {
        CTUnSignedInteger find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetCSp() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setCSp(CTUnSignedInteger cTUnSignedInteger) {
        generatedSetterHelperImpl(cTUnSignedInteger, PROPERTY_QNAME[5], 0, 1);
    }

    public CTUnSignedInteger addNewCSp() {
        CTUnSignedInteger add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return add_element_user;
    }

    public void unsetCSp() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public CTUnSignedInteger getCGp() {
        CTUnSignedInteger find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetCGp() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setCGp(CTUnSignedInteger cTUnSignedInteger) {
        generatedSetterHelperImpl(cTUnSignedInteger, PROPERTY_QNAME[6], 0, 1);
    }

    public CTUnSignedInteger addNewCGp() {
        CTUnSignedInteger add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return add_element_user;
    }

    public void unsetCGp() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public CTMCS getMcs() {
        CTMCS ctmcs;
        synchronized (monitor()) {
            check_orphaned();
            ctmcs = (CTMCS) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (ctmcs == null) {
                ctmcs = null;
            }
        }
        return ctmcs;
    }

    public boolean isSetMcs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    public void setMcs(CTMCS ctmcs) {
        generatedSetterHelperImpl(ctmcs, PROPERTY_QNAME[7], 0, 1);
    }

    public CTMCS addNewMcs() {
        CTMCS ctmcs;
        synchronized (monitor()) {
            check_orphaned();
            ctmcs = (CTMCS) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return ctmcs;
    }

    public void unsetMcs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    public CTCtrlPr getCtrlPr() {
        CTCtrlPr cTCtrlPr;
        synchronized (monitor()) {
            check_orphaned();
            cTCtrlPr = (CTCtrlPr) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (cTCtrlPr == null) {
                cTCtrlPr = null;
            }
        }
        return cTCtrlPr;
    }

    public boolean isSetCtrlPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    public void setCtrlPr(CTCtrlPr cTCtrlPr) {
        generatedSetterHelperImpl(cTCtrlPr, PROPERTY_QNAME[8], 0, 1);
    }

    public CTCtrlPr addNewCtrlPr() {
        CTCtrlPr cTCtrlPr;
        synchronized (monitor()) {
            check_orphaned();
            cTCtrlPr = (CTCtrlPr) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTCtrlPr;
    }

    public void unsetCtrlPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }
}
