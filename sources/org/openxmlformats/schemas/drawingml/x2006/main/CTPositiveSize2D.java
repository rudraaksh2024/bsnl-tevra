package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTPositiveSize2D extends XmlObject {
    public static final DocumentFactory<CTPositiveSize2D> Factory;
    public static final SchemaType type;

    long getCx();

    long getCy();

    void setCx(long j);

    void setCy(long j);

    STPositiveCoordinate xgetCx();

    STPositiveCoordinate xgetCy();

    void xsetCx(STPositiveCoordinate sTPositiveCoordinate);

    void xsetCy(STPositiveCoordinate sTPositiveCoordinate);

    static {
        DocumentFactory<CTPositiveSize2D> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpositivesize2d0147type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
