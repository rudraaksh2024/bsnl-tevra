package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLayoutTarget;

public interface CTLayoutTarget extends XmlObject {
    public static final DocumentFactory<CTLayoutTarget> Factory;
    public static final SchemaType type;

    STLayoutTarget.Enum getVal();

    boolean isSetVal();

    void setVal(STLayoutTarget.Enum enumR);

    void unsetVal();

    STLayoutTarget xgetVal();

    void xsetVal(STLayoutTarget sTLayoutTarget);

    static {
        DocumentFactory<CTLayoutTarget> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlayouttarget1001type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
