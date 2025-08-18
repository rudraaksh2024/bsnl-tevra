package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTEdnProps extends XmlObject {
    public static final DocumentFactory<CTEdnProps> Factory;
    public static final SchemaType type;

    CTNumFmt addNewNumFmt();

    CTNumRestart addNewNumRestart();

    CTDecimalNumber addNewNumStart();

    CTEdnPos addNewPos();

    CTNumFmt getNumFmt();

    CTNumRestart getNumRestart();

    CTDecimalNumber getNumStart();

    CTEdnPos getPos();

    boolean isSetNumFmt();

    boolean isSetNumRestart();

    boolean isSetNumStart();

    boolean isSetPos();

    void setNumFmt(CTNumFmt cTNumFmt);

    void setNumRestart(CTNumRestart cTNumRestart);

    void setNumStart(CTDecimalNumber cTDecimalNumber);

    void setPos(CTEdnPos cTEdnPos);

    void unsetNumFmt();

    void unsetNumRestart();

    void unsetNumStart();

    void unsetPos();

    static {
        DocumentFactory<CTEdnProps> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctednpropsd887type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
