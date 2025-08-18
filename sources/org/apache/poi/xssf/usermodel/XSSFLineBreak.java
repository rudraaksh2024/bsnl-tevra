package org.apache.poi.xssf.usermodel;

import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;

class XSSFLineBreak extends XSSFTextRun {
    private final CTTextCharacterProperties _brProps;

    XSSFLineBreak(CTRegularTextRun cTRegularTextRun, XSSFTextParagraph xSSFTextParagraph, CTTextCharacterProperties cTTextCharacterProperties) {
        super(cTRegularTextRun, xSSFTextParagraph);
        this._brProps = cTTextCharacterProperties;
    }

    /* access modifiers changed from: protected */
    public CTTextCharacterProperties getRPr() {
        return this._brProps;
    }

    public void setText(String str) {
        throw new IllegalStateException("You cannot change text of a line break, it is always '\\n'");
    }
}
