package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;

public interface CTTblLook extends XmlObject {
    public static final DocumentFactory<CTTblLook> Factory;
    public static final SchemaType type;

    Object getFirstColumn();

    Object getFirstRow();

    Object getLastColumn();

    Object getLastRow();

    Object getNoHBand();

    Object getNoVBand();

    byte[] getVal();

    boolean isSetFirstColumn();

    boolean isSetFirstRow();

    boolean isSetLastColumn();

    boolean isSetLastRow();

    boolean isSetNoHBand();

    boolean isSetNoVBand();

    boolean isSetVal();

    void setFirstColumn(Object obj);

    void setFirstRow(Object obj);

    void setLastColumn(Object obj);

    void setLastRow(Object obj);

    void setNoHBand(Object obj);

    void setNoVBand(Object obj);

    void setVal(byte[] bArr);

    void unsetFirstColumn();

    void unsetFirstRow();

    void unsetLastColumn();

    void unsetLastRow();

    void unsetNoHBand();

    void unsetNoVBand();

    void unsetVal();

    STOnOff xgetFirstColumn();

    STOnOff xgetFirstRow();

    STOnOff xgetLastColumn();

    STOnOff xgetLastRow();

    STOnOff xgetNoHBand();

    STOnOff xgetNoVBand();

    STShortHexNumber xgetVal();

    void xsetFirstColumn(STOnOff sTOnOff);

    void xsetFirstRow(STOnOff sTOnOff);

    void xsetLastColumn(STOnOff sTOnOff);

    void xsetLastRow(STOnOff sTOnOff);

    void xsetNoHBand(STOnOff sTOnOff);

    void xsetNoVBand(STOnOff sTOnOff);

    void xsetVal(STShortHexNumber sTShortHexNumber);

    static {
        DocumentFactory<CTTblLook> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttbllooka235type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
