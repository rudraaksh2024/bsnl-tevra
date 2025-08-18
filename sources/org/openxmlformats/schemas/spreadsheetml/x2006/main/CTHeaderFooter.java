package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;

public interface CTHeaderFooter extends XmlObject {
    public static final DocumentFactory<CTHeaderFooter> Factory;
    public static final SchemaType type;

    boolean getAlignWithMargins();

    boolean getDifferentFirst();

    boolean getDifferentOddEven();

    String getEvenFooter();

    String getEvenHeader();

    String getFirstFooter();

    String getFirstHeader();

    String getOddFooter();

    String getOddHeader();

    boolean getScaleWithDoc();

    boolean isSetAlignWithMargins();

    boolean isSetDifferentFirst();

    boolean isSetDifferentOddEven();

    boolean isSetEvenFooter();

    boolean isSetEvenHeader();

    boolean isSetFirstFooter();

    boolean isSetFirstHeader();

    boolean isSetOddFooter();

    boolean isSetOddHeader();

    boolean isSetScaleWithDoc();

    void setAlignWithMargins(boolean z);

    void setDifferentFirst(boolean z);

    void setDifferentOddEven(boolean z);

    void setEvenFooter(String str);

    void setEvenHeader(String str);

    void setFirstFooter(String str);

    void setFirstHeader(String str);

    void setOddFooter(String str);

    void setOddHeader(String str);

    void setScaleWithDoc(boolean z);

    void unsetAlignWithMargins();

    void unsetDifferentFirst();

    void unsetDifferentOddEven();

    void unsetEvenFooter();

    void unsetEvenHeader();

    void unsetFirstFooter();

    void unsetFirstHeader();

    void unsetOddFooter();

    void unsetOddHeader();

    void unsetScaleWithDoc();

    XmlBoolean xgetAlignWithMargins();

    XmlBoolean xgetDifferentFirst();

    XmlBoolean xgetDifferentOddEven();

    STXstring xgetEvenFooter();

    STXstring xgetEvenHeader();

    STXstring xgetFirstFooter();

    STXstring xgetFirstHeader();

    STXstring xgetOddFooter();

    STXstring xgetOddHeader();

    XmlBoolean xgetScaleWithDoc();

    void xsetAlignWithMargins(XmlBoolean xmlBoolean);

    void xsetDifferentFirst(XmlBoolean xmlBoolean);

    void xsetDifferentOddEven(XmlBoolean xmlBoolean);

    void xsetEvenFooter(STXstring sTXstring);

    void xsetEvenHeader(STXstring sTXstring);

    void xsetFirstFooter(STXstring sTXstring);

    void xsetFirstHeader(STXstring sTXstring);

    void xsetOddFooter(STXstring sTXstring);

    void xsetOddHeader(STXstring sTXstring);

    void xsetScaleWithDoc(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTHeaderFooter> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctheaderfooter90d1type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
