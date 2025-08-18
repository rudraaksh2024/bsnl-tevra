package org.etsi.uri.x01903.v13;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.AbstractDocumentFactory;
import org.w3.x2000.x09.xmldsig.CanonicalizationMethodType;

public interface GenericTimeStampType extends XmlObject {
    public static final AbstractDocumentFactory<GenericTimeStampType> Factory;
    public static final SchemaType type;

    CanonicalizationMethodType addNewCanonicalizationMethod();

    EncapsulatedPKIDataType addNewEncapsulatedTimeStamp();

    IncludeType addNewInclude();

    ReferenceInfoType addNewReferenceInfo();

    AnyType addNewXMLTimeStamp();

    CanonicalizationMethodType getCanonicalizationMethod();

    EncapsulatedPKIDataType getEncapsulatedTimeStampArray(int i);

    EncapsulatedPKIDataType[] getEncapsulatedTimeStampArray();

    List<EncapsulatedPKIDataType> getEncapsulatedTimeStampList();

    String getId();

    IncludeType getIncludeArray(int i);

    IncludeType[] getIncludeArray();

    List<IncludeType> getIncludeList();

    ReferenceInfoType getReferenceInfoArray(int i);

    ReferenceInfoType[] getReferenceInfoArray();

    List<ReferenceInfoType> getReferenceInfoList();

    AnyType getXMLTimeStampArray(int i);

    AnyType[] getXMLTimeStampArray();

    List<AnyType> getXMLTimeStampList();

    EncapsulatedPKIDataType insertNewEncapsulatedTimeStamp(int i);

    IncludeType insertNewInclude(int i);

    ReferenceInfoType insertNewReferenceInfo(int i);

    AnyType insertNewXMLTimeStamp(int i);

    boolean isSetCanonicalizationMethod();

    boolean isSetId();

    void removeEncapsulatedTimeStamp(int i);

    void removeInclude(int i);

    void removeReferenceInfo(int i);

    void removeXMLTimeStamp(int i);

    void setCanonicalizationMethod(CanonicalizationMethodType canonicalizationMethodType);

    void setEncapsulatedTimeStampArray(int i, EncapsulatedPKIDataType encapsulatedPKIDataType);

    void setEncapsulatedTimeStampArray(EncapsulatedPKIDataType[] encapsulatedPKIDataTypeArr);

    void setId(String str);

    void setIncludeArray(int i, IncludeType includeType);

    void setIncludeArray(IncludeType[] includeTypeArr);

    void setReferenceInfoArray(int i, ReferenceInfoType referenceInfoType);

    void setReferenceInfoArray(ReferenceInfoType[] referenceInfoTypeArr);

    void setXMLTimeStampArray(int i, AnyType anyType);

    void setXMLTimeStampArray(AnyType[] anyTypeArr);

    int sizeOfEncapsulatedTimeStampArray();

    int sizeOfIncludeArray();

    int sizeOfReferenceInfoArray();

    int sizeOfXMLTimeStampArray();

    void unsetCanonicalizationMethod();

    void unsetId();

    XmlID xgetId();

    void xsetId(XmlID xmlID);

    static {
        AbstractDocumentFactory<GenericTimeStampType> abstractDocumentFactory = new AbstractDocumentFactory<>(TypeSystemHolder.typeSystem, "generictimestamptypecdadtype");
        Factory = abstractDocumentFactory;
        type = abstractDocumentFactory.getType();
    }
}
