package com.microsoft.schemas.office.visio.x2012.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface SectionType extends XmlObject {
    public static final DocumentFactory<SectionType> Factory;
    public static final SchemaType type;

    CellType addNewCell();

    RowType addNewRow();

    TriggerType addNewTrigger();

    CellType getCellArray(int i);

    CellType[] getCellArray();

    List<CellType> getCellList();

    boolean getDel();

    long getIX();

    String getN();

    RowType getRowArray(int i);

    RowType[] getRowArray();

    List<RowType> getRowList();

    TriggerType getTriggerArray(int i);

    TriggerType[] getTriggerArray();

    List<TriggerType> getTriggerList();

    CellType insertNewCell(int i);

    RowType insertNewRow(int i);

    TriggerType insertNewTrigger(int i);

    boolean isSetDel();

    boolean isSetIX();

    void removeCell(int i);

    void removeRow(int i);

    void removeTrigger(int i);

    void setCellArray(int i, CellType cellType);

    void setCellArray(CellType[] cellTypeArr);

    void setDel(boolean z);

    void setIX(long j);

    void setN(String str);

    void setRowArray(int i, RowType rowType);

    void setRowArray(RowType[] rowTypeArr);

    void setTriggerArray(int i, TriggerType triggerType);

    void setTriggerArray(TriggerType[] triggerTypeArr);

    int sizeOfCellArray();

    int sizeOfRowArray();

    int sizeOfTriggerArray();

    void unsetDel();

    void unsetIX();

    XmlBoolean xgetDel();

    XmlUnsignedInt xgetIX();

    XmlString xgetN();

    void xsetDel(XmlBoolean xmlBoolean);

    void xsetIX(XmlUnsignedInt xmlUnsignedInt);

    void xsetN(XmlString xmlString);

    static {
        DocumentFactory<SectionType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "sectiontype30a6type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
