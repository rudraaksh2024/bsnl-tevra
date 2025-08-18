package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;

public interface CTSlideMasterTextStyles extends XmlObject {
    public static final DocumentFactory<CTSlideMasterTextStyles> Factory;
    public static final SchemaType type;

    CTTextListStyle addNewBodyStyle();

    CTExtensionList addNewExtLst();

    CTTextListStyle addNewOtherStyle();

    CTTextListStyle addNewTitleStyle();

    CTTextListStyle getBodyStyle();

    CTExtensionList getExtLst();

    CTTextListStyle getOtherStyle();

    CTTextListStyle getTitleStyle();

    boolean isSetBodyStyle();

    boolean isSetExtLst();

    boolean isSetOtherStyle();

    boolean isSetTitleStyle();

    void setBodyStyle(CTTextListStyle cTTextListStyle);

    void setExtLst(CTExtensionList cTExtensionList);

    void setOtherStyle(CTTextListStyle cTTextListStyle);

    void setTitleStyle(CTTextListStyle cTTextListStyle);

    void unsetBodyStyle();

    void unsetExtLst();

    void unsetOtherStyle();

    void unsetTitleStyle();

    static {
        DocumentFactory<CTSlideMasterTextStyles> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctslidemastertextstylesb48dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
