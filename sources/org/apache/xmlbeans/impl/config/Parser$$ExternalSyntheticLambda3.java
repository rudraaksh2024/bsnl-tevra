package org.apache.xmlbeans.impl.config;

import com.github.javaparser.ast.CompilationUnit;
import java.util.function.Function;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Parser$$ExternalSyntheticLambda3 implements Function {
    public final /* synthetic */ String f$0;

    public /* synthetic */ Parser$$ExternalSyntheticLambda3(String str) {
        this.f$0 = str;
    }

    public final Object apply(Object obj) {
        return ((CompilationUnit) obj).getTypes().stream().filter(Parser.matchType(this.f$0)).map(new Parser$$ExternalSyntheticLambda0()).findFirst();
    }
}
