package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CmLstDocument extends XmlObject {
    public static final DocumentFactory<CmLstDocument> Factory;
    public static final SchemaType type;

    CTCommentList addNewCmLst();

    CTCommentList getCmLst();

    void setCmLst(CTCommentList cTCommentList);

    static {
        DocumentFactory<CmLstDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cmlst3880doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
