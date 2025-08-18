package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;

public interface CTRElt extends XmlObject {
    public static final DocumentFactory<CTRElt> Factory;
    public static final SchemaType type;

    CTRPrElt addNewRPr();

    CTRPrElt getRPr();

    String getT();

    boolean isSetRPr();

    void setRPr(CTRPrElt cTRPrElt);

    void setT(String str);

    void unsetRPr();

    STXstring xgetT();

    void xsetT(STXstring sTXstring);

    static {
        DocumentFactory<CTRElt> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctrelt6464type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
