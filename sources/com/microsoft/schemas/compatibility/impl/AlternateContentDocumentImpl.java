package com.microsoft.schemas.compatibility.impl;

import com.microsoft.schemas.compatibility.AlternateContentDocument;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageNamespaces;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class AlternateContentDocumentImpl extends XmlComplexContentImpl implements AlternateContentDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(PackageNamespaces.MARKUP_COMPATIBILITY, "AlternateContent")};
    private static final long serialVersionUID = 1;

    public AlternateContentDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public AlternateContentDocument.AlternateContent getAlternateContent() {
        AlternateContentDocument.AlternateContent alternateContent;
        synchronized (monitor()) {
            check_orphaned();
            alternateContent = (AlternateContentDocument.AlternateContent) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (alternateContent == null) {
                alternateContent = null;
            }
        }
        return alternateContent;
    }

    public void setAlternateContent(AlternateContentDocument.AlternateContent alternateContent) {
        generatedSetterHelperImpl(alternateContent, PROPERTY_QNAME[0], 0, 1);
    }

    public AlternateContentDocument.AlternateContent addNewAlternateContent() {
        AlternateContentDocument.AlternateContent alternateContent;
        synchronized (monitor()) {
            check_orphaned();
            alternateContent = (AlternateContentDocument.AlternateContent) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return alternateContent;
    }

    public static class AlternateContentImpl extends XmlComplexContentImpl implements AlternateContentDocument.AlternateContent {
        private static final QName[] PROPERTY_QNAME = {new QName(PackageNamespaces.MARKUP_COMPATIBILITY, "Choice"), new QName(PackageNamespaces.MARKUP_COMPATIBILITY, "Fallback"), new QName(PackageNamespaces.MARKUP_COMPATIBILITY, "Ignorable"), new QName(PackageNamespaces.MARKUP_COMPATIBILITY, "MustUnderstand"), new QName(PackageNamespaces.MARKUP_COMPATIBILITY, "ProcessContent")};
        private static final long serialVersionUID = 1;

        public AlternateContentImpl(SchemaType schemaType) {
            super(schemaType);
        }

        public List<AlternateContentDocument.AlternateContent.Choice> getChoiceList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new AlternateContentDocumentImpl$AlternateContentImpl$$ExternalSyntheticLambda0(this), new AlternateContentDocumentImpl$AlternateContentImpl$$ExternalSyntheticLambda1(this), new AlternateContentDocumentImpl$AlternateContentImpl$$ExternalSyntheticLambda2(this), new AlternateContentDocumentImpl$AlternateContentImpl$$ExternalSyntheticLambda3(this), new AlternateContentDocumentImpl$AlternateContentImpl$$ExternalSyntheticLambda4(this));
            }
            return javaListXmlObject;
        }

        public AlternateContentDocument.AlternateContent.Choice[] getChoiceArray() {
            return (AlternateContentDocument.AlternateContent.Choice[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new AlternateContentDocument.AlternateContent.Choice[0]);
        }

        public AlternateContentDocument.AlternateContent.Choice getChoiceArray(int i) {
            AlternateContentDocument.AlternateContent.Choice choice;
            synchronized (monitor()) {
                check_orphaned();
                choice = (AlternateContentDocument.AlternateContent.Choice) get_store().find_element_user(PROPERTY_QNAME[0], i);
                if (choice == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return choice;
        }

        public int sizeOfChoiceArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
            }
            return count_elements;
        }

        public void setChoiceArray(AlternateContentDocument.AlternateContent.Choice[] choiceArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) choiceArr, PROPERTY_QNAME[0]);
        }

        public void setChoiceArray(int i, AlternateContentDocument.AlternateContent.Choice choice) {
            generatedSetterHelperImpl(choice, PROPERTY_QNAME[0], i, 2);
        }

        public AlternateContentDocument.AlternateContent.Choice insertNewChoice(int i) {
            AlternateContentDocument.AlternateContent.Choice choice;
            synchronized (monitor()) {
                check_orphaned();
                choice = (AlternateContentDocument.AlternateContent.Choice) get_store().insert_element_user(PROPERTY_QNAME[0], i);
            }
            return choice;
        }

        public AlternateContentDocument.AlternateContent.Choice addNewChoice() {
            AlternateContentDocument.AlternateContent.Choice choice;
            synchronized (monitor()) {
                check_orphaned();
                choice = (AlternateContentDocument.AlternateContent.Choice) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return choice;
        }

        public void removeChoice(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], i);
            }
        }

        public AlternateContentDocument.AlternateContent.Fallback getFallback() {
            AlternateContentDocument.AlternateContent.Fallback fallback;
            synchronized (monitor()) {
                check_orphaned();
                fallback = (AlternateContentDocument.AlternateContent.Fallback) get_store().find_element_user(PROPERTY_QNAME[1], 0);
                if (fallback == null) {
                    fallback = null;
                }
            }
            return fallback;
        }

        public boolean isSetFallback() {
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

        public void setFallback(AlternateContentDocument.AlternateContent.Fallback fallback) {
            generatedSetterHelperImpl(fallback, PROPERTY_QNAME[1], 0, 1);
        }

        public AlternateContentDocument.AlternateContent.Fallback addNewFallback() {
            AlternateContentDocument.AlternateContent.Fallback fallback;
            synchronized (monitor()) {
                check_orphaned();
                fallback = (AlternateContentDocument.AlternateContent.Fallback) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            return fallback;
        }

        public void unsetFallback() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[1], 0);
            }
        }

        public String getIgnorable() {
            String str;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
                if (simpleValue == null) {
                    str = null;
                } else {
                    str = simpleValue.getStringValue();
                }
            }
            return str;
        }

        public XmlString xgetIgnorable() {
            XmlString xmlString;
            synchronized (monitor()) {
                check_orphaned();
                xmlString = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            }
            return xmlString;
        }

        public boolean isSetIgnorable() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
            }
            return z;
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setIgnorable(java.lang.String r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
                r3 = 2
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
            throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl.AlternateContentImpl.setIgnorable(java.lang.String):void");
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void xsetIgnorable(org.apache.xmlbeans.XmlString r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
                r3 = 2
                r4 = r2[r3]     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002b }
                if (r1 != 0) goto L_0x0026
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
                r1 = r2[r3]     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
                r1 = r5
                org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002b }
            L_0x0026:
                r1.set(r6)     // Catch:{ all -> 0x002b }
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                return
            L_0x002b:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl.AlternateContentImpl.xsetIgnorable(org.apache.xmlbeans.XmlString):void");
        }

        public void unsetIgnorable() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[2]);
            }
        }

        public String getMustUnderstand() {
            String str;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
                if (simpleValue == null) {
                    str = null;
                } else {
                    str = simpleValue.getStringValue();
                }
            }
            return str;
        }

        public XmlString xgetMustUnderstand() {
            XmlString xmlString;
            synchronized (monitor()) {
                check_orphaned();
                xmlString = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            }
            return xmlString;
        }

        public boolean isSetMustUnderstand() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
            }
            return z;
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setMustUnderstand(java.lang.String r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
                r3 = 3
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
            throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl.AlternateContentImpl.setMustUnderstand(java.lang.String):void");
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void xsetMustUnderstand(org.apache.xmlbeans.XmlString r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
                r3 = 3
                r4 = r2[r3]     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002b }
                if (r1 != 0) goto L_0x0026
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
                r1 = r2[r3]     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
                r1 = r5
                org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002b }
            L_0x0026:
                r1.set(r6)     // Catch:{ all -> 0x002b }
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                return
            L_0x002b:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl.AlternateContentImpl.xsetMustUnderstand(org.apache.xmlbeans.XmlString):void");
        }

        public void unsetMustUnderstand() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[3]);
            }
        }

        public String getProcessContent() {
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

        public XmlString xgetProcessContent() {
            XmlString xmlString;
            synchronized (monitor()) {
                check_orphaned();
                xmlString = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            }
            return xmlString;
        }

        public boolean isSetProcessContent() {
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
        public void setProcessContent(java.lang.String r6) {
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
            throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl.AlternateContentImpl.setProcessContent(java.lang.String):void");
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void xsetProcessContent(org.apache.xmlbeans.XmlString r6) {
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
                org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002b }
                if (r1 != 0) goto L_0x0026
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
                r1 = r2[r3]     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
                r1 = r5
                org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002b }
            L_0x0026:
                r1.set(r6)     // Catch:{ all -> 0x002b }
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                return
            L_0x002b:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl.AlternateContentImpl.xsetProcessContent(org.apache.xmlbeans.XmlString):void");
        }

        public void unsetProcessContent() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[4]);
            }
        }

        public static class ChoiceImpl extends XmlComplexContentImpl implements AlternateContentDocument.AlternateContent.Choice {
            private static final QName[] PROPERTY_QNAME = {new QName("", "Requires"), new QName(PackageNamespaces.MARKUP_COMPATIBILITY, "Ignorable"), new QName(PackageNamespaces.MARKUP_COMPATIBILITY, "MustUnderstand"), new QName(PackageNamespaces.MARKUP_COMPATIBILITY, "ProcessContent")};
            private static final long serialVersionUID = 1;

            public ChoiceImpl(SchemaType schemaType) {
                super(schemaType);
            }

            public String getRequires() {
                String str;
                synchronized (monitor()) {
                    check_orphaned();
                    SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                    if (simpleValue == null) {
                        str = null;
                    } else {
                        str = simpleValue.getStringValue();
                    }
                }
                return str;
            }

            public XmlString xgetRequires() {
                XmlString xmlString;
                synchronized (monitor()) {
                    check_orphaned();
                    xmlString = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                }
                return xmlString;
            }

            /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void setRequires(java.lang.String r6) {
                /*
                    r5 = this;
                    java.lang.Object r0 = r5.monitor()
                    monitor-enter(r0)
                    r5.check_orphaned()     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
                    javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
                    r3 = 0
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
                throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl.AlternateContentImpl.ChoiceImpl.setRequires(java.lang.String):void");
            }

            /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void xsetRequires(org.apache.xmlbeans.XmlString r6) {
                /*
                    r5 = this;
                    java.lang.Object r0 = r5.monitor()
                    monitor-enter(r0)
                    r5.check_orphaned()     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
                    javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
                    r3 = 0
                    r4 = r2[r3]     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002b }
                    if (r1 != 0) goto L_0x0026
                    org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
                    r1 = r2[r3]     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
                    r1 = r5
                    org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002b }
                L_0x0026:
                    r1.set(r6)     // Catch:{ all -> 0x002b }
                    monitor-exit(r0)     // Catch:{ all -> 0x002b }
                    return
                L_0x002b:
                    r5 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x002b }
                    throw r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl.AlternateContentImpl.ChoiceImpl.xsetRequires(org.apache.xmlbeans.XmlString):void");
            }

            public String getIgnorable() {
                String str;
                synchronized (monitor()) {
                    check_orphaned();
                    SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                    if (simpleValue == null) {
                        str = null;
                    } else {
                        str = simpleValue.getStringValue();
                    }
                }
                return str;
            }

            public XmlString xgetIgnorable() {
                XmlString xmlString;
                synchronized (monitor()) {
                    check_orphaned();
                    xmlString = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                }
                return xmlString;
            }

            public boolean isSetIgnorable() {
                boolean z;
                synchronized (monitor()) {
                    check_orphaned();
                    z = true;
                    if (get_store().find_attribute_user(PROPERTY_QNAME[1]) == null) {
                        z = false;
                    }
                }
                return z;
            }

            /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void setIgnorable(java.lang.String r6) {
                /*
                    r5 = this;
                    java.lang.Object r0 = r5.monitor()
                    monitor-enter(r0)
                    r5.check_orphaned()     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
                    javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
                    r3 = 1
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
                throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl.AlternateContentImpl.ChoiceImpl.setIgnorable(java.lang.String):void");
            }

            /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void xsetIgnorable(org.apache.xmlbeans.XmlString r6) {
                /*
                    r5 = this;
                    java.lang.Object r0 = r5.monitor()
                    monitor-enter(r0)
                    r5.check_orphaned()     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
                    javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
                    r3 = 1
                    r4 = r2[r3]     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002b }
                    if (r1 != 0) goto L_0x0026
                    org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
                    r1 = r2[r3]     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
                    r1 = r5
                    org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002b }
                L_0x0026:
                    r1.set(r6)     // Catch:{ all -> 0x002b }
                    monitor-exit(r0)     // Catch:{ all -> 0x002b }
                    return
                L_0x002b:
                    r5 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x002b }
                    throw r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl.AlternateContentImpl.ChoiceImpl.xsetIgnorable(org.apache.xmlbeans.XmlString):void");
            }

            public void unsetIgnorable() {
                synchronized (monitor()) {
                    check_orphaned();
                    get_store().remove_attribute(PROPERTY_QNAME[1]);
                }
            }

            public String getMustUnderstand() {
                String str;
                synchronized (monitor()) {
                    check_orphaned();
                    SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
                    if (simpleValue == null) {
                        str = null;
                    } else {
                        str = simpleValue.getStringValue();
                    }
                }
                return str;
            }

            public XmlString xgetMustUnderstand() {
                XmlString xmlString;
                synchronized (monitor()) {
                    check_orphaned();
                    xmlString = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[2]);
                }
                return xmlString;
            }

            public boolean isSetMustUnderstand() {
                boolean z;
                synchronized (monitor()) {
                    check_orphaned();
                    z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
                }
                return z;
            }

            /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void setMustUnderstand(java.lang.String r6) {
                /*
                    r5 = this;
                    java.lang.Object r0 = r5.monitor()
                    monitor-enter(r0)
                    r5.check_orphaned()     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
                    javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
                    r3 = 2
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
                throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl.AlternateContentImpl.ChoiceImpl.setMustUnderstand(java.lang.String):void");
            }

            /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void xsetMustUnderstand(org.apache.xmlbeans.XmlString r6) {
                /*
                    r5 = this;
                    java.lang.Object r0 = r5.monitor()
                    monitor-enter(r0)
                    r5.check_orphaned()     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
                    javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
                    r3 = 2
                    r4 = r2[r3]     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002b }
                    if (r1 != 0) goto L_0x0026
                    org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
                    r1 = r2[r3]     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
                    r1 = r5
                    org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002b }
                L_0x0026:
                    r1.set(r6)     // Catch:{ all -> 0x002b }
                    monitor-exit(r0)     // Catch:{ all -> 0x002b }
                    return
                L_0x002b:
                    r5 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x002b }
                    throw r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl.AlternateContentImpl.ChoiceImpl.xsetMustUnderstand(org.apache.xmlbeans.XmlString):void");
            }

            public void unsetMustUnderstand() {
                synchronized (monitor()) {
                    check_orphaned();
                    get_store().remove_attribute(PROPERTY_QNAME[2]);
                }
            }

            public String getProcessContent() {
                String str;
                synchronized (monitor()) {
                    check_orphaned();
                    SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
                    if (simpleValue == null) {
                        str = null;
                    } else {
                        str = simpleValue.getStringValue();
                    }
                }
                return str;
            }

            public XmlString xgetProcessContent() {
                XmlString xmlString;
                synchronized (monitor()) {
                    check_orphaned();
                    xmlString = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[3]);
                }
                return xmlString;
            }

            public boolean isSetProcessContent() {
                boolean z;
                synchronized (monitor()) {
                    check_orphaned();
                    z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
                }
                return z;
            }

            /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void setProcessContent(java.lang.String r6) {
                /*
                    r5 = this;
                    java.lang.Object r0 = r5.monitor()
                    monitor-enter(r0)
                    r5.check_orphaned()     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
                    javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
                    r3 = 3
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
                throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl.AlternateContentImpl.ChoiceImpl.setProcessContent(java.lang.String):void");
            }

            /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void xsetProcessContent(org.apache.xmlbeans.XmlString r6) {
                /*
                    r5 = this;
                    java.lang.Object r0 = r5.monitor()
                    monitor-enter(r0)
                    r5.check_orphaned()     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
                    javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
                    r3 = 3
                    r4 = r2[r3]     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002b }
                    if (r1 != 0) goto L_0x0026
                    org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
                    r1 = r2[r3]     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
                    r1 = r5
                    org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002b }
                L_0x0026:
                    r1.set(r6)     // Catch:{ all -> 0x002b }
                    monitor-exit(r0)     // Catch:{ all -> 0x002b }
                    return
                L_0x002b:
                    r5 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x002b }
                    throw r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl.AlternateContentImpl.ChoiceImpl.xsetProcessContent(org.apache.xmlbeans.XmlString):void");
            }

            public void unsetProcessContent() {
                synchronized (monitor()) {
                    check_orphaned();
                    get_store().remove_attribute(PROPERTY_QNAME[3]);
                }
            }
        }

        public static class FallbackImpl extends XmlComplexContentImpl implements AlternateContentDocument.AlternateContent.Fallback {
            private static final QName[] PROPERTY_QNAME = {new QName(PackageNamespaces.MARKUP_COMPATIBILITY, "Ignorable"), new QName(PackageNamespaces.MARKUP_COMPATIBILITY, "MustUnderstand"), new QName(PackageNamespaces.MARKUP_COMPATIBILITY, "ProcessContent")};
            private static final long serialVersionUID = 1;

            public FallbackImpl(SchemaType schemaType) {
                super(schemaType);
            }

            public String getIgnorable() {
                String str;
                synchronized (monitor()) {
                    check_orphaned();
                    SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                    if (simpleValue == null) {
                        str = null;
                    } else {
                        str = simpleValue.getStringValue();
                    }
                }
                return str;
            }

            public XmlString xgetIgnorable() {
                XmlString xmlString;
                synchronized (monitor()) {
                    check_orphaned();
                    xmlString = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                }
                return xmlString;
            }

            public boolean isSetIgnorable() {
                boolean z;
                synchronized (monitor()) {
                    check_orphaned();
                    z = false;
                    if (get_store().find_attribute_user(PROPERTY_QNAME[0]) != null) {
                        z = true;
                    }
                }
                return z;
            }

            /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void setIgnorable(java.lang.String r6) {
                /*
                    r5 = this;
                    java.lang.Object r0 = r5.monitor()
                    monitor-enter(r0)
                    r5.check_orphaned()     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
                    javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
                    r3 = 0
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
                throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl.AlternateContentImpl.FallbackImpl.setIgnorable(java.lang.String):void");
            }

            /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void xsetIgnorable(org.apache.xmlbeans.XmlString r6) {
                /*
                    r5 = this;
                    java.lang.Object r0 = r5.monitor()
                    monitor-enter(r0)
                    r5.check_orphaned()     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
                    javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
                    r3 = 0
                    r4 = r2[r3]     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002b }
                    if (r1 != 0) goto L_0x0026
                    org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
                    r1 = r2[r3]     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
                    r1 = r5
                    org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002b }
                L_0x0026:
                    r1.set(r6)     // Catch:{ all -> 0x002b }
                    monitor-exit(r0)     // Catch:{ all -> 0x002b }
                    return
                L_0x002b:
                    r5 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x002b }
                    throw r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl.AlternateContentImpl.FallbackImpl.xsetIgnorable(org.apache.xmlbeans.XmlString):void");
            }

            public void unsetIgnorable() {
                synchronized (monitor()) {
                    check_orphaned();
                    get_store().remove_attribute(PROPERTY_QNAME[0]);
                }
            }

            public String getMustUnderstand() {
                String str;
                synchronized (monitor()) {
                    check_orphaned();
                    SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                    if (simpleValue == null) {
                        str = null;
                    } else {
                        str = simpleValue.getStringValue();
                    }
                }
                return str;
            }

            public XmlString xgetMustUnderstand() {
                XmlString xmlString;
                synchronized (monitor()) {
                    check_orphaned();
                    xmlString = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                }
                return xmlString;
            }

            public boolean isSetMustUnderstand() {
                boolean z;
                synchronized (monitor()) {
                    check_orphaned();
                    z = true;
                    if (get_store().find_attribute_user(PROPERTY_QNAME[1]) == null) {
                        z = false;
                    }
                }
                return z;
            }

            /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void setMustUnderstand(java.lang.String r6) {
                /*
                    r5 = this;
                    java.lang.Object r0 = r5.monitor()
                    monitor-enter(r0)
                    r5.check_orphaned()     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
                    javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
                    r3 = 1
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
                throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl.AlternateContentImpl.FallbackImpl.setMustUnderstand(java.lang.String):void");
            }

            /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void xsetMustUnderstand(org.apache.xmlbeans.XmlString r6) {
                /*
                    r5 = this;
                    java.lang.Object r0 = r5.monitor()
                    monitor-enter(r0)
                    r5.check_orphaned()     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
                    javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
                    r3 = 1
                    r4 = r2[r3]     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002b }
                    if (r1 != 0) goto L_0x0026
                    org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
                    r1 = r2[r3]     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
                    r1 = r5
                    org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002b }
                L_0x0026:
                    r1.set(r6)     // Catch:{ all -> 0x002b }
                    monitor-exit(r0)     // Catch:{ all -> 0x002b }
                    return
                L_0x002b:
                    r5 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x002b }
                    throw r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl.AlternateContentImpl.FallbackImpl.xsetMustUnderstand(org.apache.xmlbeans.XmlString):void");
            }

            public void unsetMustUnderstand() {
                synchronized (monitor()) {
                    check_orphaned();
                    get_store().remove_attribute(PROPERTY_QNAME[1]);
                }
            }

            public String getProcessContent() {
                String str;
                synchronized (monitor()) {
                    check_orphaned();
                    SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
                    if (simpleValue == null) {
                        str = null;
                    } else {
                        str = simpleValue.getStringValue();
                    }
                }
                return str;
            }

            public XmlString xgetProcessContent() {
                XmlString xmlString;
                synchronized (monitor()) {
                    check_orphaned();
                    xmlString = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[2]);
                }
                return xmlString;
            }

            public boolean isSetProcessContent() {
                boolean z;
                synchronized (monitor()) {
                    check_orphaned();
                    z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
                }
                return z;
            }

            /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void setProcessContent(java.lang.String r6) {
                /*
                    r5 = this;
                    java.lang.Object r0 = r5.monitor()
                    monitor-enter(r0)
                    r5.check_orphaned()     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
                    javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
                    r3 = 2
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
                throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl.AlternateContentImpl.FallbackImpl.setProcessContent(java.lang.String):void");
            }

            /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void xsetProcessContent(org.apache.xmlbeans.XmlString r6) {
                /*
                    r5 = this;
                    java.lang.Object r0 = r5.monitor()
                    monitor-enter(r0)
                    r5.check_orphaned()     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
                    javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
                    r3 = 2
                    r4 = r2[r3]     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002b }
                    if (r1 != 0) goto L_0x0026
                    org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
                    r1 = r2[r3]     // Catch:{ all -> 0x002b }
                    org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
                    r1 = r5
                    org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002b }
                L_0x0026:
                    r1.set(r6)     // Catch:{ all -> 0x002b }
                    monitor-exit(r0)     // Catch:{ all -> 0x002b }
                    return
                L_0x002b:
                    r5 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x002b }
                    throw r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl.AlternateContentImpl.FallbackImpl.xsetProcessContent(org.apache.xmlbeans.XmlString):void");
            }

            public void unsetProcessContent() {
                synchronized (monitor()) {
                    check_orphaned();
                    get_store().remove_attribute(PROPERTY_QNAME[2]);
                }
            }
        }
    }
}
