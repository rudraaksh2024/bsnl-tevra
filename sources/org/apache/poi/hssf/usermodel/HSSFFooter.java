package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.record.FooterRecord;
import org.apache.poi.hssf.record.aggregates.PageSettingsBlock;
import org.apache.poi.ss.usermodel.Footer;

public final class HSSFFooter extends HeaderFooter implements Footer {
    private final PageSettingsBlock _psb;

    protected HSSFFooter(PageSettingsBlock pageSettingsBlock) {
        this._psb = pageSettingsBlock;
    }

    /* access modifiers changed from: protected */
    public String getRawText() {
        FooterRecord footer = this._psb.getFooter();
        if (footer == null) {
            return "";
        }
        return footer.getText();
    }

    /* access modifiers changed from: protected */
    public void setHeaderFooterText(String str) {
        FooterRecord footer = this._psb.getFooter();
        if (footer == null) {
            this._psb.setFooter(new FooterRecord(str));
            return;
        }
        footer.setText(str);
    }
}
