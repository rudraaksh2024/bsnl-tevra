package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDisplacedByCustomXml;

public interface CTMarkupRange extends CTMarkup {
    public static final DocumentFactory<CTMarkupRange> Factory;
    public static final SchemaType type;

    STDisplacedByCustomXml.Enum getDisplacedByCustomXml();

    boolean isSetDisplacedByCustomXml();

    void setDisplacedByCustomXml(STDisplacedByCustomXml.Enum enumR);

    void unsetDisplacedByCustomXml();

    STDisplacedByCustomXml xgetDisplacedByCustomXml();

    void xsetDisplacedByCustomXml(STDisplacedByCustomXml sTDisplacedByCustomXml);

    static {
        DocumentFactory<CTMarkupRange> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctmarkuprangeba3dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
