package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomWorkbookView;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomWorkbookViews;

public class CTCustomWorkbookViewsImpl extends XmlComplexContentImpl implements CTCustomWorkbookViews {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "customWorkbookView")};
    private static final long serialVersionUID = 1;

    public CTCustomWorkbookViewsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTCustomWorkbookView> getCustomWorkbookViewList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTCustomWorkbookViewsImpl$$ExternalSyntheticLambda0(this), new CTCustomWorkbookViewsImpl$$ExternalSyntheticLambda1(this), new CTCustomWorkbookViewsImpl$$ExternalSyntheticLambda2(this), new CTCustomWorkbookViewsImpl$$ExternalSyntheticLambda3(this), new CTCustomWorkbookViewsImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTCustomWorkbookView[] getCustomWorkbookViewArray() {
        return (CTCustomWorkbookView[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTCustomWorkbookView[0]);
    }

    public CTCustomWorkbookView getCustomWorkbookViewArray(int i) {
        CTCustomWorkbookView cTCustomWorkbookView;
        synchronized (monitor()) {
            check_orphaned();
            cTCustomWorkbookView = (CTCustomWorkbookView) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTCustomWorkbookView == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTCustomWorkbookView;
    }

    public int sizeOfCustomWorkbookViewArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setCustomWorkbookViewArray(CTCustomWorkbookView[] cTCustomWorkbookViewArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTCustomWorkbookViewArr, PROPERTY_QNAME[0]);
    }

    public void setCustomWorkbookViewArray(int i, CTCustomWorkbookView cTCustomWorkbookView) {
        generatedSetterHelperImpl(cTCustomWorkbookView, PROPERTY_QNAME[0], i, 2);
    }

    public CTCustomWorkbookView insertNewCustomWorkbookView(int i) {
        CTCustomWorkbookView cTCustomWorkbookView;
        synchronized (monitor()) {
            check_orphaned();
            cTCustomWorkbookView = (CTCustomWorkbookView) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTCustomWorkbookView;
    }

    public CTCustomWorkbookView addNewCustomWorkbookView() {
        CTCustomWorkbookView cTCustomWorkbookView;
        synchronized (monitor()) {
            check_orphaned();
            cTCustomWorkbookView = (CTCustomWorkbookView) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTCustomWorkbookView;
    }

    public void removeCustomWorkbookView(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
