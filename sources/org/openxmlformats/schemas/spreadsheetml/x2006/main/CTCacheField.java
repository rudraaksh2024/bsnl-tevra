package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;

public interface CTCacheField extends XmlObject {
    public static final DocumentFactory<CTCacheField> Factory;
    public static final SchemaType type;

    CTExtensionList addNewExtLst();

    CTFieldGroup addNewFieldGroup();

    CTX addNewMpMap();

    CTSharedItems addNewSharedItems();

    String getCaption();

    boolean getDatabaseField();

    CTExtensionList getExtLst();

    CTFieldGroup getFieldGroup();

    String getFormula();

    int getHierarchy();

    long getLevel();

    long getMappingCount();

    boolean getMemberPropertyField();

    CTX getMpMapArray(int i);

    CTX[] getMpMapArray();

    List<CTX> getMpMapList();

    String getName();

    long getNumFmtId();

    String getPropertyName();

    boolean getServerField();

    CTSharedItems getSharedItems();

    int getSqlType();

    boolean getUniqueList();

    CTX insertNewMpMap(int i);

    boolean isSetCaption();

    boolean isSetDatabaseField();

    boolean isSetExtLst();

    boolean isSetFieldGroup();

    boolean isSetFormula();

    boolean isSetHierarchy();

    boolean isSetLevel();

    boolean isSetMappingCount();

    boolean isSetMemberPropertyField();

    boolean isSetNumFmtId();

    boolean isSetPropertyName();

    boolean isSetServerField();

    boolean isSetSharedItems();

    boolean isSetSqlType();

    boolean isSetUniqueList();

    void removeMpMap(int i);

    void setCaption(String str);

    void setDatabaseField(boolean z);

    void setExtLst(CTExtensionList cTExtensionList);

    void setFieldGroup(CTFieldGroup cTFieldGroup);

    void setFormula(String str);

    void setHierarchy(int i);

    void setLevel(long j);

    void setMappingCount(long j);

    void setMemberPropertyField(boolean z);

    void setMpMapArray(int i, CTX ctx);

    void setMpMapArray(CTX[] ctxArr);

    void setName(String str);

    void setNumFmtId(long j);

    void setPropertyName(String str);

    void setServerField(boolean z);

    void setSharedItems(CTSharedItems cTSharedItems);

    void setSqlType(int i);

    void setUniqueList(boolean z);

    int sizeOfMpMapArray();

    void unsetCaption();

    void unsetDatabaseField();

    void unsetExtLst();

    void unsetFieldGroup();

    void unsetFormula();

    void unsetHierarchy();

    void unsetLevel();

    void unsetMappingCount();

    void unsetMemberPropertyField();

    void unsetNumFmtId();

    void unsetPropertyName();

    void unsetServerField();

    void unsetSharedItems();

    void unsetSqlType();

    void unsetUniqueList();

    STXstring xgetCaption();

    XmlBoolean xgetDatabaseField();

    STXstring xgetFormula();

    XmlInt xgetHierarchy();

    XmlUnsignedInt xgetLevel();

    XmlUnsignedInt xgetMappingCount();

    XmlBoolean xgetMemberPropertyField();

    STXstring xgetName();

    STNumFmtId xgetNumFmtId();

    STXstring xgetPropertyName();

    XmlBoolean xgetServerField();

    XmlInt xgetSqlType();

    XmlBoolean xgetUniqueList();

    void xsetCaption(STXstring sTXstring);

    void xsetDatabaseField(XmlBoolean xmlBoolean);

    void xsetFormula(STXstring sTXstring);

    void xsetHierarchy(XmlInt xmlInt);

    void xsetLevel(XmlUnsignedInt xmlUnsignedInt);

    void xsetMappingCount(XmlUnsignedInt xmlUnsignedInt);

    void xsetMemberPropertyField(XmlBoolean xmlBoolean);

    void xsetName(STXstring sTXstring);

    void xsetNumFmtId(STNumFmtId sTNumFmtId);

    void xsetPropertyName(STXstring sTXstring);

    void xsetServerField(XmlBoolean xmlBoolean);

    void xsetSqlType(XmlInt xmlInt);

    void xsetUniqueList(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTCacheField> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcachefieldae21type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
