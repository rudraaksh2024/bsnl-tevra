package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public interface CTChartSpace extends XmlObject {
    public static final DocumentFactory<CTChartSpace> Factory;
    public static final SchemaType type;

    CTChart addNewChart();

    CTColorMapping addNewClrMapOvr();

    CTBoolean addNewDate1904();

    CTExtensionList addNewExtLst();

    CTExternalData addNewExternalData();

    CTTextLanguageID addNewLang();

    CTPivotSource addNewPivotSource();

    CTPrintSettings addNewPrintSettings();

    CTProtection addNewProtection();

    CTBoolean addNewRoundedCorners();

    CTShapeProperties addNewSpPr();

    CTStyle addNewStyle();

    CTTextBody addNewTxPr();

    CTRelId addNewUserShapes();

    CTChart getChart();

    CTColorMapping getClrMapOvr();

    CTBoolean getDate1904();

    CTExtensionList getExtLst();

    CTExternalData getExternalData();

    CTTextLanguageID getLang();

    CTPivotSource getPivotSource();

    CTPrintSettings getPrintSettings();

    CTProtection getProtection();

    CTBoolean getRoundedCorners();

    CTShapeProperties getSpPr();

    CTStyle getStyle();

    CTTextBody getTxPr();

    CTRelId getUserShapes();

    boolean isSetClrMapOvr();

    boolean isSetDate1904();

    boolean isSetExtLst();

    boolean isSetExternalData();

    boolean isSetLang();

    boolean isSetPivotSource();

    boolean isSetPrintSettings();

    boolean isSetProtection();

    boolean isSetRoundedCorners();

    boolean isSetSpPr();

    boolean isSetStyle();

    boolean isSetTxPr();

    boolean isSetUserShapes();

    void setChart(CTChart cTChart);

    void setClrMapOvr(CTColorMapping cTColorMapping);

    void setDate1904(CTBoolean cTBoolean);

    void setExtLst(CTExtensionList cTExtensionList);

    void setExternalData(CTExternalData cTExternalData);

    void setLang(CTTextLanguageID cTTextLanguageID);

    void setPivotSource(CTPivotSource cTPivotSource);

    void setPrintSettings(CTPrintSettings cTPrintSettings);

    void setProtection(CTProtection cTProtection);

    void setRoundedCorners(CTBoolean cTBoolean);

    void setSpPr(CTShapeProperties cTShapeProperties);

    void setStyle(CTStyle cTStyle);

    void setTxPr(CTTextBody cTTextBody);

    void setUserShapes(CTRelId cTRelId);

    void unsetClrMapOvr();

    void unsetDate1904();

    void unsetExtLst();

    void unsetExternalData();

    void unsetLang();

    void unsetPivotSource();

    void unsetPrintSettings();

    void unsetProtection();

    void unsetRoundedCorners();

    void unsetSpPr();

    void unsetStyle();

    void unsetTxPr();

    void unsetUserShapes();

    static {
        DocumentFactory<CTChartSpace> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctchartspacef9b4type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
