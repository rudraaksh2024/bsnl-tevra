package org.apache.poi.ddf;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import org.apache.poi.util.LittleEndian;

public final class EscherPropertyFactory {
    public List<EscherProperty> createProperties(byte[] bArr, int i, short s) {
        int complexData;
        BiFunction biFunction;
        ArrayList<EscherProperty> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < s; i2++) {
            short s2 = LittleEndian.getShort(bArr, i);
            int i3 = LittleEndian.getInt(bArr, i + 2);
            boolean z = (32768 & s2) != 0;
            EscherPropertyTypes forPropertyID = EscherPropertyTypes.forPropertyID(s2);
            int i4 = AnonymousClass1.$SwitchMap$org$apache$poi$ddf$EscherPropertyTypesHolder[forPropertyID.holder.ordinal()];
            if (i4 == 1) {
                new EscherPropertyFactory$$ExternalSyntheticLambda0
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x005b: CONSTRUCTOR  (r4v4 ? I:org.apache.poi.ddf.EscherPropertyFactory$$ExternalSyntheticLambda0) =  call: org.apache.poi.ddf.EscherPropertyFactory$$ExternalSyntheticLambda0.<init>():void type: CONSTRUCTOR in method: org.apache.poi.ddf.EscherPropertyFactory.createProperties(byte[], int, short):java.util.List<org.apache.poi.ddf.EscherProperty>, dex: classes3.dex
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
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:221)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
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
                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r4v4 ?
                    	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:189)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:620)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                    	... 39 more
                    */
                /*
                    this = this;
                    java.util.ArrayList r9 = new java.util.ArrayList
                    r9.<init>()
                    r0 = 0
                    r1 = r0
                L_0x0007:
                    if (r1 >= r12) goto L_0x0072
                    short r2 = org.apache.poi.util.LittleEndian.getShort(r10, r11)
                    int r3 = r11 + 2
                    int r3 = org.apache.poi.util.LittleEndian.getInt(r10, r3)
                    r4 = 32768(0x8000, float:4.5918E-41)
                    r4 = r4 & r2
                    r5 = 1
                    if (r4 == 0) goto L_0x001c
                    r4 = r5
                    goto L_0x001d
                L_0x001c:
                    r4 = r0
                L_0x001d:
                    org.apache.poi.ddf.EscherPropertyTypes r6 = org.apache.poi.ddf.EscherPropertyTypes.forPropertyID(r2)
                    int[] r7 = org.apache.poi.ddf.EscherPropertyFactory.AnonymousClass1.$SwitchMap$org$apache$poi$ddf$EscherPropertyTypesHolder
                    org.apache.poi.ddf.EscherPropertyTypesHolder r8 = r6.holder
                    int r8 = r8.ordinal()
                    r7 = r7[r8]
                    if (r7 == r5) goto L_0x0059
                    r5 = 2
                    if (r7 == r5) goto L_0x0053
                    r5 = 3
                    if (r7 == r5) goto L_0x004d
                    if (r4 == 0) goto L_0x0047
                    org.apache.poi.ddf.EscherPropertyTypesHolder r4 = r6.holder
                    org.apache.poi.ddf.EscherPropertyTypesHolder r5 = org.apache.poi.ddf.EscherPropertyTypesHolder.ARRAY
                    if (r4 != r5) goto L_0x0041
                    org.apache.poi.ddf.EscherPropertyFactory$$ExternalSyntheticLambda3 r4 = new org.apache.poi.ddf.EscherPropertyFactory$$ExternalSyntheticLambda3
                    r4.<init>()
                    goto L_0x005e
                L_0x0041:
                    org.apache.poi.ddf.EscherPropertyFactory$$ExternalSyntheticLambda4 r4 = new org.apache.poi.ddf.EscherPropertyFactory$$ExternalSyntheticLambda4
                    r4.<init>()
                    goto L_0x005e
                L_0x0047:
                    org.apache.poi.ddf.EscherPropertyFactory$$ExternalSyntheticLambda5 r4 = new org.apache.poi.ddf.EscherPropertyFactory$$ExternalSyntheticLambda5
                    r4.<init>()
                    goto L_0x005e
                L_0x004d:
                    org.apache.poi.ddf.EscherPropertyFactory$$ExternalSyntheticLambda2 r4 = new org.apache.poi.ddf.EscherPropertyFactory$$ExternalSyntheticLambda2
                    r4.<init>()
                    goto L_0x005e
                L_0x0053:
                    org.apache.poi.ddf.EscherPropertyFactory$$ExternalSyntheticLambda1 r4 = new org.apache.poi.ddf.EscherPropertyFactory$$ExternalSyntheticLambda1
                    r4.<init>()
                    goto L_0x005e
                L_0x0059:
                    org.apache.poi.ddf.EscherPropertyFactory$$ExternalSyntheticLambda0 r4 = new org.apache.poi.ddf.EscherPropertyFactory$$ExternalSyntheticLambda0
                    r4.<init>()
                L_0x005e:
                    java.lang.Short r2 = java.lang.Short.valueOf(r2)
                    java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                    java.lang.Object r2 = r4.apply(r2, r3)
                    r9.add(r2)
                    int r11 = r11 + 6
                    int r1 = r1 + 1
                    goto L_0x0007
                L_0x0072:
                    java.util.Iterator r12 = r9.iterator()
                L_0x0076:
                    boolean r0 = r12.hasNext()
                    if (r0 == 0) goto L_0x00c7
                    java.lang.Object r0 = r12.next()
                    org.apache.poi.ddf.EscherProperty r0 = (org.apache.poi.ddf.EscherProperty) r0
                    boolean r1 = r0 instanceof org.apache.poi.ddf.EscherArrayProperty
                    if (r1 == 0) goto L_0x008e
                    org.apache.poi.ddf.EscherArrayProperty r0 = (org.apache.poi.ddf.EscherArrayProperty) r0
                    int r0 = r0.setArrayData(r10, r11)
                L_0x008c:
                    int r11 = r11 + r0
                    goto L_0x0076
                L_0x008e:
                    boolean r1 = r0 instanceof org.apache.poi.ddf.EscherComplexProperty
                    if (r1 == 0) goto L_0x0076
                    org.apache.poi.ddf.EscherComplexProperty r0 = (org.apache.poi.ddf.EscherComplexProperty) r0
                    byte[] r1 = r0.getComplexData()
                    int r1 = r1.length
                    int r2 = r10.length
                    int r2 = r2 - r11
                    if (r2 < r1) goto L_0x00a2
                    int r0 = r0.setComplexData(r10, r11)
                    goto L_0x008c
                L_0x00a2:
                    java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                    java.lang.StringBuilder r10 = new java.lang.StringBuilder
                    java.lang.String r11 = "Could not read complex escher property, length was "
                    r10.<init>(r11)
                    java.lang.StringBuilder r10 = r10.append(r1)
                    java.lang.String r11 = ", but had only "
                    java.lang.StringBuilder r10 = r10.append(r11)
                    java.lang.StringBuilder r10 = r10.append(r2)
                    java.lang.String r11 = " bytes left"
                    java.lang.StringBuilder r10 = r10.append(r11)
                    java.lang.String r10 = r10.toString()
                    r9.<init>(r10)
                    throw r9
                L_0x00c7:
                    return r9
                */
                throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ddf.EscherPropertyFactory.createProperties(byte[], int, short):java.util.List");
            }

            /* renamed from: org.apache.poi.ddf.EscherPropertyFactory$1  reason: invalid class name */
            static /* synthetic */ class AnonymousClass1 {
                static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ddf$EscherPropertyTypesHolder;

                /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
                /* JADX WARNING: Failed to process nested try/catch */
                /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
                /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
                static {
                    /*
                        org.apache.poi.ddf.EscherPropertyTypesHolder[] r0 = org.apache.poi.ddf.EscherPropertyTypesHolder.values()
                        int r0 = r0.length
                        int[] r0 = new int[r0]
                        $SwitchMap$org$apache$poi$ddf$EscherPropertyTypesHolder = r0
                        org.apache.poi.ddf.EscherPropertyTypesHolder r1 = org.apache.poi.ddf.EscherPropertyTypesHolder.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x0012 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                        r2 = 1
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
                    L_0x0012:
                        int[] r0 = $SwitchMap$org$apache$poi$ddf$EscherPropertyTypesHolder     // Catch:{ NoSuchFieldError -> 0x001d }
                        org.apache.poi.ddf.EscherPropertyTypesHolder r1 = org.apache.poi.ddf.EscherPropertyTypesHolder.RGB     // Catch:{ NoSuchFieldError -> 0x001d }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                        r2 = 2
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
                    L_0x001d:
                        int[] r0 = $SwitchMap$org$apache$poi$ddf$EscherPropertyTypesHolder     // Catch:{ NoSuchFieldError -> 0x0028 }
                        org.apache.poi.ddf.EscherPropertyTypesHolder r1 = org.apache.poi.ddf.EscherPropertyTypesHolder.SHAPE_PATH     // Catch:{ NoSuchFieldError -> 0x0028 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                        r2 = 3
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
                    L_0x0028:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ddf.EscherPropertyFactory.AnonymousClass1.<clinit>():void");
                }
            }
        }
