package org.etsi.uri.x01903.v13;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface OCSPRefsType extends XmlObject {
    public static final DocumentFactory<OCSPRefsType> Factory;
    public static final SchemaType type;

    OCSPRefType addNewOCSPRef();

    OCSPRefType getOCSPRefArray(int i);

    OCSPRefType[] getOCSPRefArray();

    List<OCSPRefType> getOCSPRefList();

    OCSPRefType insertNewOCSPRef(int i);

    void removeOCSPRef(int i);

    void setOCSPRefArray(int i, OCSPRefType oCSPRefType);

    void setOCSPRefArray(OCSPRefType[] oCSPRefTypeArr);

    int sizeOfOCSPRefArray();

    static {
        DocumentFactory<OCSPRefsType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ocsprefstypef13ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
