package org.apache.xmlbeans.impl.xb.xmlschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLLANG.TypeSystemHolder;

public interface SpaceAttribute extends XmlObject {
    public static final DocumentFactory<SpaceAttribute> Factory;
    public static final SchemaType type;

    Space.Enum getSpace();

    boolean isSetSpace();

    void setSpace(Space.Enum enumR);

    void unsetSpace();

    Space xgetSpace();

    void xsetSpace(Space space);

    static {
        DocumentFactory<SpaceAttribute> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "space9344attrtypetype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface Space extends XmlNCName {
        public static final Enum DEFAULT = Enum.forString("default");
        public static final ElementFactory<Space> Factory;
        public static final int INT_DEFAULT = 1;
        public static final int INT_PRESERVE = 2;
        public static final Enum PRESERVE = Enum.forString("preserve");
        public static final SchemaType type;

        StringEnumAbstractBase getEnumValue();

        void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

        static {
            ElementFactory<Space> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "spaceb986attrtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }

        public static final class Enum extends StringEnumAbstractBase {
            static final int INT_DEFAULT = 1;
            static final int INT_PRESERVE = 2;
            private static final long serialVersionUID = 1;
            public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("default", 1), new Enum("preserve", 2)});

            public static Enum forString(String str) {
                return (Enum) table.forString(str);
            }

            public static Enum forInt(int i) {
                return (Enum) table.forInt(i);
            }

            private Enum(String str, int i) {
                super(str, i);
            }

            private Object readResolve() {
                return forInt(intValue());
            }
        }
    }
}
