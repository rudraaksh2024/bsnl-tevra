package org.apache.commons.io.input;

import java.util.Set;
import java.util.function.IntPredicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CharacterSetFilterReader$$ExternalSyntheticLambda0 implements IntPredicate {
    public final /* synthetic */ Set f$0;

    public /* synthetic */ CharacterSetFilterReader$$ExternalSyntheticLambda0(Set set) {
        this.f$0 = set;
    }

    public final boolean test(int i) {
        return this.f$0.contains(Integer.valueOf(i));
    }
}
