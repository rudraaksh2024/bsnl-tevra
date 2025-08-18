package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStopList;

public class CTTextTabStopListImpl extends XmlComplexContentImpl implements CTTextTabStopList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "tab")};
    private static final long serialVersionUID = 1;

    public CTTextTabStopListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTTextTabStop> getTabList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTextTabStopListImpl$$ExternalSyntheticLambda0(this), new CTTextTabStopListImpl$$ExternalSyntheticLambda1(this), new CTTextTabStopListImpl$$ExternalSyntheticLambda2(this), new CTTextTabStopListImpl$$ExternalSyntheticLambda3(this), new CTTextTabStopListImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTTextTabStop[] getTabArray() {
        return (CTTextTabStop[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTTextTabStop[0]);
    }

    public CTTextTabStop getTabArray(int i) {
        CTTextTabStop cTTextTabStop;
        synchronized (monitor()) {
            check_orphaned();
            cTTextTabStop = (CTTextTabStop) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTTextTabStop == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTTextTabStop;
    }

    public int sizeOfTabArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setTabArray(CTTextTabStop[] cTTextTabStopArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTextTabStopArr, PROPERTY_QNAME[0]);
    }

    public void setTabArray(int i, CTTextTabStop cTTextTabStop) {
        generatedSetterHelperImpl(cTTextTabStop, PROPERTY_QNAME[0], i, 2);
    }

    public CTTextTabStop insertNewTab(int i) {
        CTTextTabStop cTTextTabStop;
        synchronized (monitor()) {
            check_orphaned();
            cTTextTabStop = (CTTextTabStop) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTTextTabStop;
    }

    public CTTextTabStop addNewTab() {
        CTTextTabStop cTTextTabStop;
        synchronized (monitor()) {
            check_orphaned();
            cTTextTabStop = (CTTextTabStop) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTTextTabStop;
    }

    public void removeTab(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
