package org.apache.poi.sl.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import org.apache.poi.sl.usermodel.GroupShape;
import org.apache.poi.sl.usermodel.StrokeStyle;
import org.apache.poi.sl.usermodel.TableCell;
import org.apache.poi.sl.usermodel.TableShape;
import org.apache.poi.sl.usermodel.TextShape;
import org.apache.poi.util.Internal;

public class DrawTableShape extends DrawShape {
    @Internal
    public static final int borderSize = 2;

    public DrawTableShape(TableShape<?, ?> tableShape) {
        super(tableShape);
    }

    /* access modifiers changed from: protected */
    public Drawable getGroupShape(Graphics2D graphics2D) {
        if (this.shape instanceof GroupShape) {
            return DrawFactory.getInstance(graphics2D).getDrawable((GroupShape<?, ?>) (GroupShape) this.shape);
        }
        return null;
    }

    public void applyTransform(Graphics2D graphics2D) {
        Drawable groupShape = getGroupShape(graphics2D);
        if (groupShape != null) {
            groupShape.applyTransform(graphics2D);
        } else {
            super.applyTransform(graphics2D);
        }
    }

    public void draw(Graphics2D graphics2D) {
        Line2D.Double doubleR;
        Graphics2D graphics2D2 = graphics2D;
        Drawable groupShape = getGroupShape(graphics2D);
        if (groupShape != null) {
            groupShape.draw(graphics2D2);
            return;
        }
        TableShape shape = getShape();
        DrawPaint paint = DrawFactory.getInstance(graphics2D).getPaint(shape);
        int numberOfRows = shape.getNumberOfRows();
        int numberOfColumns = shape.getNumberOfColumns();
        for (int i = 0; i < numberOfRows; i++) {
            for (int i2 = 0; i2 < numberOfColumns; i2++) {
                TableCell cell = shape.getCell(i, i2);
                if (cell != null && !cell.isMerged()) {
                    graphics2D2.setPaint(paint.getPaint(graphics2D2, cell.getFillStyle().getPaint()));
                    Rectangle2D anchor = cell.getAnchor();
                    DrawPaint.fillPaintWorkaround(graphics2D2, anchor);
                    for (TableCell.BorderEdge borderEdge : TableCell.BorderEdge.values()) {
                        StrokeStyle borderStyle = cell.getBorderStyle(borderEdge);
                        if (borderStyle != null) {
                            graphics2D2.setStroke(getStroke(borderStyle));
                            graphics2D2.setPaint(paint.getPaint(graphics2D2, borderStyle.getPaint()));
                            double x = anchor.getX();
                            double y = anchor.getY();
                            double width = anchor.getWidth();
                            double height = anchor.getHeight();
                            int i3 = AnonymousClass1.$SwitchMap$org$apache$poi$sl$usermodel$TableCell$BorderEdge[borderEdge.ordinal()];
                            if (i3 == 2) {
                                doubleR = new Line2D.Double(x, y, x, y + height + 2.0d);
                            } else if (i3 == 3) {
                                double d = x + width;
                                doubleR = new Line2D.Double(d, y, d, y + height + 2.0d);
                            } else if (i3 != 4) {
                                double d2 = y + height;
                                doubleR = new Line2D.Double(x - 2.0d, d2, x + width + 2.0d, d2);
                            } else {
                                doubleR = new Line2D.Double(x - 2.0d, y, x + width + 2.0d, y);
                            }
                            graphics2D2.draw(doubleR);
                        }
                    }
                }
            }
        }
        drawContent(graphics2D);
    }

    /* renamed from: org.apache.poi.sl.draw.DrawTableShape$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$TableCell$BorderEdge;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                org.apache.poi.sl.usermodel.TableCell$BorderEdge[] r0 = org.apache.poi.sl.usermodel.TableCell.BorderEdge.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$sl$usermodel$TableCell$BorderEdge = r0
                org.apache.poi.sl.usermodel.TableCell$BorderEdge r1 = org.apache.poi.sl.usermodel.TableCell.BorderEdge.bottom     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$TableCell$BorderEdge     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.sl.usermodel.TableCell$BorderEdge r1 = org.apache.poi.sl.usermodel.TableCell.BorderEdge.left     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$TableCell$BorderEdge     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.sl.usermodel.TableCell$BorderEdge r1 = org.apache.poi.sl.usermodel.TableCell.BorderEdge.right     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$TableCell$BorderEdge     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.sl.usermodel.TableCell$BorderEdge r1 = org.apache.poi.sl.usermodel.TableCell.BorderEdge.top     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.DrawTableShape.AnonymousClass1.<clinit>():void");
        }
    }

    public void drawContent(Graphics2D graphics2D) {
        Drawable groupShape = getGroupShape(graphics2D);
        if (groupShape != null) {
            groupShape.drawContent(graphics2D);
            return;
        }
        TableShape shape = getShape();
        DrawFactory instance = DrawFactory.getInstance(graphics2D);
        int numberOfRows = shape.getNumberOfRows();
        int numberOfColumns = shape.getNumberOfColumns();
        for (int i = 0; i < numberOfRows; i++) {
            for (int i2 = 0; i2 < numberOfColumns; i2++) {
                TableCell cell = shape.getCell(i, i2);
                if (cell != null) {
                    instance.getDrawable((TextShape<?, ?>) cell).drawContent(graphics2D);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public TableShape<?, ?> getShape() {
        return (TableShape) this.shape;
    }

    public void setAllBorders(Object... objArr) {
        TableShape shape = getShape();
        int numberOfRows = shape.getNumberOfRows();
        int numberOfColumns = shape.getNumberOfColumns();
        TableCell.BorderEdge[] borderEdgeArr = {TableCell.BorderEdge.top, TableCell.BorderEdge.left, null, null};
        int i = 0;
        while (i < numberOfRows) {
            int i2 = 0;
            while (i2 < numberOfColumns) {
                borderEdgeArr[2] = i2 == numberOfColumns + -1 ? TableCell.BorderEdge.right : null;
                borderEdgeArr[3] = i == numberOfRows + -1 ? TableCell.BorderEdge.bottom : null;
                setEdges(shape.getCell(i, i2), borderEdgeArr, objArr);
                i2++;
            }
            i++;
        }
    }

    public void setOutsideBorders(Object... objArr) {
        if (objArr.length != 0) {
            TableShape shape = getShape();
            int numberOfRows = shape.getNumberOfRows();
            int numberOfColumns = shape.getNumberOfColumns();
            TableCell.BorderEdge[] borderEdgeArr = new TableCell.BorderEdge[4];
            int i = 0;
            while (i < numberOfRows) {
                int i2 = 0;
                while (i2 < numberOfColumns) {
                    TableCell.BorderEdge borderEdge = null;
                    borderEdgeArr[0] = i2 == 0 ? TableCell.BorderEdge.left : null;
                    borderEdgeArr[1] = i2 == numberOfColumns + -1 ? TableCell.BorderEdge.right : null;
                    borderEdgeArr[2] = i == 0 ? TableCell.BorderEdge.top : null;
                    if (i == numberOfRows - 1) {
                        borderEdge = TableCell.BorderEdge.bottom;
                    }
                    borderEdgeArr[3] = borderEdge;
                    setEdges(shape.getCell(i, i2), borderEdgeArr, objArr);
                    i2++;
                }
                i++;
            }
        }
    }

    public void setInsideBorders(Object... objArr) {
        if (objArr.length != 0) {
            TableShape shape = getShape();
            int numberOfRows = shape.getNumberOfRows();
            int numberOfColumns = shape.getNumberOfColumns();
            TableCell.BorderEdge[] borderEdgeArr = new TableCell.BorderEdge[2];
            for (int i = 0; i < numberOfRows; i++) {
                int i2 = 0;
                while (i2 < numberOfColumns) {
                    TableCell.BorderEdge borderEdge = null;
                    borderEdgeArr[0] = (i2 <= 0 || i2 >= numberOfColumns + -1) ? null : TableCell.BorderEdge.right;
                    if (i > 0 && i < numberOfRows - 1) {
                        borderEdge = TableCell.BorderEdge.bottom;
                    }
                    borderEdgeArr[1] = borderEdge;
                    setEdges(shape.getCell(i, i2), borderEdgeArr, objArr);
                    i2++;
                }
            }
        }
    }

    private static void setEdges(TableCell<?, ?> tableCell, TableCell.BorderEdge[] borderEdgeArr, Object... objArr) {
        if (tableCell != null) {
            for (TableCell.BorderEdge borderEdge : borderEdgeArr) {
                if (borderEdge != null) {
                    if (objArr.length == 0) {
                        tableCell.removeBorder(borderEdge);
                    } else {
                        for (Double d : objArr) {
                            if (d instanceof Double) {
                                tableCell.setBorderWidth(borderEdge, d.doubleValue());
                            } else if (d instanceof Color) {
                                tableCell.setBorderColor(borderEdge, (Color) d);
                            } else if (d instanceof StrokeStyle.LineDash) {
                                tableCell.setBorderDash(borderEdge, (StrokeStyle.LineDash) d);
                            } else if (d instanceof StrokeStyle.LineCompound) {
                                tableCell.setBorderCompound(borderEdge, (StrokeStyle.LineCompound) d);
                            }
                        }
                    }
                }
            }
        }
    }
}
