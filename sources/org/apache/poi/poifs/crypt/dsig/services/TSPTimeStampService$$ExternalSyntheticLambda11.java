package org.apache.poi.poifs.crypt.dsig.services;

import java.util.function.Function;
import java.util.stream.Stream;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.GeneralNames;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TSPTimeStampService$$ExternalSyntheticLambda11 implements Function {
    public final Object apply(Object obj) {
        return Stream.of(GeneralNames.getInstance(((DistributionPointName) obj).getName()).getNames());
    }
}
