package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AndFileFilter extends AbstractFileFilter implements ConditionalFileFilter, Serializable {
    private static final long serialVersionUID = 7215974688563965257L;
    private final List<IOFileFilter> fileFilters;

    public AndFileFilter() {
        this(0);
    }

    private AndFileFilter(ArrayList<IOFileFilter> arrayList) {
        this.fileFilters = (List) Objects.requireNonNull(arrayList, "initialList");
    }

    private AndFileFilter(int i) {
        this((ArrayList<IOFileFilter>) new ArrayList(i));
    }

    public AndFileFilter(IOFileFilter iOFileFilter, IOFileFilter iOFileFilter2) {
        this(2);
        addFileFilter(iOFileFilter);
        addFileFilter(iOFileFilter2);
    }

    public AndFileFilter(IOFileFilter... iOFileFilterArr) {
        this(((IOFileFilter[]) Objects.requireNonNull(iOFileFilterArr, "fileFilters")).length);
        addFileFilter(iOFileFilterArr);
    }

    public AndFileFilter(List<IOFileFilter> list) {
        this((ArrayList<IOFileFilter>) new ArrayList((Collection) Objects.requireNonNull(list, "fileFilters")));
    }

    public boolean accept(File file) {
        if (isEmpty()) {
            return false;
        }
        for (IOFileFilter accept : this.fileFilters) {
            if (!accept.accept(file)) {
                return false;
            }
        }
        return true;
    }

    public boolean accept(File file, String str) {
        if (isEmpty()) {
            return false;
        }
        for (IOFileFilter accept : this.fileFilters) {
            if (!accept.accept(file, str)) {
                return false;
            }
        }
        return true;
    }

    public FileVisitResult accept(Path path, BasicFileAttributes basicFileAttributes) {
        if (isEmpty()) {
            return FileVisitResult.TERMINATE;
        }
        for (IOFileFilter accept : this.fileFilters) {
            if (accept.accept(path, basicFileAttributes) != FileVisitResult.CONTINUE) {
                return FileVisitResult.TERMINATE;
            }
        }
        return FileVisitResult.CONTINUE;
    }

    public void addFileFilter(IOFileFilter iOFileFilter) {
        this.fileFilters.add(Objects.requireNonNull(iOFileFilter, "fileFilter"));
    }

    public void addFileFilter(IOFileFilter... iOFileFilterArr) {
        for (IOFileFilter addFileFilter : (IOFileFilter[]) Objects.requireNonNull(iOFileFilterArr, "fileFilters")) {
            addFileFilter(addFileFilter);
        }
    }

    public List<IOFileFilter> getFileFilters() {
        return Collections.unmodifiableList(this.fileFilters);
    }

    private boolean isEmpty() {
        return this.fileFilters.isEmpty();
    }

    public boolean removeFileFilter(IOFileFilter iOFileFilter) {
        return this.fileFilters.remove(iOFileFilter);
    }

    public void setFileFilters(List<IOFileFilter> list) {
        this.fileFilters.clear();
        this.fileFilters.addAll(list);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("(");
        for (int i = 0; i < this.fileFilters.size(); i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(this.fileFilters.get(i));
        }
        sb.append(")");
        return sb.toString();
    }
}
