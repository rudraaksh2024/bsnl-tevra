package org.apache.xmlbeans.impl.xb.xmlconfig;

import java.util.List;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLCONFIG.TypeSystemHolder;

public interface ConfigDocument extends XmlObject {
    public static final DocumentFactory<ConfigDocument> Factory;
    public static final SchemaType type;

    Config addNewConfig();

    Config getConfig();

    void setConfig(Config config);

    static {
        DocumentFactory<ConfigDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "config4185doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface Config extends XmlObject {
        public static final ElementFactory<Config> Factory;
        public static final SchemaType type;

        Extensionconfig addNewExtension();

        Nsconfig addNewNamespace();

        Qnameconfig addNewQname();

        Usertypeconfig addNewUsertype();

        Extensionconfig getExtensionArray(int i);

        Extensionconfig[] getExtensionArray();

        List<Extensionconfig> getExtensionList();

        Nsconfig getNamespaceArray(int i);

        Nsconfig[] getNamespaceArray();

        List<Nsconfig> getNamespaceList();

        Qnameconfig getQnameArray(int i);

        Qnameconfig[] getQnameArray();

        List<Qnameconfig> getQnameList();

        Usertypeconfig getUsertypeArray(int i);

        Usertypeconfig[] getUsertypeArray();

        List<Usertypeconfig> getUsertypeList();

        Extensionconfig insertNewExtension(int i);

        Nsconfig insertNewNamespace(int i);

        Qnameconfig insertNewQname(int i);

        Usertypeconfig insertNewUsertype(int i);

        void removeExtension(int i);

        void removeNamespace(int i);

        void removeQname(int i);

        void removeUsertype(int i);

        void setExtensionArray(int i, Extensionconfig extensionconfig);

        void setExtensionArray(Extensionconfig[] extensionconfigArr);

        void setNamespaceArray(int i, Nsconfig nsconfig);

        void setNamespaceArray(Nsconfig[] nsconfigArr);

        void setQnameArray(int i, Qnameconfig qnameconfig);

        void setQnameArray(Qnameconfig[] qnameconfigArr);

        void setUsertypeArray(int i, Usertypeconfig usertypeconfig);

        void setUsertypeArray(Usertypeconfig[] usertypeconfigArr);

        int sizeOfExtensionArray();

        int sizeOfNamespaceArray();

        int sizeOfQnameArray();

        int sizeOfUsertypeArray();

        static {
            ElementFactory<Config> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "configf467elemtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }
    }
}
