package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTControl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTControls;

public class CTControlsImpl extends XmlComplexContentImpl implements CTControls {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "control")};
    private static final long serialVersionUID = 1;

    public CTControlsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTControl> getControlList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTControlsImpl$$ExternalSyntheticLambda0(this), new CTControlsImpl$$ExternalSyntheticLambda1(this), new CTControlsImpl$$ExternalSyntheticLambda2(this), new CTControlsImpl$$ExternalSyntheticLambda3(this), new CTControlsImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTControl[] getControlArray() {
        return (CTControl[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTControl[0]);
    }

    public CTControl getControlArray(int i) {
        CTControl cTControl;
        synchronized (monitor()) {
            check_orphaned();
            cTControl = (CTControl) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTControl == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTControl;
    }

    public int sizeOfControlArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setControlArray(CTControl[] cTControlArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTControlArr, PROPERTY_QNAME[0]);
    }

    public void setControlArray(int i, CTControl cTControl) {
        generatedSetterHelperImpl(cTControl, PROPERTY_QNAME[0], i, 2);
    }

    public CTControl insertNewControl(int i) {
        CTControl cTControl;
        synchronized (monitor()) {
            check_orphaned();
            cTControl = (CTControl) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTControl;
    }

    public CTControl addNewControl() {
        CTControl cTControl;
        synchronized (monitor()) {
            check_orphaned();
            cTControl = (CTControl) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTControl;
    }

    public void removeControl(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
