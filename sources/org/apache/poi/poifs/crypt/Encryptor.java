package org.apache.poi.poifs.crypt;

import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.function.Supplier;
import javax.crypto.SecretKey;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public abstract class Encryptor implements GenericRecord {
    protected static final String DEFAULT_POIFS_ENTRY = "EncryptedPackage";
    private EncryptionInfo encryptionInfo;
    private SecretKey secretKey;

    static /* synthetic */ Object lambda$getGenericProperties$0() {
        return null;
    }

    public abstract void confirmPassword(String str);

    public abstract void confirmPassword(String str, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5);

    public abstract Encryptor copy();

    public abstract OutputStream getDataStream(DirectoryNode directoryNode) throws IOException, GeneralSecurityException;

    protected Encryptor() {
    }

    protected Encryptor(Encryptor encryptor) {
        this.encryptionInfo = encryptor.encryptionInfo;
        this.secretKey = encryptor.secretKey;
    }

    public static Encryptor getInstance(EncryptionInfo encryptionInfo2) {
        return encryptionInfo2.getEncryptor();
    }

    public OutputStream getDataStream(POIFSFileSystem pOIFSFileSystem) throws IOException, GeneralSecurityException {
        return getDataStream(pOIFSFileSystem.getRoot());
    }

    public ChunkedCipherOutputStream getDataStream(OutputStream outputStream, int i) throws IOException, GeneralSecurityException {
        throw new EncryptedDocumentException("this decryptor doesn't support writing directly to a stream");
    }

    public SecretKey getSecretKey() {
        return this.secretKey;
    }

    public void setSecretKey(SecretKey secretKey2) {
        this.secretKey = secretKey2;
    }

    public EncryptionInfo getEncryptionInfo() {
        return this.encryptionInfo;
    }

    public void setEncryptionInfo(EncryptionInfo encryptionInfo2) {
        this.encryptionInfo = encryptionInfo2;
    }

    public void setChunkSize(int i) {
        throw new EncryptedDocumentException("this decryptor doesn't support changing the chunk size");
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        Decryptor$$ExternalSyntheticLambda1 decryptor$$ExternalSyntheticLambda1;
        SecretKey secretKey2 = this.secretKey;
        if (secretKey2 == null) {
            new Encryptor$$ExternalSyntheticLambda0
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0006: CONSTRUCTOR  (r1v5 ? I:org.apache.poi.poifs.crypt.Encryptor$$ExternalSyntheticLambda0) =  call: org.apache.poi.poifs.crypt.Encryptor$$ExternalSyntheticLambda0.<init>():void type: CONSTRUCTOR in method: org.apache.poi.poifs.crypt.Encryptor.getGenericProperties():java.util.Map<java.lang.String, java.util.function.Supplier<?>>, dex: classes3.dex
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
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r1v5 ?
                	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:189)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:620)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                	... 34 more
                */
            /*
                this = this;
                javax.crypto.SecretKey r1 = r1.secretKey
                if (r1 != 0) goto L_0x000a
                org.apache.poi.poifs.crypt.Encryptor$$ExternalSyntheticLambda0 r1 = new org.apache.poi.poifs.crypt.Encryptor$$ExternalSyntheticLambda0
                r1.<init>()
                goto L_0x0013
            L_0x000a:
                r1.getClass()
                org.apache.poi.poifs.crypt.Decryptor$$ExternalSyntheticLambda1 r0 = new org.apache.poi.poifs.crypt.Decryptor$$ExternalSyntheticLambda1
                r0.<init>(r1)
                r1 = r0
            L_0x0013:
                java.lang.String r0 = "secretKey"
                java.util.Map r1 = org.apache.poi.util.GenericRecordUtil.getGenericProperties(r0, r1)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.Encryptor.getGenericProperties():java.util.Map");
        }
    }
