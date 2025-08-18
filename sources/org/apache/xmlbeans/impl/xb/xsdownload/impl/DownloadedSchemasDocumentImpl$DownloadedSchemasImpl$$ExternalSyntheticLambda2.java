package org.apache.xmlbeans.impl.xb.xsdownload.impl;

import java.util.function.Function;
import org.apache.xmlbeans.impl.xb.xsdownload.impl.DownloadedSchemasDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DownloadedSchemasDocumentImpl$DownloadedSchemasImpl$$ExternalSyntheticLambda2 implements Function {
    public final /* synthetic */ DownloadedSchemasDocumentImpl.DownloadedSchemasImpl f$0;

    public /* synthetic */ DownloadedSchemasDocumentImpl$DownloadedSchemasImpl$$ExternalSyntheticLambda2(DownloadedSchemasDocumentImpl.DownloadedSchemasImpl downloadedSchemasImpl) {
        this.f$0 = downloadedSchemasImpl;
    }

    public final Object apply(Object obj) {
        return this.f$0.insertNewEntry(((Integer) obj).intValue());
    }
}
