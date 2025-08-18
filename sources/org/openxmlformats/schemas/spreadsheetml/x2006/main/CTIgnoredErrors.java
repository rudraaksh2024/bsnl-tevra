package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTIgnoredErrors extends XmlObject {
    public static final DocumentFactory<CTIgnoredErrors> Factory;
    public static final SchemaType type;

    CTExtensionList addNewExtLst();

    CTIgnoredError addNewIgnoredError();

    CTExtensionList getExtLst();

    CTIgnoredError getIgnoredErrorArray(int i);

    CTIgnoredError[] getIgnoredErrorArray();

    List<CTIgnoredError> getIgnoredErrorList();

    CTIgnoredError insertNewIgnoredError(int i);

    boolean isSetExtLst();

    void removeIgnoredError(int i);

    void setExtLst(CTExtensionList cTExtensionList);

    void setIgnoredErrorArray(int i, CTIgnoredError cTIgnoredError);

    void setIgnoredErrorArray(CTIgnoredError[] cTIgnoredErrorArr);

    int sizeOfIgnoredErrorArray();

    void unsetExtLst();

    static {
        DocumentFactory<CTIgnoredErrors> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctignorederrorsbebctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
