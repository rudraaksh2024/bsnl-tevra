package org.apache.poi.hssf.record;

import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutputStream;

public final class FilePassRecord extends StandardRecord {
    private static final int ENCRYPTION_OTHER = 1;
    private static final int ENCRYPTION_XOR = 0;
    public static final short sid = 47;
    private final EncryptionInfo encryptionInfo;
    private final int encryptionType;

    public short getSid() {
        return 47;
    }

    private FilePassRecord(FilePassRecord filePassRecord) {
        super(filePassRecord);
        this.encryptionType = filePassRecord.encryptionType;
        this.encryptionInfo = filePassRecord.encryptionInfo.copy();
    }

    public FilePassRecord(EncryptionMode encryptionMode) {
        this.encryptionType = encryptionMode == EncryptionMode.xor ? 0 : 1;
        this.encryptionInfo = new EncryptionInfo(encryptionMode);
    }

    public FilePassRecord(RecordInputStream recordInputStream) {
        EncryptionMode encryptionMode;
        int readUShort = recordInputStream.readUShort();
        this.encryptionType = readUShort;
        if (readUShort == 0) {
            encryptionMode = EncryptionMode.xor;
        } else if (readUShort == 1) {
            encryptionMode = EncryptionMode.cryptoAPI;
        } else {
            throw new EncryptedDocumentException("invalid encryption type");
        }
        try {
            this.encryptionInfo = new EncryptionInfo(recordInputStream, encryptionMode);
        } catch (IOException e) {
            throw new EncryptedDocumentException((Throwable) e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00ac, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00b5, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void serialize(org.apache.poi.util.LittleEndianOutput r6) {
        /*
            r5 = this;
            int r0 = r5.encryptionType
            r6.writeShort(r0)
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]
            org.apache.poi.util.LittleEndianByteArrayOutputStream r1 = new org.apache.poi.util.LittleEndianByteArrayOutputStream     // Catch:{ IOException -> 0x00b6 }
            r2 = 0
            r1.<init>(r0, r2)     // Catch:{ IOException -> 0x00b6 }
            int[] r3 = org.apache.poi.hssf.record.FilePassRecord.AnonymousClass1.$SwitchMap$org$apache$poi$poifs$crypt$EncryptionMode     // Catch:{ all -> 0x00aa }
            org.apache.poi.poifs.crypt.EncryptionInfo r4 = r5.encryptionInfo     // Catch:{ all -> 0x00aa }
            org.apache.poi.poifs.crypt.EncryptionMode r4 = r4.getEncryptionMode()     // Catch:{ all -> 0x00aa }
            int r4 = r4.ordinal()     // Catch:{ all -> 0x00aa }
            r3 = r3[r4]     // Catch:{ all -> 0x00aa }
            r4 = 1
            if (r3 == r4) goto L_0x0089
            r4 = 2
            if (r3 == r4) goto L_0x0060
            r4 = 3
            if (r3 != r4) goto L_0x0058
            org.apache.poi.poifs.crypt.EncryptionInfo r3 = r5.encryptionInfo     // Catch:{ all -> 0x00aa }
            int r3 = r3.getVersionMajor()     // Catch:{ all -> 0x00aa }
            r6.writeShort(r3)     // Catch:{ all -> 0x00aa }
            org.apache.poi.poifs.crypt.EncryptionInfo r3 = r5.encryptionInfo     // Catch:{ all -> 0x00aa }
            int r3 = r3.getVersionMinor()     // Catch:{ all -> 0x00aa }
            r6.writeShort(r3)     // Catch:{ all -> 0x00aa }
            org.apache.poi.poifs.crypt.EncryptionInfo r3 = r5.encryptionInfo     // Catch:{ all -> 0x00aa }
            int r3 = r3.getEncryptionFlags()     // Catch:{ all -> 0x00aa }
            r6.writeInt(r3)     // Catch:{ all -> 0x00aa }
            org.apache.poi.poifs.crypt.EncryptionInfo r3 = r5.encryptionInfo     // Catch:{ all -> 0x00aa }
            org.apache.poi.poifs.crypt.EncryptionHeader r3 = r3.getHeader()     // Catch:{ all -> 0x00aa }
            org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIEncryptionHeader r3 = (org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIEncryptionHeader) r3     // Catch:{ all -> 0x00aa }
            r3.write(r1)     // Catch:{ all -> 0x00aa }
            org.apache.poi.poifs.crypt.EncryptionInfo r5 = r5.encryptionInfo     // Catch:{ all -> 0x00aa }
            org.apache.poi.poifs.crypt.EncryptionVerifier r5 = r5.getVerifier()     // Catch:{ all -> 0x00aa }
            org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIEncryptionVerifier r5 = (org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIEncryptionVerifier) r5     // Catch:{ all -> 0x00aa }
            r5.write(r1)     // Catch:{ all -> 0x00aa }
            goto L_0x009f
        L_0x0058:
            org.apache.poi.EncryptedDocumentException r5 = new org.apache.poi.EncryptedDocumentException     // Catch:{ all -> 0x00aa }
            java.lang.String r6 = "not supported"
            r5.<init>((java.lang.String) r6)     // Catch:{ all -> 0x00aa }
            throw r5     // Catch:{ all -> 0x00aa }
        L_0x0060:
            org.apache.poi.poifs.crypt.EncryptionInfo r3 = r5.encryptionInfo     // Catch:{ all -> 0x00aa }
            int r3 = r3.getVersionMajor()     // Catch:{ all -> 0x00aa }
            r6.writeShort(r3)     // Catch:{ all -> 0x00aa }
            org.apache.poi.poifs.crypt.EncryptionInfo r3 = r5.encryptionInfo     // Catch:{ all -> 0x00aa }
            int r3 = r3.getVersionMinor()     // Catch:{ all -> 0x00aa }
            r6.writeShort(r3)     // Catch:{ all -> 0x00aa }
            org.apache.poi.poifs.crypt.EncryptionInfo r3 = r5.encryptionInfo     // Catch:{ all -> 0x00aa }
            org.apache.poi.poifs.crypt.EncryptionHeader r3 = r3.getHeader()     // Catch:{ all -> 0x00aa }
            org.apache.poi.poifs.crypt.binaryrc4.BinaryRC4EncryptionHeader r3 = (org.apache.poi.poifs.crypt.binaryrc4.BinaryRC4EncryptionHeader) r3     // Catch:{ all -> 0x00aa }
            r3.write(r1)     // Catch:{ all -> 0x00aa }
            org.apache.poi.poifs.crypt.EncryptionInfo r5 = r5.encryptionInfo     // Catch:{ all -> 0x00aa }
            org.apache.poi.poifs.crypt.EncryptionVerifier r5 = r5.getVerifier()     // Catch:{ all -> 0x00aa }
            org.apache.poi.poifs.crypt.binaryrc4.BinaryRC4EncryptionVerifier r5 = (org.apache.poi.poifs.crypt.binaryrc4.BinaryRC4EncryptionVerifier) r5     // Catch:{ all -> 0x00aa }
            r5.write(r1)     // Catch:{ all -> 0x00aa }
            goto L_0x009f
        L_0x0089:
            org.apache.poi.poifs.crypt.EncryptionInfo r3 = r5.encryptionInfo     // Catch:{ all -> 0x00aa }
            org.apache.poi.poifs.crypt.EncryptionHeader r3 = r3.getHeader()     // Catch:{ all -> 0x00aa }
            org.apache.poi.poifs.crypt.xor.XOREncryptionHeader r3 = (org.apache.poi.poifs.crypt.xor.XOREncryptionHeader) r3     // Catch:{ all -> 0x00aa }
            r3.write(r1)     // Catch:{ all -> 0x00aa }
            org.apache.poi.poifs.crypt.EncryptionInfo r5 = r5.encryptionInfo     // Catch:{ all -> 0x00aa }
            org.apache.poi.poifs.crypt.EncryptionVerifier r5 = r5.getVerifier()     // Catch:{ all -> 0x00aa }
            org.apache.poi.poifs.crypt.xor.XOREncryptionVerifier r5 = (org.apache.poi.poifs.crypt.xor.XOREncryptionVerifier) r5     // Catch:{ all -> 0x00aa }
            r5.write(r1)     // Catch:{ all -> 0x00aa }
        L_0x009f:
            int r5 = r1.getWriteIndex()     // Catch:{ all -> 0x00aa }
            r6.write(r0, r2, r5)     // Catch:{ all -> 0x00aa }
            r1.close()     // Catch:{ IOException -> 0x00b6 }
            return
        L_0x00aa:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x00ac }
        L_0x00ac:
            r6 = move-exception
            r1.close()     // Catch:{ all -> 0x00b1 }
            goto L_0x00b5
        L_0x00b1:
            r0 = move-exception
            r5.addSuppressed(r0)     // Catch:{ IOException -> 0x00b6 }
        L_0x00b5:
            throw r6     // Catch:{ IOException -> 0x00b6 }
        L_0x00b6:
            r5 = move-exception
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            r6.<init>(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.record.FilePassRecord.serialize(org.apache.poi.util.LittleEndianOutput):void");
    }

    /* renamed from: org.apache.poi.hssf.record.FilePassRecord$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$poifs$crypt$EncryptionMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.apache.poi.poifs.crypt.EncryptionMode[] r0 = org.apache.poi.poifs.crypt.EncryptionMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$poifs$crypt$EncryptionMode = r0
                org.apache.poi.poifs.crypt.EncryptionMode r1 = org.apache.poi.poifs.crypt.EncryptionMode.xor     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$crypt$EncryptionMode     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.poifs.crypt.EncryptionMode r1 = org.apache.poi.poifs.crypt.EncryptionMode.binaryRC4     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$crypt$EncryptionMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.poifs.crypt.EncryptionMode r1 = org.apache.poi.poifs.crypt.EncryptionMode.cryptoAPI     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.record.FilePassRecord.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        UnsynchronizedByteArrayOutputStream unsynchronizedByteArrayOutputStream = new UnsynchronizedByteArrayOutputStream();
        serialize(new LittleEndianOutputStream(unsynchronizedByteArrayOutputStream));
        return unsynchronizedByteArrayOutputStream.size();
    }

    public EncryptionInfo getEncryptionInfo() {
        return this.encryptionInfo;
    }

    public FilePassRecord copy() {
        return new FilePassRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.FILE_PASS;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("type", new FilePassRecord$$ExternalSyntheticLambda0(this), "encryptionInfo", new FilePassRecord$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-FilePassRecord  reason: not valid java name */
    public /* synthetic */ Object m2023lambda$getGenericProperties$0$orgapachepoihssfrecordFilePassRecord() {
        return Integer.valueOf(this.encryptionType);
    }
}
