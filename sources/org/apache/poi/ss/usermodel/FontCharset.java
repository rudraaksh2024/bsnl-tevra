package org.apache.poi.ss.usermodel;

import com.google.mlkit.common.MlKitException;
import org.apache.poi.util.Removal;

@Deprecated
@Removal(version = "6.0.0")
public enum FontCharset {
    ANSI(0),
    DEFAULT(1),
    SYMBOL(2),
    MAC(77),
    SHIFTJIS(128),
    HANGEUL(129),
    JOHAB(130),
    GB2312(134),
    CHINESEBIG5(136),
    GREEK(161),
    TURKISH(162),
    VIETNAMESE(163),
    HEBREW(177),
    ARABIC(178),
    BALTIC(186),
    RUSSIAN(MlKitException.CODE_SCANNER_TASK_IN_PROGRESS),
    THAI(222),
    EASTEUROPE(238),
    OEM(255);
    
    private static FontCharset[] _table;
    private int charset;

    static {
        _table = new FontCharset[256];
        for (FontCharset fontCharset : values()) {
            _table[fontCharset.getValue()] = fontCharset;
        }
    }

    private FontCharset(int i) {
        this.charset = i;
    }

    public int getValue() {
        return this.charset;
    }

    public static FontCharset valueOf(int i) {
        FontCharset[] fontCharsetArr = _table;
        if (i >= fontCharsetArr.length) {
            return null;
        }
        return fontCharsetArr[i];
    }
}
