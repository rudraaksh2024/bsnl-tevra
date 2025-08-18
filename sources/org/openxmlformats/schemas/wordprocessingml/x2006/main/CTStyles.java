package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTStyles extends XmlObject {
    public static final DocumentFactory<CTStyles> Factory;
    public static final SchemaType type;

    CTDocDefaults addNewDocDefaults();

    CTLatentStyles addNewLatentStyles();

    CTStyle addNewStyle();

    CTDocDefaults getDocDefaults();

    CTLatentStyles getLatentStyles();

    CTStyle getStyleArray(int i);

    CTStyle[] getStyleArray();

    List<CTStyle> getStyleList();

    CTStyle insertNewStyle(int i);

    boolean isSetDocDefaults();

    boolean isSetLatentStyles();

    void removeStyle(int i);

    void setDocDefaults(CTDocDefaults cTDocDefaults);

    void setLatentStyles(CTLatentStyles cTLatentStyles);

    void setStyleArray(int i, CTStyle cTStyle);

    void setStyleArray(CTStyle[] cTStyleArr);

    int sizeOfStyleArray();

    void unsetDocDefaults();

    void unsetLatentStyles();

    static {
        DocumentFactory<CTStyles> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctstyles8506type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
