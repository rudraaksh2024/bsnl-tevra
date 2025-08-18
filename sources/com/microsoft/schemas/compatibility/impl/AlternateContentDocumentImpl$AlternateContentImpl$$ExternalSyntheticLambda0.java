package com.microsoft.schemas.compatibility.impl;

import com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl;
import java.util.function.Function;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AlternateContentDocumentImpl$AlternateContentImpl$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ AlternateContentDocumentImpl.AlternateContentImpl f$0;

    public /* synthetic */ AlternateContentDocumentImpl$AlternateContentImpl$$ExternalSyntheticLambda0(AlternateContentDocumentImpl.AlternateContentImpl alternateContentImpl) {
        this.f$0 = alternateContentImpl;
    }

    public final Object apply(Object obj) {
        return this.f$0.getChoiceArray(((Integer) obj).intValue());
    }
}
