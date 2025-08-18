package org.apache.poi.poifs.crypt.dsig.facets;

import java.util.function.Supplier;
import org.etsi.uri.x01903.v13.QualifyingPropertiesType;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XAdESXLSignatureFacet$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ QualifyingPropertiesType f$0;

    public /* synthetic */ XAdESXLSignatureFacet$$ExternalSyntheticLambda1(QualifyingPropertiesType qualifyingPropertiesType) {
        this.f$0 = qualifyingPropertiesType;
    }

    public final Object get() {
        return this.f$0.addNewUnsignedProperties();
    }
}
