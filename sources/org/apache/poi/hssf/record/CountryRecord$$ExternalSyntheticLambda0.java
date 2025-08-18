package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CountryRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ CountryRecord f$0;

    public /* synthetic */ CountryRecord$$ExternalSyntheticLambda0(CountryRecord countryRecord) {
        this.f$0 = countryRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getDefaultCountry());
    }
}
