package com.microsoft.schemas.office.visio.x2012.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface ConnectsType extends XmlObject {
    public static final DocumentFactory<ConnectsType> Factory;
    public static final SchemaType type;

    ConnectType addNewConnect();

    ConnectType getConnectArray(int i);

    ConnectType[] getConnectArray();

    List<ConnectType> getConnectList();

    ConnectType insertNewConnect(int i);

    void removeConnect(int i);

    void setConnectArray(int i, ConnectType connectType);

    void setConnectArray(ConnectType[] connectTypeArr);

    int sizeOfConnectArray();

    static {
        DocumentFactory<ConnectsType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "connectstype8750type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
