package org.openxmlformats.schemas.presentationml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTCommentAuthorList extends XmlObject {
    public static final DocumentFactory<CTCommentAuthorList> Factory;
    public static final SchemaType type;

    CTCommentAuthor addNewCmAuthor();

    CTCommentAuthor getCmAuthorArray(int i);

    CTCommentAuthor[] getCmAuthorArray();

    List<CTCommentAuthor> getCmAuthorList();

    CTCommentAuthor insertNewCmAuthor(int i);

    void removeCmAuthor(int i);

    void setCmAuthorArray(int i, CTCommentAuthor cTCommentAuthor);

    void setCmAuthorArray(CTCommentAuthor[] cTCommentAuthorArr);

    int sizeOfCmAuthorArray();

    static {
        DocumentFactory<CTCommentAuthorList> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcommentauthorlisteb07type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
