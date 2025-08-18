package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.math.BigInteger;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTwipsMeasure;

public interface CTInd extends XmlObject {
    public static final DocumentFactory<CTInd> Factory;
    public static final SchemaType type;

    Object getEnd();

    BigInteger getEndChars();

    Object getFirstLine();

    BigInteger getFirstLineChars();

    Object getHanging();

    BigInteger getHangingChars();

    Object getLeft();

    BigInteger getLeftChars();

    Object getRight();

    BigInteger getRightChars();

    Object getStart();

    BigInteger getStartChars();

    boolean isSetEnd();

    boolean isSetEndChars();

    boolean isSetFirstLine();

    boolean isSetFirstLineChars();

    boolean isSetHanging();

    boolean isSetHangingChars();

    boolean isSetLeft();

    boolean isSetLeftChars();

    boolean isSetRight();

    boolean isSetRightChars();

    boolean isSetStart();

    boolean isSetStartChars();

    void setEnd(Object obj);

    void setEndChars(BigInteger bigInteger);

    void setFirstLine(Object obj);

    void setFirstLineChars(BigInteger bigInteger);

    void setHanging(Object obj);

    void setHangingChars(BigInteger bigInteger);

    void setLeft(Object obj);

    void setLeftChars(BigInteger bigInteger);

    void setRight(Object obj);

    void setRightChars(BigInteger bigInteger);

    void setStart(Object obj);

    void setStartChars(BigInteger bigInteger);

    void unsetEnd();

    void unsetEndChars();

    void unsetFirstLine();

    void unsetFirstLineChars();

    void unsetHanging();

    void unsetHangingChars();

    void unsetLeft();

    void unsetLeftChars();

    void unsetRight();

    void unsetRightChars();

    void unsetStart();

    void unsetStartChars();

    STSignedTwipsMeasure xgetEnd();

    STDecimalNumber xgetEndChars();

    STTwipsMeasure xgetFirstLine();

    STDecimalNumber xgetFirstLineChars();

    STTwipsMeasure xgetHanging();

    STDecimalNumber xgetHangingChars();

    STSignedTwipsMeasure xgetLeft();

    STDecimalNumber xgetLeftChars();

    STSignedTwipsMeasure xgetRight();

    STDecimalNumber xgetRightChars();

    STSignedTwipsMeasure xgetStart();

    STDecimalNumber xgetStartChars();

    void xsetEnd(STSignedTwipsMeasure sTSignedTwipsMeasure);

    void xsetEndChars(STDecimalNumber sTDecimalNumber);

    void xsetFirstLine(STTwipsMeasure sTTwipsMeasure);

    void xsetFirstLineChars(STDecimalNumber sTDecimalNumber);

    void xsetHanging(STTwipsMeasure sTTwipsMeasure);

    void xsetHangingChars(STDecimalNumber sTDecimalNumber);

    void xsetLeft(STSignedTwipsMeasure sTSignedTwipsMeasure);

    void xsetLeftChars(STDecimalNumber sTDecimalNumber);

    void xsetRight(STSignedTwipsMeasure sTSignedTwipsMeasure);

    void xsetRightChars(STDecimalNumber sTDecimalNumber);

    void xsetStart(STSignedTwipsMeasure sTSignedTwipsMeasure);

    void xsetStartChars(STDecimalNumber sTDecimalNumber);

    static {
        DocumentFactory<CTInd> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctind4b93type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
