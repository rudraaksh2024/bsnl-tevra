package org.apache.poi.xslf.usermodel;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.sl.draw.DrawFactory;
import org.apache.poi.sl.usermodel.TableShape;
import org.apache.poi.sl.usermodel.TextShape;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlAnyTypeImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectData;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTable;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableRow;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrameNonVisual;

public class XSLFTable extends XSLFGraphicFrame implements Iterable<XSLFTableRow>, TableShape<XSLFShape, XSLFTextParagraph> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger LOG = LogManager.getLogger((Class<?>) XSLFTable.class);
    static final String TABLE_URI = "http://schemas.openxmlformats.org/drawingml/2006/table";
    private final List<XSLFTableRow> _rows;
    private final CTTable _table;

    /* JADX INFO: finally extract failed */
    XSLFTable(CTGraphicalObjectFrame cTGraphicalObjectFrame, XSLFSheet xSLFSheet) {
        super(cTGraphicalObjectFrame, xSLFSheet);
        CTGraphicalObjectData graphicData = cTGraphicalObjectFrame.getGraphic().getGraphicData();
        XmlCursor newCursor = graphicData.newCursor();
        try {
            if (newCursor.toChild(XSSFRelation.NS_DRAWINGML, "tbl")) {
                XmlObject object = newCursor.getObject();
                if (!(object instanceof XmlAnyTypeImpl)) {
                    CTTable cTTable = (CTTable) object;
                    this._table = cTTable;
                    newCursor.dispose();
                    this._rows = new ArrayList(cTTable.sizeOfTrArray());
                    for (CTTableRow xSLFTableRow : cTTable.getTrList()) {
                        this._rows.add(new XSLFTableRow(xSLFTableRow, this));
                    }
                    updateRowColIndexes();
                    return;
                }
                throw new IllegalStateException("Schemas (*.xsb) for CTTable can't be loaded - usually this happens when OSGI loading is used and the thread context classloader has no reference to the xmlbeans classes");
            }
            throw new IllegalStateException("a:tbl element was not found in\n " + graphicData);
        } catch (Throwable th) {
            newCursor.dispose();
            throw th;
        }
    }

    public XSLFTableCell getCell(int i, int i2) {
        XSLFTableRow xSLFTableRow;
        if (i < 0 || this._rows.size() <= i || (xSLFTableRow = this._rows.get(i)) == null) {
            return null;
        }
        List<XSLFTableCell> cells = xSLFTableRow.getCells();
        if (i2 >= 0 && cells.size() > i2) {
            return cells.get(i2);
        }
        return null;
    }

    @Internal
    public CTTable getCTTable() {
        return this._table;
    }

    public int getNumberOfColumns() {
        return this._table.getTblGrid().sizeOfGridColArray();
    }

    public int getNumberOfRows() {
        return this._table.sizeOfTrArray();
    }

    public double getColumnWidth(int i) {
        return Units.toPoints(POIXMLUnits.parseLength(this._table.getTblGrid().getGridColArray(i).xgetW()));
    }

    public void setColumnWidth(int i, double d) {
        this._table.getTblGrid().getGridColArray(i).setW(Integer.valueOf(Units.toEMU(d)));
    }

    public double getRowHeight(int i) {
        return Units.toPoints(POIXMLUnits.parseLength(this._table.getTrArray(i).xgetH()));
    }

    public void setRowHeight(int i, double d) {
        this._table.getTrArray(i).setH(Integer.valueOf(Units.toEMU(d)));
    }

    public Iterator<XSLFTableRow> iterator() {
        return this._rows.iterator();
    }

    public List<XSLFTableRow> getRows() {
        return Collections.unmodifiableList(this._rows);
    }

    public XSLFTableRow addRow() {
        XSLFTableRow initializeRow = initializeRow(this._table.addNewTr());
        this._rows.add(initializeRow);
        updateRowColIndexes();
        return initializeRow;
    }

    private XSLFTableRow initializeRow(CTTableRow cTTableRow) {
        XSLFTableRow xSLFTableRow = new XSLFTableRow(cTTableRow, this);
        xSLFTableRow.setHeight(20.0d);
        return xSLFTableRow;
    }

    public XSLFTableRow insertRow(int i) {
        if (getNumberOfRows() >= i) {
            XSLFTableRow initializeRow = initializeRow(this._table.insertNewTr(i));
            for (int i2 = 0; i2 < getNumberOfColumns(); i2++) {
                initializeRow.addCell();
            }
            this._rows.add(i, initializeRow);
            return initializeRow;
        }
        throw new IndexOutOfBoundsException("Cannot insert row at " + i + "; table has only " + getNumberOfRows() + "rows.");
    }

    public void removeRow(int i) {
        if (getNumberOfRows() >= i) {
            this._table.removeTr(i);
            this._rows.remove(i);
            updateRowColIndexes();
            return;
        }
        throw new IndexOutOfBoundsException("Cannot remove row at " + i + "; table has only " + getNumberOfRows() + "rows.");
    }

    public void addColumn() {
        this._table.getTblGrid().addNewGridCol().setW(Long.valueOf(POIXMLUnits.parseLength(this._table.getTblGrid().getGridColArray(getNumberOfColumns() - 1).xgetW())));
        for (XSLFTableRow addCell : this._rows) {
            XSLFTableCell addCell2 = addCell.addCell();
            new XDDFTextBody(addCell2, addCell2.getTextBody(true)).initialize();
        }
    }

    public void insertColumn(int i) {
        if (getNumberOfColumns() >= i) {
            this._table.getTblGrid().insertNewGridCol(i).setW(Long.valueOf(POIXMLUnits.parseLength(this._table.getTblGrid().getGridColArray(i).xgetW())));
            for (XSLFTableRow insertCell : this._rows) {
                XSLFTableCell insertCell2 = insertCell.insertCell(i);
                new XDDFTextBody(insertCell2, insertCell2.getTextBody(true)).initialize();
            }
            return;
        }
        throw new IndexOutOfBoundsException("Cannot insert column at " + i + "; table has only " + getNumberOfColumns() + "columns.");
    }

    public void removeColumn(int i) {
        if (getNumberOfColumns() >= i) {
            this._table.getTblGrid().removeGridCol(i);
            for (XSLFTableRow removeCell : this._rows) {
                removeCell.removeCell(i);
            }
            return;
        }
        throw new IndexOutOfBoundsException("Cannot remove column at " + i + "; table has only " + getNumberOfColumns() + "columns.");
    }

    static CTGraphicalObjectFrame prototype(int i) {
        CTGraphicalObjectFrame newInstance = CTGraphicalObjectFrame.Factory.newInstance();
        CTGraphicalObjectFrameNonVisual addNewNvGraphicFramePr = newInstance.addNewNvGraphicFramePr();
        CTNonVisualDrawingProps addNewCNvPr = addNewNvGraphicFramePr.addNewCNvPr();
        addNewCNvPr.setName("Table " + i);
        addNewCNvPr.setId((long) i);
        addNewNvGraphicFramePr.addNewCNvGraphicFramePr().addNewGraphicFrameLocks().setNoGrp(true);
        addNewNvGraphicFramePr.addNewNvPr();
        newInstance.addNewXfrm();
        CTGraphicalObjectData addNewGraphicData = newInstance.addNewGraphic().addNewGraphicData();
        XmlCursor newCursor = addNewGraphicData.newCursor();
        try {
            newCursor.toNextToken();
            newCursor.beginElement(new QName(XSSFRelation.NS_DRAWINGML, "tbl"));
            CTTable newInstance2 = CTTable.Factory.newInstance();
            newInstance2.addNewTblPr();
            newInstance2.addNewTblGrid();
            newCursor = newInstance2.newCursor();
            newCursor.moveXmlContents(newCursor);
            newCursor.dispose();
            addNewGraphicData.setUri(TABLE_URI);
            return newInstance;
        } catch (Throwable th) {
            throw th;
        } finally {
            newCursor.dispose();
        }
    }

    public void mergeCells(int i, int i2, int i3, int i4) {
        if (i > i2) {
            throw new IllegalArgumentException("Cannot merge, first row > last row : " + i + " > " + i2);
        } else if (i3 <= i4) {
            boolean z = true;
            int i5 = (i2 - i) + 1;
            boolean z2 = i5 > 1;
            int i6 = (i4 - i3) + 1;
            if (i6 <= 1) {
                z = false;
            }
            for (int i7 = i; i7 <= i2; i7++) {
                XSLFTableRow xSLFTableRow = this._rows.get(i7);
                for (int i8 = i3; i8 <= i4; i8++) {
                    XSLFTableCell xSLFTableCell = xSLFTableRow.getCells().get(i8);
                    if (z2) {
                        if (i7 == i) {
                            xSLFTableCell.setRowSpan(i5);
                        } else {
                            xSLFTableCell.setVMerge();
                        }
                    }
                    if (z) {
                        if (i8 == i3) {
                            xSLFTableCell.setGridSpan(i6);
                        } else {
                            xSLFTableCell.setHMerge();
                        }
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Cannot merge, first column > last column : " + i3 + " > " + i4);
        }
    }

    /* access modifiers changed from: protected */
    public XSLFTableStyle getTableStyle() {
        CTTable cTTable = getCTTable();
        if (cTTable.isSetTblPr() && cTTable.getTblPr().isSetTableStyleId()) {
            String tableStyleId = cTTable.getTblPr().getTableStyleId();
            for (XSLFTableStyle next : getSheet().getSlideShow().getTableStyles().getStyles()) {
                if (next.getStyleId().equals(tableStyleId)) {
                    return next;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void updateRowColIndexes() {
        Iterator<XSLFTableRow> it = iterator();
        int i = 0;
        while (it.hasNext()) {
            Iterator<XSLFTableCell> it2 = it.next().iterator();
            int i2 = 0;
            while (it2.hasNext()) {
                it2.next().setRowColIndex(i, i2);
                i2++;
            }
            i++;
        }
    }

    public void updateCellAnchor() {
        int i;
        int numberOfRows = getNumberOfRows();
        int numberOfColumns = getNumberOfColumns();
        double[] dArr = new double[numberOfColumns];
        double[] dArr2 = new double[numberOfRows];
        for (int i2 = 0; i2 < numberOfRows; i2++) {
            dArr2[i2] = getRowHeight(i2);
        }
        for (int i3 = 0; i3 < numberOfColumns; i3++) {
            dArr[i3] = getColumnWidth(i3);
        }
        Rectangle2D anchor = getAnchor();
        DrawFactory instance = DrawFactory.getInstance((Graphics2D) null);
        double y = anchor.getY();
        double x = anchor.getX();
        int i4 = 0;
        while (i4 < numberOfRows) {
            double d = 0.0d;
            int i5 = 0;
            while (i5 < numberOfColumns) {
                XSLFTableCell cell = getCell(i4, i5);
                double d2 = y;
                if (cell != null && cell.getGridSpan() == 1 && cell.getRowSpan() == 1) {
                    cell.setAnchor(new Rectangle2D.Double(0.0d, 0.0d, dArr[i5], 0.0d));
                    d = Math.max(d, instance.getDrawable((TextShape<?, ?>) cell).getTextHeight());
                }
                i5++;
                y = d2;
            }
            dArr2[i4] = Math.max(dArr2[i4], d);
            i4++;
            y = y;
        }
        double d3 = y;
        for (int i6 = 0; i6 < numberOfRows; i6++) {
            x = anchor.getX();
            for (int i7 = 0; i7 < numberOfColumns; i7++) {
                Rectangle2D.Double doubleR = new Rectangle2D.Double(x, y, dArr[i7], dArr2[i6]);
                XSLFTableCell cell2 = getCell(i6, i7);
                if (cell2 != null) {
                    cell2.setAnchor(doubleR);
                    x += dArr[i7] + 2.0d;
                }
            }
            y += dArr2[i6] + 2.0d;
        }
        for (int i8 = 0; i8 < numberOfRows; i8++) {
            int i9 = 0;
            while (i9 < numberOfColumns) {
                XSLFTableCell cell3 = getCell(i8, i9);
                if (cell3 == null) {
                    i = numberOfRows;
                } else {
                    Rectangle2D anchor2 = cell3.getAnchor();
                    int i10 = i9 + 1;
                    while (i10 < cell3.getGridSpan() + i9) {
                        XSLFTableCell cell4 = getCell(i8, i10);
                        int i11 = numberOfRows;
                        if (cell4.getGridSpan() != 1 || cell4.getRowSpan() != 1) {
                            LOG.warn("invalid table span - rendering result is probably wrong");
                        }
                        anchor2.add(cell4.getAnchor());
                        i10++;
                        numberOfRows = i11;
                    }
                    i = numberOfRows;
                    for (int i12 = i8 + 1; i12 < cell3.getRowSpan() + i8; i12++) {
                        XSLFTableCell cell5 = getCell(i12, i9);
                        if (cell5.getGridSpan() != 1 || cell5.getRowSpan() != 1) {
                            LOG.warn("invalid table span - rendering result is probably wrong");
                        }
                        anchor2.add(cell5.getAnchor());
                    }
                    cell3.setAnchor(anchor2);
                }
                i9++;
                numberOfRows = i;
            }
            int i13 = numberOfRows;
        }
        setAnchor(new Rectangle2D.Double(anchor.getX(), anchor.getY(), x - anchor.getX(), y - anchor.getY()));
    }
}
