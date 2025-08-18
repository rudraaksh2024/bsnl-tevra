package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STItemType;

public interface CTItem extends XmlObject {
    public static final DocumentFactory<CTItem> Factory;
    public static final SchemaType type;

    boolean getC();

    boolean getD();

    boolean getE();

    boolean getF();

    boolean getH();

    boolean getM();

    String getN();

    boolean getS();

    boolean getSd();

    STItemType.Enum getT();

    long getX();

    boolean isSetC();

    boolean isSetD();

    boolean isSetE();

    boolean isSetF();

    boolean isSetH();

    boolean isSetM();

    boolean isSetN();

    boolean isSetS();

    boolean isSetSd();

    boolean isSetT();

    boolean isSetX();

    void setC(boolean z);

    void setD(boolean z);

    void setE(boolean z);

    void setF(boolean z);

    void setH(boolean z);

    void setM(boolean z);

    void setN(String str);

    void setS(boolean z);

    void setSd(boolean z);

    void setT(STItemType.Enum enumR);

    void setX(long j);

    void unsetC();

    void unsetD();

    void unsetE();

    void unsetF();

    void unsetH();

    void unsetM();

    void unsetN();

    void unsetS();

    void unsetSd();

    void unsetT();

    void unsetX();

    XmlBoolean xgetC();

    XmlBoolean xgetD();

    XmlBoolean xgetE();

    XmlBoolean xgetF();

    XmlBoolean xgetH();

    XmlBoolean xgetM();

    STXstring xgetN();

    XmlBoolean xgetS();

    XmlBoolean xgetSd();

    STItemType xgetT();

    XmlUnsignedInt xgetX();

    void xsetC(XmlBoolean xmlBoolean);

    void xsetD(XmlBoolean xmlBoolean);

    void xsetE(XmlBoolean xmlBoolean);

    void xsetF(XmlBoolean xmlBoolean);

    void xsetH(XmlBoolean xmlBoolean);

    void xsetM(XmlBoolean xmlBoolean);

    void xsetN(STXstring sTXstring);

    void xsetS(XmlBoolean xmlBoolean);

    void xsetSd(XmlBoolean xmlBoolean);

    void xsetT(STItemType sTItemType);

    void xsetX(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTItem> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctitemc69ctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
