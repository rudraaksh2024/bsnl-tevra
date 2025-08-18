package org.apache.poi.sl.draw;

import org.apache.poi.common.usermodel.fonts.FontInfo;
import org.apache.poi.util.Internal;

@Internal
class DrawFontInfo implements FontInfo {
    private final String typeface;

    DrawFontInfo(String str) {
        this.typeface = str;
    }

    public String getTypeface() {
        return this.typeface;
    }
}
