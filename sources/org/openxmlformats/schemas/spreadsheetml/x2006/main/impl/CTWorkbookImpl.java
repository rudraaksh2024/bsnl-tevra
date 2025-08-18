package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STConformanceClass;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STConformanceClass$Enum;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBookViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalcPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomWorkbookViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedNames;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalReferences;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileRecoveryPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileSharing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFunctionGroups;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleSize;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCaches;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheets;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSmartTagPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSmartTagTypes;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishObjects;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbookPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbookProtection;

public class CTWorkbookImpl extends XmlComplexContentImpl implements CTWorkbook {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "fileVersion"), new QName(XSSFRelation.NS_SPREADSHEETML, "fileSharing"), new QName(XSSFRelation.NS_SPREADSHEETML, "workbookPr"), new QName(XSSFRelation.NS_SPREADSHEETML, "workbookProtection"), new QName(XSSFRelation.NS_SPREADSHEETML, "bookViews"), new QName(XSSFRelation.NS_SPREADSHEETML, "sheets"), new QName(XSSFRelation.NS_SPREADSHEETML, "functionGroups"), new QName(XSSFRelation.NS_SPREADSHEETML, "externalReferences"), new QName(XSSFRelation.NS_SPREADSHEETML, "definedNames"), new QName(XSSFRelation.NS_SPREADSHEETML, "calcPr"), new QName(XSSFRelation.NS_SPREADSHEETML, "oleSize"), new QName(XSSFRelation.NS_SPREADSHEETML, "customWorkbookViews"), new QName(XSSFRelation.NS_SPREADSHEETML, "pivotCaches"), new QName(XSSFRelation.NS_SPREADSHEETML, "smartTagPr"), new QName(XSSFRelation.NS_SPREADSHEETML, "smartTagTypes"), new QName(XSSFRelation.NS_SPREADSHEETML, "webPublishing"), new QName(XSSFRelation.NS_SPREADSHEETML, "fileRecoveryPr"), new QName(XSSFRelation.NS_SPREADSHEETML, "webPublishObjects"), new QName(XSSFRelation.NS_SPREADSHEETML, "extLst"), new QName("", "conformance")};
    private static final long serialVersionUID = 1;

    public CTWorkbookImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTFileVersion getFileVersion() {
        CTFileVersion cTFileVersion;
        synchronized (monitor()) {
            check_orphaned();
            cTFileVersion = (CTFileVersion) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTFileVersion == null) {
                cTFileVersion = null;
            }
        }
        return cTFileVersion;
    }

    public boolean isSetFileVersion() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = false;
            if (get_store().count_elements(PROPERTY_QNAME[0]) != 0) {
                z = true;
            }
        }
        return z;
    }

    public void setFileVersion(CTFileVersion cTFileVersion) {
        generatedSetterHelperImpl(cTFileVersion, PROPERTY_QNAME[0], 0, 1);
    }

    public CTFileVersion addNewFileVersion() {
        CTFileVersion cTFileVersion;
        synchronized (monitor()) {
            check_orphaned();
            cTFileVersion = (CTFileVersion) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTFileVersion;
    }

    public void unsetFileVersion() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTFileSharing getFileSharing() {
        CTFileSharing cTFileSharing;
        synchronized (monitor()) {
            check_orphaned();
            cTFileSharing = (CTFileSharing) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTFileSharing == null) {
                cTFileSharing = null;
            }
        }
        return cTFileSharing;
    }

    public boolean isSetFileSharing() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    public void setFileSharing(CTFileSharing cTFileSharing) {
        generatedSetterHelperImpl(cTFileSharing, PROPERTY_QNAME[1], 0, 1);
    }

    public CTFileSharing addNewFileSharing() {
        CTFileSharing cTFileSharing;
        synchronized (monitor()) {
            check_orphaned();
            cTFileSharing = (CTFileSharing) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTFileSharing;
    }

    public void unsetFileSharing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTWorkbookPr getWorkbookPr() {
        CTWorkbookPr cTWorkbookPr;
        synchronized (monitor()) {
            check_orphaned();
            cTWorkbookPr = (CTWorkbookPr) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTWorkbookPr == null) {
                cTWorkbookPr = null;
            }
        }
        return cTWorkbookPr;
    }

    public boolean isSetWorkbookPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setWorkbookPr(CTWorkbookPr cTWorkbookPr) {
        generatedSetterHelperImpl(cTWorkbookPr, PROPERTY_QNAME[2], 0, 1);
    }

    public CTWorkbookPr addNewWorkbookPr() {
        CTWorkbookPr cTWorkbookPr;
        synchronized (monitor()) {
            check_orphaned();
            cTWorkbookPr = (CTWorkbookPr) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTWorkbookPr;
    }

    public void unsetWorkbookPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTWorkbookProtection getWorkbookProtection() {
        CTWorkbookProtection cTWorkbookProtection;
        synchronized (monitor()) {
            check_orphaned();
            cTWorkbookProtection = (CTWorkbookProtection) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTWorkbookProtection == null) {
                cTWorkbookProtection = null;
            }
        }
        return cTWorkbookProtection;
    }

    public boolean isSetWorkbookProtection() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setWorkbookProtection(CTWorkbookProtection cTWorkbookProtection) {
        generatedSetterHelperImpl(cTWorkbookProtection, PROPERTY_QNAME[3], 0, 1);
    }

    public CTWorkbookProtection addNewWorkbookProtection() {
        CTWorkbookProtection cTWorkbookProtection;
        synchronized (monitor()) {
            check_orphaned();
            cTWorkbookProtection = (CTWorkbookProtection) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTWorkbookProtection;
    }

    public void unsetWorkbookProtection() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTBookViews getBookViews() {
        CTBookViews cTBookViews;
        synchronized (monitor()) {
            check_orphaned();
            cTBookViews = (CTBookViews) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTBookViews == null) {
                cTBookViews = null;
            }
        }
        return cTBookViews;
    }

    public boolean isSetBookViews() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setBookViews(CTBookViews cTBookViews) {
        generatedSetterHelperImpl(cTBookViews, PROPERTY_QNAME[4], 0, 1);
    }

    public CTBookViews addNewBookViews() {
        CTBookViews cTBookViews;
        synchronized (monitor()) {
            check_orphaned();
            cTBookViews = (CTBookViews) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTBookViews;
    }

    public void unsetBookViews() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTSheets getSheets() {
        CTSheets cTSheets;
        synchronized (monitor()) {
            check_orphaned();
            cTSheets = (CTSheets) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (cTSheets == null) {
                cTSheets = null;
            }
        }
        return cTSheets;
    }

    public void setSheets(CTSheets cTSheets) {
        generatedSetterHelperImpl(cTSheets, PROPERTY_QNAME[5], 0, 1);
    }

    public CTSheets addNewSheets() {
        CTSheets cTSheets;
        synchronized (monitor()) {
            check_orphaned();
            cTSheets = (CTSheets) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTSheets;
    }

    public CTFunctionGroups getFunctionGroups() {
        CTFunctionGroups cTFunctionGroups;
        synchronized (monitor()) {
            check_orphaned();
            cTFunctionGroups = (CTFunctionGroups) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (cTFunctionGroups == null) {
                cTFunctionGroups = null;
            }
        }
        return cTFunctionGroups;
    }

    public boolean isSetFunctionGroups() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setFunctionGroups(CTFunctionGroups cTFunctionGroups) {
        generatedSetterHelperImpl(cTFunctionGroups, PROPERTY_QNAME[6], 0, 1);
    }

    public CTFunctionGroups addNewFunctionGroups() {
        CTFunctionGroups cTFunctionGroups;
        synchronized (monitor()) {
            check_orphaned();
            cTFunctionGroups = (CTFunctionGroups) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTFunctionGroups;
    }

    public void unsetFunctionGroups() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public CTExternalReferences getExternalReferences() {
        CTExternalReferences cTExternalReferences;
        synchronized (monitor()) {
            check_orphaned();
            cTExternalReferences = (CTExternalReferences) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (cTExternalReferences == null) {
                cTExternalReferences = null;
            }
        }
        return cTExternalReferences;
    }

    public boolean isSetExternalReferences() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    public void setExternalReferences(CTExternalReferences cTExternalReferences) {
        generatedSetterHelperImpl(cTExternalReferences, PROPERTY_QNAME[7], 0, 1);
    }

    public CTExternalReferences addNewExternalReferences() {
        CTExternalReferences cTExternalReferences;
        synchronized (monitor()) {
            check_orphaned();
            cTExternalReferences = (CTExternalReferences) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTExternalReferences;
    }

    public void unsetExternalReferences() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    public CTDefinedNames getDefinedNames() {
        CTDefinedNames cTDefinedNames;
        synchronized (monitor()) {
            check_orphaned();
            cTDefinedNames = (CTDefinedNames) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (cTDefinedNames == null) {
                cTDefinedNames = null;
            }
        }
        return cTDefinedNames;
    }

    public boolean isSetDefinedNames() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    public void setDefinedNames(CTDefinedNames cTDefinedNames) {
        generatedSetterHelperImpl(cTDefinedNames, PROPERTY_QNAME[8], 0, 1);
    }

    public CTDefinedNames addNewDefinedNames() {
        CTDefinedNames cTDefinedNames;
        synchronized (monitor()) {
            check_orphaned();
            cTDefinedNames = (CTDefinedNames) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTDefinedNames;
    }

    public void unsetDefinedNames() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    public CTCalcPr getCalcPr() {
        CTCalcPr cTCalcPr;
        synchronized (monitor()) {
            check_orphaned();
            cTCalcPr = (CTCalcPr) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (cTCalcPr == null) {
                cTCalcPr = null;
            }
        }
        return cTCalcPr;
    }

    public boolean isSetCalcPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    public void setCalcPr(CTCalcPr cTCalcPr) {
        generatedSetterHelperImpl(cTCalcPr, PROPERTY_QNAME[9], 0, 1);
    }

    public CTCalcPr addNewCalcPr() {
        CTCalcPr cTCalcPr;
        synchronized (monitor()) {
            check_orphaned();
            cTCalcPr = (CTCalcPr) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTCalcPr;
    }

    public void unsetCalcPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    public CTOleSize getOleSize() {
        CTOleSize cTOleSize;
        synchronized (monitor()) {
            check_orphaned();
            cTOleSize = (CTOleSize) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            if (cTOleSize == null) {
                cTOleSize = null;
            }
        }
        return cTOleSize;
    }

    public boolean isSetOleSize() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    public void setOleSize(CTOleSize cTOleSize) {
        generatedSetterHelperImpl(cTOleSize, PROPERTY_QNAME[10], 0, 1);
    }

    public CTOleSize addNewOleSize() {
        CTOleSize cTOleSize;
        synchronized (monitor()) {
            check_orphaned();
            cTOleSize = (CTOleSize) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTOleSize;
    }

    public void unsetOleSize() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    public CTCustomWorkbookViews getCustomWorkbookViews() {
        CTCustomWorkbookViews cTCustomWorkbookViews;
        synchronized (monitor()) {
            check_orphaned();
            cTCustomWorkbookViews = (CTCustomWorkbookViews) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            if (cTCustomWorkbookViews == null) {
                cTCustomWorkbookViews = null;
            }
        }
        return cTCustomWorkbookViews;
    }

    public boolean isSetCustomWorkbookViews() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    public void setCustomWorkbookViews(CTCustomWorkbookViews cTCustomWorkbookViews) {
        generatedSetterHelperImpl(cTCustomWorkbookViews, PROPERTY_QNAME[11], 0, 1);
    }

    public CTCustomWorkbookViews addNewCustomWorkbookViews() {
        CTCustomWorkbookViews cTCustomWorkbookViews;
        synchronized (monitor()) {
            check_orphaned();
            cTCustomWorkbookViews = (CTCustomWorkbookViews) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTCustomWorkbookViews;
    }

    public void unsetCustomWorkbookViews() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    public CTPivotCaches getPivotCaches() {
        CTPivotCaches cTPivotCaches;
        synchronized (monitor()) {
            check_orphaned();
            cTPivotCaches = (CTPivotCaches) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            if (cTPivotCaches == null) {
                cTPivotCaches = null;
            }
        }
        return cTPivotCaches;
    }

    public boolean isSetPivotCaches() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    public void setPivotCaches(CTPivotCaches cTPivotCaches) {
        generatedSetterHelperImpl(cTPivotCaches, PROPERTY_QNAME[12], 0, 1);
    }

    public CTPivotCaches addNewPivotCaches() {
        CTPivotCaches cTPivotCaches;
        synchronized (monitor()) {
            check_orphaned();
            cTPivotCaches = (CTPivotCaches) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return cTPivotCaches;
    }

    public void unsetPivotCaches() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    public CTSmartTagPr getSmartTagPr() {
        CTSmartTagPr find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[13], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetSmartTagPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    public void setSmartTagPr(CTSmartTagPr cTSmartTagPr) {
        generatedSetterHelperImpl(cTSmartTagPr, PROPERTY_QNAME[13], 0, 1);
    }

    public CTSmartTagPr addNewSmartTagPr() {
        CTSmartTagPr add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return add_element_user;
    }

    public void unsetSmartTagPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    public CTSmartTagTypes getSmartTagTypes() {
        CTSmartTagTypes find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[14], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetSmartTagTypes() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    public void setSmartTagTypes(CTSmartTagTypes cTSmartTagTypes) {
        generatedSetterHelperImpl(cTSmartTagTypes, PROPERTY_QNAME[14], 0, 1);
    }

    public CTSmartTagTypes addNewSmartTagTypes() {
        CTSmartTagTypes add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return add_element_user;
    }

    public void unsetSmartTagTypes() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    public CTWebPublishing getWebPublishing() {
        CTWebPublishing cTWebPublishing;
        synchronized (monitor()) {
            check_orphaned();
            cTWebPublishing = (CTWebPublishing) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            if (cTWebPublishing == null) {
                cTWebPublishing = null;
            }
        }
        return cTWebPublishing;
    }

    public boolean isSetWebPublishing() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    public void setWebPublishing(CTWebPublishing cTWebPublishing) {
        generatedSetterHelperImpl(cTWebPublishing, PROPERTY_QNAME[15], 0, 1);
    }

    public CTWebPublishing addNewWebPublishing() {
        CTWebPublishing cTWebPublishing;
        synchronized (monitor()) {
            check_orphaned();
            cTWebPublishing = (CTWebPublishing) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return cTWebPublishing;
    }

    public void unsetWebPublishing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }

    public List<CTFileRecoveryPr> getFileRecoveryPrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTWorkbookImpl$$ExternalSyntheticLambda0(this), new CTWorkbookImpl$$ExternalSyntheticLambda1(this), new CTWorkbookImpl$$ExternalSyntheticLambda2(this), new CTWorkbookImpl$$ExternalSyntheticLambda3(this), new CTWorkbookImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTFileRecoveryPr[] getFileRecoveryPrArray() {
        return (CTFileRecoveryPr[]) getXmlObjectArray(PROPERTY_QNAME[16], (T[]) new CTFileRecoveryPr[0]);
    }

    public CTFileRecoveryPr getFileRecoveryPrArray(int i) {
        CTFileRecoveryPr cTFileRecoveryPr;
        synchronized (monitor()) {
            check_orphaned();
            cTFileRecoveryPr = (CTFileRecoveryPr) get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (cTFileRecoveryPr == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTFileRecoveryPr;
    }

    public int sizeOfFileRecoveryPrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    public void setFileRecoveryPrArray(CTFileRecoveryPr[] cTFileRecoveryPrArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTFileRecoveryPrArr, PROPERTY_QNAME[16]);
    }

    public void setFileRecoveryPrArray(int i, CTFileRecoveryPr cTFileRecoveryPr) {
        generatedSetterHelperImpl(cTFileRecoveryPr, PROPERTY_QNAME[16], i, 2);
    }

    public CTFileRecoveryPr insertNewFileRecoveryPr(int i) {
        CTFileRecoveryPr cTFileRecoveryPr;
        synchronized (monitor()) {
            check_orphaned();
            cTFileRecoveryPr = (CTFileRecoveryPr) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return cTFileRecoveryPr;
    }

    public CTFileRecoveryPr addNewFileRecoveryPr() {
        CTFileRecoveryPr cTFileRecoveryPr;
        synchronized (monitor()) {
            check_orphaned();
            cTFileRecoveryPr = (CTFileRecoveryPr) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return cTFileRecoveryPr;
    }

    public void removeFileRecoveryPr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    public CTWebPublishObjects getWebPublishObjects() {
        CTWebPublishObjects find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[17], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetWebPublishObjects() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[17]) != 0;
        }
        return z;
    }

    public void setWebPublishObjects(CTWebPublishObjects cTWebPublishObjects) {
        generatedSetterHelperImpl(cTWebPublishObjects, PROPERTY_QNAME[17], 0, 1);
    }

    public CTWebPublishObjects addNewWebPublishObjects() {
        CTWebPublishObjects add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return add_element_user;
    }

    public void unsetWebPublishObjects() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], 0);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[18], 0);
            if (cTExtensionList == null) {
                cTExtensionList = null;
            }
        }
        return cTExtensionList;
    }

    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[18]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[18], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], 0);
        }
    }

    public STConformanceClass$Enum getConformance() {
        STConformanceClass$Enum sTConformanceClass$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (simpleValue == null) {
                sTConformanceClass$Enum = null;
            } else {
                sTConformanceClass$Enum = (STConformanceClass$Enum) simpleValue.getEnumValue();
            }
        }
        return sTConformanceClass$Enum;
    }

    public STConformanceClass xgetConformance() {
        STConformanceClass find_attribute_user;
        synchronized (monitor()) {
            check_orphaned();
            find_attribute_user = get_store().find_attribute_user(PROPERTY_QNAME[19]);
        }
        return find_attribute_user;
    }

    public boolean isSetConformance() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[19]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setConformance(org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STConformanceClass$Enum r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 19
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.setEnumValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTWorkbookImpl.setConformance(org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STConformanceClass$Enum):void");
    }

    public void xsetConformance(STConformanceClass sTConformanceClass) {
        synchronized (monitor()) {
            check_orphaned();
            TypeStore typeStore = get_store();
            QName[] qNameArr = PROPERTY_QNAME;
            STConformanceClass find_attribute_user = typeStore.find_attribute_user(qNameArr[19]);
            if (find_attribute_user == null) {
                find_attribute_user = get_store().add_attribute_user(qNameArr[19]);
            }
            find_attribute_user.set(sTConformanceClass);
        }
    }

    public void unsetConformance() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[19]);
        }
    }
}
