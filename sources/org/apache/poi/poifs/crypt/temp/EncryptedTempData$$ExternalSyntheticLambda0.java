package org.apache.poi.poifs.crypt.temp;

import java.io.File;
import org.apache.logging.log4j.util.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EncryptedTempData$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ File f$0;

    public /* synthetic */ EncryptedTempData$$ExternalSyntheticLambda0(File file) {
        this.f$0 = file;
    }

    public final Object get() {
        return this.f$0.getAbsolutePath();
    }
}
