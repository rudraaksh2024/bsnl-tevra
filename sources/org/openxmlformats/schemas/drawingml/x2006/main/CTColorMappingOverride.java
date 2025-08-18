package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTColorMappingOverride extends XmlObject {
    public static final DocumentFactory<CTColorMappingOverride> Factory;
    public static final SchemaType type;

    CTEmptyElement addNewMasterClrMapping();

    CTColorMapping addNewOverrideClrMapping();

    CTEmptyElement getMasterClrMapping();

    CTColorMapping getOverrideClrMapping();

    boolean isSetMasterClrMapping();

    boolean isSetOverrideClrMapping();

    void setMasterClrMapping(CTEmptyElement cTEmptyElement);

    void setOverrideClrMapping(CTColorMapping cTColorMapping);

    void unsetMasterClrMapping();

    void unsetOverrideClrMapping();

    static {
        DocumentFactory<CTColorMappingOverride> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcolormappingoverridea2b2type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
