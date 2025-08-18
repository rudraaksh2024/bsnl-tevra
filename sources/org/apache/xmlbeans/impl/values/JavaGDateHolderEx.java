package org.apache.xmlbeans.impl.values;

import java.util.Calendar;
import java.util.Date;
import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDateBuilder;
import org.apache.xmlbeans.GDateSpecification;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;

public abstract class JavaGDateHolderEx extends XmlObjectBase {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final SchemaType _schemaType;
    private GDate _value;

    public JavaGDateHolderEx(SchemaType schemaType, boolean z) {
        this._schemaType = schemaType;
        initComplexType(z, false);
    }

    public SchemaType schemaType() {
        return this._schemaType;
    }

    /* access modifiers changed from: protected */
    public String compute_text(NamespaceManager namespaceManager) {
        GDate gDate = this._value;
        return gDate == null ? "" : gDate.toString();
    }

    /* access modifiers changed from: protected */
    public void set_text(String str) {
        GDate gDate;
        if (_validateOnSet()) {
            gDate = validateLexical(str, this._schemaType, _voorVc);
        } else {
            gDate = lex(str, this._schemaType, _voorVc);
        }
        if (_validateOnSet() && gDate != null) {
            validateValue(gDate, this._schemaType, _voorVc);
        }
        this._value = gDate;
    }

    public static GDate lex(String str, SchemaType schemaType, ValidationContext validationContext) {
        GDate gDate;
        try {
            gDate = new GDate((CharSequence) str);
        } catch (Exception unused) {
            validationContext.invalid(XmlErrorCodes.DATE, new Object[]{str});
            gDate = null;
        }
        if (gDate != null) {
            if (gDate.getBuiltinTypeCode() != schemaType.getPrimitiveType().getBuiltinTypeCode()) {
                validationContext.invalid(XmlErrorCodes.DATE, new Object[]{"wrong type: " + str});
                return null;
            } else if (!gDate.isValid()) {
                validationContext.invalid(XmlErrorCodes.DATE, new Object[]{str});
                return null;
            }
        }
        return gDate;
    }

    public static GDate validateLexical(String str, SchemaType schemaType, ValidationContext validationContext) {
        GDate lex = lex(str, schemaType, validationContext);
        if (lex != null && schemaType.hasPatternFacet() && !schemaType.matchPatternFacet(str)) {
            validationContext.invalid(XmlErrorCodes.DATATYPE_VALID$PATTERN_VALID, new Object[]{XmlErrorCodes.DATE, str, QNameHelper.readable(schemaType)});
        }
        return lex;
    }

    public static void validateValue(GDateSpecification gDateSpecification, SchemaType schemaType, ValidationContext validationContext) {
        if (gDateSpecification.getBuiltinTypeCode() != schemaType.getPrimitiveType().getBuiltinTypeCode()) {
            validationContext.invalid(XmlErrorCodes.DATE, new Object[]{"Date (" + gDateSpecification + ") does not have the set of fields required for " + QNameHelper.readable(schemaType)});
        }
        XmlAnySimpleType facet = schemaType.getFacet(3);
        if (facet != null) {
            GDate gDateValue = ((XmlObjectBase) facet).getGDateValue();
            if (gDateSpecification.compareToGDate(gDateValue) <= 0) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_MIN_EXCLUSIVE_VALID, new Object[]{XmlErrorCodes.DATE, gDateSpecification, gDateValue, QNameHelper.readable(schemaType)});
            }
        }
        XmlAnySimpleType facet2 = schemaType.getFacet(4);
        if (facet2 != null) {
            GDate gDateValue2 = ((XmlObjectBase) facet2).getGDateValue();
            if (gDateSpecification.compareToGDate(gDateValue2) < 0) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_MIN_INCLUSIVE_VALID, new Object[]{XmlErrorCodes.DATE, gDateSpecification, gDateValue2, QNameHelper.readable(schemaType)});
            }
        }
        XmlAnySimpleType facet3 = schemaType.getFacet(6);
        if (facet3 != null) {
            GDate gDateValue3 = ((XmlObjectBase) facet3).getGDateValue();
            if (gDateSpecification.compareToGDate(gDateValue3) >= 0) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_MAX_EXCLUSIVE_VALID, new Object[]{XmlErrorCodes.DATE, gDateSpecification, gDateValue3, QNameHelper.readable(schemaType)});
            }
        }
        XmlAnySimpleType facet4 = schemaType.getFacet(5);
        if (facet4 != null) {
            GDate gDateValue4 = ((XmlObjectBase) facet4).getGDateValue();
            if (gDateSpecification.compareToGDate(gDateValue4) > 0) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_MAX_INCLUSIVE_VALID, new Object[]{XmlErrorCodes.DATE, gDateSpecification, gDateValue4, QNameHelper.readable(schemaType)});
            }
        }
        XmlAnySimpleType[] enumerationValues = schemaType.getEnumerationValues();
        if (enumerationValues != null) {
            int length = enumerationValues.length;
            int i = 0;
            while (i < length) {
                if (gDateSpecification.compareToGDate(((XmlObjectBase) enumerationValues[i]).getGDateValue()) != 0) {
                    i++;
                } else {
                    return;
                }
            }
            validationContext.invalid(XmlErrorCodes.DATATYPE_ENUM_VALID, new Object[]{XmlErrorCodes.DATE, gDateSpecification, QNameHelper.readable(schemaType)});
        }
    }

    /* access modifiers changed from: protected */
    public void set_nil() {
        this._value = null;
    }

    public int getIntValue() {
        int builtinTypeCode = schemaType().getPrimitiveType().getBuiltinTypeCode();
        if (builtinTypeCode == 20 || builtinTypeCode == 21 || builtinTypeCode == 18) {
            check_dated();
            GDate gDate = this._value;
            if (gDate == null) {
                return 0;
            }
            if (builtinTypeCode == 18) {
                return gDate.getYear();
            }
            if (builtinTypeCode == 20) {
                return gDate.getDay();
            }
            if (builtinTypeCode == 21) {
                return gDate.getMonth();
            }
            throw new IllegalStateException();
        }
        throw new XmlValueOutOfRangeException();
    }

    public GDate getGDateValue() {
        check_dated();
        GDate gDate = this._value;
        if (gDate == null) {
            return null;
        }
        return gDate;
    }

    public Calendar getCalendarValue() {
        check_dated();
        GDate gDate = this._value;
        if (gDate == null) {
            return null;
        }
        return gDate.getCalendar();
    }

    public Date getDateValue() {
        check_dated();
        GDate gDate = this._value;
        if (gDate == null) {
            return null;
        }
        return gDate.getDate();
    }

    /* access modifiers changed from: protected */
    public void set_int(int i) {
        int builtinTypeCode = schemaType().getPrimitiveType().getBuiltinTypeCode();
        if (builtinTypeCode == 20 || builtinTypeCode == 21 || builtinTypeCode == 18) {
            GDateBuilder gDateBuilder = new GDateBuilder();
            if (builtinTypeCode == 18) {
                gDateBuilder.setYear(i);
            } else if (builtinTypeCode == 20) {
                gDateBuilder.setDay(i);
            } else if (builtinTypeCode == 21) {
                gDateBuilder.setMonth(i);
            }
            if (_validateOnSet()) {
                validateValue(gDateBuilder, this._schemaType, _voorVc);
            }
            this._value = gDateBuilder.toGDate();
            return;
        }
        throw new XmlValueOutOfRangeException();
    }

    /* access modifiers changed from: protected */
    public void set_GDate(GDateSpecification gDateSpecification) {
        GDate gDate;
        int builtinTypeCode = schemaType().getPrimitiveType().getBuiltinTypeCode();
        if (!gDateSpecification.isImmutable() || !(gDateSpecification instanceof GDate) || gDateSpecification.getBuiltinTypeCode() != builtinTypeCode) {
            if (gDateSpecification.getBuiltinTypeCode() != builtinTypeCode) {
                GDateBuilder gDateBuilder = new GDateBuilder(gDateSpecification);
                gDateBuilder.setBuiltinTypeCode(builtinTypeCode);
                gDateSpecification = gDateBuilder;
            }
            gDate = new GDate(gDateSpecification);
        } else {
            gDate = (GDate) gDateSpecification;
        }
        if (_validateOnSet()) {
            validateValue(gDate, this._schemaType, _voorVc);
        }
        this._value = gDate;
    }

    /* access modifiers changed from: protected */
    public void set_Calendar(Calendar calendar) {
        int builtinTypeCode = schemaType().getPrimitiveType().getBuiltinTypeCode();
        GDateBuilder gDateBuilder = new GDateBuilder(calendar);
        gDateBuilder.setBuiltinTypeCode(builtinTypeCode);
        GDate gDate = gDateBuilder.toGDate();
        if (_validateOnSet()) {
            validateValue(gDate, this._schemaType, _voorVc);
        }
        this._value = gDate;
    }

    /* access modifiers changed from: protected */
    public void set_Date(Date date) {
        int builtinTypeCode = schemaType().getPrimitiveType().getBuiltinTypeCode();
        if ((builtinTypeCode == 16 || builtinTypeCode == 14) && date != null) {
            GDateBuilder gDateBuilder = new GDateBuilder(date);
            gDateBuilder.setBuiltinTypeCode(builtinTypeCode);
            GDate gDate = gDateBuilder.toGDate();
            if (_validateOnSet()) {
                validateValue(gDate, this._schemaType, _voorVc);
            }
            this._value = gDate;
            return;
        }
        throw new XmlValueOutOfRangeException();
    }

    /* access modifiers changed from: protected */
    public int compare_to(XmlObject xmlObject) {
        return this._value.compareToGDate(((XmlObjectBase) xmlObject).getGDateValue());
    }

    /* access modifiers changed from: protected */
    public boolean equal_to(XmlObject xmlObject) {
        return this._value.equals(((XmlObjectBase) xmlObject).getGDateValue());
    }

    /* access modifiers changed from: protected */
    public int value_hash_code() {
        return this._value.hashCode();
    }

    /* access modifiers changed from: protected */
    public void validate_simpleval(String str, ValidationContext validationContext) {
        validateLexical(str, schemaType(), validationContext);
        validateValue(getGDateValue(), schemaType(), validationContext);
    }
}
