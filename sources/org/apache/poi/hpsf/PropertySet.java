package org.apache.poi.hpsf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.poi.EmptyFileException;
import org.apache.poi.hpsf.wellknown.PropertyIDMap;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.util.CodePageUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.NotImplemented;

public class PropertySet {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int BYTE_ORDER_ASSERTION = 65534;
    static final int FORMAT_ASSERTION = 0;
    static final int OFFSET_HEADER = 28;
    public static final int OS_MACINTOSH = 1;
    public static final int OS_WIN16 = 0;
    public static final int OS_WIN32 = 2;
    private int byteOrder;
    private ClassID classID;
    private int format;
    private int osVersion;
    private final List<Section> sections;

    public PropertyIDMap getPropertySetIDMap() {
        return null;
    }

    public PropertySet() {
        this.sections = new ArrayList();
        this.byteOrder = BYTE_ORDER_ASSERTION;
        this.format = 0;
        this.osVersion = 133636;
        this.classID = new ClassID();
        addSection(new Section());
    }

    public PropertySet(InputStream inputStream) throws NoPropertySetStreamException, IOException {
        this.sections = new ArrayList();
        if (isPropertySetStream(inputStream)) {
            byte[] byteArray = IOUtils.toByteArray(inputStream);
            init(byteArray, 0, byteArray.length);
            return;
        }
        throw new NoPropertySetStreamException();
    }

    public PropertySet(byte[] bArr, int i, int i2) throws NoPropertySetStreamException, UnsupportedEncodingException {
        this.sections = new ArrayList();
        if (isPropertySetStream(bArr, i, i2)) {
            init(bArr, i, i2);
            return;
        }
        throw new NoPropertySetStreamException();
    }

    public PropertySet(byte[] bArr) throws NoPropertySetStreamException, UnsupportedEncodingException {
        this(bArr, 0, bArr.length);
    }

    public PropertySet(PropertySet propertySet) {
        this.sections = new ArrayList();
        setByteOrder(propertySet.getByteOrder());
        setFormat(propertySet.getFormat());
        setOSVersion(propertySet.getOSVersion());
        setClassID(propertySet.getClassID());
        for (Section section : propertySet.getSections()) {
            this.sections.add(new Section(section));
        }
    }

    public int getByteOrder() {
        return this.byteOrder;
    }

    public void setByteOrder(int i) {
        this.byteOrder = i;
    }

    public int getFormat() {
        return this.format;
    }

    public void setFormat(int i) {
        this.format = i;
    }

    public int getOSVersion() {
        return this.osVersion;
    }

    public void setOSVersion(int i) {
        this.osVersion = i;
    }

    public ClassID getClassID() {
        return this.classID;
    }

    public void setClassID(ClassID classID2) {
        this.classID = classID2;
    }

    public int getSectionCount() {
        return this.sections.size();
    }

    public List<Section> getSections() {
        return Collections.unmodifiableList(this.sections);
    }

    public void addSection(Section section) {
        this.sections.add(section);
    }

    public void clearSections() {
        this.sections.clear();
    }

    public static boolean isPropertySetStream(InputStream inputStream) throws IOException {
        try {
            byte[] peekFirstNBytes = IOUtils.peekFirstNBytes(inputStream, 50);
            return isPropertySetStream(peekFirstNBytes, 0, peekFirstNBytes.length);
        } catch (EmptyFileException unused) {
            return false;
        }
    }

    public static boolean isPropertySetStream(byte[] bArr, int i, int i2) {
        LittleEndianByteArrayInputStream littleEndianByteArrayInputStream = new LittleEndianByteArrayInputStream(bArr, i, i2);
        try {
            if (littleEndianByteArrayInputStream.readUShort() != BYTE_ORDER_ASSERTION || littleEndianByteArrayInputStream.readUShort() != 0) {
                return false;
            }
            littleEndianByteArrayInputStream.readUInt();
            if (littleEndianByteArrayInputStream.skip(16) == 16 && littleEndianByteArrayInputStream.readUInt() >= 0) {
                return true;
            }
            return false;
        } catch (RuntimeException unused) {
            return false;
        }
    }

    private void init(byte[] bArr, int i, int i2) throws UnsupportedEncodingException {
        this.byteOrder = LittleEndian.getUShort(bArr, i);
        int i3 = i + 2;
        this.format = LittleEndian.getUShort(bArr, i3);
        int i4 = i3 + 2;
        this.osVersion = (int) LittleEndian.getUInt(bArr, i4);
        int i5 = i4 + 4;
        this.classID = new ClassID(bArr, i5);
        int i6 = i5 + 16;
        int i7 = LittleEndian.getInt(bArr, i6);
        int i8 = i6 + 4;
        if (i7 >= 0) {
            for (int i9 = 0; i9 < i7; i9++) {
                Section section = new Section(bArr, i8);
                i8 += 20;
                this.sections.add(section);
            }
            return;
        }
        throw new HPSFRuntimeException("Section count " + i7 + " is negative.");
    }

    public void write(OutputStream outputStream) throws IOException, WritingNotSupportedException {
        outputStream.write(toBytes());
        outputStream.close();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00b7, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00bc, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r9.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00c0, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00c3, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00c8, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00c9, code lost:
        r9.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00cc, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] toBytes() throws org.apache.poi.hpsf.WritingNotSupportedException, java.io.IOException {
        /*
            r9 = this;
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r0 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream
            r0.<init>()
            org.apache.poi.util.LittleEndianOutputStream r1 = new org.apache.poi.util.LittleEndianOutputStream     // Catch:{ all -> 0x00c1 }
            r1.<init>(r0)     // Catch:{ all -> 0x00c1 }
            int r2 = r9.getSectionCount()     // Catch:{ all -> 0x00b5 }
            int r3 = r9.getByteOrder()     // Catch:{ all -> 0x00b5 }
            r1.writeShort(r3)     // Catch:{ all -> 0x00b5 }
            int r3 = r9.getFormat()     // Catch:{ all -> 0x00b5 }
            r1.writeShort(r3)     // Catch:{ all -> 0x00b5 }
            int r3 = r9.getOSVersion()     // Catch:{ all -> 0x00b5 }
            r1.writeInt(r3)     // Catch:{ all -> 0x00b5 }
            org.apache.poi.hpsf.ClassID r3 = r9.getClassID()     // Catch:{ all -> 0x00b5 }
            putClassId(r0, r3)     // Catch:{ all -> 0x00b5 }
            r1.writeInt(r2)     // Catch:{ all -> 0x00b5 }
            int r2 = r9.getSectionCount()     // Catch:{ all -> 0x00b5 }
            r3 = 2
            int[] r4 = new int[r3]     // Catch:{ all -> 0x00b5 }
            r5 = 1
            r4[r5] = r3     // Catch:{ all -> 0x00b5 }
            r3 = 0
            r4[r3] = r2     // Catch:{ all -> 0x00b5 }
            java.lang.Class r2 = java.lang.Integer.TYPE     // Catch:{ all -> 0x00b5 }
            java.lang.Object r2 = java.lang.reflect.Array.newInstance(r2, r4)     // Catch:{ all -> 0x00b5 }
            int[][] r2 = (int[][]) r2     // Catch:{ all -> 0x00b5 }
            java.util.List r4 = r9.getSections()     // Catch:{ all -> 0x00b5 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x00b5 }
            r6 = r3
        L_0x004b:
            boolean r7 = r4.hasNext()     // Catch:{ all -> 0x00b5 }
            if (r7 == 0) goto L_0x0076
            java.lang.Object r7 = r4.next()     // Catch:{ all -> 0x00b5 }
            org.apache.poi.hpsf.Section r7 = (org.apache.poi.hpsf.Section) r7     // Catch:{ all -> 0x00b5 }
            org.apache.poi.hpsf.ClassID r7 = r7.getFormatID()     // Catch:{ all -> 0x00b5 }
            if (r7 == 0) goto L_0x0070
            putClassId(r0, r7)     // Catch:{ all -> 0x00b5 }
            int r7 = r6 + 1
            r6 = r2[r6]     // Catch:{ all -> 0x00b5 }
            int r8 = r0.size()     // Catch:{ all -> 0x00b5 }
            r6[r3] = r8     // Catch:{ all -> 0x00b5 }
            r6 = -1
            r1.writeInt(r6)     // Catch:{ all -> 0x00b5 }
            r6 = r7
            goto L_0x004b
        L_0x0070:
            org.apache.poi.hpsf.NoFormatIDException r9 = new org.apache.poi.hpsf.NoFormatIDException     // Catch:{ all -> 0x00b5 }
            r9.<init>()     // Catch:{ all -> 0x00b5 }
            throw r9     // Catch:{ all -> 0x00b5 }
        L_0x0076:
            java.util.List r9 = r9.getSections()     // Catch:{ all -> 0x00b5 }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x00b5 }
            r4 = r3
        L_0x007f:
            boolean r6 = r9.hasNext()     // Catch:{ all -> 0x00b5 }
            if (r6 == 0) goto L_0x009a
            java.lang.Object r6 = r9.next()     // Catch:{ all -> 0x00b5 }
            org.apache.poi.hpsf.Section r6 = (org.apache.poi.hpsf.Section) r6     // Catch:{ all -> 0x00b5 }
            int r7 = r4 + 1
            r4 = r2[r4]     // Catch:{ all -> 0x00b5 }
            int r8 = r0.size()     // Catch:{ all -> 0x00b5 }
            r4[r5] = r8     // Catch:{ all -> 0x00b5 }
            r6.write(r0)     // Catch:{ all -> 0x00b5 }
            r4 = r7
            goto L_0x007f
        L_0x009a:
            byte[] r9 = r0.toByteArray()     // Catch:{ all -> 0x00b5 }
            int r4 = r2.length     // Catch:{ all -> 0x00b5 }
            r6 = r3
        L_0x00a0:
            if (r6 >= r4) goto L_0x00ae
            r7 = r2[r6]     // Catch:{ all -> 0x00b5 }
            r8 = r7[r3]     // Catch:{ all -> 0x00b5 }
            r7 = r7[r5]     // Catch:{ all -> 0x00b5 }
            org.apache.poi.util.LittleEndian.putInt(r9, r8, r7)     // Catch:{ all -> 0x00b5 }
            int r6 = r6 + 1
            goto L_0x00a0
        L_0x00ae:
            r1.close()     // Catch:{ all -> 0x00c1 }
            r0.close()
            return r9
        L_0x00b5:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x00b7 }
        L_0x00b7:
            r2 = move-exception
            r1.close()     // Catch:{ all -> 0x00bc }
            goto L_0x00c0
        L_0x00bc:
            r1 = move-exception
            r9.addSuppressed(r1)     // Catch:{ all -> 0x00c1 }
        L_0x00c0:
            throw r2     // Catch:{ all -> 0x00c1 }
        L_0x00c1:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x00c3 }
        L_0x00c3:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x00c8 }
            goto L_0x00cc
        L_0x00c8:
            r0 = move-exception
            r9.addSuppressed(r0)
        L_0x00cc:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hpsf.PropertySet.toBytes():byte[]");
    }

    public void write(DirectoryEntry directoryEntry, String str) throws WritingNotSupportedException, IOException {
        if (directoryEntry.hasEntry(str)) {
            directoryEntry.getEntry(str).delete();
        }
        directoryEntry.createDocument(str, toInputStream());
    }

    public InputStream toInputStream() throws WritingNotSupportedException, IOException {
        return new UnsynchronizedByteArrayInputStream(toBytes());
    }

    /* access modifiers changed from: package-private */
    public String getPropertyStringValue(int i) {
        return getPropertyStringValue(getProperty(i));
    }

    public static String getPropertyStringValue(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        if (!(obj instanceof byte[])) {
            return obj.toString();
        }
        byte[] bArr = (byte[]) obj;
        int length = bArr.length;
        if (length == 0) {
            return "";
        }
        if (length == 1) {
            return Byte.toString(bArr[0]);
        }
        if (length == 2) {
            return Integer.toString(LittleEndian.getUShort(bArr));
        }
        if (length == 4) {
            return Long.toString(LittleEndian.getUInt(bArr));
        }
        try {
            return CodePageUtil.getStringFromCodePage(bArr, 1252);
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    public boolean isSummaryInformation() {
        if (this.sections.isEmpty()) {
            return false;
        }
        return matchesSummary(getFirstSection().getFormatID(), SummaryInformation.FORMAT_ID);
    }

    public boolean isDocumentSummaryInformation() {
        return !this.sections.isEmpty() && matchesSummary(getFirstSection().getFormatID(), DocumentSummaryInformation.FORMAT_ID);
    }

    static boolean matchesSummary(ClassID classID2, ClassID... classIDArr) {
        for (ClassID classID3 : classIDArr) {
            if (classID3.equals(classID2) || classID3.equalsInverted(classID2)) {
                return true;
            }
        }
        return false;
    }

    public Property[] getProperties() throws NoSingleSectionException {
        return getFirstSection().getProperties();
    }

    /* access modifiers changed from: protected */
    public Object getProperty(int i) throws NoSingleSectionException {
        return getFirstSection().getProperty((long) i);
    }

    /* access modifiers changed from: package-private */
    public boolean getPropertyBooleanValue(int i) throws NoSingleSectionException {
        return getFirstSection().getPropertyBooleanValue(i);
    }

    /* access modifiers changed from: package-private */
    public int getPropertyIntValue(int i) throws NoSingleSectionException {
        return getFirstSection().getPropertyIntValue((long) i);
    }

    public boolean wasNull() throws NoSingleSectionException {
        return getFirstSection().wasNull();
    }

    public Section getFirstSection() {
        if (!this.sections.isEmpty()) {
            return this.sections.get(0);
        }
        throw new MissingSectionException("Property set does not contain any sections.");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PropertySet)) {
            return false;
        }
        PropertySet propertySet = (PropertySet) obj;
        int byteOrder2 = propertySet.getByteOrder();
        int byteOrder3 = getByteOrder();
        ClassID classID2 = propertySet.getClassID();
        ClassID classID3 = getClassID();
        int format2 = propertySet.getFormat();
        int format3 = getFormat();
        int oSVersion = propertySet.getOSVersion();
        int oSVersion2 = getOSVersion();
        int sectionCount = propertySet.getSectionCount();
        int sectionCount2 = getSectionCount();
        if (byteOrder2 == byteOrder3 && classID2.equals(classID3) && format2 == format3 && oSVersion == oSVersion2 && sectionCount == sectionCount2) {
            return getSections().containsAll(propertySet.getSections());
        }
        return false;
    }

    @NotImplemented
    public int hashCode() {
        throw new UnsupportedOperationException("FIXME: Not yet implemented.");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int sectionCount = getSectionCount();
        sb.append(getClass().getName());
        sb.append("[byteOrder: ");
        sb.append(getByteOrder());
        sb.append(", classID: ");
        sb.append(getClassID());
        sb.append(", format: ");
        sb.append(getFormat());
        sb.append(", OSVersion: ");
        sb.append(getOSVersion());
        sb.append(", sectionCount: ");
        sb.append(sectionCount);
        sb.append(", sections: [\n");
        for (Section section : getSections()) {
            sb.append(section.toString(getPropertySetIDMap()));
        }
        sb.append("]]");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public void remove1stProperty(long j) {
        getFirstSection().removeProperty(j);
    }

    /* access modifiers changed from: package-private */
    public void set1stProperty(long j, String str) {
        getFirstSection().setProperty((int) j, str);
    }

    /* access modifiers changed from: package-private */
    public void set1stProperty(long j, int i) {
        getFirstSection().setProperty((int) j, i);
    }

    /* access modifiers changed from: package-private */
    public void set1stProperty(long j, boolean z) {
        getFirstSection().setProperty((int) j, z);
    }

    /* access modifiers changed from: package-private */
    public void set1stProperty(long j, byte[] bArr) {
        getFirstSection().setProperty((int) j, (Object) bArr);
    }

    private static void putClassId(UnsynchronizedByteArrayOutputStream unsynchronizedByteArrayOutputStream, ClassID classID2) {
        byte[] bArr = new byte[16];
        classID2.write(bArr, 0);
        unsynchronizedByteArrayOutputStream.write(bArr, 0, 16);
    }
}
