package org.apache.xmlbeans.impl.xb.ltgfmt;

import java.util.List;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLTOOLS.TypeSystemHolder;

public interface TestCase extends XmlObject {
    public static final DocumentFactory<TestCase> Factory;
    public static final SchemaType type;

    Files addNewFiles();

    String getDescription();

    Files getFiles();

    String getId();

    boolean getModified();

    String getOrigin();

    boolean isSetDescription();

    boolean isSetId();

    boolean isSetModified();

    boolean isSetOrigin();

    void setDescription(String str);

    void setFiles(Files files);

    void setId(String str);

    void setModified(boolean z);

    void setOrigin(String str);

    void unsetDescription();

    void unsetId();

    void unsetModified();

    void unsetOrigin();

    XmlString xgetDescription();

    XmlID xgetId();

    XmlBoolean xgetModified();

    XmlToken xgetOrigin();

    void xsetDescription(XmlString xmlString);

    void xsetId(XmlID xmlID);

    void xsetModified(XmlBoolean xmlBoolean);

    void xsetOrigin(XmlToken xmlToken);

    static {
        DocumentFactory<TestCase> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "testcase939btype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface Files extends XmlObject {
        public static final ElementFactory<Files> Factory;
        public static final SchemaType type;

        FileDesc addNewFile();

        FileDesc getFileArray(int i);

        FileDesc[] getFileArray();

        List<FileDesc> getFileList();

        FileDesc insertNewFile(int i);

        void removeFile(int i);

        void setFileArray(int i, FileDesc fileDesc);

        void setFileArray(FileDesc[] fileDescArr);

        int sizeOfFileArray();

        static {
            ElementFactory<Files> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "files7c3eelemtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }
    }
}
