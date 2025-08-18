package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTShape3D extends XmlObject {
    public static final DocumentFactory<CTShape3D> Factory;
    public static final SchemaType type;

    CTBevel addNewBevelB();

    CTBevel addNewBevelT();

    CTColor addNewContourClr();

    CTOfficeArtExtensionList addNewExtLst();

    CTColor addNewExtrusionClr();

    CTBevel getBevelB();

    CTBevel getBevelT();

    CTColor getContourClr();

    long getContourW();

    CTOfficeArtExtensionList getExtLst();

    CTColor getExtrusionClr();

    long getExtrusionH();

    STPresetMaterialType$Enum getPrstMaterial();

    Object getZ();

    boolean isSetBevelB();

    boolean isSetBevelT();

    boolean isSetContourClr();

    boolean isSetContourW();

    boolean isSetExtLst();

    boolean isSetExtrusionClr();

    boolean isSetExtrusionH();

    boolean isSetPrstMaterial();

    boolean isSetZ();

    void setBevelB(CTBevel cTBevel);

    void setBevelT(CTBevel cTBevel);

    void setContourClr(CTColor cTColor);

    void setContourW(long j);

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setExtrusionClr(CTColor cTColor);

    void setExtrusionH(long j);

    void setPrstMaterial(STPresetMaterialType$Enum sTPresetMaterialType$Enum);

    void setZ(Object obj);

    void unsetBevelB();

    void unsetBevelT();

    void unsetContourClr();

    void unsetContourW();

    void unsetExtLst();

    void unsetExtrusionClr();

    void unsetExtrusionH();

    void unsetPrstMaterial();

    void unsetZ();

    STPositiveCoordinate xgetContourW();

    STPositiveCoordinate xgetExtrusionH();

    STPresetMaterialType xgetPrstMaterial();

    STCoordinate xgetZ();

    void xsetContourW(STPositiveCoordinate sTPositiveCoordinate);

    void xsetExtrusionH(STPositiveCoordinate sTPositiveCoordinate);

    void xsetPrstMaterial(STPresetMaterialType sTPresetMaterialType);

    void xsetZ(STCoordinate sTCoordinate);

    static {
        DocumentFactory<CTShape3D> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctshape3d6783type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
