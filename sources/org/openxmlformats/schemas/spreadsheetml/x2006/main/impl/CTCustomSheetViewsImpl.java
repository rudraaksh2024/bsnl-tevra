package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetView;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetViews;

public class CTCustomSheetViewsImpl extends XmlComplexContentImpl implements CTCustomSheetViews {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "customSheetView")};
    private static final long serialVersionUID = 1;

    public CTCustomSheetViewsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTCustomSheetView> getCustomSheetViewList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTCustomSheetViewsImpl$$ExternalSyntheticLambda0(this), new CTCustomSheetViewsImpl$$ExternalSyntheticLambda1(this), new CTCustomSheetViewsImpl$$ExternalSyntheticLambda2(this), new CTCustomSheetViewsImpl$$ExternalSyntheticLambda3(this), new CTCustomSheetViewsImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTCustomSheetView[] getCustomSheetViewArray() {
        return (CTCustomSheetView[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTCustomSheetView[0]);
    }

    public CTCustomSheetView getCustomSheetViewArray(int i) {
        CTCustomSheetView cTCustomSheetView;
        synchronized (monitor()) {
            check_orphaned();
            cTCustomSheetView = (CTCustomSheetView) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTCustomSheetView == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTCustomSheetView;
    }

    public int sizeOfCustomSheetViewArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setCustomSheetViewArray(CTCustomSheetView[] cTCustomSheetViewArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTCustomSheetViewArr, PROPERTY_QNAME[0]);
    }

    public void setCustomSheetViewArray(int i, CTCustomSheetView cTCustomSheetView) {
        generatedSetterHelperImpl(cTCustomSheetView, PROPERTY_QNAME[0], i, 2);
    }

    public CTCustomSheetView insertNewCustomSheetView(int i) {
        CTCustomSheetView cTCustomSheetView;
        synchronized (monitor()) {
            check_orphaned();
            cTCustomSheetView = (CTCustomSheetView) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTCustomSheetView;
    }

    public CTCustomSheetView addNewCustomSheetView() {
        CTCustomSheetView cTCustomSheetView;
        synchronized (monitor()) {
            check_orphaned();
            cTCustomSheetView = (CTCustomSheetView) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTCustomSheetView;
    }

    public void removeCustomSheetView(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
