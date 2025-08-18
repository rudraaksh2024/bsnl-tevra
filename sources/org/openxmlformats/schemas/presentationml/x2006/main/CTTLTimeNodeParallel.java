package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTLTimeNodeParallel extends XmlObject {
    public static final DocumentFactory<CTTLTimeNodeParallel> Factory;
    public static final SchemaType type;

    CTTLCommonTimeNodeData addNewCTn();

    CTTLCommonTimeNodeData getCTn();

    void setCTn(CTTLCommonTimeNodeData cTTLCommonTimeNodeData);

    static {
        DocumentFactory<CTTLTimeNodeParallel> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttltimenodeparallelf917type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
