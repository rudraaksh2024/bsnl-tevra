package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTSignedTwipsMeasure extends XmlObject {
    public static final DocumentFactory<CTSignedTwipsMeasure> Factory;
    public static final SchemaType type;

    Object getVal();

    void setVal(Object obj);

    STSignedTwipsMeasure xgetVal();

    void xsetVal(STSignedTwipsMeasure sTSignedTwipsMeasure);

    static {
        DocumentFactory<CTSignedTwipsMeasure> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsignedtwipsmeasure1037type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
