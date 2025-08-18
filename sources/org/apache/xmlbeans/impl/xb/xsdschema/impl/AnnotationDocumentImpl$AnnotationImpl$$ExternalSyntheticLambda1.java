package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.function.BiConsumer;
import org.apache.xmlbeans.impl.xb.xsdschema.AppinfoDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotationDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda1 implements BiConsumer {
    public final /* synthetic */ AnnotationDocumentImpl.AnnotationImpl f$0;

    public /* synthetic */ AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda1(AnnotationDocumentImpl.AnnotationImpl annotationImpl) {
        this.f$0 = annotationImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setAppinfoArray(((Integer) obj).intValue(), (AppinfoDocument.Appinfo) obj2);
    }
}
