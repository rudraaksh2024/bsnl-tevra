package org.apache.poi.ss.formula.atp;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.NotImplementedFunctionException;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.function.FunctionMetadataRegistry;
import org.apache.poi.ss.formula.functions.Averageifs;
import org.apache.poi.ss.formula.functions.BesselJ;
import org.apache.poi.ss.formula.functions.Bin2Dec;
import org.apache.poi.ss.formula.functions.Complex;
import org.apache.poi.ss.formula.functions.Countifs;
import org.apache.poi.ss.formula.functions.Dec2Bin;
import org.apache.poi.ss.formula.functions.Dec2Hex;
import org.apache.poi.ss.formula.functions.Delta;
import org.apache.poi.ss.formula.functions.DollarDe;
import org.apache.poi.ss.formula.functions.DollarFr;
import org.apache.poi.ss.formula.functions.EDate;
import org.apache.poi.ss.formula.functions.EOMonth;
import org.apache.poi.ss.formula.functions.FactDouble;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.functions.Hex2Dec;
import org.apache.poi.ss.formula.functions.ImReal;
import org.apache.poi.ss.formula.functions.Imaginary;
import org.apache.poi.ss.formula.functions.Maxifs;
import org.apache.poi.ss.formula.functions.Minifs;
import org.apache.poi.ss.formula.functions.NormDist;
import org.apache.poi.ss.formula.functions.NormInv;
import org.apache.poi.ss.formula.functions.NormSDist;
import org.apache.poi.ss.formula.functions.NormSInv;
import org.apache.poi.ss.formula.functions.NumberValueFunction;
import org.apache.poi.ss.formula.functions.Oct2Dec;
import org.apache.poi.ss.formula.functions.Quotient;
import org.apache.poi.ss.formula.functions.Single;
import org.apache.poi.ss.formula.functions.Sumifs;
import org.apache.poi.ss.formula.functions.TDist2t;
import org.apache.poi.ss.formula.functions.TDistLt;
import org.apache.poi.ss.formula.functions.TDistRt;
import org.apache.poi.ss.formula.functions.TextFunction;
import org.apache.poi.ss.formula.functions.WeekNum;
import org.apache.poi.ss.formula.udf.UDFFinder;

public final class AnalysisToolPak implements UDFFinder {
    public static final UDFFinder instance = new AnalysisToolPak();
    private final Map<String, FreeRefFunction> _functionsByName = createFunctionsMap();

    private static final class NotImplemented implements FreeRefFunction {
        private final String _functionName;

        public NotImplemented(String str) {
            this._functionName = str;
        }

        public ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
            throw new NotImplementedFunctionException(this._functionName);
        }
    }

    private AnalysisToolPak() {
    }

    public FreeRefFunction findFunction(String str) {
        if (str.startsWith("_xlfn.")) {
            str = str.substring(6);
        }
        return this._functionsByName.get(str.toUpperCase(Locale.ROOT));
    }

    private Map<String, FreeRefFunction> createFunctionsMap() {
        HashMap hashMap = new HashMap(127);
        r(hashMap, "ACCRINT", (FreeRefFunction) null);
        r(hashMap, "ACCRINTM", (FreeRefFunction) null);
        r(hashMap, "AMORDEGRC", (FreeRefFunction) null);
        r(hashMap, "AMORLINC", (FreeRefFunction) null);
        r(hashMap, "AVERAGEIF", (FreeRefFunction) null);
        r(hashMap, "AVERAGEIFS", Averageifs.instance);
        r(hashMap, "BAHTTEXT", (FreeRefFunction) null);
        r(hashMap, "BESSELI", (FreeRefFunction) null);
        r(hashMap, "BESSELJ", BesselJ.instance);
        r(hashMap, "BESSELK", (FreeRefFunction) null);
        r(hashMap, "BESSELY", (FreeRefFunction) null);
        r(hashMap, "BIN2DEC", Bin2Dec.instance);
        r(hashMap, "BIN2HEX", (FreeRefFunction) null);
        r(hashMap, "BIN2OCT", (FreeRefFunction) null);
        r(hashMap, "COMPLEX", Complex.instance);
        r(hashMap, "CONCAT", TextFunction.CONCAT);
        r(hashMap, "CONVERT", (FreeRefFunction) null);
        r(hashMap, "COUNTIFS", Countifs.instance);
        r(hashMap, "COUPDAYBS", (FreeRefFunction) null);
        r(hashMap, "COUPDAYS", (FreeRefFunction) null);
        r(hashMap, "COUPDAYSNC", (FreeRefFunction) null);
        r(hashMap, "COUPNCD", (FreeRefFunction) null);
        r(hashMap, "COUPNUM", (FreeRefFunction) null);
        r(hashMap, "COUPPCD", (FreeRefFunction) null);
        r(hashMap, "CUBEKPIMEMBER", (FreeRefFunction) null);
        r(hashMap, "CUBEMEMBER", (FreeRefFunction) null);
        r(hashMap, "CUBEMEMBERPROPERTY", (FreeRefFunction) null);
        r(hashMap, "CUBERANKEDMEMBER", (FreeRefFunction) null);
        r(hashMap, "CUBESET", (FreeRefFunction) null);
        r(hashMap, "CUBESETCOUNT", (FreeRefFunction) null);
        r(hashMap, "CUBEVALUE", (FreeRefFunction) null);
        r(hashMap, "CUMIPMT", (FreeRefFunction) null);
        r(hashMap, "CUMPRINC", (FreeRefFunction) null);
        r(hashMap, "DEC2BIN", Dec2Bin.instance);
        r(hashMap, "DEC2HEX", Dec2Hex.instance);
        r(hashMap, "DEC2OCT", (FreeRefFunction) null);
        r(hashMap, "DELTA", Delta.instance);
        r(hashMap, "DISC", (FreeRefFunction) null);
        r(hashMap, "DOLLARDE", DollarDe.instance);
        r(hashMap, "DOLLARFR", DollarFr.instance);
        r(hashMap, "DURATION", (FreeRefFunction) null);
        r(hashMap, "EDATE", EDate.instance);
        r(hashMap, "EFFECT", (FreeRefFunction) null);
        r(hashMap, "EOMONTH", EOMonth.instance);
        r(hashMap, "ERF", (FreeRefFunction) null);
        r(hashMap, "ERFC", (FreeRefFunction) null);
        r(hashMap, "FACTDOUBLE", FactDouble.instance);
        r(hashMap, "FVSCHEDULE", (FreeRefFunction) null);
        r(hashMap, "GCD", (FreeRefFunction) null);
        r(hashMap, "GESTEP", (FreeRefFunction) null);
        r(hashMap, "HEX2BIN", (FreeRefFunction) null);
        r(hashMap, "HEX2DEC", Hex2Dec.instance);
        r(hashMap, "HEX2OCT", (FreeRefFunction) null);
        r(hashMap, "IFERROR", IfError.instance);
        r(hashMap, "IFNA", IfNa.instance);
        r(hashMap, "IFS", Ifs.instance);
        r(hashMap, "IMABS", (FreeRefFunction) null);
        r(hashMap, "IMAGINARY", Imaginary.instance);
        r(hashMap, "IMARGUMENT", (FreeRefFunction) null);
        r(hashMap, "IMCONJUGATE", (FreeRefFunction) null);
        r(hashMap, "IMCOS", (FreeRefFunction) null);
        r(hashMap, "IMDIV", (FreeRefFunction) null);
        r(hashMap, "IMEXP", (FreeRefFunction) null);
        r(hashMap, "IMLN", (FreeRefFunction) null);
        r(hashMap, "IMLOG10", (FreeRefFunction) null);
        r(hashMap, "IMLOG2", (FreeRefFunction) null);
        r(hashMap, "IMPOWER", (FreeRefFunction) null);
        r(hashMap, "IMPRODUCT", (FreeRefFunction) null);
        r(hashMap, "IMREAL", ImReal.instance);
        r(hashMap, "IMSIN", (FreeRefFunction) null);
        r(hashMap, "IMSQRT", (FreeRefFunction) null);
        r(hashMap, "IMSUB", (FreeRefFunction) null);
        r(hashMap, "IMSUM", (FreeRefFunction) null);
        r(hashMap, "INTRATE", (FreeRefFunction) null);
        r(hashMap, "ISEVEN", ParityFunction.IS_EVEN);
        r(hashMap, "ISODD", ParityFunction.IS_ODD);
        r(hashMap, "JIS", (FreeRefFunction) null);
        r(hashMap, "LCM", (FreeRefFunction) null);
        r(hashMap, "MAXIFS", Maxifs.instance);
        r(hashMap, "MDURATION", (FreeRefFunction) null);
        r(hashMap, "MINIFS", Minifs.instance);
        r(hashMap, "MROUND", MRound.instance);
        r(hashMap, "MULTINOMIAL", (FreeRefFunction) null);
        r(hashMap, "NETWORKDAYS", NetworkdaysFunction.instance);
        r(hashMap, "NOMINAL", (FreeRefFunction) null);
        r(hashMap, "NORM.DIST", NormDist.instance);
        r(hashMap, "NORM.S.DIST", NormSDist.instance);
        r(hashMap, "NORM.INV", NormInv.instance);
        r(hashMap, "NORM.S.INV", NormSInv.instance);
        r(hashMap, "NUMBERVALUE", NumberValueFunction.instance);
        r(hashMap, "OCT2BIN", (FreeRefFunction) null);
        r(hashMap, "OCT2DEC", Oct2Dec.instance);
        r(hashMap, "OCT2HEX", (FreeRefFunction) null);
        r(hashMap, "ODDFPRICE", (FreeRefFunction) null);
        r(hashMap, "ODDFYIELD", (FreeRefFunction) null);
        r(hashMap, "ODDLPRICE", (FreeRefFunction) null);
        r(hashMap, "ODDLYIELD", (FreeRefFunction) null);
        r(hashMap, "PRICE", (FreeRefFunction) null);
        r(hashMap, "PERCENTRANK.EXC", PercentRankExcFunction.instance);
        r(hashMap, "PERCENTRANK.INC", PercentRankIncFunction.instance);
        r(hashMap, "PRICEDISC", (FreeRefFunction) null);
        r(hashMap, "PRICEMAT", (FreeRefFunction) null);
        r(hashMap, "QUOTIENT", Quotient.instance);
        r(hashMap, "RANDBETWEEN", RandBetween.instance);
        r(hashMap, "RECEIVED", (FreeRefFunction) null);
        r(hashMap, "RTD", (FreeRefFunction) null);
        r(hashMap, "SERIESSUM", (FreeRefFunction) null);
        r(hashMap, "SINGLE", Single.instance);
        r(hashMap, "SQRTPI", (FreeRefFunction) null);
        r(hashMap, "SUMIFS", Sumifs.instance);
        r(hashMap, "SWITCH", Switch.instance);
        r(hashMap, "TBILLEQ", (FreeRefFunction) null);
        r(hashMap, "TBILLPRICE", (FreeRefFunction) null);
        r(hashMap, "TBILLYIELD", (FreeRefFunction) null);
        r(hashMap, "T.DIST", TDistLt.instance);
        r(hashMap, "T.DIST.2T", TDist2t.instance);
        r(hashMap, "T.DIST.RT", TDistRt.instance);
        r(hashMap, "TEXTJOIN", TextJoinFunction.instance);
        r(hashMap, "WEEKNUM", WeekNum.instance);
        r(hashMap, "WORKDAY", WorkdayFunction.instance);
        r(hashMap, "WORKDAY.INTL", WorkdayIntlFunction.instance);
        r(hashMap, "XIRR", (FreeRefFunction) null);
        r(hashMap, "XLOOKUP", XLookupFunction.instance);
        r(hashMap, "XMATCH", XMatchFunction.instance);
        r(hashMap, "XNPV", (FreeRefFunction) null);
        r(hashMap, "YEARFRAC", YearFrac.instance);
        r(hashMap, "YIELD", (FreeRefFunction) null);
        r(hashMap, "YIELDDISC", (FreeRefFunction) null);
        r(hashMap, "YIELDMAT", (FreeRefFunction) null);
        return hashMap;
    }

    private static void r(Map<String, FreeRefFunction> map, String str, FreeRefFunction freeRefFunction) {
        if (freeRefFunction == null) {
            freeRefFunction = new NotImplemented(str);
        }
        map.put(str, freeRefFunction);
    }

    public static boolean isATPFunction(String str) {
        return ((AnalysisToolPak) instance)._functionsByName.containsKey(str);
    }

    public static Collection<String> getSupportedFunctionNames() {
        TreeSet treeSet = new TreeSet();
        for (Map.Entry next : ((AnalysisToolPak) instance)._functionsByName.entrySet()) {
            FreeRefFunction freeRefFunction = (FreeRefFunction) next.getValue();
            if (freeRefFunction != null && !(freeRefFunction instanceof NotImplemented)) {
                treeSet.add(next.getKey());
            }
        }
        return Collections.unmodifiableCollection(treeSet);
    }

    public static Collection<String> getNotSupportedFunctionNames() {
        TreeSet treeSet = new TreeSet();
        for (Map.Entry next : ((AnalysisToolPak) instance)._functionsByName.entrySet()) {
            if (((FreeRefFunction) next.getValue()) instanceof NotImplemented) {
                treeSet.add(next.getKey());
            }
        }
        return Collections.unmodifiableCollection(treeSet);
    }

    public static void registerFunction(String str, FreeRefFunction freeRefFunction) {
        AnalysisToolPak analysisToolPak = (AnalysisToolPak) instance;
        if (isATPFunction(str)) {
            FreeRefFunction findFunction = analysisToolPak.findFunction(str);
            if (findFunction == null || (findFunction instanceof NotImplemented)) {
                analysisToolPak._functionsByName.put(str, freeRefFunction);
                return;
            }
            throw new IllegalArgumentException("POI already implements " + str + ". You cannot override POI's implementations of Excel functions");
        } else if (FunctionMetadataRegistry.getFunctionByName(str) != null) {
            throw new IllegalArgumentException(str + " is a built-in Excel function. Use FunctionEval.registerFunction(String name, Function func) instead.");
        } else {
            throw new IllegalArgumentException(str + " is not a function from the Excel Analysis Toolpack.");
        }
    }
}
