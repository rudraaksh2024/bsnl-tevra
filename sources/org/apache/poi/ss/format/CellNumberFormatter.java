package org.apache.poi.ss.format;

import androidx.exifinterface.media.ExifInterface;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.util.LocaleUtil;

public class CellNumberFormatter extends CellFormatter {
    private static final Logger LOG = LogManager.getLogger((Class<?>) CellNumberFormatter.class);
    private final CellFormatter SIMPLE_NUMBER;
    private final Special afterFractional;
    private final Special afterInteger;
    private final DecimalFormat decimalFmt;
    private final Special decimalPoint;
    private final String denominatorFmt;
    private final List<Special> denominatorSpecials;
    private final String desc;
    private final Special exponent;
    private final List<Special> exponentDigitSpecials;
    private final List<Special> exponentSpecials;
    private final List<Special> fractionalSpecials;
    private final boolean improperFraction;
    private final List<Special> integerSpecials;
    private final int maxDenominator;
    private final Special numerator;
    private final String numeratorFmt;
    private final List<Special> numeratorSpecials;
    private final String printfFmt;
    private final double scale;
    private final boolean showGroupingSeparator;
    private final Special slash;
    private final List<Special> specials;

    private static class GeneralNumberFormatter extends CellFormatter {
        private GeneralNumberFormatter(Locale locale) {
            super(locale, "General");
        }

        public void formatValue(StringBuffer stringBuffer, Object obj) {
            CellFormatter cellFormatter;
            if (obj != null) {
                if (obj instanceof Number) {
                    cellFormatter = ((Number) obj).doubleValue() % 1.0d == 0.0d ? new CellNumberFormatter(this.locale, "#") : new CellNumberFormatter(this.locale, "#.#");
                } else {
                    cellFormatter = CellTextFormatter.SIMPLE_TEXT;
                }
                cellFormatter.formatValue(stringBuffer, obj);
            }
        }

        public void simpleValue(StringBuffer stringBuffer, Object obj) {
            formatValue(stringBuffer, obj);
        }
    }

    static class Special {
        final char ch;
        int pos;

        Special(char c, int i) {
            this.ch = c;
            this.pos = i;
        }

        public String toString() {
            return "'" + this.ch + "' @ " + this.pos;
        }
    }

    public CellNumberFormatter(String str) {
        this(LocaleUtil.getUserLocale(), str);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CellNumberFormatter(Locale locale, String str) {
        super(locale, str);
        int i;
        ArrayList arrayList = new ArrayList();
        this.specials = arrayList;
        ArrayList<Special> arrayList2 = new ArrayList<>();
        this.integerSpecials = arrayList2;
        ArrayList arrayList3 = new ArrayList();
        this.fractionalSpecials = arrayList3;
        ArrayList arrayList4 = new ArrayList();
        this.numeratorSpecials = arrayList4;
        ArrayList arrayList5 = new ArrayList();
        this.denominatorSpecials = arrayList5;
        ArrayList arrayList6 = new ArrayList();
        this.exponentSpecials = arrayList6;
        ArrayList arrayList7 = new ArrayList();
        this.exponentDigitSpecials = arrayList7;
        this.SIMPLE_NUMBER = new GeneralNumberFormatter(this.locale);
        CellNumberPartHandler cellNumberPartHandler = new CellNumberPartHandler();
        StringBuffer parseFormat = CellFormatPart.parseFormat(str, CellFormatType.NUMBER, cellNumberPartHandler);
        Special exponent2 = cellNumberPartHandler.getExponent();
        this.exponent = exponent2;
        arrayList.addAll(cellNumberPartHandler.getSpecials());
        this.improperFraction = cellNumberPartHandler.isImproperFraction();
        if ((cellNumberPartHandler.getDecimalPoint() == null && cellNumberPartHandler.getExponent() == null) || cellNumberPartHandler.getSlash() == null) {
            this.slash = cellNumberPartHandler.getSlash();
            this.numerator = cellNumberPartHandler.getNumerator();
        } else {
            this.slash = null;
            this.numerator = null;
        }
        int interpretPrecision = interpretPrecision(cellNumberPartHandler.getDecimalPoint(), arrayList);
        if (cellNumberPartHandler.getDecimalPoint() != null) {
            int i2 = interpretPrecision + 1;
            if (interpretPrecision == 0) {
                arrayList.remove(cellNumberPartHandler.getDecimalPoint());
                this.decimalPoint = null;
            } else {
                this.decimalPoint = cellNumberPartHandler.getDecimalPoint();
            }
            i = i2;
        } else {
            this.decimalPoint = null;
            i = 0;
        }
        Special special = this.decimalPoint;
        if (special != null) {
            this.afterInteger = special;
        } else if (exponent2 != null) {
            this.afterInteger = exponent2;
        } else {
            Special special2 = this.numerator;
            if (special2 != null) {
                this.afterInteger = special2;
            } else {
                this.afterInteger = null;
            }
        }
        if (exponent2 != null) {
            this.afterFractional = exponent2;
        } else {
            Special special3 = this.numerator;
            if (special3 != null) {
                this.afterFractional = special3;
            } else {
                this.afterFractional = null;
            }
        }
        double[] dArr = {cellNumberPartHandler.getScale()};
        int i3 = interpretPrecision;
        Special special4 = exponent2;
        this.showGroupingSeparator = interpretIntegerCommas(parseFormat, arrayList, this.decimalPoint, integerEnd(), fractionalEnd(), dArr);
        if (special4 == null) {
            this.scale = dArr[0];
        } else {
            this.scale = 1.0d;
        }
        int i4 = i3;
        if (i4 != 0) {
            arrayList3.addAll(arrayList.subList(arrayList.indexOf(this.decimalPoint) + 1, fractionalEnd()));
        }
        if (special4 != null) {
            int indexOf = arrayList.indexOf(special4);
            arrayList6.addAll(specialsFor(indexOf, 2));
            arrayList7.addAll(specialsFor(indexOf + 2));
        }
        if (this.slash != null) {
            Special special5 = this.numerator;
            if (special5 != null) {
                arrayList4.addAll(specialsFor(arrayList.indexOf(special5)));
            }
            arrayList5.addAll(specialsFor(arrayList.indexOf(this.slash) + 1));
            if (arrayList5.isEmpty()) {
                arrayList4.clear();
                this.maxDenominator = 1;
                this.numeratorFmt = null;
                this.denominatorFmt = null;
            } else {
                this.maxDenominator = maxValue(arrayList5);
                this.numeratorFmt = singleNumberFormat(arrayList4);
                this.denominatorFmt = singleNumberFormat(arrayList5);
            }
        } else {
            this.maxDenominator = 1;
            this.numeratorFmt = null;
            this.denominatorFmt = null;
        }
        arrayList2.addAll(arrayList.subList(0, integerEnd()));
        if (special4 == null) {
            int calculateIntegerPartWidth = calculateIntegerPartWidth() + i;
            if (calculateIntegerPartWidth == 0) {
                this.printfFmt = "";
            } else {
                this.printfFmt = "%0" + calculateIntegerPartWidth + '.' + i4 + "f";
            }
            this.decimalFmt = null;
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            boolean z = true;
            if (arrayList2.size() == 1) {
                stringBuffer.append("0");
                z = false;
            } else {
                for (Special isDigitFmt : arrayList2) {
                    if (isDigitFmt(isDigitFmt)) {
                        stringBuffer.append(z ? '#' : '0');
                        z = false;
                    }
                }
            }
            if (!this.fractionalSpecials.isEmpty()) {
                stringBuffer.append('.');
                for (Special isDigitFmt2 : this.fractionalSpecials) {
                    if (isDigitFmt(isDigitFmt2)) {
                        if (!z) {
                            stringBuffer.append('0');
                        }
                        z = false;
                    }
                }
            }
            stringBuffer.append('E');
            List<Special> list = this.exponentSpecials;
            placeZeros(stringBuffer, list.subList(2, list.size()));
            this.decimalFmt = new DecimalFormat(stringBuffer.toString(), getDecimalFormatSymbols());
            this.printfFmt = null;
        }
        this.desc = parseFormat.toString();
    }

    private DecimalFormatSymbols getDecimalFormatSymbols() {
        return DecimalFormatSymbols.getInstance(this.locale);
    }

    private static void placeZeros(StringBuffer stringBuffer, List<Special> list) {
        for (Special isDigitFmt : list) {
            if (isDigitFmt(isDigitFmt)) {
                stringBuffer.append('0');
            }
        }
    }

    private static CellNumberStringMod insertMod(Special special, CharSequence charSequence, int i) {
        return new CellNumberStringMod(special, charSequence, i);
    }

    private static CellNumberStringMod deleteMod(Special special, boolean z, Special special2, boolean z2) {
        return new CellNumberStringMod(special, z, special2, z2);
    }

    private static CellNumberStringMod replaceMod(Special special, boolean z, Special special2, boolean z2, char c) {
        return new CellNumberStringMod(special, z, special2, z2, c);
    }

    private static String singleNumberFormat(List<Special> list) {
        return "%0" + list.size() + "d";
    }

    private static int maxValue(List<Special> list) {
        return Math.toIntExact(Math.round(Math.pow(10.0d, (double) list.size()) - 1.0d));
    }

    private List<Special> specialsFor(int i, int i2) {
        if (i >= this.specials.size()) {
            return Collections.emptyList();
        }
        int i3 = i2 + i;
        ListIterator<Special> listIterator = this.specials.listIterator(i3);
        Special next = listIterator.next();
        while (listIterator.hasNext()) {
            Special next2 = listIterator.next();
            if (!isDigitFmt(next2) || next2.pos - next.pos > 1) {
                break;
            }
            i3++;
            next = next2;
        }
        return this.specials.subList(i, i3 + 1);
    }

    private List<Special> specialsFor(int i) {
        return specialsFor(i, 0);
    }

    private static boolean isDigitFmt(Special special) {
        return special.ch == '0' || special.ch == '?' || special.ch == '#';
    }

    private int calculateIntegerPartWidth() {
        Special next;
        Iterator<Special> it = this.specials.iterator();
        int i = 0;
        while (it.hasNext() && (next = it.next()) != this.afterInteger) {
            if (isDigitFmt(next)) {
                i++;
            }
        }
        return i;
    }

    private static int interpretPrecision(Special special, List<Special> list) {
        int indexOf = list.indexOf(special);
        int i = 0;
        if (indexOf != -1) {
            ListIterator<Special> listIterator = list.listIterator(indexOf + 1);
            while (listIterator.hasNext() && isDigitFmt(listIterator.next())) {
                i++;
            }
        }
        return i;
    }

    private static boolean interpretIntegerCommas(StringBuffer stringBuffer, List<Special> list, Special special, int i, int i2, double[] dArr) {
        ListIterator<Special> listIterator = list.listIterator(i);
        int i3 = 0;
        boolean z = true;
        boolean z2 = false;
        while (listIterator.hasPrevious()) {
            if (listIterator.previous().ch != ',') {
                z = false;
            } else if (z) {
                dArr[0] = dArr[0] / 1000.0d;
            } else {
                z2 = true;
            }
        }
        if (special != null) {
            ListIterator<Special> listIterator2 = list.listIterator(i2);
            while (listIterator2.hasPrevious() && listIterator2.previous().ch == ',') {
                dArr[0] = dArr[0] / 1000.0d;
            }
        }
        ListIterator<Special> listIterator3 = list.listIterator();
        while (listIterator3.hasNext()) {
            Special next = listIterator3.next();
            next.pos -= i3;
            if (next.ch == ',') {
                i3++;
                listIterator3.remove();
                stringBuffer.deleteCharAt(next.pos);
            }
        }
        return z2;
    }

    private int integerEnd() {
        Special special = this.afterInteger;
        List<Special> list = this.specials;
        return special == null ? list.size() : list.indexOf(special);
    }

    private int fractionalEnd() {
        Special special = this.afterFractional;
        List<Special> list = this.specials;
        return special == null ? list.size() : list.indexOf(special);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0112, code lost:
        if (r3.isStartInclusive() == false) goto L_0x0114;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0114, code lost:
        r4 = r4 + 1;
        r13 = r13 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x011c, code lost:
        if (r5.get(r4) == false) goto L_0x011f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x011f, code lost:
        r15 = r3.getEnd().pos;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0129, code lost:
        if (r3.isEndInclusive() == false) goto L_0x012d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x012b, code lost:
        r15 = r15 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x012d, code lost:
        r11 = r15 + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x012f, code lost:
        if (r13 >= r11) goto L_0x0159;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0131, code lost:
        r17 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x013d, code lost:
        if ("".equals(r3.getToAdd()) == false) goto L_0x0144;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x013f, code lost:
        r14.delete(r13, r11);
        r6 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0144, code lost:
        r6 = false;
        r3 = r3.getToAdd().charAt(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x014d, code lost:
        if (r13 >= r11) goto L_0x0155;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x014f, code lost:
        r14.setCharAt(r13, r3);
        r13 = r13 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0155, code lost:
        r5.set(r4, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0159, code lost:
        r17 = r6;
        r6 = r10;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01c5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void formatValue(java.lang.StringBuffer r18, java.lang.Object r19) {
        /*
            r17 = this;
            r9 = r17
            r0 = r18
            r1 = r19
            java.lang.Number r1 = (java.lang.Number) r1
            double r1 = r1.doubleValue()
            java.math.BigDecimal r1 = java.math.BigDecimal.valueOf(r1)
            double r2 = r9.scale
            java.math.BigDecimal r2 = java.math.BigDecimal.valueOf(r2)
            java.math.BigDecimal r1 = r1.multiply(r2)
            double r1 = r1.doubleValue()
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            r10 = 0
            r11 = 1
            if (r5 >= 0) goto L_0x0028
            r12 = r11
            goto L_0x0029
        L_0x0028:
            r12 = r10
        L_0x0029:
            if (r12 == 0) goto L_0x002c
            double r1 = -r1
        L_0x002c:
            org.apache.poi.ss.format.CellNumberFormatter$Special r5 = r9.slash
            if (r5 == 0) goto L_0x003d
            boolean r5 = r9.improperFraction
            if (r5 == 0) goto L_0x0037
            r5 = r1
            r2 = r3
            goto L_0x003f
        L_0x0037:
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r3 = r1 % r3
            long r1 = (long) r1
            double r1 = (double) r1
        L_0x003d:
            r5 = r3
            r2 = r1
        L_0x003f:
            java.util.TreeSet r13 = new java.util.TreeSet
            r13.<init>()
            java.lang.StringBuffer r14 = new java.lang.StringBuffer
            java.lang.String r1 = r9.desc
            java.lang.String r1 = r9.localiseFormat(r1)
            r14.<init>(r1)
            org.apache.poi.ss.format.CellNumberFormatter$Special r1 = r9.exponent
            if (r1 == 0) goto L_0x0057
            r9.writeScientific(r2, r14, r13)
            goto L_0x009e
        L_0x0057:
            boolean r1 = r9.improperFraction
            if (r1 == 0) goto L_0x0064
            r4 = 0
            r1 = r17
            r7 = r14
            r8 = r13
            r1.writeFraction(r2, r4, r5, r7, r8)
            goto L_0x009e
        L_0x0064:
            java.lang.StringBuffer r4 = new java.lang.StringBuffer
            r4.<init>()
            java.util.Formatter r1 = new java.util.Formatter     // Catch:{ IllegalFormatException -> 0x01dd }
            java.util.Locale r7 = r9.locale     // Catch:{ IllegalFormatException -> 0x01dd }
            r1.<init>(r4, r7)     // Catch:{ IllegalFormatException -> 0x01dd }
            java.util.Locale r7 = r9.locale     // Catch:{ all -> 0x01ce }
            java.lang.String r8 = r9.printfFmt     // Catch:{ all -> 0x01ce }
            java.lang.Object[] r15 = new java.lang.Object[r11]     // Catch:{ all -> 0x01ce }
            java.lang.Double r16 = java.lang.Double.valueOf(r2)     // Catch:{ all -> 0x01ce }
            r15[r10] = r16     // Catch:{ all -> 0x01ce }
            r1.format(r7, r8, r15)     // Catch:{ all -> 0x01ce }
            r1.close()     // Catch:{ IllegalFormatException -> 0x01dd }
            org.apache.poi.ss.format.CellNumberFormatter$Special r1 = r9.numerator
            if (r1 != 0) goto L_0x0097
            r9.writeFractional(r4, r14)
            java.util.List<org.apache.poi.ss.format.CellNumberFormatter$Special> r5 = r9.integerSpecials
            boolean r6 = r9.showGroupingSeparator
            r1 = r17
            r2 = r4
            r3 = r14
            r4 = r5
            r5 = r13
            r1.writeInteger(r2, r3, r4, r5, r6)
            goto L_0x009e
        L_0x0097:
            r1 = r17
            r7 = r14
            r8 = r13
            r1.writeFraction(r2, r4, r5, r7, r8)
        L_0x009e:
            java.text.DecimalFormatSymbols r1 = r17.getDecimalFormatSymbols()
            char r1 = r1.getGroupingSeparator()
            java.lang.String r1 = java.lang.Character.toString(r1)
            java.util.Iterator r2 = r13.iterator()
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x00bb
            java.lang.Object r3 = r2.next()
            org.apache.poi.ss.format.CellNumberStringMod r3 = (org.apache.poi.ss.format.CellNumberStringMod) r3
            goto L_0x00bc
        L_0x00bb:
            r3 = 0
        L_0x00bc:
            com.zaxxer.sparsebits.SparseBitSet r5 = new com.zaxxer.sparsebits.SparseBitSet
            r5.<init>()
            java.util.List<org.apache.poi.ss.format.CellNumberFormatter$Special> r6 = r9.specials
            java.util.Iterator r6 = r6.iterator()
            r7 = r10
        L_0x00c8:
            boolean r8 = r6.hasNext()
            if (r8 == 0) goto L_0x01c3
            java.lang.Object r8 = r6.next()
            org.apache.poi.ss.format.CellNumberFormatter$Special r8 = (org.apache.poi.ss.format.CellNumberFormatter.Special) r8
            int r9 = r8.pos
            int r9 = r9 + r7
            int r13 = r8.pos
            boolean r13 = r5.get(r13)
            if (r13 != 0) goto L_0x00f1
            char r13 = r14.charAt(r9)
            r15 = 35
            if (r13 != r15) goto L_0x00f1
            r14.deleteCharAt(r9)
            int r7 = r7 + -1
            int r9 = r8.pos
            r5.set(r9)
        L_0x00f1:
            if (r3 == 0) goto L_0x01ba
            org.apache.poi.ss.format.CellNumberFormatter$Special r9 = r3.getSpecial()
            if (r8 != r9) goto L_0x01ba
            int r9 = r14.length()
            int r13 = r8.pos
            int r13 = r13 + r7
            int r15 = r3.getOp()
            if (r15 == r11) goto L_0x0196
            r4 = 2
            if (r15 == r4) goto L_0x0176
            r4 = 3
            if (r15 != r4) goto L_0x015d
            int r4 = r8.pos
            boolean r15 = r3.isStartInclusive()
            if (r15 != 0) goto L_0x0118
        L_0x0114:
            int r4 = r4 + 1
            int r13 = r13 + 1
        L_0x0118:
            boolean r15 = r5.get(r4)
            if (r15 == 0) goto L_0x011f
            goto L_0x0114
        L_0x011f:
            org.apache.poi.ss.format.CellNumberFormatter$Special r15 = r3.getEnd()
            int r15 = r15.pos
            boolean r16 = r3.isEndInclusive()
            if (r16 == 0) goto L_0x012d
            int r15 = r15 + 1
        L_0x012d:
            int r11 = r15 + r7
            if (r13 >= r11) goto L_0x0159
            java.lang.String r10 = ""
            r17 = r6
            java.lang.CharSequence r6 = r3.getToAdd()
            boolean r6 = r10.equals(r6)
            if (r6 == 0) goto L_0x0144
            r14.delete(r13, r11)
            r6 = 0
            goto L_0x0155
        L_0x0144:
            java.lang.CharSequence r3 = r3.getToAdd()
            r6 = 0
            char r3 = r3.charAt(r6)
        L_0x014d:
            if (r13 >= r11) goto L_0x0155
            r14.setCharAt(r13, r3)
            int r13 = r13 + 1
            goto L_0x014d
        L_0x0155:
            r5.set((int) r4, (int) r15)
            goto L_0x01a0
        L_0x0159:
            r17 = r6
            r6 = r10
            goto L_0x01a0
        L_0x015d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unknown op: "
            r1.<init>(r2)
            int r2 = r3.getOp()
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0176:
            r17 = r6
            r6 = r10
            java.lang.CharSequence r4 = r3.getToAdd()
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L_0x018c
            int r4 = r8.pos
            boolean r4 = r5.get(r4)
            if (r4 == 0) goto L_0x018c
            goto L_0x01a0
        L_0x018c:
            int r13 = r13 + 1
            java.lang.CharSequence r3 = r3.getToAdd()
            r14.insert(r13, r3)
            goto L_0x01a0
        L_0x0196:
            r17 = r6
            r6 = r10
            java.lang.CharSequence r3 = r3.getToAdd()
            r14.insert(r13, r3)
        L_0x01a0:
            int r3 = r14.length()
            int r3 = r3 - r9
            int r7 = r7 + r3
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x01b3
            java.lang.Object r3 = r2.next()
            org.apache.poi.ss.format.CellNumberStringMod r3 = (org.apache.poi.ss.format.CellNumberStringMod) r3
            goto L_0x01b4
        L_0x01b3:
            r3 = 0
        L_0x01b4:
            r10 = r6
            r11 = 1
            r6 = r17
            goto L_0x00f1
        L_0x01ba:
            r17 = r6
            r6 = r10
            r10 = r6
            r11 = 1
            r6 = r17
            goto L_0x00c8
        L_0x01c3:
            if (r12 == 0) goto L_0x01ca
            r1 = 45
            r0.append(r1)
        L_0x01ca:
            r0.append(r14)
            return
        L_0x01ce:
            r0 = move-exception
            r2 = r0
            throw r2     // Catch:{ all -> 0x01d1 }
        L_0x01d1:
            r0 = move-exception
            r3 = r0
            r1.close()     // Catch:{ all -> 0x01d7 }
            goto L_0x01dc
        L_0x01d7:
            r0 = move-exception
            r1 = r0
            r2.addSuppressed(r1)     // Catch:{ IllegalFormatException -> 0x01dd }
        L_0x01dc:
            throw r3     // Catch:{ IllegalFormatException -> 0x01dd }
        L_0x01dd:
            r0 = move-exception
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Format: "
            r2.<init>(r3)
            java.lang.String r3 = r9.printfFmt
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.format.CellNumberFormatter.formatValue(java.lang.StringBuffer, java.lang.Object):void");
    }

    private void writeScientific(double d, StringBuffer stringBuffer, Set<CellNumberStringMod> set) {
        StringBuffer stringBuffer2 = new StringBuffer();
        FieldPosition fieldPosition = new FieldPosition(1);
        this.decimalFmt.format(d, stringBuffer2, fieldPosition);
        writeInteger(stringBuffer2, stringBuffer, this.integerSpecials, set, this.showGroupingSeparator);
        writeFractional(stringBuffer2, stringBuffer);
        int endIndex = fieldPosition.getEndIndex() + 1;
        char charAt = stringBuffer2.charAt(endIndex);
        if (charAt != '-') {
            stringBuffer2.insert(endIndex, '+');
            charAt = '+';
        }
        Special next = this.exponentSpecials.listIterator(1).next();
        char c = next.ch;
        if (charAt == '-' || c == '+') {
            set.add(replaceMod(next, true, next, true, charAt));
        } else {
            set.add(deleteMod(next, true, next, true));
        }
        writeInteger(new StringBuffer(stringBuffer2.substring(endIndex + 1)), stringBuffer, this.exponentDigitSpecials, set, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f3 A[Catch:{ RuntimeException -> 0x011f }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00ff A[Catch:{ RuntimeException -> 0x011f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void writeFraction(double r17, java.lang.StringBuffer r19, double r20, java.lang.StringBuffer r22, java.util.Set<org.apache.poi.ss.format.CellNumberStringMod> r23) {
        /*
            r16 = this;
            r0 = r16
            r7 = r20
            r9 = r23
            boolean r1 = r0.improperFraction
            r10 = 0
            r12 = 1
            if (r1 != 0) goto L_0x00c9
            int r1 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            r13 = 32
            r14 = 63
            r2 = 48
            r15 = 0
            if (r1 != 0) goto L_0x0056
            java.util.List<org.apache.poi.ss.format.CellNumberFormatter$Special> r3 = r0.numeratorSpecials
            boolean r3 = hasChar(r2, r3)
            if (r3 != 0) goto L_0x0056
            java.util.List<org.apache.poi.ss.format.CellNumberFormatter$Special> r4 = r0.integerSpecials
            r6 = 0
            r1 = r16
            r2 = r19
            r3 = r22
            r5 = r23
            r1.writeInteger(r2, r3, r4, r5, r6)
            java.util.List<org.apache.poi.ss.format.CellNumberFormatter$Special> r1 = r0.integerSpecials
            org.apache.poi.ss.format.CellNumberFormatter$Special r1 = lastSpecial(r1)
            java.util.List<org.apache.poi.ss.format.CellNumberFormatter$Special> r2 = r0.denominatorSpecials
            org.apache.poi.ss.format.CellNumberFormatter$Special r2 = lastSpecial(r2)
            java.util.List<org.apache.poi.ss.format.CellNumberFormatter$Special> r3 = r0.integerSpecials
            java.util.List<org.apache.poi.ss.format.CellNumberFormatter$Special> r4 = r0.numeratorSpecials
            java.util.List<org.apache.poi.ss.format.CellNumberFormatter$Special> r0 = r0.denominatorSpecials
            boolean r0 = hasChar(r14, r3, r4, r0)
            if (r0 == 0) goto L_0x004e
            org.apache.poi.ss.format.CellNumberStringMod r0 = replaceMod(r1, r15, r2, r12, r13)
            r9.add(r0)
            goto L_0x0055
        L_0x004e:
            org.apache.poi.ss.format.CellNumberStringMod r0 = deleteMod(r1, r15, r2, r12)
            r9.add(r0)
        L_0x0055:
            return
        L_0x0056:
            java.util.List<org.apache.poi.ss.format.CellNumberFormatter$Special> r3 = r0.numeratorSpecials
            boolean r3 = hasChar(r2, r3)
            r3 = r3 ^ r12
            java.util.List<org.apache.poi.ss.format.CellNumberFormatter$Special> r4 = r0.integerSpecials
            boolean r2 = hasChar(r2, r4)
            r2 = r2 ^ r12
            java.util.List<org.apache.poi.ss.format.CellNumberFormatter$Special> r4 = r0.integerSpecials
            boolean r4 = r4.isEmpty()
            if (r4 != 0) goto L_0x0081
            java.util.List<org.apache.poi.ss.format.CellNumberFormatter$Special> r4 = r0.integerSpecials
            int r4 = r4.size()
            if (r4 != r12) goto L_0x007f
            r4 = 35
            java.util.List<org.apache.poi.ss.format.CellNumberFormatter$Special> r5 = r0.integerSpecials
            boolean r4 = hasChar(r4, r5)
            if (r4 == 0) goto L_0x007f
            goto L_0x0081
        L_0x007f:
            r4 = r15
            goto L_0x0082
        L_0x0081:
            r4 = r12
        L_0x0082:
            if (r1 != 0) goto L_0x008a
            if (r4 != 0) goto L_0x0088
            if (r3 == 0) goto L_0x008a
        L_0x0088:
            r3 = r12
            goto L_0x008b
        L_0x008a:
            r3 = r15
        L_0x008b:
            if (r1 == 0) goto L_0x0091
            if (r2 == 0) goto L_0x0091
            r1 = r12
            goto L_0x0092
        L_0x0091:
            r1 = r15
        L_0x0092:
            int r2 = (r17 > r10 ? 1 : (r17 == r10 ? 0 : -1))
            if (r2 != 0) goto L_0x00bb
            if (r3 != 0) goto L_0x009a
            if (r1 == 0) goto L_0x00bb
        L_0x009a:
            java.util.List<org.apache.poi.ss.format.CellNumberFormatter$Special> r1 = r0.integerSpecials
            org.apache.poi.ss.format.CellNumberFormatter$Special r1 = lastSpecial(r1)
            java.util.List<org.apache.poi.ss.format.CellNumberFormatter$Special> r2 = r0.integerSpecials
            java.util.List<org.apache.poi.ss.format.CellNumberFormatter$Special> r3 = r0.numeratorSpecials
            boolean r2 = hasChar(r14, r2, r3)
            if (r2 == 0) goto L_0x00b1
            org.apache.poi.ss.format.CellNumberFormatter$Special r2 = r0.numerator
            org.apache.poi.ss.format.CellNumberStringMod r1 = replaceMod(r1, r12, r2, r15, r13)
            goto L_0x00b7
        L_0x00b1:
            org.apache.poi.ss.format.CellNumberFormatter$Special r2 = r0.numerator
            org.apache.poi.ss.format.CellNumberStringMod r1 = deleteMod(r1, r12, r2, r15)
        L_0x00b7:
            r9.add(r1)
            goto L_0x00c9
        L_0x00bb:
            java.util.List<org.apache.poi.ss.format.CellNumberFormatter$Special> r4 = r0.integerSpecials
            r6 = 0
            r1 = r16
            r2 = r19
            r3 = r22
            r5 = r23
            r1.writeInteger(r2, r3, r4, r5, r6)
        L_0x00c9:
            int r1 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r1 == 0) goto L_0x00ea
            boolean r1 = r0.improperFraction     // Catch:{ RuntimeException -> 0x011f }
            if (r1 == 0) goto L_0x00da
            r1 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r1 = r7 % r1
            int r1 = (r1 > r10 ? 1 : (r1 == r10 ? 0 : -1))
            if (r1 != 0) goto L_0x00da
            goto L_0x00ea
        L_0x00da:
            int r1 = r0.maxDenominator     // Catch:{ RuntimeException -> 0x011f }
            org.apache.poi.ss.format.SimpleFraction r1 = org.apache.poi.ss.format.SimpleFraction.buildFractionMaxDenominator(r7, r1)     // Catch:{ RuntimeException -> 0x011f }
            int r2 = r1.getNumerator()     // Catch:{ RuntimeException -> 0x011f }
            int r1 = r1.getDenominator()     // Catch:{ RuntimeException -> 0x011f }
            r12 = r1
            goto L_0x00ef
        L_0x00ea:
            long r1 = java.lang.Math.round(r20)     // Catch:{ RuntimeException -> 0x011f }
            int r2 = (int) r1     // Catch:{ RuntimeException -> 0x011f }
        L_0x00ef:
            boolean r1 = r0.improperFraction     // Catch:{ RuntimeException -> 0x011f }
            if (r1 == 0) goto L_0x00ff
            long r1 = (long) r2     // Catch:{ RuntimeException -> 0x011f }
            double r3 = (double) r12     // Catch:{ RuntimeException -> 0x011f }
            double r3 = r3 * r17
            long r3 = java.lang.Math.round(r3)     // Catch:{ RuntimeException -> 0x011f }
            long r1 = r1 + r3
            int r1 = (int) r1     // Catch:{ RuntimeException -> 0x011f }
            r3 = r1
            goto L_0x0100
        L_0x00ff:
            r3 = r2
        L_0x0100:
            java.lang.String r2 = r0.numeratorFmt     // Catch:{ RuntimeException -> 0x011f }
            java.util.List<org.apache.poi.ss.format.CellNumberFormatter$Special> r5 = r0.numeratorSpecials     // Catch:{ RuntimeException -> 0x011f }
            r1 = r16
            r4 = r22
            r6 = r23
            r1.writeSingleInteger(r2, r3, r4, r5, r6)     // Catch:{ RuntimeException -> 0x011f }
            java.lang.String r1 = r0.denominatorFmt     // Catch:{ RuntimeException -> 0x011f }
            java.util.List<org.apache.poi.ss.format.CellNumberFormatter$Special> r2 = r0.denominatorSpecials     // Catch:{ RuntimeException -> 0x011f }
            r17 = r1
            r18 = r12
            r19 = r22
            r20 = r2
            r21 = r23
            r16.writeSingleInteger(r17, r18, r19, r20, r21)     // Catch:{ RuntimeException -> 0x011f }
            goto L_0x012f
        L_0x011f:
            r0 = move-exception
            org.apache.logging.log4j.Logger r1 = LOG
            org.apache.logging.log4j.LogBuilder r1 = r1.atError()
            org.apache.logging.log4j.LogBuilder r0 = r1.withThrowable(r0)
            java.lang.String r1 = "error while fraction evaluation"
            r0.log((java.lang.String) r1)
        L_0x012f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.format.CellNumberFormatter.writeFraction(double, java.lang.StringBuffer, double, java.lang.StringBuffer, java.util.Set):void");
    }

    private String localiseFormat(String str) {
        DecimalFormatSymbols decimalFormatSymbols = getDecimalFormatSymbols();
        if (!str.contains(",") || decimalFormatSymbols.getGroupingSeparator() == ',') {
            return (!str.contains(".") || decimalFormatSymbols.getDecimalSeparator() == '.') ? str : str.replace('.', decimalFormatSymbols.getDecimalSeparator());
        }
        if (!str.contains(".") || decimalFormatSymbols.getDecimalSeparator() == '.') {
            return str.replace(',', decimalFormatSymbols.getGroupingSeparator());
        }
        return replaceLast(str, "\\.", "[DECIMAL_SEPARATOR]").replace(',', decimalFormatSymbols.getGroupingSeparator()).replace("[DECIMAL_SEPARATOR]", Character.toString(decimalFormatSymbols.getDecimalSeparator()));
    }

    private static String replaceLast(String str, String str2, String str3) {
        return str.replaceFirst("(?s)(.*)" + str2, "$1" + str3);
    }

    private static boolean hasChar(char c, List<Special> list) {
        for (Special special : list) {
            if (special.ch == c) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasChar(char c, List<Special> list, List<Special> list2) {
        return hasChar(c, list) || hasChar(c, list2);
    }

    private static boolean hasChar(char c, List<Special> list, List<Special> list2, List<Special> list3) {
        return hasChar(c, list) || hasChar(c, list2) || hasChar(c, list3);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002e, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
        r6.addSuppressed(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0032, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0029, code lost:
        r7 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void writeSingleInteger(java.lang.String r7, int r8, java.lang.StringBuffer r9, java.util.List<org.apache.poi.ss.format.CellNumberFormatter.Special> r10, java.util.Set<org.apache.poi.ss.format.CellNumberStringMod> r11) {
        /*
            r6 = this;
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.util.Formatter r0 = new java.util.Formatter
            java.util.Locale r2 = r6.locale
            r0.<init>(r1, r2)
            java.util.Locale r2 = r6.locale     // Catch:{ all -> 0x0027 }
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0027 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0027 }
            r4 = 0
            r3[r4] = r8     // Catch:{ all -> 0x0027 }
            r0.format(r2, r7, r3)     // Catch:{ all -> 0x0027 }
            r0.close()
            r5 = 0
            r0 = r6
            r2 = r9
            r3 = r10
            r4 = r11
            r0.writeInteger(r1, r2, r3, r4, r5)
            return
        L_0x0027:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0029 }
        L_0x0029:
            r7 = move-exception
            r0.close()     // Catch:{ all -> 0x002e }
            goto L_0x0032
        L_0x002e:
            r8 = move-exception
            r6.addSuppressed(r8)
        L_0x0032:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.format.CellNumberFormatter.writeSingleInteger(java.lang.String, int, java.lang.StringBuffer, java.util.List, java.util.Set):void");
    }

    private void writeInteger(StringBuffer stringBuffer, StringBuffer stringBuffer2, List<Special> list, Set<CellNumberStringMod> set, boolean z) {
        Special special;
        boolean z2;
        int i;
        StringBuffer stringBuffer3 = stringBuffer;
        List<Special> list2 = list;
        Set<CellNumberStringMod> set2 = set;
        DecimalFormatSymbols decimalFormatSymbols = getDecimalFormatSymbols();
        String ch = Character.toString(decimalFormatSymbols.getDecimalSeparator());
        String ch2 = Character.toString(decimalFormatSymbols.getGroupingSeparator());
        int indexOf = stringBuffer3.indexOf(ch) - 1;
        if (indexOf < 0) {
            if (this.exponent == null || list2 != this.integerSpecials) {
                i = stringBuffer.length();
            } else {
                i = stringBuffer3.indexOf(ExifInterface.LONGITUDE_EAST);
            }
            indexOf = i - 1;
        }
        int i2 = 0;
        while (i2 < indexOf && ((r10 = stringBuffer3.charAt(i2)) == '0' || r10 == decimalFormatSymbols.getGroupingSeparator())) {
            i2++;
        }
        ListIterator<Special> listIterator = list2.listIterator(list.size());
        Special special2 = null;
        int i3 = 0;
        while (listIterator.hasPrevious()) {
            char charAt = indexOf >= 0 ? stringBuffer3.charAt(indexOf) : '0';
            Special previous = listIterator.previous();
            boolean z3 = z && i3 > 0 && i3 % 3 == 0;
            if (charAt != '0' || previous.ch == '0' || previous.ch == '?' || indexOf >= i2) {
                z2 = previous.ch == '?' && indexOf < i2;
                int i4 = previous.pos;
                if (z2) {
                    charAt = Chars.SPACE;
                }
                stringBuffer2.setCharAt(i4, charAt);
                special = previous;
            } else {
                StringBuffer stringBuffer4 = stringBuffer2;
                special = special2;
                z2 = false;
            }
            if (z3) {
                set2.add(insertMod(previous, z2 ? " " : ch2, 2));
            }
            i3++;
            indexOf--;
            special2 = special;
        }
        if (indexOf >= 0) {
            int i5 = indexOf + 1;
            StringBuffer stringBuffer5 = new StringBuffer(stringBuffer3.substring(0, i5));
            if (z) {
                while (i5 > 0) {
                    if (i3 > 0 && i3 % 3 == 0) {
                        stringBuffer5.insert(i5, ch2);
                    }
                    i3++;
                    i5--;
                }
            }
            set2.add(insertMod(special2, stringBuffer5, 1));
        }
    }

    private void writeFractional(StringBuffer stringBuffer, StringBuffer stringBuffer2) {
        int i;
        if (!this.fractionalSpecials.isEmpty()) {
            int indexOf = stringBuffer.indexOf(Character.toString(getDecimalFormatSymbols().getDecimalSeparator())) + 1;
            if (this.exponent != null) {
                i = stringBuffer.indexOf("e");
            } else {
                i = stringBuffer.length();
            }
            while (true) {
                i--;
                if (i <= indexOf || stringBuffer.charAt(i) != '0') {
                }
            }
            for (Special next : this.fractionalSpecials) {
                char charAt = stringBuffer.charAt(indexOf);
                if (charAt != '0' || next.ch == '0' || indexOf < i) {
                    stringBuffer2.setCharAt(next.pos, charAt);
                } else if (next.ch == '?') {
                    stringBuffer2.setCharAt(next.pos, Chars.SPACE);
                }
                indexOf++;
            }
        }
    }

    public void simpleValue(StringBuffer stringBuffer, Object obj) {
        this.SIMPLE_NUMBER.formatValue(stringBuffer, obj);
    }

    private static Special lastSpecial(List<Special> list) {
        return list.get(list.size() - 1);
    }
}
