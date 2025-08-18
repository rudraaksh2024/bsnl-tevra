package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetView;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetViews;

public class CTSheetViewsImpl extends XmlComplexContentImpl implements CTSheetViews {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "sheetView"), new QName(XSSFRelation.NS_SPREADSHEETML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTSheetViewsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTSheetView> getSheetViewList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTSheetViewsImpl$$ExternalSyntheticLambda0(this), new CTSheetViewsImpl$$ExternalSyntheticLambda1(this), new CTSheetViewsImpl$$ExternalSyntheticLambda2(this), new CTSheetViewsImpl$$ExternalSyntheticLambda3(this), new CTSheetViewsImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTSheetView[] getSheetViewArray() {
        return (CTSheetView[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTSheetView[0]);
    }

    public CTSheetView getSheetViewArray(int i) {
        CTSheetView cTSheetView;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetView = (CTSheetView) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTSheetView == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTSheetView;
    }

    public int sizeOfSheetViewArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setSheetViewArray(CTSheetView[] cTSheetViewArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTSheetViewArr, PROPERTY_QNAME[0]);
    }

    public void setSheetViewArray(int i, CTSheetView cTSheetView) {
        generatedSetterHelperImpl(cTSheetView, PROPERTY_QNAME[0], i, 2);
    }

    public CTSheetView insertNewSheetView(int i) {
        CTSheetView cTSheetView;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetView = (CTSheetView) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTSheetView;
    }

    public CTSheetView addNewSheetView() {
        CTSheetView cTSheetView;
        synchronized (monitor()) {
            check_orphaned();
            cTSheetView = (CTSheetView) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTSheetView;
    }

    public void removeSheetView(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTExtensionList == null) {
                cTExtensionList = null;
            }
        }
        return cTExtensionList;
    }

    public boolean isSetExtLst() {
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

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[1], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
