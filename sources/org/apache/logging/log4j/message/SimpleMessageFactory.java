package org.apache.logging.log4j.message;

public final class SimpleMessageFactory extends AbstractMessageFactory {
    public static final SimpleMessageFactory INSTANCE = new SimpleMessageFactory();
    private static final long serialVersionUID = 4418995198790088516L;

    public Message newMessage(String str, Object... objArr) {
        return new SimpleMessage(str);
    }

    public Message newMessage(String str, Object obj) {
        return new SimpleMessage(str);
    }

    public Message newMessage(String str, Object obj, Object obj2) {
        return new SimpleMessage(str);
    }

    public Message newMessage(String str, Object obj, Object obj2, Object obj3) {
        return new SimpleMessage(str);
    }

    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        return new SimpleMessage(str);
    }

    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return new SimpleMessage(str);
    }

    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return new SimpleMessage(str);
    }

    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return new SimpleMessage(str);
    }

    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return new SimpleMessage(str);
    }

    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        return new SimpleMessage(str);
    }

    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        return new SimpleMessage(str);
    }
}
