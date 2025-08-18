package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCfType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STConditionalFormattingOperator;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STTimePeriod;

public interface CTCfRule extends XmlObject {
    public static final DocumentFactory<CTCfRule> Factory;
    public static final SchemaType type;

    void addFormula(String str);

    CTColorScale addNewColorScale();

    CTDataBar addNewDataBar();

    CTExtensionList addNewExtLst();

    STFormula addNewFormula();

    CTIconSet addNewIconSet();

    boolean getAboveAverage();

    boolean getBottom();

    CTColorScale getColorScale();

    CTDataBar getDataBar();

    long getDxfId();

    boolean getEqualAverage();

    CTExtensionList getExtLst();

    String getFormulaArray(int i);

    String[] getFormulaArray();

    List<String> getFormulaList();

    CTIconSet getIconSet();

    STConditionalFormattingOperator.Enum getOperator();

    boolean getPercent();

    int getPriority();

    long getRank();

    int getStdDev();

    boolean getStopIfTrue();

    String getText();

    STTimePeriod.Enum getTimePeriod();

    STCfType.Enum getType();

    void insertFormula(int i, String str);

    STFormula insertNewFormula(int i);

    boolean isSetAboveAverage();

    boolean isSetBottom();

    boolean isSetColorScale();

    boolean isSetDataBar();

    boolean isSetDxfId();

    boolean isSetEqualAverage();

    boolean isSetExtLst();

    boolean isSetIconSet();

    boolean isSetOperator();

    boolean isSetPercent();

    boolean isSetRank();

    boolean isSetStdDev();

    boolean isSetStopIfTrue();

    boolean isSetText();

    boolean isSetTimePeriod();

    boolean isSetType();

    void removeFormula(int i);

    void setAboveAverage(boolean z);

    void setBottom(boolean z);

    void setColorScale(CTColorScale cTColorScale);

    void setDataBar(CTDataBar cTDataBar);

    void setDxfId(long j);

    void setEqualAverage(boolean z);

    void setExtLst(CTExtensionList cTExtensionList);

    void setFormulaArray(int i, String str);

    void setFormulaArray(String[] strArr);

    void setIconSet(CTIconSet cTIconSet);

    void setOperator(STConditionalFormattingOperator.Enum enumR);

    void setPercent(boolean z);

    void setPriority(int i);

    void setRank(long j);

    void setStdDev(int i);

    void setStopIfTrue(boolean z);

    void setText(String str);

    void setTimePeriod(STTimePeriod.Enum enumR);

    void setType(STCfType.Enum enumR);

    int sizeOfFormulaArray();

    void unsetAboveAverage();

    void unsetBottom();

    void unsetColorScale();

    void unsetDataBar();

    void unsetDxfId();

    void unsetEqualAverage();

    void unsetExtLst();

    void unsetIconSet();

    void unsetOperator();

    void unsetPercent();

    void unsetRank();

    void unsetStdDev();

    void unsetStopIfTrue();

    void unsetText();

    void unsetTimePeriod();

    void unsetType();

    XmlBoolean xgetAboveAverage();

    XmlBoolean xgetBottom();

    STDxfId xgetDxfId();

    XmlBoolean xgetEqualAverage();

    STFormula xgetFormulaArray(int i);

    STFormula[] xgetFormulaArray();

    List<STFormula> xgetFormulaList();

    STConditionalFormattingOperator xgetOperator();

    XmlBoolean xgetPercent();

    XmlInt xgetPriority();

    XmlUnsignedInt xgetRank();

    XmlInt xgetStdDev();

    XmlBoolean xgetStopIfTrue();

    XmlString xgetText();

    STTimePeriod xgetTimePeriod();

    STCfType xgetType();

    void xsetAboveAverage(XmlBoolean xmlBoolean);

    void xsetBottom(XmlBoolean xmlBoolean);

    void xsetDxfId(STDxfId sTDxfId);

    void xsetEqualAverage(XmlBoolean xmlBoolean);

    void xsetFormulaArray(int i, STFormula sTFormula);

    void xsetFormulaArray(STFormula[] sTFormulaArr);

    void xsetOperator(STConditionalFormattingOperator sTConditionalFormattingOperator);

    void xsetPercent(XmlBoolean xmlBoolean);

    void xsetPriority(XmlInt xmlInt);

    void xsetRank(XmlUnsignedInt xmlUnsignedInt);

    void xsetStdDev(XmlInt xmlInt);

    void xsetStopIfTrue(XmlBoolean xmlBoolean);

    void xsetText(XmlString xmlString);

    void xsetTimePeriod(STTimePeriod sTTimePeriod);

    void xsetType(STCfType sTCfType);

    static {
        DocumentFactory<CTCfRule> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcfrule3548type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
