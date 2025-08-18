package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabs;

public class CTTabsImpl extends XmlComplexContentImpl implements CTTabs {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tab")};
    private static final long serialVersionUID = 1;

    public CTTabsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTTabStop> getTabList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTabsImpl$$ExternalSyntheticLambda0(this), new CTTabsImpl$$ExternalSyntheticLambda1(this), new CTTabsImpl$$ExternalSyntheticLambda2(this), new CTTabsImpl$$ExternalSyntheticLambda3(this), new CTTabsImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTTabStop[] getTabArray() {
        return (CTTabStop[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTTabStop[0]);
    }

    public CTTabStop getTabArray(int i) {
        CTTabStop cTTabStop;
        synchronized (monitor()) {
            check_orphaned();
            cTTabStop = (CTTabStop) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTTabStop == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTTabStop;
    }

    public int sizeOfTabArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setTabArray(CTTabStop[] cTTabStopArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTabStopArr, PROPERTY_QNAME[0]);
    }

    public void setTabArray(int i, CTTabStop cTTabStop) {
        generatedSetterHelperImpl(cTTabStop, PROPERTY_QNAME[0], i, 2);
    }

    public CTTabStop insertNewTab(int i) {
        CTTabStop cTTabStop;
        synchronized (monitor()) {
            check_orphaned();
            cTTabStop = (CTTabStop) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTTabStop;
    }

    public CTTabStop addNewTab() {
        CTTabStop cTTabStop;
        synchronized (monitor()) {
            check_orphaned();
            cTTabStop = (CTTabStop) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTTabStop;
    }

    public void removeTab(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
