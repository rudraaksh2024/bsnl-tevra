package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

public class TableSwitchForm extends SwitchForm {
    public TableSwitchForm(int i, String str) {
        super(i, str);
    }

    public void setByteCodeOperands(ByteCode byteCode, OperandManager operandManager, int i) {
        int nextCaseCount = operandManager.nextCaseCount();
        int nextLabel = operandManager.nextLabel();
        int nextCaseValues = operandManager.nextCaseValues();
        int[] iArr = new int[nextCaseCount];
        for (int i2 = 0; i2 < nextCaseCount; i2++) {
            iArr[i2] = operandManager.nextLabel();
        }
        int i3 = nextCaseCount + 1;
        int[] iArr2 = new int[i3];
        iArr2[0] = nextLabel;
        int i4 = 1;
        for (int i5 = 1; i5 < i3; i5++) {
            iArr2[i5] = iArr[i5 - 1];
        }
        byteCode.setByteCodeTargets(iArr2);
        int i6 = (nextCaseValues + nextCaseCount) - 1;
        int i7 = 3 - (i % 4);
        int[] iArr3 = new int[(i7 + 1 + 4 + 4 + 4 + (nextCaseCount * 4))];
        iArr3[0] = byteCode.getOpcode();
        int i8 = 0;
        while (i8 < i7) {
            iArr3[i4] = 0;
            i8++;
            i4++;
        }
        int i9 = i4 + 1;
        iArr3[i4] = -1;
        int i10 = i9 + 1;
        iArr3[i9] = -1;
        int i11 = i10 + 1;
        iArr3[i10] = -1;
        int i12 = i11 + 1;
        iArr3[i11] = -1;
        setRewrite4Bytes(nextCaseValues, i12, iArr3);
        int i13 = i12 + 4;
        setRewrite4Bytes(i6, i13, iArr3);
        int i14 = i13 + 4;
        for (int i15 = 0; i15 < nextCaseCount; i15++) {
            int i16 = i14 + 1;
            iArr3[i14] = -1;
            int i17 = i16 + 1;
            iArr3[i16] = -1;
            int i18 = i17 + 1;
            iArr3[i17] = -1;
            i14 = i18 + 1;
            iArr3[i18] = -1;
        }
        byteCode.setRewrite(iArr3);
    }
}
