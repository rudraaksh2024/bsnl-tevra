package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTblPrBase extends XmlObject {
    public static final DocumentFactory<CTTblPrBase> Factory;
    public static final SchemaType type;

    CTOnOff addNewBidiVisual();

    CTJcTable addNewJc();

    CTShd addNewShd();

    CTTblBorders addNewTblBorders();

    CTString addNewTblCaption();

    CTTblCellMar addNewTblCellMar();

    CTTblWidth addNewTblCellSpacing();

    CTString addNewTblDescription();

    CTTblWidth addNewTblInd();

    CTTblLayoutType addNewTblLayout();

    CTTblLook addNewTblLook();

    CTTblOverlap addNewTblOverlap();

    CTString addNewTblStyle();

    CTDecimalNumber addNewTblStyleColBandSize();

    CTDecimalNumber addNewTblStyleRowBandSize();

    CTTblWidth addNewTblW();

    CTTblPPr addNewTblpPr();

    CTOnOff getBidiVisual();

    CTJcTable getJc();

    CTShd getShd();

    CTTblBorders getTblBorders();

    CTString getTblCaption();

    CTTblCellMar getTblCellMar();

    CTTblWidth getTblCellSpacing();

    CTString getTblDescription();

    CTTblWidth getTblInd();

    CTTblLayoutType getTblLayout();

    CTTblLook getTblLook();

    CTTblOverlap getTblOverlap();

    CTString getTblStyle();

    CTDecimalNumber getTblStyleColBandSize();

    CTDecimalNumber getTblStyleRowBandSize();

    CTTblWidth getTblW();

    CTTblPPr getTblpPr();

    boolean isSetBidiVisual();

    boolean isSetJc();

    boolean isSetShd();

    boolean isSetTblBorders();

    boolean isSetTblCaption();

    boolean isSetTblCellMar();

    boolean isSetTblCellSpacing();

    boolean isSetTblDescription();

    boolean isSetTblInd();

    boolean isSetTblLayout();

    boolean isSetTblLook();

    boolean isSetTblOverlap();

    boolean isSetTblStyle();

    boolean isSetTblStyleColBandSize();

    boolean isSetTblStyleRowBandSize();

    boolean isSetTblW();

    boolean isSetTblpPr();

    void setBidiVisual(CTOnOff cTOnOff);

    void setJc(CTJcTable cTJcTable);

    void setShd(CTShd cTShd);

    void setTblBorders(CTTblBorders cTTblBorders);

    void setTblCaption(CTString cTString);

    void setTblCellMar(CTTblCellMar cTTblCellMar);

    void setTblCellSpacing(CTTblWidth cTTblWidth);

    void setTblDescription(CTString cTString);

    void setTblInd(CTTblWidth cTTblWidth);

    void setTblLayout(CTTblLayoutType cTTblLayoutType);

    void setTblLook(CTTblLook cTTblLook);

    void setTblOverlap(CTTblOverlap cTTblOverlap);

    void setTblStyle(CTString cTString);

    void setTblStyleColBandSize(CTDecimalNumber cTDecimalNumber);

    void setTblStyleRowBandSize(CTDecimalNumber cTDecimalNumber);

    void setTblW(CTTblWidth cTTblWidth);

    void setTblpPr(CTTblPPr cTTblPPr);

    void unsetBidiVisual();

    void unsetJc();

    void unsetShd();

    void unsetTblBorders();

    void unsetTblCaption();

    void unsetTblCellMar();

    void unsetTblCellSpacing();

    void unsetTblDescription();

    void unsetTblInd();

    void unsetTblLayout();

    void unsetTblLook();

    void unsetTblOverlap();

    void unsetTblStyle();

    void unsetTblStyleColBandSize();

    void unsetTblStyleRowBandSize();

    void unsetTblW();

    void unsetTblpPr();

    static {
        DocumentFactory<CTTblPrBase> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttblprbaseeba1type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
