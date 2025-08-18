package com.microsoft.schemas.compatibility.impl;

import com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl;
import java.util.function.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AlternateContentDocumentImpl$AlternateContentImpl$$ExternalSyntheticLambda3 implements Consumer {
    public final /* synthetic */ AlternateContentDocumentImpl.AlternateContentImpl f$0;

    public /* synthetic */ AlternateContentDocumentImpl$AlternateContentImpl$$ExternalSyntheticLambda3(AlternateContentDocumentImpl.AlternateContentImpl alternateContentImpl) {
        this.f$0 = alternateContentImpl;
    }

    public final void accept(Object obj) {
        this.f$0.removeChoice(((Integer) obj).intValue());
    }
}
