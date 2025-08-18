package org.apache.poi.ss.formula.function;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.IOUtils;

final class FunctionMetadataReader {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 100000;
    private static final String[] DIGIT_ENDING_FUNCTION_NAMES;
    private static final Set<String> DIGIT_ENDING_FUNCTION_NAMES_SET;
    private static final String ELLIPSIS = "...";
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private static int MAX_RECORD_LENGTH = 100000;
    private static final String METADATA_FILE_NAME = "functionMetadata.txt";
    private static final String METADATA_FILE_NAME_CETAB = "functionMetadataCetab.txt";
    private static final Pattern SPACE_DELIM_PATTERN = Pattern.compile(" ");
    private static final Pattern TAB_DELIM_PATTERN = Pattern.compile("\t");

    FunctionMetadataReader() {
    }

    static {
        String[] strArr = {"LOG10", "ATAN2", "DAYS360", "SUMXMY2", "SUMX2MY2", "SUMX2PY2", "A1.R1C1"};
        DIGIT_ENDING_FUNCTION_NAMES = strArr;
        DIGIT_ENDING_FUNCTION_NAMES_SET = new HashSet(Arrays.asList(strArr));
    }

    public static void setMaxRecordLength(int i) {
        MAX_RECORD_LENGTH = i;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public static FunctionMetadataRegistry createRegistry() {
        FunctionDataBuilder functionDataBuilder = new FunctionDataBuilder(800);
        readResourceFile(functionDataBuilder, METADATA_FILE_NAME);
        return functionDataBuilder.build();
    }

    public static FunctionMetadataRegistry createRegistryCetab() {
        FunctionDataBuilder functionDataBuilder = new FunctionDataBuilder(800);
        readResourceFile(functionDataBuilder, METADATA_FILE_NAME_CETAB);
        return functionDataBuilder.build();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        if (r1 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0047, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0050, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void readResourceFile(org.apache.poi.ss.formula.function.FunctionDataBuilder r5, java.lang.String r6) {
        /*
            java.lang.String r0 = "resource '"
            java.lang.Class<org.apache.poi.ss.formula.function.FunctionMetadataReader> r1 = org.apache.poi.ss.formula.function.FunctionMetadataReader.class
            java.io.InputStream r1 = r1.getResourceAsStream(r6)     // Catch:{ IOException -> 0x0079 }
            if (r1 == 0) goto L_0x0053
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch:{ all -> 0x0051 }
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ all -> 0x0051 }
            java.nio.charset.Charset r2 = java.nio.charset.StandardCharsets.UTF_8     // Catch:{ all -> 0x0051 }
            r0.<init>(r1, r2)     // Catch:{ all -> 0x0051 }
            r6.<init>(r0)     // Catch:{ all -> 0x0051 }
        L_0x0016:
            java.lang.String r0 = r6.readLine()     // Catch:{ all -> 0x0045 }
            if (r0 != 0) goto L_0x0025
            r6.close()     // Catch:{ all -> 0x0051 }
            if (r1 == 0) goto L_0x0024
            r1.close()     // Catch:{ IOException -> 0x0079 }
        L_0x0024:
            return
        L_0x0025:
            int r2 = r0.length()     // Catch:{ all -> 0x0045 }
            r3 = 1
            if (r2 < r3) goto L_0x0016
            r2 = 0
            char r2 = r0.charAt(r2)     // Catch:{ all -> 0x0045 }
            r4 = 35
            if (r2 != r4) goto L_0x0036
            goto L_0x0016
        L_0x0036:
            java.lang.String r2 = r0.trim()     // Catch:{ all -> 0x0045 }
            int r2 = r2.length()     // Catch:{ all -> 0x0045 }
            if (r2 >= r3) goto L_0x0041
            goto L_0x0016
        L_0x0041:
            processLine(r5, r0)     // Catch:{ all -> 0x0045 }
            goto L_0x0016
        L_0x0045:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0047 }
        L_0x0047:
            r0 = move-exception
            r6.close()     // Catch:{ all -> 0x004c }
            goto L_0x0050
        L_0x004c:
            r6 = move-exception
            r5.addSuppressed(r6)     // Catch:{ all -> 0x0051 }
        L_0x0050:
            throw r0     // Catch:{ all -> 0x0051 }
        L_0x0051:
            r5 = move-exception
            goto L_0x006c
        L_0x0053:
            java.lang.RuntimeException r5 = new java.lang.RuntimeException     // Catch:{ all -> 0x0051 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0051 }
            r2.<init>(r0)     // Catch:{ all -> 0x0051 }
            java.lang.StringBuilder r6 = r2.append(r6)     // Catch:{ all -> 0x0051 }
            java.lang.String r0 = "' not found"
            java.lang.StringBuilder r6 = r6.append(r0)     // Catch:{ all -> 0x0051 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0051 }
            r5.<init>(r6)     // Catch:{ all -> 0x0051 }
            throw r5     // Catch:{ all -> 0x0051 }
        L_0x006c:
            throw r5     // Catch:{ all -> 0x006d }
        L_0x006d:
            r6 = move-exception
            if (r1 == 0) goto L_0x0078
            r1.close()     // Catch:{ all -> 0x0074 }
            goto L_0x0078
        L_0x0074:
            r0 = move-exception
            r5.addSuppressed(r0)     // Catch:{ IOException -> 0x0079 }
        L_0x0078:
            throw r6     // Catch:{ IOException -> 0x0079 }
        L_0x0079:
            r5 = move-exception
            java.lang.RuntimeException r6 = new java.lang.RuntimeException
            r6.<init>(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.function.FunctionMetadataReader.readResourceFile(org.apache.poi.ss.formula.function.FunctionDataBuilder, java.lang.String):void");
    }

    private static void processLine(FunctionDataBuilder functionDataBuilder, String str) {
        String[] split = TAB_DELIM_PATTERN.split(str, -2);
        if (split.length == 8) {
            int parseInt = parseInt(split[0]);
            String str2 = split[1];
            int parseInt2 = parseInt(split[2]);
            int parseInt3 = parseInt(split[3]);
            byte parseReturnTypeCode = parseReturnTypeCode(split[4]);
            byte[] parseOperandTypeCodes = parseOperandTypeCodes(split[5]);
            boolean z = split[7].length() > 0;
            validateFunctionName(str2);
            functionDataBuilder.add(parseInt, str2, parseInt2, parseInt3, parseReturnTypeCode, parseOperandTypeCodes, z);
            return;
        }
        throw new RuntimeException("Bad line format '" + str + "' - expected 8 data fields delimited by tab, but had " + split.length + ": " + Arrays.toString(split));
    }

    private static byte parseReturnTypeCode(String str) {
        if (str.length() == 0) {
            return 0;
        }
        return parseOperandTypeCode(str);
    }

    private static byte[] parseOperandTypeCodes(String str) {
        if (str.length() < 1) {
            return EMPTY_BYTE_ARRAY;
        }
        if (isDash(str)) {
            return EMPTY_BYTE_ARRAY;
        }
        String[] split = SPACE_DELIM_PATTERN.split(str);
        int length = split.length;
        if (ELLIPSIS.equals(split[length - 1])) {
            length--;
        }
        byte[] safelyAllocate = IOUtils.safelyAllocate((long) length, MAX_RECORD_LENGTH);
        for (int i = 0; i < length; i++) {
            safelyAllocate[i] = parseOperandTypeCode(split[i]);
        }
        return safelyAllocate;
    }

    private static boolean isDash(String str) {
        return str.length() == 1 && str.charAt(0) == '-';
    }

    private static byte parseOperandTypeCode(String str) {
        if (str.length() == 1) {
            char charAt = str.charAt(0);
            if (charAt == 'A') {
                return Ptg.CLASS_ARRAY;
            }
            if (charAt == 'R') {
                return 0;
            }
            if (charAt == 'V') {
                return 32;
            }
            throw new IllegalArgumentException("Unexpected operand type code '" + str + "' (" + str.charAt(0) + ")");
        }
        throw new RuntimeException("Bad operand type code format '" + str + "' expected single char");
    }

    private static void validateFunctionName(String str) {
        int length = str.length() - 1;
        if (Character.isDigit(str.charAt(length))) {
            while (length >= 0 && Character.isDigit(str.charAt(length))) {
                length--;
            }
            if (!DIGIT_ENDING_FUNCTION_NAMES_SET.contains(str)) {
                throw new RuntimeException("Invalid function name '" + str + "' (is footnote number incorrectly appended)");
            }
        }
    }

    private static int parseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            throw new RuntimeException("Value '" + str + "' could not be parsed as an integer");
        }
    }
}
