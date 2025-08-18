package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTRelativeRect extends XmlObject {
    public static final DocumentFactory<CTRelativeRect> Factory;
    public static final SchemaType type;

    Object getB();

    Object getL();

    Object getR();

    Object getT();

    boolean isSetB();

    boolean isSetL();

    boolean isSetR();

    boolean isSetT();

    void setB(Object obj);

    void setL(Object obj);

    void setR(Object obj);

    void setT(Object obj);

    void unsetB();

    void unsetL();

    void unsetR();

    void unsetT();

    STPercentage xgetB();

    STPercentage xgetL();

    STPercentage xgetR();

    STPercentage xgetT();

    void xsetB(STPercentage sTPercentage);

    void xsetL(STPercentage sTPercentage);

    void xsetR(STPercentage sTPercentage);

    void xsetT(STPercentage sTPercentage);

    static {
        DocumentFactory<CTRelativeRect> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctrelativerecta4ebtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
