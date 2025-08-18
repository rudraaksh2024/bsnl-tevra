package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.ptg.AbstractFunctionPtg;
import org.apache.poi.ss.formula.ptg.AttrPtg;
import org.apache.poi.ss.formula.ptg.ControlPtg;
import org.apache.poi.ss.formula.ptg.FuncVarPtg;
import org.apache.poi.ss.formula.ptg.IntersectionPtg;
import org.apache.poi.ss.formula.ptg.MemAreaPtg;
import org.apache.poi.ss.formula.ptg.MemFuncPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.RangePtg;
import org.apache.poi.ss.formula.ptg.UnionPtg;
import org.apache.poi.ss.formula.ptg.ValueOperatorPtg;

final class OperandClassTransformer {
    private final FormulaType _formulaType;

    public OperandClassTransformer(FormulaType formulaType) {
        this._formulaType = formulaType;
    }

    /* renamed from: org.apache.poi.ss.formula.OperandClassTransformer$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$formula$FormulaType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                org.apache.poi.ss.formula.FormulaType[] r0 = org.apache.poi.ss.formula.FormulaType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ss$formula$FormulaType = r0
                org.apache.poi.ss.formula.FormulaType r1 = org.apache.poi.ss.formula.FormulaType.CELL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ss$formula$FormulaType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.formula.FormulaType r1 = org.apache.poi.ss.formula.FormulaType.ARRAY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$formula$FormulaType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ss.formula.FormulaType r1 = org.apache.poi.ss.formula.FormulaType.NAMEDRANGE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$ss$formula$FormulaType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.ss.formula.FormulaType r1 = org.apache.poi.ss.formula.FormulaType.DATAVALIDATION_LIST     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.OperandClassTransformer.AnonymousClass1.<clinit>():void");
        }
    }

    public void transformFormula(ParseNode parseNode) {
        byte b;
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$formula$FormulaType[this._formulaType.ordinal()];
        if (i == 1) {
            b = 32;
        } else if (i == 2) {
            b = Ptg.CLASS_ARRAY;
        } else if (i == 3 || i == 4) {
            b = 0;
        } else {
            throw new RuntimeException("Incomplete code - formula type (" + this._formulaType + ") not supported yet");
        }
        transformNode(parseNode, b, false);
    }

    private void transformNode(ParseNode parseNode, byte b, boolean z) {
        Ptg token = parseNode.getToken();
        ParseNode[] children = parseNode.getChildren();
        int i = 0;
        if (isSimpleValueFunction(token)) {
            boolean z2 = b == 64;
            int length = children.length;
            while (i < length) {
                transformNode(children[i], b, z2);
                i++;
            }
            setSimpleValueFuncClass((AbstractFunctionPtg) token, b, z);
            return;
        }
        if (isSingleArgSum(token)) {
            token = FuncVarPtg.SUM;
        }
        if ((token instanceof ValueOperatorPtg) || (token instanceof ControlPtg) || (token instanceof MemFuncPtg) || (token instanceof MemAreaPtg) || (token instanceof UnionPtg) || (token instanceof IntersectionPtg)) {
            if (b == 0) {
                b = 32;
            }
            int length2 = children.length;
            while (i < length2) {
                transformNode(children[i], b, z);
                i++;
            }
        } else if (token instanceof AbstractFunctionPtg) {
            transformFunctionNode((AbstractFunctionPtg) token, children, b, z);
        } else if (children.length > 0) {
            if (token != RangePtg.instance) {
                throw new IllegalStateException("Node should not have any children");
            }
        } else if (!token.isBaseToken()) {
            token.setClass(transformClass(token.getPtgClass(), b, z));
        }
    }

    private static boolean isSingleArgSum(Ptg ptg) {
        if (ptg instanceof AttrPtg) {
            return ((AttrPtg) ptg).isSum();
        }
        return false;
    }

    private static boolean isSimpleValueFunction(Ptg ptg) {
        if (!(ptg instanceof AbstractFunctionPtg)) {
            return false;
        }
        AbstractFunctionPtg abstractFunctionPtg = (AbstractFunctionPtg) ptg;
        if (abstractFunctionPtg.getDefaultOperandClass() != 32) {
            return false;
        }
        for (int numberOfOperands = abstractFunctionPtg.getNumberOfOperands() - 1; numberOfOperands >= 0; numberOfOperands--) {
            if (abstractFunctionPtg.getParameterClass(numberOfOperands) != 32) {
                return false;
            }
        }
        return true;
    }

    private byte transformClass(byte b, byte b2, boolean z) {
        if (b2 != 0) {
            if (b2 != 32) {
                if (b2 != 64) {
                    throw new IllegalStateException("Unexpected operand class (" + b2 + ")");
                }
            } else if (!z) {
                return 32;
            }
            return Ptg.CLASS_ARRAY;
        } else if (!z) {
            return b;
        } else {
            return 0;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006e, code lost:
        if (r0 == 32) goto L_0x00b1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b4 A[LOOP:0: B:34:0x00b1->B:36:0x00b4, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void transformFunctionNode(org.apache.poi.ss.formula.ptg.AbstractFunctionPtg r8, org.apache.poi.ss.formula.ParseNode[] r9, byte r10, boolean r11) {
        /*
            r7 = this;
            byte r0 = r8.getDefaultOperandClass()
            r1 = 1
            java.lang.String r2 = ")"
            java.lang.String r3 = "Unexpected operand class ("
            r4 = 32
            r5 = 64
            r6 = 0
            if (r11 == 0) goto L_0x0040
            if (r0 == 0) goto L_0x0036
            if (r0 == r4) goto L_0x0031
            if (r0 != r5) goto L_0x001a
            r8.setClass(r5)
            goto L_0x0071
        L_0x001a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>(r3)
            java.lang.StringBuilder r8 = r8.append(r0)
            java.lang.StringBuilder r8 = r8.append(r2)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L_0x0031:
            r8.setClass(r5)
            goto L_0x00b1
        L_0x0036:
            if (r10 != 0) goto L_0x003c
            r8.setClass(r6)
            goto L_0x0071
        L_0x003c:
            r8.setClass(r5)
            goto L_0x0071
        L_0x0040:
            if (r0 != r10) goto L_0x0046
            r8.setClass(r0)
            goto L_0x0071
        L_0x0046:
            if (r10 == 0) goto L_0x008e
            if (r10 == r4) goto L_0x008a
            if (r10 != r5) goto L_0x0073
            if (r0 == 0) goto L_0x006b
            if (r0 != r4) goto L_0x0054
            r8.setClass(r5)
            goto L_0x006e
        L_0x0054:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>(r3)
            java.lang.StringBuilder r8 = r8.append(r0)
            java.lang.StringBuilder r8 = r8.append(r2)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L_0x006b:
            r8.setClass(r6)
        L_0x006e:
            if (r0 != r4) goto L_0x0071
            goto L_0x00b1
        L_0x0071:
            r1 = r6
            goto L_0x00b1
        L_0x0073:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>(r3)
            java.lang.StringBuilder r8 = r8.append(r10)
            java.lang.StringBuilder r8 = r8.append(r2)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L_0x008a:
            r8.setClass(r4)
            goto L_0x0071
        L_0x008e:
            if (r0 == r4) goto L_0x00ad
            if (r0 != r5) goto L_0x0096
            r8.setClass(r5)
            goto L_0x0071
        L_0x0096:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>(r3)
            java.lang.StringBuilder r8 = r8.append(r0)
            java.lang.StringBuilder r8 = r8.append(r2)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L_0x00ad:
            r8.setClass(r4)
            goto L_0x0071
        L_0x00b1:
            int r10 = r9.length
            if (r6 >= r10) goto L_0x00c0
            r10 = r9[r6]
            byte r11 = r8.getParameterClass(r6)
            r7.transformNode(r10, r11, r1)
            int r6 = r6 + 1
            goto L_0x00b1
        L_0x00c0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.OperandClassTransformer.transformFunctionNode(org.apache.poi.ss.formula.ptg.AbstractFunctionPtg, org.apache.poi.ss.formula.ParseNode[], byte, boolean):void");
    }

    private void setSimpleValueFuncClass(AbstractFunctionPtg abstractFunctionPtg, byte b, boolean z) {
        if (z || b == 64) {
            abstractFunctionPtg.setClass(Ptg.CLASS_ARRAY);
        } else {
            abstractFunctionPtg.setClass((byte) 32);
        }
    }
}
