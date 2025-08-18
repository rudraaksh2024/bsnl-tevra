package org.apache.poi.ss.format;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.util.LocaleUtil;

public class CellFormat {
    private static final String INVALID_VALUE_FOR_FORMAT = "###############################################################################################################################################################################################################################################################";
    private static final Logger LOG = LogManager.getLogger((Class<?>) CellFormat.class);
    private static final Pattern ONE_PART = Pattern.compile(CellFormatPart.FORMAT_PAT.pattern() + "(;|$)", 6);
    private static final String QUOTE = "\"";
    private static final Map<Locale, Map<String, CellFormat>> formatCache = new WeakHashMap();
    private final String format;
    private final int formatPartCount;
    private final Locale locale;
    private final CellFormatPart negNumFmt;
    private final CellFormatPart posNumFmt;
    private final CellFormatPart textFmt;
    private final CellFormatPart zeroNumFmt;

    private static CellFormat createGeneralFormat(final Locale locale2) {
        return new CellFormat("General", locale2) {
            public CellFormatResult apply(Object obj) {
                return new CellFormatResult(true, new CellGeneralFormatter(locale2).format(obj), (Color) null);
            }
        };
    }

    public static CellFormat getInstance(String str) {
        return getInstance(LocaleUtil.getUserLocale(), str);
    }

    public static synchronized CellFormat getInstance(Locale locale2, String str) {
        CellFormat cellFormat;
        synchronized (CellFormat.class) {
            Map computeIfAbsent = formatCache.computeIfAbsent(locale2, new CellFormat$$ExternalSyntheticLambda0());
            cellFormat = (CellFormat) computeIfAbsent.get(str);
            if (cellFormat == null) {
                if (!str.equals("General")) {
                    if (!str.equals("@")) {
                        cellFormat = new CellFormat(locale2, str);
                        computeIfAbsent.put(str, cellFormat);
                    }
                }
                cellFormat = createGeneralFormat(locale2);
                computeIfAbsent.put(str, cellFormat);
            }
        }
        return cellFormat;
    }

    static /* synthetic */ Map lambda$getInstance$0(Locale locale2) {
        return new WeakHashMap();
    }

    private CellFormat(Locale locale2, String str) {
        this.locale = locale2;
        this.format = str;
        CellFormatPart cellFormatPart = new CellFormatPart(locale2, "@");
        Matcher matcher = ONE_PART.matcher(str);
        ArrayList arrayList = new ArrayList();
        while (matcher.find()) {
            try {
                String group = matcher.group();
                arrayList.add(new CellFormatPart(locale2, group.endsWith(";") ? group.substring(0, group.length() - 1) : group));
            } catch (RuntimeException e) {
                LOG.log(Level.WARN, "Invalid format: " + CellFormatter.quote(matcher.group()), (Throwable) e);
                arrayList.add((Object) null);
            }
        }
        int size = arrayList.size();
        this.formatPartCount = size;
        if (size == 1) {
            this.posNumFmt = (CellFormatPart) arrayList.get(0);
            this.negNumFmt = null;
            this.zeroNumFmt = null;
            this.textFmt = cellFormatPart;
        } else if (size == 2) {
            this.posNumFmt = (CellFormatPart) arrayList.get(0);
            this.negNumFmt = (CellFormatPart) arrayList.get(1);
            this.zeroNumFmt = null;
            this.textFmt = cellFormatPart;
        } else if (size != 3) {
            this.posNumFmt = (CellFormatPart) arrayList.get(0);
            this.negNumFmt = (CellFormatPart) arrayList.get(1);
            this.zeroNumFmt = (CellFormatPart) arrayList.get(2);
            this.textFmt = (CellFormatPart) arrayList.get(3);
        } else {
            this.posNumFmt = (CellFormatPart) arrayList.get(0);
            this.negNumFmt = (CellFormatPart) arrayList.get(1);
            this.zeroNumFmt = (CellFormatPart) arrayList.get(2);
            this.textFmt = cellFormatPart;
        }
    }

    public CellFormatResult apply(Object obj) {
        if (obj instanceof Number) {
            double doubleValue = ((Number) obj).doubleValue();
            if (doubleValue >= 0.0d || ((this.formatPartCount != 2 || this.posNumFmt.hasCondition() || this.negNumFmt.hasCondition()) && ((this.formatPartCount != 3 || this.negNumFmt.hasCondition()) && (this.formatPartCount != 4 || this.negNumFmt.hasCondition())))) {
                return getApplicableFormatPart(Double.valueOf(doubleValue)).apply(Double.valueOf(doubleValue));
            }
            return this.negNumFmt.apply(Double.valueOf(-doubleValue));
        } else if (!(obj instanceof Date)) {
            return this.textFmt.apply(obj);
        } else {
            double excelDate = DateUtil.getExcelDate((Date) obj);
            if (DateUtil.isValidExcelDate(excelDate)) {
                return getApplicableFormatPart(Double.valueOf(excelDate)).apply(obj);
            }
            throw new IllegalArgumentException("value " + excelDate + " of date " + obj + " is not a valid Excel date");
        }
    }

    private CellFormatResult apply(Date date, double d) {
        return getApplicableFormatPart(Double.valueOf(d)).apply(date);
    }

    /* renamed from: org.apache.poi.ss.format.CellFormat$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$usermodel$CellType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                org.apache.poi.ss.usermodel.CellType[] r0 = org.apache.poi.ss.usermodel.CellType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ss$usermodel$CellType = r0
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.BLANK     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.NUMERIC     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.STRING     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.format.CellFormat.AnonymousClass2.<clinit>():void");
        }
    }

    public CellFormatResult apply(Cell cell) {
        int i = AnonymousClass2.$SwitchMap$org$apache$poi$ss$usermodel$CellType[ultimateType(cell).ordinal()];
        if (i == 1) {
            return apply((Object) "");
        }
        if (i == 2) {
            return apply((Object) Boolean.valueOf(cell.getBooleanCellValue()));
        }
        if (i == 3) {
            double numericCellValue = cell.getNumericCellValue();
            if (getApplicableFormatPart(Double.valueOf(numericCellValue)).getCellFormatType() != CellFormatType.DATE) {
                return apply((Object) Double.valueOf(numericCellValue));
            }
            if (DateUtil.isValidExcelDate(numericCellValue)) {
                return apply(cell.getDateCellValue(), numericCellValue);
            }
            return apply((Object) INVALID_VALUE_FOR_FORMAT);
        } else if (i != 4) {
            return apply((Object) "?");
        } else {
            return apply((Object) cell.getStringCellValue());
        }
    }

    public CellFormatResult apply(JLabel jLabel, Object obj) {
        CellFormatResult apply = apply(obj);
        jLabel.setText(apply.text);
        if (apply.textColor != null) {
            jLabel.setForeground(apply.textColor);
        }
        return apply;
    }

    private CellFormatResult apply(JLabel jLabel, Date date, double d) {
        CellFormatResult apply = apply(date, d);
        jLabel.setText(apply.text);
        if (apply.textColor != null) {
            jLabel.setForeground(apply.textColor);
        }
        return apply;
    }

    public CellFormatResult apply(JLabel jLabel, Cell cell) {
        int i = AnonymousClass2.$SwitchMap$org$apache$poi$ss$usermodel$CellType[ultimateType(cell).ordinal()];
        if (i == 1) {
            return apply(jLabel, (Object) "");
        }
        if (i == 2) {
            return apply(jLabel, (Object) Boolean.valueOf(cell.getBooleanCellValue()));
        }
        if (i == 3) {
            double numericCellValue = cell.getNumericCellValue();
            if (getApplicableFormatPart(Double.valueOf(numericCellValue)).getCellFormatType() != CellFormatType.DATE) {
                return apply(jLabel, (Object) Double.valueOf(numericCellValue));
            }
            if (DateUtil.isValidExcelDate(numericCellValue)) {
                return apply(jLabel, cell.getDateCellValue(), numericCellValue);
            }
            return apply(jLabel, (Object) INVALID_VALUE_FOR_FORMAT);
        } else if (i != 4) {
            return apply(jLabel, (Object) "?");
        } else {
            return apply(jLabel, (Object) cell.getStringCellValue());
        }
    }

    private CellFormatPart getApplicableFormatPart(Object obj) {
        if (obj instanceof Number) {
            double doubleValue = ((Number) obj).doubleValue();
            int i = this.formatPartCount;
            if (i == 1) {
                if (!this.posNumFmt.hasCondition() || (this.posNumFmt.hasCondition() && this.posNumFmt.applies(Double.valueOf(doubleValue)))) {
                    return this.posNumFmt;
                }
                return new CellFormatPart(this.locale, "General");
            } else if (i == 2) {
                if ((!this.posNumFmt.hasCondition() && doubleValue >= 0.0d) || (this.posNumFmt.hasCondition() && this.posNumFmt.applies(Double.valueOf(doubleValue)))) {
                    return this.posNumFmt;
                }
                if (!this.negNumFmt.hasCondition() || (this.negNumFmt.hasCondition() && this.negNumFmt.applies(Double.valueOf(doubleValue)))) {
                    return this.negNumFmt;
                }
                return new CellFormatPart("\"###############################################################################################################################################################################################################################################################\"");
            } else if ((!this.posNumFmt.hasCondition() && doubleValue > 0.0d) || (this.posNumFmt.hasCondition() && this.posNumFmt.applies(Double.valueOf(doubleValue)))) {
                return this.posNumFmt;
            } else {
                if ((this.negNumFmt.hasCondition() || doubleValue >= 0.0d) && (!this.negNumFmt.hasCondition() || !this.negNumFmt.applies(Double.valueOf(doubleValue)))) {
                    return this.zeroNumFmt;
                }
                return this.negNumFmt;
            }
        } else {
            throw new IllegalArgumentException("value must be a Number");
        }
    }

    public static CellType ultimateType(Cell cell) {
        CellType cellType = cell.getCellType();
        return cellType == CellType.FORMULA ? cell.getCachedFormulaResultType() : cellType;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CellFormat) {
            return this.format.equals(((CellFormat) obj).format);
        }
        return false;
    }

    public int hashCode() {
        return this.format.hashCode();
    }
}
