package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTableCol extends XmlObject {
    public static final DocumentFactory<CTTableCol> Factory;
    public static final SchemaType type;

    CTOfficeArtExtensionList addNewExtLst();

    CTOfficeArtExtensionList getExtLst();

    Object getW();

    boolean isSetExtLst();

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setW(Object obj);

    void unsetExtLst();

    STCoordinate xgetW();

    void xsetW(STCoordinate sTCoordinate);

    static {
        DocumentFactory<CTTableCol> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttablecol19edtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
