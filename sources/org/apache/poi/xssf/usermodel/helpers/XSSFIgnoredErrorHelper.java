package org.apache.poi.xssf.usermodel.helpers;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.poi.ss.usermodel.IgnoredErrorType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIgnoredError;

public class XSSFIgnoredErrorHelper {

    /* renamed from: org.apache.poi.xssf.usermodel.helpers.XSSFIgnoredErrorHelper$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$usermodel$IgnoredErrorType;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|(3:17|18|20)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.ss.usermodel.IgnoredErrorType[] r0 = org.apache.poi.ss.usermodel.IgnoredErrorType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ss$usermodel$IgnoredErrorType = r0
                org.apache.poi.ss.usermodel.IgnoredErrorType r1 = org.apache.poi.ss.usermodel.IgnoredErrorType.CALCULATED_COLUMN     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$IgnoredErrorType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.usermodel.IgnoredErrorType r1 = org.apache.poi.ss.usermodel.IgnoredErrorType.EMPTY_CELL_REFERENCE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$IgnoredErrorType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ss.usermodel.IgnoredErrorType r1 = org.apache.poi.ss.usermodel.IgnoredErrorType.EVALUATION_ERROR     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$IgnoredErrorType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.ss.usermodel.IgnoredErrorType r1 = org.apache.poi.ss.usermodel.IgnoredErrorType.FORMULA     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$IgnoredErrorType     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.ss.usermodel.IgnoredErrorType r1 = org.apache.poi.ss.usermodel.IgnoredErrorType.FORMULA_RANGE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$IgnoredErrorType     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.ss.usermodel.IgnoredErrorType r1 = org.apache.poi.ss.usermodel.IgnoredErrorType.LIST_DATA_VALIDATION     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$IgnoredErrorType     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.apache.poi.ss.usermodel.IgnoredErrorType r1 = org.apache.poi.ss.usermodel.IgnoredErrorType.NUMBER_STORED_AS_TEXT     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$IgnoredErrorType     // Catch:{ NoSuchFieldError -> 0x0060 }
                org.apache.poi.ss.usermodel.IgnoredErrorType r1 = org.apache.poi.ss.usermodel.IgnoredErrorType.TWO_DIGIT_TEXT_YEAR     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$IgnoredErrorType     // Catch:{ NoSuchFieldError -> 0x006c }
                org.apache.poi.ss.usermodel.IgnoredErrorType r1 = org.apache.poi.ss.usermodel.IgnoredErrorType.UNLOCKED_FORMULA     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.helpers.XSSFIgnoredErrorHelper.AnonymousClass1.<clinit>():void");
        }
    }

    public static boolean isSet(IgnoredErrorType ignoredErrorType, CTIgnoredError cTIgnoredError) {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$IgnoredErrorType[ignoredErrorType.ordinal()]) {
            case 1:
                return cTIgnoredError.isSetCalculatedColumn();
            case 2:
                return cTIgnoredError.isSetEmptyCellReference();
            case 3:
                return cTIgnoredError.isSetEvalError();
            case 4:
                return cTIgnoredError.isSetFormula();
            case 5:
                return cTIgnoredError.isSetFormulaRange();
            case 6:
                return cTIgnoredError.isSetListDataValidation();
            case 7:
                return cTIgnoredError.isSetNumberStoredAsText();
            case 8:
                return cTIgnoredError.isSetTwoDigitTextYear();
            case 9:
                return cTIgnoredError.isSetUnlockedFormula();
            default:
                throw new IllegalStateException();
        }
    }

    public static void set(IgnoredErrorType ignoredErrorType, CTIgnoredError cTIgnoredError) {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$IgnoredErrorType[ignoredErrorType.ordinal()]) {
            case 1:
                cTIgnoredError.setCalculatedColumn(true);
                return;
            case 2:
                cTIgnoredError.setEmptyCellReference(true);
                return;
            case 3:
                cTIgnoredError.setEvalError(true);
                return;
            case 4:
                cTIgnoredError.setFormula(true);
                return;
            case 5:
                cTIgnoredError.setFormulaRange(true);
                return;
            case 6:
                cTIgnoredError.setListDataValidation(true);
                return;
            case 7:
                cTIgnoredError.setNumberStoredAsText(true);
                return;
            case 8:
                cTIgnoredError.setTwoDigitTextYear(true);
                return;
            case 9:
                cTIgnoredError.setUnlockedFormula(true);
                return;
            default:
                throw new IllegalStateException();
        }
    }

    public static void addIgnoredErrors(CTIgnoredError cTIgnoredError, String str, IgnoredErrorType... ignoredErrorTypeArr) {
        cTIgnoredError.setSqref(Collections.singletonList(str));
        for (IgnoredErrorType ignoredErrorType : ignoredErrorTypeArr) {
            set(ignoredErrorType, cTIgnoredError);
        }
    }

    public static Set<IgnoredErrorType> getErrorTypes(CTIgnoredError cTIgnoredError) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (IgnoredErrorType ignoredErrorType : IgnoredErrorType.values()) {
            if (isSet(ignoredErrorType, cTIgnoredError)) {
                linkedHashSet.add(ignoredErrorType);
            }
        }
        return linkedHashSet;
    }
}
