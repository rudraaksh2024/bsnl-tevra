package org.etsi.uri.x01903.v13;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface OCSPValuesType extends XmlObject {
    public static final DocumentFactory<OCSPValuesType> Factory;
    public static final SchemaType type;

    EncapsulatedPKIDataType addNewEncapsulatedOCSPValue();

    EncapsulatedPKIDataType getEncapsulatedOCSPValueArray(int i);

    EncapsulatedPKIDataType[] getEncapsulatedOCSPValueArray();

    List<EncapsulatedPKIDataType> getEncapsulatedOCSPValueList();

    EncapsulatedPKIDataType insertNewEncapsulatedOCSPValue(int i);

    void removeEncapsulatedOCSPValue(int i);

    void setEncapsulatedOCSPValueArray(int i, EncapsulatedPKIDataType encapsulatedPKIDataType);

    void setEncapsulatedOCSPValueArray(EncapsulatedPKIDataType[] encapsulatedPKIDataTypeArr);

    int sizeOfEncapsulatedOCSPValueArray();

    static {
        DocumentFactory<OCSPValuesType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ocspvaluestypeb421type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
