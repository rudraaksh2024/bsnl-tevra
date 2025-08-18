package org.apache.poi.ss.format;

import androidx.exifinterface.media.ExifInterface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Chars;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.sl.extractor.SlideShowExtractor$$ExternalSyntheticLambda13;
import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.util.CodepointsUtil;
import org.apache.poi.util.LocaleUtil;
import org.apache.xmlbeans.impl.common.NameUtil;

public class CellFormatPart {
    public static final int COLOR_GROUP;
    public static final Pattern COLOR_PAT = Pattern.compile("\\[(black|blue|cyan|green|magenta|red|white|yellow|color [0-9]+)]", 6);
    public static final int CONDITION_OPERATOR_GROUP;
    public static final Pattern CONDITION_PAT = Pattern.compile("([<>=]=?|!=|<>)    # The operator\n  \\s*(-?([0-9]+(?:\\.[0-9]*)?)|(\\.[0-9]*))\\s*  # The constant to test against\n", 6);
    public static final int CONDITION_VALUE_GROUP;
    public static final Pattern CURRENCY_PAT = Pattern.compile("(\\[\\$.{0,3}(-[0-9a-f]{3,4})?])", 6);
    public static final Pattern FORMAT_PAT;
    private static final Logger LOG = LogManager.getLogger((Class<?>) CellFormatPart.class);
    static final Map<String, Color> NAMED_COLORS = new TreeMap(String.CASE_INSENSITIVE_ORDER);
    public static final int SPECIFICATION_GROUP;
    public static final Pattern SPECIFICATION_PAT = Pattern.compile("\\\\.                     # Quoted single character\n|\"([^\\\\\"]|\\\\.)*\"         # Quoted string of characters (handles escaped quotes like \\\") \n|(\\[\\$.{0,3}(-[0-9a-f]{3,4})?])                   # Currency symbol in a given locale\n|_.                             # Space as wide as a given character\n|\\*.                           # Repeating fill character\n|@                              # Text: cell text\n|([0?\\#][0?\\#,]*)             # Number: digit + other digits and commas\n|e[-+]                          # Number: Scientific: Exponent\n|m{1,5}                         # Date: month or minute spec\n|d{1,4}                         # Date: day/date spec\n|y{2,4}                         # Date: year spec\n|h{1,2}                         # Date: hour spec\n|s{1,2}                         # Date: second spec\n|am?/pm?                        # Date: am/pm spec\n|\\[h{1,2}]                     # Elapsed time: hour spec\n|\\[m{1,2}]                     # Elapsed time: minute spec\n|\\[s{1,2}]                     # Elapsed time: second spec\n|[^;]                           # A character\n", 6);
    private final Color color;
    private final CellFormatCondition condition;
    private final CellFormatter format;
    private final CellFormatType type;

    interface PartHandler {
        String handlePart(Matcher matcher, String str, CellFormatType cellFormatType, StringBuffer stringBuffer);
    }

    static {
        for (HSSFColor.HSSFColorPredefined hSSFColorPredefined : HSSFColor.HSSFColorPredefined.values()) {
            String name = hSSFColorPredefined.name();
            short[] triplet = hSSFColorPredefined.getTriplet();
            Color color2 = new Color(triplet[0], triplet[1], triplet[2]);
            Map<String, Color> map = NAMED_COLORS;
            map.put(name, color2);
            if (name.indexOf(95) > 0) {
                map.put(name.replace(NameUtil.USCORE, Chars.SPACE), color2);
            }
            if (name.indexOf("_PERCENT") > 0) {
                map.put(name.replace("_PERCENT", "%").replace(NameUtil.USCORE, Chars.SPACE), color2);
            }
        }
        Pattern compile = Pattern.compile("(?:\\[(black|blue|cyan|green|magenta|red|white|yellow|color [0-9]+)])?                 # Text color\n(?:\\[([<>=]=?|!=|<>)    # The operator\n  \\s*(-?([0-9]+(?:\\.[0-9]*)?)|(\\.[0-9]*))\\s*  # The constant to test against\n])?               # Condition\n(?:\\[\\$-[0-9a-fA-F]+])?                # Optional locale id, ignored currently\n((?:\\\\.                     # Quoted single character\n|\"([^\\\\\"]|\\\\.)*\"         # Quoted string of characters (handles escaped quotes like \\\") \n|(\\[\\$.{0,3}(-[0-9a-f]{3,4})?])                   # Currency symbol in a given locale\n|_.                             # Space as wide as a given character\n|\\*.                           # Repeating fill character\n|@                              # Text: cell text\n|([0?\\#][0?\\#,]*)             # Number: digit + other digits and commas\n|e[-+]                          # Number: Scientific: Exponent\n|m{1,5}                         # Date: month or minute spec\n|d{1,4}                         # Date: day/date spec\n|y{2,4}                         # Date: year spec\n|h{1,2}                         # Date: hour spec\n|s{1,2}                         # Date: second spec\n|am?/pm?                        # Date: am/pm spec\n|\\[h{1,2}]                     # Elapsed time: hour spec\n|\\[m{1,2}]                     # Elapsed time: minute spec\n|\\[s{1,2}]                     # Elapsed time: second spec\n|[^;]                           # A character\n)+)                        # Format spec\n", 6);
        FORMAT_PAT = compile;
        COLOR_GROUP = findGroup(compile, "[Blue]@", "Blue");
        CONDITION_OPERATOR_GROUP = findGroup(compile, "[>=1]@", ">=");
        CONDITION_VALUE_GROUP = findGroup(compile, "[>=1]@", "1");
        SPECIFICATION_GROUP = findGroup(compile, "[Blue][>1]\\a ?", "\\a ?");
    }

    public CellFormatPart(String str) {
        this(LocaleUtil.getUserLocale(), str);
    }

    public CellFormatPart(Locale locale, String str) {
        Matcher matcher = FORMAT_PAT.matcher(str);
        if (matcher.matches()) {
            this.color = getColor(matcher);
            this.condition = getCondition(matcher);
            this.type = getCellFormatType(matcher);
            this.format = getFormatter(locale, matcher);
            return;
        }
        throw new IllegalArgumentException("Unrecognized format: " + CellFormatter.quote(str));
    }

    public boolean applies(Object obj) {
        CellFormatCondition cellFormatCondition = this.condition;
        if (cellFormatCondition != null && (obj instanceof Number)) {
            return cellFormatCondition.pass(((Number) obj).doubleValue());
        }
        if (obj != null) {
            return true;
        }
        throw new NullPointerException("valueObject");
    }

    private static int findGroup(Pattern pattern, String str, String str2) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                String group = matcher.group(i);
                if (group != null && group.equals(str2)) {
                    return i;
                }
            }
            throw new IllegalArgumentException("\"" + str2 + "\" not found in \"" + pattern.pattern() + "\"");
        }
        throw new IllegalArgumentException("Pattern \"" + pattern.pattern() + "\" doesn't match \"" + str + "\"");
    }

    private static Color getColor(Matcher matcher) {
        String group = matcher.group(COLOR_GROUP);
        if (group == null || group.length() == 0) {
            return null;
        }
        Color color2 = NAMED_COLORS.get(group);
        if (color2 == null) {
            LOG.warn("Unknown color: " + CellFormatter.quote(group));
        }
        return color2;
    }

    private CellFormatCondition getCondition(Matcher matcher) {
        int i = CONDITION_OPERATOR_GROUP;
        String group = matcher.group(i);
        if (group == null || group.length() == 0) {
            return null;
        }
        return CellFormatCondition.getInstance(matcher.group(i), matcher.group(CONDITION_VALUE_GROUP));
    }

    private CellFormatType getCellFormatType(Matcher matcher) {
        return formatType(matcher.group(SPECIFICATION_GROUP));
    }

    private CellFormatter getFormatter(Locale locale, Matcher matcher) {
        String str;
        String group = matcher.group(SPECIFICATION_GROUP);
        Matcher matcher2 = CURRENCY_PAT.matcher(group);
        if (matcher2.find()) {
            String group2 = matcher2.group(1);
            if (group2.startsWith("[$-")) {
                str = "$";
            } else if (!group2.contains(ProcessIdUtil.DEFAULT_PROCESSID)) {
                str = group2.substring(2, group2.indexOf("]"));
            } else {
                str = group2.substring(2, group2.lastIndexOf(45));
            }
            group = group.replace(group2, str);
        }
        return this.type.formatter(locale, group);
    }

    private CellFormatType formatType(String str) {
        String trim = str.trim();
        if (trim.isEmpty() || trim.equalsIgnoreCase("General")) {
            return CellFormatType.GENERAL;
        }
        Matcher matcher = SPECIFICATION_PAT.matcher(trim);
        boolean z = false;
        boolean z2 = false;
        while (matcher.find()) {
            String group = matcher.group(0);
            Iterator<String> iteratorFor = CodepointsUtil.iteratorFor(group);
            if (iteratorFor.hasNext()) {
                String next = iteratorFor.next();
                String lowerCase = iteratorFor.hasNext() ? iteratorFor.next().toLowerCase(Locale.ROOT) : null;
                next.hashCode();
                char c = 65535;
                switch (next.hashCode()) {
                    case 35:
                        if (next.equals("#")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 48:
                        if (next.equals("0")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 63:
                        if (next.equals("?")) {
                            c = 2;
                            break;
                        }
                        break;
                    case 64:
                        if (next.equals("@")) {
                            c = 3;
                            break;
                        }
                        break;
                    case 68:
                        if (next.equals("D")) {
                            c = 4;
                            break;
                        }
                        break;
                    case 72:
                        if (next.equals("H")) {
                            c = 5;
                            break;
                        }
                        break;
                    case 77:
                        if (next.equals("M")) {
                            c = 6;
                            break;
                        }
                        break;
                    case 83:
                        if (next.equals(ExifInterface.LATITUDE_SOUTH)) {
                            c = 7;
                            break;
                        }
                        break;
                    case 89:
                        if (next.equals("Y")) {
                            c = 8;
                            break;
                        }
                        break;
                    case 91:
                        if (next.equals("[")) {
                            c = 9;
                            break;
                        }
                        break;
                    case 100:
                        if (next.equals("d")) {
                            c = 10;
                            break;
                        }
                        break;
                    case 104:
                        if (next.equals("h")) {
                            c = 11;
                            break;
                        }
                        break;
                    case 109:
                        if (next.equals("m")) {
                            c = 12;
                            break;
                        }
                        break;
                    case 115:
                        if (next.equals("s")) {
                            c = Chars.CR;
                            break;
                        }
                        break;
                    case 121:
                        if (next.equals("y")) {
                            c = 14;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                    case 2:
                        return CellFormatType.NUMBER;
                    case 1:
                        z2 = true;
                        break;
                    case 3:
                        return CellFormatType.TEXT;
                    case 4:
                    case 8:
                    case 10:
                    case 14:
                        return CellFormatType.DATE;
                    case 5:
                    case 6:
                    case 7:
                    case 11:
                    case 12:
                    case 13:
                        z = true;
                        break;
                    case 9:
                        if ("h".equals(lowerCase) || "m".equals(lowerCase) || "s".equals(lowerCase)) {
                            return CellFormatType.ELAPSED;
                        }
                        if ("$".equals(lowerCase)) {
                            return CellFormatType.NUMBER;
                        }
                        throw new IllegalArgumentException("Unsupported [] format block '" + group + "' in '" + trim + "' with c2: " + lowerCase);
                }
            }
        }
        if (z) {
            return CellFormatType.DATE;
        }
        if (z2) {
            return CellFormatType.NUMBER;
        }
        return CellFormatType.TEXT;
    }

    static String quoteSpecial(String str, CellFormatType cellFormatType) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> iteratorFor = CodepointsUtil.iteratorFor(str);
        while (iteratorFor.hasNext()) {
            String next = iteratorFor.next();
            if (!"'".equals(next) || !cellFormatType.isSpecial(Chars.QUOTE)) {
                boolean isSpecial = cellFormatType.isSpecial(next.charAt(0));
                if (isSpecial) {
                    sb.append(Chars.QUOTE);
                }
                sb.append(next);
                if (isSpecial) {
                    sb.append(Chars.QUOTE);
                }
            } else {
                sb.append(0);
            }
        }
        return sb.toString();
    }

    public CellFormatResult apply(Object obj) {
        String str;
        Color color2;
        boolean applies = applies(obj);
        if (applies) {
            str = this.format.format(obj);
            color2 = this.color;
        } else {
            str = this.format.simpleFormat(obj);
            color2 = null;
        }
        return new CellFormatResult(applies, str, color2);
    }

    public CellFormatResult apply(JLabel jLabel, Object obj) {
        CellFormatResult apply = apply(obj);
        jLabel.setText(apply.text);
        if (apply.textColor != null) {
            jLabel.setForeground(apply.textColor);
        }
        return apply;
    }

    /* access modifiers changed from: package-private */
    public CellFormatType getCellFormatType() {
        return this.type;
    }

    /* access modifiers changed from: package-private */
    public boolean hasCondition() {
        return this.condition != null;
    }

    public static StringBuffer parseFormat(String str, CellFormatType cellFormatType, PartHandler partHandler) {
        int i;
        Matcher matcher = SPECIFICATION_PAT.matcher(str);
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            i = 0;
            if (!matcher.find()) {
                break;
            }
            String group = group(matcher, 0);
            if (group.length() > 0) {
                String handlePart = partHandler.handlePart(matcher, group, cellFormatType, stringBuffer);
                if (handlePart == null) {
                    char charAt = group.charAt(0);
                    if (charAt == '\"') {
                        group = quoteSpecial(group.substring(1, group.length() - 1), cellFormatType);
                    } else if (charAt == '*') {
                        group = expandChar(group);
                    } else if (charAt == '\\') {
                        group = quoteSpecial(group.substring(1), cellFormatType);
                    } else if (charAt == '_') {
                        group = " ";
                    }
                } else {
                    group = handlePart;
                }
                matcher.appendReplacement(stringBuffer, Matcher.quoteReplacement(group));
            }
        }
        matcher.appendTail(stringBuffer);
        if (cellFormatType.isSpecial(Chars.QUOTE)) {
            int i2 = 0;
            while (true) {
                i2 = stringBuffer.indexOf("''", i2);
                if (i2 < 0) {
                    break;
                }
                stringBuffer.delete(i2, i2 + 2);
                if (partHandler instanceof CellDateFormatter.DatePartHandler) {
                    ((CellDateFormatter.DatePartHandler) partHandler).updatePositions(i2, -2);
                }
            }
            while (true) {
                i = stringBuffer.indexOf("\u0000", i);
                if (i < 0) {
                    break;
                }
                stringBuffer.replace(i, i + 1, "''");
                if (partHandler instanceof CellDateFormatter.DatePartHandler) {
                    ((CellDateFormatter.DatePartHandler) partHandler).updatePositions(i, 1);
                }
            }
        }
        return stringBuffer;
    }

    static String expandChar(String str) {
        ArrayList arrayList = new ArrayList();
        CodepointsUtil.iteratorFor(str).forEachRemaining(new SlideShowExtractor$$ExternalSyntheticLambda13(arrayList));
        if (arrayList.size() >= 2) {
            String str2 = (String) arrayList.get(1);
            return str2 + str2 + str2;
        }
        throw new IllegalArgumentException("Expected part string to have at least 2 chars");
    }

    public static String group(Matcher matcher, int i) {
        String group = matcher.group(i);
        return group == null ? "" : group;
    }

    public String toString() {
        return this.format.format;
    }
}
