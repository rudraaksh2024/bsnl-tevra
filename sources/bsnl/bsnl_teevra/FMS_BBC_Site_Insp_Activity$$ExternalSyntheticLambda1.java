package bsnl.bsnl_teevra;

import android.widget.NumberPicker;
import android.widget.TextView;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FMS_BBC_Site_Insp_Activity$$ExternalSyntheticLambda1 implements NumberPicker.OnValueChangeListener {
    public final /* synthetic */ NumberPicker f$0;
    public final /* synthetic */ NumberPicker f$1;
    public final /* synthetic */ String[] f$2;
    public final /* synthetic */ TextView f$3;

    public /* synthetic */ FMS_BBC_Site_Insp_Activity$$ExternalSyntheticLambda1(NumberPicker numberPicker, NumberPicker numberPicker2, String[] strArr, TextView textView) {
        this.f$0 = numberPicker;
        this.f$1 = numberPicker2;
        this.f$2 = strArr;
        this.f$3 = textView;
    }

    public final void onValueChange(NumberPicker numberPicker, int i, int i2) {
        FMS_BBC_Site_Insp_Activity.lambda$onClick$1(this.f$0, this.f$1, this.f$2, this.f$3, numberPicker, i, i2);
    }
}
