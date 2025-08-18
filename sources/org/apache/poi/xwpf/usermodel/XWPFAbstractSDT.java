package org.apache.poi.xwpf.usermodel;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr;

public abstract class XWPFAbstractSDT implements ISDTContents {
    private final IBody part;
    private final String tag;
    private final String title;

    public IBody getBody() {
        return null;
    }

    public abstract ISDTContent getContent();

    public XWPFAbstractSDT(CTSdtPr cTSdtPr, IBody iBody) {
        String str = "";
        this.title = (cTSdtPr == null || !cTSdtPr.isSetAlias()) ? str : cTSdtPr.getAlias().getVal();
        if (cTSdtPr != null && cTSdtPr.isSetTag()) {
            str = cTSdtPr.getTag().getVal();
        }
        this.tag = str;
        this.part = iBody;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTag() {
        return this.tag;
    }

    public POIXMLDocumentPart getPart() {
        return this.part.getPart();
    }

    public BodyType getPartType() {
        return BodyType.CONTENTCONTROL;
    }

    public BodyElementType getElementType() {
        return BodyElementType.CONTENTCONTROL;
    }

    public XWPFDocument getDocument() {
        return this.part.getXWPFDocument();
    }
}
