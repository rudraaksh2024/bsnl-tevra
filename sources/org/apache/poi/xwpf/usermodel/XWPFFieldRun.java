package org.apache.poi.xwpf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSimpleField;

public class XWPFFieldRun extends XWPFRun {
    private CTSimpleField field;

    public XWPFFieldRun(CTSimpleField cTSimpleField, CTR ctr, IRunBody iRunBody) {
        super(ctr, iRunBody);
        this.field = cTSimpleField;
    }

    @Internal
    public CTSimpleField getCTField() {
        return this.field;
    }

    public String getFieldInstruction() {
        return this.field.getInstr();
    }

    public void setFieldInstruction(String str) {
        this.field.setInstr(str);
    }
}
