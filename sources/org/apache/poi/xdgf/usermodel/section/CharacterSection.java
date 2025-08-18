package org.apache.poi.xdgf.usermodel.section;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.SectionType;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFSheet;

public class CharacterSection extends XDGFSection {
    Map<String, XDGFCell> _characterCells = new HashMap();
    Color _fontColor;
    Double _fontSize;

    public void setupMaster(XDGFSection xDGFSection) {
    }

    public CharacterSection(SectionType sectionType, XDGFSheet xDGFSheet) {
        super(sectionType, xDGFSheet);
        for (CellType cellType : sectionType.getRowArray(0).getCellArray()) {
            this._characterCells.put(cellType.getN(), new XDGFCell(cellType));
        }
        this._fontSize = XDGFCell.maybeGetDouble(this._characterCells, "Size");
        String maybeGetString = XDGFCell.maybeGetString(this._characterCells, "Color");
        if (maybeGetString != null) {
            this._fontColor = Color.decode(maybeGetString);
        }
    }

    public Double getFontSize() {
        return this._fontSize;
    }

    public Color getFontColor() {
        return this._fontColor;
    }
}
