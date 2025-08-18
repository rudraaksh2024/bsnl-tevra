package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPane;

public interface CTSelection extends XmlObject {
    public static final DocumentFactory<CTSelection> Factory;
    public static final SchemaType type;

    String getActiveCell();

    long getActiveCellId();

    STPane.Enum getPane();

    List getSqref();

    boolean isSetActiveCell();

    boolean isSetActiveCellId();

    boolean isSetPane();

    boolean isSetSqref();

    void setActiveCell(String str);

    void setActiveCellId(long j);

    void setPane(STPane.Enum enumR);

    void setSqref(List list);

    void unsetActiveCell();

    void unsetActiveCellId();

    void unsetPane();

    void unsetSqref();

    STCellRef xgetActiveCell();

    XmlUnsignedInt xgetActiveCellId();

    STPane xgetPane();

    STSqref xgetSqref();

    void xsetActiveCell(STCellRef sTCellRef);

    void xsetActiveCellId(XmlUnsignedInt xmlUnsignedInt);

    void xsetPane(STPane sTPane);

    void xsetSqref(STSqref sTSqref);

    static {
        DocumentFactory<CTSelection> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctselectionca2btype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
