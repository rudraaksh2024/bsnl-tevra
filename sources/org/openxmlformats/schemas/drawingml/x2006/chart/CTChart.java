package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTChart extends XmlObject {
    public static final DocumentFactory<CTChart> Factory;
    public static final SchemaType type;

    CTBoolean addNewAutoTitleDeleted();

    CTSurface addNewBackWall();

    CTDispBlanksAs addNewDispBlanksAs();

    CTExtensionList addNewExtLst();

    CTSurface addNewFloor();

    CTLegend addNewLegend();

    CTPivotFmts addNewPivotFmts();

    CTPlotArea addNewPlotArea();

    CTBoolean addNewPlotVisOnly();

    CTBoolean addNewShowDLblsOverMax();

    CTSurface addNewSideWall();

    CTTitle addNewTitle();

    CTView3D addNewView3D();

    CTBoolean getAutoTitleDeleted();

    CTSurface getBackWall();

    CTDispBlanksAs getDispBlanksAs();

    CTExtensionList getExtLst();

    CTSurface getFloor();

    CTLegend getLegend();

    CTPivotFmts getPivotFmts();

    CTPlotArea getPlotArea();

    CTBoolean getPlotVisOnly();

    CTBoolean getShowDLblsOverMax();

    CTSurface getSideWall();

    CTTitle getTitle();

    CTView3D getView3D();

    boolean isSetAutoTitleDeleted();

    boolean isSetBackWall();

    boolean isSetDispBlanksAs();

    boolean isSetExtLst();

    boolean isSetFloor();

    boolean isSetLegend();

    boolean isSetPivotFmts();

    boolean isSetPlotVisOnly();

    boolean isSetShowDLblsOverMax();

    boolean isSetSideWall();

    boolean isSetTitle();

    boolean isSetView3D();

    void setAutoTitleDeleted(CTBoolean cTBoolean);

    void setBackWall(CTSurface cTSurface);

    void setDispBlanksAs(CTDispBlanksAs cTDispBlanksAs);

    void setExtLst(CTExtensionList cTExtensionList);

    void setFloor(CTSurface cTSurface);

    void setLegend(CTLegend cTLegend);

    void setPivotFmts(CTPivotFmts cTPivotFmts);

    void setPlotArea(CTPlotArea cTPlotArea);

    void setPlotVisOnly(CTBoolean cTBoolean);

    void setShowDLblsOverMax(CTBoolean cTBoolean);

    void setSideWall(CTSurface cTSurface);

    void setTitle(CTTitle cTTitle);

    void setView3D(CTView3D cTView3D);

    void unsetAutoTitleDeleted();

    void unsetBackWall();

    void unsetDispBlanksAs();

    void unsetExtLst();

    void unsetFloor();

    void unsetLegend();

    void unsetPivotFmts();

    void unsetPlotVisOnly();

    void unsetShowDLblsOverMax();

    void unsetSideWall();

    void unsetTitle();

    void unsetView3D();

    static {
        DocumentFactory<CTChart> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctchartc108type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
