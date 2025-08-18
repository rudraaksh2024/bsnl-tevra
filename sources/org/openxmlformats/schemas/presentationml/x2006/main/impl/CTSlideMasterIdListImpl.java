package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterIdListEntry;

public class CTSlideMasterIdListImpl extends XmlComplexContentImpl implements CTSlideMasterIdList {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "sldMasterId")};
    private static final long serialVersionUID = 1;

    public CTSlideMasterIdListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTSlideMasterIdListEntry> getSldMasterIdList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTSlideMasterIdListImpl$$ExternalSyntheticLambda0(this), new CTSlideMasterIdListImpl$$ExternalSyntheticLambda1(this), new CTSlideMasterIdListImpl$$ExternalSyntheticLambda2(this), new CTSlideMasterIdListImpl$$ExternalSyntheticLambda3(this), new CTSlideMasterIdListImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTSlideMasterIdListEntry[] getSldMasterIdArray() {
        return (CTSlideMasterIdListEntry[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTSlideMasterIdListEntry[0]);
    }

    public CTSlideMasterIdListEntry getSldMasterIdArray(int i) {
        CTSlideMasterIdListEntry cTSlideMasterIdListEntry;
        synchronized (monitor()) {
            check_orphaned();
            cTSlideMasterIdListEntry = (CTSlideMasterIdListEntry) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTSlideMasterIdListEntry == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTSlideMasterIdListEntry;
    }

    public int sizeOfSldMasterIdArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setSldMasterIdArray(CTSlideMasterIdListEntry[] cTSlideMasterIdListEntryArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTSlideMasterIdListEntryArr, PROPERTY_QNAME[0]);
    }

    public void setSldMasterIdArray(int i, CTSlideMasterIdListEntry cTSlideMasterIdListEntry) {
        generatedSetterHelperImpl(cTSlideMasterIdListEntry, PROPERTY_QNAME[0], i, 2);
    }

    public CTSlideMasterIdListEntry insertNewSldMasterId(int i) {
        CTSlideMasterIdListEntry cTSlideMasterIdListEntry;
        synchronized (monitor()) {
            check_orphaned();
            cTSlideMasterIdListEntry = (CTSlideMasterIdListEntry) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTSlideMasterIdListEntry;
    }

    public CTSlideMasterIdListEntry addNewSldMasterId() {
        CTSlideMasterIdListEntry cTSlideMasterIdListEntry;
        synchronized (monitor()) {
            check_orphaned();
            cTSlideMasterIdListEntry = (CTSlideMasterIdListEntry) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTSlideMasterIdListEntry;
    }

    public void removeSldMasterId(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
