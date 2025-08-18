package org.apache.poi.xssf.usermodel;

import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;

public class XSSFClientAnchor extends XSSFAnchor implements ClientAnchor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final CTMarker EMPTY_MARKER = CTMarker.Factory.newInstance();
    private ClientAnchor.AnchorType anchorType;
    private CTMarker cell1;
    private CTMarker cell2;
    private CTPoint2D position;
    private XSSFSheet sheet;
    private CTPositiveSize2D size;

    public int hashCode() {
        return 42;
    }

    public XSSFClientAnchor() {
        this(0, 0, 0, 0, 0, 0, 0, 0);
    }

    public XSSFClientAnchor(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.anchorType = ClientAnchor.AnchorType.MOVE_AND_RESIZE;
        CTMarker newInstance = CTMarker.Factory.newInstance();
        this.cell1 = newInstance;
        newInstance.setCol(i5);
        this.cell1.setColOff(Integer.valueOf(i));
        this.cell1.setRow(i6);
        this.cell1.setRowOff(Integer.valueOf(i2));
        CTMarker newInstance2 = CTMarker.Factory.newInstance();
        this.cell2 = newInstance2;
        newInstance2.setCol(i7);
        this.cell2.setColOff(Integer.valueOf(i3));
        this.cell2.setRow(i8);
        this.cell2.setRowOff(Integer.valueOf(i4));
    }

    protected XSSFClientAnchor(CTMarker cTMarker, CTMarker cTMarker2) {
        this.anchorType = ClientAnchor.AnchorType.MOVE_AND_RESIZE;
        this.cell1 = cTMarker;
        this.cell2 = cTMarker2;
    }

    protected XSSFClientAnchor(XSSFSheet xSSFSheet, CTMarker cTMarker, CTPositiveSize2D cTPositiveSize2D) {
        this.anchorType = ClientAnchor.AnchorType.MOVE_DONT_RESIZE;
        this.sheet = xSSFSheet;
        this.size = cTPositiveSize2D;
        this.cell1 = cTMarker;
    }

    protected XSSFClientAnchor(XSSFSheet xSSFSheet, CTPoint2D cTPoint2D, CTPositiveSize2D cTPositiveSize2D) {
        this.anchorType = ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE;
        this.sheet = xSSFSheet;
        this.position = cTPoint2D;
        this.size = cTPositiveSize2D;
    }

    private CTMarker calcCell(CTMarker cTMarker, long j, long j2) {
        CTMarker newInstance = CTMarker.Factory.newInstance();
        int row = cTMarker.getRow();
        int col = cTMarker.getCol();
        int columnWidthToEMU = Units.columnWidthToEMU(this.sheet.getColumnWidth(col));
        long parseLength = ((long) columnWidthToEMU) - POIXMLUnits.parseLength(cTMarker.xgetColOff());
        while (parseLength < j) {
            col++;
            columnWidthToEMU = Units.columnWidthToEMU(this.sheet.getColumnWidth(col));
            parseLength += (long) columnWidthToEMU;
        }
        newInstance.setCol(col);
        newInstance.setColOff(Long.valueOf(((long) columnWidthToEMU) - (parseLength - j)));
        int emu = Units.toEMU((double) getRowHeight(this.sheet, row));
        long parseLength2 = ((long) emu) - POIXMLUnits.parseLength(cTMarker.xgetRowOff());
        while (parseLength2 < j2) {
            row++;
            emu = Units.toEMU((double) getRowHeight(this.sheet, row));
            parseLength2 += (long) emu;
        }
        newInstance.setRow(row);
        newInstance.setRowOff(Long.valueOf(((long) emu) - (parseLength2 - j2)));
        return newInstance;
    }

    private static float getRowHeight(XSSFSheet xSSFSheet, int i) {
        XSSFRow row = xSSFSheet.getRow(i);
        return row == null ? xSSFSheet.getDefaultRowHeightInPoints() : row.getHeightInPoints();
    }

    private CTMarker getCell1() {
        CTMarker cTMarker = this.cell1;
        if (cTMarker != null) {
            return cTMarker;
        }
        return calcCell(EMPTY_MARKER, POIXMLUnits.parseLength(this.position.xgetX()), POIXMLUnits.parseLength(this.position.xgetY()));
    }

    private CTMarker getCell2() {
        CTMarker cTMarker = this.cell2;
        if (cTMarker != null) {
            return cTMarker;
        }
        return calcCell(getCell1(), this.size.getCx(), this.size.getCy());
    }

    public short getCol1() {
        return (short) getCell1().getCol();
    }

    public void setCol1(int i) {
        this.cell1.setCol(i);
    }

    public short getCol2() {
        return (short) getCell2().getCol();
    }

    public void setCol2(int i) {
        this.cell2.setCol(i);
    }

    public int getRow1() {
        return getCell1().getRow();
    }

    public void setRow1(int i) {
        this.cell1.setRow(i);
    }

    public int getRow2() {
        return getCell2().getRow();
    }

    public void setRow2(int i) {
        this.cell2.setRow(i);
    }

    public int getDx1() {
        return Math.toIntExact(POIXMLUnits.parseLength(getCell1().xgetColOff()));
    }

    public void setDx1(int i) {
        this.cell1.setColOff(Integer.valueOf(i));
    }

    public int getDy1() {
        return Math.toIntExact(POIXMLUnits.parseLength(getCell1().xgetRowOff()));
    }

    public void setDy1(int i) {
        this.cell1.setRowOff(Integer.valueOf(i));
    }

    public int getDy2() {
        return Math.toIntExact(POIXMLUnits.parseLength(getCell2().xgetRowOff()));
    }

    public void setDy2(int i) {
        this.cell2.setRowOff(Integer.valueOf(i));
    }

    public int getDx2() {
        return Math.toIntExact(POIXMLUnits.parseLength(getCell2().xgetColOff()));
    }

    public void setDx2(int i) {
        this.cell2.setColOff(Integer.valueOf(i));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof XSSFClientAnchor)) {
            return false;
        }
        XSSFClientAnchor xSSFClientAnchor = (XSSFClientAnchor) obj;
        if (getDx1() == xSSFClientAnchor.getDx1() && getDx2() == xSSFClientAnchor.getDx2() && getDy1() == xSSFClientAnchor.getDy1() && getDy2() == xSSFClientAnchor.getDy2() && getCol1() == xSSFClientAnchor.getCol1() && getCol2() == xSSFClientAnchor.getCol2() && getRow1() == xSSFClientAnchor.getRow1() && getRow2() == xSSFClientAnchor.getRow2()) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "from : " + getCell1() + "; to: " + getCell2();
    }

    @Internal
    public CTMarker getFrom() {
        return getCell1();
    }

    /* access modifiers changed from: protected */
    public void setFrom(CTMarker cTMarker) {
        this.cell1 = cTMarker;
    }

    @Internal
    public CTMarker getTo() {
        return getCell2();
    }

    /* access modifiers changed from: protected */
    public void setTo(CTMarker cTMarker) {
        this.cell2 = cTMarker;
    }

    public CTPoint2D getPosition() {
        return this.position;
    }

    public void setPosition(CTPoint2D cTPoint2D) {
        this.position = cTPoint2D;
    }

    public CTPositiveSize2D getSize() {
        return this.size;
    }

    public void setSize(CTPositiveSize2D cTPositiveSize2D) {
        this.size = cTPositiveSize2D;
    }

    public void setAnchorType(ClientAnchor.AnchorType anchorType2) {
        this.anchorType = anchorType2;
    }

    public ClientAnchor.AnchorType getAnchorType() {
        return this.anchorType;
    }

    public boolean isSet() {
        CTMarker cell12 = getCell1();
        CTMarker cell22 = getCell2();
        return (cell12.getCol() == 0 && cell22.getCol() == 0 && cell12.getRow() == 0 && cell22.getRow() == 0) ? false : true;
    }
}
