package org.apache.xmlbeans.impl.xb.xmlconfig;

import java.util.List;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;
import org.apache.xmlbeans.metadata.system.sXMLCONFIG.TypeSystemHolder;

public interface NamespaceList extends XmlAnySimpleType {
    public static final SimpleTypeFactory<NamespaceList> Factory;
    public static final SchemaType type;

    Object getObjectValue();

    SchemaType instanceType();

    void setObjectValue(Object obj);

    static {
        SimpleTypeFactory<NamespaceList> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "namespacelist20datype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public interface Member extends XmlToken {
        public static final Enum ANY = Enum.forString("##any");
        public static final ElementFactory<Member> Factory;
        public static final int INT_ANY = 1;
        public static final SchemaType type;

        StringEnumAbstractBase getEnumValue();

        void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

        static {
            ElementFactory<Member> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "anonc6fftype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }

        public static final class Enum extends StringEnumAbstractBase {
            static final int INT_ANY = 1;
            private static final long serialVersionUID = 1;
            public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("##any", 1)});

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
            ElementFactory<Member2> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "anon5680type");
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
                ElementFactory<Item> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "anon0798type");
                Factory = elementFactory;
                type = elementFactory.getType();
            }

            public interface Member extends XmlToken {
                public static final ElementFactory<Member> Factory;
                public static final int INT_LOCAL = 1;
                public static final Enum LOCAL = Enum.forString("##local");
                public static final SchemaType type;

                StringEnumAbstractBase getEnumValue();

                void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

                static {
                    ElementFactory<Member> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "anon1dd3type");
                    Factory = elementFactory;
                    type = elementFactory.getType();
                }

                public static final class Enum extends StringEnumAbstractBase {
                    static final int INT_LOCAL = 1;
                    private static final long serialVersionUID = 1;
                    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("##local", 1)});

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
