package com.microsoft.schemas.compatibility.impl;

import com.microsoft.schemas.compatibility.AlternateContentDocument;
import com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl;
import java.util.function.BiConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AlternateContentDocumentImpl$AlternateContentImpl$$ExternalSyntheticLambda1 implements BiConsumer {
    public final /* synthetic */ AlternateContentDocumentImpl.AlternateContentImpl f$0;

    public /* synthetic */ AlternateContentDocumentImpl$AlternateContentImpl$$ExternalSyntheticLambda1(AlternateContentDocumentImpl.AlternateContentImpl alternateContentImpl) {
        this.f$0 = alternateContentImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setChoiceArray(((Integer) obj).intValue(), (AlternateContentDocument.AlternateContent.Choice) obj2);
    }
}
