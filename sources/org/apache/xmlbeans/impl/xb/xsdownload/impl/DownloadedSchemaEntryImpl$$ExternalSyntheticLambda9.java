package org.apache.xmlbeans.impl.xb.xsdownload.impl;

import java.util.function.Function;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DownloadedSchemaEntryImpl$$ExternalSyntheticLambda9 implements Function {
    public final /* synthetic */ DownloadedSchemaEntryImpl f$0;

    public /* synthetic */ DownloadedSchemaEntryImpl$$ExternalSyntheticLambda9(DownloadedSchemaEntryImpl downloadedSchemaEntryImpl) {
        this.f$0 = downloadedSchemaEntryImpl;
    }

    public final Object apply(Object obj) {
        return this.f$0.insertNewSchemaLocation(((Integer) obj).intValue());
    }
}
