package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;

public abstract class JavaFloatHolderEx extends JavaFloatHolder {
    private final SchemaType _schemaType;

    public JavaFloatHolderEx(SchemaType schemaType, boolean z) {
        this._schemaType = schemaType;
        initComplexType(z, false);
    }

    public SchemaType schemaType() {
        return this._schemaType;
    }

    /* access modifiers changed from: protected */
    public void set_float(float f) {
        if (_validateOnSet()) {
            validateValue(f, this._schemaType, _voorVc);
        }
        super.set_float(f);
    }

    public static float validateLexical(String str, SchemaType schemaType, ValidationContext validationContext) {
        float validateLexical = JavaFloatHolder.validateLexical(str, validationContext);
        if (!schemaType.matchPatternFacet(str)) {
            validationContext.invalid(XmlErrorCodes.DATATYPE_VALID$PATTERN_VALID, new Object[]{"float", str, QNameHelper.readable(schemaType)});
        }
        return validateLexical;
    }

    public static void validateValue(float f, SchemaType schemaType, ValidationContext validationContext) {
        XmlAnySimpleType facet = schemaType.getFacet(3);
        if (facet != null) {
            float floatValue = ((XmlObjectBase) facet).getFloatValue();
            if (compare(f, floatValue) <= 0) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_MIN_EXCLUSIVE_VALID, new Object[]{"float", Float.valueOf(f), Float.valueOf(floatValue), QNameHelper.readable(schemaType)});
            }
        }
        XmlAnySimpleType facet2 = schemaType.getFacet(4);
        if (facet2 != null) {
            float floatValue2 = ((XmlObjectBase) facet2).getFloatValue();
            if (compare(f, floatValue2) < 0) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_MIN_INCLUSIVE_VALID, new Object[]{"float", Float.valueOf(f), Float.valueOf(floatValue2), QNameHelper.readable(schemaType)});
            }
        }
        XmlAnySimpleType facet3 = schemaType.getFacet(5);
        if (facet3 != null) {
            float floatValue3 = ((XmlObjectBase) facet3).getFloatValue();
            if (compare(f, floatValue3) > 0) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_MAX_INCLUSIVE_VALID, new Object[]{"float", Float.valueOf(f), Float.valueOf(floatValue3), QNameHelper.readable(schemaType)});
            }
        }
        XmlAnySimpleType facet4 = schemaType.getFacet(6);
        if (facet4 != null) {
            float floatValue4 = ((XmlObjectBase) facet4).getFloatValue();
            if (compare(f, floatValue4) >= 0) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_MAX_EXCLUSIVE_VALID, new Object[]{"float", Float.valueOf(f), Float.valueOf(floatValue4), QNameHelper.readable(schemaType)});
            }
        }
        XmlAnySimpleType[] enumerationValues = schemaType.getEnumerationValues();
        if (enumerationValues != null) {
            int length = enumerationValues.length;
            int i = 0;
            while (i < length) {
                if (compare(f, ((XmlObjectBase) enumerationValues[i]).getFloatValue()) != 0) {
                    i++;
                } else {
                    return;
                }
            }
            validationContext.invalid(XmlErrorCodes.DATATYPE_ENUM_VALID, new Object[]{"float", Float.valueOf(f), QNameHelper.readable(schemaType)});
        }
    }

    /* access modifiers changed from: protected */
    public void validate_simpleval(String str, ValidationContext validationContext) {
        validateLexical(str, schemaType(), validationContext);
        validateValue(getFloatValue(), schemaType(), validationContext);
    }
}
