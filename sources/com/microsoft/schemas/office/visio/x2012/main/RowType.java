package com.microsoft.schemas.office.visio.x2012.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface RowType extends XmlObject {
    public static final DocumentFactory<RowType> Factory;
    public static final SchemaType type;

    CellType addNewCell();

    TriggerType addNewTrigger();

    CellType getCellArray(int i);

    CellType[] getCellArray();

    List<CellType> getCellList();

    boolean getDel();

    long getIX();

    String getLocalName();

    String getN();

    String getT();

    TriggerType getTriggerArray(int i);

    TriggerType[] getTriggerArray();

    List<TriggerType> getTriggerList();

    CellType insertNewCell(int i);

    TriggerType insertNewTrigger(int i);

    boolean isSetDel();

    boolean isSetIX();

    boolean isSetLocalName();

    boolean isSetN();

    boolean isSetT();

    void removeCell(int i);

    void removeTrigger(int i);

    void setCellArray(int i, CellType cellType);

    void setCellArray(CellType[] cellTypeArr);

    void setDel(boolean z);

    void setIX(long j);

    void setLocalName(String str);

    void setN(String str);

    void setT(String str);

    void setTriggerArray(int i, TriggerType triggerType);

    void setTriggerArray(TriggerType[] triggerTypeArr);

    int sizeOfCellArray();

    int sizeOfTriggerArray();

    void unsetDel();

    void unsetIX();

    void unsetLocalName();

    void unsetN();

    void unsetT();

    XmlBoolean xgetDel();

    XmlUnsignedInt xgetIX();

    XmlString xgetLocalName();

    XmlString xgetN();

    XmlString xgetT();

    void xsetDel(XmlBoolean xmlBoolean);

    void xsetIX(XmlUnsignedInt xmlUnsignedInt);

    void xsetLocalName(XmlString xmlString);

    void xsetN(XmlString xmlString);

    void xsetT(XmlString xmlString);

    static {
        DocumentFactory<RowType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "rowtype03d1type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
