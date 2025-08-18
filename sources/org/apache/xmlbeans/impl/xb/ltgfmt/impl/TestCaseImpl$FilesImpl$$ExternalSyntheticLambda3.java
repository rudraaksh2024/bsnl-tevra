package org.apache.xmlbeans.impl.xb.ltgfmt.impl;

import java.util.function.Consumer;
import org.apache.xmlbeans.impl.xb.ltgfmt.impl.TestCaseImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TestCaseImpl$FilesImpl$$ExternalSyntheticLambda3 implements Consumer {
    public final /* synthetic */ TestCaseImpl.FilesImpl f$0;

    public /* synthetic */ TestCaseImpl$FilesImpl$$ExternalSyntheticLambda3(TestCaseImpl.FilesImpl filesImpl) {
        this.f$0 = filesImpl;
    }

    public final void accept(Object obj) {
        this.f$0.removeFile(((Integer) obj).intValue());
    }
}
