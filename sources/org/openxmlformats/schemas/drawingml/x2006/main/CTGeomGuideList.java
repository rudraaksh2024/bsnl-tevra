package org.openxmlformats.schemas.drawingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTGeomGuideList extends XmlObject {
    public static final DocumentFactory<CTGeomGuideList> Factory;
    public static final SchemaType type;

    CTGeomGuide addNewGd();

    CTGeomGuide getGdArray(int i);

    CTGeomGuide[] getGdArray();

    List<CTGeomGuide> getGdList();

    CTGeomGuide insertNewGd(int i);

    void removeGd(int i);

    void setGdArray(int i, CTGeomGuide cTGeomGuide);

    void setGdArray(CTGeomGuide[] cTGeomGuideArr);

    int sizeOfGdArray();

    static {
        DocumentFactory<CTGeomGuideList> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctgeomguidelist364ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
