package org.apache.poi.poifs.filesystem;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.StringUtil;

public class Ole10Native {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 100000000;
    private static final int DEFAULT_MAX_STRING_LENGTH = 1024;
    private static final Charset ISO1 = StandardCharsets.ISO_8859_1;
    private static int MAX_RECORD_LENGTH = DEFAULT_MAX_RECORD_LENGTH;
    private static int MAX_STRING_LENGTH = 1024;
    public static final String OLE10_NATIVE = "\u0001Ole10Native";
    private static final byte[] OLE_MARKER_BYTES = {1, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final String OLE_MARKER_NAME = "\u0001Ole";
    private String command;
    private String command2;
    private byte[] dataBuffer;
    private String fileName;
    private String fileName2;
    private short flags1 = 2;
    private short flags2;
    private String label;
    private String label2;
    private EncodingMode mode;
    private int totalSize;
    private short unknown1 = 3;

    private enum EncodingMode {
        parsed,
        unparsed,
        compact
    }

    public static Ole10Native createFromEmbeddedOleObject(POIFSFileSystem pOIFSFileSystem) throws IOException, Ole10NativeException {
        return createFromEmbeddedOleObject(pOIFSFileSystem.getRoot());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0025, code lost:
        if (r3 != null) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002c, code lost:
        r0.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002f, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        r1 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.apache.poi.poifs.filesystem.Ole10Native createFromEmbeddedOleObject(org.apache.poi.poifs.filesystem.DirectoryNode r3) throws java.io.IOException, org.apache.poi.poifs.filesystem.Ole10NativeException {
        /*
            java.lang.String r0 = "\u0001Ole10Native"
            org.apache.poi.poifs.filesystem.Entry r0 = r3.getEntry(r0)
            org.apache.poi.poifs.filesystem.DocumentEntry r0 = (org.apache.poi.poifs.filesystem.DocumentEntry) r0
            org.apache.poi.poifs.filesystem.DocumentInputStream r3 = r3.createDocumentInputStream((org.apache.poi.poifs.filesystem.Entry) r0)
            int r0 = r0.getSize()     // Catch:{ all -> 0x0022 }
            int r1 = MAX_RECORD_LENGTH     // Catch:{ all -> 0x0022 }
            byte[] r0 = org.apache.poi.util.IOUtils.toByteArray(r3, r0, r1)     // Catch:{ all -> 0x0022 }
            org.apache.poi.poifs.filesystem.Ole10Native r1 = new org.apache.poi.poifs.filesystem.Ole10Native     // Catch:{ all -> 0x0022 }
            r2 = 0
            r1.<init>(r0, r2)     // Catch:{ all -> 0x0022 }
            if (r3 == 0) goto L_0x0021
            r3.close()
        L_0x0021:
            return r1
        L_0x0022:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0024 }
        L_0x0024:
            r1 = move-exception
            if (r3 == 0) goto L_0x002f
            r3.close()     // Catch:{ all -> 0x002b }
            goto L_0x002f
        L_0x002b:
            r3 = move-exception
            r0.addSuppressed(r3)
        L_0x002f:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.filesystem.Ole10Native.createFromEmbeddedOleObject(org.apache.poi.poifs.filesystem.DirectoryNode):org.apache.poi.poifs.filesystem.Ole10Native");
    }

    public static void setMaxRecordLength(int i) {
        MAX_RECORD_LENGTH = i;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public static void setMaxStringLength(int i) {
        MAX_STRING_LENGTH = i;
    }

    public static int getMaxStringLength() {
        return MAX_STRING_LENGTH;
    }

    public Ole10Native(String str, String str2, String str3, byte[] bArr) {
        setLabel(str);
        setFileName(str2);
        setCommand(str3);
        this.command2 = str3;
        setDataBuffer(bArr);
        this.mode = EncodingMode.parsed;
    }

    public Ole10Native(byte[] bArr, int i) throws Ole10NativeException {
        LittleEndianByteArrayInputStream littleEndianByteArrayInputStream = new LittleEndianByteArrayInputStream(bArr, i);
        int readInt = littleEndianByteArrayInputStream.readInt();
        this.totalSize = readInt;
        littleEndianByteArrayInputStream.limit(readInt + 4);
        boolean z = false;
        littleEndianByteArrayInputStream.mark(0);
        try {
            short readShort = littleEndianByteArrayInputStream.readShort();
            this.flags1 = readShort;
            if (readShort == 2) {
                littleEndianByteArrayInputStream.mark(0);
                z = !Character.isISOControl(littleEndianByteArrayInputStream.readByte()) ? true : z;
                littleEndianByteArrayInputStream.reset();
                if (z) {
                    readParsed(littleEndianByteArrayInputStream);
                } else {
                    readCompact(littleEndianByteArrayInputStream);
                }
            } else {
                littleEndianByteArrayInputStream.reset();
                readUnparsed(littleEndianByteArrayInputStream);
            }
        } catch (IOException e) {
            throw new Ole10NativeException("Invalid Ole10Native", e);
        }
    }

    private void readParsed(LittleEndianByteArrayInputStream littleEndianByteArrayInputStream) throws Ole10NativeException, IOException {
        this.mode = EncodingMode.parsed;
        this.label = readAsciiZ(littleEndianByteArrayInputStream);
        this.fileName = readAsciiZ(littleEndianByteArrayInputStream);
        this.flags2 = littleEndianByteArrayInputStream.readShort();
        this.unknown1 = littleEndianByteArrayInputStream.readShort();
        this.command = readAsciiLen(littleEndianByteArrayInputStream);
        this.dataBuffer = IOUtils.toByteArray(littleEndianByteArrayInputStream, littleEndianByteArrayInputStream.readInt(), MAX_RECORD_LENGTH);
        littleEndianByteArrayInputStream.mark(0);
        if (littleEndianByteArrayInputStream.readShort() != 0) {
            littleEndianByteArrayInputStream.reset();
            this.command2 = readUtf16(littleEndianByteArrayInputStream);
            this.label2 = readUtf16(littleEndianByteArrayInputStream);
            this.fileName2 = readUtf16(littleEndianByteArrayInputStream);
        }
    }

    private void readCompact(LittleEndianByteArrayInputStream littleEndianByteArrayInputStream) throws IOException {
        this.mode = EncodingMode.compact;
        this.dataBuffer = IOUtils.toByteArray(littleEndianByteArrayInputStream, this.totalSize - 2, MAX_RECORD_LENGTH);
    }

    private void readUnparsed(LittleEndianByteArrayInputStream littleEndianByteArrayInputStream) throws IOException {
        this.mode = EncodingMode.unparsed;
        this.dataBuffer = IOUtils.toByteArray(littleEndianByteArrayInputStream, this.totalSize, MAX_RECORD_LENGTH);
    }

    public static void createOleMarkerEntry(DirectoryEntry directoryEntry) throws IOException {
        if (!directoryEntry.hasEntry(OLE_MARKER_NAME)) {
            directoryEntry.createDocument(OLE_MARKER_NAME, new UnsynchronizedByteArrayInputStream(OLE_MARKER_BYTES));
        }
    }

    public static void createOleMarkerEntry(POIFSFileSystem pOIFSFileSystem) throws IOException {
        createOleMarkerEntry((DirectoryEntry) pOIFSFileSystem.getRoot());
    }

    private static String readAsciiZ(LittleEndianInput littleEndianInput) throws Ole10NativeException {
        int i = MAX_STRING_LENGTH;
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            byte readByte = littleEndianInput.readByte();
            bArr[i2] = readByte;
            if (readByte == 0) {
                return StringUtil.getFromCompressedUnicode(bArr, 0, i2);
            }
        }
        throw new Ole10NativeException("AsciiZ string was not null terminated after " + MAX_STRING_LENGTH + " bytes - Exiting.");
    }

    private static String readAsciiLen(LittleEndianByteArrayInputStream littleEndianByteArrayInputStream) throws IOException {
        int readInt = littleEndianByteArrayInputStream.readInt();
        byte[] byteArray = IOUtils.toByteArray(littleEndianByteArrayInputStream, readInt, MAX_STRING_LENGTH);
        return byteArray.length == 0 ? "" : StringUtil.getFromCompressedUnicode(byteArray, 0, readInt - 1);
    }

    private static String readUtf16(LittleEndianByteArrayInputStream littleEndianByteArrayInputStream) throws IOException {
        int readInt = littleEndianByteArrayInputStream.readInt();
        return StringUtil.getFromUnicodeLE(IOUtils.toByteArray(littleEndianByteArrayInputStream, readInt * 2, MAX_STRING_LENGTH), 0, readInt);
    }

    public int getTotalSize() {
        return this.totalSize;
    }

    public short getFlags1() {
        return this.flags1;
    }

    public String getLabel() {
        return this.label;
    }

    public String getFileName() {
        return this.fileName;
    }

    public short getFlags2() {
        return this.flags2;
    }

    public short getUnknown1() {
        return this.unknown1;
    }

    public String getCommand() {
        return this.command;
    }

    public int getDataSize() {
        return this.dataBuffer.length;
    }

    public byte[] getDataBuffer() {
        return this.dataBuffer;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00fb, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0100, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0101, code lost:
        r7.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0104, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeOut(java.io.OutputStream r8) throws java.io.IOException {
        /*
            r7 = this;
            org.apache.poi.util.LittleEndianOutputStream r0 = new org.apache.poi.util.LittleEndianOutputStream
            r0.<init>(r8)
            int[] r1 = org.apache.poi.poifs.filesystem.Ole10Native.AnonymousClass1.$SwitchMap$org$apache$poi$poifs$filesystem$Ole10Native$EncodingMode
            org.apache.poi.poifs.filesystem.Ole10Native$EncodingMode r2 = r7.mode
            int r2 = r2.ordinal()
            r1 = r1[r2]
            r2 = 1
            if (r1 == r2) goto L_0x003d
            r2 = 2
            if (r1 == r2) goto L_0x0025
            int r1 = r7.getDataSize()
            r0.writeInt(r1)
            byte[] r7 = r7.getDataBuffer()
            r8.write(r7)
            goto L_0x00f8
        L_0x0025:
            int r1 = r7.getDataSize()
            int r1 = r1 + r2
            r0.writeInt(r1)
            short r1 = r7.getFlags1()
            r0.writeShort(r1)
            byte[] r7 = r7.getDataBuffer()
            r8.write(r7)
            goto L_0x00f8
        L_0x003d:
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r1 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream
            r1.<init>()
            org.apache.poi.util.LittleEndianOutputStream r3 = new org.apache.poi.util.LittleEndianOutputStream
            r3.<init>(r1)
            short r4 = r7.getFlags1()     // Catch:{ all -> 0x00f9 }
            r3.writeShort(r4)     // Catch:{ all -> 0x00f9 }
            java.lang.String r4 = r7.getLabel()     // Catch:{ all -> 0x00f9 }
            java.nio.charset.Charset r5 = ISO1     // Catch:{ all -> 0x00f9 }
            byte[] r4 = r4.getBytes(r5)     // Catch:{ all -> 0x00f9 }
            r3.write(r4)     // Catch:{ all -> 0x00f9 }
            r4 = 0
            r3.write(r4)     // Catch:{ all -> 0x00f9 }
            java.lang.String r6 = r7.getFileName()     // Catch:{ all -> 0x00f9 }
            byte[] r6 = r6.getBytes(r5)     // Catch:{ all -> 0x00f9 }
            r3.write(r6)     // Catch:{ all -> 0x00f9 }
            r3.write(r4)     // Catch:{ all -> 0x00f9 }
            short r6 = r7.getFlags2()     // Catch:{ all -> 0x00f9 }
            r3.writeShort(r6)     // Catch:{ all -> 0x00f9 }
            short r6 = r7.getUnknown1()     // Catch:{ all -> 0x00f9 }
            r3.writeShort(r6)     // Catch:{ all -> 0x00f9 }
            java.lang.String r6 = r7.getCommand()     // Catch:{ all -> 0x00f9 }
            int r6 = r6.length()     // Catch:{ all -> 0x00f9 }
            int r6 = r6 + r2
            r3.writeInt(r6)     // Catch:{ all -> 0x00f9 }
            java.lang.String r2 = r7.getCommand()     // Catch:{ all -> 0x00f9 }
            byte[] r2 = r2.getBytes(r5)     // Catch:{ all -> 0x00f9 }
            r3.write(r2)     // Catch:{ all -> 0x00f9 }
            r3.write(r4)     // Catch:{ all -> 0x00f9 }
            int r2 = r7.getDataSize()     // Catch:{ all -> 0x00f9 }
            r3.writeInt(r2)     // Catch:{ all -> 0x00f9 }
            byte[] r2 = r7.getDataBuffer()     // Catch:{ all -> 0x00f9 }
            r3.write(r2)     // Catch:{ all -> 0x00f9 }
            java.lang.String r2 = r7.command2     // Catch:{ all -> 0x00f9 }
            if (r2 == 0) goto L_0x00e8
            java.lang.String r5 = r7.label2     // Catch:{ all -> 0x00f9 }
            if (r5 == 0) goto L_0x00e8
            java.lang.String r5 = r7.fileName2     // Catch:{ all -> 0x00f9 }
            if (r5 != 0) goto L_0x00b0
            goto L_0x00e8
        L_0x00b0:
            int r2 = r2.length()     // Catch:{ all -> 0x00f9 }
            long r4 = (long) r2     // Catch:{ all -> 0x00f9 }
            r3.writeUInt(r4)     // Catch:{ all -> 0x00f9 }
            java.lang.String r2 = r7.command2     // Catch:{ all -> 0x00f9 }
            byte[] r2 = org.apache.poi.util.StringUtil.getToUnicodeLE(r2)     // Catch:{ all -> 0x00f9 }
            r3.write(r2)     // Catch:{ all -> 0x00f9 }
            java.lang.String r2 = r7.label2     // Catch:{ all -> 0x00f9 }
            int r2 = r2.length()     // Catch:{ all -> 0x00f9 }
            long r4 = (long) r2     // Catch:{ all -> 0x00f9 }
            r3.writeUInt(r4)     // Catch:{ all -> 0x00f9 }
            java.lang.String r2 = r7.label2     // Catch:{ all -> 0x00f9 }
            byte[] r2 = org.apache.poi.util.StringUtil.getToUnicodeLE(r2)     // Catch:{ all -> 0x00f9 }
            r3.write(r2)     // Catch:{ all -> 0x00f9 }
            java.lang.String r2 = r7.fileName2     // Catch:{ all -> 0x00f9 }
            int r2 = r2.length()     // Catch:{ all -> 0x00f9 }
            long r4 = (long) r2     // Catch:{ all -> 0x00f9 }
            r3.writeUInt(r4)     // Catch:{ all -> 0x00f9 }
            java.lang.String r7 = r7.fileName2     // Catch:{ all -> 0x00f9 }
            byte[] r7 = org.apache.poi.util.StringUtil.getToUnicodeLE(r7)     // Catch:{ all -> 0x00f9 }
            r3.write(r7)     // Catch:{ all -> 0x00f9 }
            goto L_0x00eb
        L_0x00e8:
            r3.writeShort(r4)     // Catch:{ all -> 0x00f9 }
        L_0x00eb:
            r3.close()
            int r7 = r1.size()
            r0.writeInt(r7)
            r1.writeTo(r8)
        L_0x00f8:
            return
        L_0x00f9:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x00fb }
        L_0x00fb:
            r8 = move-exception
            r3.close()     // Catch:{ all -> 0x0100 }
            goto L_0x0104
        L_0x0100:
            r0 = move-exception
            r7.addSuppressed(r0)
        L_0x0104:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.filesystem.Ole10Native.writeOut(java.io.OutputStream):void");
    }

    /* renamed from: org.apache.poi.poifs.filesystem.Ole10Native$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$poifs$filesystem$Ole10Native$EncodingMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.apache.poi.poifs.filesystem.Ole10Native$EncodingMode[] r0 = org.apache.poi.poifs.filesystem.Ole10Native.EncodingMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$poifs$filesystem$Ole10Native$EncodingMode = r0
                org.apache.poi.poifs.filesystem.Ole10Native$EncodingMode r1 = org.apache.poi.poifs.filesystem.Ole10Native.EncodingMode.parsed     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$filesystem$Ole10Native$EncodingMode     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.poifs.filesystem.Ole10Native$EncodingMode r1 = org.apache.poi.poifs.filesystem.Ole10Native.EncodingMode.compact     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$filesystem$Ole10Native$EncodingMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.poifs.filesystem.Ole10Native$EncodingMode r1 = org.apache.poi.poifs.filesystem.Ole10Native.EncodingMode.unparsed     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.filesystem.Ole10Native.AnonymousClass1.<clinit>():void");
        }
    }

    public void setFlags1(short s) {
        this.flags1 = s;
    }

    public void setFlags2(short s) {
        this.flags2 = s;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public void setCommand(String str) {
        this.command = str;
    }

    public void setUnknown1(short s) {
        this.unknown1 = s;
    }

    public void setDataBuffer(byte[] bArr) {
        this.dataBuffer = (byte[]) bArr.clone();
    }

    public String getCommand2() {
        return this.command2;
    }

    public void setCommand2(String str) {
        this.command2 = str;
    }

    public String getLabel2() {
        return this.label2;
    }

    public void setLabel2(String str) {
        this.label2 = str;
    }

    public String getFileName2() {
        return this.fileName2;
    }

    public void setFileName2(String str) {
        this.fileName2 = str;
    }
}
