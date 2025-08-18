package org.apache.logging.log4j.util;

import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;

public final class LambdaUtil {
    private LambdaUtil() {
    }

    public static Object[] getAll(Supplier<?>... supplierArr) {
        if (supplierArr == null) {
            return null;
        }
        int length = supplierArr.length;
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            objArr[i] = get(supplierArr[i]);
        }
        return objArr;
    }

    public static Object get(Supplier<?> supplier) {
        if (supplier == null) {
            return null;
        }
        Object obj = supplier.get();
        return obj instanceof Message ? ((Message) obj).getFormattedMessage() : obj;
    }

    public static Message get(MessageSupplier messageSupplier) {
        if (messageSupplier == null) {
            return null;
        }
        return messageSupplier.get();
    }

    public static Message getMessage(Supplier<?> supplier, MessageFactory messageFactory) {
        if (supplier == null) {
            return null;
        }
        Object obj = supplier.get();
        return obj instanceof Message ? (Message) obj : messageFactory.newMessage((Object) obj);
    }
}
