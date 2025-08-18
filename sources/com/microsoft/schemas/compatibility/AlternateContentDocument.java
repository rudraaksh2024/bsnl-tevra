package com.microsoft.schemas.compatibility;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;

public interface AlternateContentDocument extends XmlObject {
    public static final DocumentFactory<AlternateContentDocument> Factory;
    public static final SchemaType type;

    AlternateContent addNewAlternateContent();

    AlternateContent getAlternateContent();

    void setAlternateContent(AlternateContent alternateContent);

    static {
        DocumentFactory<AlternateContentDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "alternatecontentdd64doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface AlternateContent extends XmlObject {
        public static final ElementFactory<AlternateContent> Factory;
        public static final SchemaType type;

        Choice addNewChoice();

        Fallback addNewFallback();

        Choice getChoiceArray(int i);

        Choice[] getChoiceArray();

        List<Choice> getChoiceList();

        Fallback getFallback();

        String getIgnorable();

        String getMustUnderstand();

        String getProcessContent();

        Choice insertNewChoice(int i);

        boolean isSetFallback();

        boolean isSetIgnorable();

        boolean isSetMustUnderstand();

        boolean isSetProcessContent();

        void removeChoice(int i);

        void setChoiceArray(int i, Choice choice);

        void setChoiceArray(Choice[] choiceArr);

        void setFallback(Fallback fallback);

        void setIgnorable(String str);

        void setMustUnderstand(String str);

        void setProcessContent(String str);

        int sizeOfChoiceArray();

        void unsetFallback();

        void unsetIgnorable();

        void unsetMustUnderstand();

        void unsetProcessContent();

        XmlString xgetIgnorable();

        XmlString xgetMustUnderstand();

        XmlString xgetProcessContent();

        void xsetIgnorable(XmlString xmlString);

        void xsetMustUnderstand(XmlString xmlString);

        void xsetProcessContent(XmlString xmlString);

        static {
            ElementFactory<AlternateContent> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "alternatecontenta8a9elemtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }

        public interface Choice extends XmlObject {
            public static final ElementFactory<Choice> Factory;
            public static final SchemaType type;

            String getIgnorable();

            String getMustUnderstand();

            String getProcessContent();

            String getRequires();

            boolean isSetIgnorable();

            boolean isSetMustUnderstand();

            boolean isSetProcessContent();

            void setIgnorable(String str);

            void setMustUnderstand(String str);

            void setProcessContent(String str);

            void setRequires(String str);

            void unsetIgnorable();

            void unsetMustUnderstand();

            void unsetProcessContent();

            XmlString xgetIgnorable();

            XmlString xgetMustUnderstand();

            XmlString xgetProcessContent();

            XmlString xgetRequires();

            void xsetIgnorable(XmlString xmlString);

            void xsetMustUnderstand(XmlString xmlString);

            void xsetProcessContent(XmlString xmlString);

            void xsetRequires(XmlString xmlString);

            static {
                ElementFactory<Choice> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "choice69c6elemtype");
                Factory = elementFactory;
                type = elementFactory.getType();
            }
        }

        public interface Fallback extends XmlObject {
            public static final ElementFactory<Fallback> Factory;
            public static final SchemaType type;

            String getIgnorable();

            String getMustUnderstand();

            String getProcessContent();

            boolean isSetIgnorable();

            boolean isSetMustUnderstand();

            boolean isSetProcessContent();

            void setIgnorable(String str);

            void setMustUnderstand(String str);

            void setProcessContent(String str);

            void unsetIgnorable();

            void unsetMustUnderstand();

            void unsetProcessContent();

            XmlString xgetIgnorable();

            XmlString xgetMustUnderstand();

            XmlString xgetProcessContent();

            void xsetIgnorable(XmlString xmlString);

            void xsetMustUnderstand(XmlString xmlString);

            void xsetProcessContent(XmlString xmlString);

            static {
                ElementFactory<Fallback> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "fallback4cc7elemtype");
                Factory = elementFactory;
                type = elementFactory.getType();
            }
        }
    }
}
