package com.microsoft.schemas.office.visio.x2012.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.XmlUnsignedShort;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface MasterType extends XmlObject {
    public static final DocumentFactory<MasterType> Factory;
    public static final SchemaType type;

    IconType addNewIcon();

    PageSheetType addNewPageSheet();

    RelType addNewRel();

    int getAlignName();

    String getBaseID();

    boolean getHidden();

    long getID();

    IconType getIcon();

    int getIconSize();

    boolean getIconUpdate();

    boolean getIsCustomName();

    boolean getIsCustomNameU();

    int getMasterType();

    boolean getMatchByName();

    String getName();

    String getNameU();

    PageSheetType getPageSheet();

    int getPatternFlags();

    String getPrompt();

    RelType getRel();

    String getUniqueID();

    boolean isSetAlignName();

    boolean isSetBaseID();

    boolean isSetHidden();

    boolean isSetIcon();

    boolean isSetIconSize();

    boolean isSetIconUpdate();

    boolean isSetIsCustomName();

    boolean isSetIsCustomNameU();

    boolean isSetMasterType();

    boolean isSetMatchByName();

    boolean isSetName();

    boolean isSetNameU();

    boolean isSetPageSheet();

    boolean isSetPatternFlags();

    boolean isSetPrompt();

    boolean isSetUniqueID();

    void setAlignName(int i);

    void setBaseID(String str);

    void setHidden(boolean z);

    void setID(long j);

    void setIcon(IconType iconType);

    void setIconSize(int i);

    void setIconUpdate(boolean z);

    void setIsCustomName(boolean z);

    void setIsCustomNameU(boolean z);

    void setMasterType(int i);

    void setMatchByName(boolean z);

    void setName(String str);

    void setNameU(String str);

    void setPageSheet(PageSheetType pageSheetType);

    void setPatternFlags(int i);

    void setPrompt(String str);

    void setRel(RelType relType);

    void setUniqueID(String str);

    void unsetAlignName();

    void unsetBaseID();

    void unsetHidden();

    void unsetIcon();

    void unsetIconSize();

    void unsetIconUpdate();

    void unsetIsCustomName();

    void unsetIsCustomNameU();

    void unsetMasterType();

    void unsetMatchByName();

    void unsetName();

    void unsetNameU();

    void unsetPageSheet();

    void unsetPatternFlags();

    void unsetPrompt();

    void unsetUniqueID();

    XmlUnsignedShort xgetAlignName();

    XmlString xgetBaseID();

    XmlBoolean xgetHidden();

    XmlUnsignedInt xgetID();

    XmlUnsignedShort xgetIconSize();

    XmlBoolean xgetIconUpdate();

    XmlBoolean xgetIsCustomName();

    XmlBoolean xgetIsCustomNameU();

    XmlUnsignedShort xgetMasterType();

    XmlBoolean xgetMatchByName();

    XmlString xgetName();

    XmlString xgetNameU();

    XmlUnsignedShort xgetPatternFlags();

    XmlString xgetPrompt();

    XmlString xgetUniqueID();

    void xsetAlignName(XmlUnsignedShort xmlUnsignedShort);

    void xsetBaseID(XmlString xmlString);

    void xsetHidden(XmlBoolean xmlBoolean);

    void xsetID(XmlUnsignedInt xmlUnsignedInt);

    void xsetIconSize(XmlUnsignedShort xmlUnsignedShort);

    void xsetIconUpdate(XmlBoolean xmlBoolean);

    void xsetIsCustomName(XmlBoolean xmlBoolean);

    void xsetIsCustomNameU(XmlBoolean xmlBoolean);

    void xsetMasterType(XmlUnsignedShort xmlUnsignedShort);

    void xsetMatchByName(XmlBoolean xmlBoolean);

    void xsetName(XmlString xmlString);

    void xsetNameU(XmlString xmlString);

    void xsetPatternFlags(XmlUnsignedShort xmlUnsignedShort);

    void xsetPrompt(XmlString xmlString);

    void xsetUniqueID(XmlString xmlString);

    static {
        DocumentFactory<MasterType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "mastertype2d97type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
