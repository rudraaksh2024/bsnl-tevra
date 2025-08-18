package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTComments extends XmlObject {
    public static final DocumentFactory<CTComments> Factory;
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
        DocumentFactory<CTComments> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcomments7674type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
