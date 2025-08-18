package org.apache.poi.sl.usermodel;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.sl.usermodel.SlideShowFactory;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SlideShowFactory$$ExternalSyntheticLambda5 implements SlideShowFactory.ProviderMethod {
    public final /* synthetic */ POIFSFileSystem f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ SlideShowFactory$$ExternalSyntheticLambda5(POIFSFileSystem pOIFSFileSystem, String str) {
        this.f$0 = pOIFSFileSystem;
        this.f$1 = str;
    }

    public final SlideShow create(SlideShowProvider slideShowProvider) {
        return slideShowProvider.create(this.f$0.getRoot(), this.f$1);
    }
}
