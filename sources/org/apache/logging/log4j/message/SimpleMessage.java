package org.apache.logging.log4j.message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.logging.log4j.util.StringBuilderFormattable;

public class SimpleMessage implements Message, StringBuilderFormattable, CharSequence {
    private static final long serialVersionUID = -8398002534962715992L;
    private transient CharSequence charSequence;
    private String message;

    public Object[] getParameters() {
        return null;
    }

    public Throwable getThrowable() {
        return null;
    }

    public SimpleMessage() {
        this((String) null);
    }

    public SimpleMessage(String str) {
        this.message = str;
        this.charSequence = str;
    }

    public SimpleMessage(CharSequence charSequence2) {
        this.charSequence = charSequence2;
    }

    public String getFormattedMessage() {
        String str = this.message;
        if (str == null) {
            str = String.valueOf(this.charSequence);
        }
        this.message = str;
        return str;
    }

    public void formatTo(StringBuilder sb) {
        CharSequence charSequence2 = this.message;
        if (charSequence2 == null) {
            charSequence2 = this.charSequence;
        }
        sb.append(charSequence2);
    }

    public String getFormat() {
        return this.message;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SimpleMessage simpleMessage = (SimpleMessage) obj;
        CharSequence charSequence2 = this.charSequence;
        if (charSequence2 != null) {
            if (charSequence2.equals(simpleMessage.charSequence)) {
                return true;
            }
        } else if (simpleMessage.charSequence == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        CharSequence charSequence2 = this.charSequence;
        if (charSequence2 != null) {
            return charSequence2.hashCode();
        }
        return 0;
    }

    public String toString() {
        return getFormattedMessage();
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

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        getFormattedMessage();
        objectOutputStream.defaultWriteObject();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.charSequence = this.message;
    }
}
