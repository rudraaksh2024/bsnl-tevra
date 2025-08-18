package com.microsoft.schemas.office.visio.x2012.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.AbstractDocumentFactory;

public interface SheetType extends XmlObject {
    public static final AbstractDocumentFactory<SheetType> Factory;
    public static final SchemaType type;

    CellType addNewCell();

    SectionType addNewSection();

    TriggerType addNewTrigger();

    CellType getCellArray(int i);

    CellType[] getCellArray();

    List<CellType> getCellList();

    long getFillStyle();

    long getLineStyle();

    SectionType getSectionArray(int i);

    SectionType[] getSectionArray();

    List<SectionType> getSectionList();

    long getTextStyle();

    TriggerType getTriggerArray(int i);

    TriggerType[] getTriggerArray();

    List<TriggerType> getTriggerList();

    CellType insertNewCell(int i);

    SectionType insertNewSection(int i);

    TriggerType insertNewTrigger(int i);

    boolean isSetFillStyle();

    boolean isSetLineStyle();

    boolean isSetTextStyle();

    void removeCell(int i);

    void removeSection(int i);

    void removeTrigger(int i);

    void setCellArray(int i, CellType cellType);

    void setCellArray(CellType[] cellTypeArr);

    void setFillStyle(long j);

    void setLineStyle(long j);

    void setSectionArray(int i, SectionType sectionType);

    void setSectionArray(SectionType[] sectionTypeArr);

    void setTextStyle(long j);

    void setTriggerArray(int i, TriggerType triggerType);

    void setTriggerArray(TriggerType[] triggerTypeArr);

    int sizeOfCellArray();

    int sizeOfSectionArray();

    int sizeOfTriggerArray();

    void unsetFillStyle();

    void unsetLineStyle();

    void unsetTextStyle();

    XmlUnsignedInt xgetFillStyle();

    XmlUnsignedInt xgetLineStyle();

    XmlUnsignedInt xgetTextStyle();

    void xsetFillStyle(XmlUnsignedInt xmlUnsignedInt);

    void xsetLineStyle(XmlUnsignedInt xmlUnsignedInt);

    void xsetTextStyle(XmlUnsignedInt xmlUnsignedInt);

    static {
        AbstractDocumentFactory<SheetType> abstractDocumentFactory = new AbstractDocumentFactory<>(TypeSystemHolder.typeSystem, "sheettype25actype");
        Factory = abstractDocumentFactory;
        type = abstractDocumentFactory.getType();
    }
}
