package org.apache.poi.util;

import java.util.Map;
import java.util.function.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class GenericRecordXmlWriter$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ Object f$0;

    public /* synthetic */ GenericRecordXmlWriter$$ExternalSyntheticLambda0(Object obj) {
        this.f$0 = obj;
    }

    public final boolean test(Object obj) {
        return GenericRecordXmlWriter.matchInstanceOrArray((Class) ((Map.Entry) obj).getKey(), this.f$0);
    }
}
