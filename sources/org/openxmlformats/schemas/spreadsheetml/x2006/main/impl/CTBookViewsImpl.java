package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBookView;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBookViews;

public class CTBookViewsImpl extends XmlComplexContentImpl implements CTBookViews {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "workbookView")};
    private static final long serialVersionUID = 1;

    public CTBookViewsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTBookView> getWorkbookViewList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBookViewsImpl$$ExternalSyntheticLambda0(this), new CTBookViewsImpl$$ExternalSyntheticLambda1(this), new CTBookViewsImpl$$ExternalSyntheticLambda2(this), new CTBookViewsImpl$$ExternalSyntheticLambda3(this), new CTBookViewsImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTBookView[] getWorkbookViewArray() {
        return (CTBookView[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTBookView[0]);
    }

    public CTBookView getWorkbookViewArray(int i) {
        CTBookView cTBookView;
        synchronized (monitor()) {
            check_orphaned();
            cTBookView = (CTBookView) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTBookView == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTBookView;
    }

    public int sizeOfWorkbookViewArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setWorkbookViewArray(CTBookView[] cTBookViewArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTBookViewArr, PROPERTY_QNAME[0]);
    }

    public void setWorkbookViewArray(int i, CTBookView cTBookView) {
        generatedSetterHelperImpl(cTBookView, PROPERTY_QNAME[0], i, 2);
    }

    public CTBookView insertNewWorkbookView(int i) {
        CTBookView cTBookView;
        synchronized (monitor()) {
            check_orphaned();
            cTBookView = (CTBookView) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTBookView;
    }

    public CTBookView addNewWorkbookView() {
        CTBookView cTBookView;
        synchronized (monitor()) {
            check_orphaned();
            cTBookView = (CTBookView) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTBookView;
    }

    public void removeWorkbookView(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
