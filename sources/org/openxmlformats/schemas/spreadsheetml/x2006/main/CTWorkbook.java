package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STConformanceClass;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STConformanceClass$Enum;

public interface CTWorkbook extends XmlObject {
    public static final DocumentFactory<CTWorkbook> Factory;
    public static final SchemaType type;

    CTBookViews addNewBookViews();

    CTCalcPr addNewCalcPr();

    CTCustomWorkbookViews addNewCustomWorkbookViews();

    CTDefinedNames addNewDefinedNames();

    CTExtensionList addNewExtLst();

    CTExternalReferences addNewExternalReferences();

    CTFileRecoveryPr addNewFileRecoveryPr();

    CTFileSharing addNewFileSharing();

    CTFileVersion addNewFileVersion();

    CTFunctionGroups addNewFunctionGroups();

    CTOleSize addNewOleSize();

    CTPivotCaches addNewPivotCaches();

    CTSheets addNewSheets();

    CTSmartTagPr addNewSmartTagPr();

    CTSmartTagTypes addNewSmartTagTypes();

    CTWebPublishObjects addNewWebPublishObjects();

    CTWebPublishing addNewWebPublishing();

    CTWorkbookPr addNewWorkbookPr();

    CTWorkbookProtection addNewWorkbookProtection();

    CTBookViews getBookViews();

    CTCalcPr getCalcPr();

    STConformanceClass$Enum getConformance();

    CTCustomWorkbookViews getCustomWorkbookViews();

    CTDefinedNames getDefinedNames();

    CTExtensionList getExtLst();

    CTExternalReferences getExternalReferences();

    CTFileRecoveryPr getFileRecoveryPrArray(int i);

    CTFileRecoveryPr[] getFileRecoveryPrArray();

    List<CTFileRecoveryPr> getFileRecoveryPrList();

    CTFileSharing getFileSharing();

    CTFileVersion getFileVersion();

    CTFunctionGroups getFunctionGroups();

    CTOleSize getOleSize();

    CTPivotCaches getPivotCaches();

    CTSheets getSheets();

    CTSmartTagPr getSmartTagPr();

    CTSmartTagTypes getSmartTagTypes();

    CTWebPublishObjects getWebPublishObjects();

    CTWebPublishing getWebPublishing();

    CTWorkbookPr getWorkbookPr();

    CTWorkbookProtection getWorkbookProtection();

    CTFileRecoveryPr insertNewFileRecoveryPr(int i);

    boolean isSetBookViews();

    boolean isSetCalcPr();

    boolean isSetConformance();

    boolean isSetCustomWorkbookViews();

    boolean isSetDefinedNames();

    boolean isSetExtLst();

    boolean isSetExternalReferences();

    boolean isSetFileSharing();

    boolean isSetFileVersion();

    boolean isSetFunctionGroups();

    boolean isSetOleSize();

    boolean isSetPivotCaches();

    boolean isSetSmartTagPr();

    boolean isSetSmartTagTypes();

    boolean isSetWebPublishObjects();

    boolean isSetWebPublishing();

    boolean isSetWorkbookPr();

    boolean isSetWorkbookProtection();

    void removeFileRecoveryPr(int i);

    void setBookViews(CTBookViews cTBookViews);

    void setCalcPr(CTCalcPr cTCalcPr);

    void setConformance(STConformanceClass$Enum sTConformanceClass$Enum);

    void setCustomWorkbookViews(CTCustomWorkbookViews cTCustomWorkbookViews);

    void setDefinedNames(CTDefinedNames cTDefinedNames);

    void setExtLst(CTExtensionList cTExtensionList);

    void setExternalReferences(CTExternalReferences cTExternalReferences);

    void setFileRecoveryPrArray(int i, CTFileRecoveryPr cTFileRecoveryPr);

    void setFileRecoveryPrArray(CTFileRecoveryPr[] cTFileRecoveryPrArr);

    void setFileSharing(CTFileSharing cTFileSharing);

    void setFileVersion(CTFileVersion cTFileVersion);

    void setFunctionGroups(CTFunctionGroups cTFunctionGroups);

    void setOleSize(CTOleSize cTOleSize);

    void setPivotCaches(CTPivotCaches cTPivotCaches);

    void setSheets(CTSheets cTSheets);

    void setSmartTagPr(CTSmartTagPr cTSmartTagPr);

    void setSmartTagTypes(CTSmartTagTypes cTSmartTagTypes);

    void setWebPublishObjects(CTWebPublishObjects cTWebPublishObjects);

    void setWebPublishing(CTWebPublishing cTWebPublishing);

    void setWorkbookPr(CTWorkbookPr cTWorkbookPr);

    void setWorkbookProtection(CTWorkbookProtection cTWorkbookProtection);

    int sizeOfFileRecoveryPrArray();

    void unsetBookViews();

    void unsetCalcPr();

    void unsetConformance();

    void unsetCustomWorkbookViews();

    void unsetDefinedNames();

    void unsetExtLst();

    void unsetExternalReferences();

    void unsetFileSharing();

    void unsetFileVersion();

    void unsetFunctionGroups();

    void unsetOleSize();

    void unsetPivotCaches();

    void unsetSmartTagPr();

    void unsetSmartTagTypes();

    void unsetWebPublishObjects();

    void unsetWebPublishing();

    void unsetWorkbookPr();

    void unsetWorkbookProtection();

    STConformanceClass xgetConformance();

    void xsetConformance(STConformanceClass sTConformanceClass);

    static {
        DocumentFactory<CTWorkbook> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctworkbook83c3type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
