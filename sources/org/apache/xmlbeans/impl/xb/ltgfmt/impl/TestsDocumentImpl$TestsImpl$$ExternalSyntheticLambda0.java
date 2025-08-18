package org.apache.xmlbeans.impl.xb.ltgfmt.impl;

import java.util.function.Function;
import org.apache.xmlbeans.impl.xb.ltgfmt.impl.TestsDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TestsDocumentImpl$TestsImpl$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ TestsDocumentImpl.TestsImpl f$0;

    public /* synthetic */ TestsDocumentImpl$TestsImpl$$ExternalSyntheticLambda0(TestsDocumentImpl.TestsImpl testsImpl) {
        this.f$0 = testsImpl;
    }

    public final Object apply(Object obj) {
        return this.f$0.getTestArray(((Integer) obj).intValue());
    }
}
