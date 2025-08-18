package org.apache.poi.xwpf.usermodel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.apache.poi.wp.usermodel.Paragraph;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff1;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdnRef;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTProofErr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSimpleField;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSmartTagRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTextAlignment;

public class XWPFParagraph implements IBodyElement, IRunBody, ISDTContents, Paragraph {
    protected XWPFDocument document;
    private final StringBuilder footnoteText = new StringBuilder(64);
    protected List<IRunElement> iruns;
    private final CTP paragraph;
    protected IBody part;
    protected List<XWPFRun> runs;

    public XWPFParagraph(CTP ctp, IBody iBody) {
        XWPFAbstractFootnoteEndnote xWPFAbstractFootnoteEndnote;
        this.paragraph = ctp;
        this.part = iBody;
        XWPFDocument xWPFDocument = iBody.getXWPFDocument();
        this.document = xWPFDocument;
        xWPFDocument.getClass();
        this.runs = new ArrayList();
        this.iruns = new ArrayList();
        buildRunsInOrderFromXml(ctp);
        for (XWPFRun ctr : this.runs) {
            XmlCursor newCursor = ctr.getCTR().newCursor();
            try {
                newCursor.selectPath("child::*");
                while (newCursor.toNextSelection()) {
                    XmlObject object = newCursor.getObject();
                    if (object instanceof CTFtnEdnRef) {
                        CTFtnEdnRef cTFtnEdnRef = (CTFtnEdnRef) object;
                        this.footnoteText.append(" [").append(cTFtnEdnRef.getId()).append(": ");
                        if (cTFtnEdnRef.getDomNode().getLocalName().equals("footnoteReference")) {
                            xWPFAbstractFootnoteEndnote = this.document.getFootnoteByID(cTFtnEdnRef.getId().intValue());
                        } else {
                            xWPFAbstractFootnoteEndnote = this.document.getEndnoteByID(cTFtnEdnRef.getId().intValue());
                        }
                        if (xWPFAbstractFootnoteEndnote != null) {
                            boolean z = true;
                            for (XWPFParagraph next : xWPFAbstractFootnoteEndnote.getParagraphs()) {
                                if (!z) {
                                    this.footnoteText.append("\n");
                                }
                                this.footnoteText.append(next.getText());
                                z = false;
                            }
                        } else {
                            this.footnoteText.append("!!! End note with ID \"").append(cTFtnEdnRef.getId()).append("\" not found in document.");
                        }
                        this.footnoteText.append("] ");
                    }
                }
            } finally {
                newCursor.dispose();
            }
        }
    }

    private void buildRunsInOrderFromXml(XmlObject xmlObject) {
        XmlCursor newCursor = xmlObject.newCursor();
        try {
            newCursor.selectPath("child::*");
            while (newCursor.toNextSelection()) {
                XmlObject object = newCursor.getObject();
                if (object instanceof CTR) {
                    XWPFRun xWPFRun = new XWPFRun((CTR) object, this);
                    this.runs.add(xWPFRun);
                    this.iruns.add(xWPFRun);
                }
                if (object instanceof CTHyperlink) {
                    CTHyperlink cTHyperlink = (CTHyperlink) object;
                    for (CTR xWPFHyperlinkRun : cTHyperlink.getRArray()) {
                        XWPFHyperlinkRun xWPFHyperlinkRun2 = new XWPFHyperlinkRun(cTHyperlink, xWPFHyperlinkRun, this);
                        this.runs.add(xWPFHyperlinkRun2);
                        this.iruns.add(xWPFHyperlinkRun2);
                    }
                }
                if (object instanceof CTSimpleField) {
                    CTSimpleField cTSimpleField = (CTSimpleField) object;
                    for (CTR xWPFFieldRun : cTSimpleField.getRArray()) {
                        XWPFFieldRun xWPFFieldRun2 = new XWPFFieldRun(cTSimpleField, xWPFFieldRun, this);
                        this.runs.add(xWPFFieldRun2);
                        this.iruns.add(xWPFFieldRun2);
                    }
                }
                if (object instanceof CTSdtBlock) {
                    this.iruns.add(new XWPFSDT((CTSdtBlock) object, this.part));
                }
                if (object instanceof CTSdtRun) {
                    this.iruns.add(new XWPFSDT((CTSdtRun) object, this.part));
                }
                if (object instanceof CTRunTrackChange) {
                    for (CTR xWPFRun2 : ((CTRunTrackChange) object).getRArray()) {
                        XWPFRun xWPFRun3 = new XWPFRun(xWPFRun2, this);
                        this.runs.add(xWPFRun3);
                        this.iruns.add(xWPFRun3);
                    }
                }
                if (object instanceof CTSmartTagRun) {
                    buildRunsInOrderFromXml(object);
                }
                if (object instanceof CTRunTrackChange) {
                    for (CTRunTrackChange buildRunsInOrderFromXml : ((CTRunTrackChange) object).getInsArray()) {
                        buildRunsInOrderFromXml(buildRunsInOrderFromXml);
                    }
                }
            }
        } finally {
            newCursor.dispose();
        }
    }

    @Internal
    public CTP getCTP() {
        return this.paragraph;
    }

    public List<XWPFRun> getRuns() {
        return Collections.unmodifiableList(this.runs);
    }

    public boolean runsIsEmpty() {
        return this.runs.isEmpty();
    }

    public List<IRunElement> getIRuns() {
        return Collections.unmodifiableList(this.iruns);
    }

    public boolean isEmpty() {
        return !this.paragraph.getDomNode().hasChildNodes();
    }

    public XWPFDocument getDocument() {
        return this.document;
    }

    public String getText() {
        StringBuilder sb = new StringBuilder(64);
        for (IRunElement next : this.iruns) {
            if (next instanceof XWPFRun) {
                XWPFRun xWPFRun = (XWPFRun) next;
                if (xWPFRun.getCTR().getDelTextArray().length == 0) {
                    sb.append(xWPFRun);
                }
            } else if (next instanceof XWPFSDT) {
                sb.append(((XWPFSDT) next).getContent().getText());
            } else {
                sb.append(next);
            }
        }
        sb.append(this.footnoteText);
        return sb.toString();
    }

    public String getStyleID() {
        if (this.paragraph.getPPr() == null || this.paragraph.getPPr().getPStyle() == null || this.paragraph.getPPr().getPStyle().getVal() == null) {
            return null;
        }
        return this.paragraph.getPPr().getPStyle().getVal();
    }

    public BigInteger getNumID() {
        if (this.paragraph.getPPr() == null || this.paragraph.getPPr().getNumPr() == null || this.paragraph.getPPr().getNumPr().getNumId() == null) {
            return null;
        }
        return this.paragraph.getPPr().getNumPr().getNumId().getVal();
    }

    public void setNumID(BigInteger bigInteger) {
        if (this.paragraph.getPPr() == null) {
            this.paragraph.addNewPPr();
        }
        if (this.paragraph.getPPr().getNumPr() == null) {
            this.paragraph.getPPr().addNewNumPr();
        }
        if (this.paragraph.getPPr().getNumPr().getNumId() == null) {
            this.paragraph.getPPr().getNumPr().addNewNumId();
        }
        this.paragraph.getPPr().getNumPr().getNumId().setVal(bigInteger);
    }

    public void setNumILvl(BigInteger bigInteger) {
        if (this.paragraph.getPPr() == null) {
            this.paragraph.addNewPPr();
        }
        if (this.paragraph.getPPr().getNumPr() == null) {
            this.paragraph.getPPr().addNewNumPr();
        }
        if (this.paragraph.getPPr().getNumPr().getIlvl() == null) {
            this.paragraph.getPPr().getNumPr().addNewIlvl();
        }
        this.paragraph.getPPr().getNumPr().getIlvl().setVal(bigInteger);
    }

    public BigInteger getNumIlvl() {
        if (this.paragraph.getPPr() == null || this.paragraph.getPPr().getNumPr() == null || this.paragraph.getPPr().getNumPr().getIlvl() == null) {
            return null;
        }
        return this.paragraph.getPPr().getNumPr().getIlvl().getVal();
    }

    public String getNumFmt() {
        XWPFNum num;
        CTLvl cTLvl;
        BigInteger numID = getNumID();
        XWPFNumbering numbering = this.document.getNumbering();
        if (!(numID == null || numbering == null || (num = numbering.getNum(numID)) == null)) {
            BigInteger numIlvl = getNumIlvl();
            CTAbstractNum abstractNum = numbering.getAbstractNum(num.getCTNum().getAbstractNumId().getVal()).getAbstractNum();
            int i = 0;
            while (true) {
                if (i >= abstractNum.sizeOfLvlArray()) {
                    cTLvl = null;
                    break;
                }
                cTLvl = abstractNum.getLvlArray(i);
                if (cTLvl.getIlvl().equals(numIlvl)) {
                    break;
                }
                i++;
            }
            if (!(cTLvl == null || cTLvl.getNumFmt() == null || cTLvl.getNumFmt().getVal() == null)) {
                return cTLvl.getNumFmt().getVal().toString();
            }
        }
        return null;
    }

    public String getNumLevelText() {
        XWPFNum num;
        CTDecimalNumber abstractNumId;
        BigInteger val;
        XWPFAbstractNum abstractNum;
        CTAbstractNum cTAbstractNum;
        CTLvl cTLvl;
        BigInteger numID = getNumID();
        XWPFNumbering numbering = this.document.getNumbering();
        if (!(numID == null || numbering == null || (num = numbering.getNum(numID)) == null)) {
            BigInteger numIlvl = getNumIlvl();
            CTNum cTNum = num.getCTNum();
            if (cTNum == null || (abstractNumId = cTNum.getAbstractNumId()) == null || (val = abstractNumId.getVal()) == null || (abstractNum = numbering.getAbstractNum(val)) == null || (cTAbstractNum = abstractNum.getCTAbstractNum()) == null) {
                return null;
            }
            int i = 0;
            while (true) {
                if (i >= cTAbstractNum.sizeOfLvlArray()) {
                    cTLvl = null;
                    break;
                }
                cTLvl = cTAbstractNum.getLvlArray(i);
                if (cTLvl != null && cTLvl.getIlvl() != null && cTLvl.getIlvl().equals(numIlvl)) {
                    break;
                }
                i++;
            }
            if (!(cTLvl == null || cTLvl.getLvlText() == null || cTLvl.getLvlText().getVal() == null)) {
                return cTLvl.getLvlText().getVal();
            }
        }
        return null;
    }

    public BigInteger getNumStartOverride() {
        XWPFNum num;
        CTNum cTNum;
        CTNumLvl cTNumLvl;
        BigInteger numID = getNumID();
        XWPFNumbering numbering = this.document.getNumbering();
        if (numID == null || numbering == null || (num = numbering.getNum(numID)) == null || (cTNum = num.getCTNum()) == null) {
            return null;
        }
        BigInteger numIlvl = getNumIlvl();
        int i = 0;
        while (true) {
            if (i >= cTNum.sizeOfLvlOverrideArray()) {
                cTNumLvl = null;
                break;
            }
            cTNumLvl = cTNum.getLvlOverrideArray(i);
            if (cTNumLvl != null && cTNumLvl.getIlvl() != null && cTNumLvl.getIlvl().equals(numIlvl)) {
                break;
            }
            i++;
        }
        if (!(cTNumLvl == null || cTNumLvl.getStartOverride() == null)) {
            return cTNumLvl.getStartOverride().getVal();
        }
        return null;
    }

    public boolean isKeepNext() {
        if (getCTP() == null || getCTP().getPPr() == null || !getCTP().getPPr().isSetKeepNext()) {
            return false;
        }
        return POIXMLUnits.parseOnOff(getCTP().getPPr().getKeepNext().xgetVal());
    }

    public void setKeepNext(boolean z) {
        CTOnOff newInstance = CTOnOff.Factory.newInstance();
        newInstance.setVal(z ? STOnOff1.ON : STOnOff1.OFF);
        getCTP().getPPr().setKeepNext(newInstance);
    }

    public String getParagraphText() {
        StringBuilder sb = new StringBuilder(64);
        for (XWPFRun append : this.runs) {
            sb.append(append);
        }
        return sb.toString();
    }

    public String getPictureText() {
        StringBuilder sb = new StringBuilder(64);
        for (XWPFRun pictureText : this.runs) {
            sb.append(pictureText.getPictureText());
        }
        return sb.toString();
    }

    public String getFootnoteText() {
        return this.footnoteText.toString();
    }

    public ParagraphAlignment getAlignment() {
        CTPPr cTPPr = getCTPPr();
        if (cTPPr == null || !cTPPr.isSetJc()) {
            return ParagraphAlignment.LEFT;
        }
        return ParagraphAlignment.valueOf(cTPPr.getJc().getVal().intValue());
    }

    public void setAlignment(ParagraphAlignment paragraphAlignment) {
        CTPPr cTPPr = getCTPPr();
        (cTPPr.isSetJc() ? cTPPr.getJc() : cTPPr.addNewJc()).setVal(STJc.Enum.forInt(paragraphAlignment.getValue()));
    }

    public int getFontAlignment() {
        return getAlignment().getValue();
    }

    public void setFontAlignment(int i) {
        setAlignment(ParagraphAlignment.valueOf(i));
    }

    public TextAlignment getVerticalAlignment() {
        CTPPr cTPPr = getCTPPr();
        if (cTPPr == null || !cTPPr.isSetTextAlignment()) {
            return TextAlignment.AUTO;
        }
        return TextAlignment.valueOf(cTPPr.getTextAlignment().getVal().intValue());
    }

    public void setVerticalAlignment(TextAlignment textAlignment) {
        CTPPr cTPPr = getCTPPr();
        (cTPPr.isSetTextAlignment() ? cTPPr.getTextAlignment() : cTPPr.addNewTextAlignment()).setVal(STTextAlignment.Enum.forInt(textAlignment.getValue()));
    }

    public Borders getBorderTop() {
        CTPBdr cTPBrd = getCTPBrd(false);
        CTBorder top = cTPBrd != null ? cTPBrd.getTop() : null;
        return Borders.valueOf((top != null ? top.getVal() : STBorder.NONE).intValue());
    }

    public void setBorderTop(Borders borders) {
        CTPBdr cTPBrd = getCTPBrd(true);
        if (cTPBrd != null) {
            CTBorder top = cTPBrd.isSetTop() ? cTPBrd.getTop() : cTPBrd.addNewTop();
            if (borders.getValue() == Borders.NONE.getValue()) {
                cTPBrd.unsetTop();
            } else {
                top.setVal(STBorder.Enum.forInt(borders.getValue()));
            }
        } else {
            throw new RuntimeException("invalid paragraph state");
        }
    }

    public Borders getBorderBottom() {
        CTPBdr cTPBrd = getCTPBrd(false);
        CTBorder bottom = cTPBrd != null ? cTPBrd.getBottom() : null;
        return Borders.valueOf((bottom != null ? bottom.getVal() : STBorder.NONE).intValue());
    }

    public void setBorderBottom(Borders borders) {
        CTPBdr cTPBrd = getCTPBrd(true);
        CTBorder bottom = cTPBrd.isSetBottom() ? cTPBrd.getBottom() : cTPBrd.addNewBottom();
        if (borders.getValue() == Borders.NONE.getValue()) {
            cTPBrd.unsetBottom();
        } else {
            bottom.setVal(STBorder.Enum.forInt(borders.getValue()));
        }
    }

    public Borders getBorderLeft() {
        CTPBdr cTPBrd = getCTPBrd(false);
        CTBorder left = cTPBrd != null ? cTPBrd.getLeft() : null;
        return Borders.valueOf((left != null ? left.getVal() : STBorder.NONE).intValue());
    }

    public void setBorderLeft(Borders borders) {
        CTPBdr cTPBrd = getCTPBrd(true);
        CTBorder left = cTPBrd.isSetLeft() ? cTPBrd.getLeft() : cTPBrd.addNewLeft();
        if (borders.getValue() == Borders.NONE.getValue()) {
            cTPBrd.unsetLeft();
        } else {
            left.setVal(STBorder.Enum.forInt(borders.getValue()));
        }
    }

    public Borders getBorderRight() {
        CTPBdr cTPBrd = getCTPBrd(false);
        CTBorder right = cTPBrd != null ? cTPBrd.getRight() : null;
        return Borders.valueOf((right != null ? right.getVal() : STBorder.NONE).intValue());
    }

    public void setBorderRight(Borders borders) {
        CTPBdr cTPBrd = getCTPBrd(true);
        CTBorder right = cTPBrd.isSetRight() ? cTPBrd.getRight() : cTPBrd.addNewRight();
        if (borders.getValue() == Borders.NONE.getValue()) {
            cTPBrd.unsetRight();
        } else {
            right.setVal(STBorder.Enum.forInt(borders.getValue()));
        }
    }

    public Borders getBorderBetween() {
        CTPBdr cTPBrd = getCTPBrd(false);
        CTBorder between = cTPBrd != null ? cTPBrd.getBetween() : null;
        return Borders.valueOf((between != null ? between.getVal() : STBorder.NONE).intValue());
    }

    public void setBorderBetween(Borders borders) {
        CTPBdr cTPBrd = getCTPBrd(true);
        CTBorder between = cTPBrd.isSetBetween() ? cTPBrd.getBetween() : cTPBrd.addNewBetween();
        if (borders.getValue() == Borders.NONE.getValue()) {
            cTPBrd.unsetBetween();
        } else {
            between.setVal(STBorder.Enum.forInt(borders.getValue()));
        }
    }

    public boolean isPageBreak() {
        CTPPr cTPPr = getCTPPr();
        CTOnOff pageBreakBefore = cTPPr.isSetPageBreakBefore() ? cTPPr.getPageBreakBefore() : null;
        if (pageBreakBefore == null) {
            return false;
        }
        return POIXMLUnits.parseOnOff(pageBreakBefore.xgetVal());
    }

    public void setPageBreak(boolean z) {
        CTPPr cTPPr = getCTPPr();
        (cTPPr.isSetPageBreakBefore() ? cTPPr.getPageBreakBefore() : cTPPr.addNewPageBreakBefore()).setVal(z ? STOnOff1.ON : STOnOff1.OFF);
    }

    public int getSpacingAfter() {
        CTSpacing cTSpacing = getCTSpacing(false);
        if (cTSpacing == null || !cTSpacing.isSetAfter()) {
            return -1;
        }
        return (int) Units.toDXA(POIXMLUnits.parseLength(cTSpacing.xgetAfter()));
    }

    public void setSpacingAfter(int i) {
        CTSpacing cTSpacing = getCTSpacing(true);
        if (cTSpacing != null) {
            cTSpacing.setAfter(new BigInteger(Integer.toString(i)));
        }
    }

    public int getSpacingAfterLines() {
        CTSpacing cTSpacing = getCTSpacing(false);
        if (cTSpacing == null || !cTSpacing.isSetAfterLines()) {
            return -1;
        }
        return cTSpacing.getAfterLines().intValue();
    }

    public void setSpacingAfterLines(int i) {
        getCTSpacing(true).setAfterLines(new BigInteger(Integer.toString(i)));
    }

    public int getSpacingBefore() {
        CTSpacing cTSpacing = getCTSpacing(false);
        if (cTSpacing == null || !cTSpacing.isSetBefore()) {
            return -1;
        }
        return (int) Units.toDXA(POIXMLUnits.parseLength(cTSpacing.xgetBefore()));
    }

    public void setSpacingBefore(int i) {
        getCTSpacing(true).setBefore(new BigInteger(Integer.toString(i)));
    }

    public int getSpacingBeforeLines() {
        CTSpacing cTSpacing = getCTSpacing(false);
        if (cTSpacing == null || !cTSpacing.isSetBeforeLines()) {
            return -1;
        }
        return cTSpacing.getBeforeLines().intValue();
    }

    public void setSpacingBeforeLines(int i) {
        getCTSpacing(true).setBeforeLines(new BigInteger(Integer.toString(i)));
    }

    public LineSpacingRule getSpacingLineRule() {
        CTSpacing cTSpacing = getCTSpacing(false);
        return (cTSpacing == null || !cTSpacing.isSetLineRule()) ? LineSpacingRule.AUTO : LineSpacingRule.valueOf(cTSpacing.getLineRule().intValue());
    }

    public void setSpacingLineRule(LineSpacingRule lineSpacingRule) {
        getCTSpacing(true).setLineRule(STLineSpacingRule.Enum.forInt(lineSpacingRule.getValue()));
    }

    public double getSpacingBetween() {
        CTSpacing cTSpacing = getCTSpacing(false);
        if (cTSpacing == null || !cTSpacing.isSetLine()) {
            return -1.0d;
        }
        return Units.toDXA(POIXMLUnits.parseLength(cTSpacing.xgetLine())) / ((double) ((cTSpacing.getLineRule() == null || cTSpacing.getLineRule() == STLineSpacingRule.AUTO) ? 240 : 20));
    }

    public void setSpacingBetween(double d, LineSpacingRule lineSpacingRule) {
        CTSpacing cTSpacing = getCTSpacing(true);
        if (lineSpacingRule == LineSpacingRule.AUTO) {
            cTSpacing.setLine(new BigInteger(String.valueOf(Math.round(d * 240.0d))));
        } else {
            cTSpacing.setLine(new BigInteger(String.valueOf(Math.round(d * 20.0d))));
        }
        cTSpacing.setLineRule(STLineSpacingRule.Enum.forInt(lineSpacingRule.getValue()));
    }

    public void setSpacingBetween(double d) {
        setSpacingBetween(d, LineSpacingRule.AUTO);
    }

    public int getIndentationLeft() {
        CTInd cTInd = getCTInd(false);
        if (cTInd == null || !cTInd.isSetLeft()) {
            return -1;
        }
        return (int) Units.toDXA(POIXMLUnits.parseLength(cTInd.xgetLeft()));
    }

    public void setIndentationLeft(int i) {
        getCTInd(true).setLeft(new BigInteger(Integer.toString(i)));
    }

    public int getIndentationLeftChars() {
        CTInd cTInd = getCTInd(false);
        if (cTInd == null || !cTInd.isSetLeftChars()) {
            return -1;
        }
        return cTInd.getLeftChars().intValue();
    }

    public void setIndentationLeftChars(int i) {
        getCTInd(true).setLeftChars(new BigInteger(Integer.toString(i)));
    }

    public int getIndentationRight() {
        CTInd cTInd = getCTInd(false);
        if (cTInd == null || !cTInd.isSetRight()) {
            return -1;
        }
        return (int) Units.toDXA(POIXMLUnits.parseLength(cTInd.xgetRight()));
    }

    public void setIndentationRight(int i) {
        getCTInd(true).setRight(new BigInteger(Integer.toString(i)));
    }

    public int getIndentationRightChars() {
        CTInd cTInd = getCTInd(false);
        if (cTInd == null || !cTInd.isSetRightChars()) {
            return -1;
        }
        return cTInd.getRightChars().intValue();
    }

    public void setIndentationRightChars(int i) {
        getCTInd(true).setRightChars(new BigInteger(Integer.toString(i)));
    }

    public int getIndentationHanging() {
        CTInd cTInd = getCTInd(false);
        if (cTInd == null || !cTInd.isSetHanging()) {
            return -1;
        }
        return (int) Units.toDXA(POIXMLUnits.parseLength(cTInd.xgetHanging()));
    }

    public void setIndentationHanging(int i) {
        getCTInd(true).setHanging(new BigInteger(Integer.toString(i)));
    }

    public int getIndentationFirstLine() {
        CTInd cTInd = getCTInd(false);
        if (cTInd == null || !cTInd.isSetFirstLine()) {
            return -1;
        }
        return (int) Units.toDXA(POIXMLUnits.parseLength(cTInd.xgetFirstLine()));
    }

    public void setIndentationFirstLine(int i) {
        getCTInd(true).setFirstLine(new BigInteger(Integer.toString(i)));
    }

    public int getIndentFromLeft() {
        return getIndentationLeft();
    }

    public void setIndentFromLeft(int i) {
        setIndentationLeft(i);
    }

    public int getIndentFromRight() {
        return getIndentationRight();
    }

    public void setIndentFromRight(int i) {
        setIndentationRight(i);
    }

    public int getFirstLineIndent() {
        return getIndentationFirstLine();
    }

    public void setFirstLineIndent(int i) {
        setIndentationFirstLine(i);
    }

    public boolean isWordWrapped() {
        return getCTPPr().isSetWordWrap() && POIXMLUnits.parseOnOff(getCTPPr().getWordWrap());
    }

    public void setWordWrapped(boolean z) {
        CTPPr cTPPr = getCTPPr();
        if (z) {
            (cTPPr.isSetWordWrap() ? cTPPr.getWordWrap() : cTPPr.addNewWordWrap()).setVal(STOnOff1.ON);
        } else if (cTPPr.isSetWordWrap()) {
            cTPPr.unsetWordWrap();
        }
    }

    public boolean isWordWrap() {
        return isWordWrapped();
    }

    @Deprecated
    public void setWordWrap(boolean z) {
        setWordWrapped(z);
    }

    public String getStyle() {
        CTPPr cTPPr = getCTPPr();
        CTString pStyle = cTPPr.isSetPStyle() ? cTPPr.getPStyle() : null;
        if (pStyle != null) {
            return pStyle.getVal();
        }
        return null;
    }

    public void setStyle(String str) {
        CTPPr cTPPr = getCTPPr();
        (cTPPr.getPStyle() != null ? cTPPr.getPStyle() : cTPPr.addNewPStyle()).setVal(str);
    }

    private CTPBdr getCTPBrd(boolean z) {
        CTPPr cTPPr = getCTPPr();
        CTPBdr pBdr = cTPPr.isSetPBdr() ? cTPPr.getPBdr() : null;
        return (!z || pBdr != null) ? pBdr : cTPPr.addNewPBdr();
    }

    private CTSpacing getCTSpacing(boolean z) {
        CTPPr cTPPr = getCTPPr();
        CTSpacing spacing = cTPPr.getSpacing();
        return (!z || spacing != null) ? spacing : cTPPr.addNewSpacing();
    }

    private CTInd getCTInd(boolean z) {
        CTPPr cTPPr = getCTPPr();
        CTInd ind = cTPPr.getInd();
        return (!z || ind != null) ? ind : cTPPr.addNewInd();
    }

    @Internal
    public CTPPr getCTPPr() {
        CTPPr pPr = this.paragraph.getPPr();
        CTP ctp = this.paragraph;
        if (pPr == null) {
            return ctp.addNewPPr();
        }
        return ctp.getPPr();
    }

    /* access modifiers changed from: protected */
    public void addRun(CTR ctr) {
        int sizeOfRArray = this.paragraph.sizeOfRArray();
        this.paragraph.addNewR();
        this.paragraph.setRArray(sizeOfRArray, ctr);
    }

    public XWPFRun createRun() {
        XWPFRun xWPFRun = new XWPFRun(this.paragraph.addNewR(), (IRunBody) this);
        this.runs.add(xWPFRun);
        this.iruns.add(xWPFRun);
        return xWPFRun;
    }

    public XWPFHyperlinkRun createHyperlinkRun(String str) {
        String id = getPart().getPackagePart().addExternalRelationship(str, XWPFRelation.HYPERLINK.getRelation()).getId();
        CTHyperlink addNewHyperlink = getCTP().addNewHyperlink();
        addNewHyperlink.setId(id);
        addNewHyperlink.addNewR();
        XWPFHyperlinkRun xWPFHyperlinkRun = new XWPFHyperlinkRun(addNewHyperlink, addNewHyperlink.getRArray(0), this);
        this.runs.add(xWPFHyperlinkRun);
        this.iruns.add(xWPFHyperlinkRun);
        return xWPFHyperlinkRun;
    }

    public XWPFFieldRun createFieldRun() {
        CTSimpleField addNewFldSimple = this.paragraph.addNewFldSimple();
        XWPFFieldRun xWPFFieldRun = new XWPFFieldRun(addNewFldSimple, addNewFldSimple.addNewR(), this);
        this.runs.add(xWPFFieldRun);
        this.iruns.add(xWPFFieldRun);
        return xWPFFieldRun;
    }

    public XWPFRun insertNewRun(int i) {
        if (i == this.runs.size()) {
            return createRun();
        }
        return insertNewProvidedRun(i, new XWPFParagraph$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$insertNewRun$0$org-apache-poi-xwpf-usermodel-XWPFParagraph  reason: not valid java name */
    public /* synthetic */ XWPFRun m2315lambda$insertNewRun$0$orgapachepoixwpfusermodelXWPFParagraph(XmlCursor xmlCursor) {
        xmlCursor.beginElement("r", CTR.type.getName().getNamespaceURI());
        xmlCursor.toParent();
        return new XWPFRun((CTR) xmlCursor.getObject(), (IRunBody) this);
    }

    public XWPFHyperlinkRun insertNewHyperlinkRun(int i, String str) {
        if (i == this.runs.size()) {
            return createHyperlinkRun(str);
        }
        XWPFHyperlinkRun xWPFHyperlinkRun = (XWPFHyperlinkRun) insertNewProvidedRun(i, new XWPFParagraph$$ExternalSyntheticLambda3(this));
        if (xWPFHyperlinkRun != null) {
            xWPFHyperlinkRun.getCTHyperlink().setId(getPart().getPackagePart().addExternalRelationship(str, XWPFRelation.HYPERLINK.getRelation()).getId());
        }
        return xWPFHyperlinkRun;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$insertNewHyperlinkRun$1$org-apache-poi-xwpf-usermodel-XWPFParagraph  reason: not valid java name */
    public /* synthetic */ XWPFHyperlinkRun m2314lambda$insertNewHyperlinkRun$1$orgapachepoixwpfusermodelXWPFParagraph(XmlCursor xmlCursor) {
        xmlCursor.beginElement("hyperlink", CTHyperlink.type.getName().getNamespaceURI());
        xmlCursor.toParent();
        CTHyperlink cTHyperlink = (CTHyperlink) xmlCursor.getObject();
        return new XWPFHyperlinkRun(cTHyperlink, cTHyperlink.addNewR(), this);
    }

    public XWPFFieldRun insertNewFieldRun(int i) {
        if (i == this.runs.size()) {
            return createFieldRun();
        }
        return (XWPFFieldRun) insertNewProvidedRun(i, new XWPFParagraph$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$insertNewFieldRun$2$org-apache-poi-xwpf-usermodel-XWPFParagraph  reason: not valid java name */
    public /* synthetic */ XWPFFieldRun m2313lambda$insertNewFieldRun$2$orgapachepoixwpfusermodelXWPFParagraph(XmlCursor xmlCursor) {
        xmlCursor.beginElement("fldSimple", CTSimpleField.type.getName().getNamespaceURI());
        xmlCursor.toParent();
        CTSimpleField cTSimpleField = (CTSimpleField) xmlCursor.getObject();
        return new XWPFFieldRun(cTSimpleField, cTSimpleField.addNewR(), this);
    }

    private <T extends XWPFRun> T insertNewProvidedRun(int i, Function<XmlCursor, T> function) {
        if (i < 0 || i >= this.runs.size()) {
            return null;
        }
        XWPFRun xWPFRun = this.runs.get(i);
        XmlCursor newCursor = xWPFRun.getCTR().newCursor();
        if (!isCursorInParagraph(newCursor)) {
            newCursor.toParent();
        }
        if (isCursorInParagraph(newCursor)) {
            T t = (XWPFRun) function.apply(newCursor);
            int size = this.iruns.size();
            int indexOf = this.iruns.indexOf(xWPFRun);
            if (indexOf != -1) {
                size = indexOf;
            }
            this.iruns.add(size, t);
            this.runs.add(i, t);
            return t;
        }
        newCursor.dispose();
        return null;
    }

    private boolean isCursorInParagraph(XmlCursor xmlCursor) {
        XmlCursor newCursor = xmlCursor.newCursor();
        newCursor.toParent();
        boolean z = newCursor.getObject() == this.paragraph;
        newCursor.dispose();
        return z;
    }

    public TextSegment searchText(String str, PositionInParagraph positionInParagraph) {
        int i;
        int i2;
        String str2 = str;
        int run = positionInParagraph.getRun();
        int text = positionInParagraph.getText();
        int i3 = positionInParagraph.getChar();
        CTR[] rArray = this.paragraph.getRArray();
        int i4 = run;
        int i5 = 0;
        boolean z = false;
        int i6 = 0;
        while (i4 < rArray.length) {
            XmlCursor newCursor = rArray[i4].newCursor();
            newCursor.selectPath("./*");
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            while (newCursor.toNextSelection()) {
                try {
                    XmlObject object = newCursor.getObject();
                    if (object instanceof CTText) {
                        if (i7 >= text) {
                            String stringValue = ((CTText) object).getStringValue();
                            int i10 = i4 == run ? i3 : 0;
                            while (i10 < stringValue.length()) {
                                int i11 = run;
                                int i12 = text;
                                if (stringValue.charAt(i10) == str2.charAt(0) && i5 == 0) {
                                    z = true;
                                    i6 = i4;
                                    i8 = i7;
                                    i9 = i10;
                                }
                                if (stringValue.charAt(i10) == str2.charAt(i5)) {
                                    int i13 = i5 + 1;
                                    if (i13 < str.length()) {
                                        i5 = i13;
                                    } else if (z) {
                                        TextSegment textSegment = new TextSegment();
                                        textSegment.setBeginRun(i6);
                                        textSegment.setBeginText(i8);
                                        textSegment.setBeginChar(i9);
                                        textSegment.setEndRun(i4);
                                        textSegment.setEndText(i7);
                                        textSegment.setEndChar(i10);
                                        return textSegment;
                                    }
                                } else {
                                    i5 = 0;
                                }
                                i10++;
                                run = i11;
                                text = i12;
                            }
                        }
                        i2 = run;
                        i = text;
                        i7++;
                    } else {
                        i2 = run;
                        i = text;
                        if (object instanceof CTProofErr) {
                            newCursor.removeXml();
                        } else if (!(object instanceof CTRPr)) {
                            i5 = 0;
                        }
                    }
                    run = i2;
                    text = i;
                } finally {
                    newCursor.dispose();
                }
            }
            newCursor.dispose();
            i4++;
            run = run;
            text = text;
        }
        return null;
    }

    public String getText(TextSegment textSegment) {
        int beginRun = textSegment.getBeginRun();
        int beginText = textSegment.getBeginText();
        int beginChar = textSegment.getBeginChar();
        int endRun = textSegment.getEndRun();
        int endText = textSegment.getEndText();
        int endChar = textSegment.getEndChar();
        StringBuilder sb = new StringBuilder();
        CTR[] rArray = this.paragraph.getRArray();
        int i = beginRun;
        while (i <= endRun) {
            CTText[] tArray = rArray[i].getTArray();
            int length = tArray.length - 1;
            int i2 = i == beginRun ? beginText : 0;
            if (i == endRun) {
                length = endText;
            }
            while (i2 <= length) {
                String stringValue = tArray[i2].getStringValue();
                int length2 = stringValue.length() - 1;
                int i3 = (i2 == beginText && i == beginRun) ? beginChar : 0;
                if (i2 == endText && i == endRun) {
                    length2 = endChar;
                }
                sb.append(stringValue, i3, length2 + 1);
                i2++;
            }
            i++;
        }
        return sb.toString();
    }

    public boolean removeRun(int i) {
        if (i < 0 || i >= this.runs.size()) {
            return false;
        }
        XWPFRun xWPFRun = this.runs.get(i);
        if (xWPFRun instanceof XWPFHyperlinkRun) {
            XWPFHyperlinkRun xWPFHyperlinkRun = (XWPFHyperlinkRun) xWPFRun;
            if (isTheOnlyCTHyperlinkInRuns(xWPFHyperlinkRun)) {
                XmlCursor newCursor = xWPFHyperlinkRun.getCTHyperlink().newCursor();
                newCursor.removeXml();
                newCursor.dispose();
                this.runs.remove(i);
                this.iruns.remove(xWPFRun);
                return true;
            }
        }
        if (xWPFRun instanceof XWPFFieldRun) {
            XWPFFieldRun xWPFFieldRun = (XWPFFieldRun) xWPFRun;
            if (isTheOnlyCTFieldInRuns(xWPFFieldRun)) {
                XmlCursor newCursor2 = xWPFFieldRun.getCTField().newCursor();
                newCursor2.removeXml();
                newCursor2.dispose();
                this.runs.remove(i);
                this.iruns.remove(xWPFRun);
                return true;
            }
        }
        XmlCursor newCursor3 = xWPFRun.getCTR().newCursor();
        newCursor3.removeXml();
        newCursor3.dispose();
        this.runs.remove(i);
        this.iruns.remove(xWPFRun);
        return true;
    }

    private boolean isTheOnlyCTHyperlinkInRuns(XWPFHyperlinkRun xWPFHyperlinkRun) {
        return this.runs.stream().filter(new XWPFParagraph$$ExternalSyntheticLambda4(xWPFHyperlinkRun.getCTHyperlink())).count() <= 1;
    }

    static /* synthetic */ boolean lambda$isTheOnlyCTHyperlinkInRuns$3(CTHyperlink cTHyperlink, XWPFRun xWPFRun) {
        return (xWPFRun instanceof XWPFHyperlinkRun) && cTHyperlink == ((XWPFHyperlinkRun) xWPFRun).getCTHyperlink();
    }

    private boolean isTheOnlyCTFieldInRuns(XWPFFieldRun xWPFFieldRun) {
        return this.runs.stream().filter(new XWPFParagraph$$ExternalSyntheticLambda1(xWPFFieldRun.getCTField())).count() <= 1;
    }

    static /* synthetic */ boolean lambda$isTheOnlyCTFieldInRuns$4(CTSimpleField cTSimpleField, XWPFRun xWPFRun) {
        return (xWPFRun instanceof XWPFFieldRun) && cTSimpleField == ((XWPFFieldRun) xWPFRun).getCTField();
    }

    public BodyElementType getElementType() {
        return BodyElementType.PARAGRAPH;
    }

    public IBody getBody() {
        return this.part;
    }

    public POIXMLDocumentPart getPart() {
        IBody iBody = this.part;
        if (iBody != null) {
            return iBody.getPart();
        }
        return null;
    }

    public BodyType getPartType() {
        return this.part.getPartType();
    }

    public void addRun(XWPFRun xWPFRun) {
        if (!this.runs.contains(xWPFRun)) {
            this.runs.add(xWPFRun);
            this.iruns.add(xWPFRun);
        }
    }

    public XWPFRun getRun(CTR ctr) {
        for (int i = 0; i < getRuns().size(); i++) {
            if (getRuns().get(i).getCTR() == ctr) {
                return getRuns().get(i);
            }
        }
        return null;
    }

    public void addFootnoteReference(XWPFAbstractFootnoteEndnote xWPFAbstractFootnoteEndnote) {
        CTR ctr = createRun().getCTR();
        ctr.addNewRPr().addNewRStyle().setVal("FootnoteReference");
        if (xWPFAbstractFootnoteEndnote instanceof XWPFEndnote) {
            ctr.addNewEndnoteReference().setId(xWPFAbstractFootnoteEndnote.getId());
        } else {
            ctr.addNewFootnoteReference().setId(xWPFAbstractFootnoteEndnote.getId());
        }
    }
}
