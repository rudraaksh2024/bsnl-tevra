package org.apache.xmlbeans.impl.values;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.impl.common.PrefixResolver;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;

public abstract class JavaNotationHolderEx extends JavaNotationHolder {
    private SchemaType _schemaType;

    public SchemaType schemaType() {
        return this._schemaType;
    }

    public JavaNotationHolderEx(SchemaType schemaType, boolean z) {
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
            if (!check(str, this._schemaType)) {
                throw new XmlValueOutOfRangeException();
            } else if (!this._schemaType.matchPatternFacet(str)) {
                throw new XmlValueOutOfRangeException();
            }
        }
        super.set_text(str);
    }

    /* access modifiers changed from: protected */
    public void set_notation(String str) {
        set_text(str);
    }

    /* access modifiers changed from: protected */
    public void set_xmlanysimple(XmlAnySimpleType xmlAnySimpleType) {
        QName qName;
        if (_validateOnSet()) {
            qName = validateLexical(xmlAnySimpleType.getStringValue(), this._schemaType, _voorVc, NamespaceContext.getCurrent());
            if (qName != null) {
                validateValue(qName, this._schemaType, _voorVc);
            }
        } else {
            qName = JavaNotationHolder.validateLexical(xmlAnySimpleType.getStringValue(), _voorVc, NamespaceContext.getCurrent());
        }
        super.set_QName(qName);
    }

    public static QName validateLexical(String str, SchemaType schemaType, ValidationContext validationContext, PrefixResolver prefixResolver) {
        QName validateLexical = JavaQNameHolder.validateLexical(str, validationContext, prefixResolver);
        if (schemaType.hasPatternFacet() && !schemaType.matchPatternFacet(str)) {
            validationContext.invalid(XmlErrorCodes.DATATYPE_VALID$PATTERN_VALID, new Object[]{"NOTATION", str, QNameHelper.readable(schemaType)});
        }
        check(str, schemaType);
        return validateLexical;
    }

    private static boolean check(String str, SchemaType schemaType) {
        XmlAnySimpleType facet = schemaType.getFacet(0);
        if (facet != null && str.length() == ((XmlObjectBase) facet).getBigIntegerValue().intValue()) {
            return false;
        }
        XmlAnySimpleType facet2 = schemaType.getFacet(1);
        if (facet2 != null && str.length() < ((XmlObjectBase) facet2).getBigIntegerValue().intValue()) {
            return false;
        }
        XmlAnySimpleType facet3 = schemaType.getFacet(2);
        if (facet3 == null || str.length() <= ((XmlObjectBase) facet3).getBigIntegerValue().intValue()) {
            return true;
        }
        return false;
    }

    public static void validateValue(QName qName, SchemaType schemaType, ValidationContext validationContext) {
        XmlAnySimpleType[] enumerationValues = schemaType.getEnumerationValues();
        if (enumerationValues != null) {
            int i = 0;
            while (i < enumerationValues.length) {
                if (!qName.equals(((XmlObjectBase) enumerationValues[i]).getQNameValue())) {
                    i++;
                } else {
                    return;
                }
            }
            validationContext.invalid(XmlErrorCodes.DATATYPE_ENUM_VALID, new Object[]{"NOTATION", qName, QNameHelper.readable(schemaType)});
        }
    }
}
