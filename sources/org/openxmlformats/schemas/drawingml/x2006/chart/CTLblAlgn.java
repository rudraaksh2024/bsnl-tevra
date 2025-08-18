package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLblAlgn;

public interface CTLblAlgn extends XmlObject {
    public static final DocumentFactory<CTLblAlgn> Factory;
    public static final SchemaType type;

    STLblAlgn.Enum getVal();

    void setVal(STLblAlgn.Enum enumR);

    STLblAlgn xgetVal();

    void xsetVal(STLblAlgn sTLblAlgn);

    static {
        DocumentFactory<CTLblAlgn> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlblalgn133etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
