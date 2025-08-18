package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.ConditionalFormattingThreshold;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfvo;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCfvoType;

public class XSSFConditionalFormattingThreshold implements ConditionalFormattingThreshold {
    private final CTCfvo cfvo;

    protected XSSFConditionalFormattingThreshold(CTCfvo cTCfvo) {
        this.cfvo = cTCfvo;
    }

    /* access modifiers changed from: protected */
    public CTCfvo getCTCfvo() {
        return this.cfvo;
    }

    public ConditionalFormattingThreshold.RangeType getRangeType() {
        return ConditionalFormattingThreshold.RangeType.byName(this.cfvo.getType().toString());
    }

    public void setRangeType(ConditionalFormattingThreshold.RangeType rangeType) {
        this.cfvo.setType(STCfvoType.Enum.forString(rangeType.name));
    }

    public String getFormula() {
        if (this.cfvo.getType() == STCfvoType.FORMULA) {
            return this.cfvo.getVal();
        }
        return null;
    }

    public void setFormula(String str) {
        this.cfvo.setVal(str);
    }

    public Double getValue() {
        if (this.cfvo.getType() == STCfvoType.FORMULA || this.cfvo.getType() == STCfvoType.MIN || this.cfvo.getType() == STCfvoType.MAX || !this.cfvo.isSetVal()) {
            return null;
        }
        return Double.valueOf(Double.parseDouble(this.cfvo.getVal()));
    }

    public void setValue(Double d) {
        if (d == null) {
            this.cfvo.unsetVal();
        } else {
            this.cfvo.setVal(d.toString());
        }
    }
}
