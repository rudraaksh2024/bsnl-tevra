package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTSst extends XmlObject {
    public static final DocumentFactory<CTSst> Factory;
    public static final SchemaType type;

    CTExtensionList addNewExtLst();

    CTRst addNewSi();

    long getCount();

    CTExtensionList getExtLst();

    CTRst getSiArray(int i);

    CTRst[] getSiArray();

    List<CTRst> getSiList();

    long getUniqueCount();

    CTRst insertNewSi(int i);

    boolean isSetCount();

    boolean isSetExtLst();

    boolean isSetUniqueCount();

    void removeSi(int i);

    void setCount(long j);

    void setExtLst(CTExtensionList cTExtensionList);

    void setSiArray(int i, CTRst cTRst);

    void setSiArray(CTRst[] cTRstArr);

    void setUniqueCount(long j);

    int sizeOfSiArray();

    void unsetCount();

    void unsetExtLst();

    void unsetUniqueCount();

    XmlUnsignedInt xgetCount();

    XmlUnsignedInt xgetUniqueCount();

    void xsetCount(XmlUnsignedInt xmlUnsignedInt);

    void xsetUniqueCount(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTSst> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsst44f3type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
