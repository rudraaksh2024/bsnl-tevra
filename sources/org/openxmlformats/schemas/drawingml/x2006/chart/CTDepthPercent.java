package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTDepthPercent extends XmlObject {
    public static final DocumentFactory<CTDepthPercent> Factory;
    public static final SchemaType type;

    Object getVal();

    boolean isSetVal();

    void setVal(Object obj);

    void unsetVal();

    STDepthPercent xgetVal();

    void xsetVal(STDepthPercent sTDepthPercent);

    static {
        DocumentFactory<CTDepthPercent> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdepthpercent117atype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
