package org.apache.xmlbeans.impl.xb.substwsdl;

import java.util.List;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLTOOLS.TypeSystemHolder;

public interface DefinitionsDocument extends XmlObject {
    public static final DocumentFactory<DefinitionsDocument> Factory;
    public static final SchemaType type;

    Definitions addNewDefinitions();

    Definitions getDefinitions();

    void setDefinitions(Definitions definitions);

    static {
        DocumentFactory<DefinitionsDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "definitionsc7f1doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface Definitions extends XmlObject {
        public static final ElementFactory<Definitions> Factory;
        public static final SchemaType type;

        XmlObject addNewBinding();

        TImport addNewImport();

        XmlObject addNewMessage();

        XmlObject addNewPortType();

        XmlObject addNewService();

        XmlObject addNewTypes();

        XmlObject getBindingArray(int i);

        XmlObject[] getBindingArray();

        List<XmlObject> getBindingList();

        TImport getImportArray(int i);

        TImport[] getImportArray();

        List<TImport> getImportList();

        XmlObject getMessageArray(int i);

        XmlObject[] getMessageArray();

        List<XmlObject> getMessageList();

        XmlObject getPortTypeArray(int i);

        XmlObject[] getPortTypeArray();

        List<XmlObject> getPortTypeList();

        XmlObject getServiceArray(int i);

        XmlObject[] getServiceArray();

        List<XmlObject> getServiceList();

        XmlObject getTypesArray(int i);

        XmlObject[] getTypesArray();

        List<XmlObject> getTypesList();

        XmlObject insertNewBinding(int i);

        TImport insertNewImport(int i);

        XmlObject insertNewMessage(int i);

        XmlObject insertNewPortType(int i);

        XmlObject insertNewService(int i);

        XmlObject insertNewTypes(int i);

        void removeBinding(int i);

        void removeImport(int i);

        void removeMessage(int i);

        void removePortType(int i);

        void removeService(int i);

        void removeTypes(int i);

        void setBindingArray(int i, XmlObject xmlObject);

        void setBindingArray(XmlObject[] xmlObjectArr);

        void setImportArray(int i, TImport tImport);

        void setImportArray(TImport[] tImportArr);

        void setMessageArray(int i, XmlObject xmlObject);

        void setMessageArray(XmlObject[] xmlObjectArr);

        void setPortTypeArray(int i, XmlObject xmlObject);

        void setPortTypeArray(XmlObject[] xmlObjectArr);

        void setServiceArray(int i, XmlObject xmlObject);

        void setServiceArray(XmlObject[] xmlObjectArr);

        void setTypesArray(int i, XmlObject xmlObject);

        void setTypesArray(XmlObject[] xmlObjectArr);

        int sizeOfBindingArray();

        int sizeOfImportArray();

        int sizeOfMessageArray();

        int sizeOfPortTypeArray();

        int sizeOfServiceArray();

        int sizeOfTypesArray();

        static {
            ElementFactory<Definitions> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "definitions05ddelemtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }
    }
}
