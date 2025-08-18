package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLang;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyAlign;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr;

public class CTRubyPrImpl extends XmlComplexContentImpl implements CTRubyPr {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rubyAlign"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "hps"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "hpsRaise"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "hpsBaseText"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "lid"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "dirty")};
    private static final long serialVersionUID = 1;

    public CTRubyPrImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTRubyAlign getRubyAlign() {
        CTRubyAlign cTRubyAlign;
        synchronized (monitor()) {
            check_orphaned();
            cTRubyAlign = (CTRubyAlign) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTRubyAlign == null) {
                cTRubyAlign = null;
            }
        }
        return cTRubyAlign;
    }

    public void setRubyAlign(CTRubyAlign cTRubyAlign) {
        generatedSetterHelperImpl(cTRubyAlign, PROPERTY_QNAME[0], 0, 1);
    }

    public CTRubyAlign addNewRubyAlign() {
        CTRubyAlign cTRubyAlign;
        synchronized (monitor()) {
            check_orphaned();
            cTRubyAlign = (CTRubyAlign) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTRubyAlign;
    }

    public CTHpsMeasure getHps() {
        CTHpsMeasure cTHpsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            cTHpsMeasure = (CTHpsMeasure) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTHpsMeasure == null) {
                cTHpsMeasure = null;
            }
        }
        return cTHpsMeasure;
    }

    public void setHps(CTHpsMeasure cTHpsMeasure) {
        generatedSetterHelperImpl(cTHpsMeasure, PROPERTY_QNAME[1], 0, 1);
    }

    public CTHpsMeasure addNewHps() {
        CTHpsMeasure cTHpsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            cTHpsMeasure = (CTHpsMeasure) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTHpsMeasure;
    }

    public CTHpsMeasure getHpsRaise() {
        CTHpsMeasure cTHpsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            cTHpsMeasure = (CTHpsMeasure) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTHpsMeasure == null) {
                cTHpsMeasure = null;
            }
        }
        return cTHpsMeasure;
    }

    public void setHpsRaise(CTHpsMeasure cTHpsMeasure) {
        generatedSetterHelperImpl(cTHpsMeasure, PROPERTY_QNAME[2], 0, 1);
    }

    public CTHpsMeasure addNewHpsRaise() {
        CTHpsMeasure cTHpsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            cTHpsMeasure = (CTHpsMeasure) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTHpsMeasure;
    }

    public CTHpsMeasure getHpsBaseText() {
        CTHpsMeasure cTHpsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            cTHpsMeasure = (CTHpsMeasure) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTHpsMeasure == null) {
                cTHpsMeasure = null;
            }
        }
        return cTHpsMeasure;
    }

    public void setHpsBaseText(CTHpsMeasure cTHpsMeasure) {
        generatedSetterHelperImpl(cTHpsMeasure, PROPERTY_QNAME[3], 0, 1);
    }

    public CTHpsMeasure addNewHpsBaseText() {
        CTHpsMeasure cTHpsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            cTHpsMeasure = (CTHpsMeasure) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTHpsMeasure;
    }

    public CTLang getLid() {
        CTLang cTLang;
        synchronized (monitor()) {
            check_orphaned();
            cTLang = (CTLang) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTLang == null) {
                cTLang = null;
            }
        }
        return cTLang;
    }

    public void setLid(CTLang cTLang) {
        generatedSetterHelperImpl(cTLang, PROPERTY_QNAME[4], 0, 1);
    }

    public CTLang addNewLid() {
        CTLang cTLang;
        synchronized (monitor()) {
            check_orphaned();
            cTLang = (CTLang) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTLang;
    }

    public CTOnOff getDirty() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetDirty() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setDirty(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[5], 0, 1);
    }

    public CTOnOff addNewDirty() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTOnOff;
    }

    public void unsetDirty() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }
}
