package org.apache.poi.xddf.usermodel.chart;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.text.TextContainer;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegend;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegendEntry;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

public final class XDDFChartLegend implements TextContainer {
    private CTLegend legend;

    public XDDFChartLegend(CTChart cTChart) {
        this.legend = cTChart.isSetLegend() ? cTChart.getLegend() : cTChart.addNewLegend();
        setDefaults();
    }

    private void setDefaults() {
        if (!this.legend.isSetOverlay()) {
            this.legend.addNewOverlay();
        }
        this.legend.getOverlay().setVal(false);
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTLegend getXmlObject() {
        return this.legend;
    }

    @Internal
    public CTShapeProperties getShapeProperties() {
        if (this.legend.isSetSpPr()) {
            return this.legend.getSpPr();
        }
        return null;
    }

    @Internal
    public void setShapeProperties(CTShapeProperties cTShapeProperties) {
        if (cTShapeProperties != null) {
            this.legend.setSpPr(cTShapeProperties);
        } else if (this.legend.isSetSpPr()) {
            this.legend.unsetSpPr();
        }
    }

    public XDDFTextBody getTextBody() {
        if (this.legend.isSetTxPr()) {
            return new XDDFTextBody(this, this.legend.getTxPr());
        }
        return null;
    }

    public void setTextBody(XDDFTextBody xDDFTextBody) {
        if (xDDFTextBody != null) {
            this.legend.setTxPr(xDDFTextBody.getXmlObject());
        } else if (this.legend.isSetTxPr()) {
            this.legend.unsetTxPr();
        }
    }

    public XDDFLegendEntry addEntry() {
        return new XDDFLegendEntry(this.legend.addNewLegendEntry());
    }

    public XDDFLegendEntry getEntry(int i) {
        return new XDDFLegendEntry(this.legend.getLegendEntryArray(i));
    }

    public List<XDDFLegendEntry> getEntries() {
        return (List) this.legend.getLegendEntryList().stream().map(new XDDFChartLegend$$ExternalSyntheticLambda0()).collect(Collectors.toList());
    }

    static /* synthetic */ XDDFLegendEntry lambda$getEntries$0(CTLegendEntry cTLegendEntry) {
        return new XDDFLegendEntry(cTLegendEntry);
    }

    public void setExtensionList(XDDFChartExtensionList xDDFChartExtensionList) {
        if (xDDFChartExtensionList != null) {
            this.legend.setExtLst(xDDFChartExtensionList.getXmlObject());
        } else if (this.legend.isSetExtLst()) {
            this.legend.unsetExtLst();
        }
    }

    public XDDFChartExtensionList getExtensionList() {
        if (this.legend.isSetExtLst()) {
            return new XDDFChartExtensionList(this.legend.getExtLst());
        }
        return null;
    }

    public void setLayout(XDDFLayout xDDFLayout) {
        if (xDDFLayout != null) {
            this.legend.setLayout(xDDFLayout.getXmlObject());
        } else if (this.legend.isSetLayout()) {
            this.legend.unsetLayout();
        }
    }

    public XDDFLayout getLayout() {
        if (this.legend.isSetLayout()) {
            return new XDDFLayout(this.legend.getLayout());
        }
        return null;
    }

    public void setPosition(LegendPosition legendPosition) {
        if (!this.legend.isSetLegendPos()) {
            this.legend.addNewLegendPos();
        }
        this.legend.getLegendPos().setVal(legendPosition.underlying);
    }

    public LegendPosition getPosition() {
        if (this.legend.isSetLegendPos()) {
            return LegendPosition.valueOf(this.legend.getLegendPos().getVal());
        }
        return LegendPosition.RIGHT;
    }

    public XDDFManualLayout getOrAddManualLayout() {
        if (!this.legend.isSetLayout()) {
            this.legend.addNewLayout();
        }
        return new XDDFManualLayout(this.legend.getLayout());
    }

    public boolean isOverlay() {
        return this.legend.getOverlay().getVal();
    }

    public void setOverlay(boolean z) {
        this.legend.getOverlay().setVal(z);
    }

    public <R> Optional<R> findDefinedParagraphProperty(Predicate<CTTextParagraphProperties> predicate, Function<CTTextParagraphProperties, R> function) {
        return Optional.empty();
    }

    public <R> Optional<R> findDefinedRunProperty(Predicate<CTTextCharacterProperties> predicate, Function<CTTextCharacterProperties, R> function) {
        return Optional.empty();
    }
}
