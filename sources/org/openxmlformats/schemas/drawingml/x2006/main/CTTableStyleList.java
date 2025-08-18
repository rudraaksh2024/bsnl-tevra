package org.openxmlformats.schemas.drawingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STGuid;

public interface CTTableStyleList extends XmlObject {
    public static final DocumentFactory<CTTableStyleList> Factory;
    public static final SchemaType type;

    CTTableStyle addNewTblStyle();

    String getDef();

    CTTableStyle getTblStyleArray(int i);

    CTTableStyle[] getTblStyleArray();

    List<CTTableStyle> getTblStyleList();

    CTTableStyle insertNewTblStyle(int i);

    void removeTblStyle(int i);

    void setDef(String str);

    void setTblStyleArray(int i, CTTableStyle cTTableStyle);

    void setTblStyleArray(CTTableStyle[] cTTableStyleArr);

    int sizeOfTblStyleArray();

    STGuid xgetDef();

    void xsetDef(STGuid sTGuid);

    static {
        DocumentFactory<CTTableStyleList> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttablestylelist4bdctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
