package org.apache.xmlbeans.impl.xb.xsdownload.impl;

import java.util.function.BiConsumer;
import org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry;
import org.apache.xmlbeans.impl.xb.xsdownload.impl.DownloadedSchemasDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DownloadedSchemasDocumentImpl$DownloadedSchemasImpl$$ExternalSyntheticLambda1 implements BiConsumer {
    public final /* synthetic */ DownloadedSchemasDocumentImpl.DownloadedSchemasImpl f$0;

    public /* synthetic */ DownloadedSchemasDocumentImpl$DownloadedSchemasImpl$$ExternalSyntheticLambda1(DownloadedSchemasDocumentImpl.DownloadedSchemasImpl downloadedSchemasImpl) {
        this.f$0 = downloadedSchemasImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setEntryArray(((Integer) obj).intValue(), (DownloadedSchemaEntry) obj2);
    }
}
