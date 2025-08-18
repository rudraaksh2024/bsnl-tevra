package org.apache.xmlbeans.impl.xb.xsdschema;

import java.util.List;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface DerivationSet extends XmlAnySimpleType {
    public static final SimpleTypeFactory<DerivationSet> Factory;
    public static final SchemaType type;

    Object getObjectValue();

    SchemaType instanceType();

    void setObjectValue(Object obj);

    static {
        SimpleTypeFactory<DerivationSet> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "derivationset037atype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public interface Member extends XmlToken {
        public static final Enum ALL = Enum.forString("#all");
        public static final ElementFactory<Member> Factory;
        public static final int INT_ALL = 1;
        public static final SchemaType type;

        StringEnumAbstractBase getEnumValue();

        void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

        static {
            ElementFactory<Member> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "anoned75type");
            Factory = elementFactory;
            type = elementFactory.getType();
        }

        public static final class Enum extends StringEnumAbstractBase {
            static final int INT_ALL = 1;
            private static final long serialVersionUID = 1;
            public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("#all", 1)});

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
            ElementFactory<Member2> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "anon9394type");
            Factory = elementFactory;
            type = elementFactory.getType();
        }
    }
}
