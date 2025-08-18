package org.apache.poi.xssf.binary;

import org.apache.logging.log4j.util.Chars;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.helpers.HeaderFooterHelper;

@Internal
class XSSFBHeaderFooter {
    private static final HeaderFooterHelper HEADER_FOOTER_HELPER = new HeaderFooterHelper();
    private final String headerFooterTypeLabel;
    private final boolean isHeader;
    private String rawString;

    XSSFBHeaderFooter(String str, boolean z) {
        this.headerFooterTypeLabel = str;
        this.isHeader = z;
    }

    /* access modifiers changed from: package-private */
    public String getHeaderFooterTypeLabel() {
        return this.headerFooterTypeLabel;
    }

    /* access modifiers changed from: package-private */
    public String getRawString() {
        return this.rawString;
    }

    /* access modifiers changed from: package-private */
    public String getString() {
        StringBuilder sb = new StringBuilder();
        HeaderFooterHelper headerFooterHelper = HEADER_FOOTER_HELPER;
        String leftSection = headerFooterHelper.getLeftSection(this.rawString);
        String centerSection = headerFooterHelper.getCenterSection(this.rawString);
        String rightSection = headerFooterHelper.getRightSection(this.rawString);
        if (leftSection != null && leftSection.length() > 0) {
            sb.append(leftSection);
        }
        if (centerSection != null && centerSection.length() > 0) {
            if (sb.length() > 0) {
                sb.append(Chars.SPACE);
            }
            sb.append(centerSection);
        }
        if (rightSection != null && rightSection.length() > 0) {
            if (sb.length() > 0) {
                sb.append(Chars.SPACE);
            }
            sb.append(rightSection);
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public void setRawString(String str) {
        this.rawString = str;
    }

    /* access modifiers changed from: package-private */
    public boolean isHeader() {
        return this.isHeader;
    }
}
