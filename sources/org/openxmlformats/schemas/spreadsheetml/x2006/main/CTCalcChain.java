package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTCalcChain extends XmlObject {
    public static final DocumentFactory<CTCalcChain> Factory;
    public static final SchemaType type;

    CTCalcCell addNewC();

    CTExtensionList addNewExtLst();

    CTCalcCell getCArray(int i);

    CTCalcCell[] getCArray();

    List<CTCalcCell> getCList();

    CTExtensionList getExtLst();

    CTCalcCell insertNewC(int i);

    boolean isSetExtLst();

    void removeC(int i);

    void setCArray(int i, CTCalcCell cTCalcCell);

    void setCArray(CTCalcCell[] cTCalcCellArr);

    void setExtLst(CTExtensionList cTExtensionList);

    int sizeOfCArray();

    void unsetExtLst();

    static {
        DocumentFactory<CTCalcChain> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcalcchain5a0btype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
