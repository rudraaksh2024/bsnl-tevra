package org.apache.xmlbeans.impl.xb.xsdschema;

import java.math.BigInteger;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.schema.AbstractDocumentFactory;
import org.apache.xmlbeans.impl.xb.xsdschema.AnyDocument;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface Group extends Annotated {
    public static final AbstractDocumentFactory<Group> Factory;
    public static final SchemaType type;

    All addNewAll();

    AnyDocument.Any addNewAny();

    ExplicitGroup addNewChoice();

    LocalElement addNewElement();

    GroupRef addNewGroup();

    ExplicitGroup addNewSequence();

    All getAllArray(int i);

    All[] getAllArray();

    List<All> getAllList();

    AnyDocument.Any getAnyArray(int i);

    AnyDocument.Any[] getAnyArray();

    List<AnyDocument.Any> getAnyList();

    ExplicitGroup getChoiceArray(int i);

    ExplicitGroup[] getChoiceArray();

    List<ExplicitGroup> getChoiceList();

    LocalElement getElementArray(int i);

    LocalElement[] getElementArray();

    List<LocalElement> getElementList();

    GroupRef getGroupArray(int i);

    GroupRef[] getGroupArray();

    List<GroupRef> getGroupList();

    Object getMaxOccurs();

    BigInteger getMinOccurs();

    String getName();

    QName getRef();

    ExplicitGroup getSequenceArray(int i);

    ExplicitGroup[] getSequenceArray();

    List<ExplicitGroup> getSequenceList();

    All insertNewAll(int i);

    AnyDocument.Any insertNewAny(int i);

    ExplicitGroup insertNewChoice(int i);

    LocalElement insertNewElement(int i);

    GroupRef insertNewGroup(int i);

    ExplicitGroup insertNewSequence(int i);

    boolean isSetMaxOccurs();

    boolean isSetMinOccurs();

    boolean isSetName();

    boolean isSetRef();

    void removeAll(int i);

    void removeAny(int i);

    void removeChoice(int i);

    void removeElement(int i);

    void removeGroup(int i);

    void removeSequence(int i);

    void setAllArray(int i, All all);

    void setAllArray(All[] allArr);

    void setAnyArray(int i, AnyDocument.Any any);

    void setAnyArray(AnyDocument.Any[] anyArr);

    void setChoiceArray(int i, ExplicitGroup explicitGroup);

    void setChoiceArray(ExplicitGroup[] explicitGroupArr);

    void setElementArray(int i, LocalElement localElement);

    void setElementArray(LocalElement[] localElementArr);

    void setGroupArray(int i, GroupRef groupRef);

    void setGroupArray(GroupRef[] groupRefArr);

    void setMaxOccurs(Object obj);

    void setMinOccurs(BigInteger bigInteger);

    void setName(String str);

    void setRef(QName qName);

    void setSequenceArray(int i, ExplicitGroup explicitGroup);

    void setSequenceArray(ExplicitGroup[] explicitGroupArr);

    int sizeOfAllArray();

    int sizeOfAnyArray();

    int sizeOfChoiceArray();

    int sizeOfElementArray();

    int sizeOfGroupArray();

    int sizeOfSequenceArray();

    void unsetMaxOccurs();

    void unsetMinOccurs();

    void unsetName();

    void unsetRef();

    AllNNI xgetMaxOccurs();

    XmlNonNegativeInteger xgetMinOccurs();

    XmlNCName xgetName();

    XmlQName xgetRef();

    void xsetMaxOccurs(AllNNI allNNI);

    void xsetMinOccurs(XmlNonNegativeInteger xmlNonNegativeInteger);

    void xsetName(XmlNCName xmlNCName);

    void xsetRef(XmlQName xmlQName);

    static {
        AbstractDocumentFactory<Group> abstractDocumentFactory = new AbstractDocumentFactory<>(TypeSystemHolder.typeSystem, "group7ca6type");
        Factory = abstractDocumentFactory;
        type = abstractDocumentFactory.getType();
    }
}
