package org.apache.logging.log4j.message;

import java.util.Map;

@AsynchronouslyFormattable
public class StringMapMessage extends MapMessage<StringMapMessage, String> {
    private static final long serialVersionUID = 1;

    public StringMapMessage() {
    }

    public StringMapMessage(int i) {
        super(i);
    }

    public StringMapMessage(Map<String, String> map) {
        super(map);
    }

    public StringMapMessage newInstance(Map<String, String> map) {
        return new StringMapMessage(map);
    }
}
