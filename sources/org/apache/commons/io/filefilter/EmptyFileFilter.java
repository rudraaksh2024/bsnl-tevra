package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import org.apache.commons.io.IOUtils;

public class EmptyFileFilter extends AbstractFileFilter implements Serializable {
    public static final IOFileFilter EMPTY;
    public static final IOFileFilter NOT_EMPTY;
    private static final long serialVersionUID = 3631422087512832211L;

    static {
        EmptyFileFilter emptyFileFilter = new EmptyFileFilter();
        EMPTY = emptyFileFilter;
        NOT_EMPTY = emptyFileFilter.negate();
    }

    protected EmptyFileFilter() {
    }

    public boolean accept(File file) {
        if (file.isDirectory()) {
            if (IOUtils.length((Object[]) file.listFiles()) == 0) {
                return true;
            }
            return false;
        } else if (file.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0025, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0026, code lost:
        if (r0 != null) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0030, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.nio.file.FileVisitResult accept(java.nio.file.Path r7, java.nio.file.attribute.BasicFileAttributes r8) {
        /*
            r6 = this;
            r8 = 0
            java.nio.file.LinkOption[] r0 = new java.nio.file.LinkOption[r8]     // Catch:{ IOException -> 0x0041 }
            boolean r0 = java.nio.file.Files.isDirectory(r7, r0)     // Catch:{ IOException -> 0x0041 }
            r1 = 1
            if (r0 == 0) goto L_0x0031
            java.util.stream.Stream r0 = java.nio.file.Files.list(r7)     // Catch:{ IOException -> 0x0041 }
            java.util.Optional r2 = r0.findFirst()     // Catch:{ all -> 0x0023 }
            boolean r2 = r2.isPresent()     // Catch:{ all -> 0x0023 }
            if (r2 != 0) goto L_0x0019
            r8 = r1
        L_0x0019:
            java.nio.file.FileVisitResult r7 = toFileVisitResult(r8, r7)     // Catch:{ all -> 0x0023 }
            if (r0 == 0) goto L_0x0022
            r0.close()     // Catch:{ IOException -> 0x0041 }
        L_0x0022:
            return r7
        L_0x0023:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0025 }
        L_0x0025:
            r8 = move-exception
            if (r0 == 0) goto L_0x0030
            r0.close()     // Catch:{ all -> 0x002c }
            goto L_0x0030
        L_0x002c:
            r0 = move-exception
            r7.addSuppressed(r0)     // Catch:{ IOException -> 0x0041 }
        L_0x0030:
            throw r8     // Catch:{ IOException -> 0x0041 }
        L_0x0031:
            long r2 = java.nio.file.Files.size(r7)     // Catch:{ IOException -> 0x0041 }
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x003c
            r8 = r1
        L_0x003c:
            java.nio.file.FileVisitResult r6 = toFileVisitResult(r8, r7)     // Catch:{ IOException -> 0x0041 }
            return r6
        L_0x0041:
            r7 = move-exception
            java.nio.file.FileVisitResult r6 = r6.handle(r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.filefilter.EmptyFileFilter.accept(java.nio.file.Path, java.nio.file.attribute.BasicFileAttributes):java.nio.file.FileVisitResult");
    }
}
