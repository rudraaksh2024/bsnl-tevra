package org.apache.xmlbeans.impl.config;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.type.PrimitiveType;
import org.apache.xmlbeans.PrePostExtension;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig;

public class PrePostExtensionImpl implements PrePostExtension {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String[] PARAMTYPES_STRING;
    private static final String SIGNATURE;
    private ClassOrInterfaceDeclaration _delegateToClass;
    private String _delegateToClassName;
    private MethodDeclaration _postSet;
    private MethodDeclaration _preSet;
    private NameSet _xbeanSet;

    static {
        String[] strArr = {XmlErrorCodes.INT, "org.apache.xmlbeans.XmlObject", "javax.xml.namespace.QName", "boolean", XmlErrorCodes.INT};
        PARAMTYPES_STRING = strArr;
        SIGNATURE = "(" + String.join(", ", strArr) + ")";
    }

    static PrePostExtensionImpl newInstance(Parser parser, NameSet nameSet, Extensionconfig.PrePostSet prePostSet) {
        if (prePostSet == null) {
            return null;
        }
        PrePostExtensionImpl prePostExtensionImpl = new PrePostExtensionImpl();
        prePostExtensionImpl._xbeanSet = nameSet;
        String staticHandler = prePostSet.getStaticHandler();
        prePostExtensionImpl._delegateToClassName = staticHandler;
        ClassOrInterfaceDeclaration validateClass = InterfaceExtensionImpl.validateClass(parser, staticHandler, prePostSet);
        prePostExtensionImpl._delegateToClass = validateClass;
        if (validateClass == null) {
            BindingConfigImpl.warning("Handler class '" + prePostSet.getStaticHandler() + "' not found on classpath, skip validation.", prePostSet);
            return prePostExtensionImpl;
        } else if (!prePostExtensionImpl.lookAfterPreAndPost(parser, prePostSet)) {
            return null;
        } else {
            return prePostExtensionImpl;
        }
    }

    private boolean lookAfterPreAndPost(Parser parser, XmlObject xmlObject) {
        ClassOrInterfaceDeclaration classOrInterfaceDeclaration = this._delegateToClass;
        String[] strArr = PARAMTYPES_STRING;
        MethodDeclaration method = InterfaceExtensionImpl.getMethod(classOrInterfaceDeclaration, "preSet", strArr);
        this._preSet = method;
        if (method != null && !method.getType().equals(PrimitiveType.booleanType())) {
            BindingConfigImpl.warning("Method '" + this._delegateToClass.getNameAsString() + ".preSet" + SIGNATURE + "' should return boolean to be considered for a preSet handler.", xmlObject);
            this._preSet = null;
        }
        MethodDeclaration method2 = InterfaceExtensionImpl.getMethod(this._delegateToClass, "postSet", strArr);
        this._postSet = method2;
        if (this._preSet != null || method2 != null) {
            return true;
        }
        StringBuilder append = new StringBuilder("prePostSet handler specified '").append(this._delegateToClass.getNameAsString()).append("' but no preSet");
        String str = SIGNATURE;
        BindingConfigImpl.error(append.append(str).append(" or postSet").append(str).append(" methods found.").toString(), xmlObject);
        return false;
    }

    public NameSet getNameSet() {
        return this._xbeanSet;
    }

    public boolean contains(String str) {
        return this._xbeanSet.contains(str);
    }

    public boolean hasPreCall() {
        return this._preSet != null;
    }

    public boolean hasPostCall() {
        return this._postSet != null;
    }

    public String getStaticHandler() {
        return this._delegateToClassName;
    }

    public String getHandlerNameForJavaSource() {
        ClassOrInterfaceDeclaration classOrInterfaceDeclaration = this._delegateToClass;
        if (classOrInterfaceDeclaration == null) {
            return null;
        }
        return classOrInterfaceDeclaration.getNameAsString();
    }

    /* access modifiers changed from: package-private */
    public boolean hasNameSetIntersection(PrePostExtensionImpl prePostExtensionImpl) {
        return !NameSet.EMPTY.equals(this._xbeanSet.intersect(prePostExtensionImpl._xbeanSet));
    }
}
