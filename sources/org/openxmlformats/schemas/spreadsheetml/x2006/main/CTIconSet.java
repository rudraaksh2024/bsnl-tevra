package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STIconSetType;

public interface CTIconSet extends XmlObject {
    public static final DocumentFactory<CTIconSet> Factory;
    public static final SchemaType type;

    CTCfvo addNewCfvo();

    CTCfvo getCfvoArray(int i);

    CTCfvo[] getCfvoArray();

    List<CTCfvo> getCfvoList();

    STIconSetType.Enum getIconSet();

    boolean getPercent();

    boolean getReverse();

    boolean getShowValue();

    CTCfvo insertNewCfvo(int i);

    boolean isSetIconSet();

    boolean isSetPercent();

    boolean isSetReverse();

    boolean isSetShowValue();

    void removeCfvo(int i);

    void setCfvoArray(int i, CTCfvo cTCfvo);

    void setCfvoArray(CTCfvo[] cTCfvoArr);

    void setIconSet(STIconSetType.Enum enumR);

    void setPercent(boolean z);

    void setReverse(boolean z);

    void setShowValue(boolean z);

    int sizeOfCfvoArray();

    void unsetIconSet();

    void unsetPercent();

    void unsetReverse();

    void unsetShowValue();

    STIconSetType xgetIconSet();

    XmlBoolean xgetPercent();

    XmlBoolean xgetReverse();

    XmlBoolean xgetShowValue();

    void xsetIconSet(STIconSetType sTIconSetType);

    void xsetPercent(XmlBoolean xmlBoolean);

    void xsetReverse(XmlBoolean xmlBoolean);

    void xsetShowValue(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTIconSet> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cticonset2648type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
