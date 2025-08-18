package org.apache.poi.poifs.dev;

import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentNode;
import org.apache.poi.poifs.filesystem.Entry;

public class POIFSLister {
    public static void main(String[] strArr) throws IOException {
        if (strArr.length == 0) {
            System.err.println("Must specify at least one file to view");
            System.exit(1);
        }
        boolean z = true;
        boolean z2 = false;
        for (String str : strArr) {
            if (str.equalsIgnoreCase("-size") || str.equalsIgnoreCase("-sizes")) {
                z2 = true;
            } else if (str.equalsIgnoreCase("-old") || str.equalsIgnoreCase("-old-poifs")) {
                z = false;
            } else if (z) {
                viewFile(str, z2);
            } else {
                viewFileOld(str, z2);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0022, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0019, code lost:
        r3 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void viewFile(java.lang.String r2, boolean r3) throws java.io.IOException {
        /*
            org.apache.poi.poifs.filesystem.POIFSFileSystem r0 = new org.apache.poi.poifs.filesystem.POIFSFileSystem
            java.io.File r1 = new java.io.File
            r1.<init>(r2)
            r0.<init>((java.io.File) r1)
            org.apache.poi.poifs.filesystem.DirectoryNode r2 = r0.getRoot()     // Catch:{ all -> 0x0017 }
            java.lang.String r1 = ""
            displayDirectory(r2, r1, r3)     // Catch:{ all -> 0x0017 }
            r0.close()
            return
        L_0x0017:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0019 }
        L_0x0019:
            r3 = move-exception
            r0.close()     // Catch:{ all -> 0x001e }
            goto L_0x0022
        L_0x001e:
            r0 = move-exception
            r2.addSuppressed(r0)
        L_0x0022:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.dev.POIFSLister.viewFile(java.lang.String, boolean):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0021, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r4.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0025, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0028, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x002d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x002e, code lost:
        r3.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0031, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void viewFileOld(java.lang.String r3, boolean r4) throws java.io.IOException {
        /*
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r3)
            org.apache.poi.poifs.filesystem.POIFSFileSystem r3 = new org.apache.poi.poifs.filesystem.POIFSFileSystem     // Catch:{ all -> 0x0026 }
            r3.<init>((java.io.InputStream) r0)     // Catch:{ all -> 0x0026 }
            org.apache.poi.poifs.filesystem.DirectoryNode r1 = r3.getRoot()     // Catch:{ all -> 0x001a }
            java.lang.String r2 = ""
            displayDirectory(r1, r2, r4)     // Catch:{ all -> 0x001a }
            r3.close()     // Catch:{ all -> 0x0026 }
            r0.close()
            return
        L_0x001a:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x001c }
        L_0x001c:
            r1 = move-exception
            r3.close()     // Catch:{ all -> 0x0021 }
            goto L_0x0025
        L_0x0021:
            r3 = move-exception
            r4.addSuppressed(r3)     // Catch:{ all -> 0x0026 }
        L_0x0025:
            throw r1     // Catch:{ all -> 0x0026 }
        L_0x0026:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0028 }
        L_0x0028:
            r4 = move-exception
            r0.close()     // Catch:{ all -> 0x002d }
            goto L_0x0031
        L_0x002d:
            r0 = move-exception
            r3.addSuppressed(r0)
        L_0x0031:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.dev.POIFSLister.viewFileOld(java.lang.String, boolean):void");
    }

    public static void displayDirectory(DirectoryNode directoryNode, String str, boolean z) {
        System.out.println(str + directoryNode.getName() + " -");
        String str2 = str + "  ";
        Iterator<Entry> entries = directoryNode.getEntries();
        boolean z2 = false;
        while (entries.hasNext()) {
            Entry next = entries.next();
            if (next instanceof DirectoryNode) {
                displayDirectory((DirectoryNode) next, str2, z);
            } else {
                DocumentNode documentNode = (DocumentNode) next;
                String name = documentNode.getName();
                if (name.charAt(0) < 10) {
                    name = name.substring(1) + " <" + ("(0x0" + name.charAt(0) + ")" + name.substring(1)) + ">";
                }
                System.out.println(str2 + name + (z ? " [" + documentNode.getSize() + " / 0x" + Integer.toHexString(documentNode.getSize()) + "]" : ""));
            }
            z2 = true;
        }
        if (!z2) {
            System.out.println(str2 + "(no children)");
        }
    }
}
