package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.function.BiConsumer;
import org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotationDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda6 implements BiConsumer {
    public final /* synthetic */ AnnotationDocumentImpl.AnnotationImpl f$0;

    public /* synthetic */ AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda6(AnnotationDocumentImpl.AnnotationImpl annotationImpl) {
        this.f$0 = annotationImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setDocumentationArray(((Integer) obj).intValue(), (DocumentationDocument.Documentation) obj2);
    }
}
