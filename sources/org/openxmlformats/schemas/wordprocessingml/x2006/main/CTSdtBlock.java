package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTSdtBlock extends XmlObject {
    public static final DocumentFactory<CTSdtBlock> Factory;
    public static final SchemaType type;

    CTSdtContentBlock addNewSdtContent();

    CTSdtEndPr addNewSdtEndPr();

    CTSdtPr addNewSdtPr();

    CTSdtContentBlock getSdtContent();

    CTSdtEndPr getSdtEndPr();

    CTSdtPr getSdtPr();

    boolean isSetSdtContent();

    boolean isSetSdtEndPr();

    boolean isSetSdtPr();

    void setSdtContent(CTSdtContentBlock cTSdtContentBlock);

    void setSdtEndPr(CTSdtEndPr cTSdtEndPr);

    void setSdtPr(CTSdtPr cTSdtPr);

    void unsetSdtContent();

    void unsetSdtEndPr();

    void unsetSdtPr();

    static {
        DocumentFactory<CTSdtBlock> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsdtblock221etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
