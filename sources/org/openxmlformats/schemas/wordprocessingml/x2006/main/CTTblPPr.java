package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTwipsMeasure;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXAlign;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STYAlign;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHAnchor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVAnchor;

public interface CTTblPPr extends XmlObject {
    public static final DocumentFactory<CTTblPPr> Factory;
    public static final SchemaType type;

    Object getBottomFromText();

    STHAnchor.Enum getHorzAnchor();

    Object getLeftFromText();

    Object getRightFromText();

    Object getTblpX();

    STXAlign.Enum getTblpXSpec();

    Object getTblpY();

    STYAlign.Enum getTblpYSpec();

    Object getTopFromText();

    STVAnchor.Enum getVertAnchor();

    boolean isSetBottomFromText();

    boolean isSetHorzAnchor();

    boolean isSetLeftFromText();

    boolean isSetRightFromText();

    boolean isSetTblpX();

    boolean isSetTblpXSpec();

    boolean isSetTblpY();

    boolean isSetTblpYSpec();

    boolean isSetTopFromText();

    boolean isSetVertAnchor();

    void setBottomFromText(Object obj);

    void setHorzAnchor(STHAnchor.Enum enumR);

    void setLeftFromText(Object obj);

    void setRightFromText(Object obj);

    void setTblpX(Object obj);

    void setTblpXSpec(STXAlign.Enum enumR);

    void setTblpY(Object obj);

    void setTblpYSpec(STYAlign.Enum enumR);

    void setTopFromText(Object obj);

    void setVertAnchor(STVAnchor.Enum enumR);

    void unsetBottomFromText();

    void unsetHorzAnchor();

    void unsetLeftFromText();

    void unsetRightFromText();

    void unsetTblpX();

    void unsetTblpXSpec();

    void unsetTblpY();

    void unsetTblpYSpec();

    void unsetTopFromText();

    void unsetVertAnchor();

    STTwipsMeasure xgetBottomFromText();

    STHAnchor xgetHorzAnchor();

    STTwipsMeasure xgetLeftFromText();

    STTwipsMeasure xgetRightFromText();

    STSignedTwipsMeasure xgetTblpX();

    STXAlign xgetTblpXSpec();

    STSignedTwipsMeasure xgetTblpY();

    STYAlign xgetTblpYSpec();

    STTwipsMeasure xgetTopFromText();

    STVAnchor xgetVertAnchor();

    void xsetBottomFromText(STTwipsMeasure sTTwipsMeasure);

    void xsetHorzAnchor(STHAnchor sTHAnchor);

    void xsetLeftFromText(STTwipsMeasure sTTwipsMeasure);

    void xsetRightFromText(STTwipsMeasure sTTwipsMeasure);

    void xsetTblpX(STSignedTwipsMeasure sTSignedTwipsMeasure);

    void xsetTblpXSpec(STXAlign sTXAlign);

    void xsetTblpY(STSignedTwipsMeasure sTSignedTwipsMeasure);

    void xsetTblpYSpec(STYAlign sTYAlign);

    void xsetTopFromText(STTwipsMeasure sTTwipsMeasure);

    void xsetVertAnchor(STVAnchor sTVAnchor);

    static {
        DocumentFactory<CTTblPPr> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttblppra134type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
