package org.apache.poi.extractor;

import java.io.InputStream;
import org.apache.poi.extractor.ExtractorFactory;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExtractorFactory$$ExternalSyntheticLambda5 implements ExtractorFactory.ProviderMethod {
    public final /* synthetic */ InputStream f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ ExtractorFactory$$ExternalSyntheticLambda5(InputStream inputStream, String str) {
        this.f$0 = inputStream;
        this.f$1 = str;
    }

    public final POITextExtractor create(ExtractorProvider extractorProvider) {
        return extractorProvider.create(this.f$0, this.f$1);
    }
}
