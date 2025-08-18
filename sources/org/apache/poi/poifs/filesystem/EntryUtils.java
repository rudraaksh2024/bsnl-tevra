package org.apache.poi.poifs.filesystem;

import java.io.EOFException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.apache.poi.util.Internal;

@Internal
public final class EntryUtils {

    private interface POIDelegate {
    }

    private EntryUtils() {
    }

    @Internal
    public static void copyNodeRecursively(Entry entry, DirectoryEntry directoryEntry) throws IOException {
        if (entry.isDirectoryEntry()) {
            DirectoryEntry directoryEntry2 = (DirectoryEntry) entry;
            DirectoryEntry createDirectory = directoryEntry.createDirectory(entry.getName());
            createDirectory.setStorageClsid(directoryEntry2.getStorageClsid());
            Iterator<Entry> entries = directoryEntry2.getEntries();
            while (entries.hasNext()) {
                copyNodeRecursively(entries.next(), createDirectory);
            }
            return;
        }
        DocumentEntry documentEntry = (DocumentEntry) entry;
        DocumentInputStream documentInputStream = new DocumentInputStream(documentEntry);
        directoryEntry.createDocument(documentEntry.getName(), documentInputStream);
        documentInputStream.close();
    }

    public static void copyNodes(DirectoryEntry directoryEntry, DirectoryEntry directoryEntry2) throws IOException {
        Iterator it = directoryEntry.iterator();
        while (it.hasNext()) {
            copyNodeRecursively((Entry) it.next(), directoryEntry2);
        }
    }

    public static void copyNodes(POIFSFileSystem pOIFSFileSystem, POIFSFileSystem pOIFSFileSystem2) throws IOException {
        copyNodes((DirectoryEntry) pOIFSFileSystem.getRoot(), (DirectoryEntry) pOIFSFileSystem2.getRoot());
    }

    public static void copyNodes(POIFSFileSystem pOIFSFileSystem, POIFSFileSystem pOIFSFileSystem2, List<String> list) throws IOException {
        copyNodes((DirectoryEntry) new FilteringDirectoryNode(pOIFSFileSystem.getRoot(), list), (DirectoryEntry) new FilteringDirectoryNode(pOIFSFileSystem2.getRoot(), list));
    }

    public static boolean areDirectoriesIdentical(DirectoryEntry directoryEntry, DirectoryEntry directoryEntry2) {
        return new DirectoryDelegate(directoryEntry).equals(new DirectoryDelegate(directoryEntry2));
    }

    public static boolean areDocumentsIdentical(DocumentEntry documentEntry, DocumentEntry documentEntry2) throws IOException {
        try {
            return new DocumentDelegate(documentEntry).equals(new DocumentDelegate(documentEntry2));
        } catch (RuntimeException e) {
            if (e.getCause() instanceof IOException) {
                throw ((IOException) e.getCause());
            }
            throw e;
        }
    }

    private static class DirectoryDelegate implements POIDelegate {
        final DirectoryEntry dir;

        DirectoryDelegate(DirectoryEntry directoryEntry) {
            this.dir = directoryEntry;
        }

        private Map<String, POIDelegate> entries() {
            return (Map) StreamSupport.stream(this.dir.spliterator(), false).collect(Collectors.toMap(new EntryUtils$DirectoryDelegate$$ExternalSyntheticLambda0(), new EntryUtils$DirectoryDelegate$$ExternalSyntheticLambda1()));
        }

        /* access modifiers changed from: private */
        public static POIDelegate toDelegate(Entry entry) {
            return entry.isDirectoryEntry() ? new DirectoryDelegate((DirectoryEntry) entry) : new DocumentDelegate((DocumentEntry) entry);
        }

        public int hashCode() {
            return this.dir.getName().hashCode();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof DirectoryDelegate)) {
                return false;
            }
            DirectoryDelegate directoryDelegate = (DirectoryDelegate) obj;
            if (this == directoryDelegate) {
                return true;
            }
            if (Objects.equals(this.dir.getName(), directoryDelegate.dir.getName()) && this.dir.getEntryCount() == directoryDelegate.dir.getEntryCount() && this.dir.getStorageClsid().equals(directoryDelegate.dir.getStorageClsid())) {
                return entries().equals(directoryDelegate.entries());
            }
            return false;
        }
    }

    private static class DocumentDelegate implements POIDelegate {
        final DocumentEntry doc;

        DocumentDelegate(DocumentEntry documentEntry) {
            this.doc = documentEntry;
        }

        public int hashCode() {
            return this.doc.getName().hashCode();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0059, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
            r3.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x005e, code lost:
            r3 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
            r4.addSuppressed(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x0062, code lost:
            throw r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x0065, code lost:
            r4 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
            r0.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x006e, code lost:
            throw r4;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r4) {
            /*
                r3 = this;
                boolean r0 = r4 instanceof org.apache.poi.poifs.filesystem.EntryUtils.DocumentDelegate
                r1 = 0
                if (r0 != 0) goto L_0x0006
                return r1
            L_0x0006:
                org.apache.poi.poifs.filesystem.EntryUtils$DocumentDelegate r4 = (org.apache.poi.poifs.filesystem.EntryUtils.DocumentDelegate) r4
                if (r3 != r4) goto L_0x000c
                r3 = 1
                return r3
            L_0x000c:
                org.apache.poi.poifs.filesystem.DocumentEntry r0 = r3.doc
                java.lang.String r0 = r0.getName()
                org.apache.poi.poifs.filesystem.DocumentEntry r2 = r4.doc
                java.lang.String r2 = r2.getName()
                boolean r0 = java.util.Objects.equals(r0, r2)
                if (r0 != 0) goto L_0x001f
                return r1
            L_0x001f:
                org.apache.poi.poifs.filesystem.DocumentInputStream r0 = new org.apache.poi.poifs.filesystem.DocumentInputStream     // Catch:{ IOException | NoPropertySetStreamException -> 0x006f }
                org.apache.poi.poifs.filesystem.DocumentEntry r3 = r3.doc     // Catch:{ IOException | NoPropertySetStreamException -> 0x006f }
                r0.<init>((org.apache.poi.poifs.filesystem.DocumentEntry) r3)     // Catch:{ IOException | NoPropertySetStreamException -> 0x006f }
                org.apache.poi.poifs.filesystem.DocumentInputStream r3 = new org.apache.poi.poifs.filesystem.DocumentInputStream     // Catch:{ all -> 0x0063 }
                org.apache.poi.poifs.filesystem.DocumentEntry r4 = r4.doc     // Catch:{ all -> 0x0063 }
                r3.<init>((org.apache.poi.poifs.filesystem.DocumentEntry) r4)     // Catch:{ all -> 0x0063 }
                boolean r4 = org.apache.poi.hpsf.PropertySet.isPropertySetStream(r0)     // Catch:{ all -> 0x0057 }
                if (r4 == 0) goto L_0x004c
                boolean r4 = org.apache.poi.hpsf.PropertySet.isPropertySetStream(r3)     // Catch:{ all -> 0x0057 }
                if (r4 == 0) goto L_0x004c
                org.apache.poi.hpsf.PropertySet r4 = org.apache.poi.hpsf.PropertySetFactory.create(r0)     // Catch:{ all -> 0x0057 }
                org.apache.poi.hpsf.PropertySet r1 = org.apache.poi.hpsf.PropertySetFactory.create(r3)     // Catch:{ all -> 0x0057 }
                boolean r4 = r4.equals(r1)     // Catch:{ all -> 0x0057 }
                r3.close()     // Catch:{ all -> 0x0063 }
                r0.close()     // Catch:{ IOException | NoPropertySetStreamException -> 0x006f }
                return r4
            L_0x004c:
                boolean r4 = isEqual(r0, r3)     // Catch:{ all -> 0x0057 }
                r3.close()     // Catch:{ all -> 0x0063 }
                r0.close()     // Catch:{ IOException | NoPropertySetStreamException -> 0x006f }
                return r4
            L_0x0057:
                r4 = move-exception
                throw r4     // Catch:{ all -> 0x0059 }
            L_0x0059:
                r1 = move-exception
                r3.close()     // Catch:{ all -> 0x005e }
                goto L_0x0062
            L_0x005e:
                r3 = move-exception
                r4.addSuppressed(r3)     // Catch:{ all -> 0x0063 }
            L_0x0062:
                throw r1     // Catch:{ all -> 0x0063 }
            L_0x0063:
                r3 = move-exception
                throw r3     // Catch:{ all -> 0x0065 }
            L_0x0065:
                r4 = move-exception
                r0.close()     // Catch:{ all -> 0x006a }
                goto L_0x006e
            L_0x006a:
                r0 = move-exception
                r3.addSuppressed(r0)     // Catch:{ IOException | NoPropertySetStreamException -> 0x006f }
            L_0x006e:
                throw r4     // Catch:{ IOException | NoPropertySetStreamException -> 0x006f }
            L_0x006f:
                r3 = move-exception
                java.lang.RuntimeException r4 = new java.lang.RuntimeException
                r4.<init>(r3)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.filesystem.EntryUtils.DocumentDelegate.equals(java.lang.Object):boolean");
        }

        private static boolean isEqual(DocumentInputStream documentInputStream, DocumentInputStream documentInputStream2) throws IOException {
            byte[] bArr = new byte[4096];
            byte[] bArr2 = new byte[4096];
            while (true) {
                try {
                    int read = documentInputStream.read(bArr);
                    if (read > 0) {
                        documentInputStream2.readFully(bArr2, 0, read);
                        int i = 0;
                        while (true) {
                            if (i < read) {
                                if (bArr[i] != bArr2[i]) {
                                    return false;
                                }
                                i++;
                            }
                        }
                    } else if (documentInputStream2.read() < 0) {
                        return true;
                    } else {
                        return false;
                    }
                } catch (EOFException | RuntimeException unused) {
                    return false;
                }
            }
        }
    }
}
