package org.apache.poi.poifs.crypt;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.function.Supplier;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public abstract class Decryptor implements GenericRecord {
    public static final String DEFAULT_PASSWORD = "VelvetSweatshop";
    public static final String DEFAULT_POIFS_ENTRY = "EncryptedPackage";
    protected EncryptionInfo encryptionInfo;
    private byte[] integrityHmacKey;
    private byte[] integrityHmacValue;
    private SecretKey secretKey;
    private byte[] verifier;

    static /* synthetic */ Object lambda$getGenericProperties$0() {
        return null;
    }

    public abstract Decryptor copy();

    public abstract InputStream getDataStream(DirectoryNode directoryNode) throws IOException, GeneralSecurityException;

    public abstract long getLength();

    public abstract boolean verifyPassword(String str) throws GeneralSecurityException;

    protected Decryptor() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: byte[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected Decryptor(org.apache.poi.poifs.crypt.Decryptor r3) {
        /*
            r2 = this;
            r2.<init>()
            org.apache.poi.poifs.crypt.EncryptionInfo r0 = r3.encryptionInfo
            r2.encryptionInfo = r0
            javax.crypto.SecretKey r0 = r3.secretKey
            r2.secretKey = r0
            byte[] r0 = r3.verifier
            r1 = 0
            if (r0 != 0) goto L_0x0012
            r0 = r1
            goto L_0x0018
        L_0x0012:
            java.lang.Object r0 = r0.clone()
            byte[] r0 = (byte[]) r0
        L_0x0018:
            r2.verifier = r0
            byte[] r0 = r3.integrityHmacKey
            if (r0 != 0) goto L_0x0020
            r0 = r1
            goto L_0x0026
        L_0x0020:
            java.lang.Object r0 = r0.clone()
            byte[] r0 = (byte[]) r0
        L_0x0026:
            r2.integrityHmacKey = r0
            byte[] r3 = r3.integrityHmacValue
            if (r3 != 0) goto L_0x002d
            goto L_0x0034
        L_0x002d:
            java.lang.Object r3 = r3.clone()
            r1 = r3
            byte[] r1 = (byte[]) r1
        L_0x0034:
            r2.integrityHmacValue = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.Decryptor.<init>(org.apache.poi.poifs.crypt.Decryptor):void");
    }

    public InputStream getDataStream(InputStream inputStream, int i, int i2) throws IOException, GeneralSecurityException {
        throw new EncryptedDocumentException("this decryptor doesn't support reading from a stream");
    }

    public void setChunkSize(int i) {
        throw new EncryptedDocumentException("this decryptor doesn't support changing the chunk size");
    }

    public Cipher initCipherForBlock(Cipher cipher, int i) throws GeneralSecurityException {
        throw new EncryptedDocumentException("this decryptor doesn't support initCipherForBlock");
    }

    public static Decryptor getInstance(EncryptionInfo encryptionInfo2) {
        Decryptor decryptor = encryptionInfo2.getDecryptor();
        if (decryptor != null) {
            return decryptor;
        }
        throw new EncryptedDocumentException("Unsupported version");
    }

    public InputStream getDataStream(POIFSFileSystem pOIFSFileSystem) throws IOException, GeneralSecurityException {
        return getDataStream(pOIFSFileSystem.getRoot());
    }

    public byte[] getVerifier() {
        return this.verifier;
    }

    public SecretKey getSecretKey() {
        return this.secretKey;
    }

    public byte[] getIntegrityHmacKey() {
        return this.integrityHmacKey;
    }

    public byte[] getIntegrityHmacValue() {
        return this.integrityHmacValue;
    }

    /* access modifiers changed from: protected */
    public void setSecretKey(SecretKey secretKey2) {
        this.secretKey = secretKey2;
    }

    /* access modifiers changed from: protected */
    public void setVerifier(byte[] bArr) {
        this.verifier = bArr == null ? null : (byte[]) bArr.clone();
    }

    /* access modifiers changed from: protected */
    public void setIntegrityHmacKey(byte[] bArr) {
        this.integrityHmacKey = bArr == null ? null : (byte[]) bArr.clone();
    }

    /* access modifiers changed from: protected */
    public void setIntegrityHmacValue(byte[] bArr) {
        this.integrityHmacValue = bArr == null ? null : (byte[]) bArr.clone();
    }

    /* access modifiers changed from: protected */
    public int getBlockSizeInBytes() {
        return this.encryptionInfo.getHeader().getBlockSize();
    }

    /* access modifiers changed from: protected */
    public int getKeySizeInBytes() {
        return this.encryptionInfo.getHeader().getKeySize() / 8;
    }

    public EncryptionInfo getEncryptionInfo() {
        return this.encryptionInfo;
    }

    public void setEncryptionInfo(EncryptionInfo encryptionInfo2) {
        this.encryptionInfo = encryptionInfo2;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        Decryptor$$ExternalSyntheticLambda1 decryptor$$ExternalSyntheticLambda1;
        SecretKey secretKey2 = this.secretKey;
        if (secretKey2 == null) {
            new Decryptor$$ExternalSyntheticLambda0
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0008: CONSTRUCTOR  (r1v3 ? I:org.apache.poi.poifs.crypt.Decryptor$$ExternalSyntheticLambda0) =  call: org.apache.poi.poifs.crypt.Decryptor$$ExternalSyntheticLambda0.<init>():void type: CONSTRUCTOR in method: org.apache.poi.poifs.crypt.Decryptor.getGenericProperties():java.util.Map<java.lang.String, java.util.function.Supplier<?>>, dex: classes3.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.util.ArrayList.forEach(ArrayList.java:1259)
                	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r1v3 ?
                	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:189)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:620)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                	... 34 more
                */
            /*
                this = this;
                java.lang.String r0 = "secretKey"
                javax.crypto.SecretKey r1 = r8.secretKey
                if (r1 != 0) goto L_0x000c
                org.apache.poi.poifs.crypt.Decryptor$$ExternalSyntheticLambda0 r1 = new org.apache.poi.poifs.crypt.Decryptor$$ExternalSyntheticLambda0
                r1.<init>()
                goto L_0x0015
            L_0x000c:
                r1.getClass()
                org.apache.poi.poifs.crypt.Decryptor$$ExternalSyntheticLambda1 r2 = new org.apache.poi.poifs.crypt.Decryptor$$ExternalSyntheticLambda1
                r2.<init>(r1)
                r1 = r2
            L_0x0015:
                java.lang.String r2 = "verifier"
                org.apache.poi.poifs.crypt.Decryptor$$ExternalSyntheticLambda2 r3 = new org.apache.poi.poifs.crypt.Decryptor$$ExternalSyntheticLambda2
                r3.<init>(r8)
                java.lang.String r4 = "integrityHmacKey"
                org.apache.poi.poifs.crypt.Decryptor$$ExternalSyntheticLambda3 r5 = new org.apache.poi.poifs.crypt.Decryptor$$ExternalSyntheticLambda3
                r5.<init>(r8)
                java.lang.String r6 = "integrityHmacValue"
                org.apache.poi.poifs.crypt.Decryptor$$ExternalSyntheticLambda4 r7 = new org.apache.poi.poifs.crypt.Decryptor$$ExternalSyntheticLambda4
                r7.<init>(r8)
                java.util.Map r8 = org.apache.poi.util.GenericRecordUtil.getGenericProperties(r0, r1, r2, r3, r4, r5, r6, r7)
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.Decryptor.getGenericProperties():java.util.Map");
        }
    }
