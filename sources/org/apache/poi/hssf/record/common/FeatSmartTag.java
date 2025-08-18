package org.apache.poi.hssf.record.common;

import com.google.firebase.messaging.Constants;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class FeatSmartTag implements SharedFeature {
    private byte[] data;

    public FeatSmartTag() {
        this.data = new byte[0];
    }

    public FeatSmartTag(FeatSmartTag featSmartTag) {
        byte[] bArr = featSmartTag.data;
        this.data = bArr == null ? null : (byte[]) bArr.clone();
    }

    public FeatSmartTag(RecordInputStream recordInputStream) {
        this.data = recordInputStream.readRemainder();
    }

    public String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    public int getDataSize() {
        return this.data.length;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.write(this.data);
    }

    public FeatSmartTag copy() {
        return new FeatSmartTag(this);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, new FeatSmartTag$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-common-FeatSmartTag  reason: not valid java name */
    public /* synthetic */ Object m2164lambda$getGenericProperties$0$orgapachepoihssfrecordcommonFeatSmartTag() {
        return this.data;
    }
}
