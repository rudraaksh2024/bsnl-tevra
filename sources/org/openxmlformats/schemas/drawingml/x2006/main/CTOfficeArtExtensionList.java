package org.openxmlformats.schemas.drawingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTOfficeArtExtensionList extends XmlObject {
    public static final DocumentFactory<CTOfficeArtExtensionList> Factory;
    public static final SchemaType type;

    CTOfficeArtExtension addNewExt();

    CTOfficeArtExtension getExtArray(int i);

    CTOfficeArtExtension[] getExtArray();

    List<CTOfficeArtExtension> getExtList();

    CTOfficeArtExtension insertNewExt(int i);

    void removeExt(int i);

    void setExtArray(int i, CTOfficeArtExtension cTOfficeArtExtension);

    void setExtArray(CTOfficeArtExtension[] cTOfficeArtExtensionArr);

    int sizeOfExtArray();

    static {
        DocumentFactory<CTOfficeArtExtensionList> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctofficeartextensionlista211type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
