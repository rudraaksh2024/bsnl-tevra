package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;

public interface CTTable extends XmlObject {
    public static final DocumentFactory<CTTable> Factory;
    public static final SchemaType type;

    CTAutoFilter addNewAutoFilter();

    CTExtensionList addNewExtLst();

    CTSortState addNewSortState();

    CTTableColumns addNewTableColumns();

    CTTableStyleInfo addNewTableStyleInfo();

    CTAutoFilter getAutoFilter();

    String getComment();

    long getConnectionId();

    String getDataCellStyle();

    long getDataDxfId();

    String getDisplayName();

    CTExtensionList getExtLst();

    long getHeaderRowBorderDxfId();

    String getHeaderRowCellStyle();

    long getHeaderRowCount();

    long getHeaderRowDxfId();

    long getId();

    boolean getInsertRow();

    boolean getInsertRowShift();

    String getName();

    boolean getPublished();

    String getRef();

    CTSortState getSortState();

    long getTableBorderDxfId();

    CTTableColumns getTableColumns();

    CTTableStyleInfo getTableStyleInfo();

    STTableType$Enum getTableType();

    long getTotalsRowBorderDxfId();

    String getTotalsRowCellStyle();

    long getTotalsRowCount();

    long getTotalsRowDxfId();

    boolean getTotalsRowShown();

    boolean isSetAutoFilter();

    boolean isSetComment();

    boolean isSetConnectionId();

    boolean isSetDataCellStyle();

    boolean isSetDataDxfId();

    boolean isSetExtLst();

    boolean isSetHeaderRowBorderDxfId();

    boolean isSetHeaderRowCellStyle();

    boolean isSetHeaderRowCount();

    boolean isSetHeaderRowDxfId();

    boolean isSetInsertRow();

    boolean isSetInsertRowShift();

    boolean isSetName();

    boolean isSetPublished();

    boolean isSetSortState();

    boolean isSetTableBorderDxfId();

    boolean isSetTableStyleInfo();

    boolean isSetTableType();

    boolean isSetTotalsRowBorderDxfId();

    boolean isSetTotalsRowCellStyle();

    boolean isSetTotalsRowCount();

    boolean isSetTotalsRowDxfId();

    boolean isSetTotalsRowShown();

    void setAutoFilter(CTAutoFilter cTAutoFilter);

    void setComment(String str);

    void setConnectionId(long j);

    void setDataCellStyle(String str);

    void setDataDxfId(long j);

    void setDisplayName(String str);

    void setExtLst(CTExtensionList cTExtensionList);

    void setHeaderRowBorderDxfId(long j);

    void setHeaderRowCellStyle(String str);

    void setHeaderRowCount(long j);

    void setHeaderRowDxfId(long j);

    void setId(long j);

    void setInsertRow(boolean z);

    void setInsertRowShift(boolean z);

    void setName(String str);

    void setPublished(boolean z);

    void setRef(String str);

    void setSortState(CTSortState cTSortState);

    void setTableBorderDxfId(long j);

    void setTableColumns(CTTableColumns cTTableColumns);

    void setTableStyleInfo(CTTableStyleInfo cTTableStyleInfo);

    void setTableType(STTableType$Enum sTTableType$Enum);

    void setTotalsRowBorderDxfId(long j);

    void setTotalsRowCellStyle(String str);

    void setTotalsRowCount(long j);

    void setTotalsRowDxfId(long j);

    void setTotalsRowShown(boolean z);

    void unsetAutoFilter();

    void unsetComment();

    void unsetConnectionId();

    void unsetDataCellStyle();

    void unsetDataDxfId();

    void unsetExtLst();

    void unsetHeaderRowBorderDxfId();

    void unsetHeaderRowCellStyle();

    void unsetHeaderRowCount();

    void unsetHeaderRowDxfId();

    void unsetInsertRow();

    void unsetInsertRowShift();

    void unsetName();

    void unsetPublished();

    void unsetSortState();

    void unsetTableBorderDxfId();

    void unsetTableStyleInfo();

    void unsetTableType();

    void unsetTotalsRowBorderDxfId();

    void unsetTotalsRowCellStyle();

    void unsetTotalsRowCount();

    void unsetTotalsRowDxfId();

    void unsetTotalsRowShown();

    STXstring xgetComment();

    XmlUnsignedInt xgetConnectionId();

    STXstring xgetDataCellStyle();

    STDxfId xgetDataDxfId();

    STXstring xgetDisplayName();

    STDxfId xgetHeaderRowBorderDxfId();

    STXstring xgetHeaderRowCellStyle();

    XmlUnsignedInt xgetHeaderRowCount();

    STDxfId xgetHeaderRowDxfId();

    XmlUnsignedInt xgetId();

    XmlBoolean xgetInsertRow();

    XmlBoolean xgetInsertRowShift();

    STXstring xgetName();

    XmlBoolean xgetPublished();

    STRef xgetRef();

    STDxfId xgetTableBorderDxfId();

    STTableType xgetTableType();

    STDxfId xgetTotalsRowBorderDxfId();

    STXstring xgetTotalsRowCellStyle();

    XmlUnsignedInt xgetTotalsRowCount();

    STDxfId xgetTotalsRowDxfId();

    XmlBoolean xgetTotalsRowShown();

    void xsetComment(STXstring sTXstring);

    void xsetConnectionId(XmlUnsignedInt xmlUnsignedInt);

    void xsetDataCellStyle(STXstring sTXstring);

    void xsetDataDxfId(STDxfId sTDxfId);

    void xsetDisplayName(STXstring sTXstring);

    void xsetHeaderRowBorderDxfId(STDxfId sTDxfId);

    void xsetHeaderRowCellStyle(STXstring sTXstring);

    void xsetHeaderRowCount(XmlUnsignedInt xmlUnsignedInt);

    void xsetHeaderRowDxfId(STDxfId sTDxfId);

    void xsetId(XmlUnsignedInt xmlUnsignedInt);

    void xsetInsertRow(XmlBoolean xmlBoolean);

    void xsetInsertRowShift(XmlBoolean xmlBoolean);

    void xsetName(STXstring sTXstring);

    void xsetPublished(XmlBoolean xmlBoolean);

    void xsetRef(STRef sTRef);

    void xsetTableBorderDxfId(STDxfId sTDxfId);

    void xsetTableType(STTableType sTTableType);

    void xsetTotalsRowBorderDxfId(STDxfId sTDxfId);

    void xsetTotalsRowCellStyle(STXstring sTXstring);

    void xsetTotalsRowCount(XmlUnsignedInt xmlUnsignedInt);

    void xsetTotalsRowDxfId(STDxfId sTDxfId);

    void xsetTotalsRowShown(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTTable> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttable736dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
