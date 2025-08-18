package org.apache.commons.compress.archivers.sevenz;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

class Folder {
    static final Folder[] EMPTY_FOLDER_ARRAY = new Folder[0];
    BindPair[] bindPairs;
    Coder[] coders;
    long crc;
    boolean hasCrc;
    int numUnpackSubStreams;
    long[] packedStreams;
    long totalInputStreams;
    long totalOutputStreams;
    long[] unpackSizes;

    Folder() {
    }

    /* access modifiers changed from: package-private */
    public Iterable<Coder> getOrderedCoders() throws IOException {
        Coder[] coderArr;
        long[] jArr = this.packedStreams;
        if (jArr == null || (coderArr = this.coders) == null || jArr.length == 0 || coderArr.length == 0) {
            return Collections.emptyList();
        }
        LinkedList linkedList = new LinkedList();
        long j = this.packedStreams[0];
        loop0:
        while (true) {
            int i = (int) j;
            while (i >= 0) {
                Coder[] coderArr2 = this.coders;
                if (i >= coderArr2.length) {
                    break loop0;
                } else if (!linkedList.contains(coderArr2[i])) {
                    linkedList.addLast(this.coders[i]);
                    int findBindPairForOutStream = findBindPairForOutStream(i);
                    if (findBindPairForOutStream != -1) {
                        j = this.bindPairs[findBindPairForOutStream].inIndex;
                    } else {
                        i = -1;
                    }
                } else {
                    throw new IOException("folder uses the same coder more than once in coder chain");
                }
            }
            break loop0;
        }
        return linkedList;
    }

    /* access modifiers changed from: package-private */
    public int findBindPairForInStream(int i) {
        if (this.bindPairs == null) {
            return -1;
        }
        int i2 = 0;
        while (true) {
            BindPair[] bindPairArr = this.bindPairs;
            if (i2 >= bindPairArr.length) {
                return -1;
            }
            if (bindPairArr[i2].inIndex == ((long) i)) {
                return i2;
            }
            i2++;
        }
    }

    /* access modifiers changed from: package-private */
    public int findBindPairForOutStream(int i) {
        if (this.bindPairs == null) {
            return -1;
        }
        int i2 = 0;
        while (true) {
            BindPair[] bindPairArr = this.bindPairs;
            if (i2 >= bindPairArr.length) {
                return -1;
            }
            if (bindPairArr[i2].outIndex == ((long) i)) {
                return i2;
            }
            i2++;
        }
    }

    /* access modifiers changed from: package-private */
    public long getUnpackSize() {
        long j = this.totalOutputStreams;
        if (j == 0) {
            return 0;
        }
        for (int i = ((int) j) - 1; i >= 0; i--) {
            if (findBindPairForOutStream(i) < 0) {
                return this.unpackSizes[i];
            }
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public long getUnpackSizeForCoder(Coder coder) {
        if (this.coders == null) {
            return 0;
        }
        int i = 0;
        while (true) {
            Coder[] coderArr = this.coders;
            if (i >= coderArr.length) {
                return 0;
            }
            if (coderArr[i] == coder) {
                return this.unpackSizes[i];
            }
            i++;
        }
    }

    public String toString() {
        return "Folder with " + this.coders.length + " coders, " + this.totalInputStreams + " input streams, " + this.totalOutputStreams + " output streams, " + this.bindPairs.length + " bind pairs, " + this.packedStreams.length + " packed streams, " + this.unpackSizes.length + " unpack sizes, " + (this.hasCrc ? "with CRC " + this.crc : "without CRC") + " and " + this.numUnpackSubStreams + " unpack streams";
    }
}
