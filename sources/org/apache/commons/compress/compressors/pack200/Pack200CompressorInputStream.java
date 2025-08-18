package org.apache.commons.compress.compressors.pack200;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;

public class Pack200CompressorInputStream extends CompressorInputStream {
    private static final byte[] CAFE_DOOD;
    private static final int SIG_LENGTH;
    private final InputStream originalInput;
    private final StreamBridge streamBridge;

    public Pack200CompressorInputStream(InputStream inputStream) throws IOException {
        this(inputStream, Pack200Strategy.IN_MEMORY);
    }

    public Pack200CompressorInputStream(InputStream inputStream, Pack200Strategy pack200Strategy) throws IOException {
        this(inputStream, (File) null, pack200Strategy, (Map<String, String>) null);
    }

    public Pack200CompressorInputStream(InputStream inputStream, Map<String, String> map) throws IOException {
        this(inputStream, Pack200Strategy.IN_MEMORY, map);
    }

    public Pack200CompressorInputStream(InputStream inputStream, Pack200Strategy pack200Strategy, Map<String, String> map) throws IOException {
        this(inputStream, (File) null, pack200Strategy, map);
    }

    public Pack200CompressorInputStream(File file) throws IOException {
        this(file, Pack200Strategy.IN_MEMORY);
    }

    public Pack200CompressorInputStream(File file, Pack200Strategy pack200Strategy) throws IOException {
        this((InputStream) null, file, pack200Strategy, (Map<String, String>) null);
    }

    public Pack200CompressorInputStream(File file, Map<String, String> map) throws IOException {
        this(file, Pack200Strategy.IN_MEMORY, map);
    }

    public Pack200CompressorInputStream(File file, Pack200Strategy pack200Strategy, Map<String, String> map) throws IOException {
        this((InputStream) null, file, pack200Strategy, map);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0031, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0036, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0037, code lost:
        r2.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003a, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private Pack200CompressorInputStream(java.io.InputStream r2, java.io.File r3, org.apache.commons.compress.compressors.pack200.Pack200Strategy r4, java.util.Map<java.lang.String, java.lang.String> r5) throws java.io.IOException {
        /*
            r1 = this;
            r1.<init>()
            r1.originalInput = r2
            org.apache.commons.compress.compressors.pack200.StreamBridge r4 = r4.newStreamBridge()
            r1.streamBridge = r4
            java.util.jar.JarOutputStream r1 = new java.util.jar.JarOutputStream
            r1.<init>(r4)
            org.apache.commons.compress.java.util.jar.Pack200$Unpacker r4 = org.apache.commons.compress.java.util.jar.Pack200.newUnpacker()     // Catch:{ all -> 0x002f }
            if (r5 == 0) goto L_0x001d
            java.util.SortedMap r0 = r4.properties()     // Catch:{ all -> 0x002f }
            r0.putAll(r5)     // Catch:{ all -> 0x002f }
        L_0x001d:
            if (r3 != 0) goto L_0x0028
            org.apache.commons.compress.utils.CloseShieldFilterInputStream r3 = new org.apache.commons.compress.utils.CloseShieldFilterInputStream     // Catch:{ all -> 0x002f }
            r3.<init>(r2)     // Catch:{ all -> 0x002f }
            r4.unpack((java.io.InputStream) r3, (java.util.jar.JarOutputStream) r1)     // Catch:{ all -> 0x002f }
            goto L_0x002b
        L_0x0028:
            r4.unpack((java.io.File) r3, (java.util.jar.JarOutputStream) r1)     // Catch:{ all -> 0x002f }
        L_0x002b:
            r1.close()
            return
        L_0x002f:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0031 }
        L_0x0031:
            r3 = move-exception
            r1.close()     // Catch:{ all -> 0x0036 }
            goto L_0x003a
        L_0x0036:
            r1 = move-exception
            r2.addSuppressed(r1)
        L_0x003a:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.compressors.pack200.Pack200CompressorInputStream.<init>(java.io.InputStream, java.io.File, org.apache.commons.compress.compressors.pack200.Pack200Strategy, java.util.Map):void");
    }

    public int read() throws IOException {
        return this.streamBridge.getInput().read();
    }

    public int read(byte[] bArr) throws IOException {
        return this.streamBridge.getInput().read(bArr);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.streamBridge.getInput().read(bArr, i, i2);
    }

    public int available() throws IOException {
        return this.streamBridge.getInput().available();
    }

    public boolean markSupported() {
        try {
            return this.streamBridge.getInput().markSupported();
        } catch (IOException unused) {
            return false;
        }
    }

    public synchronized void mark(int i) {
        try {
            this.streamBridge.getInput().mark(i);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void reset() throws IOException {
        this.streamBridge.getInput().reset();
    }

    public long skip(long j) throws IOException {
        return IOUtils.skip(this.streamBridge.getInput(), j);
    }

    public void close() throws IOException {
        try {
            this.streamBridge.stop();
        } finally {
            InputStream inputStream = this.originalInput;
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    static {
        byte[] bArr = {-54, -2, -48, 13};
        CAFE_DOOD = bArr;
        SIG_LENGTH = bArr.length;
    }

    public static boolean matches(byte[] bArr, int i) {
        if (i < SIG_LENGTH) {
            return false;
        }
        for (int i2 = 0; i2 < SIG_LENGTH; i2++) {
            if (bArr[i2] != CAFE_DOOD[i2]) {
                return false;
            }
        }
        return true;
    }
}
