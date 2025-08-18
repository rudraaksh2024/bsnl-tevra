package org.apache.poi.extractor;

import java.util.function.Predicate;
import org.apache.poi.poifs.filesystem.Entry;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExtractorFactory$$ExternalSyntheticLambda3 implements Predicate {
    public final boolean test(Object obj) {
        return ((Entry) obj).getName().startsWith("MBD");
    }
}
