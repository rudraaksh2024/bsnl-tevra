package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTPageFields extends XmlObject {
    public static final DocumentFactory<CTPageFields> Factory;
    public static final SchemaType type;

    CTPageField addNewPageField();

    long getCount();

    CTPageField getPageFieldArray(int i);

    CTPageField[] getPageFieldArray();

    List<CTPageField> getPageFieldList();

    CTPageField insertNewPageField(int i);

    boolean isSetCount();

    void removePageField(int i);

    void setCount(long j);

    void setPageFieldArray(int i, CTPageField cTPageField);

    void setPageFieldArray(CTPageField[] cTPageFieldArr);

    int sizeOfPageFieldArray();

    void unsetCount();

    XmlUnsignedInt xgetCount();

    void xsetCount(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTPageFields> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpagefields1db1type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
