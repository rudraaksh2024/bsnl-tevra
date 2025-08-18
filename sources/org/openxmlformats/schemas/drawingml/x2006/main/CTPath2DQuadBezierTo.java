package org.openxmlformats.schemas.drawingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTPath2DQuadBezierTo extends XmlObject {
    public static final DocumentFactory<CTPath2DQuadBezierTo> Factory;
    public static final SchemaType type;

    CTAdjPoint2D addNewPt();

    CTAdjPoint2D getPtArray(int i);

    CTAdjPoint2D[] getPtArray();

    List<CTAdjPoint2D> getPtList();

    CTAdjPoint2D insertNewPt(int i);

    void removePt(int i);

    void setPtArray(int i, CTAdjPoint2D cTAdjPoint2D);

    void setPtArray(CTAdjPoint2D[] cTAdjPoint2DArr);

    int sizeOfPtArray();

    static {
        DocumentFactory<CTPath2DQuadBezierTo> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpath2dquadbezierto3f53type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
