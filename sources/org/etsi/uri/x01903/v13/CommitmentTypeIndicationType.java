package org.etsi.uri.x01903.v13;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CommitmentTypeIndicationType extends XmlObject {
    public static final DocumentFactory<CommitmentTypeIndicationType> Factory;
    public static final SchemaType type;

    XmlObject addNewAllSignedDataObjects();

    ObjectIdentifierType addNewCommitmentTypeId();

    CommitmentTypeQualifiersListType addNewCommitmentTypeQualifiers();

    XmlAnyURI addNewObjectReference();

    void addObjectReference(String str);

    XmlObject getAllSignedDataObjects();

    ObjectIdentifierType getCommitmentTypeId();

    CommitmentTypeQualifiersListType getCommitmentTypeQualifiers();

    String getObjectReferenceArray(int i);

    String[] getObjectReferenceArray();

    List<String> getObjectReferenceList();

    XmlAnyURI insertNewObjectReference(int i);

    void insertObjectReference(int i, String str);

    boolean isSetAllSignedDataObjects();

    boolean isSetCommitmentTypeQualifiers();

    void removeObjectReference(int i);

    void setAllSignedDataObjects(XmlObject xmlObject);

    void setCommitmentTypeId(ObjectIdentifierType objectIdentifierType);

    void setCommitmentTypeQualifiers(CommitmentTypeQualifiersListType commitmentTypeQualifiersListType);

    void setObjectReferenceArray(int i, String str);

    void setObjectReferenceArray(String[] strArr);

    int sizeOfObjectReferenceArray();

    void unsetAllSignedDataObjects();

    void unsetCommitmentTypeQualifiers();

    XmlAnyURI xgetObjectReferenceArray(int i);

    XmlAnyURI[] xgetObjectReferenceArray();

    List<XmlAnyURI> xgetObjectReferenceList();

    void xsetObjectReferenceArray(int i, XmlAnyURI xmlAnyURI);

    void xsetObjectReferenceArray(XmlAnyURI[] xmlAnyURIArr);

    static {
        DocumentFactory<CommitmentTypeIndicationType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "commitmenttypeindicationtypef179type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
