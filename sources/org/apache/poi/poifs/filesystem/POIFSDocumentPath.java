package org.apache.poi.poifs.filesystem;

import java.io.File;
import java.util.Arrays;
import java.util.function.Predicate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class POIFSDocumentPath {
    private static final Logger LOGGER = LogManager.getLogger((Class<?>) POIFSDocumentPath.class);
    private final String[] components;
    private int hashcode;

    public POIFSDocumentPath() {
        this.components = new String[0];
    }

    public POIFSDocumentPath(String[] strArr) throws IllegalArgumentException {
        this((POIFSDocumentPath) null, strArr);
    }

    public POIFSDocumentPath(POIFSDocumentPath pOIFSDocumentPath, String[] strArr) throws IllegalArgumentException {
        String[] strArr2;
        Predicate predicate;
        if (pOIFSDocumentPath == null) {
            strArr2 = new String[0];
        } else {
            strArr2 = pOIFSDocumentPath.components;
        }
        strArr = strArr == null ? new String[0] : strArr;
        if (pOIFSDocumentPath != null) {
            new POIFSDocumentPath$$ExternalSyntheticLambda0
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0013: CONSTRUCTOR  (r3v9 ? I:org.apache.poi.poifs.filesystem.POIFSDocumentPath$$ExternalSyntheticLambda0) =  call: org.apache.poi.poifs.filesystem.POIFSDocumentPath$$ExternalSyntheticLambda0.<init>():void type: CONSTRUCTOR in method: org.apache.poi.poifs.filesystem.POIFSDocumentPath.<init>(org.apache.poi.poifs.filesystem.POIFSDocumentPath, java.lang.String[]):void, dex: classes3.dex
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
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r3v9 ?
                	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:189)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:620)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                	... 34 more
                */
            /*
                this = this;
                r2.<init>()
                r0 = 0
                if (r3 != 0) goto L_0x0009
                java.lang.String[] r1 = new java.lang.String[r0]
                goto L_0x000b
            L_0x0009:
                java.lang.String[] r1 = r3.components
            L_0x000b:
                if (r4 != 0) goto L_0x000f
                java.lang.String[] r4 = new java.lang.String[r0]
            L_0x000f:
                if (r3 == 0) goto L_0x0017
                org.apache.poi.poifs.filesystem.POIFSDocumentPath$$ExternalSyntheticLambda0 r3 = new org.apache.poi.poifs.filesystem.POIFSDocumentPath$$ExternalSyntheticLambda0
                r3.<init>()
                goto L_0x001c
            L_0x0017:
                org.apache.poi.poifs.filesystem.POIFSDocumentPath$$ExternalSyntheticLambda1 r3 = new org.apache.poi.poifs.filesystem.POIFSDocumentPath$$ExternalSyntheticLambda1
                r3.<init>()
            L_0x001c:
                java.util.stream.Stream r0 = java.util.stream.Stream.of(r4)
                boolean r3 = r0.anyMatch(r3)
                if (r3 != 0) goto L_0x0040
                java.util.stream.Stream r3 = java.util.stream.Stream.of(r1)
                java.util.stream.Stream r4 = java.util.stream.Stream.of(r4)
                java.util.stream.Stream r3 = java.util.stream.Stream.concat(r3, r4)
                org.apache.poi.poifs.filesystem.POIFSDocumentPath$$ExternalSyntheticLambda2 r4 = new org.apache.poi.poifs.filesystem.POIFSDocumentPath$$ExternalSyntheticLambda2
                r4.<init>()
                java.lang.Object[] r3 = r3.toArray(r4)
                java.lang.String[] r3 = (java.lang.String[]) r3
                r2.components = r3
                return
            L_0x0040:
                java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
                java.lang.String r3 = "components cannot contain null or empty strings"
                r2.<init>(r3)
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.filesystem.POIFSDocumentPath.<init>(org.apache.poi.poifs.filesystem.POIFSDocumentPath, java.lang.String[]):void");
        }

        static /* synthetic */ boolean lambda$new$0(String str) {
            return str == null || str.isEmpty();
        }

        static /* synthetic */ String[] lambda$new$1(int i) {
            return new String[i];
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            return Arrays.equals(this.components, ((POIFSDocumentPath) obj).components);
        }

        public int hashCode() {
            int i = this.hashcode;
            if (i != 0) {
                return i;
            }
            int hashCode = Arrays.hashCode(this.components);
            this.hashcode = hashCode;
            return hashCode;
        }

        public int length() {
            return this.components.length;
        }

        public String getComponent(int i) throws ArrayIndexOutOfBoundsException {
            return this.components[i];
        }

        public POIFSDocumentPath getParent() {
            String[] strArr = this.components;
            if (strArr.length == 0) {
                return null;
            }
            return new POIFSDocumentPath((String[]) Arrays.copyOf(strArr, strArr.length - 1));
        }

        public String getName() {
            String[] strArr = this.components;
            return strArr.length == 0 ? "" : strArr[strArr.length - 1];
        }

        public String toString() {
            return File.separatorChar + String.join(String.valueOf(File.separatorChar), this.components);
        }
    }
