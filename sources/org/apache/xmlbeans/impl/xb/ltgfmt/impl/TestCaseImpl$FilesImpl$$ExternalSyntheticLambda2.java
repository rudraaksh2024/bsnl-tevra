package org.apache.xmlbeans.impl.xb.ltgfmt.impl;

import java.util.function.Function;
import org.apache.xmlbeans.impl.xb.ltgfmt.impl.TestCaseImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TestCaseImpl$FilesImpl$$ExternalSyntheticLambda2 implements Function {
    public final /* synthetic */ TestCaseImpl.FilesImpl f$0;

    public /* synthetic */ TestCaseImpl$FilesImpl$$ExternalSyntheticLambda2(TestCaseImpl.FilesImpl filesImpl) {
        this.f$0 = filesImpl;
    }

    public final Object apply(Object obj) {
        return this.f$0.insertNewFile(((Integer) obj).intValue());
    }
}
