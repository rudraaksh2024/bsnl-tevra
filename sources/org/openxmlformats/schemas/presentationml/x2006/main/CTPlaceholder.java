package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.presentationml.x2006.main.STPlaceholderSize;
import org.openxmlformats.schemas.presentationml.x2006.main.STPlaceholderType;

public interface CTPlaceholder extends XmlObject {
    public static final DocumentFactory<CTPlaceholder> Factory;
    public static final SchemaType type;

    CTExtensionListModify addNewExtLst();

    CTExtensionListModify getExtLst();

    boolean getHasCustomPrompt();

    long getIdx();

    STDirection$Enum getOrient();

    STPlaceholderSize.Enum getSz();

    STPlaceholderType.Enum getType();

    boolean isSetExtLst();

    boolean isSetHasCustomPrompt();

    boolean isSetIdx();

    boolean isSetOrient();

    boolean isSetSz();

    boolean isSetType();

    void setExtLst(CTExtensionListModify cTExtensionListModify);

    void setHasCustomPrompt(boolean z);

    void setIdx(long j);

    void setOrient(STDirection$Enum sTDirection$Enum);

    void setSz(STPlaceholderSize.Enum enumR);

    void setType(STPlaceholderType.Enum enumR);

    void unsetExtLst();

    void unsetHasCustomPrompt();

    void unsetIdx();

    void unsetOrient();

    void unsetSz();

    void unsetType();

    XmlBoolean xgetHasCustomPrompt();

    XmlUnsignedInt xgetIdx();

    STDirection xgetOrient();

    STPlaceholderSize xgetSz();

    STPlaceholderType xgetType();

    void xsetHasCustomPrompt(XmlBoolean xmlBoolean);

    void xsetIdx(XmlUnsignedInt xmlUnsignedInt);

    void xsetOrient(STDirection sTDirection);

    void xsetSz(STPlaceholderSize sTPlaceholderSize);

    void xsetType(STPlaceholderType sTPlaceholderType);

    static {
        DocumentFactory<CTPlaceholder> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctplaceholder9efctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
