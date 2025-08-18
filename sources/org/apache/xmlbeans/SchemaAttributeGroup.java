package org.apache.xmlbeans;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaComponent;

public interface SchemaAttributeGroup extends SchemaComponent, SchemaAnnotated {
    int getComponentType();

    QName getName();

    Object getUserData();

    public static final class Ref extends SchemaComponent.Ref {
        public final int getComponentType() {
            return 4;
        }

        public Ref(SchemaAttributeGroup schemaAttributeGroup) {
            super(schemaAttributeGroup);
        }

        public Ref(SchemaTypeSystem schemaTypeSystem, String str) {
            super(schemaTypeSystem, str);
        }

        public final SchemaAttributeGroup get() {
            return (SchemaAttributeGroup) getComponent();
        }
    }
}
