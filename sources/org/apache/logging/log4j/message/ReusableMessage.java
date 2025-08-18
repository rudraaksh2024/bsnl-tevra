package org.apache.logging.log4j.message;

import org.apache.logging.log4j.util.StringBuilderFormattable;

public interface ReusableMessage extends Message, StringBuilderFormattable {
    short getParameterCount();

    Message memento();

    Object[] swapParameters(Object[] objArr);
}
