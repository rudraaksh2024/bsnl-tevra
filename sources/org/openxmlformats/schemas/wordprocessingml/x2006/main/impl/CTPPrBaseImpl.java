package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCnf;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabs;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextAlignment;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextDirection;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextboxTightWrap;

public class CTPPrBaseImpl extends XmlComplexContentImpl implements CTPPrBase {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pStyle"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "keepNext"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "keepLines"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pageBreakBefore"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "framePr"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "widowControl"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "numPr"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "suppressLineNumbers"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pBdr"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "shd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tabs"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "suppressAutoHyphens"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "kinsoku"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "wordWrap"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "overflowPunct"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "topLinePunct"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "autoSpaceDE"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "autoSpaceDN"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bidi"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "adjustRightInd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "snapToGrid"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "spacing"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "ind"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "contextualSpacing"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "mirrorIndents"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "suppressOverlap"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "jc"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "textDirection"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "textAlignment"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "textboxTightWrap"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "outlineLvl"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "divId"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cnfStyle")};
    private static final long serialVersionUID = 1;

    public CTPPrBaseImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTString getPStyle() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            cTString = (CTString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTString == null) {
                cTString = null;
            }
        }
        return cTString;
    }

    public boolean isSetPStyle() {
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

    public void setPStyle(CTString cTString) {
        generatedSetterHelperImpl(cTString, PROPERTY_QNAME[0], 0, 1);
    }

    public CTString addNewPStyle() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            cTString = (CTString) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTString;
    }

    public void unsetPStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTOnOff getKeepNext() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetKeepNext() {
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

    public void setKeepNext(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[1], 0, 1);
    }

    public CTOnOff addNewKeepNext() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTOnOff;
    }

    public void unsetKeepNext() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTOnOff getKeepLines() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetKeepLines() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setKeepLines(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[2], 0, 1);
    }

    public CTOnOff addNewKeepLines() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTOnOff;
    }

    public void unsetKeepLines() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTOnOff getPageBreakBefore() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetPageBreakBefore() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setPageBreakBefore(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[3], 0, 1);
    }

    public CTOnOff addNewPageBreakBefore() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTOnOff;
    }

    public void unsetPageBreakBefore() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTFramePr getFramePr() {
        CTFramePr cTFramePr;
        synchronized (monitor()) {
            check_orphaned();
            cTFramePr = (CTFramePr) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTFramePr == null) {
                cTFramePr = null;
            }
        }
        return cTFramePr;
    }

    public boolean isSetFramePr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setFramePr(CTFramePr cTFramePr) {
        generatedSetterHelperImpl(cTFramePr, PROPERTY_QNAME[4], 0, 1);
    }

    public CTFramePr addNewFramePr() {
        CTFramePr cTFramePr;
        synchronized (monitor()) {
            check_orphaned();
            cTFramePr = (CTFramePr) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTFramePr;
    }

    public void unsetFramePr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTOnOff getWidowControl() {
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

    public boolean isSetWidowControl() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setWidowControl(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[5], 0, 1);
    }

    public CTOnOff addNewWidowControl() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTOnOff;
    }

    public void unsetWidowControl() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public CTNumPr getNumPr() {
        CTNumPr cTNumPr;
        synchronized (monitor()) {
            check_orphaned();
            cTNumPr = (CTNumPr) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (cTNumPr == null) {
                cTNumPr = null;
            }
        }
        return cTNumPr;
    }

    public boolean isSetNumPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setNumPr(CTNumPr cTNumPr) {
        generatedSetterHelperImpl(cTNumPr, PROPERTY_QNAME[6], 0, 1);
    }

    public CTNumPr addNewNumPr() {
        CTNumPr cTNumPr;
        synchronized (monitor()) {
            check_orphaned();
            cTNumPr = (CTNumPr) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTNumPr;
    }

    public void unsetNumPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public CTOnOff getSuppressLineNumbers() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetSuppressLineNumbers() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    public void setSuppressLineNumbers(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[7], 0, 1);
    }

    public CTOnOff addNewSuppressLineNumbers() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTOnOff;
    }

    public void unsetSuppressLineNumbers() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    public CTPBdr getPBdr() {
        CTPBdr cTPBdr;
        synchronized (monitor()) {
            check_orphaned();
            cTPBdr = (CTPBdr) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (cTPBdr == null) {
                cTPBdr = null;
            }
        }
        return cTPBdr;
    }

    public boolean isSetPBdr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    public void setPBdr(CTPBdr cTPBdr) {
        generatedSetterHelperImpl(cTPBdr, PROPERTY_QNAME[8], 0, 1);
    }

    public CTPBdr addNewPBdr() {
        CTPBdr cTPBdr;
        synchronized (monitor()) {
            check_orphaned();
            cTPBdr = (CTPBdr) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTPBdr;
    }

    public void unsetPBdr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    public CTShd getShd() {
        CTShd cTShd;
        synchronized (monitor()) {
            check_orphaned();
            cTShd = (CTShd) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (cTShd == null) {
                cTShd = null;
            }
        }
        return cTShd;
    }

    public boolean isSetShd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    public void setShd(CTShd cTShd) {
        generatedSetterHelperImpl(cTShd, PROPERTY_QNAME[9], 0, 1);
    }

    public CTShd addNewShd() {
        CTShd cTShd;
        synchronized (monitor()) {
            check_orphaned();
            cTShd = (CTShd) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTShd;
    }

    public void unsetShd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    public CTTabs getTabs() {
        CTTabs cTTabs;
        synchronized (monitor()) {
            check_orphaned();
            cTTabs = (CTTabs) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            if (cTTabs == null) {
                cTTabs = null;
            }
        }
        return cTTabs;
    }

    public boolean isSetTabs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    public void setTabs(CTTabs cTTabs) {
        generatedSetterHelperImpl(cTTabs, PROPERTY_QNAME[10], 0, 1);
    }

    public CTTabs addNewTabs() {
        CTTabs cTTabs;
        synchronized (monitor()) {
            check_orphaned();
            cTTabs = (CTTabs) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTTabs;
    }

    public void unsetTabs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    public CTOnOff getSuppressAutoHyphens() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetSuppressAutoHyphens() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    public void setSuppressAutoHyphens(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[11], 0, 1);
    }

    public CTOnOff addNewSuppressAutoHyphens() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTOnOff;
    }

    public void unsetSuppressAutoHyphens() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    public CTOnOff getKinsoku() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetKinsoku() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    public void setKinsoku(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[12], 0, 1);
    }

    public CTOnOff addNewKinsoku() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return cTOnOff;
    }

    public void unsetKinsoku() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    public CTOnOff getWordWrap() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetWordWrap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    public void setWordWrap(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[13], 0, 1);
    }

    public CTOnOff addNewWordWrap() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return cTOnOff;
    }

    public void unsetWordWrap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    public CTOnOff getOverflowPunct() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetOverflowPunct() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    public void setOverflowPunct(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[14], 0, 1);
    }

    public CTOnOff addNewOverflowPunct() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return cTOnOff;
    }

    public void unsetOverflowPunct() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    public CTOnOff getTopLinePunct() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetTopLinePunct() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    public void setTopLinePunct(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[15], 0, 1);
    }

    public CTOnOff addNewTopLinePunct() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return cTOnOff;
    }

    public void unsetTopLinePunct() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }

    public CTOnOff getAutoSpaceDE() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[16], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetAutoSpaceDE() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[16]) != 0;
        }
        return z;
    }

    public void setAutoSpaceDE(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[16], 0, 1);
    }

    public CTOnOff addNewAutoSpaceDE() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return cTOnOff;
    }

    public void unsetAutoSpaceDE() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], 0);
        }
    }

    public CTOnOff getAutoSpaceDN() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[17], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetAutoSpaceDN() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[17]) != 0;
        }
        return z;
    }

    public void setAutoSpaceDN(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[17], 0, 1);
    }

    public CTOnOff addNewAutoSpaceDN() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return cTOnOff;
    }

    public void unsetAutoSpaceDN() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], 0);
        }
    }

    public CTOnOff getBidi() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[18], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetBidi() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[18]) != 0;
        }
        return z;
    }

    public void setBidi(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[18], 0, 1);
    }

    public CTOnOff addNewBidi() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return cTOnOff;
    }

    public void unsetBidi() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], 0);
        }
    }

    public CTOnOff getAdjustRightInd() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[19], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetAdjustRightInd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[19]) != 0;
        }
        return z;
    }

    public void setAdjustRightInd(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[19], 0, 1);
    }

    public CTOnOff addNewAdjustRightInd() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return cTOnOff;
    }

    public void unsetAdjustRightInd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], 0);
        }
    }

    public CTOnOff getSnapToGrid() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[20], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetSnapToGrid() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[20]) != 0;
        }
        return z;
    }

    public void setSnapToGrid(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[20], 0, 1);
    }

    public CTOnOff addNewSnapToGrid() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return cTOnOff;
    }

    public void unsetSnapToGrid() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], 0);
        }
    }

    public CTSpacing getSpacing() {
        CTSpacing cTSpacing;
        synchronized (monitor()) {
            check_orphaned();
            cTSpacing = (CTSpacing) get_store().find_element_user(PROPERTY_QNAME[21], 0);
            if (cTSpacing == null) {
                cTSpacing = null;
            }
        }
        return cTSpacing;
    }

    public boolean isSetSpacing() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[21]) != 0;
        }
        return z;
    }

    public void setSpacing(CTSpacing cTSpacing) {
        generatedSetterHelperImpl(cTSpacing, PROPERTY_QNAME[21], 0, 1);
    }

    public CTSpacing addNewSpacing() {
        CTSpacing cTSpacing;
        synchronized (monitor()) {
            check_orphaned();
            cTSpacing = (CTSpacing) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return cTSpacing;
    }

    public void unsetSpacing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], 0);
        }
    }

    public CTInd getInd() {
        CTInd cTInd;
        synchronized (monitor()) {
            check_orphaned();
            cTInd = (CTInd) get_store().find_element_user(PROPERTY_QNAME[22], 0);
            if (cTInd == null) {
                cTInd = null;
            }
        }
        return cTInd;
    }

    public boolean isSetInd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[22]) != 0;
        }
        return z;
    }

    public void setInd(CTInd cTInd) {
        generatedSetterHelperImpl(cTInd, PROPERTY_QNAME[22], 0, 1);
    }

    public CTInd addNewInd() {
        CTInd cTInd;
        synchronized (monitor()) {
            check_orphaned();
            cTInd = (CTInd) get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return cTInd;
    }

    public void unsetInd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], 0);
        }
    }

    public CTOnOff getContextualSpacing() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[23], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetContextualSpacing() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[23]) != 0;
        }
        return z;
    }

    public void setContextualSpacing(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[23], 0, 1);
    }

    public CTOnOff addNewContextualSpacing() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return cTOnOff;
    }

    public void unsetContextualSpacing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], 0);
        }
    }

    public CTOnOff getMirrorIndents() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[24], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetMirrorIndents() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[24]) != 0;
        }
        return z;
    }

    public void setMirrorIndents(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[24], 0, 1);
    }

    public CTOnOff addNewMirrorIndents() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[24]);
        }
        return cTOnOff;
    }

    public void unsetMirrorIndents() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], 0);
        }
    }

    public CTOnOff getSuppressOverlap() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[25], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetSuppressOverlap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[25]) != 0;
        }
        return z;
    }

    public void setSuppressOverlap(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[25], 0, 1);
    }

    public CTOnOff addNewSuppressOverlap() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[25]);
        }
        return cTOnOff;
    }

    public void unsetSuppressOverlap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], 0);
        }
    }

    public CTJc getJc() {
        CTJc cTJc;
        synchronized (monitor()) {
            check_orphaned();
            cTJc = (CTJc) get_store().find_element_user(PROPERTY_QNAME[26], 0);
            if (cTJc == null) {
                cTJc = null;
            }
        }
        return cTJc;
    }

    public boolean isSetJc() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[26]) != 0;
        }
        return z;
    }

    public void setJc(CTJc cTJc) {
        generatedSetterHelperImpl(cTJc, PROPERTY_QNAME[26], 0, 1);
    }

    public CTJc addNewJc() {
        CTJc cTJc;
        synchronized (monitor()) {
            check_orphaned();
            cTJc = (CTJc) get_store().add_element_user(PROPERTY_QNAME[26]);
        }
        return cTJc;
    }

    public void unsetJc() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[26], 0);
        }
    }

    public CTTextDirection getTextDirection() {
        CTTextDirection cTTextDirection;
        synchronized (monitor()) {
            check_orphaned();
            cTTextDirection = (CTTextDirection) get_store().find_element_user(PROPERTY_QNAME[27], 0);
            if (cTTextDirection == null) {
                cTTextDirection = null;
            }
        }
        return cTTextDirection;
    }

    public boolean isSetTextDirection() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[27]) != 0;
        }
        return z;
    }

    public void setTextDirection(CTTextDirection cTTextDirection) {
        generatedSetterHelperImpl(cTTextDirection, PROPERTY_QNAME[27], 0, 1);
    }

    public CTTextDirection addNewTextDirection() {
        CTTextDirection cTTextDirection;
        synchronized (monitor()) {
            check_orphaned();
            cTTextDirection = (CTTextDirection) get_store().add_element_user(PROPERTY_QNAME[27]);
        }
        return cTTextDirection;
    }

    public void unsetTextDirection() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[27], 0);
        }
    }

    public CTTextAlignment getTextAlignment() {
        CTTextAlignment cTTextAlignment;
        synchronized (monitor()) {
            check_orphaned();
            cTTextAlignment = (CTTextAlignment) get_store().find_element_user(PROPERTY_QNAME[28], 0);
            if (cTTextAlignment == null) {
                cTTextAlignment = null;
            }
        }
        return cTTextAlignment;
    }

    public boolean isSetTextAlignment() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[28]) != 0;
        }
        return z;
    }

    public void setTextAlignment(CTTextAlignment cTTextAlignment) {
        generatedSetterHelperImpl(cTTextAlignment, PROPERTY_QNAME[28], 0, 1);
    }

    public CTTextAlignment addNewTextAlignment() {
        CTTextAlignment cTTextAlignment;
        synchronized (monitor()) {
            check_orphaned();
            cTTextAlignment = (CTTextAlignment) get_store().add_element_user(PROPERTY_QNAME[28]);
        }
        return cTTextAlignment;
    }

    public void unsetTextAlignment() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[28], 0);
        }
    }

    public CTTextboxTightWrap getTextboxTightWrap() {
        CTTextboxTightWrap find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[29], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetTextboxTightWrap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[29]) != 0;
        }
        return z;
    }

    public void setTextboxTightWrap(CTTextboxTightWrap cTTextboxTightWrap) {
        generatedSetterHelperImpl(cTTextboxTightWrap, PROPERTY_QNAME[29], 0, 1);
    }

    public CTTextboxTightWrap addNewTextboxTightWrap() {
        CTTextboxTightWrap add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[29]);
        }
        return add_element_user;
    }

    public void unsetTextboxTightWrap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[29], 0);
        }
    }

    public CTDecimalNumber getOutlineLvl() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[30], 0);
            if (cTDecimalNumber == null) {
                cTDecimalNumber = null;
            }
        }
        return cTDecimalNumber;
    }

    public boolean isSetOutlineLvl() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[30]) != 0;
        }
        return z;
    }

    public void setOutlineLvl(CTDecimalNumber cTDecimalNumber) {
        generatedSetterHelperImpl(cTDecimalNumber, PROPERTY_QNAME[30], 0, 1);
    }

    public CTDecimalNumber addNewOutlineLvl() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[30]);
        }
        return cTDecimalNumber;
    }

    public void unsetOutlineLvl() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[30], 0);
        }
    }

    public CTDecimalNumber getDivId() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[31], 0);
            if (cTDecimalNumber == null) {
                cTDecimalNumber = null;
            }
        }
        return cTDecimalNumber;
    }

    public boolean isSetDivId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[31]) != 0;
        }
        return z;
    }

    public void setDivId(CTDecimalNumber cTDecimalNumber) {
        generatedSetterHelperImpl(cTDecimalNumber, PROPERTY_QNAME[31], 0, 1);
    }

    public CTDecimalNumber addNewDivId() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[31]);
        }
        return cTDecimalNumber;
    }

    public void unsetDivId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[31], 0);
        }
    }

    public CTCnf getCnfStyle() {
        CTCnf cTCnf;
        synchronized (monitor()) {
            check_orphaned();
            cTCnf = (CTCnf) get_store().find_element_user(PROPERTY_QNAME[32], 0);
            if (cTCnf == null) {
                cTCnf = null;
            }
        }
        return cTCnf;
    }

    public boolean isSetCnfStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[32]) != 0;
        }
        return z;
    }

    public void setCnfStyle(CTCnf cTCnf) {
        generatedSetterHelperImpl(cTCnf, PROPERTY_QNAME[32], 0, 1);
    }

    public CTCnf addNewCnfStyle() {
        CTCnf cTCnf;
        synchronized (monitor()) {
            check_orphaned();
            cTCnf = (CTCnf) get_store().add_element_user(PROPERTY_QNAME[32]);
        }
        return cTCnf;
    }

    public void unsetCnfStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[32], 0);
        }
    }
}
