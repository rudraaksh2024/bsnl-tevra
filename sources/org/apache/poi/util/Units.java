package org.apache.poi.util;

import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;

public class Units {
    public static final float DEFAULT_CHARACTER_WIDTH = 7.0017f;
    public static final int EMU_PER_CENTIMETER = 360000;
    public static final int EMU_PER_CHARACTER = 66691;
    public static final int EMU_PER_DXA = 635;
    public static final int EMU_PER_INCH = 914400;
    public static final int EMU_PER_PIXEL = 9525;
    public static final int EMU_PER_POINT = 12700;
    public static final int MASTER_DPI = 576;
    public static final int PIXEL_DPI = 96;
    public static final int POINT_DPI = 72;

    public static int charactersToEMU(double d) {
        return ((int) d) * EMU_PER_CHARACTER;
    }

    public static double fixedPointToDouble(int i) {
        return ((double) (i >> 16)) + (((double) (i & 65535)) / 65536.0d);
    }

    public static double masterToPoints(int i) {
        return (((double) i) * 72.0d) / 576.0d;
    }

    public static int pixelToEMU(int i) {
        return i * 9525;
    }

    public static double pixelToPoints(double d) {
        return (d * 72.0d) / 96.0d;
    }

    public static double toDXA(long j) {
        if (j == -1) {
            return -1.0d;
        }
        return ((double) j) / 635.0d;
    }

    public static double toPoints(long j) {
        if (j == -1) {
            return -1.0d;
        }
        return ((double) j) / 12700.0d;
    }

    public static int toEMU(double d) {
        return (int) Math.rint(d * 12700.0d);
    }

    public static int doubleToFixedPoint(double d) {
        double d2 = d % 1.0d;
        return (((int) Math.floor(d - d2)) << 16) | (((int) Math.rint(d2 * 65536.0d)) & 65535);
    }

    public static int pointsToMaster(double d) {
        return (int) Math.rint((d * 576.0d) / 72.0d);
    }

    public static int pointsToPixel(double d) {
        return (int) Math.rint((d * 96.0d) / 72.0d);
    }

    public static Dimension2D pointsToPixel(Dimension2D dimension2D) {
        return new Dimension2DDouble((dimension2D.getWidth() * 96.0d) / 72.0d, (dimension2D.getHeight() * 96.0d) / 72.0d);
    }

    public static Dimension2D pixelToPoints(Dimension2D dimension2D) {
        return new Dimension2DDouble((dimension2D.getWidth() * 72.0d) / 96.0d, (dimension2D.getHeight() * 72.0d) / 96.0d);
    }

    public static Rectangle2D pointsToPixel(Rectangle2D rectangle2D) {
        return new Rectangle2D.Double((rectangle2D.getX() * 96.0d) / 72.0d, (rectangle2D.getY() * 96.0d) / 72.0d, (rectangle2D.getWidth() * 96.0d) / 72.0d, (rectangle2D.getHeight() * 96.0d) / 72.0d);
    }

    public static Rectangle2D pixelToPoints(Rectangle2D rectangle2D) {
        return new Rectangle2D.Double((rectangle2D.getX() * 72.0d) / 96.0d, (rectangle2D.getY() * 72.0d) / 96.0d, (rectangle2D.getWidth() * 72.0d) / 96.0d, (rectangle2D.getHeight() * 72.0d) / 96.0d);
    }

    public static int columnWidthToEMU(int i) {
        return charactersToEMU(((double) i) / 256.0d);
    }
}
