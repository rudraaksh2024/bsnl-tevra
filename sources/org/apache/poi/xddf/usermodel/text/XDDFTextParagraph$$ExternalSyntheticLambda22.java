package org.apache.poi.xddf.usermodel.text;

import java.util.function.Function;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XDDFTextParagraph$$ExternalSyntheticLambda22 implements Function {
    public final /* synthetic */ XDDFTextParagraph f$0;

    public /* synthetic */ XDDFTextParagraph$$ExternalSyntheticLambda22(XDDFTextParagraph xDDFTextParagraph) {
        this.f$0 = xDDFTextParagraph;
    }

    public final Object apply(Object obj) {
        return this.f$0.extractSpacing((CTTextSpacing) obj);
    }
}
