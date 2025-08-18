package org.apache.xmlbeans.impl.config;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;
import kotlin.text.Typography;
import org.apache.xmlbeans.InterfaceExtension;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig;

public class InterfaceExtensionImpl implements InterfaceExtension {
    private String _delegateToClassName;
    private String _interfaceClassName;
    private MethodSignatureImpl[] _methods;
    private NameSet _xbeanSet;

    static InterfaceExtensionImpl newInstance(Parser parser, NameSet nameSet, Extensionconfig.Interface interfaceR) {
        InterfaceExtensionImpl interfaceExtensionImpl = new InterfaceExtensionImpl();
        interfaceExtensionImpl._xbeanSet = nameSet;
        ClassOrInterfaceDeclaration validateInterface = validateInterface(parser, interfaceR.getName(), interfaceR);
        if (validateInterface == null) {
            BindingConfigImpl.error("Interface '" + interfaceR.getStaticHandler() + "' not found.", interfaceR);
            return null;
        }
        interfaceExtensionImpl._interfaceClassName = (String) validateInterface.getFullyQualifiedName().get();
        String staticHandler = interfaceR.getStaticHandler();
        interfaceExtensionImpl._delegateToClassName = staticHandler;
        ClassOrInterfaceDeclaration validateClass = validateClass(parser, staticHandler, interfaceR);
        if (validateClass == null) {
            BindingConfigImpl.warning("Handler class '" + interfaceR.getStaticHandler() + "' not found on classpath, skip validation.", interfaceR);
            return interfaceExtensionImpl;
        } else if (!interfaceExtensionImpl.validateMethods(validateInterface, validateClass, interfaceR)) {
            return null;
        } else {
            return interfaceExtensionImpl;
        }
    }

    private static ClassOrInterfaceDeclaration validateInterface(Parser parser, String str, XmlObject xmlObject) {
        return validateJava(parser, str, true, xmlObject);
    }

    static ClassOrInterfaceDeclaration validateClass(Parser parser, String str, XmlObject xmlObject) {
        return validateJava(parser, str, false, xmlObject);
    }

    static ClassOrInterfaceDeclaration validateJava(Parser parser, String str, boolean z, XmlObject xmlObject) {
        if (parser == null) {
            return null;
        }
        String str2 = z ? "Interface" : "Class";
        ClassOrInterfaceDeclaration loadSource = parser.loadSource(str);
        if (loadSource == null) {
            BindingConfigImpl.error(str2 + " '" + str + "' not found.", xmlObject);
            return null;
        }
        if (z != loadSource.isInterface()) {
            BindingConfigImpl.error("'" + str + "' must be " + (z ? "an interface" : "a class") + ".", xmlObject);
        }
        if (!loadSource.isPublic()) {
            BindingConfigImpl.error(str2 + " '" + str + "' is not public.", xmlObject);
        }
        return loadSource;
    }

    private boolean validateMethods(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, ClassOrInterfaceDeclaration classOrInterfaceDeclaration2, XmlObject xmlObject) {
        MethodSignatureImpl[] methodSignatureImplArr = (MethodSignatureImpl[]) classOrInterfaceDeclaration.getMethods().stream().map(new InterfaceExtensionImpl$$ExternalSyntheticLambda4(this, classOrInterfaceDeclaration, classOrInterfaceDeclaration2, xmlObject)).map(new InterfaceExtensionImpl$$ExternalSyntheticLambda5(this)).toArray(new InterfaceExtensionImpl$$ExternalSyntheticLambda6());
        this._methods = methodSignatureImplArr;
        return Stream.of(methodSignatureImplArr).allMatch(new InterfaceExtensionImpl$$ExternalSyntheticLambda7());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$validateMethods$1$org-apache-xmlbeans-impl-config-InterfaceExtensionImpl  reason: not valid java name */
    public /* synthetic */ MethodSignatureImpl m2317lambda$validateMethods$1$orgapachexmlbeansimplconfigInterfaceExtensionImpl(MethodDeclaration methodDeclaration) {
        if (methodDeclaration == null) {
            return null;
        }
        return new MethodSignatureImpl(getStaticHandler(), methodDeclaration);
    }

    static /* synthetic */ MethodSignatureImpl[] lambda$validateMethods$2(int i) {
        return new MethodSignatureImpl[i];
    }

    /* access modifiers changed from: private */
    /* renamed from: validateMethod */
    public MethodDeclaration m2316lambda$validateMethods$0$orgapachexmlbeansimplconfigInterfaceExtensionImpl(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, ClassOrInterfaceDeclaration classOrInterfaceDeclaration2, MethodDeclaration methodDeclaration, XmlObject xmlObject) {
        String asString = methodDeclaration.getName().asString();
        MethodDeclaration method = getMethod(classOrInterfaceDeclaration2, asString, (String[]) Stream.concat(Stream.of("org.apache.xmlbeans.XmlObject"), Stream.of(paramStrings(methodDeclaration.getParameters()))).toArray(new InterfaceExtensionImpl$$ExternalSyntheticLambda8()));
        String str = (String) classOrInterfaceDeclaration2.getFullyQualifiedName().orElse("");
        String str2 = asString + "(" + methodDeclaration.getParameters().toString() + ")";
        String str3 = (String) classOrInterfaceDeclaration.getFullyQualifiedName().orElse("");
        if (method == null) {
            BindingConfigImpl.error("Handler class '" + str + "' does not contain method " + str2, xmlObject);
            return null;
        } else if (!Arrays.equals(exceptionStrings(methodDeclaration), exceptionStrings(method))) {
            BindingConfigImpl.error("Handler method '" + str + "." + asString + "' must declare the same exceptions as the interface method '" + str3 + "." + str2, xmlObject);
            return null;
        } else if (!method.isPublic() || !method.isStatic()) {
            BindingConfigImpl.error("Method '" + classOrInterfaceDeclaration2.getFullyQualifiedName() + "." + str2 + "' must be declared public and static.", xmlObject);
            return null;
        } else {
            String typeAsString = methodDeclaration.getTypeAsString();
            if (typeAsString.equals(method.getTypeAsString())) {
                return methodDeclaration;
            }
            BindingConfigImpl.error("Return type for method '" + typeAsString + " " + str + "." + asString + "(...)' does not match the return type of the interface method :'" + typeAsString + "'.", xmlObject);
            return null;
        }
    }

    static /* synthetic */ String[] lambda$validateMethod$3(int i) {
        return new String[i];
    }

    static MethodDeclaration getMethod(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, String str, String[] strArr) {
        return (MethodDeclaration) classOrInterfaceDeclaration.getMethodsByName(str).stream().filter(new InterfaceExtensionImpl$$ExternalSyntheticLambda9(strArr)).findFirst().orElse((Object) null);
    }

    static /* synthetic */ String[] lambda$paramStrings$5(int i) {
        return new String[i];
    }

    private static String[] paramStrings(NodeList<Parameter> nodeList) {
        return (String[]) nodeList.stream().map(new InterfaceExtensionImpl$$ExternalSyntheticLambda2()).toArray(new InterfaceExtensionImpl$$ExternalSyntheticLambda3());
    }

    private static String[] exceptionStrings(MethodDeclaration methodDeclaration) {
        return (String[]) methodDeclaration.getThrownExceptions().stream().map(new InterfaceExtensionImpl$$ExternalSyntheticLambda0()).toArray(new InterfaceExtensionImpl$$ExternalSyntheticLambda1());
    }

    static /* synthetic */ String[] lambda$exceptionStrings$6(int i) {
        return new String[i];
    }

    /* access modifiers changed from: private */
    public static boolean parameterMatches(String[] strArr, String[] strArr2) {
        if (strArr.length != strArr2.length) {
            return false;
        }
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            String str2 = strArr2[i];
            if (!str.contains(".")) {
                String str3 = str2;
                str2 = str;
                str = str3;
            }
            if (!str.endsWith(str2)) {
                return false;
            }
        }
        return true;
    }

    public boolean contains(String str) {
        return this._xbeanSet.contains(str);
    }

    public String getStaticHandler() {
        return this._delegateToClassName;
    }

    public String getInterface() {
        return this._interfaceClassName;
    }

    public InterfaceExtension.MethodSignature[] getMethods() {
        return this._methods;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("  static handler: ");
        sb.append(this._delegateToClassName).append("\n  interface: ");
        sb.append(this._interfaceClassName).append("\n  name set: ");
        sb.append(this._xbeanSet).append("\n");
        for (int i = 0; i < this._methods.length; i++) {
            sb.append("  method[").append(i).append("]=").append(this._methods[i]).append("\n");
        }
        return sb.toString();
    }

    static class MethodSignatureImpl implements InterfaceExtension.MethodSignature {
        private final int NOTINITIALIZED = -1;
        private final String[] _exceptions;
        private int _hashCode = -1;
        private final String _intfName;
        private final String _name;
        private final String[] _params;
        private final String _return;
        private String _signature;

        MethodSignatureImpl(String str, MethodDeclaration methodDeclaration) {
            if (str == null || methodDeclaration == null) {
                throw new IllegalArgumentException("Interface: " + str + " method: " + methodDeclaration);
            }
            this._intfName = str;
            this._signature = null;
            this._name = methodDeclaration.getName().asString();
            this._return = replaceInner(methodDeclaration.getTypeAsString());
            this._params = (String[]) methodDeclaration.getParameters().stream().map(new InterfaceExtensionImpl$$ExternalSyntheticLambda2()).map(new InterfaceExtensionImpl$MethodSignatureImpl$$ExternalSyntheticLambda0()).toArray(new InterfaceExtensionImpl$MethodSignatureImpl$$ExternalSyntheticLambda1());
            this._exceptions = (String[]) methodDeclaration.getThrownExceptions().stream().map(new InterfaceExtensionImpl$MethodSignatureImpl$$ExternalSyntheticLambda2()).map(new InterfaceExtensionImpl$MethodSignatureImpl$$ExternalSyntheticLambda0()).toArray(new InterfaceExtensionImpl$MethodSignatureImpl$$ExternalSyntheticLambda3());
        }

        static /* synthetic */ String[] lambda$new$0(int i) {
            return new String[i];
        }

        static /* synthetic */ String[] lambda$new$1(int i) {
            return new String[i];
        }

        /* access modifiers changed from: private */
        public static String replaceInner(String str) {
            return str.replace(Typography.dollar, '.');
        }

        /* access modifiers changed from: package-private */
        public String getInterfaceName() {
            return this._intfName;
        }

        public String getName() {
            return this._name;
        }

        public String getReturnType() {
            return this._return;
        }

        public String[] getParameterTypes() {
            return this._params;
        }

        public String[] getExceptionTypes() {
            return this._exceptions;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof MethodSignatureImpl)) {
                return false;
            }
            MethodSignatureImpl methodSignatureImpl = (MethodSignatureImpl) obj;
            if (!methodSignatureImpl.getName().equals(getName()) || !this._intfName.equals(methodSignatureImpl._intfName) || !Arrays.equals(getParameterTypes(), methodSignatureImpl.getParameterTypes())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i = this._hashCode;
            if (i != -1) {
                return i;
            }
            int hash = Objects.hash(new Object[]{getName(), Integer.valueOf(Arrays.hashCode(getParameterTypes())), this._intfName});
            this._hashCode = hash;
            return hash;
        }

        /* access modifiers changed from: package-private */
        public String getSignature() {
            String str = this._signature;
            if (str != null) {
                return str;
            }
            String str2 = this._name + "(" + String.join(" ,", this._params) + ")";
            this._signature = str2;
            return str2;
        }

        public String toString() {
            return getReturnType() + " " + getSignature();
        }
    }
}
