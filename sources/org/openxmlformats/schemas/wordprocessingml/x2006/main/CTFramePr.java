package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.math.BigInteger;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTwipsMeasure;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXAlign;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STYAlign;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHAnchor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHeightRule;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVAnchor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STWrap;

public interface CTFramePr extends XmlObject {
    public static final DocumentFactory<CTFramePr> Factory;
    public static final SchemaType type;

    Object getAnchorLock();

    STDropCap$Enum getDropCap();

    Object getH();

    STHAnchor.Enum getHAnchor();

    STHeightRule.Enum getHRule();

    Object getHSpace();

    BigInteger getLines();

    STVAnchor.Enum getVAnchor();

    Object getVSpace();

    Object getW();

    STWrap.Enum getWrap();

    Object getX();

    STXAlign.Enum getXAlign();

    Object getY();

    STYAlign.Enum getYAlign();

    boolean isSetAnchorLock();

    boolean isSetDropCap();

    boolean isSetH();

    boolean isSetHAnchor();

    boolean isSetHRule();

    boolean isSetHSpace();

    boolean isSetLines();

    boolean isSetVAnchor();

    boolean isSetVSpace();

    boolean isSetW();

    boolean isSetWrap();

    boolean isSetX();

    boolean isSetXAlign();

    boolean isSetY();

    boolean isSetYAlign();

    void setAnchorLock(Object obj);

    void setDropCap(STDropCap$Enum sTDropCap$Enum);

    void setH(Object obj);

    void setHAnchor(STHAnchor.Enum enumR);

    void setHRule(STHeightRule.Enum enumR);

    void setHSpace(Object obj);

    void setLines(BigInteger bigInteger);

    void setVAnchor(STVAnchor.Enum enumR);

    void setVSpace(Object obj);

    void setW(Object obj);

    void setWrap(STWrap.Enum enumR);

    void setX(Object obj);

    void setXAlign(STXAlign.Enum enumR);

    void setY(Object obj);

    void setYAlign(STYAlign.Enum enumR);

    void unsetAnchorLock();

    void unsetDropCap();

    void unsetH();

    void unsetHAnchor();

    void unsetHRule();

    void unsetHSpace();

    void unsetLines();

    void unsetVAnchor();

    void unsetVSpace();

    void unsetW();

    void unsetWrap();

    void unsetX();

    void unsetXAlign();

    void unsetY();

    void unsetYAlign();

    STOnOff xgetAnchorLock();

    STDropCap xgetDropCap();

    STTwipsMeasure xgetH();

    STHAnchor xgetHAnchor();

    STHeightRule xgetHRule();

    STTwipsMeasure xgetHSpace();

    STDecimalNumber xgetLines();

    STVAnchor xgetVAnchor();

    STTwipsMeasure xgetVSpace();

    STTwipsMeasure xgetW();

    STWrap xgetWrap();

    STSignedTwipsMeasure xgetX();

    STXAlign xgetXAlign();

    STSignedTwipsMeasure xgetY();

    STYAlign xgetYAlign();

    void xsetAnchorLock(STOnOff sTOnOff);

    void xsetDropCap(STDropCap sTDropCap);

    void xsetH(STTwipsMeasure sTTwipsMeasure);

    void xsetHAnchor(STHAnchor sTHAnchor);

    void xsetHRule(STHeightRule sTHeightRule);

    void xsetHSpace(STTwipsMeasure sTTwipsMeasure);

    void xsetLines(STDecimalNumber sTDecimalNumber);

    void xsetVAnchor(STVAnchor sTVAnchor);

    void xsetVSpace(STTwipsMeasure sTTwipsMeasure);

    void xsetW(STTwipsMeasure sTTwipsMeasure);

    void xsetWrap(STWrap sTWrap);

    void xsetX(STSignedTwipsMeasure sTSignedTwipsMeasure);

    void xsetXAlign(STXAlign sTXAlign);

    void xsetY(STSignedTwipsMeasure sTSignedTwipsMeasure);

    void xsetYAlign(STYAlign sTYAlign);

    static {
        DocumentFactory<CTFramePr> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctframepr12a3type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
