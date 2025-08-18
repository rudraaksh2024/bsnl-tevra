package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTSectPr extends XmlObject {
    public static final DocumentFactory<CTSectPr> Factory;
    public static final SchemaType type;

    CTOnOff addNewBidi();

    CTColumns addNewCols();

    CTDocGrid addNewDocGrid();

    CTEdnProps addNewEndnotePr();

    CTHdrFtrRef addNewFooterReference();

    CTFtnProps addNewFootnotePr();

    CTOnOff addNewFormProt();

    CTHdrFtrRef addNewHeaderReference();

    CTLineNumber addNewLnNumType();

    CTOnOff addNewNoEndnote();

    CTPaperSource addNewPaperSrc();

    CTPageBorders addNewPgBorders();

    CTPageMar addNewPgMar();

    CTPageNumber addNewPgNumType();

    CTPageSz addNewPgSz();

    CTRel addNewPrinterSettings();

    CTOnOff addNewRtlGutter();

    CTSectPrChange addNewSectPrChange();

    CTTextDirection addNewTextDirection();

    CTOnOff addNewTitlePg();

    CTSectType addNewType();

    CTVerticalJc addNewVAlign();

    CTOnOff getBidi();

    CTColumns getCols();

    CTDocGrid getDocGrid();

    CTEdnProps getEndnotePr();

    CTHdrFtrRef getFooterReferenceArray(int i);

    CTHdrFtrRef[] getFooterReferenceArray();

    List<CTHdrFtrRef> getFooterReferenceList();

    CTFtnProps getFootnotePr();

    CTOnOff getFormProt();

    CTHdrFtrRef getHeaderReferenceArray(int i);

    CTHdrFtrRef[] getHeaderReferenceArray();

    List<CTHdrFtrRef> getHeaderReferenceList();

    CTLineNumber getLnNumType();

    CTOnOff getNoEndnote();

    CTPaperSource getPaperSrc();

    CTPageBorders getPgBorders();

    CTPageMar getPgMar();

    CTPageNumber getPgNumType();

    CTPageSz getPgSz();

    CTRel getPrinterSettings();

    byte[] getRsidDel();

    byte[] getRsidR();

    byte[] getRsidRPr();

    byte[] getRsidSect();

    CTOnOff getRtlGutter();

    CTSectPrChange getSectPrChange();

    CTTextDirection getTextDirection();

    CTOnOff getTitlePg();

    CTSectType getType();

    CTVerticalJc getVAlign();

    CTHdrFtrRef insertNewFooterReference(int i);

    CTHdrFtrRef insertNewHeaderReference(int i);

    boolean isSetBidi();

    boolean isSetCols();

    boolean isSetDocGrid();

    boolean isSetEndnotePr();

    boolean isSetFootnotePr();

    boolean isSetFormProt();

    boolean isSetLnNumType();

    boolean isSetNoEndnote();

    boolean isSetPaperSrc();

    boolean isSetPgBorders();

    boolean isSetPgMar();

    boolean isSetPgNumType();

    boolean isSetPgSz();

    boolean isSetPrinterSettings();

    boolean isSetRsidDel();

    boolean isSetRsidR();

    boolean isSetRsidRPr();

    boolean isSetRsidSect();

    boolean isSetRtlGutter();

    boolean isSetSectPrChange();

    boolean isSetTextDirection();

    boolean isSetTitlePg();

    boolean isSetType();

    boolean isSetVAlign();

    void removeFooterReference(int i);

    void removeHeaderReference(int i);

    void setBidi(CTOnOff cTOnOff);

    void setCols(CTColumns cTColumns);

    void setDocGrid(CTDocGrid cTDocGrid);

    void setEndnotePr(CTEdnProps cTEdnProps);

    void setFooterReferenceArray(int i, CTHdrFtrRef cTHdrFtrRef);

    void setFooterReferenceArray(CTHdrFtrRef[] cTHdrFtrRefArr);

    void setFootnotePr(CTFtnProps cTFtnProps);

    void setFormProt(CTOnOff cTOnOff);

    void setHeaderReferenceArray(int i, CTHdrFtrRef cTHdrFtrRef);

    void setHeaderReferenceArray(CTHdrFtrRef[] cTHdrFtrRefArr);

    void setLnNumType(CTLineNumber cTLineNumber);

    void setNoEndnote(CTOnOff cTOnOff);

    void setPaperSrc(CTPaperSource cTPaperSource);

    void setPgBorders(CTPageBorders cTPageBorders);

    void setPgMar(CTPageMar cTPageMar);

    void setPgNumType(CTPageNumber cTPageNumber);

    void setPgSz(CTPageSz cTPageSz);

    void setPrinterSettings(CTRel cTRel);

    void setRsidDel(byte[] bArr);

    void setRsidR(byte[] bArr);

    void setRsidRPr(byte[] bArr);

    void setRsidSect(byte[] bArr);

    void setRtlGutter(CTOnOff cTOnOff);

    void setSectPrChange(CTSectPrChange cTSectPrChange);

    void setTextDirection(CTTextDirection cTTextDirection);

    void setTitlePg(CTOnOff cTOnOff);

    void setType(CTSectType cTSectType);

    void setVAlign(CTVerticalJc cTVerticalJc);

    int sizeOfFooterReferenceArray();

    int sizeOfHeaderReferenceArray();

    void unsetBidi();

    void unsetCols();

    void unsetDocGrid();

    void unsetEndnotePr();

    void unsetFootnotePr();

    void unsetFormProt();

    void unsetLnNumType();

    void unsetNoEndnote();

    void unsetPaperSrc();

    void unsetPgBorders();

    void unsetPgMar();

    void unsetPgNumType();

    void unsetPgSz();

    void unsetPrinterSettings();

    void unsetRsidDel();

    void unsetRsidR();

    void unsetRsidRPr();

    void unsetRsidSect();

    void unsetRtlGutter();

    void unsetSectPrChange();

    void unsetTextDirection();

    void unsetTitlePg();

    void unsetType();

    void unsetVAlign();

    STLongHexNumber xgetRsidDel();

    STLongHexNumber xgetRsidR();

    STLongHexNumber xgetRsidRPr();

    STLongHexNumber xgetRsidSect();

    void xsetRsidDel(STLongHexNumber sTLongHexNumber);

    void xsetRsidR(STLongHexNumber sTLongHexNumber);

    void xsetRsidRPr(STLongHexNumber sTLongHexNumber);

    void xsetRsidSect(STLongHexNumber sTLongHexNumber);

    static {
        DocumentFactory<CTSectPr> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsectpr1123type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
