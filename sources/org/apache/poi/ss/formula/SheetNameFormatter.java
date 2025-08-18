package org.apache.poi.ss.formula;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.util.CellReference;

public final class SheetNameFormatter {
    private static final Pattern CELL_REF_PATTERN = Pattern.compile("([A-Za-z]+)([0-9]+)");
    private static final char DELIMITER = '\'';

    private SheetNameFormatter() {
    }

    public static String format(String str) {
        StringBuilder sb = new StringBuilder((str == null ? 0 : str.length()) + 2);
        appendFormat(sb, str);
        return sb.toString();
    }

    public static void appendFormat(Appendable appendable, String str) {
        try {
            if (needsDelimiting(str)) {
                appendable.append('\'');
                appendAndEscape(appendable, str);
                appendable.append('\'');
                return;
            }
            appendAndEscape(appendable, str);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0016 A[Catch:{ Exception -> 0x0044 }] */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0037 A[Catch:{ Exception -> 0x0044 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void appendFormat(java.lang.Appendable r4, java.lang.String r5, java.lang.String r6) {
        /*
            boolean r0 = needsDelimiting(r5)     // Catch:{ Exception -> 0x0044 }
            if (r0 != 0) goto L_0x000f
            boolean r0 = needsDelimiting(r6)     // Catch:{ Exception -> 0x0044 }
            if (r0 == 0) goto L_0x000d
            goto L_0x000f
        L_0x000d:
            r0 = 0
            goto L_0x0010
        L_0x000f:
            r0 = 1
        L_0x0010:
            r1 = 93
            r2 = 91
            if (r0 == 0) goto L_0x0037
            r0 = 39
            r4.append(r0)     // Catch:{ Exception -> 0x0044 }
            r4.append(r2)     // Catch:{ Exception -> 0x0044 }
            r3 = 40
            java.lang.String r5 = r5.replace(r2, r3)     // Catch:{ Exception -> 0x0044 }
            r2 = 41
            java.lang.String r5 = r5.replace(r1, r2)     // Catch:{ Exception -> 0x0044 }
            appendAndEscape(r4, r5)     // Catch:{ Exception -> 0x0044 }
            r4.append(r1)     // Catch:{ Exception -> 0x0044 }
            appendAndEscape(r4, r6)     // Catch:{ Exception -> 0x0044 }
            r4.append(r0)     // Catch:{ Exception -> 0x0044 }
            goto L_0x0043
        L_0x0037:
            r4.append(r2)     // Catch:{ Exception -> 0x0044 }
            appendOrREF(r4, r5)     // Catch:{ Exception -> 0x0044 }
            r4.append(r1)     // Catch:{ Exception -> 0x0044 }
            appendOrREF(r4, r6)     // Catch:{ Exception -> 0x0044 }
        L_0x0043:
            return
        L_0x0044:
            r4 = move-exception
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            r5.<init>(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.SheetNameFormatter.appendFormat(java.lang.Appendable, java.lang.String, java.lang.String):void");
    }

    private static void appendOrREF(Appendable appendable, String str) throws IOException {
        if (str == null) {
            appendable.append("#REF");
        } else {
            appendable.append(str);
        }
    }

    static void appendAndEscape(Appendable appendable, String str) {
        if (str == null) {
            try {
                appendable.append("#REF");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (charAt == '\'') {
                    appendable.append('\'');
                }
                appendable.append(charAt);
            }
        }
    }

    static boolean needsDelimiting(String str) {
        int length;
        if (str == null || (length = str.length()) < 1) {
            return false;
        }
        if (Character.isDigit(str.charAt(0))) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            if (isSpecialChar(str.charAt(i))) {
                return true;
            }
        }
        return (Character.isLetter(str.charAt(0)) && Character.isDigit(str.charAt(length - 1)) && nameLooksLikePlainCellReference(str)) || nameLooksLikeBooleanLiteral(str);
    }

    private static boolean nameLooksLikeBooleanLiteral(String str) {
        char charAt = str.charAt(0);
        if (charAt != 'F') {
            if (charAt != 'T') {
                if (charAt != 'f') {
                    if (charAt != 't') {
                        return false;
                    }
                }
            }
            return "TRUE".equalsIgnoreCase(str);
        }
        return "FALSE".equalsIgnoreCase(str);
    }

    static boolean isSpecialChar(char c) {
        if (Character.isLetterOrDigit(c)) {
            return false;
        }
        if (c != 9 && c != 10 && c != 13) {
            return (c == '.' || c == '_') ? false : true;
        }
        throw new RuntimeException("Illegal character (0x" + Integer.toHexString(c) + ") found in sheet name");
    }

    static boolean cellReferenceIsWithinRange(String str, String str2) {
        return CellReference.cellReferenceIsWithinRange(str, str2, SpreadsheetVersion.EXCEL97);
    }

    static boolean nameLooksLikePlainCellReference(String str) {
        Matcher matcher = CELL_REF_PATTERN.matcher(str);
        if (!matcher.matches()) {
            return false;
        }
        return cellReferenceIsWithinRange(matcher.group(1), matcher.group(2));
    }
}
