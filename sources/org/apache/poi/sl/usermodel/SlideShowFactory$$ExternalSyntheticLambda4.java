package org.apache.poi.sl.usermodel;

import java.io.InputStream;
import org.apache.poi.sl.usermodel.SlideShowFactory;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SlideShowFactory$$ExternalSyntheticLambda4 implements SlideShowFactory.ProviderMethod {
    public final /* synthetic */ InputStream f$0;

    public /* synthetic */ SlideShowFactory$$ExternalSyntheticLambda4(InputStream inputStream) {
        this.f$0 = inputStream;
    }

    public final SlideShow create(SlideShowProvider slideShowProvider) {
        return slideShowProvider.create(this.f$0);
    }
}
