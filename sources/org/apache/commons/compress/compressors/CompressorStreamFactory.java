package org.apache.commons.compress.compressors;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.compress.compressors.brotli.BrotliCompressorInputStream;
import org.apache.commons.compress.compressors.brotli.BrotliUtils;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.apache.commons.compress.compressors.deflate.DeflateCompressorInputStream;
import org.apache.commons.compress.compressors.deflate.DeflateCompressorOutputStream;
import org.apache.commons.compress.compressors.deflate64.Deflate64CompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.compress.compressors.lz4.BlockLZ4CompressorInputStream;
import org.apache.commons.compress.compressors.lz4.BlockLZ4CompressorOutputStream;
import org.apache.commons.compress.compressors.lz4.FramedLZ4CompressorInputStream;
import org.apache.commons.compress.compressors.lz4.FramedLZ4CompressorOutputStream;
import org.apache.commons.compress.compressors.lzma.LZMACompressorInputStream;
import org.apache.commons.compress.compressors.lzma.LZMACompressorOutputStream;
import org.apache.commons.compress.compressors.lzma.LZMAUtils;
import org.apache.commons.compress.compressors.pack200.Pack200CompressorInputStream;
import org.apache.commons.compress.compressors.pack200.Pack200CompressorOutputStream;
import org.apache.commons.compress.compressors.snappy.FramedSnappyCompressorInputStream;
import org.apache.commons.compress.compressors.snappy.FramedSnappyCompressorOutputStream;
import org.apache.commons.compress.compressors.snappy.SnappyCompressorInputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorOutputStream;
import org.apache.commons.compress.compressors.xz.XZUtils;
import org.apache.commons.compress.compressors.z.ZCompressorInputStream;
import org.apache.commons.compress.compressors.zstandard.ZstdCompressorInputStream;
import org.apache.commons.compress.compressors.zstandard.ZstdCompressorOutputStream;
import org.apache.commons.compress.compressors.zstandard.ZstdUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.compress.utils.ServiceLoaderIterator;
import org.apache.commons.compress.utils.Sets;

public class CompressorStreamFactory implements CompressorStreamProvider {
    public static final String BROTLI = "br";
    public static final String BZIP2 = "bzip2";
    public static final String DEFLATE = "deflate";
    public static final String DEFLATE64 = "deflate64";
    public static final String GZIP = "gz";
    public static final String LZ4_BLOCK = "lz4-block";
    public static final String LZ4_FRAMED = "lz4-framed";
    public static final String LZMA = "lzma";
    public static final String PACK200 = "pack200";
    private static final CompressorStreamFactory SINGLETON = new CompressorStreamFactory();
    public static final String SNAPPY_FRAMED = "snappy-framed";
    public static final String SNAPPY_RAW = "snappy-raw";
    public static final String XZ = "xz";
    private static final String YOU_NEED_BROTLI_DEC = youNeed("Google Brotli Dec", "https://github.com/google/brotli/");
    private static final String YOU_NEED_XZ_JAVA = youNeed("XZ for Java", "https://tukaani.org/xz/java.html");
    private static final String YOU_NEED_ZSTD_JNI = youNeed("Zstd JNI", "https://github.com/luben/zstd-jni");
    public static final String Z = "z";
    public static final String ZSTANDARD = "zstd";
    private SortedMap<String, CompressorStreamProvider> compressorInputStreamProviders;
    private SortedMap<String, CompressorStreamProvider> compressorOutputStreamProviders;
    private volatile boolean decompressConcatenated;
    private final Boolean decompressUntilEOF;
    private final int memoryLimitInKb;

    public static String getBrotli() {
        return BROTLI;
    }

    public static String getBzip2() {
        return BZIP2;
    }

    public static String getDeflate() {
        return DEFLATE;
    }

    public static String getDeflate64() {
        return DEFLATE64;
    }

    public static String getGzip() {
        return GZIP;
    }

    public static String getLZ4Block() {
        return LZ4_BLOCK;
    }

    public static String getLZ4Framed() {
        return LZ4_FRAMED;
    }

    public static String getLzma() {
        return LZMA;
    }

    public static String getPack200() {
        return PACK200;
    }

    public static String getSnappyFramed() {
        return SNAPPY_FRAMED;
    }

    public static String getSnappyRaw() {
        return SNAPPY_RAW;
    }

    public static String getXz() {
        return XZ;
    }

    public static String getZ() {
        return Z;
    }

    public static String getZstandard() {
        return ZSTANDARD;
    }

    private static String youNeed(String str, String str2) {
        return " In addition to Apache Commons Compress you need the " + str + " library - see " + str2;
    }

    public static SortedMap<String, CompressorStreamProvider> findAvailableCompressorInputStreamProviders() {
        return (SortedMap) AccessController.doPrivileged(new CompressorStreamFactory$$ExternalSyntheticLambda0());
    }

    static /* synthetic */ SortedMap lambda$findAvailableCompressorInputStreamProviders$0() {
        TreeMap treeMap = new TreeMap();
        CompressorStreamFactory compressorStreamFactory = SINGLETON;
        putAll(compressorStreamFactory.getInputStreamCompressorNames(), compressorStreamFactory, treeMap);
        Iterator<CompressorStreamProvider> it = findCompressorStreamProviders().iterator();
        while (it.hasNext()) {
            CompressorStreamProvider next = it.next();
            putAll(next.getInputStreamCompressorNames(), next, treeMap);
        }
        return treeMap;
    }

    public static SortedMap<String, CompressorStreamProvider> findAvailableCompressorOutputStreamProviders() {
        return (SortedMap) AccessController.doPrivileged(new CompressorStreamFactory$$ExternalSyntheticLambda1());
    }

    static /* synthetic */ SortedMap lambda$findAvailableCompressorOutputStreamProviders$1() {
        TreeMap treeMap = new TreeMap();
        CompressorStreamFactory compressorStreamFactory = SINGLETON;
        putAll(compressorStreamFactory.getOutputStreamCompressorNames(), compressorStreamFactory, treeMap);
        Iterator<CompressorStreamProvider> it = findCompressorStreamProviders().iterator();
        while (it.hasNext()) {
            CompressorStreamProvider next = it.next();
            putAll(next.getOutputStreamCompressorNames(), next, treeMap);
        }
        return treeMap;
    }

    private static ArrayList<CompressorStreamProvider> findCompressorStreamProviders() {
        return Lists.newArrayList(serviceLoaderIterator());
    }

    public static CompressorStreamFactory getSingleton() {
        return SINGLETON;
    }

    static void putAll(Set<String> set, CompressorStreamProvider compressorStreamProvider, TreeMap<String, CompressorStreamProvider> treeMap) {
        for (String key : set) {
            treeMap.put(toKey(key), compressorStreamProvider);
        }
    }

    private static Iterator<CompressorStreamProvider> serviceLoaderIterator() {
        return new ServiceLoaderIterator(CompressorStreamProvider.class);
    }

    private static String toKey(String str) {
        return str.toUpperCase(Locale.ROOT);
    }

    public CompressorStreamFactory() {
        this.decompressUntilEOF = null;
        this.memoryLimitInKb = -1;
    }

    public CompressorStreamFactory(boolean z, int i) {
        this.decompressUntilEOF = Boolean.valueOf(z);
        this.decompressConcatenated = z;
        this.memoryLimitInKb = i;
    }

    public CompressorStreamFactory(boolean z) {
        this(z, -1);
    }

    public static String detect(InputStream inputStream) throws CompressorException {
        if (inputStream == null) {
            throw new IllegalArgumentException("Stream must not be null.");
        } else if (inputStream.markSupported()) {
            byte[] bArr = new byte[12];
            inputStream.mark(12);
            try {
                int readFully = IOUtils.readFully(inputStream, bArr);
                inputStream.reset();
                if (BZip2CompressorInputStream.matches(bArr, readFully)) {
                    return BZIP2;
                }
                if (GzipCompressorInputStream.matches(bArr, readFully)) {
                    return GZIP;
                }
                if (Pack200CompressorInputStream.matches(bArr, readFully)) {
                    return PACK200;
                }
                if (FramedSnappyCompressorInputStream.matches(bArr, readFully)) {
                    return SNAPPY_FRAMED;
                }
                if (ZCompressorInputStream.matches(bArr, readFully)) {
                    return Z;
                }
                if (DeflateCompressorInputStream.matches(bArr, readFully)) {
                    return DEFLATE;
                }
                if (XZUtils.matches(bArr, readFully)) {
                    return XZ;
                }
                if (LZMAUtils.matches(bArr, readFully)) {
                    return LZMA;
                }
                if (FramedLZ4CompressorInputStream.matches(bArr, readFully)) {
                    return LZ4_FRAMED;
                }
                if (ZstdUtils.matches(bArr, readFully)) {
                    return ZSTANDARD;
                }
                throw new CompressorException("No Compressor found for the stream signature.");
            } catch (IOException e) {
                throw new CompressorException("IOException while reading signature.", e);
            }
        } else {
            throw new IllegalArgumentException("Mark is not supported.");
        }
    }

    public CompressorInputStream createCompressorInputStream(InputStream inputStream) throws CompressorException {
        return createCompressorInputStream(detect(inputStream), inputStream);
    }

    public CompressorInputStream createCompressorInputStream(String str, InputStream inputStream) throws CompressorException {
        return createCompressorInputStream(str, inputStream, this.decompressConcatenated);
    }

    public CompressorInputStream createCompressorInputStream(String str, InputStream inputStream, boolean z) throws CompressorException {
        if (str == null || inputStream == null) {
            throw new IllegalArgumentException("Compressor name and stream must not be null.");
        }
        try {
            if (GZIP.equalsIgnoreCase(str)) {
                return new GzipCompressorInputStream(inputStream, z);
            }
            if (BZIP2.equalsIgnoreCase(str)) {
                return new BZip2CompressorInputStream(inputStream, z);
            }
            if (BROTLI.equalsIgnoreCase(str)) {
                if (BrotliUtils.isBrotliCompressionAvailable()) {
                    return new BrotliCompressorInputStream(inputStream);
                }
                throw new CompressorException("Brotli compression is not available." + YOU_NEED_BROTLI_DEC);
            } else if (XZ.equalsIgnoreCase(str)) {
                if (XZUtils.isXZCompressionAvailable()) {
                    return new XZCompressorInputStream(inputStream, z, this.memoryLimitInKb);
                }
                throw new CompressorException("XZ compression is not available." + YOU_NEED_XZ_JAVA);
            } else if (ZSTANDARD.equalsIgnoreCase(str)) {
                if (ZstdUtils.isZstdCompressionAvailable()) {
                    return new ZstdCompressorInputStream(inputStream);
                }
                throw new CompressorException("Zstandard compression is not available." + YOU_NEED_ZSTD_JNI);
            } else if (LZMA.equalsIgnoreCase(str)) {
                if (LZMAUtils.isLZMACompressionAvailable()) {
                    return new LZMACompressorInputStream(inputStream, this.memoryLimitInKb);
                }
                throw new CompressorException("LZMA compression is not available" + YOU_NEED_XZ_JAVA);
            } else if (PACK200.equalsIgnoreCase(str)) {
                return new Pack200CompressorInputStream(inputStream);
            } else {
                if (SNAPPY_RAW.equalsIgnoreCase(str)) {
                    return new SnappyCompressorInputStream(inputStream);
                }
                if (SNAPPY_FRAMED.equalsIgnoreCase(str)) {
                    return new FramedSnappyCompressorInputStream(inputStream);
                }
                if (Z.equalsIgnoreCase(str)) {
                    return new ZCompressorInputStream(inputStream, this.memoryLimitInKb);
                }
                if (DEFLATE.equalsIgnoreCase(str)) {
                    return new DeflateCompressorInputStream(inputStream);
                }
                if (DEFLATE64.equalsIgnoreCase(str)) {
                    return new Deflate64CompressorInputStream(inputStream);
                }
                if (LZ4_BLOCK.equalsIgnoreCase(str)) {
                    return new BlockLZ4CompressorInputStream(inputStream);
                }
                if (LZ4_FRAMED.equalsIgnoreCase(str)) {
                    return new FramedLZ4CompressorInputStream(inputStream, z);
                }
                CompressorStreamProvider compressorStreamProvider = (CompressorStreamProvider) getCompressorInputStreamProviders().get(toKey(str));
                if (compressorStreamProvider != null) {
                    return compressorStreamProvider.createCompressorInputStream(str, inputStream, z);
                }
                throw new CompressorException("Compressor: " + str + " not found.");
            }
        } catch (IOException e) {
            throw new CompressorException("Could not create CompressorInputStream.", e);
        }
    }

    public CompressorOutputStream createCompressorOutputStream(String str, OutputStream outputStream) throws CompressorException {
        if (str == null || outputStream == null) {
            throw new IllegalArgumentException("Compressor name and stream must not be null.");
        }
        try {
            if (GZIP.equalsIgnoreCase(str)) {
                return new GzipCompressorOutputStream(outputStream);
            }
            if (BZIP2.equalsIgnoreCase(str)) {
                return new BZip2CompressorOutputStream(outputStream);
            }
            if (XZ.equalsIgnoreCase(str)) {
                return new XZCompressorOutputStream(outputStream);
            }
            if (PACK200.equalsIgnoreCase(str)) {
                return new Pack200CompressorOutputStream(outputStream);
            }
            if (LZMA.equalsIgnoreCase(str)) {
                return new LZMACompressorOutputStream(outputStream);
            }
            if (DEFLATE.equalsIgnoreCase(str)) {
                return new DeflateCompressorOutputStream(outputStream);
            }
            if (SNAPPY_FRAMED.equalsIgnoreCase(str)) {
                return new FramedSnappyCompressorOutputStream(outputStream);
            }
            if (LZ4_BLOCK.equalsIgnoreCase(str)) {
                return new BlockLZ4CompressorOutputStream(outputStream);
            }
            if (LZ4_FRAMED.equalsIgnoreCase(str)) {
                return new FramedLZ4CompressorOutputStream(outputStream);
            }
            if (ZSTANDARD.equalsIgnoreCase(str)) {
                return new ZstdCompressorOutputStream(outputStream);
            }
            CompressorStreamProvider compressorStreamProvider = (CompressorStreamProvider) getCompressorOutputStreamProviders().get(toKey(str));
            if (compressorStreamProvider != null) {
                return compressorStreamProvider.createCompressorOutputStream(str, outputStream);
            }
            throw new CompressorException("Compressor: " + str + " not found.");
        } catch (IOException e) {
            throw new CompressorException("Could not create CompressorOutputStream", e);
        }
    }

    public SortedMap<String, CompressorStreamProvider> getCompressorInputStreamProviders() {
        if (this.compressorInputStreamProviders == null) {
            this.compressorInputStreamProviders = Collections.unmodifiableSortedMap(findAvailableCompressorInputStreamProviders());
        }
        return this.compressorInputStreamProviders;
    }

    public SortedMap<String, CompressorStreamProvider> getCompressorOutputStreamProviders() {
        if (this.compressorOutputStreamProviders == null) {
            this.compressorOutputStreamProviders = Collections.unmodifiableSortedMap(findAvailableCompressorOutputStreamProviders());
        }
        return this.compressorOutputStreamProviders;
    }

    /* access modifiers changed from: package-private */
    public boolean getDecompressConcatenated() {
        return this.decompressConcatenated;
    }

    public Boolean getDecompressUntilEOF() {
        return this.decompressUntilEOF;
    }

    public Set<String> getInputStreamCompressorNames() {
        return Sets.newHashSet(GZIP, BROTLI, BZIP2, XZ, LZMA, PACK200, DEFLATE, SNAPPY_RAW, SNAPPY_FRAMED, Z, LZ4_BLOCK, LZ4_FRAMED, ZSTANDARD, DEFLATE64);
    }

    public Set<String> getOutputStreamCompressorNames() {
        return Sets.newHashSet(GZIP, BZIP2, XZ, LZMA, PACK200, DEFLATE, SNAPPY_FRAMED, LZ4_BLOCK, LZ4_FRAMED, ZSTANDARD);
    }

    @Deprecated
    public void setDecompressConcatenated(boolean z) {
        if (this.decompressUntilEOF == null) {
            this.decompressConcatenated = z;
            return;
        }
        throw new IllegalStateException("Cannot override the setting defined by the constructor");
    }
}
