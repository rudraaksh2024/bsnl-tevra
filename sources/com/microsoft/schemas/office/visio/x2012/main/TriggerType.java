package com.microsoft.schemas.office.visio.x2012.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface TriggerType extends XmlObject {
    public static final DocumentFactory<TriggerType> Factory;
    public static final SchemaType type;

    RefByType addNewRefBy();

    String getN();

    RefByType getRefByArray(int i);

    RefByType[] getRefByArray();

    List<RefByType> getRefByList();

    RefByType insertNewRefBy(int i);

    void removeRefBy(int i);

    void setN(String str);

    void setRefByArray(int i, RefByType refByType);

    void setRefByArray(RefByType[] refByTypeArr);

    int sizeOfRefByArray();

    XmlString xgetN();

    void xsetN(XmlString xmlString);

    static {
        DocumentFactory<TriggerType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "triggertype2933type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
