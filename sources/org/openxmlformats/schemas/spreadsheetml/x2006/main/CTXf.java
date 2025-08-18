package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTXf extends XmlObject {
    public static final DocumentFactory<CTXf> Factory;
    public static final SchemaType type;

    CTCellAlignment addNewAlignment();

    CTExtensionList addNewExtLst();

    CTCellProtection addNewProtection();

    CTCellAlignment getAlignment();

    boolean getApplyAlignment();

    boolean getApplyBorder();

    boolean getApplyFill();

    boolean getApplyFont();

    boolean getApplyNumberFormat();

    boolean getApplyProtection();

    long getBorderId();

    CTExtensionList getExtLst();

    long getFillId();

    long getFontId();

    long getNumFmtId();

    boolean getPivotButton();

    CTCellProtection getProtection();

    boolean getQuotePrefix();

    long getXfId();

    boolean isSetAlignment();

    boolean isSetApplyAlignment();

    boolean isSetApplyBorder();

    boolean isSetApplyFill();

    boolean isSetApplyFont();

    boolean isSetApplyNumberFormat();

    boolean isSetApplyProtection();

    boolean isSetBorderId();

    boolean isSetExtLst();

    boolean isSetFillId();

    boolean isSetFontId();

    boolean isSetNumFmtId();

    boolean isSetPivotButton();

    boolean isSetProtection();

    boolean isSetQuotePrefix();

    boolean isSetXfId();

    void setAlignment(CTCellAlignment cTCellAlignment);

    void setApplyAlignment(boolean z);

    void setApplyBorder(boolean z);

    void setApplyFill(boolean z);

    void setApplyFont(boolean z);

    void setApplyNumberFormat(boolean z);

    void setApplyProtection(boolean z);

    void setBorderId(long j);

    void setExtLst(CTExtensionList cTExtensionList);

    void setFillId(long j);

    void setFontId(long j);

    void setNumFmtId(long j);

    void setPivotButton(boolean z);

    void setProtection(CTCellProtection cTCellProtection);

    void setQuotePrefix(boolean z);

    void setXfId(long j);

    void unsetAlignment();

    void unsetApplyAlignment();

    void unsetApplyBorder();

    void unsetApplyFill();

    void unsetApplyFont();

    void unsetApplyNumberFormat();

    void unsetApplyProtection();

    void unsetBorderId();

    void unsetExtLst();

    void unsetFillId();

    void unsetFontId();

    void unsetNumFmtId();

    void unsetPivotButton();

    void unsetProtection();

    void unsetQuotePrefix();

    void unsetXfId();

    XmlBoolean xgetApplyAlignment();

    XmlBoolean xgetApplyBorder();

    XmlBoolean xgetApplyFill();

    XmlBoolean xgetApplyFont();

    XmlBoolean xgetApplyNumberFormat();

    XmlBoolean xgetApplyProtection();

    STBorderId xgetBorderId();

    STFillId xgetFillId();

    STFontId xgetFontId();

    STNumFmtId xgetNumFmtId();

    XmlBoolean xgetPivotButton();

    XmlBoolean xgetQuotePrefix();

    STCellStyleXfId xgetXfId();

    void xsetApplyAlignment(XmlBoolean xmlBoolean);

    void xsetApplyBorder(XmlBoolean xmlBoolean);

    void xsetApplyFill(XmlBoolean xmlBoolean);

    void xsetApplyFont(XmlBoolean xmlBoolean);

    void xsetApplyNumberFormat(XmlBoolean xmlBoolean);

    void xsetApplyProtection(XmlBoolean xmlBoolean);

    void xsetBorderId(STBorderId sTBorderId);

    void xsetFillId(STFillId sTFillId);

    void xsetFontId(STFontId sTFontId);

    void xsetNumFmtId(STNumFmtId sTNumFmtId);

    void xsetPivotButton(XmlBoolean xmlBoolean);

    void xsetQuotePrefix(XmlBoolean xmlBoolean);

    void xsetXfId(STCellStyleXfId sTCellStyleXfId);

    static {
        DocumentFactory<CTXf> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctxf97f7type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
