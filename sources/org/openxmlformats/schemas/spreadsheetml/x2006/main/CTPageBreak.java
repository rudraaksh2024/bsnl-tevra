package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTPageBreak extends XmlObject {
    public static final DocumentFactory<CTPageBreak> Factory;
    public static final SchemaType type;

    CTBreak addNewBrk();

    CTBreak getBrkArray(int i);

    CTBreak[] getBrkArray();

    List<CTBreak> getBrkList();

    long getCount();

    long getManualBreakCount();

    CTBreak insertNewBrk(int i);

    boolean isSetCount();

    boolean isSetManualBreakCount();

    void removeBrk(int i);

    void setBrkArray(int i, CTBreak cTBreak);

    void setBrkArray(CTBreak[] cTBreakArr);

    void setCount(long j);

    void setManualBreakCount(long j);

    int sizeOfBrkArray();

    void unsetCount();

    void unsetManualBreakCount();

    XmlUnsignedInt xgetCount();

    XmlUnsignedInt xgetManualBreakCount();

    void xsetCount(XmlUnsignedInt xmlUnsignedInt);

    void xsetManualBreakCount(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTPageBreak> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpagebreakeb4ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
