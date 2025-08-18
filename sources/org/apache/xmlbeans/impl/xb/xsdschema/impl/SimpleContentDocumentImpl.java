package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleContentDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleExtensionType;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleRestrictionType;

public class SimpleContentDocumentImpl extends XmlComplexContentImpl implements SimpleContentDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "simpleContent")};
    private static final long serialVersionUID = 1;

    public SimpleContentDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public SimpleContentDocument.SimpleContent getSimpleContent() {
        SimpleContentDocument.SimpleContent simpleContent;
        synchronized (monitor()) {
            check_orphaned();
            simpleContent = (SimpleContentDocument.SimpleContent) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (simpleContent == null) {
                simpleContent = null;
            }
        }
        return simpleContent;
    }

    public void setSimpleContent(SimpleContentDocument.SimpleContent simpleContent) {
        generatedSetterHelperImpl(simpleContent, PROPERTY_QNAME[0], 0, 1);
    }

    public SimpleContentDocument.SimpleContent addNewSimpleContent() {
        SimpleContentDocument.SimpleContent simpleContent;
        synchronized (monitor()) {
            check_orphaned();
            simpleContent = (SimpleContentDocument.SimpleContent) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return simpleContent;
    }

    public static class SimpleContentImpl extends AnnotatedImpl implements SimpleContentDocument.SimpleContent {
        private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "restriction"), new QName("http://www.w3.org/2001/XMLSchema", "extension")};
        private static final long serialVersionUID = 1;

        public SimpleContentImpl(SchemaType schemaType) {
            super(schemaType);
        }

        public SimpleRestrictionType getRestriction() {
            SimpleRestrictionType simpleRestrictionType;
            synchronized (monitor()) {
                check_orphaned();
                simpleRestrictionType = (SimpleRestrictionType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
                if (simpleRestrictionType == null) {
                    simpleRestrictionType = null;
                }
            }
            return simpleRestrictionType;
        }

        public boolean isSetRestriction() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = false;
                if (get_store().count_elements(PROPERTY_QNAME[0]) != 0) {
                    z = true;
                }
            }
            return z;
        }

        public void setRestriction(SimpleRestrictionType simpleRestrictionType) {
            generatedSetterHelperImpl(simpleRestrictionType, PROPERTY_QNAME[0], 0, 1);
        }

        public SimpleRestrictionType addNewRestriction() {
            SimpleRestrictionType simpleRestrictionType;
            synchronized (monitor()) {
                check_orphaned();
                simpleRestrictionType = (SimpleRestrictionType) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return simpleRestrictionType;
        }

        public void unsetRestriction() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], 0);
            }
        }

        public SimpleExtensionType getExtension() {
            SimpleExtensionType simpleExtensionType;
            synchronized (monitor()) {
                check_orphaned();
                simpleExtensionType = (SimpleExtensionType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
                if (simpleExtensionType == null) {
                    simpleExtensionType = null;
                }
            }
            return simpleExtensionType;
        }

        public boolean isSetExtension() {
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

        public void setExtension(SimpleExtensionType simpleExtensionType) {
            generatedSetterHelperImpl(simpleExtensionType, PROPERTY_QNAME[1], 0, 1);
        }

        public SimpleExtensionType addNewExtension() {
            SimpleExtensionType simpleExtensionType;
            synchronized (monitor()) {
                check_orphaned();
                simpleExtensionType = (SimpleExtensionType) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            return simpleExtensionType;
        }

        public void unsetExtension() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[1], 0);
            }
        }
    }
}
