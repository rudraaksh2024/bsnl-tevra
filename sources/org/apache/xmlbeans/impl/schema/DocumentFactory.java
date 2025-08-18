package org.apache.xmlbeans.impl.schema;

import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlOptions;

public class DocumentFactory<T> extends AbstractDocumentFactory<T> {
    public DocumentFactory(SchemaTypeSystem schemaTypeSystem, String str) {
        super(schemaTypeSystem, str);
    }

    public T newInstance() {
        return getTypeLoader().newInstance(getType(), (XmlOptions) null);
    }

    public T newInstance(XmlOptions xmlOptions) {
        return getTypeLoader().newInstance(getType(), xmlOptions);
    }
}
