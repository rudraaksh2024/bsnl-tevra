package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTLineJoinMiterProperties extends XmlObject {
    public static final DocumentFactory<CTLineJoinMiterProperties> Factory;
    public static final SchemaType type;

    Object getLim();

    boolean isSetLim();

    void setLim(Object obj);

    void unsetLim();

    STPositivePercentage xgetLim();

    void xsetLim(STPositivePercentage sTPositivePercentage);

    static {
        DocumentFactory<CTLineJoinMiterProperties> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlinejoinmiterproperties02abtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
