package org.apache.poi.poifs.crypt.dsig;

import java.util.function.Function;
import org.apache.poi.poifs.crypt.dsig.SignatureInfo;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SignatureInfo$XmlProviderInitSingleton$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ SignatureInfo.XmlProviderInitSingleton f$0;

    public /* synthetic */ SignatureInfo$XmlProviderInitSingleton$$ExternalSyntheticLambda0(SignatureInfo.XmlProviderInitSingleton xmlProviderInitSingleton) {
        this.f$0 = xmlProviderInitSingleton;
    }

    public final Object apply(Object obj) {
        return this.f$0.getProvider((String) obj);
    }
}
