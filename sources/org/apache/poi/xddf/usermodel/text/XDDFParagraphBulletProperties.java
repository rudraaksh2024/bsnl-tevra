package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.common.usermodel.fonts.FontGroup;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

public class XDDFParagraphBulletProperties {
    private CTTextParagraphProperties props;

    @Internal
    protected XDDFParagraphBulletProperties(CTTextParagraphProperties cTTextParagraphProperties) {
        this.props = cTTextParagraphProperties;
    }

    public XDDFBulletStyle getBulletStyle() {
        if (this.props.isSetBuAutoNum()) {
            return new XDDFBulletStyleAutoNumbered(this.props.getBuAutoNum());
        }
        if (this.props.isSetBuBlip()) {
            return new XDDFBulletStylePicture(this.props.getBuBlip());
        }
        if (this.props.isSetBuChar()) {
            return new XDDFBulletStyleCharacter(this.props.getBuChar());
        }
        if (this.props.isSetBuNone()) {
            return new XDDFBulletStyleNone(this.props.getBuNone());
        }
        return null;
    }

    public void setBulletStyle(XDDFBulletStyle xDDFBulletStyle) {
        if (this.props.isSetBuAutoNum()) {
            this.props.unsetBuAutoNum();
        }
        if (this.props.isSetBuBlip()) {
            this.props.unsetBuBlip();
        }
        if (this.props.isSetBuChar()) {
            this.props.unsetBuChar();
        }
        if (this.props.isSetBuNone()) {
            this.props.unsetBuNone();
        }
        if (xDDFBulletStyle == null) {
            return;
        }
        if (xDDFBulletStyle instanceof XDDFBulletStyleAutoNumbered) {
            this.props.setBuAutoNum(((XDDFBulletStyleAutoNumbered) xDDFBulletStyle).getXmlObject());
        } else if (xDDFBulletStyle instanceof XDDFBulletStyleCharacter) {
            this.props.setBuChar(((XDDFBulletStyleCharacter) xDDFBulletStyle).getXmlObject());
        } else if (xDDFBulletStyle instanceof XDDFBulletStyleNone) {
            this.props.setBuNone(((XDDFBulletStyleNone) xDDFBulletStyle).getXmlObject());
        } else if (xDDFBulletStyle instanceof XDDFBulletStylePicture) {
            this.props.setBuBlip(((XDDFBulletStylePicture) xDDFBulletStyle).getXmlObject());
        }
    }

    public XDDFColor getBulletColor() {
        if (this.props.isSetBuClr()) {
            return XDDFColor.forColorContainer(this.props.getBuClr());
        }
        return null;
    }

    public void setBulletColor(XDDFColor xDDFColor) {
        if (this.props.isSetBuClrTx()) {
            this.props.unsetBuClrTx();
        }
        if (xDDFColor != null) {
            this.props.setBuClr(xDDFColor.getColorContainer());
        } else if (this.props.isSetBuClr()) {
            this.props.unsetBuClr();
        }
    }

    public void setBulletColorFollowText() {
        if (this.props.isSetBuClr()) {
            this.props.unsetBuClr();
        }
        if (!this.props.isSetBuClrTx()) {
            this.props.addNewBuClrTx();
        }
    }

    public XDDFFont getBulletFont() {
        if (this.props.isSetBuFont()) {
            return new XDDFFont(FontGroup.SYMBOL, this.props.getBuFont());
        }
        return null;
    }

    public void setBulletFont(XDDFFont xDDFFont) {
        if (this.props.isSetBuFontTx()) {
            this.props.unsetBuFontTx();
        }
        if (xDDFFont != null) {
            this.props.setBuFont(xDDFFont.getXmlObject());
        } else if (this.props.isSetBuFont()) {
            this.props.unsetBuFont();
        }
    }

    public void setBulletFontFollowText() {
        if (this.props.isSetBuFont()) {
            this.props.unsetBuFont();
        }
        if (!this.props.isSetBuFontTx()) {
            this.props.addNewBuFontTx();
        }
    }

    public XDDFBulletSize getBulletSize() {
        if (this.props.isSetBuSzPct()) {
            return new XDDFBulletSizePercent(this.props.getBuSzPct(), (Double) null);
        }
        if (this.props.isSetBuSzPts()) {
            return new XDDFBulletSizePoints(this.props.getBuSzPts());
        }
        if (this.props.isSetBuSzTx()) {
            return new XDDFBulletSizeFollowText(this.props.getBuSzTx());
        }
        return null;
    }

    public void setBulletSize(XDDFBulletSize xDDFBulletSize) {
        if (this.props.isSetBuSzPct()) {
            this.props.unsetBuSzPct();
        }
        if (this.props.isSetBuSzPts()) {
            this.props.unsetBuSzPts();
        }
        if (this.props.isSetBuSzTx()) {
            this.props.unsetBuSzTx();
        }
        if (xDDFBulletSize == null) {
            return;
        }
        if (xDDFBulletSize instanceof XDDFBulletSizeFollowText) {
            this.props.setBuSzTx(((XDDFBulletSizeFollowText) xDDFBulletSize).getXmlObject());
        } else if (xDDFBulletSize instanceof XDDFBulletSizePercent) {
            this.props.setBuSzPct(((XDDFBulletSizePercent) xDDFBulletSize).getXmlObject());
        } else if (xDDFBulletSize instanceof XDDFBulletSizePoints) {
            this.props.setBuSzPts(((XDDFBulletSizePoints) xDDFBulletSize).getXmlObject());
        }
    }

    public void clearAll() {
        if (this.props.isSetBuAutoNum()) {
            this.props.unsetBuAutoNum();
        }
        if (this.props.isSetBuBlip()) {
            this.props.unsetBuBlip();
        }
        if (this.props.isSetBuChar()) {
            this.props.unsetBuChar();
        }
        if (this.props.isSetBuNone()) {
            this.props.unsetBuNone();
        }
        if (this.props.isSetBuClr()) {
            this.props.unsetBuClr();
        }
        if (this.props.isSetBuClrTx()) {
            this.props.unsetBuClrTx();
        }
        if (this.props.isSetBuFont()) {
            this.props.unsetBuFont();
        }
        if (this.props.isSetBuFontTx()) {
            this.props.unsetBuFontTx();
        }
        if (this.props.isSetBuSzPct()) {
            this.props.unsetBuSzPct();
        }
        if (this.props.isSetBuSzPts()) {
            this.props.unsetBuSzPts();
        }
        if (this.props.isSetBuSzTx()) {
            this.props.unsetBuSzTx();
        }
    }

    public CTTextParagraphProperties getXmlObject() {
        return this.props;
    }
}
