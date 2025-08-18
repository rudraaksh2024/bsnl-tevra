package org.apache.xmlbeans.impl.xb.ltgfmt.impl;

import java.util.function.Consumer;
import org.apache.xmlbeans.impl.xb.ltgfmt.impl.TestsDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TestsDocumentImpl$TestsImpl$$ExternalSyntheticLambda3 implements Consumer {
    public final /* synthetic */ TestsDocumentImpl.TestsImpl f$0;

    public /* synthetic */ TestsDocumentImpl$TestsImpl$$ExternalSyntheticLambda3(TestsDocumentImpl.TestsImpl testsImpl) {
        this.f$0 = testsImpl;
    }

    public final void accept(Object obj) {
        this.f$0.removeTest(((Integer) obj).intValue());
    }
}
