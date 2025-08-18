package org.apache.poi.util;

import java.util.Map;
import java.util.function.Predicate;
import org.apache.poi.util.GenericRecordUtil;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class GenericRecordUtil$AnnotatedFlag$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ GenericRecordUtil.AnnotatedFlag f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ GenericRecordUtil$AnnotatedFlag$$ExternalSyntheticLambda0(GenericRecordUtil.AnnotatedFlag annotatedFlag, int i) {
        this.f$0 = annotatedFlag;
        this.f$1 = i;
    }

    public final boolean test(Object obj) {
        return this.f$0.m2290lambda$getDescription$0$orgapachepoiutilGenericRecordUtil$AnnotatedFlag(this.f$1, (Map.Entry) obj);
    }
}
