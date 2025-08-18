package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTablePartStyle extends XmlObject {
    public static final DocumentFactory<CTTablePartStyle> Factory;
    public static final SchemaType type;

    CTTableStyleCellStyle addNewTcStyle();

    CTTableStyleTextStyle addNewTcTxStyle();

    CTTableStyleCellStyle getTcStyle();

    CTTableStyleTextStyle getTcTxStyle();

    boolean isSetTcStyle();

    boolean isSetTcTxStyle();

    void setTcStyle(CTTableStyleCellStyle cTTableStyleCellStyle);

    void setTcTxStyle(CTTableStyleTextStyle cTTableStyleTextStyle);

    void unsetTcStyle();

    void unsetTcTxStyle();

    static {
        DocumentFactory<CTTablePartStyle> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttablepartstylef22btype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
