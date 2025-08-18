package org.etsi.uri.x01903.v13;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface ClaimedRolesListType extends XmlObject {
    public static final DocumentFactory<ClaimedRolesListType> Factory;
    public static final SchemaType type;

    AnyType addNewClaimedRole();

    AnyType getClaimedRoleArray(int i);

    AnyType[] getClaimedRoleArray();

    List<AnyType> getClaimedRoleList();

    AnyType insertNewClaimedRole(int i);

    void removeClaimedRole(int i);

    void setClaimedRoleArray(int i, AnyType anyType);

    void setClaimedRoleArray(AnyType[] anyTypeArr);

    int sizeOfClaimedRoleArray();

    static {
        DocumentFactory<ClaimedRolesListType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "claimedroleslisttypef16etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
