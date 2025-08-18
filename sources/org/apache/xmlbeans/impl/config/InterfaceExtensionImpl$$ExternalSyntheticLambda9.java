package org.apache.xmlbeans.impl.config;

import com.github.javaparser.ast.body.MethodDeclaration;
import java.util.function.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class InterfaceExtensionImpl$$ExternalSyntheticLambda9 implements Predicate {
    public final /* synthetic */ String[] f$0;

    public /* synthetic */ InterfaceExtensionImpl$$ExternalSyntheticLambda9(String[] strArr) {
        this.f$0 = strArr;
    }

    public final boolean test(Object obj) {
        return InterfaceExtensionImpl.parameterMatches(InterfaceExtensionImpl.paramStrings(((MethodDeclaration) obj).getParameters()), this.f$0);
    }
}
