package org.apache.xmlbeans.impl.regex;

import java.text.CharacterIterator;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.apache.poi.ss.formula.functions.Complex;

public final class REUtil {
    static final int CACHESIZE = 20;
    static final RegularExpression[] regexCache = new RegularExpression[20];

    static final int composeFromSurrogates(int i, int i2) {
        return ((((i - 55296) << 10) + 65536) + i2) - 56320;
    }

    static final int getOptionValue(int i) {
        if (i == 44) {
            return 1024;
        }
        if (i == 70) {
            return 256;
        }
        if (i == 72) {
            return 128;
        }
        if (i == 88) {
            return 512;
        }
        if (i == 105) {
            return 2;
        }
        if (i == 109) {
            return 8;
        }
        if (i == 115) {
            return 4;
        }
        if (i == 117) {
            return 32;
        }
        if (i != 119) {
            return i != 120 ? 0 : 16;
        }
        return 64;
    }

    static final boolean isHighSurrogate(int i) {
        return (i & 64512) == 55296;
    }

    static final boolean isLowSurrogate(int i) {
        return (i & 64512) == 56320;
    }

    private REUtil() {
    }

    static final String decomposeToSurrogates(int i) {
        int i2 = i - 65536;
        return new String(new char[]{(char) ((i2 >> 10) + 55296), (char) ((i2 & IEEEDouble.EXPONENT_BIAS) + 56320)});
    }

    static final String substring(CharacterIterator characterIterator, int i, int i2) {
        int i3 = i2 - i;
        char[] cArr = new char[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            cArr[i4] = characterIterator.setIndex(i4 + i);
        }
        return new String(cArr);
    }

    static final int parseOptions(String str) throws ParseException {
        int i = 0;
        if (str == null) {
            return 0;
        }
        int i2 = 0;
        while (i < str.length()) {
            int optionValue = getOptionValue(str.charAt(i));
            if (optionValue != 0) {
                i2 |= optionValue;
                i++;
            } else {
                throw new ParseException("Unknown Option: " + str.substring(i), -1);
            }
        }
        return i2;
    }

    static final String createOptionString(int i) {
        StringBuilder sb = new StringBuilder(9);
        if ((i & 256) != 0) {
            sb.append('F');
        }
        if ((i & 128) != 0) {
            sb.append('H');
        }
        if ((i & 512) != 0) {
            sb.append('X');
        }
        if ((i & 2) != 0) {
            sb.append('i');
        }
        if ((i & 8) != 0) {
            sb.append('m');
        }
        if ((i & 4) != 0) {
            sb.append('s');
        }
        if ((i & 32) != 0) {
            sb.append('u');
        }
        if ((i & 64) != 0) {
            sb.append('w');
        }
        if ((i & 16) != 0) {
            sb.append('x');
        }
        if ((i & 1024) != 0) {
            sb.append(',');
        }
        return sb.toString().intern();
    }

    static String stripExtendedComment(String str) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            char charAt = str.charAt(i);
            if (charAt != 9 && charAt != 10 && charAt != 12 && charAt != 13 && charAt != ' ') {
                if (charAt == '#') {
                    while (true) {
                        i = i2;
                        if (i >= length) {
                            break;
                        }
                        i2 = i + 1;
                        char charAt2 = str.charAt(i);
                        if (charAt2 != 13) {
                            if (charAt2 == 10) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                } else if (charAt != '\\' || i2 >= length) {
                    sb.append((char) charAt);
                } else {
                    char charAt3 = str.charAt(i2);
                    if (charAt3 == '#' || charAt3 == 9 || charAt3 == 10 || charAt3 == 12 || charAt3 == 13 || charAt3 == ' ') {
                        sb.append((char) charAt3);
                    } else {
                        sb.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                        sb.append((char) charAt3);
                    }
                    i2++;
                }
            }
            i = i2;
        }
        return sb.toString();
    }

    public static void main(String[] strArr) {
        String str = null;
        try {
            if (strArr.length == 0) {
                System.out.println("Error:Usage: java REUtil -i|-m|-s|-u|-w|-X regularExpression String");
                System.exit(0);
            }
            String str2 = "";
            String str3 = null;
            for (int i = 0; i < strArr.length; i++) {
                if (strArr[i].length() != 0) {
                    if (strArr[i].charAt(0) == '-') {
                        if (strArr[i].equals("-i")) {
                            str2 = str2 + Complex.DEFAULT_SUFFIX;
                        } else if (strArr[i].equals("-m")) {
                            str2 = str2 + "m";
                        } else if (strArr[i].equals("-s")) {
                            str2 = str2 + "s";
                        } else if (strArr[i].equals("-u")) {
                            str2 = str2 + "u";
                        } else if (strArr[i].equals("-w")) {
                            str2 = str2 + "w";
                        } else if (strArr[i].equals("-X")) {
                            str2 = str2 + "X";
                        } else {
                            System.err.println("Unknown option: " + strArr[i]);
                        }
                    }
                }
                if (str == null) {
                    str = strArr[i];
                } else if (str3 == null) {
                    str3 = strArr[i];
                } else {
                    System.err.println("Unnecessary: " + strArr[i]);
                }
            }
            RegularExpression regularExpression = new RegularExpression(str, str2);
            System.out.println("RegularExpression: " + regularExpression);
            Match match = new Match();
            regularExpression.matches(str3, match);
            for (int i2 = 0; i2 < match.getNumberOfGroups(); i2++) {
                if (i2 == 0) {
                    System.out.print("Matched range for the whole pattern: ");
                } else {
                    System.out.print("[" + i2 + "]: ");
                }
                if (match.getBeginning(i2) < 0) {
                    System.out.println("-1");
                } else {
                    System.out.print(match.getBeginning(i2) + ", " + match.getEnd(i2) + ", ");
                    System.out.println("\"" + match.getCapturedText(i2) + "\"");
                }
            }
        } catch (ParseException e) {
            if (0 == 0) {
                e.printStackTrace();
                return;
            }
            System.err.println("org.apache.xerces.utils.regex.ParseException: " + e.getMessage());
            System.err.println("        " + null);
            int location = e.getLocation();
            if (location >= 0) {
                System.err.print("        ");
                for (int i3 = 0; i3 < location; i3++) {
                    System.err.print(ProcessIdUtil.DEFAULT_PROCESSID);
                }
                System.err.println("^");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static RegularExpression createRegex(String str, String str2) throws ParseException {
        RegularExpression regularExpression;
        int parseOptions = parseOptions(str2);
        synchronized (regexCache) {
            int i = 0;
            while (true) {
                regularExpression = null;
                if (i >= 20) {
                    break;
                }
                try {
                    RegularExpression regularExpression2 = regexCache[i];
                    if (regularExpression2 == null) {
                        i = -1;
                        break;
                    } else if (regularExpression2.equals(str, parseOptions)) {
                        regularExpression = regularExpression2;
                        break;
                    } else {
                        i++;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (regularExpression == null) {
                regularExpression = new RegularExpression(str, str2);
                RegularExpression[] regularExpressionArr = regexCache;
                System.arraycopy(regularExpressionArr, 0, regularExpressionArr, 1, 19);
                regularExpressionArr[0] = regularExpression;
            } else if (i != 0) {
                RegularExpression[] regularExpressionArr2 = regexCache;
                System.arraycopy(regularExpressionArr2, 0, regularExpressionArr2, 1, i);
                regularExpressionArr2[0] = regularExpression;
            }
        }
        return regularExpression;
    }

    public static boolean matches(String str, String str2) throws ParseException {
        return createRegex(str, (String) null).matches(str2);
    }

    public static boolean matches(String str, String str2, String str3) throws ParseException {
        return createRegex(str, str2).matches(str3);
    }

    public static String quoteMeta(String str) {
        int length = str.length();
        StringBuilder sb = null;
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (".*+?{[()|\\^$".indexOf(charAt) >= 0) {
                if (sb == null) {
                    sb = new StringBuilder(((length - i) * 2) + i);
                    if (i > 0) {
                        sb.append(str.substring(0, i));
                    }
                }
                sb.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                sb.append((char) charAt);
            } else if (sb != null) {
                sb.append((char) charAt);
            }
        }
        return sb != null ? sb.toString() : str;
    }

    static void dumpString(String str) {
        for (int i = 0; i < str.length(); i++) {
            System.out.print(Integer.toHexString(str.charAt(i)));
            System.out.print(" ");
        }
        System.out.println();
    }
}
