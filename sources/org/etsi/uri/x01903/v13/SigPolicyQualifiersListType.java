package org.etsi.uri.x01903.v13;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface SigPolicyQualifiersListType extends XmlObject {
    public static final DocumentFactory<SigPolicyQualifiersListType> Factory;
    public static final SchemaType type;

    AnyType addNewSigPolicyQualifier();

    AnyType getSigPolicyQualifierArray(int i);

    AnyType[] getSigPolicyQualifierArray();

    List<AnyType> getSigPolicyQualifierList();

    AnyType insertNewSigPolicyQualifier(int i);

    void removeSigPolicyQualifier(int i);

    void setSigPolicyQualifierArray(int i, AnyType anyType);

    void setSigPolicyQualifierArray(AnyType[] anyTypeArr);

    int sizeOfSigPolicyQualifierArray();

    static {
        DocumentFactory<SigPolicyQualifiersListType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "sigpolicyqualifierslisttype3266type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
