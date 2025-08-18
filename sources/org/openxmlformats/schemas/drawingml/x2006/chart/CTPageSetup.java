package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STPositiveUniversalMeasure;

public interface CTPageSetup extends XmlObject {
    public static final DocumentFactory<CTPageSetup> Factory;
    public static final SchemaType type;

    boolean getBlackAndWhite();

    long getCopies();

    boolean getDraft();

    long getFirstPageNumber();

    int getHorizontalDpi();

    STPageSetupOrientation$Enum getOrientation();

    String getPaperHeight();

    long getPaperSize();

    String getPaperWidth();

    boolean getUseFirstPageNumber();

    int getVerticalDpi();

    boolean isSetBlackAndWhite();

    boolean isSetCopies();

    boolean isSetDraft();

    boolean isSetFirstPageNumber();

    boolean isSetHorizontalDpi();

    boolean isSetOrientation();

    boolean isSetPaperHeight();

    boolean isSetPaperSize();

    boolean isSetPaperWidth();

    boolean isSetUseFirstPageNumber();

    boolean isSetVerticalDpi();

    void setBlackAndWhite(boolean z);

    void setCopies(long j);

    void setDraft(boolean z);

    void setFirstPageNumber(long j);

    void setHorizontalDpi(int i);

    void setOrientation(STPageSetupOrientation$Enum sTPageSetupOrientation$Enum);

    void setPaperHeight(String str);

    void setPaperSize(long j);

    void setPaperWidth(String str);

    void setUseFirstPageNumber(boolean z);

    void setVerticalDpi(int i);

    void unsetBlackAndWhite();

    void unsetCopies();

    void unsetDraft();

    void unsetFirstPageNumber();

    void unsetHorizontalDpi();

    void unsetOrientation();

    void unsetPaperHeight();

    void unsetPaperSize();

    void unsetPaperWidth();

    void unsetUseFirstPageNumber();

    void unsetVerticalDpi();

    XmlBoolean xgetBlackAndWhite();

    XmlUnsignedInt xgetCopies();

    XmlBoolean xgetDraft();

    XmlUnsignedInt xgetFirstPageNumber();

    XmlInt xgetHorizontalDpi();

    STPageSetupOrientation xgetOrientation();

    STPositiveUniversalMeasure xgetPaperHeight();

    XmlUnsignedInt xgetPaperSize();

    STPositiveUniversalMeasure xgetPaperWidth();

    XmlBoolean xgetUseFirstPageNumber();

    XmlInt xgetVerticalDpi();

    void xsetBlackAndWhite(XmlBoolean xmlBoolean);

    void xsetCopies(XmlUnsignedInt xmlUnsignedInt);

    void xsetDraft(XmlBoolean xmlBoolean);

    void xsetFirstPageNumber(XmlUnsignedInt xmlUnsignedInt);

    void xsetHorizontalDpi(XmlInt xmlInt);

    void xsetOrientation(STPageSetupOrientation sTPageSetupOrientation);

    void xsetPaperHeight(STPositiveUniversalMeasure sTPositiveUniversalMeasure);

    void xsetPaperSize(XmlUnsignedInt xmlUnsignedInt);

    void xsetPaperWidth(STPositiveUniversalMeasure sTPositiveUniversalMeasure);

    void xsetUseFirstPageNumber(XmlBoolean xmlBoolean);

    void xsetVerticalDpi(XmlInt xmlInt);

    static {
        DocumentFactory<CTPageSetup> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpagesetupdb38type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
