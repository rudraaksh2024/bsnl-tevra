package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STBarDir;

public interface CTBarDir extends XmlObject {
    public static final DocumentFactory<CTBarDir> Factory;
    public static final SchemaType type;

    STBarDir.Enum getVal();

    boolean isSetVal();

    void setVal(STBarDir.Enum enumR);

    void unsetVal();

    STBarDir xgetVal();

    void xsetVal(STBarDir sTBarDir);

    static {
        DocumentFactory<CTBarDir> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctbardir5f42type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
