package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTableStyles extends XmlObject {
    public static final DocumentFactory<CTTableStyles> Factory;
    public static final SchemaType type;

    CTTableStyle addNewTableStyle();

    long getCount();

    String getDefaultPivotStyle();

    String getDefaultTableStyle();

    CTTableStyle getTableStyleArray(int i);

    CTTableStyle[] getTableStyleArray();

    List<CTTableStyle> getTableStyleList();

    CTTableStyle insertNewTableStyle(int i);

    boolean isSetCount();

    boolean isSetDefaultPivotStyle();

    boolean isSetDefaultTableStyle();

    void removeTableStyle(int i);

    void setCount(long j);

    void setDefaultPivotStyle(String str);

    void setDefaultTableStyle(String str);

    void setTableStyleArray(int i, CTTableStyle cTTableStyle);

    void setTableStyleArray(CTTableStyle[] cTTableStyleArr);

    int sizeOfTableStyleArray();

    void unsetCount();

    void unsetDefaultPivotStyle();

    void unsetDefaultTableStyle();

    XmlUnsignedInt xgetCount();

    XmlString xgetDefaultPivotStyle();

    XmlString xgetDefaultTableStyle();

    void xsetCount(XmlUnsignedInt xmlUnsignedInt);

    void xsetDefaultPivotStyle(XmlString xmlString);

    void xsetDefaultTableStyle(XmlString xmlString);

    static {
        DocumentFactory<CTTableStyles> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttablestyles872ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
