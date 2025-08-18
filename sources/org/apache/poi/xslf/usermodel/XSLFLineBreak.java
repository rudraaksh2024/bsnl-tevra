package org.apache.poi.xslf.usermodel;

import org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak;

class XSLFLineBreak extends XSLFTextRun {
    protected XSLFLineBreak(CTTextLineBreak cTTextLineBreak, XSLFTextParagraph xSLFTextParagraph) {
        super(cTTextLineBreak, xSLFTextParagraph);
    }

    public void setText(String str) {
        throw new IllegalStateException("You cannot change text of a line break, it is always '\\n'");
    }
}
