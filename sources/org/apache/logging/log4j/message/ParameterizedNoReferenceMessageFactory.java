package org.apache.logging.log4j.message;

public final class ParameterizedNoReferenceMessageFactory extends AbstractMessageFactory {
    public static final ParameterizedNoReferenceMessageFactory INSTANCE = new ParameterizedNoReferenceMessageFactory();
    private static final long serialVersionUID = 5027639245636870500L;

    static class StatusMessage implements Message {
        private static final long serialVersionUID = 4199272162767841280L;
        private final String formattedMessage;
        private final Throwable throwable;

        public Object[] getParameters() {
            return null;
        }

        public StatusMessage(String str, Throwable th) {
            this.formattedMessage = str;
            this.throwable = th;
        }

        public String getFormattedMessage() {
            return this.formattedMessage;
        }

        public String getFormat() {
            return this.formattedMessage;
        }

        public Throwable getThrowable() {
            return this.throwable;
        }
    }

    public Message newMessage(String str, Object... objArr) {
        if (objArr == null) {
            return new SimpleMessage(str);
        }
        ParameterizedMessage parameterizedMessage = new ParameterizedMessage(str, objArr);
        return new StatusMessage(parameterizedMessage.getFormattedMessage(), parameterizedMessage.getThrowable());
    }
}
