package org.apache.poi.sl.usermodel;

import java.io.File;
import org.apache.poi.sl.usermodel.SlideShowFactory;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SlideShowFactory$$ExternalSyntheticLambda3 implements SlideShowFactory.ProviderMethod {
    public final /* synthetic */ File f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ SlideShowFactory$$ExternalSyntheticLambda3(File file, String str, boolean z) {
        this.f$0 = file;
        this.f$1 = str;
        this.f$2 = z;
    }

    public final SlideShow create(SlideShowProvider slideShowProvider) {
        return slideShowProvider.create(this.f$0, this.f$1, this.f$2);
    }
}
