package org.etsi.uri.x01903.v13.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CommitmentTypeIndicationType;
import org.etsi.uri.x01903.v13.DataObjectFormatType;
import org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType;
import org.etsi.uri.x01903.v13.XAdESTimeStampType;

public class SignedDataObjectPropertiesTypeImpl extends XmlComplexContentImpl implements SignedDataObjectPropertiesType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "DataObjectFormat"), new QName(SignatureFacet.XADES_132_NS, "CommitmentTypeIndication"), new QName(SignatureFacet.XADES_132_NS, "AllDataObjectsTimeStamp"), new QName(SignatureFacet.XADES_132_NS, "IndividualDataObjectsTimeStamp"), new QName("", PackageRelationship.ID_ATTRIBUTE_NAME)};
    private static final long serialVersionUID = 1;

    public SignedDataObjectPropertiesTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<DataObjectFormatType> getDataObjectFormatList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda6(this), new SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda7(this), new SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda8(this), new SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda9(this), new SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda10(this));
        }
        return javaListXmlObject;
    }

    public DataObjectFormatType[] getDataObjectFormatArray() {
        return (DataObjectFormatType[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new DataObjectFormatType[0]);
    }

    public DataObjectFormatType getDataObjectFormatArray(int i) {
        DataObjectFormatType dataObjectFormatType;
        synchronized (monitor()) {
            check_orphaned();
            dataObjectFormatType = (DataObjectFormatType) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (dataObjectFormatType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return dataObjectFormatType;
    }

    public int sizeOfDataObjectFormatArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setDataObjectFormatArray(DataObjectFormatType[] dataObjectFormatTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) dataObjectFormatTypeArr, PROPERTY_QNAME[0]);
    }

    public void setDataObjectFormatArray(int i, DataObjectFormatType dataObjectFormatType) {
        generatedSetterHelperImpl(dataObjectFormatType, PROPERTY_QNAME[0], i, 2);
    }

    public DataObjectFormatType insertNewDataObjectFormat(int i) {
        DataObjectFormatType dataObjectFormatType;
        synchronized (monitor()) {
            check_orphaned();
            dataObjectFormatType = (DataObjectFormatType) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return dataObjectFormatType;
    }

    public DataObjectFormatType addNewDataObjectFormat() {
        DataObjectFormatType dataObjectFormatType;
        synchronized (monitor()) {
            check_orphaned();
            dataObjectFormatType = (DataObjectFormatType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return dataObjectFormatType;
    }

    public void removeDataObjectFormat(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<CommitmentTypeIndicationType> getCommitmentTypeIndicationList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda1(this), new SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda2(this), new SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda3(this), new SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda4(this), new SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public CommitmentTypeIndicationType[] getCommitmentTypeIndicationArray() {
        return (CommitmentTypeIndicationType[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CommitmentTypeIndicationType[0]);
    }

    public CommitmentTypeIndicationType getCommitmentTypeIndicationArray(int i) {
        CommitmentTypeIndicationType commitmentTypeIndicationType;
        synchronized (monitor()) {
            check_orphaned();
            commitmentTypeIndicationType = (CommitmentTypeIndicationType) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (commitmentTypeIndicationType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return commitmentTypeIndicationType;
    }

    public int sizeOfCommitmentTypeIndicationArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setCommitmentTypeIndicationArray(CommitmentTypeIndicationType[] commitmentTypeIndicationTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) commitmentTypeIndicationTypeArr, PROPERTY_QNAME[1]);
    }

    public void setCommitmentTypeIndicationArray(int i, CommitmentTypeIndicationType commitmentTypeIndicationType) {
        generatedSetterHelperImpl(commitmentTypeIndicationType, PROPERTY_QNAME[1], i, 2);
    }

    public CommitmentTypeIndicationType insertNewCommitmentTypeIndication(int i) {
        CommitmentTypeIndicationType commitmentTypeIndicationType;
        synchronized (monitor()) {
            check_orphaned();
            commitmentTypeIndicationType = (CommitmentTypeIndicationType) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return commitmentTypeIndicationType;
    }

    public CommitmentTypeIndicationType addNewCommitmentTypeIndication() {
        CommitmentTypeIndicationType commitmentTypeIndicationType;
        synchronized (monitor()) {
            check_orphaned();
            commitmentTypeIndicationType = (CommitmentTypeIndicationType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return commitmentTypeIndicationType;
    }

    public void removeCommitmentTypeIndication(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public List<XAdESTimeStampType> getAllDataObjectsTimeStampList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda15(this), new SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda16(this), new SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda17(this), new SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda18(this), new SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda19(this));
        }
        return javaListXmlObject;
    }

    public XAdESTimeStampType[] getAllDataObjectsTimeStampArray() {
        return (XAdESTimeStampType[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new XAdESTimeStampType[0]);
    }

    public XAdESTimeStampType getAllDataObjectsTimeStampArray(int i) {
        XAdESTimeStampType xAdESTimeStampType;
        synchronized (monitor()) {
            check_orphaned();
            xAdESTimeStampType = (XAdESTimeStampType) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (xAdESTimeStampType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return xAdESTimeStampType;
    }

    public int sizeOfAllDataObjectsTimeStampArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setAllDataObjectsTimeStampArray(XAdESTimeStampType[] xAdESTimeStampTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) xAdESTimeStampTypeArr, PROPERTY_QNAME[2]);
    }

    public void setAllDataObjectsTimeStampArray(int i, XAdESTimeStampType xAdESTimeStampType) {
        generatedSetterHelperImpl(xAdESTimeStampType, PROPERTY_QNAME[2], i, 2);
    }

    public XAdESTimeStampType insertNewAllDataObjectsTimeStamp(int i) {
        XAdESTimeStampType xAdESTimeStampType;
        synchronized (monitor()) {
            check_orphaned();
            xAdESTimeStampType = (XAdESTimeStampType) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return xAdESTimeStampType;
    }

    public XAdESTimeStampType addNewAllDataObjectsTimeStamp() {
        XAdESTimeStampType xAdESTimeStampType;
        synchronized (monitor()) {
            check_orphaned();
            xAdESTimeStampType = (XAdESTimeStampType) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return xAdESTimeStampType;
    }

    public void removeAllDataObjectsTimeStamp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public List<XAdESTimeStampType> getIndividualDataObjectsTimeStampList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda0(this), new SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda11(this), new SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda12(this), new SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda13(this), new SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda14(this));
        }
        return javaListXmlObject;
    }

    public XAdESTimeStampType[] getIndividualDataObjectsTimeStampArray() {
        return (XAdESTimeStampType[]) getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new XAdESTimeStampType[0]);
    }

    public XAdESTimeStampType getIndividualDataObjectsTimeStampArray(int i) {
        XAdESTimeStampType xAdESTimeStampType;
        synchronized (monitor()) {
            check_orphaned();
            xAdESTimeStampType = (XAdESTimeStampType) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (xAdESTimeStampType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return xAdESTimeStampType;
    }

    public int sizeOfIndividualDataObjectsTimeStampArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    public void setIndividualDataObjectsTimeStampArray(XAdESTimeStampType[] xAdESTimeStampTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) xAdESTimeStampTypeArr, PROPERTY_QNAME[3]);
    }

    public void setIndividualDataObjectsTimeStampArray(int i, XAdESTimeStampType xAdESTimeStampType) {
        generatedSetterHelperImpl(xAdESTimeStampType, PROPERTY_QNAME[3], i, 2);
    }

    public XAdESTimeStampType insertNewIndividualDataObjectsTimeStamp(int i) {
        XAdESTimeStampType xAdESTimeStampType;
        synchronized (monitor()) {
            check_orphaned();
            xAdESTimeStampType = (XAdESTimeStampType) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return xAdESTimeStampType;
    }

    public XAdESTimeStampType addNewIndividualDataObjectsTimeStamp() {
        XAdESTimeStampType xAdESTimeStampType;
        synchronized (monitor()) {
            check_orphaned();
            xAdESTimeStampType = (XAdESTimeStampType) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return xAdESTimeStampType;
    }

    public void removeIndividualDataObjectsTimeStamp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    public String getId() {
        String str;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (simpleValue == null) {
                str = null;
            } else {
                str = simpleValue.getStringValue();
            }
        }
        return str;
    }

    public XmlID xgetId() {
        XmlID xmlID;
        synchronized (monitor()) {
            check_orphaned();
            xmlID = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return xmlID;
    }

    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setId(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 4
            r4 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.setStringValue(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.etsi.uri.x01903.v13.impl.SignedDataObjectPropertiesTypeImpl.setId(java.lang.String):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetId(org.apache.xmlbeans.XmlID r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 4
            r4 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.XmlID r1 = (org.apache.xmlbeans.XmlID) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.apache.xmlbeans.XmlID r1 = (org.apache.xmlbeans.XmlID) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.set(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.etsi.uri.x01903.v13.impl.SignedDataObjectPropertiesTypeImpl.xsetId(org.apache.xmlbeans.XmlID):void");
    }

    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }
}
