package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STDispBlanksAs;

public interface CTDispBlanksAs extends XmlObject {
    public static final DocumentFactory<CTDispBlanksAs> Factory;
    public static final SchemaType type;

    STDispBlanksAs.Enum getVal();

    boolean isSetVal();

    void setVal(STDispBlanksAs.Enum enumR);

    void unsetVal();

    STDispBlanksAs xgetVal();

    void xsetVal(STDispBlanksAs sTDispBlanksAs);

    static {
        DocumentFactory<CTDispBlanksAs> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdispblanksas3069type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
