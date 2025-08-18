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

public class OrFileFilter extends AbstractFileFilter implements ConditionalFileFilter, Serializable {
    private static final long serialVersionUID = 5767770777065432721L;
    private final List<IOFileFilter> fileFilters;

    public OrFileFilter() {
        this(0);
    }

    private OrFileFilter(ArrayList<IOFileFilter> arrayList) {
        this.fileFilters = (List) Objects.requireNonNull(arrayList, "initialList");
    }

    private OrFileFilter(int i) {
        this((ArrayList<IOFileFilter>) new ArrayList(i));
    }

    public OrFileFilter(IOFileFilter... iOFileFilterArr) {
        this(((IOFileFilter[]) Objects.requireNonNull(iOFileFilterArr, "fileFilters")).length);
        addFileFilter(iOFileFilterArr);
    }

    public OrFileFilter(IOFileFilter iOFileFilter, IOFileFilter iOFileFilter2) {
        this(2);
        addFileFilter(iOFileFilter);
        addFileFilter(iOFileFilter2);
    }

    public OrFileFilter(List<IOFileFilter> list) {
        this((ArrayList<IOFileFilter>) new ArrayList((Collection) Objects.requireNonNull(list, "fileFilters")));
    }

    public boolean accept(File file) {
        for (IOFileFilter accept : this.fileFilters) {
            if (accept.accept(file)) {
                return true;
            }
        }
        return false;
    }

    public boolean accept(File file, String str) {
        for (IOFileFilter accept : this.fileFilters) {
            if (accept.accept(file, str)) {
                return true;
            }
        }
        return false;
    }

    public FileVisitResult accept(Path path, BasicFileAttributes basicFileAttributes) {
        for (IOFileFilter accept : this.fileFilters) {
            if (accept.accept(path, basicFileAttributes) == FileVisitResult.CONTINUE) {
                return FileVisitResult.CONTINUE;
            }
        }
        return FileVisitResult.TERMINATE;
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

    public boolean removeFileFilter(IOFileFilter iOFileFilter) {
        return this.fileFilters.remove(iOFileFilter);
    }

    public void setFileFilters(List<IOFileFilter> list) {
        this.fileFilters.clear();
        this.fileFilters.addAll((Collection) Objects.requireNonNull(list, "fileFilters"));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("(");
        if (this.fileFilters != null) {
            for (int i = 0; i < this.fileFilters.size(); i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(this.fileFilters.get(i));
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
