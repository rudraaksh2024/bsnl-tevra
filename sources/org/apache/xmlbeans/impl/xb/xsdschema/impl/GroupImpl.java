package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.commons.codec.language.bm.Languages;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.apache.xmlbeans.impl.xb.xsdschema.AnyDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.Group;
import org.apache.xmlbeans.impl.xb.xsdschema.GroupRef;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalElement;

public class GroupImpl extends AnnotatedImpl implements Group {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "element"), new QName("http://www.w3.org/2001/XMLSchema", "group"), new QName("http://www.w3.org/2001/XMLSchema", "all"), new QName("http://www.w3.org/2001/XMLSchema", "choice"), new QName("http://www.w3.org/2001/XMLSchema", "sequence"), new QName("http://www.w3.org/2001/XMLSchema", Languages.ANY), new QName("", "name"), new QName("", "ref"), new QName("", "minOccurs"), new QName("", "maxOccurs")};
    private static final long serialVersionUID = 1;

    public GroupImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<LocalElement> getElementList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new GroupImpl$$ExternalSyntheticLambda6(this), new GroupImpl$$ExternalSyntheticLambda7(this), new GroupImpl$$ExternalSyntheticLambda8(this), new GroupImpl$$ExternalSyntheticLambda9(this), new GroupImpl$$ExternalSyntheticLambda10(this));
        }
        return javaListXmlObject;
    }

    public LocalElement[] getElementArray() {
        return (LocalElement[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new LocalElement[0]);
    }

    public LocalElement getElementArray(int i) {
        LocalElement localElement;
        synchronized (monitor()) {
            check_orphaned();
            localElement = (LocalElement) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (localElement == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return localElement;
    }

    public int sizeOfElementArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setElementArray(LocalElement[] localElementArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) localElementArr, PROPERTY_QNAME[0]);
    }

    public void setElementArray(int i, LocalElement localElement) {
        generatedSetterHelperImpl(localElement, PROPERTY_QNAME[0], i, 2);
    }

    public LocalElement insertNewElement(int i) {
        LocalElement localElement;
        synchronized (monitor()) {
            check_orphaned();
            localElement = (LocalElement) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return localElement;
    }

    public LocalElement addNewElement() {
        LocalElement localElement;
        synchronized (monitor()) {
            check_orphaned();
            localElement = (LocalElement) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return localElement;
    }

    public void removeElement(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<GroupRef> getGroupList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new GroupImpl$$ExternalSyntheticLambda1(this), new GroupImpl$$ExternalSyntheticLambda2(this), new GroupImpl$$ExternalSyntheticLambda3(this), new GroupImpl$$ExternalSyntheticLambda4(this), new GroupImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public GroupRef[] getGroupArray() {
        return (GroupRef[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new GroupRef[0]);
    }

    public GroupRef getGroupArray(int i) {
        GroupRef groupRef;
        synchronized (monitor()) {
            check_orphaned();
            groupRef = (GroupRef) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (groupRef == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return groupRef;
    }

    public int sizeOfGroupArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setGroupArray(GroupRef[] groupRefArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) groupRefArr, PROPERTY_QNAME[1]);
    }

    public void setGroupArray(int i, GroupRef groupRef) {
        generatedSetterHelperImpl(groupRef, PROPERTY_QNAME[1], i, 2);
    }

    public GroupRef insertNewGroup(int i) {
        GroupRef groupRef;
        synchronized (monitor()) {
            check_orphaned();
            groupRef = (GroupRef) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return groupRef;
    }

    public GroupRef addNewGroup() {
        GroupRef groupRef;
        synchronized (monitor()) {
            check_orphaned();
            groupRef = (GroupRef) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return groupRef;
    }

    public void removeGroup(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public List<All> getAllList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new GroupImpl$$ExternalSyntheticLambda17(this), new GroupImpl$$ExternalSyntheticLambda18(this), new GroupImpl$$ExternalSyntheticLambda19(this), new GroupImpl$$ExternalSyntheticLambda20(this), new GroupImpl$$ExternalSyntheticLambda21(this));
        }
        return javaListXmlObject;
    }

    public All[] getAllArray() {
        return (All[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new All[0]);
    }

    public All getAllArray(int i) {
        All all;
        synchronized (monitor()) {
            check_orphaned();
            all = (All) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (all == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return all;
    }

    public int sizeOfAllArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setAllArray(All[] allArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) allArr, PROPERTY_QNAME[2]);
    }

    public void setAllArray(int i, All all) {
        generatedSetterHelperImpl(all, PROPERTY_QNAME[2], i, 2);
    }

    public All insertNewAll(int i) {
        All all;
        synchronized (monitor()) {
            check_orphaned();
            all = (All) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return all;
    }

    public All addNewAll() {
        All all;
        synchronized (monitor()) {
            check_orphaned();
            all = (All) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return all;
    }

    public void removeAll(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public List<ExplicitGroup> getChoiceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new GroupImpl$$ExternalSyntheticLambda25(this), new GroupImpl$$ExternalSyntheticLambda26(this), new GroupImpl$$ExternalSyntheticLambda27(this), new GroupImpl$$ExternalSyntheticLambda28(this), new GroupImpl$$ExternalSyntheticLambda29(this));
        }
        return javaListXmlObject;
    }

    public ExplicitGroup[] getChoiceArray() {
        return (ExplicitGroup[]) getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new ExplicitGroup[0]);
    }

    public ExplicitGroup getChoiceArray(int i) {
        ExplicitGroup explicitGroup;
        synchronized (monitor()) {
            check_orphaned();
            explicitGroup = (ExplicitGroup) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (explicitGroup == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return explicitGroup;
    }

    public int sizeOfChoiceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    public void setChoiceArray(ExplicitGroup[] explicitGroupArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) explicitGroupArr, PROPERTY_QNAME[3]);
    }

    public void setChoiceArray(int i, ExplicitGroup explicitGroup) {
        generatedSetterHelperImpl(explicitGroup, PROPERTY_QNAME[3], i, 2);
    }

    public ExplicitGroup insertNewChoice(int i) {
        ExplicitGroup explicitGroup;
        synchronized (monitor()) {
            check_orphaned();
            explicitGroup = (ExplicitGroup) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return explicitGroup;
    }

    public ExplicitGroup addNewChoice() {
        ExplicitGroup explicitGroup;
        synchronized (monitor()) {
            check_orphaned();
            explicitGroup = (ExplicitGroup) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return explicitGroup;
    }

    public void removeChoice(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    public List<ExplicitGroup> getSequenceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new GroupImpl$$ExternalSyntheticLambda12(this), new GroupImpl$$ExternalSyntheticLambda13(this), new GroupImpl$$ExternalSyntheticLambda14(this), new GroupImpl$$ExternalSyntheticLambda15(this), new GroupImpl$$ExternalSyntheticLambda16(this));
        }
        return javaListXmlObject;
    }

    public ExplicitGroup[] getSequenceArray() {
        return (ExplicitGroup[]) getXmlObjectArray(PROPERTY_QNAME[4], (T[]) new ExplicitGroup[0]);
    }

    public ExplicitGroup getSequenceArray(int i) {
        ExplicitGroup explicitGroup;
        synchronized (monitor()) {
            check_orphaned();
            explicitGroup = (ExplicitGroup) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (explicitGroup == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return explicitGroup;
    }

    public int sizeOfSequenceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    public void setSequenceArray(ExplicitGroup[] explicitGroupArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) explicitGroupArr, PROPERTY_QNAME[4]);
    }

    public void setSequenceArray(int i, ExplicitGroup explicitGroup) {
        generatedSetterHelperImpl(explicitGroup, PROPERTY_QNAME[4], i, 2);
    }

    public ExplicitGroup insertNewSequence(int i) {
        ExplicitGroup explicitGroup;
        synchronized (monitor()) {
            check_orphaned();
            explicitGroup = (ExplicitGroup) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return explicitGroup;
    }

    public ExplicitGroup addNewSequence() {
        ExplicitGroup explicitGroup;
        synchronized (monitor()) {
            check_orphaned();
            explicitGroup = (ExplicitGroup) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return explicitGroup;
    }

    public void removeSequence(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    public List<AnyDocument.Any> getAnyList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new GroupImpl$$ExternalSyntheticLambda0(this), new GroupImpl$$ExternalSyntheticLambda11(this), new GroupImpl$$ExternalSyntheticLambda22(this), new GroupImpl$$ExternalSyntheticLambda23(this), new GroupImpl$$ExternalSyntheticLambda24(this));
        }
        return javaListXmlObject;
    }

    public AnyDocument.Any[] getAnyArray() {
        return (AnyDocument.Any[]) getXmlObjectArray(PROPERTY_QNAME[5], (T[]) new AnyDocument.Any[0]);
    }

    public AnyDocument.Any getAnyArray(int i) {
        AnyDocument.Any any;
        synchronized (monitor()) {
            check_orphaned();
            any = (AnyDocument.Any) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (any == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return any;
    }

    public int sizeOfAnyArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    public void setAnyArray(AnyDocument.Any[] anyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) anyArr, PROPERTY_QNAME[5]);
    }

    public void setAnyArray(int i, AnyDocument.Any any) {
        generatedSetterHelperImpl(any, PROPERTY_QNAME[5], i, 2);
    }

    public AnyDocument.Any insertNewAny(int i) {
        AnyDocument.Any any;
        synchronized (monitor()) {
            check_orphaned();
            any = (AnyDocument.Any) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return any;
    }

    public AnyDocument.Any addNewAny() {
        AnyDocument.Any any;
        synchronized (monitor()) {
            check_orphaned();
            any = (AnyDocument.Any) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return any;
    }

    public void removeAny(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    public String getName() {
        String str;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (simpleValue == null) {
                str = null;
            } else {
                str = simpleValue.getStringValue();
            }
        }
        return str;
    }

    public XmlNCName xgetName() {
        XmlNCName xmlNCName;
        synchronized (monitor()) {
            check_orphaned();
            xmlNCName = (XmlNCName) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return xmlNCName;
    }

    public boolean isSetName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setName(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 6
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
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl.setName(java.lang.String):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetName(org.apache.xmlbeans.XmlNCName r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 6
            r4 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.XmlNCName r1 = (org.apache.xmlbeans.XmlNCName) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.apache.xmlbeans.XmlNCName r1 = (org.apache.xmlbeans.XmlNCName) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.set(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl.xsetName(org.apache.xmlbeans.XmlNCName):void");
    }

    public void unsetName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    public QName getRef() {
        QName qName;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (simpleValue == null) {
                qName = null;
            } else {
                qName = simpleValue.getQNameValue();
            }
        }
        return qName;
    }

    public XmlQName xgetRef() {
        XmlQName xmlQName;
        synchronized (monitor()) {
            check_orphaned();
            xmlQName = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return xmlQName;
    }

    public boolean isSetRef() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setRef(javax.xml.namespace.QName r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 7
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
            r1.setQNameValue(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl.setRef(javax.xml.namespace.QName):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetRef(org.apache.xmlbeans.XmlQName r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 7
            r4 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.XmlQName r1 = (org.apache.xmlbeans.XmlQName) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.apache.xmlbeans.XmlQName r1 = (org.apache.xmlbeans.XmlQName) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.set(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl.xsetRef(org.apache.xmlbeans.XmlQName):void");
    }

    public void unsetRef() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    /* JADX WARNING: type inference failed for: r5v5, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.math.BigInteger getMinOccurs() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 8
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0023
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x002d }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
        L_0x0023:
            if (r1 != 0) goto L_0x0027
            r5 = 0
            goto L_0x002b
        L_0x0027:
            java.math.BigInteger r5 = r1.getBigIntegerValue()     // Catch:{ all -> 0x002d }
        L_0x002b:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return r5
        L_0x002d:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl.getMinOccurs():java.math.BigInteger");
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.xmlbeans.XmlNonNegativeInteger xgetMinOccurs() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x0025 }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x0025 }
            r3 = 8
            r4 = r2[r3]     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.XmlNonNegativeInteger r1 = (org.apache.xmlbeans.XmlNonNegativeInteger) r1     // Catch:{ all -> 0x0025 }
            if (r1 != 0) goto L_0x0023
            r1 = r2[r3]     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x0025 }
            r1 = r5
            org.apache.xmlbeans.XmlNonNegativeInteger r1 = (org.apache.xmlbeans.XmlNonNegativeInteger) r1     // Catch:{ all -> 0x0025 }
        L_0x0023:
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            return r1
        L_0x0025:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl.xgetMinOccurs():org.apache.xmlbeans.XmlNonNegativeInteger");
    }

    public boolean isSetMinOccurs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setMinOccurs(java.math.BigInteger r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 8
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.setBigIntegerValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl.setMinOccurs(java.math.BigInteger):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetMinOccurs(org.apache.xmlbeans.XmlNonNegativeInteger r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 8
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.XmlNonNegativeInteger r1 = (org.apache.xmlbeans.XmlNonNegativeInteger) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.apache.xmlbeans.XmlNonNegativeInteger r1 = (org.apache.xmlbeans.XmlNonNegativeInteger) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl.xsetMinOccurs(org.apache.xmlbeans.XmlNonNegativeInteger):void");
    }

    public void unsetMinOccurs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }

    /* JADX WARNING: type inference failed for: r5v5, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getMaxOccurs() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 9
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0023
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x002d }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
        L_0x0023:
            if (r1 != 0) goto L_0x0027
            r5 = 0
            goto L_0x002b
        L_0x0027:
            java.lang.Object r5 = r1.getObjectValue()     // Catch:{ all -> 0x002d }
        L_0x002b:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return r5
        L_0x002d:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl.getMaxOccurs():java.lang.Object");
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.xmlbeans.impl.xb.xsdschema.AllNNI xgetMaxOccurs() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x0025 }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x0025 }
            r3 = 9
            r4 = r2[r3]     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.impl.xb.xsdschema.AllNNI r1 = (org.apache.xmlbeans.impl.xb.xsdschema.AllNNI) r1     // Catch:{ all -> 0x0025 }
            if (r1 != 0) goto L_0x0023
            r1 = r2[r3]     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x0025 }
            r1 = r5
            org.apache.xmlbeans.impl.xb.xsdschema.AllNNI r1 = (org.apache.xmlbeans.impl.xb.xsdschema.AllNNI) r1     // Catch:{ all -> 0x0025 }
        L_0x0023:
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            return r1
        L_0x0025:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl.xgetMaxOccurs():org.apache.xmlbeans.impl.xb.xsdschema.AllNNI");
    }

    public boolean isSetMaxOccurs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setMaxOccurs(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 9
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.setObjectValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl.setMaxOccurs(java.lang.Object):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetMaxOccurs(org.apache.xmlbeans.impl.xb.xsdschema.AllNNI r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 9
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.xb.xsdschema.AllNNI r1 = (org.apache.xmlbeans.impl.xb.xsdschema.AllNNI) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.apache.xmlbeans.impl.xb.xsdschema.AllNNI r1 = (org.apache.xmlbeans.impl.xb.xsdschema.AllNNI) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl.xsetMaxOccurs(org.apache.xmlbeans.impl.xb.xsdschema.AllNNI):void");
    }

    public void unsetMaxOccurs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }
}
