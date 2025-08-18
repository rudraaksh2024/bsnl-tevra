package org.apache.poi.sl.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.poi.sl.draw.geom.Outline;
import org.apache.poi.sl.draw.geom.Path;
import org.apache.poi.sl.draw.geom.PathIf;
import org.apache.poi.sl.usermodel.LineDecoration;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.sl.usermodel.Shadow;
import org.apache.poi.sl.usermodel.SimpleShape;

public class DrawSimpleShape extends DrawShape {
    private static final double DECO_SIZE_POW = 1.5d;

    public DrawSimpleShape(SimpleShape<?, ?> simpleShape) {
        super(simpleShape);
    }

    public void draw(Graphics2D graphics2D) {
        if (getAnchor(graphics2D, (PlaceableShape<?, ?>) getShape()) != null) {
            Paint paint = graphics2D.getPaint();
            Stroke stroke = graphics2D.getStroke();
            Color color = graphics2D.getColor();
            Paint fillPaint = getFillPaint(graphics2D);
            Paint linePaint = getLinePaint(graphics2D);
            BasicStroke stroke2 = getStroke();
            graphics2D.setStroke(stroke2);
            Collection<Outline> computeOutlines = computeOutlines(graphics2D);
            drawShadow(graphics2D, computeOutlines, fillPaint, linePaint);
            if (fillPaint != null) {
                Path2D.Double doubleR = new Path2D.Double();
                graphics2D.setRenderingHint(Drawable.GRADIENT_SHAPE, doubleR);
                DrawSimpleShape$$ExternalSyntheticLambda0 drawSimpleShape$$ExternalSyntheticLambda0 = new DrawSimpleShape$$ExternalSyntheticLambda0(this, graphics2D, doubleR);
                PaintStyle.PaintModifier paintModifier = null;
                for (Outline next : computeOutlines) {
                    PathIf path = next.getPath();
                    if (path.isFilled()) {
                        PaintStyle.PaintModifier fill = path.getFill();
                        if (paintModifier == null || paintModifier == fill) {
                            doubleR.append(next.getOutline(), false);
                        } else {
                            drawSimpleShape$$ExternalSyntheticLambda0.accept(paintModifier);
                            doubleR.reset();
                        }
                        paintModifier = fill;
                    }
                }
                if (doubleR.getCurrentPoint() != null) {
                    drawSimpleShape$$ExternalSyntheticLambda0.accept(paintModifier);
                }
            }
            drawContent(graphics2D);
            if (linePaint != null) {
                graphics2D.setPaint(linePaint);
                graphics2D.setStroke(stroke2);
                for (Outline next2 : computeOutlines) {
                    if (next2.getPath().isStroked()) {
                        Shape outline = next2.getOutline();
                        graphics2D.setRenderingHint(Drawable.GRADIENT_SHAPE, outline);
                        graphics2D.draw(outline);
                    }
                }
            }
            drawDecoration(graphics2D, linePaint, stroke2);
            graphics2D.setColor(color);
            graphics2D.setPaint(paint);
            graphics2D.setStroke(stroke);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: fillArea */
    public void m2233lambda$draw$0$orgapachepoisldrawDrawSimpleShape(Graphics2D graphics2D, PaintStyle.PaintModifier paintModifier, Path2D path2D) {
        SimpleShape shape = getShape();
        Paint paint = DrawFactory.getInstance(graphics2D).getPaint(shape).getPaint(graphics2D, shape.getFillStyle().getPaint(), paintModifier);
        if (paint != null) {
            graphics2D.setPaint(paint);
            DrawPaint.fillPaintWorkaround(graphics2D, path2D);
        }
    }

    /* access modifiers changed from: protected */
    public Paint getFillPaint(Graphics2D graphics2D) {
        return DrawFactory.getInstance(graphics2D).getPaint(getShape()).getPaint(graphics2D, getShape().getFillStyle().getPaint());
    }

    /* access modifiers changed from: protected */
    public Paint getLinePaint(Graphics2D graphics2D) {
        return DrawFactory.getInstance(graphics2D).getPaint(getShape()).getPaint(graphics2D, getShape().getStrokeStyle().getPaint());
    }

    /* access modifiers changed from: protected */
    public void drawDecoration(Graphics2D graphics2D, Paint paint, BasicStroke basicStroke) {
        if (paint != null) {
            graphics2D.setPaint(paint);
            ArrayList<Outline> arrayList = new ArrayList<>();
            LineDecoration lineDecoration = getShape().getLineDecoration();
            Outline headDecoration = getHeadDecoration(graphics2D, lineDecoration, basicStroke);
            if (headDecoration != null) {
                arrayList.add(headDecoration);
            }
            Outline tailDecoration = getTailDecoration(graphics2D, lineDecoration, basicStroke);
            if (tailDecoration != null) {
                arrayList.add(tailDecoration);
            }
            for (Outline outline : arrayList) {
                Shape outline2 = outline.getOutline();
                PathIf path = outline.getPath();
                graphics2D.setRenderingHint(Drawable.GRADIENT_SHAPE, outline2);
                if (path.isFilled()) {
                    graphics2D.fill(outline2);
                }
                if (path.isStroked()) {
                    graphics2D.draw(outline2);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public Outline getTailDecoration(Graphics2D graphics2D, LineDecoration lineDecoration, BasicStroke basicStroke) {
        double d;
        double d2;
        double d3;
        Shape shape;
        Path path;
        if (lineDecoration == null || basicStroke == null) {
            return null;
        }
        LineDecoration.DecorationSize tailLength = lineDecoration.getTailLength();
        if (tailLength == null) {
            tailLength = LineDecoration.DecorationSize.MEDIUM;
        }
        LineDecoration.DecorationSize tailWidth = lineDecoration.getTailWidth();
        if (tailWidth == null) {
            tailWidth = LineDecoration.DecorationSize.MEDIUM;
        }
        double max = Math.max(2.5d, (double) basicStroke.getLineWidth());
        Rectangle2D anchor = getAnchor(graphics2D, (PlaceableShape<?, ?>) getShape());
        if (anchor != null) {
            d3 = anchor.getX() + anchor.getWidth();
            d2 = anchor.getY() + anchor.getHeight();
            d = Math.atan(anchor.getHeight() / anchor.getWidth());
        } else {
            d3 = 0.0d;
            d2 = 0.0d;
            d = 0.0d;
        }
        AffineTransform affineTransform = new AffineTransform();
        double d4 = d;
        double pow = Math.pow(DECO_SIZE_POW, ((double) tailWidth.ordinal()) + 1.0d);
        double pow2 = Math.pow(DECO_SIZE_POW, ((double) tailLength.ordinal()) + 1.0d);
        LineDecoration.DecorationShape tailShape = lineDecoration.getTailShape();
        if (tailShape == null) {
            return null;
        }
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$sl$usermodel$LineDecoration$DecorationShape[tailShape.ordinal()];
        if (i == 1) {
            path = new Path();
            Shape shape2 = new Ellipse2D.Double(0.0d, 0.0d, max * pow2, max * pow);
            Rectangle2D bounds2D = shape2.getBounds2D();
            affineTransform.translate(d3 - (bounds2D.getWidth() / 2.0d), d2 - (bounds2D.getHeight() / 2.0d));
            affineTransform.rotate(d4, bounds2D.getX() + (bounds2D.getWidth() / 2.0d), bounds2D.getY() + (bounds2D.getHeight() / 2.0d));
            shape = shape2;
        } else if (i == 2 || i == 3) {
            path = new Path();
            path.setFill(PaintStyle.PaintModifier.NONE);
            path.setStroke(true);
            shape = new Path2D.Double();
            double d5 = -max;
            double d6 = pow2 * d5;
            shape.moveTo(d6, (d5 * pow) / 2.0d);
            shape.lineTo(0.0d, 0.0d);
            shape.lineTo(d6, (max * pow) / 2.0d);
            affineTransform.translate(d3, d2);
            affineTransform.rotate(d4);
        } else if (i != 4) {
            path = null;
            shape = null;
        } else {
            path = new Path();
            shape = new Path2D.Double();
            double d7 = -max;
            double d8 = pow2 * d7;
            shape.moveTo(d8, (d7 * pow) / 2.0d);
            shape.lineTo(0.0d, 0.0d);
            shape.lineTo(d8, (max * pow) / 2.0d);
            shape.closePath();
            affineTransform.translate(d3, d2);
            affineTransform.rotate(d4);
        }
        if (shape != null) {
            shape = affineTransform.createTransformedShape(shape);
        }
        if (shape == null) {
            return null;
        }
        return new Outline(shape, path);
    }

    /* renamed from: org.apache.poi.sl.draw.DrawSimpleShape$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$LineDecoration$DecorationShape;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                org.apache.poi.sl.usermodel.LineDecoration$DecorationShape[] r0 = org.apache.poi.sl.usermodel.LineDecoration.DecorationShape.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$sl$usermodel$LineDecoration$DecorationShape = r0
                org.apache.poi.sl.usermodel.LineDecoration$DecorationShape r1 = org.apache.poi.sl.usermodel.LineDecoration.DecorationShape.OVAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$LineDecoration$DecorationShape     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.sl.usermodel.LineDecoration$DecorationShape r1 = org.apache.poi.sl.usermodel.LineDecoration.DecorationShape.STEALTH     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$LineDecoration$DecorationShape     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.sl.usermodel.LineDecoration$DecorationShape r1 = org.apache.poi.sl.usermodel.LineDecoration.DecorationShape.ARROW     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$LineDecoration$DecorationShape     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.sl.usermodel.LineDecoration$DecorationShape r1 = org.apache.poi.sl.usermodel.LineDecoration.DecorationShape.TRIANGLE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.DrawSimpleShape.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public Outline getHeadDecoration(Graphics2D graphics2D, LineDecoration lineDecoration, BasicStroke basicStroke) {
        double d;
        double d2;
        double d3;
        Shape shape;
        Path path;
        if (lineDecoration == null || basicStroke == null) {
            return null;
        }
        LineDecoration.DecorationSize headLength = lineDecoration.getHeadLength();
        if (headLength == null) {
            headLength = LineDecoration.DecorationSize.MEDIUM;
        }
        LineDecoration.DecorationSize headWidth = lineDecoration.getHeadWidth();
        if (headWidth == null) {
            headWidth = LineDecoration.DecorationSize.MEDIUM;
        }
        double max = Math.max(2.5d, (double) basicStroke.getLineWidth());
        Rectangle2D anchor = getAnchor(graphics2D, (PlaceableShape<?, ?>) getShape());
        if (anchor != null) {
            d3 = anchor.getX();
            d2 = anchor.getY();
            d = Math.atan(anchor.getHeight() / anchor.getWidth());
        } else {
            d3 = 0.0d;
            d2 = 0.0d;
            d = 0.0d;
        }
        AffineTransform affineTransform = new AffineTransform();
        double d4 = d;
        double pow = Math.pow(DECO_SIZE_POW, ((double) headWidth.ordinal()) + 1.0d);
        double pow2 = Math.pow(DECO_SIZE_POW, ((double) headLength.ordinal()) + 1.0d);
        LineDecoration.DecorationShape headShape = lineDecoration.getHeadShape();
        if (headShape == null) {
            return null;
        }
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$sl$usermodel$LineDecoration$DecorationShape[headShape.ordinal()];
        if (i == 1) {
            path = new Path();
            Shape shape2 = new Ellipse2D.Double(0.0d, 0.0d, max * pow2, max * pow);
            Rectangle2D bounds2D = shape2.getBounds2D();
            affineTransform.translate(d3 - (bounds2D.getWidth() / 2.0d), d2 - (bounds2D.getHeight() / 2.0d));
            affineTransform.rotate(d4, bounds2D.getX() + (bounds2D.getWidth() / 2.0d), bounds2D.getY() + (bounds2D.getHeight() / 2.0d));
            shape = shape2;
        } else if (i == 2 || i == 3) {
            path = new Path();
            path.setFill(PaintStyle.PaintModifier.NONE);
            path.setStroke(true);
            shape = new Path2D.Double();
            double d5 = pow2 * max;
            shape.moveTo(d5, ((-max) * pow) / 2.0d);
            shape.lineTo(0.0d, 0.0d);
            shape.lineTo(d5, (max * pow) / 2.0d);
            affineTransform.translate(d3, d2);
            affineTransform.rotate(d4);
        } else if (i != 4) {
            path = null;
            shape = null;
        } else {
            path = new Path();
            shape = new Path2D.Double();
            double d6 = pow2 * max;
            shape.moveTo(d6, ((-max) * pow) / 2.0d);
            shape.lineTo(0.0d, 0.0d);
            shape.lineTo(d6, (max * pow) / 2.0d);
            shape.closePath();
            affineTransform.translate(d3, d2);
            affineTransform.rotate(d4);
        }
        if (shape != null) {
            shape = affineTransform.createTransformedShape(shape);
        }
        if (shape == null) {
            return null;
        }
        return new Outline(shape, path);
    }

    public BasicStroke getStroke() {
        return getStroke(getShape().getStrokeStyle());
    }

    /* access modifiers changed from: protected */
    public void drawShadow(Graphics2D graphics2D, Collection<Outline> collection, Paint paint, Paint paint2) {
        Shadow shadow = getShape().getShadow();
        if (shadow == null) {
            return;
        }
        if (paint != null || paint2 != null) {
            Color applyColorTransform = DrawPaint.applyColorTransform(shadow.getFillStyle().getSolidColor());
            double rotation = getShape().getRotation();
            if (getShape().getFlipVertical()) {
                rotation += 180.0d;
            }
            double angle = shadow.getAngle() - rotation;
            double distance = shadow.getDistance();
            double cos = Math.cos(Math.toRadians(angle)) * distance;
            double sin = distance * Math.sin(Math.toRadians(angle));
            graphics2D.translate(cos, sin);
            for (Outline next : collection) {
                Shape outline = next.getOutline();
                PathIf path = next.getPath();
                graphics2D.setRenderingHint(Drawable.GRADIENT_SHAPE, outline);
                graphics2D.setPaint(applyColorTransform);
                if (paint != null && path.isFilled()) {
                    DrawPaint.fillPaintWorkaround(graphics2D, outline);
                } else if (paint2 != null && path.isStroked()) {
                    graphics2D.draw(outline);
                }
            }
            graphics2D.translate(-cos, -sin);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0078  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Collection<org.apache.poi.sl.draw.geom.Outline> computeOutlines(java.awt.Graphics2D r31) {
        /*
            r30 = this;
            org.apache.poi.sl.usermodel.SimpleShape r0 = r30.getShape()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            org.apache.poi.sl.draw.geom.CustomGeometry r2 = r0.getGeometry()
            if (r2 != 0) goto L_0x0010
            return r1
        L_0x0010:
            r3 = r31
            java.awt.geom.Rectangle2D r3 = getAnchor((java.awt.Graphics2D) r3, (org.apache.poi.sl.usermodel.PlaceableShape<?, ?>) r0)
            if (r3 != 0) goto L_0x0019
            return r1
        L_0x0019:
            java.util.Iterator r4 = r2.iterator()
        L_0x001d:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x00c3
            java.lang.Object r5 = r4.next()
            org.apache.poi.sl.draw.geom.PathIf r5 = (org.apache.poi.sl.draw.geom.PathIf) r5
            long r6 = r5.getW()
            double r6 = (double) r6
            long r8 = r5.getH()
            double r8 = (double) r8
            r10 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            int r12 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            r13 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r15 = 1
            r17 = 0
            if (r12 != 0) goto L_0x0051
            double r6 = r3.getWidth()
            int r6 = org.apache.poi.util.Units.toEMU(r6)
            double r6 = (double) r6
            double r19 = org.apache.poi.util.Units.toPoints(r15)
        L_0x004c:
            r26 = r6
            r6 = r19
            goto L_0x0064
        L_0x0051:
            double r19 = r3.getWidth()
            int r12 = (r19 > r17 ? 1 : (r19 == r17 ? 0 : -1))
            if (r12 != 0) goto L_0x005d
            r26 = r6
            r6 = r13
            goto L_0x0064
        L_0x005d:
            double r19 = r3.getWidth()
            double r19 = r19 / r6
            goto L_0x004c
        L_0x0064:
            int r10 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r10 != 0) goto L_0x0078
            double r8 = r3.getHeight()
            int r8 = org.apache.poi.util.Units.toEMU(r8)
            double r8 = (double) r8
            double r13 = org.apache.poi.util.Units.toPoints(r15)
        L_0x0075:
            r28 = r8
            goto L_0x0088
        L_0x0078:
            double r10 = r3.getHeight()
            int r10 = (r10 > r17 ? 1 : (r10 == r17 ? 0 : -1))
            if (r10 != 0) goto L_0x0081
            goto L_0x0075
        L_0x0081:
            double r10 = r3.getHeight()
            double r13 = r10 / r8
            goto L_0x0075
        L_0x0088:
            java.awt.geom.Rectangle2D$Double r8 = new java.awt.geom.Rectangle2D$Double
            r22 = 0
            r24 = 0
            r21 = r8
            r21.<init>(r22, r24, r26, r28)
            org.apache.poi.sl.draw.geom.Context r9 = new org.apache.poi.sl.draw.geom.Context
            r9.<init>(r2, r8, r0)
            java.awt.geom.Path2D$Double r8 = r5.getPath(r9)
            java.awt.geom.AffineTransform r9 = new java.awt.geom.AffineTransform
            r9.<init>()
            double r10 = r3.getX()
            r30 = r0
            r12 = r1
            double r0 = r3.getY()
            r9.translate(r10, r0)
            r9.scale(r6, r13)
            java.awt.Shape r0 = r9.createTransformedShape(r8)
            org.apache.poi.sl.draw.geom.Outline r1 = new org.apache.poi.sl.draw.geom.Outline
            r1.<init>(r0, r5)
            r12.add(r1)
            r0 = r30
            r1 = r12
            goto L_0x001d
        L_0x00c3:
            r12 = r1
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.DrawSimpleShape.computeOutlines(java.awt.Graphics2D):java.util.Collection");
    }

    /* access modifiers changed from: protected */
    public SimpleShape<?, ?> getShape() {
        return (SimpleShape) this.shape;
    }
}
