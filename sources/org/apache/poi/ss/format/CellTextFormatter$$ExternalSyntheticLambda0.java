package org.apache.poi.ss.format;

import java.util.regex.Matcher;
import org.apache.poi.ss.format.CellFormatPart;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CellTextFormatter$$ExternalSyntheticLambda0 implements CellFormatPart.PartHandler {
    public final /* synthetic */ int[] f$0;

    public /* synthetic */ CellTextFormatter$$ExternalSyntheticLambda0(int[] iArr) {
        this.f$0 = iArr;
    }

    public final String handlePart(Matcher matcher, String str, CellFormatType cellFormatType, StringBuffer stringBuffer) {
        return CellTextFormatter.lambda$new$0(this.f$0, matcher, str, cellFormatType, stringBuffer);
    }
}
