package org.apache.xmlbeans.impl.xb.xsdownload.impl;

import java.util.function.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DownloadedSchemaEntryImpl$$ExternalSyntheticLambda5 implements Consumer {
    public final /* synthetic */ DownloadedSchemaEntryImpl f$0;

    public /* synthetic */ DownloadedSchemaEntryImpl$$ExternalSyntheticLambda5(DownloadedSchemaEntryImpl downloadedSchemaEntryImpl) {
        this.f$0 = downloadedSchemaEntryImpl;
    }

    public final void accept(Object obj) {
        this.f$0.removeSchemaLocation(((Integer) obj).intValue());
    }
}
