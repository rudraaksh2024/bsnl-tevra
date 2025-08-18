package org.apache.xmlbeans.impl.xb.xsdschema;

import java.util.List;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface UnionDocument extends XmlObject {
    public static final DocumentFactory<UnionDocument> Factory;
    public static final SchemaType type;

    Union addNewUnion();

    Union getUnion();

    void setUnion(Union union);

    static {
        DocumentFactory<UnionDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "union5866doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface Union extends Annotated {
        public static final ElementFactory<Union> Factory;
        public static final SchemaType type;

        LocalSimpleType addNewSimpleType();

        List getMemberTypes();

        LocalSimpleType getSimpleTypeArray(int i);

        LocalSimpleType[] getSimpleTypeArray();

        List<LocalSimpleType> getSimpleTypeList();

        LocalSimpleType insertNewSimpleType(int i);

        boolean isSetMemberTypes();

        void removeSimpleType(int i);

        void setMemberTypes(List list);

        void setSimpleTypeArray(int i, LocalSimpleType localSimpleType);

        void setSimpleTypeArray(LocalSimpleType[] localSimpleTypeArr);

        int sizeOfSimpleTypeArray();

        void unsetMemberTypes();

        MemberTypes xgetMemberTypes();

        void xsetMemberTypes(MemberTypes memberTypes);

        static {
            ElementFactory<Union> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "union498belemtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }

        public interface MemberTypes extends XmlAnySimpleType {
            public static final ElementFactory<MemberTypes> Factory;
            public static final SchemaType type;

            List getListValue();

            void setListValue(List<?> list);

            List xgetListValue();

            static {
                ElementFactory<MemberTypes> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "membertypes2404attrtype");
                Factory = elementFactory;
                type = elementFactory.getType();
            }
        }
    }
}
