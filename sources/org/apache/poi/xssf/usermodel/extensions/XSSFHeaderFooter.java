package org.apache.poi.xssf.usermodel.extensions;

import org.apache.poi.ss.usermodel.HeaderFooter;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.helpers.HeaderFooterHelper;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;

public abstract class XSSFHeaderFooter implements HeaderFooter {
    private final CTHeaderFooter headerFooter;
    private final HeaderFooterHelper helper = new HeaderFooterHelper();
    private boolean stripFields;

    public abstract String getText();

    /* access modifiers changed from: protected */
    public abstract void setText(String str);

    public XSSFHeaderFooter(CTHeaderFooter cTHeaderFooter) {
        this.headerFooter = cTHeaderFooter;
    }

    @Internal
    public CTHeaderFooter getHeaderFooter() {
        return this.headerFooter;
    }

    public String getValue() {
        String text = getText();
        return text == null ? "" : text;
    }

    public boolean areFieldsStripped() {
        return this.stripFields;
    }

    public void setAreFieldsStripped(boolean z) {
        this.stripFields = z;
    }

    public static String stripFields(String str) {
        return org.apache.poi.hssf.usermodel.HeaderFooter.stripFields(str);
    }

    public String getCenter() {
        String centerSection = this.helper.getCenterSection(getText());
        return this.stripFields ? stripFields(centerSection) : centerSection;
    }

    public String getLeft() {
        String leftSection = this.helper.getLeftSection(getText());
        return this.stripFields ? stripFields(leftSection) : leftSection;
    }

    public String getRight() {
        String rightSection = this.helper.getRightSection(getText());
        return this.stripFields ? stripFields(rightSection) : rightSection;
    }

    public void setCenter(String str) {
        setText(this.helper.setCenterSection(getText(), str));
    }

    public void setLeft(String str) {
        setText(this.helper.setLeftSection(getText(), str));
    }

    public void setRight(String str) {
        setText(this.helper.setRightSection(getText(), str));
    }
}
