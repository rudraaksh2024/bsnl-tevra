package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTLocation extends XmlObject {
    public static final DocumentFactory<CTLocation> Factory;
    public static final SchemaType type;

    long getColPageCount();

    long getFirstDataCol();

    long getFirstDataRow();

    long getFirstHeaderRow();

    String getRef();

    long getRowPageCount();

    boolean isSetColPageCount();

    boolean isSetRowPageCount();

    void setColPageCount(long j);

    void setFirstDataCol(long j);

    void setFirstDataRow(long j);

    void setFirstHeaderRow(long j);

    void setRef(String str);

    void setRowPageCount(long j);

    void unsetColPageCount();

    void unsetRowPageCount();

    XmlUnsignedInt xgetColPageCount();

    XmlUnsignedInt xgetFirstDataCol();

    XmlUnsignedInt xgetFirstDataRow();

    XmlUnsignedInt xgetFirstHeaderRow();

    STRef xgetRef();

    XmlUnsignedInt xgetRowPageCount();

    void xsetColPageCount(XmlUnsignedInt xmlUnsignedInt);

    void xsetFirstDataCol(XmlUnsignedInt xmlUnsignedInt);

    void xsetFirstDataRow(XmlUnsignedInt xmlUnsignedInt);

    void xsetFirstHeaderRow(XmlUnsignedInt xmlUnsignedInt);

    void xsetRef(STRef sTRef);

    void xsetRowPageCount(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTLocation> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlocationc23etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
