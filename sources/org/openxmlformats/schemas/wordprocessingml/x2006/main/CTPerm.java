package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDisplacedByCustomXml;

public interface CTPerm extends XmlObject {
    public static final DocumentFactory<CTPerm> Factory;
    public static final SchemaType type;

    STDisplacedByCustomXml.Enum getDisplacedByCustomXml();

    String getId();

    boolean isSetDisplacedByCustomXml();

    void setDisplacedByCustomXml(STDisplacedByCustomXml.Enum enumR);

    void setId(String str);

    void unsetDisplacedByCustomXml();

    STDisplacedByCustomXml xgetDisplacedByCustomXml();

    STString xgetId();

    void xsetDisplacedByCustomXml(STDisplacedByCustomXml sTDisplacedByCustomXml);

    void xsetId(STString sTString);

    static {
        DocumentFactory<CTPerm> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctperm7878type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
