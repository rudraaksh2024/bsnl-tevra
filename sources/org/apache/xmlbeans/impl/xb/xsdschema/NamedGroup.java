package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface NamedGroup extends RealGroup {
    public static final DocumentFactory<NamedGroup> Factory;
    public static final SchemaType type;

    String getName();

    boolean isSetName();

    void setName(String str);

    void unsetName();

    XmlNCName xgetName();

    void xsetName(XmlNCName xmlNCName);

    static {
        DocumentFactory<NamedGroup> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "namedgroup878dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface All extends All {
        public static final ElementFactory<All> Factory;
        public static final SchemaType type;

        static {
            ElementFactory<All> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "all82daelemtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }
    }
}
