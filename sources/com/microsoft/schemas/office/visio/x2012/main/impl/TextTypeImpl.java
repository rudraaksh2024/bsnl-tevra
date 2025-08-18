package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.CpType;
import com.microsoft.schemas.office.visio.x2012.main.FldType;
import com.microsoft.schemas.office.visio.x2012.main.PpType;
import com.microsoft.schemas.office.visio.x2012.main.TextType;
import com.microsoft.schemas.office.visio.x2012.main.TpType;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class TextTypeImpl extends XmlComplexContentImpl implements TextType {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "cp"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "pp"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "tp"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "fld")};
    private static final long serialVersionUID = 1;

    public TextTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CpType> getCpList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new TextTypeImpl$$ExternalSyntheticLambda6(this), new TextTypeImpl$$ExternalSyntheticLambda7(this), new TextTypeImpl$$ExternalSyntheticLambda8(this), new TextTypeImpl$$ExternalSyntheticLambda9(this), new TextTypeImpl$$ExternalSyntheticLambda10(this));
        }
        return javaListXmlObject;
    }

    public CpType[] getCpArray() {
        return getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CpType[0]);
    }

    public CpType getCpArray(int i) {
        CpType find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfCpArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setCpArray(CpType[] cpTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cpTypeArr, PROPERTY_QNAME[0]);
    }

    public void setCpArray(int i, CpType cpType) {
        generatedSetterHelperImpl(cpType, PROPERTY_QNAME[0], i, 2);
    }

    public CpType insertNewCp(int i) {
        CpType insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return insert_element_user;
    }

    public CpType addNewCp() {
        CpType add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return add_element_user;
    }

    public void removeCp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<PpType> getPpList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new TextTypeImpl$$ExternalSyntheticLambda1(this), new TextTypeImpl$$ExternalSyntheticLambda2(this), new TextTypeImpl$$ExternalSyntheticLambda3(this), new TextTypeImpl$$ExternalSyntheticLambda4(this), new TextTypeImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public PpType[] getPpArray() {
        return getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new PpType[0]);
    }

    public PpType getPpArray(int i) {
        PpType find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfPpArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setPpArray(PpType[] ppTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) ppTypeArr, PROPERTY_QNAME[1]);
    }

    public void setPpArray(int i, PpType ppType) {
        generatedSetterHelperImpl(ppType, PROPERTY_QNAME[1], i, 2);
    }

    public PpType insertNewPp(int i) {
        PpType insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return insert_element_user;
    }

    public PpType addNewPp() {
        PpType add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return add_element_user;
    }

    public void removePp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public List<TpType> getTpList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new TextTypeImpl$$ExternalSyntheticLambda15(this), new TextTypeImpl$$ExternalSyntheticLambda16(this), new TextTypeImpl$$ExternalSyntheticLambda17(this), new TextTypeImpl$$ExternalSyntheticLambda18(this), new TextTypeImpl$$ExternalSyntheticLambda19(this));
        }
        return javaListXmlObject;
    }

    public TpType[] getTpArray() {
        return getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new TpType[0]);
    }

    public TpType getTpArray(int i) {
        TpType find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfTpArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setTpArray(TpType[] tpTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) tpTypeArr, PROPERTY_QNAME[2]);
    }

    public void setTpArray(int i, TpType tpType) {
        generatedSetterHelperImpl(tpType, PROPERTY_QNAME[2], i, 2);
    }

    public TpType insertNewTp(int i) {
        TpType insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return insert_element_user;
    }

    public TpType addNewTp() {
        TpType add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return add_element_user;
    }

    public void removeTp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public List<FldType> getFldList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new TextTypeImpl$$ExternalSyntheticLambda0(this), new TextTypeImpl$$ExternalSyntheticLambda11(this), new TextTypeImpl$$ExternalSyntheticLambda12(this), new TextTypeImpl$$ExternalSyntheticLambda13(this), new TextTypeImpl$$ExternalSyntheticLambda14(this));
        }
        return javaListXmlObject;
    }

    public FldType[] getFldArray() {
        return getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new FldType[0]);
    }

    public FldType getFldArray(int i) {
        FldType find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfFldArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    public void setFldArray(FldType[] fldTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) fldTypeArr, PROPERTY_QNAME[3]);
    }

    public void setFldArray(int i, FldType fldType) {
        generatedSetterHelperImpl(fldType, PROPERTY_QNAME[3], i, 2);
    }

    public FldType insertNewFld(int i) {
        FldType insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return insert_element_user;
    }

    public FldType addNewFld() {
        FldType add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return add_element_user;
    }

    public void removeFld(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }
}
