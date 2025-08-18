package org.apache.xmlbeans.impl.config;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import java.util.function.Function;
import org.apache.xmlbeans.XmlObject;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class InterfaceExtensionImpl$$ExternalSyntheticLambda4 implements Function {
    public final /* synthetic */ InterfaceExtensionImpl f$0;
    public final /* synthetic */ ClassOrInterfaceDeclaration f$1;
    public final /* synthetic */ ClassOrInterfaceDeclaration f$2;
    public final /* synthetic */ XmlObject f$3;

    public /* synthetic */ InterfaceExtensionImpl$$ExternalSyntheticLambda4(InterfaceExtensionImpl interfaceExtensionImpl, ClassOrInterfaceDeclaration classOrInterfaceDeclaration, ClassOrInterfaceDeclaration classOrInterfaceDeclaration2, XmlObject xmlObject) {
        this.f$0 = interfaceExtensionImpl;
        this.f$1 = classOrInterfaceDeclaration;
        this.f$2 = classOrInterfaceDeclaration2;
        this.f$3 = xmlObject;
    }

    public final Object apply(Object obj) {
        return this.f$0.m2316lambda$validateMethods$0$orgapachexmlbeansimplconfigInterfaceExtensionImpl(this.f$1, this.f$2, this.f$3, (MethodDeclaration) obj);
    }
}
