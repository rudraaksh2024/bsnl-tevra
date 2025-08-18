package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;

public interface CTHdrFtrRef extends CTRel {
    public static final DocumentFactory<CTHdrFtrRef> Factory;
    public static final SchemaType type;

    STHdrFtr.Enum getType();

    void setType(STHdrFtr.Enum enumR);

    STHdrFtr xgetType();

    void xsetType(STHdrFtr sTHdrFtr);

    static {
        DocumentFactory<CTHdrFtrRef> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cthdrftrref224dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
