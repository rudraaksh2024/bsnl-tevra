package org.apache.poi.ss.format;

import java.util.Locale;
import java.util.regex.Matcher;

public class CellTextFormatter extends CellFormatter {
    static final CellFormatter SIMPLE_TEXT = new CellTextFormatter("@");
    private final String desc;
    private final int[] textPos;

    public CellTextFormatter(String str) {
        super(str);
        int[] iArr = new int[1];
        String stringBuffer = CellFormatPart.parseFormat(str, CellFormatType.TEXT, new CellTextFormatter$$ExternalSyntheticLambda0(iArr)).toString();
        this.desc = stringBuffer;
        this.textPos = new int[iArr[0]];
        int length = stringBuffer.length() - 1;
        int i = 0;
        while (true) {
            int[] iArr2 = this.textPos;
            if (i < iArr2.length) {
                iArr2[i] = this.desc.lastIndexOf(0, length);
                length = this.textPos[i] - 1;
                i++;
            } else {
                return;
            }
        }
    }

    static /* synthetic */ String lambda$new$0(int[] iArr, Matcher matcher, String str, CellFormatType cellFormatType, StringBuffer stringBuffer) {
        if (!str.equals("@")) {
            return null;
        }
        iArr[0] = iArr[0] + 1;
        return "\u0000";
    }

    public void formatValue(StringBuffer stringBuffer, Object obj) {
        int length = stringBuffer.length();
        String obj2 = obj.toString();
        if (obj instanceof Boolean) {
            obj2 = obj2.toUpperCase(Locale.ROOT);
        }
        stringBuffer.append(this.desc);
        for (int i : this.textPos) {
            int i2 = i + length;
            stringBuffer.replace(i2, i2 + 1, obj2);
        }
    }

    public void simpleValue(StringBuffer stringBuffer, Object obj) {
        SIMPLE_TEXT.formatValue(stringBuffer, obj);
    }
}
