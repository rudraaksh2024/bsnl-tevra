package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.ss.util.CellUtil;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontDataId;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry;

public class CTEmbeddedFontListEntryImpl extends XmlComplexContentImpl implements CTEmbeddedFontListEntry {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", CellUtil.FONT), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "regular"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "bold"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "italic"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "boldItalic")};
    private static final long serialVersionUID = 1;

    public CTEmbeddedFontListEntryImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTTextFont getFont() {
        CTTextFont cTTextFont;
        synchronized (monitor()) {
            check_orphaned();
            cTTextFont = (CTTextFont) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTTextFont == null) {
                cTTextFont = null;
            }
        }
        return cTTextFont;
    }

    public void setFont(CTTextFont cTTextFont) {
        generatedSetterHelperImpl(cTTextFont, PROPERTY_QNAME[0], 0, 1);
    }

    public CTTextFont addNewFont() {
        CTTextFont cTTextFont;
        synchronized (monitor()) {
            check_orphaned();
            cTTextFont = (CTTextFont) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTTextFont;
    }

    public CTEmbeddedFontDataId getRegular() {
        CTEmbeddedFontDataId cTEmbeddedFontDataId;
        synchronized (monitor()) {
            check_orphaned();
            cTEmbeddedFontDataId = (CTEmbeddedFontDataId) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTEmbeddedFontDataId == null) {
                cTEmbeddedFontDataId = null;
            }
        }
        return cTEmbeddedFontDataId;
    }

    public boolean isSetRegular() {
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

    public void setRegular(CTEmbeddedFontDataId cTEmbeddedFontDataId) {
        generatedSetterHelperImpl(cTEmbeddedFontDataId, PROPERTY_QNAME[1], 0, 1);
    }

    public CTEmbeddedFontDataId addNewRegular() {
        CTEmbeddedFontDataId cTEmbeddedFontDataId;
        synchronized (monitor()) {
            check_orphaned();
            cTEmbeddedFontDataId = (CTEmbeddedFontDataId) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTEmbeddedFontDataId;
    }

    public void unsetRegular() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTEmbeddedFontDataId getBold() {
        CTEmbeddedFontDataId cTEmbeddedFontDataId;
        synchronized (monitor()) {
            check_orphaned();
            cTEmbeddedFontDataId = (CTEmbeddedFontDataId) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTEmbeddedFontDataId == null) {
                cTEmbeddedFontDataId = null;
            }
        }
        return cTEmbeddedFontDataId;
    }

    public boolean isSetBold() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setBold(CTEmbeddedFontDataId cTEmbeddedFontDataId) {
        generatedSetterHelperImpl(cTEmbeddedFontDataId, PROPERTY_QNAME[2], 0, 1);
    }

    public CTEmbeddedFontDataId addNewBold() {
        CTEmbeddedFontDataId cTEmbeddedFontDataId;
        synchronized (monitor()) {
            check_orphaned();
            cTEmbeddedFontDataId = (CTEmbeddedFontDataId) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTEmbeddedFontDataId;
    }

    public void unsetBold() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTEmbeddedFontDataId getItalic() {
        CTEmbeddedFontDataId cTEmbeddedFontDataId;
        synchronized (monitor()) {
            check_orphaned();
            cTEmbeddedFontDataId = (CTEmbeddedFontDataId) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTEmbeddedFontDataId == null) {
                cTEmbeddedFontDataId = null;
            }
        }
        return cTEmbeddedFontDataId;
    }

    public boolean isSetItalic() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setItalic(CTEmbeddedFontDataId cTEmbeddedFontDataId) {
        generatedSetterHelperImpl(cTEmbeddedFontDataId, PROPERTY_QNAME[3], 0, 1);
    }

    public CTEmbeddedFontDataId addNewItalic() {
        CTEmbeddedFontDataId cTEmbeddedFontDataId;
        synchronized (monitor()) {
            check_orphaned();
            cTEmbeddedFontDataId = (CTEmbeddedFontDataId) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTEmbeddedFontDataId;
    }

    public void unsetItalic() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTEmbeddedFontDataId getBoldItalic() {
        CTEmbeddedFontDataId cTEmbeddedFontDataId;
        synchronized (monitor()) {
            check_orphaned();
            cTEmbeddedFontDataId = (CTEmbeddedFontDataId) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTEmbeddedFontDataId == null) {
                cTEmbeddedFontDataId = null;
            }
        }
        return cTEmbeddedFontDataId;
    }

    public boolean isSetBoldItalic() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setBoldItalic(CTEmbeddedFontDataId cTEmbeddedFontDataId) {
        generatedSetterHelperImpl(cTEmbeddedFontDataId, PROPERTY_QNAME[4], 0, 1);
    }

    public CTEmbeddedFontDataId addNewBoldItalic() {
        CTEmbeddedFontDataId cTEmbeddedFontDataId;
        synchronized (monitor()) {
            check_orphaned();
            cTEmbeddedFontDataId = (CTEmbeddedFontDataId) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTEmbeddedFontDataId;
    }

    public void unsetBoldItalic() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }
}
