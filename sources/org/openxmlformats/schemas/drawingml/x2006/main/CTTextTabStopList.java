package org.openxmlformats.schemas.drawingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTextTabStopList extends XmlObject {
    public static final DocumentFactory<CTTextTabStopList> Factory;
    public static final SchemaType type;

    CTTextTabStop addNewTab();

    CTTextTabStop getTabArray(int i);

    CTTextTabStop[] getTabArray();

    List<CTTextTabStop> getTabList();

    CTTextTabStop insertNewTab(int i);

    void removeTab(int i);

    void setTabArray(int i, CTTextTabStop cTTextTabStop);

    void setTabArray(CTTextTabStop[] cTTextTabStopArr);

    int sizeOfTabArray();

    static {
        DocumentFactory<CTTextTabStopList> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttexttabstoplistf539type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
