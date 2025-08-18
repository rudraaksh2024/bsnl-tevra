package org.apache.xmlbeans.impl.xb.xsdschema;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.schema.AbstractDocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface AttributeGroup extends Annotated {
    public static final AbstractDocumentFactory<AttributeGroup> Factory;
    public static final SchemaType type;

    Wildcard addNewAnyAttribute();

    Attribute addNewAttribute();

    AttributeGroupRef addNewAttributeGroup();

    Wildcard getAnyAttribute();

    Attribute getAttributeArray(int i);

    Attribute[] getAttributeArray();

    AttributeGroupRef getAttributeGroupArray(int i);

    AttributeGroupRef[] getAttributeGroupArray();

    List<AttributeGroupRef> getAttributeGroupList();

    List<Attribute> getAttributeList();

    String getName();

    QName getRef();

    Attribute insertNewAttribute(int i);

    AttributeGroupRef insertNewAttributeGroup(int i);

    boolean isSetAnyAttribute();

    boolean isSetName();

    boolean isSetRef();

    void removeAttribute(int i);

    void removeAttributeGroup(int i);

    void setAnyAttribute(Wildcard wildcard);

    void setAttributeArray(int i, Attribute attribute);

    void setAttributeArray(Attribute[] attributeArr);

    void setAttributeGroupArray(int i, AttributeGroupRef attributeGroupRef);

    void setAttributeGroupArray(AttributeGroupRef[] attributeGroupRefArr);

    void setName(String str);

    void setRef(QName qName);

    int sizeOfAttributeArray();

    int sizeOfAttributeGroupArray();

    void unsetAnyAttribute();

    void unsetName();

    void unsetRef();

    XmlNCName xgetName();

    XmlQName xgetRef();

    void xsetName(XmlNCName xmlNCName);

    void xsetRef(XmlQName xmlQName);

    static {
        AbstractDocumentFactory<AttributeGroup> abstractDocumentFactory = new AbstractDocumentFactory<>(TypeSystemHolder.typeSystem, "attributegroupe530type");
        Factory = abstractDocumentFactory;
        type = abstractDocumentFactory.getType();
    }
}
