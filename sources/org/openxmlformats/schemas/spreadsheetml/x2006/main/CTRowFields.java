package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTRowFields extends XmlObject {
    public static final DocumentFactory<CTRowFields> Factory;
    public static final SchemaType type;

    CTField addNewField();

    long getCount();

    CTField getFieldArray(int i);

    CTField[] getFieldArray();

    List<CTField> getFieldList();

    CTField insertNewField(int i);

    boolean isSetCount();

    void removeField(int i);

    void setCount(long j);

    void setFieldArray(int i, CTField cTField);

    void setFieldArray(CTField[] cTFieldArr);

    int sizeOfFieldArray();

    void unsetCount();

    XmlUnsignedInt xgetCount();

    void xsetCount(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTRowFields> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctrowfields0312type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
