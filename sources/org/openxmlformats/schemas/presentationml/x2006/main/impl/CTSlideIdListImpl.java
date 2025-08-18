package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdListEntry;

public class CTSlideIdListImpl extends XmlComplexContentImpl implements CTSlideIdList {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "sldId")};
    private static final long serialVersionUID = 1;

    public CTSlideIdListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTSlideIdListEntry> getSldIdList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTSlideIdListImpl$$ExternalSyntheticLambda0(this), new CTSlideIdListImpl$$ExternalSyntheticLambda1(this), new CTSlideIdListImpl$$ExternalSyntheticLambda2(this), new CTSlideIdListImpl$$ExternalSyntheticLambda3(this), new CTSlideIdListImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTSlideIdListEntry[] getSldIdArray() {
        return (CTSlideIdListEntry[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTSlideIdListEntry[0]);
    }

    public CTSlideIdListEntry getSldIdArray(int i) {
        CTSlideIdListEntry cTSlideIdListEntry;
        synchronized (monitor()) {
            check_orphaned();
            cTSlideIdListEntry = (CTSlideIdListEntry) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTSlideIdListEntry == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTSlideIdListEntry;
    }

    public int sizeOfSldIdArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setSldIdArray(CTSlideIdListEntry[] cTSlideIdListEntryArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTSlideIdListEntryArr, PROPERTY_QNAME[0]);
    }

    public void setSldIdArray(int i, CTSlideIdListEntry cTSlideIdListEntry) {
        generatedSetterHelperImpl(cTSlideIdListEntry, PROPERTY_QNAME[0], i, 2);
    }

    public CTSlideIdListEntry insertNewSldId(int i) {
        CTSlideIdListEntry cTSlideIdListEntry;
        synchronized (monitor()) {
            check_orphaned();
            cTSlideIdListEntry = (CTSlideIdListEntry) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTSlideIdListEntry;
    }

    public CTSlideIdListEntry addNewSldId() {
        CTSlideIdListEntry cTSlideIdListEntry;
        synchronized (monitor()) {
            check_orphaned();
            cTSlideIdListEntry = (CTSlideIdListEntry) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTSlideIdListEntry;
    }

    public void removeSldId(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
