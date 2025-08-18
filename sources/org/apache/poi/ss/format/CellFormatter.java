package org.apache.poi.ss.format;

import java.util.Locale;
import org.apache.poi.util.LocaleUtil;

public abstract class CellFormatter {
    protected final String format;
    protected final Locale locale;

    public abstract void formatValue(StringBuffer stringBuffer, Object obj);

    public abstract void simpleValue(StringBuffer stringBuffer, Object obj);

    public CellFormatter(String str) {
        this(LocaleUtil.getUserLocale(), str);
    }

    public CellFormatter(Locale locale2, String str) {
        this.locale = locale2;
        this.format = str;
    }

    public String format(Object obj) {
        StringBuffer stringBuffer = new StringBuffer();
        formatValue(stringBuffer, obj);
        return stringBuffer.toString();
    }

    public String simpleFormat(Object obj) {
        StringBuffer stringBuffer = new StringBuffer();
        simpleValue(stringBuffer, obj);
        return stringBuffer.toString();
    }

    static String quote(String str) {
        return "\"" + str + '\"';
    }
}
