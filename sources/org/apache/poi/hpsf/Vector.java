package org.apache.poi.hpsf;

import java.util.ArrayList;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianByteArrayInputStream;

@Internal
public class Vector {
    private final short _type;
    private TypedPropertyValue[] _values;

    public Vector(short s) {
        this._type = s;
    }

    public void read(LittleEndianByteArrayInputStream littleEndianByteArrayInputStream) {
        long readUInt = littleEndianByteArrayInputStream.readUInt();
        if (readUInt <= 2147483647L) {
            int i = (int) readUInt;
            ArrayList arrayList = new ArrayList();
            short s = this._type;
            if (s == 12) {
                s = 0;
            }
            for (int i2 = 0; i2 < i; i2++) {
                TypedPropertyValue typedPropertyValue = new TypedPropertyValue(s, (Object) null);
                if (s == 0) {
                    typedPropertyValue.read(littleEndianByteArrayInputStream);
                } else {
                    typedPropertyValue.readValue(littleEndianByteArrayInputStream);
                }
                arrayList.add(typedPropertyValue);
            }
            this._values = (TypedPropertyValue[]) arrayList.toArray(new TypedPropertyValue[0]);
            return;
        }
        throw new UnsupportedOperationException("Vector is too long -- " + readUInt);
    }

    public TypedPropertyValue[] getValues() {
        return this._values;
    }
}
