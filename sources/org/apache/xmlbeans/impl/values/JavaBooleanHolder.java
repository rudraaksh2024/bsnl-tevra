package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;

public abstract class JavaBooleanHolder extends XmlObjectBase {
    private boolean _value;

    public SchemaType schemaType() {
        return BuiltinSchemaTypeSystem.ST_BOOLEAN;
    }

    /* access modifiers changed from: protected */
    public String compute_text(NamespaceManager namespaceManager) {
        return this._value ? "true" : "false";
    }

    /* access modifiers changed from: protected */
    public void set_text(String str) {
        this._value = validateLexical(str, _voorVc);
    }

    public static boolean validateLexical(String str, ValidationContext validationContext) {
        if (str.equals("true") || str.equals("1")) {
            return true;
        }
        if (!str.equals("false") && !str.equals("0")) {
            validationContext.invalid("boolean", new Object[]{str});
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void set_nil() {
        this._value = false;
    }

    public boolean getBooleanValue() {
        check_dated();
        return this._value;
    }

    /* access modifiers changed from: protected */
    public void set_boolean(boolean z) {
        this._value = z;
    }

    /* access modifiers changed from: protected */
    public int compare_to(XmlObject xmlObject) {
        return this._value == ((XmlBoolean) xmlObject).getBooleanValue() ? 0 : 2;
    }

    /* access modifiers changed from: protected */
    public boolean equal_to(XmlObject xmlObject) {
        return this._value == ((XmlBoolean) xmlObject).getBooleanValue();
    }

    /* access modifiers changed from: protected */
    public int value_hash_code() {
        return this._value ? 957379554 : 676335975;
    }
}
