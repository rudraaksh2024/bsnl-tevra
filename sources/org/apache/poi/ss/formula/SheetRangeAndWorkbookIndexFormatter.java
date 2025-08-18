package org.apache.poi.ss.formula;

import org.apache.logging.log4j.util.Chars;
import org.apache.xmlbeans.impl.common.NameUtil;

public class SheetRangeAndWorkbookIndexFormatter {
    private SheetRangeAndWorkbookIndexFormatter() {
    }

    public static String format(StringBuilder sb, int i, String str, String str2) {
        if (anySheetNameNeedsEscaping(str, str2)) {
            return formatWithDelimiting(sb, i, str, str2);
        }
        return formatWithoutDelimiting(sb, i, str, str2);
    }

    private static String formatWithDelimiting(StringBuilder sb, int i, String str, String str2) {
        sb.append(Chars.QUOTE);
        if (i >= 0) {
            sb.append('[');
            sb.append(i);
            sb.append(']');
        }
        SheetNameFormatter.appendAndEscape(sb, str);
        if (str2 != null) {
            sb.append(NameUtil.COLON);
            SheetNameFormatter.appendAndEscape(sb, str2);
        }
        sb.append(Chars.QUOTE);
        return sb.toString();
    }

    private static String formatWithoutDelimiting(StringBuilder sb, int i, String str, String str2) {
        if (i >= 0) {
            sb.append('[');
            sb.append(i);
            sb.append(']');
        }
        sb.append(str);
        if (str2 != null) {
            sb.append(NameUtil.COLON);
            sb.append(str2);
        }
        return sb.toString();
    }

    private static boolean anySheetNameNeedsEscaping(String str, String str2) {
        return SheetNameFormatter.needsDelimiting(str) | SheetNameFormatter.needsDelimiting(str2);
    }
}
