package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalcCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalcChain;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;

public class CTCalcChainImpl extends XmlComplexContentImpl implements CTCalcChain {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "c"), new QName(XSSFRelation.NS_SPREADSHEETML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTCalcChainImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTCalcCell> getCList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTCalcChainImpl$$ExternalSyntheticLambda0(this), new CTCalcChainImpl$$ExternalSyntheticLambda1(this), new CTCalcChainImpl$$ExternalSyntheticLambda2(this), new CTCalcChainImpl$$ExternalSyntheticLambda3(this), new CTCalcChainImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTCalcCell[] getCArray() {
        return (CTCalcCell[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTCalcCell[0]);
    }

    public CTCalcCell getCArray(int i) {
        CTCalcCell cTCalcCell;
        synchronized (monitor()) {
            check_orphaned();
            cTCalcCell = (CTCalcCell) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTCalcCell == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTCalcCell;
    }

    public int sizeOfCArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setCArray(CTCalcCell[] cTCalcCellArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTCalcCellArr, PROPERTY_QNAME[0]);
    }

    public void setCArray(int i, CTCalcCell cTCalcCell) {
        generatedSetterHelperImpl(cTCalcCell, PROPERTY_QNAME[0], i, 2);
    }

    public CTCalcCell insertNewC(int i) {
        CTCalcCell cTCalcCell;
        synchronized (monitor()) {
            check_orphaned();
            cTCalcCell = (CTCalcCell) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTCalcCell;
    }

    public CTCalcCell addNewC() {
        CTCalcCell cTCalcCell;
        synchronized (monitor()) {
            check_orphaned();
            cTCalcCell = (CTCalcCell) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTCalcCell;
    }

    public void removeC(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTExtensionList == null) {
                cTExtensionList = null;
            }
        }
        return cTExtensionList;
    }

    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[1], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
