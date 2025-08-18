package org.apache.poi.xslf.usermodel;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.sl.usermodel.ColorStyle;
import org.apache.poi.sl.usermodel.Insets2D;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.util.Dimension2DDouble;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlip;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeRect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;

@Internal
public class XSLFTexturePaint implements PaintStyle.TexturePaint {
    private final CTBlip blip;
    private final CTBlipFillProperties blipFill;
    private final PackagePart parentPart;
    private final CTSchemeColor phClr;
    private final XSLFShape shape;
    private final XSLFSheet sheet;
    private final XSLFTheme theme;

    public XSLFTexturePaint(XSLFShape xSLFShape, CTBlipFillProperties cTBlipFillProperties, PackagePart packagePart, CTSchemeColor cTSchemeColor, XSLFTheme xSLFTheme, XSLFSheet xSLFSheet) {
        this.shape = xSLFShape;
        this.blipFill = cTBlipFillProperties;
        this.parentPart = packagePart;
        this.blip = cTBlipFillProperties.getBlip();
        this.phClr = cTSchemeColor;
        this.theme = xSLFTheme;
        this.sheet = xSLFSheet;
    }

    private PackagePart getPart() {
        try {
            return this.parentPart.getRelatedPart(this.parentPart.getRelationship(this.blip.getEmbed()));
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public InputStream getImageData() {
        try {
            return getPart().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getContentType() {
        CTBlip cTBlip = this.blip;
        if (cTBlip == null || !cTBlip.isSetEmbed() || this.blip.getEmbed().isEmpty()) {
            return null;
        }
        return getPart().getContentType();
    }

    public int getAlpha() {
        return this.blip.sizeOfAlphaModFixArray() > 0 ? POIXMLUnits.parsePercent(this.blip.getAlphaModFixArray(0).xgetAmt()) : BZip2Constants.BASEBLOCKSIZE;
    }

    public boolean isRotatedWithShape() {
        return !this.blipFill.isSetRotWithShape() || this.blipFill.getRotWithShape();
    }

    public Dimension2D getScale() {
        CTTileInfoProperties tile = this.blipFill.getTile();
        if (tile == null) {
            return null;
        }
        double d = 1.0d;
        double parsePercent = tile.isSetSx() ? ((double) POIXMLUnits.parsePercent(tile.xgetSx())) / 100000.0d : 1.0d;
        if (tile.isSetSy()) {
            d = ((double) POIXMLUnits.parsePercent(tile.xgetSy())) / 100000.0d;
        }
        return new Dimension2DDouble(parsePercent, d);
    }

    public Point2D getOffset() {
        CTTileInfoProperties tile = this.blipFill.getTile();
        if (tile == null) {
            return null;
        }
        double d = 0.0d;
        double points = tile.isSetTx() ? Units.toPoints(POIXMLUnits.parseLength(tile.xgetTx())) : 0.0d;
        if (tile.isSetTy()) {
            d = Units.toPoints(POIXMLUnits.parseLength(tile.xgetTy()));
        }
        return new Point2D.Double(points, d);
    }

    public PaintStyle.FlipMode getFlipMode() {
        CTTileInfoProperties tile = this.blipFill.getTile();
        int intValue = (tile == null || tile.getFlip() == null) ? 1 : tile.getFlip().intValue();
        if (intValue == 2) {
            return PaintStyle.FlipMode.X;
        }
        if (intValue == 3) {
            return PaintStyle.FlipMode.Y;
        }
        if (intValue != 4) {
            return PaintStyle.FlipMode.NONE;
        }
        return PaintStyle.FlipMode.XY;
    }

    public PaintStyle.TextureAlignment getAlignment() {
        CTTileInfoProperties tile = this.blipFill.getTile();
        if (tile == null || !tile.isSetAlgn()) {
            return null;
        }
        return PaintStyle.TextureAlignment.fromOoxmlId(tile.getAlgn().toString());
    }

    public Insets2D getInsets() {
        return getRectVal(this.blipFill.getSrcRect());
    }

    public Insets2D getStretch() {
        return getRectVal(this.blipFill.isSetStretch() ? this.blipFill.getStretch().getFillRect() : null);
    }

    public List<ColorStyle> getDuoTone() {
        if (this.blip.sizeOfDuotoneArray() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (CTSchemeColor xSLFColor : this.blip.getDuotoneArray(0).getSchemeClrArray()) {
            arrayList.add(new XSLFColor(xSLFColor, this.theme, this.phClr, this.sheet).getColorStyle());
        }
        return arrayList;
    }

    public Shape getShape() {
        return this.shape;
    }

    private static Insets2D getRectVal(CTRelativeRect cTRelativeRect) {
        if (cTRelativeRect == null) {
            return null;
        }
        cTRelativeRect.getClass();
        XSLFTexturePaint$$ExternalSyntheticLambda0 xSLFTexturePaint$$ExternalSyntheticLambda0 = new XSLFTexturePaint$$ExternalSyntheticLambda0(cTRelativeRect);
        cTRelativeRect.getClass();
        double rectVal = (double) getRectVal(xSLFTexturePaint$$ExternalSyntheticLambda0, new XSLFTexturePaint$$ExternalSyntheticLambda1(cTRelativeRect));
        cTRelativeRect.getClass();
        XSLFTexturePaint$$ExternalSyntheticLambda2 xSLFTexturePaint$$ExternalSyntheticLambda2 = new XSLFTexturePaint$$ExternalSyntheticLambda2(cTRelativeRect);
        cTRelativeRect.getClass();
        double rectVal2 = (double) getRectVal(xSLFTexturePaint$$ExternalSyntheticLambda2, new XSLFTexturePaint$$ExternalSyntheticLambda3(cTRelativeRect));
        cTRelativeRect.getClass();
        XSLFTexturePaint$$ExternalSyntheticLambda4 xSLFTexturePaint$$ExternalSyntheticLambda4 = new XSLFTexturePaint$$ExternalSyntheticLambda4(cTRelativeRect);
        cTRelativeRect.getClass();
        double rectVal3 = (double) getRectVal(xSLFTexturePaint$$ExternalSyntheticLambda4, new XSLFTexturePaint$$ExternalSyntheticLambda5(cTRelativeRect));
        cTRelativeRect.getClass();
        XSLFTexturePaint$$ExternalSyntheticLambda6 xSLFTexturePaint$$ExternalSyntheticLambda6 = new XSLFTexturePaint$$ExternalSyntheticLambda6(cTRelativeRect);
        cTRelativeRect.getClass();
        return new Insets2D(rectVal, rectVal2, rectVal3, (double) getRectVal(xSLFTexturePaint$$ExternalSyntheticLambda6, new XSLFTexturePaint$$ExternalSyntheticLambda7(cTRelativeRect)));
    }

    private static int getRectVal(Supplier<Boolean> supplier, Supplier<STPercentage> supplier2) {
        if (supplier.get().booleanValue()) {
            return POIXMLUnits.parsePercent(supplier2.get());
        }
        return 0;
    }
}
