package org.apache.poi.xddf.usermodel;

import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeRect;

public class XDDFRelativeRectangle {
    private final CTRelativeRect rect;

    public XDDFRelativeRectangle() {
        this(CTRelativeRect.Factory.newInstance());
    }

    protected XDDFRelativeRectangle(CTRelativeRect cTRelativeRect) {
        this.rect = cTRelativeRect;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTRelativeRect getXmlObject() {
        return this.rect;
    }

    public Integer getBottom() {
        if (this.rect.isSetB()) {
            return Integer.valueOf(POIXMLUnits.parsePercent(this.rect.xgetB()));
        }
        return null;
    }

    public void setBottom(Integer num) {
        if (num != null) {
            this.rect.setB(num);
        } else if (this.rect.isSetB()) {
            this.rect.unsetB();
        }
    }

    public Integer getLeft() {
        if (this.rect.isSetL()) {
            return Integer.valueOf(POIXMLUnits.parsePercent(this.rect.xgetL()));
        }
        return null;
    }

    public void setLeft(Integer num) {
        if (num != null) {
            this.rect.setL(num);
        } else if (this.rect.isSetL()) {
            this.rect.unsetL();
        }
    }

    public Integer getRight() {
        if (this.rect.isSetR()) {
            return Integer.valueOf(POIXMLUnits.parsePercent(this.rect.xgetR()));
        }
        return null;
    }

    public void setRight(Integer num) {
        if (num != null) {
            this.rect.setR(num);
        } else if (this.rect.isSetR()) {
            this.rect.unsetR();
        }
    }

    public Integer getTop() {
        if (this.rect.isSetT()) {
            return Integer.valueOf(POIXMLUnits.parsePercent(this.rect.xgetT()));
        }
        return null;
    }

    public void setTop(Integer num) {
        if (num != null) {
            this.rect.setT(num);
        } else if (this.rect.isSetT()) {
            this.rect.unsetT();
        }
    }
}
