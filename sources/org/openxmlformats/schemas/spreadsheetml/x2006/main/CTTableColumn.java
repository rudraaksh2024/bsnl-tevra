package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;

public interface CTTableColumn extends XmlObject {
    public static final DocumentFactory<CTTableColumn> Factory;
    public static final SchemaType type;

    CTTableFormula addNewCalculatedColumnFormula();

    CTExtensionList addNewExtLst();

    CTTableFormula addNewTotalsRowFormula();

    CTXmlColumnPr addNewXmlColumnPr();

    CTTableFormula getCalculatedColumnFormula();

    String getDataCellStyle();

    long getDataDxfId();

    CTExtensionList getExtLst();

    String getHeaderRowCellStyle();

    long getHeaderRowDxfId();

    long getId();

    String getName();

    long getQueryTableFieldId();

    String getTotalsRowCellStyle();

    long getTotalsRowDxfId();

    CTTableFormula getTotalsRowFormula();

    STTotalsRowFunction$Enum getTotalsRowFunction();

    String getTotalsRowLabel();

    String getUniqueName();

    CTXmlColumnPr getXmlColumnPr();

    boolean isSetCalculatedColumnFormula();

    boolean isSetDataCellStyle();

    boolean isSetDataDxfId();

    boolean isSetExtLst();

    boolean isSetHeaderRowCellStyle();

    boolean isSetHeaderRowDxfId();

    boolean isSetQueryTableFieldId();

    boolean isSetTotalsRowCellStyle();

    boolean isSetTotalsRowDxfId();

    boolean isSetTotalsRowFormula();

    boolean isSetTotalsRowFunction();

    boolean isSetTotalsRowLabel();

    boolean isSetUniqueName();

    boolean isSetXmlColumnPr();

    void setCalculatedColumnFormula(CTTableFormula cTTableFormula);

    void setDataCellStyle(String str);

    void setDataDxfId(long j);

    void setExtLst(CTExtensionList cTExtensionList);

    void setHeaderRowCellStyle(String str);

    void setHeaderRowDxfId(long j);

    void setId(long j);

    void setName(String str);

    void setQueryTableFieldId(long j);

    void setTotalsRowCellStyle(String str);

    void setTotalsRowDxfId(long j);

    void setTotalsRowFormula(CTTableFormula cTTableFormula);

    void setTotalsRowFunction(STTotalsRowFunction$Enum sTTotalsRowFunction$Enum);

    void setTotalsRowLabel(String str);

    void setUniqueName(String str);

    void setXmlColumnPr(CTXmlColumnPr cTXmlColumnPr);

    void unsetCalculatedColumnFormula();

    void unsetDataCellStyle();

    void unsetDataDxfId();

    void unsetExtLst();

    void unsetHeaderRowCellStyle();

    void unsetHeaderRowDxfId();

    void unsetQueryTableFieldId();

    void unsetTotalsRowCellStyle();

    void unsetTotalsRowDxfId();

    void unsetTotalsRowFormula();

    void unsetTotalsRowFunction();

    void unsetTotalsRowLabel();

    void unsetUniqueName();

    void unsetXmlColumnPr();

    STXstring xgetDataCellStyle();

    STDxfId xgetDataDxfId();

    STXstring xgetHeaderRowCellStyle();

    STDxfId xgetHeaderRowDxfId();

    XmlUnsignedInt xgetId();

    STXstring xgetName();

    XmlUnsignedInt xgetQueryTableFieldId();

    STXstring xgetTotalsRowCellStyle();

    STDxfId xgetTotalsRowDxfId();

    STTotalsRowFunction xgetTotalsRowFunction();

    STXstring xgetTotalsRowLabel();

    STXstring xgetUniqueName();

    void xsetDataCellStyle(STXstring sTXstring);

    void xsetDataDxfId(STDxfId sTDxfId);

    void xsetHeaderRowCellStyle(STXstring sTXstring);

    void xsetHeaderRowDxfId(STDxfId sTDxfId);

    void xsetId(XmlUnsignedInt xmlUnsignedInt);

    void xsetName(STXstring sTXstring);

    void xsetQueryTableFieldId(XmlUnsignedInt xmlUnsignedInt);

    void xsetTotalsRowCellStyle(STXstring sTXstring);

    void xsetTotalsRowDxfId(STDxfId sTDxfId);

    void xsetTotalsRowFunction(STTotalsRowFunction sTTotalsRowFunction);

    void xsetTotalsRowLabel(STXstring sTXstring);

    void xsetUniqueName(STXstring sTXstring);

    static {
        DocumentFactory<CTTableColumn> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttablecolumn08a3type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
