package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTDocDefaults extends XmlObject {
    public static final DocumentFactory<CTDocDefaults> Factory;
    public static final SchemaType type;

    CTPPrDefault addNewPPrDefault();

    CTRPrDefault addNewRPrDefault();

    CTPPrDefault getPPrDefault();

    CTRPrDefault getRPrDefault();

    boolean isSetPPrDefault();

    boolean isSetRPrDefault();

    void setPPrDefault(CTPPrDefault cTPPrDefault);

    void setRPrDefault(CTRPrDefault cTRPrDefault);

    void unsetPPrDefault();

    void unsetRPrDefault();

    static {
        DocumentFactory<CTDocDefaults> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdocdefaults2ea8type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
