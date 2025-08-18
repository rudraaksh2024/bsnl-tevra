package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;

public interface CTExternalDefinedName extends XmlObject {
    public static final DocumentFactory<CTExternalDefinedName> Factory;
    public static final SchemaType type;

    String getName();

    String getRefersTo();

    long getSheetId();

    boolean isSetRefersTo();

    boolean isSetSheetId();

    void setName(String str);

    void setRefersTo(String str);

    void setSheetId(long j);

    void unsetRefersTo();

    void unsetSheetId();

    STXstring xgetName();

    STXstring xgetRefersTo();

    XmlUnsignedInt xgetSheetId();

    void xsetName(STXstring sTXstring);

    void xsetRefersTo(STXstring sTXstring);

    void xsetSheetId(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTExternalDefinedName> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctexternaldefinedname9408type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
