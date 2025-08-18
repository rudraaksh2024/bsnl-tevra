package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.math.BigInteger;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;

public interface CTSpacing extends XmlObject {
    public static final DocumentFactory<CTSpacing> Factory;
    public static final SchemaType type;

    Object getAfter();

    Object getAfterAutospacing();

    BigInteger getAfterLines();

    Object getBefore();

    Object getBeforeAutospacing();

    BigInteger getBeforeLines();

    Object getLine();

    STLineSpacingRule.Enum getLineRule();

    boolean isSetAfter();

    boolean isSetAfterAutospacing();

    boolean isSetAfterLines();

    boolean isSetBefore();

    boolean isSetBeforeAutospacing();

    boolean isSetBeforeLines();

    boolean isSetLine();

    boolean isSetLineRule();

    void setAfter(Object obj);

    void setAfterAutospacing(Object obj);

    void setAfterLines(BigInteger bigInteger);

    void setBefore(Object obj);

    void setBeforeAutospacing(Object obj);

    void setBeforeLines(BigInteger bigInteger);

    void setLine(Object obj);

    void setLineRule(STLineSpacingRule.Enum enumR);

    void unsetAfter();

    void unsetAfterAutospacing();

    void unsetAfterLines();

    void unsetBefore();

    void unsetBeforeAutospacing();

    void unsetBeforeLines();

    void unsetLine();

    void unsetLineRule();

    STTwipsMeasure xgetAfter();

    STOnOff xgetAfterAutospacing();

    STDecimalNumber xgetAfterLines();

    STTwipsMeasure xgetBefore();

    STOnOff xgetBeforeAutospacing();

    STDecimalNumber xgetBeforeLines();

    STSignedTwipsMeasure xgetLine();

    STLineSpacingRule xgetLineRule();

    void xsetAfter(STTwipsMeasure sTTwipsMeasure);

    void xsetAfterAutospacing(STOnOff sTOnOff);

    void xsetAfterLines(STDecimalNumber sTDecimalNumber);

    void xsetBefore(STTwipsMeasure sTTwipsMeasure);

    void xsetBeforeAutospacing(STOnOff sTOnOff);

    void xsetBeforeLines(STDecimalNumber sTDecimalNumber);

    void xsetLine(STSignedTwipsMeasure sTSignedTwipsMeasure);

    void xsetLineRule(STLineSpacingRule sTLineSpacingRule);

    static {
        DocumentFactory<CTSpacing> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctspacingff2ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
