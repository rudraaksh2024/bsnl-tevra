package org.apache.poi.xddf.usermodel;

import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor;

public class XDDFColorHsl extends XDDFColor {
    private CTHslColor color;

    public XDDFColorHsl(int i, int i2, int i3) {
        this(CTHslColor.Factory.newInstance(), CTColor.Factory.newInstance());
        setHue(i);
        setSaturation(i2);
        setLuminance(i3);
    }

    @Internal
    protected XDDFColorHsl(CTHslColor cTHslColor) {
        this(cTHslColor, (CTColor) null);
    }

    @Internal
    protected XDDFColorHsl(CTHslColor cTHslColor, CTColor cTColor) {
        super(cTColor);
        this.color = cTHslColor;
    }

    /* access modifiers changed from: protected */
    @Internal
    public XmlObject getXmlObject() {
        return this.color;
    }

    public int getHue() {
        return this.color.getHue2();
    }

    public void setHue(int i) {
        this.color.setHue2(i);
    }

    public int getSaturation() {
        return POIXMLUnits.parsePercent(this.color.xgetSat2()) / 1000;
    }

    public void setSaturation(int i) {
        this.color.setSat2(Integer.valueOf(i));
    }

    public int getLuminance() {
        return POIXMLUnits.parsePercent(this.color.xgetLum2()) / 1000;
    }

    public void setLuminance(int i) {
        this.color.setLum2(Integer.valueOf(i));
    }
}
