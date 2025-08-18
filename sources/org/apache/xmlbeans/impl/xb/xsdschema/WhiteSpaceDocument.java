package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlNMTOKEN;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface WhiteSpaceDocument extends XmlObject {
    public static final DocumentFactory<WhiteSpaceDocument> Factory;
    public static final SchemaType type;

    WhiteSpace addNewWhiteSpace();

    WhiteSpace getWhiteSpace();

    void setWhiteSpace(WhiteSpace whiteSpace);

    static {
        DocumentFactory<WhiteSpaceDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "whitespaced2c6doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface WhiteSpace extends Facet {
        public static final ElementFactory<WhiteSpace> Factory;
        public static final SchemaType type;

        static {
            ElementFactory<WhiteSpace> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "whitespace97ffelemtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }

        public interface Value extends XmlNMTOKEN {
            public static final Enum COLLAPSE = Enum.forString("collapse");
            public static final ElementFactory<Value> Factory;
            public static final int INT_COLLAPSE = 3;
            public static final int INT_PRESERVE = 1;
            public static final int INT_REPLACE = 2;
            public static final Enum PRESERVE = Enum.forString("preserve");
            public static final Enum REPLACE = Enum.forString("replace");
            public static final SchemaType type;

            StringEnumAbstractBase getEnumValue();

            void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

            static {
                ElementFactory<Value> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "value8186attrtype");
                Factory = elementFactory;
                type = elementFactory.getType();
            }

            public static final class Enum extends StringEnumAbstractBase {
                static final int INT_COLLAPSE = 3;
                static final int INT_PRESERVE = 1;
                static final int INT_REPLACE = 2;
                private static final long serialVersionUID = 1;
                public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("preserve", 1), new Enum("replace", 2), new Enum("collapse", 3)});

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
}
