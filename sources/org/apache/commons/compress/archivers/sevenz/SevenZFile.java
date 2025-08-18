package org.apache.commons.compress.archivers.sevenz;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.CRC32;
import kotlin.UByte;
import org.apache.commons.compress.MemoryLimitException;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.commons.compress.utils.BoundedInputStream;
import org.apache.commons.compress.utils.CRC32VerifyingInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;

public class SevenZFile implements Closeable {
    private static final String DEFAULT_FILE_NAME = "unknown archive";
    private static final CharsetEncoder PASSWORD_ENCODER = StandardCharsets.UTF_16LE.newEncoder();
    static final int SIGNATURE_HEADER_SIZE = 32;
    static final byte[] sevenZSignature = {TarConstants.LF_CONTIG, 122, -68, -81, 39, 28};
    private final Archive archive;
    private SeekableByteChannel channel;
    /* access modifiers changed from: private */
    public long compressedBytesReadFromCurrentEntry;
    private int currentEntryIndex;
    private int currentFolderIndex;
    private InputStream currentFolderInputStream;
    private final ArrayList<InputStream> deferredBlockStreams;
    private final String fileName;
    private final SevenZFileOptions options;
    private byte[] password;
    /* access modifiers changed from: private */
    public long uncompressedBytesReadFromCurrentEntry;

    public SevenZFile(File file, char[] cArr) throws IOException {
        this(file, cArr, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(File file, char[] cArr, SevenZFileOptions sevenZFileOptions) throws IOException {
        this(Files.newByteChannel(file.toPath(), EnumSet.of(StandardOpenOption.READ), new FileAttribute[0]), file.getAbsolutePath(), utf16Decode(cArr), true, sevenZFileOptions);
    }

    @Deprecated
    public SevenZFile(File file, byte[] bArr) throws IOException {
        this(Files.newByteChannel(file.toPath(), EnumSet.of(StandardOpenOption.READ), new FileAttribute[0]), file.getAbsolutePath(), bArr, true, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel) throws IOException {
        this(seekableByteChannel, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, SevenZFileOptions sevenZFileOptions) throws IOException {
        this(seekableByteChannel, DEFAULT_FILE_NAME, (char[]) null, sevenZFileOptions);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, char[] cArr) throws IOException {
        this(seekableByteChannel, cArr, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, char[] cArr, SevenZFileOptions sevenZFileOptions) throws IOException {
        this(seekableByteChannel, DEFAULT_FILE_NAME, cArr, sevenZFileOptions);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, String str, char[] cArr) throws IOException {
        this(seekableByteChannel, str, cArr, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, String str, char[] cArr, SevenZFileOptions sevenZFileOptions) throws IOException {
        this(seekableByteChannel, str, utf16Decode(cArr), false, sevenZFileOptions);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, String str) throws IOException {
        this(seekableByteChannel, str, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, String str, SevenZFileOptions sevenZFileOptions) throws IOException {
        this(seekableByteChannel, str, (byte[]) null, false, sevenZFileOptions);
    }

    @Deprecated
    public SevenZFile(SeekableByteChannel seekableByteChannel, byte[] bArr) throws IOException {
        this(seekableByteChannel, DEFAULT_FILE_NAME, bArr);
    }

    @Deprecated
    public SevenZFile(SeekableByteChannel seekableByteChannel, String str, byte[] bArr) throws IOException {
        this(seekableByteChannel, str, bArr, false, SevenZFileOptions.DEFAULT);
    }

    private SevenZFile(SeekableByteChannel seekableByteChannel, String str, byte[] bArr, boolean z, SevenZFileOptions sevenZFileOptions) throws IOException {
        this.currentEntryIndex = -1;
        this.currentFolderIndex = -1;
        this.deferredBlockStreams = new ArrayList<>();
        this.channel = seekableByteChannel;
        this.fileName = str;
        this.options = sevenZFileOptions;
        try {
            this.archive = readHeaders(bArr);
            if (bArr != null) {
                this.password = Arrays.copyOf(bArr, bArr.length);
            } else {
                this.password = null;
            }
        } catch (Throwable th) {
            if (z) {
                this.channel.close();
            }
            throw th;
        }
    }

    public SevenZFile(File file) throws IOException {
        this(file, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(File file, SevenZFileOptions sevenZFileOptions) throws IOException {
        this(file, (char[]) null, sevenZFileOptions);
    }

    public void close() throws IOException {
        SeekableByteChannel seekableByteChannel = this.channel;
        if (seekableByteChannel != null) {
            try {
                seekableByteChannel.close();
            } finally {
                this.channel = null;
                byte[] bArr = this.password;
                if (bArr != null) {
                    Arrays.fill(bArr, (byte) 0);
                }
                this.password = null;
            }
        }
    }

    public SevenZArchiveEntry getNextEntry() throws IOException {
        if (this.currentEntryIndex >= this.archive.files.length - 1) {
            return null;
        }
        this.currentEntryIndex++;
        SevenZArchiveEntry sevenZArchiveEntry = this.archive.files[this.currentEntryIndex];
        if (sevenZArchiveEntry.getName() == null && this.options.getUseDefaultNameForUnnamedEntries()) {
            sevenZArchiveEntry.setName(getDefaultName());
        }
        buildDecodingStream(this.currentEntryIndex, false);
        this.compressedBytesReadFromCurrentEntry = 0;
        this.uncompressedBytesReadFromCurrentEntry = 0;
        return sevenZArchiveEntry;
    }

    public Iterable<SevenZArchiveEntry> getEntries() {
        return new ArrayList(Arrays.asList(this.archive.files));
    }

    private Archive readHeaders(byte[] bArr) throws IOException {
        ByteBuffer order = ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN);
        readFully(order);
        byte[] bArr2 = new byte[6];
        order.get(bArr2);
        if (Arrays.equals(bArr2, sevenZSignature)) {
            byte b = order.get();
            byte b2 = order.get();
            boolean z = false;
            if (b == 0) {
                long j = ((long) order.getInt()) & 4294967295L;
                if (j == 0) {
                    long position = this.channel.position();
                    ByteBuffer allocate = ByteBuffer.allocate(20);
                    readFully(allocate);
                    this.channel.position(position);
                    while (true) {
                        if (allocate.hasRemaining()) {
                            if (allocate.get() != 0) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    z = true;
                } else {
                    z = true;
                }
                if (z) {
                    return initializeArchive(readStartHeader(j), bArr, true);
                }
                if (this.options.getTryToRecoverBrokenArchives()) {
                    return tryToLocateEndHeader(bArr);
                }
                throw new IOException("archive seems to be invalid.\nYou may want to retry and enable the tryToRecoverBrokenArchives if the archive could be a multi volume archive that has been closed prematurely.");
            }
            throw new IOException(String.format("Unsupported 7z version (%d,%d)", new Object[]{Byte.valueOf(b), Byte.valueOf(b2)}));
        }
        throw new IOException("Bad 7z signature");
    }

    private Archive tryToLocateEndHeader(byte[] bArr) throws IOException {
        long j;
        ByteBuffer allocate = ByteBuffer.allocate(1);
        long position = this.channel.position() + 20;
        if (this.channel.position() + 1048576 > this.channel.size()) {
            j = this.channel.position();
        } else {
            j = this.channel.size() - 1048576;
        }
        long size = this.channel.size() - 1;
        while (size > j) {
            size--;
            this.channel.position(size);
            allocate.rewind();
            if (this.channel.read(allocate) >= 1) {
                byte b = allocate.array()[0];
                if (b == 23 || b == 1) {
                    try {
                        StartHeader startHeader = new StartHeader();
                        startHeader.nextHeaderOffset = size - position;
                        startHeader.nextHeaderSize = this.channel.size() - size;
                        Archive initializeArchive = initializeArchive(startHeader, bArr, false);
                        if (initializeArchive.packSizes.length > 0 && initializeArchive.files.length > 0) {
                            return initializeArchive;
                        }
                    } catch (Exception unused) {
                        continue;
                    }
                }
            } else {
                throw new EOFException();
            }
        }
        throw new IOException("Start header corrupt and unable to guess end header");
    }

    private Archive initializeArchive(StartHeader startHeader, byte[] bArr, boolean z) throws IOException {
        assertFitsIntoNonNegativeInt("nextHeaderSize", startHeader.nextHeaderSize);
        this.channel.position(startHeader.nextHeaderOffset + 32);
        ByteBuffer order = ByteBuffer.allocate((int) startHeader.nextHeaderSize).order(ByteOrder.LITTLE_ENDIAN);
        readFully(order);
        if (z) {
            CRC32 crc32 = new CRC32();
            crc32.update(order.array());
            if (startHeader.nextHeaderCrc != crc32.getValue()) {
                throw new IOException("NextHeader CRC mismatch");
            }
        }
        Archive archive2 = new Archive();
        int unsignedByte = getUnsignedByte(order);
        if (unsignedByte == 23) {
            order = readEncodedHeader(order, archive2, bArr);
            archive2 = new Archive();
            unsignedByte = getUnsignedByte(order);
        }
        if (unsignedByte == 1) {
            readHeader(order, archive2);
            archive2.subStreamsInfo = null;
            return archive2;
        }
        throw new IOException("Broken or unsupported archive: no Header");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0082, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0087, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0088, code lost:
        r9.addSuppressed(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x008b, code lost:
        throw r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.apache.commons.compress.archivers.sevenz.StartHeader readStartHeader(long r10) throws java.io.IOException {
        /*
            r9 = this;
            org.apache.commons.compress.archivers.sevenz.StartHeader r0 = new org.apache.commons.compress.archivers.sevenz.StartHeader
            r0.<init>()
            java.io.DataInputStream r1 = new java.io.DataInputStream
            org.apache.commons.compress.utils.CRC32VerifyingInputStream r8 = new org.apache.commons.compress.utils.CRC32VerifyingInputStream
            org.apache.commons.compress.archivers.sevenz.BoundedSeekableByteChannelInputStream r3 = new org.apache.commons.compress.archivers.sevenz.BoundedSeekableByteChannelInputStream
            java.nio.channels.SeekableByteChannel r2 = r9.channel
            r4 = 20
            r3.<init>(r2, r4)
            r2 = r8
            r6 = r10
            r2.<init>((java.io.InputStream) r3, (long) r4, (long) r6)
            r1.<init>(r8)
            long r10 = r1.readLong()     // Catch:{ all -> 0x0080 }
            long r10 = java.lang.Long.reverseBytes(r10)     // Catch:{ all -> 0x0080 }
            r0.nextHeaderOffset = r10     // Catch:{ all -> 0x0080 }
            long r10 = r0.nextHeaderOffset     // Catch:{ all -> 0x0080 }
            r2 = 0
            int r10 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r10 < 0) goto L_0x0078
            long r10 = r0.nextHeaderOffset     // Catch:{ all -> 0x0080 }
            r2 = 32
            long r10 = r10 + r2
            java.nio.channels.SeekableByteChannel r4 = r9.channel     // Catch:{ all -> 0x0080 }
            long r4 = r4.size()     // Catch:{ all -> 0x0080 }
            int r10 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r10 > 0) goto L_0x0078
            long r10 = r1.readLong()     // Catch:{ all -> 0x0080 }
            long r10 = java.lang.Long.reverseBytes(r10)     // Catch:{ all -> 0x0080 }
            r0.nextHeaderSize = r10     // Catch:{ all -> 0x0080 }
            long r10 = r0.nextHeaderOffset     // Catch:{ all -> 0x0080 }
            long r4 = r0.nextHeaderSize     // Catch:{ all -> 0x0080 }
            long r10 = r10 + r4
            long r4 = r0.nextHeaderOffset     // Catch:{ all -> 0x0080 }
            int r4 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r4 < 0) goto L_0x0070
            long r10 = r10 + r2
            java.nio.channels.SeekableByteChannel r9 = r9.channel     // Catch:{ all -> 0x0080 }
            long r2 = r9.size()     // Catch:{ all -> 0x0080 }
            int r9 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r9 > 0) goto L_0x0070
            int r9 = r1.readInt()     // Catch:{ all -> 0x0080 }
            int r9 = java.lang.Integer.reverseBytes(r9)     // Catch:{ all -> 0x0080 }
            long r9 = (long) r9     // Catch:{ all -> 0x0080 }
            r2 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r9 = r9 & r2
            r0.nextHeaderCrc = r9     // Catch:{ all -> 0x0080 }
            r1.close()
            return r0
        L_0x0070:
            java.io.IOException r9 = new java.io.IOException     // Catch:{ all -> 0x0080 }
            java.lang.String r10 = "nextHeaderSize is out of bounds"
            r9.<init>(r10)     // Catch:{ all -> 0x0080 }
            throw r9     // Catch:{ all -> 0x0080 }
        L_0x0078:
            java.io.IOException r9 = new java.io.IOException     // Catch:{ all -> 0x0080 }
            java.lang.String r10 = "nextHeaderOffset is out of bounds"
            r9.<init>(r10)     // Catch:{ all -> 0x0080 }
            throw r9     // Catch:{ all -> 0x0080 }
        L_0x0080:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x0082 }
        L_0x0082:
            r10 = move-exception
            r1.close()     // Catch:{ all -> 0x0087 }
            goto L_0x008b
        L_0x0087:
            r11 = move-exception
            r9.addSuppressed(r11)
        L_0x008b:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.sevenz.SevenZFile.readStartHeader(long):org.apache.commons.compress.archivers.sevenz.StartHeader");
    }

    private void readHeader(ByteBuffer byteBuffer, Archive archive2) throws IOException {
        int position = byteBuffer.position();
        sanityCheckAndCollectStatistics(byteBuffer).assertValidity(this.options.getMaxMemoryLimitInKb());
        byteBuffer.position(position);
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 2) {
            readArchiveProperties(byteBuffer);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte != 3) {
            if (unsignedByte == 4) {
                readStreamsInfo(byteBuffer, archive2);
                unsignedByte = getUnsignedByte(byteBuffer);
            }
            if (unsignedByte == 5) {
                readFilesInfo(byteBuffer, archive2);
                getUnsignedByte(byteBuffer);
                return;
            }
            return;
        }
        throw new IOException("Additional streams unsupported");
    }

    private ArchiveStatistics sanityCheckAndCollectStatistics(ByteBuffer byteBuffer) throws IOException {
        ArchiveStatistics archiveStatistics = new ArchiveStatistics();
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 2) {
            sanityCheckArchiveProperties(byteBuffer);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte != 3) {
            if (unsignedByte == 4) {
                sanityCheckStreamsInfo(byteBuffer, archiveStatistics);
                unsignedByte = getUnsignedByte(byteBuffer);
            }
            if (unsignedByte == 5) {
                sanityCheckFilesInfo(byteBuffer, archiveStatistics);
                unsignedByte = getUnsignedByte(byteBuffer);
            }
            if (unsignedByte == 0) {
                return archiveStatistics;
            }
            throw new IOException("Badly terminated header, found " + unsignedByte);
        }
        throw new IOException("Additional streams unsupported");
    }

    private void readArchiveProperties(ByteBuffer byteBuffer) throws IOException {
        int unsignedByte = getUnsignedByte(byteBuffer);
        while (unsignedByte != 0) {
            get(byteBuffer, new byte[((int) readUint64(byteBuffer))]);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
    }

    private void sanityCheckArchiveProperties(ByteBuffer byteBuffer) throws IOException {
        int unsignedByte = getUnsignedByte(byteBuffer);
        while (unsignedByte != 0) {
            long assertFitsIntoNonNegativeInt = (long) assertFitsIntoNonNegativeInt("propertySize", readUint64(byteBuffer));
            if (skipBytesFully(byteBuffer, assertFitsIntoNonNegativeInt) >= assertFitsIntoNonNegativeInt) {
                unsignedByte = getUnsignedByte(byteBuffer);
            } else {
                throw new IOException("invalid property size");
            }
        }
    }

    private ByteBuffer readEncodedHeader(ByteBuffer byteBuffer, Archive archive2, byte[] bArr) throws IOException {
        int position = byteBuffer.position();
        ArchiveStatistics archiveStatistics = new ArchiveStatistics();
        sanityCheckStreamsInfo(byteBuffer, archiveStatistics);
        archiveStatistics.assertValidity(this.options.getMaxMemoryLimitInKb());
        byteBuffer.position(position);
        readStreamsInfo(byteBuffer, archive2);
        if (archive2.folders == null || archive2.folders.length == 0) {
            throw new IOException("no folders, can't read encoded header");
        } else if (archive2.packSizes == null || archive2.packSizes.length == 0) {
            throw new IOException("no packed streams, can't read encoded header");
        } else {
            Folder folder = archive2.folders[0];
            this.channel.position(archive2.packPos + 32 + 0);
            BoundedSeekableByteChannelInputStream boundedSeekableByteChannelInputStream = new BoundedSeekableByteChannelInputStream(this.channel, archive2.packSizes[0]);
            CRC32VerifyingInputStream cRC32VerifyingInputStream = boundedSeekableByteChannelInputStream;
            for (Coder next : folder.getOrderedCoders()) {
                if (next.numInStreams == 1 && next.numOutStreams == 1) {
                    cRC32VerifyingInputStream = Coders.addDecoder(this.fileName, cRC32VerifyingInputStream, folder.getUnpackSizeForCoder(next), next, bArr, this.options.getMaxMemoryLimitInKb());
                } else {
                    throw new IOException("Multi input/output stream coders are not yet supported");
                }
            }
            if (folder.hasCrc) {
                cRC32VerifyingInputStream = new CRC32VerifyingInputStream(cRC32VerifyingInputStream, folder.getUnpackSize(), folder.crc);
            }
            int assertFitsIntoNonNegativeInt = assertFitsIntoNonNegativeInt("unpackSize", folder.getUnpackSize());
            byte[] readRange = IOUtils.readRange(cRC32VerifyingInputStream, assertFitsIntoNonNegativeInt);
            if (readRange.length >= assertFitsIntoNonNegativeInt) {
                cRC32VerifyingInputStream.close();
                return ByteBuffer.wrap(readRange).order(ByteOrder.LITTLE_ENDIAN);
            }
            throw new IOException("premature end of stream");
        }
    }

    private void sanityCheckStreamsInfo(ByteBuffer byteBuffer, ArchiveStatistics archiveStatistics) throws IOException {
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 6) {
            sanityCheckPackInfo(byteBuffer, archiveStatistics);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 7) {
            sanityCheckUnpackInfo(byteBuffer, archiveStatistics);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 8) {
            sanityCheckSubStreamsInfo(byteBuffer, archiveStatistics);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte != 0) {
            throw new IOException("Badly terminated StreamsInfo");
        }
    }

    private void readStreamsInfo(ByteBuffer byteBuffer, Archive archive2) throws IOException {
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 6) {
            readPackInfo(byteBuffer, archive2);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 7) {
            readUnpackInfo(byteBuffer, archive2);
            unsignedByte = getUnsignedByte(byteBuffer);
        } else {
            archive2.folders = Folder.EMPTY_FOLDER_ARRAY;
        }
        if (unsignedByte == 8) {
            readSubStreamsInfo(byteBuffer, archive2);
            getUnsignedByte(byteBuffer);
        }
    }

    private void sanityCheckPackInfo(ByteBuffer byteBuffer, ArchiveStatistics archiveStatistics) throws IOException {
        ByteBuffer byteBuffer2 = byteBuffer;
        long readUint64 = readUint64(byteBuffer);
        long j = 0;
        if (readUint64 >= 0) {
            long j2 = 32 + readUint64;
            if (j2 <= this.channel.size() && j2 >= 0) {
                int unused = archiveStatistics.numberOfPackedStreams = assertFitsIntoNonNegativeInt("numPackStreams", readUint64(byteBuffer));
                int unsignedByte = getUnsignedByte(byteBuffer);
                if (unsignedByte == 9) {
                    int i = 0;
                    long j3 = 0;
                    while (i < archiveStatistics.numberOfPackedStreams) {
                        long readUint642 = readUint64(byteBuffer);
                        j3 += readUint642;
                        long j4 = j2 + j3;
                        if (readUint642 < j || j4 > this.channel.size() || j4 < readUint64) {
                            throw new IOException("packSize (" + readUint642 + ") is out of range");
                        }
                        i++;
                        j = 0;
                    }
                    unsignedByte = getUnsignedByte(byteBuffer);
                }
                if (unsignedByte == 10) {
                    long cardinality = (long) (readAllOrBits(byteBuffer2, archiveStatistics.numberOfPackedStreams).cardinality() * 4);
                    if (skipBytesFully(byteBuffer2, cardinality) >= cardinality) {
                        unsignedByte = getUnsignedByte(byteBuffer);
                    } else {
                        throw new IOException("invalid number of CRCs in PackInfo");
                    }
                }
                if (unsignedByte != 0) {
                    throw new IOException("Badly terminated PackInfo (" + unsignedByte + ")");
                }
                return;
            }
        }
        throw new IOException("packPos (" + readUint64 + ") is out of range");
    }

    private void readPackInfo(ByteBuffer byteBuffer, Archive archive2) throws IOException {
        archive2.packPos = readUint64(byteBuffer);
        int readUint64 = (int) readUint64(byteBuffer);
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 9) {
            archive2.packSizes = new long[readUint64];
            for (int i = 0; i < archive2.packSizes.length; i++) {
                archive2.packSizes[i] = readUint64(byteBuffer);
            }
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 10) {
            archive2.packCrcsDefined = readAllOrBits(byteBuffer, readUint64);
            archive2.packCrcs = new long[readUint64];
            for (int i2 = 0; i2 < readUint64; i2++) {
                if (archive2.packCrcsDefined.get(i2)) {
                    archive2.packCrcs[i2] = ((long) getInt(byteBuffer)) & 4294967295L;
                }
            }
            getUnsignedByte(byteBuffer);
        }
    }

    private void sanityCheckUnpackInfo(ByteBuffer byteBuffer, ArchiveStatistics archiveStatistics) throws IOException {
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 11) {
            int unused = archiveStatistics.numberOfFolders = assertFitsIntoNonNegativeInt("numFolders", readUint64(byteBuffer));
            if (getUnsignedByte(byteBuffer) == 0) {
                LinkedList<Integer> linkedList = new LinkedList<>();
                for (int i = 0; i < archiveStatistics.numberOfFolders; i++) {
                    linkedList.add(Integer.valueOf(sanityCheckFolder(byteBuffer, archiveStatistics)));
                }
                if (archiveStatistics.numberOfInStreams - (archiveStatistics.numberOfOutStreams - ((long) archiveStatistics.numberOfFolders)) >= ((long) archiveStatistics.numberOfPackedStreams)) {
                    int unsignedByte2 = getUnsignedByte(byteBuffer);
                    if (unsignedByte2 == 12) {
                        for (Integer intValue : linkedList) {
                            int intValue2 = intValue.intValue();
                            int i2 = 0;
                            while (true) {
                                if (i2 < intValue2) {
                                    if (readUint64(byteBuffer) >= 0) {
                                        i2++;
                                    } else {
                                        throw new IllegalArgumentException("negative unpackSize");
                                    }
                                }
                            }
                        }
                        int unsignedByte3 = getUnsignedByte(byteBuffer);
                        if (unsignedByte3 == 10) {
                            BitSet unused2 = archiveStatistics.folderHasCrc = readAllOrBits(byteBuffer, archiveStatistics.numberOfFolders);
                            long cardinality = (long) (archiveStatistics.folderHasCrc.cardinality() * 4);
                            if (skipBytesFully(byteBuffer, cardinality) >= cardinality) {
                                unsignedByte3 = getUnsignedByte(byteBuffer);
                            } else {
                                throw new IOException("invalid number of CRCs in UnpackInfo");
                            }
                        }
                        if (unsignedByte3 != 0) {
                            throw new IOException("Badly terminated UnpackInfo");
                        }
                        return;
                    }
                    throw new IOException("Expected kCodersUnpackSize, got " + unsignedByte2);
                }
                throw new IOException("archive doesn't contain enough packed streams");
            }
            throw new IOException("External unsupported");
        }
        throw new IOException("Expected kFolder, got " + unsignedByte);
    }

    private void readUnpackInfo(ByteBuffer byteBuffer, Archive archive2) throws IOException {
        getUnsignedByte(byteBuffer);
        int readUint64 = (int) readUint64(byteBuffer);
        Folder[] folderArr = new Folder[readUint64];
        archive2.folders = folderArr;
        getUnsignedByte(byteBuffer);
        for (int i = 0; i < readUint64; i++) {
            folderArr[i] = readFolder(byteBuffer);
        }
        getUnsignedByte(byteBuffer);
        for (int i2 = 0; i2 < readUint64; i2++) {
            Folder folder = folderArr[i2];
            assertFitsIntoNonNegativeInt("totalOutputStreams", folder.totalOutputStreams);
            folder.unpackSizes = new long[((int) folder.totalOutputStreams)];
            for (int i3 = 0; ((long) i3) < folder.totalOutputStreams; i3++) {
                folder.unpackSizes[i3] = readUint64(byteBuffer);
            }
        }
        if (getUnsignedByte(byteBuffer) == 10) {
            BitSet readAllOrBits = readAllOrBits(byteBuffer, readUint64);
            for (int i4 = 0; i4 < readUint64; i4++) {
                if (readAllOrBits.get(i4)) {
                    folderArr[i4].hasCrc = true;
                    folderArr[i4].crc = ((long) getInt(byteBuffer)) & 4294967295L;
                } else {
                    folderArr[i4].hasCrc = false;
                }
            }
            getUnsignedByte(byteBuffer);
        }
    }

    private void sanityCheckSubStreamsInfo(ByteBuffer byteBuffer, ArchiveStatistics archiveStatistics) throws IOException {
        int i;
        int unsignedByte = getUnsignedByte(byteBuffer);
        LinkedList<Integer> linkedList = new LinkedList<>();
        int i2 = 0;
        if (unsignedByte == 13) {
            for (int i3 = 0; i3 < archiveStatistics.numberOfFolders; i3++) {
                linkedList.add(Integer.valueOf(assertFitsIntoNonNegativeInt("numStreams", readUint64(byteBuffer))));
            }
            long unused = archiveStatistics.numberOfUnpackSubStreams = ((Long) linkedList.stream().collect(Collectors.summingLong(new SevenZFile$$ExternalSyntheticLambda0()))).longValue();
            unsignedByte = getUnsignedByte(byteBuffer);
        } else {
            long unused2 = archiveStatistics.numberOfUnpackSubStreams = (long) archiveStatistics.numberOfFolders;
        }
        assertFitsIntoNonNegativeInt("totalUnpackStreams", archiveStatistics.numberOfUnpackSubStreams);
        if (unsignedByte == 9) {
            for (Integer intValue : linkedList) {
                int intValue2 = intValue.intValue();
                if (intValue2 != 0) {
                    int i4 = 0;
                    while (i4 < intValue2 - 1) {
                        if (readUint64(byteBuffer) >= 0) {
                            i4++;
                        } else {
                            throw new IOException("negative unpackSize");
                        }
                    }
                    continue;
                }
            }
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (!linkedList.isEmpty()) {
            int i5 = 0;
            for (Integer intValue3 : linkedList) {
                int intValue4 = intValue3.intValue();
                if (intValue4 == 1 && archiveStatistics.folderHasCrc != null) {
                    int i6 = i5 + 1;
                    if (!archiveStatistics.folderHasCrc.get(i5)) {
                        i5 = i6;
                    } else {
                        i5 = i6;
                    }
                }
                i2 += intValue4;
            }
            i = i2;
        } else if (archiveStatistics.folderHasCrc == null) {
            i = archiveStatistics.numberOfFolders;
        } else {
            i = archiveStatistics.numberOfFolders - archiveStatistics.folderHasCrc.cardinality();
        }
        if (unsignedByte == 10) {
            assertFitsIntoNonNegativeInt("numDigests", (long) i);
            long cardinality = (long) (readAllOrBits(byteBuffer, i).cardinality() * 4);
            if (skipBytesFully(byteBuffer, cardinality) >= cardinality) {
                unsignedByte = getUnsignedByte(byteBuffer);
            } else {
                throw new IOException("invalid number of missing CRCs in SubStreamInfo");
            }
        }
        if (unsignedByte != 0) {
            throw new IOException("Badly terminated SubStreamsInfo");
        }
    }

    private void readSubStreamsInfo(ByteBuffer byteBuffer, Archive archive2) throws IOException {
        Archive archive3 = archive2;
        for (Folder folder : archive3.folders) {
            folder.numUnpackSubStreams = 1;
        }
        long length = (long) archive3.folders.length;
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 13) {
            long j = 0;
            for (Folder folder2 : archive3.folders) {
                long readUint64 = readUint64(byteBuffer);
                folder2.numUnpackSubStreams = (int) readUint64;
                j += readUint64;
            }
            unsignedByte = getUnsignedByte(byteBuffer);
            length = j;
        }
        int i = (int) length;
        SubStreamsInfo subStreamsInfo = new SubStreamsInfo();
        subStreamsInfo.unpackSizes = new long[i];
        subStreamsInfo.hasCrc = new BitSet(i);
        subStreamsInfo.crcs = new long[i];
        int i2 = 0;
        for (Folder folder3 : archive3.folders) {
            if (folder3.numUnpackSubStreams != 0) {
                long j2 = 0;
                if (unsignedByte == 9) {
                    int i3 = 0;
                    while (i3 < folder3.numUnpackSubStreams - 1) {
                        long readUint642 = readUint64(byteBuffer);
                        subStreamsInfo.unpackSizes[i2] = readUint642;
                        j2 += readUint642;
                        i3++;
                        i2++;
                    }
                }
                if (j2 <= folder3.getUnpackSize()) {
                    subStreamsInfo.unpackSizes[i2] = folder3.getUnpackSize() - j2;
                    i2++;
                } else {
                    throw new IOException("sum of unpack sizes of folder exceeds total unpack size");
                }
            }
        }
        if (unsignedByte == 9) {
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        int i4 = 0;
        for (Folder folder4 : archive3.folders) {
            if (folder4.numUnpackSubStreams != 1 || !folder4.hasCrc) {
                i4 += folder4.numUnpackSubStreams;
            }
        }
        if (unsignedByte == 10) {
            BitSet readAllOrBits = readAllOrBits(byteBuffer, i4);
            long[] jArr = new long[i4];
            for (int i5 = 0; i5 < i4; i5++) {
                if (readAllOrBits.get(i5)) {
                    jArr[i5] = ((long) getInt(byteBuffer)) & 4294967295L;
                }
            }
            int i6 = 0;
            int i7 = 0;
            for (Folder folder5 : archive3.folders) {
                if (folder5.numUnpackSubStreams != 1 || !folder5.hasCrc) {
                    for (int i8 = 0; i8 < folder5.numUnpackSubStreams; i8++) {
                        subStreamsInfo.hasCrc.set(i6, readAllOrBits.get(i7));
                        subStreamsInfo.crcs[i6] = jArr[i7];
                        i6++;
                        i7++;
                    }
                } else {
                    subStreamsInfo.hasCrc.set(i6, true);
                    subStreamsInfo.crcs[i6] = folder5.crc;
                    i6++;
                }
            }
            getUnsignedByte(byteBuffer);
        }
        archive3.subStreamsInfo = subStreamsInfo;
    }

    private int sanityCheckFolder(ByteBuffer byteBuffer, ArchiveStatistics archiveStatistics) throws IOException {
        ByteBuffer byteBuffer2 = byteBuffer;
        ArchiveStatistics archiveStatistics2 = archiveStatistics;
        int assertFitsIntoNonNegativeInt = assertFitsIntoNonNegativeInt("numCoders", readUint64(byteBuffer));
        if (assertFitsIntoNonNegativeInt != 0) {
            long unused = archiveStatistics2.numberOfCoders = archiveStatistics.numberOfCoders + ((long) assertFitsIntoNonNegativeInt);
            int i = 0;
            long j = 0;
            long j2 = 0;
            int i2 = 0;
            while (true) {
                long j3 = 1;
                boolean z = true;
                if (i2 < assertFitsIntoNonNegativeInt) {
                    int unsignedByte = getUnsignedByte(byteBuffer);
                    get(byteBuffer2, new byte[(unsignedByte & 15)]);
                    boolean z2 = (unsignedByte & 16) == 0;
                    boolean z3 = (unsignedByte & 32) != 0;
                    if ((unsignedByte & 128) == 0) {
                        z = false;
                    }
                    if (!z) {
                        if (z2) {
                            j++;
                        } else {
                            j += (long) assertFitsIntoNonNegativeInt("numInStreams", readUint64(byteBuffer));
                            j3 = (long) assertFitsIntoNonNegativeInt("numOutStreams", readUint64(byteBuffer));
                        }
                        j2 += j3;
                        if (z3) {
                            long assertFitsIntoNonNegativeInt2 = (long) assertFitsIntoNonNegativeInt("propertiesSize", readUint64(byteBuffer));
                            if (skipBytesFully(byteBuffer2, assertFitsIntoNonNegativeInt2) < assertFitsIntoNonNegativeInt2) {
                                throw new IOException("invalid propertiesSize in folder");
                            }
                        }
                        i2++;
                    } else {
                        throw new IOException("Alternative methods are unsupported, please report. The reference implementation doesn't support them either.");
                    }
                } else {
                    assertFitsIntoNonNegativeInt("totalInStreams", j);
                    assertFitsIntoNonNegativeInt("totalOutStreams", j2);
                    long unused2 = archiveStatistics2.numberOfOutStreams = archiveStatistics.numberOfOutStreams + j2;
                    long unused3 = archiveStatistics2.numberOfInStreams = archiveStatistics.numberOfInStreams + j;
                    if (j2 != 0) {
                        int assertFitsIntoNonNegativeInt3 = assertFitsIntoNonNegativeInt("numBindPairs", j2 - 1);
                        long j4 = (long) assertFitsIntoNonNegativeInt3;
                        if (j >= j4) {
                            BitSet bitSet = new BitSet((int) j);
                            int i3 = 0;
                            while (i3 < assertFitsIntoNonNegativeInt3) {
                                int assertFitsIntoNonNegativeInt4 = assertFitsIntoNonNegativeInt("inIndex", readUint64(byteBuffer));
                                if (j > ((long) assertFitsIntoNonNegativeInt4)) {
                                    bitSet.set(assertFitsIntoNonNegativeInt4);
                                    if (j2 > ((long) assertFitsIntoNonNegativeInt("outIndex", readUint64(byteBuffer)))) {
                                        i3++;
                                    } else {
                                        throw new IOException("outIndex is bigger than number of outStreams");
                                    }
                                } else {
                                    throw new IOException("inIndex is bigger than number of inStreams");
                                }
                            }
                            int assertFitsIntoNonNegativeInt5 = assertFitsIntoNonNegativeInt("numPackedStreams", j - j4);
                            if (assertFitsIntoNonNegativeInt5 != 1) {
                                while (i < assertFitsIntoNonNegativeInt5) {
                                    if (((long) assertFitsIntoNonNegativeInt("packedStreamIndex", readUint64(byteBuffer))) < j) {
                                        i++;
                                    } else {
                                        throw new IOException("packedStreamIndex is bigger than number of totalInStreams");
                                    }
                                }
                            } else if (bitSet.nextClearBit(0) == -1) {
                                throw new IOException("Couldn't find stream's bind pair index");
                            }
                            return (int) j2;
                        }
                        throw new IOException("Total input streams can't be less than the number of bind pairs");
                    }
                    throw new IOException("Total output streams can't be 0");
                }
            }
        } else {
            throw new IOException("Folder without coders");
        }
    }

    private Folder readFolder(ByteBuffer byteBuffer) throws IOException {
        ByteBuffer byteBuffer2 = byteBuffer;
        Folder folder = new Folder();
        int readUint64 = (int) readUint64(byteBuffer);
        Coder[] coderArr = new Coder[readUint64];
        long j = 0;
        long j2 = 0;
        int i = 0;
        while (i < readUint64) {
            coderArr[i] = new Coder();
            int unsignedByte = getUnsignedByte(byteBuffer);
            int i2 = unsignedByte & 15;
            boolean z = true;
            boolean z2 = (unsignedByte & 16) == 0;
            boolean z3 = (unsignedByte & 32) != 0;
            if ((unsignedByte & 128) == 0) {
                z = false;
            }
            coderArr[i].decompressionMethodId = new byte[i2];
            get(byteBuffer2, coderArr[i].decompressionMethodId);
            if (z2) {
                coderArr[i].numInStreams = 1;
                coderArr[i].numOutStreams = 1;
            } else {
                coderArr[i].numInStreams = readUint64(byteBuffer);
                coderArr[i].numOutStreams = readUint64(byteBuffer);
            }
            j += coderArr[i].numInStreams;
            j2 += coderArr[i].numOutStreams;
            if (z3) {
                coderArr[i].properties = new byte[((int) readUint64(byteBuffer))];
                get(byteBuffer2, coderArr[i].properties);
            }
            if (!z) {
                i++;
            } else {
                throw new IOException("Alternative methods are unsupported, please report. The reference implementation doesn't support them either.");
            }
        }
        folder.coders = coderArr;
        folder.totalInputStreams = j;
        folder.totalOutputStreams = j2;
        long j3 = j2 - 1;
        int i3 = (int) j3;
        BindPair[] bindPairArr = new BindPair[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            BindPair bindPair = new BindPair();
            bindPairArr[i4] = bindPair;
            bindPair.inIndex = readUint64(byteBuffer);
            bindPairArr[i4].outIndex = readUint64(byteBuffer);
        }
        folder.bindPairs = bindPairArr;
        long j4 = j - j3;
        int i5 = (int) j4;
        long[] jArr = new long[i5];
        if (j4 == 1) {
            int i6 = 0;
            while (i6 < ((int) j) && folder.findBindPairForInStream(i6) >= 0) {
                i6++;
            }
            jArr[0] = (long) i6;
        } else {
            for (int i7 = 0; i7 < i5; i7++) {
                jArr[i7] = readUint64(byteBuffer);
            }
        }
        folder.packedStreams = jArr;
        return folder;
    }

    private BitSet readAllOrBits(ByteBuffer byteBuffer, int i) throws IOException {
        if (getUnsignedByte(byteBuffer) == 0) {
            return readBits(byteBuffer, i);
        }
        BitSet bitSet = new BitSet(i);
        for (int i2 = 0; i2 < i; i2++) {
            bitSet.set(i2, true);
        }
        return bitSet;
    }

    private BitSet readBits(ByteBuffer byteBuffer, int i) throws IOException {
        BitSet bitSet = new BitSet(i);
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            if (i2 == 0) {
                i3 = getUnsignedByte(byteBuffer);
                i2 = 128;
            }
            bitSet.set(i4, (i3 & i2) != 0);
            i2 >>>= 1;
        }
        return bitSet;
    }

    private void sanityCheckFilesInfo(ByteBuffer byteBuffer, ArchiveStatistics archiveStatistics) throws IOException {
        int unused = archiveStatistics.numberOfEntries = assertFitsIntoNonNegativeInt("numFiles", readUint64(byteBuffer));
        int i = -1;
        while (true) {
            int unsignedByte = getUnsignedByte(byteBuffer);
            if (unsignedByte == 0) {
                int access$800 = archiveStatistics.numberOfEntries;
                if (i <= 0) {
                    i = 0;
                }
                int unused2 = archiveStatistics.numberOfEntriesWithStream = access$800 - i;
                return;
            }
            long readUint64 = readUint64(byteBuffer);
            switch (unsignedByte) {
                case 14:
                    i = readBits(byteBuffer, archiveStatistics.numberOfEntries).cardinality();
                    break;
                case 15:
                    if (i != -1) {
                        readBits(byteBuffer, i);
                        break;
                    } else {
                        throw new IOException("Header format error: kEmptyStream must appear before kEmptyFile");
                    }
                case 16:
                    if (i != -1) {
                        readBits(byteBuffer, i);
                        break;
                    } else {
                        throw new IOException("Header format error: kEmptyStream must appear before kAnti");
                    }
                case 17:
                    if (getUnsignedByte(byteBuffer) == 0) {
                        int assertFitsIntoNonNegativeInt = assertFitsIntoNonNegativeInt("file names length", readUint64 - 1);
                        if ((assertFitsIntoNonNegativeInt & 1) == 0) {
                            int i2 = 0;
                            for (int i3 = 0; i3 < assertFitsIntoNonNegativeInt; i3 += 2) {
                                if (getChar(byteBuffer) == 0) {
                                    i2++;
                                }
                            }
                            if (i2 == archiveStatistics.numberOfEntries) {
                                break;
                            } else {
                                throw new IOException("Invalid number of file names (" + i2 + " instead of " + archiveStatistics.numberOfEntries + ")");
                            }
                        } else {
                            throw new IOException("File names length invalid");
                        }
                    } else {
                        throw new IOException("Not implemented");
                    }
                case 18:
                    int cardinality = readAllOrBits(byteBuffer, archiveStatistics.numberOfEntries).cardinality();
                    if (getUnsignedByte(byteBuffer) == 0) {
                        long j = (long) (cardinality * 8);
                        if (skipBytesFully(byteBuffer, j) >= j) {
                            break;
                        } else {
                            throw new IOException("invalid creation dates size");
                        }
                    } else {
                        throw new IOException("Not implemented");
                    }
                case 19:
                    int cardinality2 = readAllOrBits(byteBuffer, archiveStatistics.numberOfEntries).cardinality();
                    if (getUnsignedByte(byteBuffer) == 0) {
                        long j2 = (long) (cardinality2 * 8);
                        if (skipBytesFully(byteBuffer, j2) >= j2) {
                            break;
                        } else {
                            throw new IOException("invalid access dates size");
                        }
                    } else {
                        throw new IOException("Not implemented");
                    }
                case 20:
                    int cardinality3 = readAllOrBits(byteBuffer, archiveStatistics.numberOfEntries).cardinality();
                    if (getUnsignedByte(byteBuffer) == 0) {
                        long j3 = (long) (cardinality3 * 8);
                        if (skipBytesFully(byteBuffer, j3) >= j3) {
                            break;
                        } else {
                            throw new IOException("invalid modification dates size");
                        }
                    } else {
                        throw new IOException("Not implemented");
                    }
                case 21:
                    int cardinality4 = readAllOrBits(byteBuffer, archiveStatistics.numberOfEntries).cardinality();
                    if (getUnsignedByte(byteBuffer) == 0) {
                        long j4 = (long) (cardinality4 * 4);
                        if (skipBytesFully(byteBuffer, j4) >= j4) {
                            break;
                        } else {
                            throw new IOException("invalid windows attributes size");
                        }
                    } else {
                        throw new IOException("Not implemented");
                    }
                case 24:
                    throw new IOException("kStartPos is unsupported, please report");
                case 25:
                    if (skipBytesFully(byteBuffer, readUint64) >= readUint64) {
                        break;
                    } else {
                        throw new IOException("Incomplete kDummy property");
                    }
                default:
                    if (skipBytesFully(byteBuffer, readUint64) >= readUint64) {
                        break;
                    } else {
                        throw new IOException("Incomplete property of type " + unsignedByte);
                    }
            }
        }
    }

    private void readFilesInfo(ByteBuffer byteBuffer, Archive archive2) throws IOException {
        ByteBuffer byteBuffer2 = byteBuffer;
        Archive archive3 = archive2;
        int readUint64 = (int) readUint64(byteBuffer);
        HashMap hashMap = new HashMap();
        BitSet bitSet = null;
        BitSet bitSet2 = null;
        BitSet bitSet3 = null;
        while (true) {
            int unsignedByte = getUnsignedByte(byteBuffer);
            int i = 0;
            if (unsignedByte == 0) {
                int i2 = 0;
                int i3 = 0;
                for (int i4 = 0; i4 < readUint64; i4++) {
                    SevenZArchiveEntry sevenZArchiveEntry = (SevenZArchiveEntry) hashMap.get(Integer.valueOf(i4));
                    if (sevenZArchiveEntry != null) {
                        boolean z = true;
                        sevenZArchiveEntry.setHasStream(bitSet == null || !bitSet.get(i4));
                        if (!sevenZArchiveEntry.hasStream()) {
                            sevenZArchiveEntry.setDirectory(bitSet2 == null || !bitSet2.get(i2));
                            if (bitSet3 == null || !bitSet3.get(i2)) {
                                z = false;
                            }
                            sevenZArchiveEntry.setAntiItem(z);
                            sevenZArchiveEntry.setHasCrc(false);
                            sevenZArchiveEntry.setSize(0);
                            i2++;
                        } else if (archive3.subStreamsInfo != null) {
                            sevenZArchiveEntry.setDirectory(false);
                            sevenZArchiveEntry.setAntiItem(false);
                            sevenZArchiveEntry.setHasCrc(archive3.subStreamsInfo.hasCrc.get(i3));
                            sevenZArchiveEntry.setCrcValue(archive3.subStreamsInfo.crcs[i3]);
                            sevenZArchiveEntry.setSize(archive3.subStreamsInfo.unpackSizes[i3]);
                            if (sevenZArchiveEntry.getSize() >= 0) {
                                i3++;
                            } else {
                                throw new IOException("broken archive, entry with negative size");
                            }
                        } else {
                            throw new IOException("Archive contains file with streams but no subStreamsInfo");
                        }
                    }
                }
                ArrayList arrayList = new ArrayList();
                for (SevenZArchiveEntry sevenZArchiveEntry2 : hashMap.values()) {
                    if (sevenZArchiveEntry2 != null) {
                        arrayList.add(sevenZArchiveEntry2);
                    }
                }
                archive3.files = (SevenZArchiveEntry[]) arrayList.toArray(SevenZArchiveEntry.EMPTY_SEVEN_Z_ARCHIVE_ENTRY_ARRAY);
                calculateStreamMap(archive3);
                return;
            }
            long readUint642 = readUint64(byteBuffer);
            if (unsignedByte != 25) {
                switch (unsignedByte) {
                    case 14:
                        bitSet = readBits(byteBuffer2, readUint64);
                        break;
                    case 15:
                        bitSet2 = readBits(byteBuffer2, bitSet.cardinality());
                        break;
                    case 16:
                        bitSet3 = readBits(byteBuffer2, bitSet.cardinality());
                        break;
                    case 17:
                        getUnsignedByte(byteBuffer);
                        int i5 = (int) (readUint642 - 1);
                        byte[] bArr = new byte[i5];
                        get(byteBuffer2, bArr);
                        int i6 = 0;
                        int i7 = 0;
                        while (i < i5) {
                            if (bArr[i] == 0 && bArr[i + 1] == 0) {
                                checkEntryIsInitialized(hashMap, i7);
                                ((SevenZArchiveEntry) hashMap.get(Integer.valueOf(i7))).setName(new String(bArr, i6, i - i6, StandardCharsets.UTF_16LE));
                                i7++;
                                i6 = i + 2;
                            }
                            i += 2;
                            Archive archive4 = archive2;
                        }
                        if (i6 != i5 || i7 != readUint64) {
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        BitSet readAllOrBits = readAllOrBits(byteBuffer2, readUint64);
                        getUnsignedByte(byteBuffer);
                        while (i < readUint64) {
                            checkEntryIsInitialized(hashMap, i);
                            SevenZArchiveEntry sevenZArchiveEntry3 = (SevenZArchiveEntry) hashMap.get(Integer.valueOf(i));
                            sevenZArchiveEntry3.setHasCreationDate(readAllOrBits.get(i));
                            if (sevenZArchiveEntry3.getHasCreationDate()) {
                                sevenZArchiveEntry3.setCreationDate(getLong(byteBuffer));
                            }
                            i++;
                        }
                        break;
                    case 19:
                        BitSet readAllOrBits2 = readAllOrBits(byteBuffer2, readUint64);
                        getUnsignedByte(byteBuffer);
                        while (i < readUint64) {
                            checkEntryIsInitialized(hashMap, i);
                            SevenZArchiveEntry sevenZArchiveEntry4 = (SevenZArchiveEntry) hashMap.get(Integer.valueOf(i));
                            sevenZArchiveEntry4.setHasAccessDate(readAllOrBits2.get(i));
                            if (sevenZArchiveEntry4.getHasAccessDate()) {
                                sevenZArchiveEntry4.setAccessDate(getLong(byteBuffer));
                            }
                            i++;
                        }
                        break;
                    case 20:
                        BitSet readAllOrBits3 = readAllOrBits(byteBuffer2, readUint64);
                        getUnsignedByte(byteBuffer);
                        while (i < readUint64) {
                            checkEntryIsInitialized(hashMap, i);
                            SevenZArchiveEntry sevenZArchiveEntry5 = (SevenZArchiveEntry) hashMap.get(Integer.valueOf(i));
                            sevenZArchiveEntry5.setHasLastModifiedDate(readAllOrBits3.get(i));
                            if (sevenZArchiveEntry5.getHasLastModifiedDate()) {
                                sevenZArchiveEntry5.setLastModifiedDate(getLong(byteBuffer));
                            }
                            i++;
                        }
                        break;
                    case 21:
                        BitSet readAllOrBits4 = readAllOrBits(byteBuffer2, readUint64);
                        getUnsignedByte(byteBuffer);
                        while (i < readUint64) {
                            checkEntryIsInitialized(hashMap, i);
                            SevenZArchiveEntry sevenZArchiveEntry6 = (SevenZArchiveEntry) hashMap.get(Integer.valueOf(i));
                            sevenZArchiveEntry6.setHasWindowsAttributes(readAllOrBits4.get(i));
                            if (sevenZArchiveEntry6.getHasWindowsAttributes()) {
                                sevenZArchiveEntry6.setWindowsAttributes(getInt(byteBuffer));
                            }
                            i++;
                        }
                        break;
                    default:
                        skipBytesFully(byteBuffer2, readUint642);
                        break;
                }
            } else {
                skipBytesFully(byteBuffer2, readUint642);
            }
            archive3 = archive2;
        }
        throw new IOException("Error parsing file names");
    }

    private void checkEntryIsInitialized(Map<Integer, SevenZArchiveEntry> map, int i) {
        if (map.get(Integer.valueOf(i)) == null) {
            map.put(Integer.valueOf(i), new SevenZArchiveEntry());
        }
    }

    private void calculateStreamMap(Archive archive2) throws IOException {
        StreamMap streamMap = new StreamMap();
        int length = archive2.folders != null ? archive2.folders.length : 0;
        streamMap.folderFirstPackStreamIndex = new int[length];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            streamMap.folderFirstPackStreamIndex[i2] = i;
            i += archive2.folders[i2].packedStreams.length;
        }
        int length2 = archive2.packSizes.length;
        streamMap.packStreamOffsets = new long[length2];
        long j = 0;
        for (int i3 = 0; i3 < length2; i3++) {
            streamMap.packStreamOffsets[i3] = j;
            j += archive2.packSizes[i3];
        }
        streamMap.folderFirstFileIndex = new int[length];
        streamMap.fileFolderIndex = new int[archive2.files.length];
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < archive2.files.length; i6++) {
            if (archive2.files[i6].hasStream() || i4 != 0) {
                if (i4 == 0) {
                    while (i5 < archive2.folders.length) {
                        streamMap.folderFirstFileIndex[i5] = i6;
                        if (archive2.folders[i5].numUnpackSubStreams > 0) {
                            break;
                        }
                        i5++;
                    }
                    if (i5 >= archive2.folders.length) {
                        throw new IOException("Too few folders in archive");
                    }
                }
                streamMap.fileFolderIndex[i6] = i5;
                if (archive2.files[i6].hasStream() && (i4 = i4 + 1) >= archive2.folders[i5].numUnpackSubStreams) {
                    i5++;
                    i4 = 0;
                }
            } else {
                streamMap.fileFolderIndex[i6] = -1;
            }
        }
        archive2.streamMap = streamMap;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: org.apache.commons.compress.utils.BoundedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: org.apache.commons.compress.utils.BoundedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: org.apache.commons.compress.utils.CRC32VerifyingInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: org.apache.commons.compress.utils.BoundedInputStream} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void buildDecodingStream(int r11, boolean r12) throws java.io.IOException {
        /*
            r10 = this;
            org.apache.commons.compress.archivers.sevenz.Archive r0 = r10.archive
            org.apache.commons.compress.archivers.sevenz.StreamMap r0 = r0.streamMap
            if (r0 == 0) goto L_0x008c
            org.apache.commons.compress.archivers.sevenz.Archive r0 = r10.archive
            org.apache.commons.compress.archivers.sevenz.StreamMap r0 = r0.streamMap
            int[] r0 = r0.fileFolderIndex
            r0 = r0[r11]
            if (r0 >= 0) goto L_0x0016
            java.util.ArrayList<java.io.InputStream> r10 = r10.deferredBlockStreams
            r10.clear()
            return
        L_0x0016:
            org.apache.commons.compress.archivers.sevenz.Archive r1 = r10.archive
            org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry[] r1 = r1.files
            r1 = r1[r11]
            int r2 = r10.currentFolderIndex
            r3 = 0
            if (r2 != r0) goto L_0x0051
            if (r11 <= 0) goto L_0x0032
            org.apache.commons.compress.archivers.sevenz.Archive r2 = r10.archive
            org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry[] r2 = r2.files
            int r4 = r11 + -1
            r2 = r2[r4]
            java.lang.Iterable r2 = r2.getContentMethods()
            r1.setContentMethods(r2)
        L_0x0032:
            if (r12 == 0) goto L_0x004f
            java.lang.Iterable r2 = r1.getContentMethods()
            if (r2 != 0) goto L_0x004f
            org.apache.commons.compress.archivers.sevenz.Archive r2 = r10.archive
            org.apache.commons.compress.archivers.sevenz.StreamMap r2 = r2.streamMap
            int[] r2 = r2.folderFirstFileIndex
            r2 = r2[r0]
            org.apache.commons.compress.archivers.sevenz.Archive r4 = r10.archive
            org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry[] r4 = r4.files
            r2 = r4[r2]
            java.lang.Iterable r2 = r2.getContentMethods()
            r1.setContentMethods(r2)
        L_0x004f:
            r2 = 1
            goto L_0x0057
        L_0x0051:
            r10.currentFolderIndex = r0
            r10.reopenFolderInputStream(r0, r1)
            r2 = r3
        L_0x0057:
            if (r12 == 0) goto L_0x005d
            boolean r3 = r10.skipEntriesWhenNeeded(r11, r2, r0)
        L_0x005d:
            if (r12 == 0) goto L_0x0066
            int r12 = r10.currentEntryIndex
            if (r12 != r11) goto L_0x0066
            if (r3 != 0) goto L_0x0066
            return
        L_0x0066:
            org.apache.commons.compress.utils.BoundedInputStream r5 = new org.apache.commons.compress.utils.BoundedInputStream
            java.io.InputStream r11 = r10.currentFolderInputStream
            long r2 = r1.getSize()
            r5.<init>(r11, r2)
            boolean r11 = r1.getHasCrc()
            if (r11 == 0) goto L_0x0086
            org.apache.commons.compress.utils.CRC32VerifyingInputStream r11 = new org.apache.commons.compress.utils.CRC32VerifyingInputStream
            long r6 = r1.getSize()
            long r8 = r1.getCrcValue()
            r4 = r11
            r4.<init>((java.io.InputStream) r5, (long) r6, (long) r8)
            r5 = r11
        L_0x0086:
            java.util.ArrayList<java.io.InputStream> r10 = r10.deferredBlockStreams
            r10.add(r5)
            return
        L_0x008c:
            java.io.IOException r10 = new java.io.IOException
            java.lang.String r11 = "Archive doesn't contain stream information to read entries"
            r10.<init>(r11)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.sevenz.SevenZFile.buildDecodingStream(int, boolean):void");
    }

    private void reopenFolderInputStream(int i, SevenZArchiveEntry sevenZArchiveEntry) throws IOException {
        this.deferredBlockStreams.clear();
        InputStream inputStream = this.currentFolderInputStream;
        if (inputStream != null) {
            inputStream.close();
            this.currentFolderInputStream = null;
        }
        Folder folder = this.archive.folders[i];
        int i2 = this.archive.streamMap.folderFirstPackStreamIndex[i];
        this.currentFolderInputStream = buildDecoderStack(folder, this.archive.streamMap.packStreamOffsets[i2] + this.archive.packPos + 32, i2, sevenZArchiveEntry);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: org.apache.commons.compress.utils.BoundedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: org.apache.commons.compress.utils.BoundedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: org.apache.commons.compress.utils.CRC32VerifyingInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: org.apache.commons.compress.utils.BoundedInputStream} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean skipEntriesWhenNeeded(int r10, boolean r11, int r12) throws java.io.IOException {
        /*
            r9 = this;
            org.apache.commons.compress.archivers.sevenz.Archive r0 = r9.archive
            org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry[] r0 = r0.files
            r0 = r0[r10]
            int r1 = r9.currentEntryIndex
            if (r1 != r10) goto L_0x0012
            boolean r1 = r9.hasCurrentEntryBeenRead()
            if (r1 != 0) goto L_0x0012
            r9 = 0
            return r9
        L_0x0012:
            org.apache.commons.compress.archivers.sevenz.Archive r1 = r9.archive
            org.apache.commons.compress.archivers.sevenz.StreamMap r1 = r1.streamMap
            int[] r1 = r1.folderFirstFileIndex
            int r2 = r9.currentFolderIndex
            r1 = r1[r2]
            r2 = 1
            if (r11 == 0) goto L_0x0029
            int r11 = r9.currentEntryIndex
            if (r11 >= r10) goto L_0x0026
            int r1 = r11 + 1
            goto L_0x0029
        L_0x0026:
            r9.reopenFolderInputStream(r12, r0)
        L_0x0029:
            if (r1 >= r10) goto L_0x0060
            org.apache.commons.compress.archivers.sevenz.Archive r11 = r9.archive
            org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry[] r11 = r11.files
            r11 = r11[r1]
            org.apache.commons.compress.utils.BoundedInputStream r4 = new org.apache.commons.compress.utils.BoundedInputStream
            java.io.InputStream r12 = r9.currentFolderInputStream
            long r5 = r11.getSize()
            r4.<init>(r12, r5)
            boolean r12 = r11.getHasCrc()
            if (r12 == 0) goto L_0x0051
            org.apache.commons.compress.utils.CRC32VerifyingInputStream r12 = new org.apache.commons.compress.utils.CRC32VerifyingInputStream
            long r5 = r11.getSize()
            long r7 = r11.getCrcValue()
            r3 = r12
            r3.<init>((java.io.InputStream) r4, (long) r5, (long) r7)
            r4 = r12
        L_0x0051:
            java.util.ArrayList<java.io.InputStream> r12 = r9.deferredBlockStreams
            r12.add(r4)
            java.lang.Iterable r12 = r0.getContentMethods()
            r11.setContentMethods(r12)
            int r1 = r1 + 1
            goto L_0x0029
        L_0x0060:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.sevenz.SevenZFile.skipEntriesWhenNeeded(int, boolean, int):boolean");
    }

    private boolean hasCurrentEntryBeenRead() {
        if (this.deferredBlockStreams.isEmpty()) {
            return false;
        }
        ArrayList<InputStream> arrayList = this.deferredBlockStreams;
        InputStream inputStream = arrayList.get(arrayList.size() - 1);
        boolean z = (inputStream instanceof CRC32VerifyingInputStream) && ((CRC32VerifyingInputStream) inputStream).getBytesRemaining() != this.archive.files[this.currentEntryIndex].getSize();
        if (!(inputStream instanceof BoundedInputStream)) {
            return z;
        }
        if (((BoundedInputStream) inputStream).getBytesRemaining() != this.archive.files[this.currentEntryIndex].getSize()) {
            return true;
        }
        return false;
    }

    private InputStream buildDecoderStack(Folder folder, long j, int i, SevenZArchiveEntry sevenZArchiveEntry) throws IOException {
        this.channel.position(j);
        AnonymousClass1 r10 = new FilterInputStream(new BufferedInputStream(new BoundedSeekableByteChannelInputStream(this.channel, this.archive.packSizes[i]))) {
            public int read() throws IOException {
                int read = this.in.read();
                if (read >= 0) {
                    count(1);
                }
                return read;
            }

            public int read(byte[] bArr) throws IOException {
                return read(bArr, 0, bArr.length);
            }

            public int read(byte[] bArr, int i, int i2) throws IOException {
                if (i2 == 0) {
                    return 0;
                }
                int read = this.in.read(bArr, i, i2);
                if (read >= 0) {
                    count(read);
                }
                return read;
            }

            private void count(int i) {
                SevenZFile sevenZFile = SevenZFile.this;
                long unused = sevenZFile.compressedBytesReadFromCurrentEntry = sevenZFile.compressedBytesReadFromCurrentEntry + ((long) i);
            }
        };
        LinkedList linkedList = new LinkedList();
        InputStream inputStream = r10;
        for (Coder next : folder.getOrderedCoders()) {
            if (next.numInStreams == 1 && next.numOutStreams == 1) {
                SevenZMethod byId = SevenZMethod.byId(next.decompressionMethodId);
                inputStream = Coders.addDecoder(this.fileName, inputStream, folder.getUnpackSizeForCoder(next), next, this.password, this.options.getMaxMemoryLimitInKb());
                linkedList.addFirst(new SevenZMethodConfiguration(byId, Coders.findByMethod(byId).getOptionsFromCoder(next, inputStream)));
            } else {
                throw new IOException("Multi input/output stream coders are not yet supported");
            }
        }
        sevenZArchiveEntry.setContentMethods(linkedList);
        return folder.hasCrc ? new CRC32VerifyingInputStream(inputStream, folder.getUnpackSize(), folder.crc) : inputStream;
    }

    public int read() throws IOException {
        int read = getCurrentStream().read();
        if (read >= 0) {
            this.uncompressedBytesReadFromCurrentEntry++;
        }
        return read;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0046, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0047, code lost:
        if (r0 != null) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004e, code lost:
        r6.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0051, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.io.InputStream getCurrentStream() throws java.io.IOException {
        /*
            r6 = this;
            org.apache.commons.compress.archivers.sevenz.Archive r0 = r6.archive
            org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry[] r0 = r0.files
            int r1 = r6.currentEntryIndex
            r0 = r0[r1]
            long r0 = r0.getSize()
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L_0x001a
            java.io.ByteArrayInputStream r6 = new java.io.ByteArrayInputStream
            byte[] r0 = org.apache.commons.compress.utils.ByteUtils.EMPTY_BYTE_ARRAY
            r6.<init>(r0)
            return r6
        L_0x001a:
            java.util.ArrayList<java.io.InputStream> r0 = r6.deferredBlockStreams
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x005b
        L_0x0022:
            java.util.ArrayList<java.io.InputStream> r0 = r6.deferredBlockStreams
            int r0 = r0.size()
            r1 = 1
            r4 = 0
            if (r0 <= r1) goto L_0x0052
            java.util.ArrayList<java.io.InputStream> r0 = r6.deferredBlockStreams
            java.lang.Object r0 = r0.remove(r4)
            java.io.InputStream r0 = (java.io.InputStream) r0
            r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            org.apache.commons.compress.utils.IOUtils.skip(r0, r4)     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x0041
            r0.close()
        L_0x0041:
            r6.compressedBytesReadFromCurrentEntry = r2
            goto L_0x0022
        L_0x0044:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0046 }
        L_0x0046:
            r1 = move-exception
            if (r0 == 0) goto L_0x0051
            r0.close()     // Catch:{ all -> 0x004d }
            goto L_0x0051
        L_0x004d:
            r0 = move-exception
            r6.addSuppressed(r0)
        L_0x0051:
            throw r1
        L_0x0052:
            java.util.ArrayList<java.io.InputStream> r6 = r6.deferredBlockStreams
            java.lang.Object r6 = r6.get(r4)
            java.io.InputStream r6 = (java.io.InputStream) r6
            return r6
        L_0x005b:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "No current 7z entry (call getNextEntry() first)."
            r6.<init>(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.sevenz.SevenZFile.getCurrentStream():java.io.InputStream");
    }

    public InputStream getInputStream(SevenZArchiveEntry sevenZArchiveEntry) throws IOException {
        int i = 0;
        while (true) {
            if (i >= this.archive.files.length) {
                i = -1;
                break;
            } else if (sevenZArchiveEntry == this.archive.files[i]) {
                break;
            } else {
                i++;
            }
        }
        if (i >= 0) {
            buildDecodingStream(i, true);
            this.currentEntryIndex = i;
            this.currentFolderIndex = this.archive.streamMap.fileFolderIndex[i];
            return getCurrentStream();
        }
        throw new IllegalArgumentException("Can not find " + sevenZArchiveEntry.getName() + " in " + this.fileName);
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        int read = getCurrentStream().read(bArr, i, i2);
        if (read > 0) {
            this.uncompressedBytesReadFromCurrentEntry += (long) read;
        }
        return read;
    }

    public InputStreamStatistics getStatisticsForCurrentEntry() {
        return new InputStreamStatistics() {
            public long getCompressedCount() {
                return SevenZFile.this.compressedBytesReadFromCurrentEntry;
            }

            public long getUncompressedCount() {
                return SevenZFile.this.uncompressedBytesReadFromCurrentEntry;
            }
        };
    }

    private static long readUint64(ByteBuffer byteBuffer) throws IOException {
        long unsignedByte = (long) getUnsignedByte(byteBuffer);
        int i = 128;
        long j = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            if ((((long) i) & unsignedByte) == 0) {
                return ((unsignedByte & ((long) (i - 1))) << (i2 * 8)) | j;
            }
            j |= ((long) getUnsignedByte(byteBuffer)) << (i2 * 8);
            i >>>= 1;
        }
        return j;
    }

    private static char getChar(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.remaining() >= 2) {
            return byteBuffer.getChar();
        }
        throw new EOFException();
    }

    private static int getInt(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.remaining() >= 4) {
            return byteBuffer.getInt();
        }
        throw new EOFException();
    }

    private static long getLong(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.remaining() >= 8) {
            return byteBuffer.getLong();
        }
        throw new EOFException();
    }

    private static void get(ByteBuffer byteBuffer, byte[] bArr) throws IOException {
        if (byteBuffer.remaining() >= bArr.length) {
            byteBuffer.get(bArr);
            return;
        }
        throw new EOFException();
    }

    private static int getUnsignedByte(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.hasRemaining()) {
            return byteBuffer.get() & UByte.MAX_VALUE;
        }
        throw new EOFException();
    }

    public static boolean matches(byte[] bArr, int i) {
        if (i < sevenZSignature.length) {
            return false;
        }
        int i2 = 0;
        while (true) {
            byte[] bArr2 = sevenZSignature;
            if (i2 >= bArr2.length) {
                return true;
            }
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
            i2++;
        }
    }

    private static long skipBytesFully(ByteBuffer byteBuffer, long j) throws IOException {
        if (j < 1) {
            return 0;
        }
        int position = byteBuffer.position();
        long remaining = (long) byteBuffer.remaining();
        if (remaining < j) {
            j = remaining;
        }
        byteBuffer.position(position + ((int) j));
        return j;
    }

    private void readFully(ByteBuffer byteBuffer) throws IOException {
        byteBuffer.rewind();
        IOUtils.readFully((ReadableByteChannel) this.channel, byteBuffer);
        byteBuffer.flip();
    }

    public String toString() {
        return this.archive.toString();
    }

    public String getDefaultName() {
        if (DEFAULT_FILE_NAME.equals(this.fileName) || this.fileName == null) {
            return null;
        }
        String name = new File(this.fileName).getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf > 0) {
            return name.substring(0, lastIndexOf);
        }
        return name + "~";
    }

    private static byte[] utf16Decode(char[] cArr) throws IOException {
        if (cArr == null) {
            return null;
        }
        ByteBuffer encode = PASSWORD_ENCODER.encode(CharBuffer.wrap(cArr));
        if (encode.hasArray()) {
            return encode.array();
        }
        byte[] bArr = new byte[encode.remaining()];
        encode.get(bArr);
        return bArr;
    }

    private static int assertFitsIntoNonNegativeInt(String str, long j) throws IOException {
        if (j <= 2147483647L && j >= 0) {
            return (int) j;
        }
        throw new IOException("Cannot handle " + str + " " + j);
    }

    private static class ArchiveStatistics {
        /* access modifiers changed from: private */
        public BitSet folderHasCrc;
        /* access modifiers changed from: private */
        public long numberOfCoders;
        /* access modifiers changed from: private */
        public int numberOfEntries;
        /* access modifiers changed from: private */
        public int numberOfEntriesWithStream;
        /* access modifiers changed from: private */
        public int numberOfFolders;
        /* access modifiers changed from: private */
        public long numberOfInStreams;
        /* access modifiers changed from: private */
        public long numberOfOutStreams;
        /* access modifiers changed from: private */
        public int numberOfPackedStreams;
        /* access modifiers changed from: private */
        public long numberOfUnpackSubStreams;

        private long bindPairSize() {
            return 16;
        }

        private long coderSize() {
            return 22;
        }

        private long entrySize() {
            return 100;
        }

        private long folderSize() {
            return 30;
        }

        private ArchiveStatistics() {
        }

        public String toString() {
            return "Archive with " + this.numberOfEntries + " entries in " + this.numberOfFolders + " folders. Estimated size " + (estimateSize() / 1024) + " kB.";
        }

        /* access modifiers changed from: package-private */
        public long estimateSize() {
            int i = this.numberOfPackedStreams;
            long folderSize = (((long) i) * 16) + ((long) (i / 8)) + (((long) this.numberOfFolders) * folderSize()) + (this.numberOfCoders * coderSize()) + ((this.numberOfOutStreams - ((long) this.numberOfFolders)) * bindPairSize());
            long j = this.numberOfInStreams;
            long j2 = this.numberOfOutStreams;
            return (folderSize + (((j - j2) + ((long) this.numberOfFolders)) * 8) + (j2 * 8) + (((long) this.numberOfEntries) * entrySize()) + streamMapSize()) * 2;
        }

        /* access modifiers changed from: package-private */
        public void assertValidity(int i) throws IOException {
            int i2 = this.numberOfEntriesWithStream;
            if (i2 > 0 && this.numberOfFolders == 0) {
                throw new IOException("archive with entries but no folders");
            } else if (((long) i2) <= this.numberOfUnpackSubStreams) {
                long estimateSize = estimateSize() / 1024;
                if (((long) i) < estimateSize) {
                    throw new MemoryLimitException(estimateSize, i);
                }
            } else {
                throw new IOException("archive doesn't contain enough substreams for entries");
            }
        }

        private long streamMapSize() {
            return (long) ((this.numberOfFolders * 8) + (this.numberOfPackedStreams * 8) + (this.numberOfEntries * 4));
        }
    }
}
