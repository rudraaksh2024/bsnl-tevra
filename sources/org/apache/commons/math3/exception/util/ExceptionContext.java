package org.apache.commons.math3.exception.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class ExceptionContext implements Serializable {
    private static final long serialVersionUID = -6024911025449780478L;
    private Map<String, Object> context = new HashMap();
    private List<Object[]> msgArguments = new ArrayList();
    private List<Localizable> msgPatterns = new ArrayList();
    private Throwable throwable;

    public ExceptionContext(Throwable th) {
        this.throwable = th;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public void addMessage(Localizable localizable, Object... objArr) {
        this.msgPatterns.add(localizable);
        this.msgArguments.add(ArgUtils.flatten(objArr));
    }

    public void setValue(String str, Object obj) {
        this.context.put(str, obj);
    }

    public Object getValue(String str) {
        return this.context.get(str);
    }

    public Set<String> getKeys() {
        return this.context.keySet();
    }

    public String getMessage() {
        return getMessage(Locale.US);
    }

    public String getLocalizedMessage() {
        return getMessage(Locale.getDefault());
    }

    public String getMessage(Locale locale) {
        return buildMessage(locale, ": ");
    }

    public String getMessage(Locale locale, String str) {
        return buildMessage(locale, str);
    }

    private String buildMessage(Locale locale, String str) {
        StringBuilder sb = new StringBuilder();
        int size = this.msgPatterns.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            sb.append(new MessageFormat(this.msgPatterns.get(i2).getLocalizedString(locale), locale).format(this.msgArguments.get(i2)));
            i++;
            if (i < size) {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.throwable);
        serializeMessages(objectOutputStream);
        serializeContext(objectOutputStream);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.throwable = (Throwable) objectInputStream.readObject();
        deSerializeMessages(objectInputStream);
        deSerializeContext(objectInputStream);
    }

    private void serializeMessages(ObjectOutputStream objectOutputStream) throws IOException {
        int size = this.msgPatterns.size();
        objectOutputStream.writeInt(size);
        for (int i = 0; i < size; i++) {
            objectOutputStream.writeObject(this.msgPatterns.get(i));
            objectOutputStream.writeInt(r4);
            for (Object obj : this.msgArguments.get(i)) {
                if (obj instanceof Serializable) {
                    objectOutputStream.writeObject(obj);
                } else {
                    objectOutputStream.writeObject(nonSerializableReplacement(obj));
                }
            }
        }
    }

    private void deSerializeMessages(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        int readInt = objectInputStream.readInt();
        this.msgPatterns = new ArrayList(readInt);
        this.msgArguments = new ArrayList(readInt);
        for (int i = 0; i < readInt; i++) {
            this.msgPatterns.add((Localizable) objectInputStream.readObject());
            int readInt2 = objectInputStream.readInt();
            Object[] objArr = new Object[readInt2];
            for (int i2 = 0; i2 < readInt2; i2++) {
                objArr[i2] = objectInputStream.readObject();
            }
            this.msgArguments.add(objArr);
        }
    }

    private void serializeContext(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.context.size());
        for (Map.Entry next : this.context.entrySet()) {
            objectOutputStream.writeObject(next.getKey());
            Object value = next.getValue();
            if (value instanceof Serializable) {
                objectOutputStream.writeObject(value);
            } else {
                objectOutputStream.writeObject(nonSerializableReplacement(value));
            }
        }
    }

    private void deSerializeContext(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        int readInt = objectInputStream.readInt();
        this.context = new HashMap();
        for (int i = 0; i < readInt; i++) {
            Object readObject = objectInputStream.readObject();
            this.context.put((String) objectInputStream.readObject(), readObject);
        }
    }

    private String nonSerializableReplacement(Object obj) {
        return "[Object could not be serialized: " + obj.getClass().getName() + "]";
    }
}
