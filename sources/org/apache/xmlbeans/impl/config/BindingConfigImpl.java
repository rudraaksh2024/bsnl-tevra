package org.apache.xmlbeans.impl.config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.BindingConfig;
import org.apache.xmlbeans.InterfaceExtension;
import org.apache.xmlbeans.PrePostExtension;
import org.apache.xmlbeans.UserType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.config.InterfaceExtensionImpl;
import org.apache.xmlbeans.impl.schema.StscState;
import org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument;
import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.Qnametargetenum;
import org.apache.xmlbeans.impl.xb.xmlconfig.Usertypeconfig;

public class BindingConfigImpl extends BindingConfig {
    private final List<InterfaceExtensionImpl> _interfaceExtensions = new ArrayList();
    private final Map _packageMap = new LinkedHashMap();
    private final Map<Object, String> _packageMapByUriPrefix = new LinkedHashMap();
    private final List<PrePostExtensionImpl> _prePostExtensions = new ArrayList();
    private final Map _prefixMap = new LinkedHashMap();
    private final Map<Object, String> _prefixMapByUriPrefix = new LinkedHashMap();
    private final Map<QName, String> _qnameAttMap = new LinkedHashMap();
    private final Map<QName, String> _qnameDocTypeMap = new LinkedHashMap();
    private final Map<QName, String> _qnameElemMap = new LinkedHashMap();
    private final Map<QName, String> _qnameTypeMap = new LinkedHashMap();
    private final Map _suffixMap = new LinkedHashMap();
    private final Map<Object, String> _suffixMapByUriPrefix = new LinkedHashMap();
    private final Map<QName, UserTypeImpl> _userTypes = new LinkedHashMap();

    public static BindingConfig forConfigDocuments(ConfigDocument.Config[] configArr, File[] fileArr, File[] fileArr2) {
        return new BindingConfigImpl(configArr, fileArr, fileArr2);
    }

    private BindingConfigImpl(ConfigDocument.Config[] configArr, File[] fileArr, File[] fileArr2) {
        for (ConfigDocument.Config config : configArr) {
            for (Nsconfig nsconfig : config.getNamespaceArray()) {
                recordNamespaceSetting(nsconfig.getUri(), nsconfig.getPackage(), this._packageMap);
                recordNamespaceSetting(nsconfig.getUri(), nsconfig.getPrefix(), this._prefixMap);
                recordNamespaceSetting(nsconfig.getUri(), nsconfig.getSuffix(), this._suffixMap);
                recordNamespacePrefixSetting(nsconfig.getUriprefix(), nsconfig.getPackage(), this._packageMapByUriPrefix);
                recordNamespacePrefixSetting(nsconfig.getUriprefix(), nsconfig.getPrefix(), this._prefixMapByUriPrefix);
                recordNamespacePrefixSetting(nsconfig.getUriprefix(), nsconfig.getSuffix(), this._suffixMapByUriPrefix);
            }
            for (Qnameconfig qnameconfig : config.getQnameArray()) {
                List<Qnametargetenum> xgetListValue = qnameconfig.xgetTarget().xgetListValue();
                QName name = qnameconfig.getName();
                String javaname = qnameconfig.getJavaname();
                for (Qnametargetenum enumValue : xgetListValue) {
                    int intValue = enumValue.getEnumValue().intValue();
                    if (intValue == 1) {
                        this._qnameTypeMap.put(name, javaname);
                    } else if (intValue == 2) {
                        this._qnameDocTypeMap.put(name, javaname);
                    } else if (intValue == 3) {
                        this._qnameElemMap.put(name, javaname);
                    } else if (intValue == 4) {
                        this._qnameAttMap.put(name, javaname);
                    }
                }
            }
            for (Extensionconfig recordExtensionSetting : config.getExtensionArray()) {
                recordExtensionSetting(fileArr, fileArr2, recordExtensionSetting);
            }
            for (Usertypeconfig recordUserTypeSetting : config.getUsertypeArray()) {
                recordUserTypeSetting(fileArr, fileArr2, recordUserTypeSetting);
            }
        }
        secondPhaseValidation();
    }

    /* access modifiers changed from: package-private */
    public void addInterfaceExtension(InterfaceExtensionImpl interfaceExtensionImpl) {
        if (interfaceExtensionImpl != null) {
            this._interfaceExtensions.add(interfaceExtensionImpl);
        }
    }

    /* access modifiers changed from: package-private */
    public void addPrePostExtension(PrePostExtensionImpl prePostExtensionImpl) {
        if (prePostExtensionImpl != null) {
            this._prePostExtensions.add(prePostExtensionImpl);
        }
    }

    /* access modifiers changed from: package-private */
    public void secondPhaseValidation() {
        HashMap hashMap = new HashMap();
        Iterator<InterfaceExtensionImpl> it = this._interfaceExtensions.iterator();
        while (true) {
            int i = 0;
            if (it.hasNext()) {
                InterfaceExtensionImpl.MethodSignatureImpl[] methodSignatureImplArr = (InterfaceExtensionImpl.MethodSignatureImpl[]) it.next().getMethods();
                int length = methodSignatureImplArr.length;
                while (true) {
                    if (i < length) {
                        InterfaceExtensionImpl.MethodSignatureImpl methodSignatureImpl = methodSignatureImplArr[i];
                        if (hashMap.containsKey(methodSignatureImpl)) {
                            InterfaceExtensionImpl.MethodSignatureImpl methodSignatureImpl2 = (InterfaceExtensionImpl.MethodSignatureImpl) hashMap.get(methodSignatureImpl);
                            if (!methodSignatureImpl.getReturnType().equals(methodSignatureImpl2.getReturnType())) {
                                error("Colliding methods '" + methodSignatureImpl.getSignature() + "' in interfaces " + methodSignatureImpl.getInterfaceName() + " and " + methodSignatureImpl2.getInterfaceName() + ".", (XmlObject) null);
                                return;
                            }
                            return;
                        }
                        hashMap.put(methodSignatureImpl, methodSignatureImpl);
                        i++;
                    }
                }
            } else {
                while (true) {
                    if (i < this._prePostExtensions.size() - 1) {
                        PrePostExtensionImpl prePostExtensionImpl = this._prePostExtensions.get(i);
                        for (int i2 = 1; i2 < this._prePostExtensions.size(); i2++) {
                            PrePostExtensionImpl prePostExtensionImpl2 = this._prePostExtensions.get(i2);
                            if (prePostExtensionImpl.hasNameSetIntersection(prePostExtensionImpl2)) {
                                error("The applicable domain for handler '" + prePostExtensionImpl.getHandlerNameForJavaSource() + "' intersects with the one for '" + prePostExtensionImpl2.getHandlerNameForJavaSource() + "'.", (XmlObject) null);
                            }
                        }
                        i++;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    private static void recordNamespaceSetting(Object obj, String str, Map<Object, String> map) {
        if (str != null) {
            if (obj == null) {
                map.put("", str);
            } else if ((obj instanceof String) && "##any".equals(obj)) {
                map.put(obj, str);
            } else if (obj instanceof List) {
                ((List) obj).forEach(new BindingConfigImpl$$ExternalSyntheticLambda2(map, str));
            }
        }
    }

    static /* synthetic */ void lambda$recordNamespaceSetting$0(Map map, String str, Object obj) {
        if ("##local".equals(obj)) {
            obj = "";
        }
        String str2 = (String) map.put(obj, str);
    }

    static /* synthetic */ void lambda$recordNamespacePrefixSetting$1(Map map, String str, Object obj) {
        String str2 = (String) map.put(obj, str);
    }

    private static void recordNamespacePrefixSetting(List list, String str, Map<Object, String> map) {
        if (str != null && list != null) {
            list.forEach(new BindingConfigImpl$$ExternalSyntheticLambda4(map, str));
        }
    }

    private void recordExtensionSetting(File[] fileArr, File[] fileArr2, Extensionconfig extensionconfig) {
        NameSet nameSet;
        Object obj = extensionconfig.getFor();
        if ((obj instanceof String) && "*".equals(obj)) {
            nameSet = NameSet.EVERYTHING;
        } else if (obj instanceof List) {
            NameSetBuilder nameSetBuilder = new NameSetBuilder();
            for (String add : (List) obj) {
                nameSetBuilder.add(add);
            }
            nameSet = nameSetBuilder.toNameSet();
        } else {
            nameSet = null;
        }
        if (nameSet == null) {
            error("Invalid value of attribute 'for' : '" + obj + "'.", extensionconfig);
        }
        Extensionconfig.Interface[] interfaceArray = extensionconfig.getInterfaceArray();
        Extensionconfig.PrePostSet prePostSet = extensionconfig.getPrePostSet();
        Parser parser = new Parser(fileArr, fileArr2);
        if (interfaceArray.length > 0 || prePostSet != null) {
            for (Extensionconfig.Interface newInstance : interfaceArray) {
                addInterfaceExtension(InterfaceExtensionImpl.newInstance(parser, nameSet, newInstance));
            }
            addPrePostExtension(PrePostExtensionImpl.newInstance(parser, nameSet, prePostSet));
        }
    }

    private void recordUserTypeSetting(File[] fileArr, File[] fileArr2, Usertypeconfig usertypeconfig) {
        UserTypeImpl newInstance = UserTypeImpl.newInstance(new Parser(fileArr, fileArr2), usertypeconfig);
        this._userTypes.put(newInstance.getName(), newInstance);
    }

    private String lookup(Map map, Map map2, String str) {
        String lookupByUriPrefix;
        if (str == null) {
            str = "";
        }
        String str2 = (String) map.get(str);
        if (str2 != null) {
            return str2;
        }
        if (map2 == null || (lookupByUriPrefix = lookupByUriPrefix(map2, str)) == null) {
            return (String) map.get("##any");
        }
        return lookupByUriPrefix;
    }

    private String lookupByUriPrefix(Map map, String str) {
        if (str == null || map.isEmpty()) {
            return null;
        }
        String str2 = null;
        for (String str3 : map.keySet()) {
            if ((str2 == null || str3.length() >= str2.length()) && str.startsWith(str3)) {
                str2 = str3;
            }
        }
        if (str2 != null) {
            return (String) map.get(str2);
        }
        return null;
    }

    static void warning(String str, XmlObject xmlObject) {
        StscState.get().error(str, 1, xmlObject);
    }

    static void error(String str, XmlObject xmlObject) {
        StscState.get().error(str, 0, xmlObject);
    }

    public String lookupPackageForNamespace(String str) {
        return lookup(this._packageMap, this._packageMapByUriPrefix, str);
    }

    public String lookupPrefixForNamespace(String str) {
        return lookup(this._prefixMap, this._prefixMapByUriPrefix, str);
    }

    public String lookupSuffixForNamespace(String str) {
        return lookup(this._suffixMap, this._suffixMapByUriPrefix, str);
    }

    public String lookupJavanameForQName(QName qName) {
        String str = this._qnameTypeMap.get(qName);
        return str != null ? str : this._qnameDocTypeMap.get(qName);
    }

    public String lookupJavanameForQName(QName qName, int i) {
        if (i == 1) {
            return this._qnameTypeMap.get(qName);
        }
        if (i == 2) {
            return this._qnameDocTypeMap.get(qName);
        }
        if (i == 3) {
            return this._qnameElemMap.get(qName);
        }
        if (i != 4) {
            return null;
        }
        return this._qnameAttMap.get(qName);
    }

    public UserType lookupUserTypeForQName(QName qName) {
        if (qName == null) {
            return null;
        }
        return this._userTypes.get(qName);
    }

    public InterfaceExtension[] getInterfaceExtensions() {
        return (InterfaceExtension[]) this._interfaceExtensions.toArray(new InterfaceExtension[0]);
    }

    public InterfaceExtension[] getInterfaceExtensions(String str) {
        return (InterfaceExtension[]) this._interfaceExtensions.stream().filter(new BindingConfigImpl$$ExternalSyntheticLambda0(str)).toArray(new BindingConfigImpl$$ExternalSyntheticLambda1());
    }

    static /* synthetic */ InterfaceExtension[] lambda$getInterfaceExtensions$3(int i) {
        return new InterfaceExtension[i];
    }

    public PrePostExtension[] getPrePostExtensions() {
        return (PrePostExtension[]) this._prePostExtensions.toArray(new PrePostExtension[0]);
    }

    public PrePostExtension getPrePostExtension(String str) {
        return (PrePostExtension) this._prePostExtensions.stream().filter(new BindingConfigImpl$$ExternalSyntheticLambda3(str)).findFirst().orElse((Object) null);
    }
}
