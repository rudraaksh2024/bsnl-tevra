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

public class XQuerySchemaTypeSystem extends SchemaTypeLoaderBase implements SchemaTypeSystem {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int BTC_ANY_ATOMIC = 52;
    public static final int BTC_DAY_TIME_DURATION = 53;
    public static final int BTC_FIRST_XQUERY = 52;
    public static final int BTC_LAST_XQUERY = 54;
    public static final int BTC_YEAR_MONTH_DURATION = 54;
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
    public static final SchemaTypeImpl ST_ANY_ATOMIC;
    public static final SchemaTypeImpl ST_ANY_SIMPLE;
    public static final SchemaTypeImpl ST_ANY_TYPE;
    public static final SchemaTypeImpl ST_ANY_URI;
    public static final SchemaTypeImpl ST_BASE_64_BINARY;
    public static final SchemaTypeImpl ST_BOOLEAN;
    public static final SchemaTypeImpl ST_BYTE;
    public static final SchemaTypeImpl ST_DATE;
    public static final SchemaTypeImpl ST_DATE_TIME;
    public static final SchemaTypeImpl ST_DAY_TIME_DURATION;
    public static final SchemaTypeImpl ST_DECIMAL;
    public static final SchemaTypeImpl ST_DOUBLE;
    public static final SchemaTypeImpl ST_DURATION;
    public static final SchemaTypeImpl ST_ENTITIES;
    public static final SchemaTypeImpl ST_ENTITY;
    public static final SchemaTypeImpl ST_FLOAT;
    public static final SchemaTypeImpl ST_G_DAY;
    public static final SchemaTypeImpl ST_G_MONTH;
    public static final SchemaTypeImpl ST_G_MONTH_DAY;
    public static final SchemaTypeImpl ST_G_YEAR;
    public static final SchemaTypeImpl ST_G_YEAR_MONTH;
    public static final SchemaTypeImpl ST_HEX_BINARY;
    public static final SchemaTypeImpl ST_ID;
    public static final SchemaTypeImpl ST_IDREF;
    public static final SchemaTypeImpl ST_IDREFS;
    public static final SchemaTypeImpl ST_INT;
    public static final SchemaTypeImpl ST_INTEGER;
    public static final SchemaTypeImpl ST_LANGUAGE;
    public static final SchemaTypeImpl ST_LONG;
    public static final SchemaTypeImpl ST_NAME;
    public static final SchemaTypeImpl ST_NCNAME;
    public static final SchemaTypeImpl ST_NEGATIVE_INTEGER;
    public static final SchemaTypeImpl ST_NMTOKEN;
    public static final SchemaTypeImpl ST_NMTOKENS;
    public static final SchemaTypeImpl ST_NON_NEGATIVE_INTEGER;
    public static final SchemaTypeImpl ST_NON_POSITIVE_INTEGER;
    public static final SchemaTypeImpl ST_NORMALIZED_STRING;
    public static final SchemaTypeImpl ST_NOTATION;
    public static final SchemaTypeImpl ST_NO_TYPE;
    public static final SchemaTypeImpl ST_POSITIVE_INTEGER;
    public static final SchemaTypeImpl ST_QNAME;
    public static final SchemaTypeImpl ST_SHORT;
    public static final SchemaTypeImpl ST_STRING;
    public static final SchemaTypeImpl ST_TIME;
    public static final SchemaTypeImpl ST_TOKEN;
    public static final SchemaTypeImpl ST_UNSIGNED_BYTE;
    public static final SchemaTypeImpl ST_UNSIGNED_INT;
    public static final SchemaTypeImpl ST_UNSIGNED_LONG;
    public static final SchemaTypeImpl ST_UNSIGNED_SHORT;
    public static final SchemaTypeImpl ST_YEAR_MONTH_DURATION;
    private static final XmlValueRef XMLSTR_COLLAPSE = buildString("preserve");
    private static final XmlValueRef XMLSTR_PRESERVE = buildString("preserve");
    private static final XmlValueRef XMLSTR_REPLACE = buildString("preserve");
    private static final XQuerySchemaTypeSystem _global;
    private final SchemaContainer _container;
    private final Map<String, SchemaType> _handlesToObjects = new HashMap();
    private final Map<SchemaType, String> _objectsToHandles = new HashMap();
    private final SchemaTypeImpl[] _typeArray = new SchemaTypeImpl[50];
    private final Map<QName, SchemaType> _typeMap = new HashMap();
    private final Map<String, SchemaType> _typesByClassname = new HashMap();

    private int arrayIndexForBtc(int i) {
        return i > 46 ? (i - 52) + 46 + 1 : i;
    }

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
        return "xquery.typesystem.builtin";
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
        XQuerySchemaTypeSystem xQuerySchemaTypeSystem = new XQuerySchemaTypeSystem();
        _global = xQuerySchemaTypeSystem;
        ST_ANY_TYPE = xQuerySchemaTypeSystem.getBuiltinType(1);
        ST_ANY_SIMPLE = xQuerySchemaTypeSystem.getBuiltinType(2);
        ST_ANY_ATOMIC = xQuerySchemaTypeSystem.getBuiltinType(52);
        ST_BOOLEAN = xQuerySchemaTypeSystem.getBuiltinType(3);
        ST_BASE_64_BINARY = xQuerySchemaTypeSystem.getBuiltinType(4);
        ST_HEX_BINARY = xQuerySchemaTypeSystem.getBuiltinType(5);
        ST_ANY_URI = xQuerySchemaTypeSystem.getBuiltinType(6);
        ST_QNAME = xQuerySchemaTypeSystem.getBuiltinType(7);
        ST_NOTATION = xQuerySchemaTypeSystem.getBuiltinType(8);
        ST_FLOAT = xQuerySchemaTypeSystem.getBuiltinType(9);
        ST_DOUBLE = xQuerySchemaTypeSystem.getBuiltinType(10);
        ST_DECIMAL = xQuerySchemaTypeSystem.getBuiltinType(11);
        ST_STRING = xQuerySchemaTypeSystem.getBuiltinType(12);
        ST_DURATION = xQuerySchemaTypeSystem.getBuiltinType(13);
        ST_DATE_TIME = xQuerySchemaTypeSystem.getBuiltinType(14);
        ST_TIME = xQuerySchemaTypeSystem.getBuiltinType(15);
        ST_DATE = xQuerySchemaTypeSystem.getBuiltinType(16);
        ST_G_YEAR_MONTH = xQuerySchemaTypeSystem.getBuiltinType(17);
        ST_G_YEAR = xQuerySchemaTypeSystem.getBuiltinType(18);
        ST_G_MONTH_DAY = xQuerySchemaTypeSystem.getBuiltinType(19);
        ST_G_DAY = xQuerySchemaTypeSystem.getBuiltinType(20);
        ST_G_MONTH = xQuerySchemaTypeSystem.getBuiltinType(21);
        ST_INTEGER = xQuerySchemaTypeSystem.getBuiltinType(22);
        ST_LONG = xQuerySchemaTypeSystem.getBuiltinType(23);
        ST_INT = xQuerySchemaTypeSystem.getBuiltinType(24);
        ST_SHORT = xQuerySchemaTypeSystem.getBuiltinType(25);
        ST_BYTE = xQuerySchemaTypeSystem.getBuiltinType(26);
        ST_NON_POSITIVE_INTEGER = xQuerySchemaTypeSystem.getBuiltinType(27);
        ST_NEGATIVE_INTEGER = xQuerySchemaTypeSystem.getBuiltinType(28);
        ST_NON_NEGATIVE_INTEGER = xQuerySchemaTypeSystem.getBuiltinType(29);
        ST_POSITIVE_INTEGER = xQuerySchemaTypeSystem.getBuiltinType(30);
        ST_UNSIGNED_LONG = xQuerySchemaTypeSystem.getBuiltinType(31);
        ST_UNSIGNED_INT = xQuerySchemaTypeSystem.getBuiltinType(32);
        ST_UNSIGNED_SHORT = xQuerySchemaTypeSystem.getBuiltinType(33);
        ST_UNSIGNED_BYTE = xQuerySchemaTypeSystem.getBuiltinType(34);
        ST_NORMALIZED_STRING = xQuerySchemaTypeSystem.getBuiltinType(35);
        ST_TOKEN = xQuerySchemaTypeSystem.getBuiltinType(36);
        ST_NAME = xQuerySchemaTypeSystem.getBuiltinType(37);
        ST_NCNAME = xQuerySchemaTypeSystem.getBuiltinType(38);
        ST_LANGUAGE = xQuerySchemaTypeSystem.getBuiltinType(39);
        ST_ID = xQuerySchemaTypeSystem.getBuiltinType(40);
        ST_IDREF = xQuerySchemaTypeSystem.getBuiltinType(41);
        ST_IDREFS = xQuerySchemaTypeSystem.getBuiltinType(42);
        ST_ENTITY = xQuerySchemaTypeSystem.getBuiltinType(43);
        ST_ENTITIES = xQuerySchemaTypeSystem.getBuiltinType(44);
        ST_NMTOKEN = xQuerySchemaTypeSystem.getBuiltinType(45);
        ST_NMTOKENS = xQuerySchemaTypeSystem.getBuiltinType(46);
        ST_DAY_TIME_DURATION = xQuerySchemaTypeSystem.getBuiltinType(53);
        ST_YEAR_MONTH_DURATION = xQuerySchemaTypeSystem.getBuiltinType(54);
        ST_NO_TYPE = xQuerySchemaTypeSystem.getBuiltinType(0);
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
        for (int i2 = 52; i2 <= 54; i2++) {
            _global.fillInType(i2);
        }
    }

    private SchemaTypeImpl getBuiltinType(int i) {
        return this._typeArray[arrayIndexForBtc(i)];
    }

    private XQuerySchemaTypeSystem() {
        SchemaContainer schemaContainer = new SchemaContainer("http://www.w3.org/2001/XMLSchema");
        this._container = schemaContainer;
        schemaContainer.setTypeSystem(this);
        setupType(1, "anyType", "org.apache.xmlbeans.XmlObject");
        setupType(2, "anySimpleType", "org.apache.xmlbeans.XmlAnySimpleType");
        setupType(52, "anyAtomicType", (String) null);
        setupType(3, "boolean", "org.apache.xmlbeans.XmlBoolean");
        setupType(4, XmlErrorCodes.BASE64BINARY, "org.apache.xmlbeans.XmlBase64Binary");
        setupType(5, XmlErrorCodes.HEXBINARY, "org.apache.xmlbeans.XmlHexBinary");
        setupType(6, XmlErrorCodes.ANYURI, "org.apache.xmlbeans.XmlAnyURI");
        setupType(7, XmlErrorCodes.QNAME, "org.apache.xmlbeans.XmlQName");
        setupType(8, "NOTATION", "org.apache.xmlbeans.XmlNOTATION");
        setupType(9, "float", "org.apache.xmlbeans.XmlFloat");
        setupType(10, XmlErrorCodes.DOUBLE, "org.apache.xmlbeans.XmlDouble");
        setupType(11, XmlErrorCodes.DECIMAL, "org.apache.xmlbeans.XmlDecimal");
        setupType(12, TypedValues.Custom.S_STRING, "org.apache.xmlbeans.XmlString");
        setupType(13, "duration", "org.apache.xmlbeans.XmlDuration");
        setupType(14, "dateTime", "org.apache.xmlbeans.XmlDateTime");
        setupType(15, "time", "org.apache.xmlbeans.XmlTime");
        setupType(16, XmlErrorCodes.DATE, "org.apache.xmlbeans.XmlDate");
        setupType(17, "gYearMonth", "org.apache.xmlbeans.XmlGYearMonth");
        setupType(18, "gYear", "org.apache.xmlbeans.XmlGYear");
        setupType(19, "gMonthDay", "org.apache.xmlbeans.XmlGMonthDay");
        setupType(20, "gDay", "org.apache.xmlbeans.XmlGDay");
        setupType(21, "gMonth", "org.apache.xmlbeans.XmlGMonth");
        setupType(22, "integer", "org.apache.xmlbeans.XmlInteger");
        setupType(23, XmlErrorCodes.LONG, "org.apache.xmlbeans.XmlLong");
        setupType(24, XmlErrorCodes.INT, "org.apache.xmlbeans.XmlInt");
        setupType(25, "short", "org.apache.xmlbeans.XmlShort");
        setupType(26, "byte", "org.apache.xmlbeans.XmlByte");
        setupType(27, "nonPositiveInteger", "org.apache.xmlbeans.XmlNonPositiveInteger");
        setupType(28, "negativeInteger", "org.apache.xmlbeans.XmlNegativeInteger");
        setupType(29, "nonNegativeInteger", "org.apache.xmlbeans.XmlNonNegativeInteger");
        setupType(30, "positiveInteger", "org.apache.xmlbeans.XmlPositiveInteger");
        setupType(31, "unsignedLong", "org.apache.xmlbeans.XmlUnsignedLong");
        setupType(32, "unsignedInt", "org.apache.xmlbeans.XmlUnsignedInt");
        setupType(33, "unsignedShort", "org.apache.xmlbeans.XmlUnsignedShort");
        setupType(34, "unsignedByte", "org.apache.xmlbeans.XmlUnsignedByte");
        setupType(35, "normalizedString", "org.apache.xmlbeans.XmlNormalizedString");
        setupType(36, "token", "org.apache.xmlbeans.XmlToken");
        setupType(37, "Name", "org.apache.xmlbeans.XmlName");
        setupType(38, XmlErrorCodes.NCNAME, "org.apache.xmlbeans.XmlNCName");
        setupType(39, "language", "org.apache.xmlbeans.XmlLanguage");
        setupType(40, "ID", "org.apache.xmlbeans.XmlID");
        setupType(41, "IDREF", "org.apache.xmlbeans.XmlIDREF");
        setupType(42, "IDREFS", "org.apache.xmlbeans.XmlIDREFS");
        setupType(43, "ENTITY", "org.apache.xmlbeans.XmlENTITY");
        setupType(44, "ENTITIES", "org.apache.xmlbeans.XmlENTITIES");
        setupType(45, XmlErrorCodes.NMTOKEN, "org.apache.xmlbeans.XmlNMTOKEN");
        setupType(46, "NMTOKENS", "org.apache.xmlbeans.XmlNMTOKENS");
        setupType(53, "dayTimeDuration", (String) null);
        setupType(54, "yearMonthDuration", (String) null);
        setupType(0, (String) null, (String) null);
        schemaContainer.setImmutable();
    }

    public boolean isNamespaceDefined(String str) {
        return str.equals("http://www.w3.org/2001/XMLSchema");
    }

    public SchemaType findType(QName qName) {
        return this._typeMap.get(qName);
    }

    public SchemaType.Ref findTypeRef(QName qName) {
        SchemaType findType = findType(qName);
        if (findType == null) {
            return null;
        }
        return findType.getRef();
    }

    public SchemaType typeForClassname(String str) {
        return this._typesByClassname.get(str);
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
        return this._objectsToHandles.get(schemaType);
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

    private void setupType(int i, String str, String str2) {
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
        this._typeArray[arrayIndexForBtc(i)] = schemaTypeImpl;
        this._typeMap.put(qName, schemaTypeImpl);
        this._handlesToObjects.put(sb2, schemaTypeImpl);
        this._objectsToHandles.put(schemaTypeImpl, sb2);
        if (str2 != null) {
            this._typesByClassname.put(str2, schemaTypeImpl);
        }
    }

    public SchemaType typeForHandle(String str) {
        return this._handlesToObjects.get(str);
    }

    public SchemaComponent resolveHandle(String str) {
        return this._handlesToObjects.get(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x018c, code lost:
        r5 = 2;
        r9 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x018f, code lost:
        r9 = false;
        r10 = false;
        r11 = false;
        r5 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0194, code lost:
        r5 = 2;
        r9 = false;
        r11 = false;
        r10 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0199, code lost:
        r5 = 0;
        r9 = false;
        r10 = false;
        r11 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x019e, code lost:
        r5 = 0;
        r9 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x01a0, code lost:
        r10 = r9;
        r11 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01a2, code lost:
        r12.setBasicFacets(r2, r4);
        r12.setWhiteSpaceRule(r0);
        r12.setOrdered(r5);
        r12.setBounded(r9);
        r12.setNumeric(r10);
        r12.setFinite(r11);
        r12.setDecimalSize(r8);
        r12.setAnonymousTypeRefs(EMPTY_SCHEMATYPEREF_ARRAY);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x01be, code lost:
        if (r13 == 43) goto L_0x01e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x01c2, code lost:
        if (r13 == 45) goto L_0x01e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x01c6, code lost:
        if (r13 == 53) goto L_0x01df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x01ca, code lost:
        if (r13 == 54) goto L_0x01dc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x01cc, code lost:
        switch(r13) {
            case 37: goto L_0x01d8;
            case 38: goto L_0x01d5;
            case 39: goto L_0x01d2;
            case 40: goto L_0x01e5;
            case 41: goto L_0x01e5;
            default: goto L_0x01cf;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x01cf, code lost:
        r0 = null;
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x01d2, code lost:
        r0 = "[a-zA-Z]{1,8}(-[a-zA-Z0-9]{1,8})*";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x01d5, code lost:
        r0 = "[\\i-[:]][\\c-[:]]*";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x01d8, code lost:
        r0 = "\\i\\c*";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x01da, code lost:
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x01dc, code lost:
        r0 = "[^DT]*";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x01df, code lost:
        r0 = "[^YM]*[DT].*";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x01e2, code lost:
        r0 = "\\c+";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x01e5, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x01e7, code lost:
        if (r0 == null) goto L_0x01f6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:?, code lost:
        r0 = org.apache.xmlbeans.impl.regex.SchemaRegularExpression.forPattern(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x01ee, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x006b, code lost:
        r3 = null;
        r4 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0074, code lost:
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0075, code lost:
        r12.setDerivationType(1);
        r12.setSimpleTypeVariety(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x007b, code lost:
        if (r4 == 0) goto L_0x0080;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x007d, code lost:
        r12.setSimpleType(true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0080, code lost:
        if (r2 != null) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0082, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0084, code lost:
        r5 = r2.getRef();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0088, code lost:
        r12.setBaseTypeRef(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x008b, code lost:
        if (r2 != null) goto L_0x008f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x008d, code lost:
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x008f, code lost:
        r5 = r2.getBaseDepth() + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0097, code lost:
        r12.setBaseDepth(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x009a, code lost:
        if (r3 != null) goto L_0x009e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x009c, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x009e, code lost:
        r3 = r3.getRef();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00a2, code lost:
        r12.setListItemTypeRef(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00a6, code lost:
        if (r13 < 2) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00aa, code lost:
        if (r13 <= 21) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00ae, code lost:
        if (r13 != 52) goto L_0x00b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00b0, code lost:
        r12.setPrimitiveTypeRef(r12.getRef());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00b8, code lost:
        if (r4 != 1) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00ba, code lost:
        if (r2 == null) goto L_0x00e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00c0, code lost:
        if (r2.getPrimitiveType() == null) goto L_0x00ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00c2, code lost:
        r12.setPrimitiveTypeRef(r2.getPrimitiveType().getRef());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00e2, code lost:
        throw new java.lang.IllegalStateException("Base.gpt was null for " + r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00f7, code lost:
        throw new java.lang.IllegalStateException("Base was null for " + r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00f8, code lost:
        r5 = 64;
        r8 = 1000000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0101, code lost:
        switch(r13) {
            case 0: goto L_0x017f;
            case 1: goto L_0x017f;
            case 2: goto L_0x017f;
            case 3: goto L_0x0179;
            case 4: goto L_0x0179;
            case 5: goto L_0x0179;
            case 6: goto L_0x0179;
            case 7: goto L_0x0179;
            case 8: goto L_0x0179;
            case 9: goto L_0x0179;
            case 10: goto L_0x0179;
            case 11: goto L_0x0170;
            case 12: goto L_0x0169;
            case 13: goto L_0x0179;
            case 14: goto L_0x0179;
            case 15: goto L_0x0179;
            case 16: goto L_0x0179;
            case 17: goto L_0x0179;
            case 18: goto L_0x0179;
            case 19: goto L_0x0179;
            case 20: goto L_0x0179;
            case 21: goto L_0x0179;
            case 22: goto L_0x0164;
            case 23: goto L_0x015f;
            case 24: goto L_0x0158;
            case 25: goto L_0x0151;
            case 26: goto L_0x014a;
            case 27: goto L_0x0145;
            case 28: goto L_0x0140;
            case 29: goto L_0x013b;
            case 30: goto L_0x0136;
            case 31: goto L_0x0131;
            case 32: goto L_0x012c;
            case 33: goto L_0x0127;
            case 34: goto L_0x0122;
            case 35: goto L_0x011b;
            case 36: goto L_0x0115;
            case 37: goto L_0x0115;
            case 38: goto L_0x0115;
            case 39: goto L_0x0115;
            case 40: goto L_0x0115;
            case 41: goto L_0x0115;
            case 42: goto L_0x0115;
            case 43: goto L_0x0115;
            case 44: goto L_0x010f;
            case 45: goto L_0x0115;
            case 46: goto L_0x010f;
            default: goto L_0x0104;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0104, code lost:
        switch(r13) {
            case 52: goto L_0x017f;
            case 53: goto L_0x0109;
            case 54: goto L_0x0109;
            default: goto L_0x0107;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0109, code lost:
        r2 = FACETS_WS_COLLAPSE;
        r4 = FIXED_FACETS_WS;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x010f, code lost:
        r2 = FACETS_BUILTIN_LIST;
        r4 = FIXED_FACETS_NONE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0115, code lost:
        r2 = FACETS_WS_COLLAPSE;
        r4 = FIXED_FACETS_NONE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x011b, code lost:
        r2 = FACETS_WS_REPLACE;
        r4 = FIXED_FACETS_NONE;
        r0 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0122, code lost:
        r4 = FACETS_UNSIGNED_BYTE;
        r5 = FIXED_FACETS_INTEGER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0127, code lost:
        r2 = FACETS_UNSIGNED_SHORT;
        r5 = FIXED_FACETS_INTEGER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x012c, code lost:
        r2 = FACETS_UNSIGNED_INT;
        r4 = FIXED_FACETS_INTEGER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0131, code lost:
        r2 = FACETS_UNSIGNED_LONG;
        r4 = FIXED_FACETS_INTEGER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0136, code lost:
        r2 = FACETS_POSITIVE;
        r4 = FIXED_FACETS_INTEGER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x013b, code lost:
        r2 = FACETS_NONNEGATIVE;
        r4 = FIXED_FACETS_INTEGER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0140, code lost:
        r2 = FACETS_NEGATIVE;
        r4 = FIXED_FACETS_INTEGER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0145, code lost:
        r2 = FACETS_NONPOSITIVE;
        r4 = FIXED_FACETS_INTEGER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x014a, code lost:
        r2 = FACETS_BYTE;
        r4 = FIXED_FACETS_INTEGER;
        r5 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0151, code lost:
        r4 = FACETS_SHORT;
        r5 = FIXED_FACETS_INTEGER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0155, code lost:
        r8 = 16;
        r2 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0158, code lost:
        r2 = FACETS_INT;
        r5 = FIXED_FACETS_INTEGER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x015c, code lost:
        r8 = 32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x015d, code lost:
        r4 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x015f, code lost:
        r2 = FACETS_LONG;
        r4 = FIXED_FACETS_INTEGER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0164, code lost:
        r2 = FACETS_INTEGER;
        r4 = FIXED_FACETS_INTEGER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0169, code lost:
        r2 = FACETS_WS_PRESERVE;
        r4 = FIXED_FACETS_NONE;
        r8 = 0;
        r0 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0170, code lost:
        r2 = FACETS_WS_COLLAPSE;
        r4 = FIXED_FACETS_WS;
        r5 = org.apache.xmlbeans.SchemaType.SIZE_BIG_DECIMAL;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0177, code lost:
        r8 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0179, code lost:
        r2 = FACETS_WS_COLLAPSE;
        r4 = FIXED_FACETS_WS;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x017d, code lost:
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x017f, code lost:
        r2 = FACETS_NONE;
        r4 = FIXED_FACETS_NONE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0183, code lost:
        r0 = 0;
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0185, code lost:
        switch(r13) {
            case 0: goto L_0x019e;
            case 1: goto L_0x019e;
            case 2: goto L_0x019e;
            case 3: goto L_0x0199;
            case 4: goto L_0x019e;
            case 5: goto L_0x019e;
            case 6: goto L_0x019e;
            case 7: goto L_0x019e;
            case 8: goto L_0x019e;
            case 9: goto L_0x0194;
            case 10: goto L_0x0194;
            case 11: goto L_0x0194;
            case 12: goto L_0x019e;
            case 13: goto L_0x018f;
            case 14: goto L_0x018f;
            case 15: goto L_0x018f;
            case 16: goto L_0x018f;
            case 17: goto L_0x018f;
            case 18: goto L_0x018f;
            case 19: goto L_0x018f;
            case 20: goto L_0x018f;
            case 21: goto L_0x018f;
            case 22: goto L_0x0194;
            case 23: goto L_0x018c;
            case 24: goto L_0x018c;
            case 25: goto L_0x018c;
            case 26: goto L_0x018c;
            case 27: goto L_0x018c;
            case 28: goto L_0x018c;
            case 29: goto L_0x018c;
            case 30: goto L_0x018c;
            case 31: goto L_0x018c;
            case 32: goto L_0x018c;
            case 33: goto L_0x018c;
            case 34: goto L_0x018c;
            case 35: goto L_0x019e;
            case 36: goto L_0x019e;
            case 37: goto L_0x019e;
            case 38: goto L_0x019e;
            case 39: goto L_0x019e;
            case 40: goto L_0x019e;
            case 41: goto L_0x019e;
            case 42: goto L_0x019e;
            case 43: goto L_0x019e;
            case 44: goto L_0x019e;
            case 45: goto L_0x019e;
            case 46: goto L_0x019e;
            default: goto L_0x0188;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0188, code lost:
        switch(r13) {
            case 52: goto L_0x019e;
            case 53: goto L_0x018f;
            case 54: goto L_0x018f;
            default: goto L_0x018b;
        };
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void fillInType(int r13) {
        /*
            r12 = this;
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r12 = r12.getBuiltinType(r13)
            r0 = 3
            r6 = 0
            r1 = 0
            r7 = 1
            switch(r13) {
                case 0: goto L_0x0071;
                case 1: goto L_0x006e;
                case 2: goto L_0x0069;
                case 3: goto L_0x0066;
                case 4: goto L_0x0066;
                case 5: goto L_0x0066;
                case 6: goto L_0x0066;
                case 7: goto L_0x0066;
                case 8: goto L_0x0066;
                case 9: goto L_0x0066;
                case 10: goto L_0x0066;
                case 11: goto L_0x0066;
                case 12: goto L_0x0066;
                case 13: goto L_0x0066;
                case 14: goto L_0x0066;
                case 15: goto L_0x0066;
                case 16: goto L_0x0066;
                case 17: goto L_0x0066;
                case 18: goto L_0x0066;
                case 19: goto L_0x0066;
                case 20: goto L_0x0066;
                case 21: goto L_0x0066;
                case 22: goto L_0x0063;
                case 23: goto L_0x0060;
                case 24: goto L_0x005d;
                case 25: goto L_0x005a;
                case 26: goto L_0x0057;
                case 27: goto L_0x0054;
                case 28: goto L_0x0051;
                case 29: goto L_0x004e;
                case 30: goto L_0x004b;
                case 31: goto L_0x0048;
                case 32: goto L_0x0045;
                case 33: goto L_0x0042;
                case 34: goto L_0x003f;
                case 35: goto L_0x003c;
                case 36: goto L_0x0039;
                case 37: goto L_0x0036;
                case 38: goto L_0x0033;
                case 39: goto L_0x0030;
                case 40: goto L_0x002d;
                case 41: goto L_0x002d;
                case 42: goto L_0x0018;
                case 43: goto L_0x002d;
                case 44: goto L_0x0018;
                case 45: goto L_0x0030;
                case 46: goto L_0x0018;
                default: goto L_0x000b;
            }
        L_0x000b:
            switch(r13) {
                case 52: goto L_0x0014;
                case 53: goto L_0x0010;
                case 54: goto L_0x0010;
                default: goto L_0x000e;
            }
        L_0x000e:
            goto L_0x0069
        L_0x0010:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_DURATION
            goto L_0x006b
        L_0x0014:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_ANY_SIMPLE
            goto L_0x006b
        L_0x0018:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_ANY_SIMPLE
            r3 = 42
            if (r13 != r3) goto L_0x0023
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r3 = ST_IDREF
        L_0x0020:
            r4 = r0
            goto L_0x0075
        L_0x0023:
            r3 = 44
            if (r13 != r3) goto L_0x002a
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r3 = ST_ENTITY
            goto L_0x0020
        L_0x002a:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r3 = ST_NMTOKEN
            goto L_0x0020
        L_0x002d:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_NCNAME
            goto L_0x006b
        L_0x0030:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_TOKEN
            goto L_0x006b
        L_0x0033:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_NAME
            goto L_0x006b
        L_0x0036:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_TOKEN
            goto L_0x006b
        L_0x0039:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_NORMALIZED_STRING
            goto L_0x006b
        L_0x003c:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_STRING
            goto L_0x006b
        L_0x003f:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_UNSIGNED_SHORT
            goto L_0x006b
        L_0x0042:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_UNSIGNED_INT
            goto L_0x006b
        L_0x0045:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_UNSIGNED_LONG
            goto L_0x006b
        L_0x0048:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_NON_NEGATIVE_INTEGER
            goto L_0x006b
        L_0x004b:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_NON_NEGATIVE_INTEGER
            goto L_0x006b
        L_0x004e:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_INTEGER
            goto L_0x006b
        L_0x0051:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_NON_POSITIVE_INTEGER
            goto L_0x006b
        L_0x0054:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_INTEGER
            goto L_0x006b
        L_0x0057:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_SHORT
            goto L_0x006b
        L_0x005a:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_INT
            goto L_0x006b
        L_0x005d:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_LONG
            goto L_0x006b
        L_0x0060:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_INTEGER
            goto L_0x006b
        L_0x0063:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_DECIMAL
            goto L_0x006b
        L_0x0066:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_ANY_ATOMIC
            goto L_0x006b
        L_0x0069:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_ANY_TYPE
        L_0x006b:
            r3 = r1
            r4 = r7
            goto L_0x0075
        L_0x006e:
            r2 = r1
            r3 = r2
            goto L_0x0074
        L_0x0071:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = ST_ANY_TYPE
            r3 = r1
        L_0x0074:
            r4 = r6
        L_0x0075:
            r12.setDerivationType(r7)
            r12.setSimpleTypeVariety(r4)
            if (r4 == 0) goto L_0x0080
            r12.setSimpleType(r7)
        L_0x0080:
            if (r2 != 0) goto L_0x0084
            r5 = r1
            goto L_0x0088
        L_0x0084:
            org.apache.xmlbeans.SchemaType$Ref r5 = r2.getRef()
        L_0x0088:
            r12.setBaseTypeRef(r5)
            if (r2 != 0) goto L_0x008f
            r5 = r6
            goto L_0x0097
        L_0x008f:
            r5 = r2
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r5 = (org.apache.xmlbeans.impl.schema.SchemaTypeImpl) r5
            int r5 = r5.getBaseDepth()
            int r5 = r5 + r7
        L_0x0097:
            r12.setBaseDepth(r5)
            if (r3 != 0) goto L_0x009e
            r3 = r1
            goto L_0x00a2
        L_0x009e:
            org.apache.xmlbeans.SchemaType$Ref r3 = r3.getRef()
        L_0x00a2:
            r12.setListItemTypeRef(r3)
            r3 = 2
            if (r13 < r3) goto L_0x00ac
            r5 = 21
            if (r13 <= r5) goto L_0x00b0
        L_0x00ac:
            r5 = 52
            if (r13 != r5) goto L_0x00b8
        L_0x00b0:
            org.apache.xmlbeans.SchemaType$Ref r2 = r12.getRef()
            r12.setPrimitiveTypeRef(r2)
            goto L_0x00f8
        L_0x00b8:
            if (r4 != r7) goto L_0x00f8
            if (r2 == 0) goto L_0x00e3
            org.apache.xmlbeans.SchemaType r4 = r2.getPrimitiveType()
            if (r4 == 0) goto L_0x00ce
            org.apache.xmlbeans.SchemaType r2 = r2.getPrimitiveType()
            org.apache.xmlbeans.SchemaType$Ref r2 = r2.getRef()
            r12.setPrimitiveTypeRef(r2)
            goto L_0x00f8
        L_0x00ce:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Base.gpt was null for "
            r0.<init>(r1)
            java.lang.StringBuilder r13 = r0.append(r13)
            java.lang.String r13 = r13.toString()
            r12.<init>(r13)
            throw r12
        L_0x00e3:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Base was null for "
            r0.<init>(r1)
            java.lang.StringBuilder r13 = r0.append(r13)
            java.lang.String r13 = r13.toString()
            r12.<init>(r13)
            throw r12
        L_0x00f8:
            r2 = 16
            r4 = 32
            r5 = 64
            r8 = 1000000(0xf4240, float:1.401298E-39)
            switch(r13) {
                case 0: goto L_0x017f;
                case 1: goto L_0x017f;
                case 2: goto L_0x017f;
                case 3: goto L_0x0179;
                case 4: goto L_0x0179;
                case 5: goto L_0x0179;
                case 6: goto L_0x0179;
                case 7: goto L_0x0179;
                case 8: goto L_0x0179;
                case 9: goto L_0x0179;
                case 10: goto L_0x0179;
                case 11: goto L_0x0170;
                case 12: goto L_0x0169;
                case 13: goto L_0x0179;
                case 14: goto L_0x0179;
                case 15: goto L_0x0179;
                case 16: goto L_0x0179;
                case 17: goto L_0x0179;
                case 18: goto L_0x0179;
                case 19: goto L_0x0179;
                case 20: goto L_0x0179;
                case 21: goto L_0x0179;
                case 22: goto L_0x0164;
                case 23: goto L_0x015f;
                case 24: goto L_0x0158;
                case 25: goto L_0x0151;
                case 26: goto L_0x014a;
                case 27: goto L_0x0145;
                case 28: goto L_0x0140;
                case 29: goto L_0x013b;
                case 30: goto L_0x0136;
                case 31: goto L_0x0131;
                case 32: goto L_0x012c;
                case 33: goto L_0x0127;
                case 34: goto L_0x0122;
                case 35: goto L_0x011b;
                case 36: goto L_0x0115;
                case 37: goto L_0x0115;
                case 38: goto L_0x0115;
                case 39: goto L_0x0115;
                case 40: goto L_0x0115;
                case 41: goto L_0x0115;
                case 42: goto L_0x0115;
                case 43: goto L_0x0115;
                case 44: goto L_0x010f;
                case 45: goto L_0x0115;
                case 46: goto L_0x010f;
                default: goto L_0x0104;
            }
        L_0x0104:
            switch(r13) {
                case 52: goto L_0x017f;
                case 53: goto L_0x0109;
                case 54: goto L_0x0109;
                default: goto L_0x0107;
            }
        L_0x0107:
            goto L_0x017f
        L_0x0109:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_WS_COLLAPSE
            boolean[] r4 = FIXED_FACETS_WS
            goto L_0x017d
        L_0x010f:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_BUILTIN_LIST
            boolean[] r4 = FIXED_FACETS_NONE
            goto L_0x0183
        L_0x0115:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_WS_COLLAPSE
            boolean[] r4 = FIXED_FACETS_NONE
            goto L_0x017d
        L_0x011b:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_WS_REPLACE
            boolean[] r4 = FIXED_FACETS_NONE
            r0 = r3
            goto L_0x017d
        L_0x0122:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r4 = FACETS_UNSIGNED_BYTE
            boolean[] r5 = FIXED_FACETS_INTEGER
            goto L_0x0155
        L_0x0127:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_UNSIGNED_SHORT
            boolean[] r5 = FIXED_FACETS_INTEGER
            goto L_0x015c
        L_0x012c:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_UNSIGNED_INT
            boolean[] r4 = FIXED_FACETS_INTEGER
            goto L_0x0177
        L_0x0131:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_UNSIGNED_LONG
            boolean[] r4 = FIXED_FACETS_INTEGER
            goto L_0x0185
        L_0x0136:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_POSITIVE
            boolean[] r4 = FIXED_FACETS_INTEGER
            goto L_0x0185
        L_0x013b:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_NONNEGATIVE
            boolean[] r4 = FIXED_FACETS_INTEGER
            goto L_0x0185
        L_0x0140:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_NEGATIVE
            boolean[] r4 = FIXED_FACETS_INTEGER
            goto L_0x0185
        L_0x0145:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_NONPOSITIVE
            boolean[] r4 = FIXED_FACETS_INTEGER
            goto L_0x0185
        L_0x014a:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_BYTE
            boolean[] r4 = FIXED_FACETS_INTEGER
            r5 = 8
            goto L_0x0177
        L_0x0151:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r4 = FACETS_SHORT
            boolean[] r5 = FIXED_FACETS_INTEGER
        L_0x0155:
            r8 = r2
            r2 = r4
            goto L_0x015d
        L_0x0158:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_INT
            boolean[] r5 = FIXED_FACETS_INTEGER
        L_0x015c:
            r8 = r4
        L_0x015d:
            r4 = r5
            goto L_0x0185
        L_0x015f:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_LONG
            boolean[] r4 = FIXED_FACETS_INTEGER
            goto L_0x0177
        L_0x0164:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_INTEGER
            boolean[] r4 = FIXED_FACETS_INTEGER
            goto L_0x0185
        L_0x0169:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_WS_PRESERVE
            boolean[] r4 = FIXED_FACETS_NONE
            r8 = r6
            r0 = r7
            goto L_0x0185
        L_0x0170:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_WS_COLLAPSE
            boolean[] r4 = FIXED_FACETS_WS
            r5 = 1000001(0xf4241, float:1.4013E-39)
        L_0x0177:
            r8 = r5
            goto L_0x0185
        L_0x0179:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_WS_COLLAPSE
            boolean[] r4 = FIXED_FACETS_WS
        L_0x017d:
            r8 = r6
            goto L_0x0185
        L_0x017f:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r2 = FACETS_NONE
            boolean[] r4 = FIXED_FACETS_NONE
        L_0x0183:
            r0 = r6
            r8 = r0
        L_0x0185:
            switch(r13) {
                case 0: goto L_0x019e;
                case 1: goto L_0x019e;
                case 2: goto L_0x019e;
                case 3: goto L_0x0199;
                case 4: goto L_0x019e;
                case 5: goto L_0x019e;
                case 6: goto L_0x019e;
                case 7: goto L_0x019e;
                case 8: goto L_0x019e;
                case 9: goto L_0x0194;
                case 10: goto L_0x0194;
                case 11: goto L_0x0194;
                case 12: goto L_0x019e;
                case 13: goto L_0x018f;
                case 14: goto L_0x018f;
                case 15: goto L_0x018f;
                case 16: goto L_0x018f;
                case 17: goto L_0x018f;
                case 18: goto L_0x018f;
                case 19: goto L_0x018f;
                case 20: goto L_0x018f;
                case 21: goto L_0x018f;
                case 22: goto L_0x0194;
                case 23: goto L_0x018c;
                case 24: goto L_0x018c;
                case 25: goto L_0x018c;
                case 26: goto L_0x018c;
                case 27: goto L_0x018c;
                case 28: goto L_0x018c;
                case 29: goto L_0x018c;
                case 30: goto L_0x018c;
                case 31: goto L_0x018c;
                case 32: goto L_0x018c;
                case 33: goto L_0x018c;
                case 34: goto L_0x018c;
                case 35: goto L_0x019e;
                case 36: goto L_0x019e;
                case 37: goto L_0x019e;
                case 38: goto L_0x019e;
                case 39: goto L_0x019e;
                case 40: goto L_0x019e;
                case 41: goto L_0x019e;
                case 42: goto L_0x019e;
                case 43: goto L_0x019e;
                case 44: goto L_0x019e;
                case 45: goto L_0x019e;
                case 46: goto L_0x019e;
                default: goto L_0x0188;
            }
        L_0x0188:
            switch(r13) {
                case 52: goto L_0x019e;
                case 53: goto L_0x018f;
                case 54: goto L_0x018f;
                default: goto L_0x018b;
            }
        L_0x018b:
            goto L_0x019e
        L_0x018c:
            r5 = r3
            r9 = r7
            goto L_0x01a0
        L_0x018f:
            r9 = r6
            r10 = r9
            r11 = r10
            r5 = r7
            goto L_0x01a2
        L_0x0194:
            r5 = r3
            r9 = r6
            r11 = r9
            r10 = r7
            goto L_0x01a2
        L_0x0199:
            r5 = r6
            r9 = r5
            r10 = r9
            r11 = r7
            goto L_0x01a2
        L_0x019e:
            r5 = r6
            r9 = r5
        L_0x01a0:
            r10 = r9
            r11 = r10
        L_0x01a2:
            r12.setBasicFacets(r2, r4)
            r12.setWhiteSpaceRule(r0)
            r12.setOrdered(r5)
            r12.setBounded(r9)
            r12.setNumeric(r10)
            r12.setFinite(r11)
            r12.setDecimalSize(r8)
            org.apache.xmlbeans.SchemaType$Ref[] r0 = EMPTY_SCHEMATYPEREF_ARRAY
            r12.setAnonymousTypeRefs(r0)
            r0 = 43
            if (r13 == r0) goto L_0x01e5
            r0 = 45
            if (r13 == r0) goto L_0x01e2
            r0 = 53
            if (r13 == r0) goto L_0x01df
            r0 = 54
            if (r13 == r0) goto L_0x01dc
            switch(r13) {
                case 37: goto L_0x01d8;
                case 38: goto L_0x01d5;
                case 39: goto L_0x01d2;
                case 40: goto L_0x01e5;
                case 41: goto L_0x01e5;
                default: goto L_0x01cf;
            }
        L_0x01cf:
            r0 = r1
            r2 = r6
            goto L_0x01e7
        L_0x01d2:
            java.lang.String r0 = "[a-zA-Z]{1,8}(-[a-zA-Z0-9]{1,8})*"
            goto L_0x01da
        L_0x01d5:
            java.lang.String r0 = "[\\i-[:]][\\c-[:]]*"
            goto L_0x01da
        L_0x01d8:
            java.lang.String r0 = "\\i\\c*"
        L_0x01da:
            r2 = r7
            goto L_0x01e7
        L_0x01dc:
            java.lang.String r0 = "[^DT]*"
            goto L_0x01da
        L_0x01df:
            java.lang.String r0 = "[^YM]*[DT].*"
            goto L_0x01da
        L_0x01e2:
            java.lang.String r0 = "\\c+"
            goto L_0x01da
        L_0x01e5:
            r0 = r1
            goto L_0x01da
        L_0x01e7:
            if (r0 == 0) goto L_0x01f6
            org.apache.xmlbeans.impl.regex.RegularExpression r0 = org.apache.xmlbeans.impl.regex.SchemaRegularExpression.forPattern(r0)     // Catch:{ ParseException -> 0x01ee }
            goto L_0x01ef
        L_0x01ee:
            r0 = r1
        L_0x01ef:
            org.apache.xmlbeans.impl.regex.RegularExpression[] r4 = new org.apache.xmlbeans.impl.regex.RegularExpression[r7]
            r4[r6] = r0
            r12.setPatterns(r4)
        L_0x01f6:
            r12.setPatternFacet(r2)
            if (r13 != r7) goto L_0x0246
            org.apache.xmlbeans.impl.schema.SchemaParticleImpl r13 = new org.apache.xmlbeans.impl.schema.SchemaParticleImpl
            r13.<init>()
            r0 = 5
            r13.setParticleType(r0)
            org.apache.xmlbeans.QNameSet r0 = org.apache.xmlbeans.QNameSet.ALL
            r13.setWildcardSet(r0)
            r13.setWildcardProcess(r3)
            java.math.BigInteger r0 = java.math.BigInteger.ZERO
            r13.setMinOccurs(r0)
            r13.setMaxOccurs(r1)
            org.apache.xmlbeans.QNameSet r0 = org.apache.xmlbeans.QNameSet.ALL
            r13.setTransitionRules(r0, r7)
            org.apache.xmlbeans.QNameSet r0 = org.apache.xmlbeans.QNameSet.ALL
            r13.setTransitionNotes(r0, r7)
            org.apache.xmlbeans.impl.schema.SchemaAttributeModelImpl r2 = new org.apache.xmlbeans.impl.schema.SchemaAttributeModelImpl
            r2.<init>()
            r2.setWildcardProcess(r3)
            org.apache.xmlbeans.QNameSet r0 = org.apache.xmlbeans.QNameSet.ALL
            r2.setWildcardSet(r0)
            r0 = 4
            r12.setComplexTypeVariety(r0)
            java.util.Map r3 = java.util.Collections.EMPTY_MAP
            java.util.Map r4 = java.util.Collections.EMPTY_MAP
            r5 = 0
            r0 = r12
            r1 = r13
            r0.setContentModel(r1, r2, r3, r4, r5)
            org.apache.xmlbeans.SchemaType$Ref[] r13 = EMPTY_SCHEMATYPEREF_ARRAY
            r12.setAnonymousTypeRefs(r13)
            org.apache.xmlbeans.QNameSet r13 = org.apache.xmlbeans.QNameSet.ALL
            org.apache.xmlbeans.QNameSet r0 = org.apache.xmlbeans.QNameSet.ALL
            r12.setWildcardSummary(r13, r7, r0, r7)
            goto L_0x0266
        L_0x0246:
            if (r13 != 0) goto L_0x0266
            r1 = 0
            org.apache.xmlbeans.impl.schema.SchemaAttributeModelImpl r2 = new org.apache.xmlbeans.impl.schema.SchemaAttributeModelImpl
            r2.<init>()
            r12.setComplexTypeVariety(r7)
            java.util.Map r3 = java.util.Collections.EMPTY_MAP
            java.util.Map r4 = java.util.Collections.EMPTY_MAP
            r5 = 0
            r0 = r12
            r0.setContentModel(r1, r2, r3, r4, r5)
            org.apache.xmlbeans.SchemaType$Ref[] r13 = EMPTY_SCHEMATYPEREF_ARRAY
            r12.setAnonymousTypeRefs(r13)
            org.apache.xmlbeans.QNameSet r13 = org.apache.xmlbeans.QNameSet.EMPTY
            org.apache.xmlbeans.QNameSet r0 = org.apache.xmlbeans.QNameSet.EMPTY
            r12.setWildcardSummary(r13, r6, r0, r6)
        L_0x0266:
            r12.setOrderSensitive(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.XQuerySchemaTypeSystem.fillInType(int):void");
    }

    public static SchemaType getNoType() {
        return ST_NO_TYPE;
    }
}
