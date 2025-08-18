package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTPlaceholder extends XmlObject {
    public static final DocumentFactory<CTPlaceholder> Factory;
    public static final SchemaType type;

    CTString addNewDocPart();

    CTString getDocPart();

    void setDocPart(CTString cTString);

    static {
        DocumentFactory<CTPlaceholder> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctplaceholder117ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
