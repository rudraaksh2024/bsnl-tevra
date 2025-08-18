package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTFills extends XmlObject {
    public static final DocumentFactory<CTFills> Factory;
    public static final SchemaType type;

    CTFill addNewFill();

    long getCount();

    CTFill getFillArray(int i);

    CTFill[] getFillArray();

    List<CTFill> getFillList();

    CTFill insertNewFill(int i);

    boolean isSetCount();

    void removeFill(int i);

    void setCount(long j);

    void setFillArray(int i, CTFill cTFill);

    void setFillArray(CTFill[] cTFillArr);

    int sizeOfFillArray();

    void unsetCount();

    XmlUnsignedInt xgetCount();

    void xsetCount(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTFills> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfills2c6ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
