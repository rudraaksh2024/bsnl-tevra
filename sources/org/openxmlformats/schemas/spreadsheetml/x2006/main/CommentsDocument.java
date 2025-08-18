package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CommentsDocument extends XmlObject {
    public static final DocumentFactory<CommentsDocument> Factory;
    public static final SchemaType type;

    CTComments addNewComments();

    CTComments getComments();

    void setComments(CTComments cTComments);

    static {
        DocumentFactory<CommentsDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "comments4c11doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
