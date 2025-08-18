package org.openxmlformats.schemas.presentationml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTLTimeConditionList extends XmlObject {
    public static final DocumentFactory<CTTLTimeConditionList> Factory;
    public static final SchemaType type;

    CTTLTimeCondition addNewCond();

    CTTLTimeCondition getCondArray(int i);

    CTTLTimeCondition[] getCondArray();

    List<CTTLTimeCondition> getCondList();

    CTTLTimeCondition insertNewCond(int i);

    void removeCond(int i);

    void setCondArray(int i, CTTLTimeCondition cTTLTimeCondition);

    void setCondArray(CTTLTimeCondition[] cTTLTimeConditionArr);

    int sizeOfCondArray();

    static {
        DocumentFactory<CTTLTimeConditionList> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttltimeconditionlistebbbtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
