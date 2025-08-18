package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSite;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSiteList;

public class CTConnectionSiteListImpl extends XmlComplexContentImpl implements CTConnectionSiteList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "cxn")};
    private static final long serialVersionUID = 1;

    public CTConnectionSiteListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTConnectionSite> getCxnList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTConnectionSiteListImpl$$ExternalSyntheticLambda0(this), new CTConnectionSiteListImpl$$ExternalSyntheticLambda1(this), new CTConnectionSiteListImpl$$ExternalSyntheticLambda2(this), new CTConnectionSiteListImpl$$ExternalSyntheticLambda3(this), new CTConnectionSiteListImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTConnectionSite[] getCxnArray() {
        return (CTConnectionSite[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTConnectionSite[0]);
    }

    public CTConnectionSite getCxnArray(int i) {
        CTConnectionSite cTConnectionSite;
        synchronized (monitor()) {
            check_orphaned();
            cTConnectionSite = (CTConnectionSite) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTConnectionSite == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTConnectionSite;
    }

    public int sizeOfCxnArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setCxnArray(CTConnectionSite[] cTConnectionSiteArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTConnectionSiteArr, PROPERTY_QNAME[0]);
    }

    public void setCxnArray(int i, CTConnectionSite cTConnectionSite) {
        generatedSetterHelperImpl(cTConnectionSite, PROPERTY_QNAME[0], i, 2);
    }

    public CTConnectionSite insertNewCxn(int i) {
        CTConnectionSite cTConnectionSite;
        synchronized (monitor()) {
            check_orphaned();
            cTConnectionSite = (CTConnectionSite) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTConnectionSite;
    }

    public CTConnectionSite addNewCxn() {
        CTConnectionSite cTConnectionSite;
        synchronized (monitor()) {
            check_orphaned();
            cTConnectionSite = (CTConnectionSite) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTConnectionSite;
    }

    public void removeCxn(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
