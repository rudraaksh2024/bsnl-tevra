package com.graphbuilder.curve;

import com.graphbuilder.math.Expression;
import com.graphbuilder.math.ExpressionParseException;
import com.graphbuilder.math.ExpressionTree;
import com.graphbuilder.math.FuncMap;
import com.graphbuilder.math.VarMap;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.logging.log4j.util.Chars;

public class GroupIterator {
    protected String controlString = null;
    protected int count_j = 0;
    protected int[] group = null;
    protected int index_i = 0;

    public GroupIterator(String str, int i) {
        if (str != null) {
            this.group = parseControlString(str, i);
            this.controlString = str;
            return;
        }
        throw new IllegalArgumentException("control string cannot be null");
    }

    public GroupIterator(int[] iArr) {
        if (iArr == null) {
            throw new IllegalArgumentException("group array cannot be null");
        } else if (iArr.length != 0) {
            if (iArr.length % 2 == 0) {
                double log = Math.log(10.0d);
                int length = iArr.length;
                int[] iArr2 = new int[length];
                int i = 0;
                for (int i2 = 0; i2 < length; i2++) {
                    int i3 = iArr[i2];
                    iArr2[i2] = i3;
                    if (i3 < 0) {
                        i++;
                        i3 = -i3;
                    }
                    i += ((int) (Math.log((double) i3) / log)) + 1;
                }
                this.group = iArr2;
                StringBuffer stringBuffer = new StringBuffer(i + (length / 2) + (length - 1));
                stringBuffer.append(iArr2[0]);
                stringBuffer.append(ParameterizedMessage.ERROR_MSG_SEPARATOR);
                stringBuffer.append(iArr2[1]);
                for (int i4 = 2; i4 < iArr.length; i4 += 2) {
                    stringBuffer.append(",");
                    stringBuffer.append(iArr[i4]);
                    stringBuffer.append(ParameterizedMessage.ERROR_MSG_SEPARATOR);
                    stringBuffer.append(iArr[i4 + 1]);
                }
                this.controlString = stringBuffer.toString();
                return;
            }
            throw new IllegalArgumentException("group array must have even length");
        } else {
            throw new IllegalArgumentException("group array length cannot be 0");
        }
    }

    public static int[] parseControlString(String str, int i) {
        char c;
        char c2;
        char c3;
        int i2;
        String str2 = str;
        int i3 = i;
        int length = str.length();
        int i4 = 1;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            c = ')';
            c2 = '(';
            c3 = ',';
            if (i6 >= length) {
                break;
            }
            char charAt = str2.charAt(i6);
            if (charAt == ',' && i7 == 0) {
                i4++;
            } else if (charAt == '(') {
                i7++;
            } else if (charAt == ')') {
                i7--;
            }
            i6++;
        }
        if (i7 == 0) {
            int[] iArr = new int[(i4 * 2)];
            VarMap varMap = new VarMap();
            FuncMap funcMap = new FuncMap();
            funcMap.loadDefaultFunctions();
            int i8 = 0;
            int i9 = i7;
            int i10 = -1;
            int i11 = 0;
            while (i5 <= length) {
                char charAt2 = i5 < length ? str2.charAt(i5) : Chars.SPACE;
                if (i5 == length || (charAt2 == c3 && i9 == 0)) {
                    if (i10 == -1) {
                        int round = (int) Math.round(setVariables(str2, varMap, i3, i11, i5).eval(varMap, funcMap));
                        iArr[i8] = round;
                        iArr[i8 + 1] = round;
                        i8 += 2;
                    } else {
                        int i12 = i8 + 1;
                        iArr[i8] = (int) Math.round(setVariables(str2, varMap, i3, i11, i10).eval(varMap, funcMap));
                        i8 = i12 + 1;
                        iArr[i12] = (int) Math.round(setVariables(str2, varMap, i3, i10 + 1, i5).eval(varMap, funcMap));
                    }
                    i2 = i5 + 1;
                    i10 = -1;
                } else {
                    if (charAt2 == c2) {
                        i9++;
                    } else if (charAt2 == c) {
                        i9--;
                    } else if (charAt2 == ':') {
                        i10 = i5;
                    }
                    i2 = i11;
                }
                i5++;
                i11 = i2;
                c = ')';
                c2 = '(';
                c3 = ',';
            }
            return iArr;
        }
        throw new ControlStringParseException("round brackets do not balance");
    }

    private static Expression setVariables(String str, VarMap varMap, int i, int i2, int i3) {
        try {
            Expression parse = ExpressionTree.parse(str.substring(i2, i3));
            if (parse != null) {
                String[] variableNames = parse.getVariableNames();
                if (variableNames.length <= 1) {
                    if (variableNames.length == 1) {
                        varMap.setValue(variableNames[0], (double) i);
                    }
                    return parse;
                }
                throw new ControlStringParseException("too many variables", i2, i3);
            }
            throw new ControlStringParseException("control substring is empty", i2, i3);
        } catch (ExpressionParseException e) {
            throw new ControlStringParseException("error parsing expression", i2, i3, e);
        }
    }

    public String getControlString() {
        return this.controlString;
    }

    public int getGroupLength() {
        return this.group.length;
    }

    public int getGroupValue(int i) {
        if (i >= 0) {
            int[] iArr = this.group;
            if (i < iArr.length) {
                return iArr[i];
            }
        }
        throw new IllegalArgumentException("required: (index >= 0 && index < group.length) but: (index = " + i + ", group.length = " + this.group.length + ")");
    }

    public int getGroupSize() {
        int i = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = this.group;
            if (i >= iArr.length) {
                return i2;
            }
            int i3 = iArr[i] - iArr[i + 1];
            if (i3 < 0) {
                i3 = -i3;
            }
            i2 += i3 + 1;
            i += 2;
        }
    }

    public void copyGroupArray(int[] iArr) {
        if (iArr == null) {
            throw new IllegalArgumentException("specified array cannot be null");
        } else if (iArr.length >= this.group.length) {
            int i = 0;
            while (true) {
                int[] iArr2 = this.group;
                if (i < iArr2.length) {
                    iArr[i] = iArr2[i];
                    i++;
                } else {
                    return;
                }
            }
        } else {
            throw new IllegalArgumentException("specified array is too small");
        }
    }

    public boolean hasNext() {
        return this.index_i < this.group.length;
    }

    public int next() {
        int i;
        int[] iArr = this.group;
        int i2 = this.index_i;
        int i3 = iArr[i2];
        int i4 = iArr[i2 + 1];
        if (i3 <= i4) {
            int i5 = this.count_j;
            i = i3 + i5;
            if (i >= i4) {
                this.count_j = 0;
                this.index_i = i2 + 2;
            } else {
                this.count_j = i5 + 1;
            }
        } else {
            int i6 = this.count_j;
            i = i3 - i6;
            if (i <= i4) {
                this.count_j = 0;
                this.index_i = i2 + 2;
            } else {
                this.count_j = i6 + 1;
            }
        }
        return i;
    }

    public void set(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("index_i >= 0 required");
        } else if (i % 2 == 1) {
            throw new IllegalArgumentException("index_i must be an even number");
        } else if (i2 >= 0) {
            this.index_i = i;
            this.count_j = i2;
        } else {
            throw new IllegalArgumentException("count_j >= 0 required");
        }
    }

    public int index_i() {
        return this.index_i;
    }

    public int count_j() {
        return this.count_j;
    }

    public void reset() {
        this.index_i = 0;
        this.count_j = 0;
    }

    public boolean isInRange(int i, int i2) {
        int i3 = 0;
        while (true) {
            int[] iArr = this.group;
            if (i3 >= iArr.length) {
                return true;
            }
            int i4 = iArr[i3];
            if (i4 < i || i4 >= i2) {
                return false;
            }
            i3++;
        }
        return false;
    }
}
