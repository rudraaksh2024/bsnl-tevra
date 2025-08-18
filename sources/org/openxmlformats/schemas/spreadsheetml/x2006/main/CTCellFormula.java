package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellFormulaType;

public interface CTCellFormula extends STFormula {
    public static final DocumentFactory<CTCellFormula> Factory;
    public static final SchemaType type;

    boolean getAca();

    boolean getBx();

    boolean getCa();

    boolean getDel1();

    boolean getDel2();

    boolean getDt2D();

    boolean getDtr();

    String getR1();

    String getR2();

    String getRef();

    long getSi();

    STCellFormulaType.Enum getT();

    boolean isSetAca();

    boolean isSetBx();

    boolean isSetCa();

    boolean isSetDel1();

    boolean isSetDel2();

    boolean isSetDt2D();

    boolean isSetDtr();

    boolean isSetR1();

    boolean isSetR2();

    boolean isSetRef();

    boolean isSetSi();

    boolean isSetT();

    void setAca(boolean z);

    void setBx(boolean z);

    void setCa(boolean z);

    void setDel1(boolean z);

    void setDel2(boolean z);

    void setDt2D(boolean z);

    void setDtr(boolean z);

    void setR1(String str);

    void setR2(String str);

    void setRef(String str);

    void setSi(long j);

    void setT(STCellFormulaType.Enum enumR);

    void unsetAca();

    void unsetBx();

    void unsetCa();

    void unsetDel1();

    void unsetDel2();

    void unsetDt2D();

    void unsetDtr();

    void unsetR1();

    void unsetR2();

    void unsetRef();

    void unsetSi();

    void unsetT();

    XmlBoolean xgetAca();

    XmlBoolean xgetBx();

    XmlBoolean xgetCa();

    XmlBoolean xgetDel1();

    XmlBoolean xgetDel2();

    XmlBoolean xgetDt2D();

    XmlBoolean xgetDtr();

    STCellRef xgetR1();

    STCellRef xgetR2();

    STRef xgetRef();

    XmlUnsignedInt xgetSi();

    STCellFormulaType xgetT();

    void xsetAca(XmlBoolean xmlBoolean);

    void xsetBx(XmlBoolean xmlBoolean);

    void xsetCa(XmlBoolean xmlBoolean);

    void xsetDel1(XmlBoolean xmlBoolean);

    void xsetDel2(XmlBoolean xmlBoolean);

    void xsetDt2D(XmlBoolean xmlBoolean);

    void xsetDtr(XmlBoolean xmlBoolean);

    void xsetR1(STCellRef sTCellRef);

    void xsetR2(STCellRef sTCellRef);

    void xsetRef(STRef sTRef);

    void xsetSi(XmlUnsignedInt xmlUnsignedInt);

    void xsetT(STCellFormulaType sTCellFormulaType);

    static {
        DocumentFactory<CTCellFormula> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcellformula3583type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
