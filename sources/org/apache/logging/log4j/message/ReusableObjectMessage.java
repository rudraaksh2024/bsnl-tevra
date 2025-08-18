package org.apache.logging.log4j.message;

import org.apache.logging.log4j.util.StringBuilders;

public class ReusableObjectMessage implements ReusableMessage, ParameterVisitable, Clearable {
    private static final long serialVersionUID = 6922476812535519960L;
    private transient Object obj;

    public short getParameterCount() {
        return 1;
    }

    public void set(Object obj2) {
        this.obj = obj2;
    }

    public String getFormattedMessage() {
        return String.valueOf(this.obj);
    }

    public void formatTo(StringBuilder sb) {
        StringBuilders.appendValue(sb, this.obj);
    }

    public String getFormat() {
        Object obj2 = this.obj;
        if (obj2 instanceof String) {
            return (String) obj2;
        }
        return null;
    }

    public Object getParameter() {
        return this.obj;
    }

    public Object[] getParameters() {
        return new Object[]{this.obj};
    }

    public String toString() {
        return getFormattedMessage();
    }

    public Throwable getThrowable() {
        Object obj2 = this.obj;
        if (obj2 instanceof Throwable) {
            return (Throwable) obj2;
        }
        return null;
    }

    public Object[] swapParameters(Object[] objArr) {
        if (objArr.length == 0) {
            Object[] objArr2 = new Object[10];
            objArr2[0] = this.obj;
            return objArr2;
        }
        objArr[0] = this.obj;
        return objArr;
    }

    public <S> void forEachParameter(ParameterConsumer<S> parameterConsumer, S s) {
        parameterConsumer.accept(this.obj, 0, s);
    }

    public Message memento() {
        return new ObjectMessage(this.obj);
    }

    public void clear() {
        this.obj = null;
    }
}
