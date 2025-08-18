package org.apache.xmlbeans.impl.xb.xsdownload;

import java.util.List;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLTOOLS.TypeSystemHolder;

public interface DownloadedSchemasDocument extends XmlObject {
    public static final DocumentFactory<DownloadedSchemasDocument> Factory;
    public static final SchemaType type;

    DownloadedSchemas addNewDownloadedSchemas();

    DownloadedSchemas getDownloadedSchemas();

    void setDownloadedSchemas(DownloadedSchemas downloadedSchemas);

    static {
        DocumentFactory<DownloadedSchemasDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "downloadedschemas2dd7doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface DownloadedSchemas extends XmlObject {
        public static final ElementFactory<DownloadedSchemas> Factory;
        public static final SchemaType type;

        DownloadedSchemaEntry addNewEntry();

        String getDefaultDirectory();

        DownloadedSchemaEntry getEntryArray(int i);

        DownloadedSchemaEntry[] getEntryArray();

        List<DownloadedSchemaEntry> getEntryList();

        DownloadedSchemaEntry insertNewEntry(int i);

        boolean isSetDefaultDirectory();

        void removeEntry(int i);

        void setDefaultDirectory(String str);

        void setEntryArray(int i, DownloadedSchemaEntry downloadedSchemaEntry);

        void setEntryArray(DownloadedSchemaEntry[] downloadedSchemaEntryArr);

        int sizeOfEntryArray();

        void unsetDefaultDirectory();

        XmlToken xgetDefaultDirectory();

        void xsetDefaultDirectory(XmlToken xmlToken);

        static {
            ElementFactory<DownloadedSchemas> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "downloadedschemasb3efelemtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }
    }
}
