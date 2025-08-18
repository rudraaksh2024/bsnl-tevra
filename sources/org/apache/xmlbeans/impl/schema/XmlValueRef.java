package org.apache.xmlbeans.impl.schema;

import java.util.ArrayList;
import java.util.List;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;

public class XmlValueRef {
    Object _initVal;
    XmlAnySimpleType _obj;
    SchemaType.Ref _typeref;

    public XmlValueRef(XmlAnySimpleType xmlAnySimpleType) {
        if (xmlAnySimpleType != null) {
            this._obj = xmlAnySimpleType;
            return;
        }
        throw new IllegalArgumentException();
    }

    XmlValueRef(SchemaType.Ref ref, Object obj) {
        if (ref != null) {
            this._typeref = ref;
            this._initVal = obj;
            return;
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: package-private */
    public synchronized XmlAnySimpleType get() {
        if (this._obj == null) {
            SchemaType schemaType = this._typeref.get();
            if (schemaType.getSimpleVariety() != 3) {
                this._obj = schemaType.newValue(this._initVal);
            } else {
                ArrayList arrayList = new ArrayList();
                for (XmlValueRef xmlValueRef : (List) this._initVal) {
                    arrayList.add(xmlValueRef.get());
                }
                this._obj = schemaType.newValue(arrayList);
            }
        }
        return this._obj;
    }
}
