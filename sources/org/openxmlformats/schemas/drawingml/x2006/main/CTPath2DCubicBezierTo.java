package org.openxmlformats.schemas.drawingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTPath2DCubicBezierTo extends XmlObject {
    public static final DocumentFactory<CTPath2DCubicBezierTo> Factory;
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
        DocumentFactory<CTPath2DCubicBezierTo> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpath2dcubicbezierto5a1etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
