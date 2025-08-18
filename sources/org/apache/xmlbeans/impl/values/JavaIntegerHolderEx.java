package org.apache.xmlbeans.impl.values;

import java.math.BigInteger;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlPositiveInteger;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;

public class JavaIntegerHolderEx extends JavaIntegerHolder {
    private final SchemaType _schemaType;

    public JavaIntegerHolderEx(SchemaType schemaType, boolean z) {
        this._schemaType = schemaType;
        initComplexType(z, false);
    }

    public SchemaType schemaType() {
        return this._schemaType;
    }

    /* access modifiers changed from: protected */
    public void set_text(String str) {
        BigInteger lex = lex(str, _voorVc);
        if (_validateOnSet()) {
            validateValue(lex, this._schemaType, _voorVc);
        }
        if (_validateOnSet()) {
            validateLexical(str, this._schemaType, _voorVc);
        }
        super.set_BigInteger(lex);
    }

    /* access modifiers changed from: protected */
    public void set_BigInteger(BigInteger bigInteger) {
        if (_validateOnSet()) {
            validateValue(bigInteger, this._schemaType, _voorVc);
        }
        super.set_BigInteger(bigInteger);
    }

    public static void validateLexical(String str, SchemaType schemaType, ValidationContext validationContext) {
        JavaDecimalHolder.validateLexical(str, validationContext);
        if (str.lastIndexOf(46) >= 0) {
            validationContext.invalid("integer", new Object[]{str});
        }
        if (schemaType.hasPatternFacet() && !schemaType.matchPatternFacet(str)) {
            validationContext.invalid(XmlErrorCodes.DATATYPE_VALID$PATTERN_VALID, new Object[]{"integer", str, QNameHelper.readable(schemaType)});
        }
    }

    private static void validateValue(BigInteger bigInteger, SchemaType schemaType, ValidationContext validationContext) {
        XmlPositiveInteger xmlPositiveInteger = (XmlPositiveInteger) schemaType.getFacet(7);
        if (xmlPositiveInteger != null) {
            String bigInteger2 = bigInteger.toString();
            int length = bigInteger2.length();
            if (length > 0 && bigInteger2.charAt(0) == '-') {
                length--;
            }
            if (length > xmlPositiveInteger.getBigIntegerValue().intValue()) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_TOTAL_DIGITS_VALID, new Object[]{Integer.valueOf(length), bigInteger2, Integer.valueOf(xmlPositiveInteger.getBigIntegerValue().intValue()), QNameHelper.readable(schemaType)});
                return;
            }
        }
        XmlAnySimpleType facet = schemaType.getFacet(3);
        if (facet != null) {
            BigInteger bigIntegerValue = getBigIntegerValue(facet);
            if (bigInteger.compareTo(bigIntegerValue) <= 0) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_MIN_EXCLUSIVE_VALID, new Object[]{"integer", bigInteger, bigIntegerValue, QNameHelper.readable(schemaType)});
                return;
            }
        }
        XmlAnySimpleType facet2 = schemaType.getFacet(4);
        if (facet2 != null) {
            BigInteger bigIntegerValue2 = getBigIntegerValue(facet2);
            if (bigInteger.compareTo(bigIntegerValue2) < 0) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_MIN_INCLUSIVE_VALID, new Object[]{"integer", bigInteger, bigIntegerValue2, QNameHelper.readable(schemaType)});
                return;
            }
        }
        XmlAnySimpleType facet3 = schemaType.getFacet(5);
        if (facet3 != null) {
            BigInteger bigIntegerValue3 = getBigIntegerValue(facet3);
            if (bigInteger.compareTo(bigIntegerValue3) > 0) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_MAX_INCLUSIVE_VALID, new Object[]{"integer", bigInteger, bigIntegerValue3, QNameHelper.readable(schemaType)});
                return;
            }
        }
        XmlAnySimpleType facet4 = schemaType.getFacet(6);
        if (facet4 != null) {
            BigInteger bigIntegerValue4 = getBigIntegerValue(facet4);
            if (bigInteger.compareTo(bigIntegerValue4) >= 0) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_MAX_EXCLUSIVE_VALID, new Object[]{"integer", bigInteger, bigIntegerValue4, QNameHelper.readable(schemaType)});
                return;
            }
        }
        XmlAnySimpleType[] enumerationValues = schemaType.getEnumerationValues();
        if (enumerationValues != null) {
            int length2 = enumerationValues.length;
            int i = 0;
            while (i < length2) {
                if (!bigInteger.equals(getBigIntegerValue(enumerationValues[i]))) {
                    i++;
                } else {
                    return;
                }
            }
            validationContext.invalid(XmlErrorCodes.DATATYPE_ENUM_VALID, new Object[]{"integer", bigInteger, QNameHelper.readable(schemaType)});
        }
    }

    private static BigInteger getBigIntegerValue(XmlObject xmlObject) {
        SchemaType schemaType = xmlObject.schemaType();
        switch (schemaType.getDecimalSize()) {
            case 1000000:
                return ((XmlObjectBase) xmlObject).getBigIntegerValue();
            case SchemaType.SIZE_BIG_DECIMAL:
                return ((XmlObjectBase) xmlObject).getBigDecimalValue().toBigInteger();
            default:
                throw new IllegalStateException("Bad facet type for Big Int: " + schemaType);
        }
    }

    /* access modifiers changed from: protected */
    public void validate_simpleval(String str, ValidationContext validationContext) {
        validateLexical(str, schemaType(), validationContext);
        validateValue(getBigIntegerValue(), schemaType(), validationContext);
    }
}
