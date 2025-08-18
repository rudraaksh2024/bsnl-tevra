package org.apache.poi.poifs.crypt.dsig;

import java.util.function.Supplier;
import org.apache.poi.poifs.crypt.dsig.SignatureInfo;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SignatureInfo$XmlProviderInitSingleton$$ExternalSyntheticLambda2 implements Supplier {
    public final /* synthetic */ SignatureInfo.XmlProviderInitSingleton f$0;

    public /* synthetic */ SignatureInfo$XmlProviderInitSingleton$$ExternalSyntheticLambda2(SignatureInfo.XmlProviderInitSingleton xmlProviderInitSingleton) {
        this.f$0 = xmlProviderInitSingleton;
    }

    public final Object get() {
        return this.f$0.providerNotFound();
    }
}
