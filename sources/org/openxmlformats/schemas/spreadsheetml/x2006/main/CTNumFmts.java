package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTNumFmts extends XmlObject {
    public static final DocumentFactory<CTNumFmts> Factory;
    public static final SchemaType type;

    CTNumFmt addNewNumFmt();

    long getCount();

    CTNumFmt getNumFmtArray(int i);

    CTNumFmt[] getNumFmtArray();

    List<CTNumFmt> getNumFmtList();

    CTNumFmt insertNewNumFmt(int i);

    boolean isSetCount();

    void removeNumFmt(int i);

    void setCount(long j);

    void setNumFmtArray(int i, CTNumFmt cTNumFmt);

    void setNumFmtArray(CTNumFmt[] cTNumFmtArr);

    int sizeOfNumFmtArray();

    void unsetCount();

    XmlUnsignedInt xgetCount();

    void xsetCount(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTNumFmts> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctnumfmtsb58btype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
