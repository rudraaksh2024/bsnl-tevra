package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;

public class XmlAnySimpleTypeImpl extends XmlObjectBase implements XmlAnySimpleType {
    private final SchemaType _schemaType;
    String _textvalue;

    /* access modifiers changed from: protected */
    public int get_wscanon_rule() {
        return 1;
    }

    public XmlAnySimpleTypeImpl(SchemaType schemaType, boolean z) {
        this._textvalue = "";
        this._schemaType = schemaType;
        initComplexType(z, false);
    }

    public XmlAnySimpleTypeImpl() {
        this._textvalue = "";
        this._schemaType = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
    }

    public SchemaType schemaType() {
        return this._schemaType;
    }

    /* access modifiers changed from: protected */
    public String compute_text(NamespaceManager namespaceManager) {
        return this._textvalue;
    }

    /* access modifiers changed from: protected */
    public void set_text(String str) {
        this._textvalue = str;
    }

    /* access modifiers changed from: protected */
    public void set_nil() {
        this._textvalue = null;
    }

    /* access modifiers changed from: protected */
    public boolean equal_to(XmlObject xmlObject) {
        return this._textvalue.equals(((XmlAnySimpleType) xmlObject).getStringValue());
    }

    /* access modifiers changed from: protected */
    public int value_hash_code() {
        String str = this._textvalue;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }
}
