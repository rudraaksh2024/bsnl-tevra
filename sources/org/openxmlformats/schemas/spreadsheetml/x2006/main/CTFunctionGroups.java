package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTFunctionGroups extends XmlObject {
    public static final DocumentFactory<CTFunctionGroups> Factory;
    public static final SchemaType type;

    CTFunctionGroup addNewFunctionGroup();

    long getBuiltInGroupCount();

    CTFunctionGroup getFunctionGroupArray(int i);

    CTFunctionGroup[] getFunctionGroupArray();

    List<CTFunctionGroup> getFunctionGroupList();

    CTFunctionGroup insertNewFunctionGroup(int i);

    boolean isSetBuiltInGroupCount();

    void removeFunctionGroup(int i);

    void setBuiltInGroupCount(long j);

    void setFunctionGroupArray(int i, CTFunctionGroup cTFunctionGroup);

    void setFunctionGroupArray(CTFunctionGroup[] cTFunctionGroupArr);

    int sizeOfFunctionGroupArray();

    void unsetBuiltInGroupCount();

    XmlUnsignedInt xgetBuiltInGroupCount();

    void xsetBuiltInGroupCount(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTFunctionGroups> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfunctiongroupsbfd5type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
