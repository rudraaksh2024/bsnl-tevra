package org.apache.poi.poifs.crypt.dsig.services;

import java.util.function.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TSPTimeStampService$$ExternalSyntheticLambda5 implements Consumer {
    public final /* synthetic */ RevocationData f$0;

    public /* synthetic */ TSPTimeStampService$$ExternalSyntheticLambda5(RevocationData revocationData) {
        this.f$0 = revocationData;
    }

    public final void accept(Object obj) {
        this.f$0.addCRL((byte[]) obj);
    }
}
