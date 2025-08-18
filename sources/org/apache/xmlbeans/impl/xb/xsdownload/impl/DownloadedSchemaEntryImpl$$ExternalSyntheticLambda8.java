package org.apache.xmlbeans.impl.xb.xsdownload.impl;

import java.util.function.BiConsumer;
import org.apache.xmlbeans.XmlAnyURI;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DownloadedSchemaEntryImpl$$ExternalSyntheticLambda8 implements BiConsumer {
    public final /* synthetic */ DownloadedSchemaEntryImpl f$0;

    public /* synthetic */ DownloadedSchemaEntryImpl$$ExternalSyntheticLambda8(DownloadedSchemaEntryImpl downloadedSchemaEntryImpl) {
        this.f$0 = downloadedSchemaEntryImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.xsetSchemaLocationArray(((Integer) obj).intValue(), (XmlAnyURI) obj2);
    }
}
