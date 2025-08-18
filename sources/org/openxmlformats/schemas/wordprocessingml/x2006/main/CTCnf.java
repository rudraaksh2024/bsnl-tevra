package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;

public interface CTCnf extends XmlObject {
    public static final DocumentFactory<CTCnf> Factory;
    public static final SchemaType type;

    Object getEvenHBand();

    Object getEvenVBand();

    Object getFirstColumn();

    Object getFirstRow();

    Object getFirstRowFirstColumn();

    Object getFirstRowLastColumn();

    Object getLastColumn();

    Object getLastRow();

    Object getLastRowFirstColumn();

    Object getLastRowLastColumn();

    Object getOddHBand();

    Object getOddVBand();

    String getVal();

    boolean isSetEvenHBand();

    boolean isSetEvenVBand();

    boolean isSetFirstColumn();

    boolean isSetFirstRow();

    boolean isSetFirstRowFirstColumn();

    boolean isSetFirstRowLastColumn();

    boolean isSetLastColumn();

    boolean isSetLastRow();

    boolean isSetLastRowFirstColumn();

    boolean isSetLastRowLastColumn();

    boolean isSetOddHBand();

    boolean isSetOddVBand();

    boolean isSetVal();

    void setEvenHBand(Object obj);

    void setEvenVBand(Object obj);

    void setFirstColumn(Object obj);

    void setFirstRow(Object obj);

    void setFirstRowFirstColumn(Object obj);

    void setFirstRowLastColumn(Object obj);

    void setLastColumn(Object obj);

    void setLastRow(Object obj);

    void setLastRowFirstColumn(Object obj);

    void setLastRowLastColumn(Object obj);

    void setOddHBand(Object obj);

    void setOddVBand(Object obj);

    void setVal(String str);

    void unsetEvenHBand();

    void unsetEvenVBand();

    void unsetFirstColumn();

    void unsetFirstRow();

    void unsetFirstRowFirstColumn();

    void unsetFirstRowLastColumn();

    void unsetLastColumn();

    void unsetLastRow();

    void unsetLastRowFirstColumn();

    void unsetLastRowLastColumn();

    void unsetOddHBand();

    void unsetOddVBand();

    void unsetVal();

    STOnOff xgetEvenHBand();

    STOnOff xgetEvenVBand();

    STOnOff xgetFirstColumn();

    STOnOff xgetFirstRow();

    STOnOff xgetFirstRowFirstColumn();

    STOnOff xgetFirstRowLastColumn();

    STOnOff xgetLastColumn();

    STOnOff xgetLastRow();

    STOnOff xgetLastRowFirstColumn();

    STOnOff xgetLastRowLastColumn();

    STOnOff xgetOddHBand();

    STOnOff xgetOddVBand();

    STCnf xgetVal();

    void xsetEvenHBand(STOnOff sTOnOff);

    void xsetEvenVBand(STOnOff sTOnOff);

    void xsetFirstColumn(STOnOff sTOnOff);

    void xsetFirstRow(STOnOff sTOnOff);

    void xsetFirstRowFirstColumn(STOnOff sTOnOff);

    void xsetFirstRowLastColumn(STOnOff sTOnOff);

    void xsetLastColumn(STOnOff sTOnOff);

    void xsetLastRow(STOnOff sTOnOff);

    void xsetLastRowFirstColumn(STOnOff sTOnOff);

    void xsetLastRowLastColumn(STOnOff sTOnOff);

    void xsetOddHBand(STOnOff sTOnOff);

    void xsetOddVBand(STOnOff sTOnOff);

    void xsetVal(STCnf sTCnf);

    static {
        DocumentFactory<CTCnf> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcnf1397type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
