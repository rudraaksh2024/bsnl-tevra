package org.apache.poi.sl.image;

import java.awt.Dimension;
import java.awt.Rectangle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LocaleUtil;

@Internal
public class ImageHeaderEMF {
    private static final String EMF_SIGNATURE = " EMF";
    private static final Logger LOG = LogManager.getLogger((Class<?>) ImageHeaderEMF.class);
    private final Rectangle deviceBounds;

    public ImageHeaderEMF(byte[] bArr, int i) {
        int uInt = (int) LittleEndian.getUInt(bArr, i);
        int i2 = i + 4;
        int i3 = 0;
        if (uInt != 1) {
            LOG.atWarn().log("Invalid EMF picture - invalid type");
            this.deviceBounds = new Rectangle(0, 0, 200, 200);
            return;
        }
        int i4 = i2 + 4;
        int i5 = LittleEndian.getInt(bArr, i4);
        int i6 = i4 + 4;
        int i7 = LittleEndian.getInt(bArr, i6);
        int i8 = i6 + 4;
        int i9 = LittleEndian.getInt(bArr, i8);
        int i10 = i8 + 4;
        int i11 = LittleEndian.getInt(bArr, i10);
        int i12 = i10 + 4;
        int i13 = i9 - i5;
        int i14 = i11 - i7;
        this.deviceBounds = new Rectangle(i5, i7, i13 == -1 ? 0 : i13, i14 != -1 ? i14 : i3);
        if (!EMF_SIGNATURE.equals(new String(bArr, i12 + 16, 4, LocaleUtil.CHARSET_1252))) {
            LOG.atWarn().log("Invalid EMF picture - invalid signature");
        }
    }

    public Dimension getSize() {
        return this.deviceBounds.getSize();
    }

    public Rectangle getBounds() {
        return this.deviceBounds;
    }
}
