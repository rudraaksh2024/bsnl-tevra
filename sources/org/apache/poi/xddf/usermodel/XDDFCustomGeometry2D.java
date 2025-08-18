package org.apache.poi.xddf.usermodel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSite;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle;

public class XDDFCustomGeometry2D {
    private CTCustomGeometry2D geometry;

    protected XDDFCustomGeometry2D(CTCustomGeometry2D cTCustomGeometry2D) {
        this.geometry = cTCustomGeometry2D;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTCustomGeometry2D getXmlObject() {
        return this.geometry;
    }

    public XDDFGeometryRectangle getRectangle() {
        if (this.geometry.isSetRect()) {
            return new XDDFGeometryRectangle(this.geometry.getRect());
        }
        return null;
    }

    public void setRectangle(XDDFGeometryRectangle xDDFGeometryRectangle) {
        if (xDDFGeometryRectangle != null) {
            this.geometry.setRect(xDDFGeometryRectangle.getXmlObject());
        } else if (this.geometry.isSetRect()) {
            this.geometry.unsetRect();
        }
    }

    public XDDFAdjustHandlePolar addPolarAdjustHandle() {
        if (!this.geometry.isSetAhLst()) {
            this.geometry.addNewAhLst();
        }
        return new XDDFAdjustHandlePolar(this.geometry.getAhLst().addNewAhPolar());
    }

    public XDDFAdjustHandlePolar insertPolarAdjustHandle(int i) {
        if (!this.geometry.isSetAhLst()) {
            this.geometry.addNewAhLst();
        }
        return new XDDFAdjustHandlePolar(this.geometry.getAhLst().insertNewAhPolar(i));
    }

    public void removePolarAdjustHandle(int i) {
        if (this.geometry.isSetAhLst()) {
            this.geometry.getAhLst().removeAhPolar(i);
        }
    }

    public XDDFAdjustHandlePolar getPolarAdjustHandle(int i) {
        if (this.geometry.isSetAhLst()) {
            return new XDDFAdjustHandlePolar(this.geometry.getAhLst().getAhPolarArray(i));
        }
        return null;
    }

    public List<XDDFAdjustHandlePolar> getPolarAdjustHandles() {
        if (this.geometry.isSetAhLst()) {
            return Collections.unmodifiableList((List) this.geometry.getAhLst().getAhPolarList().stream().map(new XDDFCustomGeometry2D$$ExternalSyntheticLambda2()).collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }

    static /* synthetic */ XDDFAdjustHandlePolar lambda$getPolarAdjustHandles$0(CTPolarAdjustHandle cTPolarAdjustHandle) {
        return new XDDFAdjustHandlePolar(cTPolarAdjustHandle);
    }

    public XDDFAdjustHandleXY addXYAdjustHandle() {
        if (!this.geometry.isSetAhLst()) {
            this.geometry.addNewAhLst();
        }
        return new XDDFAdjustHandleXY(this.geometry.getAhLst().addNewAhXY());
    }

    public XDDFAdjustHandleXY insertXYAdjustHandle(int i) {
        if (!this.geometry.isSetAhLst()) {
            this.geometry.addNewAhLst();
        }
        return new XDDFAdjustHandleXY(this.geometry.getAhLst().insertNewAhXY(i));
    }

    public void removeXYAdjustHandle(int i) {
        if (this.geometry.isSetAhLst()) {
            this.geometry.getAhLst().removeAhXY(i);
        }
    }

    public XDDFAdjustHandleXY getXYAdjustHandle(int i) {
        if (this.geometry.isSetAhLst()) {
            return new XDDFAdjustHandleXY(this.geometry.getAhLst().getAhXYArray(i));
        }
        return null;
    }

    public List<XDDFAdjustHandleXY> getXYAdjustHandles() {
        if (this.geometry.isSetAhLst()) {
            return Collections.unmodifiableList((List) this.geometry.getAhLst().getAhXYList().stream().map(new XDDFCustomGeometry2D$$ExternalSyntheticLambda1()).collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }

    static /* synthetic */ XDDFAdjustHandleXY lambda$getXYAdjustHandles$1(CTXYAdjustHandle cTXYAdjustHandle) {
        return new XDDFAdjustHandleXY(cTXYAdjustHandle);
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
            return Collections.unmodifiableList((List) this.geometry.getAvLst().getGdList().stream().map(new XDDFCustomGeometry2D$$ExternalSyntheticLambda3()).collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }

    static /* synthetic */ XDDFGeometryGuide lambda$getAdjustValues$2(CTGeomGuide cTGeomGuide) {
        return new XDDFGeometryGuide(cTGeomGuide);
    }

    public XDDFConnectionSite addConnectionSite() {
        if (!this.geometry.isSetCxnLst()) {
            this.geometry.addNewCxnLst();
        }
        return new XDDFConnectionSite(this.geometry.getCxnLst().addNewCxn());
    }

    public XDDFConnectionSite insertConnectionSite(int i) {
        if (!this.geometry.isSetCxnLst()) {
            this.geometry.addNewCxnLst();
        }
        return new XDDFConnectionSite(this.geometry.getCxnLst().insertNewCxn(i));
    }

    public void removeConnectionSite(int i) {
        if (this.geometry.isSetCxnLst()) {
            this.geometry.getCxnLst().removeCxn(i);
        }
    }

    public XDDFConnectionSite getConnectionSite(int i) {
        if (this.geometry.isSetCxnLst()) {
            return new XDDFConnectionSite(this.geometry.getCxnLst().getCxnArray(i));
        }
        return null;
    }

    public List<XDDFConnectionSite> getConnectionSites() {
        if (this.geometry.isSetCxnLst()) {
            return Collections.unmodifiableList((List) this.geometry.getCxnLst().getCxnList().stream().map(new XDDFCustomGeometry2D$$ExternalSyntheticLambda4()).collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }

    static /* synthetic */ XDDFConnectionSite lambda$getConnectionSites$3(CTConnectionSite cTConnectionSite) {
        return new XDDFConnectionSite(cTConnectionSite);
    }

    public XDDFGeometryGuide addGuide() {
        if (!this.geometry.isSetGdLst()) {
            this.geometry.addNewGdLst();
        }
        return new XDDFGeometryGuide(this.geometry.getGdLst().addNewGd());
    }

    public XDDFGeometryGuide insertGuide(int i) {
        if (!this.geometry.isSetGdLst()) {
            this.geometry.addNewGdLst();
        }
        return new XDDFGeometryGuide(this.geometry.getGdLst().insertNewGd(i));
    }

    public void removeGuide(int i) {
        if (this.geometry.isSetGdLst()) {
            this.geometry.getGdLst().removeGd(i);
        }
    }

    public XDDFGeometryGuide getGuide(int i) {
        if (this.geometry.isSetGdLst()) {
            return new XDDFGeometryGuide(this.geometry.getGdLst().getGdArray(i));
        }
        return null;
    }

    public List<XDDFGeometryGuide> getGuides() {
        if (this.geometry.isSetGdLst()) {
            return Collections.unmodifiableList((List) this.geometry.getGdLst().getGdList().stream().map(new XDDFCustomGeometry2D$$ExternalSyntheticLambda5()).collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }

    static /* synthetic */ XDDFGeometryGuide lambda$getGuides$4(CTGeomGuide cTGeomGuide) {
        return new XDDFGeometryGuide(cTGeomGuide);
    }

    public XDDFPath addNewPath() {
        return new XDDFPath(this.geometry.getPathLst().addNewPath());
    }

    public XDDFPath insertNewPath(int i) {
        return new XDDFPath(this.geometry.getPathLst().insertNewPath(i));
    }

    public void removePath(int i) {
        this.geometry.getPathLst().removePath(i);
    }

    public XDDFPath getPath(int i) {
        return new XDDFPath(this.geometry.getPathLst().getPathArray(i));
    }

    public List<XDDFPath> getPaths() {
        return Collections.unmodifiableList((List) this.geometry.getPathLst().getPathList().stream().map(new XDDFCustomGeometry2D$$ExternalSyntheticLambda0()).collect(Collectors.toList()));
    }

    static /* synthetic */ XDDFPath lambda$getPaths$5(CTPath2D cTPath2D) {
        return new XDDFPath(cTPath2D);
    }
}
