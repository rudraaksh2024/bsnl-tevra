package org.apache.poi.xslf.draw.geom;

import java.awt.geom.Path2D;
import org.apache.poi.sl.draw.geom.ClosePathCommand;
import org.apache.poi.sl.draw.geom.Context;
import org.apache.poi.sl.draw.geom.PathCommand;
import org.apache.poi.sl.draw.geom.PathIf;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DClose;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DCubicBezierTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DLineTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DMoveTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DQuadBezierTo;
import org.openxmlformats.schemas.drawingml.x2006.main.STPathFillMode;

public class XSLFPath implements PathIf {
    private final CTPath2D pathXml;

    public void addCommand(PathCommand pathCommand) {
    }

    public XSLFPath(CTPath2D cTPath2D) {
        this.pathXml = cTPath2D;
    }

    public Path2D.Double getPath(Context context) {
        PathCommand pathCommand;
        Path2D.Double doubleR = new Path2D.Double();
        XmlCursor newCursor = this.pathXml.newCursor();
        try {
            for (boolean firstChild = newCursor.toFirstChild(); firstChild; firstChild = newCursor.toNextSibling()) {
                XmlObject object = newCursor.getObject();
                if (object instanceof CTPath2DArcTo) {
                    pathCommand = new XSLFArcTo((CTPath2DArcTo) object);
                } else if (object instanceof CTPath2DCubicBezierTo) {
                    pathCommand = new XSLFCurveTo((CTPath2DCubicBezierTo) object);
                } else if (object instanceof CTPath2DMoveTo) {
                    pathCommand = new XSLFMoveTo((CTPath2DMoveTo) object);
                } else if (object instanceof CTPath2DLineTo) {
                    pathCommand = new XSLFLineTo((CTPath2DLineTo) object);
                } else if (object instanceof CTPath2DQuadBezierTo) {
                    pathCommand = new XSLFQuadTo((CTPath2DQuadBezierTo) object);
                } else if (object instanceof CTPath2DClose) {
                    pathCommand = new ClosePathCommand();
                }
                pathCommand.execute(doubleR, context);
            }
            return doubleR;
        } finally {
            newCursor.dispose();
        }
    }

    public boolean isStroked() {
        return this.pathXml.getStroke();
    }

    public void setStroke(boolean z) {
        this.pathXml.setStroke(z);
    }

    public boolean isFilled() {
        return this.pathXml.getFill() != STPathFillMode.NONE;
    }

    public PaintStyle.PaintModifier getFill() {
        int intValue = this.pathXml.getFill().intValue();
        if (intValue == 2) {
            return PaintStyle.PaintModifier.NORM;
        }
        if (intValue == 3) {
            return PaintStyle.PaintModifier.LIGHTEN;
        }
        if (intValue == 4) {
            return PaintStyle.PaintModifier.LIGHTEN_LESS;
        }
        if (intValue == 5) {
            return PaintStyle.PaintModifier.DARKEN;
        }
        if (intValue != 6) {
            return PaintStyle.PaintModifier.NONE;
        }
        return PaintStyle.PaintModifier.DARKEN_LESS;
    }

    /* renamed from: org.apache.poi.xslf.draw.geom.XSLFPath$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$PaintModifier;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.sl.usermodel.PaintStyle$PaintModifier[] r0 = org.apache.poi.sl.usermodel.PaintStyle.PaintModifier.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$PaintModifier = r0
                org.apache.poi.sl.usermodel.PaintStyle$PaintModifier r1 = org.apache.poi.sl.usermodel.PaintStyle.PaintModifier.NONE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$PaintModifier     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.sl.usermodel.PaintStyle$PaintModifier r1 = org.apache.poi.sl.usermodel.PaintStyle.PaintModifier.NORM     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$PaintModifier     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.sl.usermodel.PaintStyle$PaintModifier r1 = org.apache.poi.sl.usermodel.PaintStyle.PaintModifier.LIGHTEN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$PaintModifier     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.sl.usermodel.PaintStyle$PaintModifier r1 = org.apache.poi.sl.usermodel.PaintStyle.PaintModifier.LIGHTEN_LESS     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$PaintModifier     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.sl.usermodel.PaintStyle$PaintModifier r1 = org.apache.poi.sl.usermodel.PaintStyle.PaintModifier.DARKEN     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$PaintModifier     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.sl.usermodel.PaintStyle$PaintModifier r1 = org.apache.poi.sl.usermodel.PaintStyle.PaintModifier.DARKEN_LESS     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.draw.geom.XSLFPath.AnonymousClass1.<clinit>():void");
        }
    }

    public void setFill(PaintStyle.PaintModifier paintModifier) {
        STPathFillMode.Enum enumR;
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$PaintModifier[paintModifier.ordinal()];
        if (i == 2) {
            enumR = STPathFillMode.NORM;
        } else if (i == 3) {
            enumR = STPathFillMode.LIGHTEN;
        } else if (i == 4) {
            enumR = STPathFillMode.LIGHTEN_LESS;
        } else if (i == 5) {
            enumR = STPathFillMode.DARKEN;
        } else if (i != 6) {
            enumR = STPathFillMode.NONE;
        } else {
            enumR = STPathFillMode.DARKEN_LESS;
        }
        this.pathXml.setFill(enumR);
    }

    public long getW() {
        return this.pathXml.getW();
    }

    public void setW(long j) {
        this.pathXml.setW(j);
    }

    public long getH() {
        return this.pathXml.getH();
    }

    public void setH(long j) {
        this.pathXml.setH(j);
    }

    public boolean isExtrusionOk() {
        return this.pathXml.getExtrusionOk();
    }

    public void setExtrusionOk(boolean z) {
        this.pathXml.setExtrusionOk(z);
    }
}
