package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STSplitType;

public interface CTSplitType extends XmlObject {
    public static final DocumentFactory<CTSplitType> Factory;
    public static final SchemaType type;

    STSplitType.Enum getVal();

    boolean isSetVal();

    void setVal(STSplitType.Enum enumR);

    void unsetVal();

    STSplitType xgetVal();

    void xsetVal(STSplitType sTSplitType);

    static {
        DocumentFactory<CTSplitType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsplittypeac32type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
