package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;

public interface CTSheetProtection extends XmlObject {
    public static final DocumentFactory<CTSheetProtection> Factory;
    public static final SchemaType type;

    String getAlgorithmName();

    boolean getAutoFilter();

    boolean getDeleteColumns();

    boolean getDeleteRows();

    boolean getFormatCells();

    boolean getFormatColumns();

    boolean getFormatRows();

    byte[] getHashValue();

    boolean getInsertColumns();

    boolean getInsertHyperlinks();

    boolean getInsertRows();

    boolean getObjects();

    byte[] getPassword();

    boolean getPivotTables();

    byte[] getSaltValue();

    boolean getScenarios();

    boolean getSelectLockedCells();

    boolean getSelectUnlockedCells();

    boolean getSheet();

    boolean getSort();

    long getSpinCount();

    boolean isSetAlgorithmName();

    boolean isSetAutoFilter();

    boolean isSetDeleteColumns();

    boolean isSetDeleteRows();

    boolean isSetFormatCells();

    boolean isSetFormatColumns();

    boolean isSetFormatRows();

    boolean isSetHashValue();

    boolean isSetInsertColumns();

    boolean isSetInsertHyperlinks();

    boolean isSetInsertRows();

    boolean isSetObjects();

    boolean isSetPassword();

    boolean isSetPivotTables();

    boolean isSetSaltValue();

    boolean isSetScenarios();

    boolean isSetSelectLockedCells();

    boolean isSetSelectUnlockedCells();

    boolean isSetSheet();

    boolean isSetSort();

    boolean isSetSpinCount();

    void setAlgorithmName(String str);

    void setAutoFilter(boolean z);

    void setDeleteColumns(boolean z);

    void setDeleteRows(boolean z);

    void setFormatCells(boolean z);

    void setFormatColumns(boolean z);

    void setFormatRows(boolean z);

    void setHashValue(byte[] bArr);

    void setInsertColumns(boolean z);

    void setInsertHyperlinks(boolean z);

    void setInsertRows(boolean z);

    void setObjects(boolean z);

    void setPassword(byte[] bArr);

    void setPivotTables(boolean z);

    void setSaltValue(byte[] bArr);

    void setScenarios(boolean z);

    void setSelectLockedCells(boolean z);

    void setSelectUnlockedCells(boolean z);

    void setSheet(boolean z);

    void setSort(boolean z);

    void setSpinCount(long j);

    void unsetAlgorithmName();

    void unsetAutoFilter();

    void unsetDeleteColumns();

    void unsetDeleteRows();

    void unsetFormatCells();

    void unsetFormatColumns();

    void unsetFormatRows();

    void unsetHashValue();

    void unsetInsertColumns();

    void unsetInsertHyperlinks();

    void unsetInsertRows();

    void unsetObjects();

    void unsetPassword();

    void unsetPivotTables();

    void unsetSaltValue();

    void unsetScenarios();

    void unsetSelectLockedCells();

    void unsetSelectUnlockedCells();

    void unsetSheet();

    void unsetSort();

    void unsetSpinCount();

    STXstring xgetAlgorithmName();

    XmlBoolean xgetAutoFilter();

    XmlBoolean xgetDeleteColumns();

    XmlBoolean xgetDeleteRows();

    XmlBoolean xgetFormatCells();

    XmlBoolean xgetFormatColumns();

    XmlBoolean xgetFormatRows();

    XmlBase64Binary xgetHashValue();

    XmlBoolean xgetInsertColumns();

    XmlBoolean xgetInsertHyperlinks();

    XmlBoolean xgetInsertRows();

    XmlBoolean xgetObjects();

    STUnsignedShortHex xgetPassword();

    XmlBoolean xgetPivotTables();

    XmlBase64Binary xgetSaltValue();

    XmlBoolean xgetScenarios();

    XmlBoolean xgetSelectLockedCells();

    XmlBoolean xgetSelectUnlockedCells();

    XmlBoolean xgetSheet();

    XmlBoolean xgetSort();

    XmlUnsignedInt xgetSpinCount();

    void xsetAlgorithmName(STXstring sTXstring);

    void xsetAutoFilter(XmlBoolean xmlBoolean);

    void xsetDeleteColumns(XmlBoolean xmlBoolean);

    void xsetDeleteRows(XmlBoolean xmlBoolean);

    void xsetFormatCells(XmlBoolean xmlBoolean);

    void xsetFormatColumns(XmlBoolean xmlBoolean);

    void xsetFormatRows(XmlBoolean xmlBoolean);

    void xsetHashValue(XmlBase64Binary xmlBase64Binary);

    void xsetInsertColumns(XmlBoolean xmlBoolean);

    void xsetInsertHyperlinks(XmlBoolean xmlBoolean);

    void xsetInsertRows(XmlBoolean xmlBoolean);

    void xsetObjects(XmlBoolean xmlBoolean);

    void xsetPassword(STUnsignedShortHex sTUnsignedShortHex);

    void xsetPivotTables(XmlBoolean xmlBoolean);

    void xsetSaltValue(XmlBase64Binary xmlBase64Binary);

    void xsetScenarios(XmlBoolean xmlBoolean);

    void xsetSelectLockedCells(XmlBoolean xmlBoolean);

    void xsetSelectUnlockedCells(XmlBoolean xmlBoolean);

    void xsetSheet(XmlBoolean xmlBoolean);

    void xsetSort(XmlBoolean xmlBoolean);

    void xsetSpinCount(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTSheetProtection> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsheetprotection22f7type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
