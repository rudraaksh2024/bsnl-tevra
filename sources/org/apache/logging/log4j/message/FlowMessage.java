package org.apache.logging.log4j.message;

public interface FlowMessage extends Message {
    Message getMessage();

    String getText();
}
