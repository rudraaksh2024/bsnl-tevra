package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.GDurationSpecification;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;

public abstract class JavaGDurationHolderEx extends XmlObjectBase {
    private final SchemaType _schemaType;
    GDuration _value;

    public JavaGDurationHolderEx(SchemaType schemaType, boolean z) {
        this._schemaType = schemaType;
        initComplexType(z, false);
    }

    public SchemaType schemaType() {
        return this._schemaType;
    }

    /* access modifiers changed from: protected */
    public void set_text(String str) {
        GDuration gDuration;
        if (_validateOnSet()) {
            gDuration = validateLexical(str, this._schemaType, _voorVc);
        } else {
            gDuration = lex(str, _voorVc);
        }
        if (_validateOnSet() && gDuration != null) {
            validateValue(gDuration, this._schemaType, _voorVc);
        }
        this._value = gDuration;
    }

    /* access modifiers changed from: protected */
    public void set_GDuration(GDurationSpecification gDurationSpecification) {
        if (_validateOnSet()) {
            validateValue(gDurationSpecification, this._schemaType, _voorVc);
        }
        if (!gDurationSpecification.isImmutable() || !(gDurationSpecification instanceof GDuration)) {
            this._value = new GDuration(gDurationSpecification);
        } else {
            this._value = (GDuration) gDurationSpecification;
        }
    }

    /* access modifiers changed from: protected */
    public String compute_text(NamespaceManager namespaceManager) {
        GDuration gDuration = this._value;
        return gDuration == null ? "" : gDuration.toString();
    }

    /* access modifiers changed from: protected */
    public void set_nil() {
        this._value = null;
    }

    public GDuration getGDurationValue() {
        check_dated();
        GDuration gDuration = this._value;
        if (gDuration == null) {
            return null;
        }
        return gDuration;
    }

    public static GDuration lex(String str, ValidationContext validationContext) {
        try {
            return new GDuration((CharSequence) str);
        } catch (Exception unused) {
            validationContext.invalid("duration", new Object[]{str});
            return null;
        }
    }

    public static GDuration validateLexical(String str, SchemaType schemaType, ValidationContext validationContext) {
        GDuration lex = lex(str, validationContext);
        if (lex != null && schemaType.hasPatternFacet() && !schemaType.matchPatternFacet(str)) {
            validationContext.invalid(XmlErrorCodes.DATATYPE_VALID$PATTERN_VALID, new Object[]{"duration", str, QNameHelper.readable(schemaType)});
        }
        return lex;
    }

    public static void validateValue(GDurationSpecification gDurationSpecification, SchemaType schemaType, ValidationContext validationContext) {
        XmlAnySimpleType facet = schemaType.getFacet(3);
        if (facet != null) {
            GDuration gDurationValue = ((XmlObjectBase) facet).getGDurationValue();
            if (gDurationSpecification.compareToGDuration(gDurationValue) <= 0) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_MIN_EXCLUSIVE_VALID, new Object[]{"duration", gDurationSpecification, gDurationValue, QNameHelper.readable(schemaType)});
            }
        }
        XmlAnySimpleType facet2 = schemaType.getFacet(4);
        if (facet2 != null) {
            GDuration gDurationValue2 = ((XmlObjectBase) facet2).getGDurationValue();
            if (gDurationSpecification.compareToGDuration(gDurationValue2) < 0) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_MIN_INCLUSIVE_VALID, new Object[]{"duration", gDurationSpecification, gDurationValue2, QNameHelper.readable(schemaType)});
            }
        }
        XmlAnySimpleType facet3 = schemaType.getFacet(6);
        if (facet3 != null) {
            GDuration gDurationValue3 = ((XmlObjectBase) facet3).getGDurationValue();
            if (gDurationSpecification.compareToGDuration(gDurationValue3) >= 0) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_MAX_EXCLUSIVE_VALID, new Object[]{"duration", gDurationSpecification, gDurationValue3, QNameHelper.readable(schemaType)});
            }
        }
        XmlAnySimpleType facet4 = schemaType.getFacet(5);
        if (facet4 != null) {
            GDuration gDurationValue4 = ((XmlObjectBase) facet4).getGDurationValue();
            if (gDurationSpecification.compareToGDuration(gDurationValue4) > 0) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_MAX_INCLUSIVE_VALID, new Object[]{"duration", gDurationSpecification, gDurationValue4, QNameHelper.readable(schemaType)});
            }
        }
        XmlAnySimpleType[] enumerationValues = schemaType.getEnumerationValues();
        if (enumerationValues != null) {
            int length = enumerationValues.length;
            int i = 0;
            while (i < length) {
                if (gDurationSpecification.compareToGDuration(((XmlObjectBase) enumerationValues[i]).getGDurationValue()) != 0) {
                    i++;
                } else {
                    return;
                }
            }
            validationContext.invalid(XmlErrorCodes.DATATYPE_ENUM_VALID, new Object[]{"duration", gDurationSpecification, QNameHelper.readable(schemaType)});
        }
    }

    /* access modifiers changed from: protected */
    public int compare_to(XmlObject xmlObject) {
        return this._value.compareToGDuration(((XmlObjectBase) xmlObject).getGDurationValue());
    }

    /* access modifiers changed from: protected */
    public boolean equal_to(XmlObject xmlObject) {
        return this._value.equals(((XmlObjectBase) xmlObject).getGDurationValue());
    }

    /* access modifiers changed from: protected */
    public int value_hash_code() {
        return this._value.hashCode();
    }

    /* access modifiers changed from: protected */
    public void validate_simpleval(String str, ValidationContext validationContext) {
        validateLexical(str, schemaType(), validationContext);
        validateValue(getGDurationValue(), schemaType(), validationContext);
    }
}
