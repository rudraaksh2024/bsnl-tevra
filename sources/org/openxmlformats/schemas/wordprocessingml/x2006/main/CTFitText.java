package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.math.BigInteger;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTwipsMeasure;

public interface CTFitText extends XmlObject {
    public static final DocumentFactory<CTFitText> Factory;
    public static final SchemaType type;

    BigInteger getId();

    Object getVal();

    boolean isSetId();

    void setId(BigInteger bigInteger);

    void setVal(Object obj);

    void unsetId();

    STDecimalNumber xgetId();

    STTwipsMeasure xgetVal();

    void xsetId(STDecimalNumber sTDecimalNumber);

    void xsetVal(STTwipsMeasure sTTwipsMeasure);

    static {
        DocumentFactory<CTFitText> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfittexta474type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
