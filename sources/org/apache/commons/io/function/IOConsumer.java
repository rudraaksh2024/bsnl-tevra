package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;

@FunctionalInterface
public interface IOConsumer<T> {
    public static final IOConsumer<?> NOOP_IO_CONSUMER = new IOConsumer$$ExternalSyntheticLambda1();

    static /* synthetic */ void lambda$static$0(Object obj) throws IOException {
    }

    void accept(T t) throws IOException;

    static <T> IOConsumer<T> noop() {
        return NOOP_IO_CONSUMER;
    }

    IOConsumer<T> andThen(IOConsumer<? super T> iOConsumer) {
        Objects.requireNonNull(iOConsumer, "after");
        return new IOConsumer$$ExternalSyntheticLambda0(this, iOConsumer);
    }

    static /* synthetic */ void lambda$andThen$1(IOConsumer _this, IOConsumer iOConsumer, Object obj) throws IOException {
        _this.accept(obj);
        iOConsumer.accept(obj);
    }
}
