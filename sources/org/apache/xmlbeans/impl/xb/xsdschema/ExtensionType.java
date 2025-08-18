package org.apache.xmlbeans.impl.xb.xsdschema;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface ExtensionType extends Annotated {
    public static final DocumentFactory<ExtensionType> Factory;
    public static final SchemaType type;

    All addNewAll();

    Wildcard addNewAnyAttribute();

    Attribute addNewAttribute();

    AttributeGroupRef addNewAttributeGroup();

    ExplicitGroup addNewChoice();

    GroupRef addNewGroup();

    ExplicitGroup addNewSequence();

    All getAll();

    Wildcard getAnyAttribute();

    Attribute getAttributeArray(int i);

    Attribute[] getAttributeArray();

    AttributeGroupRef getAttributeGroupArray(int i);

    AttributeGroupRef[] getAttributeGroupArray();

    List<AttributeGroupRef> getAttributeGroupList();

    List<Attribute> getAttributeList();

    QName getBase();

    ExplicitGroup getChoice();

    GroupRef getGroup();

    ExplicitGroup getSequence();

    Attribute insertNewAttribute(int i);

    AttributeGroupRef insertNewAttributeGroup(int i);

    boolean isSetAll();

    boolean isSetAnyAttribute();

    boolean isSetChoice();

    boolean isSetGroup();

    boolean isSetSequence();

    void removeAttribute(int i);

    void removeAttributeGroup(int i);

    void setAll(All all);

    void setAnyAttribute(Wildcard wildcard);

    void setAttributeArray(int i, Attribute attribute);

    void setAttributeArray(Attribute[] attributeArr);

    void setAttributeGroupArray(int i, AttributeGroupRef attributeGroupRef);

    void setAttributeGroupArray(AttributeGroupRef[] attributeGroupRefArr);

    void setBase(QName qName);

    void setChoice(ExplicitGroup explicitGroup);

    void setGroup(GroupRef groupRef);

    void setSequence(ExplicitGroup explicitGroup);

    int sizeOfAttributeArray();

    int sizeOfAttributeGroupArray();

    void unsetAll();

    void unsetAnyAttribute();

    void unsetChoice();

    void unsetGroup();

    void unsetSequence();

    XmlQName xgetBase();

    void xsetBase(XmlQName xmlQName);

    static {
        DocumentFactory<ExtensionType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "extensiontypeed4ctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
