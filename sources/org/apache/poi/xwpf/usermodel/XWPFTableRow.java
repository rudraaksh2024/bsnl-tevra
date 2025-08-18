package org.apache.poi.xwpf.usermodel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff1;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtCell;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHeightRule;

public class XWPFTableRow {
    private final CTRow ctRow;
    private final XWPFTable table;
    private List<XWPFTableCell> tableCells;

    public XWPFTableRow(CTRow cTRow, XWPFTable xWPFTable) {
        this.table = xWPFTable;
        this.ctRow = cTRow;
        getTableCells();
    }

    @Internal
    public CTRow getCtRow() {
        return this.ctRow;
    }

    public XWPFTableCell createCell() {
        XWPFTableCell xWPFTableCell = new XWPFTableCell(this.ctRow.addNewTc(), this, this.table.getBody());
        ensureBlockLevelElement(xWPFTableCell);
        this.tableCells.add(xWPFTableCell);
        return xWPFTableCell;
    }

    public XWPFTableCell getCell(int i) {
        if (i < 0 || i >= this.ctRow.sizeOfTcArray()) {
            return null;
        }
        return getTableCells().get(i);
    }

    public void removeCell(int i) {
        if (i >= 0 && i < this.ctRow.sizeOfTcArray()) {
            this.tableCells.remove(i);
            this.ctRow.removeTc(i);
        }
    }

    public XWPFTableCell addNewTableCell() {
        XWPFTableCell xWPFTableCell = new XWPFTableCell(this.ctRow.addNewTc(), this, this.table.getBody());
        ensureBlockLevelElement(xWPFTableCell);
        this.tableCells.add(xWPFTableCell);
        return xWPFTableCell;
    }

    private void ensureBlockLevelElement(XWPFTableCell xWPFTableCell) {
        if (xWPFTableCell.getParagraphs().isEmpty()) {
            xWPFTableCell.addParagraph();
        }
    }

    public int getHeight() {
        CTTrPr trPr = getTrPr();
        if (trPr.sizeOfTrHeightArray() == 0) {
            return 0;
        }
        return (int) Units.toDXA(POIXMLUnits.parseLength(trPr.getTrHeightArray(0).xgetVal()));
    }

    public void setHeight(int i) {
        CTTrPr trPr = getTrPr();
        (trPr.sizeOfTrHeightArray() == 0 ? trPr.addNewTrHeight() : trPr.getTrHeightArray(0)).setVal(new BigInteger(Integer.toString(i)));
    }

    public TableRowHeightRule getHeightRule() {
        CTTrPr trPr = getTrPr();
        if (trPr.sizeOfTrHeightArray() == 0) {
            return TableRowHeightRule.AUTO;
        }
        return TableRowHeightRule.valueOf(trPr.getTrHeightArray(0).getHRule().intValue());
    }

    public void setHeightRule(TableRowHeightRule tableRowHeightRule) {
        CTTrPr trPr = getTrPr();
        (trPr.sizeOfTrHeightArray() == 0 ? trPr.addNewTrHeight() : trPr.getTrHeightArray(0)).setHRule(STHeightRule.Enum.forInt(tableRowHeightRule.getValue()));
    }

    private CTTrPr getTrPr() {
        boolean isSetTrPr = this.ctRow.isSetTrPr();
        CTRow cTRow = this.ctRow;
        return isSetTrPr ? cTRow.getTrPr() : cTRow.addNewTrPr();
    }

    public XWPFTable getTable() {
        return this.table;
    }

    public List<ICell> getTableICells() {
        ArrayList arrayList = new ArrayList();
        XmlCursor newCursor = this.ctRow.newCursor();
        try {
            newCursor.selectPath("./*");
            while (newCursor.toNextSelection()) {
                XmlObject object = newCursor.getObject();
                if (object instanceof CTTc) {
                    arrayList.add(new XWPFTableCell((CTTc) object, this, this.table.getBody()));
                } else if (object instanceof CTSdtCell) {
                    arrayList.add(new XWPFSDTCell((CTSdtCell) object, this, this.table.getBody()));
                }
            }
            return arrayList;
        } finally {
            newCursor.dispose();
        }
    }

    public List<XWPFTableCell> getTableCells() {
        if (this.tableCells == null) {
            ArrayList arrayList = new ArrayList();
            for (CTTc xWPFTableCell : this.ctRow.getTcArray()) {
                arrayList.add(new XWPFTableCell(xWPFTableCell, this, this.table.getBody()));
            }
            this.tableCells = arrayList;
        }
        return this.tableCells;
    }

    public XWPFTableCell getTableCell(CTTc cTTc) {
        for (XWPFTableCell next : this.tableCells) {
            if (next.getCTTc() == cTTc) {
                return next;
            }
        }
        return null;
    }

    public boolean isCantSplitRow() {
        if (!this.ctRow.isSetTrPr()) {
            return false;
        }
        CTTrPr trPr = getTrPr();
        if (trPr.sizeOfCantSplitArray() <= 0) {
            return false;
        }
        CTOnOff cantSplitArray = trPr.getCantSplitArray(0);
        if (!cantSplitArray.isSetVal() || POIXMLUnits.parseOnOff(cantSplitArray.xgetVal())) {
            return true;
        }
        return false;
    }

    public void setCantSplitRow(boolean z) {
        CTTrPr trPr = getTrPr();
        (trPr.sizeOfCantSplitArray() > 0 ? trPr.getCantSplitArray(0) : trPr.addNewCantSplit()).setVal(z ? STOnOff1.ON : STOnOff1.OFF);
    }

    public boolean isRepeatHeader() {
        boolean z = false;
        for (XWPFTableRow next : this.table.getRows()) {
            boolean repeat = next.getRepeat();
            if (next == this || !repeat) {
                return repeat;
            }
            z = repeat;
        }
        return z;
    }

    private boolean getRepeat() {
        if (!this.ctRow.isSetTrPr()) {
            return false;
        }
        CTTrPr trPr = getTrPr();
        if (trPr.sizeOfTblHeaderArray() <= 0) {
            return false;
        }
        CTOnOff tblHeaderArray = trPr.getTblHeaderArray(0);
        if (!tblHeaderArray.isSetVal() || POIXMLUnits.parseOnOff(tblHeaderArray.xgetVal())) {
            return true;
        }
        return false;
    }

    public void setRepeatHeader(boolean z) {
        CTTrPr trPr = getTrPr();
        (trPr.sizeOfTblHeaderArray() > 0 ? trPr.getTblHeaderArray(0) : trPr.addNewTblHeader()).setVal(z ? STOnOff1.ON : STOnOff1.OFF);
    }
}
