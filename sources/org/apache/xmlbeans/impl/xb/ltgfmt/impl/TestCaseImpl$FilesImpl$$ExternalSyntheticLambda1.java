package org.apache.xmlbeans.impl.xb.ltgfmt.impl;

import java.util.function.BiConsumer;
import org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc;
import org.apache.xmlbeans.impl.xb.ltgfmt.impl.TestCaseImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TestCaseImpl$FilesImpl$$ExternalSyntheticLambda1 implements BiConsumer {
    public final /* synthetic */ TestCaseImpl.FilesImpl f$0;

    public /* synthetic */ TestCaseImpl$FilesImpl$$ExternalSyntheticLambda1(TestCaseImpl.FilesImpl filesImpl) {
        this.f$0 = filesImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setFileArray(((Integer) obj).intValue(), (FileDesc) obj2);
    }
}
