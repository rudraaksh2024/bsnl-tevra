package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTCommentAuthor extends XmlObject {
    public static final DocumentFactory<CTCommentAuthor> Factory;
    public static final SchemaType type;

    CTExtensionList addNewExtLst();

    long getClrIdx();

    CTExtensionList getExtLst();

    long getId();

    String getInitials();

    long getLastIdx();

    String getName();

    boolean isSetExtLst();

    void setClrIdx(long j);

    void setExtLst(CTExtensionList cTExtensionList);

    void setId(long j);

    void setInitials(String str);

    void setLastIdx(long j);

    void setName(String str);

    void unsetExtLst();

    XmlUnsignedInt xgetClrIdx();

    XmlUnsignedInt xgetId();

    STName xgetInitials();

    XmlUnsignedInt xgetLastIdx();

    STName xgetName();

    void xsetClrIdx(XmlUnsignedInt xmlUnsignedInt);

    void xsetId(XmlUnsignedInt xmlUnsignedInt);

    void xsetInitials(STName sTName);

    void xsetLastIdx(XmlUnsignedInt xmlUnsignedInt);

    void xsetName(STName sTName);

    static {
        DocumentFactory<CTCommentAuthor> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcommentauthora405type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
