package org.apache.xmlbeans.impl.xb.ltgfmt.impl;

import java.util.function.BiConsumer;
import org.apache.xmlbeans.impl.xb.ltgfmt.TestCase;
import org.apache.xmlbeans.impl.xb.ltgfmt.impl.TestsDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TestsDocumentImpl$TestsImpl$$ExternalSyntheticLambda1 implements BiConsumer {
    public final /* synthetic */ TestsDocumentImpl.TestsImpl f$0;

    public /* synthetic */ TestsDocumentImpl$TestsImpl$$ExternalSyntheticLambda1(TestsDocumentImpl.TestsImpl testsImpl) {
        this.f$0 = testsImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setTestArray(((Integer) obj).intValue(), (TestCase) obj2);
    }
}
