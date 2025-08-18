package org.apache.poi.poifs.crypt.dsig.facets;

import java.util.function.Supplier;
import org.etsi.uri.x01903.v13.UnsignedPropertiesType;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XAdESXLSignatureFacet$$ExternalSyntheticLambda2 implements Supplier {
    public final /* synthetic */ UnsignedPropertiesType f$0;

    public /* synthetic */ XAdESXLSignatureFacet$$ExternalSyntheticLambda2(UnsignedPropertiesType unsignedPropertiesType) {
        this.f$0 = unsignedPropertiesType;
    }

    public final Object get() {
        return this.f$0.addNewUnsignedSignatureProperties();
    }
}
