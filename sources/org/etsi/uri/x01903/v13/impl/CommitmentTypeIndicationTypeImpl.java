package org.etsi.uri.x01903.v13.impl;

import com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda133;
import java.util.List;
import java.util.function.IntFunction;
import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CommitmentTypeIndicationType;
import org.etsi.uri.x01903.v13.CommitmentTypeQualifiersListType;
import org.etsi.uri.x01903.v13.ObjectIdentifierType;

public class CommitmentTypeIndicationTypeImpl extends XmlComplexContentImpl implements CommitmentTypeIndicationType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "CommitmentTypeId"), new QName(SignatureFacet.XADES_132_NS, "ObjectReference"), new QName(SignatureFacet.XADES_132_NS, "AllSignedDataObjects"), new QName(SignatureFacet.XADES_132_NS, "CommitmentTypeQualifiers")};
    private static final long serialVersionUID = 1;

    public CommitmentTypeIndicationTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public ObjectIdentifierType getCommitmentTypeId() {
        ObjectIdentifierType objectIdentifierType;
        synchronized (monitor()) {
            check_orphaned();
            objectIdentifierType = (ObjectIdentifierType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (objectIdentifierType == null) {
                objectIdentifierType = null;
            }
        }
        return objectIdentifierType;
    }

    public void setCommitmentTypeId(ObjectIdentifierType objectIdentifierType) {
        generatedSetterHelperImpl(objectIdentifierType, PROPERTY_QNAME[0], 0, 1);
    }

    public ObjectIdentifierType addNewCommitmentTypeId() {
        ObjectIdentifierType objectIdentifierType;
        synchronized (monitor()) {
            check_orphaned();
            objectIdentifierType = (ObjectIdentifierType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return objectIdentifierType;
    }

    public List<String> getObjectReferenceList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new CommitmentTypeIndicationTypeImpl$$ExternalSyntheticLambda3(this), new CommitmentTypeIndicationTypeImpl$$ExternalSyntheticLambda4(this), new CommitmentTypeIndicationTypeImpl$$ExternalSyntheticLambda5(this), new CommitmentTypeIndicationTypeImpl$$ExternalSyntheticLambda6(this), new CommitmentTypeIndicationTypeImpl$$ExternalSyntheticLambda7(this));
        }
        return javaListObject;
    }

    static /* synthetic */ String[] lambda$getObjectReferenceArray$0(int i) {
        return new String[i];
    }

    public String[] getObjectReferenceArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[1], new CTClientDataImpl$$ExternalSyntheticLambda133(), (IntFunction<T[]>) new CommitmentTypeIndicationTypeImpl$$ExternalSyntheticLambda1());
    }

    public String getObjectReferenceArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (simpleValue != null) {
                stringValue = simpleValue.getStringValue();
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
        return stringValue;
    }

    public List<XmlAnyURI> xgetObjectReferenceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CommitmentTypeIndicationTypeImpl$$ExternalSyntheticLambda8(this), new CommitmentTypeIndicationTypeImpl$$ExternalSyntheticLambda9(this), new CommitmentTypeIndicationTypeImpl$$ExternalSyntheticLambda0(this), new CommitmentTypeIndicationTypeImpl$$ExternalSyntheticLambda6(this), new CommitmentTypeIndicationTypeImpl$$ExternalSyntheticLambda7(this));
        }
        return javaListXmlObject;
    }

    static /* synthetic */ XmlAnyURI[] lambda$xgetObjectReferenceArray$1(int i) {
        return new XmlAnyURI[i];
    }

    public XmlAnyURI[] xgetObjectReferenceArray() {
        return (XmlAnyURI[]) xgetArray(PROPERTY_QNAME[1], (IntFunction<T[]>) new CommitmentTypeIndicationTypeImpl$$ExternalSyntheticLambda2());
    }

    public XmlAnyURI xgetObjectReferenceArray(int i) {
        XmlAnyURI xmlAnyURI;
        synchronized (monitor()) {
            check_orphaned();
            xmlAnyURI = (XmlAnyURI) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (xmlAnyURI == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return xmlAnyURI;
    }

    public int sizeOfObjectReferenceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setObjectReferenceArray(String[] strArr) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(strArr, PROPERTY_QNAME[1]);
        }
    }

    public void setObjectReferenceArray(int i, String str) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (simpleValue != null) {
                simpleValue.setStringValue(str);
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
    }

    public void xsetObjectReferenceArray(XmlAnyURI[] xmlAnyURIArr) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) xmlAnyURIArr, PROPERTY_QNAME[1]);
        }
    }

    public void xsetObjectReferenceArray(int i, XmlAnyURI xmlAnyURI) {
        synchronized (monitor()) {
            check_orphaned();
            XmlAnyURI xmlAnyURI2 = (XmlAnyURI) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (xmlAnyURI2 != null) {
                xmlAnyURI2.set(xmlAnyURI);
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
    }

    public void insertObjectReference(int i, String str) {
        synchronized (monitor()) {
            check_orphaned();
            ((SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[1], i)).setStringValue(str);
        }
    }

    public void addObjectReference(String str) {
        synchronized (monitor()) {
            check_orphaned();
            ((SimpleValue) get_store().add_element_user(PROPERTY_QNAME[1])).setStringValue(str);
        }
    }

    public XmlAnyURI insertNewObjectReference(int i) {
        XmlAnyURI xmlAnyURI;
        synchronized (monitor()) {
            check_orphaned();
            xmlAnyURI = (XmlAnyURI) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return xmlAnyURI;
    }

    public XmlAnyURI addNewObjectReference() {
        XmlAnyURI xmlAnyURI;
        synchronized (monitor()) {
            check_orphaned();
            xmlAnyURI = (XmlAnyURI) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return xmlAnyURI;
    }

    public void removeObjectReference(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public XmlObject getAllSignedDataObjects() {
        XmlObject xmlObject;
        synchronized (monitor()) {
            check_orphaned();
            xmlObject = (XmlObject) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (xmlObject == null) {
                xmlObject = null;
            }
        }
        return xmlObject;
    }

    public boolean isSetAllSignedDataObjects() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setAllSignedDataObjects(XmlObject xmlObject) {
        generatedSetterHelperImpl(xmlObject, PROPERTY_QNAME[2], 0, 1);
    }

    public XmlObject addNewAllSignedDataObjects() {
        XmlObject xmlObject;
        synchronized (monitor()) {
            check_orphaned();
            xmlObject = (XmlObject) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return xmlObject;
    }

    public void unsetAllSignedDataObjects() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CommitmentTypeQualifiersListType getCommitmentTypeQualifiers() {
        CommitmentTypeQualifiersListType commitmentTypeQualifiersListType;
        synchronized (monitor()) {
            check_orphaned();
            commitmentTypeQualifiersListType = (CommitmentTypeQualifiersListType) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (commitmentTypeQualifiersListType == null) {
                commitmentTypeQualifiersListType = null;
            }
        }
        return commitmentTypeQualifiersListType;
    }

    public boolean isSetCommitmentTypeQualifiers() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setCommitmentTypeQualifiers(CommitmentTypeQualifiersListType commitmentTypeQualifiersListType) {
        generatedSetterHelperImpl(commitmentTypeQualifiersListType, PROPERTY_QNAME[3], 0, 1);
    }

    public CommitmentTypeQualifiersListType addNewCommitmentTypeQualifiers() {
        CommitmentTypeQualifiersListType commitmentTypeQualifiersListType;
        synchronized (monitor()) {
            check_orphaned();
            commitmentTypeQualifiersListType = (CommitmentTypeQualifiersListType) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return commitmentTypeQualifiersListType;
    }

    public void unsetCommitmentTypeQualifiers() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
