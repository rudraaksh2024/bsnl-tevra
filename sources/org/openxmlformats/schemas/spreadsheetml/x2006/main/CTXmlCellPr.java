package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;

public interface CTXmlCellPr extends XmlObject {
    public static final DocumentFactory<CTXmlCellPr> Factory;
    public static final SchemaType type;

    CTExtensionList addNewExtLst();

    CTXmlPr addNewXmlPr();

    CTExtensionList getExtLst();

    long getId();

    String getUniqueName();

    CTXmlPr getXmlPr();

    boolean isSetExtLst();

    boolean isSetUniqueName();

    void setExtLst(CTExtensionList cTExtensionList);

    void setId(long j);

    void setUniqueName(String str);

    void setXmlPr(CTXmlPr cTXmlPr);

    void unsetExtLst();

    void unsetUniqueName();

    XmlUnsignedInt xgetId();

    STXstring xgetUniqueName();

    void xsetId(XmlUnsignedInt xmlUnsignedInt);

    void xsetUniqueName(STXstring sTXstring);

    static {
        DocumentFactory<CTXmlCellPr> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctxmlcellprf1datype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
