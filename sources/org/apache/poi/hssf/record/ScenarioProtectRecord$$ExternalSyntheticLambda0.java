package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ScenarioProtectRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ ScenarioProtectRecord f$0;

    public /* synthetic */ ScenarioProtectRecord$$ExternalSyntheticLambda0(ScenarioProtectRecord scenarioProtectRecord) {
        this.f$0 = scenarioProtectRecord;
    }

    public final Object get() {
        return Boolean.valueOf(this.f$0.getProtect());
    }
}
