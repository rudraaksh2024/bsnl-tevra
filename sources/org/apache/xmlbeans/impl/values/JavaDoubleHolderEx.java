package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;

public abstract class JavaDoubleHolderEx extends JavaDoubleHolder {
    private final SchemaType _schemaType;

    public JavaDoubleHolderEx(SchemaType schemaType, boolean z) {
        this._schemaType = schemaType;
        initComplexType(z, false);
    }

    public SchemaType schemaType() {
        return this._schemaType;
    }

    /* access modifiers changed from: protected */
    public void set_double(double d) {
        if (_validateOnSet()) {
            validateValue(d, this._schemaType, _voorVc);
        }
        super.set_double(d);
    }

    public static double validateLexical(String str, SchemaType schemaType, ValidationContext validationContext) {
        double validateLexical = JavaDoubleHolder.validateLexical(str, validationContext);
        if (!schemaType.matchPatternFacet(str)) {
            validationContext.invalid(XmlErrorCodes.DATATYPE_VALID$PATTERN_VALID, new Object[]{XmlErrorCodes.DOUBLE, str, QNameHelper.readable(schemaType)});
        }
        return validateLexical;
    }

    public static void validateValue(double d, SchemaType schemaType, ValidationContext validationContext) {
        XmlAnySimpleType facet = schemaType.getFacet(3);
        if (facet != null) {
            double doubleValue = ((XmlObjectBase) facet).getDoubleValue();
            if (compare(d, doubleValue) <= 0) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_MIN_EXCLUSIVE_VALID, new Object[]{XmlErrorCodes.DOUBLE, Double.valueOf(d), Double.valueOf(doubleValue), QNameHelper.readable(schemaType)});
            }
        }
        XmlAnySimpleType facet2 = schemaType.getFacet(4);
        if (facet2 != null) {
            double doubleValue2 = ((XmlObjectBase) facet2).getDoubleValue();
            if (compare(d, doubleValue2) < 0) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_MIN_INCLUSIVE_VALID, new Object[]{XmlErrorCodes.DOUBLE, Double.valueOf(d), Double.valueOf(doubleValue2), QNameHelper.readable(schemaType)});
            }
        }
        XmlAnySimpleType facet3 = schemaType.getFacet(5);
        if (facet3 != null) {
            double doubleValue3 = ((XmlObjectBase) facet3).getDoubleValue();
            if (compare(d, doubleValue3) > 0) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_MAX_INCLUSIVE_VALID, new Object[]{XmlErrorCodes.DOUBLE, Double.valueOf(d), Double.valueOf(doubleValue3), QNameHelper.readable(schemaType)});
            }
        }
        XmlAnySimpleType facet4 = schemaType.getFacet(6);
        if (facet4 != null) {
            double doubleValue4 = ((XmlObjectBase) facet4).getDoubleValue();
            if (compare(d, doubleValue4) >= 0) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_MAX_EXCLUSIVE_VALID, new Object[]{XmlErrorCodes.DOUBLE, Double.valueOf(d), Double.valueOf(doubleValue4), QNameHelper.readable(schemaType)});
            }
        }
        XmlAnySimpleType[] enumerationValues = schemaType.getEnumerationValues();
        if (enumerationValues != null) {
            int length = enumerationValues.length;
            int i = 0;
            while (i < length) {
                if (compare(d, ((XmlObjectBase) enumerationValues[i]).getDoubleValue()) != 0) {
                    i++;
                } else {
                    return;
                }
            }
            validationContext.invalid(XmlErrorCodes.DATATYPE_ENUM_VALID, new Object[]{XmlErrorCodes.DOUBLE, Double.valueOf(d), QNameHelper.readable(schemaType)});
        }
    }

    /* access modifiers changed from: protected */
    public void validate_simpleval(String str, ValidationContext validationContext) {
        validateLexical(str, schemaType(), validationContext);
        validateValue(getDoubleValue(), schemaType(), validationContext);
    }
}
