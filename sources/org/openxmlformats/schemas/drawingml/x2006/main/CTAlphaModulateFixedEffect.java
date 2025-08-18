package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTAlphaModulateFixedEffect extends XmlObject {
    public static final DocumentFactory<CTAlphaModulateFixedEffect> Factory;
    public static final SchemaType type;

    Object getAmt();

    boolean isSetAmt();

    void setAmt(Object obj);

    void unsetAmt();

    STPositivePercentage xgetAmt();

    void xsetAmt(STPositivePercentage sTPositivePercentage);

    static {
        DocumentFactory<CTAlphaModulateFixedEffect> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctalphamodulatefixedeffect9769type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
