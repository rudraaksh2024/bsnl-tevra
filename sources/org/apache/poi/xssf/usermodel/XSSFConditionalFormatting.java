package org.apache.poi.xssf.usermodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.ss.usermodel.ConditionalFormatting;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.util.CellRangeAddress;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTConditionalFormatting;

public class XSSFConditionalFormatting implements ConditionalFormatting {
    private final CTConditionalFormatting _cf;
    private final XSSFSheet _sh;

    XSSFConditionalFormatting(XSSFSheet xSSFSheet) {
        this._cf = CTConditionalFormatting.Factory.newInstance();
        this._sh = xSSFSheet;
    }

    XSSFConditionalFormatting(XSSFSheet xSSFSheet, CTConditionalFormatting cTConditionalFormatting) {
        this._cf = cTConditionalFormatting;
        this._sh = xSSFSheet;
    }

    /* access modifiers changed from: package-private */
    public CTConditionalFormatting getCTConditionalFormatting() {
        return this._cf;
    }

    public CellRangeAddress[] getFormattingRanges() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this._cf.getSqref().iterator();
        while (true) {
            if (!it.hasNext()) {
                return (CellRangeAddress[]) arrayList.toArray(new CellRangeAddress[0]);
            }
            for (String valueOf : it.next().toString().split(" ")) {
                arrayList.add(CellRangeAddress.valueOf(valueOf));
            }
        }
    }

    public void setFormattingRanges(CellRangeAddress[] cellRangeAddressArr) {
        if (cellRangeAddressArr != null) {
            StringBuilder sb = new StringBuilder();
            boolean z = true;
            for (CellRangeAddress cellRangeAddress : cellRangeAddressArr) {
                if (!z) {
                    sb.append(Chars.SPACE);
                } else {
                    z = false;
                }
                sb.append(cellRangeAddress.formatAsString());
            }
            this._cf.setSqref(Collections.singletonList(sb.toString()));
            return;
        }
        throw new IllegalArgumentException("cellRanges must not be null");
    }

    public void setRule(int i, ConditionalFormattingRule conditionalFormattingRule) {
        this._cf.getCfRuleArray(i).set(((XSSFConditionalFormattingRule) conditionalFormattingRule).getCTCfRule());
    }

    public void addRule(ConditionalFormattingRule conditionalFormattingRule) {
        this._cf.addNewCfRule().set(((XSSFConditionalFormattingRule) conditionalFormattingRule).getCTCfRule());
    }

    public XSSFConditionalFormattingRule getRule(int i) {
        return new XSSFConditionalFormattingRule(this._sh, this._cf.getCfRuleArray(i));
    }

    public int getNumberOfRules() {
        return this._cf.sizeOfCfRuleArray();
    }

    public String toString() {
        return this._cf.toString();
    }
}
