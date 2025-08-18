package org.apache.xmlbeans.impl.schema;

import org.apache.xmlbeans.SchemaTypeSystem;

public class SimpleTypeFactory<T> extends ElementFactory<T> {
    public SimpleTypeFactory(SchemaTypeSystem schemaTypeSystem, String str) {
        super(schemaTypeSystem, str);
    }

    public T newValue(Object obj) {
        return getType().newValue(obj);
    }
}
