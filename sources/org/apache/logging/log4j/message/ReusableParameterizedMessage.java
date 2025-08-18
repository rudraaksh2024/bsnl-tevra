package org.apache.logging.log4j.message;

import java.util.Arrays;
import org.apache.logging.log4j.util.Constants;
import org.apache.logging.log4j.util.StringBuilders;

public class ReusableParameterizedMessage implements ReusableMessage, ParameterVisitable, Clearable {
    private static final int MAX_PARMS = 10;
    private static final int MIN_BUILDER_SIZE = 512;
    private static final long serialVersionUID = 7800075879295123856L;
    private int argCount;
    private transient ThreadLocal<StringBuilder> buffer;
    private final int[] indices = new int[256];
    private String messagePattern;
    private transient Object[] params = new Object[10];
    transient boolean reserved = false;
    private transient Throwable throwable;
    private int usedCount;
    private transient Object[] varargs;

    private Object[] getTrimmedParams() {
        Object[] objArr = this.varargs;
        return objArr == null ? Arrays.copyOf(this.params, this.argCount) : objArr;
    }

    private Object[] getParams() {
        Object[] objArr = this.varargs;
        return objArr == null ? this.params : objArr;
    }

    public Object[] swapParameters(Object[] objArr) {
        Object[] objArr2 = this.varargs;
        if (objArr2 == null) {
            Object[] objArr3 = this.params;
            if (objArr.length >= 10) {
                this.params = objArr;
            } else {
                int i = this.argCount;
                if (i <= objArr.length) {
                    System.arraycopy(objArr3, 0, objArr, 0, i);
                    for (int i2 = 0; i2 < this.argCount; i2++) {
                        this.params[i2] = null;
                    }
                    return objArr;
                }
                this.params = new Object[10];
            }
            return objArr3;
        }
        int i3 = this.argCount;
        if (i3 > objArr.length) {
            objArr = new Object[i3];
        }
        System.arraycopy(objArr2, 0, objArr, 0, i3);
        return objArr;
    }

    public short getParameterCount() {
        return (short) this.argCount;
    }

    public <S> void forEachParameter(ParameterConsumer<S> parameterConsumer, S s) {
        Object[] params2 = getParams();
        for (short s2 = 0; s2 < this.argCount; s2 = (short) (s2 + 1)) {
            parameterConsumer.accept(params2[s2], s2, s);
        }
    }

    public Message memento() {
        return new ParameterizedMessage(this.messagePattern, getTrimmedParams());
    }

    private void init(String str, int i, Object[] objArr) {
        this.varargs = null;
        this.messagePattern = str;
        this.argCount = i;
        int count = count(str, this.indices);
        initThrowable(objArr, i, count);
        this.usedCount = Math.min(count, i);
    }

    private static int count(String str, int[] iArr) {
        try {
            return ParameterFormatter.countArgumentPlaceholders2(str, iArr);
        } catch (Exception unused) {
            return ParameterFormatter.countArgumentPlaceholders(str);
        }
    }

    private void initThrowable(Object[] objArr, int i, int i2) {
        if (i2 < i) {
            Throwable th = objArr[i - 1];
            if (th instanceof Throwable) {
                this.throwable = th;
                return;
            }
        }
        this.throwable = null;
    }

    /* access modifiers changed from: package-private */
    public ReusableParameterizedMessage set(String str, Object... objArr) {
        init(str, objArr == null ? 0 : objArr.length, objArr);
        this.varargs = objArr;
        return this;
    }

    /* access modifiers changed from: package-private */
    public ReusableParameterizedMessage set(String str, Object obj) {
        Object[] objArr = this.params;
        objArr[0] = obj;
        init(str, 1, objArr);
        return this;
    }

    /* access modifiers changed from: package-private */
    public ReusableParameterizedMessage set(String str, Object obj, Object obj2) {
        Object[] objArr = this.params;
        objArr[0] = obj;
        objArr[1] = obj2;
        init(str, 2, objArr);
        return this;
    }

    /* access modifiers changed from: package-private */
    public ReusableParameterizedMessage set(String str, Object obj, Object obj2, Object obj3) {
        Object[] objArr = this.params;
        objArr[0] = obj;
        objArr[1] = obj2;
        objArr[2] = obj3;
        init(str, 3, objArr);
        return this;
    }

    /* access modifiers changed from: package-private */
    public ReusableParameterizedMessage set(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        Object[] objArr = this.params;
        objArr[0] = obj;
        objArr[1] = obj2;
        objArr[2] = obj3;
        objArr[3] = obj4;
        init(str, 4, objArr);
        return this;
    }

    /* access modifiers changed from: package-private */
    public ReusableParameterizedMessage set(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        Object[] objArr = this.params;
        objArr[0] = obj;
        objArr[1] = obj2;
        objArr[2] = obj3;
        objArr[3] = obj4;
        objArr[4] = obj5;
        init(str, 5, objArr);
        return this;
    }

    /* access modifiers changed from: package-private */
    public ReusableParameterizedMessage set(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        Object[] objArr = this.params;
        objArr[0] = obj;
        objArr[1] = obj2;
        objArr[2] = obj3;
        objArr[3] = obj4;
        objArr[4] = obj5;
        objArr[5] = obj6;
        init(str, 6, objArr);
        return this;
    }

    /* access modifiers changed from: package-private */
    public ReusableParameterizedMessage set(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        Object[] objArr = this.params;
        objArr[0] = obj;
        objArr[1] = obj2;
        objArr[2] = obj3;
        objArr[3] = obj4;
        objArr[4] = obj5;
        objArr[5] = obj6;
        objArr[6] = obj7;
        init(str, 7, objArr);
        return this;
    }

    /* access modifiers changed from: package-private */
    public ReusableParameterizedMessage set(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        Object[] objArr = this.params;
        objArr[0] = obj;
        objArr[1] = obj2;
        objArr[2] = obj3;
        objArr[3] = obj4;
        objArr[4] = obj5;
        objArr[5] = obj6;
        objArr[6] = obj7;
        objArr[7] = obj8;
        init(str, 8, objArr);
        return this;
    }

    /* access modifiers changed from: package-private */
    public ReusableParameterizedMessage set(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        Object[] objArr = this.params;
        objArr[0] = obj;
        objArr[1] = obj2;
        objArr[2] = obj3;
        objArr[3] = obj4;
        objArr[4] = obj5;
        objArr[5] = obj6;
        objArr[6] = obj7;
        objArr[7] = obj8;
        objArr[8] = obj9;
        init(str, 9, objArr);
        return this;
    }

    /* access modifiers changed from: package-private */
    public ReusableParameterizedMessage set(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        Object[] objArr = this.params;
        objArr[0] = obj;
        objArr[1] = obj2;
        objArr[2] = obj3;
        objArr[3] = obj4;
        objArr[4] = obj5;
        objArr[5] = obj6;
        objArr[6] = obj7;
        objArr[7] = obj8;
        objArr[8] = obj9;
        objArr[9] = obj10;
        init(str, 10, objArr);
        return this;
    }

    public String getFormat() {
        return this.messagePattern;
    }

    public Object[] getParameters() {
        return getTrimmedParams();
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public String getFormattedMessage() {
        StringBuilder buffer2 = getBuffer();
        formatTo(buffer2);
        String sb = buffer2.toString();
        StringBuilders.trimToMaxSize(buffer2, Constants.MAX_REUSABLE_MESSAGE_SIZE);
        return sb;
    }

    private StringBuilder getBuffer() {
        if (this.buffer == null) {
            this.buffer = new ThreadLocal<>();
        }
        StringBuilder sb = this.buffer.get();
        if (sb == null) {
            String str = this.messagePattern;
            StringBuilder sb2 = new StringBuilder(Math.max(512, (str == null ? 0 : str.length()) * 2));
            this.buffer.set(sb2);
            sb = sb2;
        }
        sb.setLength(0);
        return sb;
    }

    public void formatTo(StringBuilder sb) {
        if (this.indices[0] < 0) {
            ParameterFormatter.formatMessage(sb, this.messagePattern, getParams(), this.argCount);
        } else {
            ParameterFormatter.formatMessage2(sb, this.messagePattern, getParams(), this.usedCount, this.indices);
        }
    }

    /* access modifiers changed from: package-private */
    public ReusableParameterizedMessage reserve() {
        this.reserved = true;
        return this;
    }

    public String toString() {
        return "ReusableParameterizedMessage[messagePattern=" + getFormat() + ", stringArgs=" + Arrays.toString(getParameters()) + ", throwable=" + getThrowable() + ']';
    }

    public void clear() {
        this.reserved = false;
        this.varargs = null;
        this.messagePattern = null;
        this.throwable = null;
    }
}
