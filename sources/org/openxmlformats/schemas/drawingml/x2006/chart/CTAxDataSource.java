package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTAxDataSource extends XmlObject {
    public static final DocumentFactory<CTAxDataSource> Factory;
    public static final SchemaType type;

    CTMultiLvlStrRef addNewMultiLvlStrRef();

    CTNumData addNewNumLit();

    CTNumRef addNewNumRef();

    CTStrData addNewStrLit();

    CTStrRef addNewStrRef();

    CTMultiLvlStrRef getMultiLvlStrRef();

    CTNumData getNumLit();

    CTNumRef getNumRef();

    CTStrData getStrLit();

    CTStrRef getStrRef();

    boolean isSetMultiLvlStrRef();

    boolean isSetNumLit();

    boolean isSetNumRef();

    boolean isSetStrLit();

    boolean isSetStrRef();

    void setMultiLvlStrRef(CTMultiLvlStrRef cTMultiLvlStrRef);

    void setNumLit(CTNumData cTNumData);

    void setNumRef(CTNumRef cTNumRef);

    void setStrLit(CTStrData cTStrData);

    void setStrRef(CTStrRef cTStrRef);

    void unsetMultiLvlStrRef();

    void unsetNumLit();

    void unsetNumRef();

    void unsetStrLit();

    void unsetStrRef();

    static {
        DocumentFactory<CTAxDataSource> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctaxdatasource1440type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
