package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTStylesheet extends XmlObject {
    public static final DocumentFactory<CTStylesheet> Factory;
    public static final SchemaType type;

    CTBorders addNewBorders();

    CTCellStyleXfs addNewCellStyleXfs();

    CTCellStyles addNewCellStyles();

    CTCellXfs addNewCellXfs();

    CTColors addNewColors();

    CTDxfs addNewDxfs();

    CTExtensionList addNewExtLst();

    CTFills addNewFills();

    CTFonts addNewFonts();

    CTNumFmts addNewNumFmts();

    CTTableStyles addNewTableStyles();

    CTBorders getBorders();

    CTCellStyleXfs getCellStyleXfs();

    CTCellStyles getCellStyles();

    CTCellXfs getCellXfs();

    CTColors getColors();

    CTDxfs getDxfs();

    CTExtensionList getExtLst();

    CTFills getFills();

    CTFonts getFonts();

    CTNumFmts getNumFmts();

    CTTableStyles getTableStyles();

    boolean isSetBorders();

    boolean isSetCellStyleXfs();

    boolean isSetCellStyles();

    boolean isSetCellXfs();

    boolean isSetColors();

    boolean isSetDxfs();

    boolean isSetExtLst();

    boolean isSetFills();

    boolean isSetFonts();

    boolean isSetNumFmts();

    boolean isSetTableStyles();

    void setBorders(CTBorders cTBorders);

    void setCellStyleXfs(CTCellStyleXfs cTCellStyleXfs);

    void setCellStyles(CTCellStyles cTCellStyles);

    void setCellXfs(CTCellXfs cTCellXfs);

    void setColors(CTColors cTColors);

    void setDxfs(CTDxfs cTDxfs);

    void setExtLst(CTExtensionList cTExtensionList);

    void setFills(CTFills cTFills);

    void setFonts(CTFonts cTFonts);

    void setNumFmts(CTNumFmts cTNumFmts);

    void setTableStyles(CTTableStyles cTTableStyles);

    void unsetBorders();

    void unsetCellStyleXfs();

    void unsetCellStyles();

    void unsetCellXfs();

    void unsetColors();

    void unsetDxfs();

    void unsetExtLst();

    void unsetFills();

    void unsetFonts();

    void unsetNumFmts();

    void unsetTableStyles();

    static {
        DocumentFactory<CTStylesheet> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctstylesheet4257type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
