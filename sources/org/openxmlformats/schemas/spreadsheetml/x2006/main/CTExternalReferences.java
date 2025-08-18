package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTExternalReferences extends XmlObject {
    public static final DocumentFactory<CTExternalReferences> Factory;
    public static final SchemaType type;

    CTExternalReference addNewExternalReference();

    CTExternalReference getExternalReferenceArray(int i);

    CTExternalReference[] getExternalReferenceArray();

    List<CTExternalReference> getExternalReferenceList();

    CTExternalReference insertNewExternalReference(int i);

    void removeExternalReference(int i);

    void setExternalReferenceArray(int i, CTExternalReference cTExternalReference);

    void setExternalReferenceArray(CTExternalReference[] cTExternalReferenceArr);

    int sizeOfExternalReferenceArray();

    static {
        DocumentFactory<CTExternalReferences> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctexternalreferencesd77ctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
