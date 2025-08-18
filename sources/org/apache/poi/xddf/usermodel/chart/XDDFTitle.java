package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.xddf.usermodel.text.TextContainer;
import org.apache.poi.xddf.usermodel.text.XDDFRunProperties;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTx;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

public class XDDFTitle {
    private final TextContainer parent;
    private final CTTitle title;

    public XDDFTitle(TextContainer textContainer, CTTitle cTTitle) {
        this.parent = textContainer;
        this.title = cTTitle;
    }

    public XDDFTextBody getBody() {
        if (!this.title.isSetTx()) {
            this.title.addNewTx();
        }
        CTTx tx = this.title.getTx();
        if (tx.isSetStrRef()) {
            tx.unsetStrRef();
        }
        if (!tx.isSetRich()) {
            tx.addNewRich();
        }
        return new XDDFTextBody(this.parent, tx.getRich());
    }

    public void setText(String str) {
        if (str != null) {
            if (!this.title.isSetLayout()) {
                this.title.addNewLayout();
            }
            getBody().setText(str);
        } else if (this.title.isSetTx()) {
            this.title.unsetTx();
        }
    }

    public void setOverlay(Boolean bool) {
        if (bool == null) {
            if (this.title.isSetOverlay()) {
                this.title.unsetOverlay();
            }
        } else if (this.title.isSetOverlay()) {
            this.title.getOverlay().setVal(bool.booleanValue());
        } else {
            this.title.addNewOverlay().setVal(bool.booleanValue());
        }
    }

    public XDDFRunProperties getOrAddTextProperties() {
        CTTextBody cTTextBody;
        if (this.title.isSetTxPr()) {
            cTTextBody = this.title.getTxPr();
        } else {
            cTTextBody = this.title.addNewTxPr();
        }
        return new XDDFRunProperties(getOrAddTextProperties(cTTextBody));
    }

    private CTTextCharacterProperties getOrAddTextProperties(CTTextBody cTTextBody) {
        CTTextParagraph cTTextParagraph;
        CTTextParagraphProperties cTTextParagraphProperties;
        if (cTTextBody.getBodyPr() == null) {
            cTTextBody.addNewBodyPr();
        }
        if (cTTextBody.sizeOfPArray() > 0) {
            cTTextParagraph = cTTextBody.getPArray(0);
        } else {
            cTTextParagraph = cTTextBody.addNewP();
        }
        if (cTTextParagraph.isSetPPr()) {
            cTTextParagraphProperties = cTTextParagraph.getPPr();
        } else {
            cTTextParagraphProperties = cTTextParagraph.addNewPPr();
        }
        if (cTTextParagraphProperties.isSetDefRPr()) {
            return cTTextParagraphProperties.getDefRPr();
        }
        return cTTextParagraphProperties.addNewDefRPr();
    }
}
