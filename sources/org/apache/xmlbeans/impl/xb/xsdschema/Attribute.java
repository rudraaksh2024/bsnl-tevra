package org.apache.xmlbeans.impl.xb.xsdschema;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.XmlNMTOKEN;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.impl.xb.xsdschema.FormChoice;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface Attribute extends Annotated {
    public static final DocumentFactory<Attribute> Factory;
    public static final SchemaType type;

    LocalSimpleType addNewSimpleType();

    String getDefault();

    String getFixed();

    FormChoice.Enum getForm();

    String getName();

    QName getRef();

    LocalSimpleType getSimpleType();

    QName getType();

    Use.Enum getUse();

    boolean isSetDefault();

    boolean isSetFixed();

    boolean isSetForm();

    boolean isSetName();

    boolean isSetRef();

    boolean isSetSimpleType();

    boolean isSetType();

    boolean isSetUse();

    void setDefault(String str);

    void setFixed(String str);

    void setForm(FormChoice.Enum enumR);

    void setName(String str);

    void setRef(QName qName);

    void setSimpleType(LocalSimpleType localSimpleType);

    void setType(QName qName);

    void setUse(Use.Enum enumR);

    void unsetDefault();

    void unsetFixed();

    void unsetForm();

    void unsetName();

    void unsetRef();

    void unsetSimpleType();

    void unsetType();

    void unsetUse();

    XmlString xgetDefault();

    XmlString xgetFixed();

    FormChoice xgetForm();

    XmlNCName xgetName();

    XmlQName xgetRef();

    XmlQName xgetType();

    Use xgetUse();

    void xsetDefault(XmlString xmlString);

    void xsetFixed(XmlString xmlString);

    void xsetForm(FormChoice formChoice);

    void xsetName(XmlNCName xmlNCName);

    void xsetRef(XmlQName xmlQName);

    void xsetType(XmlQName xmlQName);

    void xsetUse(Use use);

    static {
        DocumentFactory<Attribute> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "attribute83a9type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface Use extends XmlNMTOKEN {
        public static final ElementFactory<Use> Factory;
        public static final int INT_OPTIONAL = 2;
        public static final int INT_PROHIBITED = 1;
        public static final int INT_REQUIRED = 3;
        public static final Enum OPTIONAL = Enum.forString("optional");
        public static final Enum PROHIBITED = Enum.forString("prohibited");
        public static final Enum REQUIRED = Enum.forString("required");
        public static final SchemaType type;

        StringEnumAbstractBase getEnumValue();

        void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

        static {
            ElementFactory<Use> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "usea41aattrtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }

        public static final class Enum extends StringEnumAbstractBase {
            static final int INT_OPTIONAL = 2;
            static final int INT_PROHIBITED = 1;
            static final int INT_REQUIRED = 3;
            private static final long serialVersionUID = 1;
            public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("prohibited", 1), new Enum("optional", 2), new Enum("required", 3)});

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
