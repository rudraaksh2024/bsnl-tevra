package org.openxmlformats.schemas.officeDocument.x2006.math;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTMPr extends XmlObject {
    public static final DocumentFactory<CTMPr> Factory;
    public static final SchemaType type;

    CTYAlign addNewBaseJc();

    CTUnSignedInteger addNewCGp();

    CTSpacingRule addNewCGpRule();

    CTUnSignedInteger addNewCSp();

    CTCtrlPr addNewCtrlPr();

    CTMCS addNewMcs();

    CTOnOff addNewPlcHide();

    CTUnSignedInteger addNewRSp();

    CTSpacingRule addNewRSpRule();

    CTYAlign getBaseJc();

    CTUnSignedInteger getCGp();

    CTSpacingRule getCGpRule();

    CTUnSignedInteger getCSp();

    CTCtrlPr getCtrlPr();

    CTMCS getMcs();

    CTOnOff getPlcHide();

    CTUnSignedInteger getRSp();

    CTSpacingRule getRSpRule();

    boolean isSetBaseJc();

    boolean isSetCGp();

    boolean isSetCGpRule();

    boolean isSetCSp();

    boolean isSetCtrlPr();

    boolean isSetMcs();

    boolean isSetPlcHide();

    boolean isSetRSp();

    boolean isSetRSpRule();

    void setBaseJc(CTYAlign cTYAlign);

    void setCGp(CTUnSignedInteger cTUnSignedInteger);

    void setCGpRule(CTSpacingRule cTSpacingRule);

    void setCSp(CTUnSignedInteger cTUnSignedInteger);

    void setCtrlPr(CTCtrlPr cTCtrlPr);

    void setMcs(CTMCS ctmcs);

    void setPlcHide(CTOnOff cTOnOff);

    void setRSp(CTUnSignedInteger cTUnSignedInteger);

    void setRSpRule(CTSpacingRule cTSpacingRule);

    void unsetBaseJc();

    void unsetCGp();

    void unsetCGpRule();

    void unsetCSp();

    void unsetCtrlPr();

    void unsetMcs();

    void unsetPlcHide();

    void unsetRSp();

    void unsetRSpRule();

    static {
        DocumentFactory<CTMPr> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctmpr122dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
