package org.apache.xmlbeans;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaComponent;

public interface SchemaModelGroup extends SchemaComponent, SchemaAnnotated {
    int getComponentType();

    QName getName();

    Object getUserData();

    public static final class Ref extends SchemaComponent.Ref {
        public final int getComponentType() {
            return 6;
        }

        public Ref(SchemaModelGroup schemaModelGroup) {
            super(schemaModelGroup);
        }

        public Ref(SchemaTypeSystem schemaTypeSystem, String str) {
            super(schemaTypeSystem, str);
        }

        public final SchemaModelGroup get() {
            return (SchemaModelGroup) getComponent();
        }
    }
}
