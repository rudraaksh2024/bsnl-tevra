package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.math.BigInteger;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation;

public interface CTPageSz extends XmlObject {
    public static final DocumentFactory<CTPageSz> Factory;
    public static final SchemaType type;

    BigInteger getCode();

    Object getH();

    STPageOrientation.Enum getOrient();

    Object getW();

    boolean isSetCode();

    boolean isSetH();

    boolean isSetOrient();

    boolean isSetW();

    void setCode(BigInteger bigInteger);

    void setH(Object obj);

    void setOrient(STPageOrientation.Enum enumR);

    void setW(Object obj);

    void unsetCode();

    void unsetH();

    void unsetOrient();

    void unsetW();

    STDecimalNumber xgetCode();

    STTwipsMeasure xgetH();

    STPageOrientation xgetOrient();

    STTwipsMeasure xgetW();

    void xsetCode(STDecimalNumber sTDecimalNumber);

    void xsetH(STTwipsMeasure sTTwipsMeasure);

    void xsetOrient(STPageOrientation sTPageOrientation);

    void xsetW(STTwipsMeasure sTTwipsMeasure);

    static {
        DocumentFactory<CTPageSz> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpagesz2d12type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
