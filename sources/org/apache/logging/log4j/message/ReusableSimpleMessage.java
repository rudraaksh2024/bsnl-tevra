package org.apache.logging.log4j.message;

import org.apache.logging.log4j.util.Constants;

public class ReusableSimpleMessage implements ReusableMessage, CharSequence, ParameterVisitable, Clearable {
    private static final long serialVersionUID = -9199974506498249809L;
    private CharSequence charSequence;

    public <S> void forEachParameter(ParameterConsumer<S> parameterConsumer, S s) {
    }

    public short getParameterCount() {
        return 0;
    }

    public Throwable getThrowable() {
        return null;
    }

    public Object[] swapParameters(Object[] objArr) {
        return objArr;
    }

    public void set(String str) {
        this.charSequence = str;
    }

    public void set(CharSequence charSequence2) {
        this.charSequence = charSequence2;
    }

    public String getFormattedMessage() {
        return String.valueOf(this.charSequence);
    }

    public String getFormat() {
        CharSequence charSequence2 = this.charSequence;
        if (charSequence2 instanceof String) {
            return (String) charSequence2;
        }
        return null;
    }

    public Object[] getParameters() {
        return Constants.EMPTY_OBJECT_ARRAY;
    }

    public void formatTo(StringBuilder sb) {
        sb.append(this.charSequence);
    }

    public Message memento() {
        return new SimpleMessage(this.charSequence);
    }

    public int length() {
        CharSequence charSequence2 = this.charSequence;
        if (charSequence2 == null) {
            return 0;
        }
        return charSequence2.length();
    }

    public char charAt(int i) {
        return this.charSequence.charAt(i);
    }

    public CharSequence subSequence(int i, int i2) {
        return this.charSequence.subSequence(i, i2);
    }

    public void clear() {
        this.charSequence = null;
    }
}
