package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontReference;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrixReference;

public class CTShapeStyleImpl extends XmlComplexContentImpl implements CTShapeStyle {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "lnRef"), new QName(XSSFRelation.NS_DRAWINGML, "fillRef"), new QName(XSSFRelation.NS_DRAWINGML, "effectRef"), new QName(XSSFRelation.NS_DRAWINGML, "fontRef")};
    private static final long serialVersionUID = 1;

    public CTShapeStyleImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTStyleMatrixReference getLnRef() {
        CTStyleMatrixReference cTStyleMatrixReference;
        synchronized (monitor()) {
            check_orphaned();
            cTStyleMatrixReference = (CTStyleMatrixReference) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTStyleMatrixReference == null) {
                cTStyleMatrixReference = null;
            }
        }
        return cTStyleMatrixReference;
    }

    public void setLnRef(CTStyleMatrixReference cTStyleMatrixReference) {
        generatedSetterHelperImpl(cTStyleMatrixReference, PROPERTY_QNAME[0], 0, 1);
    }

    public CTStyleMatrixReference addNewLnRef() {
        CTStyleMatrixReference cTStyleMatrixReference;
        synchronized (monitor()) {
            check_orphaned();
            cTStyleMatrixReference = (CTStyleMatrixReference) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTStyleMatrixReference;
    }

    public CTStyleMatrixReference getFillRef() {
        CTStyleMatrixReference cTStyleMatrixReference;
        synchronized (monitor()) {
            check_orphaned();
            cTStyleMatrixReference = (CTStyleMatrixReference) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTStyleMatrixReference == null) {
                cTStyleMatrixReference = null;
            }
        }
        return cTStyleMatrixReference;
    }

    public void setFillRef(CTStyleMatrixReference cTStyleMatrixReference) {
        generatedSetterHelperImpl(cTStyleMatrixReference, PROPERTY_QNAME[1], 0, 1);
    }

    public CTStyleMatrixReference addNewFillRef() {
        CTStyleMatrixReference cTStyleMatrixReference;
        synchronized (monitor()) {
            check_orphaned();
            cTStyleMatrixReference = (CTStyleMatrixReference) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTStyleMatrixReference;
    }

    public CTStyleMatrixReference getEffectRef() {
        CTStyleMatrixReference cTStyleMatrixReference;
        synchronized (monitor()) {
            check_orphaned();
            cTStyleMatrixReference = (CTStyleMatrixReference) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTStyleMatrixReference == null) {
                cTStyleMatrixReference = null;
            }
        }
        return cTStyleMatrixReference;
    }

    public void setEffectRef(CTStyleMatrixReference cTStyleMatrixReference) {
        generatedSetterHelperImpl(cTStyleMatrixReference, PROPERTY_QNAME[2], 0, 1);
    }

    public CTStyleMatrixReference addNewEffectRef() {
        CTStyleMatrixReference cTStyleMatrixReference;
        synchronized (monitor()) {
            check_orphaned();
            cTStyleMatrixReference = (CTStyleMatrixReference) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTStyleMatrixReference;
    }

    public CTFontReference getFontRef() {
        CTFontReference cTFontReference;
        synchronized (monitor()) {
            check_orphaned();
            cTFontReference = (CTFontReference) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTFontReference == null) {
                cTFontReference = null;
            }
        }
        return cTFontReference;
    }

    public void setFontRef(CTFontReference cTFontReference) {
        generatedSetterHelperImpl(cTFontReference, PROPERTY_QNAME[3], 0, 1);
    }

    public CTFontReference addNewFontRef() {
        CTFontReference cTFontReference;
        synchronized (monitor()) {
            check_orphaned();
            cTFontReference = (CTFontReference) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTFontReference;
    }
}
