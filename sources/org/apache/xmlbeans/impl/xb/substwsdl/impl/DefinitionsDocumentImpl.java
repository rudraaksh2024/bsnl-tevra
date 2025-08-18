package org.apache.xmlbeans.impl.xb.substwsdl.impl;

import androidx.core.app.NotificationCompat;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument;
import org.apache.xmlbeans.impl.xb.substwsdl.TImport;

public class DefinitionsDocumentImpl extends XmlComplexContentImpl implements DefinitionsDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.apache.org/internal/xmlbeans/wsdlsubst", "definitions")};
    private static final long serialVersionUID = 1;

    public DefinitionsDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public DefinitionsDocument.Definitions getDefinitions() {
        DefinitionsDocument.Definitions definitions;
        synchronized (monitor()) {
            check_orphaned();
            definitions = (DefinitionsDocument.Definitions) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (definitions == null) {
                definitions = null;
            }
        }
        return definitions;
    }

    public void setDefinitions(DefinitionsDocument.Definitions definitions) {
        generatedSetterHelperImpl(definitions, PROPERTY_QNAME[0], 0, 1);
    }

    public DefinitionsDocument.Definitions addNewDefinitions() {
        DefinitionsDocument.Definitions definitions;
        synchronized (monitor()) {
            check_orphaned();
            definitions = (DefinitionsDocument.Definitions) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return definitions;
    }

    public static class DefinitionsImpl extends XmlComplexContentImpl implements DefinitionsDocument.Definitions {
        private static final QName[] PROPERTY_QNAME = {new QName("http://www.apache.org/internal/xmlbeans/wsdlsubst", "import"), new QName("http://www.apache.org/internal/xmlbeans/wsdlsubst", "types"), new QName("http://www.apache.org/internal/xmlbeans/wsdlsubst", "message"), new QName("http://www.apache.org/internal/xmlbeans/wsdlsubst", "binding"), new QName("http://www.apache.org/internal/xmlbeans/wsdlsubst", "portType"), new QName("http://www.apache.org/internal/xmlbeans/wsdlsubst", NotificationCompat.CATEGORY_SERVICE)};
        private static final long serialVersionUID = 1;

        public DefinitionsImpl(SchemaType schemaType) {
            super(schemaType);
        }

        public List<TImport> getImportList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda17(this), new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda18(this), new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda19(this), new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda20(this), new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda21(this));
            }
            return javaListXmlObject;
        }

        public TImport[] getImportArray() {
            return (TImport[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new TImport[0]);
        }

        public TImport getImportArray(int i) {
            TImport tImport;
            synchronized (monitor()) {
                check_orphaned();
                tImport = (TImport) get_store().find_element_user(PROPERTY_QNAME[0], i);
                if (tImport == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return tImport;
        }

        public int sizeOfImportArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
            }
            return count_elements;
        }

        public void setImportArray(TImport[] tImportArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) tImportArr, PROPERTY_QNAME[0]);
        }

        public void setImportArray(int i, TImport tImport) {
            generatedSetterHelperImpl(tImport, PROPERTY_QNAME[0], i, 2);
        }

        public TImport insertNewImport(int i) {
            TImport tImport;
            synchronized (monitor()) {
                check_orphaned();
                tImport = (TImport) get_store().insert_element_user(PROPERTY_QNAME[0], i);
            }
            return tImport;
        }

        public TImport addNewImport() {
            TImport tImport;
            synchronized (monitor()) {
                check_orphaned();
                tImport = (TImport) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return tImport;
        }

        public void removeImport(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], i);
            }
        }

        public List<XmlObject> getTypesList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda0(this), new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda11(this), new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda22(this), new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda23(this), new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda24(this));
            }
            return javaListXmlObject;
        }

        public XmlObject[] getTypesArray() {
            return getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new XmlObject[0]);
        }

        public XmlObject getTypesArray(int i) {
            XmlObject xmlObject;
            synchronized (monitor()) {
                check_orphaned();
                xmlObject = (XmlObject) get_store().find_element_user(PROPERTY_QNAME[1], i);
                if (xmlObject == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return xmlObject;
        }

        public int sizeOfTypesArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
            }
            return count_elements;
        }

        public void setTypesArray(XmlObject[] xmlObjectArr) {
            check_orphaned();
            arraySetterHelper(xmlObjectArr, PROPERTY_QNAME[1]);
        }

        public void setTypesArray(int i, XmlObject xmlObject) {
            generatedSetterHelperImpl(xmlObject, PROPERTY_QNAME[1], i, 2);
        }

        public XmlObject insertNewTypes(int i) {
            XmlObject xmlObject;
            synchronized (monitor()) {
                check_orphaned();
                xmlObject = (XmlObject) get_store().insert_element_user(PROPERTY_QNAME[1], i);
            }
            return xmlObject;
        }

        public XmlObject addNewTypes() {
            XmlObject xmlObject;
            synchronized (monitor()) {
                check_orphaned();
                xmlObject = (XmlObject) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            return xmlObject;
        }

        public void removeTypes(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[1], i);
            }
        }

        public List<XmlObject> getMessageList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda12(this), new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda13(this), new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda14(this), new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda15(this), new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda16(this));
            }
            return javaListXmlObject;
        }

        public XmlObject[] getMessageArray() {
            return getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new XmlObject[0]);
        }

        public XmlObject getMessageArray(int i) {
            XmlObject xmlObject;
            synchronized (monitor()) {
                check_orphaned();
                xmlObject = (XmlObject) get_store().find_element_user(PROPERTY_QNAME[2], i);
                if (xmlObject == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return xmlObject;
        }

        public int sizeOfMessageArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
            }
            return count_elements;
        }

        public void setMessageArray(XmlObject[] xmlObjectArr) {
            check_orphaned();
            arraySetterHelper(xmlObjectArr, PROPERTY_QNAME[2]);
        }

        public void setMessageArray(int i, XmlObject xmlObject) {
            generatedSetterHelperImpl(xmlObject, PROPERTY_QNAME[2], i, 2);
        }

        public XmlObject insertNewMessage(int i) {
            XmlObject xmlObject;
            synchronized (monitor()) {
                check_orphaned();
                xmlObject = (XmlObject) get_store().insert_element_user(PROPERTY_QNAME[2], i);
            }
            return xmlObject;
        }

        public XmlObject addNewMessage() {
            XmlObject xmlObject;
            synchronized (monitor()) {
                check_orphaned();
                xmlObject = (XmlObject) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            return xmlObject;
        }

        public void removeMessage(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[2], i);
            }
        }

        public List<XmlObject> getBindingList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda1(this), new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda2(this), new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda3(this), new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda4(this), new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda5(this));
            }
            return javaListXmlObject;
        }

        public XmlObject[] getBindingArray() {
            return getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new XmlObject[0]);
        }

        public XmlObject getBindingArray(int i) {
            XmlObject xmlObject;
            synchronized (monitor()) {
                check_orphaned();
                xmlObject = (XmlObject) get_store().find_element_user(PROPERTY_QNAME[3], i);
                if (xmlObject == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return xmlObject;
        }

        public int sizeOfBindingArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
            }
            return count_elements;
        }

        public void setBindingArray(XmlObject[] xmlObjectArr) {
            check_orphaned();
            arraySetterHelper(xmlObjectArr, PROPERTY_QNAME[3]);
        }

        public void setBindingArray(int i, XmlObject xmlObject) {
            generatedSetterHelperImpl(xmlObject, PROPERTY_QNAME[3], i, 2);
        }

        public XmlObject insertNewBinding(int i) {
            XmlObject xmlObject;
            synchronized (monitor()) {
                check_orphaned();
                xmlObject = (XmlObject) get_store().insert_element_user(PROPERTY_QNAME[3], i);
            }
            return xmlObject;
        }

        public XmlObject addNewBinding() {
            XmlObject xmlObject;
            synchronized (monitor()) {
                check_orphaned();
                xmlObject = (XmlObject) get_store().add_element_user(PROPERTY_QNAME[3]);
            }
            return xmlObject;
        }

        public void removeBinding(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[3], i);
            }
        }

        public List<XmlObject> getPortTypeList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda25(this), new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda26(this), new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda27(this), new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda28(this), new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda29(this));
            }
            return javaListXmlObject;
        }

        public XmlObject[] getPortTypeArray() {
            return getXmlObjectArray(PROPERTY_QNAME[4], (T[]) new XmlObject[0]);
        }

        public XmlObject getPortTypeArray(int i) {
            XmlObject xmlObject;
            synchronized (monitor()) {
                check_orphaned();
                xmlObject = (XmlObject) get_store().find_element_user(PROPERTY_QNAME[4], i);
                if (xmlObject == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return xmlObject;
        }

        public int sizeOfPortTypeArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
            }
            return count_elements;
        }

        public void setPortTypeArray(XmlObject[] xmlObjectArr) {
            check_orphaned();
            arraySetterHelper(xmlObjectArr, PROPERTY_QNAME[4]);
        }

        public void setPortTypeArray(int i, XmlObject xmlObject) {
            generatedSetterHelperImpl(xmlObject, PROPERTY_QNAME[4], i, 2);
        }

        public XmlObject insertNewPortType(int i) {
            XmlObject xmlObject;
            synchronized (monitor()) {
                check_orphaned();
                xmlObject = (XmlObject) get_store().insert_element_user(PROPERTY_QNAME[4], i);
            }
            return xmlObject;
        }

        public XmlObject addNewPortType() {
            XmlObject xmlObject;
            synchronized (monitor()) {
                check_orphaned();
                xmlObject = (XmlObject) get_store().add_element_user(PROPERTY_QNAME[4]);
            }
            return xmlObject;
        }

        public void removePortType(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[4], i);
            }
        }

        public List<XmlObject> getServiceList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda6(this), new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda7(this), new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda8(this), new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda9(this), new DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda10(this));
            }
            return javaListXmlObject;
        }

        public XmlObject[] getServiceArray() {
            return getXmlObjectArray(PROPERTY_QNAME[5], (T[]) new XmlObject[0]);
        }

        public XmlObject getServiceArray(int i) {
            XmlObject xmlObject;
            synchronized (monitor()) {
                check_orphaned();
                xmlObject = (XmlObject) get_store().find_element_user(PROPERTY_QNAME[5], i);
                if (xmlObject == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return xmlObject;
        }

        public int sizeOfServiceArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
            }
            return count_elements;
        }

        public void setServiceArray(XmlObject[] xmlObjectArr) {
            check_orphaned();
            arraySetterHelper(xmlObjectArr, PROPERTY_QNAME[5]);
        }

        public void setServiceArray(int i, XmlObject xmlObject) {
            generatedSetterHelperImpl(xmlObject, PROPERTY_QNAME[5], i, 2);
        }

        public XmlObject insertNewService(int i) {
            XmlObject xmlObject;
            synchronized (monitor()) {
                check_orphaned();
                xmlObject = (XmlObject) get_store().insert_element_user(PROPERTY_QNAME[5], i);
            }
            return xmlObject;
        }

        public XmlObject addNewService() {
            XmlObject xmlObject;
            synchronized (monitor()) {
                check_orphaned();
                xmlObject = (XmlObject) get_store().add_element_user(PROPERTY_QNAME[5]);
            }
            return xmlObject;
        }

        public void removeService(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[5], i);
            }
        }
    }
}
