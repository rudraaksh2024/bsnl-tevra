package org.apache.commons.io.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;
import org.apache.commons.io.file.Counters;
import org.apache.commons.io.filefilter.TrueFileFilter;

public class CountingPathVisitor extends SimplePathVisitor {
    static final String[] EMPTY_STRING_ARRAY = new String[0];
    private final PathFilter dirFilter;
    private final PathFilter fileFilter;
    private final Counters.PathCounters pathCounters;

    public static CountingPathVisitor withBigIntegerCounters() {
        return new CountingPathVisitor(Counters.bigIntegerPathCounters());
    }

    public static CountingPathVisitor withLongCounters() {
        return new CountingPathVisitor(Counters.longPathCounters());
    }

    public CountingPathVisitor(Counters.PathCounters pathCounters2) {
        this(pathCounters2, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
    }

    public CountingPathVisitor(Counters.PathCounters pathCounters2, PathFilter pathFilter, PathFilter pathFilter2) {
        this.pathCounters = (Counters.PathCounters) Objects.requireNonNull(pathCounters2, "pathCounter");
        this.fileFilter = (PathFilter) Objects.requireNonNull(pathFilter, "fileFilter");
        this.dirFilter = (PathFilter) Objects.requireNonNull(pathFilter2, "dirFilter");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CountingPathVisitor)) {
            return false;
        }
        return Objects.equals(this.pathCounters, ((CountingPathVisitor) obj).pathCounters);
    }

    public Counters.PathCounters getPathCounters() {
        return this.pathCounters;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.pathCounters});
    }

    public FileVisitResult postVisitDirectory(Path path, IOException iOException) throws IOException {
        updateDirCounter(path, iOException);
        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
        return this.dirFilter.accept(path, basicFileAttributes) != FileVisitResult.CONTINUE ? FileVisitResult.SKIP_SUBTREE : FileVisitResult.CONTINUE;
    }

    public String toString() {
        return this.pathCounters.toString();
    }

    /* access modifiers changed from: protected */
    public void updateDirCounter(Path path, IOException iOException) {
        this.pathCounters.getDirectoryCounter().increment();
    }

    /* access modifiers changed from: protected */
    public void updateFileCounters(Path path, BasicFileAttributes basicFileAttributes) {
        this.pathCounters.getFileCounter().increment();
        this.pathCounters.getByteCounter().add(basicFileAttributes.size());
    }

    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
        if (Files.exists(path, new LinkOption[0]) && this.fileFilter.accept(path, basicFileAttributes) == FileVisitResult.CONTINUE) {
            updateFileCounters(path, basicFileAttributes);
        }
        return FileVisitResult.CONTINUE;
    }
}
