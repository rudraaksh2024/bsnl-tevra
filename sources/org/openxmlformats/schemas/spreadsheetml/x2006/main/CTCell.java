package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellType;

public interface CTCell extends XmlObject {
    public static final DocumentFactory<CTCell> Factory;
    public static final SchemaType type;

    CTExtensionList addNewExtLst();

    CTCellFormula addNewF();

    CTRst addNewIs();

    long getCm();

    CTExtensionList getExtLst();

    CTCellFormula getF();

    CTRst getIs();

    boolean getPh();

    String getR();

    long getS();

    STCellType.Enum getT();

    String getV();

    long getVm();

    boolean isSetCm();

    boolean isSetExtLst();

    boolean isSetF();

    boolean isSetIs();

    boolean isSetPh();

    boolean isSetR();

    boolean isSetS();

    boolean isSetT();

    boolean isSetV();

    boolean isSetVm();

    void setCm(long j);

    void setExtLst(CTExtensionList cTExtensionList);

    void setF(CTCellFormula cTCellFormula);

    void setIs(CTRst cTRst);

    void setPh(boolean z);

    void setR(String str);

    void setS(long j);

    void setT(STCellType.Enum enumR);

    void setV(String str);

    void setVm(long j);

    void unsetCm();

    void unsetExtLst();

    void unsetF();

    void unsetIs();

    void unsetPh();

    void unsetR();

    void unsetS();

    void unsetT();

    void unsetV();

    void unsetVm();

    XmlUnsignedInt xgetCm();

    XmlBoolean xgetPh();

    STCellRef xgetR();

    XmlUnsignedInt xgetS();

    STCellType xgetT();

    STXstring xgetV();

    XmlUnsignedInt xgetVm();

    void xsetCm(XmlUnsignedInt xmlUnsignedInt);

    void xsetPh(XmlBoolean xmlBoolean);

    void xsetR(STCellRef sTCellRef);

    void xsetS(XmlUnsignedInt xmlUnsignedInt);

    void xsetT(STCellType sTCellType);

    void xsetV(STXstring sTXstring);

    void xsetVm(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTCell> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcell842btype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
