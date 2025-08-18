package org.apache.poi.ddf;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.Removal;

public final class EscherArrayProperty extends EscherComplexProperty implements Iterable<byte[]> {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 100000;
    private static final int FIXED_SIZE = 6;
    private static int MAX_RECORD_LENGTH = 100000;
    private final boolean emptyComplexPart;
    private boolean sizeIncludesHeaderSize;

    private static int getActualSizeOfElements(short s) {
        return s < 0 ? (short) ((-s) >> 2) : s;
    }

    private static int safeSize(int i) {
        if (i == 0) {
            return 6;
        }
        return i;
    }

    public static void setMaxRecordLength(int i) {
        MAX_RECORD_LENGTH = i;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    @Internal
    public EscherArrayProperty(short s, int i) {
        super(s, i);
        boolean z = true;
        this.sizeIncludesHeaderSize = true;
        this.emptyComplexPart = i != 0 ? false : z;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    @Removal(version = "5.0.0")
    public EscherArrayProperty(short s, boolean z, byte[] bArr) {
        this((short) (s | (z ? (short) 16384 : 0)), safeSize(bArr != null ? bArr.length : 0));
        setComplexData(bArr);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public EscherArrayProperty(EscherPropertyTypes escherPropertyTypes, boolean z, int i) {
        this((short) (escherPropertyTypes.propNumber | (z ? (short) 16384 : 0)), safeSize(i));
    }

    public int getNumberOfElementsInArray() {
        if (this.emptyComplexPart) {
            return 0;
        }
        return LittleEndian.getUShort(getComplexData(), 0);
    }

    public void setNumberOfElementsInArray(int i) {
        if (!this.emptyComplexPart) {
            rewriteArray(i, false);
            LittleEndian.putShort(getComplexData(), 0, (short) i);
        }
    }

    private void rewriteArray(int i, boolean z) {
        int i2;
        int actualSizeOfElements = (i * getActualSizeOfElements(getSizeOfElements())) + 6;
        if (z) {
            i2 = actualSizeOfElements;
        } else {
            i2 = getComplexData().length;
        }
        resizeComplexData(actualSizeOfElements, i2);
    }

    public int getNumberOfElementsInMemory() {
        if (this.emptyComplexPart) {
            return 0;
        }
        return LittleEndian.getUShort(getComplexData(), 2);
    }

    public void setNumberOfElementsInMemory(int i) {
        if (!this.emptyComplexPart) {
            rewriteArray(i, true);
            LittleEndian.putShort(getComplexData(), 2, (short) i);
        }
    }

    public short getSizeOfElements() {
        if (this.emptyComplexPart) {
            return 0;
        }
        return LittleEndian.getShort(getComplexData(), 4);
    }

    public void setSizeOfElements(int i) {
        if (!this.emptyComplexPart) {
            LittleEndian.putShort(getComplexData(), 4, (short) i);
            resizeComplexData((getNumberOfElementsInArray() * getActualSizeOfElements(getSizeOfElements())) + 6, 6);
        }
    }

    public byte[] getElement(int i) {
        int actualSizeOfElements = getActualSizeOfElements(getSizeOfElements());
        return IOUtils.safelyClone(getComplexData(), (i * actualSizeOfElements) + 6, actualSizeOfElements, MAX_RECORD_LENGTH);
    }

    public void setElement(int i, byte[] bArr) {
        if (!this.emptyComplexPart) {
            int actualSizeOfElements = getActualSizeOfElements(getSizeOfElements());
            System.arraycopy(bArr, 0, getComplexData(), (i * actualSizeOfElements) + 6, actualSizeOfElements);
        }
    }

    public int setArrayData(byte[] bArr, int i) {
        if (this.emptyComplexPart) {
            resizeComplexData(0);
        } else {
            short s = LittleEndian.getShort(bArr, i);
            short s2 = LittleEndian.getShort(bArr, i + 4);
            int length = getComplexData().length;
            int actualSizeOfElements = getActualSizeOfElements(s2) * s;
            if (actualSizeOfElements == length) {
                resizeComplexData(actualSizeOfElements + 6, 0);
                this.sizeIncludesHeaderSize = false;
            }
            setComplexData(bArr, i);
        }
        return getComplexData().length;
    }

    public int serializeSimplePart(byte[] bArr, int i) {
        LittleEndian.putShort(bArr, i, getId());
        int length = getComplexData().length;
        if (!this.sizeIncludesHeaderSize) {
            length -= 6;
        }
        LittleEndian.putInt(bArr, i + 2, length);
        return 6;
    }

    public Iterator<byte[]> iterator() {
        return new Iterator<byte[]>() {
            int idx;

            public boolean hasNext() {
                return this.idx < EscherArrayProperty.this.getNumberOfElementsInArray();
            }

            public byte[] next() {
                if (hasNext()) {
                    EscherArrayProperty escherArrayProperty = EscherArrayProperty.this;
                    int i = this.idx;
                    this.idx = i + 1;
                    return escherArrayProperty.getElement(i);
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                throw new UnsupportedOperationException("not yet implemented");
            }
        };
    }

    public Spliterator<byte[]> spliterator() {
        return Spliterators.spliterator(iterator(), (long) getNumberOfElementsInArray(), 0);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new EscherArrayProperty$$ExternalSyntheticLambda0(this), "numElements", new EscherArrayProperty$$ExternalSyntheticLambda1(this), "numElementsInMemory", new EscherArrayProperty$$ExternalSyntheticLambda2(this), "sizeOfElements", new EscherArrayProperty$$ExternalSyntheticLambda3(this), "elements", new EscherArrayProperty$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ddf-EscherArrayProperty  reason: not valid java name */
    public /* synthetic */ Object m1949lambda$getGenericProperties$0$orgapachepoiddfEscherArrayProperty() {
        return super.getGenericProperties();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-ddf-EscherArrayProperty  reason: not valid java name */
    public /* synthetic */ Object m1950lambda$getGenericProperties$1$orgapachepoiddfEscherArrayProperty() {
        return (List) StreamSupport.stream(spliterator(), false).collect(Collectors.toList());
    }
}
