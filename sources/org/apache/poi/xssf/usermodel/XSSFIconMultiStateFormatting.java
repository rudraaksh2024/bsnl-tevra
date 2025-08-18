package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.ConditionalFormattingThreshold;
import org.apache.poi.ss.usermodel.IconMultiStateFormatting;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfvo;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STIconSetType;

public class XSSFIconMultiStateFormatting implements IconMultiStateFormatting {
    CTIconSet _iconset;

    XSSFIconMultiStateFormatting(CTIconSet cTIconSet) {
        this._iconset = cTIconSet;
    }

    public IconMultiStateFormatting.IconSet getIconSet() {
        return IconMultiStateFormatting.IconSet.byName(this._iconset.getIconSet().toString());
    }

    public void setIconSet(IconMultiStateFormatting.IconSet iconSet) {
        this._iconset.setIconSet(STIconSetType.Enum.forString(iconSet.name));
    }

    public boolean isIconOnly() {
        if (this._iconset.isSetShowValue()) {
            return !this._iconset.getShowValue();
        }
        return false;
    }

    public void setIconOnly(boolean z) {
        this._iconset.setShowValue(!z);
    }

    public boolean isReversed() {
        if (this._iconset.isSetReverse()) {
            return this._iconset.getReverse();
        }
        return false;
    }

    public void setReversed(boolean z) {
        this._iconset.setReverse(z);
    }

    public XSSFConditionalFormattingThreshold[] getThresholds() {
        CTCfvo[] cfvoArray = this._iconset.getCfvoArray();
        XSSFConditionalFormattingThreshold[] xSSFConditionalFormattingThresholdArr = new XSSFConditionalFormattingThreshold[cfvoArray.length];
        for (int i = 0; i < cfvoArray.length; i++) {
            xSSFConditionalFormattingThresholdArr[i] = new XSSFConditionalFormattingThreshold(cfvoArray[i]);
        }
        return xSSFConditionalFormattingThresholdArr;
    }

    public void setThresholds(ConditionalFormattingThreshold[] conditionalFormattingThresholdArr) {
        CTCfvo[] cTCfvoArr = new CTCfvo[conditionalFormattingThresholdArr.length];
        for (int i = 0; i < conditionalFormattingThresholdArr.length; i++) {
            cTCfvoArr[i] = conditionalFormattingThresholdArr[i].getCTCfvo();
        }
        this._iconset.setCfvoArray(cTCfvoArr);
    }

    public XSSFConditionalFormattingThreshold createThreshold() {
        return new XSSFConditionalFormattingThreshold(this._iconset.addNewCfvo());
    }
}
