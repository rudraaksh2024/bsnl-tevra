package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlNMTOKEN;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface Wildcard extends Annotated {
    public static final DocumentFactory<Wildcard> Factory;
    public static final SchemaType type;

    Object getNamespace();

    ProcessContents.Enum getProcessContents();

    boolean isSetNamespace();

    boolean isSetProcessContents();

    void setNamespace(Object obj);

    void setProcessContents(ProcessContents.Enum enumR);

    void unsetNamespace();

    void unsetProcessContents();

    NamespaceList xgetNamespace();

    ProcessContents xgetProcessContents();

    void xsetNamespace(NamespaceList namespaceList);

    void xsetProcessContents(ProcessContents processContents);

    static {
        DocumentFactory<Wildcard> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "wildcarde0b9type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface ProcessContents extends XmlNMTOKEN {
        public static final ElementFactory<ProcessContents> Factory;
        public static final int INT_LAX = 2;
        public static final int INT_SKIP = 1;
        public static final int INT_STRICT = 3;
        public static final Enum LAX = Enum.forString("lax");
        public static final Enum SKIP = Enum.forString("skip");
        public static final Enum STRICT = Enum.forString("strict");
        public static final SchemaType type;

        StringEnumAbstractBase getEnumValue();

        void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

        static {
            ElementFactory<ProcessContents> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "processcontents864aattrtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }

        public static final class Enum extends StringEnumAbstractBase {
            static final int INT_LAX = 2;
            static final int INT_SKIP = 1;
            static final int INT_STRICT = 3;
            private static final long serialVersionUID = 1;
            public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("skip", 1), new Enum("lax", 2), new Enum("strict", 3)});

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
