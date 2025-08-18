package org.apache.poi.sl.image;

import java.util.Arrays;
import org.apache.poi.poifs.filesystem.FileMagic;

public final class ImageHeaderPNG {
    private static final int MAGIC_OFFSET = 16;
    private final byte[] data;

    public ImageHeaderPNG(byte[] bArr) {
        this.data = bArr;
    }

    public byte[] extractPNG() {
        byte[] bArr = this.data;
        if (bArr.length >= 16) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 16, bArr.length);
            if (FileMagic.valueOf(copyOfRange) == FileMagic.PNG) {
                return copyOfRange;
            }
        }
        return this.data;
    }
}
