package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.function.Consumer;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotationDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda8 implements Consumer {
    public final /* synthetic */ AnnotationDocumentImpl.AnnotationImpl f$0;

    public /* synthetic */ AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda8(AnnotationDocumentImpl.AnnotationImpl annotationImpl) {
        this.f$0 = annotationImpl;
    }

    public final void accept(Object obj) {
        this.f$0.removeDocumentation(((Integer) obj).intValue());
    }
}
