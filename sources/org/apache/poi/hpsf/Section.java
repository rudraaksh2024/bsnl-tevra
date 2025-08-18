package org.apache.poi.hpsf;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hpsf.wellknown.PropertyIDMap;
import org.apache.poi.util.CodePageUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianByteArrayInputStream;

public class Section {
    private static final Logger LOG = LogManager.getLogger((Class<?>) Section.class);
    private final long _offset;
    private Map<Long, String> dictionary;
    private ClassID formatID;
    private final Map<Long, Property> properties;
    private final UnsynchronizedByteArrayOutputStream sectionBytes;
    private transient boolean wasNull;

    public Section() {
        this.sectionBytes = new UnsynchronizedByteArrayOutputStream();
        this.properties = new LinkedHashMap();
        this._offset = -1;
    }

    public Section(Section section) {
        this.sectionBytes = new UnsynchronizedByteArrayOutputStream();
        this.properties = new LinkedHashMap();
        this._offset = -1;
        setFormatID(section.getFormatID());
        for (Property next : section.properties.values()) {
            this.properties.put(Long.valueOf(next.getID()), new Property(next));
        }
        setDictionary(section.getDictionary());
    }

    public Section(byte[] bArr, int i) throws UnsupportedEncodingException {
        int i2;
        long j;
        byte[] bArr2 = bArr;
        int i3 = i;
        this.sectionBytes = new UnsynchronizedByteArrayOutputStream();
        this.properties = new LinkedHashMap();
        this.formatID = new ClassID(bArr2, i3);
        int uInt = (int) LittleEndian.getUInt(bArr2, i3 + 16);
        if (bArr2[uInt] == 0) {
            int i4 = 0;
            while (i4 < 3 && bArr2[uInt] == 0) {
                i4++;
                uInt++;
            }
            int i5 = 0;
            while (i5 < 3 && (bArr2[uInt + 3] != 0 || bArr2[uInt + 7] != 0 || bArr2[uInt + 11] != 0)) {
                i5++;
                uInt--;
            }
        }
        long j2 = (long) uInt;
        this._offset = j2;
        LittleEndianByteArrayInputStream littleEndianByteArrayInputStream = new LittleEndianByteArrayInputStream(bArr2, uInt);
        int min = (int) Math.min(littleEndianByteArrayInputStream.readUInt(), ((long) bArr2.length) - j2);
        int readUInt = (int) littleEndianByteArrayInputStream.readUInt();
        TreeBidiMap treeBidiMap = new TreeBidiMap();
        for (int i6 = 0; i6 < readUInt; i6++) {
            treeBidiMap.put(Long.valueOf(littleEndianByteArrayInputStream.readUInt()), Long.valueOf(littleEndianByteArrayInputStream.readUInt()));
        }
        long j3 = 1;
        Long l = (Long) treeBidiMap.getKey((Object) 1L);
        if (l != null) {
            littleEndianByteArrayInputStream.setReadIndex(Math.toIntExact(this._offset + l.longValue()));
            long readUInt2 = littleEndianByteArrayInputStream.readUInt();
            if (readUInt2 == 2) {
                i2 = littleEndianByteArrayInputStream.readUShort();
                setCodepage(i2);
            } else {
                throw new HPSFRuntimeException("Value type of property ID 1 is not VT_I2 but " + readUInt2 + ".");
            }
        } else {
            i2 = -1;
        }
        int i7 = i2;
        Iterator it = treeBidiMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            long longValue = ((Long) entry.getKey()).longValue();
            long longValue2 = ((Long) entry.getValue()).longValue();
            if (longValue2 != j3) {
                int propLen = propLen(treeBidiMap, Long.valueOf(longValue), (long) min);
                Iterator it2 = it;
                littleEndianByteArrayInputStream.setReadIndex(Math.toIntExact(this._offset + longValue));
                if (longValue2 == 0) {
                    littleEndianByteArrayInputStream.mark(BZip2Constants.BASEBLOCKSIZE);
                    if (!readDictionary(littleEndianByteArrayInputStream, propLen, i7)) {
                        littleEndianByteArrayInputStream.reset();
                        try {
                            j = 1;
                            try {
                                setProperty(new Property(Math.max(31, ((Long) treeBidiMap.inverseBidiMap().lastKey()).longValue()) + 1, littleEndianByteArrayInputStream, propLen, i7));
                            } catch (RuntimeException unused) {
                            }
                        } catch (RuntimeException unused2) {
                            j = 1;
                            LOG.atInfo().log("Dictionary fallback failed - ignoring property");
                            j3 = j;
                            it = it2;
                        }
                    } else {
                        j = 1;
                    }
                } else {
                    j = 1;
                    setProperty(new Property(longValue2, littleEndianByteArrayInputStream, propLen, i7));
                }
                j3 = j;
                it = it2;
            }
        }
        this.sectionBytes.write(bArr2, Math.toIntExact(this._offset), min);
        padSectionBytes();
    }

    private static int propLen(TreeBidiMap<Long, Long> treeBidiMap, Long l, long j) {
        Long nextKey = treeBidiMap.nextKey(l);
        long longValue = l.longValue();
        if (nextKey != null) {
            j = nextKey.longValue();
        }
        return Math.toIntExact(j - longValue);
    }

    public ClassID getFormatID() {
        return this.formatID;
    }

    public void setFormatID(ClassID classID) {
        this.formatID = classID;
    }

    public void setFormatID(byte[] bArr) {
        ClassID formatID2 = getFormatID();
        if (formatID2 == null) {
            formatID2 = new ClassID();
            setFormatID(formatID2);
        }
        formatID2.setBytes(bArr);
    }

    public long getOffset() {
        return this._offset;
    }

    public int getPropertyCount() {
        return this.properties.size();
    }

    public Property[] getProperties() {
        return (Property[]) this.properties.values().toArray(new Property[0]);
    }

    public void setProperties(Property[] propertyArr) {
        this.properties.clear();
        for (Property property : propertyArr) {
            setProperty(property);
        }
    }

    public Object getProperty(long j) {
        boolean z = !this.properties.containsKey(Long.valueOf(j));
        this.wasNull = z;
        if (z) {
            return null;
        }
        return this.properties.get(Long.valueOf(j)).getValue();
    }

    public void setProperty(int i, String str) {
        setProperty(i, 30, str);
    }

    public void setProperty(int i, int i2) {
        setProperty(i, 3, Integer.valueOf(i2));
    }

    public void setProperty(int i, long j) {
        setProperty(i, 20, Long.valueOf(j));
    }

    public void setProperty(int i, boolean z) {
        setProperty(i, 11, Boolean.valueOf(z));
    }

    public void setProperty(int i, long j, Object obj) {
        setProperty(new Property((long) i, j, obj));
    }

    public void setProperty(Property property) {
        Property property2 = this.properties.get(Long.valueOf(property.getID()));
        if (property2 == null || !property2.equals(property)) {
            this.properties.put(Long.valueOf(property.getID()), property);
            this.sectionBytes.reset();
        }
    }

    public void setProperty(int i, Object obj) {
        if (obj instanceof String) {
            setProperty(i, (String) obj);
        } else if (obj instanceof Long) {
            setProperty(i, ((Long) obj).longValue());
        } else if (obj instanceof Integer) {
            setProperty(i, ((Integer) obj).intValue());
        } else if (obj instanceof Short) {
            setProperty(i, ((Short) obj).intValue());
        } else if (obj instanceof Boolean) {
            setProperty(i, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Date) {
            setProperty(i, 64, obj);
        } else {
            throw new HPSFRuntimeException("HPSF does not support properties of type " + obj.getClass().getName() + ".");
        }
    }

    /* access modifiers changed from: package-private */
    public int getPropertyIntValue(long j) {
        Object property = getProperty(j);
        if (property == null) {
            return 0;
        }
        if ((property instanceof Long) || (property instanceof Integer)) {
            return ((Number) property).intValue();
        }
        throw new HPSFRuntimeException("This property is not an integer type, but " + property.getClass().getName() + ".");
    }

    /* access modifiers changed from: package-private */
    public boolean getPropertyBooleanValue(int i) {
        Boolean bool = (Boolean) getProperty((long) i);
        return bool != null && bool.booleanValue();
    }

    /* access modifiers changed from: protected */
    public void setPropertyBooleanValue(int i, boolean z) {
        setProperty(i, 11, Boolean.valueOf(z));
    }

    public int getSize() {
        int size = this.sectionBytes.size();
        if (size > 0) {
            return size;
        }
        try {
            return calcSize();
        } catch (HPSFRuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new HPSFRuntimeException((Throwable) e2);
        }
    }

    private int calcSize() throws WritingNotSupportedException, IOException {
        this.sectionBytes.reset();
        write(this.sectionBytes);
        padSectionBytes();
        return this.sectionBytes.size();
    }

    private void padSectionBytes() {
        this.sectionBytes.write(new byte[]{0, 0, 0}, 0, 3 & (4 - (this.sectionBytes.size() & 3)));
    }

    public boolean wasNull() {
        return this.wasNull;
    }

    public String getPIDString(long j) {
        Map dictionary2 = getDictionary();
        if (dictionary2 == null || !dictionary2.containsKey(Long.valueOf(j))) {
            ClassID formatID2 = getFormatID();
            if (SummaryInformation.FORMAT_ID.equals(formatID2)) {
                dictionary2 = PropertyIDMap.getSummaryInformationProperties();
            } else if (DocumentSummaryInformation.FORMAT_ID[0].equals(formatID2)) {
                dictionary2 = PropertyIDMap.getDocumentSummaryInformationProperties();
            }
        }
        return (dictionary2 == null || !dictionary2.containsKey(Long.valueOf(j))) ? PropertyIDMap.UNDEFINED : (String) dictionary2.get(Long.valueOf(j));
    }

    public void clear() {
        for (Property id : getProperties()) {
            removeProperty(id.getID());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0047  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof org.apache.poi.hpsf.Section
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            org.apache.poi.hpsf.Section r6 = (org.apache.poi.hpsf.Section) r6
            org.apache.poi.hpsf.ClassID r0 = r6.getFormatID()
            org.apache.poi.hpsf.ClassID r2 = r5.getFormatID()
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0017
            return r1
        L_0x0017:
            java.util.HashSet r0 = new java.util.HashSet
            java.util.Map<java.lang.Long, org.apache.poi.hpsf.Property> r2 = r5.properties
            java.util.Set r2 = r2.keySet()
            r0.<init>(r2)
            java.util.Map<java.lang.Long, org.apache.poi.hpsf.Property> r2 = r6.properties
            java.util.Set r2 = r2.keySet()
            r0.addAll(r2)
            r2 = 0
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r0.remove(r2)
            r2 = 1
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r0.remove(r2)
            java.util.Iterator r0 = r0.iterator()
        L_0x0041:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0066
            java.lang.Object r2 = r0.next()
            java.lang.Long r2 = (java.lang.Long) r2
            java.util.Map<java.lang.Long, org.apache.poi.hpsf.Property> r3 = r5.properties
            java.lang.Object r3 = r3.get(r2)
            org.apache.poi.hpsf.Property r3 = (org.apache.poi.hpsf.Property) r3
            java.util.Map<java.lang.Long, org.apache.poi.hpsf.Property> r4 = r6.properties
            java.lang.Object r2 = r4.get(r2)
            org.apache.poi.hpsf.Property r2 = (org.apache.poi.hpsf.Property) r2
            if (r3 == 0) goto L_0x0065
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L_0x0041
        L_0x0065:
            return r1
        L_0x0066:
            java.util.Map r5 = r5.getDictionary()
            java.util.Map r6 = r6.getDictionary()
            if (r5 != 0) goto L_0x0072
            if (r6 == 0) goto L_0x007a
        L_0x0072:
            if (r5 == 0) goto L_0x007b
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x007b
        L_0x007a:
            r1 = 1
        L_0x007b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hpsf.Section.equals(java.lang.Object):boolean");
    }

    public void removeProperty(long j) {
        if (this.properties.remove(Long.valueOf(j)) != null) {
            this.sectionBytes.reset();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00e2, code lost:
        r15 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00e7, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        r14.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00eb, code lost:
        throw r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ee, code lost:
        r15 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00f3, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00f4, code lost:
        r14.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00f7, code lost:
        throw r15;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int write(java.io.OutputStream r15) throws org.apache.poi.hpsf.WritingNotSupportedException, java.io.IOException {
        /*
            r14 = this;
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r0 = r14.sectionBytes
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x0014
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r0 = r14.sectionBytes
            r0.writeTo(r15)
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r14 = r14.sectionBytes
            int r14 = r14.size()
            return r14
        L_0x0014:
            int r0 = r14.getCodepage()
            r1 = -1
            if (r0 != r1) goto L_0x0028
            org.apache.logging.log4j.Logger r0 = LOG
            org.apache.logging.log4j.LogBuilder r0 = r0.atWarn()
            java.lang.String r2 = "The codepage property is not set although a dictionary is present. Defaulting to ISO-8859-1."
            r0.log((java.lang.String) r2)
            r0 = 1252(0x4e4, float:1.754E-42)
        L_0x0028:
            java.util.Map<java.lang.Long, org.apache.poi.hpsf.Property> r2 = r14.properties
            int r2 = r2.size()
            r3 = 2
            int[] r4 = new int[r3]
            r5 = 1
            r4[r5] = r3
            r3 = 0
            r4[r3] = r2
            java.lang.Class r2 = java.lang.Integer.TYPE
            java.lang.Object r2 = java.lang.reflect.Array.newInstance(r2, r4)
            int[][] r2 = (int[][]) r2
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r4 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream
            r4.<init>()
            org.apache.poi.util.LittleEndianOutputStream r6 = new org.apache.poi.util.LittleEndianOutputStream     // Catch:{ all -> 0x00ec }
            r6.<init>(r4)     // Catch:{ all -> 0x00ec }
            r6.writeInt(r1)     // Catch:{ all -> 0x00e0 }
            java.util.Map<java.lang.Long, org.apache.poi.hpsf.Property> r7 = r14.properties     // Catch:{ all -> 0x00e0 }
            int r7 = r7.size()     // Catch:{ all -> 0x00e0 }
            r6.writeInt(r7)     // Catch:{ all -> 0x00e0 }
            java.util.Map<java.lang.Long, org.apache.poi.hpsf.Property> r7 = r14.properties     // Catch:{ all -> 0x00e0 }
            java.util.Collection r7 = r7.values()     // Catch:{ all -> 0x00e0 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x00e0 }
            r8 = r3
        L_0x0060:
            boolean r9 = r7.hasNext()     // Catch:{ all -> 0x00e0 }
            if (r9 == 0) goto L_0x0082
            java.lang.Object r9 = r7.next()     // Catch:{ all -> 0x00e0 }
            org.apache.poi.hpsf.Property r9 = (org.apache.poi.hpsf.Property) r9     // Catch:{ all -> 0x00e0 }
            long r9 = r9.getID()     // Catch:{ all -> 0x00e0 }
            r6.writeUInt(r9)     // Catch:{ all -> 0x00e0 }
            int r9 = r8 + 1
            r8 = r2[r8]     // Catch:{ all -> 0x00e0 }
            int r10 = r4.size()     // Catch:{ all -> 0x00e0 }
            r8[r3] = r10     // Catch:{ all -> 0x00e0 }
            r6.writeInt(r1)     // Catch:{ all -> 0x00e0 }
            r8 = r9
            goto L_0x0060
        L_0x0082:
            java.util.Map<java.lang.Long, org.apache.poi.hpsf.Property> r1 = r14.properties     // Catch:{ all -> 0x00e0 }
            java.util.Collection r1 = r1.values()     // Catch:{ all -> 0x00e0 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x00e0 }
            r7 = r3
        L_0x008d:
            boolean r8 = r1.hasNext()     // Catch:{ all -> 0x00e0 }
            if (r8 == 0) goto L_0x00b6
            java.lang.Object r8 = r1.next()     // Catch:{ all -> 0x00e0 }
            org.apache.poi.hpsf.Property r8 = (org.apache.poi.hpsf.Property) r8     // Catch:{ all -> 0x00e0 }
            int r9 = r7 + 1
            r7 = r2[r7]     // Catch:{ all -> 0x00e0 }
            int r10 = r4.size()     // Catch:{ all -> 0x00e0 }
            r7[r5] = r10     // Catch:{ all -> 0x00e0 }
            long r10 = r8.getID()     // Catch:{ all -> 0x00e0 }
            r12 = 0
            int r7 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r7 == 0) goto L_0x00b1
            r8.write(r4, r0)     // Catch:{ all -> 0x00e0 }
            goto L_0x00b4
        L_0x00b1:
            r14.writeDictionary(r4, r0)     // Catch:{ all -> 0x00e0 }
        L_0x00b4:
            r7 = r9
            goto L_0x008d
        L_0x00b6:
            byte[] r14 = r4.toByteArray()     // Catch:{ all -> 0x00e0 }
            int r0 = r4.size()     // Catch:{ all -> 0x00e0 }
            org.apache.poi.util.LittleEndian.putInt(r14, r3, r0)     // Catch:{ all -> 0x00e0 }
            int r0 = r2.length     // Catch:{ all -> 0x00e0 }
            r1 = r3
        L_0x00c3:
            if (r1 >= r0) goto L_0x00d2
            r7 = r2[r1]     // Catch:{ all -> 0x00e0 }
            r8 = r7[r3]     // Catch:{ all -> 0x00e0 }
            r7 = r7[r5]     // Catch:{ all -> 0x00e0 }
            long r9 = (long) r7     // Catch:{ all -> 0x00e0 }
            org.apache.poi.util.LittleEndian.putUInt(r14, r8, r9)     // Catch:{ all -> 0x00e0 }
            int r1 = r1 + 1
            goto L_0x00c3
        L_0x00d2:
            r15.write(r14)     // Catch:{ all -> 0x00e0 }
            int r14 = r4.size()     // Catch:{ all -> 0x00e0 }
            r6.close()     // Catch:{ all -> 0x00ec }
            r4.close()
            return r14
        L_0x00e0:
            r14 = move-exception
            throw r14     // Catch:{ all -> 0x00e2 }
        L_0x00e2:
            r15 = move-exception
            r6.close()     // Catch:{ all -> 0x00e7 }
            goto L_0x00eb
        L_0x00e7:
            r0 = move-exception
            r14.addSuppressed(r0)     // Catch:{ all -> 0x00ec }
        L_0x00eb:
            throw r15     // Catch:{ all -> 0x00ec }
        L_0x00ec:
            r14 = move-exception
            throw r14     // Catch:{ all -> 0x00ee }
        L_0x00ee:
            r15 = move-exception
            r4.close()     // Catch:{ all -> 0x00f3 }
            goto L_0x00f7
        L_0x00f3:
            r0 = move-exception
            r14.addSuppressed(r0)
        L_0x00f7:
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hpsf.Section.write(java.io.OutputStream):int");
    }

    private boolean readDictionary(LittleEndianByteArrayInputStream littleEndianByteArrayInputStream, int i, int i2) {
        LittleEndianByteArrayInputStream littleEndianByteArrayInputStream2 = littleEndianByteArrayInputStream;
        HashMap hashMap = new HashMap();
        long readUInt = littleEndianByteArrayInputStream.readUInt();
        long j = -1;
        boolean z = false;
        int i3 = 0;
        while (true) {
            if (((long) i3) >= readUInt) {
                break;
            }
            String str = "The property set's dictionary contains bogus data. All dictionary entries starting with the one with ID " + j + " will be ignored.";
            long readUInt2 = littleEndianByteArrayInputStream.readUInt();
            long readUInt3 = littleEndianByteArrayInputStream.readUInt();
            int i4 = i2;
            int i5 = i4 == -1 ? 1252 : i4;
            int intExact = Math.toIntExact((readUInt3 - 1) * ((long) (i5 == 1200 ? 2 : 1)));
            if (intExact > 16777215) {
                LOG.atWarn().log(str);
                break;
            }
            try {
                byte[] safelyAllocate = IOUtils.safelyAllocate((long) intExact, CodePageString.getMaxRecordLength());
                littleEndianByteArrayInputStream2.readFully(safelyAllocate, 0, intExact);
                String stringFromCodePage = CodePageUtil.getStringFromCodePage(safelyAllocate, 0, intExact, i5);
                IOUtils.skipFully(littleEndianByteArrayInputStream2, (long) (i5 == 1200 ? ((4 - ((intExact + 2) & 3)) & 3) + 2 : 1));
                hashMap.put(Long.valueOf(readUInt2), stringFromCodePage);
                i3++;
                j = readUInt2;
            } catch (IOException | RuntimeException e) {
                LOG.atWarn().withThrowable(e).log(str);
                z = true;
                setDictionary(hashMap);
                return !z;
            }
        }
        setDictionary(hashMap);
        return !z;
    }

    private void writeDictionary(OutputStream outputStream, int i) throws IOException {
        byte[] bArr = new byte[4];
        Map<Long, String> dictionary2 = getDictionary();
        LittleEndian.putUInt((long) dictionary2.size(), outputStream);
        int i2 = 4;
        for (Map.Entry next : dictionary2.entrySet()) {
            LittleEndian.putUInt(((Long) next.getKey()).longValue(), outputStream);
            int i3 = i2 + 4;
            String str = ((String) next.getValue()) + "\u0000";
            byte[] bytesInCodePage = CodePageUtil.getBytesInCodePage(str, i);
            LittleEndian.putUInt((long) (i == 1200 ? str.length() : bytesInCodePage.length), outputStream);
            outputStream.write(bytesInCodePage);
            int length = i3 + 4 + bytesInCodePage.length;
            int i4 = i == 1200 ? (4 - (length & 3)) & 3 : 0;
            outputStream.write(bArr, 0, i4);
            i2 = length + i4;
        }
        outputStream.write(bArr, 0, (4 - (i2 & 3)) & 3);
    }

    public void setDictionary(Map<Long, String> map) throws IllegalPropertySetDataException {
        if (map != null) {
            if (this.dictionary == null) {
                this.dictionary = new TreeMap();
            }
            this.dictionary.putAll(map);
            if (getCodepage() == -1) {
                setCodepage(1252);
            }
            setProperty(0, -1, map);
            return;
        }
        removeProperty(0);
        this.dictionary = null;
    }

    public int hashCode() {
        return Arrays.deepHashCode(new Object[]{getFormatID(), getProperties()});
    }

    public String toString() {
        return toString((PropertyIDMap) null);
    }

    public String toString(PropertyIDMap propertyIDMap) {
        StringBuilder sb = new StringBuilder("\n\n\n");
        Property[] properties2 = getProperties();
        sb.append(getClass().getName());
        sb.append("[formatID: ");
        sb.append(getFormatID());
        sb.append(", offset: ");
        sb.append(getOffset());
        sb.append(", propertyCount: ");
        sb.append(getPropertyCount());
        sb.append(", size: ");
        sb.append(getSize());
        sb.append(", properties: [\n");
        int codepage = getCodepage();
        if (codepage == -1) {
            codepage = 1252;
        }
        for (Property property : properties2) {
            sb.append(property.toString(codepage, propertyIDMap));
            sb.append(",\n");
        }
        sb.append("]]");
        return sb.toString();
    }

    public Map<Long, String> getDictionary() {
        if (this.dictionary == null) {
            this.dictionary = (Map) getProperty(0);
        }
        return this.dictionary;
    }

    public int getCodepage() {
        Integer num = (Integer) getProperty(1);
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    public void setCodepage(int i) {
        setProperty(1, 2, Integer.valueOf(i));
    }
}
