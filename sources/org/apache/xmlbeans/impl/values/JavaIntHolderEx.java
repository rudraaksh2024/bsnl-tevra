package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.util.XsTypeConverter;

public abstract class JavaIntHolderEx extends JavaIntHolder {
    private final SchemaType _schemaType;

    public JavaIntHolderEx(SchemaType schemaType, boolean z) {
        this._schemaType = schemaType;
        initComplexType(z, false);
    }

    public SchemaType schemaType() {
        return this._schemaType;
    }

    /* access modifiers changed from: protected */
    public void set_text(String str) {
        try {
            int lexInt = XsTypeConverter.lexInt(str);
            if (_validateOnSet()) {
                validateValue(lexInt, this._schemaType, _voorVc);
                validateLexical(str, this._schemaType, _voorVc);
            }
            super.set_int(lexInt);
        } catch (Exception unused) {
            throw new XmlValueOutOfRangeException();
        }
    }

    /* access modifiers changed from: protected */
    public void set_int(int i) {
        if (_validateOnSet()) {
            validateValue(i, this._schemaType, _voorVc);
        }
        super.set_int(i);
    }

    public static void validateLexical(String str, SchemaType schemaType, ValidationContext validationContext) {
        JavaDecimalHolder.validateLexical(str, validationContext);
        if (schemaType.hasPatternFacet() && !schemaType.matchPatternFacet(str)) {
            validationContext.invalid(XmlErrorCodes.DATATYPE_VALID$PATTERN_VALID, new Object[]{XmlErrorCodes.INT, str, QNameHelper.readable(schemaType)});
        }
    }

    private static void validateValue(int i, SchemaType schemaType, ValidationContext validationContext) {
        int intValue;
        int intValue2;
        int intValue3;
        int intValue4;
        XmlAnySimpleType facet = schemaType.getFacet(7);
        if (facet != null) {
            String num = Integer.toString(i);
            int length = num.length();
            if (length > 0 && num.charAt(0) == '-') {
                length--;
            }
            if (length > getIntValue(facet)) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_TOTAL_DIGITS_VALID, new Object[]{Integer.valueOf(length), num, Integer.valueOf(getIntValue(facet)), QNameHelper.readable(schemaType)});
                return;
            }
        }
        XmlAnySimpleType facet2 = schemaType.getFacet(3);
        if (facet2 == null || i > (intValue4 = getIntValue(facet2))) {
            XmlAnySimpleType facet3 = schemaType.getFacet(4);
            if (facet3 == null || i >= (intValue3 = getIntValue(facet3))) {
                XmlAnySimpleType facet4 = schemaType.getFacet(5);
                if (facet4 == null || i <= (intValue2 = getIntValue(facet4))) {
                    XmlAnySimpleType facet5 = schemaType.getFacet(6);
                    if (facet5 == null || i < (intValue = getIntValue(facet5))) {
                        XmlAnySimpleType[] enumerationValues = schemaType.getEnumerationValues();
                        if (enumerationValues != null) {
                            int length2 = enumerationValues.length;
                            int i2 = 0;
                            while (i2 < length2) {
                                if (i != getIntValue(enumerationValues[i2])) {
                                    i2++;
                                } else {
                                    return;
                                }
                            }
                            validationContext.invalid(XmlErrorCodes.DATATYPE_ENUM_VALID, new Object[]{XmlErrorCodes.INT, Integer.valueOf(i), QNameHelper.readable(schemaType)});
                            return;
                        }
                        return;
                    }
                    validationContext.invalid(XmlErrorCodes.DATATYPE_MAX_EXCLUSIVE_VALID, new Object[]{XmlErrorCodes.INT, Integer.valueOf(i), Integer.valueOf(intValue), QNameHelper.readable(schemaType)});
                    return;
                }
                validationContext.invalid(XmlErrorCodes.DATATYPE_MAX_EXCLUSIVE_VALID, new Object[]{XmlErrorCodes.INT, Integer.valueOf(i), Integer.valueOf(intValue2), QNameHelper.readable(schemaType)});
                return;
            }
            validationContext.invalid(XmlErrorCodes.DATATYPE_MIN_INCLUSIVE_VALID, new Object[]{XmlErrorCodes.INT, Integer.valueOf(i), Integer.valueOf(intValue3), QNameHelper.readable(schemaType)});
            return;
        }
        validationContext.invalid(XmlErrorCodes.DATATYPE_MIN_EXCLUSIVE_VALID, new Object[]{XmlErrorCodes.INT, Integer.valueOf(i), Integer.valueOf(intValue4), QNameHelper.readable(schemaType)});
    }

    private static int getIntValue(XmlObject xmlObject) {
        int decimalSize = xmlObject.schemaType().getDecimalSize();
        if (decimalSize == 64) {
            return (int) ((XmlObjectBase) xmlObject).getLongValue();
        }
        switch (decimalSize) {
            case 1000000:
                return ((XmlObjectBase) xmlObject).getBigIntegerValue().intValue();
            case SchemaType.SIZE_BIG_DECIMAL:
                return ((XmlObjectBase) xmlObject).getBigDecimalValue().intValue();
            default:
                return ((XmlObjectBase) xmlObject).getIntValue();
        }
    }

    /* access modifiers changed from: protected */
    public void validate_simpleval(String str, ValidationContext validationContext) {
        validateLexical(str, schemaType(), validationContext);
        validateValue(getIntValue(), schemaType(), validationContext);
    }
}
