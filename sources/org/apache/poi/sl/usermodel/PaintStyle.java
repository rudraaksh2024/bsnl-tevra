package org.apache.poi.sl.usermodel;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.io.InputStream;
import java.util.List;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

public interface PaintStyle {

    public enum FlipMode {
        NONE,
        X,
        Y,
        XY
    }

    public interface GradientPaint extends PaintStyle {

        public enum GradientType {
            linear,
            circular,
            rectangular,
            shape
        }

        Insets2D getFillToInsets() {
            return null;
        }

        double getGradientAngle();

        ColorStyle[] getGradientColors();

        float[] getGradientFractions();

        GradientType getGradientType();

        boolean isRotatedWithShape();
    }

    public enum PaintModifier {
        NONE,
        NORM,
        LIGHTEN,
        LIGHTEN_LESS,
        DARKEN,
        DARKEN_LESS
    }

    public interface SolidPaint extends PaintStyle {
        ColorStyle getSolidColor();
    }

    public enum TextureAlignment {
        BOTTOM("b"),
        BOTTOM_LEFT("bl"),
        BOTTOM_RIGHT(CompressorStreamFactory.BROTLI),
        CENTER("ctr"),
        LEFT("l"),
        RIGHT("r"),
        TOP("t"),
        TOP_LEFT("tl"),
        TOP_RIGHT("tr");
        
        private final String ooxmlId;

        private TextureAlignment(String str) {
            this.ooxmlId = str;
        }

        public static TextureAlignment fromOoxmlId(String str) {
            for (TextureAlignment textureAlignment : values()) {
                if (textureAlignment.ooxmlId.equals(str)) {
                    return textureAlignment;
                }
            }
            return null;
        }
    }

    public interface TexturePaint extends PaintStyle {
        TextureAlignment getAlignment() {
            return null;
        }

        int getAlpha();

        String getContentType();

        List<ColorStyle> getDuoTone() {
            return null;
        }

        InputStream getImageData();

        Insets2D getInsets() {
            return null;
        }

        Point2D getOffset() {
            return null;
        }

        Dimension2D getScale() {
            return null;
        }

        Shape getShape();

        Insets2D getStretch() {
            return null;
        }

        boolean isRotatedWithShape() {
            return true;
        }

        FlipMode getFlipMode() {
            return FlipMode.NONE;
        }
    }
}
