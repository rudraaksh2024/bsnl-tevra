package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPane;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPaneState;

public interface CTPane extends XmlObject {
    public static final DocumentFactory<CTPane> Factory;
    public static final SchemaType type;

    STPane.Enum getActivePane();

    STPaneState.Enum getState();

    String getTopLeftCell();

    double getXSplit();

    double getYSplit();

    boolean isSetActivePane();

    boolean isSetState();

    boolean isSetTopLeftCell();

    boolean isSetXSplit();

    boolean isSetYSplit();

    void setActivePane(STPane.Enum enumR);

    void setState(STPaneState.Enum enumR);

    void setTopLeftCell(String str);

    void setXSplit(double d);

    void setYSplit(double d);

    void unsetActivePane();

    void unsetState();

    void unsetTopLeftCell();

    void unsetXSplit();

    void unsetYSplit();

    STPane xgetActivePane();

    STPaneState xgetState();

    STCellRef xgetTopLeftCell();

    XmlDouble xgetXSplit();

    XmlDouble xgetYSplit();

    void xsetActivePane(STPane sTPane);

    void xsetState(STPaneState sTPaneState);

    void xsetTopLeftCell(STCellRef sTCellRef);

    void xsetXSplit(XmlDouble xmlDouble);

    void xsetYSplit(XmlDouble xmlDouble);

    static {
        DocumentFactory<CTPane> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpaneaab1type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
