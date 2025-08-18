package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLayout;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;

public final class XDDFManualLayout {
    private static final LayoutMode defaultLayoutMode = LayoutMode.EDGE;
    private static final LayoutTarget defaultLayoutTarget = LayoutTarget.INNER;
    private CTManualLayout layout;

    public XDDFManualLayout(CTLayout cTLayout) {
        initializeLayout(cTLayout);
    }

    public XDDFManualLayout(CTPlotArea cTPlotArea) {
        initializeLayout(cTPlotArea.isSetLayout() ? cTPlotArea.getLayout() : cTPlotArea.addNewLayout());
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTManualLayout getXmlObject() {
        return this.layout;
    }

    public void setExtensionList(XDDFChartExtensionList xDDFChartExtensionList) {
        if (xDDFChartExtensionList != null) {
            this.layout.setExtLst(xDDFChartExtensionList.getXmlObject());
        } else if (this.layout.isSetExtLst()) {
            this.layout.unsetExtLst();
        }
    }

    public XDDFChartExtensionList getExtensionList() {
        if (this.layout.isSetExtLst()) {
            return new XDDFChartExtensionList(this.layout.getExtLst());
        }
        return null;
    }

    public void setWidthRatio(double d) {
        if (!this.layout.isSetW()) {
            this.layout.addNewW();
        }
        this.layout.getW().setVal(d);
    }

    public double getWidthRatio() {
        if (!this.layout.isSetW()) {
            return 0.0d;
        }
        return this.layout.getW().getVal();
    }

    public void setHeightRatio(double d) {
        if (!this.layout.isSetH()) {
            this.layout.addNewH();
        }
        this.layout.getH().setVal(d);
    }

    public double getHeightRatio() {
        if (!this.layout.isSetH()) {
            return 0.0d;
        }
        return this.layout.getH().getVal();
    }

    public LayoutTarget getTarget() {
        if (!this.layout.isSetLayoutTarget()) {
            return defaultLayoutTarget;
        }
        return LayoutTarget.valueOf(this.layout.getLayoutTarget().getVal());
    }

    public void setTarget(LayoutTarget layoutTarget) {
        if (!this.layout.isSetLayoutTarget()) {
            this.layout.addNewLayoutTarget();
        }
        this.layout.getLayoutTarget().setVal(layoutTarget.underlying);
    }

    public LayoutMode getXMode() {
        if (!this.layout.isSetXMode()) {
            return defaultLayoutMode;
        }
        return LayoutMode.valueOf(this.layout.getXMode().getVal());
    }

    public void setXMode(LayoutMode layoutMode) {
        if (!this.layout.isSetXMode()) {
            this.layout.addNewXMode();
        }
        this.layout.getXMode().setVal(layoutMode.underlying);
    }

    public LayoutMode getYMode() {
        if (!this.layout.isSetYMode()) {
            return defaultLayoutMode;
        }
        return LayoutMode.valueOf(this.layout.getYMode().getVal());
    }

    public void setYMode(LayoutMode layoutMode) {
        if (!this.layout.isSetYMode()) {
            this.layout.addNewYMode();
        }
        this.layout.getYMode().setVal(layoutMode.underlying);
    }

    public double getX() {
        if (!this.layout.isSetX()) {
            return 0.0d;
        }
        return this.layout.getX().getVal();
    }

    public void setX(double d) {
        if (!this.layout.isSetX()) {
            this.layout.addNewX();
        }
        this.layout.getX().setVal(d);
    }

    public double getY() {
        if (!this.layout.isSetY()) {
            return 0.0d;
        }
        return this.layout.getY().getVal();
    }

    public void setY(double d) {
        if (!this.layout.isSetY()) {
            this.layout.addNewY();
        }
        this.layout.getY().setVal(d);
    }

    public LayoutMode getWidthMode() {
        if (!this.layout.isSetWMode()) {
            return defaultLayoutMode;
        }
        return LayoutMode.valueOf(this.layout.getWMode().getVal());
    }

    public void setWidthMode(LayoutMode layoutMode) {
        if (!this.layout.isSetWMode()) {
            this.layout.addNewWMode();
        }
        this.layout.getWMode().setVal(layoutMode.underlying);
    }

    public LayoutMode getHeightMode() {
        if (!this.layout.isSetHMode()) {
            return defaultLayoutMode;
        }
        return LayoutMode.valueOf(this.layout.getHMode().getVal());
    }

    public void setHeightMode(LayoutMode layoutMode) {
        if (!this.layout.isSetHMode()) {
            this.layout.addNewHMode();
        }
        this.layout.getHMode().setVal(layoutMode.underlying);
    }

    private void initializeLayout(CTLayout cTLayout) {
        if (cTLayout.isSetManualLayout()) {
            this.layout = cTLayout.getManualLayout();
        } else {
            this.layout = cTLayout.addNewManualLayout();
        }
    }
}
