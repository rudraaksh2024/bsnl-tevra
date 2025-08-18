package org.apache.xmlbeans.impl.values;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;

public abstract class JavaDecimalHolderEx extends JavaDecimalHolder {
    private final SchemaType _schemaType;

    public SchemaType schemaType() {
        return this._schemaType;
    }

    public JavaDecimalHolderEx(SchemaType schemaType, boolean z) {
        this._schemaType = schemaType;
        initComplexType(z, false);
    }

    /* access modifiers changed from: protected */
    public void set_text(String str) {
        BigDecimal bigDecimal;
        if (_validateOnSet()) {
            validateLexical(str, this._schemaType, _voorVc);
        }
        try {
            bigDecimal = new BigDecimal(str);
        } catch (NumberFormatException unused) {
            _voorVc.invalid(XmlErrorCodes.DECIMAL, new Object[]{str});
            bigDecimal = null;
        }
        if (_validateOnSet()) {
            validateValue(bigDecimal, this._schemaType, _voorVc);
        }
        super.set_BigDecimal(bigDecimal);
    }

    /* access modifiers changed from: protected */
    public void set_BigDecimal(BigDecimal bigDecimal) {
        if (_validateOnSet()) {
            validateValue(bigDecimal, this._schemaType, _voorVc);
        }
        super.set_BigDecimal(bigDecimal);
    }

    public static void validateLexical(String str, SchemaType schemaType, ValidationContext validationContext) {
        JavaDecimalHolder.validateLexical(str, validationContext);
        if (schemaType.hasPatternFacet() && !schemaType.matchPatternFacet(str)) {
            validationContext.invalid(XmlErrorCodes.DATATYPE_VALID$PATTERN_VALID, new Object[]{XmlErrorCodes.DECIMAL, str, QNameHelper.readable(schemaType)});
        }
    }

    public static void validateValue(BigDecimal bigDecimal, SchemaType schemaType, ValidationContext validationContext) {
        XmlAnySimpleType facet = schemaType.getFacet(8);
        if (facet != null) {
            int intValue = ((XmlObjectBase) facet).getBigIntegerValue().intValue();
            try {
                bigDecimal.setScale(intValue, RoundingMode.UNNECESSARY);
            } catch (ArithmeticException unused) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_FRACTION_DIGITS_VALID, new Object[]{Integer.valueOf(bigDecimal.scale()), bigDecimal.toString(), Integer.valueOf(intValue), QNameHelper.readable(schemaType)});
                return;
            }
        }
        XmlAnySimpleType facet2 = schemaType.getFacet(7);
        if (facet2 != null) {
            String bigInteger = bigDecimal.unscaledValue().toString();
            int intValue2 = ((XmlObjectBase) facet2).getBigIntegerValue().intValue();
            int length = bigInteger.length();
            if (length > 0) {
                int i = bigInteger.charAt(0) == '-' ? length - 1 : length;
                int scale = bigDecimal.scale();
                int i2 = length - 1;
                int i3 = 0;
                while (bigInteger.charAt(i2) == '0' && i2 > 0 && i3 < scale) {
                    i3++;
                    i2--;
                }
                length = i - i3;
            }
            if (length > intValue2) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_TOTAL_DIGITS_VALID, new Object[]{Integer.valueOf(length), bigDecimal.toString(), Integer.valueOf(intValue2), QNameHelper.readable(schemaType)});
                return;
            }
        }
        XmlAnySimpleType facet3 = schemaType.getFacet(3);
        if (facet3 != null) {
            BigDecimal bigDecimalValue = ((XmlObjectBase) facet3).getBigDecimalValue();
            if (bigDecimal.compareTo(bigDecimalValue) <= 0) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_MIN_EXCLUSIVE_VALID, new Object[]{XmlErrorCodes.DECIMAL, bigDecimal, bigDecimalValue, QNameHelper.readable(schemaType)});
                return;
            }
        }
        XmlAnySimpleType facet4 = schemaType.getFacet(4);
        if (facet4 != null) {
            BigDecimal bigDecimalValue2 = ((XmlObjectBase) facet4).getBigDecimalValue();
            if (bigDecimal.compareTo(bigDecimalValue2) < 0) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_MIN_INCLUSIVE_VALID, new Object[]{XmlErrorCodes.DECIMAL, bigDecimal, bigDecimalValue2, QNameHelper.readable(schemaType)});
                return;
            }
        }
        XmlAnySimpleType facet5 = schemaType.getFacet(5);
        if (facet5 != null) {
            BigDecimal bigDecimalValue3 = ((XmlObjectBase) facet5).getBigDecimalValue();
            if (bigDecimal.compareTo(bigDecimalValue3) > 0) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_MAX_INCLUSIVE_VALID, new Object[]{XmlErrorCodes.DECIMAL, bigDecimal, bigDecimalValue3, QNameHelper.readable(schemaType)});
                return;
            }
        }
        XmlAnySimpleType facet6 = schemaType.getFacet(6);
        if (facet6 != null) {
            BigDecimal bigDecimalValue4 = ((XmlObjectBase) facet6).getBigDecimalValue();
            if (bigDecimal.compareTo(bigDecimalValue4) >= 0) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_MAX_EXCLUSIVE_VALID, new Object[]{XmlErrorCodes.DECIMAL, bigDecimal, bigDecimalValue4, QNameHelper.readable(schemaType)});
                return;
            }
        }
        XmlAnySimpleType[] enumerationValues = schemaType.getEnumerationValues();
        if (enumerationValues != null) {
            int length2 = enumerationValues.length;
            int i4 = 0;
            while (i4 < length2) {
                if (bigDecimal.compareTo(((XmlObjectBase) enumerationValues[i4]).getBigDecimalValue()) != 0) {
                    i4++;
                } else {
                    return;
                }
            }
            validationContext.invalid(XmlErrorCodes.DATATYPE_ENUM_VALID, new Object[]{XmlErrorCodes.DECIMAL, bigDecimal, QNameHelper.readable(schemaType)});
        }
    }

    /* access modifiers changed from: protected */
    public void validate_simpleval(String str, ValidationContext validationContext) {
        validateLexical(str, schemaType(), validationContext);
        validateValue(getBigDecimalValue(), schemaType(), validationContext);
    }
}
