package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedByte;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTSheetFormatPr extends XmlObject {
    public static final DocumentFactory<CTSheetFormatPr> Factory;
    public static final SchemaType type;

    long getBaseColWidth();

    boolean getCustomHeight();

    double getDefaultColWidth();

    double getDefaultRowHeight();

    short getOutlineLevelCol();

    short getOutlineLevelRow();

    boolean getThickBottom();

    boolean getThickTop();

    boolean getZeroHeight();

    boolean isSetBaseColWidth();

    boolean isSetCustomHeight();

    boolean isSetDefaultColWidth();

    boolean isSetOutlineLevelCol();

    boolean isSetOutlineLevelRow();

    boolean isSetThickBottom();

    boolean isSetThickTop();

    boolean isSetZeroHeight();

    void setBaseColWidth(long j);

    void setCustomHeight(boolean z);

    void setDefaultColWidth(double d);

    void setDefaultRowHeight(double d);

    void setOutlineLevelCol(short s);

    void setOutlineLevelRow(short s);

    void setThickBottom(boolean z);

    void setThickTop(boolean z);

    void setZeroHeight(boolean z);

    void unsetBaseColWidth();

    void unsetCustomHeight();

    void unsetDefaultColWidth();

    void unsetOutlineLevelCol();

    void unsetOutlineLevelRow();

    void unsetThickBottom();

    void unsetThickTop();

    void unsetZeroHeight();

    XmlUnsignedInt xgetBaseColWidth();

    XmlBoolean xgetCustomHeight();

    XmlDouble xgetDefaultColWidth();

    XmlDouble xgetDefaultRowHeight();

    XmlUnsignedByte xgetOutlineLevelCol();

    XmlUnsignedByte xgetOutlineLevelRow();

    XmlBoolean xgetThickBottom();

    XmlBoolean xgetThickTop();

    XmlBoolean xgetZeroHeight();

    void xsetBaseColWidth(XmlUnsignedInt xmlUnsignedInt);

    void xsetCustomHeight(XmlBoolean xmlBoolean);

    void xsetDefaultColWidth(XmlDouble xmlDouble);

    void xsetDefaultRowHeight(XmlDouble xmlDouble);

    void xsetOutlineLevelCol(XmlUnsignedByte xmlUnsignedByte);

    void xsetOutlineLevelRow(XmlUnsignedByte xmlUnsignedByte);

    void xsetThickBottom(XmlBoolean xmlBoolean);

    void xsetThickTop(XmlBoolean xmlBoolean);

    void xsetZeroHeight(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTSheetFormatPr> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsheetformatprdef7type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
