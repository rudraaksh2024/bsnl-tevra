package org.apache.poi.xdgf.usermodel.section;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.SectionType;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.util.Internal;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFSheet;

public abstract class XDGFSection {
    protected Map<String, XDGFCell> _cells = new HashMap();
    protected XDGFSheet _containingSheet;
    protected SectionType _section;

    public abstract void setupMaster(XDGFSection xDGFSection);

    public static XDGFSection load(SectionType sectionType, XDGFSheet xDGFSheet) {
        return XDGFSectionTypes.load(sectionType, xDGFSheet);
    }

    protected XDGFSection(SectionType sectionType, XDGFSheet xDGFSheet) {
        this._section = sectionType;
        this._containingSheet = xDGFSheet;
        for (CellType cellType : sectionType.getCellArray()) {
            this._cells.put(cellType.getN(), new XDGFCell(cellType));
        }
    }

    @Internal
    public SectionType getXmlObject() {
        return this._section;
    }

    public String toString() {
        return "<Section type=" + this._section.getN() + " from " + this._containingSheet + ">";
    }
}
