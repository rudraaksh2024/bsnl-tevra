package kotlinx.coroutines.stream;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.jvm.Volatile;
import kotlinx.coroutines.flow.Flow;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u001f\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH@ø\u0001\u0000¢\u0006\u0002\u0010\fR\t\u0010\u0006\u001a\u00020\u0007X\u0004R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/stream/StreamFlow;", "T", "Lkotlinx/coroutines/flow/Flow;", "stream", "Ljava/util/stream/Stream;", "(Ljava/util/stream/Stream;)V", "consumed", "Lkotlinx/atomicfu/AtomicBoolean;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Stream.kt */
final class StreamFlow<T> implements Flow<T> {
    private static final AtomicIntegerFieldUpdater consumed$FU = AtomicIntegerFieldUpdater.newUpdater(StreamFlow.class, "consumed");
    @Volatile
    private volatile int consumed = 0;
    private final Stream<T> stream;

    public StreamFlow(Stream<T> stream2) {
        this.stream = stream2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005f A[Catch:{ all -> 0x007a }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0072 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object collect(kotlinx.coroutines.flow.FlowCollector<? super T> r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof kotlinx.coroutines.stream.StreamFlow$collect$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            kotlinx.coroutines.stream.StreamFlow$collect$1 r0 = (kotlinx.coroutines.stream.StreamFlow$collect$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.stream.StreamFlow$collect$1 r0 = new kotlinx.coroutines.stream.StreamFlow$collect$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0043
            if (r2 != r3) goto L_0x003b
            java.lang.Object r5 = r0.L$2
            java.util.Iterator r5 = (java.util.Iterator) r5
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.stream.StreamFlow r2 = (kotlinx.coroutines.stream.StreamFlow) r2
            kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ all -> 0x0038 }
            r7 = r6
            r6 = r2
            goto L_0x0059
        L_0x0038:
            r5 = move-exception
            r6 = r2
            goto L_0x0080
        L_0x003b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0043:
            kotlin.ResultKt.throwOnFailure(r7)
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r7 = consumed$FU
            r2 = 0
            boolean r7 = r7.compareAndSet(r5, r2, r3)
            if (r7 == 0) goto L_0x0086
            java.util.stream.Stream<T> r7 = r5.stream     // Catch:{ all -> 0x007c }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x007c }
            r4 = r6
            r6 = r5
            r5 = r7
            r7 = r4
        L_0x0059:
            boolean r2 = r5.hasNext()     // Catch:{ all -> 0x007a }
            if (r2 == 0) goto L_0x0072
            java.lang.Object r2 = r5.next()     // Catch:{ all -> 0x007a }
            r0.L$0 = r6     // Catch:{ all -> 0x007a }
            r0.L$1 = r7     // Catch:{ all -> 0x007a }
            r0.L$2 = r5     // Catch:{ all -> 0x007a }
            r0.label = r3     // Catch:{ all -> 0x007a }
            java.lang.Object r2 = r7.emit(r2, r0)     // Catch:{ all -> 0x007a }
            if (r2 != r1) goto L_0x0059
            return r1
        L_0x0072:
            java.util.stream.Stream<T> r5 = r6.stream
            r5.close()
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L_0x007a:
            r5 = move-exception
            goto L_0x0080
        L_0x007c:
            r6 = move-exception
            r4 = r6
            r6 = r5
            r5 = r4
        L_0x0080:
            java.util.stream.Stream<T> r6 = r6.stream
            r6.close()
            throw r5
        L_0x0086:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "Stream.consumeAsFlow can be collected only once"
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.stream.StreamFlow.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
