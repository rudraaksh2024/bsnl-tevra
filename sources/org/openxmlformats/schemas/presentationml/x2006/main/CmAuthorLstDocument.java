package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CmAuthorLstDocument extends XmlObject {
    public static final DocumentFactory<CmAuthorLstDocument> Factory;
    public static final SchemaType type;

    CTCommentAuthorList addNewCmAuthorLst();

    CTCommentAuthorList getCmAuthorLst();

    void setCmAuthorLst(CTCommentAuthorList cTCommentAuthorList);

    static {
        DocumentFactory<CmAuthorLstDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cmauthorlst86abdoctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
