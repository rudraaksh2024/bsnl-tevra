package org.apache.xmlbeans.impl.values;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;

public abstract class JavaStringEnumerationHolderEx extends JavaStringHolderEx {
    private StringEnumAbstractBase _val;

    public JavaStringEnumerationHolderEx(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }

    /* access modifiers changed from: protected */
    public void set_text(String str) {
        StringEnumAbstractBase enumForString = schemaType().enumForString(str);
        if (enumForString != null) {
            super.set_text(str);
            this._val = enumForString;
            return;
        }
        throw new XmlValueOutOfRangeException(XmlErrorCodes.DATATYPE_ENUM_VALID, new Object[]{TypedValues.Custom.S_STRING, str, QNameHelper.readable(schemaType())});
    }

    public static void validateLexical(String str, SchemaType schemaType, ValidationContext validationContext) {
        JavaStringHolderEx.validateLexical(str, schemaType, validationContext);
    }

    /* access modifiers changed from: protected */
    public void set_nil() {
        this._val = null;
        super.set_nil();
    }

    public StringEnumAbstractBase getEnumValue() {
        check_dated();
        return this._val;
    }

    /* access modifiers changed from: protected */
    public void set_enum(StringEnumAbstractBase stringEnumAbstractBase) {
        Class<? extends StringEnumAbstractBase> enumJavaClass = schemaType().getEnumJavaClass();
        if (enumJavaClass == null || stringEnumAbstractBase.getClass().equals(enumJavaClass)) {
            super.set_text(stringEnumAbstractBase.toString());
            this._val = stringEnumAbstractBase;
            return;
        }
        throw new XmlValueOutOfRangeException();
    }
}
