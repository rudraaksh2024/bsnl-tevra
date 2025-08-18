package org.apache.xmlbeans.impl.values;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;

public abstract class JavaStringHolderEx extends JavaStringHolder {
    private final SchemaType _schemaType;

    public SchemaType schemaType() {
        return this._schemaType;
    }

    public JavaStringHolderEx(SchemaType schemaType, boolean z) {
        this._schemaType = schemaType;
        initComplexType(z, false);
    }

    /* access modifiers changed from: protected */
    public int get_wscanon_rule() {
        return schemaType().getWhiteSpaceRule();
    }

    /* access modifiers changed from: protected */
    public void set_text(String str) {
        if (_validateOnSet()) {
            validateLexical(str, this._schemaType, _voorVc);
        }
        super.set_text(str);
    }

    /* access modifiers changed from: protected */
    public boolean is_defaultable_ws(String str) {
        try {
            validateLexical(str, this._schemaType, _voorVc);
            return false;
        } catch (XmlValueOutOfRangeException unused) {
            return true;
        }
    }

    public static void validateLexical(String str, SchemaType schemaType, ValidationContext validationContext) {
        int intValue;
        int intValue2;
        int intValue3;
        if (!schemaType.matchPatternFacet(str)) {
            validationContext.invalid(XmlErrorCodes.DATATYPE_VALID$PATTERN_VALID, new Object[]{TypedValues.Custom.S_STRING, str, QNameHelper.readable(schemaType)});
            return;
        }
        XmlAnySimpleType facet = schemaType.getFacet(0);
        if (facet == null || str.length() == (intValue3 = ((XmlObjectBase) facet).getBigIntegerValue().intValue())) {
            XmlAnySimpleType facet2 = schemaType.getFacet(1);
            if (facet2 == null || str.length() >= (intValue2 = ((XmlObjectBase) facet2).getBigIntegerValue().intValue())) {
                XmlAnySimpleType facet3 = schemaType.getFacet(2);
                if (facet3 == null || str.length() <= (intValue = ((XmlObjectBase) facet3).getBigIntegerValue().intValue())) {
                    XmlAnySimpleType[] enumerationValues = schemaType.getEnumerationValues();
                    if (enumerationValues != null) {
                        int length = enumerationValues.length;
                        int i = 0;
                        while (i < length) {
                            if (!str.equals(enumerationValues[i].getStringValue())) {
                                i++;
                            } else {
                                return;
                            }
                        }
                        validationContext.invalid(XmlErrorCodes.DATATYPE_ENUM_VALID, new Object[]{TypedValues.Custom.S_STRING, str, QNameHelper.readable(schemaType)});
                        return;
                    }
                    return;
                }
                validationContext.invalid(XmlErrorCodes.DATATYPE_MAX_LENGTH_VALID$STRING, new Object[]{TypedValues.Custom.S_STRING, Integer.valueOf(str.length()), Integer.valueOf(intValue), QNameHelper.readable(schemaType)});
                return;
            }
            validationContext.invalid(XmlErrorCodes.DATATYPE_MIN_LENGTH_VALID$STRING, new Object[]{TypedValues.Custom.S_STRING, Integer.valueOf(str.length()), Integer.valueOf(intValue2), QNameHelper.readable(schemaType)});
            return;
        }
        validationContext.invalid(XmlErrorCodes.DATATYPE_LENGTH_VALID$STRING, new Object[]{TypedValues.Custom.S_STRING, Integer.valueOf(str.length()), Integer.valueOf(intValue3), QNameHelper.readable(schemaType)});
    }

    /* access modifiers changed from: protected */
    public void validate_simpleval(String str, ValidationContext validationContext) {
        validateLexical(getStringValue(), schemaType(), validationContext);
    }
}
