package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;

public abstract class JavaBase64HolderEx extends JavaBase64Holder {
    private final SchemaType _schemaType;

    public SchemaType schemaType() {
        return this._schemaType;
    }

    public JavaBase64HolderEx(SchemaType schemaType, boolean z) {
        this._schemaType = schemaType;
        initComplexType(z, false);
    }

    /* access modifiers changed from: protected */
    public int get_wscanon_rule() {
        return schemaType().getWhiteSpaceRule();
    }

    /* access modifiers changed from: protected */
    public void set_text(String str) {
        byte[] bArr;
        if (_validateOnSet()) {
            bArr = validateLexical(str, schemaType(), _voorVc);
        } else {
            bArr = lex(str, _voorVc);
        }
        if (bArr != null && _validateOnSet()) {
            validateValue(bArr, schemaType(), XmlObjectBase._voorVc);
        }
        if (bArr != null) {
            super.set_ByteArray(bArr);
        }
    }

    /* access modifiers changed from: protected */
    public void set_ByteArray(byte[] bArr) {
        if (_validateOnSet()) {
            validateValue(bArr, schemaType(), _voorVc);
        }
        super.set_ByteArray(bArr);
    }

    public static void validateValue(byte[] bArr, SchemaType schemaType, ValidationContext validationContext) {
        int intValue;
        int intValue2;
        int intValue3;
        XmlAnySimpleType facet = schemaType.getFacet(0);
        if (!(facet == null || (intValue3 = ((XmlObjectBase) facet).getBigIntegerValue().intValue()) == bArr.length)) {
            validationContext.invalid(XmlErrorCodes.DATATYPE_LENGTH_VALID$BINARY, new Object[]{XmlErrorCodes.BASE64BINARY, Integer.valueOf(bArr.length), Integer.valueOf(intValue3), QNameHelper.readable(schemaType)});
        }
        XmlAnySimpleType facet2 = schemaType.getFacet(1);
        if (facet2 != null && (intValue2 = ((XmlObjectBase) facet2).getBigIntegerValue().intValue()) > bArr.length) {
            validationContext.invalid(XmlErrorCodes.DATATYPE_MIN_LENGTH_VALID$BINARY, new Object[]{XmlErrorCodes.BASE64BINARY, Integer.valueOf(bArr.length), Integer.valueOf(intValue2), QNameHelper.readable(schemaType)});
        }
        XmlAnySimpleType facet3 = schemaType.getFacet(2);
        if (facet3 != null && (intValue = ((XmlObjectBase) facet3).getBigIntegerValue().intValue()) < bArr.length) {
            validationContext.invalid(XmlErrorCodes.DATATYPE_MAX_LENGTH_VALID$BINARY, new Object[]{XmlErrorCodes.BASE64BINARY, Integer.valueOf(bArr.length), Integer.valueOf(intValue), QNameHelper.readable(schemaType)});
        }
        XmlAnySimpleType[] enumerationValues = schemaType.getEnumerationValues();
        if (enumerationValues != null) {
            int i = 0;
            loop0:
            while (i < enumerationValues.length) {
                byte[] byteArrayValue = ((XmlObjectBase) enumerationValues[i]).getByteArrayValue();
                if (byteArrayValue.length == bArr.length) {
                    int i2 = 0;
                    while (i2 < byteArrayValue.length) {
                        if (byteArrayValue[i2] == bArr[i2]) {
                            i2++;
                        }
                    }
                    break loop0;
                }
                i++;
            }
            if (i >= enumerationValues.length) {
                validationContext.invalid(XmlErrorCodes.DATATYPE_ENUM_VALID$NO_VALUE, new Object[]{XmlErrorCodes.BASE64BINARY, QNameHelper.readable(schemaType)});
            }
        }
    }

    /* access modifiers changed from: protected */
    public void validate_simpleval(String str, ValidationContext validationContext) {
        validateLexical(str, schemaType(), validationContext);
        validateValue(getByteArrayValue(), schemaType(), validationContext);
    }
}
