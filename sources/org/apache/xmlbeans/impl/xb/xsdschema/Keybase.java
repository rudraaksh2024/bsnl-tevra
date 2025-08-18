package org.apache.xmlbeans.impl.xb.xsdschema;

import java.util.List;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SelectorDocument;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface Keybase extends Annotated {
    public static final DocumentFactory<Keybase> Factory;
    public static final SchemaType type;

    FieldDocument.Field addNewField();

    SelectorDocument.Selector addNewSelector();

    FieldDocument.Field getFieldArray(int i);

    FieldDocument.Field[] getFieldArray();

    List<FieldDocument.Field> getFieldList();

    String getName();

    SelectorDocument.Selector getSelector();

    FieldDocument.Field insertNewField(int i);

    void removeField(int i);

    void setFieldArray(int i, FieldDocument.Field field);

    void setFieldArray(FieldDocument.Field[] fieldArr);

    void setName(String str);

    void setSelector(SelectorDocument.Selector selector);

    int sizeOfFieldArray();

    XmlNCName xgetName();

    void xsetName(XmlNCName xmlNCName);

    static {
        DocumentFactory<Keybase> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "keybase3955type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
