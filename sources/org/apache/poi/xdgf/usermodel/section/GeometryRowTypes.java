package org.apache.poi.xdgf.usermodel.section;

import com.microsoft.schemas.office.visio.x2012.main.RowType;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.util.Internal;
import org.apache.poi.xdgf.usermodel.section.geometry.GeometryRow;

@Internal
enum GeometryRowTypes {
    ARC_TO("ArcTo", new GeometryRowTypes$$ExternalSyntheticLambda0()),
    ELLIPSE("Ellipse", new GeometryRowTypes$$ExternalSyntheticLambda13()),
    ELLIPTICAL_ARC_TO("EllipticalArcTo", new GeometryRowTypes$$ExternalSyntheticLambda14()),
    INFINITE_LINE("InfiniteLine", new GeometryRowTypes$$ExternalSyntheticLambda15()),
    LINE_TO("LineTo", new GeometryRowTypes$$ExternalSyntheticLambda1()),
    MOVE_TO("MoveTo", new GeometryRowTypes$$ExternalSyntheticLambda2()),
    NURBS_TO("NURBSTo", new GeometryRowTypes$$ExternalSyntheticLambda3()),
    POLYLINE_TO("PolylineTo", new GeometryRowTypes$$ExternalSyntheticLambda4()),
    REL_CUB_BEZ_TO("RelCubBezTo", new GeometryRowTypes$$ExternalSyntheticLambda5()),
    REL_ELLIPTICAL_ARC_TO("RelEllipticalArcTo", new GeometryRowTypes$$ExternalSyntheticLambda6()),
    REL_LINE_TO("RelLineTo", new GeometryRowTypes$$ExternalSyntheticLambda7()),
    REL_MOVE_TO("RelMoveTo", new GeometryRowTypes$$ExternalSyntheticLambda8()),
    REL_QUAD_BEZ_TO("RelQuadBezTo", new GeometryRowTypes$$ExternalSyntheticLambda9()),
    SPLINE_KNOT("SplineKnot", new GeometryRowTypes$$ExternalSyntheticLambda10()),
    SPLINE_START("SplineStart", new GeometryRowTypes$$ExternalSyntheticLambda11());
    
    private static final Map<String, GeometryRowTypes> LOOKUP = null;
    private final Function<RowType, ? extends GeometryRow> constructor;
    private final String rowType;

    static {
        LOOKUP = (Map) Stream.of(values()).collect(Collectors.toMap(new GeometryRowTypes$$ExternalSyntheticLambda12(), Function.identity()));
    }

    private GeometryRowTypes(String str, Function<RowType, ? extends GeometryRow> function) {
        this.rowType = str;
        this.constructor = function;
    }

    public String getRowType() {
        return this.rowType;
    }

    public static GeometryRow load(RowType rowType2) {
        String t = rowType2.getT();
        GeometryRowTypes geometryRowTypes = LOOKUP.get(t);
        if (geometryRowTypes != null) {
            return (GeometryRow) geometryRowTypes.constructor.apply(rowType2);
        }
        throw new POIXMLException("Invalid '" + rowType2.schemaType().getName().getLocalPart() + "' name '" + t + "'");
    }
}
