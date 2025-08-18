package com.microsoft.schemas.office.visio.x2012.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface PageType extends XmlObject {
    public static final DocumentFactory<PageType> Factory;
    public static final SchemaType type;

    PageSheetType addNewPageSheet();

    RelType addNewRel();

    long getAssociatedPage();

    long getBackPage();

    boolean getBackground();

    long getID();

    boolean getIsCustomName();

    boolean getIsCustomNameU();

    String getName();

    String getNameU();

    PageSheetType getPageSheet();

    RelType getRel();

    long getReviewerID();

    double getViewCenterX();

    double getViewCenterY();

    double getViewScale();

    boolean isSetAssociatedPage();

    boolean isSetBackPage();

    boolean isSetBackground();

    boolean isSetIsCustomName();

    boolean isSetIsCustomNameU();

    boolean isSetName();

    boolean isSetNameU();

    boolean isSetPageSheet();

    boolean isSetReviewerID();

    boolean isSetViewCenterX();

    boolean isSetViewCenterY();

    boolean isSetViewScale();

    void setAssociatedPage(long j);

    void setBackPage(long j);

    void setBackground(boolean z);

    void setID(long j);

    void setIsCustomName(boolean z);

    void setIsCustomNameU(boolean z);

    void setName(String str);

    void setNameU(String str);

    void setPageSheet(PageSheetType pageSheetType);

    void setRel(RelType relType);

    void setReviewerID(long j);

    void setViewCenterX(double d);

    void setViewCenterY(double d);

    void setViewScale(double d);

    void unsetAssociatedPage();

    void unsetBackPage();

    void unsetBackground();

    void unsetIsCustomName();

    void unsetIsCustomNameU();

    void unsetName();

    void unsetNameU();

    void unsetPageSheet();

    void unsetReviewerID();

    void unsetViewCenterX();

    void unsetViewCenterY();

    void unsetViewScale();

    XmlUnsignedInt xgetAssociatedPage();

    XmlUnsignedInt xgetBackPage();

    XmlBoolean xgetBackground();

    XmlUnsignedInt xgetID();

    XmlBoolean xgetIsCustomName();

    XmlBoolean xgetIsCustomNameU();

    XmlString xgetName();

    XmlString xgetNameU();

    XmlUnsignedInt xgetReviewerID();

    XmlDouble xgetViewCenterX();

    XmlDouble xgetViewCenterY();

    XmlDouble xgetViewScale();

    void xsetAssociatedPage(XmlUnsignedInt xmlUnsignedInt);

    void xsetBackPage(XmlUnsignedInt xmlUnsignedInt);

    void xsetBackground(XmlBoolean xmlBoolean);

    void xsetID(XmlUnsignedInt xmlUnsignedInt);

    void xsetIsCustomName(XmlBoolean xmlBoolean);

    void xsetIsCustomNameU(XmlBoolean xmlBoolean);

    void xsetName(XmlString xmlString);

    void xsetNameU(XmlString xmlString);

    void xsetReviewerID(XmlUnsignedInt xmlUnsignedInt);

    void xsetViewCenterX(XmlDouble xmlDouble);

    void xsetViewCenterY(XmlDouble xmlDouble);

    void xsetViewScale(XmlDouble xmlDouble);

    static {
        DocumentFactory<PageType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "pagetype2fcatype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
