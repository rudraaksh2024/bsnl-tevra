package org.apache.poi.xddf.usermodel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;

public class XDDFPresetGeometry2D {
    private CTPresetGeometry2D geometry;

    protected XDDFPresetGeometry2D(CTPresetGeometry2D cTPresetGeometry2D) {
        this.geometry = cTPresetGeometry2D;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTPresetGeometry2D getXmlObject() {
        return this.geometry;
    }

    public PresetGeometry getGeometry() {
        return PresetGeometry.valueOf(this.geometry.getPrst());
    }

    public void setGeometry(PresetGeometry presetGeometry) {
        this.geometry.setPrst(presetGeometry.underlying);
    }

    public XDDFGeometryGuide addAdjustValue() {
        if (!this.geometry.isSetAvLst()) {
            this.geometry.addNewAvLst();
        }
        return new XDDFGeometryGuide(this.geometry.getAvLst().addNewGd());
    }

    public XDDFGeometryGuide insertAdjustValue(int i) {
        if (!this.geometry.isSetAvLst()) {
            this.geometry.addNewAvLst();
        }
        return new XDDFGeometryGuide(this.geometry.getAvLst().insertNewGd(i));
    }

    public void removeAdjustValue(int i) {
        if (this.geometry.isSetAvLst()) {
            this.geometry.getAvLst().removeGd(i);
        }
    }

    public XDDFGeometryGuide getAdjustValue(int i) {
        if (this.geometry.isSetAvLst()) {
            return new XDDFGeometryGuide(this.geometry.getAvLst().getGdArray(i));
        }
        return null;
    }

    public List<XDDFGeometryGuide> getAdjustValues() {
        if (this.geometry.isSetAvLst()) {
            return Collections.unmodifiableList((List) this.geometry.getAvLst().getGdList().stream().map(new XDDFPresetGeometry2D$$ExternalSyntheticLambda0()).collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }

    static /* synthetic */ XDDFGeometryGuide lambda$getAdjustValues$0(CTGeomGuide cTGeomGuide) {
        return new XDDFGeometryGuide(cTGeomGuide);
    }
}
