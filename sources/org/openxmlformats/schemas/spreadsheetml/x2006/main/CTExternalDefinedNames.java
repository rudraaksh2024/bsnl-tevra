package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTExternalDefinedNames extends XmlObject {
    public static final DocumentFactory<CTExternalDefinedNames> Factory;
    public static final SchemaType type;

    CTExternalDefinedName addNewDefinedName();

    CTExternalDefinedName getDefinedNameArray(int i);

    CTExternalDefinedName[] getDefinedNameArray();

    List<CTExternalDefinedName> getDefinedNameList();

    CTExternalDefinedName insertNewDefinedName(int i);

    void removeDefinedName(int i);

    void setDefinedNameArray(int i, CTExternalDefinedName cTExternalDefinedName);

    void setDefinedNameArray(CTExternalDefinedName[] cTExternalDefinedNameArr);

    int sizeOfDefinedNameArray();

    static {
        DocumentFactory<CTExternalDefinedNames> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctexternaldefinednamesccf3type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
