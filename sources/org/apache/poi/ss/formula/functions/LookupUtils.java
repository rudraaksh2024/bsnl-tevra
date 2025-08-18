package org.apache.poi.ss.formula.functions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;
import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Countif;
import org.apache.poi.util.Internal;

@Internal
public final class LookupUtils {
    private static Map<Integer, MatchMode> matchModeMap = new HashMap();
    private static Map<Integer, SearchMode> searchModeMap = new HashMap();

    public interface LookupValueComparer {
        CompareResult compareTo(ValueEval valueEval);
    }

    public enum MatchMode {
        ExactMatch(0),
        ExactMatchFallbackToSmallerValue(-1),
        ExactMatchFallbackToLargerValue(1),
        WildcardMatch(2);
        
        private final int intValue;

        private MatchMode(int i) {
            this.intValue = i;
        }

        public int getIntValue() {
            return this.intValue;
        }
    }

    public enum SearchMode {
        IterateForward(1),
        IterateBackward(-1),
        BinarySearchForward(2),
        BinarySearchBackward(-2);
        
        private final int intValue;

        private SearchMode(int i) {
            this.intValue = i;
        }

        public int getIntValue() {
            return this.intValue;
        }
    }

    static {
        for (MatchMode matchMode : MatchMode.values()) {
            matchModeMap.put(Integer.valueOf(matchMode.getIntValue()), matchMode);
        }
        for (SearchMode searchMode : SearchMode.values()) {
            searchModeMap.put(Integer.valueOf(searchMode.getIntValue()), searchMode);
        }
    }

    public static MatchMode matchMode(int i) {
        MatchMode matchMode = matchModeMap.get(Integer.valueOf(i));
        if (matchMode != null) {
            return matchMode;
        }
        throw new IllegalArgumentException("unknown match mode " + i);
    }

    public static SearchMode searchMode(int i) {
        SearchMode searchMode = searchModeMap.get(Integer.valueOf(i));
        if (searchMode != null) {
            return searchMode;
        }
        throw new IllegalArgumentException("unknown search mode " + i);
    }

    public interface ValueVector {
        ValueEval getItem(int i);

        int getSize();

        Iterator<Integer> indexIterator() {
            return new Iterator<Integer>() {
                private int pos = 0;

                public boolean hasNext() {
                    return this.pos < ValueVector.this.getSize();
                }

                public Integer next() {
                    if (hasNext()) {
                        int i = this.pos;
                        this.pos = i + 1;
                        return Integer.valueOf(i);
                    }
                    throw new NoSuchElementException();
                }
            };
        }

        Iterator<Integer> reverseIndexIterator() {
            return new Iterator<Integer>() {
                private int pos;

                {
                    this.pos = ValueVector.this.getSize() - 1;
                }

                public boolean hasNext() {
                    return this.pos > 0;
                }

                public Integer next() {
                    int i = this.pos - 1;
                    this.pos = i;
                    if (i >= 0) {
                        return Integer.valueOf(i);
                    }
                    throw new NoSuchElementException();
                }
            };
        }
    }

    private static final class RowVector implements ValueVector {
        private final int _rowIndex;
        private final int _size;
        private final TwoDEval _tableArray;

        public RowVector(TwoDEval twoDEval, int i) {
            this._rowIndex = i;
            int height = twoDEval.getHeight() - 1;
            if (i < 0 || i > height) {
                throw new IllegalArgumentException("Specified row index (" + i + ") is outside the allowed range (0.." + height + ")");
            }
            this._tableArray = twoDEval;
            this._size = twoDEval.getWidth();
        }

        public ValueEval getItem(int i) {
            if (i <= this._size) {
                return this._tableArray.getValue(this._rowIndex, i);
            }
            throw new ArrayIndexOutOfBoundsException("Specified index (" + i + ") is outside the allowed range (0.." + (this._size - 1) + ")");
        }

        public int getSize() {
            return this._size;
        }
    }

    private static final class ColumnVector implements ValueVector {
        private final int _columnIndex;
        private final int _size;
        private final TwoDEval _tableArray;

        public ColumnVector(TwoDEval twoDEval, int i) {
            this._columnIndex = i;
            int width = twoDEval.getWidth() - 1;
            if (i < 0 || i > width) {
                throw new IllegalArgumentException("Specified column index (" + i + ") is outside the allowed range (0.." + width + ")");
            }
            this._tableArray = twoDEval;
            this._size = twoDEval.getHeight();
        }

        public ValueEval getItem(int i) {
            if (i <= this._size) {
                return this._tableArray.getValue(i, this._columnIndex);
            }
            throw new ArrayIndexOutOfBoundsException("Specified index (" + i + ") is outside the allowed range (0.." + (this._size - 1) + ")");
        }

        public int getSize() {
            return this._size;
        }
    }

    private static final class SheetVector implements ValueVector {
        private final RefEval _re;
        private final int _size;

        public SheetVector(RefEval refEval) {
            this._size = refEval.getNumberOfSheets();
            this._re = refEval;
        }

        public ValueEval getItem(int i) {
            if (i < this._size) {
                return this._re.getInnerValueEval(this._re.getFirstSheetIndex() + i);
            }
            throw new ArrayIndexOutOfBoundsException("Specified index (" + i + ") is outside the allowed range (0.." + (this._size - 1) + ")");
        }

        public int getSize() {
            return this._size;
        }
    }

    public static ValueVector createRowVector(TwoDEval twoDEval, int i) {
        return new RowVector(twoDEval, i);
    }

    public static ValueVector createColumnVector(TwoDEval twoDEval, int i) {
        return new ColumnVector(twoDEval, i);
    }

    public static ValueVector createVector(TwoDEval twoDEval) {
        if (twoDEval.isColumn()) {
            return createColumnVector(twoDEval, 0);
        }
        if (twoDEval.isRow()) {
            return createRowVector(twoDEval, 0);
        }
        return null;
    }

    public static ValueVector createVector(RefEval refEval) {
        return new SheetVector(refEval);
    }

    public static final class CompareResult {
        public static final CompareResult EQUAL = new CompareResult(false, 0);
        public static final CompareResult GREATER_THAN = new CompareResult(false, 1);
        public static final CompareResult LESS_THAN = new CompareResult(false, -1);
        public static final CompareResult TYPE_MISMATCH = new CompareResult(true, 0);
        private final boolean _isEqual;
        private final boolean _isGreaterThan;
        private final boolean _isLessThan;
        private final boolean _isTypeMismatch;

        private CompareResult(boolean z, int i) {
            boolean z2 = true;
            if (z) {
                this._isTypeMismatch = true;
                this._isLessThan = false;
                this._isEqual = false;
                this._isGreaterThan = false;
                return;
            }
            this._isTypeMismatch = false;
            this._isLessThan = i < 0;
            this._isEqual = i == 0;
            this._isGreaterThan = i <= 0 ? false : z2;
        }

        public static CompareResult valueOf(int i) {
            if (i < 0) {
                return LESS_THAN;
            }
            if (i > 0) {
                return GREATER_THAN;
            }
            return EQUAL;
        }

        public static CompareResult valueOf(boolean z) {
            if (z) {
                return EQUAL;
            }
            return LESS_THAN;
        }

        public boolean isTypeMismatch() {
            return this._isTypeMismatch;
        }

        public boolean isLessThan() {
            return this._isLessThan;
        }

        public boolean isEqual() {
            return this._isEqual;
        }

        public boolean isGreaterThan() {
            return this._isGreaterThan;
        }

        public String toString() {
            return getClass().getName() + " [" + formatAsString() + "]";
        }

        private String formatAsString() {
            if (this._isTypeMismatch) {
                return "TYPE_MISMATCH";
            }
            if (this._isLessThan) {
                return "LESS_THAN";
            }
            if (this._isEqual) {
                return "EQUAL";
            }
            return this._isGreaterThan ? "GREATER_THAN" : "??error??";
        }
    }

    private static abstract class LookupValueComparerBase implements LookupValueComparer {
        private final Class<? extends ValueEval> _targetClass;

        /* access modifiers changed from: protected */
        public abstract CompareResult compareSameType(ValueEval valueEval);

        /* access modifiers changed from: protected */
        public abstract String getValueAsString();

        protected LookupValueComparerBase(ValueEval valueEval) {
            if (valueEval != null) {
                this._targetClass = valueEval.getClass();
                return;
            }
            throw new RuntimeException("targetValue cannot be null");
        }

        public final CompareResult compareTo(ValueEval valueEval) {
            if (valueEval == null) {
                throw new RuntimeException("compare to value cannot be null");
            } else if (this._targetClass != valueEval.getClass()) {
                return CompareResult.TYPE_MISMATCH;
            } else {
                return compareSameType(valueEval);
            }
        }

        public String toString() {
            return getClass().getName() + " [" + getValueAsString() + "]";
        }
    }

    private static class StringLookupComparer extends LookupValueComparerBase {
        protected final boolean _isMatchFunction;
        protected final boolean _matchExact;
        protected final String _value;
        protected final Pattern _wildCardPattern;

        protected StringLookupComparer(StringEval stringEval, boolean z, boolean z2) {
            super(stringEval);
            String stringValue = stringEval.getStringValue();
            this._value = stringValue;
            this._wildCardPattern = Countif.StringMatcher.getWildCardPattern(stringValue);
            this._matchExact = z;
            this._isMatchFunction = z2;
        }

        /* access modifiers changed from: protected */
        public String convertToString(ValueEval valueEval) {
            return ((StringEval) valueEval).getStringValue();
        }

        /* access modifiers changed from: protected */
        public CompareResult compareSameType(ValueEval valueEval) {
            String convertToString = convertToString(valueEval);
            Pattern pattern = this._wildCardPattern;
            if (pattern == null || (!this._isMatchFunction && this._matchExact)) {
                return CompareResult.valueOf(this._value.compareToIgnoreCase(convertToString));
            }
            return CompareResult.valueOf(pattern.matcher(convertToString).matches());
        }

        /* access modifiers changed from: protected */
        public String getValueAsString() {
            return this._value;
        }
    }

    private static final class TolerantStringLookupComparer extends StringLookupComparer {
        static StringEval convertToStringEval(ValueEval valueEval) {
            if (valueEval instanceof StringEval) {
                return (StringEval) valueEval;
            }
            return new StringEval(OperandResolver.coerceValueToString(valueEval));
        }

        protected TolerantStringLookupComparer(ValueEval valueEval, boolean z, boolean z2) {
            super(convertToStringEval(valueEval), z, z2);
        }

        /* access modifiers changed from: protected */
        public String convertToString(ValueEval valueEval) {
            return OperandResolver.coerceValueToString(valueEval);
        }
    }

    private static final class NumberLookupComparer extends LookupValueComparerBase {
        private final double _value;

        protected NumberLookupComparer(NumberEval numberEval) {
            super(numberEval);
            this._value = numberEval.getNumberValue();
        }

        /* access modifiers changed from: protected */
        public CompareResult compareSameType(ValueEval valueEval) {
            return CompareResult.valueOf(Double.compare(this._value, ((NumberEval) valueEval).getNumberValue()));
        }

        /* access modifiers changed from: protected */
        public String getValueAsString() {
            return String.valueOf(this._value);
        }
    }

    private static final class BooleanLookupComparer extends LookupValueComparerBase {
        private final boolean _value;

        protected BooleanLookupComparer(BoolEval boolEval) {
            super(boolEval);
            this._value = boolEval.getBooleanValue();
        }

        /* access modifiers changed from: protected */
        public CompareResult compareSameType(ValueEval valueEval) {
            boolean booleanValue = ((BoolEval) valueEval).getBooleanValue();
            boolean z = this._value;
            if (z == booleanValue) {
                return CompareResult.EQUAL;
            }
            if (z) {
                return CompareResult.GREATER_THAN;
            }
            return CompareResult.LESS_THAN;
        }

        /* access modifiers changed from: protected */
        public String getValueAsString() {
            return String.valueOf(this._value);
        }
    }

    public static int resolveRowOrColIndexArg(ValueEval valueEval, int i, int i2) throws EvaluationException {
        if (valueEval != null) {
            try {
                ValueEval singleValue = OperandResolver.getSingleValue(valueEval, i, (short) i2);
                if (!(singleValue instanceof StringEval) || OperandResolver.parseDouble(((StringEval) singleValue).getStringValue()) != null) {
                    int coerceValueToInt = OperandResolver.coerceValueToInt(singleValue);
                    if (coerceValueToInt >= 1) {
                        return coerceValueToInt - 1;
                    }
                    throw EvaluationException.invalidValue();
                }
                throw EvaluationException.invalidRef();
            } catch (EvaluationException unused) {
                throw EvaluationException.invalidRef();
            }
        } else {
            throw new IllegalArgumentException("argument must not be null");
        }
    }

    public static TwoDEval resolveTableArrayArg(ValueEval valueEval) throws EvaluationException {
        if (valueEval instanceof TwoDEval) {
            return (TwoDEval) valueEval;
        }
        if (valueEval instanceof RefEval) {
            return ((RefEval) valueEval).offset(0, 0, 0, 0);
        }
        throw EvaluationException.invalidValue();
    }

    public static boolean resolveRangeLookupArg(ValueEval valueEval, int i, int i2) throws EvaluationException {
        ValueEval singleValue = OperandResolver.getSingleValue(valueEval, i, i2);
        if (singleValue == MissingArgEval.instance || (singleValue instanceof BlankEval)) {
            return false;
        }
        if (singleValue instanceof BoolEval) {
            return ((BoolEval) singleValue).getBooleanValue();
        }
        if (singleValue instanceof StringEval) {
            String stringValue = ((StringEval) singleValue).getStringValue();
            if (stringValue.length() >= 1) {
                Boolean parseBoolean = Countif.parseBoolean(stringValue);
                if (parseBoolean != null) {
                    return parseBoolean.booleanValue();
                }
                throw EvaluationException.invalidValue();
            }
            throw EvaluationException.invalidValue();
        } else if (!(singleValue instanceof NumericValueEval)) {
            throw new RuntimeException("Unexpected eval type (" + singleValue + ")");
        } else if (0.0d != ((NumericValueEval) singleValue).getNumberValue()) {
            return true;
        } else {
            return false;
        }
    }

    public static int lookupFirstIndexOfValue(ValueEval valueEval, ValueVector valueVector, boolean z) throws EvaluationException {
        int i;
        LookupValueComparer createLookupComparer = createLookupComparer(valueEval, z, false);
        if (z) {
            i = performBinarySearch(valueVector, createLookupComparer);
        } else {
            i = lookupFirstIndexOfValue(createLookupComparer, valueVector, MatchMode.ExactMatch);
        }
        if (i >= 0) {
            return i;
        }
        throw new EvaluationException(ErrorEval.NA);
    }

    public static int xlookupIndexOfValue(ValueEval valueEval, ValueVector valueVector, MatchMode matchMode, SearchMode searchMode) throws EvaluationException {
        int i;
        if ((valueEval instanceof StringEval) && (matchMode == MatchMode.ExactMatchFallbackToLargerValue || matchMode == MatchMode.ExactMatchFallbackToSmallerValue)) {
            String stringValue = ((StringEval) valueEval).getStringValue();
            StringBuilder sb = new StringBuilder(stringValue.length());
            boolean z = false;
            for (char c : stringValue.toCharArray()) {
                if (c == '*' || c == '?' || c == '~') {
                    z = true;
                } else {
                    sb.append(c);
                }
                if (z) {
                    break;
                }
            }
            if (z) {
                valueEval = new StringEval(sb.toString());
            }
        }
        LookupValueComparer createTolerantLookupComparer = createTolerantLookupComparer(valueEval, matchMode != MatchMode.WildcardMatch, true);
        if (searchMode == SearchMode.BinarySearchForward) {
            i = binarySearchIndexOfValue(createTolerantLookupComparer, valueVector, matchMode, false);
        } else if (searchMode == SearchMode.BinarySearchBackward) {
            i = binarySearchIndexOfValue(createTolerantLookupComparer, valueVector, matchMode, true);
        } else if (searchMode == SearchMode.IterateBackward) {
            i = lookupLastIndexOfValue(createTolerantLookupComparer, valueVector, matchMode);
        } else {
            i = lookupFirstIndexOfValue(createTolerantLookupComparer, valueVector, matchMode);
        }
        if (i >= 0) {
            return i;
        }
        throw new EvaluationException(ErrorEval.NA);
    }

    private static int lookupFirstIndexOfValue(LookupValueComparer lookupValueComparer, ValueVector valueVector, MatchMode matchMode) {
        return lookupIndexOfValue(lookupValueComparer, valueVector, matchMode, false);
    }

    private static int lookupLastIndexOfValue(LookupValueComparer lookupValueComparer, ValueVector valueVector, MatchMode matchMode) {
        return lookupIndexOfValue(lookupValueComparer, valueVector, matchMode, true);
    }

    private static int lookupIndexOfValue(LookupValueComparer lookupValueComparer, ValueVector valueVector, MatchMode matchMode, boolean z) {
        Iterator<Integer> reverseIndexIterator = z ? valueVector.reverseIndexIterator() : valueVector.indexIterator();
        int i = -1;
        ValueEval valueEval = null;
        while (reverseIndexIterator.hasNext()) {
            int intValue = reverseIndexIterator.next().intValue();
            ValueEval item = valueVector.getItem(intValue);
            CompareResult compareTo = lookupValueComparer.compareTo(item);
            if (compareTo.isEqual()) {
                return intValue;
            }
            int i2 = AnonymousClass1.$SwitchMap$org$apache$poi$ss$formula$functions$LookupUtils$MatchMode[matchMode.ordinal()];
            if (i2 != 1) {
                if (i2 == 2 && compareTo.isGreaterThan()) {
                    if (valueEval != null && !createTolerantLookupComparer(item, true, true).compareTo(valueEval).isGreaterThan()) {
                    }
                }
            } else if (compareTo.isLessThan()) {
                if (valueEval != null && !createTolerantLookupComparer(item, true, true).compareTo(valueEval).isLessThan()) {
                }
            }
            i = intValue;
            valueEval = item;
        }
        return i;
    }

    /* renamed from: org.apache.poi.ss.formula.functions.LookupUtils$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$formula$functions$LookupUtils$MatchMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                org.apache.poi.ss.formula.functions.LookupUtils$MatchMode[] r0 = org.apache.poi.ss.formula.functions.LookupUtils.MatchMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ss$formula$functions$LookupUtils$MatchMode = r0
                org.apache.poi.ss.formula.functions.LookupUtils$MatchMode r1 = org.apache.poi.ss.formula.functions.LookupUtils.MatchMode.ExactMatchFallbackToLargerValue     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ss$formula$functions$LookupUtils$MatchMode     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.formula.functions.LookupUtils$MatchMode r1 = org.apache.poi.ss.formula.functions.LookupUtils.MatchMode.ExactMatchFallbackToSmallerValue     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.functions.LookupUtils.AnonymousClass1.<clinit>():void");
        }
    }

    private static int binarySearchIndexOfValue(LookupValueComparer lookupValueComparer, ValueVector valueVector, MatchMode matchMode, boolean z) {
        HashSet hashSet = new HashSet();
        BinarySearchIndexes binarySearchIndexes = new BinarySearchIndexes(valueVector.getSize());
        int i = -1;
        ValueEval valueEval = null;
        while (true) {
            int midIx = binarySearchIndexes.getMidIx();
            if (midIx < 0 || hashSet.contains(Integer.valueOf(midIx))) {
                return i;
            }
            hashSet.add(Integer.valueOf(midIx));
            ValueEval item = valueVector.getItem(midIx);
            CompareResult compareTo = lookupValueComparer.compareTo(item);
            if (compareTo.isEqual()) {
                return midIx;
            }
            int i2 = AnonymousClass1.$SwitchMap$org$apache$poi$ss$formula$functions$LookupUtils$MatchMode[matchMode.ordinal()];
            if (i2 == 1 ? !(!compareTo.isLessThan() || (valueEval != null && !createTolerantLookupComparer(item, true, true).compareTo(valueEval).isLessThan())) : !(i2 != 2 || !compareTo.isGreaterThan() || (valueEval != null && !createTolerantLookupComparer(item, true, true).compareTo(valueEval).isGreaterThan()))) {
                i = midIx;
                valueEval = item;
            }
            if (compareTo.isTypeMismatch()) {
                int handleMidValueTypeMismatch = handleMidValueTypeMismatch(lookupValueComparer, valueVector, binarySearchIndexes, midIx, z);
                if (handleMidValueTypeMismatch >= 0) {
                    return handleMidValueTypeMismatch;
                }
            } else if (z) {
                binarySearchIndexes.narrowSearch(midIx, compareTo.isGreaterThan());
            } else {
                binarySearchIndexes.narrowSearch(midIx, compareTo.isLessThan());
            }
        }
        return i;
    }

    private static final class BinarySearchIndexes {
        private int _highIx;
        private int _lowIx = -1;

        public BinarySearchIndexes(int i) {
            this._highIx = i;
        }

        public int getMidIx() {
            int i = this._highIx;
            int i2 = this._lowIx;
            int i3 = i - i2;
            if (i3 < 2) {
                return -1;
            }
            return i2 + (i3 / 2);
        }

        public int getLowIx() {
            return this._lowIx;
        }

        public int getHighIx() {
            return this._highIx;
        }

        public void narrowSearch(int i, boolean z) {
            if (z) {
                this._highIx = i;
            } else {
                this._lowIx = i;
            }
        }
    }

    private static int performBinarySearch(ValueVector valueVector, LookupValueComparer lookupValueComparer) {
        BinarySearchIndexes binarySearchIndexes = new BinarySearchIndexes(valueVector.getSize());
        while (true) {
            int midIx = binarySearchIndexes.getMidIx();
            if (midIx < 0) {
                return binarySearchIndexes.getLowIx();
            }
            CompareResult compareTo = lookupValueComparer.compareTo(valueVector.getItem(midIx));
            if (compareTo.isTypeMismatch()) {
                midIx = handleMidValueTypeMismatch(lookupValueComparer, valueVector, binarySearchIndexes, midIx, false);
                if (midIx < 0) {
                    continue;
                } else {
                    compareTo = lookupValueComparer.compareTo(valueVector.getItem(midIx));
                }
            }
            if (compareTo.isEqual()) {
                return findLastIndexInRunOfEqualValues(lookupValueComparer, valueVector, midIx, binarySearchIndexes.getHighIx());
            }
            binarySearchIndexes.narrowSearch(midIx, compareTo.isLessThan());
        }
    }

    private static int handleMidValueTypeMismatch(LookupValueComparer lookupValueComparer, ValueVector valueVector, BinarySearchIndexes binarySearchIndexes, int i, boolean z) {
        CompareResult compareTo;
        int highIx = binarySearchIndexes.getHighIx();
        int i2 = i;
        do {
            i2++;
            if (i2 == highIx) {
                binarySearchIndexes.narrowSearch(i, true);
                return -1;
            }
            compareTo = lookupValueComparer.compareTo(valueVector.getItem(i2));
            if (compareTo.isLessThan() && !z && i2 == highIx - 1) {
                binarySearchIndexes.narrowSearch(i, true);
                return -1;
            } else if (compareTo.isGreaterThan() && z && i2 == highIx - 1) {
                binarySearchIndexes.narrowSearch(i, true);
                return -1;
            }
        } while (compareTo.isTypeMismatch());
        if (compareTo.isEqual()) {
            return i2;
        }
        if (z) {
            binarySearchIndexes.narrowSearch(i2, compareTo.isGreaterThan());
        } else {
            binarySearchIndexes.narrowSearch(i2, compareTo.isLessThan());
        }
        return -1;
    }

    private static int findLastIndexInRunOfEqualValues(LookupValueComparer lookupValueComparer, ValueVector valueVector, int i, int i2) {
        do {
            i++;
            if (i >= i2) {
                return i2 - 1;
            }
        } while (lookupValueComparer.compareTo(valueVector.getItem(i)).isEqual());
        return i - 1;
    }

    static LookupValueComparer createLookupComparer(ValueEval valueEval, boolean z, boolean z2) {
        if (valueEval == BlankEval.instance) {
            return new NumberLookupComparer(NumberEval.ZERO);
        }
        if (valueEval instanceof StringEval) {
            return new StringLookupComparer((StringEval) valueEval, z, z2);
        }
        if (valueEval instanceof NumberEval) {
            return new NumberLookupComparer((NumberEval) valueEval);
        }
        if (valueEval instanceof BoolEval) {
            return new BooleanLookupComparer((BoolEval) valueEval);
        }
        throw new IllegalArgumentException("Bad lookup value type (" + valueEval.getClass().getName() + ")");
    }

    private static LookupValueComparer createTolerantLookupComparer(ValueEval valueEval, boolean z, boolean z2) {
        if (valueEval == BlankEval.instance) {
            return new TolerantStringLookupComparer(new StringEval(""), z, z2);
        }
        if (valueEval instanceof BoolEval) {
            return new BooleanLookupComparer((BoolEval) valueEval);
        }
        if (!z || !(valueEval instanceof NumberEval)) {
            return new TolerantStringLookupComparer(valueEval, z, z2);
        }
        return new NumberLookupComparer((NumberEval) valueEval);
    }
}
