package org.apache.xmlbeans.impl.xb.xsdschema;

import java.util.List;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface NamespaceList extends XmlAnySimpleType {
    public static final SimpleTypeFactory<NamespaceList> Factory;
    public static final SchemaType type;

    Object getObjectValue();

    SchemaType instanceType();

    void setObjectValue(Object obj);

    static {
        SimpleTypeFactory<NamespaceList> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "namespacelist10cctype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public interface Member extends XmlToken {
        public static final Enum ANY = Enum.forString("##any");
        public static final ElementFactory<Member> Factory;
        public static final int INT_ANY = 1;
        public static final int INT_OTHER = 2;
        public static final Enum OTHER = Enum.forString("##other");
        public static final SchemaType type;

        StringEnumAbstractBase getEnumValue();

        void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

        static {
            ElementFactory<Member> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "anonfac7type");
            Factory = elementFactory;
            type = elementFactory.getType();
        }

        public static final class Enum extends StringEnumAbstractBase {
            static final int INT_ANY = 1;
            static final int INT_OTHER = 2;
            private static final long serialVersionUID = 1;
            public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("##any", 1), new Enum("##other", 2)});

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

    public interface Member2 extends XmlAnySimpleType {
        public static final ElementFactory<Member2> Factory;
        public static final SchemaType type;

        List getListValue();

        void setListValue(List<?> list);

        List xgetListValue();

        static {
            ElementFactory<Member2> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "anona0e6type");
            Factory = elementFactory;
            type = elementFactory.getType();
        }

        public interface Item extends XmlAnySimpleType {
            public static final ElementFactory<Item> Factory;
            public static final SchemaType type;

            Object getObjectValue();

            SchemaType instanceType();

            void setObjectValue(Object obj);

            static {
                ElementFactory<Item> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "anon16cetype");
                Factory = elementFactory;
                type = elementFactory.getType();
            }

            public interface Member extends XmlToken {
                public static final ElementFactory<Member> Factory;
                public static final int INT_LOCAL = 2;
                public static final int INT_TARGET_NAMESPACE = 1;
                public static final Enum LOCAL = Enum.forString("##local");
                public static final Enum TARGET_NAMESPACE = Enum.forString("##targetNamespace");
                public static final SchemaType type;

                StringEnumAbstractBase getEnumValue();

                void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

                static {
                    ElementFactory<Member> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "anon0c73type");
                    Factory = elementFactory;
                    type = elementFactory.getType();
                }

                public static final class Enum extends StringEnumAbstractBase {
                    static final int INT_LOCAL = 2;
                    static final int INT_TARGET_NAMESPACE = 1;
                    private static final long serialVersionUID = 1;
                    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("##targetNamespace", 1), new Enum("##local", 2)});

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
}
