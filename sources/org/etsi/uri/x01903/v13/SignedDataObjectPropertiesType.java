package org.etsi.uri.x01903.v13;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface SignedDataObjectPropertiesType extends XmlObject {
    public static final DocumentFactory<SignedDataObjectPropertiesType> Factory;
    public static final SchemaType type;

    XAdESTimeStampType addNewAllDataObjectsTimeStamp();

    CommitmentTypeIndicationType addNewCommitmentTypeIndication();

    DataObjectFormatType addNewDataObjectFormat();

    XAdESTimeStampType addNewIndividualDataObjectsTimeStamp();

    XAdESTimeStampType getAllDataObjectsTimeStampArray(int i);

    XAdESTimeStampType[] getAllDataObjectsTimeStampArray();

    List<XAdESTimeStampType> getAllDataObjectsTimeStampList();

    CommitmentTypeIndicationType getCommitmentTypeIndicationArray(int i);

    CommitmentTypeIndicationType[] getCommitmentTypeIndicationArray();

    List<CommitmentTypeIndicationType> getCommitmentTypeIndicationList();

    DataObjectFormatType getDataObjectFormatArray(int i);

    DataObjectFormatType[] getDataObjectFormatArray();

    List<DataObjectFormatType> getDataObjectFormatList();

    String getId();

    XAdESTimeStampType getIndividualDataObjectsTimeStampArray(int i);

    XAdESTimeStampType[] getIndividualDataObjectsTimeStampArray();

    List<XAdESTimeStampType> getIndividualDataObjectsTimeStampList();

    XAdESTimeStampType insertNewAllDataObjectsTimeStamp(int i);

    CommitmentTypeIndicationType insertNewCommitmentTypeIndication(int i);

    DataObjectFormatType insertNewDataObjectFormat(int i);

    XAdESTimeStampType insertNewIndividualDataObjectsTimeStamp(int i);

    boolean isSetId();

    void removeAllDataObjectsTimeStamp(int i);

    void removeCommitmentTypeIndication(int i);

    void removeDataObjectFormat(int i);

    void removeIndividualDataObjectsTimeStamp(int i);

    void setAllDataObjectsTimeStampArray(int i, XAdESTimeStampType xAdESTimeStampType);

    void setAllDataObjectsTimeStampArray(XAdESTimeStampType[] xAdESTimeStampTypeArr);

    void setCommitmentTypeIndicationArray(int i, CommitmentTypeIndicationType commitmentTypeIndicationType);

    void setCommitmentTypeIndicationArray(CommitmentTypeIndicationType[] commitmentTypeIndicationTypeArr);

    void setDataObjectFormatArray(int i, DataObjectFormatType dataObjectFormatType);

    void setDataObjectFormatArray(DataObjectFormatType[] dataObjectFormatTypeArr);

    void setId(String str);

    void setIndividualDataObjectsTimeStampArray(int i, XAdESTimeStampType xAdESTimeStampType);

    void setIndividualDataObjectsTimeStampArray(XAdESTimeStampType[] xAdESTimeStampTypeArr);

    int sizeOfAllDataObjectsTimeStampArray();

    int sizeOfCommitmentTypeIndicationArray();

    int sizeOfDataObjectFormatArray();

    int sizeOfIndividualDataObjectsTimeStampArray();

    void unsetId();

    XmlID xgetId();

    void xsetId(XmlID xmlID);

    static {
        DocumentFactory<SignedDataObjectPropertiesType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "signeddataobjectpropertiestype19a6type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
