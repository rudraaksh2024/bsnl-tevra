package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;

public abstract class JavaUriHolder extends XmlObjectBase {
    private String _value;

    public SchemaType schemaType() {
        return BuiltinSchemaTypeSystem.ST_ANY_URI;
    }

    public String compute_text(NamespaceManager namespaceManager) {
        String str = this._value;
        return str == null ? "" : str;
    }

    /* access modifiers changed from: protected */
    public void set_text(String str) {
        if (_validateOnSet()) {
            validateLexical(str, _voorVc);
        }
        this._value = str;
    }

    public static void validateLexical(String str, ValidationContext validationContext) {
        if (str.startsWith("##")) {
            validationContext.invalid(XmlErrorCodes.ANYURI, new Object[]{str});
        }
    }

    /* access modifiers changed from: protected */
    public void set_nil() {
        this._value = null;
    }

    /* access modifiers changed from: protected */
    public boolean equal_to(XmlObject xmlObject) {
        return this._value.equals(((XmlAnyURI) xmlObject).getStringValue());
    }

    /* access modifiers changed from: protected */
    public int value_hash_code() {
        return this._value.hashCode();
    }
}
