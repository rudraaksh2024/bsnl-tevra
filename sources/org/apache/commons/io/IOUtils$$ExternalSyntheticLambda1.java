package org.apache.commons.io;

import org.apache.commons.io.function.IOFunction;
import org.apache.commons.io.output.ThresholdingOutputStream;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class IOUtils$$ExternalSyntheticLambda1 implements IOFunction {
    public final /* synthetic */ UnsynchronizedByteArrayOutputStream f$0;

    public /* synthetic */ IOUtils$$ExternalSyntheticLambda1(UnsynchronizedByteArrayOutputStream unsynchronizedByteArrayOutputStream) {
        this.f$0 = unsynchronizedByteArrayOutputStream;
    }

    public final Object apply(Object obj) {
        return IOUtils.lambda$toByteArray$1(this.f$0, (ThresholdingOutputStream) obj);
    }
}
