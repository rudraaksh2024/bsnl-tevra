package org.apache.logging.log4j.message;

import java.io.Serializable;

public class DefaultFlowMessageFactory implements FlowMessageFactory, Serializable {
    private static final String ENTRY_DEFAULT_PREFIX = "Enter";
    private static final String EXIT_DEFAULT_PREFIX = "Exit";
    private static final long serialVersionUID = 8578655591131397576L;
    private final String entryText;
    private final String exitText;

    public DefaultFlowMessageFactory() {
        this(ENTRY_DEFAULT_PREFIX, EXIT_DEFAULT_PREFIX);
    }

    public DefaultFlowMessageFactory(String str, String str2) {
        this.entryText = str;
        this.exitText = str2;
    }

    private static class AbstractFlowMessage implements FlowMessage {
        private static final long serialVersionUID = 1;
        private final Message message;
        private final String text;

        AbstractFlowMessage(String str, Message message2) {
            this.message = message2;
            this.text = str;
        }

        public String getFormattedMessage() {
            if (this.message != null) {
                return this.text + " " + this.message.getFormattedMessage();
            }
            return this.text;
        }

        public String getFormat() {
            if (this.message != null) {
                return this.text + ": " + this.message.getFormat();
            }
            return this.text;
        }

        public Object[] getParameters() {
            Message message2 = this.message;
            if (message2 != null) {
                return message2.getParameters();
            }
            return null;
        }

        public Throwable getThrowable() {
            Message message2 = this.message;
            if (message2 != null) {
                return message2.getThrowable();
            }
            return null;
        }

        public Message getMessage() {
            return this.message;
        }

        public String getText() {
            return this.text;
        }
    }

    private static final class SimpleEntryMessage extends AbstractFlowMessage implements EntryMessage {
        private static final long serialVersionUID = 1;

        SimpleEntryMessage(String str, Message message) {
            super(str, message);
        }
    }

    private static final class SimpleExitMessage extends AbstractFlowMessage implements ExitMessage {
        private static final long serialVersionUID = 1;
        private final boolean isVoid;
        private final Object result;

        SimpleExitMessage(String str, EntryMessage entryMessage) {
            super(str, entryMessage.getMessage());
            this.result = null;
            this.isVoid = true;
        }

        SimpleExitMessage(String str, Object obj, EntryMessage entryMessage) {
            super(str, entryMessage.getMessage());
            this.result = obj;
            this.isVoid = false;
        }

        SimpleExitMessage(String str, Object obj, Message message) {
            super(str, message);
            this.result = obj;
            this.isVoid = false;
        }

        public String getFormattedMessage() {
            String formattedMessage = super.getFormattedMessage();
            if (this.isVoid) {
                return formattedMessage;
            }
            return formattedMessage + ": " + this.result;
        }
    }

    public String getEntryText() {
        return this.entryText;
    }

    public String getExitText() {
        return this.exitText;
    }

    public EntryMessage newEntryMessage(Message message) {
        return new SimpleEntryMessage(this.entryText, makeImmutable(message));
    }

    private Message makeImmutable(Message message) {
        if (!(message instanceof ReusableMessage)) {
            return message;
        }
        return new SimpleMessage(message.getFormattedMessage());
    }

    public ExitMessage newExitMessage(EntryMessage entryMessage) {
        return new SimpleExitMessage(this.exitText, entryMessage);
    }

    public ExitMessage newExitMessage(Object obj, EntryMessage entryMessage) {
        return new SimpleExitMessage(this.exitText, obj, entryMessage);
    }

    public ExitMessage newExitMessage(Object obj, Message message) {
        return new SimpleExitMessage(this.exitText, obj, message);
    }
}
