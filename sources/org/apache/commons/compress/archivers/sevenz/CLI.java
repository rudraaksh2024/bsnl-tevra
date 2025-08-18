package org.apache.commons.compress.archivers.sevenz;

import java.io.IOException;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;

public class CLI {

    private enum Mode {
        LIST("Analysing") {
            public void takeAction(SevenZFile sevenZFile, SevenZArchiveEntry sevenZArchiveEntry) {
                System.out.print(sevenZArchiveEntry.getName());
                if (sevenZArchiveEntry.isDirectory()) {
                    System.out.print(" dir");
                } else {
                    System.out.print(" " + sevenZArchiveEntry.getCompressedSize() + PackagingURIHelper.FORWARD_SLASH_STRING + sevenZArchiveEntry.getSize());
                }
                if (sevenZArchiveEntry.getHasLastModifiedDate()) {
                    System.out.print(" " + sevenZArchiveEntry.getLastModifiedDate());
                } else {
                    System.out.print(" no last modified date");
                }
                if (!sevenZArchiveEntry.isDirectory()) {
                    System.out.println(" " + getContentMethods(sevenZArchiveEntry));
                } else {
                    System.out.println();
                }
            }

            private String getContentMethods(SevenZArchiveEntry sevenZArchiveEntry) {
                StringBuilder sb = new StringBuilder();
                boolean z = true;
                for (SevenZMethodConfiguration sevenZMethodConfiguration : sevenZArchiveEntry.getContentMethods()) {
                    if (!z) {
                        sb.append(", ");
                    }
                    sb.append(sevenZMethodConfiguration.getMethod());
                    if (sevenZMethodConfiguration.getOptions() != null) {
                        sb.append("(").append(sevenZMethodConfiguration.getOptions()).append(")");
                    }
                    z = false;
                }
                return sb.toString();
            }
        };
        
        private final String message;

        public abstract void takeAction(SevenZFile sevenZFile, SevenZArchiveEntry sevenZArchiveEntry) throws IOException;

        private Mode(String str) {
            this.message = str;
        }

        public String getMessage() {
            return this.message;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0068, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006d, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006e, code lost:
        r0.addSuppressed(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0071, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void main(java.lang.String[] r5) throws java.lang.Exception {
        /*
            int r0 = r5.length
            if (r0 != 0) goto L_0x0007
            usage()
            return
        L_0x0007:
            org.apache.commons.compress.archivers.sevenz.CLI$Mode r0 = grabMode(r5)
            java.io.PrintStream r1 = java.lang.System.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r0.getMessage()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = " "
            java.lang.StringBuilder r2 = r2.append(r3)
            r3 = 0
            r4 = r5[r3]
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            r1.println(r2)
            java.io.File r1 = new java.io.File
            r5 = r5[r3]
            r1.<init>(r5)
            boolean r5 = r1.isFile()
            if (r5 != 0) goto L_0x0053
            java.io.PrintStream r5 = java.lang.System.err
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.StringBuilder r2 = r2.append(r1)
            java.lang.String r3 = " doesn't exist or is a directory"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r5.println(r2)
        L_0x0053:
            org.apache.commons.compress.archivers.sevenz.SevenZFile r5 = new org.apache.commons.compress.archivers.sevenz.SevenZFile
            r5.<init>((java.io.File) r1)
        L_0x0058:
            org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry r1 = r5.getNextEntry()     // Catch:{ all -> 0x0066 }
            if (r1 == 0) goto L_0x0062
            r0.takeAction(r5, r1)     // Catch:{ all -> 0x0066 }
            goto L_0x0058
        L_0x0062:
            r5.close()
            return
        L_0x0066:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0068 }
        L_0x0068:
            r1 = move-exception
            r5.close()     // Catch:{ all -> 0x006d }
            goto L_0x0071
        L_0x006d:
            r5 = move-exception
            r0.addSuppressed(r5)
        L_0x0071:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.sevenz.CLI.main(java.lang.String[]):void");
    }

    private static void usage() {
        System.out.println("Parameters: archive-name [list]");
    }

    private static Mode grabMode(String[] strArr) {
        if (strArr.length < 2) {
            return Mode.LIST;
        }
        return (Mode) Enum.valueOf(Mode.class, strArr[1].toUpperCase());
    }
}
