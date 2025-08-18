package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocGrid;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEdnProps;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtrRef;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLineNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPaperSource;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRel;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPrChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextDirection;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber;

public class CTSectPrImpl extends XmlComplexContentImpl implements CTSectPr {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "headerReference"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "footerReference"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "footnotePr"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "endnotePr"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "type"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pgSz"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pgMar"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "paperSrc"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pgBorders"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "lnNumType"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pgNumType"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cols"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "formProt"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "vAlign"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "noEndnote"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "titlePg"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "textDirection"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bidi"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rtlGutter"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "docGrid"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "printerSettings"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "sectPrChange"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rsidRPr"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rsidDel"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rsidR"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rsidSect")};
    private static final long serialVersionUID = 1;

    public CTSectPrImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTHdrFtrRef> getHeaderReferenceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTSectPrImpl$$ExternalSyntheticLambda0(this), new CTSectPrImpl$$ExternalSyntheticLambda1(this), new CTSectPrImpl$$ExternalSyntheticLambda2(this), new CTSectPrImpl$$ExternalSyntheticLambda3(this), new CTSectPrImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTHdrFtrRef[] getHeaderReferenceArray() {
        return (CTHdrFtrRef[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTHdrFtrRef[0]);
    }

    public CTHdrFtrRef getHeaderReferenceArray(int i) {
        CTHdrFtrRef cTHdrFtrRef;
        synchronized (monitor()) {
            check_orphaned();
            cTHdrFtrRef = (CTHdrFtrRef) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTHdrFtrRef == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTHdrFtrRef;
    }

    public int sizeOfHeaderReferenceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setHeaderReferenceArray(CTHdrFtrRef[] cTHdrFtrRefArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTHdrFtrRefArr, PROPERTY_QNAME[0]);
    }

    public void setHeaderReferenceArray(int i, CTHdrFtrRef cTHdrFtrRef) {
        generatedSetterHelperImpl(cTHdrFtrRef, PROPERTY_QNAME[0], i, 2);
    }

    public CTHdrFtrRef insertNewHeaderReference(int i) {
        CTHdrFtrRef cTHdrFtrRef;
        synchronized (monitor()) {
            check_orphaned();
            cTHdrFtrRef = (CTHdrFtrRef) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTHdrFtrRef;
    }

    public CTHdrFtrRef addNewHeaderReference() {
        CTHdrFtrRef cTHdrFtrRef;
        synchronized (monitor()) {
            check_orphaned();
            cTHdrFtrRef = (CTHdrFtrRef) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTHdrFtrRef;
    }

    public void removeHeaderReference(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<CTHdrFtrRef> getFooterReferenceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTSectPrImpl$$ExternalSyntheticLambda5(this), new CTSectPrImpl$$ExternalSyntheticLambda6(this), new CTSectPrImpl$$ExternalSyntheticLambda7(this), new CTSectPrImpl$$ExternalSyntheticLambda8(this), new CTSectPrImpl$$ExternalSyntheticLambda9(this));
        }
        return javaListXmlObject;
    }

    public CTHdrFtrRef[] getFooterReferenceArray() {
        return (CTHdrFtrRef[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTHdrFtrRef[0]);
    }

    public CTHdrFtrRef getFooterReferenceArray(int i) {
        CTHdrFtrRef cTHdrFtrRef;
        synchronized (monitor()) {
            check_orphaned();
            cTHdrFtrRef = (CTHdrFtrRef) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTHdrFtrRef == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTHdrFtrRef;
    }

    public int sizeOfFooterReferenceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setFooterReferenceArray(CTHdrFtrRef[] cTHdrFtrRefArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTHdrFtrRefArr, PROPERTY_QNAME[1]);
    }

    public void setFooterReferenceArray(int i, CTHdrFtrRef cTHdrFtrRef) {
        generatedSetterHelperImpl(cTHdrFtrRef, PROPERTY_QNAME[1], i, 2);
    }

    public CTHdrFtrRef insertNewFooterReference(int i) {
        CTHdrFtrRef cTHdrFtrRef;
        synchronized (monitor()) {
            check_orphaned();
            cTHdrFtrRef = (CTHdrFtrRef) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTHdrFtrRef;
    }

    public CTHdrFtrRef addNewFooterReference() {
        CTHdrFtrRef cTHdrFtrRef;
        synchronized (monitor()) {
            check_orphaned();
            cTHdrFtrRef = (CTHdrFtrRef) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTHdrFtrRef;
    }

    public void removeFooterReference(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public CTFtnProps getFootnotePr() {
        CTFtnProps cTFtnProps;
        synchronized (monitor()) {
            check_orphaned();
            cTFtnProps = (CTFtnProps) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTFtnProps == null) {
                cTFtnProps = null;
            }
        }
        return cTFtnProps;
    }

    public boolean isSetFootnotePr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setFootnotePr(CTFtnProps cTFtnProps) {
        generatedSetterHelperImpl(cTFtnProps, PROPERTY_QNAME[2], 0, 1);
    }

    public CTFtnProps addNewFootnotePr() {
        CTFtnProps cTFtnProps;
        synchronized (monitor()) {
            check_orphaned();
            cTFtnProps = (CTFtnProps) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTFtnProps;
    }

    public void unsetFootnotePr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTEdnProps getEndnotePr() {
        CTEdnProps cTEdnProps;
        synchronized (monitor()) {
            check_orphaned();
            cTEdnProps = (CTEdnProps) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTEdnProps == null) {
                cTEdnProps = null;
            }
        }
        return cTEdnProps;
    }

    public boolean isSetEndnotePr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setEndnotePr(CTEdnProps cTEdnProps) {
        generatedSetterHelperImpl(cTEdnProps, PROPERTY_QNAME[3], 0, 1);
    }

    public CTEdnProps addNewEndnotePr() {
        CTEdnProps cTEdnProps;
        synchronized (monitor()) {
            check_orphaned();
            cTEdnProps = (CTEdnProps) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTEdnProps;
    }

    public void unsetEndnotePr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTSectType getType() {
        CTSectType cTSectType;
        synchronized (monitor()) {
            check_orphaned();
            cTSectType = (CTSectType) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTSectType == null) {
                cTSectType = null;
            }
        }
        return cTSectType;
    }

    public boolean isSetType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setType(CTSectType cTSectType) {
        generatedSetterHelperImpl(cTSectType, PROPERTY_QNAME[4], 0, 1);
    }

    public CTSectType addNewType() {
        CTSectType cTSectType;
        synchronized (monitor()) {
            check_orphaned();
            cTSectType = (CTSectType) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTSectType;
    }

    public void unsetType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTPageSz getPgSz() {
        CTPageSz cTPageSz;
        synchronized (monitor()) {
            check_orphaned();
            cTPageSz = (CTPageSz) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (cTPageSz == null) {
                cTPageSz = null;
            }
        }
        return cTPageSz;
    }

    public boolean isSetPgSz() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setPgSz(CTPageSz cTPageSz) {
        generatedSetterHelperImpl(cTPageSz, PROPERTY_QNAME[5], 0, 1);
    }

    public CTPageSz addNewPgSz() {
        CTPageSz cTPageSz;
        synchronized (monitor()) {
            check_orphaned();
            cTPageSz = (CTPageSz) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTPageSz;
    }

    public void unsetPgSz() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public CTPageMar getPgMar() {
        CTPageMar cTPageMar;
        synchronized (monitor()) {
            check_orphaned();
            cTPageMar = (CTPageMar) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (cTPageMar == null) {
                cTPageMar = null;
            }
        }
        return cTPageMar;
    }

    public boolean isSetPgMar() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setPgMar(CTPageMar cTPageMar) {
        generatedSetterHelperImpl(cTPageMar, PROPERTY_QNAME[6], 0, 1);
    }

    public CTPageMar addNewPgMar() {
        CTPageMar cTPageMar;
        synchronized (monitor()) {
            check_orphaned();
            cTPageMar = (CTPageMar) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTPageMar;
    }

    public void unsetPgMar() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public CTPaperSource getPaperSrc() {
        CTPaperSource cTPaperSource;
        synchronized (monitor()) {
            check_orphaned();
            cTPaperSource = (CTPaperSource) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (cTPaperSource == null) {
                cTPaperSource = null;
            }
        }
        return cTPaperSource;
    }

    public boolean isSetPaperSrc() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    public void setPaperSrc(CTPaperSource cTPaperSource) {
        generatedSetterHelperImpl(cTPaperSource, PROPERTY_QNAME[7], 0, 1);
    }

    public CTPaperSource addNewPaperSrc() {
        CTPaperSource cTPaperSource;
        synchronized (monitor()) {
            check_orphaned();
            cTPaperSource = (CTPaperSource) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTPaperSource;
    }

    public void unsetPaperSrc() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    public CTPageBorders getPgBorders() {
        CTPageBorders cTPageBorders;
        synchronized (monitor()) {
            check_orphaned();
            cTPageBorders = (CTPageBorders) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (cTPageBorders == null) {
                cTPageBorders = null;
            }
        }
        return cTPageBorders;
    }

    public boolean isSetPgBorders() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    public void setPgBorders(CTPageBorders cTPageBorders) {
        generatedSetterHelperImpl(cTPageBorders, PROPERTY_QNAME[8], 0, 1);
    }

    public CTPageBorders addNewPgBorders() {
        CTPageBorders cTPageBorders;
        synchronized (monitor()) {
            check_orphaned();
            cTPageBorders = (CTPageBorders) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTPageBorders;
    }

    public void unsetPgBorders() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    public CTLineNumber getLnNumType() {
        CTLineNumber find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetLnNumType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    public void setLnNumType(CTLineNumber cTLineNumber) {
        generatedSetterHelperImpl(cTLineNumber, PROPERTY_QNAME[9], 0, 1);
    }

    public CTLineNumber addNewLnNumType() {
        CTLineNumber add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return add_element_user;
    }

    public void unsetLnNumType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    public CTPageNumber getPgNumType() {
        CTPageNumber cTPageNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTPageNumber = (CTPageNumber) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            if (cTPageNumber == null) {
                cTPageNumber = null;
            }
        }
        return cTPageNumber;
    }

    public boolean isSetPgNumType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    public void setPgNumType(CTPageNumber cTPageNumber) {
        generatedSetterHelperImpl(cTPageNumber, PROPERTY_QNAME[10], 0, 1);
    }

    public CTPageNumber addNewPgNumType() {
        CTPageNumber cTPageNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTPageNumber = (CTPageNumber) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTPageNumber;
    }

    public void unsetPgNumType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    public CTColumns getCols() {
        CTColumns cTColumns;
        synchronized (monitor()) {
            check_orphaned();
            cTColumns = (CTColumns) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            if (cTColumns == null) {
                cTColumns = null;
            }
        }
        return cTColumns;
    }

    public boolean isSetCols() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    public void setCols(CTColumns cTColumns) {
        generatedSetterHelperImpl(cTColumns, PROPERTY_QNAME[11], 0, 1);
    }

    public CTColumns addNewCols() {
        CTColumns cTColumns;
        synchronized (monitor()) {
            check_orphaned();
            cTColumns = (CTColumns) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTColumns;
    }

    public void unsetCols() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    public CTOnOff getFormProt() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetFormProt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    public void setFormProt(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[12], 0, 1);
    }

    public CTOnOff addNewFormProt() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return cTOnOff;
    }

    public void unsetFormProt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    public CTVerticalJc getVAlign() {
        CTVerticalJc cTVerticalJc;
        synchronized (monitor()) {
            check_orphaned();
            cTVerticalJc = (CTVerticalJc) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            if (cTVerticalJc == null) {
                cTVerticalJc = null;
            }
        }
        return cTVerticalJc;
    }

    public boolean isSetVAlign() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    public void setVAlign(CTVerticalJc cTVerticalJc) {
        generatedSetterHelperImpl(cTVerticalJc, PROPERTY_QNAME[13], 0, 1);
    }

    public CTVerticalJc addNewVAlign() {
        CTVerticalJc cTVerticalJc;
        synchronized (monitor()) {
            check_orphaned();
            cTVerticalJc = (CTVerticalJc) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return cTVerticalJc;
    }

    public void unsetVAlign() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    public CTOnOff getNoEndnote() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetNoEndnote() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    public void setNoEndnote(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[14], 0, 1);
    }

    public CTOnOff addNewNoEndnote() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return cTOnOff;
    }

    public void unsetNoEndnote() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    public CTOnOff getTitlePg() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetTitlePg() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    public void setTitlePg(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[15], 0, 1);
    }

    public CTOnOff addNewTitlePg() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return cTOnOff;
    }

    public void unsetTitlePg() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }

    public CTTextDirection getTextDirection() {
        CTTextDirection cTTextDirection;
        synchronized (monitor()) {
            check_orphaned();
            cTTextDirection = (CTTextDirection) get_store().find_element_user(PROPERTY_QNAME[16], 0);
            if (cTTextDirection == null) {
                cTTextDirection = null;
            }
        }
        return cTTextDirection;
    }

    public boolean isSetTextDirection() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[16]) != 0;
        }
        return z;
    }

    public void setTextDirection(CTTextDirection cTTextDirection) {
        generatedSetterHelperImpl(cTTextDirection, PROPERTY_QNAME[16], 0, 1);
    }

    public CTTextDirection addNewTextDirection() {
        CTTextDirection cTTextDirection;
        synchronized (monitor()) {
            check_orphaned();
            cTTextDirection = (CTTextDirection) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return cTTextDirection;
    }

    public void unsetTextDirection() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], 0);
        }
    }

    public CTOnOff getBidi() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[17], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetBidi() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[17]) != 0;
        }
        return z;
    }

    public void setBidi(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[17], 0, 1);
    }

    public CTOnOff addNewBidi() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return cTOnOff;
    }

    public void unsetBidi() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], 0);
        }
    }

    public CTOnOff getRtlGutter() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[18], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetRtlGutter() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[18]) != 0;
        }
        return z;
    }

    public void setRtlGutter(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[18], 0, 1);
    }

    public CTOnOff addNewRtlGutter() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return cTOnOff;
    }

    public void unsetRtlGutter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], 0);
        }
    }

    public CTDocGrid getDocGrid() {
        CTDocGrid cTDocGrid;
        synchronized (monitor()) {
            check_orphaned();
            cTDocGrid = (CTDocGrid) get_store().find_element_user(PROPERTY_QNAME[19], 0);
            if (cTDocGrid == null) {
                cTDocGrid = null;
            }
        }
        return cTDocGrid;
    }

    public boolean isSetDocGrid() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[19]) != 0;
        }
        return z;
    }

    public void setDocGrid(CTDocGrid cTDocGrid) {
        generatedSetterHelperImpl(cTDocGrid, PROPERTY_QNAME[19], 0, 1);
    }

    public CTDocGrid addNewDocGrid() {
        CTDocGrid cTDocGrid;
        synchronized (monitor()) {
            check_orphaned();
            cTDocGrid = (CTDocGrid) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return cTDocGrid;
    }

    public void unsetDocGrid() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], 0);
        }
    }

    public CTRel getPrinterSettings() {
        CTRel cTRel;
        synchronized (monitor()) {
            check_orphaned();
            cTRel = (CTRel) get_store().find_element_user(PROPERTY_QNAME[20], 0);
            if (cTRel == null) {
                cTRel = null;
            }
        }
        return cTRel;
    }

    public boolean isSetPrinterSettings() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[20]) != 0;
        }
        return z;
    }

    public void setPrinterSettings(CTRel cTRel) {
        generatedSetterHelperImpl(cTRel, PROPERTY_QNAME[20], 0, 1);
    }

    public CTRel addNewPrinterSettings() {
        CTRel cTRel;
        synchronized (monitor()) {
            check_orphaned();
            cTRel = (CTRel) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return cTRel;
    }

    public void unsetPrinterSettings() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], 0);
        }
    }

    public CTSectPrChange getSectPrChange() {
        CTSectPrChange find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[21], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetSectPrChange() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[21]) != 0;
        }
        return z;
    }

    public void setSectPrChange(CTSectPrChange cTSectPrChange) {
        generatedSetterHelperImpl(cTSectPrChange, PROPERTY_QNAME[21], 0, 1);
    }

    public CTSectPrChange addNewSectPrChange() {
        CTSectPrChange add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return add_element_user;
    }

    public void unsetSectPrChange() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], 0);
        }
    }

    public byte[] getRsidRPr() {
        byte[] bArr;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (simpleValue == null) {
                bArr = null;
            } else {
                bArr = simpleValue.getByteArrayValue();
            }
        }
        return bArr;
    }

    public STLongHexNumber xgetRsidRPr() {
        STLongHexNumber sTLongHexNumber;
        synchronized (monitor()) {
            check_orphaned();
            sTLongHexNumber = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[22]);
        }
        return sTLongHexNumber;
    }

    public boolean isSetRsidRPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[22]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setRsidRPr(byte[] r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 22
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
            r1.setByteArrayValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSectPrImpl.setRsidRPr(byte[]):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetRsidRPr(org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 22
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSectPrImpl.xsetRsidRPr(org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber):void");
    }

    public void unsetRsidRPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[22]);
        }
    }

    public byte[] getRsidDel() {
        byte[] bArr;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (simpleValue == null) {
                bArr = null;
            } else {
                bArr = simpleValue.getByteArrayValue();
            }
        }
        return bArr;
    }

    public STLongHexNumber xgetRsidDel() {
        STLongHexNumber sTLongHexNumber;
        synchronized (monitor()) {
            check_orphaned();
            sTLongHexNumber = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[23]);
        }
        return sTLongHexNumber;
    }

    public boolean isSetRsidDel() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[23]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setRsidDel(byte[] r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 23
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
            r1.setByteArrayValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSectPrImpl.setRsidDel(byte[]):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetRsidDel(org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 23
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSectPrImpl.xsetRsidDel(org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber):void");
    }

    public void unsetRsidDel() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[23]);
        }
    }

    public byte[] getRsidR() {
        byte[] bArr;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (simpleValue == null) {
                bArr = null;
            } else {
                bArr = simpleValue.getByteArrayValue();
            }
        }
        return bArr;
    }

    public STLongHexNumber xgetRsidR() {
        STLongHexNumber sTLongHexNumber;
        synchronized (monitor()) {
            check_orphaned();
            sTLongHexNumber = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[24]);
        }
        return sTLongHexNumber;
    }

    public boolean isSetRsidR() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[24]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setRsidR(byte[] r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 24
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
            r1.setByteArrayValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSectPrImpl.setRsidR(byte[]):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetRsidR(org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 24
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSectPrImpl.xsetRsidR(org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber):void");
    }

    public void unsetRsidR() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[24]);
        }
    }

    public byte[] getRsidSect() {
        byte[] bArr;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (simpleValue == null) {
                bArr = null;
            } else {
                bArr = simpleValue.getByteArrayValue();
            }
        }
        return bArr;
    }

    public STLongHexNumber xgetRsidSect() {
        STLongHexNumber sTLongHexNumber;
        synchronized (monitor()) {
            check_orphaned();
            sTLongHexNumber = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[25]);
        }
        return sTLongHexNumber;
    }

    public boolean isSetRsidSect() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[25]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setRsidSect(byte[] r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 25
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
            r1.setByteArrayValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSectPrImpl.setRsidSect(byte[]):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetRsidSect(org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 25
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSectPrImpl.xsetRsidSect(org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber):void");
    }

    public void unsetRsidSect() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[25]);
        }
    }
}
