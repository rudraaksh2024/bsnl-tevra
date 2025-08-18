package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STVisibility;

public interface CTBookView extends XmlObject {
    public static final DocumentFactory<CTBookView> Factory;
    public static final SchemaType type;

    CTExtensionList addNewExtLst();

    long getActiveTab();

    boolean getAutoFilterDateGrouping();

    CTExtensionList getExtLst();

    long getFirstSheet();

    boolean getMinimized();

    boolean getShowHorizontalScroll();

    boolean getShowSheetTabs();

    boolean getShowVerticalScroll();

    long getTabRatio();

    STVisibility.Enum getVisibility();

    long getWindowHeight();

    long getWindowWidth();

    int getXWindow();

    int getYWindow();

    boolean isSetActiveTab();

    boolean isSetAutoFilterDateGrouping();

    boolean isSetExtLst();

    boolean isSetFirstSheet();

    boolean isSetMinimized();

    boolean isSetShowHorizontalScroll();

    boolean isSetShowSheetTabs();

    boolean isSetShowVerticalScroll();

    boolean isSetTabRatio();

    boolean isSetVisibility();

    boolean isSetWindowHeight();

    boolean isSetWindowWidth();

    boolean isSetXWindow();

    boolean isSetYWindow();

    void setActiveTab(long j);

    void setAutoFilterDateGrouping(boolean z);

    void setExtLst(CTExtensionList cTExtensionList);

    void setFirstSheet(long j);

    void setMinimized(boolean z);

    void setShowHorizontalScroll(boolean z);

    void setShowSheetTabs(boolean z);

    void setShowVerticalScroll(boolean z);

    void setTabRatio(long j);

    void setVisibility(STVisibility.Enum enumR);

    void setWindowHeight(long j);

    void setWindowWidth(long j);

    void setXWindow(int i);

    void setYWindow(int i);

    void unsetActiveTab();

    void unsetAutoFilterDateGrouping();

    void unsetExtLst();

    void unsetFirstSheet();

    void unsetMinimized();

    void unsetShowHorizontalScroll();

    void unsetShowSheetTabs();

    void unsetShowVerticalScroll();

    void unsetTabRatio();

    void unsetVisibility();

    void unsetWindowHeight();

    void unsetWindowWidth();

    void unsetXWindow();

    void unsetYWindow();

    XmlUnsignedInt xgetActiveTab();

    XmlBoolean xgetAutoFilterDateGrouping();

    XmlUnsignedInt xgetFirstSheet();

    XmlBoolean xgetMinimized();

    XmlBoolean xgetShowHorizontalScroll();

    XmlBoolean xgetShowSheetTabs();

    XmlBoolean xgetShowVerticalScroll();

    XmlUnsignedInt xgetTabRatio();

    STVisibility xgetVisibility();

    XmlUnsignedInt xgetWindowHeight();

    XmlUnsignedInt xgetWindowWidth();

    XmlInt xgetXWindow();

    XmlInt xgetYWindow();

    void xsetActiveTab(XmlUnsignedInt xmlUnsignedInt);

    void xsetAutoFilterDateGrouping(XmlBoolean xmlBoolean);

    void xsetFirstSheet(XmlUnsignedInt xmlUnsignedInt);

    void xsetMinimized(XmlBoolean xmlBoolean);

    void xsetShowHorizontalScroll(XmlBoolean xmlBoolean);

    void xsetShowSheetTabs(XmlBoolean xmlBoolean);

    void xsetShowVerticalScroll(XmlBoolean xmlBoolean);

    void xsetTabRatio(XmlUnsignedInt xmlUnsignedInt);

    void xsetVisibility(STVisibility sTVisibility);

    void xsetWindowHeight(XmlUnsignedInt xmlUnsignedInt);

    void xsetWindowWidth(XmlUnsignedInt xmlUnsignedInt);

    void xsetXWindow(XmlInt xmlInt);

    void xsetYWindow(XmlInt xmlInt);

    static {
        DocumentFactory<CTBookView> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctbookviewf677type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
