package org.apache.poi.xslf.usermodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Units;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCell;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableRow;

public class XSLFTableRow implements Iterable<XSLFTableCell> {
    private final List<XSLFTableCell> _cells;
    private final CTTableRow _row;
    private final XSLFTable _table;

    XSLFTableRow(CTTableRow cTTableRow, XSLFTable xSLFTable) {
        this._row = cTTableRow;
        this._table = xSLFTable;
        CTTableCell[] tcArray = cTTableRow.getTcArray();
        this._cells = new ArrayList(tcArray.length);
        for (CTTableCell xSLFTableCell : tcArray) {
            this._cells.add(new XSLFTableCell(xSLFTableCell, xSLFTable));
        }
    }

    public CTTableRow getXmlObject() {
        return this._row;
    }

    public Iterator<XSLFTableCell> iterator() {
        return this._cells.iterator();
    }

    public List<XSLFTableCell> getCells() {
        return Collections.unmodifiableList(this._cells);
    }

    public double getHeight() {
        return Units.toPoints(POIXMLUnits.parseLength(this._row.xgetH()));
    }

    public void setHeight(double d) {
        this._row.setH(Integer.valueOf(Units.toEMU(d)));
    }

    public XSLFTableCell addCell() {
        CTTableCell addNewTc = this._row.addNewTc();
        addNewTc.set(XSLFTableCell.prototype());
        XSLFTableCell xSLFTableCell = new XSLFTableCell(addNewTc, this._table);
        this._cells.add(xSLFTableCell);
        if (this._table.getNumberOfColumns() < this._row.sizeOfTcArray()) {
            this._table.getCTTable().getTblGrid().addNewGridCol().setW(Integer.valueOf(Units.toEMU(100.0d)));
        }
        this._table.updateRowColIndexes();
        return xSLFTableCell;
    }

    public XSLFTableCell insertCell(int i) {
        CTTableCell insertNewTc = this._row.insertNewTc(i);
        insertNewTc.set(XSLFTableCell.prototype());
        XSLFTableCell xSLFTableCell = new XSLFTableCell(insertNewTc, this._table);
        this._cells.add(i, xSLFTableCell);
        if (this._table.getNumberOfColumns() < this._row.sizeOfTcArray()) {
            this._table.getCTTable().getTblGrid().insertNewGridCol(i).setW(Integer.valueOf(Units.toEMU(100.0d)));
        }
        this._table.updateRowColIndexes();
        return xSLFTableCell;
    }

    public void removeCell(int i) {
        if (this._row.sizeOfTcArray() >= i) {
            this._row.removeTc(i);
            this._cells.remove(i);
            this._table.updateRowColIndexes();
            return;
        }
        throw new IndexOutOfBoundsException("Cannot remove cell at " + i + "; row has only " + this._row.sizeOfTcArray() + "columns.");
    }

    public void mergeCells(int i, int i2) {
        if (i < i2) {
            this._cells.get(i).setGridSpan((i2 - i) + 1);
            for (XSLFTableCell hMerge : this._cells.subList(i + 1, i2 + 1)) {
                hMerge.setHMerge();
            }
            return;
        }
        throw new IllegalArgumentException("Cannot merge, first column >= last column : " + i + " >= " + i2);
    }
}
