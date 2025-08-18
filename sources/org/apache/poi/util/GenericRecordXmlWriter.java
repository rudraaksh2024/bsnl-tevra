package org.apache.poi.util;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;
import kotlin.text.Typography;
import org.apache.commons.io.output.NullOutputStream;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;

public class GenericRecordXmlWriter implements Closeable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Pattern ESC_CHARS = Pattern.compile("[<>&'\"\\p{Cntrl}]");
    private static final String TABS;
    private static final String ZEROS = "0000000000000000";
    private static final List<Map.Entry<Class<?>, GenericRecordHandler>> handler = new ArrayList();
    private boolean attributePhase = true;
    private int childIndex = 0;
    private final PrintWriter fw;
    private int indent = 0;
    private boolean withComments = true;

    @FunctionalInterface
    protected interface GenericRecordHandler {
        boolean print(GenericRecordXmlWriter genericRecordXmlWriter, String str, Object obj);
    }

    static /* synthetic */ Object lambda$writeProp$1(Object obj) {
        return obj;
    }

    static {
        char[] cArr = new char[255];
        Arrays.fill(cArr, 9);
        TABS = new String(cArr);
        handler(String.class, new GenericRecordXmlWriter$$ExternalSyntheticLambda17());
        handler(Number.class, new GenericRecordXmlWriter$$ExternalSyntheticLambda2());
        handler(Boolean.class, new GenericRecordXmlWriter$$ExternalSyntheticLambda3());
        handler(List.class, new GenericRecordXmlWriter$$ExternalSyntheticLambda4());
        handler(GenericRecordUtil.AnnotatedFlag.class, new GenericRecordXmlWriter$$ExternalSyntheticLambda5());
        handler(byte[].class, new GenericRecordXmlWriter$$ExternalSyntheticLambda6());
        handler(Point2D.class, new GenericRecordXmlWriter$$ExternalSyntheticLambda7());
        handler(Dimension2D.class, new GenericRecordXmlWriter$$ExternalSyntheticLambda8());
        handler(Rectangle2D.class, new GenericRecordXmlWriter$$ExternalSyntheticLambda9());
        handler(Path2D.class, new GenericRecordXmlWriter$$ExternalSyntheticLambda10());
        handler(AffineTransform.class, new GenericRecordXmlWriter$$ExternalSyntheticLambda18());
        handler(Color.class, new GenericRecordXmlWriter$$ExternalSyntheticLambda19());
        handler(BufferedImage.class, new GenericRecordXmlWriter$$ExternalSyntheticLambda20());
        handler(Array.class, new GenericRecordXmlWriter$$ExternalSyntheticLambda1());
        handler(Object.class, new GenericRecordXmlWriter$$ExternalSyntheticLambda17());
    }

    private static void handler(Class<?> cls, GenericRecordHandler genericRecordHandler) {
        handler.add(new AbstractMap.SimpleEntry(cls, genericRecordHandler));
    }

    public GenericRecordXmlWriter(File file) throws IOException {
        this.fw = new PrintWriter(new OutputStreamWriter("null".equals(file.getName()) ? NullOutputStream.NULL_OUTPUT_STREAM : new FileOutputStream(file), StandardCharsets.UTF_8));
    }

    public GenericRecordXmlWriter(Appendable appendable) {
        this.fw = new PrintWriter(new GenericRecordJsonWriter.AppendableWriter(appendable));
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
            org.apache.poi.util.GenericRecordXmlWriter r1 = new org.apache.poi.util.GenericRecordXmlWriter     // Catch:{ IOException -> 0x0024 }
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
            java.lang.String r2 = "<record/>"
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.util.GenericRecordXmlWriter.marshal(org.apache.poi.common.usermodel.GenericRecord, boolean):java.lang.String");
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
        write("record", genericRecord);
    }

    /* access modifiers changed from: protected */
    public void write(String str, GenericRecord genericRecord) {
        String tabs = tabs();
        Enum<?> genericRecordType = genericRecord.getGenericRecordType();
        String name = genericRecordType != null ? genericRecordType.name() : genericRecord.getClass().getSimpleName();
        this.fw.append(tabs);
        this.fw.append("<").append(str).append(" type=\"");
        this.fw.append(name);
        this.fw.append("\"");
        if (this.childIndex > 0) {
            this.fw.append(" index=\"");
            this.fw.print(this.childIndex);
            this.fw.append("\"");
        }
        this.attributePhase = true;
        boolean writeProperties = writeProperties(genericRecord);
        this.attributePhase = false;
        if (writeChildren(genericRecord, writeProperties) || writeProperties) {
            this.fw.append(tabs);
            this.fw.println("</" + str + ">");
            return;
        }
        this.fw.println("/>");
    }

    /* access modifiers changed from: protected */
    public boolean writeProperties(GenericRecord genericRecord) {
        Map<String, Supplier<?>> genericProperties = genericRecord.getGenericProperties();
        if (genericProperties == null || genericProperties.isEmpty()) {
            return false;
        }
        int i = this.childIndex;
        this.childIndex = 0;
        List list = (List) genericProperties.entrySet().stream().flatMap(new GenericRecordXmlWriter$$ExternalSyntheticLambda13(this)).collect(Collectors.toList());
        this.attributePhase = false;
        if (!list.isEmpty()) {
            this.fw.println(">");
            this.indent++;
            list.forEach(new GenericRecordXmlWriter$$ExternalSyntheticLambda14(this));
            this.indent--;
        }
        this.childIndex = i;
        return !list.isEmpty();
    }

    /* access modifiers changed from: protected */
    public boolean writeChildren(GenericRecord genericRecord, boolean z) {
        List<? extends GenericRecord> genericChildren = genericRecord.getGenericChildren();
        if (genericChildren == null || genericChildren.isEmpty()) {
            return false;
        }
        if (!z) {
            this.fw.print(">");
        }
        this.indent++;
        this.fw.println();
        this.fw.println(tabs() + "<children>");
        this.indent++;
        int i = this.childIndex;
        this.childIndex = 0;
        genericChildren.forEach(new GenericRecordXmlWriter$$ExternalSyntheticLambda16(this));
        this.childIndex = i;
        this.fw.println();
        this.indent--;
        this.fw.println(tabs() + "</children>");
        this.indent--;
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$writeChildren$0$org-apache-poi-util-GenericRecordXmlWriter  reason: not valid java name */
    public /* synthetic */ void m2292lambda$writeChildren$0$orgapachepoiutilGenericRecordXmlWriter(GenericRecord genericRecord) {
        writeValue("record", genericRecord);
        this.childIndex++;
    }

    public void writeError(String str) {
        printObject("error", str);
    }

    /* access modifiers changed from: protected */
    public Stream<Map.Entry<String, Supplier<?>>> writeProp(Map.Entry<String, Supplier<?>> entry) {
        Object obj = entry.getValue().get();
        if (obj == null) {
            return Stream.empty();
        }
        boolean isComplex = isComplex(obj);
        if (this.attributePhase == isComplex) {
            return isComplex ? Stream.of(new AbstractMap.SimpleEntry(entry.getKey(), new GenericRecordXmlWriter$$ExternalSyntheticLambda15(obj))) : Stream.empty();
        }
        int i = this.childIndex;
        this.childIndex = 0;
        writeValue(entry.getKey(), obj);
        this.childIndex = i;
        return Stream.empty();
    }

    protected static boolean isComplex(Object obj) {
        return !(obj instanceof Number) && !(obj instanceof Boolean) && !(obj instanceof Character) && !(obj instanceof String) && !(obj instanceof Color) && !(obj instanceof Enum);
    }

    /* access modifiers changed from: protected */
    public void writeValue(String str, Object obj) {
        if (obj instanceof GenericRecord) {
            printGenericRecord(str, obj);
        } else if (obj != null) {
            if (str.endsWith(">")) {
                this.fw.print("\t");
            }
            handler.stream().filter(new GenericRecordXmlWriter$$ExternalSyntheticLambda0(obj)).findFirst().ifPresent(new GenericRecordXmlWriter$$ExternalSyntheticLambda11(this, str, obj));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$writeValue$3$org-apache-poi-util-GenericRecordXmlWriter  reason: not valid java name */
    public /* synthetic */ void m2293lambda$writeValue$3$orgapachepoiutilGenericRecordXmlWriter(String str, Object obj, Map.Entry entry) {
        ((GenericRecordHandler) entry.getValue()).print(this, str, obj);
    }

    /* access modifiers changed from: protected */
    public static boolean matchInstanceOrArray(Class<?> cls, Object obj) {
        return cls.isInstance(obj) || (Array.class.equals(cls) && obj.getClass().isArray());
    }

    /* access modifiers changed from: protected */
    public void openName(String str) {
        String replace = str.replace(">>", ">");
        if (this.attributePhase) {
            this.fw.print(" " + replace.replace(Typography.greater, Chars.SPACE).trim() + "=\"");
            return;
        }
        this.fw.print(tabs() + "<" + replace);
        if (replace.endsWith(">")) {
            this.fw.println();
        }
    }

    /* access modifiers changed from: protected */
    public void closeName(String str) {
        String replace = str.replace(">>", ">");
        if (this.attributePhase) {
            this.fw.append("\"");
        } else if (replace.endsWith(">")) {
            this.fw.println(tabs() + "\t</" + replace);
        } else {
            this.fw.println("/>");
        }
    }

    /* access modifiers changed from: protected */
    public boolean printNumber(String str, Object obj) {
        openName(str);
        this.fw.print(((Number) obj).toString());
        closeName(str);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean printBoolean(String str, Object obj) {
        openName(str);
        this.fw.write(((Boolean) obj).toString());
        closeName(str);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean printList(String str, Object obj) {
        openName(str + ">");
        int i = this.childIndex;
        this.childIndex = 0;
        ((List) obj).forEach(new GenericRecordXmlWriter$$ExternalSyntheticLambda12(this));
        this.childIndex = i;
        closeName(str + ">");
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$printList$4$org-apache-poi-util-GenericRecordXmlWriter  reason: not valid java name */
    public /* synthetic */ void m2291lambda$printList$4$orgapachepoiutilGenericRecordXmlWriter(Object obj) {
        writeValue("item>", obj);
        this.childIndex++;
    }

    /* access modifiers changed from: protected */
    public boolean printArray(String str, Object obj) {
        openName(str + ">");
        int length = Array.getLength(obj);
        int i = this.childIndex;
        int i2 = 0;
        while (true) {
            this.childIndex = i2;
            int i3 = this.childIndex;
            if (i3 < length) {
                writeValue("item>", Array.get(obj, i3));
                i2 = this.childIndex + 1;
            } else {
                this.childIndex = i;
                closeName(str + ">");
                return true;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void printGenericRecord(String str, Object obj) {
        write(str, (GenericRecord) obj);
    }

    /* access modifiers changed from: protected */
    public boolean printAnnotatedFlag(String str, Object obj) {
        int i;
        GenericRecordUtil.AnnotatedFlag annotatedFlag = (GenericRecordUtil.AnnotatedFlag) obj;
        Number number = annotatedFlag.getValue().get();
        if (number instanceof Byte) {
            i = 2;
        } else if (number instanceof Short) {
            i = 4;
        } else {
            i = number instanceof Integer ? 8 : 16;
        }
        openName(str);
        this.fw.print(" flag=\"0x");
        this.fw.print(trimHex(number.longValue(), i));
        this.fw.print('\"');
        if (this.withComments) {
            this.fw.print(" description=\"");
            this.fw.print(annotatedFlag.getDescription());
            this.fw.print("\"");
        }
        closeName(str);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean printBytes(String str, Object obj) {
        openName(str + ">");
        this.fw.write(Base64.getEncoder().encodeToString((byte[]) obj));
        closeName(str + ">");
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean printPoint(String str, Object obj) {
        openName(str);
        Point2D point2D = (Point2D) obj;
        this.fw.println(" x=\"" + point2D.getX() + "\" y=\"" + point2D.getY() + "\"/>");
        closeName(str);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean printDimension(String str, Object obj) {
        openName(str);
        Dimension2D dimension2D = (Dimension2D) obj;
        this.fw.println(" width=\"" + dimension2D.getWidth() + "\" height=\"" + dimension2D.getHeight() + "\"/>");
        closeName(str);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean printRectangle(String str, Object obj) {
        openName(str);
        Rectangle2D rectangle2D = (Rectangle2D) obj;
        this.fw.println(" x=\"" + rectangle2D.getX() + "\" y=\"" + rectangle2D.getY() + "\" width=\"" + rectangle2D.getWidth() + "\" height=\"" + rectangle2D.getHeight() + "\"/>");
        closeName(str);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean printPath(String str, Object obj) {
        int i;
        String str2 = str;
        openName(str2 + ">");
        PathIterator pathIterator = ((Path2D) obj).getPathIterator((AffineTransform) null);
        double[] dArr = new double[6];
        int i2 = 2;
        this.indent += 2;
        String tabs = tabs();
        this.indent -= 2;
        while (!pathIterator.isDone()) {
            this.fw.print(tabs);
            int currentSegment = pathIterator.currentSegment(dArr);
            this.fw.print("<pathelement ");
            if (currentSegment == 0) {
                i = i2;
                this.fw.print("type=\"move\" x=\"" + dArr[0] + "\" y=\"" + dArr[1] + "\"");
            } else if (currentSegment == 1) {
                i = i2;
                this.fw.print("type=\"lineto\" x=\"" + dArr[0] + "\" y=\"" + dArr[1] + "\"");
            } else if (currentSegment == i2) {
                i = 2;
                this.fw.print("type=\"quad\" x1=\"" + dArr[0] + "\" y1=\"" + dArr[1] + "\" x2=\"" + dArr[2] + "\" y2=\"" + dArr[3] + "\"");
            } else if (currentSegment != 3) {
                if (currentSegment == 4) {
                    this.fw.print("type=\"close\"");
                }
                i = i2;
            } else {
                this.fw.print("type=\"cubic\" x1=\"" + dArr[0] + "\" y1=\"" + dArr[1] + "\" x2=\"" + dArr[2] + "\" y2=\"" + dArr[3] + "\" x3=\"" + dArr[4] + "\" y3=\"" + dArr[5] + "\"");
                i = 2;
            }
            this.fw.println("/>");
            pathIterator.next();
            i2 = i;
        }
        closeName(str2 + ">");
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0068, code lost:
        if (r3.equals("&") == false) goto L_0x0041;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean printObject(java.lang.String r8, java.lang.Object r9) {
        /*
            r7 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.StringBuilder r0 = r0.append(r8)
            java.lang.String r1 = ">"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.openName(r0)
            java.lang.String r9 = r9.toString()
            java.util.regex.Pattern r0 = ESC_CHARS
            java.util.regex.Matcher r0 = r0.matcher(r9)
            r2 = 0
            r3 = r2
        L_0x0022:
            boolean r4 = r0.find()
            r5 = 1
            if (r4 == 0) goto L_0x00c2
            java.io.PrintWriter r4 = r7.fw
            int r6 = r0.start()
            r4.write(r9, r3, r6)
            java.lang.String r3 = r0.group()
            r3.hashCode()
            int r4 = r3.hashCode()
            r6 = -1
            switch(r4) {
                case 34: goto L_0x006b;
                case 38: goto L_0x0062;
                case 39: goto L_0x0057;
                case 60: goto L_0x004c;
                case 62: goto L_0x0043;
                default: goto L_0x0041;
            }
        L_0x0041:
            r5 = r6
            goto L_0x0075
        L_0x0043:
            boolean r4 = r3.equals(r1)
            if (r4 != 0) goto L_0x004a
            goto L_0x0041
        L_0x004a:
            r5 = 4
            goto L_0x0075
        L_0x004c:
            java.lang.String r4 = "<"
            boolean r4 = r3.equals(r4)
            if (r4 != 0) goto L_0x0055
            goto L_0x0041
        L_0x0055:
            r5 = 3
            goto L_0x0075
        L_0x0057:
            java.lang.String r4 = "'"
            boolean r4 = r3.equals(r4)
            if (r4 != 0) goto L_0x0060
            goto L_0x0041
        L_0x0060:
            r5 = 2
            goto L_0x0075
        L_0x0062:
            java.lang.String r4 = "&"
            boolean r4 = r3.equals(r4)
            if (r4 != 0) goto L_0x0075
            goto L_0x0041
        L_0x006b:
            java.lang.String r4 = "\""
            boolean r4 = r3.equals(r4)
            if (r4 != 0) goto L_0x0074
            goto L_0x0041
        L_0x0074:
            r5 = r2
        L_0x0075:
            switch(r5) {
                case 0: goto L_0x00b5;
                case 1: goto L_0x00ad;
                case 2: goto L_0x00a5;
                case 3: goto L_0x009d;
                case 4: goto L_0x0095;
                default: goto L_0x0078;
            }
        L_0x0078:
            java.io.PrintWriter r4 = r7.fw
            java.lang.String r5 = "&#x"
            r4.write(r5)
            java.io.PrintWriter r4 = r7.fw
            int r3 = r3.codePointAt(r2)
            long r5 = (long) r3
            java.lang.String r3 = java.lang.Long.toHexString(r5)
            r4.write(r3)
            java.io.PrintWriter r3 = r7.fw
            java.lang.String r4 = ";"
            r3.write(r4)
            goto L_0x00bc
        L_0x0095:
            java.io.PrintWriter r3 = r7.fw
            java.lang.String r4 = "&gt;"
            r3.write(r4)
            goto L_0x00bc
        L_0x009d:
            java.io.PrintWriter r3 = r7.fw
            java.lang.String r4 = "&lt;"
            r3.write(r4)
            goto L_0x00bc
        L_0x00a5:
            java.io.PrintWriter r3 = r7.fw
            java.lang.String r4 = "&apos;"
            r3.write(r4)
            goto L_0x00bc
        L_0x00ad:
            java.io.PrintWriter r3 = r7.fw
            java.lang.String r4 = "&amp;"
            r3.write(r4)
            goto L_0x00bc
        L_0x00b5:
            java.io.PrintWriter r3 = r7.fw
            java.lang.String r4 = "&quot;"
            r3.write(r4)
        L_0x00bc:
            int r3 = r0.end()
            goto L_0x0022
        L_0x00c2:
            java.io.PrintWriter r0 = r7.fw
            int r2 = r9.length()
            r0.append(r9, r3, r2)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.StringBuilder r8 = r9.append(r8)
            java.lang.StringBuilder r8 = r8.append(r1)
            java.lang.String r8 = r8.toString()
            r7.closeName(r8)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.util.GenericRecordXmlWriter.printObject(java.lang.String, java.lang.Object):boolean");
    }

    /* access modifiers changed from: protected */
    public boolean printAffineTransform(String str, Object obj) {
        openName(str);
        AffineTransform affineTransform = (AffineTransform) obj;
        this.fw.write("<" + str + " scaleX=\"" + affineTransform.getScaleX() + "\" shearX=\"" + affineTransform.getShearX() + "\" transX=\"" + affineTransform.getTranslateX() + "\" scaleY=\"" + affineTransform.getScaleY() + "\" shearY=\"" + affineTransform.getShearY() + "\" transY=\"" + affineTransform.getTranslateY() + "\"/>");
        closeName(str);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean printColor(String str, Object obj) {
        openName(str);
        this.fw.print("0x" + trimHex((long) ((Color) obj).getRGB(), 8));
        closeName(str);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean printBufferedImage(String str, Object obj) {
        openName(str);
        BufferedImage bufferedImage = (BufferedImage) obj;
        this.fw.println(" width=\"" + bufferedImage.getWidth() + "\" height=\"" + bufferedImage.getHeight() + "\" bands=\"" + bufferedImage.getColorModel().getNumComponents() + "\"");
        closeName(str);
        return true;
    }

    /* access modifiers changed from: protected */
    public String trimHex(long j, int i) {
        String hexString = Long.toHexString(j);
        int length = hexString.length();
        return ZEROS.substring(0, Math.max(0, i - length)) + hexString.substring(Math.max(0, length - i), length);
    }
}
