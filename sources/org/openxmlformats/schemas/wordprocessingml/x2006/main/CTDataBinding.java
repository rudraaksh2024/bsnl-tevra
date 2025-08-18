package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;

public interface CTDataBinding extends XmlObject {
    public static final DocumentFactory<CTDataBinding> Factory;
    public static final SchemaType type;

    String getPrefixMappings();

    String getStoreItemID();

    String getXpath();

    boolean isSetPrefixMappings();

    void setPrefixMappings(String str);

    void setStoreItemID(String str);

    void setXpath(String str);

    void unsetPrefixMappings();

    STString xgetPrefixMappings();

    STString xgetStoreItemID();

    STString xgetXpath();

    void xsetPrefixMappings(STString sTString);

    void xsetStoreItemID(STString sTString);

    void xsetXpath(STString sTString);

    static {
        DocumentFactory<CTDataBinding> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdatabinding9077type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
