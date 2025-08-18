package org.apache.xmlbeans.impl.config;

import com.github.javaparser.ast.body.TypeDeclaration;
import java.util.function.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Parser$$ExternalSyntheticLambda1 implements Predicate {
    public final /* synthetic */ String f$0;

    public /* synthetic */ Parser$$ExternalSyntheticLambda1(String str) {
        this.f$0 = str;
    }

    public final boolean test(Object obj) {
        return Parser.lambda$matchType$3(this.f$0, (TypeDeclaration) obj);
    }
}
