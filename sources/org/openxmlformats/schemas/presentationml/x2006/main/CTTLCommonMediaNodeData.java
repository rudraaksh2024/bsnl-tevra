package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveFixedPercentage;

public interface CTTLCommonMediaNodeData extends XmlObject {
    public static final DocumentFactory<CTTLCommonMediaNodeData> Factory;
    public static final SchemaType type;

    CTTLCommonTimeNodeData addNewCTn();

    CTTLTimeTargetElement addNewTgtEl();

    CTTLCommonTimeNodeData getCTn();

    boolean getMute();

    long getNumSld();

    boolean getShowWhenStopped();

    CTTLTimeTargetElement getTgtEl();

    Object getVol();

    boolean isSetMute();

    boolean isSetNumSld();

    boolean isSetShowWhenStopped();

    boolean isSetVol();

    void setCTn(CTTLCommonTimeNodeData cTTLCommonTimeNodeData);

    void setMute(boolean z);

    void setNumSld(long j);

    void setShowWhenStopped(boolean z);

    void setTgtEl(CTTLTimeTargetElement cTTLTimeTargetElement);

    void setVol(Object obj);

    void unsetMute();

    void unsetNumSld();

    void unsetShowWhenStopped();

    void unsetVol();

    XmlBoolean xgetMute();

    XmlUnsignedInt xgetNumSld();

    XmlBoolean xgetShowWhenStopped();

    STPositiveFixedPercentage xgetVol();

    void xsetMute(XmlBoolean xmlBoolean);

    void xsetNumSld(XmlUnsignedInt xmlUnsignedInt);

    void xsetShowWhenStopped(XmlBoolean xmlBoolean);

    void xsetVol(STPositiveFixedPercentage sTPositiveFixedPercentage);

    static {
        DocumentFactory<CTTLCommonMediaNodeData> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttlcommonmedianodedatab6c2type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
