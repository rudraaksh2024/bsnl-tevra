package org.apache.logging.log4j.message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.util.StringBuilderFormattable;

public class StructuredDataCollectionMessage implements StringBuilderFormattable, MessageCollectionMessage<StructuredDataMessage> {
    private static final long serialVersionUID = 5725337076388822924L;
    private final List<StructuredDataMessage> structuredDataMessageList;

    public StructuredDataCollectionMessage(List<StructuredDataMessage> list) {
        this.structuredDataMessageList = list;
    }

    public Iterator<StructuredDataMessage> iterator() {
        return this.structuredDataMessageList.iterator();
    }

    public String getFormattedMessage() {
        StringBuilder sb = new StringBuilder();
        formatTo(sb);
        return sb.toString();
    }

    public String getFormat() {
        StringBuilder sb = new StringBuilder();
        for (StructuredDataMessage next : this.structuredDataMessageList) {
            if (next.getFormat() != null) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(next.getFormat());
            }
        }
        return sb.toString();
    }

    public void formatTo(StringBuilder sb) {
        for (StructuredDataMessage formatTo : this.structuredDataMessageList) {
            formatTo.formatTo(sb);
        }
    }

    public Object[] getParameters() {
        ArrayList<Object[]> arrayList = new ArrayList<>();
        int i = 0;
        for (StructuredDataMessage parameters : this.structuredDataMessageList) {
            Object[] parameters2 = parameters.getParameters();
            if (parameters2 != null) {
                arrayList.add(parameters2);
                i += parameters2.length;
            }
        }
        Object[] objArr = new Object[i];
        int i2 = 0;
        for (Object[] objArr2 : arrayList) {
            int length = objArr2.length;
            int i3 = 0;
            while (i3 < length) {
                objArr[i2] = objArr2[i3];
                i3++;
                i2++;
            }
        }
        return objArr;
    }

    public Throwable getThrowable() {
        for (StructuredDataMessage throwable : this.structuredDataMessageList) {
            Throwable throwable2 = throwable.getThrowable();
            if (throwable2 != null) {
                return throwable2;
            }
        }
        return null;
    }
}
