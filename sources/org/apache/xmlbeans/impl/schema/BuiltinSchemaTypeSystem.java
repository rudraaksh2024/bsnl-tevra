package org.apache.xmlbeans.impl.schema;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.File;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.Filer;
import org.apache.xmlbeans.SchemaAnnotation;
import org.apache.xmlbeans.SchemaAttributeGroup;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.SchemaGlobalAttribute;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaIdentityConstraint;
import org.apache.xmlbeans.SchemaModelGroup;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.values.XmlIntegerImpl;
import org.apache.xmlbeans.impl.values.XmlStringImpl;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;

public class BuiltinSchemaTypeSystem extends SchemaTypeLoaderBase implements SchemaTypeSystem {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final SchemaAnnotation[] EMPTY_SCHEMAANNOTATION_ARRAY = new SchemaAnnotation[0];
    private static final SchemaAttributeGroup[] EMPTY_SCHEMAATTRIBUTEGROUP_ARRAY = new SchemaAttributeGroup[0];
    private static final SchemaGlobalAttribute[] EMPTY_SCHEMAATTRIBUTE_ARRAY = new SchemaGlobalAttribute[0];
    private static final SchemaGlobalElement[] EMPTY_SCHEMAELEMENT_ARRAY = new SchemaGlobalElement[0];
    private static final SchemaModelGroup[] EMPTY_SCHEMAMODELGROUP_ARRAY = new SchemaModelGroup[0];
    private static final SchemaType.Ref[] EMPTY_SCHEMATYPEREF_ARRAY = new SchemaType.Ref[0];
    private static final SchemaType[] EMPTY_SCHEMATYPE_ARRAY = new SchemaType[0];
    private static final XmlValueRef[] FACETS_BUILTIN_LIST = {null, buildNnInteger(BigInteger.ONE), null, null, null, null, null, null, null, build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_BYTE = {null, null, null, null, buildInteger(BigInteger.valueOf(-128)), buildInteger(BigInteger.valueOf(127)), null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_INT = {null, null, null, null, buildInteger(BigInteger.valueOf(-2147483648L)), buildInteger(BigInteger.valueOf(2147483647L)), null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_INTEGER = {null, null, null, null, null, null, null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    static final XmlValueRef[] FACETS_LIST;
    private static final XmlValueRef[] FACETS_LONG = {null, null, null, null, buildInteger(BigInteger.valueOf(Long.MIN_VALUE)), buildInteger(BigInteger.valueOf(Long.MAX_VALUE)), null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_NEGATIVE = {null, null, null, null, null, buildInteger(BigInteger.ONE.negate()), null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_NONE;
    private static final XmlValueRef[] FACETS_NONNEGATIVE = {null, null, null, null, buildInteger(BigInteger.ZERO), null, null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_NONPOSITIVE = {null, null, null, null, null, buildInteger(BigInteger.ZERO), null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_POSITIVE = {null, null, null, null, buildInteger(BigInteger.ONE), null, null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_SHORT = {null, null, null, null, buildInteger(BigInteger.valueOf(-32768)), buildInteger(BigInteger.valueOf(32767)), null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    static final XmlValueRef[] FACETS_UNION;
    private static final XmlValueRef[] FACETS_UNSIGNED_BYTE = {null, null, null, null, buildInteger(BigInteger.ZERO), buildInteger(BigInteger.valueOf(255)), null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_UNSIGNED_INT = {null, null, null, null, buildInteger(BigInteger.ZERO), buildInteger(BigInteger.valueOf(4294967295L)), null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_UNSIGNED_LONG = {null, null, null, null, buildInteger(BigInteger.ZERO), buildInteger(new BigInteger("18446744073709551615")), null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_UNSIGNED_SHORT = {null, null, null, null, buildInteger(BigInteger.ZERO), buildInteger(BigInteger.valueOf(65535)), null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_WS_COLLAPSE;
    private static final XmlValueRef[] FACETS_WS_PRESERVE = {null, null, null, null, null, null, null, null, null, build_wsstring(1), null, null};
    private static final XmlValueRef[] FACETS_WS_REPLACE = {null, null, null, null, null, null, null, null, null, build_wsstring(2), null, null};
    private static final boolean[] FIXED_FACETS_INTEGER = {false, false, false, false, false, false, false, false, true, true, false, false};
    static final boolean[] FIXED_FACETS_LIST;
    private static final boolean[] FIXED_FACETS_NONE;
    static final boolean[] FIXED_FACETS_UNION;
    private static final boolean[] FIXED_FACETS_WS;
    public static final SchemaTypeImpl ST_ANY_SIMPLE = _global.getBuiltinType(2);
    public static final SchemaTypeImpl ST_ANY_TYPE;
    public static final SchemaTypeImpl ST_ANY_URI = _global.getBuiltinType(6);
    public static final SchemaTypeImpl ST_BASE_64_BINARY = _global.getBuiltinType(4);
    public static final SchemaTypeImpl ST_BOOLEAN = _global.getBuiltinType(3);
    public static final SchemaTypeImpl ST_BYTE = _global.getBuiltinType(26);
    public static final SchemaTypeImpl ST_DATE = _global.getBuiltinType(16);
    public static final SchemaTypeImpl ST_DATE_TIME = _global.getBuiltinType(14);
    public static final SchemaTypeImpl ST_DECIMAL = _global.getBuiltinType(11);
    public static final SchemaTypeImpl ST_DOUBLE = _global.getBuiltinType(10);
    public static final SchemaTypeImpl ST_DURATION = _global.getBuiltinType(13);
    public static final SchemaTypeImpl ST_ENTITIES = _global.getBuiltinType(44);
    public static final SchemaTypeImpl ST_ENTITY = _global.getBuiltinType(43);
    public static final SchemaTypeImpl ST_FLOAT = _global.getBuiltinType(9);
    public static final SchemaTypeImpl ST_G_DAY = _global.getBuiltinType(20);
    public static final SchemaTypeImpl ST_G_MONTH = _global.getBuiltinType(21);
    public static final SchemaTypeImpl ST_G_MONTH_DAY = _global.getBuiltinType(19);
    public static final SchemaTypeImpl ST_G_YEAR = _global.getBuiltinType(18);
    public static final SchemaTypeImpl ST_G_YEAR_MONTH = _global.getBuiltinType(17);
    public static final SchemaTypeImpl ST_HEX_BINARY = _global.getBuiltinType(5);
    public static final SchemaTypeImpl ST_ID = _global.getBuiltinType(40);
    public static final SchemaTypeImpl ST_IDREF = _global.getBuiltinType(41);
    public static final SchemaTypeImpl ST_IDREFS = _global.getBuiltinType(42);
    public static final SchemaTypeImpl ST_INT = _global.getBuiltinType(24);
    public static final SchemaTypeImpl ST_INTEGER = _global.getBuiltinType(22);
    public static final SchemaTypeImpl ST_LANGUAGE = _global.getBuiltinType(39);
    public static final SchemaTypeImpl ST_LONG = _global.getBuiltinType(23);
    public static final SchemaTypeImpl ST_NAME = _global.getBuiltinType(37);
    public static final SchemaTypeImpl ST_NCNAME = _global.getBuiltinType(38);
    public static final SchemaTypeImpl ST_NEGATIVE_INTEGER = _global.getBuiltinType(28);
    public static final SchemaTypeImpl ST_NMTOKEN = _global.getBuiltinType(45);
    public static final SchemaTypeImpl ST_NMTOKENS = _global.getBuiltinType(46);
    public static final SchemaTypeImpl ST_NON_NEGATIVE_INTEGER = _global.getBuiltinType(29);
    public static final SchemaTypeImpl ST_NON_POSITIVE_INTEGER = _global.getBuiltinType(27);
    public static final SchemaTypeImpl ST_NORMALIZED_STRING = _global.getBuiltinType(35);
    public static final SchemaTypeImpl ST_NOTATION = _global.getBuiltinType(8);
    public static final SchemaTypeImpl ST_NO_TYPE = _global.getBuiltinType(0);
    public static final SchemaTypeImpl ST_POSITIVE_INTEGER = _global.getBuiltinType(30);
    public static final SchemaTypeImpl ST_QNAME = _global.getBuiltinType(7);
    public static final SchemaTypeImpl ST_SHORT = _global.getBuiltinType(25);
    public static final SchemaTypeImpl ST_STRING = _global.getBuiltinType(12);
    public static final SchemaTypeImpl ST_TIME = _global.getBuiltinType(15);
    public static final SchemaTypeImpl ST_TOKEN = _global.getBuiltinType(36);
    public static final SchemaTypeImpl ST_UNSIGNED_BYTE = _global.getBuiltinType(34);
    public static final SchemaTypeImpl ST_UNSIGNED_INT = _global.getBuiltinType(32);
    public static final SchemaTypeImpl ST_UNSIGNED_LONG = _global.getBuiltinType(31);
    public static final SchemaTypeImpl ST_UNSIGNED_SHORT = _global.getBuiltinType(33);
    private static final XmlValueRef XMLSTR_COLLAPSE = buildString("preserve");
    private static final XmlValueRef XMLSTR_PRESERVE = buildString("preserve");
    private static final XmlValueRef XMLSTR_REPLACE = buildString("preserve");
    private static BuiltinSchemaTypeSystem _global;
    private SchemaContainer _container;
    private Map _handlesToObjects = new HashMap();
    private Map _objectsToHandles = new HashMap();
    private SchemaTypeImpl[] _typeArray = new SchemaTypeImpl[47];
    private Map _typeMap = new HashMap();
    private Map _typesByClassname = new HashMap();

    public SchemaGlobalAttribute findAttribute(QName qName) {
        return null;
    }

    public SchemaAttributeGroup.Ref findAttributeGroupRef(QName qName) {
        return null;
    }

    public SchemaGlobalAttribute.Ref findAttributeRef(QName qName) {
        return null;
    }

    public SchemaType findAttributeType(QName qName) {
        return null;
    }

    public SchemaType.Ref findAttributeTypeRef(QName qName) {
        return null;
    }

    public SchemaType findDocumentType(QName qName) {
        return null;
    }

    public SchemaType.Ref findDocumentTypeRef(QName qName) {
        return null;
    }

    public SchemaGlobalElement findElement(QName qName) {
        return null;
    }

    public SchemaGlobalElement.Ref findElementRef(QName qName) {
        return null;
    }

    public SchemaIdentityConstraint.Ref findIdentityConstraintRef(QName qName) {
        return null;
    }

    public SchemaModelGroup.Ref findModelGroupRef(QName qName) {
        return null;
    }

    public String getName() {
        return "schema.typesystem.builtin";
    }

    public InputStream getSourceAsStream(String str) {
        return null;
    }

    public void resolve() {
    }

    public static SchemaTypeSystem get() {
        return _global;
    }

    static {
        BuiltinSchemaTypeSystem builtinSchemaTypeSystem = new BuiltinSchemaTypeSystem();
        _global = builtinSchemaTypeSystem;
        ST_ANY_TYPE = builtinSchemaTypeSystem.getBuiltinType(1);
        XmlValueRef[] xmlValueRefArr = {null, null, null, null, null, null, null, null, null, null, null, null};
        FACETS_NONE = xmlValueRefArr;
        boolean[] zArr = {false, false, false, false, false, false, false, false, false, false, false, false};
        FIXED_FACETS_NONE = zArr;
        XmlValueRef[] xmlValueRefArr2 = {null, null, null, null, null, null, null, null, null, build_wsstring(3), null, null};
        FACETS_WS_COLLAPSE = xmlValueRefArr2;
        boolean[] zArr2 = {false, false, false, false, false, false, false, false, false, true, false, false};
        FIXED_FACETS_WS = zArr2;
        FACETS_UNION = xmlValueRefArr;
        FIXED_FACETS_UNION = zArr;
        FACETS_LIST = xmlValueRefArr2;
        FIXED_FACETS_LIST = zArr2;
        for (int i = 0; i <= 46; i++) {
            _global.fillInType(i);
        }
    }

    private SchemaTypeImpl getBuiltinType(int i) {
        return this._typeArray[i];
    }

    private BuiltinSchemaTypeSystem() {
        SchemaContainer schemaContainer = new SchemaContainer("http://www.w3.org/2001/XMLSchema");
        this._container = schemaContainer;
        schemaContainer.setTypeSystem(this);
        setupBuiltin(1, "anyType", "org.apache.xmlbeans.XmlObject");
        setupBuiltin(2, "anySimpleType", "org.apache.xmlbeans.XmlAnySimpleType");
        setupBuiltin(3, "boolean", "org.apache.xmlbeans.XmlBoolean");
        setupBuiltin(4, XmlErrorCodes.BASE64BINARY, "org.apache.xmlbeans.XmlBase64Binary");
        setupBuiltin(5, XmlErrorCodes.HEXBINARY, "org.apache.xmlbeans.XmlHexBinary");
        setupBuiltin(6, XmlErrorCodes.ANYURI, "org.apache.xmlbeans.XmlAnyURI");
        setupBuiltin(7, XmlErrorCodes.QNAME, "org.apache.xmlbeans.XmlQName");
        setupBuiltin(8, "NOTATION", "org.apache.xmlbeans.XmlNOTATION");
        setupBuiltin(9, "float", "org.apache.xmlbeans.XmlFloat");
        setupBuiltin(10, XmlErrorCodes.DOUBLE, "org.apache.xmlbeans.XmlDouble");
        setupBuiltin(11, XmlErrorCodes.DECIMAL, "org.apache.xmlbeans.XmlDecimal");
        setupBuiltin(12, TypedValues.Custom.S_STRING, "org.apache.xmlbeans.XmlString");
        setupBuiltin(13, "duration", "org.apache.xmlbeans.XmlDuration");
        setupBuiltin(14, "dateTime", "org.apache.xmlbeans.XmlDateTime");
        setupBuiltin(15, "time", "org.apache.xmlbeans.XmlTime");
        setupBuiltin(16, XmlErrorCodes.DATE, "org.apache.xmlbeans.XmlDate");
        setupBuiltin(17, "gYearMonth", "org.apache.xmlbeans.XmlGYearMonth");
        setupBuiltin(18, "gYear", "org.apache.xmlbeans.XmlGYear");
        setupBuiltin(19, "gMonthDay", "org.apache.xmlbeans.XmlGMonthDay");
        setupBuiltin(20, "gDay", "org.apache.xmlbeans.XmlGDay");
        setupBuiltin(21, "gMonth", "org.apache.xmlbeans.XmlGMonth");
        setupBuiltin(22, "integer", "org.apache.xmlbeans.XmlInteger");
        setupBuiltin(23, XmlErrorCodes.LONG, "org.apache.xmlbeans.XmlLong");
        setupBuiltin(24, XmlErrorCodes.INT, "org.apache.xmlbeans.XmlInt");
        setupBuiltin(25, "short", "org.apache.xmlbeans.XmlShort");
        setupBuiltin(26, "byte", "org.apache.xmlbeans.XmlByte");
        setupBuiltin(27, "nonPositiveInteger", "org.apache.xmlbeans.XmlNonPositiveInteger");
        setupBuiltin(28, "negativeInteger", "org.apache.xmlbeans.XmlNegativeInteger");
        setupBuiltin(29, "nonNegativeInteger", "org.apache.xmlbeans.XmlNonNegativeInteger");
        setupBuiltin(30, "positiveInteger", "org.apache.xmlbeans.XmlPositiveInteger");
        setupBuiltin(31, "unsignedLong", "org.apache.xmlbeans.XmlUnsignedLong");
        setupBuiltin(32, "unsignedInt", "org.apache.xmlbeans.XmlUnsignedInt");
        setupBuiltin(33, "unsignedShort", "org.apache.xmlbeans.XmlUnsignedShort");
        setupBuiltin(34, "unsignedByte", "org.apache.xmlbeans.XmlUnsignedByte");
        setupBuiltin(35, "normalizedString", "org.apache.xmlbeans.XmlNormalizedString");
        setupBuiltin(36, "token", "org.apache.xmlbeans.XmlToken");
        setupBuiltin(37, "Name", "org.apache.xmlbeans.XmlName");
        setupBuiltin(38, XmlErrorCodes.NCNAME, "org.apache.xmlbeans.XmlNCName");
        setupBuiltin(39, "language", "org.apache.xmlbeans.XmlLanguage");
        setupBuiltin(40, "ID", "org.apache.xmlbeans.XmlID");
        setupBuiltin(41, "IDREF", "org.apache.xmlbeans.XmlIDREF");
        setupBuiltin(42, "IDREFS", "org.apache.xmlbeans.XmlIDREFS");
        setupBuiltin(43, "ENTITY", "org.apache.xmlbeans.XmlENTITY");
        setupBuiltin(44, "ENTITIES", "org.apache.xmlbeans.XmlENTITIES");
        setupBuiltin(45, XmlErrorCodes.NMTOKEN, "org.apache.xmlbeans.XmlNMTOKEN");
        setupBuiltin(46, "NMTOKENS", "org.apache.xmlbeans.XmlNMTOKENS");
        setupBuiltin(0, (String) null, (String) null);
        this._container.setImmutable();
    }

    public boolean isNamespaceDefined(String str) {
        return str.equals("http://www.w3.org/2001/XMLSchema");
    }

    public SchemaType findType(QName qName) {
        return (SchemaType) this._typeMap.get(qName);
    }

    public SchemaType.Ref findTypeRef(QName qName) {
        SchemaType findType = findType(qName);
        if (findType == null) {
            return null;
        }
        return findType.getRef();
    }

    public SchemaType typeForClassname(String str) {
        return (SchemaType) this._typesByClassname.get(str);
    }

    public SchemaType[] globalTypes() {
        SchemaTypeImpl[] schemaTypeImplArr = this._typeArray;
        int length = schemaTypeImplArr.length - 1;
        SchemaType[] schemaTypeArr = new SchemaType[length];
        System.arraycopy(schemaTypeImplArr, 1, schemaTypeArr, 0, length);
        return schemaTypeArr;
    }

    public SchemaType[] documentTypes() {
        return EMPTY_SCHEMATYPE_ARRAY;
    }

    public SchemaType[] attributeTypes() {
        return EMPTY_SCHEMATYPE_ARRAY;
    }

    public SchemaGlobalElement[] globalElements() {
        return EMPTY_SCHEMAELEMENT_ARRAY;
    }

    public SchemaGlobalAttribute[] globalAttributes() {
        return EMPTY_SCHEMAATTRIBUTE_ARRAY;
    }

    public SchemaModelGroup[] modelGroups() {
        return EMPTY_SCHEMAMODELGROUP_ARRAY;
    }

    public SchemaAttributeGroup[] attributeGroups() {
        return EMPTY_SCHEMAATTRIBUTEGROUP_ARRAY;
    }

    public SchemaAnnotation[] annotations() {
        return EMPTY_SCHEMAANNOTATION_ARRAY;
    }

    public String handleForType(SchemaType schemaType) {
        return (String) this._objectsToHandles.get(schemaType);
    }

    public ClassLoader getClassLoader() {
        return BuiltinSchemaTypeSystem.class.getClassLoader();
    }

    public void saveToDirectory(File file) {
        throw new UnsupportedOperationException("The builtin schema type system cannot be saved.");
    }

    public void save(Filer filer) {
        throw new UnsupportedOperationException("The builtin schema type system cannot be saved.");
    }

    private static XmlValueRef build_wsstring(int i) {
        if (i == 1) {
            return XMLSTR_PRESERVE;
        }
        if (i == 2) {
            return XMLSTR_REPLACE;
        }
        if (i != 3) {
            return null;
        }
        return XMLSTR_COLLAPSE;
    }

    private static XmlValueRef buildNnInteger(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0) {
            return null;
        }
        try {
            XmlIntegerImpl xmlIntegerImpl = new XmlIntegerImpl();
            xmlIntegerImpl.setBigIntegerValue(bigInteger);
            xmlIntegerImpl.setImmutable();
            return new XmlValueRef(xmlIntegerImpl);
        } catch (XmlValueOutOfRangeException unused) {
            return null;
        }
    }

    private static XmlValueRef buildInteger(BigInteger bigInteger) {
        if (bigInteger == null) {
            return null;
        }
        try {
            XmlIntegerImpl xmlIntegerImpl = new XmlIntegerImpl();
            xmlIntegerImpl.setBigIntegerValue(bigInteger);
            xmlIntegerImpl.setImmutable();
            return new XmlValueRef(xmlIntegerImpl);
        } catch (XmlValueOutOfRangeException unused) {
            return null;
        }
    }

    private static XmlValueRef buildString(String str) {
        if (str == null) {
            return null;
        }
        try {
            XmlStringImpl xmlStringImpl = new XmlStringImpl();
            xmlStringImpl.setStringValue(str);
            xmlStringImpl.setImmutable();
            return new XmlValueRef(xmlStringImpl);
        } catch (XmlValueOutOfRangeException unused) {
            return null;
        }
    }

    private void setupBuiltin(int i, String str, String str2) {
        QName qName;
        SchemaTypeImpl schemaTypeImpl = new SchemaTypeImpl(this._container, true);
        this._container.addGlobalType(schemaTypeImpl.getRef());
        if (str == null) {
            qName = null;
        } else {
            qName = QNameHelper.forLNS(str, "http://www.w3.org/2001/XMLSchema");
        }
        StringBuilder sb = new StringBuilder("_BI_");
        if (str == null) {
            str = "NO_TYPE";
        }
        String sb2 = sb.append(str).toString();
        schemaTypeImpl.setName(qName);
        schemaTypeImpl.setBuiltinTypeCode(i);
        if (str2 != null) {
            schemaTypeImpl.setFullJavaName(str2);
        }
        this._typeArray[i] = schemaTypeImpl;
        this._typeMap.put(qName, schemaTypeImpl);
        this._handlesToObjects.put(sb2, schemaTypeImpl);
        this._objectsToHandles.put(schemaTypeImpl, sb2);
        if (str2 != null) {
            this._typesByClassname.put(str2, schemaTypeImpl);
        }
    }

    public SchemaType typeForHandle(String str) {
        return (SchemaType) this._handlesToObjects.get(str);
    }

    public SchemaComponent resolveHandle(String str) {
        return (SchemaComponent) this._handlesToObjects.get(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x01ad, code lost:
        r0 = null;
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01b0, code lost:
        r0 = "\\c+";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01b3, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x01b5, code lost:
        r0 = "[a-zA-Z]{1,8}(-[a-zA-Z0-9]{1,8})*";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x01b8, code lost:
        r0 = "[\\i-[:]][\\c-[:]]*";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x01bb, code lost:
        r0 = "\\i\\c*";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01bd, code lost:
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x01be, code lost:
        if (r0 == null) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:?, code lost:
        r0 = org.apache.xmlbeans.impl.regex.SchemaRegularExpression.forPattern(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x01c5, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0064, code lost:
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0068, code lost:
        r3 = null;
        r4 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x006a, code lost:
        r13.setDerivationType(1);
        r13.setSimpleTypeVariety(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0070, code lost:
        if (r4 == 0) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0072, code lost:
        r13.setSimpleType(true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0075, code lost:
        if (r2 != null) goto L_0x0079;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0077, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0079, code lost:
        r5 = r2.getRef();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x007d, code lost:
        r13.setBaseTypeRef(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0080, code lost:
        if (r2 != null) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0082, code lost:
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0084, code lost:
        r5 = r2.getBaseDepth() + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x008c, code lost:
        r13.setBaseDepth(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x008f, code lost:
        if (r3 != null) goto L_0x0093;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0091, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0093, code lost:
        r3 = r3.getRef();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0097, code lost:
        r13.setListItemTypeRef(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x009b, code lost:
        if (r14 < 2) goto L_0x00a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x009f, code lost:
        if (r14 > 21) goto L_0x00a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00a1, code lost:
        r13.setPrimitiveTypeRef(r13.getRef());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00a9, code lost:
        if (r4 != 1) goto L_0x00e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00ab, code lost:
        if (r2 == null) goto L_0x00d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00b1, code lost:
        if (r2.getPrimitiveType() == null) goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00b3, code lost:
        r13.setPrimitiveTypeRef(r2.getPrimitiveType().getRef());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00d3, code lost:
        throw new java.lang.IllegalStateException("Base.gpt was null for " + r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00e8, code lost:
        throw new java.lang.IllegalStateException("Base was null for " + r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00e9, code lost:
        r5 = 64;
        r8 = 1000000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00f2, code lost:
        switch(r14) {
            case 0: goto L_0x016f;
            case 1: goto L_0x016f;
            case 2: goto L_0x016f;
            case 3: goto L_0x0166;
            case 4: goto L_0x0166;
            case 5: goto L_0x0166;
            case 6: goto L_0x0166;
            case 7: goto L_0x0166;
            case 8: goto L_0x0166;
            case 9: goto L_0x0166;
            case 10: goto L_0x0166;
            case 11: goto L_0x015d;
            case 12: goto L_0x0155;
            case 13: goto L_0x0166;
            case 14: goto L_0x0166;
            case 15: goto L_0x0166;
            case 16: goto L_0x0166;
            case 17: goto L_0x0166;
            case 18: goto L_0x0166;
            case 19: goto L_0x0166;
            case 20: goto L_0x0166;
            case 21: goto L_0x0166;
            case 22: goto L_0x0150;
            case 23: goto L_0x014b;
            case 24: goto L_0x0144;
            case 25: goto L_0x013b;
            case 26: goto L_0x0134;
            case 27: goto L_0x012f;
            case 28: goto L_0x012a;
            case 29: goto L_0x0125;
            case 30: goto L_0x0120;
            case 31: goto L_0x011b;
            case 32: goto L_0x0116;
            case 33: goto L_0x0111;
            case 34: goto L_0x010c;
            case 35: goto L_0x0103;
            case 36: goto L_0x00fd;
            case 37: goto L_0x00fd;
            case 38: goto L_0x00fd;
            case 39: goto L_0x00fd;
            case 40: goto L_0x00fd;
            case 41: goto L_0x00fd;
            case 42: goto L_0x00fd;
            case 43: goto L_0x00fd;
            case 44: goto L_0x00f7;
            case 45: goto L_0x00fd;
            case 46: goto L_0x00f7;
            default: goto L_0x00f5;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00f7, code lost:
        r0 = FACETS_BUILTIN_LIST;
        r2 = FIXED_FACETS_NONE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00fd, code lost:
        r2 = FACETS_WS_COLLAPSE;
        r4 = FIXED_FACETS_NONE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0103, code lost:
        r0 = FACETS_WS_REPLACE;
        r4 = FIXED_FACETS_NONE;
        r2 = 2;
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x010c, code lost:
        r4 = FACETS_UNSIGNED_BYTE;
        r5 = FIXED_FACETS_INTEGER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0111, code lost:
        r2 = FACETS_UNSIGNED_SHORT;
        r5 = FIXED_FACETS_INTEGER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0116, code lost:
        r2 = FACETS_UNSIGNED_INT;
        r4 = FIXED_FACETS_INTEGER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x011b, code lost:
        r2 = FACETS_UNSIGNED_LONG;
        r4 = FIXED_FACETS_INTEGER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0120, code lost:
        r2 = FACETS_POSITIVE;
        r4 = FIXED_FACETS_INTEGER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0125, code lost:
        r2 = FACETS_NONNEGATIVE;
        r4 = FIXED_FACETS_INTEGER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x012a, code lost:
        r2 = FACETS_NEGATIVE;
        r4 = FIXED_FACETS_INTEGER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x012f, code lost:
        r2 = FACETS_NONPOSITIVE;
        r4 = FIXED_FACETS_INTEGER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0134, code lost:
        r2 = FACETS_BYTE;
        r4 = FIXED_FACETS_INTEGER;
        r5 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x013b, code lost:
        r4 = FACETS_SHORT;
        r5 = FIXED_FACETS_INTEGER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x013f, code lost:
        r8 = 16;
        r2 = 3;
        r0 = r4;
        r4 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0144, code lost:
        r2 = FACETS_INT;
        r5 = FIXED_FACETS_INTEGER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0148, code lost:
        r8 = 32;
        r4 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x014b, code lost:
        r2 = FACETS_LONG;
        r4 = FIXED_FACETS_INTEGER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0150, code lost:
        r2 = FACETS_INTEGER;
        r4 = FIXED_FACETS_INTEGER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0155, code lost:
        r0 = FACETS_WS_PRESERVE;
        r4 = FIXED_FACETS_NONE;
        r8 = 0;
        r2 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x015d, code lost:
        r2 = FACETS_WS_COLLAPSE;
        r4 = FIXED_FACETS_WS;
        r5 = org.apache.xmlbeans.SchemaType.SIZE_BIG_DECIMAL;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0164, code lost:
        r8 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0166, code lost:
        r2 = FACETS_WS_COLLAPSE;
        r4 = FIXED_FACETS_WS;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x016a, code lost:
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x016b, code lost:
        r12 = r2;
        r2 = 3;
        r0 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x016f, code lost:
        r0 = FACETS_NONE;
        r2 = FIXED_FACETS_NONE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0173, code lost:
        r4 = r2;
        r2 = 0;
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0176, code lost:
        switch(r14) {
            case 0: goto L_0x018c;
            case 1: goto L_0x018c;
            case 2: goto L_0x018c;
            case 3: goto L_0x0187;
            case 4: goto L_0x018c;
            case 5: goto L_0x018c;
            case 6: goto L_0x018c;
            case 7: goto L_0x018c;
            case 8: goto L_0x018c;
            case 9: goto L_0x0182;
            case 10: goto L_0x0182;
            case 11: goto L_0x0182;
            case 12: goto L_0x018c;
            case 13: goto L_0x017d;
            case 14: goto L_0x017d;
            case 15: goto L_0x017d;
            case 16: goto L_0x017d;
            case 17: goto L_0x017d;
            case 18: goto L_0x017d;
            case 19: goto L_0x017d;
            case 20: goto L_0x017d;
            case 21: goto L_0x017d;
            case 22: goto L_0x0182;
            case 23: goto L_0x017a;
            case 24: goto L_0x017a;
            case 25: goto L_0x017a;
            case 26: goto L_0x017a;
            case 27: goto L_0x017a;
            case 28: goto L_0x017a;
            case 29: goto L_0x017a;
            case 30: goto L_0x017a;
            case 31: goto L_0x017a;
            case 32: goto L_0x017a;
            case 33: goto L_0x017a;
            case 34: goto L_0x017a;
            case 35: goto L_0x018c;
            case 36: goto L_0x018c;
            case 37: goto L_0x018c;
            case 38: goto L_0x018c;
            case 39: goto L_0x018c;
            case 40: goto L_0x018c;
            case 41: goto L_0x018c;
            case 42: goto L_0x018c;
            case 43: goto L_0x018c;
            case 44: goto L_0x018c;
            case 45: goto L_0x018c;
            case 46: goto L_0x018c;
            default: goto L_0x0179;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x017a, code lost:
        r5 = 2;
        r9 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x017d, code lost:
        r9 = false;
        r10 = false;
        r11 = false;
        r5 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0182, code lost:
        r5 = 2;
        r9 = false;
        r11 = false;
        r10 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0187, code lost:
        r5 = 0;
        r9 = false;
        r10 = false;
        r11 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x018c, code lost:
        r5 = 0;
        r9 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x018e, code lost:
        r10 = r9;
        r11 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0190, code lost:
        r13.setBasicFacets(r0, r4);
        r13.setWhiteSpaceRule(r2);
        r13.setOrdered(r5);
        r13.setBounded(r9);
        r13.setNumeric(r10);
        r13.setFinite(r11);
        r13.setDecimalSize(r8);
        r13.setAnonymousTypeRefs(EMPTY_SCHEMATYPEREF_ARRAY);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x01aa, code lost:
        switch(r14) {
            case 37: goto L_0x01bb;
            case 38: goto L_0x01b8;
            case 39: goto L_0x01b5;
            case 40: goto L_0x01b3;
            case 41: goto L_0x01b3;
            case 42: goto L_0x01ad;
            case 43: goto L_0x01b3;
            case 44: goto L_0x01ad;
            case 45: goto L_0x01b0;
            default: goto L_0x01ad;
        };
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void fillInType(int r14) {
        /*
            r13 = this;
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r13 = r13.getBuiltinType(r14)
            r0 = 3
            r6 = 0
            r1 = 0
            r7 = 1
            switch(r14) {
                case 0: goto L_0x0061;
                case 1: goto L_0x005e;
                case 2: goto L_0x0066;
                case 3: goto L_0x005b;
                case 4: goto L_0x005b;
                case 5: goto L_0x005b;
                case 6: goto L_0x005b;
                case 7: goto L_0x005b;
                case 8: goto L_0x005b;
                case 9: goto L_0x005b;
                case 10: goto L_0x005b;
                case 11: goto L_0x005b;
                case 12: goto L_0x005b;
                case 13: goto L_0x005b;
                case 14: goto L_0x005b;
                case 15: goto L_0x005b;
                case 16: goto L_0x005b;
                case 17: goto L_0x005b;
                case 18: goto L_0x005b;
                case 19: goto L_0x005b;
                case 20: goto L_0x005b;
                case 21: goto L_0x005b;
                case 22: goto L_0x0058;
                case 23: goto L_0x0055;
                case 24: goto L_0x0052;
                case 25: goto L_0x004f;
                case 26: goto L_0x004c;
                case 27: goto L_0x0049;
                case 28: goto L_0x0046;
                case 29: goto L_0x0043;
                case 30: goto L_0x0040;
                case 31: goto L_0x003d;
                case 32: goto L_0x003a;
                case 33: goto L_0x0037;
                case 34: goto L_0x0034;
                case 35: goto L_0x0031;
                case 36: goto L_0x002e;
                case 37: goto L_0x002b;
                case 38: goto L_0x0028;
                case 39: goto L_0x0025;
                case 40: goto L_0x0022;
                case 41: goto L_0x0022;
                case 42: goto L_0x000d;
                case 43: goto L_0x0022;
                case 44: goto L_0x000d;
                case 45: goto L_0x0025;
                case 46: goto L_0x000d;
                default: goto L_0x000b;
            }
        L_0x000b:
            goto L_0x0066
        L_0x000d:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_ANY_SIMPLE
            r3 = 42
            if (r14 != r3) goto L_0x0018
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r3 = ST_IDREF
        L_0x0015:
            r4 = r0
            goto L_0x006a
        L_0x0018:
            r3 = 44
            if (r14 != r3) goto L_0x001f
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r3 = ST_ENTITY
            goto L_0x0015
        L_0x001f:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r3 = ST_NMTOKEN
            goto L_0x0015
        L_0x0022:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_NCNAME
            goto L_0x0068
        L_0x0025:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_TOKEN
            goto L_0x0068
        L_0x0028:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_NAME
            goto L_0x0068
        L_0x002b:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_TOKEN
            goto L_0x0068
        L_0x002e:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_NORMALIZED_STRING
            goto L_0x0068
        L_0x0031:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_STRING
            goto L_0x0068
        L_0x0034:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_UNSIGNED_SHORT
            goto L_0x0068
        L_0x0037:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_UNSIGNED_INT
            goto L_0x0068
        L_0x003a:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_UNSIGNED_LONG
            goto L_0x0068
        L_0x003d:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_NON_NEGATIVE_INTEGER
            goto L_0x0068
        L_0x0040:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_NON_NEGATIVE_INTEGER
            goto L_0x0068
        L_0x0043:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_INTEGER
            goto L_0x0068
        L_0x0046:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_NON_POSITIVE_INTEGER
            goto L_0x0068
        L_0x0049:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_INTEGER
            goto L_0x0068
        L_0x004c:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_SHORT
            goto L_0x0068
        L_0x004f:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_INT
            goto L_0x0068
        L_0x0052:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_LONG
            goto L_0x0068
        L_0x0055:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_INTEGER
            goto L_0x0068
        L_0x0058:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_DECIMAL
            goto L_0x0068
        L_0x005b:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_ANY_SIMPLE
            goto L_0x0068
        L_0x005e:
            r2 = r1
            r3 = r2
            goto L_0x0064
        L_0x0061:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_ANY_TYPE
            r3 = r1
        L_0x0064:
            r4 = r6
            goto L_0x006a
        L_0x0066:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_ANY_TYPE
        L_0x0068:
            r3 = r1
            r4 = r7
        L_0x006a:
            r13.setDerivationType(r7)
            r13.setSimpleTypeVariety(r4)
            if (r4 == 0) goto L_0x0075
            r13.setSimpleType(r7)
        L_0x0075:
            if (r2 != 0) goto L_0x0079
            r5 = r1
            goto L_0x007d
        L_0x0079:
            org.apache.xmlbeans.SchemaType$Ref r5 = r2.getRef()
        L_0x007d:
            r13.setBaseTypeRef(r5)
            if (r2 != 0) goto L_0x0084
            r5 = r6
            goto L_0x008c
        L_0x0084:
            r5 = r2
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r5 = (org.apache.xmlbeans.impl.schema.SchemaTypeImpl) r5
            int r5 = r5.getBaseDepth()
            int r5 = r5 + r7
        L_0x008c:
            r13.setBaseDepth(r5)
            if (r3 != 0) goto L_0x0093
            r3 = r1
            goto L_0x0097
        L_0x0093:
            org.apache.xmlbeans.SchemaType$Ref r3 = r3.getRef()
        L_0x0097:
            r13.setListItemTypeRef(r3)
            r3 = 2
            if (r14 < r3) goto L_0x00a9
            r5 = 21
            if (r14 > r5) goto L_0x00a9
            org.apache.xmlbeans.SchemaType$Ref r2 = r13.getRef()
            r13.setPrimitiveTypeRef(r2)
            goto L_0x00e9
        L_0x00a9:
            if (r4 != r7) goto L_0x00e9
            if (r2 == 0) goto L_0x00d4
            org.apache.xmlbeans.SchemaType r4 = r2.getPrimitiveType()
            if (r4 == 0) goto L_0x00bf
            org.apache.xmlbeans.SchemaType r2 = r2.getPrimitiveType()
            org.apache.xmlbeans.SchemaType$Ref r2 = r2.getRef()
            r13.setPrimitiveTypeRef(r2)
            goto L_0x00e9
        L_0x00bf:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Base.gpt was null for "
            r0.<init>(r1)
            java.lang.StringBuilder r14 = r0.append(r14)
            java.lang.String r14 = r14.toString()
            r13.<init>(r14)
            throw r13
        L_0x00d4:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Base was null for "
            r0.<init>(r1)
            java.lang.StringBuilder r14 = r0.append(r14)
            java.lang.String r14 = r14.toString()
            r13.<init>(r14)
            throw r13
        L_0x00e9:
            r2 = 16
            r4 = 32
            r5 = 64
            r8 = 1000000(0xf4240, float:1.401298E-39)
            switch(r14) {
                case 0: goto L_0x016f;
                case 1: goto L_0x016f;
                case 2: goto L_0x016f;
                case 3: goto L_0x0166;
                case 4: goto L_0x0166;
                case 5: goto L_0x0166;
                case 6: goto L_0x0166;
                case 7: goto L_0x0166;
                case 8: goto L_0x0166;
                case 9: goto L_0x0166;
                case 10: goto L_0x0166;
                case 11: goto L_0x015d;
                case 12: goto L_0x0155;
                case 13: goto L_0x0166;
                case 14: goto L_0x0166;
                case 15: goto L_0x0166;
                case 16: goto L_0x0166;
                case 17: goto L_0x0166;
                case 18: goto L_0x0166;
                case 19: goto L_0x0166;
                case 20: goto L_0x0166;
                case 21: goto L_0x0166;
                case 22: goto L_0x0150;
                case 23: goto L_0x014b;
                case 24: goto L_0x0144;
                case 25: goto L_0x013b;
                case 26: goto L_0x0134;
                case 27: goto L_0x012f;
                case 28: goto L_0x012a;
                case 29: goto L_0x0125;
                case 30: goto L_0x0120;
                case 31: goto L_0x011b;
                case 32: goto L_0x0116;
                case 33: goto L_0x0111;
                case 34: goto L_0x010c;
                case 35: goto L_0x0103;
                case 36: goto L_0x00fd;
                case 37: goto L_0x00fd;
                case 38: goto L_0x00fd;
                case 39: goto L_0x00fd;
                case 40: goto L_0x00fd;
                case 41: goto L_0x00fd;
                case 42: goto L_0x00fd;
                case 43: goto L_0x00fd;
                case 44: goto L_0x00f7;
                case 45: goto L_0x00fd;
                case 46: goto L_0x00f7;
                default: goto L_0x00f5;
            }
        L_0x00f5:
            goto L_0x016f
        L_0x00f7:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r0 = FACETS_BUILTIN_LIST
            boolean[] r2 = FIXED_FACETS_NONE
            goto L_0x0173
        L_0x00fd:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_WS_COLLAPSE
            boolean[] r4 = FIXED_FACETS_NONE
            goto L_0x016a
        L_0x0103:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r0 = FACETS_WS_REPLACE
            boolean[] r2 = FIXED_FACETS_NONE
            r4 = r2
            r2 = r3
            r8 = r6
            goto L_0x0176
        L_0x010c:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r4 = FACETS_UNSIGNED_BYTE
            boolean[] r5 = FIXED_FACETS_INTEGER
            goto L_0x013f
        L_0x0111:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_UNSIGNED_SHORT
            boolean[] r5 = FIXED_FACETS_INTEGER
            goto L_0x0148
        L_0x0116:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_UNSIGNED_INT
            boolean[] r4 = FIXED_FACETS_INTEGER
            goto L_0x0164
        L_0x011b:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_UNSIGNED_LONG
            boolean[] r4 = FIXED_FACETS_INTEGER
            goto L_0x016b
        L_0x0120:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_POSITIVE
            boolean[] r4 = FIXED_FACETS_INTEGER
            goto L_0x016b
        L_0x0125:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_NONNEGATIVE
            boolean[] r4 = FIXED_FACETS_INTEGER
            goto L_0x016b
        L_0x012a:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_NEGATIVE
            boolean[] r4 = FIXED_FACETS_INTEGER
            goto L_0x016b
        L_0x012f:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_NONPOSITIVE
            boolean[] r4 = FIXED_FACETS_INTEGER
            goto L_0x016b
        L_0x0134:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_BYTE
            boolean[] r4 = FIXED_FACETS_INTEGER
            r5 = 8
            goto L_0x0164
        L_0x013b:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r4 = FACETS_SHORT
            boolean[] r5 = FIXED_FACETS_INTEGER
        L_0x013f:
            r8 = r2
            r2 = r0
            r0 = r4
            r4 = r5
            goto L_0x0176
        L_0x0144:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_INT
            boolean[] r5 = FIXED_FACETS_INTEGER
        L_0x0148:
            r8 = r4
            r4 = r5
            goto L_0x016b
        L_0x014b:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_LONG
            boolean[] r4 = FIXED_FACETS_INTEGER
            goto L_0x0164
        L_0x0150:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_INTEGER
            boolean[] r4 = FIXED_FACETS_INTEGER
            goto L_0x016b
        L_0x0155:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r0 = FACETS_WS_PRESERVE
            boolean[] r2 = FIXED_FACETS_NONE
            r4 = r2
            r8 = r6
            r2 = r7
            goto L_0x0176
        L_0x015d:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_WS_COLLAPSE
            boolean[] r4 = FIXED_FACETS_WS
            r5 = 1000001(0xf4241, float:1.4013E-39)
        L_0x0164:
            r8 = r5
            goto L_0x016b
        L_0x0166:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_WS_COLLAPSE
            boolean[] r4 = FIXED_FACETS_WS
        L_0x016a:
            r8 = r6
        L_0x016b:
            r12 = r2
            r2 = r0
            r0 = r12
            goto L_0x0176
        L_0x016f:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r0 = FACETS_NONE
            boolean[] r2 = FIXED_FACETS_NONE
        L_0x0173:
            r4 = r2
            r2 = r6
            r8 = r2
        L_0x0176:
            switch(r14) {
                case 0: goto L_0x018c;
                case 1: goto L_0x018c;
                case 2: goto L_0x018c;
                case 3: goto L_0x0187;
                case 4: goto L_0x018c;
                case 5: goto L_0x018c;
                case 6: goto L_0x018c;
                case 7: goto L_0x018c;
                case 8: goto L_0x018c;
                case 9: goto L_0x0182;
                case 10: goto L_0x0182;
                case 11: goto L_0x0182;
                case 12: goto L_0x018c;
                case 13: goto L_0x017d;
                case 14: goto L_0x017d;
                case 15: goto L_0x017d;
                case 16: goto L_0x017d;
                case 17: goto L_0x017d;
                case 18: goto L_0x017d;
                case 19: goto L_0x017d;
                case 20: goto L_0x017d;
                case 21: goto L_0x017d;
                case 22: goto L_0x0182;
                case 23: goto L_0x017a;
                case 24: goto L_0x017a;
                case 25: goto L_0x017a;
                case 26: goto L_0x017a;
                case 27: goto L_0x017a;
                case 28: goto L_0x017a;
                case 29: goto L_0x017a;
                case 30: goto L_0x017a;
                case 31: goto L_0x017a;
                case 32: goto L_0x017a;
                case 33: goto L_0x017a;
                case 34: goto L_0x017a;
                case 35: goto L_0x018c;
                case 36: goto L_0x018c;
                case 37: goto L_0x018c;
                case 38: goto L_0x018c;
                case 39: goto L_0x018c;
                case 40: goto L_0x018c;
                case 41: goto L_0x018c;
                case 42: goto L_0x018c;
                case 43: goto L_0x018c;
                case 44: goto L_0x018c;
                case 45: goto L_0x018c;
                case 46: goto L_0x018c;
                default: goto L_0x0179;
            }
        L_0x0179:
            goto L_0x018c
        L_0x017a:
            r5 = r3
            r9 = r7
            goto L_0x018e
        L_0x017d:
            r9 = r6
            r10 = r9
            r11 = r10
            r5 = r7
            goto L_0x0190
        L_0x0182:
            r5 = r3
            r9 = r6
            r11 = r9
            r10 = r7
            goto L_0x0190
        L_0x0187:
            r5 = r6
            r9 = r5
            r10 = r9
            r11 = r7
            goto L_0x0190
        L_0x018c:
            r5 = r6
            r9 = r5
        L_0x018e:
            r10 = r9
            r11 = r10
        L_0x0190:
            r13.setBasicFacets(r0, r4)
            r13.setWhiteSpaceRule(r2)
            r13.setOrdered(r5)
            r13.setBounded(r9)
            r13.setNumeric(r10)
            r13.setFinite(r11)
            r13.setDecimalSize(r8)
            org.apache.xmlbeans.SchemaType$Ref[] r0 = EMPTY_SCHEMATYPEREF_ARRAY
            r13.setAnonymousTypeRefs(r0)
            switch(r14) {
                case 37: goto L_0x01bb;
                case 38: goto L_0x01b8;
                case 39: goto L_0x01b5;
                case 40: goto L_0x01b3;
                case 41: goto L_0x01b3;
                case 42: goto L_0x01ad;
                case 43: goto L_0x01b3;
                case 44: goto L_0x01ad;
                case 45: goto L_0x01b0;
                default: goto L_0x01ad;
            }
        L_0x01ad:
            r0 = r1
            r2 = r6
            goto L_0x01be
        L_0x01b0:
            java.lang.String r0 = "\\c+"
            goto L_0x01bd
        L_0x01b3:
            r0 = r1
            goto L_0x01bd
        L_0x01b5:
            java.lang.String r0 = "[a-zA-Z]{1,8}(-[a-zA-Z0-9]{1,8})*"
            goto L_0x01bd
        L_0x01b8:
            java.lang.String r0 = "[\\i-[:]][\\c-[:]]*"
            goto L_0x01bd
        L_0x01bb:
            java.lang.String r0 = "\\i\\c*"
        L_0x01bd:
            r2 = r7
        L_0x01be:
            if (r0 == 0) goto L_0x01cd
            org.apache.xmlbeans.impl.regex.RegularExpression r0 = org.apache.xmlbeans.impl.regex.SchemaRegularExpression.forPattern(r0)     // Catch:{ ParseException -> 0x01c5 }
            goto L_0x01c6
        L_0x01c5:
            r0 = r1
        L_0x01c6:
            org.apache.xmlbeans.impl.regex.RegularExpression[] r4 = new org.apache.xmlbeans.impl.regex.RegularExpression[r7]
            r4[r6] = r0
            r13.setPatterns(r4)
        L_0x01cd:
            r13.setPatternFacet(r2)
            if (r14 != r7) goto L_0x021d
            org.apache.xmlbeans.impl.schema.SchemaParticleImpl r14 = new org.apache.xmlbeans.impl.schema.SchemaParticleImpl
            r14.<init>()
            r0 = 5
            r14.setParticleType(r0)
            org.apache.xmlbeans.QNameSet r0 = org.apache.xmlbeans.QNameSet.ALL
            r14.setWildcardSet(r0)
            r14.setWildcardProcess(r3)
            java.math.BigInteger r0 = java.math.BigInteger.ZERO
            r14.setMinOccurs(r0)
            r14.setMaxOccurs(r1)
            org.apache.xmlbeans.QNameSet r0 = org.apache.xmlbeans.QNameSet.ALL
            r14.setTransitionRules(r0, r7)
            org.apache.xmlbeans.QNameSet r0 = org.apache.xmlbeans.QNameSet.ALL
            r14.setTransitionNotes(r0, r7)
            org.apache.xmlbeans.impl.schema.SchemaAttributeModelImpl r2 = new org.apache.xmlbeans.impl.schema.SchemaAttributeModelImpl
            r2.<init>()
            r2.setWildcardProcess(r3)
            org.apache.xmlbeans.QNameSet r0 = org.apache.xmlbeans.QNameSet.ALL
            r2.setWildcardSet(r0)
            r0 = 4
            r13.setComplexTypeVariety(r0)
            java.util.Map r3 = java.util.Collections.EMPTY_MAP
            java.util.Map r4 = java.util.Collections.EMPTY_MAP
            r5 = 0
            r0 = r13
            r1 = r14
            r0.setContentModel(r1, r2, r3, r4, r5)
            org.apache.xmlbeans.SchemaType$Ref[] r14 = EMPTY_SCHEMATYPEREF_ARRAY
            r13.setAnonymousTypeRefs(r14)
            org.apache.xmlbeans.QNameSet r14 = org.apache.xmlbeans.QNameSet.ALL
            org.apache.xmlbeans.QNameSet r0 = org.apache.xmlbeans.QNameSet.ALL
            r13.setWildcardSummary(r14, r7, r0, r7)
            goto L_0x023d
        L_0x021d:
            if (r14 != 0) goto L_0x023d
            r1 = 0
            org.apache.xmlbeans.impl.schema.SchemaAttributeModelImpl r2 = new org.apache.xmlbeans.impl.schema.SchemaAttributeModelImpl
            r2.<init>()
            r13.setComplexTypeVariety(r7)
            java.util.Map r3 = java.util.Collections.EMPTY_MAP
            java.util.Map r4 = java.util.Collections.EMPTY_MAP
            r5 = 0
            r0 = r13
            r0.setContentModel(r1, r2, r3, r4, r5)
            org.apache.xmlbeans.SchemaType$Ref[] r14 = EMPTY_SCHEMATYPEREF_ARRAY
            r13.setAnonymousTypeRefs(r14)
            org.apache.xmlbeans.QNameSet r14 = org.apache.xmlbeans.QNameSet.EMPTY
            org.apache.xmlbeans.QNameSet r0 = org.apache.xmlbeans.QNameSet.EMPTY
            r13.setWildcardSummary(r14, r6, r0, r6)
        L_0x023d:
            r13.setOrderSensitive(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem.fillInType(int):void");
    }

    public static SchemaType getNoType() {
        return ST_NO_TYPE;
    }
}
