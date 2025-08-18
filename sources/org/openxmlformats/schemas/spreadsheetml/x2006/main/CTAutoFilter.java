package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTAutoFilter extends XmlObject {
    public static final DocumentFactory<CTAutoFilter> Factory;
    public static final SchemaType type;

    CTExtensionList addNewExtLst();

    CTFilterColumn addNewFilterColumn();

    CTSortState addNewSortState();

    CTExtensionList getExtLst();

    CTFilterColumn getFilterColumnArray(int i);

    CTFilterColumn[] getFilterColumnArray();

    List<CTFilterColumn> getFilterColumnList();

    String getRef();

    CTSortState getSortState();

    CTFilterColumn insertNewFilterColumn(int i);

    boolean isSetExtLst();

    boolean isSetRef();

    boolean isSetSortState();

    void removeFilterColumn(int i);

    void setExtLst(CTExtensionList cTExtensionList);

    void setFilterColumnArray(int i, CTFilterColumn cTFilterColumn);

    void setFilterColumnArray(CTFilterColumn[] cTFilterColumnArr);

    void setRef(String str);

    void setSortState(CTSortState cTSortState);

    int sizeOfFilterColumnArray();

    void unsetExtLst();

    void unsetRef();

    void unsetSortState();

    STRef xgetRef();

    void xsetRef(STRef sTRef);

    static {
        DocumentFactory<CTAutoFilter> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctautofiltera8d0type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
