package org.apache.poi.xssf.usermodel;

import java.lang.reflect.Array;
import java.util.List;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColors;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRgbColor;

public class CustomIndexedColorMap implements IndexedColorMap {
    private final byte[][] colorIndex;

    private CustomIndexedColorMap(byte[][] bArr) {
        this.colorIndex = bArr;
    }

    public byte[] getRGB(int i) {
        byte[][] bArr = this.colorIndex;
        if (bArr == null || i < 0 || i >= bArr.length) {
            return null;
        }
        return bArr[i];
    }

    public static CustomIndexedColorMap fromColors(CTColors cTColors) {
        if (cTColors == null || !cTColors.isSetIndexedColors()) {
            return null;
        }
        List<CTRgbColor> rgbColorList = cTColors.getIndexedColors().getRgbColorList();
        int size = rgbColorList.size();
        int[] iArr = new int[2];
        iArr[1] = 3;
        iArr[0] = size;
        byte[][] bArr = (byte[][]) Array.newInstance(Byte.TYPE, iArr);
        for (int i = 0; i < rgbColorList.size(); i++) {
            bArr[i] = rgbColorList.get(i).getRgb();
        }
        return new CustomIndexedColorMap(bArr);
    }
}
