package org.apache.poi.hpsf;

import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianByteArrayInputStream;

@Internal
public class Array {
    private final ArrayHeader _header = new ArrayHeader();
    private TypedPropertyValue[] _values;

    static class ArrayDimension {
        private int _indexOffset;
        /* access modifiers changed from: private */
        public long _size;

        ArrayDimension() {
        }

        /* access modifiers changed from: package-private */
        public void read(LittleEndianByteArrayInputStream littleEndianByteArrayInputStream) {
            this._size = littleEndianByteArrayInputStream.readUInt();
            this._indexOffset = littleEndianByteArrayInputStream.readInt();
        }
    }

    static class ArrayHeader {
        private ArrayDimension[] _dimensions;
        /* access modifiers changed from: private */
        public int _type;

        ArrayHeader() {
        }

        /* access modifiers changed from: package-private */
        public void read(LittleEndianByteArrayInputStream littleEndianByteArrayInputStream) {
            this._type = littleEndianByteArrayInputStream.readInt();
            long readUInt = littleEndianByteArrayInputStream.readUInt();
            if (1 > readUInt || readUInt > 31) {
                throw new IllegalPropertySetDataException("Array dimension number " + readUInt + " is not in [1; 31] range");
            }
            int i = (int) readUInt;
            this._dimensions = new ArrayDimension[i];
            for (int i2 = 0; i2 < i; i2++) {
                ArrayDimension arrayDimension = new ArrayDimension();
                arrayDimension.read(littleEndianByteArrayInputStream);
                this._dimensions[i2] = arrayDimension;
            }
        }

        /* access modifiers changed from: package-private */
        public long getNumberOfScalarValues() {
            long j = 1;
            for (ArrayDimension access$000 : this._dimensions) {
                j *= access$000._size;
            }
            return j;
        }

        /* access modifiers changed from: package-private */
        public int getType() {
            return this._type;
        }
    }

    public void read(LittleEndianByteArrayInputStream littleEndianByteArrayInputStream) {
        this._header.read(littleEndianByteArrayInputStream);
        long numberOfScalarValues = this._header.getNumberOfScalarValues();
        if (numberOfScalarValues <= 2147483647L) {
            int i = (int) numberOfScalarValues;
            this._values = new TypedPropertyValue[i];
            int access$100 = this._header._type == 12 ? 0 : this._header._type;
            for (int i2 = 0; i2 < i; i2++) {
                TypedPropertyValue typedPropertyValue = new TypedPropertyValue(access$100, (Object) null);
                typedPropertyValue.read(littleEndianByteArrayInputStream);
                this._values[i2] = typedPropertyValue;
                if (access$100 != 0) {
                    TypedPropertyValue.skipPadding(littleEndianByteArrayInputStream);
                }
            }
            return;
        }
        throw new UnsupportedOperationException("Sorry, but POI can't store array of properties with size of " + numberOfScalarValues + " in memory");
    }

    public TypedPropertyValue[] getValues() {
        return this._values;
    }
}
