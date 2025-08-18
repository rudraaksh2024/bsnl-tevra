package org.apache.poi.xslf.usermodel;

import java.util.function.Consumer;
import org.apache.poi.xslf.model.CharacterPropertyFetcher;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XSLFTextRun$XSLFFontInfo$$ExternalSyntheticLambda0 implements CharacterPropertyFetcher.CharPropFetcher {
    public final /* synthetic */ XSLFTextRun.XSLFFontInfo f$0;

    public /* synthetic */ XSLFTextRun$XSLFFontInfo$$ExternalSyntheticLambda0(XSLFTextRun.XSLFFontInfo xSLFFontInfo) {
        this.f$0 = xSLFFontInfo;
    }

    public final void fetch(CTTextCharacterProperties cTTextCharacterProperties, Consumer consumer) {
        this.f$0.m2309lambda$getXmlObject$0$orgapachepoixslfusermodelXSLFTextRun$XSLFFontInfo(cTTextCharacterProperties, consumer);
    }
}
