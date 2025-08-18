package org.apache.poi.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;

public class DefaultTempFileCreationStrategy implements TempFileCreationStrategy {
    public static final String DELETE_FILES_ON_EXIT = "poi.delete.tmp.files.on.exit";
    public static final String POIFILES = "poifiles";
    private File dir;

    public DefaultTempFileCreationStrategy() {
        this((File) null);
    }

    public DefaultTempFileCreationStrategy(File file) {
        this.dir = file;
    }

    private void createPOIFilesDirectory() throws IOException {
        if (this.dir == null) {
            String property = System.getProperty(TempFile.JAVA_IO_TMPDIR);
            if (property != null) {
                this.dir = new File(property, POIFILES);
            } else {
                throw new IOException("Systems temporary directory not defined - set the -Djava.io.tmpdir jvm property!");
            }
        }
        createTempDirectory(this.dir);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0017  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0038  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void createTempDirectory(java.io.File r4) throws java.io.IOException {
        /*
            r3 = this;
            java.lang.String r0 = "Could not create temporary directory. '"
            java.lang.String r1 = "Could not create temporary directory '"
            monitor-enter(r3)
            boolean r2 = r4.exists()     // Catch:{ all -> 0x0051 }
            if (r2 != 0) goto L_0x0014
            boolean r2 = r4.mkdirs()     // Catch:{ all -> 0x0051 }
            if (r2 == 0) goto L_0x0012
            goto L_0x0014
        L_0x0012:
            r2 = 0
            goto L_0x0015
        L_0x0014:
            r2 = 1
        L_0x0015:
            if (r2 == 0) goto L_0x0038
            boolean r1 = r4.isDirectory()     // Catch:{ all -> 0x0051 }
            if (r1 == 0) goto L_0x001f
            monitor-exit(r3)
            return
        L_0x001f:
            java.io.IOException r1 = new java.io.IOException     // Catch:{ all -> 0x0051 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0051 }
            r2.<init>(r0)     // Catch:{ all -> 0x0051 }
            java.lang.StringBuilder r4 = r2.append(r4)     // Catch:{ all -> 0x0051 }
            java.lang.String r0 = "' exists but is not a directory."
            java.lang.StringBuilder r4 = r4.append(r0)     // Catch:{ all -> 0x0051 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0051 }
            r1.<init>(r4)     // Catch:{ all -> 0x0051 }
            throw r1     // Catch:{ all -> 0x0051 }
        L_0x0038:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0051 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0051 }
            r2.<init>(r1)     // Catch:{ all -> 0x0051 }
            java.lang.StringBuilder r4 = r2.append(r4)     // Catch:{ all -> 0x0051 }
            java.lang.String r1 = "'"
            java.lang.StringBuilder r4 = r4.append(r1)     // Catch:{ all -> 0x0051 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0051 }
            r0.<init>(r4)     // Catch:{ all -> 0x0051 }
            throw r0     // Catch:{ all -> 0x0051 }
        L_0x0051:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.util.DefaultTempFileCreationStrategy.createTempDirectory(java.io.File):void");
    }

    public File createTempFile(String str, String str2) throws IOException {
        createPOIFilesDirectory();
        File file = Files.createTempFile(this.dir.toPath(), str, str2, new FileAttribute[0]).toFile();
        if (System.getProperty(DELETE_FILES_ON_EXIT) != null) {
            file.deleteOnExit();
        }
        return file;
    }

    public File createTempDirectory(String str) throws IOException {
        createPOIFilesDirectory();
        File file = new File(this.dir, str + Long.toString(RandomSingleton.getInstance().nextLong()));
        createTempDirectory(file);
        file.deleteOnExit();
        return file;
    }
}
