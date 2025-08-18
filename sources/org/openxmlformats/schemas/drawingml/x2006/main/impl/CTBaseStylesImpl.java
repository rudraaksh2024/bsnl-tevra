package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBaseStyles;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontScheme;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix;

public class CTBaseStylesImpl extends XmlComplexContentImpl implements CTBaseStyles {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "clrScheme"), new QName(XSSFRelation.NS_DRAWINGML, "fontScheme"), new QName(XSSFRelation.NS_DRAWINGML, "fmtScheme"), new QName(XSSFRelation.NS_DRAWINGML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTBaseStylesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTColorScheme getClrScheme() {
        CTColorScheme cTColorScheme;
        synchronized (monitor()) {
            check_orphaned();
            cTColorScheme = (CTColorScheme) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTColorScheme == null) {
                cTColorScheme = null;
            }
        }
        return cTColorScheme;
    }

    public void setClrScheme(CTColorScheme cTColorScheme) {
        generatedSetterHelperImpl(cTColorScheme, PROPERTY_QNAME[0], 0, 1);
    }

    public CTColorScheme addNewClrScheme() {
        CTColorScheme cTColorScheme;
        synchronized (monitor()) {
            check_orphaned();
            cTColorScheme = (CTColorScheme) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTColorScheme;
    }

    public CTFontScheme getFontScheme() {
        CTFontScheme cTFontScheme;
        synchronized (monitor()) {
            check_orphaned();
            cTFontScheme = (CTFontScheme) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTFontScheme == null) {
                cTFontScheme = null;
            }
        }
        return cTFontScheme;
    }

    public void setFontScheme(CTFontScheme cTFontScheme) {
        generatedSetterHelperImpl(cTFontScheme, PROPERTY_QNAME[1], 0, 1);
    }

    public CTFontScheme addNewFontScheme() {
        CTFontScheme cTFontScheme;
        synchronized (monitor()) {
            check_orphaned();
            cTFontScheme = (CTFontScheme) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTFontScheme;
    }

    public CTStyleMatrix getFmtScheme() {
        CTStyleMatrix cTStyleMatrix;
        synchronized (monitor()) {
            check_orphaned();
            cTStyleMatrix = (CTStyleMatrix) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTStyleMatrix == null) {
                cTStyleMatrix = null;
            }
        }
        return cTStyleMatrix;
    }

    public void setFmtScheme(CTStyleMatrix cTStyleMatrix) {
        generatedSetterHelperImpl(cTStyleMatrix, PROPERTY_QNAME[2], 0, 1);
    }

    public CTStyleMatrix addNewFmtScheme() {
        CTStyleMatrix cTStyleMatrix;
        synchronized (monitor()) {
            check_orphaned();
            cTStyleMatrix = (CTStyleMatrix) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTStyleMatrix;
    }

    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTOfficeArtExtensionList == null) {
                cTOfficeArtExtensionList = null;
            }
        }
        return cTOfficeArtExtensionList;
    }

    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        generatedSetterHelperImpl(cTOfficeArtExtensionList, PROPERTY_QNAME[3], 0, 1);
    }

    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTOfficeArtExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
