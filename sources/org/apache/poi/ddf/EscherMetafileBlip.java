package org.apache.poi.ddf;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.Removal;

public final class EscherMetafileBlip extends EscherBlipRecord {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 100000000;
    private static final int HEADER_SIZE = 8;
    private static final Logger LOGGER = LogManager.getLogger((Class<?>) EscherMetafileBlip.class);
    private static int MAX_RECORD_LENGTH = DEFAULT_MAX_RECORD_LENGTH;
    @Deprecated
    @Removal(version = "5.3")
    public static final short RECORD_ID_EMF = EscherRecordTypes.BLIP_EMF.typeID;
    @Deprecated
    @Removal(version = "5.3")
    public static final short RECORD_ID_PICT = EscherRecordTypes.BLIP_PICT.typeID;
    @Deprecated
    @Removal(version = "5.3")
    public static final short RECORD_ID_WMF = EscherRecordTypes.BLIP_WMF.typeID;
    private final byte[] field_1_UID;
    private final byte[] field_2_UID;
    private int field_2_cb;
    private int field_3_rcBounds_x1;
    private int field_3_rcBounds_x2;
    private int field_3_rcBounds_y1;
    private int field_3_rcBounds_y2;
    private int field_4_ptSize_h;
    private int field_4_ptSize_w;
    private int field_5_cbSave;
    private byte field_6_fCompression;
    private byte field_7_fFilter;
    private byte[] raw_pictureData;
    private byte[] remainingData;

    public static void setMaxRecordLength(int i) {
        MAX_RECORD_LENGTH = i;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public EscherMetafileBlip() {
        this.field_1_UID = new byte[16];
        this.field_2_UID = new byte[16];
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: byte[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public EscherMetafileBlip(org.apache.poi.ddf.EscherMetafileBlip r6) {
        /*
            r5 = this;
            r5.<init>(r6)
            r0 = 16
            byte[] r1 = new byte[r0]
            r5.field_1_UID = r1
            byte[] r0 = new byte[r0]
            r5.field_2_UID = r0
            byte[] r2 = r6.field_1_UID
            int r3 = r1.length
            r4 = 0
            java.lang.System.arraycopy(r2, r4, r1, r4, r3)
            byte[] r1 = r6.field_2_UID
            int r2 = r0.length
            java.lang.System.arraycopy(r1, r4, r0, r4, r2)
            int r0 = r6.field_2_cb
            r5.field_2_cb = r0
            int r0 = r6.field_3_rcBounds_x1
            r5.field_3_rcBounds_x1 = r0
            int r0 = r6.field_3_rcBounds_y1
            r5.field_3_rcBounds_y1 = r0
            int r0 = r6.field_3_rcBounds_x2
            r5.field_3_rcBounds_x2 = r0
            int r0 = r6.field_3_rcBounds_y2
            r5.field_3_rcBounds_y2 = r0
            int r0 = r6.field_4_ptSize_h
            r5.field_4_ptSize_h = r0
            int r0 = r6.field_4_ptSize_w
            r5.field_4_ptSize_w = r0
            int r0 = r6.field_5_cbSave
            r5.field_5_cbSave = r0
            byte r0 = r6.field_6_fCompression
            r5.field_6_fCompression = r0
            byte r0 = r6.field_7_fFilter
            r5.field_7_fFilter = r0
            byte[] r0 = r6.raw_pictureData
            r1 = 0
            if (r0 != 0) goto L_0x0049
            r0 = r1
            goto L_0x004f
        L_0x0049:
            java.lang.Object r0 = r0.clone()
            byte[] r0 = (byte[]) r0
        L_0x004f:
            r5.raw_pictureData = r0
            byte[] r6 = r6.remainingData
            if (r6 != 0) goto L_0x0056
            goto L_0x005d
        L_0x0056:
            java.lang.Object r6 = r6.clone()
            r1 = r6
            byte[] r1 = (byte[]) r1
        L_0x005d:
            r5.remainingData = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ddf.EscherMetafileBlip.<init>(org.apache.poi.ddf.EscherMetafileBlip):void");
    }

    public int fillFields(byte[] bArr, int i, EscherRecordFactory escherRecordFactory) {
        int readHeader = readHeader(bArr, i);
        int i2 = i + 8;
        System.arraycopy(bArr, i2, this.field_1_UID, 0, 16);
        int i3 = i2 + 16;
        if ((getOptions() ^ getSignature()) == 16) {
            System.arraycopy(bArr, i3, this.field_2_UID, 0, 16);
            i3 += 16;
        }
        this.field_2_cb = LittleEndian.getInt(bArr, i3);
        int i4 = i3 + 4;
        this.field_3_rcBounds_x1 = LittleEndian.getInt(bArr, i4);
        int i5 = i4 + 4;
        this.field_3_rcBounds_y1 = LittleEndian.getInt(bArr, i5);
        int i6 = i5 + 4;
        this.field_3_rcBounds_x2 = LittleEndian.getInt(bArr, i6);
        int i7 = i6 + 4;
        this.field_3_rcBounds_y2 = LittleEndian.getInt(bArr, i7);
        int i8 = i7 + 4;
        this.field_4_ptSize_w = LittleEndian.getInt(bArr, i8);
        int i9 = i8 + 4;
        this.field_4_ptSize_h = LittleEndian.getInt(bArr, i9);
        int i10 = i9 + 4;
        int i11 = LittleEndian.getInt(bArr, i10);
        this.field_5_cbSave = i11;
        int i12 = i10 + 4;
        this.field_6_fCompression = bArr[i12];
        int i13 = i12 + 1;
        this.field_7_fFilter = bArr[i13];
        int i14 = i13 + 1;
        byte[] safelyClone = IOUtils.safelyClone(bArr, i14, i11, MAX_RECORD_LENGTH);
        this.raw_pictureData = safelyClone;
        int i15 = i14 + this.field_5_cbSave;
        if (this.field_6_fCompression == 0) {
            super.setPictureData(inflatePictureData(safelyClone));
        } else {
            super.setPictureData(safelyClone);
        }
        int i16 = (readHeader - i15) + i + 8;
        if (i16 > 0) {
            this.remainingData = IOUtils.safelyClone(bArr, i15, i16, MAX_RECORD_LENGTH);
        }
        return readHeader + 8;
    }

    public int serialize(int i, byte[] bArr, EscherSerializationListener escherSerializationListener) {
        escherSerializationListener.beforeRecordSerialize(i, getRecordId(), this);
        LittleEndian.putShort(bArr, i, getOptions());
        int i2 = i + 2;
        LittleEndian.putShort(bArr, i2, getRecordId());
        int i3 = i2 + 2;
        LittleEndian.putInt(bArr, i3, getRecordSize() - 8);
        int i4 = i3 + 4;
        byte[] bArr2 = this.field_1_UID;
        System.arraycopy(bArr2, 0, bArr, i4, bArr2.length);
        int length = i4 + this.field_1_UID.length;
        if ((getOptions() ^ getSignature()) == 16) {
            byte[] bArr3 = this.field_2_UID;
            System.arraycopy(bArr3, 0, bArr, length, bArr3.length);
            length += this.field_2_UID.length;
        }
        LittleEndian.putInt(bArr, length, this.field_2_cb);
        int i5 = length + 4;
        LittleEndian.putInt(bArr, i5, this.field_3_rcBounds_x1);
        int i6 = i5 + 4;
        LittleEndian.putInt(bArr, i6, this.field_3_rcBounds_y1);
        int i7 = i6 + 4;
        LittleEndian.putInt(bArr, i7, this.field_3_rcBounds_x2);
        int i8 = i7 + 4;
        LittleEndian.putInt(bArr, i8, this.field_3_rcBounds_y2);
        int i9 = i8 + 4;
        LittleEndian.putInt(bArr, i9, this.field_4_ptSize_w);
        int i10 = i9 + 4;
        LittleEndian.putInt(bArr, i10, this.field_4_ptSize_h);
        int i11 = i10 + 4;
        LittleEndian.putInt(bArr, i11, this.field_5_cbSave);
        int i12 = i11 + 4;
        bArr[i12] = this.field_6_fCompression;
        int i13 = i12 + 1;
        bArr[i13] = this.field_7_fFilter;
        int i14 = i13 + 1;
        byte[] bArr4 = this.raw_pictureData;
        System.arraycopy(bArr4, 0, bArr, i14, bArr4.length);
        int length2 = i14 + this.raw_pictureData.length;
        byte[] bArr5 = this.remainingData;
        if (bArr5 != null) {
            System.arraycopy(bArr5, 0, bArr, length2, bArr5.length);
        }
        escherSerializationListener.afterRecordSerialize(i + getRecordSize(), getRecordId(), getRecordSize(), this);
        return getRecordSize();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001f, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0024, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r2.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0028, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x002b, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0034, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] inflatePictureData(byte[] r4) {
        /*
            java.util.zip.InflaterInputStream r0 = new java.util.zip.InflaterInputStream     // Catch:{ IOException -> 0x0035 }
            org.apache.commons.io.input.UnsynchronizedByteArrayInputStream r1 = new org.apache.commons.io.input.UnsynchronizedByteArrayInputStream     // Catch:{ IOException -> 0x0035 }
            r1.<init>(r4)     // Catch:{ IOException -> 0x0035 }
            r0.<init>(r1)     // Catch:{ IOException -> 0x0035 }
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r1 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream     // Catch:{ all -> 0x0029 }
            r1.<init>()     // Catch:{ all -> 0x0029 }
            org.apache.poi.util.IOUtils.copy((java.io.InputStream) r0, (java.io.OutputStream) r1)     // Catch:{ all -> 0x001d }
            byte[] r2 = r1.toByteArray()     // Catch:{ all -> 0x001d }
            r1.close()     // Catch:{ all -> 0x0029 }
            r0.close()     // Catch:{ IOException -> 0x0035 }
            return r2
        L_0x001d:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x001f }
        L_0x001f:
            r3 = move-exception
            r1.close()     // Catch:{ all -> 0x0024 }
            goto L_0x0028
        L_0x0024:
            r1 = move-exception
            r2.addSuppressed(r1)     // Catch:{ all -> 0x0029 }
        L_0x0028:
            throw r3     // Catch:{ all -> 0x0029 }
        L_0x0029:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x002b }
        L_0x002b:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x0030 }
            goto L_0x0034
        L_0x0030:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ IOException -> 0x0035 }
        L_0x0034:
            throw r2     // Catch:{ IOException -> 0x0035 }
        L_0x0035:
            r0 = move-exception
            org.apache.logging.log4j.Logger r1 = LOGGER
            org.apache.logging.log4j.LogBuilder r1 = r1.atWarn()
            org.apache.logging.log4j.LogBuilder r0 = r1.withThrowable(r0)
            java.lang.String r1 = "Possibly corrupt compression or non-compressed data"
            r0.log((java.lang.String) r1)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ddf.EscherMetafileBlip.inflatePictureData(byte[]):byte[]");
    }

    public int getRecordSize() {
        int length = this.raw_pictureData.length + 58;
        byte[] bArr = this.remainingData;
        if (bArr != null) {
            length += bArr.length;
        }
        return (getOptions() ^ getSignature()) == 16 ? length + this.field_2_UID.length : length;
    }

    public byte[] getUID() {
        return this.field_1_UID;
    }

    public void setUID(byte[] bArr) {
        if (bArr == null || bArr.length != 16) {
            throw new IllegalArgumentException("uid must be byte[16]");
        }
        byte[] bArr2 = this.field_1_UID;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
    }

    public byte[] getPrimaryUID() {
        return this.field_2_UID;
    }

    public void setPrimaryUID(byte[] bArr) {
        if (bArr == null || bArr.length != 16) {
            throw new IllegalArgumentException("primaryUID must be byte[16]");
        }
        byte[] bArr2 = this.field_2_UID;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
    }

    public int getUncompressedSize() {
        return this.field_2_cb;
    }

    public void setUncompressedSize(int i) {
        this.field_2_cb = i;
    }

    public Rectangle getBounds() {
        int i = this.field_3_rcBounds_x1;
        int i2 = this.field_3_rcBounds_y1;
        return new Rectangle(i, i2, this.field_3_rcBounds_x2 - i, this.field_3_rcBounds_y2 - i2);
    }

    public void setBounds(Rectangle rectangle) {
        this.field_3_rcBounds_x1 = rectangle.x;
        this.field_3_rcBounds_y1 = rectangle.y;
        this.field_3_rcBounds_x2 = rectangle.x + rectangle.width;
        this.field_3_rcBounds_y2 = rectangle.y + rectangle.height;
    }

    public Dimension getSizeEMU() {
        return new Dimension(this.field_4_ptSize_w, this.field_4_ptSize_h);
    }

    public void setSizeEMU(Dimension dimension) {
        this.field_4_ptSize_w = dimension.width;
        this.field_4_ptSize_h = dimension.height;
    }

    public int getCompressedSize() {
        return this.field_5_cbSave;
    }

    public void setCompressedSize(int i) {
        this.field_5_cbSave = i;
    }

    public boolean isCompressed() {
        return this.field_6_fCompression == 0;
    }

    public void setCompressed(boolean z) {
        this.field_6_fCompression = z ? (byte) 0 : -2;
    }

    public byte getFilter() {
        return this.field_7_fFilter;
    }

    public void setFilter(byte b) {
        this.field_7_fFilter = b;
    }

    public byte[] getRemainingData() {
        return this.remainingData;
    }

    /* renamed from: org.apache.poi.ddf.EscherMetafileBlip$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ddf$EscherRecordTypes;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.apache.poi.ddf.EscherRecordTypes[] r0 = org.apache.poi.ddf.EscherRecordTypes.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ddf$EscherRecordTypes = r0
                org.apache.poi.ddf.EscherRecordTypes r1 = org.apache.poi.ddf.EscherRecordTypes.BLIP_EMF     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ddf$EscherRecordTypes     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ddf.EscherRecordTypes r1 = org.apache.poi.ddf.EscherRecordTypes.BLIP_WMF     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$ddf$EscherRecordTypes     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ddf.EscherRecordTypes r1 = org.apache.poi.ddf.EscherRecordTypes.BLIP_PICT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ddf.EscherMetafileBlip.AnonymousClass1.<clinit>():void");
        }
    }

    public short getSignature() {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ddf$EscherRecordTypes[EscherRecordTypes.forTypeID(getRecordId()).ordinal()];
        if (i == 1) {
            return HSSFPictureData.MSOBI_EMF;
        }
        if (i == 2) {
            return HSSFPictureData.MSOBI_WMF;
        }
        if (i == 3) {
            return HSSFPictureData.MSOBI_PICT;
        }
        LOGGER.atWarn().log("Unknown metafile: {}", (Object) Unbox.box(getRecordId()));
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002d, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0032, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r2.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0036, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0039, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0042, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setPictureData(byte[] r3) {
        /*
            r2 = this;
            super.setPictureData(r3)
            int r0 = r3.length
            r2.setUncompressedSize(r0)
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r0 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream     // Catch:{ IOException -> 0x0043 }
            r0.<init>()     // Catch:{ IOException -> 0x0043 }
            java.util.zip.DeflaterOutputStream r1 = new java.util.zip.DeflaterOutputStream     // Catch:{ all -> 0x0037 }
            r1.<init>(r0)     // Catch:{ all -> 0x0037 }
            r1.write(r3)     // Catch:{ all -> 0x002b }
            r1.close()     // Catch:{ all -> 0x0037 }
            byte[] r3 = r0.toByteArray()     // Catch:{ all -> 0x0037 }
            r2.raw_pictureData = r3     // Catch:{ all -> 0x0037 }
            r0.close()     // Catch:{ IOException -> 0x0043 }
            byte[] r3 = r2.raw_pictureData
            int r3 = r3.length
            r2.setCompressedSize(r3)
            r3 = 1
            r2.setCompressed(r3)
            return
        L_0x002b:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x002d }
        L_0x002d:
            r3 = move-exception
            r1.close()     // Catch:{ all -> 0x0032 }
            goto L_0x0036
        L_0x0032:
            r1 = move-exception
            r2.addSuppressed(r1)     // Catch:{ all -> 0x0037 }
        L_0x0036:
            throw r3     // Catch:{ all -> 0x0037 }
        L_0x0037:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0039 }
        L_0x0039:
            r3 = move-exception
            r0.close()     // Catch:{ all -> 0x003e }
            goto L_0x0042
        L_0x003e:
            r0 = move-exception
            r2.addSuppressed(r0)     // Catch:{ IOException -> 0x0043 }
        L_0x0042:
            throw r3     // Catch:{ IOException -> 0x0043 }
        L_0x0043:
            r2 = move-exception
            java.lang.RuntimeException r3 = new java.lang.RuntimeException
            java.lang.String r0 = "Can't compress metafile picture data"
            r3.<init>(r0, r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ddf.EscherMetafileBlip.setPictureData(byte[]):void");
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(super.getGenericProperties());
        linkedHashMap.put("uid", new EscherMetafileBlip$$ExternalSyntheticLambda0(this));
        linkedHashMap.put("uncompressedSize", new EscherMetafileBlip$$ExternalSyntheticLambda1(this));
        linkedHashMap.put("bounds", new EscherMetafileBlip$$ExternalSyntheticLambda2(this));
        linkedHashMap.put("sizeInEMU", new EscherMetafileBlip$$ExternalSyntheticLambda3(this));
        linkedHashMap.put("compressedSize", new EscherMetafileBlip$$ExternalSyntheticLambda4(this));
        linkedHashMap.put("isCompressed", new EscherMetafileBlip$$ExternalSyntheticLambda5(this));
        linkedHashMap.put("filter", new EscherMetafileBlip$$ExternalSyntheticLambda6(this));
        return Collections.unmodifiableMap(linkedHashMap);
    }

    public EscherMetafileBlip copy() {
        return new EscherMetafileBlip(this);
    }
}
