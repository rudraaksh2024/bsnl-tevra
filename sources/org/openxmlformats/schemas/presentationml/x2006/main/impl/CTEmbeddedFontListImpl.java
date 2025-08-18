package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry;

public class CTEmbeddedFontListImpl extends XmlComplexContentImpl implements CTEmbeddedFontList {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "embeddedFont")};
    private static final long serialVersionUID = 1;

    public CTEmbeddedFontListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTEmbeddedFontListEntry> getEmbeddedFontList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEmbeddedFontListImpl$$ExternalSyntheticLambda0(this), new CTEmbeddedFontListImpl$$ExternalSyntheticLambda1(this), new CTEmbeddedFontListImpl$$ExternalSyntheticLambda2(this), new CTEmbeddedFontListImpl$$ExternalSyntheticLambda3(this), new CTEmbeddedFontListImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTEmbeddedFontListEntry[] getEmbeddedFontArray() {
        return (CTEmbeddedFontListEntry[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTEmbeddedFontListEntry[0]);
    }

    public CTEmbeddedFontListEntry getEmbeddedFontArray(int i) {
        CTEmbeddedFontListEntry cTEmbeddedFontListEntry;
        synchronized (monitor()) {
            check_orphaned();
            cTEmbeddedFontListEntry = (CTEmbeddedFontListEntry) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTEmbeddedFontListEntry == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTEmbeddedFontListEntry;
    }

    public int sizeOfEmbeddedFontArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setEmbeddedFontArray(CTEmbeddedFontListEntry[] cTEmbeddedFontListEntryArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTEmbeddedFontListEntryArr, PROPERTY_QNAME[0]);
    }

    public void setEmbeddedFontArray(int i, CTEmbeddedFontListEntry cTEmbeddedFontListEntry) {
        generatedSetterHelperImpl(cTEmbeddedFontListEntry, PROPERTY_QNAME[0], i, 2);
    }

    public CTEmbeddedFontListEntry insertNewEmbeddedFont(int i) {
        CTEmbeddedFontListEntry cTEmbeddedFontListEntry;
        synchronized (monitor()) {
            check_orphaned();
            cTEmbeddedFontListEntry = (CTEmbeddedFontListEntry) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTEmbeddedFontListEntry;
    }

    public CTEmbeddedFontListEntry addNewEmbeddedFont() {
        CTEmbeddedFontListEntry cTEmbeddedFontListEntry;
        synchronized (monitor()) {
            check_orphaned();
            cTEmbeddedFontListEntry = (CTEmbeddedFontListEntry) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTEmbeddedFontListEntry;
    }

    public void removeEmbeddedFont(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
