package org.apache.poi.xddf.usermodel.text;

import java.util.function.Function;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate32;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XDDFTextParagraph$$ExternalSyntheticLambda15 implements Function {
    public final Object apply(Object obj) {
        return Long.valueOf(POIXMLUnits.parseLength((STCoordinate32) obj));
    }
}
