package org.apache.poi.poifs.property;

import java.util.List;

final class PropertyFactory {
    private PropertyFactory() {
    }

    static void convertToProperties(byte[] bArr, List<Property> list) {
        int length = bArr.length / 128;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            byte b = bArr[i + 66];
            if (b == 1) {
                list.add(new DirectoryProperty(list.size(), bArr, i));
            } else if (b == 2) {
                list.add(new DocumentProperty(list.size(), bArr, i));
            } else if (b != 5) {
                list.add((Object) null);
            } else {
                list.add(new RootProperty(list.size(), bArr, i));
            }
            i += 128;
        }
    }
}
