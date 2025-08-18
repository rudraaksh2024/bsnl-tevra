package org.apache.logging.log4j.message;

public interface ParameterConsumer<S> {
    void accept(Object obj, int i, S s);
}
