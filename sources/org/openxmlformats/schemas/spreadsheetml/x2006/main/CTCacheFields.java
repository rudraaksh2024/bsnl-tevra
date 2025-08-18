package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTCacheFields extends XmlObject {
    public static final DocumentFactory<CTCacheFields> Factory;
    public static final SchemaType type;

    CTCacheField addNewCacheField();

    CTCacheField getCacheFieldArray(int i);

    CTCacheField[] getCacheFieldArray();

    List<CTCacheField> getCacheFieldList();

    long getCount();

    CTCacheField insertNewCacheField(int i);

    boolean isSetCount();

    void removeCacheField(int i);

    void setCacheFieldArray(int i, CTCacheField cTCacheField);

    void setCacheFieldArray(CTCacheField[] cTCacheFieldArr);

    void setCount(long j);

    int sizeOfCacheFieldArray();

    void unsetCount();

    XmlUnsignedInt xgetCount();

    void xsetCount(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTCacheFields> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcachefieldsf5fatype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
