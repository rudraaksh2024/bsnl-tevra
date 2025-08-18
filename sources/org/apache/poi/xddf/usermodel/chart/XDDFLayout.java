package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLayout;

public class XDDFLayout {
    private CTLayout layout;

    public XDDFLayout() {
        this(CTLayout.Factory.newInstance());
    }

    @Internal
    protected XDDFLayout(CTLayout cTLayout) {
        this.layout = cTLayout;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTLayout getXmlObject() {
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

    public void setManualLayout(XDDFManualLayout xDDFManualLayout) {
        if (xDDFManualLayout != null) {
            this.layout.setManualLayout(xDDFManualLayout.getXmlObject());
        } else if (this.layout.isSetManualLayout()) {
            this.layout.unsetManualLayout();
        }
    }

    public XDDFManualLayout getManualLayout() {
        if (this.layout.isSetManualLayout()) {
            return new XDDFManualLayout(this.layout);
        }
        return null;
    }
}
