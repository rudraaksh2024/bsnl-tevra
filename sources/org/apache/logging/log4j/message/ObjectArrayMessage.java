package org.apache.logging.log4j.message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import org.apache.logging.log4j.util.Constants;

public final class ObjectArrayMessage implements Message {
    private static final long serialVersionUID = -5903272448334166185L;
    private transient Object[] array;
    private transient String arrayString;

    public Throwable getThrowable() {
        return null;
    }

    public ObjectArrayMessage(Object... objArr) {
        this.array = objArr == null ? Constants.EMPTY_OBJECT_ARRAY : objArr;
    }

    private boolean equalObjectsOrStrings(Object[] objArr, Object[] objArr2) {
        return Arrays.equals(objArr, objArr2) || Arrays.toString(objArr).equals(Arrays.toString(objArr2));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ObjectArrayMessage objectArrayMessage = (ObjectArrayMessage) obj;
        Object[] objArr = this.array;
        if (objArr != null) {
            return equalObjectsOrStrings(objArr, objectArrayMessage.array);
        }
        if (objectArrayMessage.array == null) {
            return true;
        }
        return false;
    }

    public String getFormat() {
        return getFormattedMessage();
    }

    public String getFormattedMessage() {
        if (this.arrayString == null) {
            this.arrayString = Arrays.toString(this.array);
        }
        return this.arrayString;
    }

    public Object[] getParameters() {
        return this.array;
    }

    public int hashCode() {
        return Arrays.hashCode(this.array);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.array = (Object[]) objectInputStream.readObject();
    }

    public String toString() {
        return getFormattedMessage();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.array);
    }
}
