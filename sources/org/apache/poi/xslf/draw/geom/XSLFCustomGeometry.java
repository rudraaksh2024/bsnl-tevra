package org.apache.poi.xslf.draw.geom;

import org.apache.poi.sl.draw.geom.CustomGeometry;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSite;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle;

public class XSLFCustomGeometry {
    public static CustomGeometry convertCustomGeometry(CTCustomGeometry2D cTCustomGeometry2D) {
        CustomGeometry customGeometry = new CustomGeometry();
        if (cTCustomGeometry2D.isSetAhLst()) {
            CTAdjustHandleList ahLst = cTCustomGeometry2D.getAhLst();
            for (CTXYAdjustHandle xSLFXYAdjustHandle : ahLst.getAhXYArray()) {
                customGeometry.addAdjustHandle(new XSLFXYAdjustHandle(xSLFXYAdjustHandle));
            }
            for (CTPolarAdjustHandle xSLFPolarAdjustHandle : ahLst.getAhPolarArray()) {
                customGeometry.addAdjustHandle(new XSLFPolarAdjustHandle(xSLFPolarAdjustHandle));
            }
        }
        if (cTCustomGeometry2D.isSetAvLst()) {
            for (CTGeomGuide xSLFAdjustValue : cTCustomGeometry2D.getAvLst().getGdArray()) {
                customGeometry.addAdjustGuide(new XSLFAdjustValue(xSLFAdjustValue));
            }
        }
        if (cTCustomGeometry2D.isSetGdLst()) {
            for (CTGeomGuide xSLFGuide : cTCustomGeometry2D.getGdLst().getGdArray()) {
                customGeometry.addGeomGuide(new XSLFGuide(xSLFGuide));
            }
        }
        if (cTCustomGeometry2D.isSetRect()) {
            CTGeomRect rect = cTCustomGeometry2D.getRect();
            customGeometry.setTextBounds(rect.xgetL().getStringValue(), rect.xgetT().getStringValue(), rect.xgetR().getStringValue(), rect.xgetB().getStringValue());
        }
        if (cTCustomGeometry2D.isSetCxnLst()) {
            for (CTConnectionSite xSLFConnectionSite : cTCustomGeometry2D.getCxnLst().getCxnArray()) {
                customGeometry.addConnectionSite(new XSLFConnectionSite(xSLFConnectionSite));
            }
        }
        CTPath2DList pathLst = cTCustomGeometry2D.getPathLst();
        if (pathLst != null) {
            for (CTPath2D xSLFPath : pathLst.getPathArray()) {
                customGeometry.addPath(new XSLFPath(xSLFPath));
            }
        }
        return customGeometry;
    }
}
