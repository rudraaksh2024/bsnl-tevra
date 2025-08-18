package org.apache.poi.ddf;

import com.google.firebase.messaging.Constants;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;
import kotlin.jvm.internal.ShortCompanionObject;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;

public class EscherComplexProperty extends EscherProperty {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 100000000;
    private static int MAX_RECORD_LENGTH = 100000000;
    private byte[] complexData;

    public static void setMaxRecordLength(int i) {
        MAX_RECORD_LENGTH = i;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public EscherComplexProperty(short s, int i) {
        super((short) (s | ShortCompanionObject.MIN_VALUE));
        this.complexData = IOUtils.safelyAllocate((long) i, MAX_RECORD_LENGTH);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public EscherComplexProperty(short s, boolean z, int i) {
        this((short) (s | (z ? (short) 16384 : 0)), i);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public EscherComplexProperty(EscherPropertyTypes escherPropertyTypes, boolean z, int i) {
        this((short) (escherPropertyTypes.propNumber | (z ? (short) 16384 : 0)), i);
    }

    public int serializeSimplePart(byte[] bArr, int i) {
        LittleEndian.putShort(bArr, i, getId());
        LittleEndian.putInt(bArr, i + 2, this.complexData.length);
        return 6;
    }

    public int serializeComplexPart(byte[] bArr, int i) {
        byte[] bArr2 = this.complexData;
        System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
        return this.complexData.length;
    }

    public byte[] getComplexData() {
        return this.complexData;
    }

    public int setComplexData(byte[] bArr) {
        return setComplexData(bArr, 0);
    }

    public int setComplexData(byte[] bArr, int i) {
        if (bArr == null) {
            return 0;
        }
        int max = Math.max(0, Math.min(this.complexData.length, bArr.length - i));
        System.arraycopy(bArr, i, this.complexData, 0, max);
        return max;
    }

    /* access modifiers changed from: protected */
    public void resizeComplexData(int i) {
        resizeComplexData(i, Integer.MAX_VALUE);
    }

    /* access modifiers changed from: protected */
    public void resizeComplexData(int i, int i2) {
        if (i != this.complexData.length) {
            byte[] safelyAllocate = IOUtils.safelyAllocate((long) i, MAX_RECORD_LENGTH);
            byte[] bArr = this.complexData;
            System.arraycopy(bArr, 0, safelyAllocate, 0, Math.min(Math.min(bArr.length, i2), i));
            this.complexData = safelyAllocate;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EscherComplexProperty)) {
            return false;
        }
        return Arrays.equals(this.complexData, ((EscherComplexProperty) obj).complexData);
    }

    public int getPropertySize() {
        return this.complexData.length + 6;
    }

    public int hashCode() {
        return Arrays.deepHashCode(new Object[]{this.complexData, Short.valueOf(getId())});
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new EscherComplexProperty$$ExternalSyntheticLambda0(this), Constants.ScionAnalytics.MessageType.DATA_MESSAGE, new EscherComplexProperty$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ddf-EscherComplexProperty  reason: not valid java name */
    public /* synthetic */ Object m1955lambda$getGenericProperties$0$orgapachepoiddfEscherComplexProperty() {
        return super.getGenericProperties();
    }
}
