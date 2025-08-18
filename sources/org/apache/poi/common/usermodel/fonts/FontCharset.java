package org.apache.poi.common.usermodel.fonts;

import com.google.mlkit.common.MlKitException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import org.apache.logging.log4j.LogManager;

public enum FontCharset {
    ANSI(0, "Cp1252"),
    DEFAULT(1, "Cp1252"),
    SYMBOL(2, ""),
    MAC(77, "MacRoman"),
    SHIFTJIS(128, "Shift_JIS"),
    HANGUL(129, "cp949"),
    JOHAB(130, "x-Johab"),
    GB2312(134, "GB2312"),
    CHINESEBIG5(136, "Big5"),
    GREEK(161, "Cp1253"),
    TURKISH(162, "Cp1254"),
    VIETNAMESE(163, "Cp1258"),
    HEBREW(177, "Cp1255"),
    ARABIC(178, "Cp1256"),
    BALTIC(186, "Cp1257"),
    RUSSIAN(MlKitException.CODE_SCANNER_TASK_IN_PROGRESS, "Cp1251"),
    THAI(222, "x-windows-874"),
    EASTEUROPE(238, "Cp1250"),
    OEM(255, "Cp1252");
    
    private static FontCharset[] _table;
    private Charset charset;
    private int nativeId;

    static {
        _table = new FontCharset[256];
        for (FontCharset fontCharset : values()) {
            _table[fontCharset.getNativeId()] = fontCharset;
        }
    }

    private FontCharset(int i, String str) {
        this.nativeId = i;
        if (str.length() > 0) {
            try {
                this.charset = Charset.forName(str);
                return;
            } catch (UnsupportedCharsetException unused) {
                LogManager.getLogger((Class<?>) FontCharset.class).atWarn().log("Unsupported charset: {}", (Object) str);
            }
        }
        this.charset = null;
    }

    public Charset getCharset() {
        return this.charset;
    }

    public int getNativeId() {
        return this.nativeId;
    }

    public static FontCharset valueOf(int i) {
        if (i >= 0) {
            FontCharset[] fontCharsetArr = _table;
            if (i < fontCharsetArr.length) {
                return fontCharsetArr[i];
            }
        }
        return null;
    }
}
