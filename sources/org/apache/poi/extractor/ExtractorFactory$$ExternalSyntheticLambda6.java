package org.apache.poi.extractor;

import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.poifs.filesystem.DirectoryNode;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExtractorFactory$$ExternalSyntheticLambda6 implements ExtractorFactory.ProviderMethod {
    public final /* synthetic */ DirectoryNode f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ ExtractorFactory$$ExternalSyntheticLambda6(DirectoryNode directoryNode, String str) {
        this.f$0 = directoryNode;
        this.f$1 = str;
    }

    public final POITextExtractor create(ExtractorProvider extractorProvider) {
        return extractorProvider.create(this.f$0, this.f$1);
    }
}
