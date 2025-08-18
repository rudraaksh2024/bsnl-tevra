package org.apache.xmlbeans.impl.schema;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.xml.namespace.QName;
import kotlin.text.Typography;
import org.apache.xmlbeans.BindingConfig;
import org.apache.xmlbeans.InterfaceExtension;
import org.apache.xmlbeans.PrePostExtension;
import org.apache.xmlbeans.SchemaProperty;
import org.apache.xmlbeans.SchemaStringEnumEntry;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.UserType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.NameUtil;

public class StscJavaizer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int MAX_ENUM_COUNT = 3668;
    private static final String[] PREFIXES = {"get", "xget", "isNil", "isSet", "sizeOf", "set", "xset", "addNew", "setNil", "unset", "insert", "add", "insertNew", "addNew", "remove"};
    static String[] PROTECTED_PROPERTIES = {"StringValue", "BooleanValue", "ByteValue", "ShortValue", "IntValue", "LongValue", "BigIntegerValue", "BigDecimalValue", "FloatValue", "DoubleValue", "ByteArrayValue", "EnumValue", "CalendarValue", "DateValue", "GDateValue", "GDurationValue", "QNameValue", "ListValue", "ObjectValue", "Class"};
    static Set<String> PROTECTED_PROPERTIES_SET = new HashSet(Arrays.asList(PROTECTED_PROPERTIES));

    public static void javaizeAllTypes(boolean z) {
        StscState stscState = StscState.get();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(stscState.documentTypes()));
        arrayList.addAll(Arrays.asList(stscState.attributeTypes()));
        arrayList.addAll(Arrays.asList(stscState.globalTypes()));
        if (z) {
            assignGlobalJavaNames(arrayList);
        }
        for (int i = 0; i < arrayList.size(); i++) {
            SchemaType schemaType = (SchemaType) arrayList.get(i);
            if (z) {
                javaizeType((SchemaTypeImpl) schemaType);
                String fullJavaName = schemaType.getFullJavaName();
                if (fullJavaName != null) {
                    stscState.addClassname(fullJavaName.replace(Typography.dollar, '.'), schemaType);
                }
            } else {
                skipJavaizingType((SchemaTypeImpl) schemaType);
            }
            arrayList.addAll(Arrays.asList(schemaType.getAnonymousTypes()));
            addAnonymousTypesFromRedefinition(schemaType, arrayList);
        }
    }

    static void assignGlobalJavaNames(Collection<SchemaType> collection) {
        HashSet hashSet = new HashSet();
        StscState stscState = StscState.get();
        Iterator<SchemaType> it = collection.iterator();
        while (it.hasNext()) {
            SchemaTypeImpl schemaTypeImpl = (SchemaTypeImpl) it.next();
            String javaname = stscState.getJavaname(findTopName(schemaTypeImpl), schemaTypeImpl.isDocumentType() ? 2 : 1);
            if (schemaTypeImpl.isUnjavaized()) {
                schemaTypeImpl.setFullJavaName(pickFullJavaClassName(hashSet, findTopName(schemaTypeImpl), javaname, schemaTypeImpl.isDocumentType(), schemaTypeImpl.isAttributeType()));
                schemaTypeImpl.setFullJavaImplName(pickFullJavaImplName(hashSet, schemaTypeImpl.getFullJavaName()));
                setUserTypes(schemaTypeImpl, stscState);
                setExtensions(schemaTypeImpl, stscState);
            }
        }
        verifyInterfaceNameCollisions(hashSet, stscState);
    }

    private static void verifyInterfaceNameCollisions(Set<String> set, StscState stscState) {
        BindingConfig bindingConfig = stscState.getBindingConfig();
        if (bindingConfig != null) {
            for (InterfaceExtension interfaceExtension : bindingConfig.getInterfaceExtensions()) {
                if (set.contains(interfaceExtension.getInterface().toLowerCase(Locale.ROOT))) {
                    stscState.error("InterfaceExtension interface '" + interfaceExtension.getInterface() + "' creates a name collision with one of the generated interfaces or classes.", 0, (XmlObject) null);
                }
                String staticHandler = interfaceExtension.getStaticHandler();
                if (staticHandler != null && set.contains(staticHandler.toLowerCase(Locale.ROOT))) {
                    stscState.error("InterfaceExtension handler class '" + staticHandler + "' creates a name collision with one of the generated interfaces or classes.", 0, (XmlObject) null);
                }
            }
            for (PrePostExtension staticHandler2 : bindingConfig.getPrePostExtensions()) {
                String staticHandler3 = staticHandler2.getStaticHandler();
                if (staticHandler3 != null && set.contains(staticHandler3.toLowerCase(Locale.ROOT))) {
                    stscState.error("PrePostExtension handler class '" + staticHandler3 + "' creates a name collision with one of the generated interfaces or classes.", 0, (XmlObject) null);
                }
            }
        }
    }

    private static void setUserTypes(SchemaTypeImpl schemaTypeImpl, StscState stscState) {
        UserType lookupUserTypeForQName;
        BindingConfig bindingConfig = stscState.getBindingConfig();
        if (bindingConfig != null && (lookupUserTypeForQName = bindingConfig.lookupUserTypeForQName(schemaTypeImpl.getName())) != null) {
            schemaTypeImpl.setUserTypeName(lookupUserTypeForQName.getJavaName());
            schemaTypeImpl.setUserTypeHandlerName(lookupUserTypeForQName.getStaticHandler());
        }
    }

    private static void setExtensions(SchemaTypeImpl schemaTypeImpl, StscState stscState) {
        String fullJavaName = schemaTypeImpl.getFullJavaName();
        BindingConfig bindingConfig = stscState.getBindingConfig();
        if (fullJavaName != null && bindingConfig != null) {
            schemaTypeImpl.setInterfaceExtensions(bindingConfig.getInterfaceExtensions(fullJavaName));
            schemaTypeImpl.setPrePostExtension(bindingConfig.getPrePostExtension(fullJavaName));
        }
    }

    private static boolean isStringType(SchemaType schemaType) {
        if (schemaType != null && schemaType.getSimpleVariety() == 1 && schemaType.getPrimitiveType().getBuiltinTypeCode() == 12) {
            return true;
        }
        return false;
    }

    static String pickConstantName(Set<String> set, String str) {
        String upperCaseUnderbar = NameUtil.upperCaseUnderbar(str);
        if (upperCaseUnderbar.length() == 0) {
            upperCaseUnderbar = "X";
        }
        if (upperCaseUnderbar.startsWith("INT_")) {
            upperCaseUnderbar = "X_" + upperCaseUnderbar;
        }
        String str2 = upperCaseUnderbar;
        int i = 1;
        while (set.contains(str2)) {
            i++;
            str2 = upperCaseUnderbar + "_" + i;
        }
        set.add(str2);
        return str2;
    }

    static void skipJavaizingType(SchemaTypeImpl schemaTypeImpl) {
        if (!schemaTypeImpl.isJavaized()) {
            SchemaTypeImpl schemaTypeImpl2 = (SchemaTypeImpl) schemaTypeImpl.getBaseType();
            if (schemaTypeImpl2 != null) {
                skipJavaizingType(schemaTypeImpl2);
            }
            schemaTypeImpl.startJavaizing();
            secondPassProcessType(schemaTypeImpl);
            schemaTypeImpl.finishJavaizing();
        }
    }

    static void secondPassProcessType(SchemaTypeImpl schemaTypeImpl) {
        XmlAnySimpleType[] enumerationValues;
        if (!isStringType(schemaTypeImpl) || (enumerationValues = schemaTypeImpl.getEnumerationValues()) == null) {
            return;
        }
        if (enumerationValues.length > MAX_ENUM_COUNT) {
            StscState.get().warning("SchemaType Enumeration found with too many enumeration values to create a Java enumeration. The base SchemaType \"" + schemaTypeImpl.getBaseEnumType() + "\" will be used instead", 1, (XmlObject) null);
            return;
        }
        SchemaType baseEnumType = schemaTypeImpl.getBaseEnumType();
        if (baseEnumType != null) {
            SchemaStringEnumEntry[] schemaStringEnumEntryArr = new SchemaStringEnumEntry[enumerationValues.length];
            int i = 0;
            if (baseEnumType == schemaTypeImpl) {
                HashSet hashSet = new HashSet();
                while (i < enumerationValues.length) {
                    String stringValue = enumerationValues[i].getStringValue();
                    int i2 = i + 1;
                    schemaStringEnumEntryArr[i] = new SchemaStringEnumEntryImpl(stringValue, i2, pickConstantName(hashSet, stringValue));
                    i = i2;
                }
            } else {
                while (i < enumerationValues.length) {
                    schemaStringEnumEntryArr[i] = baseEnumType.enumEntryForString(enumerationValues[i].getStringValue());
                    i++;
                }
            }
            schemaTypeImpl.setStringEnumEntries(schemaStringEnumEntryArr);
        }
    }

    static void javaizeType(SchemaTypeImpl schemaTypeImpl) {
        if (!schemaTypeImpl.isJavaized()) {
            SchemaTypeImpl schemaTypeImpl2 = (SchemaTypeImpl) schemaTypeImpl.getBaseType();
            if (schemaTypeImpl2 != null) {
                javaizeType(schemaTypeImpl2);
            }
            if (!(schemaTypeImpl.getContentBasedOnType() == null || schemaTypeImpl.getContentBasedOnType() == schemaTypeImpl2)) {
                javaizeType((SchemaTypeImpl) schemaTypeImpl.getContentBasedOnType());
            }
            schemaTypeImpl.startJavaizing();
            schemaTypeImpl.setCompiled(true);
            secondPassProcessType(schemaTypeImpl);
            if (!schemaTypeImpl.isSimpleType()) {
                SchemaProperty[] elementProperties = schemaTypeImpl.getElementProperties();
                SchemaProperty[] attributeProperties = schemaTypeImpl.getAttributeProperties();
                HashSet hashSet = new HashSet();
                for (SchemaProperty javaPropertyName : schemaTypeImpl2.getProperties()) {
                    hashSet.add(javaPropertyName.getJavaPropertyName());
                }
                avoidExtensionMethods(hashSet, schemaTypeImpl);
                boolean z = true;
                while (true) {
                    if (elementProperties.length > 0) {
                        assignJavaPropertyNames(hashSet, elementProperties, schemaTypeImpl2, z);
                    }
                    assignJavaPropertyNames(hashSet, attributeProperties, schemaTypeImpl2, z);
                    if (!z) {
                        break;
                    }
                    z = false;
                }
                SchemaProperty[] properties = schemaTypeImpl.getProperties();
                boolean isPropertyModelOrderInsensitive = isPropertyModelOrderInsensitive(properties);
                assignJavaTypeCodes(properties);
                schemaTypeImpl.setOrderSensitive(!isPropertyModelOrderInsensitive);
            }
            if (!(schemaTypeImpl.getFullJavaName() == null && schemaTypeImpl.getOuterType() == null)) {
                assignJavaAnonymousTypeNames(schemaTypeImpl);
            }
            schemaTypeImpl.finishJavaizing();
        }
    }

    private static void avoidExtensionMethods(Set<String> set, SchemaTypeImpl schemaTypeImpl) {
        InterfaceExtension[] interfaceExtensions = schemaTypeImpl.getInterfaceExtensions();
        if (interfaceExtensions != null) {
            for (InterfaceExtension methods : interfaceExtensions) {
                for (InterfaceExtension.MethodSignature name : methods.getMethods()) {
                    String name2 = name.getName();
                    for (String str : PREFIXES) {
                        if (name2.startsWith(str)) {
                            set.add(name2.substring(str.length()));
                        }
                    }
                }
            }
        }
    }

    static void assignJavaAnonymousTypeNames(SchemaTypeImpl schemaTypeImpl) {
        String str;
        String str2;
        HashSet hashSet = new HashSet();
        SchemaType[] anonymousTypes = schemaTypeImpl.getAnonymousTypes();
        StscState stscState = StscState.get();
        int length = anonymousTypes.length;
        if (schemaTypeImpl.isRedefinition()) {
            ArrayList arrayList = new ArrayList();
            addAnonymousTypesFromRedefinition(schemaTypeImpl, arrayList);
            if (arrayList.size() > 0) {
                SchemaType[] schemaTypeArr = new SchemaType[(arrayList.size() + length)];
                arrayList.toArray(schemaTypeArr);
                System.arraycopy(anonymousTypes, 0, schemaTypeArr, arrayList.size(), length);
                anonymousTypes = schemaTypeArr;
            }
        }
        for (SchemaType schemaType = schemaTypeImpl; schemaType != null; schemaType = schemaType.getOuterType()) {
            hashSet.add(schemaType.getShortJavaName());
        }
        for (SchemaType schemaType2 = schemaTypeImpl; schemaType2 != null; schemaType2 = schemaType2.getOuterType()) {
            hashSet.add(schemaType2.getShortJavaImplName());
        }
        hashSet.add(getOutermostPackage(schemaTypeImpl.getFullJavaName()));
        for (int i = 0; i < anonymousTypes.length; i++) {
            SchemaTypeImpl schemaTypeImpl2 = (SchemaTypeImpl) anonymousTypes[i];
            if (schemaTypeImpl2 != null && !schemaTypeImpl2.isSkippedAnonymousType()) {
                String str3 = null;
                if (schemaTypeImpl2.getContainerField() != null) {
                    str2 = schemaTypeImpl2.getContainerField().getName().getLocalPart();
                    str = stscState.getJavaname(schemaTypeImpl2.getContainerField().getName(), 1);
                } else {
                    int simpleVariety = schemaTypeImpl2.getOuterType().getSimpleVariety();
                    str = simpleVariety != 2 ? simpleVariety != 3 ? "Base" : "Item" : "Member";
                    str2 = null;
                }
                if (i < length) {
                    schemaTypeImpl2.setShortJavaName(pickInnerJavaClassName(hashSet, str2, str));
                    if (str != null) {
                        str3 = str + "Impl";
                    }
                    schemaTypeImpl2.setShortJavaImplName(pickInnerJavaImplName(hashSet, str2, str3));
                } else {
                    schemaTypeImpl2.setFullJavaName(schemaTypeImpl.getFullJavaName() + "$" + pickInnerJavaClassName(hashSet, str2, str));
                    StringBuilder append = new StringBuilder().append(schemaTypeImpl.getFullJavaImplName()).append("$");
                    if (str != null) {
                        str3 = str + "Impl";
                    }
                    schemaTypeImpl2.setFullJavaImplName(append.append(pickInnerJavaImplName(hashSet, str2, str3)).toString());
                }
                setExtensions(schemaTypeImpl2, stscState);
            }
        }
    }

    static void assignJavaPropertyNames(Set<String> set, SchemaProperty[] schemaPropertyArr, SchemaType schemaType, boolean z) {
        SchemaProperty schemaProperty;
        String str;
        StscState stscState = StscState.get();
        for (SchemaPropertyImpl schemaPropertyImpl : schemaPropertyArr) {
            if (schemaPropertyImpl.isAttribute()) {
                schemaProperty = schemaType.getAttributeProperty(schemaPropertyImpl.getName());
            } else {
                schemaProperty = schemaType.getElementProperty(schemaPropertyImpl.getName());
            }
            boolean z2 = true;
            if ((schemaProperty == null) != z) {
                QName name = schemaPropertyImpl.getName();
                if (schemaProperty == null) {
                    str = pickJavaPropertyName(set, name.getLocalPart(), stscState.getJavaname(name, schemaPropertyImpl.isAttribute() ? 4 : 3));
                } else {
                    str = schemaProperty.getJavaPropertyName();
                }
                schemaPropertyImpl.setJavaPropertyName(str);
                boolean z3 = schemaPropertyImpl.getMaxOccurs() == null || schemaPropertyImpl.getMaxOccurs().compareTo(BigInteger.ONE) > 0;
                boolean z4 = !z3 && schemaPropertyImpl.getMaxOccurs().signum() > 0;
                boolean z5 = z4 && schemaPropertyImpl.getMinOccurs().signum() == 0;
                SchemaType type = schemaPropertyImpl.getType();
                if (schemaProperty != null) {
                    if (schemaProperty.extendsJavaArray()) {
                        z4 = false;
                        z5 = false;
                        z3 = true;
                    }
                    if (schemaProperty.extendsJavaSingleton()) {
                        z4 = true;
                    }
                    if (!schemaProperty.extendsJavaOption()) {
                        z2 = z5;
                    }
                    type = schemaProperty.javaBasedOnType();
                    z5 = z2;
                }
                schemaPropertyImpl.setExtendsJava(type.getRef(), z4, z5, z3);
            }
        }
    }

    static void assignJavaTypeCodes(SchemaProperty[] schemaPropertyArr) {
        for (SchemaPropertyImpl schemaPropertyImpl : schemaPropertyArr) {
            schemaPropertyImpl.setJavaTypeCode(javaTypeCodeForType(schemaPropertyImpl.javaBasedOnType()));
        }
    }

    static int javaTypeCodeInCommon(SchemaType[] schemaTypeArr) {
        int i = 0;
        if (!(schemaTypeArr == null || schemaTypeArr.length == 0)) {
            i = javaTypeCodeForType(schemaTypeArr[0]);
            if (i == 19) {
                return i;
            }
            for (int i2 = 1; i2 < schemaTypeArr.length; i2++) {
                if (i != javaTypeCodeForType(schemaTypeArr[i2])) {
                    return 19;
                }
            }
        }
        return i;
    }

    static int javaTypeCodeForType(SchemaType schemaType) {
        if (!schemaType.isSimpleType()) {
            return 0;
        }
        if (((SchemaTypeImpl) schemaType).getUserTypeHandlerName() != null) {
            return 20;
        }
        if (schemaType.getSimpleVariety() == 2) {
            SchemaType unionCommonBaseType = schemaType.getUnionCommonBaseType();
            if (unionCommonBaseType == null || unionCommonBaseType.isURType()) {
                return javaTypeCodeInCommon(schemaType.getUnionConstituentTypes());
            }
            schemaType = unionCommonBaseType;
        }
        if (schemaType.getSimpleVariety() == 3) {
            return 16;
        }
        if (schemaType.isURType()) {
            return 0;
        }
        switch (schemaType.getPrimitiveType().getBuiltinTypeCode()) {
            case 2:
                return 10;
            case 3:
                return 1;
            case 4:
            case 5:
                return 11;
            case 6:
                return 10;
            case 7:
                return 15;
            case 8:
                return 0;
            case 9:
                return 2;
            case 10:
                return 3;
            case 11:
                int decimalSize = schemaType.getDecimalSize();
                if (decimalSize == 8) {
                    return 4;
                }
                if (decimalSize == 16) {
                    return 5;
                }
                if (decimalSize == 32) {
                    return 6;
                }
                if (decimalSize != 64) {
                    return decimalSize != 1000000 ? 8 : 9;
                }
                return 7;
            case 12:
                if (!isStringType(schemaType.getBaseEnumType())) {
                    return 10;
                }
                if (schemaType.getEnumerationValues() == null || schemaType.getEnumerationValues().length <= MAX_ENUM_COUNT) {
                    return 18;
                }
                return 10;
            case 13:
                return 13;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return 17;
            default:
                throw new IllegalStateException("unrecognized code " + schemaType.getPrimitiveType().getBuiltinTypeCode() + " of " + schemaType.getPrimitiveType().getName());
        }
    }

    static boolean isPropertyModelOrderInsensitive(SchemaProperty[] schemaPropertyArr) {
        for (SchemaProperty schemaProperty : schemaPropertyArr) {
            if (schemaProperty.hasNillable() == 1 || schemaProperty.hasDefault() == 1 || schemaProperty.hasFixed() == 1) {
                return false;
            }
            if (schemaProperty.hasDefault() != 0 && schemaProperty.getDefaultText() == null) {
                return false;
            }
        }
        return true;
    }

    static boolean protectReservedGlobalClassNames(String str) {
        String substring = str.substring(str.lastIndexOf(46) + 1);
        if (!substring.endsWith("Document") || substring.equals("Document")) {
            return false;
        }
        return true;
    }

    static boolean protectReservedInnerClassNames(String str) {
        return str.equals("Enum") || str.equals("Factory");
    }

    static boolean protectReservedPropertyNames(String str) {
        return PROTECTED_PROPERTIES_SET.contains(str) || (str.endsWith(SoapEncSchemaTypeSystem.SOAP_ARRAY) && !str.equals(SoapEncSchemaTypeSystem.SOAP_ARRAY));
    }

    static String pickFullJavaClassName(Set<String> set, QName qName, String str, boolean z, boolean z2) {
        boolean z3;
        if (str == null || str.indexOf(46) < 0) {
            StscState stscState = StscState.get();
            String namespaceURI = qName.getNamespaceURI();
            String classNameFromQName = NameUtil.getClassNameFromQName(qName);
            String packageOverride = stscState.getPackageOverride(namespaceURI);
            if (packageOverride != null) {
                classNameFromQName = packageOverride + "." + classNameFromQName.substring(classNameFromQName.lastIndexOf(46) + 1);
            }
            String javaPrefix = stscState.getJavaPrefix(namespaceURI);
            if (javaPrefix != null) {
                classNameFromQName = classNameFromQName.substring(0, classNameFromQName.lastIndexOf(46) + 1) + javaPrefix + classNameFromQName.substring(classNameFromQName.lastIndexOf(46) + 1);
            }
            if (str != null) {
                classNameFromQName = classNameFromQName.substring(0, classNameFromQName.lastIndexOf(46) + 1) + str;
            }
            boolean protectReservedGlobalClassNames = protectReservedGlobalClassNames(classNameFromQName);
            if (str == null) {
                if (z) {
                    classNameFromQName = classNameFromQName + "Document";
                } else if (z2) {
                    classNameFromQName = classNameFromQName + "Attribute";
                }
                str = classNameFromQName;
                String javaSuffix = stscState.getJavaSuffix(namespaceURI);
                if (javaSuffix != null) {
                    str = str + javaSuffix;
                }
            } else {
                str = classNameFromQName;
            }
            z3 = protectReservedGlobalClassNames;
        } else {
            z3 = protectReservedGlobalClassNames(str);
        }
        String outermostPackage = getOutermostPackage(str);
        String str2 = z3 ? str + 1 : str;
        int i = 1;
        while (true) {
            if (set.contains(str2.toLowerCase(Locale.ROOT)) || str2.equals(outermostPackage)) {
                i++;
                str2 = str + i;
            } else {
                set.add(str2.toLowerCase(Locale.ROOT));
                return str2;
            }
        }
    }

    static String getOutermostPackage(String str) {
        int indexOf;
        if (str != null && (indexOf = str.indexOf(46)) >= 0) {
            return str.substring(0, indexOf);
        }
        return "";
    }

    static String pickFullJavaImplName(Set<String> set, String str) {
        String str2;
        String str3;
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf >= 0) {
            str3 = str.substring(lastIndexOf + 1);
            str2 = str.substring(0, lastIndexOf);
        } else {
            str3 = str;
            str2 = null;
        }
        String str4 = str2 + ".impl." + str3 + "Impl";
        String str5 = str4;
        int i = 1;
        while (set.contains(str5.toLowerCase(Locale.ROOT))) {
            i++;
            str5 = str4 + i;
        }
        set.add(str5.toLowerCase(Locale.ROOT));
        return str5;
    }

    static String pickJavaPropertyName(Set<String> set, String str, String str2) {
        if (str2 == null) {
            str2 = NameUtil.upperCamelCase(str);
        }
        String str3 = protectReservedPropertyNames(str2) ? str2 + 1 : str2;
        int i = 1;
        while (set.contains(str3)) {
            i++;
            str3 = str2 + i;
        }
        set.add(str3);
        return str3;
    }

    static String pickInnerJavaClassName(Set<String> set, String str, String str2) {
        if (str2 == null) {
            str2 = NameUtil.upperCamelCase(str);
        }
        String str3 = protectReservedInnerClassNames(str2) ? str2 + 1 : str2;
        int i = 1;
        while (set.contains(str3)) {
            i++;
            str3 = str2 + i;
        }
        set.add(str3);
        return str3;
    }

    static String pickInnerJavaImplName(Set<String> set, String str, String str2) {
        if (str2 == null) {
            str2 = NameUtil.upperCamelCase(str) + "Impl";
        }
        int i = 1;
        String str3 = str2;
        while (set.contains(str3)) {
            i++;
            str3 = str2 + i;
        }
        set.add(str3);
        return str3;
    }

    static QName findTopName(SchemaType schemaType) {
        if (schemaType.getName() != null) {
            return schemaType.getName();
        }
        if (schemaType.isDocumentType()) {
            if (schemaType.getContentModel() != null && schemaType.getContentModel().getParticleType() == 4) {
                return schemaType.getDocumentElementName();
            }
            throw new IllegalStateException();
        } else if (!schemaType.isAttributeType()) {
            return schemaType.getContainerField().getName();
        } else {
            if (schemaType.getAttributeModel() != null && schemaType.getAttributeModel().getAttributes().length == 1) {
                return schemaType.getAttributeTypeAttributeName();
            }
            throw new IllegalStateException();
        }
    }

    static void addAnonymousTypesFromRedefinition(SchemaType schemaType, List<SchemaType> list) {
        while (((SchemaTypeImpl) schemaType).isRedefinition()) {
            if (schemaType.getDerivationType() == 2 || schemaType.isSimpleType()) {
                schemaType = schemaType.getBaseType();
                SchemaType[] anonymousTypes = schemaType.getAnonymousTypes();
                if (anonymousTypes.length > 0) {
                    list.addAll(Arrays.asList(anonymousTypes));
                }
            } else {
                return;
            }
        }
    }
}
