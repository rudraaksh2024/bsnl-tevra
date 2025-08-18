package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;

public class JavaStringHolder extends XmlObjectBase {
    private String _value;

    /* access modifiers changed from: protected */
    public int get_wscanon_rule() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public boolean is_defaultable_ws(String str) {
        return false;
    }

    public SchemaType schemaType() {
        return BuiltinSchemaTypeSystem.ST_STRING;
    }

    public String compute_text(NamespaceManager namespaceManager) {
        return this._value;
    }

    /* access modifiers changed from: protected */
    public void set_text(String str) {
        this._value = str;
    }

    /* access modifiers changed from: protected */
    public void set_nil() {
        this._value = null;
    }

    /* access modifiers changed from: protected */
    public boolean equal_to(XmlObject xmlObject) {
        return this._value.equals(((XmlObjectBase) xmlObject).getStringValue());
    }

    /* access modifiers changed from: protected */
    public int value_hash_code() {
        return this._value.hashCode();
    }
}
