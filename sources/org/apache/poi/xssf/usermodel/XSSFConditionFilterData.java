package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.ConditionFilterData;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfRule;

public class XSSFConditionFilterData implements ConditionFilterData {
    private final CTCfRule _cfRule;

    XSSFConditionFilterData(CTCfRule cTCfRule) {
        this._cfRule = cTCfRule;
    }

    public boolean getAboveAverage() {
        return this._cfRule.getAboveAverage();
    }

    public boolean getBottom() {
        return this._cfRule.getBottom();
    }

    public boolean getEqualAverage() {
        return this._cfRule.getEqualAverage();
    }

    public boolean getPercent() {
        return this._cfRule.getPercent();
    }

    public long getRank() {
        return this._cfRule.getRank();
    }

    public int getStdDev() {
        return this._cfRule.getStdDev();
    }
}
