package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STPositiveUniversalMeasure;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellComments;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STOrientation;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPageOrder;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPrintError;

public interface CTPageSetup extends XmlObject {
    public static final DocumentFactory<CTPageSetup> Factory;
    public static final SchemaType type;

    boolean getBlackAndWhite();

    STCellComments.Enum getCellComments();

    long getCopies();

    boolean getDraft();

    STPrintError.Enum getErrors();

    long getFirstPageNumber();

    long getFitToHeight();

    long getFitToWidth();

    long getHorizontalDpi();

    String getId();

    STOrientation.Enum getOrientation();

    STPageOrder.Enum getPageOrder();

    String getPaperHeight();

    long getPaperSize();

    String getPaperWidth();

    long getScale();

    boolean getUseFirstPageNumber();

    boolean getUsePrinterDefaults();

    long getVerticalDpi();

    boolean isSetBlackAndWhite();

    boolean isSetCellComments();

    boolean isSetCopies();

    boolean isSetDraft();

    boolean isSetErrors();

    boolean isSetFirstPageNumber();

    boolean isSetFitToHeight();

    boolean isSetFitToWidth();

    boolean isSetHorizontalDpi();

    boolean isSetId();

    boolean isSetOrientation();

    boolean isSetPageOrder();

    boolean isSetPaperHeight();

    boolean isSetPaperSize();

    boolean isSetPaperWidth();

    boolean isSetScale();

    boolean isSetUseFirstPageNumber();

    boolean isSetUsePrinterDefaults();

    boolean isSetVerticalDpi();

    void setBlackAndWhite(boolean z);

    void setCellComments(STCellComments.Enum enumR);

    void setCopies(long j);

    void setDraft(boolean z);

    void setErrors(STPrintError.Enum enumR);

    void setFirstPageNumber(long j);

    void setFitToHeight(long j);

    void setFitToWidth(long j);

    void setHorizontalDpi(long j);

    void setId(String str);

    void setOrientation(STOrientation.Enum enumR);

    void setPageOrder(STPageOrder.Enum enumR);

    void setPaperHeight(String str);

    void setPaperSize(long j);

    void setPaperWidth(String str);

    void setScale(long j);

    void setUseFirstPageNumber(boolean z);

    void setUsePrinterDefaults(boolean z);

    void setVerticalDpi(long j);

    void unsetBlackAndWhite();

    void unsetCellComments();

    void unsetCopies();

    void unsetDraft();

    void unsetErrors();

    void unsetFirstPageNumber();

    void unsetFitToHeight();

    void unsetFitToWidth();

    void unsetHorizontalDpi();

    void unsetId();

    void unsetOrientation();

    void unsetPageOrder();

    void unsetPaperHeight();

    void unsetPaperSize();

    void unsetPaperWidth();

    void unsetScale();

    void unsetUseFirstPageNumber();

    void unsetUsePrinterDefaults();

    void unsetVerticalDpi();

    XmlBoolean xgetBlackAndWhite();

    STCellComments xgetCellComments();

    XmlUnsignedInt xgetCopies();

    XmlBoolean xgetDraft();

    STPrintError xgetErrors();

    XmlUnsignedInt xgetFirstPageNumber();

    XmlUnsignedInt xgetFitToHeight();

    XmlUnsignedInt xgetFitToWidth();

    XmlUnsignedInt xgetHorizontalDpi();

    STRelationshipId xgetId();

    STOrientation xgetOrientation();

    STPageOrder xgetPageOrder();

    STPositiveUniversalMeasure xgetPaperHeight();

    XmlUnsignedInt xgetPaperSize();

    STPositiveUniversalMeasure xgetPaperWidth();

    XmlUnsignedInt xgetScale();

    XmlBoolean xgetUseFirstPageNumber();

    XmlBoolean xgetUsePrinterDefaults();

    XmlUnsignedInt xgetVerticalDpi();

    void xsetBlackAndWhite(XmlBoolean xmlBoolean);

    void xsetCellComments(STCellComments sTCellComments);

    void xsetCopies(XmlUnsignedInt xmlUnsignedInt);

    void xsetDraft(XmlBoolean xmlBoolean);

    void xsetErrors(STPrintError sTPrintError);

    void xsetFirstPageNumber(XmlUnsignedInt xmlUnsignedInt);

    void xsetFitToHeight(XmlUnsignedInt xmlUnsignedInt);

    void xsetFitToWidth(XmlUnsignedInt xmlUnsignedInt);

    void xsetHorizontalDpi(XmlUnsignedInt xmlUnsignedInt);

    void xsetId(STRelationshipId sTRelationshipId);

    void xsetOrientation(STOrientation sTOrientation);

    void xsetPageOrder(STPageOrder sTPageOrder);

    void xsetPaperHeight(STPositiveUniversalMeasure sTPositiveUniversalMeasure);

    void xsetPaperSize(XmlUnsignedInt xmlUnsignedInt);

    void xsetPaperWidth(STPositiveUniversalMeasure sTPositiveUniversalMeasure);

    void xsetScale(XmlUnsignedInt xmlUnsignedInt);

    void xsetUseFirstPageNumber(XmlBoolean xmlBoolean);

    void xsetUsePrinterDefaults(XmlBoolean xmlBoolean);

    void xsetVerticalDpi(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTPageSetup> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpagesetup534dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
