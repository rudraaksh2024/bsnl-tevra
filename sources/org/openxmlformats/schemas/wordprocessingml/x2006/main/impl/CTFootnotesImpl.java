package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFootnotes;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdn;

public class CTFootnotesImpl extends XmlComplexContentImpl implements CTFootnotes {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "footnote")};
    private static final long serialVersionUID = 1;

    public CTFootnotesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTFtnEdn> getFootnoteList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTFootnotesImpl$$ExternalSyntheticLambda0(this), new CTFootnotesImpl$$ExternalSyntheticLambda1(this), new CTFootnotesImpl$$ExternalSyntheticLambda2(this), new CTFootnotesImpl$$ExternalSyntheticLambda3(this), new CTFootnotesImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTFtnEdn[] getFootnoteArray() {
        return (CTFtnEdn[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTFtnEdn[0]);
    }

    public CTFtnEdn getFootnoteArray(int i) {
        CTFtnEdn cTFtnEdn;
        synchronized (monitor()) {
            check_orphaned();
            cTFtnEdn = (CTFtnEdn) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTFtnEdn == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTFtnEdn;
    }

    public int sizeOfFootnoteArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setFootnoteArray(CTFtnEdn[] cTFtnEdnArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTFtnEdnArr, PROPERTY_QNAME[0]);
    }

    public void setFootnoteArray(int i, CTFtnEdn cTFtnEdn) {
        generatedSetterHelperImpl(cTFtnEdn, PROPERTY_QNAME[0], i, 2);
    }

    public CTFtnEdn insertNewFootnote(int i) {
        CTFtnEdn cTFtnEdn;
        synchronized (monitor()) {
            check_orphaned();
            cTFtnEdn = (CTFtnEdn) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTFtnEdn;
    }

    public CTFtnEdn addNewFootnote() {
        CTFtnEdn cTFtnEdn;
        synchronized (monitor()) {
            check_orphaned();
            cTFtnEdn = (CTFtnEdn) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTFtnEdn;
    }

    public void removeFootnote(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
