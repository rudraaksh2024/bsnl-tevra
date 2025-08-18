package org.apache.xmlbeans.impl.xb.xmlconfig.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument;
import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.Usertypeconfig;

public class ConfigDocumentImpl extends XmlComplexContentImpl implements ConfigDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "config")};
    private static final long serialVersionUID = 1;

    public ConfigDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public ConfigDocument.Config getConfig() {
        ConfigDocument.Config config;
        synchronized (monitor()) {
            check_orphaned();
            config = (ConfigDocument.Config) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (config == null) {
                config = null;
            }
        }
        return config;
    }

    public void setConfig(ConfigDocument.Config config) {
        generatedSetterHelperImpl(config, PROPERTY_QNAME[0], 0, 1);
    }

    public ConfigDocument.Config addNewConfig() {
        ConfigDocument.Config config;
        synchronized (monitor()) {
            check_orphaned();
            config = (ConfigDocument.Config) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return config;
    }

    public static class ConfigImpl extends XmlComplexContentImpl implements ConfigDocument.Config {
        private static final QName[] PROPERTY_QNAME = {new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "namespace"), new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "qname"), new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "extension"), new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "usertype")};
        private static final long serialVersionUID = 1;

        public ConfigImpl(SchemaType schemaType) {
            super(schemaType);
        }

        public List<Nsconfig> getNamespaceList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda15(this), new ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda16(this), new ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda17(this), new ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda18(this), new ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda19(this));
            }
            return javaListXmlObject;
        }

        public Nsconfig[] getNamespaceArray() {
            return (Nsconfig[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new Nsconfig[0]);
        }

        public Nsconfig getNamespaceArray(int i) {
            Nsconfig nsconfig;
            synchronized (monitor()) {
                check_orphaned();
                nsconfig = (Nsconfig) get_store().find_element_user(PROPERTY_QNAME[0], i);
                if (nsconfig == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return nsconfig;
        }

        public int sizeOfNamespaceArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
            }
            return count_elements;
        }

        public void setNamespaceArray(Nsconfig[] nsconfigArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) nsconfigArr, PROPERTY_QNAME[0]);
        }

        public void setNamespaceArray(int i, Nsconfig nsconfig) {
            generatedSetterHelperImpl(nsconfig, PROPERTY_QNAME[0], i, 2);
        }

        public Nsconfig insertNewNamespace(int i) {
            Nsconfig nsconfig;
            synchronized (monitor()) {
                check_orphaned();
                nsconfig = (Nsconfig) get_store().insert_element_user(PROPERTY_QNAME[0], i);
            }
            return nsconfig;
        }

        public Nsconfig addNewNamespace() {
            Nsconfig nsconfig;
            synchronized (monitor()) {
                check_orphaned();
                nsconfig = (Nsconfig) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return nsconfig;
        }

        public void removeNamespace(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], i);
            }
        }

        public List<Qnameconfig> getQnameList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda6(this), new ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda7(this), new ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda8(this), new ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda9(this), new ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda10(this));
            }
            return javaListXmlObject;
        }

        public Qnameconfig[] getQnameArray() {
            return (Qnameconfig[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new Qnameconfig[0]);
        }

        public Qnameconfig getQnameArray(int i) {
            Qnameconfig qnameconfig;
            synchronized (monitor()) {
                check_orphaned();
                qnameconfig = (Qnameconfig) get_store().find_element_user(PROPERTY_QNAME[1], i);
                if (qnameconfig == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return qnameconfig;
        }

        public int sizeOfQnameArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
            }
            return count_elements;
        }

        public void setQnameArray(Qnameconfig[] qnameconfigArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) qnameconfigArr, PROPERTY_QNAME[1]);
        }

        public void setQnameArray(int i, Qnameconfig qnameconfig) {
            generatedSetterHelperImpl(qnameconfig, PROPERTY_QNAME[1], i, 2);
        }

        public Qnameconfig insertNewQname(int i) {
            Qnameconfig qnameconfig;
            synchronized (monitor()) {
                check_orphaned();
                qnameconfig = (Qnameconfig) get_store().insert_element_user(PROPERTY_QNAME[1], i);
            }
            return qnameconfig;
        }

        public Qnameconfig addNewQname() {
            Qnameconfig qnameconfig;
            synchronized (monitor()) {
                check_orphaned();
                qnameconfig = (Qnameconfig) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            return qnameconfig;
        }

        public void removeQname(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[1], i);
            }
        }

        public List<Extensionconfig> getExtensionList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda0(this), new ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda11(this), new ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda12(this), new ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda13(this), new ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda14(this));
            }
            return javaListXmlObject;
        }

        public Extensionconfig[] getExtensionArray() {
            return (Extensionconfig[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new Extensionconfig[0]);
        }

        public Extensionconfig getExtensionArray(int i) {
            Extensionconfig extensionconfig;
            synchronized (monitor()) {
                check_orphaned();
                extensionconfig = (Extensionconfig) get_store().find_element_user(PROPERTY_QNAME[2], i);
                if (extensionconfig == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return extensionconfig;
        }

        public int sizeOfExtensionArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
            }
            return count_elements;
        }

        public void setExtensionArray(Extensionconfig[] extensionconfigArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) extensionconfigArr, PROPERTY_QNAME[2]);
        }

        public void setExtensionArray(int i, Extensionconfig extensionconfig) {
            generatedSetterHelperImpl(extensionconfig, PROPERTY_QNAME[2], i, 2);
        }

        public Extensionconfig insertNewExtension(int i) {
            Extensionconfig extensionconfig;
            synchronized (monitor()) {
                check_orphaned();
                extensionconfig = (Extensionconfig) get_store().insert_element_user(PROPERTY_QNAME[2], i);
            }
            return extensionconfig;
        }

        public Extensionconfig addNewExtension() {
            Extensionconfig extensionconfig;
            synchronized (monitor()) {
                check_orphaned();
                extensionconfig = (Extensionconfig) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            return extensionconfig;
        }

        public void removeExtension(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[2], i);
            }
        }

        public List<Usertypeconfig> getUsertypeList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda1(this), new ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda2(this), new ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda3(this), new ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda4(this), new ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda5(this));
            }
            return javaListXmlObject;
        }

        public Usertypeconfig[] getUsertypeArray() {
            return (Usertypeconfig[]) getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new Usertypeconfig[0]);
        }

        public Usertypeconfig getUsertypeArray(int i) {
            Usertypeconfig usertypeconfig;
            synchronized (monitor()) {
                check_orphaned();
                usertypeconfig = (Usertypeconfig) get_store().find_element_user(PROPERTY_QNAME[3], i);
                if (usertypeconfig == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return usertypeconfig;
        }

        public int sizeOfUsertypeArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
            }
            return count_elements;
        }

        public void setUsertypeArray(Usertypeconfig[] usertypeconfigArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) usertypeconfigArr, PROPERTY_QNAME[3]);
        }

        public void setUsertypeArray(int i, Usertypeconfig usertypeconfig) {
            generatedSetterHelperImpl(usertypeconfig, PROPERTY_QNAME[3], i, 2);
        }

        public Usertypeconfig insertNewUsertype(int i) {
            Usertypeconfig usertypeconfig;
            synchronized (monitor()) {
                check_orphaned();
                usertypeconfig = (Usertypeconfig) get_store().insert_element_user(PROPERTY_QNAME[3], i);
            }
            return usertypeconfig;
        }

        public Usertypeconfig addNewUsertype() {
            Usertypeconfig usertypeconfig;
            synchronized (monitor()) {
                check_orphaned();
                usertypeconfig = (Usertypeconfig) get_store().add_element_user(PROPERTY_QNAME[3]);
            }
            return usertypeconfig;
        }

        public void removeUsertype(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[3], i);
            }
        }
    }
}
