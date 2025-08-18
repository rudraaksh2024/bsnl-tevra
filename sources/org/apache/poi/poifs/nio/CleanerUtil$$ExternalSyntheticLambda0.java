package org.apache.poi.poifs.nio;

import java.lang.invoke.MethodHandle;
import java.nio.ByteBuffer;
import org.apache.poi.poifs.nio.CleanerUtil;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CleanerUtil$$ExternalSyntheticLambda0 implements CleanerUtil.BufferCleaner {
    public final /* synthetic */ Class f$0;
    public final /* synthetic */ MethodHandle f$1;

    public /* synthetic */ CleanerUtil$$ExternalSyntheticLambda0(Class cls, MethodHandle methodHandle) {
        this.f$0 = cls;
        this.f$1 = methodHandle;
    }

    public final void freeBuffer(ByteBuffer byteBuffer) {
        CleanerUtil.lambda$newBufferCleaner$1(this.f$0, this.f$1, byteBuffer);
    }
}
