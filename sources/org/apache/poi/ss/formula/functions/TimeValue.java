package org.apache.poi.ss.formula.functions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.DateParser;

public class TimeValue extends Fixed1ArgFunction {
    private static final Logger LOG = LogManager.getLogger((Class<?>) TimeValue.class);

    /* JADX WARNING: Can't wrap try/catch for region: R(3:12|13|14) */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:9|10|11) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0029, code lost:
        return parseTimeFromDateTime("1/01/2000 " + r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        org.apache.poi.ss.util.DateParser.parseLocalDate(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0034, code lost:
        return new org.apache.poi.ss.formula.eval.NumberEval(0.0d);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x002a */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0018 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.ss.formula.eval.ValueEval evaluate(int r2, int r3, org.apache.poi.ss.formula.eval.ValueEval r4) {
        /*
            r1 = this;
            java.lang.String r0 = "1/01/2000 "
            org.apache.poi.ss.formula.eval.ValueEval r2 = org.apache.poi.ss.formula.eval.OperandResolver.getSingleValue(r4, r2, r3)     // Catch:{ DateTimeException -> 0x003e, EvaluationException -> 0x0038 }
            java.lang.String r2 = org.apache.poi.ss.formula.eval.OperandResolver.coerceValueToString(r2)     // Catch:{ DateTimeException -> 0x003e, EvaluationException -> 0x0038 }
            if (r2 == 0) goto L_0x0035
            boolean r3 = r2.isEmpty()     // Catch:{ DateTimeException -> 0x003e, EvaluationException -> 0x0038 }
            if (r3 == 0) goto L_0x0013
            goto L_0x0035
        L_0x0013:
            org.apache.poi.ss.formula.eval.NumberEval r1 = r1.parseTimeFromDateTime(r2)     // Catch:{ Exception -> 0x0018 }
            return r1
        L_0x0018:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x002a }
            r3.<init>(r0)     // Catch:{ Exception -> 0x002a }
            java.lang.StringBuilder r3 = r3.append(r2)     // Catch:{ Exception -> 0x002a }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x002a }
            org.apache.poi.ss.formula.eval.NumberEval r1 = r1.parseTimeFromDateTime(r3)     // Catch:{ Exception -> 0x002a }
            return r1
        L_0x002a:
            org.apache.poi.ss.util.DateParser.parseLocalDate(r2)     // Catch:{ DateTimeException -> 0x003e, EvaluationException -> 0x0038 }
            org.apache.poi.ss.formula.eval.NumberEval r1 = new org.apache.poi.ss.formula.eval.NumberEval     // Catch:{ DateTimeException -> 0x003e, EvaluationException -> 0x0038 }
            r2 = 0
            r1.<init>((double) r2)     // Catch:{ DateTimeException -> 0x003e, EvaluationException -> 0x0038 }
            return r1
        L_0x0035:
            org.apache.poi.ss.formula.eval.BlankEval r1 = org.apache.poi.ss.formula.eval.BlankEval.instance     // Catch:{ DateTimeException -> 0x003e, EvaluationException -> 0x0038 }
            return r1
        L_0x0038:
            r1 = move-exception
            org.apache.poi.ss.formula.eval.ErrorEval r1 = r1.getErrorEval()
            return r1
        L_0x003e:
            r1 = move-exception
            org.apache.logging.log4j.Logger r2 = LOG
            org.apache.logging.log4j.LogBuilder r2 = r2.atInfo()
            java.lang.String r3 = "Failed to parse date/time"
            r2.log((java.lang.String) r3, (java.lang.Object) r1)
            org.apache.poi.ss.formula.eval.ErrorEval r1 = org.apache.poi.ss.formula.eval.ErrorEval.VALUE_INVALID
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.functions.TimeValue.evaluate(int, int, org.apache.poi.ss.formula.eval.ValueEval):org.apache.poi.ss.formula.eval.ValueEval");
    }

    private NumberEval parseTimeFromDateTime(String str) throws EvaluationException {
        return new NumberEval(DateUtil.parseDateTime(str).doubleValue() - DateUtil.getExcelDate(DateParser.parseLocalDate(str)));
    }
}
