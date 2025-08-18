package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTableCell extends XmlObject {
    public static final DocumentFactory<CTTableCell> Factory;
    public static final SchemaType type;

    CTOfficeArtExtensionList addNewExtLst();

    CTTableCellProperties addNewTcPr();

    CTTextBody addNewTxBody();

    CTOfficeArtExtensionList getExtLst();

    int getGridSpan();

    boolean getHMerge();

    String getId();

    int getRowSpan();

    CTTableCellProperties getTcPr();

    CTTextBody getTxBody();

    boolean getVMerge();

    boolean isSetExtLst();

    boolean isSetGridSpan();

    boolean isSetHMerge();

    boolean isSetId();

    boolean isSetRowSpan();

    boolean isSetTcPr();

    boolean isSetTxBody();

    boolean isSetVMerge();

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setGridSpan(int i);

    void setHMerge(boolean z);

    void setId(String str);

    void setRowSpan(int i);

    void setTcPr(CTTableCellProperties cTTableCellProperties);

    void setTxBody(CTTextBody cTTextBody);

    void setVMerge(boolean z);

    void unsetExtLst();

    void unsetGridSpan();

    void unsetHMerge();

    void unsetId();

    void unsetRowSpan();

    void unsetTcPr();

    void unsetTxBody();

    void unsetVMerge();

    XmlInt xgetGridSpan();

    XmlBoolean xgetHMerge();

    XmlString xgetId();

    XmlInt xgetRowSpan();

    XmlBoolean xgetVMerge();

    void xsetGridSpan(XmlInt xmlInt);

    void xsetHMerge(XmlBoolean xmlBoolean);

    void xsetId(XmlString xmlString);

    void xsetRowSpan(XmlInt xmlInt);

    void xsetVMerge(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTTableCell> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttablecell3ac1type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
