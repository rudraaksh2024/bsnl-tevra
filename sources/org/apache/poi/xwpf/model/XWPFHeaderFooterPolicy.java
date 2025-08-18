package org.apache.poi.xwpf.model;

import com.microsoft.schemas.office.office.STConnectType;
import com.microsoft.schemas.vml.CTFormulas;
import com.microsoft.schemas.vml.CTGroup;
import com.microsoft.schemas.vml.CTH;
import com.microsoft.schemas.vml.CTPath;
import com.microsoft.schemas.vml.CTShape;
import com.microsoft.schemas.vml.CTShapetype;
import com.microsoft.schemas.vml.CTTextPath;
import com.microsoft.schemas.vml.STExt;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFactory;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFHeaderFooter;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRelation;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalse;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtrRef;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPicture;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.FtrDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.HdrDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;

public class XWPFHeaderFooterPolicy {
    public static final STHdrFtr.Enum DEFAULT = STHdrFtr.DEFAULT;
    public static final STHdrFtr.Enum EVEN = STHdrFtr.EVEN;
    public static final STHdrFtr.Enum FIRST = STHdrFtr.FIRST;
    private XWPFFooter defaultFooter;
    private XWPFHeader defaultHeader;
    private XWPFDocument doc;
    private XWPFFooter evenPageFooter;
    private XWPFHeader evenPageHeader;
    private XWPFFooter firstPageFooter;
    private XWPFHeader firstPageHeader;
    private CTSectPr sectPr;

    public XWPFHeaderFooterPolicy(XWPFDocument xWPFDocument) {
        this(xWPFDocument, (CTSectPr) null);
    }

    public XWPFHeaderFooterPolicy(XWPFDocument xWPFDocument, CTSectPr cTSectPr) {
        STHdrFtr.Enum enumR;
        STHdrFtr.Enum enumR2;
        if (cTSectPr == null) {
            CTBody body = xWPFDocument.getDocument().getBody();
            if (body.isSetSectPr()) {
                cTSectPr = body.getSectPr();
            } else {
                cTSectPr = body.addNewSectPr();
            }
        }
        this.doc = xWPFDocument;
        this.sectPr = cTSectPr;
        int i = 0;
        while (true) {
            XWPFHeader xWPFHeader = null;
            if (i >= cTSectPr.sizeOfHeaderReferenceArray()) {
                break;
            }
            CTHdrFtrRef headerReferenceArray = cTSectPr.getHeaderReferenceArray(i);
            POIXMLDocumentPart relationById = xWPFDocument.getRelationById(headerReferenceArray.getId());
            if (relationById != null && (relationById instanceof XWPFHeader)) {
                xWPFHeader = (XWPFHeader) relationById;
            }
            try {
                enumR2 = headerReferenceArray.getType();
            } catch (XmlValueOutOfRangeException unused) {
                enumR2 = STHdrFtr.DEFAULT;
            }
            assignHeader(xWPFHeader, enumR2);
            i++;
        }
        for (int i2 = 0; i2 < cTSectPr.sizeOfFooterReferenceArray(); i2++) {
            CTHdrFtrRef footerReferenceArray = cTSectPr.getFooterReferenceArray(i2);
            POIXMLDocumentPart relationById2 = xWPFDocument.getRelationById(footerReferenceArray.getId());
            XWPFFooter xWPFFooter = (relationById2 == null || !(relationById2 instanceof XWPFFooter)) ? null : (XWPFFooter) relationById2;
            try {
                enumR = footerReferenceArray.getType();
            } catch (XmlValueOutOfRangeException unused2) {
                enumR = STHdrFtr.DEFAULT;
            }
            assignFooter(xWPFFooter, enumR);
        }
    }

    private void assignFooter(XWPFFooter xWPFFooter, STHdrFtr.Enum enumR) {
        if (enumR == STHdrFtr.FIRST) {
            this.firstPageFooter = xWPFFooter;
        } else if (enumR == STHdrFtr.EVEN) {
            this.evenPageFooter = xWPFFooter;
        } else {
            this.defaultFooter = xWPFFooter;
        }
    }

    private void assignHeader(XWPFHeader xWPFHeader, STHdrFtr.Enum enumR) {
        if (enumR == STHdrFtr.FIRST) {
            this.firstPageHeader = xWPFHeader;
        } else if (enumR == STHdrFtr.EVEN) {
            this.evenPageHeader = xWPFHeader;
        } else {
            this.defaultHeader = xWPFHeader;
        }
    }

    public XWPFHeader createHeader(STHdrFtr.Enum enumR) {
        return createHeader(enumR, (XWPFParagraph[]) null);
    }

    public XWPFHeader createHeader(STHdrFtr.Enum enumR, XWPFParagraph[] xWPFParagraphArr) {
        XWPFHeader header = getHeader(enumR);
        if (header != null) {
            return header;
        }
        XWPFRelation xWPFRelation = XWPFRelation.HEADER;
        XWPFHeader xWPFHeader = (XWPFHeader) this.doc.createRelationship(xWPFRelation, XWPFFactory.getInstance(), getRelationIndex(xWPFRelation));
        xWPFHeader.setXWPFDocument(this.doc);
        CTHdrFtr buildHdr = buildHdr(enumR, xWPFHeader, xWPFParagraphArr);
        xWPFHeader.setHeaderFooter(buildHdr);
        HdrDocument.Factory.newInstance().setHdr(buildHdr);
        assignHeader(xWPFHeader, enumR);
        return xWPFHeader;
    }

    public XWPFFooter createFooter(STHdrFtr.Enum enumR) {
        return createFooter(enumR, (XWPFParagraph[]) null);
    }

    public XWPFFooter createFooter(STHdrFtr.Enum enumR, XWPFParagraph[] xWPFParagraphArr) {
        XWPFFooter footer = getFooter(enumR);
        if (footer != null) {
            return footer;
        }
        XWPFRelation xWPFRelation = XWPFRelation.FOOTER;
        XWPFFooter xWPFFooter = (XWPFFooter) this.doc.createRelationship(xWPFRelation, XWPFFactory.getInstance(), getRelationIndex(xWPFRelation));
        xWPFFooter.setXWPFDocument(this.doc);
        CTHdrFtr buildFtr = buildFtr(enumR, xWPFFooter, xWPFParagraphArr);
        xWPFFooter.setHeaderFooter(buildFtr);
        FtrDocument.Factory.newInstance().setFtr(buildFtr);
        assignFooter(xWPFFooter, enumR);
        return xWPFFooter;
    }

    private int getRelationIndex(XWPFRelation xWPFRelation) {
        int i = 1;
        for (POIXMLDocumentPart.RelationPart relationship : this.doc.getRelationParts()) {
            if (relationship.getRelationship().getRelationshipType().equals(xWPFRelation.getRelation())) {
                i++;
            }
        }
        return i;
    }

    private CTHdrFtr buildFtr(STHdrFtr.Enum enumR, XWPFHeaderFooter xWPFHeaderFooter, XWPFParagraph[] xWPFParagraphArr) {
        CTHdrFtr buildHdrFtr = buildHdrFtr(xWPFParagraphArr, xWPFHeaderFooter);
        setFooterReference(enumR, xWPFHeaderFooter);
        return buildHdrFtr;
    }

    private CTHdrFtr buildHdr(STHdrFtr.Enum enumR, XWPFHeaderFooter xWPFHeaderFooter, XWPFParagraph[] xWPFParagraphArr) {
        CTHdrFtr buildHdrFtr = buildHdrFtr(xWPFParagraphArr, xWPFHeaderFooter);
        setHeaderReference(enumR, xWPFHeaderFooter);
        return buildHdrFtr;
    }

    private CTHdrFtr buildHdrFtr(XWPFParagraph[] xWPFParagraphArr, XWPFHeaderFooter xWPFHeaderFooter) {
        CTHdrFtr _getHdrFtr = xWPFHeaderFooter._getHdrFtr();
        if (xWPFParagraphArr != null) {
            for (int i = 0; i < xWPFParagraphArr.length; i++) {
                _getHdrFtr.addNewP();
                _getHdrFtr.setPArray(i, xWPFParagraphArr[i].getCTP());
            }
        }
        return _getHdrFtr;
    }

    private void setFooterReference(STHdrFtr.Enum enumR, XWPFHeaderFooter xWPFHeaderFooter) {
        CTHdrFtrRef addNewFooterReference = this.sectPr.addNewFooterReference();
        addNewFooterReference.setType(enumR);
        addNewFooterReference.setId(this.doc.getRelationId(xWPFHeaderFooter));
    }

    private void setHeaderReference(STHdrFtr.Enum enumR, XWPFHeaderFooter xWPFHeaderFooter) {
        CTHdrFtrRef addNewHeaderReference = this.sectPr.addNewHeaderReference();
        addNewHeaderReference.setType(enumR);
        addNewHeaderReference.setId(this.doc.getRelationId(xWPFHeaderFooter));
    }

    public XWPFHeader getFirstPageHeader() {
        return this.firstPageHeader;
    }

    public XWPFFooter getFirstPageFooter() {
        return this.firstPageFooter;
    }

    public XWPFHeader getOddPageHeader() {
        return this.defaultHeader;
    }

    public XWPFFooter getOddPageFooter() {
        return this.defaultFooter;
    }

    public XWPFHeader getEvenPageHeader() {
        return this.evenPageHeader;
    }

    public XWPFFooter getEvenPageFooter() {
        return this.evenPageFooter;
    }

    public XWPFHeader getDefaultHeader() {
        return this.defaultHeader;
    }

    public XWPFFooter getDefaultFooter() {
        return this.defaultFooter;
    }

    public XWPFHeader getHeader(int i) {
        XWPFHeader xWPFHeader;
        XWPFHeader xWPFHeader2;
        if (i == 1 && (xWPFHeader2 = this.firstPageHeader) != null) {
            return xWPFHeader2;
        }
        if (i % 2 != 0 || (xWPFHeader = this.evenPageHeader) == null) {
            return this.defaultHeader;
        }
        return xWPFHeader;
    }

    public XWPFHeader getHeader(STHdrFtr.Enum enumR) {
        if (enumR == STHdrFtr.EVEN) {
            return this.evenPageHeader;
        }
        if (enumR == STHdrFtr.FIRST) {
            return this.firstPageHeader;
        }
        return this.defaultHeader;
    }

    public XWPFFooter getFooter(int i) {
        XWPFFooter xWPFFooter;
        XWPFFooter xWPFFooter2;
        if (i == 1 && (xWPFFooter2 = this.firstPageFooter) != null) {
            return xWPFFooter2;
        }
        if (i % 2 != 0 || (xWPFFooter = this.evenPageFooter) == null) {
            return this.defaultFooter;
        }
        return xWPFFooter;
    }

    public XWPFFooter getFooter(STHdrFtr.Enum enumR) {
        if (enumR == STHdrFtr.EVEN) {
            return this.evenPageFooter;
        }
        if (enumR == STHdrFtr.FIRST) {
            return this.firstPageFooter;
        }
        return this.defaultFooter;
    }

    public void createWatermark(String str) {
        XWPFParagraph[] xWPFParagraphArr = {getWatermarkParagraph(str, 1)};
        createHeader(DEFAULT, xWPFParagraphArr);
        xWPFParagraphArr[0] = getWatermarkParagraph(str, 2);
        createHeader(FIRST, xWPFParagraphArr);
        xWPFParagraphArr[0] = getWatermarkParagraph(str, 3);
        createHeader(EVEN, xWPFParagraphArr);
    }

    private XWPFParagraph getWatermarkParagraph(String str, int i) {
        byte[] bArr;
        byte[] bArr2;
        CTP newInstance = CTP.Factory.newInstance();
        CTBody body = this.doc.getDocument().getBody();
        if (body.sizeOfPArray() == 0) {
            bArr2 = null;
            bArr = null;
        } else {
            CTP pArray = body.getPArray(0);
            byte[] rsidR = pArray.getRsidR();
            bArr = pArray.getRsidRDefault();
            bArr2 = rsidR;
        }
        newInstance.setRsidP(bArr2);
        newInstance.setRsidRDefault(bArr);
        newInstance.addNewPPr().addNewPStyle().setVal("Header");
        CTR addNewR = newInstance.addNewR();
        addNewR.addNewRPr().addNewNoProof();
        CTPicture addNewPict = addNewR.addNewPict();
        CTGroup newInstance2 = CTGroup.Factory.newInstance();
        CTShapetype addNewShapetype = newInstance2.addNewShapetype();
        addNewShapetype.setId("_x0000_t136");
        addNewShapetype.setCoordsize("1600,21600");
        addNewShapetype.setSpt(136.0f);
        addNewShapetype.setAdj("10800");
        addNewShapetype.setPath2("m@7,0l@8,0m@5,21600l@6,21600e");
        CTFormulas addNewFormulas = addNewShapetype.addNewFormulas();
        addNewFormulas.addNewF().setEqn("sum #0 0 10800");
        addNewFormulas.addNewF().setEqn("prod #0 2 1");
        addNewFormulas.addNewF().setEqn("sum 21600 0 @1");
        addNewFormulas.addNewF().setEqn("sum 0 0 @2");
        addNewFormulas.addNewF().setEqn("sum 21600 0 @3");
        addNewFormulas.addNewF().setEqn("if @0 @3 0");
        addNewFormulas.addNewF().setEqn("if @0 21600 @1");
        addNewFormulas.addNewF().setEqn("if @0 0 @2");
        addNewFormulas.addNewF().setEqn("if @0 @4 21600");
        addNewFormulas.addNewF().setEqn("mid @5 @6");
        addNewFormulas.addNewF().setEqn("mid @8 @5");
        addNewFormulas.addNewF().setEqn("mid @7 @8");
        addNewFormulas.addNewF().setEqn("mid @6 @7");
        addNewFormulas.addNewF().setEqn("sum @6 0 @5");
        CTPath addNewPath = addNewShapetype.addNewPath();
        addNewPath.setTextpathok(STTrueFalse.T);
        addNewPath.setConnecttype(STConnectType.CUSTOM);
        addNewPath.setConnectlocs("@9,0;@10,10800;@11,21600;@12,10800");
        addNewPath.setConnectangles("270,180,90,0");
        CTTextPath addNewTextpath = addNewShapetype.addNewTextpath();
        addNewTextpath.setOn(STTrueFalse.T);
        addNewTextpath.setFitshape(STTrueFalse.T);
        CTH addNewH = addNewShapetype.addNewHandles().addNewH();
        addNewH.setPosition("#0,bottomRight");
        addNewH.setXrange("6629,14971");
        addNewShapetype.addNewLock().setExt(STExt.EDIT);
        CTShape addNewShape = newInstance2.addNewShape();
        addNewShape.setId("PowerPlusWaterMarkObject" + i);
        addNewShape.setSpid("_x0000_s102" + (i + 4));
        addNewShape.setType("#_x0000_t136");
        addNewShape.setStyle("position:absolute;margin-left:0;margin-top:0;width:415pt;height:207.5pt;z-index:-251654144;mso-wrap-edited:f;mso-position-horizontal:center;mso-position-horizontal-relative:margin;mso-position-vertical:center;mso-position-vertical-relative:margin");
        addNewShape.setWrapcoords("616 5068 390 16297 39 16921 -39 17155 7265 17545 7186 17467 -39 17467 18904 17467 10507 17467 8710 17545 18904 17077 18787 16843 18358 16297 18279 12554 19178 12476 20701 11774 20779 11228 21131 10059 21248 8811 21248 7563 20975 6316 20935 5380 19490 5146 14022 5068 2616 5068");
        addNewShape.setFillcolor("black");
        addNewShape.setStroked(STTrueFalse.FALSE);
        CTTextPath addNewTextpath2 = addNewShape.addNewTextpath();
        addNewTextpath2.setStyle("font-family:&quot;Cambria&quot;;font-size:1pt");
        addNewTextpath2.setString(str);
        addNewPict.set(newInstance2);
        return new XWPFParagraph(newInstance, this.doc);
    }
}
