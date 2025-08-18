package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D;

public class XDDFView3D {
    private final CTView3D view3D;

    @Internal
    protected XDDFView3D(CTView3D cTView3D) {
        this.view3D = cTView3D;
    }

    public Byte getXRotationAngle() {
        if (this.view3D.isSetRotX()) {
            return Byte.valueOf(this.view3D.getRotX().getVal());
        }
        return null;
    }

    public void setXRotationAngle(Byte b) {
        if (b == null) {
            if (this.view3D.isSetRotX()) {
                this.view3D.unsetRotX();
            }
        } else if (b.byteValue() < -90 || 90 < b.byteValue()) {
            throw new IllegalArgumentException("rotation must be between -90 and 90");
        } else if (this.view3D.isSetRotX()) {
            this.view3D.getRotX().setVal(b.byteValue());
        } else {
            this.view3D.addNewRotX().setVal(b.byteValue());
        }
    }

    public Integer getYRotationAngle() {
        if (this.view3D.isSetRotY()) {
            return Integer.valueOf(this.view3D.getRotY().getVal());
        }
        return null;
    }

    public void setYRotationAngle(Integer num) {
        if (num == null) {
            if (this.view3D.isSetRotY()) {
                this.view3D.unsetRotY();
            }
        } else if (num.intValue() < 0 || 360 < num.intValue()) {
            throw new IllegalArgumentException("rotation must be between 0 and 360");
        } else if (this.view3D.isSetRotY()) {
            this.view3D.getRotY().setVal(num.intValue());
        } else {
            this.view3D.addNewRotY().setVal(num.intValue());
        }
    }

    public Boolean hasRightAngleAxes() {
        if (this.view3D.isSetRAngAx()) {
            return Boolean.valueOf(this.view3D.getRAngAx().getVal());
        }
        return null;
    }

    public void setRightAngleAxes(Boolean bool) {
        if (bool == null) {
            if (this.view3D.isSetRAngAx()) {
                this.view3D.unsetRAngAx();
            }
        } else if (this.view3D.isSetRAngAx()) {
            this.view3D.getRAngAx().setVal(bool.booleanValue());
        } else {
            this.view3D.addNewRAngAx().setVal(bool.booleanValue());
        }
    }

    public Short getPerspectiveAngle() {
        if (this.view3D.isSetPerspective()) {
            return Short.valueOf(this.view3D.getPerspective().getVal());
        }
        return null;
    }

    public void setPerspectiveAngle(Short sh) {
        if (sh == null) {
            if (this.view3D.isSetPerspective()) {
                this.view3D.unsetPerspective();
            }
        } else if (sh.shortValue() < 0 || 240 < sh.shortValue()) {
            throw new IllegalArgumentException("perspective must be between 0 and 240");
        } else if (this.view3D.isSetPerspective()) {
            this.view3D.getPerspective().setVal(sh.shortValue());
        } else {
            this.view3D.addNewPerspective().setVal(sh.shortValue());
        }
    }

    public Integer getDepthPercent() {
        if (this.view3D.isSetDepthPercent()) {
            return Integer.valueOf(POIXMLUnits.parsePercent(this.view3D.getDepthPercent().xgetVal()));
        }
        return null;
    }

    public void setDepthPercent(Integer num) {
        if (num == null) {
            if (this.view3D.isSetDepthPercent()) {
                this.view3D.unsetDepthPercent();
            }
        } else if (num.intValue() < 20 || 2000 < num.intValue()) {
            throw new IllegalArgumentException("percent must be between 20 and 2000");
        } else if (this.view3D.isSetDepthPercent()) {
            this.view3D.getDepthPercent().setVal(num);
        } else {
            this.view3D.addNewDepthPercent().setVal(num);
        }
    }

    public Integer getHPercent() {
        if (this.view3D.isSetHPercent()) {
            return Integer.valueOf(POIXMLUnits.parsePercent(this.view3D.getHPercent().xgetVal()));
        }
        return null;
    }

    public void setHPercent(Integer num) {
        if (num == null) {
            if (this.view3D.isSetHPercent()) {
                this.view3D.unsetHPercent();
            }
        } else if (num.intValue() < 5 || 500 < num.intValue()) {
            throw new IllegalArgumentException("percent must be between 5 and 500");
        } else if (this.view3D.isSetHPercent()) {
            this.view3D.getHPercent().setVal(num);
        } else {
            this.view3D.addNewHPercent().setVal(num);
        }
    }
}
