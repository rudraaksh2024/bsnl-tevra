package org.apache.poi;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.PropertySet;
import org.apache.poi.hpsf.PropertySetFactory;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.Internal;

public abstract class POIDocument implements Closeable {
    private static final Logger LOG = LogManager.getLogger((Class<?>) POIDocument.class);
    private DirectoryNode directory;
    private DocumentSummaryInformation dsInf;
    private boolean initialized;
    private SummaryInformation sInf;

    /* access modifiers changed from: protected */
    public String getEncryptedPropertyStreamName() {
        return "encryption";
    }

    public EncryptionInfo getEncryptionInfo() throws IOException {
        return null;
    }

    public abstract void write() throws IOException;

    public abstract void write(File file) throws IOException;

    public abstract void write(OutputStream outputStream) throws IOException;

    protected POIDocument(DirectoryNode directoryNode) {
        this.directory = directoryNode;
    }

    protected POIDocument(POIFSFileSystem pOIFSFileSystem) {
        this(pOIFSFileSystem.getRoot());
    }

    public DocumentSummaryInformation getDocumentSummaryInformation() {
        if (!this.initialized) {
            readProperties();
        }
        return this.dsInf;
    }

    public SummaryInformation getSummaryInformation() {
        if (!this.initialized) {
            readProperties();
        }
        return this.sInf;
    }

    public void createInformationProperties() {
        if (!this.initialized) {
            readProperties();
        }
        if (this.sInf == null) {
            this.sInf = PropertySetFactory.newSummaryInformation();
        }
        if (this.dsInf == null) {
            this.dsInf = PropertySetFactory.newDocumentSummaryInformation();
        }
    }

    @Internal
    public void readProperties() {
        if (!this.initialized) {
            DocumentSummaryInformation documentSummaryInformation = (DocumentSummaryInformation) readPropertySet(DocumentSummaryInformation.class, DocumentSummaryInformation.DEFAULT_STREAM_NAME);
            if (documentSummaryInformation != null) {
                this.dsInf = documentSummaryInformation;
            }
            SummaryInformation summaryInformation = (SummaryInformation) readPropertySet(SummaryInformation.class, SummaryInformation.DEFAULT_STREAM_NAME);
            if (summaryInformation != null) {
                this.sInf = summaryInformation;
            }
            this.initialized = true;
        }
    }

    private <T> T readPropertySet(Class<T> cls, String str) {
        String substring = cls.getName().substring(cls.getName().lastIndexOf(46) + 1);
        try {
            T propertySet = getPropertySet(str);
            if (cls.isInstance(propertySet)) {
                return propertySet;
            }
            if (propertySet != null) {
                LOG.atWarn().log("{} property set came back with wrong class - {}", substring, propertySet.getClass().getName());
                return null;
            }
            LOG.atWarn().log("{} property set came back as null", (Object) substring);
            return null;
        } catch (IOException e) {
            LOG.atError().withThrowable(e).log("can't retrieve property set");
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public PropertySet getPropertySet(String str) throws IOException {
        return getPropertySet(str, getEncryptionInfo());
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0045, code lost:
        r9 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004b, code lost:
        r8 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0071, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0072, code lost:
        if (r9 != null) goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x007c, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0080, code lost:
        r8 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0081, code lost:
        r3 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0083, code lost:
        r9 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0084, code lost:
        r3 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0085, code lost:
        r4 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00a3, code lost:
        r8 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00a4, code lost:
        r3 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00a5, code lost:
        throw r8;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004b A[ExcHandler: IOException (e java.io.IOException), Splitter:B:2:0x000b] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0051 A[SYNTHETIC, Splitter:B:21:0x0051] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0080 A[ExcHandler: all (th java.lang.Throwable), PHI: r7 
      PHI: (r7v4 org.apache.poi.poifs.filesystem.POIFSFileSystem) = (r7v5 org.apache.poi.poifs.filesystem.POIFSFileSystem), (r7v5 org.apache.poi.poifs.filesystem.POIFSFileSystem), (r7v5 org.apache.poi.poifs.filesystem.POIFSFileSystem), (r7v5 org.apache.poi.poifs.filesystem.POIFSFileSystem), (r7v5 org.apache.poi.poifs.filesystem.POIFSFileSystem), (r7v11 org.apache.poi.poifs.filesystem.POIFSFileSystem) binds: [B:21:0x0051, B:24:0x0058, B:42:0x0079, B:30:0x0068, B:31:?, B:10:0x0027] A[DONT_GENERATE, DONT_INLINE], Splitter:B:10:0x0027] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00a3 A[Catch:{ all -> 0x0047 }, ExcHandler: IOException (e java.io.IOException), PHI: r7 
      PHI: (r7v1 org.apache.poi.poifs.filesystem.POIFSFileSystem) = (r7v5 org.apache.poi.poifs.filesystem.POIFSFileSystem), (r7v5 org.apache.poi.poifs.filesystem.POIFSFileSystem), (r7v5 org.apache.poi.poifs.filesystem.POIFSFileSystem), (r7v5 org.apache.poi.poifs.filesystem.POIFSFileSystem), (r7v5 org.apache.poi.poifs.filesystem.POIFSFileSystem), (r7v11 org.apache.poi.poifs.filesystem.POIFSFileSystem) binds: [B:21:0x0051, B:24:0x0058, B:42:0x0079, B:30:0x0068, B:31:?, B:10:0x0027] A[DONT_GENERATE, DONT_INLINE], Splitter:B:10:0x0027] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.hpsf.PropertySet getPropertySet(java.lang.String r8, org.apache.poi.poifs.crypt.EncryptionInfo r9) throws java.io.IOException {
        /*
            r7 = this;
            java.lang.String r0 = "can't find encrypted property stream '"
            java.lang.String r1 = "Error "
            org.apache.poi.poifs.filesystem.DirectoryNode r2 = r7.directory
            r3 = 0
            java.lang.String r4 = "getting"
            if (r9 == 0) goto L_0x004d
            boolean r5 = r9.isDocPropsEncrypted()     // Catch:{ IOException -> 0x004b, Exception -> 0x0049 }
            if (r5 == 0) goto L_0x004d
            java.lang.String r5 = "getting encrypted"
            java.lang.String r7 = r7.getEncryptedPropertyStreamName()     // Catch:{ IOException -> 0x004b, Exception -> 0x0045 }
            boolean r6 = r2.hasEntry(r7)     // Catch:{ IOException -> 0x004b, Exception -> 0x0045 }
            if (r6 == 0) goto L_0x002c
            org.apache.poi.poifs.crypt.Decryptor r9 = r9.getDecryptor()     // Catch:{ IOException -> 0x004b, Exception -> 0x0045 }
            org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIDecryptor r9 = (org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIDecryptor) r9     // Catch:{ IOException -> 0x004b, Exception -> 0x0045 }
            org.apache.poi.poifs.filesystem.POIFSFileSystem r7 = r9.getSummaryEntries(r2, r7)     // Catch:{ IOException -> 0x004b, Exception -> 0x0045 }
            org.apache.poi.poifs.filesystem.DirectoryNode r2 = r7.getRoot()     // Catch:{ IOException -> 0x00a3, Exception -> 0x0083, all -> 0x0080 }
            goto L_0x004f
        L_0x002c:
            org.apache.poi.EncryptedDocumentException r9 = new org.apache.poi.EncryptedDocumentException     // Catch:{ IOException -> 0x004b, Exception -> 0x0045 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x004b, Exception -> 0x0045 }
            r2.<init>(r0)     // Catch:{ IOException -> 0x004b, Exception -> 0x0045 }
            java.lang.StringBuilder r7 = r2.append(r7)     // Catch:{ IOException -> 0x004b, Exception -> 0x0045 }
            java.lang.String r0 = "'"
            java.lang.StringBuilder r7 = r7.append(r0)     // Catch:{ IOException -> 0x004b, Exception -> 0x0045 }
            java.lang.String r7 = r7.toString()     // Catch:{ IOException -> 0x004b, Exception -> 0x0045 }
            r9.<init>((java.lang.String) r7)     // Catch:{ IOException -> 0x004b, Exception -> 0x0045 }
            throw r9     // Catch:{ IOException -> 0x004b, Exception -> 0x0045 }
        L_0x0045:
            r9 = move-exception
            goto L_0x0085
        L_0x0047:
            r8 = move-exception
            goto L_0x00a6
        L_0x0049:
            r9 = move-exception
            goto L_0x0086
        L_0x004b:
            r8 = move-exception
            goto L_0x00a5
        L_0x004d:
            r7 = r3
            r5 = r4
        L_0x004f:
            if (r2 == 0) goto L_0x00aa
            boolean r9 = r2.hasEntry(r8)     // Catch:{ IOException -> 0x00a3, Exception -> 0x0083, all -> 0x0080 }
            if (r9 != 0) goto L_0x0058
            goto L_0x00aa
        L_0x0058:
            org.apache.poi.poifs.filesystem.Entry r9 = r2.getEntry(r8)     // Catch:{ IOException -> 0x00a3, Exception -> 0x007d, all -> 0x0080 }
            org.apache.poi.poifs.filesystem.DocumentInputStream r9 = r2.createDocumentInputStream((org.apache.poi.poifs.filesystem.Entry) r9)     // Catch:{ IOException -> 0x00a3, Exception -> 0x007d, all -> 0x0080 }
            java.lang.String r4 = "creating"
            org.apache.poi.hpsf.PropertySet r0 = org.apache.poi.hpsf.PropertySetFactory.create(r9)     // Catch:{ all -> 0x006f }
            if (r9 == 0) goto L_0x006b
            r9.close()     // Catch:{ IOException -> 0x00a3, Exception -> 0x007d, all -> 0x0080 }
        L_0x006b:
            org.apache.poi.util.IOUtils.closeQuietly(r7)
            return r0
        L_0x006f:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0071 }
        L_0x0071:
            r2 = move-exception
            if (r9 == 0) goto L_0x007c
            r9.close()     // Catch:{ all -> 0x0078 }
            goto L_0x007c
        L_0x0078:
            r9 = move-exception
            r0.addSuppressed(r9)     // Catch:{ IOException -> 0x00a3, Exception -> 0x007d, all -> 0x0080 }
        L_0x007c:
            throw r2     // Catch:{ IOException -> 0x00a3, Exception -> 0x007d, all -> 0x0080 }
        L_0x007d:
            r9 = move-exception
            r3 = r7
            goto L_0x0086
        L_0x0080:
            r8 = move-exception
            r3 = r7
            goto L_0x00a6
        L_0x0083:
            r9 = move-exception
            r3 = r7
        L_0x0085:
            r4 = r5
        L_0x0086:
            java.io.IOException r7 = new java.io.IOException     // Catch:{ all -> 0x0047 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0047 }
            r0.<init>(r1)     // Catch:{ all -> 0x0047 }
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ all -> 0x0047 }
            java.lang.String r1 = " property set with name "
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x0047 }
            java.lang.StringBuilder r8 = r0.append(r8)     // Catch:{ all -> 0x0047 }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x0047 }
            r7.<init>(r8, r9)     // Catch:{ all -> 0x0047 }
            throw r7     // Catch:{ all -> 0x0047 }
        L_0x00a3:
            r8 = move-exception
            r3 = r7
        L_0x00a5:
            throw r8     // Catch:{ all -> 0x0047 }
        L_0x00a6:
            org.apache.poi.util.IOUtils.closeQuietly(r3)
            throw r8
        L_0x00aa:
            org.apache.poi.util.IOUtils.closeQuietly(r7)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.POIDocument.getPropertySet(java.lang.String, org.apache.poi.poifs.crypt.EncryptionInfo):org.apache.poi.hpsf.PropertySet");
    }

    /* access modifiers changed from: protected */
    public void writeProperties() throws IOException {
        validateInPlaceWritePossible();
        writeProperties(this.directory.getFileSystem(), (List<String>) null);
    }

    @Internal
    public void writeProperties(POIFSFileSystem pOIFSFileSystem) throws IOException {
        writeProperties(pOIFSFileSystem, (List<String>) null);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0073, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0078, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0079, code lost:
        r7.addSuppressed(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x007c, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeProperties(org.apache.poi.poifs.filesystem.POIFSFileSystem r8, java.util.List<java.lang.String> r9) throws java.io.IOException {
        /*
            r7 = this;
            java.lang.String r0 = "\u0005DocumentSummaryInformation"
            java.lang.String r1 = "\u0005SummaryInformation"
            org.apache.poi.poifs.crypt.EncryptionInfo r2 = r7.getEncryptionInfo()
            if (r2 != 0) goto L_0x000c
            r3 = 0
            goto L_0x0010
        L_0x000c:
            org.apache.poi.poifs.crypt.Encryptor r3 = r2.getEncryptor()
        L_0x0010:
            if (r2 == 0) goto L_0x001e
            boolean r2 = r2.isDocPropsEncrypted()
            if (r2 == 0) goto L_0x001e
            boolean r2 = r3 instanceof org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIEncryptor
            if (r2 == 0) goto L_0x001e
            r2 = 1
            goto L_0x001f
        L_0x001e:
            r2 = 0
        L_0x001f:
            org.apache.poi.poifs.filesystem.POIFSFileSystem r4 = new org.apache.poi.poifs.filesystem.POIFSFileSystem
            r4.<init>()
            if (r2 == 0) goto L_0x0028
            r5 = r4
            goto L_0x0029
        L_0x0028:
            r5 = r8
        L_0x0029:
            org.apache.poi.hpsf.SummaryInformation r6 = r7.getSummaryInformation()     // Catch:{ all -> 0x0071 }
            r7.writePropertySet(r1, r6, r5, r9)     // Catch:{ all -> 0x0071 }
            org.apache.poi.hpsf.DocumentSummaryInformation r6 = r7.getDocumentSummaryInformation()     // Catch:{ all -> 0x0071 }
            r7.writePropertySet(r0, r6, r5, r9)     // Catch:{ all -> 0x0071 }
            if (r2 != 0) goto L_0x003d
            r4.close()
            return
        L_0x003d:
            org.apache.poi.hpsf.DocumentSummaryInformation r9 = org.apache.poi.hpsf.PropertySetFactory.newDocumentSummaryInformation()     // Catch:{ all -> 0x0071 }
            r7.writePropertySet(r0, r9, r8)     // Catch:{ all -> 0x0071 }
            org.apache.poi.poifs.filesystem.DirectoryNode r9 = r8.getRoot()     // Catch:{ all -> 0x0071 }
            boolean r9 = r9.hasEntry(r1)     // Catch:{ all -> 0x0071 }
            if (r9 == 0) goto L_0x0059
            org.apache.poi.poifs.filesystem.DirectoryNode r9 = r8.getRoot()     // Catch:{ all -> 0x0071 }
            org.apache.poi.poifs.filesystem.Entry r9 = r9.getEntry(r1)     // Catch:{ all -> 0x0071 }
            r9.delete()     // Catch:{ all -> 0x0071 }
        L_0x0059:
            org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIEncryptor r3 = (org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIEncryptor) r3     // Catch:{ all -> 0x0071 }
            org.apache.poi.poifs.filesystem.DirectoryNode r8 = r8.getRoot()     // Catch:{ GeneralSecurityException -> 0x006a }
            java.lang.String r7 = r7.getEncryptedPropertyStreamName()     // Catch:{ GeneralSecurityException -> 0x006a }
            r3.setSummaryEntries(r8, r7, r5)     // Catch:{ GeneralSecurityException -> 0x006a }
            r4.close()
            return
        L_0x006a:
            r7 = move-exception
            java.io.IOException r8 = new java.io.IOException     // Catch:{ all -> 0x0071 }
            r8.<init>(r7)     // Catch:{ all -> 0x0071 }
            throw r8     // Catch:{ all -> 0x0071 }
        L_0x0071:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0073 }
        L_0x0073:
            r8 = move-exception
            r4.close()     // Catch:{ all -> 0x0078 }
            goto L_0x007c
        L_0x0078:
            r9 = move-exception
            r7.addSuppressed(r9)
        L_0x007c:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.POIDocument.writeProperties(org.apache.poi.poifs.filesystem.POIFSFileSystem, java.util.List):void");
    }

    private void writePropertySet(String str, PropertySet propertySet, POIFSFileSystem pOIFSFileSystem, List<String> list) throws IOException {
        if (propertySet != null) {
            writePropertySet(str, propertySet, pOIFSFileSystem);
            if (list != null) {
                list.add(str);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0032, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0033, code lost:
        if (r3 != null) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0039, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r4.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003d, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0040, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0049, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void writePropertySet(java.lang.String r2, org.apache.poi.hpsf.PropertySet r3, org.apache.poi.poifs.filesystem.POIFSFileSystem r4) throws java.io.IOException {
        /*
            r1 = this;
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r1 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream     // Catch:{ WritingNotSupportedException -> 0x004a }
            r1.<init>()     // Catch:{ WritingNotSupportedException -> 0x004a }
            org.apache.poi.hpsf.PropertySet r0 = new org.apache.poi.hpsf.PropertySet     // Catch:{ all -> 0x003e }
            r0.<init>((org.apache.poi.hpsf.PropertySet) r3)     // Catch:{ all -> 0x003e }
            r0.write(r1)     // Catch:{ all -> 0x003e }
            java.io.InputStream r3 = r1.toInputStream()     // Catch:{ all -> 0x003e }
            r4.createOrUpdateDocument(r3, r2)     // Catch:{ all -> 0x0030 }
            if (r3 == 0) goto L_0x0019
            r3.close()     // Catch:{ all -> 0x003e }
        L_0x0019:
            org.apache.logging.log4j.Logger r3 = LOG     // Catch:{ all -> 0x003e }
            org.apache.logging.log4j.LogBuilder r3 = r3.atInfo()     // Catch:{ all -> 0x003e }
            java.lang.String r4 = "Wrote property set {} of size {}"
            int r0 = r1.size()     // Catch:{ all -> 0x003e }
            java.lang.StringBuilder r0 = org.apache.logging.log4j.util.Unbox.box((int) r0)     // Catch:{ all -> 0x003e }
            r3.log(r4, r2, r0)     // Catch:{ all -> 0x003e }
            r1.close()     // Catch:{ WritingNotSupportedException -> 0x004a }
            goto L_0x0055
        L_0x0030:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0032 }
        L_0x0032:
            r0 = move-exception
            if (r3 == 0) goto L_0x003d
            r3.close()     // Catch:{ all -> 0x0039 }
            goto L_0x003d
        L_0x0039:
            r3 = move-exception
            r4.addSuppressed(r3)     // Catch:{ all -> 0x003e }
        L_0x003d:
            throw r0     // Catch:{ all -> 0x003e }
        L_0x003e:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0040 }
        L_0x0040:
            r4 = move-exception
            r1.close()     // Catch:{ all -> 0x0045 }
            goto L_0x0049
        L_0x0045:
            r1 = move-exception
            r3.addSuppressed(r1)     // Catch:{ WritingNotSupportedException -> 0x004a }
        L_0x0049:
            throw r4     // Catch:{ WritingNotSupportedException -> 0x004a }
        L_0x004a:
            org.apache.logging.log4j.Logger r1 = LOG
            org.apache.logging.log4j.LogBuilder r1 = r1.atError()
            java.lang.String r3 = "Couldn't write property set with name {} as not supported by HPSF yet"
            r1.log((java.lang.String) r3, (java.lang.Object) r2)
        L_0x0055:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.POIDocument.writePropertySet(java.lang.String, org.apache.poi.hpsf.PropertySet, org.apache.poi.poifs.filesystem.POIFSFileSystem):void");
    }

    /* access modifiers changed from: protected */
    public void validateInPlaceWritePossible() throws IllegalStateException {
        DirectoryNode directoryNode = this.directory;
        if (directoryNode == null) {
            throw new IllegalStateException("Newly created Document, cannot save in-place");
        } else if (directoryNode.getParent() != null) {
            throw new IllegalStateException("This is not the root Document, cannot save embedded resource in-place");
        } else if (this.directory.getFileSystem() == null || !this.directory.getFileSystem().isInPlaceWriteable()) {
            throw new IllegalStateException("Opened read-only or via an InputStream, a Writeable File is required");
        }
    }

    public void close() throws IOException {
        DirectoryNode directoryNode = this.directory;
        if (directoryNode != null && directoryNode.getFileSystem() != null) {
            this.directory.getFileSystem().close();
            clearDirectory();
        }
    }

    @Internal
    public DirectoryNode getDirectory() {
        return this.directory;
    }

    /* access modifiers changed from: protected */
    @Internal
    public void clearDirectory() {
        this.directory = null;
    }

    /* access modifiers changed from: protected */
    @Internal
    public boolean initDirectory() {
        if (this.directory != null) {
            return false;
        }
        this.directory = new POIFSFileSystem().getRoot();
        return true;
    }

    /* access modifiers changed from: protected */
    @Internal
    public void replaceDirectory(DirectoryNode directoryNode) throws IOException {
        DirectoryNode directoryNode2 = this.directory;
        if (directoryNode == directoryNode2) {
            return;
        }
        if (directoryNode == null || directoryNode2 == null || directoryNode.getFileSystem() != this.directory.getFileSystem()) {
            DirectoryNode directoryNode3 = this.directory;
            if (!(directoryNode3 == null || directoryNode3.getFileSystem() == null)) {
                this.directory.getFileSystem().close();
            }
            this.directory = directoryNode;
        }
    }
}
