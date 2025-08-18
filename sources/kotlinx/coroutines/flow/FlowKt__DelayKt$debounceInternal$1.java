package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H@"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "downstream", "Lkotlinx/coroutines/flow/FlowCollector;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1", f = "Delay.kt", i = {0, 0, 0, 0, 1, 1, 1}, l = {221, 426}, m = "invokeSuspend", n = {"downstream", "values", "lastValue", "timeoutMillis", "downstream", "values", "lastValue"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2"})
/* compiled from: Delay.kt */
final class FlowKt__DelayKt$debounceInternal$1 extends SuspendLambda implements Function3<CoroutineScope, FlowCollector<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow<T> $this_debounceInternal;
    final /* synthetic */ Function1<T, Long> $timeoutMillisSelector;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FlowKt__DelayKt$debounceInternal$1(Function1<? super T, Long> function1, Flow<? extends T> flow, Continuation<? super FlowKt__DelayKt$debounceInternal$1> continuation) {
        super(3, continuation);
        this.$timeoutMillisSelector = function1;
        this.$this_debounceInternal = flow;
    }

    public final Object invoke(CoroutineScope coroutineScope, FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        FlowKt__DelayKt$debounceInternal$1 flowKt__DelayKt$debounceInternal$1 = new FlowKt__DelayKt$debounceInternal$1(this.$timeoutMillisSelector, this.$this_debounceInternal, continuation);
        flowKt__DelayKt$debounceInternal$1.L$0 = coroutineScope;
        flowKt__DelayKt$debounceInternal$1.L$1 = flowCollector;
        return flowKt__DelayKt$debounceInternal$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0121 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            r14 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r14.label
            r2 = 0
            r3 = 2
            r4 = 0
            r6 = 1
            r7 = 0
            if (r1 == 0) goto L_0x0042
            if (r1 == r6) goto L_0x002d
            if (r1 != r3) goto L_0x0025
            java.lang.Object r1 = r14.L$2
            kotlin.jvm.internal.Ref$ObjectRef r1 = (kotlin.jvm.internal.Ref.ObjectRef) r1
            java.lang.Object r8 = r14.L$1
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r14.L$0
            kotlinx.coroutines.flow.FlowCollector r9 = (kotlinx.coroutines.flow.FlowCollector) r9
            kotlin.ResultKt.throwOnFailure(r15)
        L_0x0021:
            r10 = r9
            r9 = r8
            r8 = r1
            goto L_0x0067
        L_0x0025:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r15)
            throw r14
        L_0x002d:
            java.lang.Object r1 = r14.L$3
            kotlin.jvm.internal.Ref$LongRef r1 = (kotlin.jvm.internal.Ref.LongRef) r1
            java.lang.Object r8 = r14.L$2
            kotlin.jvm.internal.Ref$ObjectRef r8 = (kotlin.jvm.internal.Ref.ObjectRef) r8
            java.lang.Object r9 = r14.L$1
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r14.L$0
            kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x00b7
        L_0x0042:
            kotlin.ResultKt.throwOnFailure(r15)
            java.lang.Object r15 = r14.L$0
            r8 = r15
            kotlinx.coroutines.CoroutineScope r8 = (kotlinx.coroutines.CoroutineScope) r8
            java.lang.Object r15 = r14.L$1
            kotlinx.coroutines.flow.FlowCollector r15 = (kotlinx.coroutines.flow.FlowCollector) r15
            r9 = 0
            r10 = 0
            kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1$values$1 r1 = new kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1$values$1
            kotlinx.coroutines.flow.Flow<T> r11 = r14.$this_debounceInternal
            r1.<init>(r11, r7)
            r11 = r1
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            r12 = 3
            r13 = 0
            kotlinx.coroutines.channels.ReceiveChannel r1 = kotlinx.coroutines.channels.ProduceKt.produce$default(r8, r9, r10, r11, r12, r13)
            kotlin.jvm.internal.Ref$ObjectRef r8 = new kotlin.jvm.internal.Ref$ObjectRef
            r8.<init>()
            r10 = r15
            r9 = r1
        L_0x0067:
            T r15 = r8.element
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.flow.internal.NullSurrogateKt.DONE
            if (r15 == r1) goto L_0x0122
            kotlin.jvm.internal.Ref$LongRef r1 = new kotlin.jvm.internal.Ref$LongRef
            r1.<init>()
            T r15 = r8.element
            if (r15 == 0) goto L_0x00b9
            kotlin.jvm.functions.Function1<T, java.lang.Long> r15 = r14.$timeoutMillisSelector
            kotlinx.coroutines.internal.Symbol r11 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            T r12 = r8.element
            if (r12 != r11) goto L_0x007f
            r12 = r7
        L_0x007f:
            java.lang.Object r15 = r15.invoke(r12)
            java.lang.Number r15 = (java.lang.Number) r15
            long r11 = r15.longValue()
            r1.element = r11
            long r11 = r1.element
            int r15 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r15 < 0) goto L_0x0093
            r15 = r6
            goto L_0x0094
        L_0x0093:
            r15 = r2
        L_0x0094:
            if (r15 == 0) goto L_0x00be
            long r11 = r1.element
            int r15 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r15 != 0) goto L_0x00b9
            kotlinx.coroutines.internal.Symbol r15 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            T r11 = r8.element
            if (r11 != r15) goto L_0x00a3
            r11 = r7
        L_0x00a3:
            r15 = r14
            kotlin.coroutines.Continuation r15 = (kotlin.coroutines.Continuation) r15
            r14.L$0 = r10
            r14.L$1 = r9
            r14.L$2 = r8
            r14.L$3 = r1
            r14.label = r6
            java.lang.Object r15 = r10.emit(r11, r15)
            if (r15 != r0) goto L_0x00b7
            return r0
        L_0x00b7:
            r8.element = r7
        L_0x00b9:
            r15 = r1
            r1 = r8
            r8 = r9
            r9 = r10
            goto L_0x00ca
        L_0x00be:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            java.lang.String r15 = "Debounce timeout should not be negative"
            java.lang.String r15 = r15.toString()
            r14.<init>(r15)
            throw r14
        L_0x00ca:
            boolean r10 = kotlinx.coroutines.DebugKt.getASSERTIONS_ENABLED()
            if (r10 == 0) goto L_0x00e7
            T r10 = r1.element
            if (r10 == 0) goto L_0x00dd
            long r10 = r15.element
            int r10 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r10 <= 0) goto L_0x00db
            goto L_0x00dd
        L_0x00db:
            r10 = r2
            goto L_0x00de
        L_0x00dd:
            r10 = r6
        L_0x00de:
            if (r10 == 0) goto L_0x00e1
            goto L_0x00e7
        L_0x00e1:
            java.lang.AssertionError r14 = new java.lang.AssertionError
            r14.<init>()
            throw r14
        L_0x00e7:
            kotlinx.coroutines.selects.SelectImplementation r10 = new kotlinx.coroutines.selects.SelectImplementation
            kotlin.coroutines.CoroutineContext r11 = r14.getContext()
            r10.<init>(r11)
            r11 = r10
            kotlinx.coroutines.selects.SelectBuilder r11 = (kotlinx.coroutines.selects.SelectBuilder) r11
            T r12 = r1.element
            if (r12 == 0) goto L_0x0103
            long r12 = r15.element
            kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1$3$1 r15 = new kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1$3$1
            r15.<init>(r9, r1, r7)
            kotlin.jvm.functions.Function1 r15 = (kotlin.jvm.functions.Function1) r15
            kotlinx.coroutines.selects.OnTimeoutKt.onTimeout(r11, r12, r15)
        L_0x0103:
            kotlinx.coroutines.selects.SelectClause1 r15 = r8.getOnReceiveCatching()
            kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1$3$2 r12 = new kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1$3$2
            r12.<init>(r1, r9, r7)
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            r11.invoke(r15, r12)
            r14.L$0 = r9
            r14.L$1 = r8
            r14.L$2 = r1
            r14.L$3 = r7
            r14.label = r3
            java.lang.Object r15 = r10.doSelect(r14)
            if (r15 != r0) goto L_0x0021
            return r0
        L_0x0122:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
