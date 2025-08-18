package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTExternalLink extends XmlObject {
    public static final DocumentFactory<CTExternalLink> Factory;
    public static final SchemaType type;

    CTDdeLink addNewDdeLink();

    CTExtensionList addNewExtLst();

    CTExternalBook addNewExternalBook();

    CTOleLink addNewOleLink();

    CTDdeLink getDdeLink();

    CTExtensionList getExtLst();

    CTExternalBook getExternalBook();

    CTOleLink getOleLink();

    boolean isSetDdeLink();

    boolean isSetExtLst();

    boolean isSetExternalBook();

    boolean isSetOleLink();

    void setDdeLink(CTDdeLink cTDdeLink);

    void setExtLst(CTExtensionList cTExtensionList);

    void setExternalBook(CTExternalBook cTExternalBook);

    void setOleLink(CTOleLink cTOleLink);

    void unsetDdeLink();

    void unsetExtLst();

    void unsetExternalBook();

    void unsetOleLink();

    static {
        DocumentFactory<CTExternalLink> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctexternallink966etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
