package org.apache.poi.xslf.usermodel;

import java.util.function.Consumer;
import org.openxmlformats.schemas.presentationml.x2006.main.CTHeaderFooter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XSLFPlaceholderDetails$$ExternalSyntheticLambda7 implements Consumer {
    public final /* synthetic */ CTHeaderFooter f$0;

    public /* synthetic */ XSLFPlaceholderDetails$$ExternalSyntheticLambda7(CTHeaderFooter cTHeaderFooter) {
        this.f$0 = cTHeaderFooter;
    }

    public final void accept(Object obj) {
        this.f$0.setHdr(((Boolean) obj).booleanValue());
    }
}
