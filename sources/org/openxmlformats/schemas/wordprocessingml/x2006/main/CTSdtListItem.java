package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;

public interface CTSdtListItem extends XmlObject {
    public static final DocumentFactory<CTSdtListItem> Factory;
    public static final SchemaType type;

    String getDisplayText();

    String getValue();

    boolean isSetDisplayText();

    boolean isSetValue();

    void setDisplayText(String str);

    void setValue(String str);

    void unsetDisplayText();

    void unsetValue();

    STString xgetDisplayText();

    STString xgetValue();

    void xsetDisplayText(STString sTString);

    void xsetValue(STString sTString);

    static {
        DocumentFactory<CTSdtListItem> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsdtlistitem705etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
