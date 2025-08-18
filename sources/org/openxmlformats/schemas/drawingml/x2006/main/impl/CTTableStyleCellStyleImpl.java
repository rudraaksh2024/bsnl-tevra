package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCell3D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrixReference;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellBorderStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle;

public class CTTableStyleCellStyleImpl extends XmlComplexContentImpl implements CTTableStyleCellStyle {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "tcBdr"), new QName(XSSFRelation.NS_DRAWINGML, "fill"), new QName(XSSFRelation.NS_DRAWINGML, "fillRef"), new QName(XSSFRelation.NS_DRAWINGML, "cell3D")};
    private static final long serialVersionUID = 1;

    public CTTableStyleCellStyleImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTTableCellBorderStyle getTcBdr() {
        CTTableCellBorderStyle find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetTcBdr() {
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

    public void setTcBdr(CTTableCellBorderStyle cTTableCellBorderStyle) {
        generatedSetterHelperImpl(cTTableCellBorderStyle, PROPERTY_QNAME[0], 0, 1);
    }

    public CTTableCellBorderStyle addNewTcBdr() {
        CTTableCellBorderStyle add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return add_element_user;
    }

    public void unsetTcBdr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTFillProperties getFill() {
        CTFillProperties cTFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTFillProperties = (CTFillProperties) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTFillProperties == null) {
                cTFillProperties = null;
            }
        }
        return cTFillProperties;
    }

    public boolean isSetFill() {
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

    public void setFill(CTFillProperties cTFillProperties) {
        generatedSetterHelperImpl(cTFillProperties, PROPERTY_QNAME[1], 0, 1);
    }

    public CTFillProperties addNewFill() {
        CTFillProperties cTFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTFillProperties = (CTFillProperties) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTFillProperties;
    }

    public void unsetFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTStyleMatrixReference getFillRef() {
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

    public boolean isSetFillRef() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setFillRef(CTStyleMatrixReference cTStyleMatrixReference) {
        generatedSetterHelperImpl(cTStyleMatrixReference, PROPERTY_QNAME[2], 0, 1);
    }

    public CTStyleMatrixReference addNewFillRef() {
        CTStyleMatrixReference cTStyleMatrixReference;
        synchronized (monitor()) {
            check_orphaned();
            cTStyleMatrixReference = (CTStyleMatrixReference) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTStyleMatrixReference;
    }

    public void unsetFillRef() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTCell3D getCell3D() {
        CTCell3D find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetCell3D() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setCell3D(CTCell3D cTCell3D) {
        generatedSetterHelperImpl(cTCell3D, PROPERTY_QNAME[3], 0, 1);
    }

    public CTCell3D addNewCell3D() {
        CTCell3D add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return add_element_user;
    }

    public void unsetCell3D() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
