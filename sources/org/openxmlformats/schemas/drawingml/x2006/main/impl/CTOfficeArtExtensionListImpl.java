package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtension;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;

public class CTOfficeArtExtensionListImpl extends XmlComplexContentImpl implements CTOfficeArtExtensionList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "ext")};
    private static final long serialVersionUID = 1;

    public CTOfficeArtExtensionListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTOfficeArtExtension> getExtList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTOfficeArtExtensionListImpl$$ExternalSyntheticLambda0(this), new CTOfficeArtExtensionListImpl$$ExternalSyntheticLambda1(this), new CTOfficeArtExtensionListImpl$$ExternalSyntheticLambda2(this), new CTOfficeArtExtensionListImpl$$ExternalSyntheticLambda3(this), new CTOfficeArtExtensionListImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTOfficeArtExtension[] getExtArray() {
        return (CTOfficeArtExtension[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTOfficeArtExtension[0]);
    }

    public CTOfficeArtExtension getExtArray(int i) {
        CTOfficeArtExtension cTOfficeArtExtension;
        synchronized (monitor()) {
            check_orphaned();
            cTOfficeArtExtension = (CTOfficeArtExtension) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTOfficeArtExtension == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOfficeArtExtension;
    }

    public int sizeOfExtArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setExtArray(CTOfficeArtExtension[] cTOfficeArtExtensionArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOfficeArtExtensionArr, PROPERTY_QNAME[0]);
    }

    public void setExtArray(int i, CTOfficeArtExtension cTOfficeArtExtension) {
        generatedSetterHelperImpl(cTOfficeArtExtension, PROPERTY_QNAME[0], i, 2);
    }

    public CTOfficeArtExtension insertNewExt(int i) {
        CTOfficeArtExtension cTOfficeArtExtension;
        synchronized (monitor()) {
            check_orphaned();
            cTOfficeArtExtension = (CTOfficeArtExtension) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTOfficeArtExtension;
    }

    public CTOfficeArtExtension addNewExt() {
        CTOfficeArtExtension cTOfficeArtExtension;
        synchronized (monitor()) {
            check_orphaned();
            cTOfficeArtExtension = (CTOfficeArtExtension) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTOfficeArtExtension;
    }

    public void removeExt(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
