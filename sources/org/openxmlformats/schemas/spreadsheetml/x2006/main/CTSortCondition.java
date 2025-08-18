package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STIconSetType;

public interface CTSortCondition extends XmlObject {
    public static final DocumentFactory<CTSortCondition> Factory;
    public static final SchemaType type;

    String getCustomList();

    boolean getDescending();

    long getDxfId();

    long getIconId();

    STIconSetType.Enum getIconSet();

    String getRef();

    STSortBy$Enum getSortBy();

    boolean isSetCustomList();

    boolean isSetDescending();

    boolean isSetDxfId();

    boolean isSetIconId();

    boolean isSetIconSet();

    boolean isSetSortBy();

    void setCustomList(String str);

    void setDescending(boolean z);

    void setDxfId(long j);

    void setIconId(long j);

    void setIconSet(STIconSetType.Enum enumR);

    void setRef(String str);

    void setSortBy(STSortBy$Enum sTSortBy$Enum);

    void unsetCustomList();

    void unsetDescending();

    void unsetDxfId();

    void unsetIconId();

    void unsetIconSet();

    void unsetSortBy();

    STXstring xgetCustomList();

    XmlBoolean xgetDescending();

    STDxfId xgetDxfId();

    XmlUnsignedInt xgetIconId();

    STIconSetType xgetIconSet();

    STRef xgetRef();

    STSortBy xgetSortBy();

    void xsetCustomList(STXstring sTXstring);

    void xsetDescending(XmlBoolean xmlBoolean);

    void xsetDxfId(STDxfId sTDxfId);

    void xsetIconId(XmlUnsignedInt xmlUnsignedInt);

    void xsetIconSet(STIconSetType sTIconSetType);

    void xsetRef(STRef sTRef);

    void xsetSortBy(STSortBy sTSortBy);

    static {
        DocumentFactory<CTSortCondition> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsortconditionc4fctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
