package org.apache.poi.util;

import androidx.core.os.EnvironmentCompat;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.PackedColorModel;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import org.apache.commons.io.output.NullOutputStream;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.GenericRecordUtil;

public class GenericRecordJsonWriter implements Closeable {
    private static final Pattern ESC_CHARS = Pattern.compile("[\"\\p{Cntrl}\\\\]");
    private static final String NL = System.getProperty("line.separator");
    private static final String TABS;
    private static final String ZEROS = "0000000000000000";
    private static final List<Map.Entry<Class<?>, GenericRecordHandler>> handler = new ArrayList();
    protected final AppendableWriter aw;
    protected int childIndex = 0;
    protected final PrintWriter fw;
    protected int indent = 0;
    protected boolean withComments = true;

    @FunctionalInterface
    protected interface GenericRecordHandler {
        boolean print(GenericRecordJsonWriter genericRecordJsonWriter, String str, Object obj);
    }

    static {
        char[] cArr = new char[255];
        Arrays.fill(cArr, 9);
        TABS = new String(cArr);
        handler(String.class, new GenericRecordJsonWriter$$ExternalSyntheticLambda16());
        handler(Number.class, new GenericRecordJsonWriter$$ExternalSyntheticLambda2());
        handler(Boolean.class, new GenericRecordJsonWriter$$ExternalSyntheticLambda3());
        handler(List.class, new GenericRecordJsonWriter$$ExternalSyntheticLambda4());
        handler(GenericRecord.class, new GenericRecordJsonWriter$$ExternalSyntheticLambda5());
        handler(GenericRecordUtil.AnnotatedFlag.class, new GenericRecordJsonWriter$$ExternalSyntheticLambda6());
        handler(byte[].class, new GenericRecordJsonWriter$$ExternalSyntheticLambda7());
        handler(Point2D.class, new GenericRecordJsonWriter$$ExternalSyntheticLambda8());
        handler(Dimension2D.class, new GenericRecordJsonWriter$$ExternalSyntheticLambda9());
        handler(Rectangle2D.class, new GenericRecordJsonWriter$$ExternalSyntheticLambda10());
        handler(Path2D.class, new GenericRecordJsonWriter$$ExternalSyntheticLambda17());
        handler(AffineTransform.class, new GenericRecordJsonWriter$$ExternalSyntheticLambda18());
        handler(Color.class, new GenericRecordJsonWriter$$ExternalSyntheticLambda19());
        handler(BufferedImage.class, new GenericRecordJsonWriter$$ExternalSyntheticLambda20());
        handler(Array.class, new GenericRecordJsonWriter$$ExternalSyntheticLambda1());
        handler(Object.class, new GenericRecordJsonWriter$$ExternalSyntheticLambda16());
    }

    private static void handler(Class<?> cls, GenericRecordHandler genericRecordHandler) {
        handler.add(new AbstractMap.SimpleEntry(cls, genericRecordHandler));
    }

    public GenericRecordJsonWriter(File file) throws IOException {
        AppendableWriter appendableWriter = new AppendableWriter((Writer) new OutputStreamWriter("null".equals(file.getName()) ? NullOutputStream.NULL_OUTPUT_STREAM : new FileOutputStream(file), StandardCharsets.UTF_8));
        this.aw = appendableWriter;
        this.fw = new PrintWriter(appendableWriter);
    }

    public GenericRecordJsonWriter(Appendable appendable) {
        AppendableWriter appendableWriter = new AppendableWriter(appendable);
        this.aw = appendableWriter;
        this.fw = new PrintWriter(appendableWriter);
    }

    public static String marshal(GenericRecord genericRecord) {
        return marshal(genericRecord, true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001a, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0023, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String marshal(org.apache.poi.common.usermodel.GenericRecord r2, boolean r3) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            org.apache.poi.util.GenericRecordJsonWriter r1 = new org.apache.poi.util.GenericRecordJsonWriter     // Catch:{ IOException -> 0x0024 }
            r1.<init>((java.lang.Appendable) r0)     // Catch:{ IOException -> 0x0024 }
            r1.setWithComments(r3)     // Catch:{ all -> 0x0018 }
            r1.write(r2)     // Catch:{ all -> 0x0018 }
            java.lang.String r2 = r0.toString()     // Catch:{ all -> 0x0018 }
            r1.close()     // Catch:{ IOException -> 0x0024 }
            return r2
        L_0x0018:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x001a }
        L_0x001a:
            r3 = move-exception
            r1.close()     // Catch:{ all -> 0x001f }
            goto L_0x0023
        L_0x001f:
            r0 = move-exception
            r2.addSuppressed(r0)     // Catch:{ IOException -> 0x0024 }
        L_0x0023:
            throw r3     // Catch:{ IOException -> 0x0024 }
        L_0x0024:
            java.lang.String r2 = "{}"
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.util.GenericRecordJsonWriter.marshal(org.apache.poi.common.usermodel.GenericRecord, boolean):java.lang.String");
    }

    public void setWithComments(boolean z) {
        this.withComments = z;
    }

    public void close() throws IOException {
        this.fw.close();
    }

    /* access modifiers changed from: protected */
    public String tabs() {
        String str = TABS;
        return str.substring(0, Math.min(this.indent, str.length()));
    }

    public void write(GenericRecord genericRecord) {
        String tabs = tabs();
        Enum<?> genericRecordType = genericRecord.getGenericRecordType();
        String name = genericRecordType != null ? genericRecordType.name() : genericRecord.getClass().getSimpleName();
        this.fw.append(tabs);
        this.fw.append(VectorFormat.DEFAULT_PREFIX);
        if (this.withComments) {
            this.fw.append("   /* ");
            this.fw.append(name);
            if (this.childIndex > 0) {
                this.fw.append(" - index: ");
                this.fw.print(this.childIndex);
            }
            this.fw.append(" */");
        }
        this.fw.println();
        boolean writeProperties = writeProperties(genericRecord);
        this.fw.println();
        writeChildren(genericRecord, writeProperties);
        this.fw.append(tabs);
        this.fw.append(VectorFormat.DEFAULT_SUFFIX);
    }

    /* access modifiers changed from: protected */
    public boolean writeProperties(GenericRecord genericRecord) {
        Map<String, Supplier<?>> genericProperties = genericRecord.getGenericProperties();
        if (genericProperties == null || genericProperties.isEmpty()) {
            return false;
        }
        int i = this.childIndex;
        this.childIndex = 0;
        long count = genericProperties.entrySet().stream().filter(new GenericRecordJsonWriter$$ExternalSyntheticLambda11(this)).count();
        this.childIndex = i;
        if (count > 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$writeProperties$0$org-apache-poi-util-GenericRecordJsonWriter  reason: not valid java name */
    public /* synthetic */ boolean m2289lambda$writeProperties$0$orgapachepoiutilGenericRecordJsonWriter(Map.Entry entry) {
        return writeProp((String) entry.getKey(), (Supplier) entry.getValue());
    }

    /* access modifiers changed from: protected */
    public boolean writeChildren(GenericRecord genericRecord, boolean z) {
        List<? extends GenericRecord> genericChildren = genericRecord.getGenericChildren();
        if (genericChildren == null || genericChildren.isEmpty()) {
            return false;
        }
        this.indent++;
        this.aw.setHoldBack(tabs() + (z ? ", " : "") + "\"children\": [" + NL);
        int i = this.childIndex;
        this.childIndex = 0;
        long count = genericChildren.stream().filter(new GenericRecordJsonWriter$$ExternalSyntheticLambda12(this)).count();
        this.childIndex = i;
        this.aw.setHoldBack((String) null);
        int i2 = (count > 0 ? 1 : (count == 0 ? 0 : -1));
        if (i2 > 0) {
            this.fw.println();
            this.fw.println(tabs() + "]");
        }
        this.indent--;
        if (i2 > 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$writeChildren$1$org-apache-poi-util-GenericRecordJsonWriter  reason: not valid java name */
    public /* synthetic */ boolean m2288lambda$writeChildren$1$orgapachepoiutilGenericRecordJsonWriter(GenericRecord genericRecord) {
        if (writeValue((String) null, genericRecord)) {
            int i = this.childIndex + 1;
            this.childIndex = i;
            if (i > 0) {
                return true;
            }
        }
        return false;
    }

    public void writeError(String str) {
        this.fw.append("{ error: ");
        printObject("error", str);
        this.fw.append(" }");
    }

    /* access modifiers changed from: protected */
    public boolean writeProp(String str, Supplier<?> supplier) {
        String str2;
        StringBuilder sb;
        boolean z = this.childIndex > 0;
        AppendableWriter appendableWriter = this.aw;
        if (z) {
            sb = new StringBuilder().append(NL).append(tabs());
            str2 = "\t, ";
        } else {
            sb = new StringBuilder().append(tabs());
            str2 = "\t  ";
        }
        appendableWriter.setHoldBack(sb.append(str2).toString());
        int i = this.childIndex;
        this.childIndex = 0;
        boolean writeValue = writeValue(str, supplier.get());
        this.childIndex = i + (writeValue ? 1 : 0);
        this.aw.setHoldBack((String) null);
        return writeValue;
    }

    /* access modifiers changed from: protected */
    public boolean writeValue(String str, Object obj) {
        GenericRecordHandler genericRecordHandler;
        if (this.childIndex > 0) {
            this.aw.setHoldBack(",");
        }
        if (obj == null) {
            new GenericRecordJsonWriter$$ExternalSyntheticLambda13
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0010: CONSTRUCTOR  (r1v8 ? I:org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda13) =  call: org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda13.<init>():void type: CONSTRUCTOR in method: org.apache.poi.util.GenericRecordJsonWriter.writeValue(java.lang.String, java.lang.Object):boolean, dex: classes3.dex
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
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r1v8 ?
                	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:189)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:620)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                	... 34 more
                */
            /*
                this = this;
                int r0 = r3.childIndex
                if (r0 <= 0) goto L_0x000b
                org.apache.poi.util.GenericRecordJsonWriter$AppendableWriter r0 = r3.aw
                java.lang.String r1 = ","
                r0.setHoldBack(r1)
            L_0x000b:
                r0 = 0
                if (r5 != 0) goto L_0x0014
                org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda13 r1 = new org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda13
                r1.<init>()
                goto L_0x0036
            L_0x0014:
                java.util.List<java.util.Map$Entry<java.lang.Class<?>, org.apache.poi.util.GenericRecordJsonWriter$GenericRecordHandler>> r1 = handler
                java.util.stream.Stream r1 = r1.stream()
                org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda14 r2 = new org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda14
                r2.<init>(r5)
                java.util.stream.Stream r1 = r1.filter(r2)
                java.util.Optional r1 = r1.findFirst()
                org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda15 r2 = new org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda15
                r2.<init>()
                java.util.Optional r1 = r1.map(r2)
                java.lang.Object r1 = r1.orElse(r0)
                org.apache.poi.util.GenericRecordJsonWriter$GenericRecordHandler r1 = (org.apache.poi.util.GenericRecordJsonWriter.GenericRecordHandler) r1
            L_0x0036:
                if (r1 == 0) goto L_0x0040
                boolean r4 = r1.print(r3, r4, r5)
                if (r4 == 0) goto L_0x0040
                r4 = 1
                goto L_0x0041
            L_0x0040:
                r4 = 0
            L_0x0041:
                org.apache.poi.util.GenericRecordJsonWriter$AppendableWriter r3 = r3.aw
                r3.setHoldBack(r0)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.util.GenericRecordJsonWriter.writeValue(java.lang.String, java.lang.Object):boolean");
        }

        /* access modifiers changed from: protected */
        public static boolean matchInstanceOrArray(Class<?> cls, Object obj) {
            return cls.isInstance(obj) || (Array.class.equals(cls) && obj.getClass().isArray());
        }

        /* access modifiers changed from: protected */
        public void printName(String str) {
            this.fw.print(str != null ? "\"" + str + "\": " : "");
        }

        /* access modifiers changed from: protected */
        public boolean printNull(String str, Object obj) {
            printName(str);
            this.fw.write("null");
            return true;
        }

        /* access modifiers changed from: protected */
        public boolean printNumber(String str, Object obj) {
            int i;
            Number number = (Number) obj;
            printName(str);
            if (obj instanceof Float) {
                this.fw.print(number.floatValue());
                return true;
            } else if (obj instanceof Double) {
                this.fw.print(number.doubleValue());
                return true;
            } else {
                this.fw.print(number.longValue());
                if (number instanceof Byte) {
                    i = 2;
                } else if (number instanceof Short) {
                    i = 4;
                } else if (number instanceof Integer) {
                    i = 8;
                } else {
                    i = number instanceof Long ? 16 : -1;
                }
                long longValue = number.longValue();
                if (this.withComments && i > 0 && (longValue < 0 || longValue > 9)) {
                    this.fw.write(" /* 0x");
                    this.fw.write(trimHex(longValue, i));
                    this.fw.write(" */");
                }
                return true;
            }
        }

        /* access modifiers changed from: protected */
        public boolean printBoolean(String str, Object obj) {
            printName(str);
            this.fw.write(((Boolean) obj).toString());
            return true;
        }

        /* access modifiers changed from: protected */
        public boolean printList(String str, Object obj) {
            printName(str);
            this.fw.println("[");
            int i = this.childIndex;
            this.childIndex = 0;
            ((List) obj).forEach(new GenericRecordJsonWriter$$ExternalSyntheticLambda0(this));
            this.childIndex = i;
            this.fw.write(tabs() + "\t]");
            return true;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$printList$3$org-apache-poi-util-GenericRecordJsonWriter  reason: not valid java name */
        public /* synthetic */ void m2287lambda$printList$3$orgapachepoiutilGenericRecordJsonWriter(Object obj) {
            writeValue((String) null, obj);
            this.childIndex++;
        }

        /* access modifiers changed from: protected */
        public boolean printGenericRecord(String str, Object obj) {
            printName(str);
            this.indent++;
            write((GenericRecord) obj);
            this.indent--;
            return true;
        }

        /* access modifiers changed from: protected */
        public boolean printAnnotatedFlag(String str, Object obj) {
            printName(str);
            GenericRecordUtil.AnnotatedFlag annotatedFlag = (GenericRecordUtil.AnnotatedFlag) obj;
            this.fw.print(annotatedFlag.getValue().get().longValue());
            if (!this.withComments) {
                return true;
            }
            this.fw.write(" /* ");
            this.fw.write(annotatedFlag.getDescription());
            this.fw.write(" */ ");
            return true;
        }

        /* access modifiers changed from: protected */
        public boolean printBytes(String str, Object obj) {
            printName(str);
            this.fw.write(34);
            this.fw.write(Base64.getEncoder().encodeToString((byte[]) obj));
            this.fw.write(34);
            return true;
        }

        /* access modifiers changed from: protected */
        public boolean printPoint(String str, Object obj) {
            printName(str);
            Point2D point2D = (Point2D) obj;
            this.fw.write("{ \"x\": " + point2D.getX() + ", \"y\": " + point2D.getY() + " }");
            return true;
        }

        /* access modifiers changed from: protected */
        public boolean printDimension(String str, Object obj) {
            printName(str);
            Dimension2D dimension2D = (Dimension2D) obj;
            this.fw.write("{ \"width\": " + dimension2D.getWidth() + ", \"height\": " + dimension2D.getHeight() + " }");
            return true;
        }

        /* access modifiers changed from: protected */
        public boolean printRectangle(String str, Object obj) {
            printName(str);
            Rectangle2D rectangle2D = (Rectangle2D) obj;
            this.fw.write("{ \"x\": " + rectangle2D.getX() + ", \"y\": " + rectangle2D.getY() + ", \"width\": " + rectangle2D.getWidth() + ", \"height\": " + rectangle2D.getHeight() + " }");
            return true;
        }

        /* access modifiers changed from: protected */
        public boolean printPath(String str, Object obj) {
            printName(str);
            PathIterator pathIterator = ((Path2D) obj).getPathIterator((AffineTransform) null);
            double[] dArr = new double[6];
            this.fw.write("[");
            this.indent += 2;
            String tabs = tabs();
            this.indent -= 2;
            boolean z = false;
            while (!pathIterator.isDone()) {
                this.fw.println(z ? ", " : "");
                this.fw.print(tabs);
                int currentSegment = pathIterator.currentSegment(dArr);
                this.fw.append("{ \"type\": ");
                if (currentSegment == 0) {
                    this.fw.write("\"move\", \"x\": " + dArr[0] + ", \"y\": " + dArr[1]);
                } else if (currentSegment == 1) {
                    this.fw.write("\"lineto\", \"x\": " + dArr[0] + ", \"y\": " + dArr[1]);
                } else if (currentSegment == 2) {
                    this.fw.write("\"quad\", \"x1\": " + dArr[0] + ", \"y1\": " + dArr[1] + ", \"x2\": " + dArr[2] + ", \"y2\": " + dArr[3]);
                } else if (currentSegment == 3) {
                    this.fw.write("\"cubic\", \"x1\": " + dArr[0] + ", \"y1\": " + dArr[1] + ", \"x2\": " + dArr[2] + ", \"y2\": " + dArr[3] + ", \"x3\": " + dArr[4] + ", \"y3\": " + dArr[5]);
                } else if (currentSegment == 4) {
                    this.fw.write("\"close\"");
                }
                this.fw.append(" }");
                pathIterator.next();
                z = true;
            }
            this.fw.write("]");
            return true;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0075, code lost:
            if (r2.equals("\t") == false) goto L_0x0036;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean printObject(java.lang.String r9, java.lang.Object r10) {
            /*
                r8 = this;
                r8.printName(r9)
                java.io.PrintWriter r9 = r8.fw
                r0 = 34
                r9.write(r0)
                java.lang.String r9 = r10.toString()
                java.util.regex.Pattern r10 = ESC_CHARS
                java.util.regex.Matcher r10 = r10.matcher(r9)
                r1 = 0
                r2 = r1
            L_0x0016:
                boolean r3 = r10.find()
                r4 = 1
                if (r3 == 0) goto L_0x00d8
                java.io.PrintWriter r3 = r8.fw
                int r5 = r10.start()
                r3.append(r9, r2, r5)
                java.lang.String r2 = r10.group()
                r2.hashCode()
                int r3 = r2.hashCode()
                r5 = 4
                r6 = -1
                switch(r3) {
                    case 8: goto L_0x0078;
                    case 9: goto L_0x006f;
                    case 10: goto L_0x0064;
                    case 12: goto L_0x0059;
                    case 13: goto L_0x004e;
                    case 34: goto L_0x0043;
                    case 92: goto L_0x0038;
                    default: goto L_0x0036;
                }
            L_0x0036:
                r4 = r6
                goto L_0x0082
            L_0x0038:
                java.lang.String r3 = "\\"
                boolean r3 = r2.equals(r3)
                if (r3 != 0) goto L_0x0041
                goto L_0x0036
            L_0x0041:
                r4 = 6
                goto L_0x0082
            L_0x0043:
                java.lang.String r3 = "\""
                boolean r3 = r2.equals(r3)
                if (r3 != 0) goto L_0x004c
                goto L_0x0036
            L_0x004c:
                r4 = 5
                goto L_0x0082
            L_0x004e:
                java.lang.String r3 = "\r"
                boolean r3 = r2.equals(r3)
                if (r3 != 0) goto L_0x0057
                goto L_0x0036
            L_0x0057:
                r4 = r5
                goto L_0x0082
            L_0x0059:
                java.lang.String r3 = "\f"
                boolean r3 = r2.equals(r3)
                if (r3 != 0) goto L_0x0062
                goto L_0x0036
            L_0x0062:
                r4 = 3
                goto L_0x0082
            L_0x0064:
                java.lang.String r3 = "\n"
                boolean r3 = r2.equals(r3)
                if (r3 != 0) goto L_0x006d
                goto L_0x0036
            L_0x006d:
                r4 = 2
                goto L_0x0082
            L_0x006f:
                java.lang.String r3 = "\t"
                boolean r3 = r2.equals(r3)
                if (r3 != 0) goto L_0x0082
                goto L_0x0036
            L_0x0078:
                java.lang.String r3 = "\b"
                boolean r3 = r2.equals(r3)
                if (r3 != 0) goto L_0x0081
                goto L_0x0036
            L_0x0081:
                r4 = r1
            L_0x0082:
                switch(r4) {
                    case 0: goto L_0x00cb;
                    case 1: goto L_0x00c3;
                    case 2: goto L_0x00bb;
                    case 3: goto L_0x00b3;
                    case 4: goto L_0x00ab;
                    case 5: goto L_0x00a3;
                    case 6: goto L_0x009b;
                    default: goto L_0x0085;
                }
            L_0x0085:
                java.io.PrintWriter r3 = r8.fw
                java.lang.String r4 = "\\\\u"
                r3.write(r4)
                java.io.PrintWriter r3 = r8.fw
                char r2 = r2.charAt(r1)
                long r6 = (long) r2
                java.lang.String r2 = trimHex(r6, r5)
                r3.write(r2)
                goto L_0x00d2
            L_0x009b:
                java.io.PrintWriter r2 = r8.fw
                java.lang.String r3 = "\\\\\\\\"
                r2.write(r3)
                goto L_0x00d2
            L_0x00a3:
                java.io.PrintWriter r2 = r8.fw
                java.lang.String r3 = "\\\\\""
                r2.write(r3)
                goto L_0x00d2
            L_0x00ab:
                java.io.PrintWriter r2 = r8.fw
                java.lang.String r3 = "\\\\r"
                r2.write(r3)
                goto L_0x00d2
            L_0x00b3:
                java.io.PrintWriter r2 = r8.fw
                java.lang.String r3 = "\\\\f"
                r2.write(r3)
                goto L_0x00d2
            L_0x00bb:
                java.io.PrintWriter r2 = r8.fw
                java.lang.String r3 = "\\\\n"
                r2.write(r3)
                goto L_0x00d2
            L_0x00c3:
                java.io.PrintWriter r2 = r8.fw
                java.lang.String r3 = "\\\\t"
                r2.write(r3)
                goto L_0x00d2
            L_0x00cb:
                java.io.PrintWriter r2 = r8.fw
                java.lang.String r3 = "\\\\b"
                r2.write(r3)
            L_0x00d2:
                int r2 = r10.end()
                goto L_0x0016
            L_0x00d8:
                java.io.PrintWriter r10 = r8.fw
                int r1 = r9.length()
                r10.append(r9, r2, r1)
                java.io.PrintWriter r8 = r8.fw
                r8.write(r0)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.util.GenericRecordJsonWriter.printObject(java.lang.String, java.lang.Object):boolean");
        }

        /* access modifiers changed from: protected */
        public boolean printAffineTransform(String str, Object obj) {
            printName(str);
            AffineTransform affineTransform = (AffineTransform) obj;
            this.fw.write("{ \"scaleX\": " + affineTransform.getScaleX() + ", \"shearX\": " + affineTransform.getShearX() + ", \"transX\": " + affineTransform.getTranslateX() + ", \"scaleY\": " + affineTransform.getScaleY() + ", \"shearY\": " + affineTransform.getShearY() + ", \"transY\": " + affineTransform.getTranslateY() + " }");
            return true;
        }

        /* access modifiers changed from: protected */
        public boolean printColor(String str, Object obj) {
            printName(str);
            int rgb = ((Color) obj).getRGB();
            this.fw.print(rgb);
            if (!this.withComments) {
                return true;
            }
            this.fw.write(" /* 0x");
            this.fw.write(trimHex((long) rgb, 8));
            this.fw.write(" */");
            return true;
        }

        /* access modifiers changed from: protected */
        public boolean printArray(String str, Object obj) {
            printName(str);
            this.fw.write("[");
            int length = Array.getLength(obj);
            int i = this.childIndex;
            int i2 = 0;
            while (true) {
                this.childIndex = i2;
                int i3 = this.childIndex;
                if (i3 < length) {
                    writeValue((String) null, Array.get(obj, i3));
                    i2 = this.childIndex + 1;
                } else {
                    this.childIndex = i;
                    this.fw.write(tabs() + "\t]");
                    return true;
                }
            }
        }

        /* access modifiers changed from: protected */
        public boolean printImage(String str, Object obj) {
            BufferedImage bufferedImage = (BufferedImage) obj;
            String[] strArr = {"XYZ", "Lab", "Luv", "YCbCr", "Yxy", "RGB", "GRAY", "HSV", "HLS", "CMYK", "Unknown", "CMY", "Unknown"};
            String[] strArr2 = {"CUSTOM", "INT_RGB", "INT_ARGB", "INT_ARGB_PRE", "INT_BGR", "3BYTE_BGR", "4BYTE_ABGR", "4BYTE_ABGR_PRE", "USHORT_565_RGB", "USHORT_555_RGB", "BYTE_GRAY", "USHORT_GRAY", "BYTE_BINARY", "BYTE_INDEXED"};
            printName(str);
            ColorModel colorModel = bufferedImage.getColorModel();
            this.fw.write("{ \"width\": " + bufferedImage.getWidth() + ", \"height\": " + bufferedImage.getHeight() + ", \"type\": \"" + strArr2[bufferedImage.getType()] + "\", \"colormodel\": \"" + (colorModel instanceof IndexColorModel ? "indexed" : colorModel instanceof ComponentColorModel ? "component" : colorModel instanceof DirectColorModel ? "direct" : colorModel instanceof PackedColorModel ? "packed" : EnvironmentCompat.MEDIA_UNKNOWN) + "\", \"pixelBits\": " + colorModel.getPixelSize() + ", \"numComponents\": " + colorModel.getNumComponents() + ", \"colorSpace\": \"" + strArr[Math.min(colorModel.getColorSpace().getType(), 12)] + "\", \"transparency\": " + colorModel.getTransparency() + ", \"alpha\": " + colorModel.hasAlpha() + VectorFormat.DEFAULT_SUFFIX);
            return true;
        }

        static String trimHex(long j, int i) {
            String hexString = Long.toHexString(j);
            int length = hexString.length();
            return ZEROS.substring(0, Math.max(0, i - length)) + hexString.substring(Math.max(0, length - i), length);
        }

        static class AppendableWriter extends Writer {
            private final Appendable appender;
            private String holdBack;
            private final Writer writer;

            AppendableWriter(Appendable appendable) {
                super(appendable);
                this.appender = appendable;
                this.writer = null;
            }

            AppendableWriter(Writer writer2) {
                super(writer2);
                this.appender = null;
                this.writer = writer2;
            }

            /* access modifiers changed from: package-private */
            public void setHoldBack(String str) {
                this.holdBack = str;
            }

            public void write(char[] cArr, int i, int i2) throws IOException {
                String str = this.holdBack;
                if (str != null) {
                    Appendable appendable = this.appender;
                    if (appendable != null) {
                        appendable.append(str);
                    } else {
                        Writer writer2 = this.writer;
                        if (writer2 != null) {
                            writer2.write(str);
                        }
                    }
                    this.holdBack = null;
                }
                Appendable appendable2 = this.appender;
                if (appendable2 != null) {
                    appendable2.append(String.valueOf(cArr), i, i2);
                    return;
                }
                Writer writer3 = this.writer;
                if (writer3 != null) {
                    writer3.write(cArr, i, i2);
                }
            }

            public void flush() throws IOException {
                Object obj = this.appender;
                if (obj == null) {
                    obj = this.writer;
                }
                if (obj instanceof Flushable) {
                    ((Flushable) obj).flush();
                }
            }

            public void close() throws IOException {
                flush();
                Object obj = this.appender;
                if (obj == null) {
                    obj = this.writer;
                }
                if (obj instanceof Closeable) {
                    ((Closeable) obj).close();
                }
            }
        }
    }
