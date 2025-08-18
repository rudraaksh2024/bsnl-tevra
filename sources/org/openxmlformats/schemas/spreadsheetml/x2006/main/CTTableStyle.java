package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTableStyle extends XmlObject {
    public static final DocumentFactory<CTTableStyle> Factory;
    public static final SchemaType type;

    CTTableStyleElement addNewTableStyleElement();

    long getCount();

    String getName();

    boolean getPivot();

    boolean getTable();

    CTTableStyleElement getTableStyleElementArray(int i);

    CTTableStyleElement[] getTableStyleElementArray();

    List<CTTableStyleElement> getTableStyleElementList();

    CTTableStyleElement insertNewTableStyleElement(int i);

    boolean isSetCount();

    boolean isSetPivot();

    boolean isSetTable();

    void removeTableStyleElement(int i);

    void setCount(long j);

    void setName(String str);

    void setPivot(boolean z);

    void setTable(boolean z);

    void setTableStyleElementArray(int i, CTTableStyleElement cTTableStyleElement);

    void setTableStyleElementArray(CTTableStyleElement[] cTTableStyleElementArr);

    int sizeOfTableStyleElementArray();

    void unsetCount();

    void unsetPivot();

    void unsetTable();

    XmlUnsignedInt xgetCount();

    XmlString xgetName();

    XmlBoolean xgetPivot();

    XmlBoolean xgetTable();

    void xsetCount(XmlUnsignedInt xmlUnsignedInt);

    void xsetName(XmlString xmlString);

    void xsetPivot(XmlBoolean xmlBoolean);

    void xsetTable(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTTableStyle> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttablestylea24ctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
