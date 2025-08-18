package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda133;
import java.util.List;
import java.util.function.IntFunction;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAuthors;

public class CTAuthorsImpl extends XmlComplexContentImpl implements CTAuthors {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "author")};
    private static final long serialVersionUID = 1;

    public CTAuthorsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<String> getAuthorList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new CTAuthorsImpl$$ExternalSyntheticLambda8(this), new CTAuthorsImpl$$ExternalSyntheticLambda9(this), new CTAuthorsImpl$$ExternalSyntheticLambda1(this), new CTAuthorsImpl$$ExternalSyntheticLambda4(this), new CTAuthorsImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListObject;
    }

    static /* synthetic */ String[] lambda$getAuthorArray$0(int i) {
        return new String[i];
    }

    public String[] getAuthorArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[0], new CTClientDataImpl$$ExternalSyntheticLambda133(), (IntFunction<T[]>) new CTAuthorsImpl$$ExternalSyntheticLambda7());
    }

    public String getAuthorArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (simpleValue != null) {
                stringValue = simpleValue.getStringValue();
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
        return stringValue;
    }

    public List<STXstring> xgetAuthorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTAuthorsImpl$$ExternalSyntheticLambda0(this), new CTAuthorsImpl$$ExternalSyntheticLambda2(this), new CTAuthorsImpl$$ExternalSyntheticLambda3(this), new CTAuthorsImpl$$ExternalSyntheticLambda4(this), new CTAuthorsImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    static /* synthetic */ STXstring[] lambda$xgetAuthorArray$1(int i) {
        return new STXstring[i];
    }

    public STXstring[] xgetAuthorArray() {
        return (STXstring[]) xgetArray(PROPERTY_QNAME[0], (IntFunction<T[]>) new CTAuthorsImpl$$ExternalSyntheticLambda6());
    }

    public STXstring xgetAuthorArray(int i) {
        STXstring sTXstring;
        synchronized (monitor()) {
            check_orphaned();
            sTXstring = (STXstring) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (sTXstring == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return sTXstring;
    }

    public int sizeOfAuthorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setAuthorArray(String[] strArr) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(strArr, PROPERTY_QNAME[0]);
        }
    }

    public void setAuthorArray(int i, String str) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (simpleValue != null) {
                simpleValue.setStringValue(str);
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
    }

    public void xsetAuthorArray(STXstring[] sTXstringArr) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) sTXstringArr, PROPERTY_QNAME[0]);
        }
    }

    public void xsetAuthorArray(int i, STXstring sTXstring) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring sTXstring2 = (STXstring) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (sTXstring2 != null) {
                sTXstring2.set(sTXstring);
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
    }

    public void insertAuthor(int i, String str) {
        synchronized (monitor()) {
            check_orphaned();
            ((SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[0], i)).setStringValue(str);
        }
    }

    public void addAuthor(String str) {
        synchronized (monitor()) {
            check_orphaned();
            ((SimpleValue) get_store().add_element_user(PROPERTY_QNAME[0])).setStringValue(str);
        }
    }

    public STXstring insertNewAuthor(int i) {
        STXstring sTXstring;
        synchronized (monitor()) {
            check_orphaned();
            sTXstring = (STXstring) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return sTXstring;
    }

    public STXstring addNewAuthor() {
        STXstring sTXstring;
        synchronized (monitor()) {
            check_orphaned();
            sTXstring = (STXstring) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return sTXstring;
    }

    public void removeAuthor(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
