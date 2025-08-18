package org.openxmlformats.schemas.drawingml.x2006.chart;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTExtensionList extends XmlObject {
    public static final DocumentFactory<CTExtensionList> Factory;
    public static final SchemaType type;

    CTExtension addNewExt();

    CTExtension getExtArray(int i);

    CTExtension[] getExtArray();

    List<CTExtension> getExtList();

    CTExtension insertNewExt(int i);

    void removeExt(int i);

    void setExtArray(int i, CTExtension cTExtension);

    void setExtArray(CTExtension[] cTExtensionArr);

    int sizeOfExtArray();

    static {
        DocumentFactory<CTExtensionList> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctextensionlist7389type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
