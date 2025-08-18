package org.apache.poi.xslf.usermodel;

import java.util.Comparator;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientStop;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XSLFGradientPaint$$ExternalSyntheticLambda0 implements Comparator {
    public final int compare(Object obj, Object obj2) {
        return Integer.compare(POIXMLUnits.parsePercent(((CTGradientStop) obj).xgetPos()), POIXMLUnits.parsePercent(((CTGradientStop) obj2).xgetPos()));
    }
}
