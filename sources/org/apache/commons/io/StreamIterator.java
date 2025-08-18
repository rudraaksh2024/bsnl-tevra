package org.apache.commons.io;

import java.io.Closeable;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

class StreamIterator<E> implements Iterator<E>, Closeable {
    private final Iterator<E> iterator;
    private final Stream<E> stream;

    public static <T> Iterator<T> iterator(Stream<T> stream2) {
        return new StreamIterator(stream2).iterator;
    }

    private StreamIterator(Stream<E> stream2) {
        this.stream = (Stream) Objects.requireNonNull(stream2, "stream");
        this.iterator = stream2.iterator();
    }

    public boolean hasNext() {
        boolean hasNext = this.iterator.hasNext();
        if (!hasNext) {
            close();
        }
        return hasNext;
    }

    public E next() {
        E next = this.iterator.next();
        if (next == null) {
            close();
        }
        return next;
    }

    public void close() {
        this.stream.close();
    }
}
