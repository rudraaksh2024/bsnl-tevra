package org.apache.poi.xssf.binary;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.util.Internal;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

@Internal
class XSSFBRichTextString extends XSSFRichTextString {
    private final String string;

    @NotImplemented
    public void applyFont(int i, int i2, Font font) {
    }

    @NotImplemented
    public void applyFont(int i, int i2, short s) {
    }

    @NotImplemented
    public void applyFont(Font font) {
    }

    @NotImplemented
    public void applyFont(short s) {
    }

    @NotImplemented
    public void clearFormatting() {
    }

    @NotImplemented
    public int getIndexOfFormattingRun(int i) {
        return 0;
    }

    @NotImplemented
    public int numFormattingRuns() {
        return 0;
    }

    XSSFBRichTextString(String str) {
        this.string = str;
    }

    public String getString() {
        return this.string;
    }

    public int length() {
        return this.string.length();
    }
}
