package org.apache.poi.poifs.eventfilesystem;

import java.io.File;
import java.io.IOException;
import org.apache.poi.poifs.filesystem.POIFSDocumentPath;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class POIFSReader {
    private boolean notifyEmptyDirectories;
    private final POIFSReaderRegistry registry = new POIFSReaderRegistry();
    private boolean registryClosed = false;

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0013, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0014, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0017, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000e, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void read(java.io.InputStream r2) throws java.io.IOException {
        /*
            r1 = this;
            org.apache.poi.poifs.filesystem.POIFSFileSystem r0 = new org.apache.poi.poifs.filesystem.POIFSFileSystem
            r0.<init>((java.io.InputStream) r2)
            r1.read((org.apache.poi.poifs.filesystem.POIFSFileSystem) r0)     // Catch:{ all -> 0x000c }
            r0.close()
            return
        L_0x000c:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x000e }
        L_0x000e:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x0013 }
            goto L_0x0017
        L_0x0013:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x0017:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.eventfilesystem.POIFSReader.read(java.io.InputStream):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0014, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0015, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0018, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
        r3 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void read(java.io.File r3) throws java.io.IOException {
        /*
            r2 = this;
            org.apache.poi.poifs.filesystem.POIFSFileSystem r0 = new org.apache.poi.poifs.filesystem.POIFSFileSystem
            r1 = 1
            r0.<init>((java.io.File) r3, (boolean) r1)
            r2.read((org.apache.poi.poifs.filesystem.POIFSFileSystem) r0)     // Catch:{ all -> 0x000d }
            r0.close()
            return
        L_0x000d:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x000f }
        L_0x000f:
            r3 = move-exception
            r0.close()     // Catch:{ all -> 0x0014 }
            goto L_0x0018
        L_0x0014:
            r0 = move-exception
            r2.addSuppressed(r0)
        L_0x0018:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.eventfilesystem.POIFSReader.read(java.io.File):void");
    }

    public void read(POIFSFileSystem pOIFSFileSystem) throws IOException {
        this.registryClosed = true;
        processProperties(pOIFSFileSystem, pOIFSFileSystem.getPropertyTable().getRoot(), new POIFSDocumentPath());
    }

    public void registerListener(POIFSReaderListener pOIFSReaderListener) {
        pOIFSReaderListener.getClass();
        if (!this.registryClosed) {
            this.registry.registerListener(pOIFSReaderListener);
            return;
        }
        throw new IllegalStateException();
    }

    public void registerListener(POIFSReaderListener pOIFSReaderListener, String str) {
        registerListener(pOIFSReaderListener, (POIFSDocumentPath) null, str);
    }

    public void registerListener(POIFSReaderListener pOIFSReaderListener, POIFSDocumentPath pOIFSDocumentPath, String str) {
        if (pOIFSReaderListener == null || str == null || str.length() == 0) {
            throw null;
        } else if (!this.registryClosed) {
            POIFSReaderRegistry pOIFSReaderRegistry = this.registry;
            if (pOIFSDocumentPath == null) {
                pOIFSDocumentPath = new POIFSDocumentPath();
            }
            pOIFSReaderRegistry.registerListener(pOIFSReaderListener, pOIFSDocumentPath, str);
        } else {
            throw new IllegalStateException();
        }
    }

    public void setNotifyEmptyDirectories(boolean z) {
        this.notifyEmptyDirectories = z;
    }

    public static void main(String[] strArr) throws IOException {
        if (strArr.length == 0) {
            System.err.println("at least one argument required: input filename(s)");
            System.exit(1);
        }
        for (String str : strArr) {
            POIFSReader pOIFSReader = new POIFSReader();
            pOIFSReader.registerListener(new POIFSReader$$ExternalSyntheticLambda0());
            System.out.println("reading " + str);
            pOIFSReader.read(new File(str));
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0054, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0055, code lost:
        if (r2 != null) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005f, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void readEntry(org.apache.poi.poifs.eventfilesystem.POIFSReaderEvent r7) {
        /*
            org.apache.poi.poifs.filesystem.POIFSDocumentPath r0 = r7.getPath()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            org.apache.poi.poifs.filesystem.DocumentInputStream r2 = r7.getStream()     // Catch:{ IOException -> 0x0060 }
            r3 = 0
            r1.setLength(r3)     // Catch:{ all -> 0x0052 }
            int r4 = r0.length()     // Catch:{ all -> 0x0052 }
        L_0x0015:
            r5 = 47
            if (r3 >= r4) goto L_0x0027
            java.lang.StringBuilder r5 = r1.append(r5)     // Catch:{ all -> 0x0052 }
            java.lang.String r6 = r0.getComponent(r3)     // Catch:{ all -> 0x0052 }
            r5.append(r6)     // Catch:{ all -> 0x0052 }
            int r3 = r3 + 1
            goto L_0x0015
        L_0x0027:
            byte[] r0 = org.apache.poi.util.IOUtils.toByteArray(r2)     // Catch:{ all -> 0x0052 }
            java.lang.StringBuilder r3 = r1.append(r5)     // Catch:{ all -> 0x0052 }
            java.lang.String r7 = r7.getName()     // Catch:{ all -> 0x0052 }
            java.lang.StringBuilder r7 = r3.append(r7)     // Catch:{ all -> 0x0052 }
            java.lang.String r3 = ": "
            java.lang.StringBuilder r7 = r7.append(r3)     // Catch:{ all -> 0x0052 }
            int r0 = r0.length     // Catch:{ all -> 0x0052 }
            java.lang.StringBuilder r7 = r7.append(r0)     // Catch:{ all -> 0x0052 }
            java.lang.String r0 = " bytes read"
            r7.append(r0)     // Catch:{ all -> 0x0052 }
            java.io.PrintStream r7 = java.lang.System.out     // Catch:{ all -> 0x0052 }
            r7.println(r1)     // Catch:{ all -> 0x0052 }
            if (r2 == 0) goto L_0x0060
            r2.close()     // Catch:{ IOException -> 0x0060 }
            goto L_0x0060
        L_0x0052:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0054 }
        L_0x0054:
            r0 = move-exception
            if (r2 == 0) goto L_0x005f
            r2.close()     // Catch:{ all -> 0x005b }
            goto L_0x005f
        L_0x005b:
            r1 = move-exception
            r7.addSuppressed(r1)     // Catch:{ IOException -> 0x0060 }
        L_0x005f:
            throw r0     // Catch:{ IOException -> 0x0060 }
        L_0x0060:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.eventfilesystem.POIFSReader.readEntry(org.apache.poi.poifs.eventfilesystem.POIFSReaderEvent):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0064, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0069, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006a, code lost:
        r11.addSuppressed(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006d, code lost:
        throw r12;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void processProperties(org.apache.poi.poifs.filesystem.POIFSFileSystem r12, org.apache.poi.poifs.property.DirectoryProperty r13, org.apache.poi.poifs.filesystem.POIFSDocumentPath r14) {
        /*
            r11 = this;
            java.util.Iterator r0 = r13.iterator()
            r1 = 0
            r2 = r1
        L_0x0006:
            boolean r3 = r0.hasNext()
            r4 = 0
            if (r3 == 0) goto L_0x0070
            java.lang.Object r2 = r0.next()
            org.apache.poi.poifs.property.Property r2 = (org.apache.poi.poifs.property.Property) r2
            java.lang.String r3 = r2.getName()
            boolean r5 = r2.isDirectory()
            r6 = 1
            if (r5 == 0) goto L_0x002d
            org.apache.poi.poifs.filesystem.POIFSDocumentPath r4 = new org.apache.poi.poifs.filesystem.POIFSDocumentPath
            java.lang.String[] r5 = new java.lang.String[r6]
            r5[r1] = r3
            r4.<init>(r14, r5)
            org.apache.poi.poifs.property.DirectoryProperty r2 = (org.apache.poi.poifs.property.DirectoryProperty) r2
            r11.processProperties(r12, r2, r4)
            goto L_0x006e
        L_0x002d:
            org.apache.poi.poifs.eventfilesystem.POIFSReaderRegistry r5 = r11.registry
            java.lang.Iterable r5 = r5.getListeners(r14, r3)
            java.util.Iterator r5 = r5.iterator()
        L_0x0037:
            boolean r7 = r5.hasNext()
            if (r7 == 0) goto L_0x006e
            java.lang.Object r7 = r5.next()
            org.apache.poi.poifs.eventfilesystem.POIFSReaderListener r7 = (org.apache.poi.poifs.eventfilesystem.POIFSReaderListener) r7
            if (r4 != 0) goto L_0x004d
            org.apache.poi.poifs.filesystem.POIFSDocument r4 = new org.apache.poi.poifs.filesystem.POIFSDocument
            r8 = r2
            org.apache.poi.poifs.property.DocumentProperty r8 = (org.apache.poi.poifs.property.DocumentProperty) r8
            r4.<init>(r8, r12)
        L_0x004d:
            org.apache.poi.poifs.filesystem.DocumentInputStream r8 = new org.apache.poi.poifs.filesystem.DocumentInputStream
            r8.<init>((org.apache.poi.poifs.filesystem.POIFSDocument) r4)
            org.apache.poi.poifs.eventfilesystem.POIFSReaderEvent r9 = new org.apache.poi.poifs.eventfilesystem.POIFSReaderEvent     // Catch:{ all -> 0x0062 }
            org.apache.poi.hpsf.ClassID r10 = r13.getStorageClsid()     // Catch:{ all -> 0x0062 }
            r9.<init>(r8, r14, r3, r10)     // Catch:{ all -> 0x0062 }
            r7.processPOIFSReaderEvent(r9)     // Catch:{ all -> 0x0062 }
            r8.close()
            goto L_0x0037
        L_0x0062:
            r11 = move-exception
            throw r11     // Catch:{ all -> 0x0064 }
        L_0x0064:
            r12 = move-exception
            r8.close()     // Catch:{ all -> 0x0069 }
            goto L_0x006d
        L_0x0069:
            r13 = move-exception
            r11.addSuppressed(r13)
        L_0x006d:
            throw r12
        L_0x006e:
            r2 = r6
            goto L_0x0006
        L_0x0070:
            if (r2 != 0) goto L_0x009c
            boolean r12 = r11.notifyEmptyDirectories
            if (r12 != 0) goto L_0x0077
            goto L_0x009c
        L_0x0077:
            org.apache.poi.poifs.eventfilesystem.POIFSReaderRegistry r11 = r11.registry
            java.lang.String r12 = "."
            java.lang.Iterable r11 = r11.getListeners(r14, r12)
            java.util.Iterator r11 = r11.iterator()
        L_0x0083:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto L_0x009c
            java.lang.Object r12 = r11.next()
            org.apache.poi.poifs.eventfilesystem.POIFSReaderListener r12 = (org.apache.poi.poifs.eventfilesystem.POIFSReaderListener) r12
            org.apache.poi.poifs.eventfilesystem.POIFSReaderEvent r0 = new org.apache.poi.poifs.eventfilesystem.POIFSReaderEvent
            org.apache.poi.hpsf.ClassID r1 = r13.getStorageClsid()
            r0.<init>(r4, r14, r4, r1)
            r12.processPOIFSReaderEvent(r0)
            goto L_0x0083
        L_0x009c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.eventfilesystem.POIFSReader.processProperties(org.apache.poi.poifs.filesystem.POIFSFileSystem, org.apache.poi.poifs.property.DirectoryProperty, org.apache.poi.poifs.filesystem.POIFSDocumentPath):void");
    }
}
