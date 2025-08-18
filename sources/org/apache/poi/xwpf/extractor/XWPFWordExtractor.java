package org.apache.poi.xwpf.extractor;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.model.XWPFCommentsDecorator;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.model.XWPFParagraphDecorator;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.ICell;
import org.apache.poi.xwpf.usermodel.IRunElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHyperlink;
import org.apache.poi.xwpf.usermodel.XWPFHyperlinkRun;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRelation;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFSDT;
import org.apache.poi.xwpf.usermodel.XWPFSDTCell;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

public class XWPFWordExtractor implements POIXMLTextExtractor {
    public static final List<XWPFRelation> SUPPORTED_TYPES = Collections.unmodifiableList(Arrays.asList(new XWPFRelation[]{XWPFRelation.DOCUMENT, XWPFRelation.TEMPLATE, XWPFRelation.MACRO_DOCUMENT, XWPFRelation.MACRO_TEMPLATE_DOCUMENT}));
    private boolean concatenatePhoneticRuns;
    private boolean doCloseFilesystem;
    private final XWPFDocument document;
    private boolean fetchHyperlinks;

    public XWPFWordExtractor(OPCPackage oPCPackage) throws IOException {
        this(new XWPFDocument(oPCPackage));
    }

    public XWPFWordExtractor(XWPFDocument xWPFDocument) {
        this.concatenatePhoneticRuns = true;
        this.doCloseFilesystem = true;
        this.document = xWPFDocument;
    }

    public void setFetchHyperlinks(boolean z) {
        this.fetchHyperlinks = z;
    }

    public void setConcatenatePhoneticRuns(boolean z) {
        this.concatenatePhoneticRuns = z;
    }

    public String getText() {
        StringBuilder sb = new StringBuilder(64);
        XWPFHeaderFooterPolicy headerFooterPolicy = this.document.getHeaderFooterPolicy();
        extractHeaders(sb, headerFooterPolicy);
        for (IBodyElement appendBodyElementText : this.document.getBodyElements()) {
            appendBodyElementText(sb, appendBodyElementText);
            sb.append(10);
        }
        extractFooters(sb, headerFooterPolicy);
        return sb.toString();
    }

    public void appendBodyElementText(StringBuilder sb, IBodyElement iBodyElement) {
        if (iBodyElement instanceof XWPFParagraph) {
            appendParagraphText(sb, (XWPFParagraph) iBodyElement);
        } else if (iBodyElement instanceof XWPFTable) {
            appendTableText(sb, (XWPFTable) iBodyElement);
        } else if (iBodyElement instanceof XWPFSDT) {
            sb.append(((XWPFSDT) iBodyElement).getContent().getText());
        }
    }

    public void appendParagraphText(StringBuilder sb, XWPFParagraph xWPFParagraph) {
        XWPFHeaderFooterPolicy xWPFHeaderFooterPolicy;
        XWPFHyperlink hyperlink;
        CTSectPr sectPr = xWPFParagraph.getCTP().getPPr() != null ? xWPFParagraph.getCTP().getPPr().getSectPr() : null;
        if (sectPr != null) {
            xWPFHeaderFooterPolicy = new XWPFHeaderFooterPolicy(this.document, sectPr);
            extractHeaders(sb, xWPFHeaderFooterPolicy);
        } else {
            xWPFHeaderFooterPolicy = null;
        }
        for (IRunElement next : xWPFParagraph.getIRuns()) {
            if (next instanceof XWPFSDT) {
                sb.append(((XWPFSDT) next).getContent().getText());
            } else if (this.concatenatePhoneticRuns || !(next instanceof XWPFRun)) {
                sb.append(next);
            } else {
                sb.append(((XWPFRun) next).text());
            }
            if ((next instanceof XWPFHyperlinkRun) && this.fetchHyperlinks && (hyperlink = ((XWPFHyperlinkRun) next).getHyperlink(this.document)) != null) {
                sb.append(" <").append(hyperlink.getURL()).append(">");
            }
        }
        String commentText = new XWPFCommentsDecorator(xWPFParagraph, (XWPFParagraphDecorator) null).getCommentText();
        if (commentText.length() > 0) {
            sb.append(commentText).append(10);
        }
        String footnoteText = xWPFParagraph.getFootnoteText();
        if (footnoteText != null && footnoteText.length() > 0) {
            sb.append(footnoteText).append(10);
        }
        if (sectPr != null) {
            extractFooters(sb, xWPFHeaderFooterPolicy);
        }
    }

    private void appendTableText(StringBuilder sb, XWPFTable xWPFTable) {
        for (XWPFTableRow tableICells : xWPFTable.getRows()) {
            List<ICell> tableICells2 = tableICells.getTableICells();
            for (int i = 0; i < tableICells2.size(); i++) {
                ICell iCell = tableICells2.get(i);
                if (iCell instanceof XWPFTableCell) {
                    sb.append(((XWPFTableCell) iCell).getTextRecursively());
                } else if (iCell instanceof XWPFSDTCell) {
                    sb.append(((XWPFSDTCell) iCell).getContent().getText());
                }
                if (i < tableICells2.size() - 1) {
                    sb.append("\t");
                }
            }
            sb.append(10);
        }
    }

    private void extractFooters(StringBuilder sb, XWPFHeaderFooterPolicy xWPFHeaderFooterPolicy) {
        if (xWPFHeaderFooterPolicy != null) {
            if (xWPFHeaderFooterPolicy.getFirstPageFooter() != null) {
                sb.append(xWPFHeaderFooterPolicy.getFirstPageFooter().getText());
            }
            if (xWPFHeaderFooterPolicy.getEvenPageFooter() != null) {
                sb.append(xWPFHeaderFooterPolicy.getEvenPageFooter().getText());
            }
            if (xWPFHeaderFooterPolicy.getDefaultFooter() != null) {
                sb.append(xWPFHeaderFooterPolicy.getDefaultFooter().getText());
            }
        }
    }

    private void extractHeaders(StringBuilder sb, XWPFHeaderFooterPolicy xWPFHeaderFooterPolicy) {
        if (xWPFHeaderFooterPolicy != null) {
            if (xWPFHeaderFooterPolicy.getFirstPageHeader() != null) {
                sb.append(xWPFHeaderFooterPolicy.getFirstPageHeader().getText());
            }
            if (xWPFHeaderFooterPolicy.getEvenPageHeader() != null) {
                sb.append(xWPFHeaderFooterPolicy.getEvenPageHeader().getText());
            }
            if (xWPFHeaderFooterPolicy.getDefaultHeader() != null) {
                sb.append(xWPFHeaderFooterPolicy.getDefaultHeader().getText());
            }
        }
    }

    public XWPFDocument getDocument() {
        return this.document;
    }

    public void setCloseFilesystem(boolean z) {
        this.doCloseFilesystem = z;
    }

    public boolean isCloseFilesystem() {
        return this.doCloseFilesystem;
    }

    public XWPFDocument getFilesystem() {
        return this.document;
    }
}
