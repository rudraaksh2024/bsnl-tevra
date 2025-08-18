package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STProofErr;

public interface CTProofErr extends XmlObject {
    public static final DocumentFactory<CTProofErr> Factory;
    public static final SchemaType type;

    STProofErr.Enum getType();

    void setType(STProofErr.Enum enumR);

    STProofErr xgetType();

    void xsetType(STProofErr sTProofErr);

    static {
        DocumentFactory<CTProofErr> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctprooferr1e07type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
