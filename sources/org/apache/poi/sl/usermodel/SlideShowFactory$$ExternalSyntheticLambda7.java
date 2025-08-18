package org.apache.poi.sl.usermodel;

import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.sl.usermodel.SlideShowFactory;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SlideShowFactory$$ExternalSyntheticLambda7 implements SlideShowFactory.ProviderMethod {
    public final /* synthetic */ DirectoryNode f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ SlideShowFactory$$ExternalSyntheticLambda7(DirectoryNode directoryNode, String str) {
        this.f$0 = directoryNode;
        this.f$1 = str;
    }

    public final SlideShow create(SlideShowProvider slideShowProvider) {
        return slideShowProvider.create(this.f$0, this.f$1);
    }
}
