package org.apache.poi.extractor;

import java.io.File;
import org.apache.poi.extractor.ExtractorFactory;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExtractorFactory$$ExternalSyntheticLambda0 implements ExtractorFactory.ProviderMethod {
    public final /* synthetic */ File f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ ExtractorFactory$$ExternalSyntheticLambda0(File file, String str) {
        this.f$0 = file;
        this.f$1 = str;
    }

    public final POITextExtractor create(ExtractorProvider extractorProvider) {
        return extractorProvider.create(this.f$0, this.f$1);
    }
}
