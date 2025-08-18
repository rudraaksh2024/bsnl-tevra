package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTCommentList extends XmlObject {
    public static final DocumentFactory<CTCommentList> Factory;
    public static final SchemaType type;

    CTComment addNewComment();

    CTComment getCommentArray(int i);

    CTComment[] getCommentArray();

    List<CTComment> getCommentList();

    CTComment insertNewComment(int i);

    void removeComment(int i);

    void setCommentArray(int i, CTComment cTComment);

    void setCommentArray(CTComment[] cTCommentArr);

    int sizeOfCommentArray();

    static {
        DocumentFactory<CTCommentList> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcommentlist7a3ctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
