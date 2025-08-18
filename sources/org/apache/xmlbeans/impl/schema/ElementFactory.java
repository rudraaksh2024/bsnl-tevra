package org.apache.xmlbeans.impl.schema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlOptions;

public class ElementFactory<T> {
    private final SchemaType type;
    private final SchemaTypeSystem typeSystem;

    public ElementFactory(SchemaTypeSystem schemaTypeSystem, String str) {
        this.typeSystem = schemaTypeSystem;
        this.type = (SchemaType) schemaTypeSystem.resolveHandle(str);
    }

    public SchemaType getType() {
        return this.type;
    }

    public SchemaTypeSystem getTypeLoader() {
        return this.typeSystem;
    }

    public T newInstance() {
        return getTypeLoader().newInstance(this.type, (XmlOptions) null);
    }

    public T newInstance(XmlOptions xmlOptions) {
        return getTypeLoader().newInstance(this.type, xmlOptions);
    }
}
