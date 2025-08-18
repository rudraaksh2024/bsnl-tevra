package org.apache.poi.xddf.usermodel.chart;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.text.TextContainer;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegendEntry;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

public class XDDFLegendEntry implements TextContainer {
    private CTLegendEntry entry;

    @Internal
    protected XDDFLegendEntry(CTLegendEntry cTLegendEntry) {
        this.entry = cTLegendEntry;
        if (cTLegendEntry.getIdx() == null) {
            cTLegendEntry.addNewIdx().setVal(0);
        }
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTLegendEntry getXmlObject() {
        return this.entry;
    }

    public XDDFTextBody getTextBody() {
        if (this.entry.isSetTxPr()) {
            return new XDDFTextBody(this, this.entry.getTxPr());
        }
        return null;
    }

    public void setTextBody(XDDFTextBody xDDFTextBody) {
        if (xDDFTextBody != null) {
            this.entry.setTxPr(xDDFTextBody.getXmlObject());
        } else if (this.entry.isSetTxPr()) {
            this.entry.unsetTxPr();
        }
    }

    public boolean getDelete() {
        if (this.entry.isSetDelete()) {
            return this.entry.getDelete().getVal();
        }
        return false;
    }

    public void setDelete(Boolean bool) {
        if (bool == null) {
            if (this.entry.isSetDelete()) {
                this.entry.unsetDelete();
            }
        } else if (this.entry.isSetDelete()) {
            this.entry.getDelete().setVal(bool.booleanValue());
        } else {
            this.entry.addNewDelete().setVal(bool.booleanValue());
        }
    }

    public long getIndex() {
        return this.entry.getIdx().getVal();
    }

    public void setIndex(long j) {
        this.entry.getIdx().setVal(j);
    }

    public void setExtensionList(XDDFChartExtensionList xDDFChartExtensionList) {
        if (xDDFChartExtensionList != null) {
            this.entry.setExtLst(xDDFChartExtensionList.getXmlObject());
        } else if (this.entry.isSetExtLst()) {
            this.entry.unsetExtLst();
        }
    }

    public XDDFChartExtensionList getExtensionList() {
        if (this.entry.isSetExtLst()) {
            return new XDDFChartExtensionList(this.entry.getExtLst());
        }
        return null;
    }

    public <R> Optional<R> findDefinedParagraphProperty(Predicate<CTTextParagraphProperties> predicate, Function<CTTextParagraphProperties, R> function) {
        return Optional.empty();
    }

    public <R> Optional<R> findDefinedRunProperty(Predicate<CTTextCharacterProperties> predicate, Function<CTTextCharacterProperties, R> function) {
        return Optional.empty();
    }
}
