package org.apache.poi.xslf.usermodel;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import org.apache.poi.sl.draw.DrawPaint;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.StrokeStyle;
import org.apache.poi.sl.usermodel.TableCell;
import org.apache.poi.sl.usermodel.TextShape;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.apache.poi.xslf.usermodel.XSLFPropertiesDelegate;
import org.apache.poi.xslf.usermodel.XSLFTableStyle;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontReference;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTable;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCell;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTablePartStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STCompoundLine;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineCap;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndLength;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndType;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndWidth;
import org.openxmlformats.schemas.drawingml.x2006.main.STPenAlignment;
import org.openxmlformats.schemas.drawingml.x2006.main.STPresetLineDashVal;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAnchoringType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextVerticalType;

public class XSLFTableCell extends XSLFTextShape implements TableCell<XSLFShape, XSLFTextParagraph> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private CTTableCellProperties _tcPr;
    private Rectangle2D anchor;
    private int col;
    private int row;
    private final XSLFTable table;

    XSLFTableCell(CTTableCell cTTableCell, XSLFTable xSLFTable) {
        super(cTTableCell, xSLFTable.getSheet());
        this.table = xSLFTable;
    }

    /* access modifiers changed from: protected */
    public CTTextBody getTextBody(boolean z) {
        CTTableCell cell = getCell();
        CTTextBody txBody = cell.getTxBody();
        if (txBody != null || !z) {
            return txBody;
        }
        cell.setTxBody(new XDDFTextBody(this).getXmlObject());
        return cell.getTxBody();
    }

    static CTTableCell prototype() {
        CTTableCell newInstance = CTTableCell.Factory.newInstance();
        CTTableCellProperties addNewTcPr = newInstance.addNewTcPr();
        addNewTcPr.addNewLnL().addNewNoFill();
        addNewTcPr.addNewLnR().addNewNoFill();
        addNewTcPr.addNewLnT().addNewNoFill();
        addNewTcPr.addNewLnB().addNewNoFill();
        return newInstance;
    }

    /* access modifiers changed from: protected */
    public CTTableCellProperties getCellProperties(boolean z) {
        if (this._tcPr == null) {
            CTTableCell cell = getCell();
            CTTableCellProperties tcPr = cell.getTcPr();
            this._tcPr = tcPr;
            if (tcPr == null && z) {
                this._tcPr = cell.addNewTcPr();
            }
        }
        return this._tcPr;
    }

    public void setLeftInset(double d) {
        getCellProperties(true).setMarL(Integer.valueOf(Units.toEMU(d)));
    }

    public void setRightInset(double d) {
        getCellProperties(true).setMarR(Integer.valueOf(Units.toEMU(d)));
    }

    public void setTopInset(double d) {
        getCellProperties(true).setMarT(Integer.valueOf(Units.toEMU(d)));
    }

    public void setBottomInset(double d) {
        getCellProperties(true).setMarB(Integer.valueOf(Units.toEMU(d)));
    }

    private CTLineProperties getCTLine(TableCell.BorderEdge borderEdge, boolean z) {
        if (borderEdge != null) {
            CTTableCellProperties cellProperties = getCellProperties(z);
            if (cellProperties == null) {
                return null;
            }
            int i = AnonymousClass2.$SwitchMap$org$apache$poi$sl$usermodel$TableCell$BorderEdge[borderEdge.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            return null;
                        }
                        if (cellProperties.isSetLnR()) {
                            return cellProperties.getLnR();
                        }
                        if (z) {
                            return cellProperties.addNewLnR();
                        }
                        return null;
                    } else if (cellProperties.isSetLnT()) {
                        return cellProperties.getLnT();
                    } else {
                        if (z) {
                            return cellProperties.addNewLnT();
                        }
                        return null;
                    }
                } else if (cellProperties.isSetLnL()) {
                    return cellProperties.getLnL();
                } else {
                    if (z) {
                        return cellProperties.addNewLnL();
                    }
                    return null;
                }
            } else if (cellProperties.isSetLnB()) {
                return cellProperties.getLnB();
            } else {
                if (z) {
                    return cellProperties.addNewLnB();
                }
                return null;
            }
        } else {
            throw new IllegalArgumentException("BorderEdge needs to be specified.");
        }
    }

    public XDDFLineProperties getBorderProperties(TableCell.BorderEdge borderEdge) {
        CTLineProperties cTLine = getCTLine(borderEdge, false);
        if (cTLine == null) {
            return null;
        }
        return new XDDFLineProperties(cTLine);
    }

    public void setBorderProperties(TableCell.BorderEdge borderEdge, XDDFLineProperties xDDFLineProperties) {
        CTLineProperties cTLine = getCTLine(borderEdge, true);
        if (cTLine != null) {
            cTLine.set(xDDFLineProperties.getXmlObject().copy());
        }
    }

    public void removeBorder(TableCell.BorderEdge borderEdge) {
        CTTableCellProperties cellProperties = getCellProperties(false);
        if (cellProperties != null) {
            int i = AnonymousClass2.$SwitchMap$org$apache$poi$sl$usermodel$TableCell$BorderEdge[borderEdge.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            throw new IllegalArgumentException();
                        } else if (cellProperties.isSetLnR()) {
                            cellProperties.unsetLnR();
                        }
                    } else if (cellProperties.isSetLnT()) {
                        cellProperties.unsetLnT();
                    }
                } else if (cellProperties.isSetLnL()) {
                    cellProperties.unsetLnL();
                }
            } else if (cellProperties.isSetLnB()) {
                cellProperties.unsetLnB();
            }
        }
    }

    public StrokeStyle getBorderStyle(final TableCell.BorderEdge borderEdge) {
        final Double borderWidth = getBorderWidth(borderEdge);
        if (borderWidth == null) {
            return null;
        }
        return new StrokeStyle() {
            public PaintStyle getPaint() {
                return DrawPaint.createSolidPaint(XSLFTableCell.this.getBorderColor(borderEdge));
            }

            public StrokeStyle.LineCap getLineCap() {
                return XSLFTableCell.this.getBorderCap(borderEdge);
            }

            public StrokeStyle.LineDash getLineDash() {
                return XSLFTableCell.this.getBorderDash(borderEdge);
            }

            public StrokeStyle.LineCompound getLineCompound() {
                return XSLFTableCell.this.getBorderCompound(borderEdge);
            }

            public double getLineWidth() {
                return borderWidth.doubleValue();
            }
        };
    }

    public void setBorderStyle(TableCell.BorderEdge borderEdge, StrokeStyle strokeStyle) {
        if (strokeStyle != null) {
            StrokeStyle.LineCap lineCap = strokeStyle.getLineCap();
            if (lineCap != null) {
                setBorderCap(borderEdge, lineCap);
            }
            StrokeStyle.LineCompound lineCompound = strokeStyle.getLineCompound();
            if (lineCompound != null) {
                setBorderCompound(borderEdge, lineCompound);
            }
            StrokeStyle.LineDash lineDash = strokeStyle.getLineDash();
            if (lineDash != null) {
                setBorderDash(borderEdge, lineDash);
            }
            setBorderWidth(borderEdge, strokeStyle.getLineWidth());
            return;
        }
        throw new IllegalArgumentException("StrokeStyle needs to be specified.");
    }

    public Double getBorderWidth(TableCell.BorderEdge borderEdge) {
        CTLineProperties cTLine = getCTLine(borderEdge, false);
        if (cTLine == null || !cTLine.isSetW()) {
            return null;
        }
        return Double.valueOf(Units.toPoints((long) cTLine.getW()));
    }

    public void setBorderWidth(TableCell.BorderEdge borderEdge, double d) {
        CTLineProperties cTLine = getCTLine(borderEdge, true);
        if (cTLine != null) {
            cTLine.setW(Units.toEMU(d));
        }
    }

    private CTLineProperties setBorderDefaults(TableCell.BorderEdge borderEdge) {
        CTLineProperties cTLine = getCTLine(borderEdge, true);
        if (cTLine != null) {
            if (cTLine.isSetNoFill()) {
                cTLine.unsetNoFill();
            }
            if (!cTLine.isSetPrstDash()) {
                cTLine.addNewPrstDash().setVal(STPresetLineDashVal.SOLID);
            }
            if (!cTLine.isSetCmpd()) {
                cTLine.setCmpd(STCompoundLine.SNG);
            }
            if (!cTLine.isSetAlgn()) {
                cTLine.setAlgn(STPenAlignment.CTR);
            }
            if (!cTLine.isSetCap()) {
                cTLine.setCap(STLineCap.FLAT);
            }
            if (!cTLine.isSetRound()) {
                cTLine.addNewRound();
            }
            if (!cTLine.isSetHeadEnd()) {
                CTLineEndProperties addNewHeadEnd = cTLine.addNewHeadEnd();
                addNewHeadEnd.setType(STLineEndType.NONE);
                addNewHeadEnd.setW(STLineEndWidth.MED);
                addNewHeadEnd.setLen(STLineEndLength.MED);
            }
            if (!cTLine.isSetTailEnd()) {
                CTLineEndProperties addNewTailEnd = cTLine.addNewTailEnd();
                addNewTailEnd.setType(STLineEndType.NONE);
                addNewTailEnd.setW(STLineEndWidth.MED);
                addNewTailEnd.setLen(STLineEndLength.MED);
            }
            return cTLine;
        }
        throw new IllegalStateException("CTLineProperties couldn't be initialized");
    }

    public void setBorderColor(TableCell.BorderEdge borderEdge, Color color) {
        if (color != null) {
            CTSolidColorFillProperties addNewSolidFill = setBorderDefaults(borderEdge).addNewSolidFill();
            new XSLFColor(addNewSolidFill, getSheet().getTheme(), addNewSolidFill.getSchemeClr(), getSheet()).setColor(color);
            return;
        }
        throw new IllegalArgumentException("Colors need to be specified.");
    }

    public Color getBorderColor(TableCell.BorderEdge borderEdge) {
        CTLineProperties cTLine = getCTLine(borderEdge, false);
        if (cTLine == null || cTLine.isSetNoFill() || !cTLine.isSetSolidFill()) {
            return null;
        }
        CTSolidColorFillProperties solidFill = cTLine.getSolidFill();
        return new XSLFColor(solidFill, getSheet().getTheme(), solidFill.getSchemeClr(), getSheet()).getColor();
    }

    public StrokeStyle.LineCompound getBorderCompound(TableCell.BorderEdge borderEdge) {
        CTLineProperties cTLine = getCTLine(borderEdge, false);
        if (cTLine == null || cTLine.isSetNoFill() || !cTLine.isSetSolidFill() || !cTLine.isSetCmpd()) {
            return null;
        }
        return StrokeStyle.LineCompound.fromOoxmlId(cTLine.getCmpd().intValue());
    }

    public void setBorderCompound(TableCell.BorderEdge borderEdge, StrokeStyle.LineCompound lineCompound) {
        if (lineCompound != null) {
            setBorderDefaults(borderEdge).setCmpd(STCompoundLine.Enum.forInt(lineCompound.ooxmlId));
            return;
        }
        throw new IllegalArgumentException("LineCompound need to be specified.");
    }

    public StrokeStyle.LineDash getBorderDash(TableCell.BorderEdge borderEdge) {
        CTLineProperties cTLine = getCTLine(borderEdge, false);
        if (cTLine == null || cTLine.isSetNoFill() || !cTLine.isSetSolidFill() || !cTLine.isSetPrstDash()) {
            return null;
        }
        return StrokeStyle.LineDash.fromOoxmlId(cTLine.getPrstDash().getVal().intValue());
    }

    public void setBorderDash(TableCell.BorderEdge borderEdge, StrokeStyle.LineDash lineDash) {
        if (lineDash != null) {
            CTLineProperties borderDefaults = setBorderDefaults(borderEdge);
            if (!borderDefaults.isSetPrstDash()) {
                borderDefaults.addNewPrstDash();
            }
            borderDefaults.getPrstDash().setVal(STPresetLineDashVal.Enum.forInt(lineDash.ooxmlId));
            return;
        }
        throw new IllegalArgumentException("LineDash need to be specified.");
    }

    public StrokeStyle.LineCap getBorderCap(TableCell.BorderEdge borderEdge) {
        CTLineProperties cTLine = getCTLine(borderEdge, false);
        if (cTLine == null || cTLine.isSetNoFill() || !cTLine.isSetSolidFill() || !cTLine.isSetCap()) {
            return null;
        }
        return StrokeStyle.LineCap.fromOoxmlId(cTLine.getCap().intValue());
    }

    public void setBorderCap(TableCell.BorderEdge borderEdge, StrokeStyle.LineCap lineCap) {
        if (lineCap != null) {
            setBorderDefaults(borderEdge).setCap(STLineCap.Enum.forInt(lineCap.ooxmlId));
            return;
        }
        throw new IllegalArgumentException("LineCap need to be specified.");
    }

    public void setFillColor(Color color) {
        CTTableCellProperties cellProperties = getCellProperties(true);
        if (color != null) {
            CTSolidColorFillProperties solidFill = cellProperties.isSetSolidFill() ? cellProperties.getSolidFill() : cellProperties.addNewSolidFill();
            new XSLFColor(solidFill, getSheet().getTheme(), solidFill.getSchemeClr(), getSheet()).setColor(color);
        } else if (cellProperties.isSetSolidFill()) {
            cellProperties.unsetSolidFill();
        }
    }

    public Color getFillColor() {
        PaintStyle fillPaint = getFillPaint();
        if (fillPaint instanceof PaintStyle.SolidPaint) {
            return DrawPaint.applyColorTransform(((PaintStyle.SolidPaint) fillPaint).getSolidColor());
        }
        return null;
    }

    public PaintStyle getFillPaint() {
        XmlObject xmlObject;
        PaintStyle selectPaint;
        PaintStyle selectPaint2;
        XSLFSheet sheet = getSheet();
        XSLFTheme theme = sheet.getTheme();
        boolean z = getPlaceholder() != null;
        XSLFPropertiesDelegate.XSLFFillProperties fillDelegate = XSLFPropertiesDelegate.getFillDelegate(getCellProperties(false));
        if (fillDelegate != null && (selectPaint2 = selectPaint(fillDelegate, (CTSchemeColor) null, sheet.getPackagePart(), theme, z)) != null) {
            return selectPaint2;
        }
        CTTablePartStyle tablePartStyle = getTablePartStyle((XSLFTableStyle.TablePartStyle) null);
        if ((tablePartStyle != null && tablePartStyle.isSetTcStyle()) || ((tablePartStyle = getTablePartStyle(XSLFTableStyle.TablePartStyle.wholeTbl)) != null && tablePartStyle.isSetTcStyle())) {
            XMLSlideShow slideShow = sheet.getSlideShow();
            CTTableStyleCellStyle tcStyle = tablePartStyle.getTcStyle();
            if (tcStyle.isSetFill()) {
                xmlObject = tcStyle.getFill();
            } else if (tcStyle.isSetFillRef()) {
                xmlObject = tcStyle.getFillRef();
            }
            XSLFPropertiesDelegate.XSLFFillProperties fillDelegate2 = XSLFPropertiesDelegate.getFillDelegate(xmlObject);
            if (fillDelegate2 == null || (selectPaint = selectPaint(fillDelegate2, (CTSchemeColor) null, slideShow.getPackagePart(), theme, z)) == null) {
                return null;
            }
            return selectPaint;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public CTTablePartStyle getTablePartStyle(XSLFTableStyle.TablePartStyle tablePartStyle) {
        CTTable cTTable = this.table.getCTTable();
        if (!cTTable.isSetTblPr()) {
            return null;
        }
        CTTableProperties tblPr = cTTable.getTblPr();
        boolean z = false;
        boolean z2 = tblPr.isSetBandRow() && tblPr.getBandRow();
        int i = (!tblPr.isSetFirstRow() || !tblPr.getFirstRow()) ? 0 : 1;
        boolean z3 = tblPr.isSetLastRow() && tblPr.getLastRow();
        boolean z4 = tblPr.isSetBandCol() && tblPr.getBandCol();
        int i2 = (!tblPr.isSetFirstCol() || !tblPr.getFirstCol()) ? 0 : 1;
        if (tblPr.isSetLastCol() && tblPr.getLastCol()) {
            z = true;
        }
        if (tablePartStyle == null) {
            int i3 = this.row;
            if (i3 == 0 && i != 0) {
                tablePartStyle = XSLFTableStyle.TablePartStyle.firstRow;
            } else if (i3 != this.table.getNumberOfRows() - 1 || !z3) {
                int i4 = this.col;
                if (i4 == 0 && i2 != 0) {
                    tablePartStyle = XSLFTableStyle.TablePartStyle.firstCol;
                } else if (i4 != this.table.getNumberOfColumns() - 1 || !z) {
                    tablePartStyle = XSLFTableStyle.TablePartStyle.wholeTbl;
                    int i5 = this.row + i;
                    int i6 = this.col + i2;
                    if (z2 && (i5 & 1) == 0) {
                        tablePartStyle = XSLFTableStyle.TablePartStyle.band1H;
                    } else if (z4 && (i6 & 1) == 0) {
                        tablePartStyle = XSLFTableStyle.TablePartStyle.band1V;
                    }
                } else {
                    tablePartStyle = XSLFTableStyle.TablePartStyle.lastCol;
                }
            } else {
                tablePartStyle = XSLFTableStyle.TablePartStyle.lastRow;
            }
        }
        XSLFTableStyle tableStyle = this.table.getTableStyle();
        if (tableStyle == null) {
            return null;
        }
        CTTablePartStyle tablePartStyle2 = tableStyle.getTablePartStyle(tablePartStyle);
        return tablePartStyle2 == null ? tableStyle.getTablePartStyle(XSLFTableStyle.TablePartStyle.wholeTbl) : tablePartStyle2;
    }

    /* access modifiers changed from: package-private */
    public void setGridSpan(int i) {
        getCell().setGridSpan(i);
    }

    public int getGridSpan() {
        CTTableCell cell = getCell();
        if (cell.isSetGridSpan()) {
            return cell.getGridSpan();
        }
        return 1;
    }

    /* access modifiers changed from: package-private */
    public void setRowSpan(int i) {
        getCell().setRowSpan(i);
    }

    public int getRowSpan() {
        CTTableCell cell = getCell();
        if (cell.isSetRowSpan()) {
            return cell.getRowSpan();
        }
        return 1;
    }

    /* access modifiers changed from: package-private */
    public void setHMerge() {
        getCell().setHMerge(true);
    }

    /* access modifiers changed from: package-private */
    public void setVMerge() {
        getCell().setVMerge(true);
    }

    public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
        CTTableCellProperties cellProperties = getCellProperties(true);
        if (verticalAlignment != null) {
            cellProperties.setAnchor(STTextAnchoringType.Enum.forInt(verticalAlignment.ordinal() + 1));
        } else if (cellProperties.isSetAnchor()) {
            cellProperties.unsetAnchor();
        }
    }

    public VerticalAlignment getVerticalAlignment() {
        CTTableCellProperties cellProperties = getCellProperties(false);
        VerticalAlignment verticalAlignment = VerticalAlignment.TOP;
        if (cellProperties == null || !cellProperties.isSetAnchor()) {
            return verticalAlignment;
        }
        return VerticalAlignment.values()[cellProperties.getAnchor().intValue() - 1];
    }

    public void setTextDirection(TextShape.TextDirection textDirection) {
        STTextVerticalType.Enum enumR;
        CTTableCellProperties cellProperties = getCellProperties(true);
        if (textDirection != null) {
            int i = AnonymousClass2.$SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextDirection[textDirection.ordinal()];
            if (i == 2) {
                enumR = STTextVerticalType.VERT;
            } else if (i == 3) {
                enumR = STTextVerticalType.VERT_270;
            } else if (i != 4) {
                enumR = STTextVerticalType.HORZ;
            } else {
                enumR = STTextVerticalType.WORD_ART_VERT;
            }
            cellProperties.setVert(enumR);
        } else if (cellProperties.isSetVert()) {
            cellProperties.unsetVert();
        }
    }

    /* renamed from: org.apache.poi.xslf.usermodel.XSLFTableCell$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$TableCell$BorderEdge;
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextDirection;

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|17|18|19|20|21|22|(3:23|24|26)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(21:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|26) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0044 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0058 */
        static {
            /*
                org.apache.poi.sl.usermodel.TextShape$TextDirection[] r0 = org.apache.poi.sl.usermodel.TextShape.TextDirection.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextDirection = r0
                r1 = 1
                org.apache.poi.sl.usermodel.TextShape$TextDirection r2 = org.apache.poi.sl.usermodel.TextShape.TextDirection.HORIZONTAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextDirection     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.sl.usermodel.TextShape$TextDirection r3 = org.apache.poi.sl.usermodel.TextShape.TextDirection.VERTICAL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextDirection     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.sl.usermodel.TextShape$TextDirection r4 = org.apache.poi.sl.usermodel.TextShape.TextDirection.VERTICAL_270     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextDirection     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.sl.usermodel.TextShape$TextDirection r5 = org.apache.poi.sl.usermodel.TextShape.TextDirection.STACKED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                org.apache.poi.sl.usermodel.TableCell$BorderEdge[] r4 = org.apache.poi.sl.usermodel.TableCell.BorderEdge.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$org$apache$poi$sl$usermodel$TableCell$BorderEdge = r4
                org.apache.poi.sl.usermodel.TableCell$BorderEdge r5 = org.apache.poi.sl.usermodel.TableCell.BorderEdge.bottom     // Catch:{ NoSuchFieldError -> 0x0044 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0044 }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x0044 }
            L_0x0044:
                int[] r1 = $SwitchMap$org$apache$poi$sl$usermodel$TableCell$BorderEdge     // Catch:{ NoSuchFieldError -> 0x004e }
                org.apache.poi.sl.usermodel.TableCell$BorderEdge r4 = org.apache.poi.sl.usermodel.TableCell.BorderEdge.left     // Catch:{ NoSuchFieldError -> 0x004e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004e }
                r1[r4] = r0     // Catch:{ NoSuchFieldError -> 0x004e }
            L_0x004e:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$TableCell$BorderEdge     // Catch:{ NoSuchFieldError -> 0x0058 }
                org.apache.poi.sl.usermodel.TableCell$BorderEdge r1 = org.apache.poi.sl.usermodel.TableCell.BorderEdge.top     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$TableCell$BorderEdge     // Catch:{ NoSuchFieldError -> 0x0062 }
                org.apache.poi.sl.usermodel.TableCell$BorderEdge r1 = org.apache.poi.sl.usermodel.TableCell.BorderEdge.right     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFTableCell.AnonymousClass2.<clinit>():void");
        }
    }

    public TextShape.TextDirection getTextDirection() {
        STTextVerticalType.Enum enumR;
        CTTableCellProperties cellProperties = getCellProperties(false);
        if (cellProperties == null || !cellProperties.isSetVert()) {
            enumR = STTextVerticalType.HORZ;
        } else {
            enumR = cellProperties.getVert();
        }
        switch (enumR.intValue()) {
            case 2:
            case 5:
            case 6:
                return TextShape.TextDirection.VERTICAL;
            case 3:
                return TextShape.TextDirection.VERTICAL_270;
            case 4:
            case 7:
                return TextShape.TextDirection.STACKED;
            default:
                return TextShape.TextDirection.HORIZONTAL;
        }
    }

    private CTTableCell getCell() {
        return (CTTableCell) getXmlObject();
    }

    /* access modifiers changed from: package-private */
    public void setRowColIndex(int i, int i2) {
        this.row = i;
        this.col = i2;
    }

    /* access modifiers changed from: protected */
    public CTTransform2D getXfrm() {
        Rectangle2D anchor2 = getAnchor();
        CTTransform2D newInstance = CTTransform2D.Factory.newInstance();
        CTPoint2D addNewOff = newInstance.addNewOff();
        addNewOff.setX(Integer.valueOf(Units.toEMU(anchor2.getX())));
        addNewOff.setY(Integer.valueOf(Units.toEMU(anchor2.getY())));
        CTPositiveSize2D addNewExt = newInstance.addNewExt();
        addNewExt.setCx((long) Units.toEMU(anchor2.getWidth()));
        addNewExt.setCy((long) Units.toEMU(anchor2.getHeight()));
        return newInstance;
    }

    public void setAnchor(Rectangle2D rectangle2D) {
        Rectangle2D rectangle2D2 = this.anchor;
        if (rectangle2D2 == null) {
            this.anchor = (Rectangle2D) rectangle2D.clone();
        } else {
            rectangle2D2.setRect(rectangle2D);
        }
    }

    public Rectangle2D getAnchor() {
        if (this.anchor == null) {
            this.table.updateCellAnchor();
        }
        return this.anchor;
    }

    public boolean isMerged() {
        CTTableCell cell = getCell();
        return (cell.isSetHMerge() && cell.getHMerge()) || (cell.isSetVMerge() && cell.getVMerge());
    }

    /* access modifiers changed from: protected */
    public XSLFCellTextParagraph newTextParagraph(CTTextParagraph cTTextParagraph) {
        return new XSLFCellTextParagraph(cTTextParagraph, this);
    }

    /* access modifiers changed from: protected */
    public XmlObject getShapeProperties() {
        return getCellProperties(false);
    }

    private final class XSLFCellTextParagraph extends XSLFTextParagraph {
        private XSLFCellTextParagraph(CTTextParagraph cTTextParagraph, XSLFTextShape xSLFTextShape) {
            super(cTTextParagraph, xSLFTextShape);
        }

        /* access modifiers changed from: protected */
        public XSLFCellTextRun newTextRun(XmlObject xmlObject) {
            return new XSLFCellTextRun(xmlObject, this);
        }
    }

    private final class XSLFCellTextRun extends XSLFTextRun {
        private XSLFCellTextRun(XmlObject xmlObject, XSLFTextParagraph xSLFTextParagraph) {
            super(xmlObject, xSLFTextParagraph);
        }

        public PaintStyle getFontColor() {
            CTTableStyleTextStyle textStyle = getTextStyle();
            if (textStyle == null) {
                return super.getFontColor();
            }
            CTFontReference fontRef = textStyle.getFontRef();
            return DrawPaint.createSolidPaint(new XSLFColor(textStyle, XSLFTableCell.this.getSheet().getTheme(), fontRef != null ? fontRef.getSchemeClr() : null, XSLFTableCell.this.getSheet()).getColorStyle());
        }

        public boolean isBold() {
            CTTableStyleTextStyle textStyle = getTextStyle();
            if (textStyle == null) {
                return super.isBold();
            }
            return textStyle.isSetB() && textStyle.getB().intValue() == 1;
        }

        public boolean isItalic() {
            CTTableStyleTextStyle textStyle = getTextStyle();
            if (textStyle == null) {
                return super.isItalic();
            }
            return textStyle.isSetI() && textStyle.getI().intValue() == 1;
        }

        private CTTableStyleTextStyle getTextStyle() {
            CTTablePartStyle access$200 = XSLFTableCell.this.getTablePartStyle((XSLFTableStyle.TablePartStyle) null);
            if (access$200 == null || !access$200.isSetTcTxStyle()) {
                access$200 = XSLFTableCell.this.getTablePartStyle(XSLFTableStyle.TablePartStyle.wholeTbl);
            }
            if (access$200 == null) {
                return null;
            }
            return access$200.getTcTxStyle();
        }
    }
}
