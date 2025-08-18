package org.apache.poi.poifs.dev;

public final class POIFSDump {
    private POIFSDump() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00fd, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0102, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        r11.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0106, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0109, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x010e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x010f, code lost:
        r11.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0112, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void main(java.lang.String[] r11) throws java.io.IOException {
        /*
            int r0 = r11.length
            r1 = 1
            if (r0 != 0) goto L_0x000e
            java.io.PrintStream r0 = java.lang.System.err
            java.lang.String r2 = "Must specify at least one file to dump"
            r0.println(r2)
            java.lang.System.exit(r1)
        L_0x000e:
            int r0 = r11.length
            r2 = 0
            r3 = r2
            r4 = r3
        L_0x0012:
            if (r2 >= r0) goto L_0x011a
            r5 = r11[r2]
            java.lang.String r6 = "-dumprops"
            boolean r6 = r5.equalsIgnoreCase(r6)
            if (r6 != 0) goto L_0x0115
            java.lang.String r6 = "-dump-props"
            boolean r6 = r5.equalsIgnoreCase(r6)
            if (r6 != 0) goto L_0x0115
            java.lang.String r6 = "-dump-properties"
            boolean r6 = r5.equalsIgnoreCase(r6)
            if (r6 == 0) goto L_0x0030
            goto L_0x0115
        L_0x0030:
            java.lang.String r6 = "-dumpmini"
            boolean r6 = r5.equalsIgnoreCase(r6)
            if (r6 != 0) goto L_0x0113
            java.lang.String r6 = "-dump-mini"
            boolean r6 = r5.equalsIgnoreCase(r6)
            if (r6 != 0) goto L_0x0113
            java.lang.String r6 = "-dump-ministream"
            boolean r6 = r5.equalsIgnoreCase(r6)
            if (r6 != 0) goto L_0x0113
            java.lang.String r6 = "-dump-mini-stream"
            boolean r6 = r5.equalsIgnoreCase(r6)
            if (r6 == 0) goto L_0x0052
            goto L_0x0113
        L_0x0052:
            java.io.PrintStream r6 = java.lang.System.out
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "Dumping "
            r7.<init>(r8)
            java.lang.StringBuilder r7 = r7.append(r5)
            java.lang.String r7 = r7.toString()
            r6.println(r7)
            java.io.FileInputStream r6 = new java.io.FileInputStream
            r6.<init>(r5)
            org.apache.poi.poifs.filesystem.POIFSFileSystem r7 = new org.apache.poi.poifs.filesystem.POIFSFileSystem     // Catch:{ all -> 0x0107 }
            r7.<init>((java.io.InputStream) r6)     // Catch:{ all -> 0x0107 }
            org.apache.poi.poifs.filesystem.DirectoryNode r8 = r7.getRoot()     // Catch:{ all -> 0x00fb }
            java.io.File r9 = new java.io.File     // Catch:{ all -> 0x00fb }
            r9.<init>(r5)     // Catch:{ all -> 0x00fb }
            java.lang.String r5 = r9.getName()     // Catch:{ all -> 0x00fb }
            java.io.File r9 = new java.io.File     // Catch:{ all -> 0x00fb }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x00fb }
            r10.<init>()     // Catch:{ all -> 0x00fb }
            java.lang.StringBuilder r5 = r10.append(r5)     // Catch:{ all -> 0x00fb }
            java.lang.String r10 = "_dump"
            java.lang.StringBuilder r5 = r5.append(r10)     // Catch:{ all -> 0x00fb }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x00fb }
            r9.<init>(r5)     // Catch:{ all -> 0x00fb }
            java.io.File r5 = new java.io.File     // Catch:{ all -> 0x00fb }
            java.lang.String r10 = r8.getName()     // Catch:{ all -> 0x00fb }
            r5.<init>(r9, r10)     // Catch:{ all -> 0x00fb }
            boolean r9 = r5.exists()     // Catch:{ all -> 0x00fb }
            if (r9 != 0) goto L_0x00c4
            boolean r9 = r5.mkdirs()     // Catch:{ all -> 0x00fb }
            if (r9 == 0) goto L_0x00ab
            goto L_0x00c4
        L_0x00ab:
            java.io.IOException r11 = new java.io.IOException     // Catch:{ all -> 0x00fb }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00fb }
            r0.<init>()     // Catch:{ all -> 0x00fb }
            java.lang.String r1 = "Could not create directory "
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x00fb }
            java.lang.StringBuilder r0 = r0.append(r5)     // Catch:{ all -> 0x00fb }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00fb }
            r11.<init>(r0)     // Catch:{ all -> 0x00fb }
            throw r11     // Catch:{ all -> 0x00fb }
        L_0x00c4:
            dump(r8, r5)     // Catch:{ all -> 0x00fb }
            if (r3 == 0) goto L_0x00d6
            org.apache.poi.poifs.storage.HeaderBlock r8 = r7.getHeaderBlock()     // Catch:{ all -> 0x00fb }
            int r8 = r8.getPropertyStart()     // Catch:{ all -> 0x00fb }
            java.lang.String r9 = "properties"
            dump(r7, r8, r9, r5)     // Catch:{ all -> 0x00fb }
        L_0x00d6:
            if (r4 == 0) goto L_0x00f4
            org.apache.poi.poifs.property.PropertyTable r8 = r7.getPropertyTable()     // Catch:{ all -> 0x00fb }
            org.apache.poi.poifs.property.RootProperty r8 = r8.getRoot()     // Catch:{ all -> 0x00fb }
            int r8 = r8.getStartBlock()     // Catch:{ all -> 0x00fb }
            r9 = -2
            if (r8 != r9) goto L_0x00ef
            java.io.PrintStream r5 = java.lang.System.err     // Catch:{ all -> 0x00fb }
            java.lang.String r8 = "No Mini Stream in file"
            r5.println(r8)     // Catch:{ all -> 0x00fb }
            goto L_0x00f4
        L_0x00ef:
            java.lang.String r9 = "mini-stream"
            dump(r7, r8, r9, r5)     // Catch:{ all -> 0x00fb }
        L_0x00f4:
            r7.close()     // Catch:{ all -> 0x0107 }
            r6.close()
            goto L_0x0116
        L_0x00fb:
            r11 = move-exception
            throw r11     // Catch:{ all -> 0x00fd }
        L_0x00fd:
            r0 = move-exception
            r7.close()     // Catch:{ all -> 0x0102 }
            goto L_0x0106
        L_0x0102:
            r1 = move-exception
            r11.addSuppressed(r1)     // Catch:{ all -> 0x0107 }
        L_0x0106:
            throw r0     // Catch:{ all -> 0x0107 }
        L_0x0107:
            r11 = move-exception
            throw r11     // Catch:{ all -> 0x0109 }
        L_0x0109:
            r0 = move-exception
            r6.close()     // Catch:{ all -> 0x010e }
            goto L_0x0112
        L_0x010e:
            r1 = move-exception
            r11.addSuppressed(r1)
        L_0x0112:
            throw r0
        L_0x0113:
            r4 = r1
            goto L_0x0116
        L_0x0115:
            r3 = r1
        L_0x0116:
            int r2 = r2 + 1
            goto L_0x0012
        L_0x011a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.dev.POIFSDump.main(java.lang.String[]):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003d, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0042, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0043, code lost:
        r4.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0046, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void dump(org.apache.poi.poifs.filesystem.DirectoryEntry r4, java.io.File r5) throws java.io.IOException {
        /*
            java.util.Iterator r4 = r4.getEntries()
        L_0x0004:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x0093
            java.lang.Object r0 = r4.next()
            org.apache.poi.poifs.filesystem.Entry r0 = (org.apache.poi.poifs.filesystem.Entry) r0
            boolean r1 = r0 instanceof org.apache.poi.poifs.filesystem.DocumentNode
            if (r1 == 0) goto L_0x0047
            org.apache.poi.poifs.filesystem.DocumentNode r0 = (org.apache.poi.poifs.filesystem.DocumentNode) r0
            org.apache.poi.poifs.filesystem.DocumentInputStream r1 = new org.apache.poi.poifs.filesystem.DocumentInputStream
            r1.<init>((org.apache.poi.poifs.filesystem.DocumentEntry) r0)
            byte[] r2 = org.apache.poi.util.IOUtils.toByteArray(r1)
            r1.close()
            java.io.FileOutputStream r1 = new java.io.FileOutputStream
            java.io.File r3 = new java.io.File
            java.lang.String r0 = r0.getName()
            java.lang.String r0 = r0.trim()
            r3.<init>(r5, r0)
            r1.<init>(r3)
            r1.write(r2)     // Catch:{ all -> 0x003b }
            r1.close()
            goto L_0x0004
        L_0x003b:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x003d }
        L_0x003d:
            r5 = move-exception
            r1.close()     // Catch:{ all -> 0x0042 }
            goto L_0x0046
        L_0x0042:
            r0 = move-exception
            r4.addSuppressed(r0)
        L_0x0046:
            throw r5
        L_0x0047:
            boolean r1 = r0 instanceof org.apache.poi.poifs.filesystem.DirectoryEntry
            if (r1 == 0) goto L_0x007d
            r1 = r0
            org.apache.poi.poifs.filesystem.DirectoryEntry r1 = (org.apache.poi.poifs.filesystem.DirectoryEntry) r1
            java.io.File r2 = new java.io.File
            java.lang.String r0 = r0.getName()
            r2.<init>(r5, r0)
            boolean r0 = r2.exists()
            if (r0 != 0) goto L_0x0079
            boolean r0 = r2.mkdirs()
            if (r0 == 0) goto L_0x0064
            goto L_0x0079
        L_0x0064:
            java.io.IOException r4 = new java.io.IOException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r0 = "Could not create directory "
            r5.<init>(r0)
            java.lang.StringBuilder r5 = r5.append(r2)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L_0x0079:
            dump(r1, r2)
            goto L_0x0004
        L_0x007d:
            java.io.PrintStream r1 = java.lang.System.err
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Skipping unsupported POIFS entry: "
            r2.<init>(r3)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            r1.println(r0)
            goto L_0x0004
        L_0x0093:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.dev.POIFSDump.dump(org.apache.poi.poifs.filesystem.DirectoryEntry, java.io.File):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003e, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0043, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0044, code lost:
        r1.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0047, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void dump(org.apache.poi.poifs.filesystem.POIFSFileSystem r1, int r2, java.lang.String r3, java.io.File r4) throws java.io.IOException {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r4, r3)
            java.io.FileOutputStream r3 = new java.io.FileOutputStream
            r3.<init>(r0)
            org.apache.poi.poifs.filesystem.POIFSStream r4 = new org.apache.poi.poifs.filesystem.POIFSStream     // Catch:{ all -> 0x003c }
            r4.<init>(r1, r2)     // Catch:{ all -> 0x003c }
            int r1 = r1.getBigBlockSize()     // Catch:{ all -> 0x003c }
            long r1 = (long) r1     // Catch:{ all -> 0x003c }
            int r0 = org.apache.poi.poifs.filesystem.POIFSFileSystem.getMaxRecordLength()     // Catch:{ all -> 0x003c }
            byte[] r1 = org.apache.poi.util.IOUtils.safelyAllocate(r1, r0)     // Catch:{ all -> 0x003c }
            java.util.Iterator r2 = r4.iterator()     // Catch:{ all -> 0x003c }
        L_0x0020:
            boolean r4 = r2.hasNext()     // Catch:{ all -> 0x003c }
            if (r4 == 0) goto L_0x0038
            java.lang.Object r4 = r2.next()     // Catch:{ all -> 0x003c }
            java.nio.ByteBuffer r4 = (java.nio.ByteBuffer) r4     // Catch:{ all -> 0x003c }
            int r0 = r4.remaining()     // Catch:{ all -> 0x003c }
            r4.get(r1)     // Catch:{ all -> 0x003c }
            r4 = 0
            r3.write(r1, r4, r0)     // Catch:{ all -> 0x003c }
            goto L_0x0020
        L_0x0038:
            r3.close()
            return
        L_0x003c:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x003e }
        L_0x003e:
            r2 = move-exception
            r3.close()     // Catch:{ all -> 0x0043 }
            goto L_0x0047
        L_0x0043:
            r3 = move-exception
            r1.addSuppressed(r3)
        L_0x0047:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.dev.POIFSDump.dump(org.apache.poi.poifs.filesystem.POIFSFileSystem, int, java.lang.String, java.io.File):void");
    }
}
