package org.apache.poi.poifs.storage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.apache.poi.hssf.OldExcelFormatException;
import org.apache.poi.poifs.common.POIFSBigBlockSize;
import org.apache.poi.poifs.common.POIFSConstants;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.NotOLE2FileException;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.IntegerField;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LongField;
import org.apache.poi.util.ShortField;

public final class HeaderBlock implements HeaderBlockConstants {
    private static final byte _default_value = -1;
    private int _bat_count;
    private final byte[] _data;
    private int _property_start;
    private int _sbat_count;
    private int _sbat_start;
    private int _xbat_count;
    private int _xbat_start;
    private final POIFSBigBlockSize bigBlockSize;

    public HeaderBlock(InputStream inputStream) throws IOException {
        this(readFirst512(inputStream));
        if (this.bigBlockSize.getBigBlockSize() != 512) {
            IOUtils.readFully(inputStream, IOUtils.safelyAllocate((long) (this.bigBlockSize.getBigBlockSize() - 512), POIFSFileSystem.getMaxRecordLength()));
        }
    }

    public HeaderBlock(ByteBuffer byteBuffer) throws IOException {
        this(IOUtils.toByteArray(byteBuffer, 512));
    }

    private HeaderBlock(byte[] bArr) throws IOException {
        byte[] bArr2 = (byte[]) bArr.clone();
        this._data = bArr2;
        FileMagic valueOf = FileMagic.valueOf(bArr);
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$poifs$filesystem$FileMagic[valueOf.ordinal()]) {
            case 1:
                byte b = bArr2[30];
                if (b == 12) {
                    this.bigBlockSize = POIFSConstants.LARGER_BIG_BLOCK_SIZE_DETAILS;
                } else if (b == 9) {
                    this.bigBlockSize = POIFSConstants.SMALLER_BIG_BLOCK_SIZE_DETAILS;
                } else {
                    throw new IOException("Unsupported blocksize  (2^" + bArr2[30] + "). Expected 2^9 or 2^12.");
                }
                this._bat_count = new IntegerField(44, bArr).get();
                this._property_start = new IntegerField(48, bArr2).get();
                this._sbat_start = new IntegerField(60, bArr2).get();
                this._sbat_count = new IntegerField(64, bArr2).get();
                this._xbat_start = new IntegerField(68, bArr2).get();
                this._xbat_count = new IntegerField(72, bArr2).get();
                return;
            case 2:
                throw new OfficeXmlFileException("The supplied data appears to be in the Office 2007+ XML. You are calling the part of POI that deals with OLE2 Office Documents. You need to call a different part of POI to process this data (eg XSSF instead of HSSF)");
            case 3:
                throw new NotOLE2FileException("The supplied data appears to be a raw XML file. Formats such as Office 2003 XML are not supported");
            case 4:
                throw new NotOLE2FileException("The supplied data appears to be in the old MS Write format. Apache POI doesn't currently support this format");
            case 5:
                throw new NotOLE2FileException("The supplied data appears to be an old Word version 2 file. Apache POI doesn't currently support this format");
            case 6:
            case 7:
            case 8:
                throw new OldExcelFormatException("The supplied data appears to be in " + valueOf + " format. HSSF only supports the BIFF8 format, try OldExcelExtractor");
            default:
                throw new NotOLE2FileException("Invalid header signature; read " + HexDump.longToHex(LittleEndian.getLong(bArr, 0)) + ", expected " + HexDump.longToHex(HeaderBlockConstants._signature) + " - Your file appears not to be a valid OLE2 document");
        }
    }

    /* renamed from: org.apache.poi.poifs.storage.HeaderBlock$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$poifs$filesystem$FileMagic;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.poifs.filesystem.FileMagic[] r0 = org.apache.poi.poifs.filesystem.FileMagic.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$poifs$filesystem$FileMagic = r0
                org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.OLE2     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$filesystem$FileMagic     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.OOXML     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$filesystem$FileMagic     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.XML     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$filesystem$FileMagic     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.MSWRITE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$filesystem$FileMagic     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.WORD2     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$filesystem$FileMagic     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.BIFF2     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$filesystem$FileMagic     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.BIFF3     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$filesystem$FileMagic     // Catch:{ NoSuchFieldError -> 0x0060 }
                org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.BIFF4     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.storage.HeaderBlock.AnonymousClass1.<clinit>():void");
        }
    }

    public HeaderBlock(POIFSBigBlockSize pOIFSBigBlockSize) {
        this.bigBlockSize = pOIFSBigBlockSize;
        byte[] bArr = new byte[512];
        this._data = bArr;
        Arrays.fill(bArr, (byte) -1);
        new LongField(0, HeaderBlockConstants._signature, bArr);
        new IntegerField(8, 0, bArr);
        new IntegerField(12, 0, bArr);
        new IntegerField(16, 0, bArr);
        new IntegerField(20, 0, bArr);
        new ShortField(24, 59, bArr);
        new ShortField(26, 3, bArr);
        new ShortField(28, -2, bArr);
        new ShortField(30, pOIFSBigBlockSize.getHeaderValue(), bArr);
        new IntegerField(32, 6, bArr);
        new IntegerField(36, 0, bArr);
        new IntegerField(40, 0, bArr);
        new IntegerField(52, 0, bArr);
        new IntegerField(56, 4096, bArr);
        this._bat_count = 0;
        this._sbat_count = 0;
        this._xbat_count = 0;
        this._property_start = -2;
        this._sbat_start = -2;
        this._xbat_start = -2;
    }

    private static byte[] readFirst512(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[512];
        int readFully = IOUtils.readFully(inputStream, bArr);
        if (readFully == 512) {
            return bArr;
        }
        throw alertShortRead(readFully);
    }

    private static IOException alertShortRead(int i) {
        if (i < 0) {
            i = 0;
        }
        return new IOException("Unable to read entire header; " + i + " byte".concat(i == 1 ? "" : "s") + " read; expected 512 bytes");
    }

    public int getPropertyStart() {
        return this._property_start;
    }

    public void setPropertyStart(int i) {
        this._property_start = i;
    }

    public int getSBATStart() {
        return this._sbat_start;
    }

    public int getSBATCount() {
        return this._sbat_count;
    }

    public void setSBATStart(int i) {
        this._sbat_start = i;
    }

    public void setSBATBlockCount(int i) {
        this._sbat_count = i;
    }

    public int getBATCount() {
        return this._bat_count;
    }

    public void setBATCount(int i) {
        this._bat_count = i;
    }

    public int[] getBATArray() {
        int min = Math.min(this._bat_count, 109);
        int[] iArr = new int[min];
        int i = 76;
        for (int i2 = 0; i2 < min; i2++) {
            iArr[i2] = LittleEndian.getInt(this._data, i);
            i += 4;
        }
        return iArr;
    }

    public void setBATArray(int[] iArr) {
        int min = Math.min(iArr.length, 109);
        int i = 109 - min;
        int i2 = 76;
        for (int i3 = 0; i3 < min; i3++) {
            LittleEndian.putInt(this._data, i2, iArr[i3]);
            i2 += 4;
        }
        for (int i4 = 0; i4 < i; i4++) {
            LittleEndian.putInt(this._data, i2, -1);
            i2 += 4;
        }
    }

    public int getXBATCount() {
        return this._xbat_count;
    }

    public void setXBATCount(int i) {
        this._xbat_count = i;
    }

    public int getXBATIndex() {
        return this._xbat_start;
    }

    public void setXBATStart(int i) {
        this._xbat_start = i;
    }

    public POIFSBigBlockSize getBigBlockSize() {
        return this.bigBlockSize;
    }

    public void writeData(OutputStream outputStream) throws IOException {
        new IntegerField(44, this._bat_count, this._data);
        new IntegerField(48, this._property_start, this._data);
        new IntegerField(60, this._sbat_start, this._data);
        new IntegerField(64, this._sbat_count, this._data);
        new IntegerField(68, this._xbat_start, this._data);
        new IntegerField(72, this._xbat_count, this._data);
        outputStream.write(this._data, 0, 512);
        for (int i = 512; i < this.bigBlockSize.getBigBlockSize(); i++) {
            outputStream.write(0);
        }
    }
}
