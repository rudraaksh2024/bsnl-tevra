package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRuby;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyContent;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr;

public class CTRubyImpl extends XmlComplexContentImpl implements CTRuby {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rubyPr"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rt"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rubyBase")};
    private static final long serialVersionUID = 1;

    public CTRubyImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTRubyPr getRubyPr() {
        CTRubyPr cTRubyPr;
        synchronized (monitor()) {
            check_orphaned();
            cTRubyPr = (CTRubyPr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTRubyPr == null) {
                cTRubyPr = null;
            }
        }
        return cTRubyPr;
    }

    public void setRubyPr(CTRubyPr cTRubyPr) {
        generatedSetterHelperImpl(cTRubyPr, PROPERTY_QNAME[0], 0, 1);
    }

    public CTRubyPr addNewRubyPr() {
        CTRubyPr cTRubyPr;
        synchronized (monitor()) {
            check_orphaned();
            cTRubyPr = (CTRubyPr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTRubyPr;
    }

    public CTRubyContent getRt() {
        CTRubyContent cTRubyContent;
        synchronized (monitor()) {
            check_orphaned();
            cTRubyContent = (CTRubyContent) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTRubyContent == null) {
                cTRubyContent = null;
            }
        }
        return cTRubyContent;
    }

    public void setRt(CTRubyContent cTRubyContent) {
        generatedSetterHelperImpl(cTRubyContent, PROPERTY_QNAME[1], 0, 1);
    }

    public CTRubyContent addNewRt() {
        CTRubyContent cTRubyContent;
        synchronized (monitor()) {
            check_orphaned();
            cTRubyContent = (CTRubyContent) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTRubyContent;
    }

    public CTRubyContent getRubyBase() {
        CTRubyContent cTRubyContent;
        synchronized (monitor()) {
            check_orphaned();
            cTRubyContent = (CTRubyContent) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTRubyContent == null) {
                cTRubyContent = null;
            }
        }
        return cTRubyContent;
    }

    public void setRubyBase(CTRubyContent cTRubyContent) {
        generatedSetterHelperImpl(cTRubyContent, PROPERTY_QNAME[2], 0, 1);
    }

    public CTRubyContent addNewRubyBase() {
        CTRubyContent cTRubyContent;
        synchronized (monitor()) {
            check_orphaned();
            cTRubyContent = (CTRubyContent) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTRubyContent;
    }
}
