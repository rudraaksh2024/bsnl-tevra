package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;

public interface CTBookmark extends CTBookmarkRange {
    public static final DocumentFactory<CTBookmark> Factory;
    public static final SchemaType type;

    String getName();

    void setName(String str);

    STString xgetName();

    void xsetName(STString sTString);

    static {
        DocumentFactory<CTBookmark> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctbookmarkd672type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
