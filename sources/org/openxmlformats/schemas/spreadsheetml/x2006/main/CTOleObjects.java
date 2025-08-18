package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTOleObjects extends XmlObject {
    public static final DocumentFactory<CTOleObjects> Factory;
    public static final SchemaType type;

    CTOleObject addNewOleObject();

    CTOleObject getOleObjectArray(int i);

    CTOleObject[] getOleObjectArray();

    List<CTOleObject> getOleObjectList();

    CTOleObject insertNewOleObject(int i);

    void removeOleObject(int i);

    void setOleObjectArray(int i, CTOleObject cTOleObject);

    void setOleObjectArray(CTOleObject[] cTOleObjectArr);

    int sizeOfOleObjectArray();

    static {
        DocumentFactory<CTOleObjects> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctoleobjects1455type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
