package org.apache.xmlbeans.impl.xb.xsdownload.impl;

import java.util.function.Consumer;
import org.apache.xmlbeans.impl.xb.xsdownload.impl.DownloadedSchemasDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DownloadedSchemasDocumentImpl$DownloadedSchemasImpl$$ExternalSyntheticLambda3 implements Consumer {
    public final /* synthetic */ DownloadedSchemasDocumentImpl.DownloadedSchemasImpl f$0;

    public /* synthetic */ DownloadedSchemasDocumentImpl$DownloadedSchemasImpl$$ExternalSyntheticLambda3(DownloadedSchemasDocumentImpl.DownloadedSchemasImpl downloadedSchemasImpl) {
        this.f$0 = downloadedSchemasImpl;
    }

    public final void accept(Object obj) {
        this.f$0.removeEntry(((Integer) obj).intValue());
    }
}
