package com.microsoft.schemas.office.visio.x2012.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface DocumentSettingsType extends XmlObject {
    public static final DocumentFactory<DocumentSettingsType> Factory;
    public static final SchemaType type;

    AttachedToolbarsType addNewAttachedToolbars();

    CustomMenusFileType addNewCustomMenusFile();

    CustomToolbarsFileType addNewCustomToolbarsFile();

    DynamicGridEnabledType addNewDynamicGridEnabled();

    GlueSettingsType addNewGlueSettings();

    ProtectBkgndsType addNewProtectBkgnds();

    ProtectMastersType addNewProtectMasters();

    ProtectShapesType addNewProtectShapes();

    ProtectStylesType addNewProtectStyles();

    SnapAnglesType addNewSnapAngles();

    SnapExtensionsType addNewSnapExtensions();

    SnapSettingsType addNewSnapSettings();

    AttachedToolbarsType getAttachedToolbars();

    CustomMenusFileType getCustomMenusFile();

    CustomToolbarsFileType getCustomToolbarsFile();

    long getDefaultFillStyle();

    long getDefaultGuideStyle();

    long getDefaultLineStyle();

    long getDefaultTextStyle();

    DynamicGridEnabledType getDynamicGridEnabled();

    GlueSettingsType getGlueSettings();

    ProtectBkgndsType getProtectBkgnds();

    ProtectMastersType getProtectMasters();

    ProtectShapesType getProtectShapes();

    ProtectStylesType getProtectStyles();

    SnapAnglesType getSnapAngles();

    SnapExtensionsType getSnapExtensions();

    SnapSettingsType getSnapSettings();

    long getTopPage();

    boolean isSetAttachedToolbars();

    boolean isSetCustomMenusFile();

    boolean isSetCustomToolbarsFile();

    boolean isSetDefaultFillStyle();

    boolean isSetDefaultGuideStyle();

    boolean isSetDefaultLineStyle();

    boolean isSetDefaultTextStyle();

    boolean isSetDynamicGridEnabled();

    boolean isSetGlueSettings();

    boolean isSetProtectBkgnds();

    boolean isSetProtectMasters();

    boolean isSetProtectShapes();

    boolean isSetProtectStyles();

    boolean isSetSnapAngles();

    boolean isSetSnapExtensions();

    boolean isSetSnapSettings();

    boolean isSetTopPage();

    void setAttachedToolbars(AttachedToolbarsType attachedToolbarsType);

    void setCustomMenusFile(CustomMenusFileType customMenusFileType);

    void setCustomToolbarsFile(CustomToolbarsFileType customToolbarsFileType);

    void setDefaultFillStyle(long j);

    void setDefaultGuideStyle(long j);

    void setDefaultLineStyle(long j);

    void setDefaultTextStyle(long j);

    void setDynamicGridEnabled(DynamicGridEnabledType dynamicGridEnabledType);

    void setGlueSettings(GlueSettingsType glueSettingsType);

    void setProtectBkgnds(ProtectBkgndsType protectBkgndsType);

    void setProtectMasters(ProtectMastersType protectMastersType);

    void setProtectShapes(ProtectShapesType protectShapesType);

    void setProtectStyles(ProtectStylesType protectStylesType);

    void setSnapAngles(SnapAnglesType snapAnglesType);

    void setSnapExtensions(SnapExtensionsType snapExtensionsType);

    void setSnapSettings(SnapSettingsType snapSettingsType);

    void setTopPage(long j);

    void unsetAttachedToolbars();

    void unsetCustomMenusFile();

    void unsetCustomToolbarsFile();

    void unsetDefaultFillStyle();

    void unsetDefaultGuideStyle();

    void unsetDefaultLineStyle();

    void unsetDefaultTextStyle();

    void unsetDynamicGridEnabled();

    void unsetGlueSettings();

    void unsetProtectBkgnds();

    void unsetProtectMasters();

    void unsetProtectShapes();

    void unsetProtectStyles();

    void unsetSnapAngles();

    void unsetSnapExtensions();

    void unsetSnapSettings();

    void unsetTopPage();

    XmlUnsignedInt xgetDefaultFillStyle();

    XmlUnsignedInt xgetDefaultGuideStyle();

    XmlUnsignedInt xgetDefaultLineStyle();

    XmlUnsignedInt xgetDefaultTextStyle();

    XmlUnsignedInt xgetTopPage();

    void xsetDefaultFillStyle(XmlUnsignedInt xmlUnsignedInt);

    void xsetDefaultGuideStyle(XmlUnsignedInt xmlUnsignedInt);

    void xsetDefaultLineStyle(XmlUnsignedInt xmlUnsignedInt);

    void xsetDefaultTextStyle(XmlUnsignedInt xmlUnsignedInt);

    void xsetTopPage(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<DocumentSettingsType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "documentsettingstype945btype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
