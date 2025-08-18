package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;

public interface CTSdtDropDownList extends XmlObject {
    public static final DocumentFactory<CTSdtDropDownList> Factory;
    public static final SchemaType type;

    CTSdtListItem addNewListItem();

    String getLastValue();

    CTSdtListItem getListItemArray(int i);

    CTSdtListItem[] getListItemArray();

    List<CTSdtListItem> getListItemList();

    CTSdtListItem insertNewListItem(int i);

    boolean isSetLastValue();

    void removeListItem(int i);

    void setLastValue(String str);

    void setListItemArray(int i, CTSdtListItem cTSdtListItem);

    void setListItemArray(CTSdtListItem[] cTSdtListItemArr);

    int sizeOfListItemArray();

    void unsetLastValue();

    STString xgetLastValue();

    void xsetLastValue(STString sTString);

    static {
        DocumentFactory<CTSdtDropDownList> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsdtdropdownlist5880type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
