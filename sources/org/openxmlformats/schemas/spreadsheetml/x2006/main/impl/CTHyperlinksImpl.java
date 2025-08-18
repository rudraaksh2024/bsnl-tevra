package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHyperlinks;

public class CTHyperlinksImpl extends XmlComplexContentImpl implements CTHyperlinks {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "hyperlink")};
    private static final long serialVersionUID = 1;

    public CTHyperlinksImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTHyperlink> getHyperlinkList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTHyperlinksImpl$$ExternalSyntheticLambda0(this), new CTHyperlinksImpl$$ExternalSyntheticLambda1(this), new CTHyperlinksImpl$$ExternalSyntheticLambda2(this), new CTHyperlinksImpl$$ExternalSyntheticLambda3(this), new CTHyperlinksImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTHyperlink[] getHyperlinkArray() {
        return (CTHyperlink[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTHyperlink[0]);
    }

    public CTHyperlink getHyperlinkArray(int i) {
        CTHyperlink cTHyperlink;
        synchronized (monitor()) {
            check_orphaned();
            cTHyperlink = (CTHyperlink) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTHyperlink == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTHyperlink;
    }

    public int sizeOfHyperlinkArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setHyperlinkArray(CTHyperlink[] cTHyperlinkArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTHyperlinkArr, PROPERTY_QNAME[0]);
    }

    public void setHyperlinkArray(int i, CTHyperlink cTHyperlink) {
        generatedSetterHelperImpl(cTHyperlink, PROPERTY_QNAME[0], i, 2);
    }

    public CTHyperlink insertNewHyperlink(int i) {
        CTHyperlink cTHyperlink;
        synchronized (monitor()) {
            check_orphaned();
            cTHyperlink = (CTHyperlink) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTHyperlink;
    }

    public CTHyperlink addNewHyperlink() {
        CTHyperlink cTHyperlink;
        synchronized (monitor()) {
            check_orphaned();
            cTHyperlink = (CTHyperlink) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTHyperlink;
    }

    public void removeHyperlink(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
