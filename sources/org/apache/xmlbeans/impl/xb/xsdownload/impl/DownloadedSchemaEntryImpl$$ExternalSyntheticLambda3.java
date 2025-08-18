package org.apache.xmlbeans.impl.xb.xsdownload.impl;

import java.util.function.BiConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DownloadedSchemaEntryImpl$$ExternalSyntheticLambda3 implements BiConsumer {
    public final /* synthetic */ DownloadedSchemaEntryImpl f$0;

    public /* synthetic */ DownloadedSchemaEntryImpl$$ExternalSyntheticLambda3(DownloadedSchemaEntryImpl downloadedSchemaEntryImpl) {
        this.f$0 = downloadedSchemaEntryImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setSchemaLocationArray(((Integer) obj).intValue(), (String) obj2);
    }
}
