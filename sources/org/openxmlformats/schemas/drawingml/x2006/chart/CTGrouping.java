package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STGrouping;

public interface CTGrouping extends XmlObject {
    public static final DocumentFactory<CTGrouping> Factory;
    public static final SchemaType type;

    STGrouping.Enum getVal();

    boolean isSetVal();

    void setVal(STGrouping.Enum enumR);

    void unsetVal();

    STGrouping xgetVal();

    void xsetVal(STGrouping sTGrouping);

    static {
        DocumentFactory<CTGrouping> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctgroupingdcd9type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
