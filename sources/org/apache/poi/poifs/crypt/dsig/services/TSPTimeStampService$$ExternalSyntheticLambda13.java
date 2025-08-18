package org.apache.poi.poifs.crypt.dsig.services;

import java.util.function.Function;
import org.bouncycastle.asn1.ASN1IA5String;
import org.bouncycastle.asn1.x509.GeneralName;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TSPTimeStampService$$ExternalSyntheticLambda13 implements Function {
    public final Object apply(Object obj) {
        return ASN1IA5String.getInstance(((GeneralName) obj).getName()).getString();
    }
}
