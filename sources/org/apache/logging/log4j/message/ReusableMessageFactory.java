package org.apache.logging.log4j.message;

import java.io.Serializable;

public final class ReusableMessageFactory implements MessageFactory2, Serializable {
    public static final ReusableMessageFactory INSTANCE = new ReusableMessageFactory();
    private static final long serialVersionUID = -8970940216592525651L;
    private static ThreadLocal<ReusableObjectMessage> threadLocalObjectMessage = new ThreadLocal<>();
    private static ThreadLocal<ReusableParameterizedMessage> threadLocalParameterized = new ThreadLocal<>();
    private static ThreadLocal<ReusableSimpleMessage> threadLocalSimpleMessage = new ThreadLocal<>();

    private static ReusableParameterizedMessage getParameterized() {
        ReusableParameterizedMessage reusableParameterizedMessage = threadLocalParameterized.get();
        if (reusableParameterizedMessage == null) {
            reusableParameterizedMessage = new ReusableParameterizedMessage();
            threadLocalParameterized.set(reusableParameterizedMessage);
        }
        if (reusableParameterizedMessage.reserved) {
            reusableParameterizedMessage = new ReusableParameterizedMessage();
        }
        return reusableParameterizedMessage.reserve();
    }

    private static ReusableSimpleMessage getSimple() {
        ReusableSimpleMessage reusableSimpleMessage = threadLocalSimpleMessage.get();
        if (reusableSimpleMessage != null) {
            return reusableSimpleMessage;
        }
        ReusableSimpleMessage reusableSimpleMessage2 = new ReusableSimpleMessage();
        threadLocalSimpleMessage.set(reusableSimpleMessage2);
        return reusableSimpleMessage2;
    }

    private static ReusableObjectMessage getObject() {
        ReusableObjectMessage reusableObjectMessage = threadLocalObjectMessage.get();
        if (reusableObjectMessage != null) {
            return reusableObjectMessage;
        }
        ReusableObjectMessage reusableObjectMessage2 = new ReusableObjectMessage();
        threadLocalObjectMessage.set(reusableObjectMessage2);
        return reusableObjectMessage2;
    }

    public static void release(Message message) {
        if (message instanceof Clearable) {
            ((Clearable) message).clear();
        }
    }

    public Message newMessage(CharSequence charSequence) {
        ReusableSimpleMessage simple = getSimple();
        simple.set(charSequence);
        return simple;
    }

    public Message newMessage(String str, Object... objArr) {
        return getParameterized().set(str, objArr);
    }

    public Message newMessage(String str, Object obj) {
        return getParameterized().set(str, obj);
    }

    public Message newMessage(String str, Object obj, Object obj2) {
        return getParameterized().set(str, obj, obj2);
    }

    public Message newMessage(String str, Object obj, Object obj2, Object obj3) {
        return getParameterized().set(str, obj, obj2, obj3);
    }

    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        return getParameterized().set(str, obj, obj2, obj3, obj4);
    }

    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return getParameterized().set(str, obj, obj2, obj3, obj4, obj5);
    }

    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return getParameterized().set(str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return getParameterized().set(str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return getParameterized().set(str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        return getParameterized().set(str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        return getParameterized().set(str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    public Message newMessage(String str) {
        ReusableSimpleMessage simple = getSimple();
        simple.set(str);
        return simple;
    }

    public Message newMessage(Object obj) {
        ReusableObjectMessage object = getObject();
        object.set(obj);
        return object;
    }
}
