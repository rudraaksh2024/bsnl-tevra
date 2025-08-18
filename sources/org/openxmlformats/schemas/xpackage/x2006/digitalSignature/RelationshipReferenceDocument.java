package org.openxmlformats.schemas.xpackage.x2006.digitalSignature;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface RelationshipReferenceDocument extends XmlObject {
    public static final DocumentFactory<RelationshipReferenceDocument> Factory;
    public static final SchemaType type;

    CTRelationshipReference addNewRelationshipReference();

    CTRelationshipReference getRelationshipReference();

    void setRelationshipReference(CTRelationshipReference cTRelationshipReference);

    static {
        DocumentFactory<RelationshipReferenceDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "relationshipreference8903doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
